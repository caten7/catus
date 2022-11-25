// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class ProfilePerm
{
    public final String name;
    public final String code;
    
    public ProfilePerm(final String name, final String code) {
        this.name = name;
        this.code = code;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
