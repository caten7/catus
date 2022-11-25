// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Comparator;

public class Tally<T>
{
    final T key;
    int count;
    public static final Comparator<Tally> CMP;
    
    Tally(final T key) {
        this.key = key;
    }
    
    static {
        CMP = new Comparator<Tally>() {
            @Override
            public int compare(final Tally a, final Tally b) {
                final int cmp = b.count - a.count;
                return (cmp == 0) ? a.key.toString().compareTo(b.key.toString()) : cmp;
            }
        };
    }
}
