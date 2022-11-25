// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Collection;

public abstract class LineWriter
{
    public static final LineWriter OUT;
    
    public static LineWriter buffered(final Collection<String> c) {
        return new LineWriter() {
            @Override
            public void add(final String s) {
                c.add(s);
            }
        };
    }
    
    public static LineWriter buffered(final StringBuilder sb) {
        return new LineWriter() {
            @Override
            public void add(final String s) {
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(s);
            }
        };
    }
    
    public void add() {
        this.add("");
    }
    
    public void add(final String fmt, final Object... a) {
        this.add(String.format(fmt, a));
    }
    
    public abstract void add(final String p0);
    
    static {
        OUT = new LineWriter() {
            @Override
            public void add(final String s) {
                System.out.println(s);
            }
        };
    }
}
