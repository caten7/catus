// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class GemCoord
{
    public final int slotIndex;
    public final int socketIndex;
    public final Gem gem;
    
    public GemCoord(final int slotIndex, final int socketIndex, final Gem gem) {
        this.slotIndex = slotIndex;
        this.socketIndex = socketIndex;
        this.gem = gem;
    }
}
