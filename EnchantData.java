// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class EnchantData
{
    public static final int TYPE_STAT = 5;
    public final int id;
    public final SlotT slot;
    public final String name;
    public final Record[] records;
    
    EnchantData(final int id, final SlotT slot, final String name, final Record[] records) {
        this.id = id;
        this.slot = slot;
        this.name = name;
        this.records = records;
    }
    
    public static class Record
    {
        public final int type;
        public final int amount;
        public final int prop;
        public final double coeff;
        
        Record(final int type, final int amount, final int prop, final double coeff) {
            this.type = type;
            this.amount = amount;
            this.prop = prop;
            this.coeff = coeff;
        }
        
        public StatT getStat() {
            return StatT.MAP.get(this.prop);
        }
    }
}
