// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class SlotT extends BlizzT
{
    static final int BODYPART_ARMOR = 1;
    static final int BODYPART_HAND = 2;
    static final int BODYPART_JEWELRY = 4;
    static final int BODYPART_CLOTH = 8;
    static final int BODYPARTS_NO_HAND = 13;
    static final int BODYPARTS_ALL = 15;
    final TypeSet<EquipT> set;
    final int bodyPart;
    public static final SlotT HEAD;
    public static final SlotT NECK;
    public static final SlotT SHOULDER;
    public static final SlotT SHIRT;
    public static final SlotT CHEST;
    public static final SlotT WAIST;
    public static final SlotT LEGS;
    public static final SlotT FEET;
    public static final SlotT WRIST;
    public static final SlotT HANDS;
    public static final SlotT FINGER_1;
    public static final SlotT FINGER_2;
    public static final SlotT TRINKET_1;
    public static final SlotT TRINKET_2;
    public static final SlotT BACK;
    public static final SlotT MAIN_HAND;
    public static final SlotT OFF_HAND;
    public static final SlotT TABARD;
    public static final SlotT[] ALL;
    public static final TypeMap<SlotT> MAP;
    static final int NUM;
    
    SlotT(final int id, final String name, final int bodyPart, final TypeSet<EquipT> set) {
        super(id, name);
        this.bodyPart = bodyPart;
        this.set = set;
    }
    
    public String equipName() {
        return (this.set instanceof EquipT) ? ((EquipT)this.set).name : this.name;
    }
    
    public boolean accepts(final EquipT x) {
        return this.set.contains(x);
    }
    
    static {
        ALL = new SlotT[] { HEAD = new SlotT(1, "Head", 1, EquipT.HEAD), NECK = new SlotT(2, "Neck", 4, EquipT.NECK), SHOULDER = new SlotT(3, "Shoulder", 1, EquipT.SHOULDER), SHIRT = new SlotT(4, "Shirt", 8, EquipT.SHIRT), CHEST = new SlotT(5, "Chest", 1, EquipT.TORSO), WAIST = new SlotT(6, "Waist", 1, EquipT.WAIST), LEGS = new SlotT(7, "Legs", 1, EquipT.LEGS), FEET = new SlotT(8, "Feet", 1, EquipT.FEET), WRIST = new SlotT(9, "Wrist", 1, EquipT.WRIST), HANDS = new SlotT(10, "Hands", 1, EquipT.HANDS), FINGER_1 = new SlotT(11, "Finger 1", 4, EquipT.FINGER), FINGER_2 = new SlotT(12, "Finger 2", 4, EquipT.FINGER), TRINKET_1 = new SlotT(13, "Trinket 1", 4, EquipT.TRINKET), TRINKET_2 = new SlotT(14, "Trinket 2", 4, EquipT.TRINKET), BACK = new SlotT(15, "Back", 8, EquipT.CLOAK), MAIN_HAND = new SlotT(16, "Main Hand", 2, EquipT.MAIN_HANDS), OFF_HAND = new SlotT(17, "Off Hand", 2, EquipT.OFF_HANDS), TABARD = new SlotT(19, "Tabard", 8, EquipT.TABARD) };
        MAP = BlizzT.makeMap(SlotT.ALL);
        int max = 0;
        for (final SlotT x : SlotT.ALL) {
            max = Math.max(max, x.id);
        }
        NUM = max + 1;
    }
}
