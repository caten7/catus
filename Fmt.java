// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Iterator;
import java.util.Collection;

public class Fmt
{
    public static String msDur_since(final long ms) {
        return msDur(System.currentTimeMillis() - ms);
    }
    
    public static String yesNo(final boolean b) {
        return b ? "Yes" : "No";
    }
    
    public static String msDur(final long ms) {
        if (ms < 0L) {
            return "???";
        }
        if (ms < 1000L) {
            return String.format("%dms", ms);
        }
        if (ms < 10000L) {
            return String.format("%.1fs", ms / 1000.0);
        }
        if (ms < 60000L) {
            return String.format("%ds", ms / 1000L);
        }
        if (ms < 600000L) {
            return String.format("%.1fm", ms / 60000.0);
        }
        if (ms < 3600000L) {
            return String.format("%dm", ms / 60000L);
        }
        if (ms < 36000000L) {
            return String.format("%.1fh", ms / 3600000.0);
        }
        if (ms < 86400000L) {
            return String.format("%dh", ms / 3600000L);
        }
        return String.format("%.1fd", ms / 8.64E7);
    }
    
    public static String mm_ss(final int ms) {
        return String.format("%02d:%02d", ms / 60000 % 60, ms / 1000 % 60);
    }
    
    public static String repeat(final char ch, final int len) {
        final char[] v = new char[len];
        for (int i = 0; i < len; ++i) {
            v[i] = ch;
        }
        return new String(v);
    }
    
    public static void plural(final StringBuilder sb, final int num, final String str, final String s) {
        sb.append(num);
        sb.append(' ');
        sb.append(str);
        if (num != 1) {
            sb.append(s);
        }
    }
    
    static void padLeft(final StringBuilder sb, final String s, final int w) {
        if (s.length() > w) {
            sb.append(s.substring(0, w));
        }
        else {
            for (int i = s.length(); i < w; ++i) {
                sb.append(' ');
            }
            sb.append(s);
        }
    }
    
    static void padRight(final StringBuilder sb, final String s, final int w) {
        if (s.length() > w) {
            sb.append(s.substring(0, w));
        }
        else {
            sb.append(s);
            for (int i = s.length(); i < w; ++i) {
                sb.append(' ');
            }
        }
    }
    
    public static String fixedWidthJoin(final int width, final String a, final String sep, final String b) {
        final StringBuilder sb = new StringBuilder(width * 2 + sep.length());
        if (a.length() > width) {
            sb.append(a.substring(0, width));
        }
        else {
            sb.append(a);
            for (int i = sb.length(); i < width; ++i) {
                sb.append(' ');
            }
        }
        sb.append(sep);
        if (b.length() > width) {
            sb.append(b.substring(0, width));
        }
        else {
            sb.append(b);
        }
        return sb.toString();
    }
    
    public static String msDuration_precise(final long ms) {
        if (ms < 0L) {
            return "???";
        }
        if (ms < 1000L) {
            return String.format("%d ms", ms);
        }
        if (ms < 120000L) {
            return String.format("%.2f sec", ms / 1000.0);
        }
        return String.format("%.2f min", ms / 60000.0);
    }
    
    public static String msDuration(final long ms) {
        if (ms < 0L) {
            return "???";
        }
        if (ms < 1000L) {
            return String.format("%d ms", ms);
        }
        if (ms < 10000L) {
            return String.format("%.1f sec", ms / 1000.0);
        }
        if (ms < 120000L) {
            return String.format("%d sec", ms / 1000L);
        }
        if (ms < 600000L) {
            return String.format("%.1f min", ms / 60000.0);
        }
        if (ms < 3600000L) {
            return String.format("%d mins", ms / 60000L);
        }
        if (ms < 36000000L) {
            return String.format("%.1f hours", ms / 3600000.0);
        }
        if (ms < 86400000L) {
            return String.format("%d hours", ms / 3600000L);
        }
        return String.format("%.1f days", ms / 8.64E7);
    }
    
    static String msms(final int ms) {
        return String.format("%02d:%02d.%03d", ms / 60000 % 60, ms / 1000 % 60, ms % 1000);
    }
    
    static String bigNum(double num) {
        if (num < 0.0) {
            return "-" + bigNum(-num);
        }
        if (num < 10000.0) {
            return String.format("%.0f", num);
        }
        if (num != num) {
            return "???";
        }
        final String suffix = "KMBT";
        int i = 0;
        while (true) {
            num /= 1000.0;
            if (num < 1000.0) {
                break;
            }
            ++i;
        }
        if (num < 10.0) {
            return String.format("%.3f%c", num, "KMBT".charAt(i));
        }
        if (num < 100.0) {
            return String.format("%.2f%c", num, "KMBT".charAt(i));
        }
        return String.format("%.1f%c", num, "KMBT".charAt(i));
    }
    
    static String bigNum_signed(final double num) {
        return (num < 0.0) ? ("-" + bigNum(-num)) : ("+" + bigNum(num));
    }
    
    public static String fixed(final String x, final int n) {
        final int len = x.length();
        if (len < n) {
            final StringBuilder sb = new StringBuilder(n);
            sb.append(x);
            for (int i = len; i < n; ++i) {
                sb.append(' ');
            }
            return sb.toString();
        }
        return x.substring(0, n);
    }
    
    public static String join(final Collection c, final String sep) {
        if (c == null || c.isEmpty()) {
            return "";
        }
        final StringBuilder sb = new StringBuilder((sep.length() + 4) * (1 + c.size()));
        final Iterator iter = c.iterator();
        sb.append(iter.next());
        while (iter.hasNext()) {
            sb.append(sep);
            sb.append(iter.next());
        }
        return sb.toString();
    }
    
    public static String dropOneExt(String x) {
        final int pos = x.lastIndexOf(46);
        if (pos >= 0) {
            x = x.substring(0, pos);
        }
        return x.trim();
    }
    
    public static String exception(final Throwable err) {
        final String msg = err.getMessage();
        return (msg == null || msg.isEmpty()) ? err.getClass().getName() : err.getMessage();
    }
    
    public static void append(final StringBuilder sb, final String thing) {
        append(sb, thing, ",");
    }
    
    public static void append(final StringBuilder sb, final String thing, final String sep) {
        if (sb.length() > 0) {
            sb.append(sep);
        }
        sb.append(thing);
    }
}
