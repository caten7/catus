// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class SetBonus
{
    public final int index;
    public final int threshold;
    public final String description;
    
    SetBonus(final int index, final int threshold, final String description) {
        this.index = index;
        this.threshold = threshold;
        this.description = description;
    }
    
    @Override
    public String toString() {
        return String.format("(%d) %s", this.threshold, this.description);
    }
}
