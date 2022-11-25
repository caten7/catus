// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class UIPanel extends JPanel
{
    public UIPanel() {
        this.setOpaque(false);
    }
    
    void pad(final int x) {
        this.pad(x, x);
    }
    
    void pad(final int x, final int y) {
        this.setBorder(BorderFactory.createEmptyBorder(y, x, y, x));
    }
    
    void gap() {
        this.add(UI.gap());
    }
    
    void gap(final int w) {
        this.add(Box.createHorizontalStrut(w));
    }
    
    void cap_top(final int x) {
        this.setBorder(BorderFactory.createEmptyBorder(x, x, 0, x));
    }
    
    void cap_bot(final int x) {
        this.setBorder(BorderFactory.createEmptyBorder(0, x, x, x));
    }
}
