// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public abstract class Action
{
    public final String actionName;
    
    public Action(final String name) {
        this.actionName = name;
    }
    
    public boolean actionable() {
        return true;
    }
    
    public abstract void runAction();
    
    @Override
    public String toString() {
        return this.actionName;
    }
}
