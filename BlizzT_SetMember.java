// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public abstract class BlizzT_SetMember extends BlizzT
{
    public final int bit;
    
    public BlizzT_SetMember(final int id, final String name) {
        this(id, 1 << id - 1, name);
    }
    
    public BlizzT_SetMember(final int id, final int bit, final String name) {
        super(id, name);
        this.bit = bit;
    }
    
    public boolean isMemberOf(final int bits) {
        return (bits & this.bit) > 0;
    }
    
    public static String join(final BlizzT_SetMember[] members, final int bits) {
        final StringBuilder sb = new StringBuilder(64);
        for (final BlizzT_SetMember x : members) {
            if (x.isMemberOf(bits)) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(x.name);
            }
        }
        return (sb.length() == 0) ? "None" : sb.toString();
    }
}
