// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class Glyph
{
    public final char ch;
    public final String name;
    public static final Glyph NONE;
    
    public Glyph(final char ch, final String name) {
        this.ch = ch;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public boolean isActive(final String x) {
        return x != null && x.indexOf(this.ch) >= 0;
    }
    
    public static Glyph find(final Glyph[] pool, final String name) {
        for (final Glyph x : pool) {
            if (x.name.equalsIgnoreCase(name)) {
                return x;
            }
        }
        return null;
    }
    
    static {
        NONE = new Glyph('.', "None");
    }
}
