// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.FontMetrics;
import javax.swing.JComboBox;

public class UIComboBox<T> extends JComboBox
{
    private boolean wide;
    private boolean layingOut;
    
    public UIComboBox() {
        this.setMaximumRowCount(30);
        this.setFocusable(false);
        this.setOpaque(false);
    }
    
    public UIComboBox(final T[] items) {
        this();
        for (final T x : items) {
            this.addItem(x);
        }
    }
    
    public UIComboBox(final T first, final T... rest) {
        this();
        this.addItem(first);
        for (final T x : rest) {
            this.addItem(x);
        }
    }
    
    public void setWide(final boolean wide) {
        this.wide = wide;
    }
    
    public int getPopupWidth() {
        final FontMetrics metrics = this.getFontMetrics(this.getFont());
        int max = 0;
        for (int i = 0, e = this.getItemCount(); i < e; ++i) {
            max = Math.max(max, metrics.stringWidth(this.getItemAt(i).toString()));
        }
        return max + metrics.stringWidth("  ");
    }
    
    @Override
    public Dimension getSize() {
        final Dimension size = super.getSize();
        if (this.wide && !this.layingOut) {
            final Insets insets = this.getInsets();
            size.width = Math.max(size.width, this.getPopupWidth() + insets.left + insets.right);
        }
        return size;
    }
    
    @Override
    public void doLayout() {
        if (this.wide) {
            try {
                this.layingOut = true;
                super.doLayout();
            }
            finally {
                this.layingOut = false;
            }
        }
        else {
            super.doLayout();
        }
    }
    
    public T at(final int index) {
        return this.getItemAt(index);
    }
    
    public T getPick() {
        return (T)this.getSelectedItem();
    }
    
    public String exportPick() {
        final Object sel = this.getSelectedItem();
        return (sel == null) ? "" : sel.toString();
    }
    
    public void importPick(final String name, final int index) {
        if (name != null) {
            for (int i = 0, e = this.getItemCount(); i < e; ++i) {
                if (name.equals(this.getItemAt(i).toString())) {
                    this.setSelectedIndex(i);
                    return;
                }
            }
        }
        this.setSelectedIndex(index);
    }
    
    public void importPick(final String name, final T backup) {
        if (name != null) {
            for (int i = 0, e = this.getItemCount(); i < e; ++i) {
                if (name.equals(this.getItemAt(i).toString())) {
                    this.setSelectedIndex(i);
                    return;
                }
            }
        }
        this.setSelectedItem(backup);
    }
}
