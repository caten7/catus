// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Iterator;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Set;
import java.util.Comparator;

public abstract class ReforgerBase
{
    static final Comparator<int[]> CMP_PERM;
    public int resultBestIndex;
    public ProfilePerm[] results;
    public Set<SlotT> slotSet;
    public ProgBarProxy progBar;
    public final AtomicBoolean abort;
    
    public ReforgerBase() {
        this.progBar = ProgBarProxy.NONE;
        this.abort = new AtomicBoolean();
    }
    
    static int gemDiffs(final Gem[] a, final Gem[] b, final int len) {
        final LinkedList<Gem> v = new LinkedList<Gem>();
        Collections.addAll(v, b);
        for (int i = 0; i < len; ++i) {
            final Iterator<Gem> iter = v.iterator();
            final Gem x = a[i];
            if (x != null) {
                while (iter.hasNext()) {
                    if (Gem.sameAs(x, iter.next())) {
                        iter.remove();
                        break;
                    }
                }
            }
        }
        return v.size();
    }
    
    public abstract void reforge(final API p0, final Profile p1, final Profile p2);
    
    static {
        CMP_PERM = new Comparator<int[]>() {
            @Override
            public int compare(final int[] a, final int[] b) {
                for (int i = 0; i < a.length; ++i) {
                    final int cmp = a[i] - b[i];
                    if (cmp != 0) {
                        return cmp;
                    }
                }
                return 0;
            }
        };
    }
    
    static class Scored
    {
        final double score;
        final int[] cfg;
        final int[] sum;
        static final Comparator<Scored> CMP;
        
        Scored(final double score, final int[] cfg, final int[] sum) {
            this.score = score;
            this.cfg = cfg;
            this.sum = sum;
        }
        
        static {
            CMP = new Comparator<Scored>() {
                @Override
                public int compare(final Scored a, final Scored b) {
                    return Double.compare(b.score, a.score);
                }
            };
        }
    }
}
