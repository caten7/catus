// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.net.URI;
import java.util.Map;
import java.util.Iterator;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Collection;
import java.awt.Desktop;
import java.net.URISyntaxException;
import java.io.FileInputStream;
import java.io.File;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.io.IOException;
import java.io.Closeable;
import javax.swing.JDialog;

public class Utils
{
    public static final String NL;
    
    public static String tt_simple(final String title, final String... a) {
        final StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        if (title != null) {
            sb.append("<b>");
            sb.append(title);
            sb.append("</b><br/>");
        }
        for (int i = 0; i < a.length; ++i) {
            if (i > 0) {
                sb.append("<br/>");
            }
            sb.append(a[i]);
        }
        sb.append("</html>");
        return sb.toString();
    }
    
    public static <T extends JDialog> T makeSheet(final T dialog) {
        if (isMac()) {
            dialog.getRootPane().putClientProperty("apple.awt.documentModalSheet", "true");
        }
        return dialog;
    }
    
    public static String windowTitle(final String name, final int version) {
        return String.format("%s: v%d", name, version);
    }
    
    public static <T> void decompose(long p, final T[][] space, final T[] dst) {
        for (int i = 0; i < space.length; ++i) {
            final T[] v = space[i];
            final int n = v.length;
            final int x = (int)(p % n);
            p /= n;
            dst[i] = v[x];
        }
    }
    
    public static <T extends Enum> T getEnum(final T[] enums, final String name, final T backup) {
        for (final T x : enums) {
            if (x.name().equalsIgnoreCase(name)) {
                return x;
            }
        }
        return backup;
    }
    
    static void silentClose(final Closeable x) {
        if (x != null) {
            try {
                x.close();
            }
            catch (IOException ex) {}
        }
    }
    
    static String urlDecode(final String x) {
        try {
            return URLDecoder.decode(x, "UTF-8");
        }
        catch (UnsupportedEncodingException fuckyou) {
            return x;
        }
    }
    
    static String urlEncode(final String x) {
        try {
            return URLEncoder.encode(x, "UTF-8");
        }
        catch (UnsupportedEncodingException fuckyou) {
            return x;
        }
    }
    
    public static String htmlEscape(final String s) {
        final int n = s.length();
        final StringBuilder sb = new StringBuilder(n * 2);
        for (int i = 0; i < n; ++i) {
            final char ch = s.charAt(i);
            if (ch > '\u007f') {
                sb.append("&#");
                sb.append((int)ch);
                sb.append(';');
            }
            else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
    
    public static void sleep(final long t) {
        if (t > 0L) {
            try {
                Thread.sleep(t);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public static boolean parseTruth(String x) {
        if (x == null) {
            return false;
        }
        x = x.trim();
        if (x.isEmpty()) {
            return false;
        }
        switch (x.charAt(0)) {
            case '1':
            case 'T':
            case 'Y':
            case 't':
            case 'y': {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static int editDist(String a, String b) {
        if (a.length() > b.length()) {
            final String x = a;
            a = b;
            b = x;
        }
        int[] mat1 = new int[a.length() + 1];
        int[] mat2 = new int[a.length() + 1];
        for (int i = 1; i <= a.length(); ++i) {
            mat1[i] = i;
        }
        mat2[0] = 1;
        for (int j = 1; j <= b.length(); ++j) {
            for (int k = 1; k <= a.length(); ++k) {
                final int c = (a.charAt(k - 1) != b.charAt(j - 1)) ? 1 : 0;
                mat2[k] = Math.min(mat1[k - 1] + c, Math.min(mat1[k] + 1, mat2[k - 1] + 1));
            }
            final int[] temp = mat1;
            mat1 = mat2;
            mat2 = temp;
            mat2[0] = mat1[0] + 1;
        }
        return mat1[a.length()];
    }
    
    static String readFile(final File f) throws IOException {
        final FileInputStream in = new FileInputStream(f);
        final byte[] buf = new byte[(int)f.length()];
        int result;
        for (int len = buf.length, total = 0; total < len; total += result) {
            result = in.read(buf, total, len - total);
            if (result == -1) {
                break;
            }
        }
        return new String(buf, "UTF-8");
    }
    
    public static File getJarFile() {
        try {
            final File f = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            if (f.isFile() && f.getName().endsWith(".jar")) {
                return f;
            }
        }
        catch (URISyntaxException ex) {}
        return null;
    }
    
    public static boolean isMac() {
        return System.getProperty("os.name").contains("Mac");
    }
    
    public static boolean isWindows() {
        return System.getProperty("os.name").contains("Windows");
    }
    
    public static boolean openFile(final File f) {
        if (f == null || !f.exists()) {
            return false;
        }
        if (f.isDirectory() && isWindows()) {
            try {
                final Process proc = Runtime.getRuntime().exec("explorer /root," + f.getAbsolutePath());
                try {
                    proc.waitFor();
                }
                catch (InterruptedException ex) {}
                return true;
            }
            catch (IOException err) {
                return false;
            }
        }
        try {
            Desktop.getDesktop().open(f);
            return true;
        }
        catch (IOException err) {
            return false;
        }
    }
    
    public static void writeLines(final File f, final Collection<String> lines) throws IOException {
        BufferedWriter w = null;
        try {
            w = new BufferedWriter(new FileWriter(f));
            for (final String x : lines) {
                w.append((CharSequence)x);
                w.newLine();
            }
            w.close();
        }
        catch (IOException err) {
            silentClose(w);
            f.delete();
            throw err;
        }
    }
    
    public static int[] range(int to, final int from) {
        if (to > from) {
            return new int[0];
        }
        final int len = 1 + from - to;
        final int[] v = new int[len];
        for (int i = 0; i < len; ++i) {
            v[i] = to++;
        }
        return v;
    }
    
    public static <K> double getMapDouble(final Map<K, Double> map, final K key, final double nope) {
        final Double val = map.get(key);
        return (val == null) ? nope : val;
    }
    
    public static Class firstNamedClass(final Object x) {
        if (x == null) {
            return Object.class;
        }
        final Class c = x.getClass();
        if (c.isAnonymousClass()) {
            return c.getSuperclass();
        }
        return c;
    }
    
    public static boolean openURL(final String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
            return true;
        }
        catch (Exception err) {
            return false;
        }
    }
    
    public static void setClipboard(final String s) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(s), null);
    }
    
    public static byte[] sha1(final byte[] dat) {
        try {
            return MessageDigest.getInstance("SHA-1").digest(dat);
        }
        catch (NoSuchAlgorithmException err) {
            throw new IllegalArgumentException(err);
        }
    }
    
    public static String sha1(final String x) {
        return DatatypeConverter.printHexBinary(sha1(x.getBytes(Charset.forName("UTF-8"))));
    }
    
    static {
        NL = System.getProperty("line.separator");
    }
}
