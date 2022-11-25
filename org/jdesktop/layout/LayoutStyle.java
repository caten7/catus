// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.layout;

import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.Icon;
import javax.swing.AbstractButton;
import java.awt.Insets;
import javax.swing.plaf.UIResource;
import java.awt.Container;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.LookAndFeel;

public class LayoutStyle
{
    private static final boolean USE_CORE_LAYOUT_STYLE;
    public static final int RELATED = 0;
    public static final int UNRELATED = 1;
    public static final int INDENT = 3;
    private static LayoutStyle layoutStyle;
    private static LookAndFeel laf;
    
    public static void setSharedInstance(final LayoutStyle value) {
        UIManager.getLookAndFeelDefaults().put("LayoutStyle.instance", value);
    }
    
    public static LayoutStyle getSharedInstance() {
        final Object value = UIManager.get("LayoutStyle.instance");
        if (value != null && value instanceof LayoutStyle) {
            return (LayoutStyle)value;
        }
        final LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
        if (LayoutStyle.layoutStyle == null || lookAndFeel != LayoutStyle.laf) {
            LayoutStyle.laf = lookAndFeel;
            final String id = LayoutStyle.laf.getID();
            if (LayoutStyle.USE_CORE_LAYOUT_STYLE) {
                if ("Aqua" == id) {
                    try {
                        lookAndFeel.getClass().getDeclaredMethod("getLayoutStyle", (Class<?>[])new Class[0]);
                        LayoutStyle.layoutStyle = new SwingLayoutStyle();
                    }
                    catch (NoSuchMethodException ex) {
                        LayoutStyle.layoutStyle = new AquaLayoutStyle();
                    }
                }
                else {
                    LayoutStyle.layoutStyle = new SwingLayoutStyle();
                }
            }
            else if ("Metal" == id) {
                LayoutStyle.layoutStyle = new MetalLayoutStyle();
            }
            else if ("Windows" == id) {
                LayoutStyle.layoutStyle = new WindowsLayoutStyle();
            }
            else if ("GTK" == id) {
                LayoutStyle.layoutStyle = new GnomeLayoutStyle();
            }
            else if ("Aqua" == id) {
                LayoutStyle.layoutStyle = new AquaLayoutStyle();
            }
            else {
                LayoutStyle.layoutStyle = new LayoutStyle();
            }
        }
        return LayoutStyle.layoutStyle;
    }
    
    public int getPreferredGap(final JComponent component, final JComponent component2, final int n, final int n2, final Container container) {
        if (n2 != 1 && n2 != 5 && n2 != 7 && n2 != 3) {
            throw new IllegalArgumentException("Invalid position");
        }
        if (component == null || component2 == null) {
            throw new IllegalArgumentException("Components must be non-null");
        }
        if (n == 0) {
            return 6;
        }
        if (n == 1) {
            return 12;
        }
        if (n != 3) {
            throw new IllegalArgumentException("Invalid type");
        }
        if (n2 != 3 && n2 != 7) {
            return 6;
        }
        final int buttonChildIndent = this.getButtonChildIndent(component, n2);
        if (buttonChildIndent != 0) {
            return buttonChildIndent;
        }
        return 6;
    }
    
    public int getContainerGap(final JComponent component, final int n, final Container container) {
        if (n != 1 && n != 5 && n != 7 && n != 3) {
            throw new IllegalArgumentException("Invalid position");
        }
        if (component == null) {
            throw new IllegalArgumentException("Component must be non-null");
        }
        return 12;
    }
    
    boolean isDialog(final JComponent component) {
        final String name = component.getName();
        return name != null && name.endsWith(".contentPane");
    }
    
    int getCBRBPadding(final JComponent component, final JComponent component2, final int n, int n2) {
        n2 -= this.getCBRBPadding(component, n);
        if (n2 > 0) {
            n2 -= this.getCBRBPadding(component2, this.flipDirection(n));
        }
        if (n2 < 0) {
            return 0;
        }
        return n2;
    }
    
    int getCBRBPadding(final JComponent component, final int n, int a) {
        a -= this.getCBRBPadding(component, n);
        return Math.max(a, 0);
    }
    
    int flipDirection(final int n) {
        switch (n) {
            case 1: {
                return 5;
            }
            case 5: {
                return 1;
            }
            case 3: {
                return 7;
            }
            case 7: {
                return 3;
            }
            default: {
                assert false;
                return 0;
            }
        }
    }
    
    private int getCBRBPadding(final JComponent component, final int n) {
        if ((component.getUIClassID() == "CheckBoxUI" || component.getUIClassID() == "RadioButtonUI") && component.getBorder() instanceof UIResource) {
            return this.getInset(component, n);
        }
        return 0;
    }
    
    private int getInset(final JComponent component, final int n) {
        final Insets insets = component.getInsets();
        switch (n) {
            case 1: {
                return insets.top;
            }
            case 5: {
                return insets.bottom;
            }
            case 3: {
                return insets.right;
            }
            case 7: {
                return insets.left;
            }
            default: {
                assert false;
                return 0;
            }
        }
    }
    
    private boolean isLeftAligned(final AbstractButton abstractButton, final int n) {
        if (n == 7) {
            final boolean leftToRight = abstractButton.getComponentOrientation().isLeftToRight();
            final int horizontalAlignment = abstractButton.getHorizontalAlignment();
            return (leftToRight && (horizontalAlignment == 2 || horizontalAlignment == 10)) || (!leftToRight && horizontalAlignment == 11);
        }
        return false;
    }
    
    private boolean isRightAligned(final AbstractButton abstractButton, final int n) {
        if (n == 3) {
            final boolean leftToRight = abstractButton.getComponentOrientation().isLeftToRight();
            final int horizontalAlignment = abstractButton.getHorizontalAlignment();
            return (leftToRight && (horizontalAlignment == 4 || horizontalAlignment == 11)) || (!leftToRight && horizontalAlignment == 10);
        }
        return false;
    }
    
    private Icon getIcon(final AbstractButton abstractButton) {
        final Icon icon = abstractButton.getIcon();
        if (icon != null) {
            return icon;
        }
        Object key = null;
        if (abstractButton instanceof JCheckBox) {
            key = "CheckBox.icon";
        }
        else if (abstractButton instanceof JRadioButton) {
            key = "RadioButton.icon";
        }
        if (key != null) {
            final Object value = UIManager.get(key);
            if (value instanceof Icon) {
                return (Icon)value;
            }
        }
        return null;
    }
    
    int getButtonChildIndent(final JComponent component, final int n) {
        if (component instanceof JRadioButton || component instanceof JCheckBox) {
            final AbstractButton abstractButton = (AbstractButton)component;
            final Insets insets = component.getInsets();
            final Icon icon = this.getIcon(abstractButton);
            final int iconTextGap = abstractButton.getIconTextGap();
            if (this.isLeftAligned(abstractButton, n)) {
                return insets.left + icon.getIconWidth() + iconTextGap;
            }
            if (this.isRightAligned(abstractButton, n)) {
                return insets.right + icon.getIconWidth() + iconTextGap;
            }
        }
        return 0;
    }
    
    static {
        boolean use_CORE_LAYOUT_STYLE = false;
        try {
            Class.forName("javax.swing.LayoutStyle");
            use_CORE_LAYOUT_STYLE = true;
        }
        catch (ClassNotFoundException ex) {}
        USE_CORE_LAYOUT_STYLE = use_CORE_LAYOUT_STYLE;
    }
}
