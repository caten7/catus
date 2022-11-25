// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public enum ItemT
{
    CONSUME(0), 
    WEAPON(2), 
    GEM(3), 
    ARMOR(4), 
    QUEST(12);
    
    final int id;
    
    private ItemT(final int id) {
        this.id = id;
    }
    
    public static ItemT fromId(final int id) {
        final ItemT[] v = values();
        int i = 0;
        int e = v.length;
        while (i < e) {
            final int m = e + i >>> 1;
            final ItemT x = v[m];
            if (x.id == id) {
                return x;
            }
            if (x.id > id) {
                e = m;
            }
            else {
                i = m + 1;
            }
        }
        throw new IllegalArgumentException("Unknown ItemType: " + id);
    }
    
    public static int statBudget(final int itemLevel, final double mod) {
        final double a = 3.1303988579149586;
        final double b = 0.009317466726654814;
        return (int)(0.5 + Math.exp(a + b * itemLevel) * mod);
    }
    
    public static double statBudgetUnrounded(final int itemLevel, final double mod) {
        final double a = 3.1303988579149586;
        final double b = 0.009317466726654814;
        return Math.exp(a + b * itemLevel) * mod;
    }
    
    public static double procMod(final int itemLevel0, final int itemLevel1) {
        final double a = 0.009317462825010576;
        return Math.exp(a * (itemLevel1 - itemLevel0));
    }
}
