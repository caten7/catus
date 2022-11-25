// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class FeralSpec
{
    static final StatT[] REFORGE_STATS;
    static final Glyph GLYPH_SR;
    static final Glyph GLYPH_CAT_FORM;
    static final Glyph GLYPH_SHRED;
    static final Glyph GLYPH_FB;
    static final Glyph GLYPH_FF;
    static final Glyph GLYPH_MAUL;
    static final Glyph[] MAJOR;
    static final Talent[][] TALENTS;
    boolean glyph_shred;
    boolean glyph_fb;
    boolean glyph_sr;
    boolean glyph_catForm;
    boolean glyph_ff;
    boolean glyph_maul;
    boolean talent_displacer;
    boolean talent_charge;
    boolean talent_vortex;
    boolean talent_sotf;
    boolean talent_kotj;
    boolean talent_fon;
    boolean talent_doc;
    boolean talent_hotw;
    boolean talent_nv;
    boolean talent_fs;
    
    public String talentStr() {
        final StringBuilder sb = new StringBuilder();
        if (this.talent_fon) {
            Fmt.append(sb, "FoN", "/");
        }
        if (this.talent_sotf) {
            Fmt.append(sb, "SotF", "/");
        }
        if (this.talent_kotj) {
            Fmt.append(sb, "KotJ", "/");
        }
        if (this.talent_hotw) {
            Fmt.append(sb, "HotW", "/");
        }
        if (this.talent_doc) {
            Fmt.append(sb, "DoC", "/");
        }
        if (this.talent_nv) {
            Fmt.append(sb, "NV", "/");
        }
        return sb.toString();
    }
    
    public FeralSpec copy() {
        final FeralSpec copy = new FeralSpec();
        copy.glyph_catForm = this.glyph_catForm;
        copy.glyph_fb = this.glyph_fb;
        copy.glyph_shred = this.glyph_shred;
        copy.glyph_sr = this.glyph_sr;
        copy.glyph_ff = this.glyph_ff;
        copy.glyph_maul = this.glyph_maul;
        copy.talent_vortex = this.talent_vortex;
        copy.talent_displacer = this.talent_displacer;
        copy.talent_fs = this.talent_fs;
        copy.talent_charge = this.talent_charge;
        copy.talent_sotf = this.talent_sotf;
        copy.talent_kotj = this.talent_kotj;
        copy.talent_fon = this.talent_fon;
        copy.talent_doc = this.talent_doc;
        copy.talent_hotw = this.talent_hotw;
        copy.talent_nv = this.talent_nv;
        return copy;
    }
    
    public void loadGlyphs(final String x) {
        this.glyph_shred = FeralSpec.GLYPH_SHRED.isActive(x);
        this.glyph_sr = FeralSpec.GLYPH_SR.isActive(x);
        this.glyph_catForm = FeralSpec.GLYPH_CAT_FORM.isActive(x);
        this.glyph_fb = FeralSpec.GLYPH_FB.isActive(x);
        this.glyph_ff = FeralSpec.GLYPH_FF.isActive(x);
        this.glyph_maul = FeralSpec.GLYPH_MAUL.isActive(x);
    }
    
    public void loadTalents(final String x) {
        this.loadTalents(API.parseTalents(x));
    }
    
    public void loadTalents(final byte[] v) {
        this.talent_fs = (v[0] == 0);
        this.talent_displacer = (v[0] == 1);
        this.talent_charge = (v[0] == 2);
        this.talent_sotf = (v[3] == 0);
        this.talent_kotj = (v[3] == 1);
        this.talent_fon = (v[3] == 2);
        this.talent_vortex = (v[4] == 1);
        this.talent_hotw = (v[5] == 0);
        this.talent_doc = (v[5] == 1);
        this.talent_nv = (v[5] == 2);
    }
    
    public void dump() {
        this.dumpTalents();
        this.dumpGlyphs();
    }
    
    public void dumpTalents() {
        System.out.println(String.format("Soul of the Forest = %s", this.talent_sotf));
        System.out.println(String.format("Incarnation        = %s", this.talent_kotj));
        System.out.println(String.format("Force of Nature    = %s", this.talent_fon));
        System.out.println(String.format("Ursol's Vortex     = %s", this.talent_vortex));
        System.out.println(String.format("Heart of the Wild  = %s", this.talent_hotw));
        System.out.println(String.format("Dream of Cenarius  = %s", this.talent_doc));
        System.out.println(String.format("Nature's Vigil     = %s", this.talent_nv));
    }
    
    public void dumpGlyphs() {
        System.out.println(String.format("Savagery = %s", this.glyph_sr));
        System.out.println(String.format("Shred    = %s", this.glyph_shred));
        System.out.println(String.format("Cat Form = %s", this.glyph_catForm));
        System.out.println(String.format("FB       = %s", this.glyph_fb));
        System.out.println(String.format("FF       = %s", this.glyph_ff));
    }
    
    static {
        REFORGE_STATS = new StatT[] { StatT.HIT, StatT.EXP, StatT.AGI, StatT.MASTERY, StatT.HASTE, StatT.CRIT };
        MAJOR = new Glyph[] { new Glyph('J', "Barkskin"), GLYPH_CAT_FORM = new Glyph('t', "Cat Form"), new Glyph('N', "Dash"), GLYPH_FF = new Glyph('s', "Faerie Fire"), GLYPH_FB = new Glyph('r', "Ferocious Bite"), new Glyph('M', "Master Shapeshifter"), GLYPH_MAUL = new Glyph('Z', "Maul"), new Glyph('d', "Pounce"), new Glyph('X', "Prowl"), GLYPH_SR = new Glyph('j', "Savagery"), new Glyph('i', "Skull Bash"), new Glyph('W', "Stampede"), new Glyph('K', "Stampeding Roar"), new Glyph('o', "Survival Instincts"), GLYPH_SHRED = new Glyph('c', "Shred"), new Glyph('V', "Rebirth") };
        TALENTS = new Talent[][] { { new Talent("Feline Swiftness", "spell_druid_tirelesspursuit"), new Talent("Dispacer Beast", "spell_druid_displacement"), new Talent("Wild Charge", "spell_druid_wildcharge") }, { new Talent("Ysera's Gift", "inv_misc_head_dragon_green"), new Talent("Renewal", "spell_nature_natureblessing"), new Talent("Cenarion Ward", "ability_druid_naturalperfection") }, { new Talent("Faerie Swarm", "spell_druid_swarm"), new Talent("Mass Entanglement", "spell_druid_massentanglement"), new Talent("Typhoon", "ability_druid_typhoon") }, { new Talent("Soul of the Forest", "ability_druid_manatree"), new Talent("Incarnation", "spell_druid_incarnation"), new Talent("Force of Nature", "ability_druid_forceofnature") }, { new Talent("Disorienting Roar", "ability_druid_demoralizingroar"), new Talent("Ursol's Vortex", "spell_druid_ursolsvortex"), new Talent("Mighty Bash", "ability_druid_bash") }, { new Talent("Heart of the Wild", "spell_holy_blessingofagility"), new Talent("Dream of Cenarius", "ability_druid_dreamstate"), new Talent("Nature's Vigil", "achievement_zone_feralas") } };
    }
}
