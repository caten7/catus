// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class ClassT extends BlizzT_SetMember
{
    private static final int[] EMPTY;
    public static final ClassT WARRIOR;
    public static final ClassT PALADIN;
    public static final ClassT HUNTER;
    public static final ClassT ROGUE;
    public static final ClassT PRIEST;
    public static final ClassT DK;
    public static final ClassT SHAMAN;
    public static final ClassT MAGE;
    public static final ClassT DRUID;
    public static final ClassT WARLOCK;
    public static final ClassT MONK;
    public static final ClassT[] ALL;
    public static TypeMap<ClassT> MAP;
    public static final StatT[] STATS;
    public final int[] stats;
    public final RaceT[] races;
    public final TypeSet<WeaponT> weaponSet;
    
    private ClassT(final int id, final String name, final int[] stats, final RaceT[] races, final TypeSet<WeaponT> weaponSet) {
        super(id, name);
        this.stats = stats;
        this.races = races;
        this.weaponSet = weaponSet;
    }
    
    public static String fmt(final int mask) {
        final StringBuilder sb = new StringBuilder(64);
        for (final ClassT x : ClassT.ALL) {
            final int bit = x.bit;
            if ((mask & bit) == bit) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(x.name);
            }
        }
        return sb.toString();
    }
    
    static {
        EMPTY = new int[] { 0, 0, 0, 0, 0 };
        ALL = new ClassT[] { WARRIOR = new ClassT(1, "Warrior", ClassT.EMPTY, null, WeaponT.ANY), PALADIN = new ClassT(2, "Paladin", ClassT.EMPTY, null, WeaponT.ANY), HUNTER = new ClassT(3, "Hunter", new int[] { 66, 196, 131, 85, 93 }, RaceT.ALL, WeaponT.ANY), ROGUE = new ClassT(4, "Rogue", new int[] { 112, 205, 103, 28, 57 }, new RaceT[] { RaceT.BLOOD_ELF, RaceT.DWARF, RaceT.GNOME, RaceT.GOBLIN, RaceT.HUMAN, RaceT.NIGHT_ELF, RaceT.ORC, RaceT.PANDA_A, RaceT.PANDA_H, RaceT.PANDA_H, RaceT.TROLL, RaceT.UNDEAD, RaceT.WORGEN }, BlizzT.makeSet(WeaponT.ONE_HAND_AXE, WeaponT.ONE_HAND_MACE, WeaponT.ONE_HAND_SWORD, WeaponT.FIST, WeaponT.DAGGER)), PRIEST = new ClassT(5, "Priest", new int[] { 28, 38, 57, 187, 196 }, RaceT.ALL, WeaponT.ANY), DK = new ClassT(6, "Death Knight", ClassT.EMPTY, null, WeaponT.ANY), SHAMAN = new ClassT(7, "Shaman", ClassT.EMPTY, null, WeaponT.ANY), MAGE = new ClassT(8, "Mage", new int[] { 19, 28, 47, 195, 187 }, null, WeaponT.ANY), WARLOCK = new ClassT(9, "Warlock", ClassT.EMPTY, null, WeaponT.ANY), MONK = new ClassT(10, "Monk", new int[] { 74, 93, 93, 149, 170 }, new RaceT[] { RaceT.BLOOD_ELF, RaceT.DRAENEI, RaceT.DWARF, RaceT.GNOME, RaceT.HUMAN, RaceT.NIGHT_ELF, RaceT.ORC, RaceT.PANDA_A, RaceT.PANDA_H, RaceT.PANDA_H, RaceT.TAUREN, RaceT.TROLL, RaceT.UNDEAD }, BlizzT.makeSet(WeaponT.ONE_HAND_AXE, WeaponT.ONE_HAND_MACE, WeaponT.ONE_HAND_SWORD, WeaponT.FIST, WeaponT.STAFF, WeaponT.POLEARM)), DRUID = new ClassT(11, "Druid", new int[] { 84, 75, 94, 149, 168 }, new RaceT[] { RaceT.NIGHT_ELF, RaceT.WORGEN, RaceT.TROLL, RaceT.TAUREN }, BlizzT.makeSet(WeaponT.DAGGER, WeaponT.ONE_HAND_MACE, WeaponT.TWO_HAND_MACE, WeaponT.POLEARM, WeaponT.STAFF, WeaponT.FISHING_POLE, WeaponT.FIST, WeaponT.MISC)) };
        ClassT.MAP = BlizzT.makeMap(ClassT.ALL);
        STATS = new StatT[] { StatT.STR, StatT.AGI, StatT.STA, StatT.INT, StatT.SPI };
    }
}
