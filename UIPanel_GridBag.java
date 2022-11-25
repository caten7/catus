// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.Box;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;

public class UIPanel_GridBag extends UIPanel
{
    UIPanel_GridBag() {
        this.setLayout(new GridBagLayout());
    }
    
    void spacer() {
        this.spacer(Box.createGlue());
    }
    
    void spacer(final Component p) {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.fill = 2;
        this.add(p, gbc);
    }
    
    void balloon() {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = 1;
        this.add(Box.createGlue(), gbc);
    }
    
    void gap_add(final int gap, final Component p) {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, gap, 0, 0);
        this.add(p, gbc);
    }
    
    void row(final Component p, final boolean fill, final int pad) {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        if (fill) {
            gbc.weightx = 1.0;
            gbc.fill = 2;
        }
        if (pad > 0) {
            gbc.insets = new Insets(pad, 0, 0, 0);
        }
        this.add(p, gbc);
    }
    
    void row_both(final Component p) {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = 1;
        this.add(p, gbc);
    }
}
