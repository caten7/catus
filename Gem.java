// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class Gem extends Item
{
    int minItemLevel;
    
    public static boolean sameAs(final Gem a, final Gem b) {
        return (a == null) ? (b == null) : (a == b || (b != null && a.getType() == b.getType() && a.stats.sameAs(b.stats) && Ints.cmp(a.itemSpells, b.itemSpells) == 0));
    }
    
    @Override
    public String toString() {
        return this.name + ": " + this.fancyStats();
    }
    
    public GemT getType() {
        return (GemT)this.minorType;
    }
    
    public static Gem makeFake(final GemT type) {
        final Gem temp = new Gem();
        temp.majorType = ItemT.GEM;
        temp.minorType = type;
        return temp;
    }
}
