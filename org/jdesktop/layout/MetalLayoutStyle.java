// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.layout;

import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.ButtonModel;
import javax.swing.DefaultButtonModel;
import javax.swing.JToggleButton;
import java.awt.Container;
import javax.swing.JComponent;
import java.lang.reflect.InvocationTargetException;
import javax.swing.plaf.metal.MetalTheme;

class MetalLayoutStyle extends LayoutStyle
{
    private boolean isOcean;
    
    public MetalLayoutStyle() {
        this.isOcean = false;
        try {
            this.isOcean = (((MetalTheme)MetalLookAndFeel.class.getMethod("getCurrentTheme", (Class[])null).invoke(null, (Object[])null)).getName() == "Ocean");
        }
        catch (NoSuchMethodException ex) {}
        catch (IllegalAccessException ex2) {}
        catch (IllegalArgumentException ex3) {}
        catch (InvocationTargetException ex4) {}
    }
    
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
        final String uiClassID = component.getUIClassID();
        final String uiClassID2 = component2.getUIClassID();
        int n3;
        if (n == 0) {
            if (uiClassID == "ToggleButtonUI" && uiClassID2 == "ToggleButtonUI") {
                final ButtonModel model = ((JToggleButton)component).getModel();
                final ButtonModel model2 = ((JToggleButton)component2).getModel();
                if (model instanceof DefaultButtonModel && model2 instanceof DefaultButtonModel && ((DefaultButtonModel)model).getGroup() == ((DefaultButtonModel)model2).getGroup() && ((DefaultButtonModel)model).getGroup() != null) {
                    return 2;
                }
                if (this.isOcean) {
                    return 6;
                }
                return 5;
            }
            else {
                n3 = 6;
            }
        }
        else {
            n3 = 12;
        }
        if ((n2 == 3 || n2 == 7) && ((uiClassID == "LabelUI" && uiClassID2 != "LabelUI") || (uiClassID != "LabelUI" && uiClassID2 == "LabelUI"))) {
            return this.getCBRBPadding(component, component2, n2, n3 + 6);
        }
        return this.getCBRBPadding(component, component2, n2, n3);
    }
    
    int getCBRBPadding(final JComponent component, final JComponent component2, final int n, int cbrbPadding) {
        cbrbPadding = super.getCBRBPadding(component, component2, n, cbrbPadding);
        if (cbrbPadding > 0) {
            int n2 = this.getButtonAdjustment(component, n);
            if (n2 == 0) {
                n2 = this.getButtonAdjustment(component2, this.flipDirection(n));
            }
            cbrbPadding -= n2;
        }
        if (cbrbPadding < 0) {
            return 0;
        }
        return cbrbPadding;
    }
    
    private int getButtonAdjustment(final JComponent component, final int n) {
        final String uiClassID = component.getUIClassID();
        if (uiClassID == "ButtonUI" || uiClassID == "ToggleButtonUI") {
            if (!this.isOcean && (n == 3 || n == 5)) {
                return 1;
            }
        }
        else if (n == 5 && (uiClassID == "RadioButtonUI" || (!this.isOcean && uiClassID == "CheckBoxUI"))) {
            return 1;
        }
        return 0;
    }
    
    public int getContainerGap(final JComponent component, final int n, final Container container) {
        super.getContainerGap(component, n, container);
        return this.getCBRBPadding(component, n, 12 - this.getButtonAdjustment(component, n));
    }
}
