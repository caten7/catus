// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class RaceT extends BlizzT_SetMember
{
    static final RaceT HUMAN;
    static final RaceT DWARF;
    static final RaceT ORC;
    static final RaceT NIGHT_ELF;
    static final RaceT UNDEAD;
    static final RaceT TAUREN;
    static final RaceT GNOME;
    static final RaceT TROLL;
    static final RaceT GOBLIN;
    static final RaceT BLOOD_ELF;
    static final RaceT DRAENEI;
    static final RaceT WORGEN;
    static final RaceT PANDA_N;
    static final RaceT PANDA_A;
    static final RaceT PANDA_H;
    static final RaceT[] ALL;
    static final StatT[] STATS;
    static final TypeMap<RaceT> MAP;
    public final FactionT faction;
    public final int[] stats;
    public final String key;
    static final int ALL_ALLIANCE;
    static final int ALL_HORDE;
    
    public RaceT(final int id, final String name, final String key, final FactionT faction, final int[] stats) {
        super(id, name);
        this.key = key;
        this.faction = faction;
        this.stats = stats;
    }
    
    static {
        ALL = new RaceT[] { HUMAN = new RaceT(1, "Human", "Human", FactionT.ALLIANCE, new int[] { 20, 20, 20, 20, 20 }), ORC = new RaceT(2, "Orc", "Orc", FactionT.HORDE, new int[] { 23, 17, 21, 17, 22 }), DWARF = new RaceT(3, "Dwarf", "Dwarf", FactionT.ALLIANCE, new int[] { 25, 16, 21, 19, 19 }), NIGHT_ELF = new RaceT(4, "Night Elf", "NightElf", FactionT.ALLIANCE, new int[] { 16, 24, 20, 20, 20 }), UNDEAD = new RaceT(5, "Undead", "Scourge", FactionT.HORDE, new int[] { 19, 18, 20, 18, 25 }), TAUREN = new RaceT(6, "Tauren", "Tauren", FactionT.HORDE, new int[] { 25, 16, 21, 16, 22 }), GNOME = new RaceT(7, "Gnome", "Gnome", FactionT.ALLIANCE, new int[] { 15, 22, 20, 23, 20 }), TROLL = new RaceT(8, "Troll", "Troll", FactionT.HORDE, new int[] { 21, 22, 20, 16, 21 }), GOBLIN = new RaceT(9, "Goblin", "Goblin", FactionT.HORDE, new int[] { 17, 22, 20, 23, 18 }), BLOOD_ELF = new RaceT(10, "Blood Elf", "BloodElf", FactionT.HORDE, new int[] { 17, 22, 20, 23, 18 }), DRAENEI = new RaceT(11, "Draenei", "Draenei", FactionT.ALLIANCE, new int[] { 21, 17, 20, 20, 22 }), WORGEN = new RaceT(22, "Worgen", "Worgen", FactionT.ALLIANCE, new int[] { 23, 22, 20, 16, 19 }), PANDA_N = new RaceT(24, "Pandaren", "Pandaren", FactionT.NEUTRAL, new int[] { 20, 18, 21, 19, 22 }), PANDA_A = new RaceT(25, "Pandaren (A)", "Pandaren", FactionT.ALLIANCE, new int[] { 20, 18, 21, 19, 22 }), PANDA_H = new RaceT(26, "Pandaren (H)", "Pandaren", FactionT.HORDE, new int[] { 20, 18, 21, 19, 22 }) };
        STATS = new StatT[] { StatT.STR, StatT.AGI, StatT.STA, StatT.INT, StatT.SPI };
        MAP = BlizzT.makeMap(RaceT.ALL);
        ALL_ALLIANCE = (RaceT.HUMAN.bit | RaceT.DWARF.bit | RaceT.NIGHT_ELF.bit | RaceT.GNOME.bit | RaceT.DRAENEI.bit | RaceT.WORGEN.bit | RaceT.PANDA_A.bit);
        ALL_HORDE = (RaceT.ORC.bit | RaceT.UNDEAD.bit | RaceT.TAUREN.bit | RaceT.TROLL.bit | RaceT.BLOOD_ELF.bit | RaceT.GOBLIN.bit | RaceT.PANDA_H.bit);
    }
}
