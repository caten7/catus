// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class ProfT extends BlizzT_SetMember
{
    public static final ProfT ENCH;
    public static final ProfT ENG;
    public static final ProfT JC;
    public static final ProfT INS;
    public static final ProfT TAILOR;
    public static final ProfT BS;
    public static final ProfT LW;
    public static final ProfT ALCH;
    public static final ProfT SKIN;
    public static final ProfT HERB;
    public static final ProfT MINE;
    public static final ProfT[] ALL;
    public static final ProfT[] PRIMARY;
    public static final ProfT[] GATHERER;
    public static final int ALLOW_ALL = -1;
    public static final TypeMap<ProfT> MAP;
    public final String abbr;
    
    private ProfT(final int id, final int bit, final String name, final String abbr) {
        super(id, bit, name);
        this.abbr = abbr;
    }
    
    public static String fmt(final int profs, final boolean abbr) {
        final StringBuilder sb = new StringBuilder();
        for (final ProfT x : ProfT.ALL) {
            if (x.isMemberOf(profs)) {
                Fmt.append(sb, abbr ? x.abbr : x.name, "/");
            }
        }
        return sb.toString();
    }
    
    static {
        ALL = new ProfT[] { ENCH = new ProfT(333, 1, "Enchanting", "Ench"), ENG = new ProfT(202, 2, "Engineering", "Eng"), JC = new ProfT(755, 4, "Jewelcrafting", "JC"), TAILOR = new ProfT(197, 8, "Tailoring", "Tailor"), INS = new ProfT(773, 16, "Inscription", "Ins"), BS = new ProfT(164, 32, "Blacksmithing", "BS"), LW = new ProfT(165, 64, "Leatherworking", "LW"), SKIN = new ProfT(393, 128, "Skinning", "Skin"), ALCH = new ProfT(171, 256, "Alchemy", "Alch"), HERB = new ProfT(182, 512, "Herbalism", "Herb"), MINE = new ProfT(186, 1024, "Mining", "Mine") };
        PRIMARY = new ProfT[] { ProfT.ENCH, ProfT.ENG, ProfT.JC, ProfT.INS, ProfT.TAILOR, ProfT.BS, ProfT.LW, ProfT.ALCH, ProfT.SKIN, ProfT.HERB, ProfT.MINE };
        GATHERER = new ProfT[] { ProfT.SKIN, ProfT.HERB, ProfT.MINE };
        MAP = BlizzT.makeMap(ProfT.ALL);
    }
}
