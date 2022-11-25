// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class ArmorT extends BlizzT
{
    static final ArmorT NONE;
    static final ArmorT CLOTH;
    static final ArmorT LEATHER;
    static final ArmorT MAIL;
    static final ArmorT PLATE;
    static final ArmorT SHIELD;
    static final TypeMap<ArmorT> MAP;
    public final double scale;
    
    private ArmorT(final int id, final String name, final double scale) {
        super(id, name);
        this.scale = scale;
    }
    
    public int armor(final int itemLevel, final double slotMod) {
        final double a = -8692.662063168213;
        final double b = 46.26700506191467;
        return (int)(0.5 + (a + itemLevel * b) * this.scale * slotMod);
    }
    
    static {
        MAP = BlizzT.makeMap(NONE = new ArmorT(0, "None", 0.0), CLOTH = new ArmorT(1, "Cloth", 1.0), LEATHER = new ArmorT(2, "Leather", 1.272808391525244), MAIL = new ArmorT(3, "Mail", 1.7709804881108826), PLATE = new ArmorT(4, "Plate", 2.418603633516069), SHIELD = new ArmorT(6, "Shield", 0.0));
    }
}
