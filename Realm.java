// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Comparator;

public class Realm
{
    public final String name;
    public final String slug;
    public final String group;
    int index;
    public static final Comparator<Realm> CMP_INDEX;
    
    Realm(final String name, final String slug, final String group) {
        this.name = name;
        this.slug = slug;
        this.group = group;
    }
    
    static {
        CMP_INDEX = new Comparator<Realm>() {
            @Override
            public int compare(final Realm a, final Realm b) {
                return a.index - b.index;
            }
        };
    }
}
