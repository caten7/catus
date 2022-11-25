// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.HashMap;

public class ReforgePair
{
    private static final HashMap<Integer, ReforgePair> memo;
    public final StatT src;
    public final StatT dst;
    private static final StatT[] statTable;
    
    public static ReforgePair make(final StatT src, final StatT dst) {
        final int hash = StatT.NUM * src.index + dst.index;
        ReforgePair temp = ReforgePair.memo.get(hash);
        if (temp == null) {
            temp = new ReforgePair(src, dst);
            ReforgePair.memo.put(hash, temp);
        }
        return temp;
    }
    
    public ReforgePair(final StatT src, final StatT dst) {
        this.src = src;
        this.dst = dst;
    }
    
    @Override
    public String toString() {
        return String.format("%s > %s", this.src, this.dst);
    }
    
    public int getCode() {
        final int s = indexOf(this.src);
        final int d = indexOf(this.dst);
        return 113 + s * 7 + ((d > s) ? (d - 1) : d);
    }
    
    private static int indexOf(final StatT x) {
        for (int i = 0; i < ReforgePair.statTable.length; ++i) {
            if (ReforgePair.statTable[i] == x) {
                return i;
            }
        }
        throw new IllegalArgumentException("Unreforgable stat: " + x);
    }
    
    static ReforgePair decode(final int id) {
        final int index = id - 113;
        if (index < 0) {
            throw new IllegalArgumentException("Invalid reforge: " + id);
        }
        final int src = index / 7;
        if (src >= ReforgePair.statTable.length) {
            throw new IllegalArgumentException("Invalid reforge: " + id);
        }
        int dst = index % 7;
        if (dst >= src) {
            ++dst;
        }
        return make(ReforgePair.statTable[src], ReforgePair.statTable[dst]);
    }
    
    static {
        memo = new HashMap<Integer, ReforgePair>();
        statTable = new StatT[] { StatT.SPI, StatT.DODGE, StatT.PARRY, StatT.HIT, StatT.CRIT, StatT.HASTE, StatT.EXP, StatT.MASTERY };
    }
}
