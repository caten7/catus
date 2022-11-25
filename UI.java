// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import javax.swing.BorderFactory;
import javax.swing.KeyStroke;
import java.awt.Toolkit;
import javax.swing.Action;
import javax.swing.AbstractAction;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractButton;
import java.util.Set;
import java.util.Collection;
import java.awt.AWTKeyStroke;
import java.util.HashSet;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.border.Border;

public class UI
{
    public static final Border EMPTY_BORDER_1;
    public static final Font FONT_MONO;
    
    static void lookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {}
    }
    
    public static int scrollBarWidth() {
        return (int)UIManager.get("ScrollBar.width");
    }
    
    public static JPanel makePanel() {
        final JPanel temp = new JPanel();
        temp.setOpaque(false);
        return temp;
    }
    
    public static JTextField makeField() {
        final JTextField temp = new JTextField();
        temp.setOpaque(false);
        return temp;
    }
    
    public static JRadioButton makeRadio(final String name) {
        final JRadioButton temp = new JRadioButton(name);
        temp.setFocusable(false);
        temp.setOpaque(false);
        return temp;
    }
    
    public static JCheckBox makeCheck(final String name) {
        final JCheckBox temp = new JCheckBox(name);
        temp.setFocusable(false);
        temp.setOpaque(false);
        return temp;
    }
    
    public static <T extends Component> T makeBold(final T x) {
        x.setFont(x.getFont().deriveFont(1));
        return x;
    }
    
    public static JLabel L(final String text, final Font font) {
        final JLabel temp = new JLabel(text);
        temp.setFont(font);
        return temp;
    }
    
    public static JButton makeButton(final String name) {
        final JButton temp = new JButton(name);
        temp.setFocusable(false);
        temp.setOpaque(false);
        return temp;
    }
    
    public static JLabel makeIcon(final Image image) {
        final JLabel temp = new JLabel();
        if (image != null) {
            temp.setIcon(new ImageIcon(image));
        }
        return temp;
    }
    
    public static JComboBox makeCombo() {
        final JComboBox temp = new JComboBox();
        temp.setMaximumRowCount(30);
        temp.setFocusable(false);
        temp.setOpaque(false);
        return temp;
    }
    
    public static void fixCombo(final JComboBox temp) {
        temp.setMaximumRowCount(30);
        temp.setFocusable(false);
        temp.setOpaque(false);
    }
    
    public static Component gap() {
        return Box.createHorizontalStrut(16);
    }
    
    public static boolean isAltKeyDown(final ActionEvent ev) {
        return (ev.getModifiers() & 0x8) > 0;
    }
    
    public static boolean isShiftKeyDown(final ActionEvent ev) {
        return (ev.getModifiers() & 0x1) > 0;
    }
    
    public static IntDist parseIntDist_time(final JTextField f, final int scale) {
        return InvFmt.parseIntDist_time(f.getText(), f.getName(), scale);
    }
    
    public static int parseInt_time(final JTextField f, final int scale) {
        return InvFmt.parse_time(f.getText(), f.getName(), scale, true);
    }
    
    public static int parse_nonNegIntOrPercentWithScale(final JTextField f, final int def, final double percScale) {
        String s = f.getText().trim();
        if (s.isEmpty()) {
            return def;
        }
        int val;
        if (s.endsWith("%")) {
            s = s.substring(0, s.length() - 1).trim();
            double perc;
            try {
                perc = InvFmt.parseLocalDouble(s);
            }
            catch (NumberFormatException err) {
                throw new RuntimeException(String.format("%s is not an percentage (%s)", f.getName(), s));
            }
            val = (int)(perc * percScale / 100.0);
        }
        else {
            try {
                val = Integer.parseInt(s);
            }
            catch (NumberFormatException err2) {
                throw new RuntimeException(String.format("%s is not an integer (%s)", f.getName(), s));
            }
        }
        if (val < 0) {
            throw new RuntimeException(String.format("%s must be non-negative (%d)", f.getName(), val));
        }
        return val;
    }
    
    public static int parse_nonNegInt(final JTextField f) {
        return parse_nonNegInt(f, 0);
    }
    
    public static int parse_nonNegInt(final JTextField f, final int def) {
        final String s = f.getText().trim();
        if (s.isEmpty()) {
            return def;
        }
        try {
            final int val = Integer.parseInt(s);
            if (val < 0) {
                throw new RuntimeException(String.format("%s must be non-negative (%d)", f.getName(), val));
            }
            return val;
        }
        catch (NumberFormatException err) {
            throw new RuntimeException(String.format("%s is not an integer (%s)", f.getName(), s));
        }
    }
    
    public static int parse_posInt(final JTextField f, final boolean required) {
        final String s = f.getText().trim();
        if (s.isEmpty()) {
            if (required) {
                throw new RuntimeException(String.format("%s is required.", f.getName()));
            }
            return 0;
        }
        else {
            try {
                final int val = Integer.parseInt(s);
                if (val < 1) {
                    throw new RuntimeException(String.format("%s must be positive (%d)", f.getName(), val));
                }
                return val;
            }
            catch (NumberFormatException err) {
                throw new RuntimeException(String.format("%s is not an integer (%s)", f.getName(), s));
            }
        }
    }
    
    public static int parse_int(final JTextField f) {
        final String s = f.getText().trim();
        if (s.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException err) {
            throw new RuntimeException(String.format("%s is not an integer (%s)", f.getName(), s));
        }
    }
    
    public static double parse_percent(final JTextField f, final double def, final double min, final double max, final boolean allowEmpty) {
        return InvFmt.parse_percent(f.getText(), f.getName(), def, min, max, true);
    }
    
    public static double parse_double(final JTextField f, final double def, final double min, final double max) {
        return InvFmt.parse_bigNum(f.getText(), f.getName(), def, min, max, true);
    }
    
    public static void addLeftRightToTraversalKeys(final Component c) {
        addTraversalKeys(c, 0, 39);
        addTraversalKeys(c, 1, 37);
    }
    
    public static void addTraversalKeys(final Component c, final int keysetId, final int... keyCodes) {
        final HashSet<AWTKeyStroke> newKeys = new HashSet<AWTKeyStroke>(c.getFocusTraversalKeys(keysetId));
        for (final int keyCode : keyCodes) {
            newKeys.add(AWTKeyStroke.getAWTKeyStroke(keyCode, 0));
        }
        c.setFocusTraversalKeys(keysetId, newKeys);
    }
    
    public static String wrapAsHtml(final String msg) {
        return "<html><p style='width:400px'>" + Utils.htmlEscape(msg).replace("\n", "<br/>") + "</p></html>";
    }
    
    public static void setComboText(final JComboBox combo) {
        setComboText(combo, combo.getPrototypeDisplayValue().toString());
    }
    
    public static void setComboText(final JComboBox combo, final String text) {
        combo.setEditable(true);
        combo.setSelectedItem(text);
        combo.setEditable(false);
    }
    
    public static void setComboFromString(final JComboBox combo, final String text) {
        final int num = combo.getItemCount();
        if (text != null) {
            for (int i = 0; i < num; ++i) {
                if (combo.getItemAt(i).toString().equalsIgnoreCase(text)) {
                    combo.setSelectedIndex(i);
                    return;
                }
            }
        }
        if (num > 0) {
            combo.setSelectedIndex(0);
        }
    }
    
    public static <T extends AbstractButton> void onChange(final T btn, final Handler<T> h) {
        btn.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent ie) {
                h.handle(btn);
            }
        });
        btn.addPropertyChangeListener("enabled", new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent pce) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        h.handle(btn);
                    }
                });
            }
        });
        h.handle(btn);
    }
    
    public static void onChange_enable(final boolean flip, final AbstractButton btn, final Component... others) {
        btn.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent ie) {
                final boolean b = btn.isSelected() == flip;
                for (final Component x : others) {
                    x.setEnabled(b);
                }
            }
        });
        final boolean b = btn.isSelected() == flip;
        for (final Component x : others) {
            x.setEnabled(b);
        }
    }
    
    public static void onChange_enable(final AbstractButton btn, final Component... others) {
        onChange_enable(true, btn, others);
    }
    
    public static void scrollY(final JComponent c) {
        c.validate();
        final Rectangle r = c.getBounds();
        r.width = 1;
        c.scrollRectToVisible(r);
    }
    
    public static void setIconAndSize(final AbstractButton btn, final ImageIcon icon) {
        btn.setIcon(icon);
        final Insets insets = btn.getInsets();
        btn.setPreferredSize(new Dimension(icon.getIconWidth() + insets.left + insets.right, icon.getIconHeight() + insets.top + insets.bottom));
    }
    
    public static void onShortcut(final JComponent comp, final int key, final String name, final Runnable r) {
        comp.getActionMap().put(name, new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                r.run();
            }
        });
        comp.getInputMap(2).put(KeyStroke.getKeyStroke(key, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), name);
    }
    
    static {
        EMPTY_BORDER_1 = BorderFactory.createEmptyBorder(1, 1, 1, 1);
        FONT_MONO = Font.decode("Monospaced").deriveFont(11.0f);
    }
}
