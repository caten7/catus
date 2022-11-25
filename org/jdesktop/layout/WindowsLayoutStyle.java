// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.layout;

import java.awt.FontMetrics;
import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.Container;
import javax.swing.JComponent;

class WindowsLayoutStyle extends LayoutStyle
{
    private int baseUnitX;
    private int baseUnitY;
    
    public int getPreferredGap(final JComponent component, final JComponent component2, int n, final int n2, final Container container) {
        super.getPreferredGap(component, component2, n, n2, container);
        if (n == 3) {
            if (n2 == 3 || n2 == 7) {
                final int buttonChildIndent = this.getButtonChildIndent(component, n2);
                if (buttonChildIndent != 0) {
                    return buttonChildIndent;
                }
                return 10;
            }
            else {
                n = 0;
            }
        }
        if (n == 1) {
            return this.getCBRBPadding(component, component2, n2, this.dluToPixels(7, n2));
        }
        final boolean b = component.getUIClassID() == "LabelUI";
        final boolean b2 = component2.getUIClassID() == "LabelUI";
        if (((b && !b2) || (b2 && !b)) && (n2 == 3 || n2 == 7)) {
            return this.getCBRBPadding(component, component2, n2, this.dluToPixels(3, n2));
        }
        return this.getCBRBPadding(component, component2, n2, this.dluToPixels(4, n2));
    }
    
    public int getContainerGap(final JComponent component, final int n, final Container container) {
        super.getContainerGap(component, n, container);
        return this.getCBRBPadding(component, n, this.dluToPixels(7, n));
    }
    
    private int dluToPixels(final int n, final int n2) {
        if (this.baseUnitX == 0) {
            this.calculateBaseUnits();
        }
        if (n2 == 3 || n2 == 7) {
            return n * this.baseUnitX / 4;
        }
        assert n2 == 5;
        return n * this.baseUnitY / 8;
    }
    
    private void calculateBaseUnits() {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(UIManager.getFont("Button.font"));
        this.baseUnitX = fontMetrics.stringWidth("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        this.baseUnitX = (this.baseUnitX / 26 + 1) / 2;
        this.baseUnitY = fontMetrics.getAscent() + fontMetrics.getDescent() - 1;
    }
}
