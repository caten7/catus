// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import javax.swing.ImageIcon;

public class IconStore
{
    public final String name;
    public final ImageIcon[] icons;
    
    IconStore(final String name) {
        this.icons = new ImageIcon[API.IconSize.values().length];
        this.name = name;
    }
    
    public ImageIcon getIcon(final API.IconSize size) {
        return this.icons[size.ordinal()];
    }
}
