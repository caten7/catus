// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class NamedRuntimeException extends RuntimeException
{
    public final String name;
    
    public NamedRuntimeException(final String name, final String msg) {
        super(msg);
        this.name = name;
    }
    
    public NamedRuntimeException(final String name, final Exception err) {
        super(err);
        this.name = name;
    }
}
