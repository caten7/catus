// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class AttackT extends BlizzT
{
    public static final Avoid MISS;
    public static final Avoid DODGE;
    public static final Avoid PARRY;
    public static final Hit HIT;
    public static final Hit BLOCK_HIT;
    public static final Hit CRIT;
    public static final Hit BLOCK_CRIT;
    public static final Hit GLANCE;
    public static final Hit BLOCK_GLANCE;
    public static final AttackT[] ALL;
    public final boolean hit;
    public final boolean crit;
    public final boolean block;
    public final boolean glance;
    
    private AttackT(final int id, final String name, final boolean hit, final boolean crit, final boolean block, final boolean glance) {
        super(id, name);
        this.hit = hit;
        this.crit = crit;
        this.block = block;
        this.glance = glance;
    }
    
    static AttackT critOrHit(final boolean crit) {
        return crit ? AttackT.CRIT : AttackT.HIT;
    }
    
    static {
        ALL = new AttackT[] { MISS = new Avoid(1, "Miss"), DODGE = new Avoid(2, "Dodge"), PARRY = new Avoid(3, "Parry"), HIT = new Hit(4, "Hit", false, false, false), BLOCK_HIT = new Hit(5, "Block-Hit", false, true, false), CRIT = new Hit(6, "Crit", true, false, false), BLOCK_CRIT = new Hit(7, "Block-Crit", true, true, false), GLANCE = new Hit(8, "Glance", false, false, true), BLOCK_GLANCE = new Hit(9, "Block-Glance", false, true, true) };
    }
    
    public static class Avoid extends AttackT
    {
        private Avoid(final int id, final String name) {
            super(id, name, false, false, false, false, null);
        }
    }
    
    public static class Hit extends AttackT
    {
        private Hit(final int id, final String name, final boolean crit, final boolean block, final boolean glance) {
            super(id, name, true, crit, block, glance, null);
        }
    }
}
