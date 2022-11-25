// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.HashMap;

public class StatValue implements StatValued
{
    private static final HashMap<Integer, StatValue> memo;
    final StatT type;
    final int value;
    
    public static StatValue make(final StatT type, final int value) {
        final int hash = (value << 8) + type.id;
        StatValue temp = StatValue.memo.get(hash);
        if (temp == null) {
            temp = new StatValue(type, value);
            StatValue.memo.put(hash, temp);
        }
        return temp;
    }
    
    private StatValue(final StatT type, final int value) {
        this.type = type;
        this.value = value;
    }
    
    @Override
    public String toString() {
        return this.type.formatValue(this.value);
    }
    
    public static boolean sameAs(final StatValue a, final StatValue b) {
        return (a == null) ? (b == null) : (a == b);
    }
    
    @Override
    public int getStat(final StatT stat) {
        return (stat == this.type) ? this.value : 0;
    }
    
    static {
        memo = new HashMap<Integer, StatValue>();
    }
}
