// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.layout;

import java.util.Collections;
import java.util.HashMap;
import java.awt.Toolkit;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.lang.reflect.Field;
import javax.swing.text.View;
import javax.swing.ComboBoxEditor;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import java.util.Enumeration;
import java.util.Dictionary;
import java.awt.FontMetrics;
import javax.swing.SwingUtilities;
import javax.swing.Icon;
import javax.swing.text.JTextComponent;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.DefaultListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JTree;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.JProgressBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import java.awt.Component;
import javax.swing.ListCellRenderer;
import javax.swing.JList;
import java.lang.reflect.Method;
import java.util.Map;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.Rectangle;

public class Baseline
{
    static final int BRB_NONE = 0;
    public static final int BRB_CONSTANT_ASCENT = 1;
    public static final int BRB_CONSTANT_DESCENT = 2;
    public static final int BRB_CENTER_OFFSET = 3;
    public static final int BRB_OTHER = 4;
    private static final Rectangle viewRect;
    private static final Rectangle textRect;
    private static final Rectangle iconRect;
    private static final int EDGE_SPACING = 2;
    private static final int TEXT_SPACING = 2;
    private static final Insets EMPTY_INSETS;
    private static JLabel TABLE_LABEL;
    private static JLabel LIST_LABEL;
    private static JLabel TREE_LABEL;
    private static Class CLASSIC_WINDOWS;
    private static boolean checkedForClassic;
    private static Class WINDOWS_CLASS;
    private static boolean checkedForWindows;
    private static boolean inSandbox;
    private static boolean checkedForOcean;
    private static boolean usingOcean;
    private static final Map BASELINE_MAP;
    private static final Map BRB_I_MAP;
    private static final Method COMPONENT_BASELINE_METHOD;
    private static final Method COMPONENT_BRB_METHOD;
    private static final Object ENUM_BRB_CENTER_OFFSET;
    private static final Object ENUM_BRB_CONSTANT_ASCENT;
    private static final Object ENUM_BRB_CONSTANT_DESCENT;
    private static final Object ENUM_BRB_OTHER;
    private static JList brbList;
    private static ListCellRenderer brbListCellRenderer;
    
    private static Object getFieldValue(final Class clazz, final String name) throws IllegalAccessException, NoSuchFieldException {
        return clazz.getField(name).get(null);
    }
    
    static int getBaselineResizeBehavior(final Component component) {
        if (component instanceof JComponent) {
            return getBaselineResizeBehavior((JComponent)component);
        }
        return 4;
    }
    
    public static int getBaselineResizeBehavior(final JComponent component) {
        final Method brbiMethod = getBRBIMethod(component);
        if (brbiMethod != null) {
            return invokeBRBIMethod(brbiMethod, component);
        }
        if (Baseline.COMPONENT_BRB_METHOD != null) {
            return getBaselineResizeBehaviorUsingMustang(component);
        }
        final String uiClassID = component.getUIClassID();
        if (uiClassID == "ButtonUI" || uiClassID == "CheckBoxUI" || uiClassID == "RadioButtonUI" || uiClassID == "ToggleButtonUI") {
            return getButtonBaselineResizeBehavior((AbstractButton)component);
        }
        if (uiClassID == "ComboBoxUI") {
            return getComboBoxBaselineResizeBehavior((JComboBox)component);
        }
        if (uiClassID == "TextAreaUI") {
            return getTextAreaBaselineResizeBehavior((JTextArea)component);
        }
        if (uiClassID == "TextFieldUI" || uiClassID == "FormattedTextFieldUI" || uiClassID == "PasswordFieldUI") {
            return getSingleLineTextBaselineResizeBehavior((JTextField)component);
        }
        if (uiClassID == "LabelUI") {
            return getLabelBaselineResizeBehavior((JLabel)component);
        }
        if (uiClassID == "ListUI") {
            return getListBaselineResizeBehavior((JList)component);
        }
        if (uiClassID == "PanelUI") {
            return getPanelBaselineResizeBehavior((JPanel)component);
        }
        if (uiClassID == "ProgressBarUI") {
            return getProgressBarBaselineResizeBehavior((JProgressBar)component);
        }
        if (uiClassID == "SliderUI") {
            return getSliderBaselineResizeBehavior((JSlider)component);
        }
        if (uiClassID == "SpinnerUI") {
            return getSpinnerBaselineResizeBehavior((JSpinner)component);
        }
        if (uiClassID == "ScrollPaneUI") {
            return getScrollPaneBaselineBaselineResizeBehavior((JScrollPane)component);
        }
        if (uiClassID == "TabbedPaneUI") {
            return getTabbedPaneBaselineResizeBehavior((JTabbedPane)component);
        }
        if (uiClassID == "TableUI") {
            return getTableBaselineResizeBehavior((JTable)component);
        }
        if (uiClassID == "TreeUI") {
            return getTreeBaselineResizeBehavior((JTree)component);
        }
        return 4;
    }
    
    private static int getBaselineResizeBehaviorUsingMustang(final JComponent obj) {
        try {
            final Object invoke = Baseline.COMPONENT_BRB_METHOD.invoke(obj, (Object[])null);
            if (invoke == Baseline.ENUM_BRB_CENTER_OFFSET) {
                return 3;
            }
            if (invoke == Baseline.ENUM_BRB_CONSTANT_ASCENT) {
                return 1;
            }
            if (invoke == Baseline.ENUM_BRB_CONSTANT_DESCENT) {
                return 2;
            }
        }
        catch (IllegalAccessException ex) {
            assert false;
        }
        catch (IllegalArgumentException ex2) {
            assert false;
        }
        catch (InvocationTargetException ex3) {
            assert false;
        }
        return 4;
    }
    
    private static Method getBRBIMethod(final Component component) {
        for (Serializable s = component.getClass(); s != null; s = ((Class<? extends Component>)s).getSuperclass()) {
            if (Baseline.BRB_I_MAP.containsKey(s)) {
                return (Method)Baseline.BRB_I_MAP.get(s);
            }
        }
        final Class<? extends Component> class1 = component.getClass();
        final Method[] methods = class1.getMethods();
        for (int i = methods.length - 1; i >= 0; --i) {
            final Method method = methods[i];
            if ("getBaselineResizeBehaviorInt".equals(method.getName()) && method.getParameterTypes().length == 0) {
                Baseline.BRB_I_MAP.put(class1, method);
                return method;
            }
        }
        Baseline.BRB_I_MAP.put(class1, null);
        return null;
    }
    
    private static int invokeBRBIMethod(final Method method, final Component obj) {
        int intValue = 4;
        try {
            intValue = (int)method.invoke(obj, (Object[])null);
        }
        catch (IllegalAccessException ex) {}
        catch (IllegalArgumentException ex2) {}
        catch (InvocationTargetException ex3) {}
        return intValue;
    }
    
    private static int getTreeBaselineResizeBehavior(final JTree tree) {
        return 1;
    }
    
    private static int getSingleLineTextBaselineResizeBehavior(final JTextField textField) {
        return 3;
    }
    
    private static int getTextAreaBaselineResizeBehavior(final JTextArea textArea) {
        return 1;
    }
    
    private static int getTableBaselineResizeBehavior(final JTable table) {
        return 1;
    }
    
    private static int getTabbedPaneBaselineResizeBehavior(final JTabbedPane tabbedPane) {
        switch (tabbedPane.getTabPlacement()) {
            case 1:
            case 2:
            case 4: {
                return 1;
            }
            case 3: {
                return 2;
            }
            default: {
                return 4;
            }
        }
    }
    
    private static int getSpinnerBaselineResizeBehavior(final JSpinner spinner) {
        return getBaselineResizeBehavior(spinner.getEditor());
    }
    
    private static int getSliderBaselineResizeBehavior(final JSlider slider) {
        return 4;
    }
    
    private static int getScrollPaneBaselineBaselineResizeBehavior(final JScrollPane scrollPane) {
        return 1;
    }
    
    private static int getProgressBarBaselineResizeBehavior(final JProgressBar progressBar) {
        if (progressBar.isStringPainted() && progressBar.getOrientation() == 0) {
            return 3;
        }
        return 4;
    }
    
    private static int getPanelBaselineResizeBehavior(final JPanel panel) {
        final Border border = panel.getBorder();
        if (border instanceof TitledBorder) {
            switch (((TitledBorder)border).getTitlePosition()) {
                case 0:
                case 1:
                case 2:
                case 3: {
                    return 1;
                }
                case 4:
                case 5:
                case 6: {
                    return 2;
                }
            }
        }
        return 4;
    }
    
    private static int getListBaselineResizeBehavior(final JList list) {
        return 1;
    }
    
    private static int getLabelBaselineResizeBehavior(final JLabel label) {
        if (label.getClientProperty("html") != null) {
            return 4;
        }
        switch (label.getVerticalAlignment()) {
            case 1: {
                return 1;
            }
            case 3: {
                return 2;
            }
            case 0: {
                return 3;
            }
            default: {
                return 4;
            }
        }
    }
    
    private static int getButtonBaselineResizeBehavior(final AbstractButton abstractButton) {
        if (abstractButton.getClientProperty("html") != null) {
            return 4;
        }
        switch (abstractButton.getVerticalAlignment()) {
            case 1: {
                return 1;
            }
            case 3: {
                return 2;
            }
            case 0: {
                return 3;
            }
            default: {
                return 4;
            }
        }
    }
    
    private static int getComboBoxBaselineResizeBehavior(final JComboBox comboBox) {
        if (comboBox.isEditable()) {
            return getBaselineResizeBehavior(comboBox.getEditor().getEditorComponent());
        }
        ListCellRenderer<? super Object> listCellRenderer = comboBox.getRenderer();
        if (listCellRenderer == null) {
            if (Baseline.brbListCellRenderer == null) {
                Baseline.brbListCellRenderer = new DefaultListCellRenderer();
            }
            listCellRenderer = (ListCellRenderer<? super Object>)Baseline.brbListCellRenderer;
        }
        Object element = null;
        final Object prototypeDisplayValue = comboBox.getPrototypeDisplayValue();
        if (prototypeDisplayValue != null) {
            element = prototypeDisplayValue;
        }
        else if (comboBox.getModel().getSize() > 0) {
            element = comboBox.getModel().getElementAt(0);
        }
        if (element != null) {
            if (Baseline.brbList == null) {
                Baseline.brbList = new JList();
            }
            return getBaselineResizeBehavior(listCellRenderer.getListCellRendererComponent(Baseline.brbList, element, -1, false, false));
        }
        return 4;
    }
    
    public static int getBaseline(final JComponent component) {
        final Dimension preferredSize = component.getPreferredSize();
        return getBaseline(component, preferredSize.width, preferredSize.height);
    }
    
    private static Method getBaselineMethod(final JComponent component) {
        if (Baseline.COMPONENT_BASELINE_METHOD != null) {
            return Baseline.COMPONENT_BASELINE_METHOD;
        }
        Serializable s = component.getClass();
        while (s != null) {
            if (Baseline.BASELINE_MAP.containsKey(s)) {
                final Method method = Baseline.BASELINE_MAP.get(s);
                if (method != null || s == component.getClass()) {
                    return method;
                }
                break;
            }
            else {
                s = ((Class<? extends JComponent>)s).getSuperclass();
            }
        }
        final Class<? extends JComponent> class1 = component.getClass();
        final Method[] methods = class1.getMethods();
        for (int i = methods.length - 1; i >= 0; --i) {
            final Method method2 = methods[i];
            if ("getBaseline".equals(method2.getName())) {
                final Class<?>[] parameterTypes = method2.getParameterTypes();
                if (parameterTypes.length == 2 && parameterTypes[0] == Integer.TYPE && parameterTypes[1] == Integer.TYPE) {
                    Baseline.BASELINE_MAP.put(class1, method2);
                    return method2;
                }
            }
        }
        Baseline.BASELINE_MAP.put(class1, null);
        return null;
    }
    
    private static int invokeBaseline(final Method method, final JComponent obj, final int value, final int value2) {
        int intValue = -1;
        try {
            intValue = (int)method.invoke(obj, new Integer(value), new Integer(value2));
        }
        catch (IllegalAccessException ex) {}
        catch (IllegalArgumentException ex2) {}
        catch (InvocationTargetException ex3) {}
        return intValue;
    }
    
    private static boolean isKnownLookAndFeel() {
        final LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
        final String id = lookAndFeel.getID();
        return id == "GTK" || id == "Aqua" || isMetal(lookAndFeel) || isWindows(lookAndFeel);
    }
    
    public static int getBaseline(final JComponent component, final int n, final int n2) {
        final Method baselineMethod = getBaselineMethod(component);
        if (baselineMethod != null) {
            return invokeBaseline(baselineMethod, component, n, n2);
        }
        final Object value = UIManager.get("Baseline.instance");
        if (value != null && value instanceof Baseline) {
            return ((Baseline)value).getComponentBaseline(component, n, n2);
        }
        if (!isKnownLookAndFeel()) {
            return -1;
        }
        final String uiClassID = component.getUIClassID();
        int a = -1;
        if (uiClassID == "ButtonUI" || uiClassID == "CheckBoxUI" || uiClassID == "RadioButtonUI" || uiClassID == "ToggleButtonUI") {
            a = getButtonBaseline((AbstractButton)component, n2);
        }
        else {
            if (uiClassID == "ComboBoxUI") {
                return getComboBoxBaseline((JComboBox)component, n2);
            }
            if (uiClassID == "TextAreaUI") {
                return getTextAreaBaseline((JTextArea)component, n2);
            }
            if (uiClassID == "FormattedTextFieldUI" || uiClassID == "PasswordFieldUI" || uiClassID == "TextFieldUI") {
                a = getSingleLineTextBaseline((JTextComponent)component, n2);
            }
            else if (uiClassID == "LabelUI") {
                a = getLabelBaseline((JLabel)component, n2);
            }
            else if (uiClassID == "ListUI") {
                a = getListBaseline((JList)component, n2);
            }
            else if (uiClassID == "PanelUI") {
                a = getPanelBaseline((JPanel)component, n2);
            }
            else if (uiClassID == "ProgressBarUI") {
                a = getProgressBarBaseline((JProgressBar)component, n2);
            }
            else if (uiClassID == "SliderUI") {
                a = getSliderBaseline((JSlider)component, n2);
            }
            else if (uiClassID == "SpinnerUI") {
                a = getSpinnerBaseline((JSpinner)component, n2);
            }
            else if (uiClassID == "ScrollPaneUI") {
                a = getScrollPaneBaseline((JScrollPane)component, n2);
            }
            else if (uiClassID == "TabbedPaneUI") {
                a = getTabbedPaneBaseline((JTabbedPane)component, n2);
            }
            else if (uiClassID == "TableUI") {
                a = getTableBaseline((JTable)component, n2);
            }
            else if (uiClassID == "TreeUI") {
                a = getTreeBaseline((JTree)component, n2);
            }
        }
        return Math.max(a, -1);
    }
    
    private static Insets rotateInsets(final Insets insets, final int n) {
        switch (n) {
            case 2: {
                return new Insets(insets.left, insets.top, insets.right, insets.bottom);
            }
            case 3: {
                return new Insets(insets.bottom, insets.left, insets.top, insets.right);
            }
            case 4: {
                return new Insets(insets.left, insets.bottom, insets.right, insets.top);
            }
            default: {
                return new Insets(insets.top, insets.left, insets.bottom, insets.right);
            }
        }
    }
    
    private static int getMaxTabHeight(final JTabbedPane tabbedPane) {
        int a;
        final int n = a = tabbedPane.getFontMetrics(tabbedPane.getFont()).getHeight();
        boolean b = false;
        for (int i = tabbedPane.getTabCount() - 1; i >= 0; --i) {
            final Icon icon = tabbedPane.getIconAt(i);
            if (icon != null) {
                final int iconHeight = icon.getIconHeight();
                a = Math.max(a, iconHeight);
                if (iconHeight > n) {
                    b = true;
                }
            }
        }
        final Insets insets = UIManager.getInsets("TabbedPane.tabInsets");
        a += 2;
        if (!isMetal() || !b) {
            a += insets.top + insets.bottom;
        }
        return a;
    }
    
    private static int getTabbedPaneBaseline(final JTabbedPane c, final int n) {
        if (c.getTabCount() > 0) {
            if (isAqua()) {
                return getAquaTabbedPaneBaseline(c, n);
            }
            final Insets insets = c.getInsets();
            UIManager.getInsets("TabbedPane.contentBorderInsets");
            final Insets rotateInsets = rotateInsets(UIManager.getInsets("TabbedPane.tabAreaInsets"), c.getTabPlacement());
            final FontMetrics fontMetrics = c.getFontMetrics(c.getFont());
            final int maxTabHeight = getMaxTabHeight(c);
            Baseline.iconRect.setBounds(0, 0, 0, 0);
            Baseline.textRect.setBounds(0, 0, 0, 0);
            Baseline.viewRect.setBounds(0, 0, 32767, maxTabHeight);
            SwingUtilities.layoutCompoundLabel(c, fontMetrics, "A", null, 0, 0, 0, 11, Baseline.viewRect, Baseline.iconRect, Baseline.textRect, 0);
            final int n2 = Baseline.textRect.y + fontMetrics.getAscent();
            switch (c.getTabPlacement()) {
                case 1: {
                    int n3 = n2 + (insets.top + rotateInsets.top);
                    if (isWindows()) {
                        if (c.getTabCount() > 1) {
                            ++n3;
                        }
                        else {
                            --n3;
                        }
                    }
                    return n3;
                }
                case 3: {
                    int n4 = c.getHeight() - insets.bottom - rotateInsets.bottom - maxTabHeight + n2;
                    if (isWindows()) {
                        if (c.getTabCount() > 1) {
                            --n4;
                        }
                        else {
                            ++n4;
                        }
                    }
                    return n4;
                }
                case 2:
                case 4: {
                    if (isAqua()) {
                        return -1;
                    }
                    int n5 = n2 + (insets.top + rotateInsets.top);
                    if (isWindows()) {
                        n5 += maxTabHeight % 2;
                    }
                    return n5;
                }
            }
        }
        return -1;
    }
    
    private static int getAquaTabbedPaneBaseline(final JTabbedPane tabbedPane, final int n) {
        final FontMetrics fontMetrics = tabbedPane.getFontMetrics(tabbedPane.getFont());
        final int ascent = fontMetrics.getAscent();
        switch (tabbedPane.getTabPlacement()) {
            case 1: {
                int n2 = 5;
                if (tabbedPane.getFont().getSize() > 12) {
                    n2 = 6;
                }
                return n2 + (20 - fontMetrics.getHeight()) / 2 + ascent - 1;
            }
            case 3: {
                int n3;
                if (tabbedPane.getFont().getSize() > 12) {
                    n3 = 6;
                }
                else {
                    n3 = 4;
                }
                return n - (20 - ((20 - fontMetrics.getHeight()) / 2 + ascent)) - n3;
            }
            case 2:
            case 4: {
                return -1;
            }
            default: {
                return -1;
            }
        }
    }
    
    private static int getSliderBaseline(final JSlider c, final int n) {
        if (c.getPaintLabels() && !isGTK()) {
            final boolean aqua = isAqua();
            final FontMetrics fontMetrics = c.getFontMetrics(c.getFont());
            final Insets insets = c.getInsets();
            final Insets insets2 = (Insets)UIManager.get("Slider.focusInsets");
            if (c.getOrientation() == 0) {
                int n2 = 8;
                final int n3 = n - insets.top - insets.bottom - insets2.top - insets2.bottom;
                int iconHeight = 20;
                if (isMetal()) {
                    n2 = (int)UIManager.get("Slider.majorTickLength") + 5;
                    iconHeight = UIManager.getIcon("Slider.horizontalThumbIcon").getIconHeight();
                }
                else if (isWindows() && isXP()) {
                    ++iconHeight;
                }
                int n4 = iconHeight;
                if (aqua || c.getPaintTicks()) {
                    n4 += n2;
                }
                int n5 = insets.top + insets2.top + (n3 - (n4 + (fontMetrics.getAscent() + fontMetrics.getDescent())) - 1) / 2;
                if (aqua) {
                    if (c.getPaintTicks()) {
                        final int b = n - c.getUI().getPreferredSize(c).height;
                        if (b > 0) {
                            n5 -= Math.min(1, b);
                        }
                    }
                    else {
                        --n5;
                    }
                }
                final int n6 = n5 + iconHeight;
                int n7 = n2;
                if (!aqua && !c.getPaintTicks()) {
                    n7 = 0;
                }
                return n6 + n7 + fontMetrics.getAscent();
            }
            final boolean inverted = c.getInverted();
            final Integer n8 = inverted ? getMinSliderValue(c) : getMaxSliderValue(c);
            if (n8 != null) {
                int iconHeight2 = 11;
                if (isMetal()) {
                    iconHeight2 = UIManager.getIcon("Slider.verticalThumbIcon").getIconHeight();
                }
                final int max = Math.max(fontMetrics.getHeight() / 2, iconHeight2 / 2);
                int a = insets2.top + insets.top + max;
                final int n9 = n - insets2.top - insets2.bottom - insets.top - insets.bottom - max - max;
                getMaxSliderValue(c);
                final int minimum = c.getMinimum();
                final int maximum = c.getMaximum();
                final double n10 = n9 / (maximum - (double)minimum);
                int a2 = a + (n9 - 1);
                if (aqua) {
                    a -= 3;
                    a2 += 6;
                }
                final int n11 = a;
                double n12;
                if (!inverted) {
                    n12 = n10 * (maximum - (double)n8);
                }
                else {
                    n12 = n10 * (n8 - (double)minimum);
                }
                int b2;
                if (aqua) {
                    b2 = (int)(n11 + Math.floor(n12));
                }
                else {
                    b2 = (int)(n11 + Math.round(n12));
                }
                final int min = Math.min(a2, Math.max(a, b2));
                if (aqua) {
                    return min + fontMetrics.getAscent();
                }
                return min - fontMetrics.getHeight() / 2 + fontMetrics.getAscent();
            }
        }
        return -1;
    }
    
    private static Integer getMaxSliderValue(final JSlider slider) {
        final Dictionary labelTable = slider.getLabelTable();
        if (labelTable == null) {
            return null;
        }
        final Enumeration<Integer> keys = labelTable.keys();
        int max = slider.getMinimum() - 1;
        while (keys.hasMoreElements()) {
            max = Math.max(max, keys.nextElement());
        }
        if (max == slider.getMinimum() - 1) {
            return null;
        }
        return new Integer(max);
    }
    
    private static Integer getMinSliderValue(final JSlider slider) {
        final Dictionary labelTable = slider.getLabelTable();
        if (labelTable == null) {
            return null;
        }
        final Enumeration<Integer> keys = labelTable.keys();
        int min = slider.getMaximum() + 1;
        while (keys.hasMoreElements()) {
            min = Math.min(min, keys.nextElement());
        }
        if (min == slider.getMaximum() + 1) {
            return null;
        }
        return new Integer(min);
    }
    
    private static int getProgressBarBaseline(final JProgressBar progressBar, int n) {
        if (progressBar.isStringPainted() && progressBar.getOrientation() == 0) {
            final FontMetrics fontMetrics = progressBar.getFontMetrics(progressBar.getFont());
            final Insets insets = progressBar.getInsets();
            int top = insets.top;
            if (isWindows() && isXP()) {
                if (progressBar.isIndeterminate()) {
                    top = -1;
                    --n;
                }
                else {
                    top = 0;
                    n -= 3;
                }
            }
            else {
                if (isGTK()) {
                    return (n - fontMetrics.getAscent() - fontMetrics.getDescent()) / 2 + fontMetrics.getAscent();
                }
                if (isAqua()) {
                    if (progressBar.isIndeterminate()) {
                        return -1;
                    }
                    --top;
                    n -= insets.top + insets.bottom;
                }
                else {
                    n -= insets.top + insets.bottom;
                }
            }
            return top + (n + fontMetrics.getAscent() - fontMetrics.getLeading() - fontMetrics.getDescent()) / 2;
        }
        return -1;
    }
    
    private static int getTreeBaseline(final JTree tree, final int n) {
        int n2 = tree.getRowHeight();
        if (Baseline.TREE_LABEL == null) {
            (Baseline.TREE_LABEL = new JLabel("X")).setIcon(UIManager.getIcon("Tree.closedIcon"));
        }
        final JLabel tree_LABEL = Baseline.TREE_LABEL;
        tree_LABEL.setFont(tree.getFont());
        if (n2 <= 0) {
            n2 = tree_LABEL.getPreferredSize().height;
        }
        return getLabelBaseline(tree_LABEL, n2) + tree.getInsets().top;
    }
    
    private static int getTableBaseline(final JTable table, final int n) {
        if (Baseline.TABLE_LABEL == null) {
            (Baseline.TABLE_LABEL = new JLabel("")).setBorder(new EmptyBorder(1, 1, 1, 1));
        }
        final JLabel table_LABEL = Baseline.TABLE_LABEL;
        table_LABEL.setFont(table.getFont());
        final int rowMargin = table.getRowMargin();
        return getLabelBaseline(table_LABEL, table.getRowHeight() - rowMargin) + rowMargin / 2;
    }
    
    private static int getTextAreaBaseline(final JTextArea textArea, final int n) {
        return textArea.getInsets().top + textArea.getFontMetrics(textArea.getFont()).getAscent();
    }
    
    private static int getListBaseline(final JList list, final int n) {
        int n2 = list.getFixedCellHeight();
        if (Baseline.LIST_LABEL == null) {
            (Baseline.LIST_LABEL = new JLabel("X")).setBorder(new EmptyBorder(1, 1, 1, 1));
        }
        final JLabel list_LABEL = Baseline.LIST_LABEL;
        list_LABEL.setFont(list.getFont());
        if (n2 == -1) {
            n2 = list_LABEL.getPreferredSize().height;
        }
        return getLabelBaseline(list_LABEL, n2) + list.getInsets().top;
    }
    
    private static int getScrollPaneBaseline(final JScrollPane scrollPane, final int n) {
        final Component view = scrollPane.getViewport().getView();
        if (view instanceof JComponent) {
            final int baseline = getBaseline((JComponent)view);
            if (baseline > 0) {
                return baseline + scrollPane.getViewport().getY();
            }
        }
        return -1;
    }
    
    private static int getPanelBaseline(final JPanel panel, final int n) {
        final Border border = panel.getBorder();
        if (border instanceof TitledBorder) {
            final TitledBorder titledBorder = (TitledBorder)border;
            if (titledBorder.getTitle() != null && !"".equals(titledBorder.getTitle())) {
                Font font = titledBorder.getTitleFont();
                if (font == null) {
                    font = panel.getFont();
                    if (font == null) {
                        font = new Font("Dialog", 0, 12);
                    }
                }
                final Border border2 = titledBorder.getBorder();
                Insets insets;
                if (border2 != null) {
                    insets = border2.getBorderInsets(panel);
                }
                else {
                    insets = Baseline.EMPTY_INSETS;
                }
                final FontMetrics fontMetrics = panel.getFontMetrics(font);
                final int height = fontMetrics.getHeight();
                final int descent = fontMetrics.getDescent();
                final int ascent = fontMetrics.getAscent();
                final int n2 = 2;
                final int n3 = n - 4;
                switch (((TitledBorder)border).getTitlePosition()) {
                    case 1: {
                        return n2 + (ascent + descent + (Math.max(2, 4) - 2)) - (descent + 2);
                    }
                    case 0:
                    case 2: {
                        return n2 + Math.max(0, ascent / 2 + 2 - 2) - descent + (insets.top + ascent + descent) / 2;
                    }
                    case 3: {
                        return n2 + insets.top + ascent + 2;
                    }
                    case 4: {
                        return n2 + n3 - (insets.bottom + descent + 2);
                    }
                    case 5: {
                        return n2 + (n3 - height / 2) - descent + (ascent + descent - insets.bottom) / 2;
                    }
                    case 6: {
                        return n2 + (n3 - height) + ascent + 2;
                    }
                }
            }
        }
        return -1;
    }
    
    private static int getSpinnerBaseline(final JSpinner spinner, int n) {
        final JComponent editor = spinner.getEditor();
        if (!(editor instanceof JSpinner.DefaultEditor)) {
            return spinner.getInsets().top + spinner.getFontMetrics(spinner.getFont()).getAscent();
        }
        final JSpinner.DefaultEditor defaultEditor = (JSpinner.DefaultEditor)editor;
        final JFormattedTextField textField = defaultEditor.getTextField();
        final Insets insets = spinner.getInsets();
        final Insets insets2 = defaultEditor.getInsets();
        final int n2 = insets.top + insets2.top;
        n -= n2 + insets.bottom + insets2.bottom;
        if (n <= 0) {
            return -1;
        }
        return n2 + getSingleLineTextBaseline(textField, n);
    }
    
    private static int getLabelBaseline(final JLabel c, final int n) {
        final Icon icon = c.isEnabled() ? c.getIcon() : c.getDisabledIcon();
        final FontMetrics fontMetrics = c.getFontMetrics(c.getFont());
        resetRects(c, n);
        SwingUtilities.layoutCompoundLabel(c, fontMetrics, "a", icon, c.getVerticalAlignment(), c.getHorizontalAlignment(), c.getVerticalTextPosition(), c.getHorizontalTextPosition(), Baseline.viewRect, Baseline.iconRect, Baseline.textRect, c.getIconTextGap());
        return Baseline.textRect.y + fontMetrics.getAscent();
    }
    
    private static int getComboBoxBaseline(final JComboBox comboBox, int n) {
        final Insets insets = comboBox.getInsets();
        int top = insets.top;
        n -= insets.top + insets.bottom;
        if (comboBox.isEditable()) {
            final ComboBoxEditor editor = comboBox.getEditor();
            if (editor != null && editor.getEditorComponent() instanceof JTextField) {
                return top + getSingleLineTextBaseline((JTextComponent)editor.getEditorComponent(), n);
            }
        }
        if (isMetal()) {
            if (isOceanTheme()) {
                top += 2;
                n -= 4;
            }
        }
        else if (isWindows()) {
            final String property = System.getProperty("os.version");
            if (property != null && Float.valueOf(property) > 4.0) {
                top += 2;
                n -= 4;
            }
        }
        final ListCellRenderer renderer = comboBox.getRenderer();
        if (!(renderer instanceof JLabel)) {
            return top + comboBox.getFontMetrics(comboBox.getFont()).getAscent();
        }
        final int n2 = top + getLabelBaseline((JLabel)renderer, n);
        if (isAqua()) {
            return n2 - 1;
        }
        return n2;
    }
    
    private static int getSingleLineTextBaseline(final JTextComponent textComponent, final int n) {
        final View rootView = textComponent.getUI().getRootView(textComponent);
        if (rootView.getViewCount() > 0) {
            final Insets insets = textComponent.getInsets();
            final int n2 = n - insets.top - insets.bottom;
            int top = insets.top;
            final int n3 = (int)rootView.getView(0).getPreferredSpan(1);
            if (n2 != n3) {
                top += (n2 - n3) / 2;
            }
            return top + textComponent.getFontMetrics(textComponent.getFont()).getAscent();
        }
        return -1;
    }
    
    private static int getButtonBaseline(final AbstractButton c, final int n) {
        final FontMetrics fontMetrics = c.getFontMetrics(c.getFont());
        resetRects(c, n);
        final String text = c.getText();
        if (text != null && text.startsWith("<html>")) {
            return -1;
        }
        SwingUtilities.layoutCompoundLabel(c, fontMetrics, "a", c.getIcon(), c.getVerticalAlignment(), c.getHorizontalAlignment(), c.getVerticalTextPosition(), c.getHorizontalTextPosition(), Baseline.viewRect, Baseline.iconRect, Baseline.textRect, (text == null) ? 0 : c.getIconTextGap());
        if (isAqua()) {
            return Baseline.textRect.y + fontMetrics.getAscent() + 1;
        }
        return Baseline.textRect.y + fontMetrics.getAscent();
    }
    
    private static void resetRects(final JComponent component, final int n) {
        final Insets insets = component.getInsets();
        Baseline.viewRect.x = insets.left;
        Baseline.viewRect.y = insets.top;
        Baseline.viewRect.width = component.getWidth() - (insets.right + Baseline.viewRect.x);
        Baseline.viewRect.height = n - (insets.bottom + Baseline.viewRect.y);
        final Rectangle textRect = Baseline.textRect;
        final Rectangle textRect2 = Baseline.textRect;
        final Rectangle textRect3 = Baseline.textRect;
        final Rectangle textRect4 = Baseline.textRect;
        final int n2 = 0;
        textRect4.height = n2;
        textRect3.width = n2;
        textRect2.y = n2;
        textRect.x = n2;
        final Rectangle iconRect = Baseline.iconRect;
        final Rectangle iconRect2 = Baseline.iconRect;
        final Rectangle iconRect3 = Baseline.iconRect;
        final Rectangle iconRect4 = Baseline.iconRect;
        final int n3 = 0;
        iconRect4.height = n3;
        iconRect3.width = n3;
        iconRect2.y = n3;
        iconRect.x = n3;
    }
    
    private static boolean isOceanTheme() {
        if (!Baseline.inSandbox) {
            try {
                final Field declaredField = MetalLookAndFeel.class.getDeclaredField("currentTheme");
                declaredField.setAccessible(true);
                return "javax.swing.plaf.metal.OceanTheme".equals(declaredField.get(null).getClass().getName());
            }
            catch (Exception ex) {
                Baseline.inSandbox = true;
            }
        }
        if (!Baseline.checkedForOcean) {
            Baseline.checkedForOcean = true;
            checkForOcean();
        }
        return Baseline.usingOcean;
    }
    
    private static void checkForOcean() {
        final String property = System.getProperty("java.specification.version");
        final int index = property.indexOf(46);
        String substring;
        String s;
        if (index != -1) {
            substring = property.substring(0, index);
            final int index2 = property.indexOf(46, index + 1);
            if (index2 == -1) {
                s = property.substring(index + 1);
            }
            else {
                s = property.substring(index + 1, index2);
            }
        }
        else {
            substring = property;
            s = null;
        }
        try {
            final int int1 = Integer.parseInt(substring);
            final int n = (s != null) ? Integer.parseInt(s) : 0;
            Baseline.usingOcean = (int1 > 1 || n > 4);
        }
        catch (NumberFormatException ex) {}
    }
    
    private static boolean isWindows() {
        return isWindows(UIManager.getLookAndFeel());
    }
    
    private static boolean isWindows(final LookAndFeel lookAndFeel) {
        if (lookAndFeel.getID() == "Windows") {
            return true;
        }
        if (!Baseline.checkedForWindows) {
            try {
                Baseline.WINDOWS_CLASS = Class.forName("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            }
            catch (ClassNotFoundException ex) {}
            Baseline.checkedForWindows = true;
        }
        return Baseline.WINDOWS_CLASS != null && Baseline.WINDOWS_CLASS.isInstance(lookAndFeel);
    }
    
    private static boolean isMetal() {
        return isMetal(UIManager.getLookAndFeel());
    }
    
    private static boolean isMetal(final LookAndFeel lookAndFeel) {
        return lookAndFeel.getID() == "Metal" || lookAndFeel instanceof MetalLookAndFeel;
    }
    
    private static boolean isGTK() {
        return UIManager.getLookAndFeel().getID() == "GTK";
    }
    
    private static boolean isAqua() {
        return UIManager.getLookAndFeel().getID() == "Aqua";
    }
    
    private static boolean isXP() {
        if (!Baseline.checkedForClassic) {
            try {
                Baseline.CLASSIC_WINDOWS = Class.forName("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
            }
            catch (ClassNotFoundException ex) {}
            Baseline.checkedForClassic = true;
        }
        if (Baseline.CLASSIC_WINDOWS != null && Baseline.CLASSIC_WINDOWS.isInstance(UIManager.getLookAndFeel())) {
            return false;
        }
        Boolean false = (Boolean)Toolkit.getDefaultToolkit().getDesktopProperty("win.xpstyle.themeActive");
        if (false == null) {
            false = Boolean.FALSE;
        }
        return false;
    }
    
    protected Baseline() {
    }
    
    public int getComponentBaseline(final JComponent component, final int n, final int n2) {
        return -1;
    }
    
    static {
        viewRect = new Rectangle();
        textRect = new Rectangle();
        iconRect = new Rectangle();
        EMPTY_INSETS = new Insets(0, 0, 0, 0);
        BASELINE_MAP = Collections.synchronizedMap(new HashMap<Object, Object>(1));
        BRB_I_MAP = Collections.synchronizedMap(new HashMap<Object, Object>(1));
        Method method = null;
        Method method2 = null;
        Object fieldValue = null;
        Object fieldValue2 = null;
        Object fieldValue3 = null;
        Object fieldValue4 = null;
        try {
            method = Component.class.getMethod("getBaseline", Integer.TYPE, Integer.TYPE);
            method2 = Component.class.getMethod("getBaselineResizeBehavior", (Class[])new Class[0]);
            final Class<?> forName = Class.forName("java.awt.Component$BaselineResizeBehavior");
            fieldValue = getFieldValue(forName, "CENTER_OFFSET");
            fieldValue2 = getFieldValue(forName, "CONSTANT_ASCENT");
            fieldValue3 = getFieldValue(forName, "CONSTANT_DESCENT");
            fieldValue4 = getFieldValue(forName, "OTHER");
        }
        catch (NoSuchMethodException ex) {}
        catch (ClassNotFoundException ex2) {}
        catch (NoSuchFieldException ex3) {}
        catch (IllegalAccessException ex4) {}
        if (method == null || method2 == null || fieldValue == null || fieldValue3 == null || fieldValue2 == null || fieldValue4 == null) {
            method2 = (method = null);
            fieldValue2 = (fieldValue = (fieldValue3 = (fieldValue4 = null)));
        }
        COMPONENT_BASELINE_METHOD = method;
        COMPONENT_BRB_METHOD = method2;
        ENUM_BRB_CENTER_OFFSET = fieldValue;
        ENUM_BRB_CONSTANT_ASCENT = fieldValue2;
        ENUM_BRB_CONSTANT_DESCENT = fieldValue3;
        ENUM_BRB_OTHER = fieldValue4;
    }
}
