// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class FactionT
{
    public final String name;
    public static final FactionT HORDE;
    public static final FactionT ALLIANCE;
    public static final FactionT NEUTRAL;
    public static final FactionT[] ALL;
    
    private FactionT(final String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    static {
        ALL = new FactionT[] { HORDE = new FactionT("Horde"), ALLIANCE = new FactionT("Alliance"), NEUTRAL = new FactionT("Neutral") };
    }
}
