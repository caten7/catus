// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.layout;

import java.awt.Container;
import javax.swing.JComponent;

class GnomeLayoutStyle extends LayoutStyle
{
    public int getPreferredGap(final JComponent component, final JComponent component2, int n, final int n2, final Container container) {
        super.getPreferredGap(component, component2, n, n2, container);
        if (n == 3) {
            if (n2 == 3 || n2 == 7) {
                final int buttonChildIndent = this.getButtonChildIndent(component, n2);
                if (buttonChildIndent != 0) {
                    return buttonChildIndent;
                }
                return 12;
            }
            else {
                n = 0;
            }
        }
        if (n2 == 3 || n2 == 7) {
            final boolean b = component.getUIClassID() == "LabelUI";
            final boolean b2 = component2.getUIClassID() == "LabelUI";
            if ((b && !b2) || (!b && b2)) {
                return 12;
            }
        }
        if (n == 0) {
            return 6;
        }
        return 12;
    }
    
    public int getContainerGap(final JComponent component, final int n, final Container container) {
        super.getContainerGap(component, n, container);
        return 12;
    }
}
