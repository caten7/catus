// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.ArrayList;
import java.util.HashMap;

public class SpecT
{
    public final int specId;
    public final int specIndex;
    public final String name;
    public final boolean canDualWield;
    public final StatT primaryStat;
    public final ClassT classType;
    public final ArmorT armorType;
    public static final SpecT FERAL;
    public static final SpecT WIND;
    public static final SpecT GUARD;
    public static final SpecT BREW;
    public static final SpecT BM;
    public static final SpecT[] ALL;
    static final HashMap<String, SpecT> nameMap;
    static final HashMap<ClassT, SpecT[]> classMap;
    static final String SPEC_CHARS = "aZbY";
    
    public SpecT(final int specId, final int specIndex, final String name, final ClassT classType, final ArmorT armorType, final StatT primaryStat, final boolean canDualWield) {
        this.specId = specId;
        this.specIndex = specIndex;
        this.name = name;
        this.classType = classType;
        this.armorType = armorType;
        this.primaryStat = primaryStat;
        this.canDualWield = canDualWield;
    }
    
    public static int specIndex(final String s) {
        return "aZbY".indexOf(s);
    }
    
    public char specChar() {
        return "aZbY".charAt(this.specIndex);
    }
    
    static {
        ALL = new SpecT[] { new SpecT(250, 0, "Blood", ClassT.DK, ArmorT.PLATE, StatT.STR, true), new SpecT(251, 1, "Frost", ClassT.DK, ArmorT.PLATE, StatT.STR, true), new SpecT(252, 2, "Unholy", ClassT.DK, ArmorT.PLATE, StatT.STR, true), new SpecT(102, 0, "Balance", ClassT.DRUID, ArmorT.LEATHER, StatT.INT, false), FERAL = new SpecT(103, 1, "Feral", ClassT.DRUID, ArmorT.LEATHER, StatT.AGI, false), GUARD = new SpecT(104, 2, "Guardian", ClassT.DRUID, ArmorT.LEATHER, StatT.AGI, false), new SpecT(105, 3, "Restoration", ClassT.DRUID, ArmorT.LEATHER, StatT.INT, false), BM = new SpecT(253, 0, "Beastmaster", ClassT.HUNTER, ArmorT.MAIL, StatT.AGI, true), new SpecT(254, 1, "Marksmenship", ClassT.HUNTER, ArmorT.MAIL, StatT.AGI, true), new SpecT(255, 2, "Survival", ClassT.HUNTER, ArmorT.MAIL, StatT.AGI, true), BREW = new SpecT(268, 0, "Brewmaster", ClassT.MONK, ArmorT.LEATHER, StatT.AGI, true), new SpecT(270, 1, "Mistweaver", ClassT.MONK, ArmorT.LEATHER, StatT.INT, false), WIND = new SpecT(269, 2, "Windwalker", ClassT.MONK, ArmorT.LEATHER, StatT.AGI, true), new SpecT(62, 0, "Arcane", ClassT.MAGE, ArmorT.CLOTH, StatT.INT, false), new SpecT(63, 1, "Fire", ClassT.MAGE, ArmorT.CLOTH, StatT.INT, false), new SpecT(64, 2, "Frost", ClassT.MAGE, ArmorT.CLOTH, StatT.INT, false), new SpecT(65, 0, "Holy", ClassT.PALADIN, ArmorT.PLATE, StatT.INT, false), new SpecT(66, 1, "Protection", ClassT.PALADIN, ArmorT.PLATE, StatT.STR, false), new SpecT(70, 2, "Retribution", ClassT.PALADIN, ArmorT.PLATE, StatT.STR, false), new SpecT(256, 0, "Discipline", ClassT.PRIEST, ArmorT.CLOTH, StatT.INT, false), new SpecT(257, 1, "Holy", ClassT.PRIEST, ArmorT.CLOTH, StatT.INT, false), new SpecT(258, 2, "Shadow", ClassT.PRIEST, ArmorT.CLOTH, StatT.INT, false), new SpecT(259, 0, "Assassination", ClassT.ROGUE, ArmorT.LEATHER, StatT.AGI, true), new SpecT(260, 1, "Combat", ClassT.ROGUE, ArmorT.LEATHER, StatT.AGI, true), new SpecT(261, 2, "Subtlety", ClassT.ROGUE, ArmorT.LEATHER, StatT.AGI, true), new SpecT(262, 0, "Elemental", ClassT.SHAMAN, ArmorT.MAIL, StatT.INT, false), new SpecT(263, 1, "Enhancement", ClassT.SHAMAN, ArmorT.MAIL, StatT.AGI, true), new SpecT(264, 2, "Restoration", ClassT.SHAMAN, ArmorT.MAIL, StatT.INT, false), new SpecT(265, 0, "Affliction", ClassT.WARLOCK, ArmorT.CLOTH, StatT.INT, false), new SpecT(266, 1, "Demonology", ClassT.WARLOCK, ArmorT.CLOTH, StatT.INT, false), new SpecT(267, 2, "Destruction", ClassT.WARLOCK, ArmorT.CLOTH, StatT.INT, false), new SpecT(71, 0, "Arms", ClassT.WARRIOR, ArmorT.PLATE, StatT.STR, true), new SpecT(72, 1, "Fury", ClassT.WARRIOR, ArmorT.PLATE, StatT.STR, true), new SpecT(73, 2, "Protection", ClassT.WARRIOR, ArmorT.PLATE, StatT.STR, true) };
        nameMap = new HashMap<String, SpecT>();
        classMap = new HashMap<ClassT, SpecT[]>();
        final HashMap<ClassT, ArrayList<SpecT>> map = new HashMap<ClassT, ArrayList<SpecT>>();
        for (final ClassT x : ClassT.ALL) {
            map.put(x, new ArrayList<SpecT>(4));
        }
        for (final SpecT x2 : SpecT.ALL) {
            SpecT.nameMap.put(x2.name, x2);
            map.get(x2.classType).add(x2);
        }
        for (final ClassT x : ClassT.ALL) {
            final ArrayList<SpecT> list = map.get(x);
            SpecT.classMap.put(x, list.toArray(new SpecT[list.size()]));
        }
    }
}
