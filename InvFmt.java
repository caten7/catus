// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.text.ParseException;
import java.text.NumberFormat;

public class InvFmt
{
    static final IntPair<String>[] ms_suffixes;
    
    public static IntDist parseIntDist_time(String s, final String fn, final int scale) {
        s = s.trim();
        if (s.isEmpty()) {
            return null;
        }
        final int pos = s.indexOf(45);
        if (pos == -1) {
            final int t = parse_time(s, fn, scale, true);
            return new IntDist.Const(t);
        }
        final int lo = parse_time(s.substring(0, pos), fn + "/LowerBound", scale, true);
        final int hi = parse_time(s.substring(pos + 1), fn + "/UpperBound", scale, true);
        if (lo > hi) {
            throw new RuntimeException(fn + " is empty uniform distribution");
        }
        if (lo == hi) {
            return new IntDist.Const(lo);
        }
        return new IntDist.Uniform(lo, hi);
    }
    
    public static double parseLocalDouble(final String wut) {
        final NumberFormat nf = NumberFormat.getInstance();
        try {
            return nf.parse(wut).doubleValue();
        }
        catch (ParseException err) {
            throw new NumberFormatException();
        }
    }
    
    public static int parse_time(String s, final String fn, int scale, final boolean allowEmpty) {
        if (s == null) {
            if (allowEmpty) {
                return 0;
            }
            throw new RuntimeException(String.format("%s was not provided", fn));
        }
        else {
            s = s.trim();
            if (s.isEmpty()) {
                if (allowEmpty) {
                    return 0;
                }
                throw new RuntimeException(String.format("%s was not provided", fn));
            }
            else {
                if (s.indexOf(58) != -1) {
                    final String[] v = s.split(":");
                    final int n = v.length;
                    if (n > 3) {
                        throw new RuntimeException(String.format("%s has too many (:) separators", fn));
                    }
                    try {
                        final double secs = parseLocalDouble(v[n - 1]);
                        if (secs < 0.0 || (secs >= 60.0 && n > 1)) {
                            throw new NumberFormatException();
                        }
                        int ms = (int)(secs * 1000.0);
                        if (n >= 2) {
                            final int next = Integer.parseInt(v[n - 2]);
                            if (next < 0 || (next >= 60 && n > 2)) {
                                throw new NumberFormatException();
                            }
                            ms += 60000 * next;
                        }
                        if (n == 3) {
                            final int next = Integer.parseInt(v[0]);
                            if (next < 0) {
                                throw new NumberFormatException();
                            }
                            ms += 3600000 * next;
                        }
                        return ms;
                    }
                    catch (RuntimeException err) {
                        throw new RuntimeException(String.format("%s has an invalid (H:M:S.MS) time format", fn));
                    }
                }
                for (final IntPair<String> x : InvFmt.ms_suffixes) {
                    if (s.endsWith(x.val)) {
                        s = s.substring(0, s.length() - x.val.length());
                        scale = x.key;
                        break;
                    }
                }
                double val;
                try {
                    val = parseLocalDouble(s);
                }
                catch (NumberFormatException err2) {
                    throw new RuntimeException(String.format("%s is not a valid time", fn));
                }
                if (val < 0.0 || Double.isNaN(val) || Double.isInfinite(val)) {
                    throw new RuntimeException(String.format("%s is not a positive, finite time", fn));
                }
                return (int)(val * scale + 0.5);
            }
        }
    }
    
    public static double parse_multi(String s, final String fn, final boolean allowEmpty) {
        s = checkEmpty(s, fn, allowEmpty);
        if (s == null) {
            return 0.0;
        }
        final boolean perc = s.endsWith("%");
        if (perc || s.endsWith("x")) {
            s = s.substring(0, s.length() - 1).trim();
        }
        double val;
        try {
            val = parseLocalDouble(s);
        }
        catch (NumberFormatException err) {
            throw new RuntimeException(String.format("%s is not a valid multiplier", fn));
        }
        if (perc) {
            val = 1.0 + val / 100.0;
        }
        if (val < 0.0) {
            throw new RuntimeException(String.format("%s must be a non-negative multiplier (%+.2f)", fn, val));
        }
        return val;
    }
    
    private static String checkEmpty(String s, final String fn, final boolean allowEmpty) {
        if (s == null) {
            if (allowEmpty) {
                return null;
            }
            throw new RuntimeException(String.format("%s was not provided", fn));
        }
        else {
            s = s.trim();
            if (!s.isEmpty()) {
                return s;
            }
            if (allowEmpty) {
                return null;
            }
            throw new RuntimeException(String.format("%s was not provided", fn));
        }
    }
    
    public static double parse_bigNum(String s, final String fn, final double def, final double min, final double max, final boolean allowEmpty) {
        s = checkEmpty(s, fn, allowEmpty);
        if (s == null) {
            return def;
        }
        s = s.toUpperCase();
        try {
            double scale = 1.0;
            if (s.endsWith("K")) {
                scale = 1000.0;
            }
            else if (s.endsWith("M")) {
                scale = 1000000.0;
            }
            else if (s.endsWith("B")) {
                scale = 1.0E9;
            }
            else if (s.endsWith("T")) {
                scale = 1.0E12;
            }
            if (scale > 1.0) {
                s = s.substring(0, s.length() - 1).trim();
            }
            final double val = scale * parseLocalDouble(s);
            if (Double.isNaN(val) || Double.isInfinite(val)) {
                throw new RuntimeException(String.format("%s must be a real number (%f)", fn, val));
            }
            if (val < min || val > max) {
                throw new RuntimeException(String.format("%s must be within [%f,%f]", fn, min, max));
            }
            return val;
        }
        catch (NumberFormatException err) {
            throw new RuntimeException(String.format("%s is not a number (%s)", fn, s));
        }
    }
    
    public static double parse_percent(String s, final String fn, final double def, final double min, final double max, final boolean allowEmpty) {
        s = checkEmpty(s, fn, allowEmpty);
        if (s == null) {
            return def;
        }
        s = s.toUpperCase();
        try {
            double scale = 1.0;
            if (s.endsWith("%")) {
                scale = 0.01;
                s = s.substring(0, s.length() - 1).trim();
            }
            final double val = scale * parseLocalDouble(s);
            if (Double.isNaN(val) || Double.isInfinite(val)) {
                throw new RuntimeException(String.format("%s must be a real number (%f)", fn, val));
            }
            if (val < min || val > max) {
                throw new RuntimeException(String.format("%s must be within [%f%%,%f%%]", fn, 100.0 * min, 100.0 * max));
            }
            return val;
        }
        catch (NumberFormatException err) {
            throw new RuntimeException(String.format("%s is not a number (%s)", fn, s));
        }
    }
    
    static {
        ms_suffixes = new IntPair[] { new IntPair(1000, (T)"secs"), new IntPair(60000, (T)"mins"), new IntPair(1000, (T)"sec"), new IntPair(60000, (T)"min"), new IntPair(1, (T)"ms"), new IntPair(3600000, (T)"hr"), new IntPair(3600000, (T)"h"), new IntPair(60000, (T)"m"), new IntPair(1000, (T)"s") };
    }
}
