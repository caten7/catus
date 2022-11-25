// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Component;
import javax.swing.JTabbedPane;

public class SmartTabPane extends JTabbedPane
{
    final T[] stores;
    
    public SmartTabPane(final Component... tabs) {
        this.setFocusable(false);
        this.stores = new T[tabs.length];
        for (int i = 0; i < tabs.length; ++i) {
            final T t = new T(tabs[i]);
            this.addTab(t.real.getName(), t.fake);
            this.stores[i] = t;
        }
        this.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent ce) {
                SmartTabPane.this.update(SmartTabPane.this.getSelectedIndex());
            }
        });
        this.update(0);
    }
    
    private void update(final int index) {
        for (int i = 0; i < this.stores.length; ++i) {
            this.setComponentAt(i, this.stores[i].get(i == index));
        }
        this.tabChanged(index);
    }
    
    public void tabChanged(final int index) {
    }
    
    public String getSelectedTitle() {
        return this.getTitleAt(this.getSelectedIndex());
    }
    
    public boolean setSelectedTitle(final String name) {
        for (int i = 0, e = this.getTabCount(); i < e; ++i) {
            if (this.getTitleAt(i).equalsIgnoreCase(name)) {
                this.setSelectedIndex(i);
                return true;
            }
        }
        return false;
    }
    
    static class T
    {
        final Component real;
        final JPanel fake;
        
        T(final Component tab) {
            this.real = tab;
            (this.fake = new JPanel()).setOpaque(false);
        }
        
        Component get(final boolean selected) {
            return selected ? this.real : this.fake;
        }
    }
}
