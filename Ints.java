// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Comparator;

public class Ints
{
    public static final Comparator<int[]> CMP_INTS;
    
    public static int cmp(final int[] a, final int[] b) {
        if (a == null) {
            return (b != null) ? 1 : 0;
        }
        if (b == null) {
            return -1;
        }
        final int aa = a.length;
        final int bb = b.length;
        if (aa < bb) {
            return -1;
        }
        if (aa > bb) {
            return 1;
        }
        for (int i = 0; i < aa; ++i) {
            final int aaa = a[i];
            final int bbb = b[i];
            if (aaa < bbb) {
                return -1;
            }
            if (aaa > bbb) {
                return 1;
            }
        }
        return 0;
    }
    
    static {
        CMP_INTS = new Comparator<int[]>() {
            @Override
            public int compare(final int[] a, final int[] b) {
                return Ints.cmp(a, b);
            }
        };
    }
}
