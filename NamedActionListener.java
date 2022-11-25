// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class NamedActionListener extends NamedThing implements ActionListener
{
    public NamedActionListener(final String name) {
        super(name);
    }
    
    @Override
    public abstract void actionPerformed(final ActionEvent p0);
}
