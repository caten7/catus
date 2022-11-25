// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class LockableActionListener implements ActionListener
{
    private int locked;
    
    @Override
    public void actionPerformed(final ActionEvent ae) {
        if (this.locked == 0) {
            this.action(ae);
        }
    }
    
    public void lock() {
        ++this.locked;
    }
    
    public void unlock() {
        --this.locked;
    }
    
    public abstract void action(final ActionEvent p0);
}
