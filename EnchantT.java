// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EnchantT extends BlizzT implements StatValued
{
    public static final EnchantT FEET_HASTE;
    public static final EnchantT FEET_MASTERY;
    public static final EnchantT FEET_AGI;
    public static final EnchantT FEET_HIT;
    public static final EnchantT HANDS_EXP;
    public static final EnchantT HANDS_MASTERY;
    public static final EnchantT HANDS_HASTE;
    public static final EnchantT HANDS_STR;
    public static final EnchantT BACK_SWORDGUARD;
    public static final EnchantT BACK_HIT;
    public static final EnchantT BACK_CRIT;
    public static final EnchantT CHEST_STATS;
    public static final EnchantT RING_AGI;
    public static final EnchantT WRIST_AGI;
    public static final EnchantT LEGS_AGI;
    public static final EnchantT SHOULDER_AGI;
    public static final EnchantT LW_LEGS_AGI;
    public static final EnchantT LW_WRIST_AGI;
    public static final EnchantT INS_SHOULDER_AGI;
    public static final Proc WEAPON_DANCING_STEEL;
    public static final Proc WEAPON_WINDSONG;
    public static final Proc WEAPON_ELEMENTAL_FORCE;
    public static final Proc WEAPON_JADE_SPIRIT;
    public static final Proc WEAPON_POWER_TORRENT;
    public static final Proc WEAPON_DANCING_STEEL_PVP;
    public static final Proc WEAPON_JADE_SPIRIT_PVP;
    public static final TypeMap<EnchantT> MAP;
    public static final Map<EquipT, EnchantT[]> EQUIP_MAP;
    public final int itemId;
    public final int spellId;
    public final TypeSet<EquipT> set;
    public final ProfT requiredProf;
    public final String shortName;
    public final String effect;
    
    static EnchantT find(final EquipT type, final String name) {
        for (final EnchantT x : EnchantT.MAP.by_id) {
            if (x.set.contains(type) && x.name.contains(name)) {
                return x;
            }
        }
        return null;
    }
    
    private EnchantT(final int id, final String name, final int itemId, final int spellId, final ProfT prof, final TypeSet<EquipT> set, final String effect) {
        super(id, name);
        this.itemId = itemId;
        this.spellId = spellId;
        this.requiredProf = prof;
        this.set = set;
        String temp = name;
        if (temp.startsWith("Enchant")) {
            final int pos = temp.indexOf(" - ");
            if (pos >= 0) {
                temp = temp.substring(pos + 3).trim();
            }
        }
        this.shortName = temp;
        this.effect = effect;
    }
    
    @Override
    public String toString() {
        return this.effect.isEmpty() ? this.name : (this.name + ": " + this.effect);
    }
    
    @Override
    public int getStat(final StatT statType) {
        return 0;
    }
    
    static {
        MAP = BlizzT.makeMap(new One(2929, "Enchant Ring - Striking", 0, 27920, ProfT.ENCH, StatValue.make(StatT.WDMG, 2), EquipT.FINGER), RING_AGI = new One(4359, "Enchant Ring - Greater Agility", 0, 103461, ProfT.ENCH, StatValue.make(StatT.AGI, 160), EquipT.FINGER), new One(4360, "Enchant Ring - Greater Intellect", 0, 103462, ProfT.ENCH, StatValue.make(StatT.INT, 160), EquipT.FINGER), new One(4361, "Enchant Ring - Greater Stamina", 0, 103463, ProfT.ENCH, StatValue.make(StatT.STA, 240), EquipT.FINGER), new One(4807, "Enchant Ring - Greater Strength", 0, 103465, ProfT.ENCH, StatValue.make(StatT.STR, 160), EquipT.FINGER), new One(4411, "Enchant Bracer - Mastery", 74700, 104338, null, StatValue.make(StatT.MASTERY, 170), EquipT.WRIST), new One(4412, "Enchant Bracer - Major Dodge", 74701, 104385, null, StatValue.make(StatT.DODGE, 170), EquipT.WRIST), new One(4414, "Enchant Bracer - Super Intellect", 74703, 84559, null, StatValue.make(StatT.INT, 180), EquipT.WRIST), new One(4414, "Enchant Bracer - Exceptional Strength", 74704, 84561, null, StatValue.make(StatT.STR, 180), EquipT.WRIST), WRIST_AGI = new One(4416, "Enchant Bracer - Greater Agility", 74705, 84557, null, StatValue.make(StatT.AGI, 180), EquipT.WRIST), LW_WRIST_AGI = new One(4875, "Fur Lining - Agility", 0, 124551, ProfT.LW, StatValue.make(StatT.AGI, 500), EquipT.WRIST), new One(4877, "Fur Lining - Intellect", 0, 124552, ProfT.LW, StatValue.make(StatT.INT, 500), EquipT.WRIST), new One(4878, "Fur Lining - Stamina", 0, 124553, ProfT.LW, StatValue.make(StatT.STA, 750), EquipT.WRIST), new One(4879, "Fur Lining - Strength", 0, 124554, ProfT.LW, StatValue.make(StatT.STR, 500), EquipT.WRIST), new One(4417, "Enchant Chest - Super Resilience", 74706, 104392, null, StatValue.make(StatT.PVP_RES, 200), EquipT.TORSO), new One(4418, "Enchant Chest - Mighty Spirit", 74707, 104393, null, StatValue.make(StatT.SPI, 200), EquipT.TORSO), CHEST_STATS = new All(4419, "Enchant Chest - Glorious Stats", 74708, 104395, null, 80, EquipT.TORSO), new One(4420, "Enchant Chest - Superior Stamina", 74709, 104397, null, StatValue.make(StatT.STA, 300), EquipT.TORSO), BACK_HIT = new One(4421, "Enchant Cloak - Accuracy", 74710, 104398, null, StatValue.make(StatT.HIT, 180), EquipT.CLOAK), new One(4422, "Enchant Cloak - Greater Protection", 74711, 104401, null, StatValue.make(StatT.STA, 200), EquipT.CLOAK), new One(4423, "Enchant Cloak - Superior Intellect", 74712, 104404, null, StatValue.make(StatT.INT, 180), EquipT.CLOAK), BACK_CRIT = new One(4424, "Enchant Cloak - Superior Critical Strike", 74713, 104403, null, StatValue.make(StatT.CRIT, 180), EquipT.CLOAK), new EnchantT(4892, "Lightweave Embroidery", 0, 125481, ProfT.TAILOR, EquipT.CLOAK, "+2000 Intellect for 15sec"), new EnchantT(4893, "Darkglow Embroidery", 0, 125482, ProfT.TAILOR, EquipT.CLOAK, "+3000 Spirit for 15sec"), BACK_SWORDGUARD = new EnchantT(4894, "Swordguard Embroidery", 0, 125483, ProfT.TAILOR, EquipT.CLOAK, "+4000 AP for 15sec"), FEET_HASTE = new One(4426, "Enchant Boots - Greater Haste", 74715, 104407, null, StatValue.make(StatT.HASTE, 175), EquipT.FEET), FEET_HIT = new One(4427, "Enchant Boots - Greater Precision", 74716, 104408, null, StatValue.make(StatT.HIT, 175), EquipT.FEET), FEET_AGI = new Two(4428, "Enchant Boots - Blurred Speed", 74717, 104409, null, StatValue.make(StatT.AGI, 140), StatValue.make(StatT.SPEED, 8), EquipT.FEET), FEET_MASTERY = new Two(4429, "Enchant Boots - Pandaren's Step", 74718, 104414, null, StatValue.make(StatT.MASTERY, 140), StatValue.make(StatT.SPEED, 8), EquipT.FEET), new Two(4062, "Enchant Boots - Earthen Vitality", 52743, 74189, null, StatValue.make(StatT.STA, 30), StatValue.make(StatT.SPEED, 8), EquipT.FEET), new Two(4105, "Enchant Boots - Assassin's Step", 64411, 74252, null, StatValue.make(StatT.AGI, 25), StatValue.make(StatT.SPEED, 8), EquipT.FEET), new Two(3232, "Enchant Boots - Tuskarr's Vitality", 44491, 47901, null, StatValue.make(StatT.STA, 15), StatValue.make(StatT.SPEED, 8), EquipT.FEET), new Two(4104, "Enchant Boots - Lavawalker", 64412, 74253, null, StatValue.make(StatT.MASTERY, 35), StatValue.make(StatT.SPEED, 8), EquipT.FEET), new Two(2939, "Enchant Boots - Cat's Swiftness", 28279, 34007, null, StatValue.make(StatT.AGI, 8), StatValue.make(StatT.SPEED, 8), EquipT.FEET), new Two(2940, "Enchant Boots - Boar's Speed", 28280, 34008, null, StatValue.make(StatT.STA, 9), StatValue.make(StatT.SPEED, 8), EquipT.FEET), new One(911, "Enchant Boots - Minor Speed", 38837, 13890, null, StatValue.make(StatT.SPEED, 8), EquipT.FEET), HANDS_HASTE = new One(4430, "Enchant Gloves - Greater Haste", 74719, 104416, null, StatValue.make(StatT.HASTE, 170), EquipT.HANDS), HANDS_EXP = new One(4431, "Enchant Gloves - Superior Expertise", 74720, 104417, null, StatValue.make(StatT.EXP, 170), EquipT.HANDS), HANDS_STR = new One(4432, "Enchant Gloves - Super Strength", 74721, 104419, null, StatValue.make(StatT.STR, 170), EquipT.HANDS), HANDS_MASTERY = new One(4433, "Enchant Gloves - Superior Mastery", 74722, 104420, null, StatValue.make(StatT.MASTERY, 170), EquipT.HANDS), new Two(4907, "Tiger Fang Inscription", 87580, 127015, null, StatValue.make(StatT.STR, 120), StatValue.make(StatT.CRIT, 80), EquipT.SHOULDER), new Two(4908, "Tiger Claw Inscription", 87579, 127014, null, StatValue.make(StatT.AGI, 120), StatValue.make(StatT.CRIT, 80), EquipT.SHOULDER), new Two(4909, "Crane Wing Inscription", 87578, 127013, null, StatValue.make(StatT.INT, 120), StatValue.make(StatT.CRIT, 80), EquipT.SHOULDER), new Two(4910, "Ox Horn Inscription", 87577, 127012, null, StatValue.make(StatT.STA, 180), StatValue.make(StatT.DODGE, 80), EquipT.SHOULDER), new Two(4803, "Greater Tiger Fang Inscription", 83006, 121192, null, StatValue.make(StatT.STR, 200), StatValue.make(StatT.CRIT, 100), EquipT.SHOULDER), SHOULDER_AGI = new Two(4804, "Greater Tiger Claw Inscription", 83007, 121193, null, StatValue.make(StatT.AGI, 200), StatValue.make(StatT.CRIT, 100), EquipT.SHOULDER), new Two(4805, "Greater Ox Horn Inscription", 87560, 121194, null, StatValue.make(StatT.STA, 300), StatValue.make(StatT.DODGE, 100), EquipT.SHOULDER), new Two(4806, "Greater Crane Wing Inscription", 87559, 121195, null, StatValue.make(StatT.INT, 200), StatValue.make(StatT.CRIT, 100), EquipT.SHOULDER), new Two(4912, "Secret Ox Horn Inscription", 87581, 113048, ProfT.INS, StatValue.make(StatT.STA, 750), StatValue.make(StatT.DODGE, 100), EquipT.SHOULDER), new Two(4913, "Secret Tiger Fang Inscription", 87585, 113047, ProfT.INS, StatValue.make(StatT.STR, 520), StatValue.make(StatT.CRIT, 100), EquipT.SHOULDER), INS_SHOULDER_AGI = new Two(4914, "Secret Tiger Claw Inscription", 87584, 113046, ProfT.INS, StatValue.make(StatT.AGI, 520), StatValue.make(StatT.CRIT, 100), EquipT.SHOULDER), new Two(4915, "Secret Crane Wing Inscription", 87582, 113045, ProfT.INS, StatValue.make(StatT.INT, 520), StatValue.make(StatT.CRIT, 100), EquipT.SHOULDER), new Two(4870, "Toughened Leg Armor", 85570, 124116, null, StatValue.make(StatT.STA, 250), StatValue.make(StatT.DODGE, 100), EquipT.LEGS), new Two(4871, "Sha-Touched Leg Armor", 85569, 124118, null, StatValue.make(StatT.AGI, 170), StatValue.make(StatT.CRIT, 100), EquipT.LEGS), new Two(4872, "Brutal Leg Armor", 85568, 124119, null, StatValue.make(StatT.STR, 170), StatValue.make(StatT.CRIT, 100), EquipT.LEGS), LEGS_AGI = new Two(4822, "Shadowleather Leg Armor", 83764, 122387, null, StatValue.make(StatT.AGI, 285), StatValue.make(StatT.CRIT, 165), EquipT.LEGS), new Two(4823, "Angerhide Leg Armor", 83765, 122388, null, StatValue.make(StatT.STR, 285), StatValue.make(StatT.CRIT, 165), EquipT.LEGS), new Two(4824, "Ironscale Leg Armor", 83763, 122386, null, StatValue.make(StatT.STA, 430), StatValue.make(StatT.DODGE, 165), EquipT.LEGS), new Two(4881, "Draconic Leg Reinforcements", 0, 124561, null, StatValue.make(StatT.STR, 285), StatValue.make(StatT.CRIT, 165), EquipT.LEGS), LW_LEGS_AGI = new Two(4880, "Primal Leg Reinforcements", 0, 124559, null, StatValue.make(StatT.AGI, 285), StatValue.make(StatT.CRIT, 165), EquipT.LEGS), new Two(4882, "Heavy Leg Reinforcements", 0, 124563, null, StatValue.make(StatT.STA, 430), StatValue.make(StatT.DODGE, 165), EquipT.LEGS), new Two(4825, "Greater Cerulean Spellthread", 82445, 122392, null, StatValue.make(StatT.INT, 285), StatValue.make(StatT.CRIT, 170), EquipT.LEGS), new Two(4826, "Greater Pearlescent Spellthread", 82444, 122393, null, StatValue.make(StatT.INT, 285), StatValue.make(StatT.SPI, 170), EquipT.LEGS), new Two(5003, "Cerulean Spellthread", 82443, 131862, null, StatValue.make(StatT.INT, 170), StatValue.make(StatT.CRIT, 100), EquipT.LEGS), new Two(5004, "Pearlescent Spellthread", 82442, 131863, null, StatValue.make(StatT.INT, 170), StatValue.make(StatT.SPI, 100), EquipT.LEGS), new One(4434, "Enchant Off-Hand - Major Intellect", 74729, 104445, null, StatValue.make(StatT.INT, 165), EquipT.NON_WEAP_OFF_HAND), new One(4869, "Sha Armor Kit", 85559, 124091, null, StatValue.make(StatT.STA, 150), EquipT.ARMOR_KIT), new Two(5035, "Enchant Weapon - Glorious Tyranny", 95349, 139631, null, StatValue.make(StatT.PVP_POW, 400), StatValue.make(StatT.PVP_RES, 200), EquipT.MELEE), WEAPON_POWER_TORRENT = new Proc(4097, "Enchant Weapon - Power Torrent", 52774, 74242, null, EquipT.MELEE, "+500 Intellect for 12sec"), WEAPON_WINDSONG = new Proc(4441, "Enchant Weapon - Windsong", 74723, 104425, null, EquipT.MELEE, "+1500 Secondary for 10sec"), WEAPON_JADE_SPIRIT = new Proc(4442, "Enchant Weapon - Jade Spirit", 74724, 104427, null, EquipT.MELEE, "+1650 Intellect for 12sec"), WEAPON_ELEMENTAL_FORCE = new Proc(4443, "Enchant Weapon - Elemental Force", 74725, 104430, null, EquipT.MELEE, "~3K Elemental Damage"), WEAPON_DANCING_STEEL = new Proc(4444, "Enchant Weapon - Dancing Steel", 74726, 104434, null, EquipT.MELEE, "+1650 Agility for 12sec"), WEAPON_DANCING_STEEL_PVP = new Proc(5125, "Enchant Weapon - Bloody Dancing Steel", 98163, 142468, null, EquipT.MELEE, EnchantT.WEAPON_DANCING_STEEL.effect), WEAPON_JADE_SPIRIT_PVP = new Proc(5124, "Enchant Weapon - Spirit of Conquest", 98164, 142469, null, EquipT.MELEE, EnchantT.WEAPON_JADE_SPIRIT.effect), new One(4227, "Enchant 2H Weapon - Mighty Agility", 68134, 95471, null, StatValue.make(StatT.AGI, 130), EquipT.TWO_HAND), new One(4918, "Living Steel Weapon Chain", 86597, 142468, null, StatValue.make(StatT.EXP, 200), EquipT.MELEE), new Proc(4699, "Lord Blastington's Scope of Doom", 77529, 109086, null, BlizzT.makeSet(EquipT.WAND, EquipT.RANGED), "+1800 Agility for 10sec"));
        final HashMap<EquipT, EnchantT[]> map = new HashMap<EquipT, EnchantT[]>();
        for (final EquipT e : EquipT.ALL) {
            final ArrayList<EnchantT> list = new ArrayList<EnchantT>();
            for (final EnchantT x : EnchantT.MAP.by_id) {
                if (x.set.contains(e)) {
                    list.add(x);
                }
            }
            map.put(e, list.toArray(new EnchantT[list.size()]));
        }
        EQUIP_MAP = Collections.unmodifiableMap((Map<? extends EquipT, ? extends EnchantT[]>)map);
    }
    
    public static class One extends EnchantT
    {
        final StatValue stat;
        
        One(final int id, final String name, final int itemId, final int spellId, final ProfT prof, final StatValue stat, final TypeSet<EquipT> set) {
            super(id, name, itemId, spellId, prof, set, stat.toString(), null);
            this.stat = stat;
        }
        
        @Override
        public int getStat(final StatT statType) {
            return (this.stat.type == statType) ? this.stat.value : 0;
        }
    }
    
    public static class Proc extends EnchantT
    {
        Proc(final int id, final String name, final int itemId, final int spellId, final ProfT prof, final TypeSet<EquipT> set, final String proc) {
            super(id, name, itemId, spellId, prof, set, proc, null);
        }
    }
    
    public static class Two extends EnchantT
    {
        final StatValue stat1;
        final StatValue stat2;
        
        Two(final int id, final String name, final int itemId, final int spellId, final ProfT prof, final StatValue stat1, final StatValue stat2, final TypeSet<EquipT> set) {
            super(id, name, itemId, spellId, prof, set, stat1 + " and " + stat2, null);
            this.stat1 = stat1;
            this.stat2 = stat2;
        }
        
        @Override
        public int getStat(final StatT type) {
            if (type == this.stat1.type) {
                return this.stat1.value;
            }
            if (type == this.stat2.type) {
                return this.stat2.value;
            }
            return 0;
        }
    }
    
    public static class All extends EnchantT
    {
        final int value;
        
        All(final int id, final String name, final int itemId, final int spellId, final ProfT prof, final int value, final TypeSet<EquipT> set) {
            super(id, name, itemId, spellId, prof, set, String.format("%+d All Stats", value), null);
            this.value = value;
        }
        
        @Override
        public int getStat(final StatT statType) {
            return statType.isPrimary() ? this.value : 0;
        }
    }
}
