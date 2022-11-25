// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class Maths
{
    public static int floor(final double x) {
        return (int)x;
    }
    
    public static int ceil(final double x) {
        return -(int)(-x);
    }
    
    static int mean(final int[] v) {
        return mean(v, v.length);
    }
    
    static int mean(final int[] v, final int len) {
        if (len <= 0) {
            return 0;
        }
        double sum = 0.0;
        for (int i = 0; i < len; ++i) {
            sum += v[i];
        }
        return (int)(sum / len);
    }
    
    public static double mid(final double[] v, final int n) {
        final int h = n / 2;
        return (n % 1 == 0) ? (0.5 * (v[h] + v[h + 1])) : v[h];
    }
    
    static double mean(final double[] v) {
        return mean(v, 0, v.length);
    }
    
    static double mean(final double[] v, final int len) {
        return mean(v, 0, len);
    }
    
    static double mean(final double[] v, final int a, final int b) {
        if (a >= b) {
            return 0.0;
        }
        double sum = 0.0;
        for (int i = a; i < b; ++i) {
            sum += v[i];
        }
        return sum / (b - a);
    }
    
    static double stdev(final double[] v) {
        return stdev(v, v.length);
    }
    
    static double stdev(final double[] v, final int len) {
        return stdev(v, len, mean(v, len));
    }
    
    static double stdev(final double[] v, final int len, final double mean) {
        return (len < 2) ? 0.0 : Math.sqrt(var(v, len, mean));
    }
    
    static double var(final double[] v, final int len, final double mean) {
        if (len < 2) {
            return 0.0;
        }
        double sum = 0.0;
        for (int i = 0; i < len; ++i) {
            final double x = v[i] - mean;
            sum += x * x;
        }
        return sum / (len - 1);
    }
    
    static long gcd_bothPositive(int p, int q) {
        if (p < q) {
            final int r = p;
            p = q;
            q = r;
        }
        while (q > 0) {
            final int r = p % q;
            p = q;
            q = r;
        }
        return p;
    }
}
