// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class StatAlloc
{
    public final StatT stat;
    public final int alloc;
    public final double mod;
    
    public StatAlloc(final StatT stat, final int alloc, final double mod) {
        this.stat = stat;
        this.alloc = alloc;
        this.mod = mod;
    }
    
    @Override
    public String toString() {
        return String.format("%s: %d (%.1f)", this.stat, this.alloc, this.mod);
    }
}
