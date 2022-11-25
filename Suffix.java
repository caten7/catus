// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class Suffix
{
    public final int id;
    public final String name;
    public final EnchantData[] enchants;
    public final int[] alloc;
    public final StatAlloc[] allocDist;
    
    Suffix(final int id, final String name, final EnchantData[] enchants, final int[] alloc, final StatAlloc[] allocDist) {
        this.id = id;
        this.name = name;
        this.enchants = enchants;
        this.alloc = alloc;
        this.allocDist = allocDist;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
