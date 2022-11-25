// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.net.URLConnection;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import javax.xml.bind.DatatypeConverter;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.awt.Image;
import java.io.IOException;
import java.io.Closeable;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.LinkedList;
import java.util.Set;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;
import java.nio.charset.Charset;

public class HttpCache
{
    public static final int NEVER = -1;
    public static final int ONCE = 0;
    public static final int EACH_MINUTE = 60000;
    public static final int HOURLY = 3600000;
    public static final int DAILY = 86400000;
    public static final int WEEKLY = 604800000;
    public static final int SRC_LIVE = 1;
    public static final int SRC_FRESH = 2;
    public static final int SRC_STALE = 4;
    public static final int SRC_CURRENT = 3;
    public static final int SRC_ANY = 7;
    static final Charset CHARSET;
    public final File cacheDir;
    public static final String HASH_INJECT_TOKEN = "$#$";
    public static final String NAME_INJECT_TOKEN = "$N$";
    
    public HttpCache() {
        this.cacheDir = new File("Cache");
    }
    
    public void cleanCache_except(final String... skipArr) {
        this.cleanCache(this.cacheDir, skipArr);
    }
    
    public void cleanCache_matchingURL(final String url0) {
        URL url;
        try {
            url = new URL(url0);
        }
        catch (MalformedURLException err) {
            return;
        }
        this.cleanCache_only(url.getHost(), new String[0]);
    }
    
    public File getDir_matchingURL(final String url) {
        try {
            return new File(this.cacheDir, new URL(url).getHost());
        }
        catch (MalformedURLException err) {
            return null;
        }
    }
    
    public void cleanCache_matchingURL_files(final String url, final Set<String> files) {
        final File dir = this.getDir_matchingURL(url);
        if (dir == null || !dir.isDirectory()) {
            return;
        }
        for (final File x : dir.listFiles()) {
            if (x.isFile() && files.contains(x.getName())) {
                x.delete();
            }
        }
    }
    
    public void cleanCache_only(final String dir, final String... skipArr) {
        this.cleanCache(new File(this.cacheDir, dir), skipArr);
    }
    
    void cleanCache(final File dir0, final String[] skipArr) {
        if (dir0 == null || !dir0.isDirectory()) {
            return;
        }
        final LinkedList<File> queue = new LinkedList<File>();
        final LinkedList<File> dirs = new LinkedList<File>();
        final TreeSet<String> skipSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        for (final String x : skipArr) {
            skipSet.add(x.replace('/', File.separatorChar));
        }
        final String cachePath = dir0.getAbsolutePath();
        queue.add(dir0);
        while (!queue.isEmpty()) {
            final File dir = queue.removeLast();
            for (final File x2 : dir.listFiles()) {
                String path = x2.getAbsolutePath();
                final boolean sub = path.startsWith(cachePath);
                path = path.substring(cachePath.length() + 1);
                if (!skipSet.contains(path)) {
                    if (x2.isFile()) {
                        x2.delete();
                    }
                    else if (sub && x2.isDirectory()) {
                        queue.add(x2);
                    }
                }
            }
            dirs.push(dir);
        }
        for (final File x3 : dirs) {
            x3.delete();
        }
    }
    
    private byte[] readFile(final File f) throws IOException {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(f, "r");
            final int len = (int)raf.length();
            final byte[] temp = new byte[len];
            raf.readFully(temp, 0, len);
            raf.close();
            return temp;
        }
        catch (IOException err) {
            Utils.silentClose(raf);
            throw err;
        }
    }
    
    public Image getImage(final String url, final String name, final int maxAge) {
        return this.getImage(url, name, maxAge, 7);
    }
    
    public Image getImage(final String url, final String name, final int maxAge, final int mode) {
        final Pair<byte[], Boolean> temp = this.getData(url, name, maxAge, mode);
        if (temp.cdr) {
            throw new Err("Image Download Error", "No image data available at url: " + url);
        }
        try {
            return ImageIO.read(new ByteArrayInputStream(temp.car));
        }
        catch (IOException err) {
            throw new Err("Image Read Error", err.getMessage());
        }
    }
    
    public String getStr_silent(final String url, final String name, final int maxAge) {
        return this.getStr_silent(url, name, maxAge, 7);
    }
    
    public String getStr_silent(final String url, final String name, final int maxAge, final int mode) {
        try {
            final Pair<String, Boolean> temp = this.getStr(url, name, maxAge, mode);
            return ((boolean)temp.cdr) ? null : temp.car;
        }
        catch (RuntimeException err) {
            return null;
        }
    }
    
    public Pair<String, Boolean> getStr(final String url, final String name, final int maxAge) {
        return this.getStr(url, name, maxAge, 7);
    }
    
    public Pair<String, Boolean> getStr(final String url, final String name, final int maxAge, final int mode) {
        final Pair<byte[], Boolean> temp = this.getData(url, name, maxAge, mode);
        return new Pair<String, Boolean>(new String(temp.car, HttpCache.CHARSET), temp.cdr);
    }
    
    public File getFile(final String base, final String name) {
        return new File(new File(this.cacheDir, base), name);
    }
    
    public Pair<byte[], Boolean> getData(final String url0, String name, final int maxAge, final int mode) {
        URL url;
        try {
            url = new URL(url0);
        }
        catch (MalformedURLException err) {
            throw new Err("Invalid URL", err.getMessage());
        }
        if (name != null) {
            if (name.isEmpty()) {
                name = url.getFile();
                final int pos = name.lastIndexOf(47);
                if (pos >= 0) {
                    name = name.substring(pos + 1);
                }
                if (name.isEmpty()) {
                    name = null;
                }
            }
            else {
                if (name.contains("$#$")) {
                    name = name.replace("$#$", DatatypeConverter.printHexBinary(Utils.sha1(url.toString().getBytes(HttpCache.CHARSET))));
                }
                if (name.contains("$N$")) {
                    String fn = fn = url.getFile();
                    final int pos2 = fn.lastIndexOf(47);
                    if (pos2 >= 0) {
                        fn = fn.substring(pos2 + 1);
                    }
                    name = name.replace("$N$", fn);
                }
            }
        }
        if (name == null) {
            name = DatatypeConverter.printHexBinary(Utils.sha1(url.toString().getBytes(HttpCache.CHARSET)));
        }
        final File dir = new File(this.cacheDir, url.getHost());
        final File file = new File(dir, name);
        Label_0277: {
            if (maxAge >= 0 && file.isFile() && (mode & 0x2) > 0) {
                if (maxAge != 0) {
                    if (System.currentTimeMillis() - file.lastModified() >= maxAge) {
                        break Label_0277;
                    }
                }
                try {
                    return new Pair<byte[], Boolean>(this.readFile(file), Boolean.FALSE);
                }
                catch (IOException ex) {}
            }
        }
        if ((mode & 0x1) == 0x0) {
            throw new Err("File Not Found", "Online mode not allowed");
        }
        try {
            boolean error = false;
            final URLConnection c = url.openConnection();
            c.setConnectTimeout(5000);
            c.setRequestProperty("User-Agent", "Mozilla/5.0");
            InputStream in;
            try {
                in = c.getInputStream();
            }
            catch (FileNotFoundException err2) {
                if (!(c instanceof HttpURLConnection)) {
                    throw err2;
                }
                in = ((HttpURLConnection)c).getErrorStream();
                error = true;
            }
            byte[] buf = new byte[4096];
            int len = 0;
            while (true) {
                final int num = in.read(buf, len, buf.length - len);
                if (num == -1) {
                    break;
                }
                if (num <= 0) {
                    continue;
                }
                len += num;
                if (len != buf.length) {
                    continue;
                }
                buf = Arrays.copyOf(buf, len * 2);
            }
            in.close();
            if (len < buf.length) {
                buf = Arrays.copyOf(buf, len);
            }
            if (maxAge >= 0 && !error && (dir.isDirectory() || dir.mkdirs())) {
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(file);
                    out.write(buf, 0, len);
                    out.close();
                }
                catch (IOException err4) {
                    Utils.silentClose(out);
                    file.delete();
                }
            }
            return new Pair<byte[], Boolean>(buf, error);
        }
        catch (IOException err3) {
            if ((mode & 0x4) > 0) {
                try {
                    return new Pair<byte[], Boolean>(this.readFile(file), Boolean.FALSE);
                }
                catch (IOException ex2) {}
            }
            if (err3 instanceof FileNotFoundException) {
                throw new Err("File Not Found", err3.getMessage());
            }
            throw new Err("Download Error", err3.toString());
        }
    }
    
    static {
        CHARSET = Charset.forName("UTF-8");
    }
    
    static class Err extends RuntimeException
    {
        private Err(final String title, final String msg) {
            super(title + ": " + msg);
        }
    }
}
