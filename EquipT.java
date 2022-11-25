// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class EquipT extends BlizzT_SetMember implements TypeSet<EquipT>
{
    static final double MOD_NONE = Double.NaN;
    static final double MOD_HEAD_CHEST_LEGS_TWOHAND = 1.0;
    static final double MOD_SHOULDER_HANDS_WAIST_FEET_TRINKET = 0.7428571428571429;
    static final double MOD_WRIST_NECK_BACK_FINGER_OFFHAND = 0.5571428571428572;
    static final double MOD_ONE_HAND = 0.42857142857142855;
    public static final EquipT HEAD;
    public static final EquipT SHOULDER;
    public static final EquipT WAIST;
    public static final EquipT LEGS;
    public static final EquipT FEET;
    public static final EquipT WRIST;
    public static final EquipT HANDS;
    public static final EquipT CHEST;
    public static final EquipT ROBE;
    public static final EquipT NECK;
    public static final EquipT TRINKET;
    public static final EquipT FINGER;
    public static final EquipT CLOAK;
    public static final EquipT NONE;
    public static final EquipT SHIRT;
    public static final EquipT TABARD;
    public static final EquipT ONE_HAND;
    public static final EquipT TWO_HAND;
    public static final EquipT SHIELD;
    public static final EquipT RANGED;
    public static final EquipT OFF_HAND;
    public static final EquipT MAIN_HAND;
    public static final EquipT HELD_IN_OFF;
    public static final EquipT WAND;
    public static final EquipT[] ALL;
    public static final TypeSet<EquipT> TORSO;
    public static final TypeSet<EquipT> MELEE;
    public static final TypeSet<EquipT> NON_WEAP_OFF_HAND;
    public static final TypeSet<EquipT> MAIN_HANDS;
    public static final TypeSet<EquipT> OFF_HANDS;
    public static final TypeSet<EquipT> ARMOR_KIT;
    public static final TypeMap<EquipT> MAP;
    public final double slotMod;
    public final double armorMod;
    
    private EquipT(final int id, final String name, final double armorMod, final double slotMod) {
        super(id, name);
        this.slotMod = slotMod;
        this.armorMod = armorMod;
    }
    
    @Override
    public boolean contains(final EquipT x) {
        return x == this;
    }
    
    static {
        ALL = new EquipT[] { NONE = new EquipT(0, "None", 0.0, Double.NaN), HEAD = new EquipT(1, "Head", 0.13, 1.0), NECK = new EquipT(2, "Neck", 0.0, 0.5571428571428572), SHOULDER = new EquipT(3, "Shoulder", 0.12, 0.7428571428571429), SHIRT = new EquipT(4, "Shirt", 0.0, Double.NaN), CHEST = new EquipT(5, "Chest", 0.16, 1.0), WAIST = new EquipT(6, "Waist", 0.09, 0.7428571428571429), LEGS = new EquipT(7, "Legs", 0.14, 1.0), FEET = new EquipT(8, "Feet", 0.11, 0.7428571428571429), WRIST = new EquipT(9, "Wrist", 0.07, 0.5571428571428572), HANDS = new EquipT(10, "Hands", 0.1, 0.7428571428571429), FINGER = new EquipT(11, "Finger", 0.0, 0.5571428571428572), TRINKET = new EquipT(12, "Trinket", 0.0, 0.7428571428571429), ONE_HAND = new EquipT(13, "One-Hand", 0.0, 0.42857142857142855), SHIELD = new EquipT(14, "Shield", 0.0, 0.5571428571428572), RANGED = new EquipT(15, "Ranged", 0.0, 1.0), CLOAK = new EquipT(16, "Cloak", 0.08, 0.5571428571428572), TWO_HAND = new EquipT(17, "Two-Hand", 0.0, 1.0), TABARD = new EquipT(19, "Tabard", 0.0, Double.NaN), ROBE = new EquipT(20, "Robe", 0.16, 1.0), MAIN_HAND = new EquipT(21, "Main Hand", 0.0, 0.42857142857142855), OFF_HAND = new EquipT(22, "Off Hand", 0.0, 0.42857142857142855), HELD_IN_OFF = new EquipT(23, "Held in Off-hand", 0.0, 0.5571428571428572), WAND = new EquipT(26, "Ranged", 0.0, 1.0) };
        TORSO = BlizzT.makeSet(EquipT.CHEST, EquipT.ROBE);
        MELEE = BlizzT.makeSet(EquipT.ONE_HAND, EquipT.TWO_HAND, EquipT.MAIN_HAND, EquipT.OFF_HAND);
        NON_WEAP_OFF_HAND = BlizzT.makeSet(EquipT.HELD_IN_OFF, EquipT.SHIELD);
        MAIN_HANDS = BlizzT.makeSet(EquipT.TWO_HAND, EquipT.ONE_HAND, EquipT.MAIN_HAND, EquipT.RANGED, EquipT.WAND);
        OFF_HANDS = BlizzT.makeSet(EquipT.ONE_HAND, EquipT.OFF_HAND, EquipT.HELD_IN_OFF, EquipT.SHIELD);
        ARMOR_KIT = BlizzT.makeSet(EquipT.CHEST, EquipT.ROBE, EquipT.SHOULDER, EquipT.LEGS, EquipT.HANDS, EquipT.FEET);
        MAP = BlizzT.makeMap(EquipT.ALL);
    }
}
