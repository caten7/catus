// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.layout;

import java.awt.Font;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractButton;
import java.awt.Panel;
import java.applet.Applet;
import java.awt.Frame;
import java.awt.Dialog;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JSlider;
import javax.swing.JProgressBar;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.util.HashMap;
import java.util.Map;
import java.awt.Insets;

class AquaLayoutStyle extends LayoutStyle
{
    private static final Insets EMPTY_INSETS;
    private static final int MINI = 0;
    private static final int SMALL = 1;
    private static final int REGULAR = 2;
    private static final Object[][] containerGapDefinitions;
    private static final Object[][] relatedGapDefinitions;
    private static final Object[][] unrelatedGapDefinitions;
    private static final Object[][] indentGapDefinitions;
    private static final Object[][] visualMarginDefinitions;
    private static final Map RELATED_GAPS;
    private static final Map UNRELATED_GAPS;
    private static final Map CONTAINER_GAPS;
    private static final Map INDENT_GAPS;
    private static final Map VISUAL_MARGINS;
    
    private static Map createInsetsMap(final Object[][] array) {
        final HashMap<Object, ComponentInsets> hashMap = new HashMap<Object, ComponentInsets>();
        for (int i = 0; i < array.length; ++i) {
            int n;
            for (n = 0; n < array[i].length && array[i][n] instanceof String; ++n) {}
            final Insets[] insets = new Insets[array[i].length - n];
            for (int j = n; j < array[i].length; ++j) {
                insets[j - n] = (Insets)array[i][j];
            }
            for (int k = 0; k < n; ++k) {
                final String s = (String)array[i][k];
                final int index = s.indexOf(46);
                if (index == -1) {
                    final ComponentInsets componentInsets = hashMap.get(s);
                    if (componentInsets == null) {
                        final ComponentInsets componentInsets2 = new ComponentInsets(insets);
                        hashMap.put(s, new ComponentInsets(insets));
                    }
                    else {
                        assert componentInsets.getInsets() == null;
                        componentInsets.setInsets(insets);
                    }
                }
                else {
                    final String substring = s.substring(index + 1);
                    final String substring2 = s.substring(0, index);
                    ComponentInsets componentInsets3 = hashMap.get(substring2);
                    if (componentInsets3 == null) {
                        componentInsets3 = new ComponentInsets();
                        hashMap.put(substring2, componentInsets3);
                    }
                    componentInsets3.addSubinsets(substring, new ComponentInsets(insets));
                }
            }
        }
        return hashMap;
    }
    
    public static void main(final String[] array) {
        final JButton button = new JButton();
        button.putClientProperty("JButton.buttonType", "metal");
        final JButton button2 = new JButton();
        final AquaLayoutStyle aquaLayoutStyle = new AquaLayoutStyle();
        System.err.println("gap= " + aquaLayoutStyle.getPreferredGap(button, button2, 0, 3, null));
        button.putClientProperty("JButton.buttonType", "square");
        button2.putClientProperty("JButton.buttonType", "square");
        System.err.println("gap= " + aquaLayoutStyle.getPreferredGap(button, button2, 0, 3, null));
    }
    
    public AquaLayoutStyle() {
    }
    
    public int getPreferredGap(final JComponent component, final JComponent component2, final int n, final int n2, final Container container) {
        super.getPreferredGap(component, component2, n, n2, container);
        int b = 0;
        if (n == 3) {
            if (n2 == 3 || n2 == 7) {
                final int buttonChildIndent = this.getButtonChildIndent(component, n2);
                if (buttonChildIndent != 0) {
                    return buttonChildIndent;
                }
            }
            final Insets preferredGap = this.getPreferredGap(component, n, this.getSizeStyle(component));
            switch (n2) {
                case 1: {
                    b = preferredGap.bottom;
                    break;
                }
                case 5: {
                    b = preferredGap.top;
                    break;
                }
                case 3: {
                    b = preferredGap.left;
                    break;
                }
                default: {
                    b = preferredGap.right;
                    break;
                }
            }
            final Insets visualMargin = this.getVisualMargin(component2);
            switch (n2) {
                case 1: {
                    b -= visualMargin.bottom;
                    break;
                }
                case 5: {
                    b -= visualMargin.top;
                    break;
                }
                case 3: {
                    b -= visualMargin.left;
                    break;
                }
                case 7: {
                    b -= visualMargin.right;
                    break;
                }
            }
        }
        else {
            final int min = Math.min(this.getSizeStyle(component), this.getSizeStyle(component2));
            final Insets preferredGap2 = this.getPreferredGap(component, n, min);
            final Insets preferredGap3 = this.getPreferredGap(component2, n, min);
            switch (n2) {
                case 1: {
                    b = Math.max(preferredGap2.top, preferredGap3.bottom);
                    break;
                }
                case 5: {
                    b = Math.max(preferredGap2.bottom, preferredGap3.top);
                    break;
                }
                case 3: {
                    b = Math.max(preferredGap2.right, preferredGap3.left);
                    break;
                }
                default: {
                    b = Math.max(preferredGap2.left, preferredGap3.right);
                    break;
                }
            }
            final Insets visualMargin2 = this.getVisualMargin(component);
            final Insets visualMargin3 = this.getVisualMargin(component2);
            switch (n2) {
                case 1: {
                    b -= visualMargin2.top + visualMargin3.bottom;
                    break;
                }
                case 5: {
                    b -= visualMargin2.bottom + visualMargin3.top;
                    break;
                }
                case 3: {
                    b -= visualMargin2.right + visualMargin3.left;
                    break;
                }
                case 7: {
                    b -= visualMargin2.left + visualMargin3.right;
                    break;
                }
            }
        }
        return Math.max(0, b);
    }
    
    private Insets getPreferredGap(final JComponent component, final int n, final int n2) {
        Map map = null;
        switch (n) {
            case 3: {
                map = AquaLayoutStyle.INDENT_GAPS;
                break;
            }
            case 0: {
                map = AquaLayoutStyle.RELATED_GAPS;
                break;
            }
            default: {
                map = AquaLayoutStyle.UNRELATED_GAPS;
                break;
            }
        }
        final String uiClassID = component.getUIClassID();
        String s = null;
        if (uiClassID == "ButtonUI" || uiClassID == "ToggleButtonUI") {
            s = (String)component.getClientProperty("JButton.buttonType");
        }
        else if (uiClassID == "ProgressBarUI") {
            s = ((((JProgressBar)component).getOrientation() == 0) ? "horizontal" : "vertical");
        }
        else if (uiClassID == "SliderUI") {
            s = ((((JSlider)component).getOrientation() == 0) ? "horizontal" : "vertical");
        }
        else if (uiClassID == "TabbedPaneUI") {
            switch (((JTabbedPane)component).getTabPlacement()) {
                case 1: {
                    s = "top";
                    break;
                }
                case 2: {
                    s = "left";
                    break;
                }
                case 3: {
                    s = "bottom";
                    break;
                }
                case 4: {
                    s = "right";
                    break;
                }
            }
        }
        else if (uiClassID == "ComboBoxUI") {
            s = (((JComboBox)component).isEditable() ? "editable" : "uneditable");
        }
        return this.getInsets(map, uiClassID, s, n2);
    }
    
    public int getContainerGap(final JComponent component, final int n, final Container container) {
        final Insets containerGap = this.getContainerGap(container, Math.min(this.getSizeStyle(component), this.getSizeStyle(container)));
        int b = 0;
        switch (n) {
            case 1: {
                b = containerGap.top;
                break;
            }
            case 5: {
                b = containerGap.bottom;
                break;
            }
            case 3: {
                b = containerGap.right;
                break;
            }
            default: {
                b = containerGap.left;
                break;
            }
        }
        final Insets visualMargin = this.getVisualMargin(component);
        switch (n) {
            case 1: {
                b -= visualMargin.top;
                break;
            }
            case 5: {
                b -= visualMargin.bottom;
                if (component instanceof JRadioButton) {
                    --b;
                    break;
                }
                break;
            }
            case 3: {
                b -= visualMargin.left;
                break;
            }
            case 7: {
                b -= visualMargin.right;
                break;
            }
        }
        return Math.max(0, b);
    }
    
    private Insets getContainerGap(final Container container, final int n) {
        String uiClassID;
        if (container instanceof JComponent) {
            uiClassID = ((JComponent)container).getUIClassID();
        }
        else if (container instanceof Dialog) {
            uiClassID = "Dialog";
        }
        else if (container instanceof Frame) {
            uiClassID = "Frame";
        }
        else if (container instanceof Applet) {
            uiClassID = "Applet";
        }
        else if (container instanceof Panel) {
            uiClassID = "Panel";
        }
        else {
            uiClassID = "default";
        }
        return this.getInsets(AquaLayoutStyle.CONTAINER_GAPS, uiClassID, null, n);
    }
    
    private Insets getInsets(final Map map, String s, final String s2, final int n) {
        if (s == null) {
            s = "default";
        }
        ComponentInsets componentInsets = map.get(s);
        if (componentInsets == null) {
            componentInsets = map.get("default");
            if (componentInsets == null) {
                return AquaLayoutStyle.EMPTY_INSETS;
            }
        }
        else if (s2 != null) {
            final ComponentInsets subinsets = componentInsets.getSubinsets(s2);
            if (subinsets != null) {
                componentInsets = subinsets;
            }
        }
        return componentInsets.getInsets(n);
    }
    
    private Insets getVisualMargin(final JComponent component) {
        final String uiClassID = component.getUIClassID();
        String s = null;
        if (uiClassID == "ButtonUI" || uiClassID == "ToggleButtonUI") {
            s = (String)component.getClientProperty("JButton.buttonType");
        }
        else if (uiClassID == "ProgressBarUI") {
            s = ((((JProgressBar)component).getOrientation() == 0) ? "horizontal" : "vertical");
        }
        else if (uiClassID == "SliderUI") {
            s = ((((JSlider)component).getOrientation() == 0) ? "horizontal" : "vertical");
        }
        else if (uiClassID == "TabbedPaneUI") {
            switch (((JTabbedPane)component).getTabPlacement()) {
                case 1: {
                    s = "top";
                    break;
                }
                case 2: {
                    s = "left";
                    break;
                }
                case 3: {
                    s = "bottom";
                    break;
                }
                case 4: {
                    s = "right";
                    break;
                }
            }
        }
        Insets insets = this.getInsets(AquaLayoutStyle.VISUAL_MARGINS, uiClassID, s, 0);
        if (uiClassID == "RadioButtonUI" || uiClassID == "CheckBoxUI") {
            switch (((AbstractButton)component).getHorizontalTextPosition()) {
                case 4: {
                    insets = new Insets(insets.top, insets.right, insets.bottom, insets.left);
                    break;
                }
                case 0: {
                    insets = new Insets(insets.top, insets.right, insets.bottom, insets.right);
                    break;
                }
                default: {
                    insets = new Insets(insets.top, insets.left, insets.bottom, insets.right);
                    break;
                }
            }
            if (component.getBorder() instanceof EmptyBorder) {
                final Insets insets2 = insets;
                insets2.left -= 2;
                final Insets insets3 = insets;
                insets3.right -= 2;
                final Insets insets4 = insets;
                insets4.top -= 2;
                final Insets insets5 = insets;
                insets5.bottom -= 2;
            }
        }
        return insets;
    }
    
    private int getSizeStyle(final Component component) {
        if (component == null) {
            return 2;
        }
        final Font font = component.getFont();
        if (font == null) {
            return 2;
        }
        final int size = font.getSize();
        return (size >= 13) ? 2 : (size > 9);
    }
    
    static {
        EMPTY_INSETS = new Insets(0, 0, 0, 0);
        containerGapDefinitions = new Object[][] { { "TabbedPaneUI", new Insets(6, 10, 10, 10), new Insets(6, 10, 10, 12), new Insets(12, 20, 20, 20) }, { "RootPaneUI", new Insets(8, 10, 10, 10), new Insets(8, 10, 10, 12), new Insets(14, 20, 20, 20) }, { "default", new Insets(8, 10, 10, 10), new Insets(8, 10, 10, 12), new Insets(14, 20, 20, 20) } };
        relatedGapDefinitions = new Object[][] { { "ButtonUI", "ButtonUI.push", "ButtonUI.text", "ToggleButtonUI.push", "ToggleButtonUI.text", new Insets(8, 8, 8, 8), new Insets(10, 10, 10, 10), new Insets(12, 12, 12, 12) }, { "ButtonUI.metal", "ToggleButtonUI.metal", new Insets(8, 8, 8, 8), new Insets(8, 8, 8, 8), new Insets(12, 12, 12, 12) }, { "ButtonUI.bevel", "ButtonUI.toggle", "ButtonUI.square", "ToggleButtonUI", "ToggleButtonUI.bevel", "ToggleButtonUI.square", "ToggleButtonUI.toggle", new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0) }, { "ButtonUI.bevel.largeIcon", "ToggleButtonUI.bevel.largeIcon", new Insets(8, 8, 8, 8), new Insets(8, 8, 8, 8), new Insets(8, 8, 8, 8) }, { "ButtonUI.icon", new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0) }, { "ButtonUI.icon.largeIcon", new Insets(8, 8, 8, 8), new Insets(8, 8, 8, 8), new Insets(8, 8, 8, 8) }, { "ButtonUI.round", "ToggleButtonUI.round", new Insets(12, 12, 12, 12), new Insets(12, 12, 12, 12), new Insets(12, 12, 12, 12) }, { "ButtonUI.help", new Insets(12, 12, 12, 12), new Insets(12, 12, 12, 12), new Insets(12, 12, 12, 12) }, { "ButtonUI.toggleCenter", "ToggleButtonUI.toggleCenter", new Insets(8, 0, 8, 0), new Insets(10, 0, 10, 0), new Insets(12, 0, 12, 0) }, { "ButtonUI.toggleEast", "ToggleButtonUI.toggleEast", new Insets(8, 0, 8, 8), new Insets(10, 0, 10, 10), new Insets(12, 0, 12, 12) }, { "ButtonUI.toggleWest", "ToggleButtonUI.toggleWest", new Insets(8, 8, 8, 0), new Insets(10, 10, 10, 0), new Insets(12, 12, 12, 0) }, { "ButtonUI.toolBarTab", "ToggleButtonUI.toolBarTab", new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0) }, { "ButtonUI.colorWell", "ToggleButtonUI.colorWell", new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0) }, { "CheckBoxUI", new Insets(6, 5, 6, 5), new Insets(7, 6, 7, 6), new Insets(7, 6, 7, 6) }, { "ComboBoxUI.editable", new Insets(8, 5, 8, 5), new Insets(10, 6, 10, 6), new Insets(12, 8, 12, 8) }, { "ComboBoxUI.uneditable", new Insets(6, 5, 6, 5), new Insets(8, 6, 8, 6), new Insets(10, 8, 10, 8) }, { "LabelUI", new Insets(8, 8, 8, 8), new Insets(8, 8, 8, 8), new Insets(8, 8, 8, 8) }, { "ListUI", new Insets(5, 5, 5, 5), new Insets(6, 6, 6, 6), new Insets(6, 6, 6, 6) }, { "PanelUI", new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0) }, { "ProgressBarUI", new Insets(8, 8, 8, 8), new Insets(10, 10, 10, 10), new Insets(12, 12, 12, 12) }, { "RadioButtonUI", new Insets(5, 5, 5, 5), new Insets(6, 6, 6, 6), new Insets(6, 6, 6, 6) }, { "ScrollPaneUI", new Insets(6, 8, 6, 8), new Insets(6, 8, 6, 8), new Insets(8, 10, 8, 10) }, { "SeparatorUI", new Insets(8, 8, 8, 8), new Insets(10, 10, 10, 10), new Insets(12, 12, 12, 12) }, { "SliderUI.horizontal", new Insets(8, 8, 8, 8), new Insets(10, 10, 10, 10), new Insets(12, 12, 12, 12) }, { "SliderUI.vertical", new Insets(8, 8, 8, 8), new Insets(10, 10, 10, 10), new Insets(12, 12, 12, 12) }, { "SpinnerUI", new Insets(6, 8, 6, 8), new Insets(6, 8, 6, 8), new Insets(8, 10, 8, 10) }, { "SplitPaneUI", new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0) }, { "TabbedPaneUI", new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0) }, { "TableUI", new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0) }, { "TextAreaUI", "EditorPaneUI", "TextPaneUI", new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0) }, { "TextFieldUI", "FormattedTextFieldUI", "PasswordFieldUI", new Insets(6, 8, 6, 8), new Insets(6, 8, 6, 8), new Insets(8, 10, 8, 10) }, { "TreeUI", new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0) } };
        unrelatedGapDefinitions = new Object[][] { { "ButtonUI.help", new Insets(24, 24, 24, 24), new Insets(24, 24, 24, 24), new Insets(24, 24, 24, 24) }, { "default", new Insets(10, 10, 10, 10), new Insets(12, 12, 12, 12), new Insets(14, 14, 14, 14) } };
        indentGapDefinitions = new Object[][] { { "CheckBoxUI", "RadioButtonUI", new Insets(16, 24, 16, 24), new Insets(20, 24, 20, 24), new Insets(24, 24, 24, 24) }, { "default", new Insets(16, 16, 16, 16), new Insets(20, 20, 20, 20), new Insets(24, 24, 24, 24) } };
        visualMarginDefinitions = new Object[][] { { "ButtonUI", "ButtonUI.text", "ToggleButtonUI", "ToggleButtonUI.text", new Insets(5, 3, 3, 3) }, { "ButtonUI.icon", "ToggleButtonUI.icon", new Insets(5, 2, 3, 2) }, { "ButtonUI.toolbar", "ToggleButtonUI.toolbar", new Insets(0, 0, 0, 0) }, { "CheckBoxUI", new Insets(4, 4, 3, 3) }, { "ComboBoxUI", new Insets(2, 3, 4, 3) }, { "DesktopPaneUI", new Insets(0, 0, 0, 0) }, { "EditorPaneUI", "TextAreaUI", "TextPaneUI", new Insets(0, 0, 0, 0) }, { "FormattedTextFieldUI", "PasswordFieldUI", "TextFieldUI", new Insets(0, 0, 0, 0) }, { "LabelUI", new Insets(0, 0, 0, 0) }, { "ListUI", new Insets(0, 0, 0, 0) }, { "PanelUI", new Insets(0, 0, 0, 0) }, { "ProgressBarUI", "ProgressBarUI.horizontal", new Insets(0, 2, 4, 2) }, { "ProgressBarUI.vertical", new Insets(2, 0, 2, 4) }, { "RadioButtonUI", new Insets(4, 4, 3, 3) }, { "ScrollBarUI", new Insets(0, 0, 0, 0) }, { "ScrollPaneUI", new Insets(0, 0, 0, 0) }, { "SpinnerUI", new Insets(0, 0, 0, 0) }, { "SeparatorUI", new Insets(0, 0, 0, 0) }, { "SplitPaneUI", new Insets(0, 0, 0, 0) }, { "SliderUI", "SliderUI.horizontal", new Insets(3, 6, 3, 6) }, { "SliderUI.vertical", new Insets(6, 3, 6, 3) }, { "TabbedPaneUI", "TabbedPaneUI.top", new Insets(5, 7, 10, 7) }, { "TabbedPaneUI.bottom", new Insets(4, 7, 5, 7) }, { "TabbedPaneUI.left", new Insets(4, 6, 10, 7) }, { "TabbedPaneUI.right", new Insets(4, 7, 10, 6) }, { "TableUI", new Insets(0, 0, 0, 0) }, { "TreeUI", new Insets(0, 0, 0, 0) }, { "default", new Insets(0, 0, 0, 0) } };
        RELATED_GAPS = createInsetsMap(AquaLayoutStyle.relatedGapDefinitions);
        UNRELATED_GAPS = createInsetsMap(AquaLayoutStyle.unrelatedGapDefinitions);
        CONTAINER_GAPS = createInsetsMap(AquaLayoutStyle.containerGapDefinitions);
        INDENT_GAPS = createInsetsMap(AquaLayoutStyle.indentGapDefinitions);
        VISUAL_MARGINS = createInsetsMap(AquaLayoutStyle.visualMarginDefinitions);
    }
    
    private static class ComponentInsets
    {
        private Map children;
        private Insets[] insets;
        
        public ComponentInsets() {
        }
        
        public ComponentInsets(final Insets[] insets) {
            this.insets = insets;
        }
        
        public void setInsets(final Insets[] insets) {
            this.insets = insets;
        }
        
        public Insets[] getInsets() {
            return this.insets;
        }
        
        public Insets getInsets(final int n) {
            if (this.insets == null) {
                return AquaLayoutStyle.EMPTY_INSETS;
            }
            return this.insets[n];
        }
        
        void addSubinsets(final String s, final ComponentInsets componentInsets) {
            if (this.children == null) {
                this.children = new HashMap(5);
            }
            this.children.put(s, componentInsets);
        }
        
        ComponentInsets getSubinsets(final String s) {
            return (this.children == null) ? null : this.children.get(s);
        }
    }
}
