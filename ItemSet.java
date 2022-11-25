// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class ItemSet
{
    public final int id;
    public final String name;
    public final SetBonus[] bonuses;
    public final int max;
    public final QualityT quality;
    
    ItemSet(final int id, final String name, final QualityT quality, final SetBonus[] bonuses, final int max) {
        this.id = id;
        this.name = name;
        this.quality = quality;
        this.bonuses = bonuses;
        this.max = max;
    }
}
