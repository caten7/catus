// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JSeparator;

public class UISeparator extends JSeparator
{
    public UISeparator() {
        this(true);
    }
    
    public UISeparator(final boolean horizontal) {
        super(horizontal ? 0 : 1);
        final Dimension size = this.getPreferredSize();
        if (horizontal) {
            if (size.height < 5) {
                this.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
            }
        }
        else if (size.width < 5) {
            this.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
        }
    }
}
