// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.awt.Color;

public class QualityT extends BlizzT
{
    public static final QualityT POOR;
    public static final QualityT COMMON;
    public static final QualityT UNCOMMON;
    public static final QualityT RARE;
    public static final QualityT EPIC;
    public static final QualityT LEGENDARY;
    public static final QualityT ARTIFACT;
    public static final QualityT HEIRLOOM;
    public static final QualityT[] ALL;
    public final Color color;
    public final Color trueColor;
    
    private QualityT(final int id, final String name, final Color color) {
        this(id, name, color, color);
    }
    
    private QualityT(final int id, final String name, final Color color, final Color trueColor) {
        super(id, name);
        this.color = color;
        this.trueColor = trueColor;
    }
    
    static {
        ALL = new QualityT[] { POOR = new QualityT(0, "Poor", new Color(128, 128, 128)), COMMON = new QualityT(1, "Common", Color.BLACK, new Color(150, 75, 0)), UNCOMMON = new QualityT(2, "Uncommon", new Color(30, 204, 0)), RARE = new QualityT(3, "Rare", new Color(0, 112, 221)), EPIC = new QualityT(4, "Epic", new Color(163, 53, 238)), LEGENDARY = new QualityT(5, "Legendary", new Color(255, 128, 0), new Color(255, 128, 0)), ARTIFACT = new QualityT(6, "Artifact", Color.RED), HEIRLOOM = new QualityT(7, "Heirloom", new Color(192, 171, 107), new Color(229, 204, 128)) };
    }
}
