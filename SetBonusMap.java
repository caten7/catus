// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class SetBonusMap
{
    public static final SetBonusMap EMPTY;
    final IntSet keys;
    
    public SetBonusMap() {
        this.keys = new IntSet();
    }
    
    static int key(final int id, final int index) {
        return id << 3 | index;
    }
    
    public void setBonus(final int id, final int index) {
        this.keys.add(key(id, index));
    }
    
    public boolean hasBonus(final int id, final int index) {
        return this.keys.contains(key(id, index));
    }
    
    public void clear() {
        this.keys.clear();
    }
    
    public void set(final SetBonusMap map) {
        this.keys.setAll(map.keys);
    }
    
    static {
        EMPTY = new SetBonusMap();
    }
}
