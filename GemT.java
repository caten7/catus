// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;

public final class GemT
{
    public static final String[] KEYS;
    public final String name;
    public final String type;
    public final Color color;
    final int accepts;
    final int matches;
    static final int BIT_META = 1;
    static final int BIT_RED = 2;
    static final int BIT_YELLOW = 4;
    static final int BIT_BLUE = 8;
    static final int BIT_SHA = 16;
    static final int BIT_COG = 32;
    static final int BIT_WHITE = 64;
    static final int BITS_COLORED = 78;
    public static final GemT META;
    public static final GemT WHITE;
    public static final GemT RED;
    public static final GemT BLUE;
    public static final GemT YELLOW;
    public static final GemT SHA;
    public static final GemT COG;
    public static final GemT ORANGE;
    public static final GemT PURPLE;
    public static final GemT GREEN;
    public static final GemT[] ALL;
    public static final GemT[] SOCKETS;
    public static final GemT[] PRIMARY_SOCKETS;
    public static final GemT[] COLORS;
    private static final HashMap<String, GemT> typeMap;
    
    private GemT(final String name, final String type, final Color color, final int accepts, final int matches) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.accepts = accepts;
        this.matches = matches;
    }
    
    public boolean accepts(final Gem x) {
        return x == null || this.accepts(x.getType());
    }
    
    public boolean matches(final Gem x) {
        return x != null && this.matches(x.getType());
    }
    
    public boolean colored() {
        return (this.accepts & 0x4E) > 0;
    }
    
    public boolean accepts(final GemT x) {
        return (this.accepts & x.accepts) > 0;
    }
    
    public boolean matches(final GemT x) {
        return this.accepts(x) && (this.matches & x.matches) > 0;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public static GemT fromType(final String type) {
        final GemT temp = GemT.typeMap.get(type);
        if (temp == null) {
            throw new IllegalArgumentException("Unknown Gem type: " + type);
        }
        return temp;
    }
    
    public static GemT fromBit(final int bit) {
        switch (bit) {
            case 1: {
                return GemT.META;
            }
            case 2: {
                return GemT.RED;
            }
            case 4: {
                return GemT.YELLOW;
            }
            case 8: {
                return GemT.BLUE;
            }
            case 32: {
                return GemT.COG;
            }
            case 16: {
                return GemT.SHA;
            }
            case 64: {
                return GemT.WHITE;
            }
            default: {
                throw new IllegalArgumentException("Unknown Gem bit: " + bit);
            }
        }
    }
    
    static {
        KEYS = new String[] { "gem0", "gem1", "gem2", "gem3", "gem4", "gem5" };
        ALL = new GemT[] { META = new GemT("Meta", "META", Color.BLACK, 1, 1), RED = new GemT("Red", "RED", Color.RED, 78, 2), BLUE = new GemT("Blue", "BLUE", Color.BLACK, 78, 8), YELLOW = new GemT("Yellow", "YELLOW", Color.YELLOW, 78, 4), ORANGE = new GemT("Orange", "ORANGE", Color.ORANGE, 78, 6), PURPLE = new GemT("Purple", "PURPLE", Color.MAGENTA, 78, 10), GREEN = new GemT("Green", "GREEN", Color.GREEN, 78, 12), SHA = new GemT("Sha-touched", "HYDRAULIC", Color.ORANGE, 16, 16), COG = new GemT("Cogwheel", "COGWHEEL", Color.CYAN, 32, 32), WHITE = new GemT("White", "PRISMATIC", Color.DARK_GRAY, 78, 78) };
        SOCKETS = new GemT[] { GemT.META, GemT.RED, GemT.BLUE, GemT.YELLOW, GemT.WHITE, GemT.SHA, GemT.COG };
        PRIMARY_SOCKETS = new GemT[] { GemT.RED, GemT.BLUE, GemT.YELLOW };
        final ArrayList<GemT> temp = new ArrayList<GemT>();
        for (final GemT x : GemT.ALL) {
            if (x.colored()) {
                temp.add(x);
            }
        }
        COLORS = temp.toArray(new GemT[temp.size()]);
        typeMap = new HashMap<String, GemT>();
        for (final GemT x2 : GemT.ALL) {
            GemT.typeMap.put(x2.type, x2);
        }
    }
}
