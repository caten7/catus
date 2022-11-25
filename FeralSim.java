// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.TreeMap;
import java.util.HashSet;
import java.util.BitSet;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Random;
import java.util.Map;

public class FeralSim
{
    static final boolean INCLUDE_ON_USE_SELF = false;
    static final String DOT_PREFIX = ".";
    static final int GCD_MIN = 1000;
    static final int GCD_CAT = 1000;
    static final int GCD_SHAPESHIFT = 1500;
    static final int GCD_SPELL = 1500;
    static final int GCD_BEAR = 1500;
    static final int FORM_CAT = 1;
    static final int FORM_BEAR = 2;
    static final int SWING_CAT = 1000;
    static final int SWING_BEAR = 2500;
    static final double BASE_CRIT_MOD = 2.0;
    static final double SKULL_BANNER_COEFF = 1.2;
    static final double MOD_DMG_CAT_AUTO = 2.0;
    static final double MOD_DMG_TF = 1.15;
    static final double MOD_DMG_DOC = 1.3;
    static final double MOD_DMG_SR = 1.4;
    static final double MOD_DMG_BLEED = 1.2;
    static final double MOD_DMG_NV = 1.12;
    static final double MOD_DMG_TRICKS = 1.15;
    static final int POTION_CD = 60000;
    static final String MOD_ABBR_DOC = "+DoC";
    static final String MOD_ABBR_SR = "+SR";
    static final String MOD_ABBR_TF = "+TF";
    static final String MOD_ABBR_NV = "+NV";
    static final String MOD_ABBR_HOTW = "+HotW";
    boolean hotfixed;
    double modDmg_tf;
    double modDmg_doc;
    double modDmg_sr;
    double modDmg_nv;
    double modDmg_catAuto;
    double modDmg_rip;
    double modDmg_rake;
    double modDmg_thrash;
    double modDmg_shrangle;
    double modDmg_ravage;
    double modDmg_swipe;
    double modDmg_fb;
    double modDmg_wrath;
    double modDmg_hurricane;
    static final StatT[] STATS;
    static final int STAT_NUM;
    static final Map<StatT, Integer> STAT_MAP;
    static final int STAT_AGI;
    static final int STAT_STR;
    static final int STAT_INT;
    static final int STAT_STA;
    static final int STAT_SPI;
    static final int STAT_AP;
    static final int STAT_SP;
    static final int STAT_HIT;
    static final int STAT_EXP;
    static final int STAT_CRIT;
    static final int STAT_HASTE;
    static final int STAT_MASTERY;
    static final int STAT_HP;
    static final int STAT_WDMG;
    static final int STAT_PVP_POW;
    static final int STAT_PVP_RES;
    static final int STAT_DODGE;
    static final double WEAK_ARMOR_STACK_COEFF = 0.04;
    static final double WEAK_ARMOR_COEFF = 0.88;
    static final double SHATTER_COEFF = 0.8;
    static final int MOD_WEAK_ARMOR = -2;
    static final int MOD_HOTW = -3;
    static final int MOD_SHATTERING = -4;
    static final int MOD_BEAR_FORM = -5;
    static final int MOD_BERSERK = -6;
    static final int MOD_UNHOLY_FRENZY = -7;
    static final int MOD_SKULL_BANNER = -8;
    static final int MOD_HEROISM = -9;
    static final int MOD_BERSERKING = -10;
    static final int MOD_TRICKS = -11;
    static final int MOD_NATURES_VIGIL = -12;
    static final int MOD_PERIODIC = -13;
    static final int MOD_AMP = -14;
    final EventKey castingKey;
    final EventKey lockoutKey;
    final EventKey swingingKey;
    final EventKey oocKey;
    final EventKey channelKey;
    final EventKey psKey;
    final EventKey bonus_t16_2p_key;
    final EventKey bonus_t16_4p_key;
    final EventKey sufferKey;
    final EventKey docKey;
    boolean enable_wodRacials;
    boolean enable_berserkPerk;
    boolean enable_tfPerk;
    boolean disable_racials;
    boolean disable_berserk;
    boolean disable_mastery;
    boolean disable_ripExtend;
    boolean disable_crit;
    boolean disable_armorSpec;
    boolean disable_primalFury;
    boolean disable_glancing;
    boolean disable_bitw;
    boolean guardian;
    boolean autoattack;
    boolean combat;
    boolean stealth;
    boolean saveDamage;
    boolean mixology;
    String simName;
    String simDesc;
    Object simData;
    Encounter encounter;
    int clock;
    static final int MAX_CLOCK = 3600000;
    int clockEnd;
    int form;
    int combo;
    int energy;
    int rage;
    int health;
    int healthMax;
    int mana;
    final int manaMax = 60000;
    static final int energyMax0 = 100;
    int energyMax;
    final int rageMax = 1000;
    static final int TF_DUR = 6000;
    static final int TF_CD = 30000;
    static final int TF_ENERGY = 60;
    static final int BERSERK_DUR = 15000;
    static final int BERSERK_CD = 180000;
    int shared_use_ready;
    int t15_4p_charges;
    int t16_4p_used;
    boolean pvpRavage_ready;
    int pot_count;
    int pot_expire;
    int pot_ready;
    int ps_occur;
    int ps_used;
    int ps_replaced;
    int tranq_ready;
    boolean ns;
    int ns_used;
    int sr_clockAlign;
    int sr_expire;
    int sr_uptime;
    int sr_clip_time;
    int sr_zero;
    int sr_cancel;
    int melee_count;
    int melee_miss;
    int rake_clip_count;
    int rake_clip_ticks;
    double rake_clip_coeff;
    int rip_clip_count;
    int rip_clip_ticks;
    double rip_clip_coeff;
    int pounce_tick_count;
    int pounce_clip_count;
    int pounce_clip_ticks;
    int fb_bitw_count;
    int fb_extra;
    int swing_reset;
    int ooc_occur;
    int ooc_replaced;
    int ooc_replaced_lockout;
    int ooc_thrash;
    int ooc_mangleShredRavage;
    int ooc_spent;
    int ooc_energy;
    int mana_lotp;
    int mana_waste;
    int energy_regen;
    int energy_regen_max;
    int energy_refund;
    int energy_refund_max;
    int energy_sotf;
    int energy_sotf_max;
    int energy_tf;
    int energy_tf_max;
    int doc_spent;
    int doc_total;
    int doc_wasted;
    int doc_charges;
    int generator_count;
    int generator_miss_count;
    final int[] finisher_tally;
    int finisher_miss_count;
    int sotf_count;
    int combo_finisher;
    int combo_generated;
    int combo_waste_swap;
    int combo_waste_overflow;
    long damage_total;
    int damage_count;
    final Random rng;
    static final double targetBlockMod = 0.7;
    GearConfig curGear;
    GearConfig mainGear;
    GearConfig swapGear;
    boolean bonus_pvpMaim;
    boolean bonus_pvpRavage;
    boolean bonus_t13_2p;
    boolean bonus_t13_4p;
    boolean bonus_t14_2p;
    boolean bonus_t14_4p;
    boolean bonus_t15_2p;
    boolean bonus_t15_4p;
    boolean bonus_t16_2p;
    boolean bonus_t16_4p;
    boolean bonus_t17_2p;
    boolean bonus_t17_2p_old;
    boolean bonus_t17_4p;
    boolean bonus_wodpvp_2p;
    boolean bonus_wodpvp_4p;
    final Multiplicative natureDmgMods;
    final Multiplicative allHealOutputMods;
    final Multiplicative allDmgMods;
    final Multiplicative allSufferMods;
    final Multiplicative allHasteMods;
    final Multiplicative meleeHasteMods;
    final Multiplicative meleeSpeedMods;
    final Multiplicative spellHasteMods;
    final Multiplicative critDmgMods;
    final Multiplicative energyCostMods;
    double armorMod0;
    double debuff_meleeMod;
    double debuff_spellMod;
    final Multiplicative[] statMods;
    final int[] baseStats;
    final int[] consumeStats;
    final int[] inertStats;
    final int[] fixedStats;
    final double[] percStats;
    int[] procStats;
    int[] procStats_backup;
    int[] gearStats;
    final ArrayList<Spell_BuffCooldown> raidSpells;
    final ArrayList<Spell_Cooldown> onUseSpells;
    static final Comparator<Spell_Cooldown> ORDER_CD_DECR;
    public static final double RATING_COEFF_HIT = 34000.0;
    public static final double RATING_COEFF_EXP = 34000.0;
    public static final double RATING_COEFF_CRIT = 60000.0;
    public static final double RATING_COEFF_HASTE = 42500.0;
    public static final double RATING_COEFF_FERAL_MASTERY = 19169.3291;
    public static final double RATING_COEFF_GUARD_MASTERY = 30000.0;
    public static final double RATING_COEFF_PVP_POWER = 26500.0;
    public static final double RATING_COEFF_DODGE = 88500.0;
    public static final double CRIT_PER_AGI = 125951.806640625;
    public static final double CRIT_PER_INT = 253366.357421875;
    public static final double AGI_PER_DODGE = 95115.8596;
    public static final double MELEE_CRIT_OFFSET = 0.075;
    public static final double SPELL_CRIT_OFFSET = 0.0185;
    static final int CRIT_AGI_OFFSET = 25;
    boolean _poolingResources;
    IntDist react;
    IntDist delay;
    Enemy comboTarget;
    final ArrayList<Spell> spells;
    final ArrayList<Spell> activeSpells;
    final ArrayList<ProcHandler> activeProcs;
    static final String playerPrefix;
    static final int PREFIX_LENGTH = 8;
    int global_rip_extends;
    static final String GUSHING_NAME = "Gushing Wound";
    static final String GUSHING_TICK;
    final int POUNCE_TICK_NUM = 6;
    final CatSpell_Generator POUNCE;
    final CatSpell_Generator RAVAGE;
    static final double T16_2P_DMG_MOD = 1.5;
    static final double T14_2P_DMG_MOD = 1.05;
    static final double WOD_4P_DMG_MOD = 1.1;
    static final double T15_4P_CRIT_PERC = 0.4;
    final CatSpell_Generator MANGLE;
    final CatSpell_Generator SHRED;
    final CatSpell_Generator SWIPE;
    static final int RIP_TICK_CNT = 8;
    static final int THRASH_TICK_DUR = 3000;
    static final int THRASH_TICK_CNT = 5;
    final CatSpell THRASH;
    static final int THRASH_BEAR_TICK_DUR = 2000;
    static final int THRASH_BEAR_TICK_CNT = 8;
    final BearSpell THRASH_BEAR;
    int mangle_ready;
    final BearSpell MANGLE_BEAR;
    final BearSpell MAUL;
    int vortex_ready;
    int vortex_expire;
    final Spell URSOLS_VORTEX;
    private final StringBuilder _sb;
    int sb_ready;
    final Spell_Cooldown SKULL_BASH;
    final CatSpell SHATTERING_BLOW;
    final Spell_Cooldown FERAL_SPIRIT;
    final Spell_Cooldown REDIRECT;
    final Spell_Cooldown SOUL_SWAP;
    static final int FON_CD = 20000;
    static final int FON_DUR = 15000;
    static final int FON_CHARGE_MAX = 3;
    int fon_charges;
    final Spell FON;
    final Spell_BuffCooldown NV;
    final Spell_BuffCooldown KOTJ;
    final Spell_BuffCooldown HOTW;
    final Spell_BuffCooldown BERSERK;
    final Spell_BuffCooldown TF;
    double bitw_perc;
    final CatSpell_Finisher FB;
    int rip_tick_init;
    final int RIP_TICK_DUR = 2000;
    final int RIP_EXTEND_NUM = 3;
    final CatSpell_Finisher RIP;
    final Spell_Cooldown NS;
    final CastSpell CAT_FORM;
    final CastSpell BEAR_FORM;
    final ChanSpell TRANQ;
    final ChanSpell HURRICANE;
    final CastSpell WRATH;
    final CastSpell HT;
    final int SR_TICK_DUR = 3000;
    final CatSpell_Finisher SR;
    int ff_ready;
    final Spell FF;
    static final int RAKE_TICK_CNT = 5;
    static final int RAKE_TICK_DUR = 3000;
    Enemy soulSwapTarget;
    final CatSpell_Generator RAKE;
    int runCount;
    final HashMap<String, DamageTally> dmgTallyMap;
    final HashMap<String, DamageTally> dmgTallyMap2;
    final HashMap<String, ArrayList<Damage>> dmgMap;
    final DamageWindow dw;
    boolean _dirtyTargets;
    int _loopingTargets;
    final Event[] eventStack;
    int eventCount;
    int eventStart;
    int eventMax;
    private static final int RPPM_IDLE_TIME = 120000;
    static final int PROC_RESET_STANDARD = -1;
    static final int PROC_RESET_RANDOM = -2;
    static final int RPPM_MAX_TIME = 10000;
    static final int RPPM_MAX_WAIT = 1000000;
    int futureIndex;
    Logic[] futureStack;
    final Condition ENERGY_70;
    Logic top;
    final ArrayList<ProcHandler> procs;
    boolean printLog;
    boolean printEnergy;
    boolean printDebug;
    boolean printMore;
    final ProcHandler LOTP;
    final int OOC_DUR = 15000;
    final ProcHandler CLEARCASTING;
    FeralSpec spec;
    FeralConfig cfg;
    final IntSet spellIds;
    Enemy target;
    final ArrayList<Enemy> targets;
    final ArrayList<Enemy> activeTargets;
    boolean random_ranges;
    final ArrayList<StatEffect> proc_stats;
    Proc_Single_RoR trinket_rune;
    final Profile mainProfile;
    final Profile swapProfile;
    double speed0;
    double speedMod_inForm;
    double _crit0;
    double _ripTickDmg0;
    double _rakeTickDmg0;
    double _treantRakeTickDmg0;
    final ArrayList<Origin> origins;
    final HashMap<Integer, Object> selfMap;
    final ArrayList<Runnable> prepList;
    final ArrayList<Runnable> postList;
    final EventKey fonMonitor;
    int _rampUpFreq;
    boolean _statChangedScheduled;
    static final int FON_LOOKAHEAD = 500;
    Expr castingExpr;
    private LineWriter logWriter;
    boolean setupPhase;
    int idle_depth;
    int idle_expires;
    final EncounterTime FIXED;
    double _treantRakeThresDmg;
    double _treantRakeThresBase;
    final Spell_BuffCooldown_Stat SYNAPSE_SPRINGS;
    final ProcHandler SWORDGUARD;
    final Spell_Cooldown FRAG_BELT;
    Spell_BuffCooldown heroSpell;
    final Spell_BuffCooldown_MultiMod HEROISM;
    final Spell_BuffCooldown_MultiMod DRUMS_OF_RAGE;
    final Spell_BuffCooldown_MultiMod BERSERKING;
    final Spell_BuffCooldown_Stat LIFEBLOOD;
    static final String AUTO_ATTACK_NAME = "Melee";
    final int manaPerTick = 240;
    Logic main_logic;
    final Logic idle_logic;
    boolean queuedMainSwap;
    Action parsingAction;
    String loadedScript;
    static final Expr EXPR_TRUE;
    static final Expr EXPR_FALSE;
    static final Expr EXPR_NAN;
    static final Expr EXPR_ZERO;
    static final int ORDER_UNARY = 10;
    static final int ORDER_TIMES = 9;
    static final int ORDER_PLUS = 8;
    static final int ORDER_CMP = 3;
    static final int ORDER_AND = 2;
    static final int ORDER_OR = 0;
    final NamedExpr EXPR_GENERATOR_MODE;
    final NamedExpr EXPR_FINISHER0_MODE;
    final NamedExpr EXPR_FINISHER_COUNT5;
    final NamedExpr EXPR_THRASH_MODE;
    final NamedExpr EXPR_FF_MODE;
    final NamedExpr EXPR_HOTW_SWAP;
    final NamedExpr EXPR_HOTW_BITW;
    final NamedExpr EXPR_HOTW_BEFORE_BERSERK;
    final NamedExpr EXPR_HOTW_WRATH;
    final NamedExpr EXPR_HOTW_HURRICANE;
    final NamedExpr EXPR_ENERGY;
    final NamedExpr EXPR_ENERGY_DEFICIT;
    final NamedExpr EXPR_ENERGY_TF_GAP;
    final NamedExpr EXPR_ENERGY_REGEN;
    final NamedExpr EXPR_ENERGY_TIME_TO_MAX;
    final NamedExpr EXPR_COMBO;
    final NamedExpr EXPR_TARGET_BITW;
    final NamedExpr EXPR_TARGET_PERC_HP;
    final NamedExpr EXPR_CLOCK;
    final NamedExpr EXPR_TARGET_TTL;
    final NamedExpr EXPR_TARGET_TIME_TO_BITW;
    final NamedExpr EXPR_TALENT_DOC;
    final NamedExpr EXPR_TALENT_SOTF;
    final NamedExpr EXPR_TALENT_NV;
    final NamedExpr EXPR_TALENT_KOTJ;
    final NamedExpr EXPR_FON_DMG;
    final NamedExpr EXPR_TALENT_FON;
    final NamedExpr EXPR_SR_TIME;
    final NamedExpr EXPR_OOC_REACT;
    final NamedExpr EXPR_T16_2P_REACT;
    final NamedExpr EXPR_TF_TIME;
    final NamedExpr EXPR_NV_TIME;
    final NamedExpr EXPR_PS_TIME;
    final NamedExpr EXPR_HOTW_TIME;
    final NamedExpr EXPR_NS_UP;
    final NamedExpr EXPR_RIP_MIN_TIME;
    final NamedExpr EXPR_RIP_MAX_TIME;
    final NamedExpr EXPR_POTION_TIME;
    final NamedExpr EXPR_THRASH_TIME;
    final NamedExpr EXPR_THRASH_BEAR_TIME;
    final NamedExpr EXPR_RAKE_TIME;
    final NamedExpr EXPR_RAKE_TICKS;
    final NamedExpr EXPR_FON_CHARGES;
    final NamedExpr EXPR_DOC_CHARGES;
    final NamedExpr EXPR_PROC_EFF_AP;
    final NamedExpr EXPR_CAT_FORM;
    final NamedExpr EXPR_BEAR_FORM;
    final NamedExpr EXPR_ARMORED;
    final NamedExpr EXPR_ARMOR_TIME;
    final NamedExpr EXPR_THRASH_DMG;
    final NamedExpr EXPR_RIP_DMG;
    final NamedExpr EXPR_SHRED_DMG_NEW;
    final NamedExpr EXPR_MANGLE_DMG_NEW;
    final NamedExpr EXPR_RIP_DMG_NEW;
    final NamedExpr EXPR_THRASH_DMG_NEW;
    final NamedExpr EXPR_RAKE_DMG;
    final NamedExpr EXPR_RAKE_DMG_NEW;
    final NamedExpr EXPR_ENABLED;
    final NamedExpr EXPR_ACTIVE_TARGET_COUNT;
    int minCleaveSize;
    final NamedExpr EXPR_MIN_CLEAVE_SIZE;
    final NamedExpr EXPR_RIP_EXTENDS_AVAIL;
    final NamedExpr EXPR_RIP_EXTENDS_ADDED;
    final NamedExpr EXPR_RUNE_EXISTS;
    final NamedExpr EXPR_RUNE_MASTERY_TIME;
    final Expr EXPR_T16_4P_TIME;
    
    static final String tickify(final String name) {
        return "." + name;
    }
    
    void dumpHotfixes(final LineWriter lw) {
        if (this.hotfixed) {
            lw.add();
            lw.add("[Hotfixes]");
            this.dumpHotfixTable(lw);
        }
    }
    
    void dumpHotfixTable(final LineWriter lw) {
        lw.add("%-10s  %6s  %6s  %6s  %6s  %6s", "Name", "Base", "Base%", "Multi", "Fixed", "Fixed%");
        this.dumpHotfix(lw, "Cat Auto", 2.0, this.modDmg_catAuto);
        this.dumpHotfix(lw, "Rake", 1.0, this.modDmg_rake);
        this.dumpHotfix(lw, "Rip", 1.0, this.modDmg_rip);
        this.dumpHotfix(lw, "Thrash", 1.0, this.modDmg_thrash);
        this.dumpHotfix(lw, "Shrangle", 1.0, this.modDmg_shrangle);
        this.dumpHotfix(lw, "Ravage", 1.0, this.modDmg_ravage);
        this.dumpHotfix(lw, "Swipe", 1.0, this.modDmg_swipe);
        this.dumpHotfix(lw, "FB", 1.0, this.modDmg_fb);
        this.dumpHotfix(lw, "SR", 1.4, this.modDmg_sr);
        this.dumpHotfix(lw, "TF", 1.15, this.modDmg_tf);
        this.dumpHotfix(lw, "DoC", 1.3, this.modDmg_doc);
        this.dumpHotfix(lw, "NV", 1.12, this.modDmg_nv);
        this.dumpHotfix(lw, "Wrath", 1.0, this.modDmg_wrath);
        this.dumpHotfix(lw, "Hurricane", 1.0, this.modDmg_hurricane);
    }
    
    private void dumpHotfix(final LineWriter lw, final String name, final double p0, final double p1) {
        if (p0 != p1) {
            lw.add("%-10s  %6.2f  %+5.0f%%  %5.3fx  %6.2f  %+5.0f%%", name, p0, 100.0 * (p0 - 1.0), p1 / p0, p1, 100.0 * (p1 - 1.0));
        }
    }
    
    int pot_time() {
        return Math.max(0, this.pot_expire - this.clock);
    }
    
    boolean pot_clickable() {
        return this.clock >= this.pot_ready && (this.combat ? (this.cfg.pot != null && this.pot_ready != -1) : (this.cfg.pre_pot != null));
    }
    
    void pot_click() {
        if (this.pot_clickable()) {
            final StatValue pot = this.combat ? this.cfg.pot : this.cfg.pre_pot;
            final int statIndex = FeralSim.STAT_MAP.get(pot.type);
            final int statValue = pot.value;
            final String name = this.addNum("Potion", ++this.pot_count);
            if (this.printLog) {
                this.print("+%s: %+d %s", name, statValue, FeralSim.STATS[statIndex].name);
            }
            final int[] procStats = this.procStats;
            final int n = statIndex;
            procStats[n] += statValue;
            this.fireStatChange();
            this.pot_ready = (this.combat ? -1 : (this.clock + 60000));
            this.pot_expire = this.clock + 25000;
            this.addEvent(new Event(this.pot_expire) {
                @Override
                void run() {
                    if (FeralSim.this.printLog) {
                        FeralSim.this.print("-%s", name);
                    }
                    this.remove();
                    FeralSim.this.fireStatChange();
                }
                
                @Override
                boolean futureProc() {
                    this.remove();
                    return true;
                }
                
                private void remove() {
                    final int[] procStats = FeralSim.this.procStats;
                    final int val$statIndex = statIndex;
                    procStats[val$statIndex] -= statValue;
                }
            });
        }
    }
    
    int sr_time() {
        return Math.max(0, this.sr_expire - this.clock);
    }
    
    public FeralSim() {
        this.modDmg_tf = 1.15;
        this.modDmg_doc = 1.3;
        this.modDmg_sr = 1.4;
        this.modDmg_nv = 1.12;
        this.modDmg_catAuto = 2.0;
        this.modDmg_rip = 1.0;
        this.modDmg_rake = 1.0;
        this.modDmg_thrash = 1.0;
        this.modDmg_shrangle = 1.0;
        this.modDmg_ravage = 1.0;
        this.modDmg_swipe = 1.0;
        this.modDmg_fb = 1.0;
        this.modDmg_wrath = 1.0;
        this.modDmg_hurricane = 1.0;
        this.castingKey = new EventKey();
        this.lockoutKey = new EventKey();
        this.swingingKey = new EventKey();
        this.oocKey = new EventKey();
        this.channelKey = new EventKey();
        this.psKey = new EventKey();
        this.bonus_t16_2p_key = new EventKey();
        this.bonus_t16_4p_key = new EventKey();
        this.sufferKey = new EventKey();
        this.docKey = new EventKey();
        this.clockEnd = 3600000;
        this.energyMax = 100;
        this.melee_count = 0;
        this.melee_miss = 0;
        this.swing_reset = 0;
        this.finisher_tally = new int[6];
        this.rng = new Random();
        this.natureDmgMods = new Multiplicative(5);
        this.allHealOutputMods = new Multiplicative(5);
        this.allDmgMods = new Multiplicative(5);
        this.allSufferMods = new Multiplicative(5);
        this.allHasteMods = new Multiplicative(5);
        this.meleeHasteMods = new Multiplicative(5);
        this.meleeSpeedMods = new Multiplicative(5);
        this.spellHasteMods = new Multiplicative(5);
        this.critDmgMods = new Multiplicative(5);
        this.energyCostMods = new Multiplicative(5);
        this.baseStats = new int[FeralSim.STAT_NUM];
        this.consumeStats = new int[FeralSim.STAT_NUM];
        this.inertStats = new int[FeralSim.STAT_NUM];
        this.fixedStats = new int[FeralSim.STAT_NUM];
        this.percStats = new double[FeralSim.STAT_NUM];
        this.procStats = new int[FeralSim.STAT_NUM];
        this.procStats_backup = new int[FeralSim.STAT_NUM];
        this.raidSpells = new ArrayList<Spell_BuffCooldown>();
        this.onUseSpells = new ArrayList<Spell_Cooldown>();
        this.spells = new ArrayList<Spell>();
        this.activeSpells = new ArrayList<Spell>();
        this.activeProcs = new ArrayList<ProcHandler>();
        this.POUNCE = this.register(new CatSpell_Generator("Pounce") {
            final String tickName = FeralSim.tickify(this.actionName);
            
            void startTick(final Enemy enemy) {
                FeralSim.this.addEvent(new Event(FeralSim.this.clock + 3000) {
                    @Override
                    void run() {
                        if (enemy.isDead()) {
                            return;
                        }
                        final FeralSim this$0 = FeralSim.this;
                        ++this$0.pounce_tick_count;
                        final boolean crit = FeralSim.this.chance(enemy.pounce_crit);
                        double dmg = enemy.bleedDmgMod() * enemy.pounce_tickDmg;
                        if (crit) {
                            dmg *= FeralSim.this.critDmgMods.product;
                        }
                        FeralSim.this.recordCatBleed(enemy, CatSpell_Generator.this.tickName, ++enemy.pounce_tickIndex, enemy.pounce_mods, crit, dmg);
                        final Enemy val$enemy = enemy;
                        if (--val$enemy.pounce_tickLeft > 0) {
                            CatSpell_Generator.this.startTick(enemy);
                        }
                        else if (FeralSim.this.printLog) {
                            enemy.print_fade(CatSpell_Generator.this.actionName);
                        }
                    }
                });
            }
            
            @Override
            boolean usable() {
                return super.usable() && (FeralSim.this.KOTJ.up() || FeralSim.this.stealth);
            }
            
            @Override
            int baseCost() {
                return 50;
            }
            
            @Override
            AttackT generate() {
                final boolean doc = FeralSim.this.consume_doc();
                final AttackT atk = FeralSim.this.yellow(FeralSim.this.target, 0.0, false, false, false);
                final String mods = FeralSim.this.catMods_bleed(doc);
                if (atk.hit) {
                    final double crit = FeralSim.this.target.suppress(FeralSim.this.getMeleeCrit());
                    final double critMod = FeralSim.this.critDmgMods.product;
                    final double tickDmg = FeralSim.this.catDmgMod(doc) * FeralSim.this.getRazorClawsMod() * FeralSim.this._pounceTickDmg();
                    final double avgDmg = FeralSim.this.avgDmg(tickDmg, crit, critMod);
                    if (FeralSim.this.target.pounce_tickLeft > 0) {
                        if (FeralSim.this.printLog) {
                            FeralSim.this.print_clipped(FeralSim.this.target.logPrefix, this.actionName, FeralSim.this.target.pounce_tickLeft, FeralSim.this.target.pounce_avgDmg, FeralSim.this.target.pounce_mods, avgDmg, mods);
                        }
                        final FeralSim this$0 = FeralSim.this;
                        ++this$0.pounce_clip_count;
                        final FeralSim this$2 = FeralSim.this;
                        this$2.pounce_clip_ticks += FeralSim.this.target.pounce_tickLeft - 1;
                        FeralSim.this.target.pounce_tickLeft = 7;
                    }
                    else {
                        this.startTick(FeralSim.this.target);
                        FeralSim.this.logGain(FeralSim.this.target, this.actionName, mods);
                        FeralSim.this.target.pounce_tickLeft = 6;
                        FeralSim.this.target.pounce_clock = FeralSim.this.clock;
                        FeralSim.this.target.pounce_tickIndex = 0;
                    }
                    FeralSim.this.triggerProcs(FeralSim.this.target, this.actionName, Source.MELEE, 0.0, false, 1000);
                    FeralSim.this.addCombo(1);
                    FeralSim.this.target.pounce_mods = mods;
                    FeralSim.this.target.pounce_tickDmg = tickDmg;
                    FeralSim.this.target.pounce_crit = crit;
                    FeralSim.this.target.pounce_avgDmg = avgDmg;
                }
                else {
                    FeralSim.this.logMiss(FeralSim.this.target, this.actionName, mods, atk);
                }
                return atk;
            }
        });
        this.RAVAGE = this.register(new CatSpell_Generator("Ravage") {
            @Override
            boolean usable() {
                return super.usable() && (FeralSim.this.pvpRavage_ready || FeralSim.this.KOTJ.up() || (FeralSim.this.stealth && FeralSim.this.target.isBehind()));
            }
            
            @Override
            int baseCost() {
                return FeralSim.this.pvpRavage_ready ? 0 : 45;
            }
            
            @Override
            AttackT generate() {
                String name = this.actionName;
                if (FeralSim.this.pvpRavage_ready) {
                    FeralSim.this.pvpRavage_ready = false;
                    FeralSim.this.addEvent(new Event(FeralSim.this.clock + 30000) {
                        @Override
                        void run() {
                            FeralSim.this.pvpRavage_ready = true;
                            if (FeralSim.this.printLog) {
                                FeralSim.this.print("Ravage/PvP available!");
                            }
                        }
                    });
                    if (FeralSim.this.printLog) {
                        name += "#PvP";
                    }
                }
                else if (FeralSim.this.KOTJ.up() && FeralSim.this.printLog) {
                    name += "#KotJ";
                }
                final AttackT atk = FeralSim.this.yellow(FeralSim.this.target, FeralSim.this.getMeleeCrit(FeralSim.this.consume_t15_4p_getCrit() + ((FeralSim.this.target.percHP() >= 0.8) ? 0.5 : 0.0)));
                final boolean doc = FeralSim.this.consume_doc();
                final String mods = FeralSim.this.catMods(doc, false, FeralSim.this._t16_2p_mangleShredRavageSwipeMod());
                if (atk.hit) {
                    double dmg = FeralSim.this.target.meleeDmgMod() * FeralSim.this.catDmgMod(doc) * FeralSim.this._ravageDmg(FeralSim.this.random_ranges);
                    if (atk.crit) {
                        dmg *= FeralSim.this.critDmgMods.product;
                    }
                    FeralSim.this.recordCatMelee(FeralSim.this.target, this.actionName, name, mods, atk, dmg);
                    FeralSim.this.target.extendRip();
                }
                else {
                    FeralSim.this.logMiss(FeralSim.this.target, this.actionName, mods, atk);
                }
                return atk;
            }
        });
        this.MANGLE = this.register(new CatSpell_Generator("Mangle") {
            @Override
            int baseCost() {
                return 35;
            }
            
            @Override
            AttackT generate() {
                final boolean doc = FeralSim.this.consume_doc();
                final AttackT atk = FeralSim.this.yellow(FeralSim.this.target, FeralSim.this.getMeleeCrit(FeralSim.this.consume_t15_4p_getCrit()));
                final String mods = FeralSim.this.catMods(doc, false, FeralSim.this._t16_2p_mangleShredRavageSwipeMod());
                if (atk.hit) {
                    double dmg = FeralSim.this.target.meleeDmgMod() * FeralSim.this.catDmgMod(doc) * FeralSim.this._shrangleDmg(FeralSim.this.random_ranges);
                    if (atk.crit) {
                        dmg *= FeralSim.this.critDmgMods.product;
                    }
                    if (atk.block) {
                        dmg *= 0.7;
                    }
                    FeralSim.this.recordCatMelee(FeralSim.this.target, this.actionName, this.actionName, mods, atk, dmg);
                    FeralSim.this.target.extendRip();
                }
                else {
                    FeralSim.this.logMiss(FeralSim.this.target, this.actionName, mods, atk);
                }
                return atk;
            }
        });
        this.SHRED = this.register(new CatSpell_Generator("Shred") {
            @Override
            boolean usable() {
                return super.usable() && FeralSim.this.target.isShredable();
            }
            
            @Override
            int baseCost() {
                return 40;
            }
            
            @Override
            AttackT generate() {
                final boolean doc = FeralSim.this.consume_doc();
                final double bleedMod = FeralSim.this._shredBleedMod();
                final Enemy enemy = FeralSim.this.target;
                final AttackT atk = FeralSim.this.yellow(enemy, FeralSim.this.getMeleeCrit(FeralSim.this.consume_t15_4p_getCrit()));
                final String mods = FeralSim.this.catMods(doc, false, FeralSim.this._t16_2p_mangleShredRavageSwipeMod() * bleedMod);
                if (atk.hit) {
                    double dmg = enemy.meleeDmgMod() * FeralSim.this.catDmgMod(doc) * bleedMod * FeralSim.this._shrangleDmg(FeralSim.this.random_ranges);
                    if (atk.crit) {
                        dmg *= FeralSim.this.critDmgMods.product;
                    }
                    FeralSim.this.recordCatMelee(enemy, this.actionName, this.actionName, mods, atk, dmg);
                    FeralSim.this.target.extendRip();
                    if (FeralSim.this.bonus_wodpvp_4p && atk.crit) {
                        FeralSim.this.addEvent(new Fader(FeralSim.this.clock + 6000, enemy.bloodletting, "Bloodletting", "Bloodletting", enemy.logPrefix));
                    }
                    if (FeralSim.this.bonus_t17_2p_old && atk.crit) {
                        final Spell_BuffCooldown berserk = FeralSim.this.BERSERK;
                        berserk.ready -= 5000;
                    }
                }
                else {
                    FeralSim.this.logMiss(FeralSim.this.target, this.actionName, mods, atk);
                }
                return atk;
            }
        });
        this.SWIPE = this.register(new CatSpell_Generator("Swipe", false, 1) {
            @Override
            int baseCost() {
                return 45;
            }
            
            @Override
            AttackT generate() {
                final boolean doc = FeralSim.this.consume_doc();
                final double catDmgMod = FeralSim.this.catDmgMod(doc);
                final double crit = FeralSim.this.getMeleeCrit(FeralSim.this.consume_t15_4p_getCrit());
                final double critDmgMod = FeralSim.this.critDmgMods.product;
                final double extraMod0 = FeralSim.this._t16_2p_mangleShredRavageSwipeMod();
                final FeralSim this$0 = FeralSim.this;
                ++this$0._loopingTargets;
                AttackT atk0 = AttackT.MISS;
                Enemy t = FeralSim.this.comboTarget;
                if (t == null) {
                    t = FeralSim.this.target;
                }
                for (final Enemy enemy : FeralSim.this.activeTargets) {
                    final double extraMod2 = extraMod0 * (enemy.isBleeding() ? 1.2 : 1.0);
                    final AttackT atk2 = FeralSim.this.yellow(enemy, crit);
                    final String mods = FeralSim.this.catMods(doc, false, extraMod2);
                    if (atk2.hit) {
                        double dmg = enemy.meleeDmgMod() * catDmgMod * FeralSim.this._swipeDmg(FeralSim.this.random_ranges) * extraMod2;
                        if (atk2.crit) {
                            dmg *= critDmgMod;
                        }
                        if (atk2.block) {
                            dmg *= 0.7;
                        }
                        FeralSim.this.recordCatMelee(enemy, this.actionName, this.actionName, mods, atk2, dmg);
                    }
                    else {
                        FeralSim.this.logMiss(enemy, this.actionName, mods, atk2);
                    }
                    if (enemy == t) {
                        atk0 = atk2;
                    }
                }
                FeralSim.this._cleanTargets();
                return atk0;
            }
        });
        this.THRASH = this.register(new CatSpell("Thrash/Cat", true, false) {
            final String tickName = FeralSim.tickify(this.actionName);
            
            void startTick(final Enemy enemy) {
                FeralSim.this.addEvent(new Event(FeralSim.this.clock + 3000) {
                    public void run() {
                        if (enemy.isDead()) {
                            return;
                        }
                        final Enemy val$enemy = enemy;
                        ++val$enemy.thrash_tickCount;
                        final boolean crit = FeralSim.this.chance(enemy.thrash_crit);
                        final double dmg = enemy.bleedDmgMod() * enemy.thrash_tickDmg * (crit ? FeralSim.this.critDmgMods.product : 1.0);
                        FeralSim.this.recordCatBleed(enemy, CatSpell.this.tickName, ++enemy.thrash_tickIndex, enemy.thrash_mods, crit, dmg);
                        final Enemy val$enemy2 = enemy;
                        if (--val$enemy2.thrash_tickLeft > 0) {
                            CatSpell.this.startTick(enemy);
                        }
                        else if (FeralSim.this.printLog) {
                            enemy.print_fade(CatSpell.this.actionName);
                        }
                    }
                });
            }
            
            @Override
            int baseCost() {
                return 50;
            }
            
            @Override
            boolean attack(final int cost) {
                final boolean doc = FeralSim.this.consume_doc();
                final double crit0 = FeralSim.this.getMeleeCrit();
                final String mods = FeralSim.this.catMods_bleed(doc);
                final double dmgMod = FeralSim.this.catDmgMod(doc) * FeralSim.this.getRazorClawsMod();
                final double critMod = FeralSim.this.critDmgMods.product;
                final int ap = FeralSim.this.getAP();
                final double tickDmg = dmgMod * FeralSim.this._thrashDmgTick(ap);
                final double initDmg0 = dmgMod * FeralSim.this._thrashDmgInit(ap);
                final FeralSim this$0 = FeralSim.this;
                ++this$0._loopingTargets;
                for (final Enemy enemy : FeralSim.this.activeTargets) {
                    final AttackT atk = FeralSim.this.yellow(enemy, crit0, true, true, false);
                    if (atk.hit) {
                        final double crit2 = enemy.suppress(crit0);
                        final double avgDmg = FeralSim.this.avgDmg_player(tickDmg, crit2);
                        double initDmg2 = initDmg0 * enemy.bleedDmgMod();
                        if (atk.crit) {
                            initDmg2 *= critMod;
                        }
                        if (atk.block) {
                            initDmg2 *= 0.7;
                        }
                        FeralSim.this.recordCatMelee(enemy, this.actionName, this.actionName, mods, atk, initDmg2);
                        if (enemy.thrash_tickLeft > 0) {
                            enemy.thrash_tickLeft = 6;
                        }
                        else {
                            this.startTick(enemy);
                            enemy.thrash_tickLeft = 5;
                            enemy.thrash_clock = FeralSim.this.clock;
                            enemy.thrash_tickIndex = 0;
                        }
                        enemy.thrash_crit = crit2;
                        enemy.thrash_tickDmg = tickDmg;
                        enemy.thrash_avgDmg = avgDmg;
                        enemy.thrash_mods = mods;
                    }
                    else {
                        FeralSim.this.logMiss(enemy, this.actionName, mods, atk);
                    }
                }
                FeralSim.this._cleanTargets();
                return true;
            }
        });
        this.THRASH_BEAR = this.register(new BearSpell("Thrash/Bear", false) {
            void startTick(final Enemy enemy) {
                FeralSim.this.addEvent(new Event(FeralSim.this.clock + 2000) {
                    public void run() {
                        if (enemy.isDead()) {
                            return;
                        }
                        final Enemy val$enemy = enemy;
                        ++val$enemy.thrashBear_tickCount;
                        final boolean crit = FeralSim.this.chance(enemy.thrashBear_crit);
                        final double dmg = enemy.bleedDmgMod() * enemy.thrashBear_tickDmg * (crit ? FeralSim.this.critDmgMods.product : 1.0);
                        BearSpell.this.recordBleed(enemy, BearSpell.this.actionName, ++enemy.thrashBear_tickIndex, enemy.thrashBear_mods, crit, dmg);
                        final Enemy val$enemy2 = enemy;
                        if (--val$enemy2.thrashBear_tickLeft > 0) {
                            BearSpell.this.startTick(enemy);
                        }
                        else if (FeralSim.this.printLog) {
                            enemy.print_fade(BearSpell.this.actionName);
                        }
                    }
                });
            }
            
            @Override
            int baseCost() {
                return 0;
            }
            
            @Override
            void attack(final int cost) {
                final boolean doc = FeralSim.this.consume_doc();
                final double crit0 = FeralSim.this.getMeleeCrit();
                final String mods = FeralSim.this.bearMods(doc, null, true);
                final double dmgMod = FeralSim.this.bearDmgMod(doc);
                final double critMod = FeralSim.this.critDmgMods.product;
                final int ap = FeralSim.this.getAP();
                final double tickDmg = dmgMod * (0.141 * ap + 686.0);
                final double initDmg0 = dmgMod * (0.191 * ap + 1232.0);
                final FeralSim this$0 = FeralSim.this;
                ++this$0._loopingTargets;
                for (final Enemy enemy : FeralSim.this.activeTargets) {
                    final AttackT atk = FeralSim.this.yellow(enemy, crit0, true, true, false);
                    if (atk.hit) {
                        final double crit2 = enemy.suppress(crit0);
                        final double avgDmg = FeralSim.this.avgDmg_player(tickDmg, crit2);
                        double initDmg2 = initDmg0 * enemy.bleedDmgMod();
                        if (atk.crit) {
                            initDmg2 *= critMod;
                        }
                        if (atk.block) {
                            initDmg2 *= 0.7;
                        }
                        this.recordMelee(enemy, this.actionName, mods, atk, initDmg2);
                        if (enemy.thrashBear_tickLeft > 0) {
                            enemy.thrashBear_tickLeft = 9;
                        }
                        else {
                            this.startTick(enemy);
                            enemy.thrashBear_tickLeft = 8;
                            enemy.thrashBear_clock = FeralSim.this.clock;
                            enemy.thrashBear_tickIndex = 0;
                        }
                        enemy.thrashBear_mods = mods;
                        enemy.thrashBear_crit = crit2;
                        enemy.thrashBear_tickDmg = tickDmg;
                        enemy.thrashBear_avgDmg = avgDmg;
                    }
                    else {
                        FeralSim.this.logMiss(enemy, this.actionName, mods, atk);
                    }
                }
                FeralSim.this._cleanTargets();
            }
        });
        this.MANGLE_BEAR = this.register(new BearSpell("Mangle/Bear", false) {
            @Override
            void reset() {
                super.reset();
                FeralSim.this.mangle_ready = 0;
            }
            
            @Override
            boolean usable() {
                return super.usable() && FeralSim.this.clock >= FeralSim.this.mangle_ready;
            }
            
            @Override
            int baseCost() {
                return 0;
            }
            
            @Override
            void attack(final int cost) {
                final FeralSim this$0 = FeralSim.this;
                this$0.mangle_ready += 6000;
                final boolean doc = FeralSim.this.consume_doc();
                final String mods = FeralSim.this.bearMods(doc, null, true);
                final Enemy enemy = FeralSim.this.target;
                final AttackT atk = FeralSim.this.yellow(enemy, FeralSim.this.getMeleeCrit());
                if (atk.hit) {
                    double dmg = 2.8 * FeralSim.this._weapDPS(FeralSim.this.random_ranges) * FeralSim.this.bearDmgMod(doc) * enemy.meleeDmgMod();
                    if (atk.crit) {
                        dmg *= FeralSim.this.critDmgMods.product;
                    }
                    if (atk.block) {
                        dmg *= 0.7;
                    }
                    this.recordMelee(enemy, this.actionName, mods, atk, dmg);
                    FeralSim.this.addRage(5);
                }
                else {
                    FeralSim.this.logMiss(enemy, this.actionName, mods, atk);
                }
            }
        });
        this.MAUL = this.register(new BearSpell("Maul", true) {
            int ready;
            
            @Override
            int baseLockout() {
                return 0;
            }
            
            @Override
            int baseCost() {
                return 30;
            }
            
            @Override
            boolean usable() {
                return super.usable() && FeralSim.this.clock >= this.ready;
            }
            
            @Override
            void reset() {
                super.reset();
                this.ready = 0;
            }
            
            @Override
            void attack(final int cost) {
                final boolean doc = FeralSim.this.consume_doc();
                this.ready = FeralSim.this.clock + 3000;
                this.maul(FeralSim.this.target, doc, 1.0);
                if (FeralSim.this.spec.glyph_maul) {
                    final int size = FeralSim.this.activeTargets.size();
                    if (size > 0) {
                        int other = FeralSim.this.rng.nextInt(size - 1);
                        if (other >= FeralSim.this.activeTargets.indexOf(FeralSim.this.target)) {
                            ++other;
                        }
                        this.maul(FeralSim.this.activeTargets.get(other), doc, 0.5);
                    }
                }
            }
            
            void maul(final Enemy enemy, final boolean doc, final double mod) {
                final AttackT atk = FeralSim.this.yellow(enemy, FeralSim.this.getMeleeCrit());
                final boolean bleeding = enemy.isBleeding();
                final String mods = FeralSim.this.bearMods(doc, bleeding ? "BL" : null, false);
                if (atk.hit) {
                    double dmg = enemy.meleeDmgMod() * FeralSim.this.bearDmgMod(doc) * (bleeding ? 1.2 : 1.0) * 1.1 * FeralSim.this._weapDPS(FeralSim.this.random_ranges) * mod * 2500.0 / 1000.0;
                    if (atk.block) {
                        dmg *= 0.7;
                    }
                    if (atk.crit) {
                        dmg *= FeralSim.this.critDmgMods.product;
                    }
                    this.recordMelee(enemy, this.actionName, mods, atk, dmg);
                }
                else {
                    FeralSim.this.logMiss(enemy, this.actionName, mods, atk);
                }
            }
        });
        this.URSOLS_VORTEX = this.register(new Spell("Ursol's Vortex") {
            @Override
            int baseLockout() {
                return 1500;
            }
            
            @Override
            boolean usable() {
                return super.usable() && FeralSim.this.clock >= FeralSim.this.vortex_ready;
            }
            
            @Override
            boolean casted() {
                FeralSim.this.vortex_ready = FeralSim.this.clock + 60000;
                FeralSim.this.vortex_expire = FeralSim.this.clock + 10000;
                FeralSim.this.addEvent(new Fader(FeralSim.this.vortex_expire, this));
                return true;
            }
        });
        this._sb = new StringBuilder(64);
        this.SKULL_BASH = this.register(new Spell_Cooldown("Skull Bash", 15000) {
            @Override
            boolean usable() {
                return FeralSim.this.target.casting && FeralSim.this.form > 0 && super.usable();
            }
            
            @Override
            int baseLockout() {
                return 0;
            }
            
            @Override
            void exec() {
                if (FeralSim.this.printLog) {
                    FeralSim.this.print(FeralSim.playerPrefix + this.actionName + ": " + FeralSim.this.target.name);
                }
                if (FeralSim.this.target.interrupt() && FeralSim.this.bonus_wodpvp_2p) {
                    if (FeralSim.this.printLog) {
                        FeralSim.this.print(FeralSim.playerPrefix + FeralSim.this.TF.actionName + ": Cooldown Reset!");
                    }
                    FeralSim.this.TF.resetReady();
                }
            }
        });
        this.SHATTERING_BLOW = this.register(new CatSpell(Symbiosis.SHATTERING_BLOW.title, false, false) {
            @Override
            boolean usable() {
                return super.usable() && FeralSim.this.target != null && !FeralSim.this.target.armor.has(-4);
            }
            
            @Override
            int baseCost() {
                return 50;
            }
            
            @Override
            boolean attack(final int cost) {
                final Enemy enemy = FeralSim.this.target;
                final AttackT atk = FeralSim.this.yellow(enemy, FeralSim.this.getMeleeCrit());
                if (atk.hit) {
                    return true;
                }
                if (FeralSim.this.printLog) {
                    FeralSim.this.logMiss(enemy, this.actionName, "", atk);
                }
                return false;
            }
        });
        this.FERAL_SPIRIT = this.register(new Spell_Cooldown(Symbiosis.FERAL_SPIRIT.title, 120000) {
            final int DUR = 30000;
            
            @Override
            int baseLockout() {
                return 1500;
            }
            
            @Override
            void exec() {
            }
        });
        this.REDIRECT = this.register(new Spell_Cooldown(Symbiosis.REDIRECT.title, 60000) {
            @Override
            int baseLockout() {
                return 1000;
            }
            
            @Override
            boolean usable() {
                return super.usable() && FeralSim.this.combo > 0 && FeralSim.this.target != FeralSim.this.comboTarget;
            }
            
            @Override
            void exec() {
                if (FeralSim.this.printLog) {
                    final Enemy prev = FeralSim.this.comboTarget;
                    FeralSim.this.print("Redirect: %s > %s", prev.name, FeralSim.this.target.name);
                }
                FeralSim.this.comboTarget = FeralSim.this.target;
            }
        });
        this.SOUL_SWAP = this.register(new Spell_Cooldown(Symbiosis.SOUL_SWAP.title, 30000) {
            @Override
            int baseLockout() {
                return 1000;
            }
            
            @Override
            boolean usable() {
                return super.usable() && FeralSim.this.can_soul_swap();
            }
            
            @Override
            void exec() {
                if (FeralSim.this.removeEvent(FeralSim.this.soulSwapTarget.ripTicker, false)) {
                    FeralSim.this.target.rip_tickLeft = FeralSim.this.soulSwapTarget.rip_tickLeft;
                    FeralSim.this.target.rip_tickDmg = FeralSim.this.soulSwapTarget.rip_tickDmg;
                    FeralSim.this.target.rip_avgDmg = FeralSim.this.soulSwapTarget.rip_avgDmg;
                    FeralSim.this.target.rip_clock = FeralSim.this.soulSwapTarget.rip_clock;
                    FeralSim.this.target.rip_mods = FeralSim.this.soulSwapTarget.rip_mods;
                    FeralSim.this.target.rip_name = FeralSim.this.soulSwapTarget.rip_name;
                    FeralSim.this.target.rip_tickIndex = 0;
                    FeralSim.this.target.resetRipExtends();
                    final int delta = 2000 - (FeralSim.this.clock - FeralSim.this.target.rip_clock) % 2000;
                    FeralSim.this.rip_startTick(FeralSim.this.target, delta);
                    FeralSim.this.soulSwapTarget.rip_tickLeft = 0;
                    if (FeralSim.this.printLog) {
                        FeralSim.this.soulSwapTarget.print_fade(FeralSim.this.RIP.actionName);
                        FeralSim.this.print("%sSoul Swap: %s%s [%d]", FeralSim.this.target.logPrefix, FeralSim.this.target.rip_name, FeralSim.this.target.rip_mods, FeralSim.this.target.rip_tickLeft);
                    }
                }
                FeralSim.this.soulSwapTarget = FeralSim.this.target;
            }
        });
        this.FON = this.register(new Spell("Force of Nature") {
            final String NAME = "Treant";
            final String BLEED_NAME = "Treant/Rake";
            final String BLEED_TICK_NAME = FeralSim.tickify("Treant/Rake");
            final String MELEE_NAME = "Treant/Melee";
            
            @Override
            void reset() {
                super.reset();
                FeralSim.this.fon_charges = 3;
            }
            
            @Override
            boolean usable() {
                return super.usable() && !FeralSim.this.stealth && FeralSim.this.fon_charges > 0;
            }
            
            @Override
            boolean casted() {
                FeralSim.this._breakStealth();
                if (FeralSim.this.fon_charges == 3) {
                    this.startRecharge();
                }
                final FeralSim this$0 = FeralSim.this;
                --this$0.fon_charges;
                final Tree tree = new Tree(this.click_count + 1, FeralSim.this.clock + 15000);
                final Enemy enemy = FeralSim.this.target;
                if (FeralSim.this.printLog) {
                    FeralSim.this.print("+%s#%d (%d/%d) @ %s", "Treant", tree.index, FeralSim.this.fon_charges, 3, enemy.name);
                }
                if (FeralSim.this.printLog) {
                    final StringBuilder sb = FeralSim.this.sb();
                    FeralSim.this.appendProc_ap(sb);
                    FeralSim.this.appendProc(sb, FeralSim.STAT_MASTERY);
                    FeralSim.this.appendProc(sb, FeralSim.STAT_CRIT);
                    tree.rake_mods = sb.toString();
                }
                final AttackT atk = FeralSim.this.yellow(enemy, 0.0, true, true, FeralSim.this.cfg.enable_retardFoN);
                if (atk.hit) {
                    tree.rake_target = enemy;
                    tree.rake_crit = enemy.suppress(FeralSim.this.getMeleeCrit());
                    tree.rake_tickDmg = FeralSim.this._treantRakeTickDmg();
                    tree.rake_tickLeft = 5;
                    double initDmg = enemy.bleedDmgMod() * tree.rake_tickDmg;
                    if (atk.crit) {
                        initDmg *= 2.0;
                    }
                    if (atk.block) {
                        initDmg *= 0.7;
                    }
                    FeralSim.this.recordDamage(enemy, this.BLEED_TICK_NAME, FeralSim.this.addNum(this.BLEED_TICK_NAME, 0), tree.rake_mods, atk, initDmg);
                    this.startTick(tree);
                }
                else {
                    FeralSim.this.logMiss(enemy, "Treant/Rake", tree.rake_mods, atk);
                }
                tree.auto_target = enemy;
                this.startSwing(tree);
                return true;
            }
            
            void startTick(final Tree tree) {
                FeralSim.this.addEvent(new Event(FeralSim.this.clock + 3000) {
                    @Override
                    void run() {
                        final Enemy enemy = tree.rake_target;
                        if (enemy.isDead()) {
                            return;
                        }
                        final Tree val$tree = tree;
                        --val$tree.rake_tickLeft;
                        final boolean crit = FeralSim.this.chance(tree.rake_crit);
                        final double dmg = enemy.bleedDmgMod() * tree.rake_tickDmg * (crit ? 2.0 : 1.0);
                        FeralSim.this.recordDamage(enemy, Spell.this.BLEED_TICK_NAME, FeralSim.this.addNum(Spell.this.BLEED_TICK_NAME, 5 - tree.rake_tickLeft), tree.rake_mods, AttackT.critOrHit(crit), dmg);
                        if (tree.rake_tickLeft > 0) {
                            Spell.this.startTick(tree);
                        }
                        else if (FeralSim.this.printLog) {
                            enemy.print_fade("Treant/Rake");
                        }
                    }
                });
            }
            
            void startSwing(final Tree treant) {
                Enemy enemy = treant.auto_target;
                if (enemy.isDead()) {
                    final Enemy auto_target = FeralSim.this.activeTargets.get(0);
                    treant.auto_target = auto_target;
                    enemy = auto_target;
                }
                final AttackT atk = FeralSim.this.white(enemy, FeralSim.this.getMeleeCrit());
                if (atk.hit) {
                    double dmg = FeralSim.this._treantMeleeDmg() * enemy.meleeDmgMod();
                    if (atk.crit) {
                        dmg *= 2.0;
                    }
                    if (atk.block) {
                        dmg *= 0.7;
                    }
                    FeralSim.this.recordDamage(enemy, "Treant/Melee", "Treant/Melee", "", atk, dmg);
                }
                else {
                    FeralSim.this.logMiss(enemy, "Treant/Melee", "", atk);
                }
                final int swing = FeralSim.this._treantMeleeSwing();
                final int next = FeralSim.this.clock + swing;
                if (next < treant.death) {
                    FeralSim.this.addEvent(new Event(next) {
                        public void run() {
                            Spell.this.startSwing(treant);
                        }
                    });
                }
                else if (FeralSim.this.printLog) {
                    FeralSim.this.addEvent(new Event(treant.death) {
                        public void run() {
                            FeralSim.this.print("-%s#%d", "Treant", treant.index);
                        }
                    });
                }
            }
            
            void startRecharge() {
                FeralSim.this.addEvent(new Event(FeralSim.this.clock + 20000) {
                    public void run() {
                        if (++FeralSim.this.fon_charges < 3) {
                            Spell.this.startRecharge();
                        }
                        if (FeralSim.this.printLog) {
                            FeralSim.this.print("Recharge: %s (%d/%d)", Spell.this.actionName, FeralSim.this.fon_charges, 3);
                        }
                        if (FeralSim.this.fon_charges == 3) {
                            FeralSim.this._tryFoN();
                        }
                    }
                });
            }
        });
        this.NV = this.register(new Spell_BuffCooldown("Nature's Vigil", 30000, 90000) {
            @Override
            void buffAdded() {
                FeralSim.this.allDmgMods.add(-12, FeralSim.this.modDmg_nv);
                FeralSim.this.allHealOutputMods.add(-12, FeralSim.this.modDmg_nv);
            }
            
            @Override
            void buffFaded() {
                FeralSim.this.allDmgMods.remove(-12);
                FeralSim.this.allHealOutputMods.remove(-12);
            }
        });
        this.KOTJ = this.register(new Spell_BuffCooldown("Incarnation", 30000, 180000) {
            @Override
            int baseLockout() {
                return 1000;
            }
        });
        this.HOTW = this.register(new Spell_BuffCooldown("Heart of the Wild", 45000, 360000) {
            @Override
            void buffAdded() {
                final double coeff = 4.2;
                FeralSim.this.natureDmgMods.add(-3, coeff);
                FeralSim.this.allHealOutputMods.add(-3, coeff);
            }
            
            @Override
            void buffFaded() {
                FeralSim.this.natureDmgMods.remove(-3);
                FeralSim.this.allHealOutputMods.remove(-3);
            }
        });
        this.BERSERK = this.register(new Spell_BuffCooldown_MultiMod("Berserk", 15000, 180000, this.energyCostMods, -6, 0.5) {
            @Override
            boolean usable() {
                return super.usable() && FeralSim.this.form > 0;
            }
            
            @Override
            void exec() {
                super.exec();
                if (FeralSim.this.cfg.heroism == Heroism.BERSERK) {
                    FeralSim.this.heroSpell.click();
                }
                if (FeralSim.this.enable_berserkPerk) {
                    FeralSim.this.increaseEnergyCeiling(50, this.dur);
                }
            }
        });
        this.TF = this.register(new Spell_BuffCooldown("Tiger's Fury", 6000, 30000) {
            @Override
            boolean usable() {
                return super.usable() && FeralSim.this.form == 1 && (FeralSim.this.cfg.enable_tfDuringBerserk || !FeralSim.this.BERSERK.up());
            }
            
            @Override
            void buffAdded() {
                if (FeralSim.this.bonus_t13_4p) {
                    FeralSim.this.clearcast();
                }
                if (FeralSim.this.bonus_t15_4p) {
                    FeralSim.this.t15_4p_charges = 3;
                }
                if (FeralSim.this.bonus_t16_4p) {
                    FeralSim.this.addEvent(new Fader(FeralSim.this.clock + 12000, FeralSim.this.bonus_t16_4p_key, "Feral Rage") {
                        @Override
                        void faded(final boolean consumed) {
                            if (consumed) {
                                final FeralSim this$0 = FeralSim.this;
                                ++this$0.t16_4p_used;
                            }
                        }
                    });
                    FeralSim.this._enterCombat();
                }
            }
            
            @Override
            String gainName() {
                if (FeralSim.this.enable_tfPerk) {
                    FeralSim.this.increaseEnergyCeiling(20, this.dur);
                }
                final int prev = FeralSim.this.energy;
                final int max = 60;
                final int gain = FeralSim.this.addEnergy(max);
                final FeralSim this$0 = FeralSim.this;
                this$0.energy_tf += gain;
                final FeralSim this$2 = FeralSim.this;
                this$2.energy_tf_max += max;
                return FeralSim.this.printLog ? String.format("%s: +%d (%d > %d)", this.actionName, gain, prev, FeralSim.this.energy) : null;
            }
        });
        this.FB = this.register(new CatSpell_Finisher("Ferocious Bite", true) {
            final String[] names = FeralSim.this.comboTable("", this.actionName, "");
            
            @Override
            boolean usable() {
                return super.usable() && FeralSim.this.target == FeralSim.this.comboTarget && FeralSim.this.combo > 0;
            }
            
            @Override
            int baseCost() {
                return 25;
            }
            
            @Override
            boolean finish() {
                final boolean doc = FeralSim.this.consume_doc();
                final AttackT atk = FeralSim.this.yellow(FeralSim.this.target, FeralSim.this.getMeleeCrit(FeralSim.this.consume_t15_4p_getCrit() + (FeralSim.this.target.isBleeding() ? 0.25 : 0.0)));
                final int extraMax = FeralSim.this._fbExtraMax();
                final int extra = Math.min(extraMax, FeralSim.this.energy);
                final FeralSim this$0 = FeralSim.this;
                this$0.fb_extra += extra;
                final FeralSim this$2 = FeralSim.this;
                this$2.energy -= extra;
                final double extraMod = 1.0 + extra / (double)extraMax;
                final String name = this.names[FeralSim.this.combo - 1];
                String mods = null;
                if (FeralSim.this.printLog) {
                    mods = FeralSim.this.catMods(doc, false, extraMod);
                }
                if (!atk.hit) {
                    FeralSim.this.logMiss(FeralSim.this.target, name, mods, atk);
                    return false;
                }
                double dmg = FeralSim.this.target.meleeDmgMod() * FeralSim.this.catDmgMod(doc) * FeralSim.this._fbDmg(FeralSim.this.random_ranges) * extraMod;
                if (atk.crit) {
                    dmg *= FeralSim.this.critDmgMods.product;
                }
                if (atk.block) {
                    dmg *= 0.7;
                }
                FeralSim.this.recordCatMelee(FeralSim.this.target, name, name, mods, atk, dmg);
                if (FeralSim.this.target.bitw() && FeralSim.this.target.rip_tickLeft > 0) {
                    if (FeralSim.this.printLog) {
                        FeralSim.this.print("%sBitW: %s%s [%d+%d] (%s-%s)", FeralSim.this.target.logPrefix, FeralSim.this.target.rip_name, FeralSim.this.target.rip_mods, FeralSim.this.target.rip_tickLeft, FeralSim.this.target.getRipExtends(), Fmt.msDur(FeralSim.this.target.ripMinTimeLeft()), Fmt.msDur(FeralSim.this.target.ripMaxTimeLeft()));
                    }
                    final FeralSim this$3 = FeralSim.this;
                    ++this$3.fb_bitw_count;
                    FeralSim.this.target.rip_tickLeft = FeralSim.this.rip_tick_init;
                    FeralSim.this.target.resetRipExtends();
                }
                return true;
            }
        });
        this.RIP = this.register(new CatSpell_Finisher("Rip", true) {
            final String[] names = FeralSim.this.comboTable("", this.actionName, "");
            final String[] tickNames = FeralSim.this.comboTable(".", this.actionName, "");
            
            @Override
            boolean usable() {
                return super.usable() && FeralSim.this.target == FeralSim.this.comboTarget && FeralSim.this.combo > 0;
            }
            
            @Override
            int baseCost() {
                return 35;
            }
            
            @Override
            boolean finish() {
                final boolean doc = FeralSim.this.consume_doc();
                final AttackT atk = FeralSim.this.yellow(FeralSim.this.target, 0.0, false, false, false);
                final String name = this.names[FeralSim.this.combo - 1];
                final String mods = FeralSim.this.catMods_bleed(doc);
                if (!atk.hit) {
                    FeralSim.this.logMiss(FeralSim.this.target, name, mods, atk);
                    return false;
                }
                FeralSim.this.soulSwapTarget = FeralSim.this.target;
                final double crit = FeralSim.this.getMeleeCrit();
                final double tickDmg = FeralSim.this.catDmgMod(doc) * FeralSim.this._ripTickDmg();
                final double avgDmg = FeralSim.this.avgDmg_player(tickDmg, crit);
                if (FeralSim.this.target.rip_tickLeft > 0) {
                    final double coeff = avgDmg / FeralSim.this.target.rip_avgDmg - 1.0;
                    if (FeralSim.this.printLog) {
                        FeralSim.this.print_clipped(FeralSim.this.target.logPrefix, FeralSim.this.target.rip_name, FeralSim.this.target.rip_tickLeft, FeralSim.this.target.rip_avgDmg, FeralSim.this.target.rip_mods, avgDmg, mods);
                    }
                    final FeralSim this$0 = FeralSim.this;
                    ++this$0.rip_clip_count;
                    final FeralSim this$2 = FeralSim.this;
                    this$2.rip_clip_coeff += coeff;
                    final FeralSim this$3 = FeralSim.this;
                    this$3.rip_clip_ticks += FeralSim.this.target.rip_tickLeft - 1;
                    FeralSim.this.target.rip_tickLeft = FeralSim.this.rip_tick_init + 1;
                }
                else {
                    FeralSim.this.rip_startTick(FeralSim.this.target, 2000);
                    FeralSim.this.logGain(FeralSim.this.target, name, mods);
                    FeralSim.this.target.rip_tickLeft = FeralSim.this.rip_tick_init;
                    FeralSim.this.target.rip_clock = FeralSim.this.clock;
                    FeralSim.this.target.rip_tickIndex = 0;
                }
                FeralSim.this.triggerProcs(FeralSim.this.target, this.actionName, Source.DEBUFF, 0.0, false, 1000);
                FeralSim.this.target.resetRipExtends();
                FeralSim.this.target.rip_crit = FeralSim.this.target.suppress(crit);
                FeralSim.this.target.rip_tickDmg = tickDmg;
                FeralSim.this.target.rip_avgDmg = avgDmg;
                FeralSim.this.target.rip_name = this.tickNames[FeralSim.this.combo - 1];
                FeralSim.this.target.rip_mods = mods;
                return true;
            }
        });
        this.NS = this.register(new Spell_Cooldown("NS", 60000) {
            @Override
            int baseLockout() {
                return 0;
            }
            
            @Override
            boolean usable() {
                return super.usable() && !FeralSim.this.ns;
            }
            
            @Override
            void exec() {
                FeralSim.this.ns = true;
                if (FeralSim.this.printLog) {
                    FeralSim.this.print("+" + this.actionName);
                }
            }
        });
        this.CAT_FORM = this.register(new CastSpell("Cat Form", false) {
            @Override
            boolean usable() {
                return super.usable() && FeralSim.this.form != 1;
            }
            
            @Override
            int baseCastTime() {
                return 0;
            }
            
            @Override
            int baseManaCost() {
                return 2220;
            }
            
            @Override
            int baseLockout() {
                return 1500;
            }
            
            @Override
            boolean exitForm() {
                return true;
            }
            
            @Override
            void exec(final int castTime, final int manaSpent) {
                FeralSim.this.form = 1;
                if (FeralSim.this.printLog) {
                    FeralSim.this.print("+Cat");
                }
            }
        });
        this.BEAR_FORM = this.register(new CastSpell("Bear Form", false) {
            @Override
            int baseCastTime() {
                return 0;
            }
            
            @Override
            int baseManaCost() {
                return 2220;
            }
            
            @Override
            int baseLockout() {
                return 1500;
            }
            
            @Override
            boolean exitForm() {
                return true;
            }
            
            @Override
            void exec(final int castTime, final int manaSpent) {
                FeralSim.this.form = 2;
                FeralSim.this._activateBearForm();
                FeralSim.this.rage = 10;
                FeralSim.this.TF.cancelAura();
                if (FeralSim.this.printLog) {
                    FeralSim.this.print("+Bear");
                }
            }
        });
        this.TRANQ = this.register(new ChanSpell("Tranquility") {
            @Override
            int tickCount() {
                return 4;
            }
            
            @Override
            int baseTickTime() {
                return 2000;
            }
            
            @Override
            int baseManaCost() {
                return 16260;
            }
            
            @Override
            boolean usable() {
                return super.usable() && FeralSim.this.clock >= FeralSim.this.tranq_ready;
            }
            
            @Override
            void exec(final int manaSpent) {
                FeralSim.this.tranq_ready = FeralSim.this.clock + 480000;
            }
            
            @Override
            void ticked() {
            }
        });
        this.HURRICANE = this.register(new ChanSpell("Hurricane") {
            final String tickName = FeralSim.tickify(this.actionName);
            
            @Override
            int baseManaCost() {
                return FeralSim.this.HOTW.up() ? 0 : 30180;
            }
            
            @Override
            int tickCount() {
                return 10;
            }
            
            @Override
            int baseTickTime() {
                return 1000;
            }
            
            @Override
            void exec(final int manaSpent) {
            }
            
            @Override
            void ticked() {
                final double crit = FeralSim.this.getSpellCrit();
                final double mod = FeralSim.this.allDmgMods.product * FeralSim.this.natureDmgMods.product;
                final String name = FeralSim.this.addNum(this.tickName, this.tick_index);
                String mods = null;
                if (FeralSim.this.printLog) {
                    final StringBuilder sb = FeralSim.this.sb();
                    FeralSim.this.appendProc(sb, FeralSim.STAT_AGI);
                    FeralSim.this.appendProc(sb, FeralSim.STAT_INT);
                    FeralSim.this.appendProc(sb, FeralSim.STAT_SP);
                    FeralSim.this.appendMulti(sb, mod);
                    mods = sb.toString();
                }
                final double dmg0 = (339.0 + 0.31 * FeralSim.this.getSP_nature()) * mod * FeralSim.this.modDmg_hurricane;
                final FeralSim this$0 = FeralSim.this;
                ++this$0._loopingTargets;
                for (final Enemy enemy : FeralSim.this.activeTargets) {
                    final AttackT atk = FeralSim.this.yellow_spell(enemy, crit);
                    if (atk.hit) {
                        double dmg2 = dmg0 * enemy.spellDmgMod();
                        if (atk.crit) {
                            dmg2 *= FeralSim.this.critDmgMods.product;
                        }
                        FeralSim.this.recordDamage(enemy, this.tickName, name, mods, atk, dmg2);
                        FeralSim.this.triggerProcs(enemy, this.actionName, Source.SPELL, dmg2, atk.crit, 0);
                    }
                    else {
                        FeralSim.this.logMiss(enemy, name, mods, atk);
                    }
                }
                FeralSim.this._cleanTargets();
            }
        });
        this.WRATH = this.register(new CastSpell("Wrath", true) {
            @Override
            int baseCastTime() {
                return 2000;
            }
            
            @Override
            int baseManaCost() {
                return FeralSim.this.HOTW.up() ? 0 : 7560;
            }
            
            @Override
            boolean exitForm() {
                return true;
            }
            
            @Override
            void exec(final int castTime, final int manaSpent) {
                final AttackT atk = FeralSim.this.yellow_spell(FeralSim.this.target, FeralSim.this.getSpellCrit());
                String mods = null;
                final double spellMod = FeralSim.this.allDmgMods.product * FeralSim.this.natureDmgMods.product;
                if (FeralSim.this.printLog) {
                    final StringBuilder sb = FeralSim.this._sb;
                    sb.setLength(0);
                    FeralSim.this.appendProc(sb, FeralSim.STAT_AGI);
                    FeralSim.this.appendProc(sb, FeralSim.STAT_INT);
                    FeralSim.this.appendProc(sb, FeralSim.STAT_SP);
                    FeralSim.this.appendMulti(sb, spellMod);
                    mods = sb.toString();
                }
                if (atk.hit) {
                    double dmg = (FeralSim.this.range(2564.0, 3295.0, FeralSim.this.random_ranges) + 1.338 * FeralSim.this.getSP_nature()) * spellMod * FeralSim.this.target.spellDmgMod() * FeralSim.this.modDmg_wrath;
                    if (atk.crit) {
                        dmg *= FeralSim.this.critDmgMods.product;
                    }
                    FeralSim.this.recordDamage(FeralSim.this.target, this.actionName, this.actionName, mods, atk, dmg);
                    FeralSim.this.triggerProcs(FeralSim.this.target, this.actionName, Source.SPELL, dmg, atk.crit, this.baseCastTime());
                }
                else {
                    FeralSim.this.logMiss(FeralSim.this.target, this.actionName, mods, atk);
                }
            }
        });
        this.HT = this.register(new CastSpell("Healing Touch", true) {
            @Override
            int baseCastTime() {
                return FeralSim.this.can_formTouch() ? 0 : 2500;
            }
            
            @Override
            int baseManaCost() {
                return (FeralSim.this.can_formTouch() || FeralSim.this.HOTW.up()) ? 0 : 17340;
            }
            
            @Override
            int baseLockout() {
                return (FeralSim.this.form == 1 && FeralSim.this.can_formTouch()) ? 1000 : 1500;
            }
            
            @Override
            boolean exitForm() {
                return !FeralSim.this.can_formTouch();
            }
            
            @Override
            void exec(final int castTime, final int manaSpent) {
                double heal = FeralSim.this.range(18460.0, 21800.0, FeralSim.this.random_ranges) + 1.86 * FeralSim.this.getSP_nature();
                String name = this.actionName;
                double extraMod = 1.0;
                if (FeralSim.this.consume_ns()) {
                    extraMod *= 1.5;
                    if (FeralSim.this.printLog) {
                        name += " (NS)";
                    }
                }
                else if (FeralSim.this.removeEvent(FeralSim.this.psKey, true)) {
                    final FeralSim this$0 = FeralSim.this;
                    ++this$0.ps_used;
                    if (FeralSim.this.printLog) {
                        name += " (PS)";
                    }
                }
                if (FeralSim.this.form == 1 && FeralSim.this.spec.glyph_catForm) {
                    extraMod *= 1.2;
                }
                if (FeralSim.this.spec.talent_doc) {
                    extraMod *= 1.2;
                }
                final boolean did_crit = FeralSim.this.chance(FeralSim.this.getSpellCrit());
                if (did_crit) {
                    heal *= 2.0;
                }
                heal *= extraMod;
                if (FeralSim.this.printLog) {
                    final String mods = FeralSim.this.healMods(extraMod);
                    FeralSim.this.print_columns(FeralSim.playerPrefix, name, String.format("(%.0f)", heal), AttackT.critOrHit(did_crit).name, mods);
                }
                if (FeralSim.this.NV.up()) {
                    FeralSim.this.recordDamage(FeralSim.this.target, FeralSim.this.NV.actionName, FeralSim.this.printLog ? (FeralSim.this.NV.actionName + "#" + this.actionName) : FeralSim.this.NV.actionName, "", AttackT.HIT, 0.25 * heal);
                }
                FeralSim.this.apply_doc();
                FeralSim.this.triggerProcs(null, this.actionName, Source.HEAL, 0.0, false, 0);
            }
        });
        this.SR = this.register(new CatSpell_Finisher("Savage Roar", false) {
            @Override
            boolean usable() {
                return super.usable() && (FeralSim.this.spec.glyph_sr || FeralSim.this.combo > 0);
            }
            
            @Override
            int baseCost() {
                return 25;
            }
            
            @Override
            boolean finish() {
                FeralSim.this._srApply(FeralSim.this._srDur(FeralSim.this.combo));
                return true;
            }
        });
        this.FF = this.register(new Spell("Faerie Fire") {
            @Override
            boolean usable() {
                return super.usable() && FeralSim.this.clock >= FeralSim.this.ff_ready;
            }
            
            @Override
            int baseLockout() {
                return (FeralSim.this.form == 1) ? 1000 : 1500;
            }
            
            @Override
            void reset() {
                super.reset();
                FeralSim.this.ff_ready = 0;
            }
            
            @Override
            boolean casted() {
                if (FeralSim.this.form > 0) {
                    FeralSim.this.ff_ready = FeralSim.this.clock + 6000;
                }
                final Enemy enemy = FeralSim.this.target;
                final AttackT atk = FeralSim.this.yellow_spell(enemy, 0.0);
                if (atk.hit) {
                    FeralSim.this.addEvent(new Fader(FeralSim.this.clock + 30000, enemy.ff, this.actionName, this.actionName, enemy.logPrefix) {
                        public void uptime(final int t) {
                            final Enemy val$enemy = enemy;
                            val$enemy.ff_uptime += t;
                        }
                        
                        public void added(final boolean replaced) {
                            super.added(replaced);
                            if (!replaced) {
                                enemy.armor.add(-2, 0.88);
                            }
                        }
                        
                        public void faded(final boolean executed) {
                            super.faded(executed);
                            if (executed) {
                                enemy.armor.remove(-2);
                            }
                        }
                    });
                    if (FeralSim.this.form == 2) {}
                    FeralSim.this.triggerProcs(enemy, this.actionName, (FeralSim.this.form == 2) ? Source.SPELL : Source.DEBUFF, 0.0, false, 0);
                }
                else {
                    FeralSim.this.logMiss(enemy, this.actionName, "", atk);
                }
                return true;
            }
        });
        this.RAKE = this.register(new CatSpell_Generator("Rake") {
            final String tickName = FeralSim.tickify(this.actionName);
            
            void startTick(final Enemy enemy) {
                FeralSim.this.addEvent(new Event(FeralSim.this.clock + 3000, enemy.rakeTicker) {
                    public void run() {
                        if (enemy.isDead()) {
                            return;
                        }
                        final Enemy val$enemy = enemy;
                        ++val$enemy.rake_tickCount;
                        final boolean crit = FeralSim.this.chance(enemy.rake_crit);
                        final double dmg = enemy.bleedDmgMod() * enemy.rake_tickDmg * (crit ? FeralSim.this.critDmgMods.product : 1.0);
                        FeralSim.this.recordCatBleed(enemy, CatSpell_Generator.this.tickName, ++enemy.rake_tickIndex, enemy.rake_mods, crit, dmg);
                        final Enemy val$enemy2 = enemy;
                        if (--val$enemy2.rake_tickLeft > 0) {
                            CatSpell_Generator.this.startTick(enemy);
                        }
                        else if (FeralSim.this.printLog) {
                            enemy.print_fade(CatSpell_Generator.this.actionName);
                        }
                    }
                });
            }
            
            @Override
            int baseCost() {
                return 35;
            }
            
            @Override
            AttackT generate() {
                final double crit = FeralSim.this.getMeleeCrit();
                final boolean doc = FeralSim.this.consume_doc();
                final AttackT atk = FeralSim.this.yellow(FeralSim.this.target, crit, true, true, false);
                final String mods = FeralSim.this.catMods_bleed(doc);
                if (atk.hit) {
                    FeralSim.this.soulSwapTarget = FeralSim.this.target;
                    final double tickDmg = FeralSim.this.catDmgMod(doc) * FeralSim.this._rakeTickDmg();
                    final double avgDmg = FeralSim.this.avgDmg_player(tickDmg, crit);
                    if (FeralSim.this.target.rake_tickLeft > 0) {
                        final double coeff = avgDmg / FeralSim.this.target.rake_avgDmg - 1.0;
                        if (FeralSim.this.printLog) {
                            FeralSim.this.print_clipped(FeralSim.this.target.logPrefix, this.actionName, FeralSim.this.target.rake_tickLeft, FeralSim.this.target.rake_avgDmg, FeralSim.this.target.rake_mods, avgDmg, mods);
                        }
                        final FeralSim this$0 = FeralSim.this;
                        ++this$0.rake_clip_count;
                        final FeralSim this$2 = FeralSim.this;
                        this$2.rake_clip_coeff += coeff;
                        final FeralSim this$3 = FeralSim.this;
                        this$3.rake_clip_ticks += FeralSim.this.target.rake_tickLeft - 1;
                        FeralSim.this.target.rake_tickLeft = 6;
                    }
                    else {
                        this.startTick(FeralSim.this.target);
                        FeralSim.this.target.rake_tickLeft = 5;
                        FeralSim.this.target.rake_clock = FeralSim.this.clock;
                        FeralSim.this.target.rake_tickIndex = 0;
                    }
                    FeralSim.this.target.rake_crit = FeralSim.this.target.suppress(crit);
                    FeralSim.this.target.rake_tickDmg = tickDmg;
                    FeralSim.this.target.rake_avgDmg = avgDmg;
                    FeralSim.this.target.rake_mods = mods;
                    double initDmg = FeralSim.this.target.bleedDmgMod() * tickDmg;
                    if (atk.crit) {
                        initDmg *= FeralSim.this.critDmgMods.product;
                    }
                    if (atk.block) {
                        initDmg *= 0.7;
                    }
                    FeralSim.this.recordCatMelee(FeralSim.this.target, this.actionName, this.actionName, mods, atk, initDmg);
                }
                else {
                    FeralSim.this.logMiss(FeralSim.this.target, this.actionName, mods, atk);
                }
                return atk;
            }
        });
        this.dmgTallyMap = new HashMap<String, DamageTally>();
        this.dmgTallyMap2 = new HashMap<String, DamageTally>();
        this.dmgMap = new HashMap<String, ArrayList<Damage>>();
        this.dw = new DamageWindow(10000, 60000);
        this.eventStack = new Event[256];
        this.futureStack = new Logic[10];
        this.ENERGY_70 = new Condition() {
            @Override
            public boolean test() {
                return FeralSim.this.energy >= 70;
            }
        };
        this.procs = new ArrayList<ProcHandler>();
        this.LOTP = new ProcHandler(new Proc("LOTP") {
            int ready;
            
            @Override
            void reset() {
                this.ready = 0;
            }
            
            @Override
            void run(final Enemy enemy, final String triggerName, final Source src, final double dmg, final boolean crit, final int baseTime) {
                if (FeralSim.this.form > 0 && FeralSim.this.clock >= this.ready) {
                    this.ready = FeralSim.this.clock + 6000;
                    final int manaGain = FeralSim.this.addMana(4800);
                    final FeralSim this$0 = FeralSim.this;
                    this$0.mana_lotp += manaGain;
                    FeralSim.this.addTickHealing("LOTP", (int)(0.04 * FeralSim.this.healthMax));
                    if (FeralSim.this.printLog) {
                        FeralSim.this.print(FeralSim.playerPrefix + "LotP");
                    }
                }
            }
        }, true, new Source[] { Source.SWING });
        this.CLEARCASTING = new ProcHandler(new Proc_Occur("Clearcasting", (ProcChance)new ProcChance_Prob(0.06)) {
            @Override
            void occured(final Enemy enemy, final String triggerName, final Source src, final double dmg, final boolean crit) {
                FeralSim.this.clearcast();
            }
        }, false, new Source[] { Source.SWING });
        this.spellIds = new IntSet(5);
        this.targets = new ArrayList<Enemy>();
        this.activeTargets = new ArrayList<Enemy>();
        this.proc_stats = new ArrayList<StatEffect>();
        this.mainProfile = new Profile();
        this.swapProfile = new Profile();
        this.origins = new ArrayList<Origin>();
        this.selfMap = new HashMap<Integer, Object>();
        this.prepList = new ArrayList<Runnable>();
        this.postList = new ArrayList<Runnable>();
        this.fonMonitor = new EventKey();
        this.FIXED = new EncounterTime() {
            @Override
            public int getRemaining() {
                return FeralSim.this.clockEnd - FeralSim.this.clock;
            }
        };
        this.SYNAPSE_SPRINGS = new Spell_BuffCooldown_Stat_Shared("Synapse Springs", 10000, 60000, FeralSim.STAT_AGI, 1920);
        this.SWORDGUARD = new ProcHandler(new Proc_Single_Stat("Swordguard", new ProcChance_Prob(0.15, 55000), 15000, FeralSim.STAT_AP, 4000), false, new Source[] { Source.SWING, Source.MELEE });
        this.FRAG_BELT = new Spell_EffectCooldown("Frag Belt", 60000) {
            static final String SPELL_NAME = "Cobalt Frag Bomb";
            static final int MIN = 750;
            static final int MAX = 1000;
            
            @Override
            void exec() {
                final double mod = FeralSim.this.allDmgMods.product;
                String mods = null;
                if (FeralSim.this.printLog) {
                    final StringBuilder sb = FeralSim.this.sb();
                    FeralSim.this.appendMulti(sb, mod);
                    mods = sb.toString();
                }
                final double crit = FeralSim.this.getSpellCrit();
                final FeralSim this$0 = FeralSim.this;
                ++this$0._loopingTargets;
                for (final Enemy enemy : FeralSim.this.activeTargets) {
                    final AttackT atk = FeralSim.this.yellow_spell(enemy, crit);
                    if (atk.hit) {
                        double dmg = FeralSim.this.range(750.0, 1000.0, FeralSim.this.random_ranges) * mod * enemy.spellDmgMod();
                        if (atk.crit) {
                            dmg *= FeralSim.this.critDmgMods.product;
                        }
                        FeralSim.this.recordDamage(enemy, "Cobalt Frag Bomb", "Cobalt Frag Bomb", mods, atk, dmg);
                    }
                    else {
                        FeralSim.this.logMiss(enemy, "Cobalt Frag Bomb", mods, atk);
                    }
                }
                FeralSim.this._cleanTargets();
            }
            
            @Override
            void appendEffect(final StringBuilder sb) {
                sb.append("Inflict ");
                sb.append(750);
                sb.append(" to ");
                sb.append(1000);
                sb.append(" Fire damage to all targets.");
            }
        };
        this.HEROISM = new Spell_BuffCooldown_MultiMod("Heroism", 40000, 600000, this.allHasteMods, -9, 1.25);
        this.DRUMS_OF_RAGE = new Spell_BuffCooldown_MultiMod("Drums of Rage", 40000, 600000, this.allHasteMods, -9, 1.3);
        this.BERSERKING = new Spell_BuffCooldown_MultiMod("Berserking", 10000, 180000, this.allHasteMods, -10, 1.2);
        this.LIFEBLOOD = new Spell_BuffCooldown_Stat("Lifeblood", 20000, 120000, FeralSim.STAT_HASTE, 2880);
        this.idle_logic = new Logic() {
            public void run() {
                if (FeralSim.this.idle_depth == 0) {
                    FeralSim.this.popLogic();
                    FeralSim.this.doRaidSpells();
                    return;
                }
                final int until = FeralSim.this.idle_expires - FeralSim.this.clock;
                if (until < 6000) {
                    if (FeralSim.this.spec.talent_doc && FeralSim.this.doc_charges < 2 && until >= 3000) {
                        FeralSim.this.HT.click();
                    }
                    if (FeralSim.this.form != 1) {
                        FeralSim.this.queuedMainSwap = true;
                        FeralSim.this.CAT_FORM.click();
                    }
                    if (until < 2000 && FeralSim.this.energy == FeralSim.this.energyMax && FeralSim.this._srDur(FeralSim.this.combo) > FeralSim.this.sr_time()) {
                        FeralSim.this.SR.click();
                    }
                }
            }
        };
        this.queuedMainSwap = true;
        this.EXPR_GENERATOR_MODE = new NamedExpr("Generator.mode") {
            @Override
            double getDouble() {
                return FeralSim.this.cfg.generator.ordinal();
            }
        };
        this.EXPR_FINISHER0_MODE = new NamedExpr("Finisher0.mode") {
            @Override
            double getDouble() {
                return FeralSim.this.cfg.finisher0.ordinal();
            }
        };
        this.EXPR_FINISHER_COUNT5 = new NamedExpr("FinisherCount5") {
            @Override
            double getDouble() {
                return FeralSim.this.finisher_tally[5];
            }
        };
        this.EXPR_THRASH_MODE = new NamedExpr("Thrash.mode") {
            @Override
            double getDouble() {
                return FeralSim.this.cfg.thrashStyle.ordinal();
            }
        };
        this.EXPR_FF_MODE = new NamedExpr("Thrash.mode") {
            @Override
            double getDouble() {
                return FeralSim.this.cfg.debuff_armor.ordinal();
            }
        };
        this.EXPR_HOTW_SWAP = new NamedExpr("HotW.swap") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.cfg.hotw_swap;
            }
        };
        this.EXPR_HOTW_BITW = new NamedExpr("HotW.bitw") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.cfg.hotw_bitw;
            }
        };
        this.EXPR_HOTW_BEFORE_BERSERK = new NamedExpr("HotW.before") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.cfg.hotw_beforeBerserk;
            }
        };
        this.EXPR_HOTW_WRATH = new NamedExpr("HotW.wrath") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.spec.talent_hotw && FeralSim.this.cfg.hotw_wrath;
            }
        };
        this.EXPR_HOTW_HURRICANE = new NamedExpr("HotW.hurricane") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.cfg.hotw_hurricane;
            }
        };
        this.EXPR_ENERGY = new NamedExpr("Energy") {
            @Override
            double getDouble() {
                return FeralSim.this.oocKey.react() ? FeralSim.this.energyMax : ((double)FeralSim.this.energy);
            }
        };
        this.EXPR_ENERGY_DEFICIT = new NamedExpr("EnergyDeficit") {
            @Override
            double getDouble() {
                return FeralSim.this.oocKey.react() ? 0.0 : (FeralSim.this.energyMax - FeralSim.this.energy);
            }
        };
        this.EXPR_ENERGY_TF_GAP = new NamedExpr("EnergyTFGap") {
            @Override
            double getDouble() {
                return FeralSim.this.oocKey.react() ? -50.0 : (FeralSim.this.energyMax + (FeralSim.this.enable_tfPerk ? 20 : 0) - FeralSim.this.energy - 60);
            }
        };
        this.EXPR_ENERGY_REGEN = new NamedExpr("EnergyPerSecond") {
            @Override
            double getDouble() {
                return FeralSim.this.energyGain(1000);
            }
        };
        this.EXPR_ENERGY_TIME_TO_MAX = new NamedExpr("TimeToEnergyCap") {
            @Override
            double getDouble() {
                return FeralSim.this.timeToEnergyCap() / 1000.0;
            }
        };
        this.EXPR_COMBO = new NamedExpr("Combo") {
            @Override
            double getDouble() {
                return FeralSim.this.combo;
            }
        };
        this.EXPR_TARGET_BITW = new NamedExpr("TargetBitW") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.target.bitw();
            }
        };
        this.EXPR_TARGET_PERC_HP = new NamedExpr("TargetPercHP") {
            @Override
            double getDouble() {
                return FeralSim.this.target.percHP() * 100.0;
            }
        };
        this.EXPR_CLOCK = new NamedExpr("Clock") {
            @Override
            double getDouble() {
                return FeralSim.this.clock / 1000.0;
            }
        };
        this.EXPR_TARGET_TTL = new NamedExpr("TargetTTL") {
            @Override
            double getDouble() {
                final int ttd = FeralSim.this.target.ttd;
                return (ttd < 0) ? Double.POSITIVE_INFINITY : (ttd / 1000.0);
            }
        };
        this.EXPR_TARGET_TIME_TO_BITW = new NamedExpr("TargetTimeToBitW") {
            @Override
            double getDouble() {
                final double perc = FeralSim.this.target.percHP();
                if (perc <= FeralSim.this.bitw_perc) {
                    return 0.0;
                }
                final int ttd = FeralSim.this.target.ttd;
                if (ttd < 0) {
                    return Double.NaN;
                }
                return (perc - FeralSim.this.bitw_perc) / perc * (ttd / 1000.0);
            }
        };
        this.EXPR_TALENT_DOC = new NamedExpr("Talent:DoC") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.spec.talent_doc;
            }
        };
        this.EXPR_TALENT_SOTF = new NamedExpr("Talent:SotF") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.spec.talent_doc;
            }
        };
        this.EXPR_TALENT_NV = new NamedExpr("Talent:NV") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.spec.talent_nv;
            }
        };
        this.EXPR_TALENT_KOTJ = new NamedExpr("Talent:KotJ") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.spec.talent_kotj;
            }
        };
        this.EXPR_FON_DMG = new NamedExpr("Gay") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.spec.talent_fon && FeralSim.this.avgDmg_player(FeralSim.this._treantRakeTickDmg()) >= FeralSim.this._treantRakeThresDmg;
            }
        };
        this.EXPR_TALENT_FON = new NamedExpr("Talent:FoN") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.spec.talent_fon;
            }
        };
        this.EXPR_SR_TIME = new NamedExpr("SR.time") {
            @Override
            double getDouble() {
                return FeralSim.this.sr_time() / 1000.0;
            }
        };
        this.EXPR_OOC_REACT = new NamedExpr("OoC.react") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.oocKey.react();
            }
        };
        this.EXPR_T16_2P_REACT = new NamedExpr("2pT16.react") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.bonus_t16_4p_key.react();
            }
        };
        this.EXPR_TF_TIME = new NamedExpr("TF.time") {
            @Override
            double getDouble() {
                return FeralSim.this.TF.timeLeft() / 1000.0;
            }
        };
        this.EXPR_NV_TIME = new NamedExpr("NV.time") {
            @Override
            double getDouble() {
                return FeralSim.this.NV.timeLeft() / 1000.0;
            }
        };
        this.EXPR_PS_TIME = new NamedExpr("PS.time") {
            @Override
            double getDouble() {
                return FeralSim.this.psKey.timeLeft() / 1000.0;
            }
        };
        this.EXPR_HOTW_TIME = new NamedExpr("HotW.time") {
            @Override
            double getDouble() {
                return FeralSim.this.HOTW.timeLeft() / 1000.0;
            }
        };
        this.EXPR_NS_UP = new NamedExpr("NS.time") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.ns;
            }
        };
        this.EXPR_RIP_MIN_TIME = new NamedExpr("Rip.time") {
            @Override
            double getDouble() {
                return FeralSim.this.target.ripMinTimeLeft() / 1000.0;
            }
        };
        this.EXPR_RIP_MAX_TIME = new NamedExpr("Rip.timeMax") {
            @Override
            double getDouble() {
                return FeralSim.this.target.ripMaxTimeLeft() / 1000.0;
            }
        };
        this.EXPR_POTION_TIME = new NamedExpr("Potion.time") {
            @Override
            double getDouble() {
                return FeralSim.this.pot_time() / 1000.0;
            }
        };
        this.EXPR_THRASH_TIME = new NamedExpr("Thrash.time") {
            @Override
            double getDouble() {
                return FeralSim.this.target.thrashTimeLeft() / 1000.0;
            }
        };
        this.EXPR_THRASH_BEAR_TIME = new NamedExpr("ThrashBear.time") {
            @Override
            double getDouble() {
                return FeralSim.this.target.thrashBearTimeLeft() / 1000.0;
            }
        };
        this.EXPR_RAKE_TIME = new NamedExpr("Rake.time") {
            @Override
            double getDouble() {
                return FeralSim.this.target.rakeTimeLeft() / 1000.0;
            }
        };
        this.EXPR_RAKE_TICKS = new NamedExpr("Rake.ticks") {
            @Override
            double getDouble() {
                return FeralSim.this.target.rake_tickLeft;
            }
        };
        this.EXPR_FON_CHARGES = new NamedExpr("FoN.charges") {
            @Override
            double getDouble() {
                return FeralSim.this.fon_charges;
            }
        };
        this.EXPR_DOC_CHARGES = new NamedExpr("DoC.charges") {
            @Override
            double getDouble() {
                return FeralSim.this.doc_charges;
            }
        };
        this.EXPR_PROC_EFF_AP = new NamedExpr("Proc.effectiveAP)") {
            @Override
            double getDouble() {
                return FeralSim.this._intStat_procOnly(FeralSim.STAT_AP, ((FeralSim.this.form > 0) ? (2 * FeralSim.this._intStat_procOnly(FeralSim.STAT_AGI, 0)) : 0) + FeralSim.this._intStat_procOnly(FeralSim.STAT_STR, 0));
            }
        };
        this.EXPR_CAT_FORM = new NamedExpr("Cat") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.form == 1;
            }
        };
        this.EXPR_BEAR_FORM = new NamedExpr("Bear") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.form == 2;
            }
        };
        this.EXPR_ARMORED = new NamedExpr("Armored") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.target.armor.identity > 0.0 && !FeralSim.this.target.armor.has(-2);
            }
        };
        this.EXPR_ARMOR_TIME = new NamedExpr("WeakArmor.remains") {
            @Override
            double getDouble() {
                if (FeralSim.this.cfg.debuff_armor == WeakenedArmor.ALWAYS) {
                    return Double.POSITIVE_INFINITY;
                }
                if (FeralSim.this.cfg.debuff_armor.casted) {
                    return (FeralSim.this.target.armor.identity > 0.0) ? (FeralSim.this.target.ff.timeLeft() / 1000.0) : Double.POSITIVE_INFINITY;
                }
                return 0.0;
            }
        };
        this.EXPR_THRASH_DMG = new NamedExpr("Thrash.dmg") {
            @Override
            double getDouble() {
                return (FeralSim.this.target.thrash_tickLeft > 0) ? FeralSim.this.target.thrash_avgDmg : 0.0;
            }
        };
        this.EXPR_RIP_DMG = new NamedExpr("Rip.dmg") {
            @Override
            double getDouble() {
                return (FeralSim.this.target.rip_tickLeft > 0) ? FeralSim.this.target.rip_avgDmg : 0.0;
            }
        };
        this.EXPR_SHRED_DMG_NEW = new NamedExpr("Shred.dmgNew") {
            @Override
            double getDouble() {
                return FeralSim.this.shredDmgNew();
            }
        };
        this.EXPR_MANGLE_DMG_NEW = new NamedExpr("Mangle.dmgNew") {
            @Override
            double getDouble() {
                return FeralSim.this.mangleDmgNew();
            }
        };
        this.EXPR_RIP_DMG_NEW = new NamedExpr("Rip.dmgNew") {
            @Override
            double getDouble() {
                return FeralSim.this.ripTickDmgNew();
            }
        };
        this.EXPR_THRASH_DMG_NEW = new NamedExpr("Thrash.dmgNew") {
            @Override
            double getDouble() {
                return FeralSim.this.thrashTickDmgNew();
            }
        };
        this.EXPR_RAKE_DMG = new NamedExpr("Rake.dmg") {
            @Override
            double getDouble() {
                return (FeralSim.this.target.rake_tickLeft > 0) ? FeralSim.this.target.rake_avgDmg : 0.0;
            }
        };
        this.EXPR_RAKE_DMG_NEW = new NamedExpr("Rake.dmgNew") {
            @Override
            double getDouble() {
                return FeralSim.this.rakeTickDmgNew();
            }
        };
        this.EXPR_ENABLED = new NamedExpr("Enabled") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.parsingAction instanceof Spell && ((Spell)FeralSim.this.parsingAction).enabled;
            }
        };
        this.EXPR_ACTIVE_TARGET_COUNT = new NamedExpr("ActiveTargetCount") {
            @Override
            double getDouble() {
                return FeralSim.this.activeTargets.size();
            }
        };
        this.minCleaveSize = 3;
        this.EXPR_MIN_CLEAVE_SIZE = new NamedExpr("MinCleaveSize") {
            @Override
            double getDouble() {
                return FeralSim.this.minCleaveSize;
            }
        };
        this.EXPR_RIP_EXTENDS_AVAIL = new NamedExpr("Rip.availExtends") {
            @Override
            double getDouble() {
                return (FeralSim.this.target.rip_tickLeft > 0) ? FeralSim.this.target.getRipExtends() : 0.0;
            }
        };
        this.EXPR_RIP_EXTENDS_ADDED = new NamedExpr("Rip.addedExtends") {
            @Override
            double getDouble() {
                return (FeralSim.this.target.rip_tickLeft > 0) ? Math.max(0, 3 - FeralSim.this.target.getRipExtends()) : Double.NaN;
            }
        };
        this.EXPR_RUNE_EXISTS = new NamedExpr("Rune.exists") {
            @Override
            boolean getBoolean() {
                return FeralSim.this.trinket_rune != null;
            }
        };
        this.EXPR_RUNE_MASTERY_TIME = new NamedExpr("Rune.remains") {
            @Override
            double getDouble() {
                return (FeralSim.this.trinket_rune != null && FeralSim.this.trinket_rune.mastery > 0) ? (FeralSim.this.trinket_rune.key.timeLeft() / 1000.0) : Double.NaN;
            }
        };
        this.EXPR_T16_4P_TIME = new Expr() {
            @Override
            double getDouble() {
                return FeralSim.this.bonus_t16_4p_key.timeLeft();
            }
        };
        this.statMods = new Multiplicative[FeralSim.STAT_NUM];
        for (int i = 0; i < FeralSim.STAT_NUM; ++i) {
            this.statMods[i] = new Multiplicative(5);
        }
    }
    
    int _intStat_procOnly(final int index, final int bonus) {
        return (int)(this.statMods[index].product * (this.procStats[index] + bonus));
    }
    
    int _intStat_roundSep(final int index) {
        final double mod = this.statMods[index].product;
        return (int)(mod * this.baseStats[index]) + (int)(mod * (this.gearStats[index] + this.procStats[index]));
    }
    
    int _intStat_onlyBase(final int index) {
        return (int)(this.baseStats[index] * this.statMods[index].product);
    }
    
    int _intStat_noBase(final int index) {
        return (int)((this.gearStats[index] + this.procStats[index]) * this.statMods[index].product);
    }
    
    int _intStat_noBaseOrExtra(final int index) {
        return (int)((this.gearStats[index] + this.procStats[index] - this.inertStats[index]) * this.statMods[index].product);
    }
    
    int _intStat(final int index, final int bonus) {
        return (int)((this.baseStats[index] + this.gearStats[index] + this.procStats[index] + bonus) * this.statMods[index].product);
    }
    
    int getAgi() {
        return this._intStat_roundSep(FeralSim.STAT_AGI);
    }
    
    int getStr() {
        return this._intStat_roundSep(FeralSim.STAT_STR);
    }
    
    int getInt() {
        return this._intStat_roundSep(FeralSim.STAT_INT);
    }
    
    int getSpi() {
        return this._intStat_roundSep(FeralSim.STAT_SPI);
    }
    
    int getSta() {
        return this._intStat_roundSep(FeralSim.STAT_STA);
    }
    
    int getMaxHP() {
        return Math.max(1, this._intStat(FeralSim.STAT_HP, 14 * this.getSta() - 260));
    }
    
    int getArmor() {
        final double mod = (this.form == 2) ? (this.guardian ? (4.3 * this.getNaturesGuardianMod()) : 2.2) : 1.0;
        return (int)(this.armorMod0 * (mod * this.curGear.armor + this.curGear.bonusArmor));
    }
    
    double getDodge() {
        final double before = this._intStat_noBase(FeralSim.STAT_DODGE) / 88500.0 + this._intStat_noBase(FeralSim.STAT_AGI) / 95115.8596;
        final double k_factor = 1.222;
        final double dr_cap = 1.50375948;
        final double after = 1.0 / (1.0 / dr_cap + k_factor / before);
        return this.percStats[FeralSim.STAT_DODGE] + this._intStat_onlyBase(FeralSim.STAT_DODGE) / 88500.0 + this._intStat_onlyBase(FeralSim.STAT_AGI) / 95115.8596 + after;
    }
    
    double getMovementSpeed() {
        return this.speed0 * ((this.form > 0) ? this.speedMod_inForm : 1.0) * ((this.form == 1) ? 1.25 : 1.0);
    }
    
    double getMeleeCritBase() {
        return this._critAura() + 0.075 + this.percStats[FeralSim.STAT_CRIT] + (this.baseStats[FeralSim.STAT_CRIT] + this.inertStats[FeralSim.STAT_CRIT] + this.consumeStats[FeralSim.STAT_CRIT]) * this.statMods[FeralSim.STAT_CRIT].product / 60000.0 + (this.baseStats[FeralSim.STAT_AGI] + this.inertStats[FeralSim.STAT_AGI] + this.consumeStats[FeralSim.STAT_AGI] - 25) * this.statMods[FeralSim.STAT_AGI].product / 125951.806640625;
    }
    
    double _critAura() {
        return (this.cfg.buff_crit || (this.cfg.enable_lotp && (this.form == 1 || this.form == 2))) ? 0.05 : 0.0;
    }
    
    double _jointCrit() {
        return this._intStat(FeralSim.STAT_CRIT, 0) / 60000.0 + this._critAura();
    }
    
    double getSpellCrit() {
        return this.getSpellCrit(0.0);
    }
    
    double getSpellCrit(final double extra) {
        return this.disable_crit ? 0.0 : (0.0185 + extra + this._jointCrit() + this.getInt() / 253366.357421875);
    }
    
    double getMeleeCrit() {
        return this.getMeleeCrit(0.0);
    }
    
    double getMeleeCrit(final double extra) {
        return this.disable_crit ? 0.0 : (0.075 + this.percStats[FeralSim.STAT_CRIT] + extra + this._jointCrit() + (this.getAgi() - 25) / 125951.806640625);
    }
    
    double getPaperDPS() {
        return this.getMeleeSpeedMod() * this._weapDPS(false) * ((this.form == 1) ? 2 : 1);
    }
    
    int getAP() {
        return this._intStat(FeralSim.STAT_AP, ((this.form > 0) ? (2 * this.getAgi() - 20) : 0) + this.getStr());
    }
    
    int getSP_nature() {
        return this.getSP(this.getAgi());
    }
    
    int getSP() {
        return this.getSP(0);
    }
    
    int getSP(final int bonus) {
        return this._intStat(FeralSim.STAT_SP, bonus + this.getInt() - 10);
    }
    
    double getHasteMod() {
        return (1.0 + this.percStats[FeralSim.STAT_HASTE] + this._intStat(FeralSim.STAT_HASTE, 0) / 42500.0) * this.allHasteMods.product;
    }
    
    double getMeleeHasteMod() {
        return this.getHasteMod() * this.meleeHasteMods.product;
    }
    
    double getSpellHasteMod() {
        return this.getHasteMod() * this.spellHasteMods.product;
    }
    
    double getMeleeSpeedMod() {
        return this.getMeleeHasteMod() * this.meleeSpeedMods.product;
    }
    
    double getSpellHit() {
        return this.HOTW.up() ? 0.15 : (this.getMeleeExp() + this.getMeleeHit());
    }
    
    double getMeleeHit() {
        return this._intStat(FeralSim.STAT_HIT, 0) / 34000.0;
    }
    
    double getMeleeExp() {
        return this._intStat(FeralSim.STAT_EXP, 0) / 34000.0;
    }
    
    int getMeleeExpRating() {
        return this._intStat_noBase(FeralSim.STAT_EXP);
    }
    
    int getMeleeHitRating() {
        return this._intStat_noBase(FeralSim.STAT_HIT);
    }
    
    int getMasteryRating() {
        return this._intStat_noBase(FeralSim.STAT_MASTERY);
    }
    
    int getHasteRating() {
        return this._intStat_noBase(FeralSim.STAT_HASTE);
    }
    
    int getCritRating() {
        return this._intStat_noBase(FeralSim.STAT_CRIT);
    }
    
    double _masteryRating() {
        return this.disable_mastery ? 0.0 : (this._intStat_noBaseOrExtra(FeralSim.STAT_MASTERY) + this.baseStats[FeralSim.STAT_MASTERY] + this.inertStats[FeralSim.STAT_MASTERY]);
    }
    
    double getRazorClawsMod() {
        return 1.0 + this._masteryRating() / 19169.3291;
    }
    
    double getNaturesGuardianMod() {
        return 1.0 + this._masteryRating() / 30000.0;
    }
    
    double getMasteryMod() {
        return this.guardian ? this.getNaturesGuardianMod() : this.getRazorClawsMod();
    }
    
    int getPvPPowerRating() {
        return this._intStat_noBase(FeralSim.STAT_PVP_POW);
    }
    
    double getPvPPowerMod() {
        return 1.0 + this.getPvPPowerRating() / 26500.0;
    }
    
    int getPvPResilRating() {
        return this._intStat_noBase(FeralSim.STAT_PVP_RES);
    }
    
    static double pvpResilMod(final int rating) {
        final double q = 35200.0;
        return 0.22999999999999998 * q / (rating + q);
    }
    
    double getPvPResilMod() {
        return pvpResilMod(this.getPvPResilRating());
    }
    
    void _startLockout(final int cd) {
        this._startLockout_exact(cd + this._delayTime());
    }
    
    void _startLockout_pool(final int cd) {
        if (this.printDebug) {
            this.print("+Pooling: %s", Fmt.msDur(cd));
        }
        this._startLockout_exact(cd);
        this._poolingResources = true;
    }
    
    void _startLockout_exact(final int cd) {
        this.addEvent(new Event(this.clock + cd, this.lockoutKey) {
            public void run() {
                if (FeralSim.this.printDebug) {
                    if (FeralSim.this._poolingResources) {
                        FeralSim.this.print("-Pooling");
                    }
                    else {
                        FeralSim.this.print("-GCD");
                    }
                }
            }
            
            @Override
            void faded(final boolean consumed) {
                FeralSim.this._poolingResources = false;
            }
        });
    }
    
    final int _delayTime() {
        return (this.delay != null) ? this.delay.generate(this.rng) : 0;
    }
    
    final int _reactTime() {
        return (this.react != null) ? this.react.generate(this.rng) : 0;
    }
    
    void addCombo(final int add) {
        if (this.comboTarget != this.target) {
            this.comboTarget = this.target;
            this.combo_waste_swap += this.combo;
            this.combo = 0;
        }
        final int comboMax = 5;
        final int prev = this.combo;
        this.combo += add;
        if (this.combo > 5) {
            this.combo_waste_overflow += this.combo - 5;
            this.combo = 5;
        }
        final int gen = this.combo - prev;
        this.combo_generated += gen;
        if (this.printLog) {
            this.print("%sCombo: %d (+%d/%d)", this.comboTarget.logPrefix, this.combo, gen, add);
        }
    }
    
    void stopCasting() {
        if (this.removeEvent(this.castingKey, false)) {
            this.ensureClockFrozen();
        }
    }
    
    void cancelForm() {
        switch (this.form) {
            case 2: {
                this.rage = 0;
                this.statMods[FeralSim.STAT_HASTE].remove(-5);
                this.statMods[FeralSim.STAT_CRIT].remove(-5);
                this.statMods[FeralSim.STAT_STA].remove(-5);
                if (this.printLog) {
                    this.print("-Bear");
                    break;
                }
                break;
            }
            case 1: {
                if (this.printLog) {
                    this.print("-Cat");
                    break;
                }
                break;
            }
        }
        this.form = 0;
    }
    
    static String comboName(final String name, final int combo) {
        return name + "`" + combo;
    }
    
    String[] comboTable(final String prefix, final String name, final String suffix) {
        final String[] v = new String[5];
        for (int i = 0; i < v.length; ++i) {
            v[i] = prefix + comboName(name, i + 1) + suffix;
        }
        return v;
    }
    
    void recordCatBleed(final Enemy enemy, final String name, final int index, final String mods, final boolean crit, final double dmg) {
        this.recordDamage(enemy, name, this.addNum(name, index), mods, AttackT.critOrHit(crit), dmg);
        this.triggerProcs(enemy, name, Source.BLEED, dmg, crit, 0);
        enemy.addGushing(dmg);
    }
    
    void recordCatMelee(final Enemy enemy, final String name, final String printName, final String mods, final AttackT atk, final double dmg) {
        this.recordDamage(enemy, name, printName, mods, atk, dmg);
        this.triggerProcs(enemy, name, Source.MELEE, dmg, atk.crit, 1000);
        enemy.addGushing(dmg);
    }
    
    int addMana(final int gain) {
        final int prev = this.mana;
        this.mana += gain;
        if (this.mana > 60000) {
            this.mana_waste += this.mana - 60000;
            this.mana = 60000;
        }
        return this.mana - prev;
    }
    
    int addRage(final int gain) {
        final int prev = this.rage;
        this.rage = Math.min(prev + gain, 1000);
        return this.rage - prev;
    }
    
    int addEnergy(final int gain) {
        final int prev = this.energy;
        this.energy = Math.min(prev + gain, this.energyMax);
        return this.energy - prev;
    }
    
     <T extends Spell> T register(final T x) {
        this.spells.add(x);
        return x;
    }
    
    static int armorByLevel(final int level) {
        switch (level) {
            case 88: {
                return 16250;
            }
            case 89: {
                return 17960;
            }
            case 90: {
                return 19680;
            }
            case 91: {
                return 21400;
            }
            case 92: {
                return 23115;
            }
            case 93: {
                return 24835;
            }
            default: {
                throw new RuntimeException(String.format("Unknown Level %d Armor", level));
            }
        }
    }
    
    static String logPrefix(final String name) {
        final StringBuilder sb = new StringBuilder();
        sb.append('@');
        sb.append(name);
        while (sb.length() < 8) {
            sb.append(' ');
        }
        sb.setLength(8);
        return sb.toString();
    }
    
    static double clip(final double x, final double min, final double max) {
        if (x > max) {
            return max;
        }
        if (x < min) {
            return min;
        }
        return x;
    }
    
    public static double armorMod(final double armor) {
        final double coeff = 46257.5;
        return 46257.5 / (armor + 46257.5);
    }
    
    double _pounceTickDmg() {
        return 763.0 + 0.03 * this.getAP();
    }
    
    double _t16_2p_mangleShredRavageSwipeMod() {
        return (this.bonus_t16_2p && this.bonus_t16_2p_key.exists) ? 1.5 : 1.0;
    }
    
    double _t14_2p_mangleShredMod() {
        return this.bonus_t14_2p ? 1.05 : 1.0;
    }
    
    double _ravageDmg(final boolean random) {
        return this.modDmg_ravage * (740.0 + 9.5 * this._weapDPS(random)) * this._t16_2p_mangleShredRavageSwipeMod();
    }
    
    double _shrangleDmg(final boolean random) {
        return this.modDmg_shrangle * (390.0 + 5.0 * this._weapDPS(random)) * this._t16_2p_mangleShredRavageSwipeMod() * this._t14_2p_mangleShredMod();
    }
    
    double _shredBleedMod() {
        return this.target.isBleeding() ? 1.2 : 1.0;
    }
    
    double mangleDmgNew() {
        return this.avgDmg_player(this.catDmgMod(this.doc_charges > 0) * this._shrangleDmg(false), this.getMeleeCrit((this.t15_4p_charges > 0) ? 0.4 : 0.0));
    }
    
    double shredDmgNew() {
        return this.avgDmg_player(this.catDmgMod(this.doc_charges > 0) * this._shrangleDmg(false) * this._shredBleedMod(), this.getMeleeCrit((this.t15_4p_charges > 0) ? 0.4 : 0.0));
    }
    
    double consume_t15_4p_getCrit() {
        if (this.t15_4p_charges > 0) {
            --this.t15_4p_charges;
            return 0.4;
        }
        return 0.0;
    }
    
    double _swipeDmg(final boolean random) {
        return this.modDmg_swipe * 4.0 * this._weapDPS(random);
    }
    
    double thrashTickDmgNew() {
        final double tickDmg = this.getRazorClawsMod() * this._thrashDmgTick(this.getAP());
        return this.avgDmg_player(this.catDmgMod(this.doc_charges > 0) * tickDmg);
    }
    
    double _thrashDmgInit(final int ap) {
        return this.modDmg_thrash * (0.191 * ap + 1232.0);
    }
    
    double _thrashDmgTick(final int ap) {
        return this.modDmg_thrash * (0.141 * ap + 686.0);
    }
    
    void logRefresh(final Enemy enemy, final String name, final String mods) {
        if (this.printLog) {
            final StringBuilder sb = this._sb;
            sb.setLength(0);
            sb.append(this.target.logPrefix);
            sb.append('*');
            sb.append(name);
            sb.append(mods);
            this.print(sb.toString());
        }
    }
    
    private void print(final String msg, final Object... args) {
        this.print(String.format(msg, args));
    }
    
    private void print(final String msg) {
        this.logWriter.add(Fmt.msms(this.clock) + " " + msg);
    }
    
    private void print_columns(final String target, final String name, final String dmg, final String type, final String suffix) {
        this.print("%s%-32s %8s %-12s %s", target, name, dmg, type, suffix);
    }
    
    void print_clipped(final String target, final String name, final int ticks, final double oldDmg, final String oldMods, final double newDmg, final String newMods) {
        final String s = String.format("Clipped: %s: %+.2f%% [%d]", name, 100.0 * (newDmg / oldDmg - 1.0), ticks);
        this.print_columns(target, s, String.format("%.0f", oldDmg), "Before", oldMods);
        this.print_columns(target, "", String.format("%.0f", newDmg), "After", newMods);
    }
    
    void logGain(final Enemy enemy, final String name, final String mods) {
        if (this.printLog) {
            this.print("%s+%s%s", enemy.logPrefix, name, mods);
        }
    }
    
    void logFade(final Enemy enemy, final String name, final String mods) {
        if (this.printLog) {
            this.print("%s-%s%s", enemy.logPrefix, name, mods);
        }
    }
    
    void logMiss(final Enemy enemy, final String name, final String mods, final AttackT atk) {
        if (this.printLog) {
            this.print("%s%-32s %8s %-12s %s", enemy.logPrefix, name, "---", atk.name, mods);
        }
    }
    
    double catDmgMod(final boolean doc) {
        return this.allDmgMods.product * (this.TF.up() ? this.modDmg_tf : 1.0) * ((this.sr_time() > 0) ? this.modDmg_sr : 1.0) * (doc ? this.modDmg_doc : 1.0);
    }
    
    double bearDmgMod(final boolean doc) {
        return this.allDmgMods.product * (doc ? this.modDmg_doc : 1.0);
    }
    
    double catDmgMod(final boolean doc, final int t) {
        return this.allDmgMods.product * ((this.TF.timeLeft() > t) ? this.modDmg_tf : 1.0) * ((t > 0 || this.sr_time() > 0) ? this.modDmg_sr : 1.0) * (doc ? this.modDmg_doc : 1.0);
    }
    
    private StringBuilder sb() {
        this._sb.setLength(0);
        return this._sb;
    }
    
    String healMods(final double extraMod) {
        if (!this.printLog) {
            return null;
        }
        final StringBuilder sb = this._sb;
        sb.setLength(0);
        if (this.HOTW.up()) {
            sb.append("+HotW");
        }
        this.appendProc(sb, FeralSim.STAT_AGI);
        this.appendProc(sb, FeralSim.STAT_INT);
        this.appendProc(sb, FeralSim.STAT_SP);
        this.appendMulti(sb, this.allHealOutputMods.product * extraMod);
        return sb.toString();
    }
    
    String spellMods() {
        if (!this.printLog) {
            return null;
        }
        final StringBuilder sb = this._sb;
        sb.setLength(0);
        if (this.HOTW.up()) {
            sb.append("+HotW");
        }
        this.appendProc(sb, FeralSim.STAT_AGI);
        this.appendProc(sb, FeralSim.STAT_INT);
        this.appendProc(sb, FeralSim.STAT_SP);
        this.appendMulti(sb, this.allDmgMods.product);
        return sb.toString();
    }
    
    String bearMods(final boolean doc, final String extra, final boolean dot) {
        if (!this.printLog) {
            return null;
        }
        final StringBuilder sb = this._sb;
        sb.setLength(0);
        if (doc) {
            sb.append("+DoC");
        }
        this.appendProc_ap(sb);
        if (dot) {
            this.appendProc(sb, FeralSim.STAT_CRIT);
        }
        if (extra != null) {
            sb.append('+');
            sb.append(extra);
        }
        this.appendMulti(sb, this.allDmgMods.product);
        return sb.toString();
    }
    
    String catMods_bleed(final boolean doc) {
        return this.catMods(doc, true, 1.0);
    }
    
    String catMods_melee(final boolean doc) {
        return this.catMods(doc, false, 1.0);
    }
    
    String catMods(final boolean doc, final boolean dot, final double extraMod) {
        if (!this.printLog) {
            return null;
        }
        final StringBuilder sb = this._sb;
        sb.setLength(0);
        if (this.sr_time() > 0) {
            sb.append("+SR");
        }
        if (doc) {
            sb.append("+DoC");
        }
        if (this.TF.up()) {
            sb.append("+TF");
        }
        this.appendProc_ap(sb);
        if (dot) {
            this.appendProc(sb, FeralSim.STAT_MASTERY);
            this.appendProc(sb, FeralSim.STAT_CRIT);
        }
        this.appendMulti(sb, this.allDmgMods.product * extraMod);
        return sb.toString();
    }
    
    void appendMulti(final StringBuilder sb, final double multi) {
        if (multi > 1.0) {
            sb.append('+');
            sb.append(round((multi - 1.0) * 100.0));
            sb.append('%');
        }
    }
    
    void appendProc_ap(final StringBuilder sb) {
        this.appendProc(sb, FeralSim.STAT_AGI);
        this.appendProc(sb, FeralSim.STAT_STR);
        this.appendProc(sb, FeralSim.STAT_AP);
    }
    
    void appendProc(final StringBuilder sb, final int index) {
        final int stat = this.procStats[index];
        if (stat > 0) {
            sb.append('+');
            sb.append(FeralSim.STATS[index].abbr);
            sb.append('(');
            sb.append(stat);
            sb.append(')');
        }
        else if (stat < 0) {
            sb.append('-');
            sb.append(FeralSim.STATS[index].abbr);
            sb.append('(');
            sb.append(-stat);
            sb.append(')');
        }
    }
    
    static int round(final double x) {
        return (int)(x + 0.5);
    }
    
    double flux(final double up) {
        return 1.0 + 2.0 * up * this.rng.nextDouble() - up;
    }
    
    double range(final double min, final double max, final boolean random) {
        return random ? (min + (max - min) * this.rng.nextDouble()) : ((max + min) / 2.0);
    }
    
    boolean can_soul_swap() {
        return this.soulSwapTarget != null && this.target != this.soulSwapTarget && !this.soulSwapTarget.isDead() && (this.soulSwapTarget.rake_tickLeft > 0 || this.soulSwapTarget.rip_tickLeft > 0);
    }
    
    private void increaseEnergyCeiling(final int extraEnergy, final int dur) {
        this.energyMax += extraEnergy;
        this.addEvent(new Event(this.clock + dur) {
            public void run() {
                final FeralSim this$0 = FeralSim.this;
                this$0.energyMax -= extraEnergy;
                FeralSim.this.energy = Math.min(FeralSim.this.energy, FeralSim.this.energyMax);
            }
        });
    }
    
    double _fbDmg(final boolean random) {
        return this.modDmg_fb * (this.range(316.0, 685.0, random) + (762.0 + 0.196 * this.getAP()) * this.combo);
    }
    
    int _fbExtraMax() {
        return this.BERSERK.up() ? 12 : 25;
    }
    
    private void _fadeFutureProcs(final int t) {
        System.arraycopy(this.procStats, 0, this.procStats_backup, 0, FeralSim.STAT_NUM);
        final int end = this.clock + t;
        for (int i = 0; i < this.eventCount; ++i) {
            final Event e = this.eventStack[this.eventIndex(i)];
            if (e.t > end) {
                return;
            }
            e.futureProc();
        }
    }
    
    private void _restoreProcs() {
        final int[] swap = this.procStats;
        this.procStats = this.procStats_backup;
        this.procStats_backup = swap;
    }
    
    private double catDmgModFuture(final int t) {
        double mod = this.allDmgMods.product * this.modDmg_sr;
        if (this.NV.fallsOff(t)) {
            mod /= this.modDmg_nv;
        }
        if (this.TF.stillUp(t)) {
            mod *= this.modDmg_tf;
        }
        if (this.doc_charges > 1) {
            mod *= this.modDmg_doc;
        }
        return mod;
    }
    
    double _ripTickDmg() {
        return this.getRazorClawsMod() * this.modDmg_rip * 0.15 * (900.0 + this.combo * (2560.0 + 0.3872 * this.getAP()));
    }
    
    double ripTickDmgNew() {
        return this.avgDmg_player(this.catDmgMod(this.doc_charges > 0) * this._ripTickDmg());
    }
    
    double ripTickDmgFuture(final int t) {
        if (t < 1) {
            return this.ripTickDmgNew();
        }
        final double mod = this.catDmgModFuture(t);
        this._fadeFutureProcs(t);
        final double dmg = this.avgDmg_player(mod * this._ripTickDmg());
        this._restoreProcs();
        return dmg;
    }
    
    void rip_startTick(final Enemy enemy, final int freq) {
        this.addEvent(new Event(this.clock + freq, enemy.ripTicker) {
            public void run() {
                if (enemy.isDead()) {
                    return;
                }
                final Enemy val$enemy = enemy;
                ++val$enemy.rip_tickCount;
                final boolean crit = FeralSim.this.chance(enemy.rip_crit);
                final double dmg = enemy.bleedDmgMod() * enemy.rip_tickDmg * (crit ? FeralSim.this.critDmgMods.product : 1.0);
                FeralSim.this.recordCatBleed(enemy, enemy.rip_name, ++enemy.rip_tickIndex, enemy.rip_mods, crit, dmg);
                final Enemy val$enemy2 = enemy;
                if (--val$enemy2.rip_tickLeft > 0) {
                    FeralSim.this.rip_startTick(enemy, 2000);
                }
                else if (FeralSim.this.printLog) {
                    enemy.print_fade(FeralSim.this.RIP.actionName);
                }
            }
        });
    }
    
    void tempBearForm() {
        this.form = 2;
        this._activateBearForm();
    }
    
    private void _activateBearForm() {
        this.statMods[FeralSim.STAT_HASTE].add(-5, 1.5);
        this.statMods[FeralSim.STAT_CRIT].add(-5, 1.5);
        this.statMods[FeralSim.STAT_STA].add(-5, 1.4);
    }
    
    void apply_doc() {
        if (this.spec.talent_doc) {
            if (this.printLog) {
                if (this.doc_charges > 0) {
                    this.print("~Dream of Cenarius: +%d (%d -> 2)", this.doc_charges - 2, this.doc_charges, 2);
                }
                else {
                    this.print("+Dream of Cenarius: +2");
                }
            }
            this.doc_wasted += this.doc_charges;
            this.doc_charges = 2;
            this.doc_total += this.doc_charges;
            this.addEvent(new Event(this.clock + 30000, this.docKey) {
                public void run() {
                    if (FeralSim.this.printLog) {
                        FeralSim.this.print("-Dream of Cenarius (%d/2)", FeralSim.this.doc_charges);
                    }
                    final FeralSim this$0 = FeralSim.this;
                    this$0.doc_wasted += FeralSim.this.doc_charges;
                    FeralSim.this.doc_charges = 0;
                }
            });
        }
    }
    
    boolean consume_doc() {
        if (this.doc_charges > 0) {
            if (this.printLog) {
                if (this.doc_charges == 1) {
                    this.print("-Dream of Cenarius (0/2)");
                }
                else {
                    this.print("~Dream of Cenarius (1/2)");
                }
            }
            --this.doc_charges;
            if (this.doc_charges == 0) {
                this.removeEvent(this.docKey, false);
            }
            ++this.doc_spent;
            return true;
        }
        return false;
    }
    
    boolean consume_ns() {
        if (this.ns) {
            this.ns = false;
            ++this.ns_used;
            this.NS.beginCooldown();
            if (this.printLog) {
                this.print("-" + this.NS.actionName);
            }
            return true;
        }
        return false;
    }
    
    boolean can_formTouch() {
        return this.form > 0 && (this.ns || this.psKey.exists);
    }
    
    boolean should_hotw_swap() {
        return this.cfg.hotw_swap && this.spec.talent_hotw;
    }
    
    final int _srRollover() {
        return Math.max(0, 3000 - this._delayTime());
    }
    
    final int _srDur(final int c) {
        return (2 + c) * 6000;
    }
    
    void _srApply(int dur) {
        if (this.combo == 0) {
            ++this.sr_zero;
        }
        final int prev = this.sr_time();
        if (prev > 0) {
            dur += Math.min(this._srRollover(), (this.clock - this.sr_clockAlign) % 3000);
        }
        else {
            this.sr_clockAlign = this.clock;
        }
        this.sr_clip_time += prev;
        if (prev > dur) {
            ++this.sr_cancel;
        }
        this.sr_expire = this.clock + dur;
        this.addEvent(new Fader(this.sr_expire, this.SR.eventKey, this.printLog ? String.format("%s: %.2fs (%.2fs)", comboName(this.SR.actionName, this.combo), dur / 1000.0, prev / 1000.0) : this.SR.actionName, this.SR.actionName) {
            @Override
            void uptime(final int t) {
                final FeralSim this$0 = FeralSim.this;
                this$0.sr_uptime += t;
            }
        });
    }
    
    double avgDmg_guardian(final double dmg) {
        return this.avgDmg_guardian(dmg, this.getMeleeCrit());
    }
    
    double avgDmg_guardian(final double dmg, final double crit) {
        return this.avgDmg(dmg, this.target.suppress(crit), 2.0);
    }
    
    double avgDmg_player(final double dmg) {
        return this.avgDmg_player(dmg, this.getMeleeCrit());
    }
    
    double avgDmg_player(final double dmg, final double crit) {
        return this.avgDmg(dmg, this.target.suppress(crit), this.critDmgMods.identity);
    }
    
    double avgDmg(final double dmg, final double crit, final double critMod) {
        if (crit >= 1.0) {
            return dmg * critMod;
        }
        if (crit > 0.0) {
            return (1.0 - crit) * dmg + dmg * crit * critMod;
        }
        return dmg;
    }
    
    double _rakeTickDmg() {
        return this.getRazorClawsMod() * this.modDmg_rake * (99.0 + 0.3 * this.getAP());
    }
    
    double rakeTickDmgNew() {
        return this.avgDmg_player(this.catDmgMod(this.doc_charges > 0) * this._rakeTickDmg());
    }
    
    double rakeTickDmgFuture(final int t) {
        if (t < 1) {
            return this.rakeTickDmgNew();
        }
        final double mod = this.catDmgModFuture(t);
        this._fadeFutureProcs(t);
        final double dmg = this.avgDmg_player(mod * this._rakeTickDmg());
        this._restoreProcs();
        return dmg;
    }
    
    double _treantRakeTickDmg() {
        return this._rakeTickDmg() / 3.0;
    }
    
    double treantRakeTickDmgNew() {
        return this.avgDmg_guardian(this._treantRakeTickDmg());
    }
    
    double treantRakeTickDmgFuture(final int t) {
        if (t > 0) {
            this._fadeFutureProcs(t);
            final double dmg = this.treantRakeTickDmgNew();
            this._restoreProcs();
            return dmg;
        }
        return this.treantRakeTickDmgNew();
    }
    
    boolean treantRakeTickDmgFutureBetter(final int t, final double thres) {
        System.arraycopy(this.procStats, 0, this.procStats_backup, 0, FeralSim.STAT_NUM);
        final int end = this.clock + t;
        boolean decrease = false;
        boolean better = false;
        for (int i = 0; i < this.eventCount; ++i) {
            final Event e = this.eventStack[this.eventIndex(i)];
            if (e.t > end) {
                break;
            }
            if (e.futureProc()) {
                final double dmg = this.treantRakeTickDmgNew();
                if (decrease) {
                    if (dmg > thres) {
                        better = true;
                        break;
                    }
                }
                else if (dmg < thres) {
                    decrease = true;
                }
            }
        }
        this._restoreProcs();
        return better;
    }
    
    int _treantMeleeSwing() {
        return ceil(2000.0 / this.getMeleeSpeedMod());
    }
    
    double _treantMeleeDmg() {
        return 2189.0 + 0.14285 * this.getAP();
    }
    
    double treantMeleeDPSNew() {
        return this.avgDmg_guardian(this._treantMeleeDmg()) / this._treantMeleeSwing();
    }
    
    double treantMeleeDPSFuture(final int t) {
        if (t > 0) {
            this._fadeFutureProcs(t);
            final double dmg = this.treantMeleeDPSNew();
            this._restoreProcs();
            return dmg;
        }
        return this.treantMeleeDPSNew();
    }
    
    void addTickHealing(final String name, final int amt) {
        this.addHealing(name, false, amt);
    }
    
    void addHealing(final String name, final boolean crit, final int amt) {
    }
    
    String addNum(final String name, final int num) {
        return this.printLog ? (name + "#" + num) : name;
    }
    
    private void clearDamage() {
        this.dmgTallyMap.clear();
        this.runCount = 0;
    }
    
    void _cleanTargets() {
        final int loopingTargets = this._loopingTargets - 1;
        this._loopingTargets = loopingTargets;
        if (loopingTargets > 0 || !this._dirtyTargets) {
            return;
        }
        this._dirtyTargets = false;
        final Iterator<Enemy> iter = this.activeTargets.iterator();
        while (iter.hasNext()) {
            if (iter.next().isDead()) {
                iter.remove();
            }
        }
        this._selectAnyTarget();
    }
    
    void _selectAnyTarget() {
        if (this.activeTargets.isEmpty()) {
            throw new EndOfCombat();
        }
        if (this.comboTarget != null && !this.comboTarget.isDead()) {
            this.target = this.comboTarget;
        }
        else {
            this.target = this.activeTargets.get(0);
        }
    }
    
    void recordDamage(final Enemy enemy, final String dmgName, final String printName, final String mods, final AttackT atk, final double dmg) {
        if (enemy.isDead()) {
            return;
        }
        final int rounded = round(dmg);
        if (this.printLog) {
            this.print("%s%-32s %8d %-12s %s", enemy.logPrefix, printName, rounded, atk.name, mods);
        }
        this.damage_total += rounded;
        ++this.damage_count;
        this.dw.add(this.clock, rounded);
        this.saveDamage(dmgName, mods, atk.crit, rounded);
        enemy.suffer(rounded);
        if (enemy.isDead()) {
            if (this.printLog) {
                this.print("%s has died <%s>", enemy.name, Fmt.msDur(this.clock));
            }
            if (this._loopingTargets > 0) {
                this._dirtyTargets = true;
            }
            else {
                this.activeTargets.remove(enemy);
                this._selectAnyTarget();
            }
        }
    }
    
    private void _setTarget(final Enemy enemy) {
        if (this.printLog) {
            this.print("Changed Target: %s -> %s", this.target.name, enemy.name);
        }
    }
    
    static String dropTickIndex(final String x) {
        final int pos = x.indexOf(35);
        return (pos >= 0) ? x.substring(0, pos + 1) : x;
    }
    
    void backupDamageTally() {
        this.copyDamageTally(this.dmgTallyMap, this.dmgTallyMap2);
    }
    
    void restoreDamageTally() {
        this.copyDamageTally(this.dmgTallyMap2, this.dmgTallyMap);
    }
    
    void copyDamageTally(final Map<String, DamageTally> src, final Map<String, DamageTally> dst) {
        dst.clear();
        for (final Map.Entry<String, DamageTally> e : src.entrySet()) {
            dst.put(e.getKey(), e.getValue().copy());
        }
    }
    
    void saveDamage(final String key, final String mods, final boolean crit, final int amt) {
        if (this.saveDamage) {
            DamageTally dt = this.dmgTallyMap.get(key);
            if (dt == null) {
                dt = new DamageTally(key);
                this.dmgTallyMap.put(key, dt);
            }
            if (crit) {
                if (dt.crit_num++ == 0) {
                    dt.crit_min = amt;
                    dt.crit_max = amt;
                }
                else if (amt > dt.crit_max) {
                    dt.crit_max = amt;
                }
                else if (amt < dt.crit_min) {
                    dt.crit_min = amt;
                }
                final DamageTally damageTally = dt;
                damageTally.crit_sum += amt;
            }
            else {
                if (dt.hit_num++ == 0) {
                    dt.hit_min = amt;
                    dt.hit_max = amt;
                }
                else if (amt > dt.hit_max) {
                    dt.hit_max = amt;
                }
                else if (amt < dt.hit_min) {
                    dt.hit_min = amt;
                }
                final DamageTally damageTally2 = dt;
                damageTally2.hit_sum += amt;
            }
        }
    }
    
    void enterStealth() {
        if (!this.combat && !this.stealth) {
            this.autoattack = false;
            this.stealth = true;
            if (this.printLog) {
                this.print("+Prowl");
            }
        }
    }
    
    void _breakStealth() {
        if (this.stealth) {
            this.autoattack = true;
            this.stealth = false;
            if (this.printLog) {
                this.print("-Prowl");
            }
        }
    }
    
    AttackT white(final Enemy enemy, final double crit) {
        this._breakStealth();
        double p = this.rng.nextDouble();
        final double hit = this.getMeleeHit();
        final double miss = enemy.missProb - hit;
        if (miss > 0.0) {
            p -= miss;
            if (p < 0.0) {
                return AttackT.MISS;
            }
        }
        final double exp = this.getMeleeExp();
        final double dodge = enemy.dodgeProb - exp;
        if (dodge > 0.0) {
            p -= dodge;
            if (p < 0.0) {
                return AttackT.DODGE;
            }
        }
        final boolean front = !enemy.isBehind();
        if (front) {
            final double parry = enemy.parryProb - exp;
            if (parry > 0.0) {
                p -= parry;
                if (p < 0.0) {
                    return AttackT.PARRY;
                }
            }
        }
        final boolean block = front && this.chance(enemy.blockProb);
        if (!this.disable_glancing) {
            final double glance = enemy.glanceProb;
            if (glance > 0.0) {
                p -= glance;
                if (p < 0.0) {
                    return block ? AttackT.BLOCK_GLANCE : AttackT.GLANCE;
                }
            }
        }
        if (p < crit - enemy.critMinus) {
            return block ? AttackT.BLOCK_CRIT : AttackT.CRIT;
        }
        return block ? AttackT.BLOCK_HIT : AttackT.HIT;
    }
    
    AttackT yellow_spell(final Enemy en, final double crit) {
        this._breakStealth();
        if (this.chance(en.spellMissRate - this.getSpellHit())) {
            return AttackT.MISS;
        }
        return AttackT.critOrHit(this.chance(crit - en.critMinus));
    }
    
    AttackT yellow(final Enemy en, final double crit) {
        return this.yellow(en, crit, true, false, false);
    }
    
    AttackT yellow(final Enemy en, double crit, final boolean blockable, final boolean crittableAfterBlock, final boolean frontOverride) {
        this._breakStealth();
        double p = this.rng.nextDouble();
        final double hit = this.getMeleeHit();
        final double miss = en.missProb - hit;
        if (miss > 0.0) {
            p -= miss;
            if (p < 0.0) {
                return AttackT.MISS;
            }
        }
        final double exp = this.getMeleeExp();
        final double dodge = en.dodgeProb - exp;
        if (dodge > 0.0) {
            p -= dodge;
            if (p < 0.0) {
                return AttackT.DODGE;
            }
        }
        crit -= en.critMinus;
        if (!en.isBehind() || frontOverride) {
            final double parry = en.parryProb - exp;
            if (parry > 0.0) {
                p -= parry;
                if (p < 0.0) {
                    return AttackT.PARRY;
                }
            }
            if (blockable) {
                final double block = en.blockProb;
                if (block > 0.0) {
                    p -= block;
                    if (p < 0.0) {
                        return (crittableAfterBlock && this.chance(crit)) ? AttackT.BLOCK_CRIT : AttackT.BLOCK_HIT;
                    }
                }
            }
        }
        return (p < crit) ? AttackT.CRIT : AttackT.HIT;
    }
    
    void fadeRemainingEvents() {
        for (int i = 0; i < this.eventCount; ++i) {
            final Event f = this.eventStack[this.eventIndex(i)];
            f.faded(false);
        }
        this.doc_total -= this.doc_charges;
        this.combo_generated -= this.combo;
        if (this.printLog) {
            this.print("*** End of Combat <%s> ***", Fmt.msDur(this.clock));
        }
        for (final Runnable r : this.postList) {
            r.run();
        }
    }
    
    boolean removeEvent(final EventKey key, final boolean consumed) {
        if (key.exists) {
            for (int i = 0; i < this.eventCount; ++i) {
                final int idx = this.eventIndex(i);
                final Event f = this.eventStack[idx];
                if (f.key == key) {
                    this.eventStack[idx] = new DeadEvent(f.t);
                    key.exists = false;
                    if (consumed) {
                        f.run();
                    }
                    f.faded(consumed);
                    return true;
                }
            }
            throw new RuntimeException("WTF?");
        }
        return false;
    }
    
    void clearEvents() {
        for (int i = 0; i < this.eventStack.length; ++i) {
            this.eventStack[i] = null;
        }
        this.eventStart = 10;
        this.eventCount = 0;
    }
    
    int eventIndex(final int index) {
        return (this.eventStart + index) % this.eventStack.length;
    }
    
    void dumpEvents() {
        for (int i = 0; i < this.eventCount; ++i) {
            final int index = this.eventIndex(i);
            final Event f = this.eventStack[index];
            System.out.println(String.format("%03d %03d %8d %s", i, index, f.t - this.clock, (f.key == this.lockoutKey) ? "Lockout" : f.key));
        }
        System.out.println();
    }
    
    void ensureClockFrozen() {
        if (this.eventCount == 0 || this.eventStack[this.eventIndex(0)].t > this.clock) {
            this.addEvent(new Event(this.clock));
        }
    }
    
    void addEvent(final Event x) {
        if (this.eventCount == this.eventStack.length) {
            int shift = 0;
            for (int i = 0, n = this.eventCount; i < n; ++i) {
                final int index = this.eventIndex(i);
                final Event e = this.eventStack[index];
                if (e instanceof DeadEvent) {
                    ++shift;
                }
                else if (shift > 0) {
                    this.eventStack[this.eventIndex(i - shift)] = e;
                }
            }
            if (shift == 0) {
                this.dumpEvents();
                throw new RuntimeException("Event queue is full");
            }
            System.err.println("Almost Full");
            this.eventCount -= shift;
        }
        final EventKey key = x.key;
        if (key == null) {
            x.added(false);
        }
        else {
            x.added(this.removeEvent(key, false));
            key.exists = true;
            key.added = this.clock;
            key.expires = x.t;
        }
        final int x_t = x.t;
        int a = 0;
        int b = this.eventCount - 1;
        while (a <= b) {
            final int m = a + b >>> 1;
            final int m_t = this.eventStack[this.eventIndex(m)].t;
            if (m_t > x_t) {
                b = m - 1;
            }
            else {
                if (m_t >= x_t) {
                    for (a = m; a < b && this.eventStack[this.eventIndex(a + 1)].t == x_t; ++a) {}
                    ++a;
                    break;
                }
                a = m + 1;
            }
        }
        if (a == this.eventCount) {
            this.eventStack[this.eventIndex(this.eventCount++)] = x;
        }
        else if (a == 0) {
            this.eventStart = ((this.eventStart == 0) ? this.eventStack.length : this.eventStart) - 1;
            this.eventStack[this.eventIndex(0)] = x;
            ++this.eventCount;
        }
        else {
            final int off = this.eventIndex(a);
            final int end = this.eventIndex(this.eventCount);
            if (off < end) {
                System.arraycopy(this.eventStack, off, this.eventStack, off + 1, end - off);
            }
            else {
                final int len = this.eventStack.length;
                System.arraycopy(this.eventStack, 0, this.eventStack, 1, end);
                this.eventStack[0] = this.eventStack[len - 1];
                System.arraycopy(this.eventStack, off, this.eventStack, off + 1, len - off - 1);
            }
            this.eventStack[off] = x;
            ++this.eventCount;
        }
        this.eventMax = Math.max(this.eventMax, this.eventCount);
    }
    
    boolean chance(final double prob) {
        return prob >= 1.0 || (prob > 0.0 && this.rng.nextDouble() < prob);
    }
    
    void pushLogic(final Logic x) {
        this.futureStack[++this.futureIndex] = x;
        this.top = x;
    }
    
    void popLogic() {
        final Logic[] futureStack = this.futureStack;
        final int futureIndex = this.futureIndex - 1;
        this.futureIndex = futureIndex;
        this.top = futureStack[futureIndex];
    }
    
    void clearFuture() {
        this.futureIndex = -1;
    }
    
    public void resetAll() {
        this.idle_depth = 0;
        this.idle_expires = 0;
        this._poolingResources = false;
        this._statChangedScheduled = false;
        this.form = 1;
        this.mainGear.activate();
        this.natureDmgMods.clear();
        this.allHealOutputMods.clear();
        this.allDmgMods.clear();
        this.allSufferMods.clear();
        this.allHasteMods.clear();
        this.meleeHasteMods.clear();
        this.meleeSpeedMods.clear();
        this.spellHasteMods.clear();
        this.critDmgMods.clear();
        this.energyCostMods.clear();
        for (int i = 0; i < FeralSim.STAT_NUM; ++i) {
            this.procStats[i] = 0;
        }
        this.combat = false;
        this.autoattack = true;
        this.stealth = false;
        this.queuedMainSwap = false;
        this.castingExpr = null;
        this.clearEvents();
        this.castingKey.exists = false;
        this.lockoutKey.exists = false;
        this.swingingKey.exists = false;
        this.oocKey.exists = false;
        this.channelKey.exists = false;
        this.psKey.exists = false;
        this.bonus_t16_2p_key.exists = false;
        this.bonus_t16_4p_key.exists = false;
        this.fonMonitor.exists = false;
        this.sufferKey.exists = false;
        this.docKey.exists = false;
        this.shared_use_ready = 0;
        this.dw.clear();
        this.tranq_ready = 0;
        this.ps_replaced = 0;
        this.ps_occur = 0;
        this.ps_used = 0;
        this.rake_clip_count = 0;
        this.rake_clip_ticks = 0;
        this.rake_clip_coeff = 0.0;
        this.rip_clip_count = 0;
        this.rip_clip_coeff = 0.0;
        this.rip_clip_ticks = 0;
        this.global_rip_extends = 0;
        this.pounce_tick_count = 0;
        this.pounce_clip_count = 0;
        this.pounce_clip_ticks = 0;
        this.fb_bitw_count = 0;
        this.fb_extra = 0;
        this.clock = 0;
        this.damage_total = 0L;
        this.damage_count = 0;
        this.energy = 0;
        this.mana = 0;
        this.rage = 0;
        this.combo = 0;
        this.comboTarget = null;
        this.swing_reset = 0;
        this.pvpRavage_ready = this.bonus_pvpRavage;
        this.soulSwapTarget = null;
        this.ooc_occur = 0;
        this.ooc_replaced = 0;
        this.ooc_replaced_lockout = 0;
        this.ooc_spent = 0;
        this.ooc_thrash = 0;
        this.ooc_mangleShredRavage = 0;
        this.ooc_energy = 0;
        this.pot_count = 0;
        this.pot_ready = 0;
        this.pot_expire = 0;
        this.vortex_ready = 0;
        this.vortex_expire = 0;
        this.melee_count = 0;
        this.melee_miss = 0;
        this.swing_reset = 0;
        this.doc_charges = 0;
        this.doc_wasted = 0;
        this.doc_spent = 0;
        this.doc_total = 0;
        this.t15_4p_charges = 0;
        this.t16_4p_used = 0;
        this.ns = false;
        this.ns_used = 0;
        this.mana_lotp = 0;
        this.mana_waste = 0;
        this.energy_regen = 0;
        this.energy_regen_max = 0;
        this.energy_refund = 0;
        this.energy_refund_max = 0;
        this.energy_sotf = 0;
        this.energy_sotf_max = 0;
        this.energy_tf = 0;
        this.energy_tf_max = 0;
        this.generator_miss_count = 0;
        this.generator_count = 0;
        this.finisher_miss_count = 0;
        for (int i = 0; i < this.finisher_tally.length; ++i) {
            this.finisher_tally[i] = 0;
        }
        this.sr_expire = 0;
        this.sr_uptime = 0;
        this.sr_zero = 0;
        this.sr_clip_time = 0;
        this.sr_clockAlign = 0;
        this.sotf_count = 0;
        this.combo_finisher = 0;
        this.combo_generated = 0;
        this.combo_waste_overflow = 0;
        this.combo_waste_swap = 0;
        this.fb_bitw_count = 0;
        for (final ProcHandler x : this.activeProcs) {
            x.proc.reset();
        }
        for (final Enemy x2 : this.targets) {
            x2.reset();
        }
        for (final Spell x3 : this.activeSpells) {
            x3.reset();
        }
    }
    
    void triggerProcs(final Enemy enemy, final String triggerName, final Source src, final double damage, final boolean crit, final int baseTime) {
        final int bit = src.bit();
        for (final ProcHandler x : this.procs) {
            if (x.matches(bit, crit)) {
                x.proc.run(enemy, triggerName, src, damage, crit, baseTime);
            }
        }
        if (this.printMore) {
            final String critChar = crit ? "*" : "";
            this.print("Proc Trigger: Enemy(%s) Source(%s) Type(%s) Damage(%s%.0f%s) CastTime(%s)", (enemy == null) ? "Player" : enemy.name, triggerName, src, critChar, damage, critChar, baseTime);
        }
    }
    
    void clearcast() {
        this.addEvent(new Event(this.clock + 15000, this.oocKey) {
            @Override
            void added(final boolean replaced) {
                if (replaced) {
                    if (FeralSim.this.lockoutKey.exists) {
                        final FeralSim this$0 = FeralSim.this;
                        ++this$0.ooc_replaced_lockout;
                        if (FeralSim.this.printLog) {
                            FeralSim.this.print("*%s: wasted during lockout", FeralSim.this.CLEARCASTING.proc.name);
                        }
                    }
                    else {
                        final FeralSim this$2 = FeralSim.this;
                        ++this$2.ooc_replaced;
                        if (FeralSim.this.printLog) {
                            FeralSim.this.print("*%s: wasted", FeralSim.this.CLEARCASTING.proc.name);
                        }
                    }
                }
                else if (FeralSim.this.printLog) {
                    FeralSim.this.print("+" + FeralSim.this.CLEARCASTING.proc.name);
                }
                final FeralSim this$3 = FeralSim.this;
                ++this$3.ooc_occur;
            }
            
            @Override
            void faded(final boolean executed) {
                if (FeralSim.this.printLog) {
                    FeralSim.this.print("-" + FeralSim.this.CLEARCASTING.proc.name);
                }
            }
        });
        if (this.bonus_t16_2p) {
            this.addEvent(new Fader(this.clock + 5000, this.bonus_t16_2p_key, "Feral Fury"));
        }
        if (this._poolingResources) {
            final int t = this._reactTime();
            if (t > 0) {
                if (this.printDebug) {
                    this.print("*Pooling: aborting...");
                }
                this.addEvent(new Event(this.clock + t) {
                    public void run() {
                        FeralSim.this._stopPooling();
                    }
                });
            }
            else {
                this._stopPooling();
            }
        }
    }
    
    private void _stopPooling() {
        if (this._poolingResources) {
            this.removeEvent(this.lockoutKey, true);
        }
    }
    
    GearConfig extractWeapon(final boolean main, final Profile p, final Collection<String> warnings) {
        final String name = main ? "Main" : "Swap";
        double min;
        double max;
        int speed;
        if (p.MH.isWeapon()) {
            min = p.MH.getWeapDmgMin();
            max = p.MH.getWeapDmgMax();
            speed = (int)(p.MH.getWeapSpeed() * 1000.0);
        }
        else {
            max = (min = 0.0);
            speed = 2000;
        }
        final int[] stats = new int[FeralSim.STAT_NUM];
        for (int i = 0; i < FeralSim.STAT_NUM; ++i) {
            stats[i] = this.inertStats[i] + this.fixedStats[i] + p.totalStat(FeralSim.STATS[i]);
        }
        if (this.cfg.challengeMode) {
            final GemT socket = p.MH.getSocketAt(0);
            if (socket != null && socket == GemT.SHA) {
                final Gem gem = p.MH.getGemAt(0);
                if (gem != null) {
                    for (int j = 0; j < FeralSim.STAT_NUM; ++j) {
                        final int[] array = stats;
                        final int n = j;
                        array[n] -= gem.getStat(FeralSim.STATS[j]);
                    }
                }
            }
        }
        final int armor = p.gearArmor(15);
        final int bonusArmor = p.totalStat(StatT.ARMOR);
        final GearConfig gear = new GearConfig(name, min, max, speed, stats, armor, bonusArmor);
        for (final Profile.Slot slot : p.SLOTS) {
            if (slot.type.bodyPart == 2) {
                this.spellIds.clear();
                slot.collectSpells(this.spellIds);
                for (int k = 0; k < this.spellIds.count; ++k) {
                    final int spellId = this.spellIds.member[k];
                    switch (spellId) {
                        case 109862: {
                            this.addSpellOrigin(spellId, slot.type, new ProcHandler_GearSpecific(gear, new Proc_Single_StatRampUp_FuryOfTheBeast(94), false, new Source[] { Source.SWING, Source.MELEE }));
                            break;
                        }
                        case 107824: {
                            this.addSpellOrigin(spellId, slot.type, new ProcHandler_GearSpecific(gear, new Proc_Single_StatRampUp_FuryOfTheBeast(107), false, new Source[] { Source.SWING, Source.MELEE }));
                            break;
                        }
                        case 109865: {
                            this.addSpellOrigin(spellId, slot.type, new ProcHandler_GearSpecific(gear, new Proc_Single_StatRampUp_FuryOfTheBeast(120), false, new Source[] { Source.SWING, Source.MELEE }));
                            break;
                        }
                        default: {
                            if (warnings != null) {
                                warnings.add(String.format("Unknown spell for %s: %s (%d)", p.MH.type.name, p.MH.item.name, spellId));
                                break;
                            }
                            break;
                        }
                    }
                }
            }
        }
        final EnchantT enchant = p.MH.enchant;
        if (enchant != null) {
            if (enchant == EnchantT.WEAPON_DANCING_STEEL || enchant == EnchantT.WEAPON_DANCING_STEEL_PVP) {
                final ProcHandler ph = new ProcHandler_GearSpecific(gear, new Proc_Single_Stat("Dancing Steel", new ProcChance_RPPM(2.53, 0, false), 12000, FeralSim.STAT_AGI, 1650), false, new Source[] { Source.SWING, Source.MELEE, Source.SPELL, Source.DEBUFF, Source.HEAL, Source.DOT, Source.HOT });
                this.addSpellOrigin(enchant.spellId, SlotT.MAIN_HAND, ph);
                this.addSelf(120032, ph);
            }
            else if (enchant == EnchantT.WEAPON_JADE_SPIRIT || enchant == EnchantT.WEAPON_JADE_SPIRIT_PVP) {
                this.addSpellOrigin(enchant.spellId, SlotT.MAIN_HAND, new ProcHandler_GearSpecific(gear, new Proc_Single_Stat("Jade Spirit", new ProcChance_RPPM(2.2, 0, false), 12000, FeralSim.STAT_INT, 1650), false, new Source[] { Source.SWING, Source.MELEE, Source.SPELL, Source.DEBUFF, Source.HEAL, Source.DOT, Source.HOT }));
            }
            else if (enchant == EnchantT.WEAPON_WINDSONG) {
                final ProcHandler ph = new ProcHandler_GearSpecific(gear, new Proc_MultiStat(enchant.shortName, new ProcChance_RPPM(2.2, 0, false), 12000, 1500, new int[] { FeralSim.STAT_CRIT, FeralSim.STAT_HASTE, FeralSim.STAT_MASTERY }), false, new Source[] { Source.SWING, Source.MELEE, Source.SPELL, Source.DEBUFF, Source.HEAL, Source.DOT, Source.HOT });
                this.addSpellOrigin(enchant.spellId, SlotT.MAIN_HAND, ph);
                this.addSelf(104423, ph);
                this.addSelf(104510, ph);
                this.addSelf(104509, ph);
            }
            else if (enchant == EnchantT.WEAPON_POWER_TORRENT) {
                this.addSpellOrigin(enchant.spellId, SlotT.MAIN_HAND, new ProcHandler_GearSpecific(gear, new Proc_Single_Stat(enchant.shortName, new ProcChance_Prob(0.33, 45000), 12000, FeralSim.STAT_INT, 500), false, Source.values()));
            }
            else if (enchant == EnchantT.WEAPON_ELEMENTAL_FORCE) {
                this.addSpellOrigin(enchant.spellId, SlotT.MAIN_HAND, new ProcHandler_GearSpecific(gear, new Proc_Occur(enchant.shortName, new ProcChance_RPPM(9.17, 0, true)) {
                    static final int min = 2775;
                    static final int max = 3225;
                    
                    @Override
                    void toString(final StringBuilder sb) {
                        sb.append(" to inflict ");
                        sb.append(2775);
                        sb.append(" to ");
                        sb.append(3225);
                        sb.append(" Elemental damage.");
                    }
                    
                    @Override
                    void occured(final Enemy enemy, final String triggerName, final Source src, final double dmg_, final boolean crit) {
                        String mods = null;
                        if (FeralSim.this.printLog) {
                            final StringBuilder sb = FeralSim.this.sb();
                            FeralSim.this.appendMulti(sb, FeralSim.this.allDmgMods.product);
                            mods = sb.toString();
                        }
                        final AttackT atk = FeralSim.this.yellow_spell(enemy, FeralSim.this.getSpellCrit());
                        if (atk.hit) {
                            double dmg = FeralSim.this.allDmgMods.product * FeralSim.this.range(2775.0, 3225.0, FeralSim.this.random_ranges) * enemy.spellDmgMod();
                            if (atk.crit) {
                                dmg *= FeralSim.this.critDmgMods.product;
                            }
                            FeralSim.this.recordDamage(enemy, this.name, this.name, mods, atk, dmg);
                        }
                        else {
                            FeralSim.this.logMiss(enemy, this.name, mods, atk);
                        }
                    }
                }, false, new Source[] { Source.SWING, Source.MELEE, Source.BLEED, Source.SPELL, Source.DOT }));
            }
        }
        return gear;
    }
    
    private void fauxTrigger(final Enemy enemy, final Object thing) {
        if (thing instanceof ProcHandler) {
            final ProcHandler ph = (ProcHandler)thing;
            if (ph.couldProc()) {
                ph.proc.run(enemy, "FauxTrigger", null, 1.0, false, 0);
            }
        }
        else if (thing instanceof Spell) {
            ((Spell)thing).click();
        }
        else if (thing instanceof Pair) {
            final Pair p = (Pair)thing;
            this.fauxTrigger(enemy, p.car);
            this.fauxTrigger(enemy, p.cdr);
        }
    }
    
    private void addSelf(final Integer spellId, final Object thing) {
        final Object prev = this.selfMap.get(spellId);
        this.selfMap.put(spellId, (prev == null) ? thing : new Pair(prev, thing));
    }
    
    public void setup(final Profile main, final Profile swap, final FeralSpec spec, final FeralConfig cfg, final Collection<String> warnings) {
        this.spec = spec;
        this.cfg = cfg;
        this.heroSpell = (cfg.heroismDrums ? this.DRUMS_OF_RAGE : this.HEROISM);
        this.selfMap.clear();
        this.react = IntDist.nullIfZero(cfg.react);
        this.delay = IntDist.nullIfZero(cfg.delay);
        this.random_ranges = cfg.random_ranges;
        this.proc_stats.clear();
        this.trinket_rune = null;
        this.procs.clear();
        this.origins.clear();
        this.onUseSpells.clear();
        this.raidSpells.clear();
        this.mixology = ProfT.ALCH.isMemberOf(main.profs);
        for (int i = 0; i < FeralSim.STAT_NUM; ++i) {
            final StatT stat = FeralSim.STATS[i];
            this.percStats[i] = 0.0;
            this.inertStats[i] = cfg.extra_inert.get(stat);
            this.consumeStats[i] = cfg.flask.getStat(stat, this.mixology);
            this.fixedStats[i] = cfg.extra_active.get(stat);
            this.baseStats[i] = main.baseStat(stat);
            this.statMods[i].clearAndSetIdentity(1.0);
        }
        int speed = cfg.extra_inert.get(StatT.SPEED) + cfg.extra_active.get(StatT.SPEED);
        if (!this.disable_racials && this.enable_wodRacials && main.race == RaceT.NIGHT_ELF) {
            speed += 2;
        }
        boolean minorSpeed = main.FEET.sumStat(StatT.SPEED) > 0;
        this.speed0 = 1.0 + speed / 100.0;
        this.speedMod_inForm = 1.0;
        if (cfg.food != null) {
            final Integer index = FeralSim.STAT_MAP.get(cfg.food.type);
            if (index != null) {
                final int[] consumeStats = this.consumeStats;
                final int intValue = index;
                consumeStats[intValue] += cfg.food.value;
            }
        }
        for (int j = 0; j < FeralSim.STAT_NUM; ++j) {
            final int[] fixedStats = this.fixedStats;
            final int n = j;
            fixedStats[n] += this.consumeStats[j];
        }
        this.percStats[FeralSim.STAT_DODGE] = 0.03;
        this.armorMod0 = 1.0;
        final double hotwMod = spec.talent_hotw ? 1.06 : 1.0;
        final double statMod = cfg.buff_stat ? 1.05 : 1.0;
        final boolean armorSpec = !this.disable_armorSpec && main.hasArmorSpecialization();
        this.statMods[FeralSim.STAT_AGI].clearAndSetIdentity(statMod * hotwMod * ((!this.guardian && armorSpec) ? 1.05 : 1.0));
        this.statMods[FeralSim.STAT_INT].clearAndSetIdentity(statMod * hotwMod);
        this.statMods[FeralSim.STAT_STR].clearAndSetIdentity(statMod);
        this.statMods[FeralSim.STAT_STA].clearAndSetIdentity(hotwMod * (cfg.buff_sta ? 1.1 : 1.0) * ((this.guardian && armorSpec) ? 1.05 : 1.0));
        this.statMods[FeralSim.STAT_AP].clearAndSetIdentity(cfg.buff_ap ? 1.1 : 1.0);
        this.statMods[FeralSim.STAT_SP].clearAndSetIdentity(cfg.buff_sp ? 1.1 : 1.0);
        this.statMods[FeralSim.STAT_MASTERY].clearAndSetIdentity(1.0);
        this.natureDmgMods.clearAndSetIdentity(1.0);
        this.energyCostMods.clearAndSetIdentity(1.0);
        this.allDmgMods.clearAndSetIdentity(1.0);
        this.allSufferMods.clearAndSetIdentity(1.0);
        this.allHealOutputMods.clearAndSetIdentity(1.0);
        this.meleeHasteMods.clearAndSetIdentity(1.0);
        this.meleeSpeedMods.clearAndSetIdentity(cfg.buff_meleeHaste ? 1.1 : 1.0);
        this.spellHasteMods.clearAndSetIdentity(cfg.buff_spellHaste ? 1.05 : 1.0);
        this.debuff_meleeMod = (cfg.debuff_meleeTaken ? 1.04 : 1.0);
        this.debuff_spellMod = (cfg.debuff_spellTaken ? 1.05 : 1.0);
        if (cfg.buff_mastery) {
            final int[] inertStats = this.inertStats;
            final int stat_MASTERY = FeralSim.STAT_MASTERY;
            inertStats[stat_MASTERY] += 3000;
        }
        double critDamage = 1.0;
        double critEffect = 1.0;
        if (!this.disable_racials) {
            if (main.race == RaceT.TAUREN) {
                if (this.enable_wodRacials) {
                    critDamage *= 1.02;
                    final int[] fixedStats2 = this.fixedStats;
                    final int stat_HP = FeralSim.STAT_HP;
                    fixedStats2[stat_HP] += 450;
                }
                else {
                    this.statMods[FeralSim.STAT_HP].add(-1, 1.05);
                }
            }
            else if (main.race == RaceT.WORGEN) {
                final double[] percStats = this.percStats;
                final int stat_CRIT = FeralSim.STAT_CRIT;
                percStats[stat_CRIT] += 0.01;
            }
            else if (main.race == RaceT.NIGHT_ELF) {
                final double[] percStats2 = this.percStats;
                final int stat_DODGE = FeralSim.STAT_DODGE;
                percStats2[stat_DODGE] += 0.02;
                if (this.enable_wodRacials) {
                    final double[] percStats3 = this.percStats;
                    final int stat_HASTE = FeralSim.STAT_HASTE;
                    percStats3[stat_HASTE] += 0.01;
                }
            }
        }
        if (ProfT.SKIN.isMemberOf(main.profs)) {
            final int[] fixedStats3 = this.fixedStats;
            final int stat_CRIT2 = FeralSim.STAT_CRIT;
            fixedStats3[stat_CRIT2] += 480;
        }
        if (ProfT.MINE.isMemberOf(main.profs)) {
            final int[] fixedStats4 = this.fixedStats;
            final int stat_STA = FeralSim.STAT_STA;
            fixedStats4[stat_STA] += 480;
        }
        final SetBonusMap bonuses = cfg.challengeMode ? SetBonusMap.EMPTY : cfg.setBonuses;
        if (bonuses.hasBonus(1106, 0)) {
            final int[] fixedStats5 = this.fixedStats;
            final int stat_PVP_POW = FeralSim.STAT_PVP_POW;
            fixedStats5[stat_PVP_POW] += 500;
        }
        if (bonuses.hasBonus(1106, 2)) {
            final int[] fixedStats6 = this.fixedStats;
            final int stat_PVP_POW2 = FeralSim.STAT_PVP_POW;
            fixedStats6[stat_PVP_POW2] += 1000;
        }
        if (spec.talent_fs) {
            this.speed0 *= 1.15;
        }
        else if (bonuses.hasBonus(1106, 1)) {
            this.speedMod_inForm *= 1.15;
        }
        if (bonuses.hasBonus(1203, 0)) {
            final int[] fixedStats7 = this.fixedStats;
            final int stat_PVP_RES = FeralSim.STAT_PVP_RES;
            fixedStats7[stat_PVP_RES] += 5280;
        }
        this.bonus_t13_2p = bonuses.hasBonus(1058, 0);
        this.bitw_perc = (this.disable_bitw ? Double.NaN : (this.bonus_t13_2p ? 0.6 : 0.25));
        this.bonus_t13_4p = bonuses.hasBonus(1058, 1);
        this.bonus_pvpRavage = bonuses.hasBonus(1106, 3);
        this.bonus_t14_2p = bonuses.hasBonus(1127, 0);
        this.bonus_t14_4p = bonuses.hasBonus(1127, 1);
        this.rip_tick_init = 8 + (this.bonus_t14_4p ? 2 : 0);
        this.bonus_t15_2p = bonuses.hasBonus(1153, 0);
        this.bonus_t15_4p = bonuses.hasBonus(1153, 1);
        this.bonus_t16_2p = bonuses.hasBonus(1199, 0);
        this.bonus_t16_4p = bonuses.hasBonus(1199, 1);
        this.bonus_t17_2p_old = bonuses.hasBonus(10000001, 0);
        this.bonus_t17_2p = bonuses.hasBonus(10000001, 1);
        this.bonus_t17_4p = bonuses.hasBonus(10000001, 2);
        this.bonus_wodpvp_2p = bonuses.hasBonus(10000002, 0);
        this.bonus_wodpvp_4p = bonuses.hasBonus(10000002, 1);
        if (this.bonus_t17_2p) {
            this.procs.add(new ProcHandler(new Proc("T17: 2p") {
                @Override
                void run(final Enemy enemy, final String triggerName, final Source src, final double dmg, final boolean crit, final int baseTime) {
                    if (dmg > 0.0) {
                        final int gain = FeralSim.this.addEnergy(3);
                        if (FeralSim.this.printDebug) {
                            FeralSim.this.print("Energy on Bleed: +%d", gain);
                        }
                    }
                }
            }, false, new Source[] { Source.BLEED }));
        }
        this.mainProfile.importProfile(main);
        this.swapProfile.importProfile(main);
        final GearConfig weapon = this.extractWeapon(true, main, warnings);
        this.swapGear = weapon;
        this.mainGear = weapon;
        if ((this.should_hotw_swap() || warnings == null) && swap != null) {
            this.swapProfile.MH.clear();
            this.swapProfile.OH.clear();
            try {
                this.swapProfile.MH.unsafeCopy(swap.MH);
                this.swapProfile.OH.unsafeCopy(swap.OH);
            }
            catch (RuntimeException err) {
                if (warnings != null) {
                    warnings.add("Weapon Swap: " + err.getMessage());
                }
            }
            this.swapGear = this.extractWeapon(false, this.swapProfile, warnings);
            if (warnings != null && !swap.hasTwoHander()) {
                final boolean mh = swap.MH.isEmpty();
                final boolean oh = swap.OH.isEmpty();
                if (mh && oh) {
                    warnings.add("Missing Caster Weapon");
                }
                else if (mh) {
                    warnings.add("Missing Caster " + SlotT.MAIN_HAND.name);
                }
                else if (oh) {
                    warnings.add("Missing Caster " + SlotT.OFF_HAND.name);
                }
            }
        }
        this.TF.restoreCooldown();
        this.BERSERK.restoreCooldown();
        if (this.disable_berserk) {
            this.BERSERK.enabled = false;
        }
        if (cfg.enable_lotp) {
            this.procs.add(this.LOTP);
        }
        if (cfg.enable_ooc) {
            this.procs.add(this.CLEARCASTING);
            this.addSelf(135700, this.CLEARCASTING);
        }
        if (cfg.num_tricksOfTheTrade > 0) {
            this.raidSpells.add(new Spell_TricksOfTheTrade(5000 * cfg.num_tricksOfTheTrade, 30000));
        }
        if (cfg.num_unholyFrenzy > 0) {
            this.raidSpells.add(new Spell_BuffCooldown_MultiMod("Unholy Frenzy", cfg.num_unholyFrenzy * 30000, 180000, this.allHasteMods, -7, 1.2));
        }
        if (cfg.num_skullBanner > 0) {
            this.raidSpells.add(new Spell_SkullBanner(cfg.num_skullBanner * 10000, 180000));
        }
        if (cfg.num_shatteringThrow > 0) {
            this.raidSpells.add(new Spell_ShatteringThrow(cfg.num_shatteringThrow * 10000, 300000));
        }
        if (cfg.num_stormlashTotem > 0) {
            this.raidSpells.add(new Spell_Stormlash(10000 * cfg.num_stormlashTotem, 300000));
        }
        this.SOUL_SWAP.enabled = (cfg.symbiosis == Symbiosis.SOUL_SWAP);
        this.REDIRECT.enabled = (cfg.symbiosis == Symbiosis.REDIRECT);
        final Spell_Cooldown feral_SPIRIT = this.FERAL_SPIRIT;
        final boolean enabled = cfg.symbiosis == Symbiosis.FERAL_SPIRIT;
        feral_SPIRIT.enabled = enabled;
        if (enabled) {
            this.onUseSpells.add(this.FERAL_SPIRIT);
        }
        this.FF.enabled = cfg.debuff_armor.casted;
        this.NS.enabled = false;
        this.KOTJ.enabled = spec.talent_kotj;
        this.FON.enabled = spec.talent_fon;
        this.HOTW.enabled = spec.talent_hotw;
        this.NV.enabled = spec.talent_nv;
        this.URSOLS_VORTEX.enabled = spec.talent_vortex;
        this.BERSERKING.value = (this.enable_wodRacials ? 1.15 : 1.2);
        final Spell_BuffCooldown_MultiMod berserking = this.BERSERKING;
        final boolean enabled2 = !this.disable_racials && main.race == RaceT.TROLL && cfg.enable_berserking;
        berserking.enabled = enabled2;
        if (enabled2) {
            this.onUseSpells.add(this.BERSERKING);
        }
        final Spell_BuffCooldown_Stat lifeblood = this.LIFEBLOOD;
        final boolean enabled3 = ProfT.HERB.isMemberOf(main.profs) && cfg.enable_lifeblood;
        lifeblood.enabled = enabled3;
        if (enabled3) {
            this.onUseSpells.add(this.LIFEBLOOD);
        }
        if (main.BACK.enchant == EnchantT.BACK_SWORDGUARD) {
            this.addSpellOrigin(EnchantT.BACK_SWORDGUARD.spellId, SlotT.BACK, this.SWORDGUARD);
            this.addSelf(125486, this.SWORDGUARD);
        }
        final Spell_Cooldown frag_BELT = this.FRAG_BELT;
        final boolean enabled4 = main.WAIST.tinker == TinkerT.FRAG_BELT;
        frag_BELT.enabled = enabled4;
        if (enabled4) {
            this.addSpellOrigin(TinkerT.FRAG_BELT.profSpellId, SlotT.WAIST, this.FRAG_BELT);
        }
        final Spell_BuffCooldown_Stat synapse_SPRINGS = this.SYNAPSE_SPRINGS;
        final boolean enabled5 = main.HANDS.tinker == TinkerT.SYNAPSE_SPRINGS;
        synapse_SPRINGS.enabled = enabled5;
        if (enabled5) {
            this.addSpellOrigin(TinkerT.SYNAPSE_SPRINGS.profSpellId, SlotT.HANDS, this.SYNAPSE_SPRINGS);
        }
        this.bonus_pvpMaim = false;
        for (final Profile.Slot slot : main.SLOTS) {
            if (slot.type.bodyPart != 2) {
                this.spellIds.clear();
                slot.collectSpells(this.spellIds);
                if (this.spellIds.count != 0) {
                    final Item item = slot.item;
                    final int itemLevel = slot.getScaledItemLevel();
                    final int procBudget = ItemT.statBudget(itemLevel, 1.0);
                    final String procName = fmt_scaledProc(item.name, itemLevel);
                    for (int k = 0; k < this.spellIds.count; ++k) {
                        final int spellId = this.spellIds.member[k];
                        switch (spellId) {
                            case 17619: {
                                break;
                            }
                            case 11818: {
                                break;
                            }
                            case 23990: {
                                this.addSpellOrigin(spellId, slot.type, String.format("Equip: Run speed increased slightly.", new Object[0]));
                                minorSpeed = true;
                                break;
                            }
                            case 42292: {
                                this.addSpellOrigin(spellId, slot.type, new Spell_EffectCooldown(procName, 120000) {
                                    @Override
                                    void appendEffect(final StringBuilder sb) {
                                        sb.append("Removes all movement impairing effects and all effects which cause loss of control of your character.");
                                    }
                                    
                                    @Override
                                    void exec() {
                                    }
                                });
                                break;
                            }
                            case 44797: {
                                final double perc = 0.03;
                                critEffect *= 1.0 + perc;
                                this.addSpellOrigin(spellId, slot.type, String.format("Equip: Increases critical strike damage by %.2f%%.", 100.0 * perc));
                                break;
                            }
                            case 55344: {
                                final double perc = 0.02;
                                this.armorMod0 *= 1.0 + perc;
                                this.addSpellOrigin(spellId, slot.type, String.format("Equip: Increases armor by %.2f%%.", 100.0 * perc));
                                break;
                            }
                            case 55357: {
                                break;
                            }
                            case 55358: {
                                break;
                            }
                            case 55378: {
                                break;
                            }
                            case 61252: {
                                this.bonus_pvpMaim = true;
                                break;
                            }
                            case 105574: {
                                final int stat2 = round(2.6670000553 * procBudget);
                                final ProcHandler ph = new ProcHandler(new Proc_Single_Stat(procName, new ProcChance_Prob(0.25, 55000), 15000, FeralSim.STAT_AGI, stat2), false, Source.values());
                                this.addSpellOrigin(spellId, slot.type, ph);
                                this.addSelf(60233, ph);
                                break;
                            }
                            case 126482: {
                                final int stat2 = round(1.9800000191 * procBudget);
                                final ProcHandler ph = new ProcHandler(new Proc_Single_Stat(procName, new ProcChance_Prob(0.15, 65000), 20000, FeralSim.STAT_HASTE, stat2), false, new Source[] { Source.SWING, Source.MELEE, Source.SPELL });
                                this.addSpellOrigin(spellId, slot.type, ph);
                                this.addSelf(126483, ph);
                                break;
                            }
                            case 126484: {
                                final int stat2 = round(2.4749999046 * procBudget);
                                final Spell spell = new Spell_BuffCooldown_Stat_Shared(procName, 15000, 90000, FeralSim.STAT_AGI, stat2);
                                this.addSpellOrigin(spellId, slot.type, spell);
                                break;
                            }
                            case 126490: {
                                final int stat2 = round(1.9800000191 * procBudget);
                                final ProcHandler ph = new ProcHandler(new Proc_Single_Stat(procName, new ProcChance_Prob(0.45, 85000), 25000, FeralSim.STAT_AGI, stat2), true, new Source[] { Source.SWING, Source.MELEE, Source.SPELL });
                                this.addSpellOrigin(spellId, slot.type, ph);
                                this.addSelf(126489, ph);
                                break;
                            }
                            case 126552: {
                                final int stat2 = round(1.4850000143 * procBudget);
                                final ProcHandler ph = new ProcHandler(new Proc_Single_Stat(procName, new ProcChance_Prob(0.15, 55000), 20000, FeralSim.STAT_AGI, stat2), false, new Source[] { Source.SWING, Source.MELEE, Source.SPELL });
                                this.addSpellOrigin(spellId, slot.type, ph);
                                this.addSelf(126554, ph);
                                break;
                            }
                            case 126599: {
                                final int stat2 = round(1.6499999762 * procBudget);
                                final Spell spell = new Spell_BuffCooldown_Stat_Shared(procName, 15000, 60000, FeralSim.STAT_HASTE, stat2);
                                this.addSpellOrigin(spellId, slot.type, spell);
                                break;
                            }
                            case 136086: {
                                final int stat2 = round(1.6499999762 * procBudget);
                                final Spell spell = new Spell_BuffCooldown_Stat_Shared(procName, 15000, 60000, FeralSim.STAT_CRIT, stat2);
                                this.addSpellOrigin(spellId, slot.type, spell);
                                break;
                            }
                            case 126650: {
                                final int stat2 = round(2.9700000286 * procBudget);
                                final ProcHandler ph = new ProcHandler(new Proc_Single_Stat(procName, new ProcChance_Prob(0.15, 115000), 20000, FeralSim.STAT_CRIT, stat2), false, new Source[] { Source.SWING, Source.MELEE, Source.SPELL });
                                this.addSpellOrigin(spellId, slot.type, ph);
                                this.addSelf(126649, ph);
                                break;
                            }
                            case 126690: {
                                final int stat2 = round(1.238499999 * procBudget);
                                final Spell spell = new Spell_BuffCooldown_Stat_Shared(procName, 20000, 60000, FeralSim.STAT_AGI, stat2);
                                this.addSpellOrigin(spellId, slot.type, spell);
                                break;
                            }
                            case 126708: {
                                final int stat2 = round(1.7480000257 * procBudget);
                                final ProcHandler ph = new ProcHandler(new Proc_Single_Stat(procName, new ProcChance_Prob(0.15, 55000), 20000, FeralSim.STAT_AGI, stat2), false, new Source[] { Source.SWING, Source.MELEE, Source.SPELL });
                                this.addSpellOrigin(spellId, slot.type, ph);
                                this.addSelf(126707, ph);
                                break;
                            }
                            case 127575: {
                                final int stat2 = round(2.4749999046 * procBudget);
                                final Spell spell = new Spell_BuffCooldown_Stat_Shared(procName, 20000, 120000, FeralSim.STAT_AGI, stat2);
                                this.addSpellOrigin(spellId, slot.type, spell);
                                break;
                            }
                            case 127926: {
                                final int stat2 = round(5.9439997673 * procBudget);
                                final ProcHandler ph = new ProcHandler(new Proc_Single_Stat(procName, new ProcChance_Prob(0.1, 55000), 10000, FeralSim.STAT_AP, stat2), true, new Source[] { Source.SWING, Source.MELEE });
                                this.addSpellOrigin(spellId, slot.type, ph);
                                this.addSelf(127928, ph);
                                break;
                            }
                            case 128445: {
                                final int stat2 = round(1.5684000254 * procBudget);
                                final ProcHandler ph = new ProcHandler(new Proc_Single_Stat(procName, new ProcChance_Prob(0.2, 55000), 15000, FeralSim.STAT_AGI, stat2), true, new Source[] { Source.SWING, Source.MELEE });
                                this.addSpellOrigin(spellId, slot.type, ph);
                                this.addSelf(128984, ph);
                                break;
                            }
                            case 129554: {
                                break;
                            }
                            case 137595: {
                                if (cfg.challengeMode) {
                                    break;
                                }
                                final double feralMod = 1.721;
                                final ProcHandler ph2 = new ProcHandler(new Proc_Occur("Lightning Strike", new ProcChance_RPPM(33.16367, 0, true)) {
                                    int stack;
                                    int count;
                                    
                                    @Override
                                    void toString(final StringBuilder sb) {
                                        sb.append(" to gain a stack of Capacitance.  Upon reaching 5 stacks, your stacks are consumed and you deal a Lightning Strike.");
                                    }
                                    
                                    @Override
                                    void reset() {
                                        super.reset();
                                        this.stack = 0;
                                        this.count = 0;
                                    }
                                    
                                    @Override
                                    void occured(final Enemy enemy_ignored, final String triggerName, final Source src, final double dmg_ignored, final boolean crit_ignored) {
                                        if (++this.stack < 5 && src != null) {
                                            return;
                                        }
                                        this.stack = 0;
                                        ++this.count;
                                        final Enemy enemy = FeralSim.this.target;
                                        final AttackT atk = FeralSim.this.yellow(enemy, FeralSim.this.getMeleeCrit(), false, false, false);
                                        String mods = null;
                                        if (FeralSim.this.printLog) {
                                            final StringBuilder sb = FeralSim.this.sb();
                                            FeralSim.this.appendProc_ap(sb);
                                            FeralSim.this.appendMulti(sb, FeralSim.this.allDmgMods.product);
                                            mods = sb.toString();
                                        }
                                        if (atk.hit) {
                                            double dmg = (280.0 + FeralSim.this.getAP() * 0.75) * FeralSim.this.allDmgMods.product * enemy.spellDmgMod();
                                            if (atk.crit) {
                                                dmg *= FeralSim.this.critDmgMods.product;
                                            }
                                            FeralSim.this.recordDamage(enemy, this.name, this.name, mods, atk, dmg);
                                            FeralSim.this.triggerProcs(enemy, this.name, Source.SPELL, dmg, atk.crit, 0);
                                        }
                                        else {
                                            FeralSim.this.logMiss(FeralSim.this.target, this.name, mods, atk);
                                        }
                                    }
                                }, false, new Source[] { Source.MELEE, Source.SWING });
                                this.addSpellOrigin(137595, SlotT.HEAD, ph2);
                                this.addSelf(137596, ph2);
                                break;
                            }
                            case 138939: {
                                final int stat2 = round(2.4749999046 * procBudget);
                                final ProcHandler ph = new ProcHandler(new Proc_Single_Stat(procName, new ProcChance_RPPM(1.1, 10000, false), 10000, FeralSim.STAT_AGI, stat2), false, new Source[] { Source.SWING, Source.MELEE, Source.SPELL, Source.DEBUFF });
                                this.addSpellOrigin(spellId, slot.type, ph);
                                this.addSelf(138938, ph);
                                break;
                            }
                            case 138757: {
                                final int stat2 = round(0.4499999881 * procBudget);
                                final ProcHandler ph = new ProcHandler(new Proc_Single_StatRamp_Up(procName, new ProcChance_RPPM(1.21, 10000, false), 1000, 10, FeralSim.STAT_AGI, stat2), false, new Source[] { Source.SWING, Source.MELEE, Source.SPELL, Source.DEBUFF });
                                this.addSpellOrigin(spellId, slot.type, ph);
                                this.addSelf(138756, ph);
                                break;
                            }
                            case 138894: {
                                final int stat2 = round(0.5189999938 * procBudget);
                                final ProcHandler ph = new ProcHandler(new Proc_Stacking_Stat(procName, new ProcChance_RPPM(3.5, 5000, false), 5, 10000, FeralSim.STAT_HASTE, stat2), false, new Source[] { Source.SWING, Source.MELEE, Source.SPELL, Source.DEBUFF });
                                this.addSpellOrigin(spellId, slot.type, ph);
                                this.addSelf(138895, ph);
                                break;
                            }
                            case 139116: {
                                final double rppm = 1.1 * ItemT.procMod(528, itemLevel);
                                this.trinket_rune = new Proc_Single_RoR(procName, new ProcChance_RPPM(rppm, 10000, false), 10000);
                                final ProcHandler ph2 = new ProcHandler(this.trinket_rune, false, new Source[] { Source.SWING, Source.MELEE, Source.DEBUFF, Source.SPELL });
                                this.addSpellOrigin(spellId, slot.type, ph2);
                                this.addSelf(139117, ph2);
                                this.addSelf(139120, ph2);
                                this.addSelf(139121, ph2);
                                break;
                            }
                            case 138700: {
                                final int stat2 = round(2.9699628 * procBudget);
                                final ProcHandler ph = new ProcHandler(new Proc_Single_Stat(procName, new ProcChance_Prob(0.15, 115000), 20000, FeralSim.STAT_AGI, stat2), false, new Source[] { Source.SWING, Source.MELEE, Source.SPELL });
                                this.addSpellOrigin(spellId, slot.type, ph);
                                this.addSelf(138699, ph);
                                break;
                            }
                            case 146019: {
                                final double crr = 0.39 * ItemT.procMod(553, itemLevel);
                                this.TF.reduceCooldown(crr);
                                this.BERSERK.reduceCooldown(crr);
                                this.addSpellOrigin(spellId, slot.type, String.format("Equip: Increases the cooldown recovery rate by %.1f%% for Tiger's Fury (%s) and Berserk (%s).", 100.0 * crr, Fmt.msDuration_precise(this.TF.cooldown), Fmt.msDuration_precise(this.BERSERK.cooldown)));
                                break;
                            }
                            case 146051: {
                                final double prod = 0.00354 * procBudget / 200.0;
                                critDamage *= 1.0 + prod;
                                final int key = slot.index * 1000 - 14;
                                this.statMods[FeralSim.STAT_MASTERY].add(key, 1.0 + prod);
                                this.statMods[FeralSim.STAT_HASTE].add(key, 1.0 + prod);
                                this.statMods[FeralSim.STAT_SPI].add(key, 1.0 + prod);
                                this.addSpellOrigin(spellId, slot.type, String.format("Equip: Amplifies your Critical Strike damage and healing, Haste, Mastery, and Spirit by %.2f%%.", prod * 100.0));
                                break;
                            }
                            case 146059: {
                                final double prob = 0.0354 * procBudget / 1000.0;
                                this.addSpellOrigin(spellId, slot.type, new ProcHandler(new Proc_Occur("Multistrike", new ProcChance_Prob(prob)) {
                                    @Override
                                    void toString(final StringBuilder sb) {
                                        sb.append(" to Multistrike, which deals instant additional damage to your target equal to 1/3 of the original damage dealt.");
                                    }
                                    
                                    @Override
                                    void occured(final Enemy enemy, final String triggerName, final Source src, final double dmg, final boolean crit) {
                                        final AttackT atk = src.melee ? FeralSim.this.yellow(enemy, 0.0, !enemy.isBehind(), false, false) : FeralSim.this.yellow_spell(enemy, 0.0);
                                        final String temp = FeralSim.this.printLog ? (this.name + "#" + triggerName) : this.name;
                                        if (atk.hit) {
                                            FeralSim.this.recordDamage(enemy, this.name, temp, "", AttackT.HIT, dmg / 3.0);
                                        }
                                        else {
                                            FeralSim.this.logMiss(enemy, temp, "", atk);
                                        }
                                    }
                                }, false, new Source[] { Source.SWING, Source.MELEE, Source.BLEED, Source.SPELL, Source.DOT }));
                                break;
                            }
                            case 146136: {
                                final double prob = 0.0786 * procBudget / 10000.0;
                                this.addSpellOrigin(spellId, slot.type, new ProcHandler(new Proc_Occur(procName, new ProcChance_Prob(prob)) {
                                    static final int NUM = 5;
                                    
                                    @Override
                                    void toString(final StringBuilder sb) {
                                        sb.append(" to Cleave, dealing the same damage to up to ");
                                        sb.append(5);
                                        sb.append(" other nearby targets.");
                                    }
                                    
                                    @Override
                                    void occured(final Enemy enemy, final String triggerName, final Source src, final double dmg, final boolean crit) {
                                        final FeralSim this$0 = FeralSim.this;
                                        ++this$0._loopingTargets;
                                        int num = 0;
                                        for (final Enemy e : FeralSim.this.activeTargets) {
                                            if (e == enemy) {
                                                continue;
                                            }
                                            if (num++ >= 5) {
                                                break;
                                            }
                                            FeralSim.this.recordDamage(e, "Cleave", FeralSim.this.printLog ? ("Cleave#" + enemy.name) : "Cleave", "", AttackT.HIT, dmg);
                                        }
                                        FeralSim.this._cleanTargets();
                                    }
                                }, false, new Source[] { Source.SWING, Source.MELEE, Source.BLEED, Source.SPELL, Source.DOT }));
                                break;
                            }
                            case 146195: {
                                if (cfg.challengeMode) {
                                    break;
                                }
                                final double rppm = 2.262;
                                final ProcHandler ph2 = new ProcHandler(new Proc_Occur("Flurry of Xuen", new ProcChance_RPPM(rppm, 3000, true)) {
                                    final String tickName = FeralSim.tickify(this.name);
                                    static final int TARGET_LIMIT = 5;
                                    static final int TICK_NUM = 10;
                                    int ticks = 0;
                                    
                                    @Override
                                    void toString(final StringBuilder sb) {
                                        sb.append(" to strike up to ");
                                        sb.append(5);
                                        sb.append(" targets in front of you, every 0.3 sec, for 3 sec.");
                                    }
                                    
                                    @Override
                                    void occured(final Enemy enemy, final String triggerName, final Source src, final double dmg, final boolean crit) {
                                        this.ticks = 10;
                                        this.startTick();
                                        if (FeralSim.this.printLog) {
                                            FeralSim.this.print("+%s", this.name);
                                        }
                                    }
                                    
                                    void startTick() {
                                        FeralSim.this.addEvent(new Event(FeralSim.this.clock + 300) {
                                            @Override
                                            void run() {
                                                final double crit = FeralSim.this.getMeleeCrit();
                                                final double critDmgMod = FeralSim.this.critDmgMods.product;
                                                final String mods = FeralSim.this.printLog ? FeralSim.this.physicalModStr() : null;
                                                double dmg0 = 1.0 + 0.2 * FeralSim.this.getAP();
                                                switch (FeralSim.this.form) {
                                                    case 1: {
                                                        dmg0 *= FeralSim.this.catDmgMod(false);
                                                        break;
                                                    }
                                                    case 2: {
                                                        dmg0 *= FeralSim.this.bearDmgMod(false);
                                                        break;
                                                    }
                                                    default: {
                                                        dmg0 *= FeralSim.this.allDmgMods.product;
                                                        break;
                                                    }
                                                }
                                                final FeralSim this$0 = FeralSim.this;
                                                ++this$0._loopingTargets;
                                                int num = 0;
                                                final String str = FeralSim.this.addNum(Proc_Occur.this.name, 11 - Proc_Occur.this.ticks);
                                                for (final Enemy enemy : FeralSim.this.activeTargets) {
                                                    if (num++ >= 5) {
                                                        break;
                                                    }
                                                    final AttackT atk = FeralSim.this.yellow(enemy, crit, true, false, false);
                                                    if (atk.hit) {
                                                        double dmg2 = enemy.meleeDmgMod() * dmg0;
                                                        if (atk.crit) {
                                                            dmg2 *= critDmgMod;
                                                        }
                                                        if (atk.block) {
                                                            dmg2 *= 0.7;
                                                        }
                                                        FeralSim.this.recordDamage(enemy, Proc_Occur.this.name, str, mods, atk, dmg2);
                                                        FeralSim.this.triggerProcs(enemy, Proc_Occur.this.name, Source.SPELL, dmg2, atk.crit, 0);
                                                    }
                                                    else {
                                                        FeralSim.this.logMiss(enemy, str, mods, atk);
                                                    }
                                                }
                                                FeralSim.this._cleanTargets();
                                                final Proc_Occur this$2 = Proc_Occur.this;
                                                if (--this$2.ticks > 0) {
                                                    Proc_Occur.this.startTick();
                                                }
                                                else if (FeralSim.this.printLog) {
                                                    FeralSim.this.print("-%s", Proc_Occur.this.name);
                                                }
                                            }
                                        });
                                    }
                                }, false, new Source[] { Source.SWING, Source.MELEE });
                                this.addSpellOrigin(spellId, slot.type, ph2);
                                this.addSelf(146194, ph2);
                                break;
                            }
                            case 146251: {
                                final int stat2 = round(2.9730000496 * procBudget);
                                final ProcHandler ph = new ProcHandler(new Proc_Single_Stat(procName, new ProcChance_Prob(0.15, 115000), 20000, FeralSim.STAT_STR, stat2), false, new Source[] { Source.SWING, Source.MELEE, Source.SPELL, Source.DEBUFF });
                                this.addSpellOrigin(spellId, slot.type, ph);
                                this.addSelf(146250, ph);
                                break;
                            }
                            case 146309: {
                                final int stat2 = round(2.9730000496 * procBudget);
                                final ProcHandler ph = new ProcHandler(new Proc_Single_Stat(procName, new ProcChance_Prob(0.15, 115000), 20000, FeralSim.STAT_AGI, stat2), false, new Source[] { Source.SWING, Source.MELEE, Source.SPELL, Source.DEBUFF });
                                this.addSpellOrigin(spellId, slot.type, ph);
                                this.addSelf(146308, ph);
                                break;
                            }
                            case 146311: {
                                final int stat2 = round(0.2703000009 * procBudget);
                                final ProcHandler ph = new ProcHandler(new Proc_Single_StatRamp_Down(procName, new ProcChance_RPPM(1.0, 10000, false), 500, 20, FeralSim.STAT_AGI, stat2), false, new Source[] { Source.SWING, Source.MELEE, Source.SPELL, Source.DEBUFF });
                                this.addSpellOrigin(spellId, slot.type, ph);
                                this.addSelf(146310, ph);
                                break;
                            }
                            case 146313: {
                                final int stat2 = round(2.9730000496 * procBudget);
                                final ProcHandler ph = new ProcHandler(new Proc_Single_Stat(procName, new ProcChance_Prob(0.15, 115000), 20000, FeralSim.STAT_MASTERY, stat2), false, new Source[] { Source.SWING, Source.MELEE, Source.SPELL, Source.DEBUFF });
                                this.addSpellOrigin(spellId, slot.type, ph);
                                this.addSelf(146312, ph);
                                break;
                            }
                            case 145430: {
                                break;
                            }
                            case 148446: {
                                final int stat2 = round(1.567999959 * procBudget);
                                final ProcHandler ph = new ProcHandler(new Proc_Single_Stat(procName, new ProcChance_Prob(0.2, 50000), 20000, FeralSim.STAT_HASTE, stat2), false, new Source[] { Source.SWING, Source.MELEE });
                                this.addSpellOrigin(spellId, slot.type, ph);
                                this.addSelf(148447, ph);
                                break;
                            }
                            case 148895: {
                                final int stat2 = round(2.9730000496 * procBudget);
                                final ProcHandler ph = new ProcHandler(new Proc_Single_Stat(procName, new ProcChance_Prob(0.15, 85000), 15000, FeralSim.STAT_AGI, stat2), false, new Source[] { Source.SWING, Source.MELEE, Source.SPELL, Source.DEBUFF });
                                this.addSpellOrigin(spellId, slot.type, ph);
                                this.addSelf(148896, ph);
                                break;
                            }
                            case 148904: {
                                final int stat2 = round(2.9730000496 * procBudget);
                                final ProcHandler ph = new ProcHandler(new Proc_Single_Stat(procName, new ProcChance_RPPM(0.92, 10000, false), 10000, FeralSim.STAT_AGI, stat2), false, new Source[] { Source.SWING, Source.MELEE, Source.SPELL, Source.DEBUFF });
                                this.addSpellOrigin(spellId, slot.type, ph);
                                this.addSelf(148903, ph);
                                break;
                            }
                            default: {
                                if (warnings != null) {
                                    warnings.add(String.format("Unknown spell for %s: %s (%d)", slot.type.name, item.name, spellId));
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (minorSpeed) {
            this.speed0 *= 1.08;
        }
        Collections.sort(this.onUseSpells, FeralSim.ORDER_CD_DECR);
        this.activeSpells.clear();
        this.activeSpells.addAll(this.spells);
        this.activeSpells.addAll(this.onUseSpells);
        this.activeSpells.addAll(this.raidSpells);
        final Spell_BuffCooldown heroSpell = this.heroSpell;
        final boolean enabled6 = cfg.heroism != Heroism.NONE;
        heroSpell.enabled = enabled6;
        if (enabled6) {
            this.activeSpells.add(this.heroSpell);
        }
        this._rampUpFreq = 0;
        for (final ProcHandler x : this.procs) {
            if (x.proc instanceof Proc_Single_StatRamp_Up) {
                this._rampUpFreq = Math.max(this._rampUpFreq, ((Proc_Single_StatRamp_Up)x.proc).freq);
            }
        }
        this.activeProcs.clear();
        this.activeProcs.addAll(this.procs);
        this.critDmgMods.clearAndSetIdentity(critEffect * (1.0 + critDamage));
        this.resetAll();
        this.combo = 5;
        this._crit0 = this.getMeleeCrit();
        this._ripTickDmg0 = this._ripTickDmg();
        this._rakeTickDmg0 = this._rakeTickDmg();
        this._treantRakeTickDmg0 = this._treantRakeTickDmg();
        this.combo = 0;
    }
    
    private void addSpellOrigin(final int spellId, final Object key, final Object spell) {
        if (key != null) {
            this.origins.add(new Origin(spellId, key, spell));
        }
        if (spell instanceof ProcHandler) {
            final ProcHandler ph = (ProcHandler)spell;
            this.procs.add(ph);
            if (ph.proc instanceof StatEffectOrigin) {
                this.proc_stats.add(((StatEffectOrigin)ph.proc).getStatEffect());
            }
        }
        else if (spell instanceof Spell_Cooldown) {
            this.onUseSpells.add((Spell_Cooldown)spell);
            if (spell instanceof StatEffectOrigin) {
                this.proc_stats.add(((StatEffectOrigin)spell).getStatEffect());
            }
        }
    }
    
    public Collection<IntPair<String>> getSpellDescriptions(final Object key, final boolean main) {
        final ArrayList<IntPair<String>> temp = new ArrayList<IntPair<String>>();
        final StringBuilder sb = this._sb;
        for (final Origin o : this.origins) {
            if (o.key == key) {
                String desc = null;
                final Object obj = o.spell;
                if (obj instanceof ProcHandler) {
                    final ProcHandler ph = (ProcHandler)obj;
                    if (ph instanceof ProcHandler_GearSpecific) {
                        final ProcHandler_GearSpecific gs = (ProcHandler_GearSpecific)ph;
                        if (gs.gear != (main ? this.mainGear : this.swapGear)) {
                            continue;
                        }
                    }
                    if (ph.proc instanceof Proc_Chance) {
                        final Proc_Chance proc = (Proc_Chance)ph.proc;
                        sb.setLength(0);
                        sb.append("Equip: ");
                        proc.chance.toString(sb);
                        sb.append(" on ");
                        boolean first = true;
                        for (final Source x : Source.values()) {
                            final int bit = x.bit();
                            if ((bit & ph.mask) == bit) {
                                if (first) {
                                    first = false;
                                }
                                else {
                                    sb.append('\u200b');
                                    sb.append('/');
                                    sb.append('\u200b');
                                }
                                sb.append(x.fancyName);
                            }
                        }
                        if (ph.critOnly) {
                            sb.append(" crits");
                        }
                        proc.toString(sb);
                        desc = sb.toString();
                    }
                }
                else if (obj instanceof Spell) {
                    if (obj instanceof Spell_BuffCooldown_Stat) {
                        final Spell_BuffCooldown_Stat spell = (Spell_BuffCooldown_Stat)obj;
                        desc = String.format("Use: +%d %s for %s. (%s %scooldown)", spell.eff.value, FeralSim.STATS[spell.eff.index].name, Fmt.msDuration(spell.dur), Fmt.msDuration(spell.cooldown), (spell instanceof Spell_BuffCooldown_Stat_Shared) ? "shared-" : "");
                    }
                    else if (obj instanceof Spell_EffectCooldown) {
                        final Spell_EffectCooldown spell2 = (Spell_EffectCooldown)obj;
                        sb.setLength(0);
                        sb.append("Use: ");
                        spell2.appendEffect(sb);
                        sb.append(" (");
                        sb.append(Fmt.msDuration(spell2.cooldown));
                        sb.append(" cooldown)");
                        desc = sb.toString();
                    }
                }
                else {
                    desc = obj.toString();
                }
                if (desc == null) {
                    continue;
                }
                temp.add(new IntPair<String>(o.spellId, desc));
            }
        }
        return temp;
    }
    
    static String fmt_scaledProc(final String name, final int itemLevel) {
        return String.format("%s<%d>", name, itemLevel);
    }
    
    String procResetTime() {
        if (this.cfg.procResetTime == -1) {
            return "Standard";
        }
        if (this.cfg.procResetTime == -2) {
            return "Random";
        }
        return Fmt.msDur(this.cfg.procResetTime);
    }
    
    static String toStringOrNone(final Object x) {
        return (x == null) ? "None" : x.toString();
    }
    
    void dumpFeatures(final LineWriter lw) {
        lw.add("[Combat Preparation]");
        lw.add("    Pre-Potion: " + toStringOrNone(this.cfg.pre_pot));
        lw.add("   Savage Roar: " + (this.cfg.pre_sr && this.spec.glyph_sr));
        if (this.spec.talent_doc) {
            lw.add(" Healing Touch: " + this.cfg.pre_ht);
        }
        lw.add("    Proc Reset: " + this.procResetTime());
        lw.add("        Opener: " + this.cfg.opener);
        lw.add("     Finisher0: " + this.cfg.finisher0);
        lw.add("       Energy0: " + this.cfg.energy0);
        lw.add();
        lw.add("[Temp Effects]");
        lw.add("       Heroism: " + this.cfg.heroismDesc());
        lw.add("    Berserking: " + this.BERSERKING.enabled);
        lw.add("     Lifeblood: " + this.LIFEBLOOD.enabled);
        lw.add("     Stormlash: " + this.cfg.num_stormlashTotem);
        lw.add("  Skull Banner: " + this.cfg.num_skullBanner);
        lw.add("    Shattering: " + this.cfg.num_shatteringThrow);
        lw.add("        Tricks: " + this.cfg.num_tricksOfTheTrade);
        lw.add(" Unholy Frenzy: " + this.cfg.num_unholyFrenzy);
        lw.add();
        lw.add("[Consumables]");
        lw.add("         Flask: " + this.cfg.flask.toString(this.mixology));
        lw.add("          Food: " + toStringOrNone(this.cfg.food));
        lw.add("        Potion: " + toStringOrNone(this.cfg.pot));
        lw.add();
        lw.add("[Rotation]");
        lw.add("  Action Delay: " + this.cfg.delay);
        lw.add(" Reaction Time: " + this.cfg.react);
        lw.add("     Generator: " + this.cfg.generator);
        lw.add("     Symbiosis: " + this.cfg.symbiosis);
        if (this.spec.talent_hotw) {
            lw.add();
            lw.add("[Heart of the Wild Options]");
            lw.add("    Wrath Enabled: " + this.cfg.hotw_wrath);
            lw.add("Hurricane Enabled: " + this.cfg.hotw_hurricane);
            lw.add("      Swap Weapon: " + this.cfg.hotw_swap);
            lw.add("     Maintain Rip: " + this.cfg.hotw_bitw);
            lw.add("   Before Berserk: " + this.cfg.hotw_beforeBerserk);
        }
        lw.add();
        lw.add("[Features]");
        lw.add("      Challenge Mode: " + this.cfg.challengeMode);
        lw.add("      Disabled Crits: " + this.disable_crit);
        lw.add("Disabled Primal Fury: " + this.disable_primalFury);
        lw.add("       Disabled LotP: " + !this.cfg.enable_lotp);
        lw.add("   Disabled Glancing: " + this.disable_glancing);
        lw.add("Disabled Miss Refund: " + !this.cfg.enable_refund);
        lw.add("  Disabled Clearcast: " + !this.cfg.enable_ooc);
        lw.add("    Disabled Mastery: " + this.disable_mastery);
        lw.add(" Disabled Armor Spec: " + this.disable_armorSpec);
        if (this.spec.talent_fon) {
            lw.add("          Retard FoN: " + this.cfg.enable_retardFoN);
        }
        lw.add("    Disabled Berserk: " + this.disable_berserk);
        lw.add("   TF during Berserk: " + this.cfg.enable_tfDuringBerserk);
        lw.add("    MoP Berserk Perk: " + this.enable_berserkPerk);
        lw.add("  Prevent 0-Combo PS: " + !this.cfg.enable_0comboPS);
        lw.add(" Disabled Rip Extend: " + this.disable_ripExtend);
        lw.add("   Proper Rip Extend: " + this.cfg.enable_properRipExtend);
        lw.add("    Local Rip Extend: " + this.cfg.enable_localRipExtend);
        lw.add("     BitW Percentage: " + (this.disable_bitw ? "Disabled" : String.format("%.2f%%", this.bitw_perc * 100.0)));
        lw.add("    Disabled Racials: " + this.disable_racials);
        lw.add("     Use WoD Racials: " + this.enable_wodRacials);
        lw.add("         Inert Stats: " + this.cfg.extra_inert);
        lw.add("        Active Stats: " + this.cfg.extra_active);
    }
    
    void dumpTalentsGlyphsBonuses(final LineWriter lw) {
        lw.add("[Talents/Glyphs/Bonuses]");
        if (this.spec.talent_sotf) {
            lw.add("Soul of the Forest");
        }
        if (this.spec.talent_kotj) {
            lw.add("Incarnation");
        }
        if (this.spec.talent_fon) {
            lw.add("Force of Nature");
        }
        if (this.spec.talent_hotw) {
            lw.add("Heart of the Wild");
        }
        if (this.spec.talent_doc) {
            lw.add("Dream of Cenarius");
        }
        if (this.spec.talent_nv) {
            lw.add("Nature's Vigil");
        }
        if (this.spec.glyph_catForm) {
            lw.add("Glyph of Cat Form");
        }
        if (this.spec.glyph_sr) {
            lw.add("Glyph of Savagery");
        }
        if (this.spec.glyph_shred) {
            lw.add("Glyph of Shred");
        }
        if (this.spec.glyph_fb) {
            lw.add("Glyph of Ferocious Bite");
        }
        if (this.bonus_t13_2p) {
            lw.add("Tier 13: 2p");
        }
        if (this.bonus_t13_4p) {
            lw.add("Tier 13: 4p");
        }
        if (this.bonus_t14_2p) {
            lw.add("Tier 14: 2p");
        }
        if (this.bonus_t14_4p) {
            lw.add("Tier 14: 4p");
        }
        if (this.bonus_t15_2p) {
            lw.add("Tier 15: 2p");
        }
        if (this.bonus_t15_4p) {
            lw.add("Tier 15: 4p");
        }
        if (this.bonus_t16_2p) {
            lw.add("Tier 16: 2p");
        }
        if (this.bonus_t16_4p) {
            lw.add("Tier 16: 4p");
        }
        if (this.bonus_t17_2p_old) {
            lw.add("Tier 17: 2p (Shred reduces Berserk)");
        }
        if (this.bonus_t17_2p) {
            lw.add("Tier 17: 2p (Energy on Bleed)");
        }
        if (this.bonus_t17_4p) {
            lw.add("Tier 17: 4p");
        }
        if (this.bonus_pvpRavage) {
            lw.add("PvP: Ravage");
        }
        if (this.bonus_wodpvp_2p) {
            lw.add("WoD PvP: 2pc (TF Reset)");
        }
        if (this.bonus_wodpvp_4p) {
            lw.add("WoD PvP: 4pc (Bloodletting)");
        }
    }
    
    void dumpStats(final LineWriter lw) {
        lw.add("[Stats]");
        lw.add("AP          = %8d", this.getAP());
        lw.add("SP          = %8d", this.getSP());
        lw.add("SP-Nature   = %8d", this.getSP_nature());
        lw.add("Str         = %8d", this.getStr());
        lw.add("Agi         = %8d", this.getAgi());
        lw.add("Int         = %8d", this.getInt());
        lw.add("Melee Crit  = %7.2f%% (%d)", 100.0 * this.getMeleeCrit(), this.getCritRating());
        lw.add("Melee Haste = %7.2f%% (%d)", 100.0 * (this.getMeleeHasteMod() - 1.0), this.getHasteRating());
        lw.add("Spell Crit  = %7.2f%%", 100.0 * this.getSpellCrit());
        lw.add("Spell Haste = %7.2f%%", 100.0 * (this.getSpellHasteMod() - 1.0));
        lw.add("Mastery     = %7.2f%% (%d)", 100.0 * (this.getRazorClawsMod() - 1.0), this.getMasteryRating());
        lw.add("Hit         = %7.2f%% (%d)", 100.0 * this.getMeleeHit(), this.getMeleeHitRating());
        lw.add("Exp         = %7.2f%% (%d)", 100.0 * this.getMeleeExp(), this.getMeleeExpRating());
    }
    
    void addStealthOpener(final Spell spell) {
        this.enterStealth();
        this.pushLogic(new Logic() {
            public void run() {
                try {
                    spell.click();
                }
                catch (Clicked ex) {
                    FeralSim.this.popLogic();
                }
            }
        });
    }
    
    void fireStatChange() {
        if (this._statChangedScheduled) {
            return;
        }
        this._statChangedScheduled = true;
        this.addEvent(new Event(this.clock + this._reactTime()) {
            public void run() {
                FeralSim.this._statChangedScheduled = false;
                if (FeralSim.this.printDebug) {
                    FeralSim.this.print("Event: Stat Changed");
                }
                FeralSim.this._tryFoN();
            }
        });
    }
    
    void _tryFoN() {
        if (this.FON.enabled && this.form > 0 && this.fon_charges > 0 && this.autoattack) {
            if (this.fon_charges == 3) {
                this.FON.click();
            }
            else {
                final double dmg0 = this.treantRakeTickDmgNew();
                if (dmg0 > this._treantRakeThresDmg) {
                    final double dmg2 = this.treantRakeTickDmgFuture(500);
                    if (this.printDebug) {
                        this.print("FoN Projection: %.0f -> %.0f", dmg0, dmg2);
                    }
                    boolean use = dmg2 < dmg0;
                    if (use && this._rampUpFreq > 500 && this.treantRakeTickDmgFutureBetter(5000, dmg0)) {
                        use = false;
                    }
                    if (use) {
                        while (this.fon_charges > 0) {
                            this.FON.click();
                        }
                        this.removeEvent(this.fonMonitor, false);
                    }
                    else {
                        this.addEvent(new Event(this.clock + 500, this.fonMonitor) {
                            @Override
                            void run() {
                                FeralSim.this._tryFoN();
                            }
                        });
                    }
                }
            }
        }
    }
    
    void prepareForCombat() {
        this.activeTargets.clear();
        this.activeTargets.addAll(this.targets);
        this.target = this.activeTargets.get(0);
        this._loopingTargets = 0;
        this._dirtyTargets = false;
        this.resetAll();
        this.energyMax = 100;
        this.energy = this.cfg.energy0;
        this.mana = 60000;
        this.rage = 0;
        if (this.printLog) {
            this.print("Starting Energy: %d/%d", this.energy, this.energyMax);
        }
        this.clearFuture();
        this.main_logic.reset();
        this.pushLogic(this.main_logic);
        if (this.spec.talent_doc && this.cfg.pre_ht) {
            if (this.printLog) {
                this.print(FeralSim.playerPrefix + this.HT.actionName);
            }
            this.apply_doc();
        }
        if (this.spec.glyph_sr && this.cfg.pre_sr) {
            final int time = this.timeToGainBackIfCast(this.SR);
            this._srApply(this._srDur(0) + this._srRollover() - time);
            this.sr_clockAlign = -time;
        }
        this.pot_click();
        switch (this.cfg.opener) {
            case ADVANCED: {
                if (this.bonus_t16_4p && this.spec.glyph_sr) {
                    this.enterStealth();
                    this.TF.clickResult();
                    this.SR.clickResult();
                    this.ff_ready = 5000;
                    this.addStealthOpener(this.RAVAGE);
                    break;
                }
                break;
            }
            case RAVAGE: {
                this.addStealthOpener(this.RAVAGE);
                break;
            }
            case POUNCE: {
                this.addStealthOpener(this.POUNCE);
                break;
            }
        }
        this.startEnergyRegen();
        if (this.printLog) {
            this.monitorDPS(15000);
        }
        for (final Runnable r : this.prepList) {
            r.run();
        }
        boolean alignedRaidSpells = false;
        if (this.cfg.heroism == Heroism.AFTER) {
            if (this.cfg.heroismDelay == null) {
                this.heroSpell.click();
            }
            else {
                final int t = this.cfg.heroismDelay.generate(this.rng);
                if (t < 30000) {
                    alignedRaidSpells = true;
                    this.addEvent(new Event(t) {
                        public void run() {
                            FeralSim.this.heroSpell.click();
                            FeralSim.this.doRaidSpells();
                        }
                    });
                }
                else {
                    this.addEvent(new Event(t) {
                        public void run() {
                            FeralSim.this.heroSpell.click();
                        }
                    });
                }
            }
        }
        if (!alignedRaidSpells) {
            this.doRaidSpells();
        }
        this._enterCombat();
        this.sampleDPS(5000);
    }
    
    void doRaidSpells() {
        int next = this.clock + 60000;
        for (final Spell_BuffCooldown x : this.raidSpells) {
            x.click();
            next = Math.min(next, x.ready);
        }
        this.addEvent(new Event(next) {
            @Override
            void run() {
                if (FeralSim.this.idle_depth == 0) {
                    FeralSim.this.doRaidSpells();
                }
            }
        });
    }
    
    void _enterCombat() {
        if (!this.combat) {
            this.combat = true;
            if (this.printLog) {
                this.print("*** Combat ***");
            }
        }
    }
    
    void createTimer(final int delay, final Runnable r, final boolean runNow) {
        if (runNow) {
            r.run();
        }
        this.addEvent(new Event(this.clock + delay) {
            @Override
            void run() {
                r.run();
                FeralSim.this.createTimer(delay, r, false);
            }
        });
    }
    
    void sampleDPS(final int delay) {
        if (this.cfg.heroism == Heroism.EXECUTE && (this.target.bitw() || (this.target.ttd > 0 && this.target.ttd < 60000))) {
            this.HEROISM.click();
        }
        this.addEvent(new Event(this.clock + delay) {
            @Override
            void run() {
                for (final Enemy enemy : FeralSim.this.activeTargets) {
                    enemy.updateDPS();
                }
                FeralSim.this.sampleDPS(delay);
            }
        });
    }
    
    void handleNextEvent() {
        int i = this.eventIndex(0);
        Event e = this.eventStack[i];
        if (e.t > this.clock) {
            if (this.castingKey.exists) {
                if (this.castingExpr != null && this.castingExpr.getBoolean()) {
                    this.castingExpr = null;
                    this.stopCasting();
                }
            }
            else {
                if (!this.lockoutKey.exists) {
                    while (true) {
                        try {
                            this.top.run();
                        }
                        catch (Clicked ex) {
                            if (!this.lockoutKey.exists) {
                                continue;
                            }
                            if (this.queuedMainSwap) {
                                this.queuedMainSwap = false;
                                this.mainGear.equip();
                            }
                        }
                        break;
                    }
                }
                if (!this.swingingKey.exists && this.autoattack && !this.castingKey.exists) {
                    this.autoAttack();
                    this.startSwing();
                }
            }
            i = this.eventIndex(0);
            e = this.eventStack[i];
        }
        this.clock = e.t;
        if (this.clock > this.clockEnd) {
            this.clock = this.clockEnd;
            throw new EndOfCombat();
        }
        this.eventStack[i] = null;
        ++this.eventStart;
        --this.eventCount;
        if (e.key != null) {
            e.key.exists = false;
        }
        e.run();
        e.faded(true);
    }
    
    void monitorDPS(final int delay) {
        this.addEvent(new Event(this.clock + delay) {
            @Override
            void run() {
                for (final Enemy enemy : FeralSim.this.activeTargets) {
                    final int ttd = enemy.ttd;
                    if (enemy.monitor && ttd > 0) {
                        FeralSim.this.print("Expect death: %s (%.1f%%) @ %s <%s>", enemy.name, 100.0 * enemy.percHP(), Fmt.msms(FeralSim.this.clock + ttd), Fmt.msDur(ttd));
                    }
                }
                FeralSim.this.monitorDPS(delay);
            }
        });
    }
    
    Proc getFirstProcByOriginWithUptime(final Object key) {
        for (final Origin x : this.origins) {
            if (x.key == key && x.spell instanceof ProcHandler) {
                final Proc proc = ((ProcHandler)x.spell).proc;
                if (proc instanceof Proc_Duration) {
                    return proc;
                }
                continue;
            }
        }
        return null;
    }
    
    TrinketResults trinketSim(final int runLen) {
        this.saveDamage = false;
        this.disable_print();
        final Proc t1 = this.getFirstProcByOriginWithUptime(SlotT.TRINKET_1);
        final Proc t2 = this.getFirstProcByOriginWithUptime(SlotT.TRINKET_2);
        final double[] dps = new double[runLen];
        final double[] t1_up = new double[runLen];
        final double[] t2_up = new double[runLen];
        for (int run = 0; run < runLen; ++run) {
            this.runSim();
            dps[run] = this.damage_total * 1000.0 / this.clock;
            t1_up[run] = ((t1 == null) ? Double.NaN : (t1.getUptime() / (double)this.clock));
            t2_up[run] = ((t2 == null) ? Double.NaN : (t2.getUptime() / (double)this.clock));
        }
        return new TrinketResults(dps, t1_up, t2_up);
    }
    
    public void setupCombat(final Encounter_Cleave en) {
        this.minCleaveSize = en.minCleaveSize;
        final Enemy e = new Enemy("Boss", 3, 10000000, true, 1.0, true, true, true, 1.0);
        this.targets.add(e);
        if (en.adds > 0) {
            if (en.freq != null) {
                this.prepList.add(new Runnable() {
                    int waveNum;
                    final Runnable task = new Runnable() {
                        @Override
                        public void run() {
                            final int wave = ++Runnable.this.waveNum;
                            for (int i = 0; i < en.adds; ++i) {
                                final Enemy add = new Enemy(FeralSim.this.printLog ? String.format("W%02d#%02d", wave, i + 1) : "Add", en.levelDelta, (en.health > 0) ? en.health : -1, false, 1.0 - en.frontProb, true, true, true, 1.0);
                                add.reset();
                                if (FeralSim.this.printLog) {
                                    FeralSim.this.print(">Spawned: " + add.name);
                                }
                                FeralSim.this.activeTargets.add(add);
                                if (en.lifetime != null) {
                                    FeralSim.this.addEvent(new Event(FeralSim.this.clock + en.lifetime.generate(FeralSim.this.rng)) {
                                        public void run() {
                                            FeralSim.this.activeTargets.remove(add);
                                            add.death = FeralSim.this.clock;
                                            if (FeralSim.this.printLog) {
                                                FeralSim.this.print(">Seppuku: " + add.name);
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    };
                    
                    @Override
                    public void run() {
                        this.waveNum = 0;
                        if (en.at_start) {
                            this.task.run();
                        }
                        this.sched();
                    }
                    
                    void sched() {
                        FeralSim.this.addEvent(new Event(FeralSim.this.clock + en.freq.generate(FeralSim.this.rng)) {
                            public void run() {
                                Runnable.this.task.run();
                                Runnable.this.sched();
                            }
                        });
                    }
                });
            }
            else if (en.at_start) {
                for (int i = 0; i < en.adds; ++i) {
                    final Enemy add = new Enemy(String.format("Add%02d", i + 1), en.levelDelta, (en.health > 0) ? en.health : -1, false, 1.0 - en.frontProb, true, true, true, 1.0);
                    this.targets.add(add);
                    if (en.lifetime != null) {
                        this.prepList.add(new Runnable() {
                            @Override
                            public void run() {
                                FeralSim.this.addEvent(new Event(FeralSim.this.clock + en.lifetime.generate(FeralSim.this.rng)) {
                                    public void run() {
                                        FeralSim.this.activeTargets.remove(add);
                                        add.death = FeralSim.this.clock;
                                        if (FeralSim.this.printLog) {
                                            FeralSim.this.print(">Seppuku: " + add.name);
                                        }
                                    }
                                });
                            }
                        });
                    }
                }
            }
        }
        if (en.fixed) {
            e.maxHP = 0L;
            this.clockEnd = en.duration;
        }
        else {
            e.onDeath_endOfCombat = true;
            this.adjustHP(e, 25, en.duration, 0.0);
            this.adjustHP(e, 50, en.duration, 0.0);
        }
    }
    
    private void resetEncounter() {
        this.saveDamage = false;
        this.printLog = false;
        this.prepList.clear();
        this.postList.clear();
        this.targets.clear();
        this.clockEnd = 3600000;
    }
    
    private void goIdle(final String name, final int dur) {
        ++this.idle_depth;
        this.pushLogic(this.idle_logic);
        this.stopCasting();
        this.autoattack = false;
        if (this.printLog) {
            this.print("*** %s: %s ***", name, Fmt.msDur(dur));
        }
        this.idle_expires = Math.max(this.idle_expires, this.clock + dur);
        this.addEvent(new Event(this.clock + dur) {
            @Override
            void run() {
                final FeralSim this$0 = FeralSim.this;
                final int idle_depth = this$0.idle_depth - 1;
                this$0.idle_depth = idle_depth;
                if (idle_depth == 0) {
                    FeralSim.this.ensureClockFrozen();
                    FeralSim.this.autoattack = true;
                }
                if (FeralSim.this.printLog) {
                    FeralSim.this.print("*** End of %s ***", name);
                }
            }
        });
    }
    
    private void schedulePrintSuffer() {
        this.addEvent(new Event(this.clock, this.sufferKey) {
            @Override
            void run() {
                if (FeralSim.this.allSufferMods.product > 0.0) {
                    FeralSim.this.print("*** Suffer: %.2f%% Damage ***", 100.0 * FeralSim.this.allSufferMods.product);
                }
                else {
                    FeralSim.this.print("*** Suffer: Immune ***");
                }
            }
        });
    }
    
    private void setupEncounterBasics(final Encounter en) {
        if (en.cooldownDelay != null) {
            this.prepList.add(new Runnable() {
                @Override
                public void run() {
                    final int cd = en.cooldownDelay.generate(FeralSim.this.rng);
                    if (FeralSim.this.printLog) {
                        FeralSim.this.print("*** Cooldown Delay: %s ***", Fmt.msDur(cd));
                    }
                    if (cd < FeralSim.this.TF.cooldown) {
                        FeralSim.this.TF.beginCooldown(cd);
                    }
                    if (cd < FeralSim.this.SYNAPSE_SPRINGS.cooldown) {
                        FeralSim.this.SYNAPSE_SPRINGS.beginCooldown(cd);
                    }
                    FeralSim.this.BERSERK.beginCooldown(cd);
                    FeralSim.this.NV.beginCooldown(cd);
                    FeralSim.this.HOTW.beginCooldown(cd);
                    FeralSim.this.KOTJ.beginCooldown(cd);
                }
            });
        }
        if (en.periodicIdle_freq != null && en.periodicIdle_time != null) {
            this.prepList.add(new Runnable() {
                int index;
                
                @Override
                public void run() {
                    this.index = 0;
                    this.sched();
                }
                
                void sched() {
                    if (en.periodicIdle_limit > 0 && this.index >= en.periodicIdle_limit) {
                        return;
                    }
                    FeralSim.this.addEvent(new Event(FeralSim.this.clock + en.periodicIdle_freq.generate(FeralSim.this.rng)) {
                        public void run() {
                            final Runnable this$1 = Runnable.this;
                            ++this$1.index;
                            FeralSim.this.goIdle(FeralSim.this.printLog ? ("Intermission#" + Runnable.this.index) : null, en.periodicIdle_time.generate(FeralSim.this.rng));
                            Runnable.this.sched();
                        }
                    });
                }
            });
        }
        if (en.periodicCast_freq != null) {
            this.prepList.add(new Runnable() {
                int index;
                
                @Override
                public void run() {
                    this.index = 0;
                    this.sched();
                }
                
                void sched() {
                    final int castTime = 2000;
                    FeralSim.this.addEvent(new Event(FeralSim.this.clock + Math.max(2000, en.periodicCast_freq.generate(FeralSim.this.rng))) {
                        public void run() {
                            final Enemy enemy = FeralSim.this.target;
                            if (enemy != null) {
                                final Runnable this$1 = Runnable.this;
                                ++this$1.index;
                                if (FeralSim.this.printLog) {
                                    FeralSim.this.print(enemy.logPrefix + "Casting...");
                                }
                                enemy.casting = true;
                                FeralSim.this.addEvent(new Event(FeralSim.this.clock + 2000) {
                                    public void run() {
                                        if (enemy.isDead()) {
                                            return;
                                        }
                                        if (FeralSim.this.printLog && enemy.casting) {
                                            FeralSim.this.print(enemy.logPrefix + "Cast Complete");
                                        }
                                        enemy.casting = false;
                                    }
                                });
                            }
                            Runnable.this.sched();
                        }
                    });
                }
            });
        }
        if (en.periodicDmgMod != 0.0) {
            this.prepList.add(new Runnable() {
                int stackCount;
                
                @Override
                public void run() {
                    this.stackCount = 0;
                    if (en.periodicDmg_freq0 != null) {
                        this.sched(en.periodicDmg_freq0);
                    }
                    else {
                        this.sched(en.periodicDmg_freq);
                    }
                }
                
                int getStacks() {
                    return this.stackCount;
                }
                
                void update(final int delta) {
                    final double oldMod = Math.max(-1.0, en.periodicDmgMod * this.getStacks());
                    this.stackCount += delta;
                    final int stacks = this.getStacks();
                    final double newMod = Math.max(-1.0, en.periodicDmgMod * stacks);
                    FeralSim.this.allDmgMods.add(-13, 1.0 + newMod);
                    if (FeralSim.this.printLog) {
                        FeralSim.this.print("*** Damage Modifier: %+.1f%% -> %+.1f%% [%d] *** ", oldMod * 100.0, newMod * 100.0, stacks);
                    }
                }
                
                void apply() {
                    this.update(1);
                    if (en.periodicDmg_time != null) {
                        FeralSim.this.addEvent(new Event(FeralSim.this.clock + en.periodicDmg_time.generate(FeralSim.this.rng)) {
                            @Override
                            void run() {
                                Runnable.this.update(-1);
                            }
                        });
                    }
                }
                
                void sched(final IntDist freq) {
                    if (freq == null) {
                        return;
                    }
                    FeralSim.this.addEvent(new Event(FeralSim.this.clock + freq.generate(FeralSim.this.rng)) {
                        public void run() {
                            Runnable.this.apply();
                            Runnable.this.sched(en.periodicDmg_freq);
                        }
                    });
                }
            });
        }
        if (en.commands != null) {
            try {
                for (final Encounter.Command cmd : en.commands) {
                    final int offset = cmd.time;
                    if (cmd.name.equalsIgnoreCase("wait")) {
                        final int dur = InvFmt.parse_time(cmd.map.get("dur"), "Wait Duration (dur)", 1000, false);
                        this.prepList.add(new Runnable() {
                            @Override
                            public void run() {
                                FeralSim.this.addEvent(new Event(offset) {
                                    @Override
                                    void run() {
                                        FeralSim.this.goIdle("Wait", dur);
                                    }
                                });
                            }
                        });
                    }
                    else if (cmd.name.equalsIgnoreCase("potion")) {
                        if (this.cfg.pot == null) {
                            throw new RuntimeException(String.format("A potion is not currently selected but it is scripted to be used at %s.", Fmt.mm_ss(offset)));
                        }
                        this.prepList.add(new Runnable() {
                            @Override
                            public void run() {
                                FeralSim.this.addEvent(new Event(offset) {
                                    @Override
                                    void run() {
                                        FeralSim.this.pot_click();
                                    }
                                });
                            }
                        });
                    }
                    else if (cmd.name.equalsIgnoreCase("hero")) {
                        if (!this.heroSpell.enabled) {
                            throw new RuntimeException(String.format("%s is not currently enabled but it is scripted to be cast at %s.", this.heroSpell.actionName, Fmt.mm_ss(offset)));
                        }
                        this.prepList.add(new Runnable() {
                            @Override
                            public void run() {
                                FeralSim.this.addEvent(new Event(offset) {
                                    @Override
                                    void run() {
                                        FeralSim.this.HEROISM.click();
                                    }
                                });
                            }
                        });
                    }
                    else if (cmd.name.equalsIgnoreCase("pool")) {
                        final int dur = InvFmt.parse_time(cmd.map.get("dur"), "Pool Duration (dur)", 1000, false);
                        this.prepList.add(new Runnable() {
                            @Override
                            public void run() {
                                FeralSim.this.addEvent(new Event(offset) {
                                    @Override
                                    void run() {
                                        FeralSim.this._startLockout_pool(dur);
                                    }
                                });
                            }
                        });
                    }
                    else if (cmd.name.equalsIgnoreCase("suffer")) {
                        final int dur = InvFmt.parse_time(cmd.map.get("dur"), "Suffer Duration (dur)", 1000, true);
                        final double mod = InvFmt.parse_multi(cmd.map.get("mod"), "Suffer Modifier (mod)", false);
                        final int unique = ++this.allSufferMods.unique;
                        this.prepList.add(new Runnable() {
                            @Override
                            public void run() {
                                FeralSim.this.addEvent(new Event(offset) {
                                    @Override
                                    void run() {
                                        FeralSim.this.allSufferMods.add(unique, mod);
                                        if (FeralSim.this.printLog) {
                                            FeralSim.this.schedulePrintSuffer();
                                        }
                                        if (dur > 0) {
                                            FeralSim.this.addEvent(new Event(FeralSim.this.clock + dur) {
                                                @Override
                                                void run() {
                                                    Event.this.fade();
                                                }
                                            });
                                        }
                                    }
                                    
                                    void fade() {
                                        FeralSim.this.allSufferMods.remove(unique);
                                        if (FeralSim.this.printLog) {
                                            FeralSim.this.schedulePrintSuffer();
                                        }
                                    }
                                });
                            }
                        });
                    }
                }
            }
            catch (RuntimeException err) {
                throw new RuntimeException("Encounter Script: " + err.getMessage());
            }
        }
    }
    
    public void setupCombat() {
        if (this.spec.talent_fon) {
            this.optimizeFoN();
        }
        this.setupPhase = true;
        this.resetEncounter();
        if (this.encounter != null) {
            this.setupEncounterBasics(this.encounter);
        }
        if (this.encounter instanceof Encounter_Patchwerk) {
            this.setupCombat((Encounter_Patchwerk)this.encounter);
        }
        else if (this.encounter instanceof Encounter_Cleave) {
            this.setupCombat((Encounter_Cleave)this.encounter);
        }
        else if (this.encounter instanceof Encounter_Sequence) {
            this.setupCombat((Encounter_Sequence)this.encounter);
        }
        else if (this.encounter instanceof Encounter_Mirror) {
            this.setupCombat((Encounter_Mirror)this.encounter);
        }
        this.setupPhase = false;
        this.resetAll();
    }
    
    public void setupCombat(final Encounter_Mirror en) {
        final Enemy enemy = new Enemy("Boss", 3, 10000000, true, 1.0, true, true, true, 1.0);
        this.targets.add(enemy);
        enemy.maxHP = -en.duration;
        enemy.minHP = 0L;
        enemy.immuneToDamage = true;
        this.clockEnd = en.duration;
        final HashMap<Integer, Spell> extMap = new HashMap<Integer, Spell>();
        extMap.put(2825, this.HEROISM);
        extMap.put(32182, this.HEROISM);
        extMap.put(90355, this.HEROISM);
        extMap.put(80353, this.HEROISM);
        extMap.put(146555, this.DRUMS_OF_RAGE);
        extMap.put(120676, new Spell_Stormlash(10000, 0));
        extMap.put(114207, new Spell_SkullBanner(10000, 0));
        extMap.put(57933, new Spell_TricksOfTheTrade(5000, 0));
        extMap.put(64382, new Spell_ShatteringThrow(10000, 0));
        extMap.put(113746, new Spell("Weakened Armor") {
            @Override
            boolean casted() {
                if (enemy.armorStacks < 3) {
                    final Enemy val$enemy = enemy;
                    ++val$enemy.armorStacks;
                }
                enemy.armor.add(-2, 1.0 - 0.04 * enemy.armorStacks);
                if (FeralSim.this.printLog) {
                    FeralSim.this.print("%c%s (%d)", (enemy.armorStacks > 1) ? '*' : '+', this.actionName, enemy.armorStacks);
                }
                return true;
            }
        });
        extMap.put(-113746, new Spell("Weakened Armor") {
            @Override
            boolean casted() {
                final int old = enemy.armorStacks;
                enemy.armorStacks = 0;
                enemy.armor.remove(-2);
                if (FeralSim.this.printLog) {
                    FeralSim.this.print("-%s (%d)", this.actionName, old);
                }
                return true;
            }
        });
        if (en.enabled.contains(Encounter_Mirror.Type.SELF)) {
            for (final Object x : this.selfMap.values()) {
                this.procs.remove(x);
                this.onUseSpells.remove(x);
            }
        }
        if (en.enabled.contains(Encounter_Mirror.Type.EXTERNAL)) {
            this.HEROISM.enabled = true;
            this.DRUMS_OF_RAGE.enabled = true;
            this.cfg.heroism = null;
            this.FF.enabled = false;
            this.cfg.debuff_armor = WeakenedArmor.NEVER;
            this.raidSpells.clear();
        }
        this.prepList.add(new Runnable() {
            int index;
            
            @Override
            public void run() {
                for (final Spell x : extMap.values()) {
                    x.reset();
                }
                this.index = 0;
                this.next();
            }
            
            void next() {
                if (this.index >= en.triggers.length) {
                    return;
                }
                FeralSim.this.addEvent(new Event(en.triggers[this.index].time) {
                    @Override
                    void run() {
                        Runnable.this.handle(en.triggers[Runnable.this.index++]);
                        Runnable.this.next();
                    }
                });
            }
            
            void handle(final Encounter_Mirror.Trigger t) {
                switch (t.type) {
                    case SELF: {
                        FeralSim.this.fauxTrigger(enemy, FeralSim.this.selfMap.get((int)t.data));
                    }
                    case EXTERNAL: {
                        final Spell spell = extMap.get((int)t.data);
                        if (spell != null) {
                            spell.click();
                        }
                    }
                    case HEALTH: {
                        if (enemy.maxHP < 0L) {
                            enemy.maxHP = t.data;
                        }
                        enemy.curHP = t.data;
                    }
                    case WAIT: {
                        FeralSim.this.goIdle("Wait", (int)t.data);
                    }
                    case END: {
                        throw new EndOfCombat();
                    }
                    default: {}
                }
            }
        });
    }
    
    public void setupCombat(final Encounter_Patchwerk en) {
        final Enemy e = new Enemy("Boss", en.levelDelta, 10000000, true, en.front ? 0.0 : 1.0, en.canBlock, en.canParry, en.canDodge, 1.0);
        this.targets.add(e);
        if (en.customArmor >= 0) {
            e.armor.clearAndSetIdentity(en.customArmor);
        }
        class G
        {
            long t;
            double d;
        }
        class G
        {
            int i;
            double dmg;
        }
        if (en.health > 0L) {
            e.maxHP = en.health;
            e.minHP = (long)(en.earlyDeathPerc * en.health);
            this.clockEnd = 3600000;
        }
        else if (en.variance == -1) {
            e.maxHP = -en.duration;
            e.minHP = (long)(en.duration * en.earlyDeathPerc);
            this.clockEnd = en.duration;
        }
        else if (en.variance == -2) {
            if (en.earlyDeathPerc > 0.0) {
                e.minHP = (int)(e.maxHP * en.earlyDeathPerc);
            }
            this.adjustHP(e, 10, en.duration, en.earlyDeathPerc);
            this.adjustHP(e, 25, en.duration, en.earlyDeathPerc);
            this.adjustHP(e, 50, en.duration, en.earlyDeathPerc);
        }
        else if (en.variance > 0) {
            e.maxHP = -en.duration;
            e.minHP = (long)(en.duration * en.earlyDeathPerc);
            this.clockEnd = en.duration;
            this.runSim();
            this.clockEnd = 3600000;
            final G g = new G();
            g.dmg = (double)e.damage;
            this.prepList.add(new Runnable() {
                static final int MAX = 5000;
                
                @Override
                public void run() {
                    final int index = g.i++;
                    final double vary = 1.0 + (((index & 0x1) > 0) ? 1 : -1) * (en.variance / 100.0) * Math.min(index, 5000) / 5000.0;
                    e.maxHP = (long)(g.dmg * vary / (1.0 - en.earlyDeathPerc));
                    e.minHP = (long)(e.maxHP * en.earlyDeathPerc);
                    e.reset();
                }
            });
            this.postList.add(new Runnable() {
                @Override
                public void run() {
                    final int delta = (FeralSim.this.clock - en.duration) / g.i;
                    final FeralSim.G val$g = g;
                    val$g.dmg *= FeralSim.clip(1.0 - delta / en.duration, 0.5, 1.5);
                }
            });
        }
        else {
            e.maxHP = -en.duration;
            e.minHP = (long)(en.duration * en.earlyDeathPerc);
            this.clockEnd = en.duration;
            final FeralSim.G g2 = new FeralSim.G();
            for (int i = 0; i < 10; ++i) {
                this.runSim();
                final FeralSim.G g3 = g2;
                g3.d += this.damage_total;
                final FeralSim.G g4 = g2;
                g4.t += this.clock;
            }
            this.clockEnd = 3600000;
            this.prepList.add(new Runnable() {
                @Override
                public void run() {
                    final double dmg = g2.d * en.duration / g2.t;
                    e.maxHP = (long)(dmg / (1.0 - en.earlyDeathPerc));
                    e.minHP = (long)(e.maxHP * en.earlyDeathPerc);
                    e.reset();
                }
            });
            this.postList.add(new Runnable() {
                @Override
                public void run() {
                    final FeralSim.G val$g = g2;
                    val$g.d += e.maxHP - e.minHP;
                    final FeralSim.G val$g2 = g2;
                    val$g2.t += FeralSim.this.clock;
                }
            });
        }
    }
    
    public void setupCombat(final Encounter_Sequence en) {
        this.resetEncounter();
    }
    
    public void optimizeFoN() {
        this.resetEncounter();
        final Enemy e = new Enemy("Boss", 3, 0, true, 1.0, false, false, false, 1.0);
        this.targets.add(e);
        this.clockEnd = 36000000;
        final double[] coeffs = new double[this.clockEnd / 1000 + 1];
        this.resetAll();
        class X
        {
            int i;
        }
        final X index = new X();
        final Logic simpleLogic = new Logic() {
            public void run() {
                try {
                    FeralSim.this.TF.click();
                    FeralSim.this.BERSERK.click();
                    FeralSim.this.MANGLE.click();
                }
                catch (Clicked clicked) {}
            }
        };
        if (this.main_logic == null) {
            this.main_logic = simpleLogic;
        }
        this.prepList.add(new Runnable() {
            @Override
            public void run() {
                FeralSim.this.pushLogic(simpleLogic);
                index.i = 0;
                this.sched();
            }
            
            void sched() {
                FeralSim.this.addEvent(new Event(FeralSim.this.clock + 1000) {
                    @Override
                    void run() {
                        Runnable.this.sched();
                        coeffs[index.i++] = FeralSim.this.treantRakeTickDmgNew();
                    }
                });
            }
        });
        this.runSim();
        int num;
        int i;
        int w;
        for (num = index.i, w = (i = 60); i < num; ++i) {
            double max = coeffs[i - w];
            for (int j = i + 1 - w; j < i; ++j) {
                max = Math.max(max, coeffs[j]);
            }
            coeffs[i - w] = max;
        }
        num -= w - 1;
        Arrays.sort(coeffs, 0, num);
        this.resetAll();
        this._treantRakeThresDmg = coeffs[num / 15];
        this._treantRakeThresBase = this.treantRakeTickDmgNew();
    }
    
    void adjustHPOld(final Enemy e, final int iter, final int duration, final double deathPerc) {
        double acc = 0.0;
        for (int run = 0; run < iter; ++run) {
            this.runSim();
            acc += e.damage / (double)this.clock;
        }
        final double damage = duration * acc / iter;
        e.maxHP = (long)(damage / (1.0 - deathPerc));
        e.minHP = (long)(e.maxHP - damage);
    }
    
    void adjustHP(final Enemy e, final int iter, final int duration, final double deathPerc) {
        final double[] vec = new double[iter];
        for (int i = 0; i < iter; ++i) {
            this.runSim();
            vec[i] = e.damage / (double)this.clock;
        }
        Arrays.sort(vec, 0, iter);
        final double damage = duration * Maths.mid(vec, iter);
        e.maxHP = (long)(damage / (1.0 - deathPerc));
        e.minHP = (long)(e.maxHP - damage);
    }
    
    void dumpEncounterHeader(final LineWriter lw) {
        if (this.encounter == null) {
            return;
        }
        lw.add("[Encounter]");
        lw.add("Name: " + this.encounter.name);
        lw.add("Desc: " + this.encounter.getDesc());
    }
    
    public void createCombatLog(final LineWriter lw) {
        this.saveDamage = true;
        this.clearDamage();
        this.logWriter = lw;
        this.printLog = true;
        this.resetAll();
        this.dumpEncounterHeader(lw);
        lw.add();
        this.dumpStats(lw);
        lw.add();
        lw.add("[Permanent Targets]");
        for (final Enemy x : this.targets) {
            lw.add("%s L%d (+%d) HP(%s) Armor(%.0f)", x.logPrefix, x.enemyLevel, x.levelDelta, x.isImmortal() ? "Immortal" : Long.valueOf(x.maxHP), x.armor.product);
        }
        lw.add();
        this.runSim();
        lw.add();
        if (this.activeTargets.isEmpty()) {
            lw.add("All %d targets are dead", this.targets.size());
        }
        for (final Enemy x : this.targets) {
            if (x.isDead()) {
                lw.add("%s Dead", x.logPrefix);
            }
            else if (x.isImmortal()) {
                lw.add("%s Immortal", x.logPrefix);
            }
            else {
                lw.add("%s %5.2f%% %d/%d", x.logPrefix, 100.0 * x.percHP(), x.curHP, x.maxHP);
            }
        }
        lw.add();
        this.dumpTalentsGlyphsBonuses(lw);
        lw.add();
        this.dumpFeatures(lw);
        this.dumpHotfixes(lw);
        lw.add();
        this.dumpStatistics(lw);
        lw.add();
        this.dumpSpellUptimes(lw);
        lw.add();
        this.dumpProcUptimes(lw);
        lw.add();
        this.dumpProcTriggers(lw);
        lw.add();
        this.dumpTimeSpent(lw);
        lw.add();
        this.dumpDamage(lw);
        this.dmgMap.clear();
        this.clearDamage();
        this.logWriter = null;
    }
    
    DamageTable getSpellMods(final Spell spell, final boolean assumeSR, final boolean debuffEffects, final boolean tempEffects) {
        final boolean bleed = spell == this.RIP || spell == this.RAKE || spell == this.THRASH || spell == this.FON || spell == this.THRASH_BEAR;
        final BitSet statMask = new BitSet();
        statMask.set(FeralSim.STAT_AGI);
        statMask.set(FeralSim.STAT_STR);
        statMask.set(FeralSim.STAT_AP);
        if (bleed && !(spell instanceof BearSpell)) {
            statMask.set(FeralSim.STAT_MASTERY);
        }
        statMask.set(FeralSim.STAT_CRIT);
        final ArrayList<TableMod> perm = new ArrayList<TableMod>();
        final ArrayList<TableMod> temp = new ArrayList<TableMod>();
        if (spell instanceof CatSpell) {
            (assumeSR ? perm : temp).add(TableMod.make("SR", this.modDmg_sr));
            if (this.spec.talent_doc) {
                temp.add(TableMod.make("DoC", this.modDmg_doc));
            }
            temp.add(TableMod.make("TF", this.modDmg_tf));
        }
        if (debuffEffects) {
            perm.add(TableMod.make("Debuffs", this.debuff_meleeMod));
            if (!bleed) {
                if (this.cfg.debuff_armor == WeakenedArmor.ALWAYS) {
                    perm.add(TableMod.make("FF", ModType.ARMOR, 0.88));
                }
                else if (this.cfg.debuff_armor.casted) {
                    temp.add(TableMod.make("FF", ModType.ARMOR, 0.88));
                }
            }
        }
        if (tempEffects && this.cfg.pot != null) {
            final int index = FeralSim.STAT_MAP.get(this.cfg.pot.type);
            if (statMask.get(index)) {
                temp.add(TableMod.make("Pot", index, this.cfg.pot.value));
            }
        }
        if (this.bonus_t14_2p && (spell == this.MANGLE || spell == this.SHRED)) {
            temp.add(TableMod.make("T14/2p", 1.05));
        }
        if (this.bonus_t15_4p && (spell == this.SWIPE || spell == this.MANGLE || spell == this.SHRED || spell == this.RAVAGE || spell == this.FB)) {
            temp.add(TableMod.make("T15/4p", FeralSim.STAT_CRIT, 24000));
        }
        if (this.bonus_t16_2p && (spell == this.SWIPE || spell == this.MANGLE || spell == this.SHRED || spell == this.RAVAGE)) {
            temp.add(TableMod.make("T16/2p", 1.5));
        }
        if (this.bonus_wodpvp_4p && spell == this.SHRED) {
            temp.add(TableMod.make("Bloodletting", 1.1));
        }
        if (spell == this.SWIPE || spell == this.SHRED) {
            (this.cfg.debuff_bleeding ? perm : temp).add(TableMod.make("Bleed", 1.2));
        }
        if (spell == this.FB) {
            temp.add(TableMod.make("Extra", 2.0));
        }
        if (spell != this.FON) {
            if (this.spec.talent_nv) {
                temp.add(TableMod.make("NV", this.modDmg_nv));
            }
            if (tempEffects) {
                if (this.cfg.num_tricksOfTheTrade > 0) {
                    temp.add(TableMod.make("Tricks", 1.15));
                }
                if (this.cfg.num_skullBanner > 0) {
                    temp.add(TableMod.make("Banner", ModType.CRIT, 1.2));
                }
                if (this.cfg.num_shatteringThrow > 0 && !bleed) {
                    temp.add(TableMod.make("Shatter", ModType.ARMOR, 0.8));
                }
            }
        }
        for (final Spell_Cooldown x : this.onUseSpells) {
            if (x instanceof Spell_BuffCooldown_Stat) {
                final Spell_BuffCooldown_Stat buff = (Spell_BuffCooldown_Stat)x;
                if (!statMask.get(buff.eff.index)) {
                    continue;
                }
                temp.add(TableMod.make(abbrName(buff.actionName), buff.eff.index, buff.eff.value));
            }
        }
        for (final ProcHandler x2 : this.procs) {
            if (x2 instanceof ProcHandler_GearSpecific && !((ProcHandler_GearSpecific)x2).gear.equipped()) {
                continue;
            }
            if (x2.proc instanceof Proc_Single_Stat) {
                final Proc_Single_Stat proc = (Proc_Single_Stat)x2.proc;
                if (!statMask.get(proc.eff.index)) {
                    continue;
                }
                temp.add(TableMod.make(abbrName(proc.name), proc.eff.index, proc.eff.value));
            }
            else if (x2.proc instanceof Proc_Single_StatRamp) {
                final Proc_Single_StatRamp proc2 = (Proc_Single_StatRamp)x2.proc;
                if (!statMask.get(proc2.index)) {
                    continue;
                }
                temp.add(TableMod.make(abbrName(proc2.name), proc2.index, proc2.value * proc2.max));
            }
            else if (x2.proc instanceof Proc_Single_RoR) {
                final Proc_Single_RoR proc3 = (Proc_Single_RoR)x2.proc;
                proc3.computeEffect();
                if (!statMask.get(FeralSim.STAT_CRIT) && !statMask.get(FeralSim.STAT_MASTERY) && !statMask.get(FeralSim.STAT_HASTE)) {
                    continue;
                }
                temp.add(TableMod.make(abbrName(proc3.name), FeralSim.STAT_MASTERY, proc3.mastery, FeralSim.STAT_HASTE, proc3.haste, FeralSim.STAT_CRIT, proc3.crit));
            }
            else {
                if (!(x2.proc instanceof Proc_MultiStat)) {
                    continue;
                }
                final Proc_MultiStat proc4 = (Proc_MultiStat)x2.proc;
                for (final Proc_MultiStat_Type t : proc4.types) {
                    if (statMask.get(t.index)) {
                        temp.add(TableMod.make(abbrName(proc4.name) + "/" + FeralSim.STATS[t.index].abbr, t.index, proc4.value));
                    }
                }
            }
        }
        return new DamageTable(spell, bleed, perm.toArray(new TableMod[perm.size()]), temp.toArray(new TableMod[temp.size()]));
    }
    
    static String abbrName(final String s) {
        final String[] v = s.split("\\s+");
        final StringBuilder sb = new StringBuilder();
        for (final String x : v) {
            sb.append(x.charAt(0));
        }
        final int pos = s.indexOf(60);
        if (pos >= 0) {
            sb.append(s.substring(pos));
        }
        return sb.toString();
    }
    
    private static double drop1(final double x) {
        return (x == 1.0) ? Double.NEGATIVE_INFINITY : x;
    }
    
    private String compareTable(final DamageTable A, final DamageTable B) {
        final ArrayList<DamageEntry> all = new ArrayList<DamageEntry>();
        all.addAll(A.rows);
        all.addAll(B.rows);
        Collections.sort(all, DamageEntry.CMP);
        final double baseDmg = A.baseDmg();
        final HashMap<String, TableMod> modMap = new HashMap<String, TableMod>();
        for (final TableMod mod : A.tempMods) {
            modMap.put(mod.name, mod);
        }
        for (final TableMod mod : B.tempMods) {
            modMap.put(mod.name, mod);
        }
        final TableMod[] allMods = modMap.values().toArray(new TableMod[modMap.size()]);
        Arrays.sort(allMods, new Comparator<TableMod>() {
            @Override
            public int compare(final TableMod a, final TableMod b) {
                if (a.mod == b.mod) {
                    return a.tot() - b.tot();
                }
                return Double.compare(-drop1(a.mod), -drop1(b.mod));
            }
        });
        A.name = A.spell.actionName;
        B.name = B.spell.actionName;
        if (A.name.length() > 10) {
            A.name = abbrName(A.name);
        }
        if (B.name.length() > 10) {
            B.name = abbrName(B.name);
        }
        final int max = Math.max(5, Math.max(A.name.length(), B.name.length()));
        final HashSet<String> has = new HashSet<String>();
        final StringBuilder sb = new StringBuilder(4096);
        sb.append(String.format("%" + max + "s %6s %7s %7s %7s %6s %6s %2s", "Spell", "Power", "Average", "Hit", "Crit", "AP", "Crit%", "#"));
        final String fmt = "%" + max + "s %5.2fx %7.0f %7.0f %7.0f %6d %5.1f%% %2d";
        for (final DamageEntry x : all) {
            sb.append('\n');
            sb.append(String.format(fmt, x.table.name, x.avgDmg / baseDmg, x.avgDmg, x.hitDmg, x.critDmg, x.ap, x.crit * 100.0, x.mods.length));
            if (x.mods.length == 0 && x.table == A) {
                boolean first = true;
                for (final TableMod mod2 : allMods) {
                    sb.append(first ? "  " : "--");
                    first = false;
                    for (int i = 0, e = mod2.name.length(); i < e; ++i) {
                        sb.append('-');
                    }
                }
            }
            else {
                has.clear();
                for (final TableMod mod3 : x.mods) {
                    has.add(mod3.name);
                }
                for (final TableMod mod3 : allMods) {
                    sb.append("  ");
                    if (has.contains(mod3.name)) {
                        sb.append(mod3.name);
                    }
                    else {
                        for (int j = 0, e2 = mod3.name.length(); j < e2; ++j) {
                            sb.append(' ');
                        }
                    }
                }
            }
        }
        sb.append("\n\n");
        sb.append("=== ");
        sb.append(A.spell.actionName);
        sb.append(" ===");
        this.dumpDamageMods(sb, A);
        sb.append("\n\n");
        sb.append("=== ");
        sb.append(B.spell.actionName);
        sb.append(" ===");
        this.dumpDamageMods(sb, B);
        return sb.toString();
    }
    
    public String mangleRakeTable(final boolean assumeSR, final boolean debuffEffects, final boolean tempEffects) {
        return this.compareTable(this.makeDamageTable(this.MANGLE, 0, assumeSR, debuffEffects, tempEffects), this.makeDamageTable(this.RAKE, 0, assumeSR, debuffEffects, tempEffects));
    }
    
    public String swipeThrashTable(final int ticks, final boolean assumeSR, final boolean debuffEffects, final boolean tempEffects) {
        return this.compareTable(this.makeDamageTable(this.SWIPE, 0, assumeSR, debuffEffects, tempEffects), this.makeDamageTable(this.THRASH, ticks, assumeSR, debuffEffects, tempEffects));
    }
    
    public String fbRipTable(final int ticks, final boolean assumeSR, final boolean debuffEffects, final boolean tempEffects) {
        return this.compareTable(this.makeDamageTable(this.FB, 5, assumeSR, debuffEffects, tempEffects), this.makeDamageTable(this.RIP, -ticks, assumeSR, debuffEffects, tempEffects));
    }
    
    public String mangleTable(final boolean assumeSR, final boolean debuffEffects, final boolean tempEffects) {
        return this.singleTable(this.MANGLE, 0, assumeSR, debuffEffects, tempEffects);
    }
    
    public String shredTable(final boolean assumeSR, final boolean debuffEffects, final boolean tempEffects) {
        return this.singleTable(this.SHRED, 0, assumeSR, debuffEffects, tempEffects);
    }
    
    public String ravageTable(final boolean assumeSR, final boolean debuffEffects, final boolean tempEffects) {
        return this.singleTable(this.RAVAGE, 0, assumeSR, debuffEffects, tempEffects);
    }
    
    public String swipeTable(final boolean assumeSR, final boolean debuffEffects, final boolean tempEffects) {
        return this.singleTable(this.SWIPE, 0, assumeSR, debuffEffects, tempEffects);
    }
    
    public String fbTable(final boolean assumeSR, final boolean debuffEffects, final boolean tempEffects) {
        return this.singleTable(this.FB, 5, assumeSR, debuffEffects, tempEffects);
    }
    
    public String rakeTable(final boolean assumeSR, final boolean debuffEffects, final boolean tempEffects) {
        return this.singleTable(this.RAKE, 0, assumeSR, debuffEffects, tempEffects);
    }
    
    public String thrashTable(final int ticks, final boolean assumeSR, final boolean debuffEffects, final boolean tempEffects) {
        return this.singleTable(this.THRASH, ticks, assumeSR, debuffEffects, tempEffects);
    }
    
    public String fonTable(final boolean debuffEffects) {
        return this.singleTable(this.FON, 0, false, debuffEffects, false);
    }
    
    public String ripTable(final boolean combo4, final boolean assumeSR, final boolean debuffEffects, final boolean tempEffects) {
        return this.singleTable(this.RIP, combo4 ? 0 : 5, assumeSR, debuffEffects, tempEffects);
    }
    
    private String singleTable(final Spell spell, final int option, final boolean assumeSR, final boolean debuffEffects, final boolean tempEffects) {
        final DamageTable table = this.makeDamageTable(spell, option, assumeSR, debuffEffects, tempEffects);
        final StringBuilder sb = new StringBuilder(4096);
        int max = 0;
        if (table.extra != null) {
            max = table.extra.length();
            for (final DamageEntry x : table.rows) {
                max = Math.max(max, x.extra.length());
            }
        }
        sb.append(String.format("%6s %7s %7s %7s %6s %6s", "Power", "Average", table.bleed ? "Tick" : "Hit", "Crit", "AP", "Crit%"));
        if (table.bleed) {
            sb.append(" Bleed");
        }
        if (max > 0) {
            sb.append(' ');
            Fmt.padLeft(sb, table.extra, max);
        }
        sb.append("  #");
        final double baseDmg = table.baseDmg();
        final HashSet<TableMod> has = new HashSet<TableMod>();
        for (final DamageEntry x2 : table.rows) {
            sb.append("\n");
            sb.append(String.format("%5.2fx %7.0f %7.0f %7.0f %6d %5.1f%%", x2.avgDmg / baseDmg, x2.avgDmg, x2.hitDmg, x2.critDmg, x2.ap, x2.crit * 100.0));
            if (table.bleed) {
                sb.append(String.format(" %4.2fx", x2.mast));
            }
            if (max > 0) {
                sb.append(' ');
                Fmt.padLeft(sb, x2.extra, max);
            }
            sb.append(String.format("%3d", x2.mods.length));
            has.clear();
            Collections.addAll(has, x2.mods);
            for (final TableMod mod : table.tempMods) {
                sb.append("  ");
                if (has.contains(mod)) {
                    sb.append(mod.name);
                }
                else {
                    for (int i = 0, e = mod.name.length(); i < e; ++i) {
                        sb.append(' ');
                    }
                }
            }
        }
        this.dumpDamageMods(sb, table);
        return sb.toString();
    }
    
    private void dumpDamageMods(final StringBuilder sb, final DamageTable table) {
        if (table.permMods.length > 0) {
            sb.append("\n\n");
            sb.append("[Permanent]");
            this.dumpDamageMods(sb, table.permMods);
        }
        if (table.tempMods.length > 0) {
            sb.append("\n\n");
            sb.append("[Temporary]");
            this.dumpDamageMods(sb, table.tempMods);
        }
    }
    
    private void dumpDamageMods(final StringBuilder sb, final TableMod[] mods) {
        int num = 0;
        for (final TableMod mod : mods) {
            sb.append('\n');
            sb.append(String.format("%2d. ", ++num));
            Fmt.padRight(sb, mod.name, 12);
            switch (mod.type) {
                case STAT: {
                    for (int i = 0; i < mod.stats.length; i += 2) {
                        if (i > 0) {
                            sb.append(", ");
                        }
                        sb.append(FeralSim.STATS[mod.stats[i]].formatValue(mod.stats[i + 1]));
                    }
                    break;
                }
                case ARMOR: {
                    sb.append(String.format("%+.0f%% Armor", 100.0 * (mod.mod - 1.0)));
                    break;
                }
                case DAMAGE: {
                    sb.append(String.format("%+.0f%% Damage", 100.0 * (mod.mod - 1.0)));
                    break;
                }
                case CRIT: {
                    sb.append(String.format("%+.0f%% Crit Damage", 100.0 * (mod.mod - 1.0)));
                    break;
                }
            }
        }
    }
    
    private DamageTable makeDamageTable(final Spell spell, final int option, final boolean assumeSR, final boolean debuffEffects, final boolean tempEffects) {
        this.resetAll();
        final DamageTable table = this.getSpellMods(spell, assumeSR, debuffEffects, tempEffects);
        this.combo = option;
        double prod0 = 1.0;
        double armor0 = armorByLevel(93);
        for (final TableMod mod : table.permMods) {
            switch (mod.type) {
                case STAT: {
                    mod.apply(this.procStats);
                    break;
                }
                case DAMAGE: {
                    prod0 *= mod.mod;
                    break;
                }
                case ARMOR: {
                    armor0 *= mod.mod;
                    break;
                }
            }
        }
        final int[] copyStats = this.procStats.clone();
        final TableMod[] mods = table.tempMods;
        final TableMod[] modBuf = new TableMod[mods.length];
        for (int p = (1 << mods.length) - 1; p >= 0; --p) {
            System.arraycopy(copyStats, 0, this.procStats, 0, copyStats.length);
            int modNum = 0;
            double prod2 = prod0;
            double armor2 = armor0;
            this.critDmgMods.clear();
            for (int i = 0; i < mods.length; ++i) {
                final TableMod mod2 = mods[i];
                if ((p & 1 << i) != 0x0) {
                    modBuf[modNum++] = mod2;
                    switch (mod2.type) {
                        case STAT: {
                            mod2.apply(this.procStats);
                            break;
                        }
                        case DAMAGE: {
                            prod2 *= mod2.mod;
                            break;
                        }
                        case CRIT: {
                            this.critDmgMods.add(1, mod2.mod);
                            break;
                        }
                        case ARMOR: {
                            armor2 *= mod2.mod;
                            break;
                        }
                    }
                }
            }
            if (!table.bleed) {
                prod2 *= armorMod(armor2);
            }
            final TableMod[] effects = Arrays.copyOf(modBuf, modNum);
            final double razorMod = this.getRazorClawsMod();
            final int ap = this.getAP();
            final double crit = Math.max(0.0, this.getMeleeCrit() - 0.03);
            if (spell == this.RIP) {
                if (option == 0) {
                    if (table.extra == null) {
                        table.extra = "CP";
                    }
                    this.combo = 5;
                    double tickDmg = prod2 * this._ripTickDmg();
                    double critDmg = tickDmg * this.critDmgMods.product;
                    double avgDmg = this.avgDmg(tickDmg, crit, this.critDmgMods.product);
                    table.rows.add(new DamageEntry(table, "[5]", effects, tickDmg, critDmg, avgDmg, ap, crit, razorMod));
                    this.combo = 4;
                    tickDmg = prod2 * this._ripTickDmg();
                    critDmg = tickDmg * this.critDmgMods.product;
                    avgDmg = this.avgDmg(tickDmg, crit, this.critDmgMods.product);
                    table.rows.add(new DamageEntry(table, "[4]", effects, tickDmg, critDmg, avgDmg, ap, crit, razorMod));
                }
                else {
                    int ticks;
                    if (option < 0) {
                        this.combo = 5;
                        ticks = -option;
                    }
                    else {
                        this.combo = option;
                        ticks = 1;
                    }
                    final double tickDmg2 = prod2 * this._ripTickDmg();
                    final double critDmg2 = tickDmg2 * this.critDmgMods.product;
                    final double avgDmg2 = ticks * this.avgDmg(tickDmg2, crit, this.critDmgMods.product);
                    table.rows.add(new DamageEntry(table, "", effects, tickDmg2, critDmg2, avgDmg2, ap, crit, razorMod));
                }
            }
            else if (spell == this.RAKE) {
                final double tickDmg = prod2 * this._rakeTickDmg();
                final double critDmg = tickDmg * this.critDmgMods.product;
                final double avgDmg = this.avgDmg(tickDmg, crit, this.critDmgMods.product);
                table.rows.add(new DamageEntry(table, "", effects, tickDmg, critDmg, avgDmg, ap, crit, razorMod));
            }
            else if (spell == this.FON) {
                final double tickDmg = prod2 * this._treantRakeTickDmg();
                final double critDmg = tickDmg * 2.0;
                final double avgDmg = this.avgDmg(tickDmg, crit, 2.0);
                table.rows.add(new DamageEntry(table, "", effects, tickDmg, critDmg, avgDmg, ap, crit, razorMod));
            }
            else if (spell == this.THRASH) {
                final double initDmg = prod2 * razorMod * this._thrashDmgInit(ap);
                final double tickDmg3 = prod2 * razorMod * this._thrashDmgTick(ap);
                final double critDmg3 = tickDmg3 * this.critDmgMods.product;
                final double avgDmg3 = this.avgDmg(initDmg, crit, this.critDmgMods.product) + this.avgDmg(tickDmg3, crit, this.critDmgMods.product) * option;
                table.rows.add(new DamageEntry(table, "", effects, tickDmg3, critDmg3, avgDmg3, ap, crit, razorMod));
            }
            else if (spell == this.MANGLE) {
                final double initDmg = prod2 * this._shrangleDmg(false);
                final double critDmg = initDmg * this.critDmgMods.product;
                final double avgDmg = this.avgDmg(initDmg, crit, this.critDmgMods.product);
                table.rows.add(new DamageEntry(table, "", effects, initDmg, critDmg, avgDmg, ap, crit, Double.NaN));
            }
            else if (spell == this.SHRED) {
                final double initDmg = prod2 * this._shrangleDmg(false);
                final double critDmg = initDmg * this.critDmgMods.product;
                final double avgDmg = this.avgDmg(initDmg, crit, this.critDmgMods.product);
                table.rows.add(new DamageEntry(table, "", effects, initDmg, critDmg, avgDmg, ap, crit, Double.NaN));
            }
            else if (spell == this.RAVAGE) {
                final double initDmg = prod2 * this._ravageDmg(false);
                final double critDmg = initDmg * this.critDmgMods.product;
                final double avgDmg = this.avgDmg(initDmg, crit, this.critDmgMods.product);
                table.rows.add(new DamageEntry(table, "", effects, initDmg, critDmg, avgDmg, ap, crit, Double.NaN));
            }
            else if (spell == this.SWIPE) {
                final double initDmg = prod2 * this._swipeDmg(false);
                final double critDmg = initDmg * this.critDmgMods.product;
                final double avgDmg = this.avgDmg(initDmg, crit, this.critDmgMods.product);
                table.rows.add(new DamageEntry(table, "", effects, initDmg, critDmg, avgDmg, ap, crit, Double.NaN));
            }
            else if (spell == this.FB) {
                final double initDmg = prod2 * this._fbDmg(false);
                final double critDmg = initDmg * this.critDmgMods.product;
                final double avgDmg = this.avgDmg(initDmg, crit, this.critDmgMods.product);
                table.rows.add(new DamageEntry(table, "", effects, initDmg, critDmg, avgDmg, ap, crit, Double.NaN));
            }
        }
        Collections.sort(table.rows, DamageEntry.CMP);
        return table;
    }
    
    private void disable_print() {
        this.printLog = false;
        this.printEnergy = false;
        this.printDebug = false;
        this.printMore = false;
    }
    
    void dist_prep() {
        this.clearDamage();
        this.saveDamage = true;
        this.disable_print();
    }
    
    String catStatsStr() {
        return String.format("%d AP, %d Agi, %.2f%% Mastery, %.2f%% Crit, %.2f%% Haste, %.2f%% Hit, %.2f%% Exp", this.getAP(), this.getAgi(), (this.getRazorClawsMod() - 1.0) * 100.0, this.getMeleeCrit() * 100.0, (this.getMeleeHasteMod() - 1.0) * 100.0, this.getMeleeHit() * 100.0, this.getMeleeExp() * 100.0);
    }
    
    String meleeHitExpStr() {
        return String.format("%.2f%% (%d) Hit, %.2f%% (%d) Exp", this.getMeleeHit() * 100.0, this.getMeleeHitRating(), this.getMeleeExp() * 100.0, this.getMeleeExpRating());
    }
    
    double getSimDPS() {
        return this.damage_total / (this.clock / 1000.0);
    }
    
    void runSim() {
        this.prepareForCombat();
        try {
            while (true) {
                this.handleNextEvent();
            }
        }
        catch (EndOfCombat ex) {
            this.fadeRemainingEvents();
            ++this.runCount;
        }
    }
    
    void dumpClickCount(final Collection<? extends Spell> list) {
        for (final Spell x : list) {
            System.out.println(String.format("%-20s %5d", x.actionName, x.click_count));
        }
    }
    
    void dumpProcUptimes(final LineWriter lw) {
        lw.add("[Proc Uptimes]");
        lw.add("%-32s %8s %8s", "Name", "Occ", "Uptime");
        final String fmt = "%-32s %8d %7.2f%%";
        for (final ProcHandler x : this.activeProcs) {
            if (x.proc instanceof Proc_Single) {
                final Proc_Single proc = (Proc_Single)x.proc;
                lw.add(fmt, x.procName(), proc.count, 100.0 * proc.uptime / this.clock);
            }
            else if (x.proc instanceof Proc_Stacking) {
                final Proc_Stacking proc2 = (Proc_Stacking)x.proc;
                lw.add(fmt, x.procName(), proc2.count, 100.0 * proc2.uptime() / this.clock);
            }
            else {
                if (!(x.proc instanceof Proc_MultiStat)) {
                    continue;
                }
                final Proc_MultiStat proc3 = (Proc_MultiStat)x.proc;
                for (final Proc_MultiStat_Type type : proc3.types) {
                    lw.add(fmt, x.procName() + "/" + FeralSim.STATS[type.index].abbr, type.count, 100.0 * type.uptime / this.clock);
                }
            }
        }
    }
    
    void dumpSpellUptimes(final LineWriter lw) {
        class Uptime
        {
            final String name = x.actionName;
            final int num = x.click_count;
            final int uptime = ((Spell_Cooldown)x).uptime;
            
            Uptime(final int num, final int uptime) {
            }
        }
        final ArrayList<Uptime> list = new ArrayList<Uptime>();
        for (final Spell x : this.activeSpells) {
            if (x.enabled && x instanceof Spell_Cooldown) {
                list.add(new Uptime(x.click_count));
            }
        }
        if (this.SR.click_count > 0) {
            list.add(new Uptime(this.SR.click_count));
        }
        Collections.sort(list, new Comparator<Uptime>() {
            @Override
            public int compare(final Uptime a, final Uptime b) {
                return b.uptime - a.uptime;
            }
        });
        lw.add("[Spell Uptimes]");
        lw.add("%-32s %8s %8s", "Name", "Occ", "Uptime");
        final String fmt = "%-32s %8d %7.2f%%";
        for (final Uptime x2 : list) {
            lw.add(fmt, x2.name, x2.num, 100.0 * x2.uptime / this.clock);
        }
    }
    
    void dumpProcTriggers(final LineWriter lw) {
        lw.add("[RPPM Triggers]");
        lw.add("%-32s %8s %8s", "Name", "Opp", "Opp/sec");
        for (final ProcHandler x : this.activeProcs) {
            if (x.proc instanceof Proc_Chance) {
                final Proc_Chance proc = (Proc_Chance)x.proc;
                if (!(proc.chance instanceof ProcChance_RPPM)) {
                    continue;
                }
                final ProcChance_RPPM chance = (ProcChance_RPPM)proc.chance;
                lw.add("%-32s %8d %8.2f", x.procName(), chance.oppCount, 1000.0 * chance.oppCount / this.clock);
            }
        }
    }
    
    void dumpTimeSpent(final LineWriter lw) {
        class X
        {
            final String key = x.actionName;
            final int time = x.time_spent;
            final int count = x.click_count;
            
            X(final int time, final int count) {
            }
        }
        final ArrayList<X> list = new ArrayList<X>(this.spells.size());
        int spent = 0;
        for (final Spell x : this.spells) {
            final int t = x.time_spent;
            if (t > 0) {
                spent += t;
                list.add(new X(x.time_spent));
            }
        }
        final int wait = this.clock - spent;
        if (wait > 0) {
            list.add(new X(wait));
        }
        Collections.sort(list, new Comparator<X>() {
            @Override
            public int compare(final FeralSim.X a, final FeralSim.X b) {
                return b.time - a.time;
            }
        });
        lw.add("[Time Spent]");
        lw.add("%-20s %8s %7s %4s %s", "Name", "Time", "Perc", "Occ", "Avg");
        for (final X x2 : list) {
            lw.add("%-20s %8s %6.2f%% %4s %s", x2.key, Fmt.msDur(x2.time), 100.0 * x2.time / this.clock, (x2.count > 0) ? Integer.toString(x2.count) : "", (x2.count > 0) ? Fmt.msDur(x2.time / x2.count) : "");
        }
    }
    
    void dumpDamage(final LineWriter lw) {
        final ArrayList<DamageTally> temp = new ArrayList<DamageTally>(this.dmgTallyMap.values());
        Collections.sort(temp, DamageTally.CMP);
        long sum = 0L;
        for (final DamageTally x : temp) {
            sum += x.sum();
        }
        lw.add("[Damage Distribution]");
        lw.add("%-18s %5s %7s %7s %7s %7s %5s %7s %7s %7s %5s %7s %7s", "Name", "Hit#", "Avg", "Min", "Max", "Crit%", "Crit#", "Avg", "Min", "Max", "#", "Avg", "All%");
        for (final DamageTally x : temp) {
            lw.add("%-18s %5.1f %7.0f %7d %7d %6.2f%% %5.1f %7.0f %7d %7d %5.1f %7.0f %6.2f%%", x.name, x.hit_num / (double)this.runCount, (x.hit_num > 0) ? (x.hit_sum / (double)x.hit_num) : 0.0, x.hit_min, x.hit_max, 100.0 * x.crit_num / x.occ(), x.crit_num / (double)this.runCount, (x.crit_num > 0) ? (x.crit_sum / (double)x.crit_num) : 0.0, x.crit_min, x.crit_max, x.occ() / (double)this.runCount, x.sum() / (double)x.occ(), 100.0 * x.sum() / sum);
        }
        final HashMap<String, DamageTally> map = new HashMap<String, DamageTally>();
        for (final DamageTally x2 : temp) {
            if (x2.sum() == 0L) {
                continue;
            }
            String name = x2.name;
            if (name.startsWith(".")) {
                name = name.substring(".".length());
            }
            final int pos = name.indexOf(96);
            if (pos != -1) {
                name = name.substring(0, pos);
            }
            DamageTally tally = map.get(name);
            if (tally == null) {
                tally = new DamageTally(name);
                map.put(name, tally);
            }
            final DamageTally damageTally = tally;
            damageTally.hit_num += x2.hit_num;
            final DamageTally damageTally2 = tally;
            damageTally2.hit_sum += x2.hit_sum;
            final DamageTally damageTally3 = tally;
            damageTally3.crit_num += x2.crit_num;
            final DamageTally damageTally4 = tally;
            damageTally4.crit_sum += x2.crit_sum;
        }
        temp.clear();
        temp.addAll(map.values());
        Collections.sort(temp, DamageTally.CMP);
        sum = 0L;
        for (final DamageTally x2 : temp) {
            sum += x2.sum();
        }
        lw.add();
        lw.add("[Dense Damage Distribution]");
        lw.add("%-32s %8s %8s %8s %8s", "Name", "#", "Avg", "Crit%", "All%");
        for (final DamageTally x2 : temp) {
            lw.add("%-32s %8.1f %8d %7.2f%% %7.2f%%", x2.name, x2.occ() / (double)this.runCount, x2.sum() / x2.occ(), 100.0 * x2.crit_num / x2.occ(), 100.0 * x2.sum() / sum);
        }
    }
    
    void dumpDamage(final LineWriter lw, final boolean minor, final int runCount) {
        double overall = 0.0;
        class X
        {
            String key;
            double total;
            ArrayList<Damage> list;
        }
        final X[] sorted = new X[this.dmgMap.size()];
        int i = 0;
        for (final Map.Entry<String, ArrayList<Damage>> x : this.dmgMap.entrySet()) {
            final X[] array = sorted;
            final int n = i++;
            final X x3 = new X();
            array[n] = x3;
            final X temp = x3;
            temp.key = x.getKey();
            temp.list = x.getValue();
            final double n2 = overall;
            final X x4 = temp;
            final double sum = Damage.sum(temp.list);
            x4.total = sum;
            overall = n2 + sum;
        }
        Arrays.sort(sorted, new Comparator<X>() {
            @Override
            public int compare(final FeralSim.X a, final FeralSim.X b) {
                return Double.compare(b.total, a.total);
            }
        });
        lw.add("%-40s %8s %9s %9s %7s %9s %8s", "Name", "Count", "Hit", "Crit", "Crit%", "Overall", "DPS");
        for (final X x2 : sorted) {
            lw.add("%-40s %8d %9.0f %9.0f %6.2f%% [%6.2f%%] %8.0f", x2.key, x2.list.size(), Damage.avg(x2.list, false), Damage.avg(x2.list, true), 100.0 * Damage.crit(x2.list), 100.0 * x2.total / overall, x2.total * 1000.0 / this.clock / runCount);
            if (minor) {
                this.dumpMinor(lw, x2.list);
            }
        }
    }
    
    void dumpMinor(final LineWriter lw, final ArrayList<Damage> list) {
        final TreeMap<String, ArrayList<Damage>> map = new TreeMap<String, ArrayList<Damage>>();
        for (final Damage x : list) {
            ArrayList<Damage> temp = map.get(x.mods);
            if (temp == null) {
                temp = new ArrayList<Damage>();
                map.put(x.mods, temp);
            }
            temp.add(x);
        }
        if (map.size() == 1 && map.firstEntry().getKey().isEmpty()) {
            return;
        }
        for (final Map.Entry<String, ArrayList<Damage>> minor : map.entrySet()) {
            final ArrayList<Damage> minorList = minor.getValue();
            lw.add("%-40s %8d %9.0f %9.0f %6.2f%%", minor.getKey(), minorList.size(), Damage.avg(minorList, false), Damage.avg(minorList, true), 100.0 * Damage.crit(minorList));
        }
    }
    
    void dumpStatistics(final LineWriter lw) {
        lw.add("DPS        = %8.0f", this.damage_total * 1000.0 / this.clock);
        final int[] array;
        final int[] v = array = new int[] { 300000, 180000, 120000, 60000, 30000 };
        for (final int ms : array) {
            final double avg = this.dw.mean(this.clock, ms);
            if (avg > 0.0) {
                lw.add("DPS (%3ds) = %8.0f", ms / 1000, 1000.0 * this.dw.mean(this.clock, ms));
            }
        }
        lw.add();
        lw.add("[Healing Touch]");
        lw.add("HT                 = %d", this.HT.click_count);
        lw.add("PS                 = %d", this.ps_occur);
        lw.add("PS Used            = %d (%.1f%% of HTs, once every %s)", this.ps_used, (this.HT.click_count > 0) ? (100.0 * this.ps_used / this.HT.click_count) : Double.NaN, Fmt.msDur((this.ps_used > 0) ? ((long)(this.clock / this.ps_used)) : -1L));
        lw.add("PS Replaced        = %d", this.ps_replaced);
        if (this.NS.enabled) {
            lw.add("NS Used            = %d (%.1f%% of HTs)", this.ns_used, 100.0 * this.ns_used / this.HT.click_count);
        }
        if (this.spec.talent_doc) {
            lw.add("DoC Total          = %d", this.doc_total);
            lw.add("DoC Spent          = %d", this.doc_spent);
            lw.add("DoC Wasted         = %d", this.doc_wasted);
        }
        lw.add();
        lw.add("[Combo Points]");
        lw.add("Combo Total        = %d", this.combo_generated + this.combo_waste_overflow);
        lw.add("Combo Generated    = %d", this.combo_generated);
        lw.add("Combo Wasted       = %d", this.combo_waste_overflow);
        int finisher_count = 0;
        for (final int i : this.finisher_tally) {
            finisher_count += i;
        }
        lw.add("Combo Spent        = %d (~%.1f per finisher, %d finishers)", this.combo_finisher, this.combo_finisher / (double)finisher_count, finisher_count);
        for (int j = 0; j < this.finisher_tally.length; ++j) {
            final int t = this.finisher_tally[j];
            lw.add("Finisher`%d        = %3d %6.2f%%", j, t, 100.0 * t / finisher_count);
        }
        if (this.bonus_t16_4p) {
            lw.add("4p/16 Used         = %d / %d", this.t16_4p_used, this.TF.click_count);
        }
        lw.add();
        lw.add("[Savage Roar]");
        lw.add("SR Uptime          = %6.2f%%", 100.0 * this.sr_uptime / this.clock);
        lw.add("SR Casts           = %d", this.SR.click_count);
        lw.add("SR Clipped Time    = %s", Fmt.msDur(this.sr_clip_time));
        if (this.spec.glyph_sr) {
            lw.add("SR Glyph Use       = %d", this.sr_zero);
        }
        lw.add();
        final int max = this.energy_regen_max + this.energy_refund_max + this.energy_tf_max + this.energy_sotf_max;
        final int gain = this.energy_regen + this.energy_refund + this.energy_tf + this.energy_sotf;
        lw.add("[Energy]");
        lw.add("Total              = %d", max);
        lw.add("Waste              = %d", max - gain);
        lw.add("Regen              = %4d / %d", this.energy_regen, this.energy_regen_max);
        lw.add("Refund             = %4d / %d", this.energy_refund, this.energy_refund_max);
        lw.add("TF                 = %4d / %d (%d TFs)", this.energy_tf, this.energy_tf_max, this.TF.click_count);
        if (this.spec.talent_sotf) {
            lw.add("SotF               = %4d / %d (%d Finishers)", this.energy_sotf, this.energy_sotf_max, this.sotf_count);
        }
        lw.add("OoC Savings        = %4d", this.ooc_energy);
        lw.add("OoC Total          = %d", this.ooc_occur);
        lw.add("OoC Spent          = %d", this.ooc_spent);
        lw.add("OoC Man/Shr/Rav    = %d (%.0f%%)", this.ooc_mangleShredRavage, 100.0 * this.ooc_mangleShredRavage / this.ooc_occur);
        lw.add("OoC Thrash         = %d (%.0f%%)", this.ooc_thrash, 100.0 * this.ooc_thrash / this.ooc_occur);
        lw.add("OoC Replaced       = %d (%d during lockout)", this.ooc_replaced, this.ooc_replaced_lockout);
        if (this.FON.enabled) {
            lw.add();
            lw.add("Treants            = %d (once every %s)", this.FON.click_count, Fmt.msDur((this.FON.click_count > 0) ? ((long)(this.clock / this.FON.click_count)) : -1L));
        }
        lw.add();
        lw.add("FB Extra Energy    = %.1f", this.fb_extra / (double)this.FB.click_count);
        lw.add("FB During BitW     = %d", this.fb_bitw_count);
        lw.add();
        lw.add("Rake Clips         = %d (%d ticks) (average ratio %.2f%%)", this.rake_clip_count, this.rake_clip_ticks, 100.0 * this.rake_clip_coeff / this.rake_clip_count);
        lw.add("Rip Clips          = %d (%d ticks) (average ratio %.2f%%)", this.rip_clip_count, this.rip_clip_ticks, 100.0 * this.rip_clip_coeff / this.rip_clip_count);
        lw.add();
        lw.add("[Target Uptimes]");
        for (final Enemy x : this.targets) {
            final int time = x.isDead() ? x.death : this.clock;
            lw.add("%s(%s)", x.logPrefix, Fmt.msDur(time));
            lw.add("Uptime: Rake        = %6.2f%% (%d ticks)", 100.0 * x.rake_tickCount * 3000.0 / time, x.rake_tickCount);
            lw.add("Uptime: Rip         = %6.2f%% (%d ticks)", 100.0 * x.rip_tickCount * 2000.0 / time, x.rip_tickCount);
            lw.add("Uptime: Thrash      = %6.2f%% (%d ticks)", 100.0 * x.thrash_tickCount * 3000.0 / time, x.thrash_tickCount);
            lw.add("Uptime: Thrash/Bear = %6.2f%% (%d ticks)", 100.0 * x.thrashBear_tickCount * 3000.0 / time, x.thrashBear_tickCount);
            lw.add("Uptime: Weak Armor  = %6.2f%%", 100.0 * ((this.cfg.debuff_armor == WeakenedArmor.ALWAYS) ? 1.0 : (x.ff_uptime / (double)time)));
        }
    }
    
    void dumpModUse(final LineWriter lw) {
        lw.add(this.formatModUse("+SR", null, "Melee", true));
        lw.add(this.formatModUse("+SR", null, "Melee", false));
        lw.add(this.formatModUse("+TF", null, "Melee", false));
        lw.add();
        lw.add(this.formatModUse("+DoC", "Rip", "^Rip`\\d$", true));
        lw.add(this.formatModUse("+DoC", null, "Rip`5", true));
        lw.add(this.formatModUse("+TF", null, "Rip`5", true));
        lw.add(this.formatModUse("+DoC", "Rip Ticks", "^Rip`\\d#", true));
        lw.add();
        lw.add(this.formatModUse("+DoC", null, "Rake", true));
        lw.add(this.formatModUse("+DoC", "Rake Ticks", "^Rake#", true));
        lw.add();
        lw.add(this.formatModUse("+DoC", "FB", "FB`5", true));
        lw.add(this.formatModUse("+TF", "FB", "FB`5", true));
    }
    
    String formatModUse(final String mod, String name, String patt, final boolean keep) {
        int num = 0;
        int dem = 0;
        if (name == null) {
            name = patt;
            patt = '^' + patt + '$';
        }
        for (final Map.Entry<String, ArrayList<Damage>> x : this.dmgMap.entrySet()) {
            if (patt == null || x.getKey().matches(patt) == keep) {
                final ArrayList<Damage> list = x.getValue();
                num += Damage.count(list, mod);
                dem += list.size();
            }
        }
        String what;
        if (patt == null) {
            what = "";
        }
        else if (keep) {
            what = String.format(" (only %s)", name);
        }
        else {
            what = String.format(" (except %s)", name);
        }
        return String.format("%-10s %20s = %6.2f%% (%d of %d)", mod, what, 100.0 * num / dem, num, dem);
    }
    
    double _weapDPS(final boolean random) {
        return this._weapDPS_fixed() + this._weapDPS_chance(random);
    }
    
    double _weapDPS_fixed() {
        return 1 + this._intStat(FeralSim.STAT_WDMG, 0) + this.getAP() / 14.0;
    }
    
    double _weapDPS_chance(final boolean random) {
        return this.range(this.curGear.min, this.curGear.max, random) * 1000.0 / this.curGear.speed;
    }
    
    String physicalModStr() {
        switch (this.form) {
            case 1: {
                return this.catMods_melee(false);
            }
            case 2: {
                return this.bearMods(false, null, false);
            }
            default: {
                final StringBuilder sb = this.sb();
                this.appendProc_ap(sb);
                this.appendMulti(sb, this.allDmgMods.product);
                return sb.toString();
            }
        }
    }
    
    void autoAttack() {
        final AttackT atk = this.white(this.target, this.getMeleeCrit());
        ++this.melee_count;
        final String mods = this.printLog ? this.physicalModStr() : null;
        if (!atk.hit) {
            ++this.melee_miss;
            this.logMiss(this.target, "Melee", mods, atk);
            return;
        }
        final int swing = this.baseSwingTime();
        double dmg = this.target.meleeDmgMod() * this._weapDPS(this.random_ranges) * swing / 1000.0;
        switch (this.form) {
            case 1: {
                dmg *= this.catDmgMod(false) * this.modDmg_catAuto;
                break;
            }
            case 2: {
                dmg *= this.bearDmgMod(false);
                if (atk.crit && !this.disable_primalFury) {
                    this.addRage(15);
                    break;
                }
                break;
            }
            default: {
                dmg *= this.allDmgMods.product;
                break;
            }
        }
        if (atk.crit) {
            dmg *= this.critDmgMods.product;
        }
        if (atk.block) {
            dmg *= 0.7;
        }
        if (atk.glance) {
            dmg *= this.target.glanceMod();
        }
        this.recordDamage(this.target, "Melee", "Melee", mods, atk, dmg);
        this.triggerProcs(this.target, "Melee", Source.SWING, dmg, atk.crit, swing);
    }
    
    static int ceil(final double x) {
        return -(int)(-x);
    }
    
    void startSwing() {
        this.addEvent(new Event(this.clock + ceil(this.swingTime()), this.swingingKey));
    }
    
    void resetSwing() {
        ++this.swing_reset;
        this.startSwing();
    }
    
    final int baseSwingTime() {
        switch (this.form) {
            case 1: {
                return 1000;
            }
            case 2: {
                return 2500;
            }
            default: {
                return this.curGear.speed;
            }
        }
    }
    
    final double swingTime() {
        return this.baseSwingTime() / this.getMeleeSpeedMod();
    }
    
    final double energyTime() {
        return 100.0 / this.getMeleeHasteMod();
    }
    
    boolean willEnergyCap(final int ms) {
        return this.energy + this.energyGain(ms) > this.energyMax;
    }
    
    boolean willEnergyCap() {
        return this.willEnergyCap(1000);
    }
    
    boolean willEnergyCap(final CastSpell x) {
        return this.willEnergyCap(x.castTime());
    }
    
    int energyAfter(final int ms) {
        return Math.min(this.energyMax, this.energy + this.energyGain(ms));
    }
    
    int energyGain(final int ms) {
        return (int)(ms / this.energyTime());
    }
    
    int energyWasted(final int ms) {
        return Math.max(0, this.energy + this.energyGain(ms) - this.energyMax);
    }
    
    int timeToEnergyCap() {
        return this.timeToEnergy(this.energyMax);
    }
    
    int timeToCast(final CatSpell x) {
        return this.timeToEnergy(x.energyCost());
    }
    
    int timeToEnergy(final int want) {
        return (want > this.energy) ? this.timeToEnergyGain(want - this.energy) : 0;
    }
    
    int timeToEnergyGain(final int want) {
        return (int)(want * this.energyTime());
    }
    
    int timeToGainBackIfCast(final CatSpell x) {
        return Math.max(x.lockout(), this.timeToEnergyGain(x.energyCost()));
    }
    
    void startManaRegen() {
        this.addEvent(new Event(this.clock + 2000) {
            @Override
            void run() {
                FeralSim.this.addMana(240);
                FeralSim.this.startManaRegen();
            }
        });
    }
    
    void startEnergyRegen() {
        this.addEvent(new Event(this.clock + ceil(this.energyTime())) {
            @Override
            void run() {
                if (FeralSim.this.energy < FeralSim.this.energyMax) {
                    final FeralSim this$0 = FeralSim.this;
                    ++this$0.energy;
                    final FeralSim this$2 = FeralSim.this;
                    ++this$2.energy_regen;
                    final FeralSim this$3 = FeralSim.this;
                    ++this$3.energy_regen_max;
                }
                else if (FeralSim.this.form > 0) {
                    final FeralSim this$4 = FeralSim.this;
                    ++this$4.energy_regen_max;
                }
                if (FeralSim.this.printEnergy) {
                    FeralSim.this.print("Energy: %d", FeralSim.this.energy);
                }
                FeralSim.this.startEnergyRegen();
            }
        });
    }
    
    public void loadSimc(final String script, final String entry) {
        final HashMap<String, ArrayList<SimcAction>> listMap = new HashMap<String, ArrayList<SimcAction>>();
        final HashMap<String, String> map = new HashMap<String, String>();
        class SubActionList extends Action
        {
            final boolean swap = false;
            
            SubActionList(final boolean swap) {
                super(name);
            }
            
            @Override
            public void runAction() {
                throw new UnsupportedOperationException();
            }
        }
        for (String line2 : script.split("\n")) {
            final String line0 = line2;
            int pos = line2.indexOf(35);
            if (pos >= 0) {
                line2 = line2.substring(0, pos);
            }
            line2 = line2.trim();
            Label_1710: {
                if (!line2.isEmpty()) {
                    pos = line2.indexOf(58);
                    if (pos == -1) {
                        throw new RuntimeException("Missing sub-list");
                    }
                    final String listKey = line2.substring(0, pos).trim();
                    if (!listKey.equalsIgnoreCase("precombat")) {
                        line2 = line2.substring(pos + 1).trim();
                        pos = line2.indexOf(44);
                        String name;
                        String code;
                        if (pos == -1) {
                            name = line2;
                            code = "";
                        }
                        else {
                            name = line2.substring(0, pos).trim();
                            code = line2.substring(pos + 1).trim();
                        }
                        map.clear();
                        if (!code.isEmpty()) {
                            for (final String kv : code.split(",")) {
                                pos = kv.indexOf(61);
                                if (pos == -1) {
                                    throw new RuntimeException(String.format("Unknown code kv (%s)", kv));
                                }
                                map.put(kv.substring(0, pos).trim(), kv.substring(pos + 1).trim());
                            }
                        }
                        Action action;
                        try {
                            if ("auto_attack".equalsIgnoreCase(name)) {
                                break Label_1710;
                            }
                            if ("on_use".equalsIgnoreCase(name)) {
                                action = new Action("OnUse") {
                                    @Override
                                    public void runAction() {
                                        for (final Spell x : FeralSim.this.onUseSpells) {
                                            x.click();
                                        }
                                    }
                                };
                            }
                            else if ("use_item".equalsIgnoreCase(name)) {
                                final String slot = map.get("slot");
                                if (slot == null) {
                                    throw new RuntimeException("Missing use_item slot: " + line0);
                                }
                                if (slot.equalsIgnoreCase(SlotT.HANDS.name)) {
                                    action = this.SYNAPSE_SPRINGS;
                                }
                                else {
                                    if (!slot.equalsIgnoreCase(SlotT.WAIST.name)) {
                                        throw new RuntimeException(String.format("Unable to determine use_item (%s): %s", slot, line0));
                                    }
                                    action = this.FRAG_BELT;
                                }
                            }
                            else if ("pool_resource".equalsIgnoreCase(name)) {
                                final String wait = map.get("wait");
                                int time;
                                if (wait == null) {
                                    time = this.cfg.poolMax;
                                }
                                else {
                                    try {
                                        time = (int)(1000.0 * Double.parseDouble(wait));
                                    }
                                    catch (NumberFormatException err2) {
                                        throw new RuntimeException(String.format("Invalid wait expr (%s): %s", wait, line0));
                                    }
                                    if (time < 1) {
                                        throw new RuntimeException(String.format("Invalid wait time (%s): %s", time, line0));
                                    }
                                }
                                final String expr = map.get("extra_amount");
                                int extra;
                                if (expr == null) {
                                    extra = 0;
                                }
                                else {
                                    try {
                                        extra = Integer.parseInt(expr);
                                    }
                                    catch (NumberFormatException err3) {
                                        throw new RuntimeException(String.format("Invalid extra_amount (%s): %s", expr, line0));
                                    }
                                    if (extra < 0) {
                                        throw new RuntimeException(String.format("Negative extra_amount (%d): %s", extra, line0));
                                    }
                                }
                                final String for_spell = map.get("for");
                                if (for_spell != null) {
                                    final Action spell0 = this.getNamedAction(for_spell);
                                    if (spell0 == null) {
                                        throw new RuntimeException(String.format("Unknown spell (%s): %s", for_spell, line0));
                                    }
                                    if (!(spell0 instanceof CatSpell)) {
                                        throw new RuntimeException(String.format("Unsupported for spell (%s): %s", spell0, line0));
                                    }
                                    final CatSpell spell2 = (CatSpell)spell0;
                                    action = new Action(String.format("PoolFor(%s,%dms)", spell2.actionName, time)) {
                                        @Override
                                        public void runAction() {
                                            if (spell2.usable() && spell2.affordable()) {
                                                return;
                                            }
                                            if (extra > 0) {
                                                final FeralSim this$0 = FeralSim.this;
                                                this$0.energy += extra;
                                                final boolean limited = spell2.usable() && spell2.affordable();
                                                final FeralSim this$2 = FeralSim.this;
                                                this$2.energy -= extra;
                                                if (!limited) {
                                                    return;
                                                }
                                            }
                                            FeralSim.this._startLockout_pool(Math.min(time, FeralSim.this.timeToEnergy(spell2.energyCost())));
                                            throw new Clicked();
                                        }
                                        
                                        @Override
                                        public boolean actionable() {
                                            return !FeralSim.this.lockoutKey.exists;
                                        }
                                    };
                                }
                                else {
                                    boolean for_next = false;
                                    final String expr2 = map.get("for_next");
                                    if (expr2 != null) {
                                        try {
                                            for_next = (Double.parseDouble(expr2) != 0.0);
                                        }
                                        catch (NumberFormatException err4) {
                                            throw new RuntimeException(String.format("Invalid for_next value (%s): %s", expr2, line0));
                                        }
                                    }
                                    if (for_next) {
                                        action = new Sequenced() {
                                            @Override
                                            Action compile(Action nextAction) {
                                                while (nextAction instanceof DebugAction) {
                                                    nextAction = ((DebugAction)nextAction).inner;
                                                }
                                                if (nextAction instanceof CatSpell) {
                                                    final CatSpell spell = (CatSpell)nextAction;
                                                    return new Action(String.format("PoolFor(%s,%dms)", spell.actionName, time)) {
                                                        @Override
                                                        public void runAction() {
                                                            if (spell.usable() && spell.affordable()) {
                                                                return;
                                                            }
                                                            if (extra > 0) {
                                                                final FeralSim this$0 = FeralSim.this;
                                                                this$0.energy += extra;
                                                                final boolean limited = spell.usable() && spell.affordable();
                                                                final FeralSim this$2 = FeralSim.this;
                                                                this$2.energy -= extra;
                                                                if (!limited) {
                                                                    return;
                                                                }
                                                            }
                                                            FeralSim.this._startLockout_pool(Math.min(time, FeralSim.this.timeToEnergy(spell.energyCost())));
                                                            throw new Clicked();
                                                        }
                                                        
                                                        @Override
                                                        public boolean actionable() {
                                                            return !FeralSim.this.lockoutKey.exists;
                                                        }
                                                    };
                                                }
                                                throw new RuntimeException(String.format("Unsupported for_next spell (%s): %s", nextAction, line0));
                                            }
                                        };
                                    }
                                    else {
                                        action = new Action(String.format("Pool(%dms)", time)) {
                                            @Override
                                            public void runAction() {
                                                FeralSim.this._startLockout_pool(time);
                                                throw new Clicked();
                                            }
                                            
                                            @Override
                                            public boolean actionable() {
                                                return !FeralSim.this.lockoutKey.exists;
                                            }
                                        };
                                    }
                                }
                            }
                            else if (name.endsWith("_potion")) {
                                action = new Action("Potion") {
                                    @Override
                                    public void runAction() {
                                        FeralSim.this.pot_click();
                                    }
                                    
                                    @Override
                                    public boolean actionable() {
                                        return FeralSim.this.pot_clickable();
                                    }
                                };
                            }
                            else if ("alt_weapon".equalsIgnoreCase(name)) {
                                action = this.swapGear.swap;
                            }
                            else if ("main_weapon".equalsIgnoreCase(name)) {
                                action = this.mainGear.swap;
                            }
                            else if ("enqueue_main_weapon".equalsIgnoreCase(name)) {
                                action = new Action("MainWeapon.queued") {
                                    @Override
                                    public void runAction() {
                                        FeralSim.this.queuedMainSwap = true;
                                    }
                                };
                            }
                            else if ("run_action_list".equalsIgnoreCase(name)) {
                                final String key = map.get("name");
                                if (key == null) {
                                    throw new RuntimeException(String.format("Missing name: %s", line0));
                                }
                                action = new SubActionList(key);
                            }
                            else if ("swap_action_list".equalsIgnoreCase(name)) {
                                final String key = map.get("name");
                                if (key == null) {
                                    throw new RuntimeException(String.format("Missing name: %s", line0));
                                }
                                action = new SubActionList(key);
                            }
                            else {
                                action = this.getNamedAction(name);
                                if (action == null) {
                                    throw new RuntimeException(String.format("Unknown action (%s): %s", name, line0));
                                }
                            }
                        }
                        catch (RuntimeException err) {
                            throw new RuntimeException("Parse error: " + err.getMessage());
                        }
                        this.parsingAction = action;
                        final Expr require = this.simc_parseCond(map.get("req"));
                        if (require == null || require.getBoolean()) {
                            Expr cond = this.simc_parseCond(map.get("if"));
                            final String sync = map.get("sync");
                            if (sync != null) {
                                if (!"tigers_fury".equalsIgnoreCase(sync)) {
                                    throw new RuntimeException(String.format("Unknown sync (%s)", sync));
                                }
                                final Expr syncCond = new NamedExpr("TF.sync") {
                                    @Override
                                    boolean getBoolean() {
                                        return FeralSim.this.TF.ready();
                                    }
                                };
                                if (cond == null) {
                                    cond = syncCond;
                                }
                                else {
                                    cond = new BinaryExpr_And(syncCond, cond);
                                }
                            }
                            final String interuptStr = map.get("interrupt_if");
                            if (interuptStr != null) {
                                final Expr interrupt = interuptStr.equalsIgnoreCase("if") ? cond : this.simc_parseCond(interuptStr);
                                if (!(action instanceof ChanSpell)) {
                                    throw new RuntimeException("Uninterruptible action: " + action);
                                }
                                final ChanSpell inner = (ChanSpell)action;
                                action = new Action("Interruptable:" + inner.actionName) {
                                    @Override
                                    public void runAction() {
                                        try {
                                            inner.click();
                                        }
                                        catch (Clicked ex) {
                                            if (FeralSim.this.castingKey.exists) {
                                                FeralSim.this.castingExpr = interrupt;
                                            }
                                            throw ex;
                                        }
                                    }
                                };
                            }
                            final String debug = map.get("debug");
                            if (debug != null) {
                                action = new DebugAction(action, debug);
                            }
                            final boolean cycle = Utils.parseTruth(map.get("cycle_targets"));
                            if (cond == null) {
                                cond = FeralSim.EXPR_TRUE;
                            }
                            ArrayList<SimcAction> list = listMap.get(listKey);
                            if (list == null) {
                                list = new ArrayList<SimcAction>();
                                listMap.put(listKey, list);
                            }
                            list.add(new SimcAction(action, cond, cycle));
                        }
                    }
                }
            }
        }
        if (listMap.isEmpty()) {
            throw new RuntimeException("No action lists");
        }
        final HashMap<String, SimcAction[]> compiledMap = new HashMap<String, SimcAction[]>();
        for (final Map.Entry<String, ArrayList<SimcAction>> e : listMap.entrySet()) {
            final ArrayList<SimcAction> list2 = e.getValue();
            compiledMap.put(e.getKey(), list2.toArray(new SimcAction[list2.size()]));
        }
        final SimcAction[] main = compiledMap.get(entry);
        if (main == null) {
            throw new RuntimeException("Missing entry action list: " + entry);
        }
        final SimcLogic logic = new SimcLogic(main);
        for (final SimcAction[] list3 : compiledMap.values()) {
            for (int i = 0; i < list3.length; ++i) {
                final SimcAction temp = list3[i];
                Action action2;
                for (action2 = temp.action; action2 instanceof DebugAction; action2 = ((DebugAction)action2).inner) {}
                abstract class Sequenced extends Action
                {
                    Sequenced() {
                        super("");
                    }
                    
                    @Override
                    public void runAction() {
                        throw new UnsupportedOperationException();
                    }
                    
                    abstract Action compile(final Action p0);
                }
                if (action2 instanceof Sequenced) {
                    list3[i] = new SimcAction(((Sequenced)temp.action).compile(list3[i + 1].action), temp.cond, temp.cycle);
                }
                else if (action2 instanceof SubActionList) {
                    final boolean swap = ((SubActionList)action2).swap;
                    final String name2 = action2.actionName;
                    final SimcAction[] subList = compiledMap.get(name2);
                    if (subList == null) {
                        throw new RuntimeException("Unknown action list: " + name2);
                    }
                    Action a;
                    if (swap) {
                        a = new Action("Swap Action List: " + name2) {
                            @Override
                            public void runAction() {
                                logic.current = subList;
                                if (FeralSim.this.printLog) {
                                    FeralSim.this.print(this.actionName);
                                }
                                throw new Clicked();
                            }
                        };
                    }
                    else {
                        a = new Action("Run Action List: " + name2) {
                            @Override
                            public void runAction() {
                                logic.execute(subList);
                            }
                        };
                    }
                    list3[i] = new SimcAction(a, temp.cond, temp.cycle);
                }
            }
        }
        this.main_logic = logic;
        this.loadedScript = script;
    }
    
    Action getNamedAction(final String name) {
        if ("skull_bash".equalsIgnoreCase(name)) {
            return this.SKULL_BASH;
        }
        if ("berserking".equalsIgnoreCase(name)) {
            return this.BERSERKING;
        }
        if ("heart_of_the_wild".equalsIgnoreCase(name)) {
            return this.HOTW;
        }
        if ("wrath".equalsIgnoreCase(name)) {
            return this.WRATH;
        }
        if ("hurricane".equalsIgnoreCase(name)) {
            return this.HURRICANE;
        }
        if ("cat_form".equalsIgnoreCase(name)) {
            return this.CAT_FORM;
        }
        if ("bear_form".equalsIgnoreCase(name)) {
            return this.BEAR_FORM;
        }
        if ("savage_roar".equalsIgnoreCase(name)) {
            return this.SR;
        }
        if ("healing_touch".equalsIgnoreCase(name)) {
            return this.HT;
        }
        if ("tigers_fury".equalsIgnoreCase(name)) {
            return this.TF;
        }
        if ("berserk".equalsIgnoreCase(name)) {
            return this.BERSERK;
        }
        if ("natures_vigil".equalsIgnoreCase(name)) {
            return this.NV;
        }
        if ("incarnation".equalsIgnoreCase(name)) {
            return this.KOTJ;
        }
        if ("ferocious_bite".equalsIgnoreCase(name)) {
            return this.FB;
        }
        if ("faerie_fire".equalsIgnoreCase(name)) {
            return this.FF;
        }
        if ("thrash_cat".equalsIgnoreCase(name)) {
            return this.THRASH;
        }
        if ("thrash_bear".equalsIgnoreCase(name)) {
            return this.THRASH_BEAR;
        }
        if ("mangle_bear".equalsIgnoreCase(name)) {
            return this.MANGLE_BEAR;
        }
        if ("maul".equalsIgnoreCase(name)) {
            return this.MAUL;
        }
        if ("rip".equalsIgnoreCase(name)) {
            return this.RIP;
        }
        if ("rake".equalsIgnoreCase(name)) {
            return this.RAKE;
        }
        if ("ravage".equalsIgnoreCase(name)) {
            return this.RAVAGE;
        }
        if ("shred".equalsIgnoreCase(name)) {
            return this.SHRED;
        }
        if ("soul_swap".equalsIgnoreCase(name)) {
            return this.SOUL_SWAP;
        }
        if ("redirect".equalsIgnoreCase(name)) {
            return this.REDIRECT;
        }
        if ("mangle_cat".equalsIgnoreCase(name)) {
            return this.MANGLE;
        }
        if ("swipe_cat".equalsIgnoreCase(name)) {
            return this.SWIPE;
        }
        if ("ursols_vortex".equalsIgnoreCase(name)) {
            return this.URSOLS_VORTEX;
        }
        return null;
    }
    
    Expr simc_parseCond(final String expr) {
        if (expr == null) {
            return null;
        }
        final Input in = new Input(expr.toCharArray());
        final Expr cond = this.simc_parseLeft(in, 0);
        if (in.more()) {
            throw new RuntimeException("Incomplete: " + expr + " -> " + new String(in.buf, in.pos, in.buf.length - in.pos));
        }
        return cond;
    }
    
    static Expr _makeDouble(final double x) {
        return new Expr() {
            @Override
            double getDouble() {
                return x;
            }
            
            @Override
            public String toString() {
                final int i = (int)x;
                if (i == x) {
                    return Integer.toString(i);
                }
                return Double.toString(x);
            }
        };
    }
    
    Expr simc_parseRight(final Input in, Expr cond, final int order) {
        while (in.more()) {
            final char ch = in.peek();
            if (Character.isWhitespace(ch)) {
                ++in.pos;
            }
            else if (ch == '<') {
                if (order > 3) {
                    return cond;
                }
                ++in.pos;
                if (in.peek() == '=') {
                    ++in.pos;
                    cond = new BinaryExpr("LE", cond, this.simc_parseLeft(in, 3)) {
                        public boolean getBoolean() {
                            return this.left.getDouble() <= this.right.getDouble();
                        }
                    };
                }
                else {
                    cond = new BinaryExpr("LT", cond, this.simc_parseLeft(in, 3)) {
                        public boolean getBoolean() {
                            return this.left.getDouble() < this.right.getDouble();
                        }
                    };
                }
            }
            else if (ch == '>') {
                if (order > 3) {
                    return cond;
                }
                ++in.pos;
                if (in.peek() == '=') {
                    ++in.pos;
                    cond = new BinaryExpr("GE", cond, this.simc_parseLeft(in, 3)) {
                        public boolean getBoolean() {
                            return this.left.getDouble() >= this.right.getDouble();
                        }
                    };
                }
                else {
                    cond = new BinaryExpr("GT", cond, this.simc_parseLeft(in, 3)) {
                        public boolean getBoolean() {
                            return this.left.getDouble() > this.right.getDouble();
                        }
                    };
                }
            }
            else if (ch == '=') {
                if (order > 3) {
                    return cond;
                }
                ++in.pos;
                cond = new BinaryExpr("EQ", cond, this.simc_parseLeft(in, 3)) {
                    public boolean getBoolean() {
                        return this.left.getDouble() == this.right.getDouble();
                    }
                };
            }
            else if (ch == '+') {
                if (order > 8) {
                    return cond;
                }
                ++in.pos;
                cond = new BinaryExpr("Plus", cond, this.simc_parseLeft(in, 8)) {
                    public double getDouble() {
                        return this.left.getDouble() + this.right.getDouble();
                    }
                };
            }
            else if (ch == '-') {
                if (order > 8) {
                    return cond;
                }
                ++in.pos;
                cond = new BinaryExpr("Sub", cond, this.simc_parseLeft(in, 8)) {
                    public double getDouble() {
                        return this.left.getDouble() - this.right.getDouble();
                    }
                };
            }
            else if (ch == '*') {
                if (order > 9) {
                    return cond;
                }
                ++in.pos;
                cond = new BinaryExpr("Times", cond, this.simc_parseLeft(in, 9)) {
                    public double getDouble() {
                        return this.left.getDouble() * this.right.getDouble();
                    }
                };
            }
            else if (ch == '%') {
                if (order > 9) {
                    return cond;
                }
                ++in.pos;
                cond = new BinaryExpr("Div", cond, this.simc_parseLeft(in, 9)) {
                    public double getDouble() {
                        return this.left.getDouble() / this.right.getDouble();
                    }
                };
            }
            else if (ch == '|') {
                if (order > 0) {
                    return cond;
                }
                ++in.pos;
                cond = new BinaryExpr("Or", cond, this.simc_parseLeft(in, 0)) {
                    public boolean getBoolean() {
                        return this.left.getBoolean() || this.right.getBoolean();
                    }
                };
            }
            else {
                if (ch != '&') {
                    break;
                }
                if (order > 2) {
                    return cond;
                }
                ++in.pos;
                cond = new BinaryExpr_And(cond, this.simc_parseLeft(in, 2));
            }
        }
        return cond;
    }
    
    Expr simc_parseLeft(final Input in, final int order) {
        while (in.more()) {
            char ch = in.peek();
            if (Character.isWhitespace(ch)) {
                ++in.pos;
            }
            else if (ch == '(') {
                ++in.pos;
                final Expr left = this.simc_parseLeft(in, 0);
                if (in.peek() != ')') {
                    throw new RuntimeException("Missing closing paren");
                }
                ++in.pos;
                return this.simc_parseRight(in, left, order);
            }
            else {
                if (ch == '!') {
                    ++in.pos;
                    return this.simc_parseRight(in, new UnaryExpr_Not(this.simc_parseLeft(in, 10)), order);
                }
                if (ch == '-' && !isDigitChar(in.peek(1))) {
                    ++in.pos;
                    return this.simc_parseRight(in, new UnaryExpr_Minus(this.simc_parseLeft(in, 10)), order);
                }
                if (ch != '$') {
                    final int pos = in.pos++;
                    while (in.more() && isIdentifierChar(in.peek())) {
                        ++in.pos;
                    }
                    final String name = new String(in.buf, pos, in.pos - pos);
                    if (in.more() && in.peek() == '(') {
                        final ArrayList<Expr> args = new ArrayList<Expr>();
                        ++in.pos;
                        while (in.more()) {
                            ch = in.peek();
                            if (Character.isWhitespace(ch)) {
                                ++in.pos;
                            }
                            else {
                                if (ch == ')') {
                                    ++in.pos;
                                    final Expr expr = this.simc_parseFunction(name, args.toArray(new Expr[args.size()]));
                                    return this.simc_parseRight(in, expr, order);
                                }
                                args.add(this.simc_parseLeft(in, 0));
                            }
                        }
                        throw new RuntimeException("Missing function close");
                    }
                    final Expr expr = this.simc_parseVariable(name);
                    return this.simc_parseRight(in, expr, order);
                }
                ++in.pos;
                if (in.peek() != '(') {
                    throw new RuntimeException("Missing function open");
                }
                ++in.pos;
                final int pos = in.pos;
                while (in.more() && isIdentifierChar(in.peek())) {
                    ++in.pos;
                }
                final String name = new String(in.buf, pos, in.pos - pos).trim();
                if (in.peek() != ')') {
                    throw new RuntimeException("Missing function close");
                }
                ++in.pos;
                return this.simc_parseRight(in, this.simc_parseVariable(name), order);
            }
        }
        throw new RuntimeException("Unexpected end of line");
    }
    
    static boolean isIdentifierChar(final char ch) {
        return Character.isLetterOrDigit(ch) || ch == '_' || ch == '.';
    }
    
    static boolean isDigitChar(final char ch) {
        return Character.isDigit(ch) || ch == '.';
    }
    
    Expr simc_parseFunction(final String name, final Expr[] args) {
        if (name.equalsIgnoreCase("rake_ratio")) {
            if (args.length == 0) {
                return new Expr() {
                    @Override
                    double getDouble() {
                        return (FeralSim.this.target.rake_tickLeft > 0) ? (FeralSim.this.rakeTickDmgNew() / FeralSim.this.target.rake_avgDmg) : Double.POSITIVE_INFINITY;
                    }
                };
            }
            if (args.length == 1) {
                final Expr ahead = args[0];
                return new Expr() {
                    @Override
                    double getDouble() {
                        return (FeralSim.this.target.rake_tickLeft > 0) ? (FeralSim.this.rakeTickDmgFuture((int)(ahead.getDouble() * 1000.0)) / FeralSim.this.target.rake_avgDmg) : Double.POSITIVE_INFINITY;
                    }
                };
            }
            final Expr t0 = args[0];
            final Expr t2 = args[1];
            return new Expr() {
                @Override
                double getDouble() {
                    return FeralSim.this.rakeTickDmgFuture((int)(t0.getDouble() * 1000.0)) / FeralSim.this.rakeTickDmgFuture((int)(t2.getDouble() * 1000.0));
                }
            };
        }
        else if (name.equalsIgnoreCase("rip_ratio")) {
            if (args.length == 0) {
                return new Expr() {
                    @Override
                    double getDouble() {
                        return (FeralSim.this.target.rip_tickLeft > 0) ? (FeralSim.this.ripTickDmgNew() / FeralSim.this.target.rip_avgDmg) : Double.POSITIVE_INFINITY;
                    }
                };
            }
            if (args.length == 1) {
                final Expr ahead = args[0];
                return new Expr() {
                    @Override
                    double getDouble() {
                        return (FeralSim.this.target.rip_tickLeft > 0) ? (FeralSim.this.ripTickDmgFuture((int)(ahead.getDouble() * 1000.0)) / FeralSim.this.target.rip_avgDmg) : Double.POSITIVE_INFINITY;
                    }
                };
            }
            final Expr t0 = args[0];
            final Expr t2 = args[1];
            return new Expr() {
                @Override
                double getDouble() {
                    return FeralSim.this.ripTickDmgFuture((int)(t0.getDouble() * 1000.0)) / FeralSim.this.ripTickDmgFuture((int)(t2.getDouble() * 1000.0));
                }
            };
        }
        else {
            if (name.equalsIgnoreCase("rip_delta")) {
                final Expr ahead = (args.length == 0) ? _makeDouble(0.0) : args[0];
                return new Expr() {
                    @Override
                    double getDouble() {
                        return (FeralSim.this.ripTickDmgFuture((int)(ahead.getDouble() * 1000.0)) - FeralSim.this.target.rip_avgDmg) / FeralSim.this.avgDmg_player(FeralSim.this._ripTickDmg0, FeralSim.this._crit0);
                    }
                };
            }
            if (name.equalsIgnoreCase("rake_delta")) {
                if (args.length >= 2) {
                    final Expr t0 = args[0];
                    final Expr t2 = args[1];
                    return new Expr() {
                        @Override
                        double getDouble() {
                            return (FeralSim.this.rakeTickDmgFuture((int)(t2.getDouble() * 1000.0)) - FeralSim.this.rakeTickDmgFuture((int)(t0.getDouble() * 1000.0))) / FeralSim.this.avgDmg_player(FeralSim.this._rakeTickDmg0, FeralSim.this._crit0);
                        }
                    };
                }
                final Expr ahead = (args.length == 0) ? _makeDouble(0.0) : args[0];
                return new Expr() {
                    @Override
                    double getDouble() {
                        return (FeralSim.this.rakeTickDmgFuture((int)(ahead.getDouble() * 1000.0)) - FeralSim.this.target.rake_avgDmg) / FeralSim.this.avgDmg_player(FeralSim.this._rakeTickDmg0, FeralSim.this._crit0);
                    }
                };
            }
            else if (name.equalsIgnoreCase("rake_power")) {
                if (args.length == 0) {
                    return new Expr() {
                        @Override
                        double getDouble() {
                            return (FeralSim.this.target.rake_tickLeft > 0) ? (FeralSim.this.target.rake_avgDmg / FeralSim.this.avgDmg_player(FeralSim.this._rakeTickDmg0, FeralSim.this._crit0)) : 0.0;
                        }
                    };
                }
                final Expr ahead = args[0];
                return new Expr() {
                    @Override
                    double getDouble() {
                        return FeralSim.this.rakeTickDmgFuture((int)(ahead.getDouble() * 1000.0)) / FeralSim.this.avgDmg_player(FeralSim.this._rakeTickDmg0, FeralSim.this._crit0);
                    }
                };
            }
            else if (name.equalsIgnoreCase("rip_power")) {
                if (args.length == 0) {
                    return new Expr() {
                        @Override
                        double getDouble() {
                            return (FeralSim.this.target.rip_tickLeft > 0) ? (FeralSim.this.target.rip_avgDmg / FeralSim.this.avgDmg_player(FeralSim.this._ripTickDmg0, FeralSim.this._crit0)) : 0.0;
                        }
                    };
                }
                final Expr ahead = args[0];
                return new Expr() {
                    @Override
                    double getDouble() {
                        return FeralSim.this.ripTickDmgFuture((int)(ahead.getDouble() * 1000.0)) / FeralSim.this.avgDmg_player(FeralSim.this._ripTickDmg0, FeralSim.this._crit0);
                    }
                };
            }
            else {
                if (!name.equalsIgnoreCase("rip_damage")) {
                    throw new RuntimeException("Unknown function: " + name);
                }
                if (args.length == 0) {
                    return new Expr() {
                        @Override
                        double getDouble() {
                            return (FeralSim.this.target.rip_tickLeft > 0) ? FeralSim.this.target.rip_avgDmg : 0.0;
                        }
                    };
                }
                final Expr ahead = args[0];
                return new Expr() {
                    @Override
                    double getDouble() {
                        return FeralSim.this.ripTickDmgFuture((int)(ahead.getDouble() * 1000.0));
                    }
                };
            }
        }
    }
    
    Expr simc_parseVariable(final String s) {
        if (s.isEmpty()) {
            throw new RuntimeException("Empty Variable");
        }
        if ("time".equalsIgnoreCase(s)) {
            return this.EXPR_CLOCK;
        }
        if ("remains".equalsIgnoreCase(s)) {
            return null;
        }
        if ("energy".equalsIgnoreCase(s)) {
            return this.EXPR_ENERGY;
        }
        if ("energy_deficit".equalsIgnoreCase(s)) {
            return this.EXPR_ENERGY_DEFICIT;
        }
        if ("energy_tf_gap".equalsIgnoreCase(s)) {
            return this.EXPR_ENERGY_TF_GAP;
        }
        if ("active_enemies".equalsIgnoreCase(s)) {
            return this.EXPR_ACTIVE_TARGET_COUNT;
        }
        if ("min_cleave_size".equalsIgnoreCase(s)) {
            return this.EXPR_MIN_CLEAVE_SIZE;
        }
        if ("energy.regen".equalsIgnoreCase(s)) {
            return this.EXPR_ENERGY_REGEN;
        }
        if ("generator".equalsIgnoreCase(s)) {
            return this.EXPR_GENERATOR_MODE;
        }
        if ("ff_style".equalsIgnoreCase(s)) {
            return this.EXPR_FF_MODE;
        }
        if ("thrash_style".equalsIgnoreCase(s)) {
            return this.EXPR_THRASH_MODE;
        }
        if ("finisher0".equalsIgnoreCase(s)) {
            return this.EXPR_FINISHER0_MODE;
        }
        if ("finisher_count5".equalsIgnoreCase(s)) {
            return this.EXPR_FINISHER_COUNT5;
        }
        if ("hotw_before".equalsIgnoreCase(s)) {
            return this.EXPR_HOTW_BEFORE_BERSERK;
        }
        if ("hotw_bitw".equalsIgnoreCase(s)) {
            return this.EXPR_HOTW_BITW;
        }
        if ("hotw_swap".equalsIgnoreCase(s)) {
            return this.EXPR_HOTW_SWAP;
        }
        if ("hotw_wrath".equalsIgnoreCase(s)) {
            return this.EXPR_HOTW_WRATH;
        }
        if ("hotw_hurricane".equalsIgnoreCase(s)) {
            return this.EXPR_HOTW_HURRICANE;
        }
        if ("set_bonus.tier15_2pc_melee".equalsIgnoreCase(s)) {
            return new NamedExpr("T15/2p") {
                @Override
                boolean getBoolean() {
                    return FeralSim.this.bonus_t15_2p;
                }
            };
        }
        if ("thrash_cat_perc".equalsIgnoreCase(s)) {
            return new NamedExpr("ThrashCat:Perc") {
                @Override
                double getDouble() {
                    int num = 0;
                    for (final Enemy x : FeralSim.this.activeTargets) {
                        if (x.thrash_tickLeft > 0) {
                            ++num;
                        }
                    }
                    return (num > 0) ? (num / FeralSim.this.activeTargets.size()) : 0.0;
                }
            };
        }
        if ("enabled".equalsIgnoreCase(s)) {
            if (this.parsingAction instanceof Spell) {
                return ((Spell)this.parsingAction).enabled ? FeralSim.EXPR_TRUE : FeralSim.EXPR_FALSE;
            }
            throw new RuntimeException("Unknown enabled state: " + this.parsingAction);
        }
        else if ("haste_improvement".equalsIgnoreCase(s)) {
            if (this.parsingAction instanceof ChanSpell) {
                final ChanSpell spell = (ChanSpell)this.parsingAction;
                return new NamedExpr("HasteImprovment") {
                    @Override
                    boolean getBoolean() {
                        return spell.channelTime() >= spell.lockoutTime && spell.tickFreq() < spell.tick_freq;
                    }
                };
            }
            throw new RuntimeException("Unknown haste ratio: " + this.parsingAction);
        }
        else {
            if ("energy.time_to_max".equalsIgnoreCase(s)) {
                return this.EXPR_ENERGY_TIME_TO_MAX;
            }
            if ("fon_thres".equalsIgnoreCase(s)) {
                return this.EXPR_FON_DMG;
            }
            if ("combo_points".equalsIgnoreCase(s)) {
                return this.EXPR_COMBO;
            }
            if ("target.time_to_die".equalsIgnoreCase(s)) {
                return this.EXPR_TARGET_TTL;
            }
            if ("target.time_to_bitw".equalsIgnoreCase(s)) {
                return this.EXPR_TARGET_TIME_TO_BITW;
            }
            if ("target.bitw".equalsIgnoreCase(s)) {
                return this.EXPR_TARGET_BITW;
            }
            if ("target.health.pct".equalsIgnoreCase(s)) {
                return this.EXPR_TARGET_PERC_HP;
            }
            if ("talent.dream_of_cenarius.enabled".equalsIgnoreCase(s)) {
                return this.EXPR_TALENT_DOC;
            }
            if ("talent.soul_of_the_forest.enabled".equalsIgnoreCase(s)) {
                return this.EXPR_TALENT_SOTF;
            }
            if ("talent.natures_vigil.enabled".equalsIgnoreCase(s)) {
                return this.EXPR_TALENT_NV;
            }
            if ("talent.incarnation.enabled".equalsIgnoreCase(s)) {
                return this.EXPR_TALENT_KOTJ;
            }
            if ("buff.feral_fury.react".equalsIgnoreCase(s)) {
                return this.EXPR_T16_2P_REACT;
            }
            if ("buff.feral_rage.up".equalsIgnoreCase(s) || "buff.feral_rage.remains".equalsIgnoreCase(s)) {
                return this.EXPR_T16_4P_TIME;
            }
            if ("rake_clip_mod".equalsIgnoreCase(s)) {
                return new NamedExpr("Rake.clipMod") {
                    @Override
                    double getDouble() {
                        return FeralSim.this.cfg.rakeClipMultiplier;
                    }
                };
            }
            if ("rip_clip_mod".equalsIgnoreCase(s)) {
                return new NamedExpr("Rip.clipMod") {
                    @Override
                    double getDouble() {
                        return FeralSim.this.cfg.ripClipMultiplier;
                    }
                };
            }
            if ("buff.king_of_the_jungle.up".equalsIgnoreCase(s)) {
                return new NamedExpr("KOTJ.time") {
                    @Override
                    double getDouble() {
                        return FeralSim.this.KOTJ.timeLeft() / 1000.0;
                    }
                };
            }
            if ("buff.king_of_the_jungle.down".equalsIgnoreCase(s)) {
                return new NamedExpr("KOTJ.down") {
                    @Override
                    boolean getBoolean() {
                        return !FeralSim.this.KOTJ.up();
                    }
                };
            }
            if ("buff.tigers_fury.up".equalsIgnoreCase(s) || "buff.tigers_fury.remains".equalsIgnoreCase(s)) {
                return this.EXPR_TF_TIME;
            }
            if ("has_rune".equalsIgnoreCase(s)) {
                return this.EXPR_RUNE_EXISTS;
            }
            if ("buff.rune_of_reorigination.remains".equalsIgnoreCase(s) || "buff.rune_of_reorigination.up".equalsIgnoreCase(s)) {
                return this.EXPR_RUNE_MASTERY_TIME;
            }
            if ("cooldown.tigers_fury.remains".equalsIgnoreCase(s)) {
                return new NamedExpr("TF.ready") {
                    @Override
                    double getDouble() {
                        return FeralSim.this.TF.timeUntilReady() / 1000.0;
                    }
                };
            }
            if ("cooldown.berserk.remains".equalsIgnoreCase(s)) {
                return new NamedExpr("Berserk.ready") {
                    @Override
                    double getDouble() {
                        return FeralSim.this.BERSERK.timeUntilReady() / 1000.0;
                    }
                };
            }
            if ("buff.berserk.up".equalsIgnoreCase(s) || "buff.berserk.remains".equalsIgnoreCase(s)) {
                return new NamedExpr("Berserk.time") {
                    @Override
                    double getDouble() {
                        return FeralSim.this.BERSERK.timeLeft() / 1000.0;
                    }
                };
            }
            if ("buff.berserk.down".equalsIgnoreCase(s)) {
                return new NamedExpr("Berserk.down") {
                    @Override
                    boolean getBoolean() {
                        return !FeralSim.this.BERSERK.up();
                    }
                };
            }
            if ("buff.natures_vigil.up".equalsIgnoreCase(s)) {
                return this.EXPR_NV_TIME;
            }
            if ("buff.predatory_swiftness.up".equalsIgnoreCase(s) || "buff.predatory_swiftness.remains".equalsIgnoreCase(s)) {
                return this.EXPR_PS_TIME;
            }
            if ("buff.predatory_swiftness.down".equalsIgnoreCase(s)) {
                return new UnaryExpr_Not(this.EXPR_PS_TIME);
            }
            if ("dot.thrash_cat.remains".equalsIgnoreCase(s)) {
                return this.EXPR_THRASH_TIME;
            }
            if ("dot.thrash_bear.remains".equalsIgnoreCase(s)) {
                return this.EXPR_THRASH_BEAR_TIME;
            }
            if ("buff.dream_of_cenarius.up".equalsIgnoreCase(s) || "buff.dream_of_cenarius.stack".equalsIgnoreCase(s)) {
                return this.EXPR_DOC_CHARGES;
            }
            if ("buff.dream_of_cenarius.down".equalsIgnoreCase(s)) {
                return new UnaryExpr_Not(this.EXPR_DOC_CHARGES);
            }
            if ("buff.savage_roar.up".equalsIgnoreCase(s) || "buff.savage_roar.remains".equalsIgnoreCase(s)) {
                return this.EXPR_SR_TIME;
            }
            if ("buff.savage_roar.down".equalsIgnoreCase(s)) {
                return new UnaryExpr_Not(this.EXPR_SR_TIME);
            }
            if ("buff.heart_of_the_wild.up".equalsIgnoreCase(s) || "buff.heart_of_the_wild.remains".equalsIgnoreCase(s)) {
                return this.EXPR_HOTW_TIME;
            }
            if ("buff.cat_form.up".equalsIgnoreCase(s)) {
                return this.EXPR_CAT_FORM;
            }
            if ("buff.cat_form.down".equalsIgnoreCase(s)) {
                return new UnaryExpr_Not(this.EXPR_CAT_FORM);
            }
            if ("buff.bear_form.down".equalsIgnoreCase(s)) {
                return new UnaryExpr_Not(this.EXPR_BEAR_FORM);
            }
            if ("buff.virmens_bite_potion.up".equalsIgnoreCase(s)) {
                return this.EXPR_POTION_TIME;
            }
            if ("armored".equalsIgnoreCase(s)) {
                return this.EXPR_ARMORED;
            }
            if ("debuff.weakened_armor.remains".equalsIgnoreCase(s)) {
                return this.EXPR_ARMOR_TIME;
            }
            if ("buff.omen_of_clarity.react".equalsIgnoreCase(s)) {
                return this.EXPR_OOC_REACT;
            }
            if ("cast_time".equalsIgnoreCase(s)) {
                if (this.parsingAction instanceof CastSpell) {
                    final Spell spell2 = (Spell)this.parsingAction;
                    return new NamedExpr("CastTime") {
                        @Override
                        double getDouble() {
                            return ((CastSpell)spell2).castTime() / 1000.0;
                        }
                    };
                }
                throw new RuntimeException(String.format("%s does not support %s", this.parsingAction, s));
            }
            else {
                if ("action.rip.tick_multiplier".equalsIgnoreCase(s) || "action.rip.tick_damage".equalsIgnoreCase(s)) {
                    return this.EXPR_RIP_DMG_NEW;
                }
                if ("action.rake.tick_multiplier".equalsIgnoreCase(s) || "action.rake.tick_damage".equalsIgnoreCase(s)) {
                    return this.EXPR_RAKE_DMG_NEW;
                }
                if ("proc.effective_ap".equalsIgnoreCase(s)) {
                    return this.EXPR_PROC_EFF_AP;
                }
                if ("tick_multiplier".equalsIgnoreCase(s)) {
                    if (this.parsingAction == this.RAKE) {
                        return this.EXPR_RAKE_DMG_NEW;
                    }
                    if (this.parsingAction == this.RIP) {
                        return this.EXPR_RIP_DMG_NEW;
                    }
                    if (this.parsingAction == this.THRASH) {
                        return this.EXPR_THRASH_DMG_NEW;
                    }
                    throw new RuntimeException(String.format("%s does not support %s", this.parsingAction, s));
                }
                else {
                    if ("action.mangle_cat.hit_damage".equalsIgnoreCase(s)) {
                        return this.EXPR_MANGLE_DMG_NEW;
                    }
                    if ("action.shred.hit_damage".equalsIgnoreCase(s)) {
                        return this.EXPR_SHRED_DMG_NEW;
                    }
                    if ("rip_remains".equalsIgnoreCase(s)) {
                        return this.EXPR_RIP_MAX_TIME;
                    }
                    if ("dot.rip.remains".equalsIgnoreCase(s) || "dot.rip.ticking".equalsIgnoreCase(s)) {
                        return this.EXPR_RIP_MIN_TIME;
                    }
                    if ("dot.thrash_cat.multiplier".equalsIgnoreCase(s)) {
                        return this.EXPR_THRASH_DMG;
                    }
                    if ("dot.rip.multiplier".equalsIgnoreCase(s) || "dot.rip.tick_dmg".equalsIgnoreCase(s)) {
                        return this.EXPR_RIP_DMG;
                    }
                    if ("dot.rip.ticks_added".equalsIgnoreCase(s)) {
                        return this.EXPR_RIP_EXTENDS_ADDED;
                    }
                    if ("dot.rip.ticks_avail".equalsIgnoreCase(s)) {
                        return this.EXPR_RIP_EXTENDS_AVAIL;
                    }
                    if ("dot.rake.remains".equalsIgnoreCase(s) || "dot.rake.ticking".equalsIgnoreCase(s)) {
                        return this.EXPR_RAKE_TIME;
                    }
                    if ("dot.rake.ticks_remain".equalsIgnoreCase(s)) {
                        return this.EXPR_RAKE_TICKS;
                    }
                    if ("dot.rake.multiplier".equalsIgnoreCase(s) || "dot.rake.tick_dmg".equalsIgnoreCase(s)) {
                        return this.EXPR_RAKE_DMG;
                    }
                    final char ch = s.charAt(0);
                    if (isDigitChar(ch) || ch == '-') {
                        return _makeDouble(Double.parseDouble(s));
                    }
                    throw new RuntimeException("Unknown variable: " + s);
                }
            }
        }
    }
    
    static {
        STATS = new StatT[] { StatT.AGI, StatT.STR, StatT.AP, StatT.WDMG, StatT.HIT, StatT.EXP, StatT.CRIT, StatT.HASTE, StatT.MASTERY, StatT.INT, StatT.SP, StatT.SPI, StatT.STA, StatT.HP, StatT.PVP_POW, StatT.PVP_RES, StatT.DODGE };
        STAT_NUM = FeralSim.STATS.length;
        STAT_MAP = new HashMap<StatT, Integer>();
        for (int i = 0; i < FeralSim.STAT_NUM; ++i) {
            FeralSim.STAT_MAP.put(FeralSim.STATS[i], i);
        }
        STAT_AGI = FeralSim.STAT_MAP.get(StatT.AGI);
        STAT_STR = FeralSim.STAT_MAP.get(StatT.STR);
        STAT_INT = FeralSim.STAT_MAP.get(StatT.INT);
        STAT_STA = FeralSim.STAT_MAP.get(StatT.STA);
        STAT_SPI = FeralSim.STAT_MAP.get(StatT.SPI);
        STAT_AP = FeralSim.STAT_MAP.get(StatT.AP);
        STAT_SP = FeralSim.STAT_MAP.get(StatT.SP);
        STAT_HIT = FeralSim.STAT_MAP.get(StatT.HIT);
        STAT_EXP = FeralSim.STAT_MAP.get(StatT.EXP);
        STAT_CRIT = FeralSim.STAT_MAP.get(StatT.CRIT);
        STAT_HASTE = FeralSim.STAT_MAP.get(StatT.HASTE);
        STAT_MASTERY = FeralSim.STAT_MAP.get(StatT.MASTERY);
        STAT_HP = FeralSim.STAT_MAP.get(StatT.HP);
        STAT_WDMG = FeralSim.STAT_MAP.get(StatT.WDMG);
        STAT_PVP_POW = FeralSim.STAT_MAP.get(StatT.PVP_POW);
        STAT_PVP_RES = FeralSim.STAT_MAP.get(StatT.PVP_RES);
        STAT_DODGE = FeralSim.STAT_MAP.get(StatT.DODGE);
        ORDER_CD_DECR = new Comparator<Spell_Cooldown>() {
            @Override
            public int compare(final Spell_Cooldown a, final Spell_Cooldown b) {
                return b.baseCooldown - a.baseCooldown;
            }
        };
        playerPrefix = logPrefix("Player");
        GUSHING_TICK = tickify("Gushing Wound");
        EXPR_TRUE = new Expr() {
            @Override
            boolean getBoolean() {
                return true;
            }
        };
        EXPR_FALSE = new Expr() {
            @Override
            boolean getBoolean() {
                return false;
            }
        };
        EXPR_NAN = _makeDouble(Double.NaN);
        EXPR_ZERO = _makeDouble(0.0);
    }
    
    public enum ThrashStyle
    {
        NEVER("No Thrash"), 
        CLEARCAST("Clearcasting Only"), 
        MAINTAIN("Use Thrash");
        
        public final String title;
        
        private ThrashStyle(final String title) {
            this.title = title;
        }
        
        @Override
        public String toString() {
            return this.title;
        }
    }
    
    public enum Finisher0
    {
        DEFAULT("Default Finisher"), 
        RIP("Rip"), 
        SAVAGE_ROAR("Savage Roar"), 
        FEROCIOUS_BITE("Ferocious Bite");
        
        public final String title;
        
        private Finisher0(final String title) {
            this.title = title;
        }
        
        @Override
        public String toString() {
            return this.title;
        }
    }
    
    public enum WeakenedArmor
    {
        NEVER("No Faerie Fire", false), 
        FF_MIN("After Fading", true), 
        FF_RARE("Before Fading", true), 
        FF("Use Faerie Fire", true), 
        FF_MORE("More Faerie Fire", true), 
        FF_MAX("When Available", true), 
        ALWAYS("Always Weakened", false);
        
        public final String title;
        public final boolean casted;
        
        private WeakenedArmor(final String title, final boolean casted) {
            this.title = title;
            this.casted = casted;
        }
        
        @Override
        public String toString() {
            return this.title;
        }
    }
    
    public enum Heroism
    {
        NONE("None"), 
        AFTER("After Delay"), 
        BERSERK("On Berserk"), 
        EXECUTE("Execute");
        
        public final String title;
        
        private Heroism(final String title) {
            this.title = title;
        }
        
        @Override
        public String toString() {
            return this.title;
        }
    }
    
    public enum Symbiosis
    {
        NONE("No Symbiosis"), 
        FERAL_SPIRIT("Feral Spirit"), 
        SHATTERING_BLOW("Shattering Blow"), 
        SOUL_SWAP("Soul Swap"), 
        REDIRECT("Redirect");
        
        public final String title;
        
        private Symbiosis(final String title) {
            this.title = title;
        }
        
        @Override
        public String toString() {
            return this.title;
        }
    }
    
    public enum Generator
    {
        SHRANGLE("Shrangle"), 
        MANGLE("Mangle only"), 
        SHRED("Shred only"), 
        RAKE("Rake only"), 
        SWIPE("Swipe only");
        
        public final String title;
        
        private Generator(final String title) {
            this.title = title;
        }
        
        @Override
        public String toString() {
            return this.title;
        }
    }
    
    public enum Opener
    {
        NONE("Default Opener"), 
        RAVAGE("Ravage"), 
        POUNCE("Pounce"), 
        ADVANCED("Advanced");
        
        public final String title;
        
        private Opener(final String title) {
            this.title = title;
        }
        
        @Override
        public String toString() {
            return this.title;
        }
    }
    
    static class SpellApply
    {
        final String name;
        int hit;
        int crit;
        int block;
        int miss;
        int dodge;
        int parry;
        int glance;
        
        SpellApply(final String name) {
            this.name = name;
        }
        
        void reset() {
            this.hit = 0;
            this.crit = 0;
            this.block = 0;
            this.miss = 0;
            this.dodge = 0;
            this.parry = 0;
            this.glance = 0;
        }
        
        void add(final AttackT atk) {
            if (atk.hit) {
                if (atk.crit) {
                    ++this.crit;
                }
                else {
                    ++this.hit;
                }
                if (atk.block) {
                    ++this.block;
                }
                if (atk.glance) {
                    ++this.glance;
                }
            }
            else if (atk == AttackT.MISS) {
                ++this.miss;
            }
            else if (atk == AttackT.DODGE) {
                ++this.dodge;
            }
            else if (atk == AttackT.PARRY) {
                ++this.parry;
            }
        }
    }
    
    class EventKey
    {
        boolean exists;
        int added;
        int expires;
        
        int timeLeft() {
            return this.exists ? (this.expires - FeralSim.this.clock) : 0;
        }
        
        boolean stillUp(final int t) {
            return this.exists && this.expires - FeralSim.this.clock > t;
        }
        
        boolean fallsOff(final int t) {
            return this.exists && this.expires - FeralSim.this.clock <= t;
        }
        
        boolean react() {
            return this.exists & FeralSim.this.clock >= this.added + FeralSim.this._reactTime();
        }
    }
    
    class GearConfig
    {
        final String name;
        final double min;
        final double max;
        final int speed;
        final int armor;
        final int bonusArmor;
        final int[] stats;
        final Action swap;
        
        GearConfig(final String name, final double min, final double max, final int speed, final int[] stats, final int armor, final int bonusArmor) {
            this.name = name;
            this.min = min;
            this.max = max;
            this.speed = speed;
            this.stats = stats;
            this.armor = armor;
            this.bonusArmor = bonusArmor;
            this.swap = new Action("Swap:" + name) {
                @Override
                public boolean actionable() {
                    return !GearConfig.this.equipped();
                }
                
                @Override
                public void runAction() {
                    GearConfig.this.equip();
                    throw new Clicked();
                }
            };
        }
        
        boolean equipped() {
            return FeralSim.this.curGear == this;
        }
        
        void equip() {
            if (FeralSim.this.curGear == this) {
                return;
            }
            if (FeralSim.this.clock > 0 || FeralSim.this.damage_total > 0L) {
                FeralSim.this._startLockout(1500);
                FeralSim.this.resetSwing();
                for (final ProcHandler ph : FeralSim.this.procs) {
                    if (ph instanceof ProcHandler_GearSpecific) {
                        final Proc proc = ((ProcHandler_GearSpecific)ph).proc;
                        if (!(proc instanceof Proc_Chance)) {
                            continue;
                        }
                        ((Proc_Chance)proc).chance.beginCooldown();
                    }
                }
            }
            final String old = FeralSim.this.curGear.name;
            this.activate();
            if (FeralSim.this.printLog) {
                FeralSim.this.print("Swap Weapon: %s > %s", old, this.name);
            }
        }
        
        final void activate() {
            FeralSim.this.curGear = this;
            FeralSim.this.gearStats = this.stats;
        }
    }
    
    abstract class Spell extends Action
    {
        boolean enabled;
        final EventKey eventKey;
        int ready;
        int time_spent;
        int click_count;
        
        Spell(final String name) {
            super(name);
            this.enabled = true;
            this.eventKey = new EventKey();
        }
        
        @Override
        public boolean actionable() {
            return this.clickable();
        }
        
        @Override
        public void runAction() {
            if (this.click()) {
                throw new Clicked();
            }
        }
        
        boolean ready() {
            return FeralSim.this.clock >= this.ready;
        }
        
        int timeUntilReady() {
            return Math.max(0, this.ready - FeralSim.this.clock);
        }
        
        void reset() {
            this.ready = -1000000;
            this.time_spent = 0;
            this.click_count = 0;
            this.eventKey.exists = false;
        }
        
        boolean usable() {
            return this.enabled && (this.baseLockout() <= 0 || !FeralSim.this.lockoutKey.exists) && this.ready();
        }
        
        boolean affordable() {
            return true;
        }
        
        final boolean clickable() {
            return this.usable() && this.affordable();
        }
        
        void resetReady() {
            this.ready = -1000000;
        }
        
        int baseLockout() {
            return 0;
        }
        
        final int lockout() {
            int t = this.baseLockout();
            if (t > 0) {
                t = Math.max(1000, (int)(t / FeralSim.this.getSpellHasteMod()));
            }
            return t;
        }
        
        abstract boolean casted();
        
        final boolean click() {
            if (!this.usable() || !this.affordable() || !this.casted()) {
                return false;
            }
            ++this.click_count;
            final int t = this.lockout();
            if (t > 0) {
                this.time_spent += t;
                FeralSim.this._startLockout(t);
                throw new Clicked();
            }
            if (t < 0) {
                this.time_spent -= t;
                FeralSim.this.addEvent(new Event(FeralSim.this.clock - t));
                throw new Clicked();
            }
            return true;
        }
        
        boolean clickResult() {
            try {
                return this.click();
            }
            catch (Clicked ex) {
                return true;
            }
        }
    }
    
    static class EndOfCombat extends RuntimeException
    {
    }
    
    static class Clicked extends RuntimeException
    {
    }
    
    abstract class ManaSpell extends Spell
    {
        ManaSpell(final String name) {
            super(name);
        }
        
        abstract int baseManaCost();
        
        @Override
        int baseLockout() {
            return 1500;
        }
        
        int manaCost() {
            final int baseCost = this.baseManaCost();
            final double realCost = baseCost;
            int cost = (int)realCost;
            if (realCost > cost && (baseCost / 10 & 0x1) > 0) {
                ++cost;
            }
            return cost;
        }
        
        int maxManaCost() {
            return Math.max(this.manaCost(), this.baseManaCost());
        }
        
        void clickIfManaAlsoSupports(final ManaSpell x) {
            if (FeralSim.this.mana >= this.manaCost() + x.maxManaCost()) {
                x.click();
            }
        }
    }
    
    abstract class ChanSpell extends ManaSpell
    {
        int started;
        int lockoutTime;
        int tick_freq;
        int tick_count;
        int tick_index;
        
        ChanSpell(final String name) {
            super(name);
        }
        
        abstract void exec(final int p0);
        
        abstract void ticked();
        
        abstract int tickCount();
        
        abstract int baseTickTime();
        
        int tickFreq() {
            return FeralSim.round(this.baseTickTime() / FeralSim.this.getSpellHasteMod());
        }
        
        int channelTime() {
            return FeralSim.this.clock - this.started;
        }
        
        private void startTick() {
            FeralSim.this.addEvent(new Event(FeralSim.this.clock + this.tick_freq, FeralSim.this.castingKey) {
                @Override
                void run() {
                    final ChanSpell this$1 = ChanSpell.this;
                    ++this$1.tick_index;
                    ChanSpell.this.ticked();
                }
                
                @Override
                void faded(final boolean executed) {
                    if (executed && ChanSpell.this.tick_index < ChanSpell.this.tick_count) {
                        ChanSpell.this.startTick();
                        return;
                    }
                    FeralSim.this.resetSwing();
                    final int channelledTime = FeralSim.this.clock - ChanSpell.this.started;
                    final ChanSpell this$1 = ChanSpell.this;
                    this$1.time_spent += Math.max(channelledTime, ChanSpell.this.lockoutTime);
                    if (FeralSim.this.printLog) {
                        if (executed) {
                            FeralSim.this.print("Channelled: %s (%.2fs)", ChanSpell.this.actionName, channelledTime / 1000.0);
                        }
                        else {
                            FeralSim.this.print("Interrupted: %s [%d/%d] (%.2fs)", ChanSpell.this.actionName, ChanSpell.this.tick_index, ChanSpell.this.tick_count, channelledTime / 1000.0);
                        }
                    }
                }
            });
        }
        
        @Override
        boolean casted() {
            final int cost = this.manaCost();
            if (cost > FeralSim.this.mana) {
                return false;
            }
            final FeralSim this$0 = FeralSim.this;
            this$0.mana -= cost;
            FeralSim.this.cancelForm();
            this.started = FeralSim.this.clock;
            this.lockoutTime = this.lockout();
            this.tick_index = 0;
            this.tick_count = this.tickCount();
            this.tick_freq = this.tickFreq();
            FeralSim.this._startLockout(this.lockoutTime);
            this.startTick();
            ++this.click_count;
            if (FeralSim.this.printLog) {
                FeralSim.this.print("Channelling: %s (%dx every %s)", this.actionName, this.tick_count, Fmt.msDur(this.tick_freq));
            }
            this.exec(cost);
            throw new Clicked();
        }
    }
    
    abstract class CastSpell extends ManaSpell
    {
        final boolean consume_ooc;
        
        CastSpell(final String name, final boolean consume_ooc) {
            super(name);
            this.consume_ooc = consume_ooc;
        }
        
        int castTime() {
            if (this.baseLockout() == 0) {
                return 0;
            }
            final int t = this.baseCastTime();
            return (t > 0) ? ((int)(t / FeralSim.this.getSpellHasteMod())) : 0;
        }
        
        abstract int baseCastTime();
        
        @Override
        int manaCost() {
            return (this.consume_ooc && FeralSim.this.oocKey.exists) ? 0 : super.manaCost();
        }
        
        abstract boolean exitForm();
        
        boolean instantCast() {
            return this.baseCastTime() == 0;
        }
        
        abstract void exec(final int p0, final int p1);
        
        @Override
        boolean casted() {
            final int cost0 = this.manaCost();
            if (cost0 > FeralSim.this.mana) {
                return false;
            }
            if (FeralSim.this.form > 0 && this.exitForm()) {
                FeralSim.this.cancelForm();
            }
            final int lockTime = this.lockout();
            if (lockTime == 0) {
                final FeralSim this$0 = FeralSim.this;
                this$0.mana -= cost0;
                this.exec(0, cost0);
                ++this.click_count;
                return true;
            }
            FeralSim.this._startLockout(lockTime);
            FeralSim.this._breakStealth();
            final int castTime = this.castTime();
            if (FeralSim.this.printLog && castTime > 0) {
                FeralSim.this.print("Casting: %s...", this.actionName);
            }
            final int start = FeralSim.this.clock;
            FeralSim.this.addEvent(new Event(FeralSim.this.clock + castTime, FeralSim.this.castingKey) {
                @Override
                void faded(final boolean executed) {
                    if (castTime > 0) {
                        FeralSim.this.resetSwing();
                    }
                    final int cost = CastSpell.this.manaCost();
                    if (executed && FeralSim.this.mana >= cost) {
                        final FeralSim this$0 = FeralSim.this;
                        this$0.mana -= cost;
                        if (FeralSim.this.printLog && castTime > 0) {
                            FeralSim.this.print("Casted: %s (%.2fs)", CastSpell.this.actionName, castTime / 1000.0);
                        }
                        final CastSpell this$2 = CastSpell.this;
                        this$2.time_spent += Math.max(castTime, lockTime);
                        CastSpell.this.exec(castTime, cost);
                        if (castTime > 0 && CastSpell.this.consume_ooc && !FeralSim.this.HOTW.up() && FeralSim.this.removeEvent(FeralSim.this.oocKey, true)) {
                            final FeralSim this$3 = FeralSim.this;
                            ++this$3.ooc_spent;
                        }
                        final CastSpell this$4 = CastSpell.this;
                        ++this$4.click_count;
                    }
                    else {
                        final int castedTime = FeralSim.this.clock - start;
                        if (FeralSim.this.printLog) {
                            FeralSim.this.print("Interrupted: %s (%.2f/%.2fs)", CastSpell.this.actionName, castedTime / 1000.0, castTime / 1000.0);
                        }
                        final CastSpell this$5 = CastSpell.this;
                        this$5.time_spent += castedTime;
                        FeralSim.this.removeEvent(FeralSim.this.lockoutKey, false);
                    }
                }
            });
            throw new Clicked();
        }
    }
    
    abstract class BearSpell extends Spell
    {
        final boolean consume_ooc;
        
        BearSpell(final String name, final boolean consume_ooc) {
            super(name);
            this.consume_ooc = consume_ooc;
        }
        
        @Override
        int baseLockout() {
            return 1500;
        }
        
        @Override
        boolean affordable() {
            return FeralSim.this.rage >= this.rageCost();
        }
        
        @Override
        boolean usable() {
            return super.usable() && FeralSim.this.form == 2;
        }
        
        final int rageCost() {
            return (this.consume_ooc && FeralSim.this.oocKey.exists) ? 0 : this.baseCost();
        }
        
        abstract int baseCost();
        
        abstract void attack(final int p0);
        
        @Override
        boolean casted() {
            final int cost = this.rageCost();
            if (cost > FeralSim.this.rage) {
                return false;
            }
            final FeralSim this$0 = FeralSim.this;
            this$0.rage -= cost;
            this.attack(cost);
            if (this.consume_ooc && FeralSim.this.removeEvent(FeralSim.this.oocKey, true)) {
                final FeralSim this$2 = FeralSim.this;
                ++this$2.ooc_spent;
            }
            return true;
        }
        
        void recordBleed(final Enemy enemy, final String name, final int index, final String mods, final boolean crit, final double dmg) {
            FeralSim.this.recordDamage(enemy, name, FeralSim.this.addNum(name, index), mods, AttackT.critOrHit(crit), dmg);
            FeralSim.this.triggerProcs(enemy, name, Source.BLEED, dmg, crit, 0);
        }
        
        void recordMelee(final Enemy enemy, final String name, final String mods, final AttackT atk, final double dmg) {
            FeralSim.this.recordDamage(enemy, name, name, mods, atk, dmg);
            FeralSim.this.triggerProcs(enemy, name, Source.MELEE, dmg, atk.crit, 2500);
        }
    }
    
    abstract class CatSpell extends Spell
    {
        final boolean consume_ooc;
        final boolean refunds;
        
        CatSpell(final String name, final boolean consume_ooc, final boolean refunds) {
            super(name);
            this.consume_ooc = consume_ooc;
            this.refunds = refunds;
        }
        
        @Override
        int baseLockout() {
            return 1000;
        }
        
        @Override
        boolean affordable() {
            return FeralSim.this.energy >= this.energyCost();
        }
        
        @Override
        boolean usable() {
            return super.usable() && FeralSim.this.form == 1;
        }
        
        final int energyCost() {
            if (this.consume_ooc && FeralSim.this.oocKey.exists) {
                return 0;
            }
            final int baseCost = this.baseCost();
            final double realCost = baseCost * FeralSim.this.energyCostMods.product;
            int cost = (int)realCost;
            if (realCost > cost && (baseCost / 10 & 0x1) > 0) {
                ++cost;
            }
            return cost;
        }
        
        abstract int baseCost();
        
        abstract boolean attack(final int p0);
        
        @Override
        boolean casted() {
            final int cost = this.energyCost();
            if (cost > FeralSim.this.energy) {
                return false;
            }
            final FeralSim this$0 = FeralSim.this;
            this$0.energy -= cost;
            final boolean landed = this.attack(cost);
            if (this.consume_ooc && FeralSim.this.removeEvent(FeralSim.this.oocKey, true)) {
                final FeralSim this$2 = FeralSim.this;
                this$2.ooc_energy += this.energyCost();
                if (this == FeralSim.this.THRASH) {
                    final FeralSim this$3 = FeralSim.this;
                    ++this$3.ooc_thrash;
                }
                else if (this == FeralSim.this.MANGLE || this == FeralSim.this.SHRED || this == FeralSim.this.RAVAGE) {
                    final FeralSim this$4 = FeralSim.this;
                    ++this$4.ooc_mangleShredRavage;
                }
                final FeralSim this$5 = FeralSim.this;
                ++this$5.ooc_spent;
            }
            if (this.refunds && cost > 0 && !landed && FeralSim.this.cfg.enable_refund) {
                final int max = (int)(0.8 * cost);
                final FeralSim this$6 = FeralSim.this;
                this$6.energy_refund += FeralSim.this.addEnergy(max);
                final FeralSim this$7 = FeralSim.this;
                this$7.energy_refund_max += max;
            }
            return true;
        }
    }
    
    abstract class CatSpell_Generator extends CatSpell
    {
        final boolean singleTarget;
        final int comboGain;
        
        CatSpell_Generator(final FeralSim this$0, final String name) {
            this(this$0, name, true, 1);
        }
        
        CatSpell_Generator(final String name, final boolean singleTarget, final int comboGain) {
            super(name, true, true);
            this.singleTarget = singleTarget;
            this.comboGain = comboGain;
        }
        
        abstract AttackT generate();
        
        @Override
        boolean attack(final int cost) {
            final AttackT atk = this.generate();
            if (!atk.hit) {
                final FeralSim this$0 = FeralSim.this;
                ++this$0.generator_miss_count;
                return false;
            }
            int c = this.comboGain;
            if (this.singleTarget && atk.crit && !FeralSim.this.disable_primalFury) {
                ++c;
            }
            FeralSim.this.addCombo(c);
            final FeralSim this$2 = FeralSim.this;
            ++this$2.generator_count;
            return true;
        }
    }
    
    abstract class CatSpell_Finisher extends CatSpell
    {
        int ready;
        
        CatSpell_Finisher(final String name, final boolean consume_ooc) {
            super(name, consume_ooc, true);
            this.ready = 0;
        }
        
        @Override
        void reset() {
            super.reset();
            this.ready = 0;
        }
        
        @Override
        boolean usable() {
            return FeralSim.this.clock >= this.ready && super.usable();
        }
        
        abstract boolean finish();
        
        @Override
        boolean attack(final int cost) {
            if (!this.finish()) {
                final FeralSim this$0 = FeralSim.this;
                ++this$0.finisher_miss_count;
                return false;
            }
            final int c = FeralSim.this.combo;
            FeralSim.this.combo = 0;
            FeralSim.this.comboTarget = null;
            Label_0173: {
                if (c > 0) {
                    if (!FeralSim.this.chance(0.2 * c)) {
                        break Label_0173;
                    }
                }
                else if (!FeralSim.this.cfg.enable_0comboPS || !FeralSim.this.chance(0.05)) {
                    break Label_0173;
                }
                final FeralSim this$2 = FeralSim.this;
                ++this$2.ps_occur;
                FeralSim.this.addEvent(new Fader(FeralSim.this.clock + 7000, FeralSim.this.psKey, FeralSim.this.printLog ? String.format("PS`%d", c) : null, "PS") {
                    @Override
                    void added(final boolean replaced) {
                        if (replaced) {
                            final FeralSim this$0 = FeralSim.this;
                            ++this$0.ps_replaced;
                        }
                        super.added(replaced);
                    }
                });
            }
            if (FeralSim.this.spec.talent_sotf && c > 0) {
                final int max = 4 * c;
                final FeralSim this$3 = FeralSim.this;
                this$3.energy_sotf += FeralSim.this.addEnergy(max);
                final FeralSim this$4 = FeralSim.this;
                this$4.energy_sotf_max += max;
                final FeralSim this$5 = FeralSim.this;
                ++this$5.sotf_count;
            }
            final int[] finisher_tally = FeralSim.this.finisher_tally;
            final int n = c;
            ++finisher_tally[n];
            final FeralSim this$6 = FeralSim.this;
            this$6.combo_finisher += c;
            if (FeralSim.this.bonus_t15_2p && FeralSim.this.chance(c * 0.15)) {
                FeralSim.this.addCombo(1);
            }
            if (FeralSim.this.bonus_t16_4p && FeralSim.this.removeEvent(FeralSim.this.bonus_t16_4p_key, true)) {
                FeralSim.this.addCombo(3);
            }
            return true;
        }
    }
    
    class Enemy
    {
        final Multiplicative armor;
        int gushingIndex;
        final double[] gushingDamage;
        boolean gushing;
        final EventKey ff;
        final EventKey ripTicker;
        final EventKey rakeTicker;
        final EventKey bloodletting;
        final double missProb;
        final double dodgeProb;
        final double spellMissRate;
        final double parryProb;
        final double blockProb;
        final double glanceProb;
        final double critMinus;
        final double behindProb;
        final double armorCoeff;
        final boolean monitor;
        final int enemyLevel;
        final int levelDelta;
        final String name;
        final String logPrefix;
        final double allDmgMod;
        int armorStacks;
        final DamageWindow dw;
        long minHP;
        long curHP;
        long maxHP;
        boolean casting;
        long damage;
        int ttd;
        boolean immuneToDamage;
        final double glanceMin;
        final double glanceMax;
        boolean onDeath_endOfCombat;
        int death;
        int ff_uptime;
        int pounce_clock;
        int pounce_tickIndex;
        int pounce_tickLeft;
        double pounce_tickDmg;
        double pounce_crit;
        double pounce_avgDmg;
        String pounce_mods;
        int thrash_clock;
        int thrash_tickIndex;
        int thrash_tickLeft;
        int thrash_tickCount;
        double thrash_tickDmg;
        double thrash_crit;
        double thrash_avgDmg;
        String thrash_mods;
        int thrashBear_clock;
        int thrashBear_tickLeft;
        int thrashBear_tickIndex;
        int thrashBear_tickCount;
        double thrashBear_tickDmg;
        double thrashBear_crit;
        double thrashBear_avgDmg;
        String thrashBear_mods;
        int rake_clock;
        int rake_tickIndex;
        int rake_tickLeft;
        int rake_tickCount;
        int rake_expire;
        double rake_tickDmg;
        double rake_crit;
        double rake_avgDmg;
        String rake_mods;
        int rip_clock;
        int rip_tickLeft;
        int rip_tickIndex;
        int rip_tickCount;
        private int rip_extends;
        double rip_tickDmg;
        double rip_crit;
        double rip_avgDmg;
        String rip_name;
        String rip_mods;
        private boolean _behind;
        private int _behindClock;
        
        Enemy(final String name, final int levelDelta, final int maxHP, final boolean monitor, final double behindProb, final boolean canBlock, final boolean canParry, final boolean canDodge, final double allDmgMod) {
            this.armor = new Multiplicative(5);
            this.gushingIndex = 0;
            this.gushingDamage = new double[3];
            this.ff = new EventKey();
            this.ripTicker = new EventKey();
            this.rakeTicker = new EventKey();
            this.bloodletting = new EventKey();
            this.dw = new DamageWindow(5000, 60000);
            this.name = name;
            this.levelDelta = levelDelta;
            this.maxHP = maxHP;
            this.monitor = monitor;
            this.behindProb = behindProb;
            this.allDmgMod = allDmgMod;
            this.logPrefix = FeralSim.logPrefix(name);
            this.enemyLevel = 90 + levelDelta;
            this.armorCoeff = 46257.5;
            this.blockProb = (canBlock ? 0.05 : 0.0);
            final int above = Math.max(0, levelDelta);
            this.missProb = 0.03 + above * 0.015;
            this.dodgeProb = (canDodge ? (0.03 + above * 0.015) : 0.0);
            this.parryProb = (canParry ? (0.03 + above * 0.03) : 0.0);
            this.spellMissRate = 0.03 + above * 0.03;
            this.glanceProb = 0.06 * (1 + above);
            this.critMinus = 0.01 * above;
            final double max = FeralSim.clip(1.3 - 0.15 * above, 0.2, 0.99);
            final double min = FeralSim.clip(1.4 - 0.25 * above, 0.01, 0.91);
            this.glanceMin = Math.min(min, max);
            this.glanceMax = Math.max(min, max);
            this.armor.clearAndSetIdentity(FeralSim.armorByLevel(this.enemyLevel));
        }
        
        boolean interrupt() {
            if (!this.casting) {
                return false;
            }
            this.casting = false;
            if (FeralSim.this.printLog) {
                FeralSim.this.print(this.logPrefix + "Interrupted!");
            }
            return true;
        }
        
        void updateDPS() {
            if (!this.isImmortal()) {
                final double dps = this.dw.mean(FeralSim.this.clock, 60000);
                this.ttd = ((dps > 0.0) ? FeralSim.round((this.curHP - this.minHP) / dps) : -1);
            }
            else if (this.maxHP < 0L) {
                this.ttd = (int)(-this.maxHP - FeralSim.this.clock);
            }
        }
        
        double glanceMod() {
            return FeralSim.this.range(this.glanceMin, this.glanceMax, FeralSim.this.random_ranges);
        }
        
        void suffer(final int dmg) {
            this.damage += dmg;
            if (!this.isImmortal()) {
                this.dw.add(FeralSim.this.clock, dmg);
                this.curHP -= dmg;
                if (this.curHP < this.minHP) {
                    this.death = FeralSim.this.clock;
                    if (this.onDeath_endOfCombat) {
                        throw new EndOfCombat();
                    }
                }
            }
        }
        
        double suppress(final double crit) {
            return Math.max(0.0, crit - this.critMinus);
        }
        
        double percHP() {
            if (this.isDead()) {
                return 0.0;
            }
            if (this.maxHP > 0L) {
                return this.curHP / (double)this.maxHP;
            }
            if (this.maxHP == 0L) {
                return 1.0;
            }
            return (-this.maxHP - FeralSim.this.clock) / (double)(this.minHP - this.maxHP);
        }
        
        boolean isImmortal() {
            return this.maxHP < 1L || this.immuneToDamage;
        }
        
        boolean isDead() {
            return this.death >= 0;
        }
        
        void reset() {
            this.armor.clear();
            if (FeralSim.this.cfg.debuff_armor == WeakenedArmor.ALWAYS) {
                this.armor.add(-2, 0.88);
            }
            this.bloodletting.exists = false;
            this.gushing = false;
            this.gushingIndex = 0;
            Arrays.fill(this.gushingDamage, 0.0);
            this.ff.exists = false;
            this.ff_uptime = 0;
            this.armorStacks = 0;
            this.ripTicker.exists = false;
            this.rakeTicker.exists = false;
            this.pounce_tickLeft = 0;
            this.thrash_tickLeft = 0;
            this.thrash_tickCount = 0;
            this.thrashBear_tickLeft = 0;
            this.thrashBear_tickCount = 0;
            this.rake_tickLeft = 0;
            this.rake_tickCount = 0;
            this.rip_tickLeft = 0;
            this.rip_tickCount = 0;
            this.death = -1;
            this.casting = false;
            this.curHP = this.maxHP;
            this.damage = 0L;
            this.dw.clear();
            this.ttd = -1;
            if (this.behindProb <= 0.0) {
                this._behind = false;
                this._behindClock = Integer.MAX_VALUE;
            }
            else if (this.behindProb >= 1.0) {
                this._behind = true;
                this._behindClock = Integer.MAX_VALUE;
            }
            else {
                this._behindClock = 0;
            }
        }
        
        boolean bitw() {
            return this.percHP() <= FeralSim.this.bitw_perc;
        }
        
        int getRipExtends() {
            return FeralSim.this.cfg.enable_localRipExtend ? this.rip_extends : FeralSim.this.global_rip_extends;
        }
        
        void resetRipExtends() {
            if (!FeralSim.this.disable_ripExtend) {
                if (FeralSim.this.cfg.enable_localRipExtend) {
                    this.rip_extends = 3;
                }
                else {
                    FeralSim.this.global_rip_extends = 3;
                }
            }
        }
        
        boolean isBehind() {
            if (FeralSim.this.clock >= this._behindClock) {
                this._behind = FeralSim.this.chance(this.behindProb);
                this._behindClock = FeralSim.this.clock + 1000;
            }
            return this._behind;
        }
        
        boolean isShredable() {
            return this.isBehind() || (FeralSim.this.spec.glyph_shred && (FeralSim.this.TF.up() || FeralSim.this.BERSERK.up()));
        }
        
        boolean isBleeding() {
            return FeralSim.this.cfg.debuff_bleeding || this.rake_tickLeft > 0 || this.rip_tickLeft > 0 || this.thrash_tickLeft > 0 || this.thrashBear_tickLeft > 0 || this.pounce_tickLeft > 0;
        }
        
        private double armorMod() {
            return this.armorCoeff / (this.armor.product + this.armorCoeff);
        }
        
        double meleeDmgMod() {
            return FeralSim.this.allSufferMods.product * this.allDmgMod * FeralSim.this.debuff_meleeMod * this.armorMod();
        }
        
        double bleedDmgMod() {
            return FeralSim.this.allSufferMods.product * this.allDmgMod * FeralSim.this.debuff_meleeMod * (this.bloodletting.exists ? 1.1 : 1.0);
        }
        
        double spellDmgMod() {
            return FeralSim.this.allSufferMods.product * this.allDmgMod * FeralSim.this.debuff_spellMod;
        }
        
        int rakeTimeLeft() {
            return (this.rake_tickLeft > 0) ? (this.rake_tickLeft * 3000 + (this.rake_clock - FeralSim.this.clock) % 3000) : 0;
        }
        
        int thrashTimeLeft() {
            return (this.thrash_tickLeft > 0) ? (this.thrash_tickLeft * 3000 + (this.thrash_clock - FeralSim.this.clock) % 3000) : 0;
        }
        
        int thrashBearTimeLeft() {
            return (this.thrashBear_tickLeft > 0) ? (this.thrashBear_tickLeft * 2000 + (this.thrashBear_clock - FeralSim.this.clock) % 2000) : 0;
        }
        
        int maxRipTicksLeft() {
            return (this.rip_tickLeft > 0) ? (this.rip_tickLeft + this.getRipExtends()) : 0;
        }
        
        int _ripTime(final int ticks) {
            return (this.rip_tickLeft > 0) ? (ticks * 2000 + (this.rip_clock - FeralSim.this.clock) % 2000) : 0;
        }
        
        int ripMinTimeLeft() {
            return this._ripTime(this.rip_tickLeft);
        }
        
        int ripMaxTimeLeft() {
            return this._ripTime(this.maxRipTicksLeft());
        }
        
        void extendRip() {
            if (this.rip_tickLeft > 0 && this.getRipExtends() > 0) {
                if (FeralSim.this.cfg.enable_localRipExtend) {
                    --this.rip_extends;
                }
                else {
                    final FeralSim this$0 = FeralSim.this;
                    --this$0.global_rip_extends;
                }
                if (FeralSim.this.cfg.enable_properRipExtend) {
                    ++this.rip_tickLeft;
                }
                else {
                    this.rip_tickLeft += (FeralSim.this.chance(0.5) ? 2 : 1);
                }
            }
        }
        
        void print_fade(final String thing) {
            FeralSim.this.print(this.logPrefix + "-" + thing);
        }
        
        void addGushing(final double dmg) {
            if (FeralSim.this.bonus_t17_4p && FeralSim.this.BERSERK.up()) {
                final double tickDmg = 0.05 * dmg;
                for (int i = 0; i < this.gushingDamage.length; ++i) {
                    final double[] gushingDamage = this.gushingDamage;
                    final int n = i;
                    gushingDamage[n] += tickDmg;
                }
                if (!this.gushing) {
                    this.gushing = true;
                    if (FeralSim.this.printLog) {
                        FeralSim.this.print(this.logPrefix + "+" + "Gushing Wound");
                    }
                    this.startGushingTick();
                }
            }
        }
        
        void startGushingTick() {
            FeralSim.this.addEvent(new Event(FeralSim.this.clock + 2000) {
                public void run() {
                    if (Enemy.this.isDead()) {
                        return;
                    }
                    int index = Enemy.this.gushingIndex;
                    final double dmg = Enemy.this.gushingDamage[index];
                    Enemy.this.gushingDamage[index++] = 0.0;
                    Enemy.this.gushingIndex = index % Enemy.this.gushingDamage.length;
                    if (dmg == 0.0) {
                        if (FeralSim.this.printLog) {
                            FeralSim.this.print(Enemy.this.logPrefix + "-" + "Gushing Wound");
                        }
                        Enemy.this.gushing = false;
                        return;
                    }
                    FeralSim.this.recordDamage(Enemy.this, FeralSim.GUSHING_TICK, FeralSim.GUSHING_TICK, "", AttackT.HIT, dmg);
                    Enemy.this.startGushingTick();
                }
            });
        }
    }
    
    static class Tree
    {
        final int death;
        final int index;
        Enemy rake_target;
        Enemy auto_target;
        double rake_tickDmg;
        double rake_crit;
        String rake_mods;
        int rake_tickLeft;
        
        Tree(final int index, final int death) {
            this.index = index;
            this.death = death;
        }
    }
    
    class Fader extends Event
    {
        final String gain;
        final String fade;
        final String prefix;
        final int start;
        
        Fader(final FeralSim this$0, final int t, final Spell x) {
            this(this$0, t, x.eventKey, x.actionName, x.actionName);
        }
        
        Fader(final FeralSim this$0, final int t, final EventKey key, final String both) {
            this(this$0, t, key, both, both);
        }
        
        Fader(final FeralSim this$0, final int t, final EventKey key, final String gain, final String fade) {
            this(this$0, t, key, gain, fade, null);
        }
        
        Fader(final int t, final EventKey key, final String gain, final String fade, final String prefix) {
            super(t, key);
            this.gain = gain;
            this.fade = fade;
            this.prefix = ((prefix == null) ? "" : prefix);
            this.start = FeralSim.this.clock;
        }
        
        void uptime(final int t) {
        }
        
        @Override
        void run() {
            if (FeralSim.this.printLog) {
                FeralSim.this.print(this.prefix + "-" + this.fade);
            }
        }
        
        @Override
        void faded(final boolean executed) {
            this.uptime(FeralSim.this.clock - this.start);
        }
        
        @Override
        void added(final boolean replaced) {
            if (FeralSim.this.printLog) {
                FeralSim.this.print(this.prefix + (replaced ? "*" : "+") + this.gain);
            }
        }
    }
    
    static class DamageTally
    {
        final String name;
        int hit_num;
        int crit_num;
        long hit_sum;
        long crit_sum;
        int hit_max;
        int hit_min;
        int crit_max;
        int crit_min;
        static final Comparator<DamageTally> CMP;
        
        DamageTally(final String name) {
            this.name = name;
        }
        
        long sum() {
            return this.hit_sum + this.crit_sum;
        }
        
        int occ() {
            return this.hit_num + this.crit_num;
        }
        
        DamageTally copy() {
            final DamageTally copy = new DamageTally(this.name);
            copy.hit_num = this.hit_min;
            copy.crit_num = this.crit_num;
            copy.hit_sum = this.hit_sum;
            copy.crit_sum = this.crit_sum;
            copy.hit_min = this.hit_min;
            copy.hit_max = this.hit_max;
            copy.crit_min = this.crit_min;
            copy.crit_max = this.crit_max;
            return copy;
        }
        
        static {
            CMP = new Comparator<DamageTally>() {
                @Override
                public int compare(final DamageTally a, final DamageTally b) {
                    final long aa = a.sum();
                    final long bb = b.sum();
                    return (aa > bb) ? -1 : ((aa < bb) ? 1 : 0);
                }
            };
        }
    }
    
    static class Damage
    {
        final String mods;
        final boolean crit;
        final int dmg;
        
        Damage(final String mods, final boolean crit, final int dmg) {
            this.mods = mods;
            this.crit = crit;
            this.dmg = dmg;
        }
        
        static double sum(final Collection<Damage> v) {
            double sum = 0.0;
            for (final Damage x : v) {
                sum += x.dmg;
            }
            return sum;
        }
        
        static double avg(final Collection<Damage> v, final boolean crit) {
            double num = 0.0;
            int dem = 0;
            for (final Damage x : v) {
                if (x.crit == crit) {
                    num += x.dmg;
                    ++dem;
                }
            }
            return (dem > 0) ? (num / dem) : 0.0;
        }
        
        static double crit(final Collection<Damage> v) {
            int cnt = 0;
            for (final Damage x : v) {
                if (x.crit) {
                    ++cnt;
                }
            }
            return (cnt > 0) ? (cnt / (double)v.size()) : 0.0;
        }
        
        static int count(final Collection<Damage> v, final String mod) {
            int cnt = 0;
            for (final Damage x : v) {
                if (x.mods.contains(mod)) {
                    ++cnt;
                }
            }
            return cnt;
        }
    }
    
    static class Event
    {
        final int t;
        final EventKey key;
        
        Event(final int t) {
            this(t, null);
        }
        
        Event(final int t, final EventKey key) {
            this.t = t;
            this.key = key;
        }
        
        void run() {
        }
        
        boolean futureProc() {
            return false;
        }
        
        void faded(final boolean executed) {
        }
        
        void added(final boolean replaced) {
        }
    }
    
    static class DeadEvent extends Event
    {
        DeadEvent(final int t) {
            super(t);
        }
    }
    
    enum Source
    {
        SWING("Swing", true), 
        MELEE("Melee", true), 
        BLEED("Bleed", true), 
        SPELL("Spell"), 
        DEBUFF("Debuff"), 
        HEAL("Heal"), 
        DOT("DoT"), 
        HOT("HoT");
        
        final String fancyName;
        final boolean melee;
        
        private Source(final String fancyName) {
            this(fancyName, false);
        }
        
        private Source(final String fancyName, final boolean melee) {
            this.fancyName = fancyName;
            this.melee = melee;
        }
        
        final int bit() {
            return 1 << this.ordinal();
        }
        
        @Override
        public String toString() {
            return this.fancyName;
        }
    }
    
    abstract class Proc
    {
        final String name;
        
        Proc(final String name) {
            this.name = name;
        }
        
        void reset() {
        }
        
        String type() {
            return "Custom";
        }
        
        abstract void run(final Enemy p0, final String p1, final Source p2, final double p3, final boolean p4, final int p5);
        
        public int getUptime() {
            return 0;
        }
        
        void toString(final StringBuilder sb) {
        }
    }
    
    abstract class ProcChance
    {
        abstract void reset();
        
        abstract void beginCooldown();
        
        abstract boolean shouldProc();
        
        abstract void toString(final StringBuilder p0);
    }
    
    abstract class Proc_Chance extends Proc
    {
        final ProcChance chance;
        
        Proc_Chance(final String name, final ProcChance chance) {
            super(name);
            this.chance = chance;
        }
        
        @Override
        void reset() {
            super.reset();
            this.chance.reset();
        }
        
        @Override
        String type() {
            final String s = this.getClass().getSimpleName();
            return s.substring(s.indexOf(95) + 1);
        }
    }
    
    abstract class Proc_Occur extends Proc_Chance
    {
        Proc_Occur(final String name, final ProcChance chance) {
            super(name, chance);
        }
        
        abstract void occured(final Enemy p0, final String p1, final Source p2, final double p3, final boolean p4);
        
        @Override
        void run(final Enemy enemy, final String triggerName, final Source src, final double dmg, final boolean crit, final int baseTime) {
            if (this.chance.shouldProc() || src == null) {
                this.occured(enemy, triggerName, src, dmg, crit);
            }
        }
    }
    
    abstract class Proc_Duration extends Proc_Chance
    {
        final int dur;
        
        Proc_Duration(final String name, final ProcChance chance, final int dur) {
            super(name, chance);
            this.dur = dur;
        }
    }
    
    abstract class Effect
    {
        abstract void addEffect();
        
        abstract void removeEffect();
    }
    
    class Effect_Stat extends Effect
    {
        final int index;
        final int value;
        
        Effect_Stat(final int index, final int value) {
            this.index = index;
            this.value = value;
        }
        
        @Override
        void addEffect() {
            final int[] procStats = FeralSim.this.procStats;
            final int index = this.index;
            procStats[index] += this.value;
        }
        
        @Override
        void removeEffect() {
            final int[] procStats = FeralSim.this.procStats;
            final int index = this.index;
            procStats[index] -= this.value;
        }
    }
    
    static class Proc_MultiStat_Type
    {
        final int index;
        final EventKey key;
        int count;
        int uptime;
        
        Proc_MultiStat_Type(final int index, final EventKey key) {
            this.index = index;
            this.key = key;
        }
    }
    
    class Proc_MultiStat extends Proc_Duration
    {
        final Proc_MultiStat_Type[] types;
        final int value;
        
        Proc_MultiStat(final String name, final ProcChance chance, final int dur, final int value, final int... indexes) {
            super(name, chance, dur);
            this.value = value;
            this.types = new Proc_MultiStat_Type[indexes.length];
            for (int i = 0; i < indexes.length; ++i) {
                this.types[i] = new Proc_MultiStat_Type(indexes[i], new EventKey());
            }
        }
        
        @Override
        void toString(final StringBuilder sb) {
            sb.append(" for +");
            sb.append(this.value);
            sb.append(' ');
            sb.append(FeralSim.STATS[this.types[0].index]);
            for (int i = 1; i < this.types.length; ++i) {
                sb.append("\u200b/\u200b");
                sb.append(FeralSim.STATS[this.types[i].index]);
            }
            sb.append(" for ");
            sb.append(Fmt.msDuration(this.dur));
            sb.append('.');
        }
        
        @Override
        void reset() {
            super.reset();
            for (final Proc_MultiStat_Type x : this.types) {
                x.count = 0;
                x.uptime = 0;
                x.key.exists = false;
            }
        }
        
        @Override
        public int getUptime() {
            int sum = 0;
            for (final Proc_MultiStat_Type x : this.types) {
                sum += x.uptime;
            }
            return sum;
        }
        
        @Override
        void run(final Enemy enemy, final String triggerName, final Source src, final double dmg, final boolean crit, final int baseTime) {
            if (this.chance.shouldProc() || src == null) {
                final Proc_MultiStat_Type type = this.types[FeralSim.this.rng.nextInt(this.types.length)];
                FeralSim.this.addEvent(new Event(FeralSim.this.clock + this.dur, type.key) {
                    final int start = FeralSim.this.clock;
                    
                    @Override
                    void added(final boolean replaced) {
                        if (FeralSim.this.printLog) {
                            FeralSim.this.print("%s%s/%s", replaced ? "~" : "+", Proc_MultiStat.this.name, FeralSim.STATS[type.index].abbr);
                        }
                        final Proc_MultiStat_Type val$type = type;
                        ++val$type.count;
                        final int[] procStats = FeralSim.this.procStats;
                        final int index = type.index;
                        procStats[index] += Proc_MultiStat.this.value;
                        FeralSim.this.fireStatChange();
                    }
                    
                    @Override
                    void run() {
                        if (FeralSim.this.printLog) {
                            FeralSim.this.print("-%s/%s", Proc_MultiStat.this.name, FeralSim.STATS[type.index].abbr);
                        }
                    }
                    
                    @Override
                    void faded(final boolean executed) {
                        final Proc_MultiStat_Type val$type = type;
                        val$type.uptime += FeralSim.this.clock - this.start;
                        this.remove();
                        FeralSim.this.fireStatChange();
                    }
                    
                    @Override
                    boolean futureProc() {
                        this.remove();
                        return true;
                    }
                    
                    private void remove() {
                        final int[] procStats = FeralSim.this.procStats;
                        final int index = type.index;
                        procStats[index] -= Proc_MultiStat.this.value;
                    }
                });
            }
        }
    }
    
    abstract class Proc_Unique extends Proc_Duration
    {
        final EventKey key;
        
        Proc_Unique(final String name, final ProcChance chance, final int dur) {
            super(name, chance, dur);
            this.key = new EventKey();
        }
        
        @Override
        void reset() {
            super.reset();
            this.key.exists = false;
        }
    }
    
    abstract class Proc_Single extends Proc_Unique
    {
        int uptime;
        int count;
        
        Proc_Single(final String name, final ProcChance chance, final int dur) {
            super(name, chance, dur);
        }
        
        abstract void addEffect();
        
        abstract void removeEffect();
        
        void killEffect() {
        }
        
        abstract String getInitialEffect();
        
        @Override
        void reset() {
            super.reset();
            this.uptime = 0;
            this.count = 0;
        }
        
        @Override
        public int getUptime() {
            return this.uptime;
        }
        
        @Override
        void run(final Enemy enemy, final String triggerName, final Source src, final double dmg, final boolean crit, final int baseTime) {
            if (this.chance.shouldProc() || src == null) {
                FeralSim.this.addEvent(new Event(FeralSim.this.clock + this.dur, this.key) {
                    final int start = FeralSim.this.clock;
                    
                    @Override
                    void added(final boolean replaced) {
                        Proc_Single.this.addEffect();
                        if (FeralSim.this.printLog) {
                            final String effect = Proc_Single.this.getInitialEffect();
                            if (effect == null) {
                                FeralSim.this.print("%s%s", replaced ? "~" : "+", Proc_Single.this.name);
                            }
                            else {
                                FeralSim.this.print("%s%s: %s", replaced ? "~" : "+", Proc_Single.this.name, effect);
                            }
                        }
                        final Proc_Single this$1 = Proc_Single.this;
                        ++this$1.count;
                        FeralSim.this.fireStatChange();
                    }
                    
                    @Override
                    void run() {
                        if (FeralSim.this.printLog) {
                            FeralSim.this.print("-%s", Proc_Single.this.name);
                        }
                    }
                    
                    @Override
                    void faded(final boolean executed) {
                        final Proc_Single this$1 = Proc_Single.this;
                        this$1.uptime += FeralSim.this.clock - this.start;
                        Proc_Single.this.removeEffect();
                        FeralSim.this.fireStatChange();
                        Proc_Single.this.killEffect();
                    }
                    
                    @Override
                    boolean futureProc() {
                        Proc_Single.this.removeEffect();
                        return true;
                    }
                });
            }
        }
    }
    
    class Proc_Single_Stat extends Proc_Single implements StatEffectOrigin
    {
        final StatEffect eff;
        
        Proc_Single_Stat(final String name, final ProcChance chance, final int dur, final int index, final int value) {
            super(name, chance, dur);
            this.eff = new StatEffect(index, value) {
                @Override
                boolean up() {
                    return Proc_Single_Stat.this.key.exists;
                }
            };
        }
        
        @Override
        void addEffect() {
            final int[] procStats = FeralSim.this.procStats;
            final int index = this.eff.index;
            procStats[index] += this.eff.value;
        }
        
        @Override
        void removeEffect() {
            final int[] procStats = FeralSim.this.procStats;
            final int index = this.eff.index;
            procStats[index] -= this.eff.value;
        }
        
        @Override
        String getInitialEffect() {
            return FeralSim.STATS[this.eff.index].formatValue(this.eff.value);
        }
        
        @Override
        void toString(final StringBuilder sb) {
            sb.append(" for ");
            sb.append('+');
            sb.append(this.eff.value);
            sb.append(' ');
            sb.append(FeralSim.STATS[this.eff.index].name);
            sb.append(" for ");
            sb.append(Fmt.msDuration(this.dur));
            sb.append('.');
        }
        
        @Override
        public StatEffect getStatEffect() {
            return this.eff;
        }
    }
    
    class Proc_Single_RoR extends Proc_Single
    {
        int haste;
        int crit;
        int mastery;
        
        Proc_Single_RoR(final String name, final ProcChance chance, final int dur) {
            super(name, chance, dur);
        }
        
        int computeEffect() {
            int m = FeralSim.this._intStat_noBaseOrExtra(FeralSim.STAT_MASTERY);
            int h = FeralSim.this._intStat_noBaseOrExtra(FeralSim.STAT_HASTE);
            int c = FeralSim.this._intStat_noBaseOrExtra(FeralSim.STAT_CRIT);
            if (m > h && m > c) {
                h += FeralSim.this.inertStats[FeralSim.STAT_HASTE];
                c += FeralSim.this.inertStats[FeralSim.STAT_CRIT];
                this.crit = -c;
                this.haste = -h;
                this.mastery = 2 * (h + c);
                return 0;
            }
            if (h > c) {
                m += FeralSim.this.inertStats[FeralSim.STAT_MASTERY];
                c += FeralSim.this.inertStats[FeralSim.STAT_CRIT];
                this.crit = -c;
                this.haste = 2 * (m + c);
                this.mastery = -m;
                return 1;
            }
            m += FeralSim.this.inertStats[FeralSim.STAT_MASTERY];
            h += FeralSim.this.inertStats[FeralSim.STAT_HASTE];
            this.crit = 2 * (m + h);
            this.haste = -h;
            this.mastery = -m;
            return 2;
        }
        
        @Override
        void addEffect() {
            this.computeEffect();
            final int[] procStats = FeralSim.this.procStats;
            final int stat_CRIT = FeralSim.STAT_CRIT;
            procStats[stat_CRIT] += this.crit;
            final int[] procStats2 = FeralSim.this.procStats;
            final int stat_HASTE = FeralSim.STAT_HASTE;
            procStats2[stat_HASTE] += this.haste;
            final int[] procStats3 = FeralSim.this.procStats;
            final int stat_MASTERY = FeralSim.STAT_MASTERY;
            procStats3[stat_MASTERY] += this.mastery;
        }
        
        @Override
        void removeEffect() {
            final int[] procStats = FeralSim.this.procStats;
            final int stat_CRIT = FeralSim.STAT_CRIT;
            procStats[stat_CRIT] -= this.crit;
            final int[] procStats2 = FeralSim.this.procStats;
            final int stat_HASTE = FeralSim.STAT_HASTE;
            procStats2[stat_HASTE] -= this.haste;
            final int[] procStats3 = FeralSim.this.procStats;
            final int stat_MASTERY = FeralSim.STAT_MASTERY;
            procStats3[stat_MASTERY] -= this.mastery;
        }
        
        @Override
        String getInitialEffect() {
            return String.format("%+d Mastery, %+d Crit, %+d Haste", this.mastery, this.crit, this.haste);
        }
        
        @Override
        void toString(final StringBuilder sb) {
            this.computeEffect();
            sb.append(" for ");
            sb.append(String.format("%+d", this.mastery));
            sb.append(' ');
            sb.append(StatT.MASTERY.name);
            sb.append(", ");
            sb.append(String.format("%+d", this.crit));
            sb.append(' ');
            sb.append(StatT.CRIT.name);
            sb.append(", and ");
            sb.append(String.format("%+d", this.haste));
            sb.append(' ');
            sb.append(StatT.HASTE.name);
            sb.append(" for ");
            sb.append(Fmt.msDuration(this.dur));
            sb.append('.');
        }
    }
    
    abstract class Proc_Single_StatRamp extends Proc_Single
    {
        final int max;
        final int freq;
        final int index;
        final int value;
        int cur;
        
        Proc_Single_StatRamp(final String name, final ProcChance chance, final int dur, final int freq, final int max, final int index, final int value) {
            super(name, chance, dur);
            this.max = max;
            this.freq = freq;
            this.index = index;
            this.value = value;
        }
        
        String fmt() {
            return String.format("%+d %s (%d/%d)", this.cur * this.value, FeralSim.STATS[this.index].name, this.cur, this.max);
        }
        
        @Override
        void reset() {
            super.reset();
            this.cur = 0;
        }
        
        @Override
        void killEffect() {
            this.cur = 0;
        }
        
        @Override
        void removeEffect() {
        }
    }
    
    class Proc_Single_StatRamp_Down extends Proc_Single_StatRamp
    {
        Proc_Single_StatRamp_Down(final String name, final ProcChance chance, final int freq, final int max, final int index, final int value) {
            super(name, chance, freq * max, freq, max, index, value);
        }
        
        @Override
        void addEffect() {
            this.cur = this.max;
            final int[] procStats = FeralSim.this.procStats;
            final int index = this.index;
            procStats[index] += this.cur * this.value;
            for (int i = 1; i <= this.max; ++i) {
                FeralSim.this.addEvent(new Event(FeralSim.this.clock + i * this.freq) {
                    @Override
                    void run() {
                        final Proc_Single_StatRamp_Down this$1 = Proc_Single_StatRamp_Down.this;
                        --this$1.cur;
                        final int[] procStats = FeralSim.this.procStats;
                        final int index = Proc_Single_StatRamp_Down.this.index;
                        procStats[index] -= Proc_Single_StatRamp_Down.this.value;
                        if (FeralSim.this.printLog) {
                            FeralSim.this.print("~%s: %s", Proc_Single_StatRamp_Down.this.name, Proc_Single_StatRamp_Down.this.fmt());
                        }
                        FeralSim.this.fireStatChange();
                    }
                    
                    @Override
                    boolean futureProc() {
                        final int[] procStats = FeralSim.this.procStats;
                        final int index = Proc_Single_StatRamp_Down.this.index;
                        procStats[index] -= Proc_Single_StatRamp_Down.this.value;
                        return true;
                    }
                });
            }
        }
        
        @Override
        String getInitialEffect() {
            return this.fmt();
        }
        
        @Override
        void toString(final StringBuilder sb) {
            sb.append(" for +");
            sb.append(this.value * this.max);
            sb.append(' ');
            sb.append(FeralSim.STATS[this.index].name);
            sb.append(", losing ");
            sb.append(this.value);
            sb.append(' ');
            sb.append(FeralSim.STATS[this.index].name);
            sb.append(" every ");
            sb.append(Fmt.msDuration(this.freq));
            sb.append(", fading after ");
            sb.append(Fmt.msDuration(this.dur));
            sb.append('.');
        }
    }
    
    class Proc_Single_StatRamp_Up extends Proc_Single_StatRamp
    {
        Proc_Single_StatRamp_Up(final String name, final ProcChance chance, final int freq, final int max, final int index, final int value) {
            super(name, chance, max * freq, freq, max, index, value);
        }
        
        @Override
        void addEffect() {
            this.cur = 1;
            final int[] procStats = FeralSim.this.procStats;
            final int index = this.index;
            procStats[index] += this.value;
            for (int i = 1; i < this.max; ++i) {
                FeralSim.this.addEvent(new Event(FeralSim.this.clock + this.freq * i) {
                    @Override
                    void run() {
                        final Proc_Single_StatRamp_Up this$1 = Proc_Single_StatRamp_Up.this;
                        ++this$1.cur;
                        final int[] procStats = FeralSim.this.procStats;
                        final int index = Proc_Single_StatRamp_Up.this.index;
                        procStats[index] += Proc_Single_StatRamp_Up.this.value;
                        if (FeralSim.this.printLog) {
                            FeralSim.this.print("~%s: %s", Proc_Single_StatRamp_Up.this.name, Proc_Single_StatRamp_Up.this.fmt());
                        }
                        FeralSim.this.fireStatChange();
                    }
                    
                    @Override
                    boolean futureProc() {
                        final int[] procStats = FeralSim.this.procStats;
                        final int index = Proc_Single_StatRamp_Up.this.index;
                        procStats[index] += Proc_Single_StatRamp_Up.this.value;
                        return true;
                    }
                });
            }
        }
        
        @Override
        void removeEffect() {
            final int[] procStats = FeralSim.this.procStats;
            final int index = this.index;
            procStats[index] -= this.max * this.value;
        }
        
        @Override
        String getInitialEffect() {
            return this.fmt();
        }
        
        @Override
        void toString(final StringBuilder sb) {
            sb.append(" for stacking +");
            sb.append(this.value);
            sb.append(' ');
            sb.append(FeralSim.STATS[this.index].name);
            sb.append(", ");
            sb.append(this.max);
            sb.append(" times, every ");
            sb.append(Fmt.msDuration(this.freq));
            sb.append(", for a total of +");
            sb.append(this.value * this.max);
            sb.append(' ');
            sb.append(FeralSim.STATS[this.index].name);
            sb.append(" after ");
            sb.append(Fmt.msDuration(this.max * this.freq));
            sb.append('.');
        }
    }
    
    class Proc_Single_StatRampUp_FuryOfTheBeast extends Proc_Single_StatRamp_Up
    {
        Proc_Single_StatRampUp_FuryOfTheBeast(final int agi) {
            super("Fury of the Beast", new ProcChance_Prob(FeralSim.this, 0.15, 55000), 10, 2000, FeralSim.STAT_AGI, agi);
        }
    }
    
    abstract class Proc_Stacking extends Proc_Unique
    {
        final int max;
        final int[] uptime;
        int cur;
        int count;
        
        Proc_Stacking(final String name, final ProcChance chance, final int max, final int dur) {
            super(name, chance, dur);
            this.max = max;
            this.uptime = new int[max];
        }
        
        abstract void deltaEffect(final int p0);
        
        abstract String getEffect();
        
        @Override
        void reset() {
            super.reset();
            for (int i = 0; i < this.max; ++i) {
                this.uptime[i] = 0;
            }
            this.cur = 0;
            this.count = 0;
        }
        
        @Override
        public int getUptime() {
            return this.uptime();
        }
        
        int uptime() {
            int sum = 0;
            for (final int x : this.uptime) {
                sum += x;
            }
            return sum;
        }
        
        @Override
        void run(final Enemy enemy, final String triggerName, final Source src, final double dmg, final boolean crit, final int baseTime) {
            if (this.chance.shouldProc()) {
                FeralSim.this.addEvent(new Event(FeralSim.this.clock + this.dur, this.key) {
                    final int start = FeralSim.this.clock;
                    
                    @Override
                    void added(final boolean replaced) {
                        final Proc_Stacking this$1 = Proc_Stacking.this;
                        ++this$1.count;
                        if (!replaced) {
                            Proc_Stacking.this.cur = 1;
                        }
                        else if (Proc_Stacking.this.cur < Proc_Stacking.this.max) {
                            final Proc_Stacking this$2 = Proc_Stacking.this;
                            ++this$2.cur;
                        }
                        if (FeralSim.this.printLog) {
                            final String effect = Proc_Stacking.this.getEffect();
                            if (effect == null) {
                                FeralSim.this.print("%s%s (%d/%d)", replaced ? "~" : "+", Proc_Stacking.this.name, Proc_Stacking.this.cur, Proc_Stacking.this.max);
                            }
                            else {
                                FeralSim.this.print("%s%s: %s (%d/%d)", replaced ? "~" : "+", Proc_Stacking.this.name, effect, Proc_Stacking.this.cur, Proc_Stacking.this.max);
                            }
                        }
                        Proc_Stacking.this.deltaEffect(1);
                        FeralSim.this.fireStatChange();
                    }
                    
                    @Override
                    void faded(final boolean executed) {
                        final int[] uptime = Proc_Stacking.this.uptime;
                        final int n = Proc_Stacking.this.cur - 1;
                        uptime[n] += FeralSim.this.clock - this.start;
                    }
                    
                    @Override
                    void run() {
                        if (FeralSim.this.printLog) {
                            FeralSim.this.print("-%s (%d/%d)", Proc_Stacking.this.name, Proc_Stacking.this.cur, Proc_Stacking.this.max);
                        }
                        Proc_Stacking.this.deltaEffect(-Proc_Stacking.this.cur);
                        FeralSim.this.fireStatChange();
                    }
                    
                    @Override
                    boolean futureProc() {
                        Proc_Stacking.this.deltaEffect(-Proc_Stacking.this.cur);
                        return true;
                    }
                });
            }
        }
    }
    
    class Proc_Stacking_Stat extends Proc_Stacking
    {
        final int index;
        final int value;
        
        Proc_Stacking_Stat(final String name, final ProcChance chance, final int max, final int dur, final int index, final int value) {
            super(name, chance, max, dur);
            this.index = index;
            this.value = value;
        }
        
        @Override
        void deltaEffect(final int delta) {
            final int[] procStats = FeralSim.this.procStats;
            final int index = this.index;
            procStats[index] += delta * this.value;
        }
        
        @Override
        String getEffect() {
            return FeralSim.STATS[this.index].formatValue(this.cur * this.value);
        }
        
        @Override
        void toString(final StringBuilder sb) {
            sb.append(" for +");
            sb.append(this.value);
            sb.append(' ');
            sb.append(FeralSim.STATS[this.index].name);
            sb.append(" for ");
            sb.append(Fmt.msDuration(this.dur));
            sb.append(", stacking up to ");
            sb.append(this.max);
            sb.append(" times, for a total of +");
            sb.append(this.max * this.value);
            sb.append(' ');
            sb.append(FeralSim.STATS[this.index].name);
            sb.append('.');
        }
    }
    
    class ProcChance_Prob extends ProcChance
    {
        final int icd;
        final double prob;
        int ready;
        
        ProcChance_Prob(final FeralSim this$0, final double prob) {
            this(this$0, prob, 0);
        }
        
        ProcChance_Prob(final double prob, final int icd) {
            this.icd = icd;
            this.prob = prob;
        }
        
        @Override
        void reset() {
            this.ready = 0;
            if (this.icd > 0) {
                if (FeralSim.this.cfg.procResetTime == -2) {
                    this.ready = -FeralSim.this.rng.nextInt(this.icd);
                }
                else if (FeralSim.this.cfg.procResetTime >= 0) {
                    this.ready = this.icd - FeralSim.this.cfg.procResetTime;
                }
            }
        }
        
        @Override
        void beginCooldown() {
            this.ready = FeralSim.this.clock + this.icd;
        }
        
        @Override
        boolean shouldProc() {
            if (FeralSim.this.clock >= this.ready && FeralSim.this.chance(this.prob)) {
                this.beginCooldown();
                return true;
            }
            return false;
        }
        
        @Override
        void toString(final StringBuilder sb) {
            if (this.icd > 0) {
                sb.append(String.format("%.2f%% chance (%s ICD)", 100.0 * this.prob, Fmt.msDuration(this.icd)));
            }
            else {
                sb.append(String.format("%.2f%% chance", 100.0 * this.prob));
            }
        }
    }
    
    class ProcChance_PPM extends ProcChance
    {
        final double ppm;
        
        ProcChance_PPM(final double ppm) {
            this.ppm = ppm;
        }
        
        @Override
        void reset() {
        }
        
        @Override
        void beginCooldown() {
        }
        
        @Override
        boolean shouldProc() {
            return FeralSim.this.chance(this.ppm * FeralSim.this.baseSwingTime() / 60000.0);
        }
        
        @Override
        void toString(final StringBuilder sb) {
            sb.append(String.format("%.2f PPM", this.ppm));
        }
    }
    
    class ProcChance_RPPM extends ProcChance
    {
        final double ppm;
        final int icd;
        final boolean haste;
        int ready;
        int oppCount;
        int lastTime;
        int lastProc;
        
        ProcChance_RPPM(final double ppm, final int icd, final boolean haste) {
            this.ppm = ppm;
            this.icd = icd;
            this.haste = haste;
        }
        
        @Override
        void reset() {
            this.ready = 0;
            this.oppCount = 0;
            if (FeralSim.this.cfg.procResetTime == -2) {
                final int n = -FeralSim.this.rng.nextInt((int)(120000.0 / this.ppm));
                this.lastProc = n;
                this.lastTime = n;
            }
            else if (FeralSim.this.cfg.procResetTime == -1) {
                final int n2 = -120000;
                this.lastProc = n2;
                this.lastTime = n2;
            }
            else {
                final int n3 = -FeralSim.this.cfg.procResetTime;
                this.lastProc = n3;
                this.lastTime = n3;
            }
        }
        
        @Override
        void beginCooldown() {
            this.lastTime = FeralSim.this.clock;
            this.lastProc = FeralSim.this.clock;
            this.ready = FeralSim.this.clock + this.icd;
        }
        
        @Override
        boolean shouldProc() {
            if (FeralSim.this.clock >= this.ready) {
                final int sinceLast = FeralSim.this.clock - this.lastTime;
                if (sinceLast > 0) {
                    final double hppm = this.hppm();
                    final double norm = Math.min(10000, sinceLast) / 60000.0;
                    final double wait = Math.max(1.0, 3 * Math.min(1000000, FeralSim.this.clock - this.lastProc) / Math.max(this.icd, 60000.0 / hppm) - 3.5);
                    final double prob = hppm * wait * norm;
                    ++this.oppCount;
                    if (FeralSim.this.chance(prob)) {
                        this.beginCooldown();
                        return true;
                    }
                    this.lastTime = FeralSim.this.clock;
                }
            }
            return false;
        }
        
        double hppm() {
            return this.haste ? (this.ppm * Math.max(FeralSim.this.getMeleeHasteMod(), FeralSim.this.getSpellHasteMod())) : this.ppm;
        }
        
        @Override
        void toString(final StringBuilder sb) {
            sb.append(String.format("%.2f", this.hppm()));
            if (this.haste) {
                sb.append(" Hasted (");
                sb.append(String.format("%.2f", this.ppm));
                sb.append(")");
            }
            sb.append(" RPPM");
            if (this.icd > 0) {
                sb.append(" (");
                sb.append(Fmt.msDuration(this.icd));
                sb.append(" ICD)");
            }
        }
    }
    
    abstract static class Logic
    {
        void reset() {
        }
        
        abstract void run();
    }
    
    static class ProcHandler
    {
        final Proc proc;
        final int mask;
        final boolean critOnly;
        
        ProcHandler(final Proc proc, final boolean critOnly, final Source... srcs) {
            this.proc = proc;
            int bits = 0;
            for (final Source x : srcs) {
                bits |= 1 << x.ordinal();
            }
            this.mask = bits;
            this.critOnly = critOnly;
        }
        
        boolean matches(final int bit, final boolean crit) {
            return (this.mask & bit) == bit && (crit || !this.critOnly) && this.couldProc();
        }
        
        String procName() {
            return this.name(this.proc.name);
        }
        
        String name(final String x) {
            return x;
        }
        
        boolean couldProc() {
            return true;
        }
        
        @Override
        public String toString() {
            return this.procName();
        }
    }
    
    class ProcHandler_GearSpecific extends ProcHandler
    {
        final GearConfig gear;
        
        ProcHandler_GearSpecific(final GearConfig gear, final Proc proc, final boolean critOnly, final Source... srcs) {
            super(proc, critOnly, srcs);
            this.gear = gear;
        }
        
        @Override
        boolean couldProc() {
            return FeralSim.this.curGear == this.gear;
        }
        
        @Override
        String name(final String x) {
            return x + "[" + this.gear.name + "]";
        }
    }
    
    class Spell_TricksOfTheTrade extends Spell_BuffCooldown_MultiMod
    {
        Spell_TricksOfTheTrade(final int dur, final int cd) {
            super("Tricks of the Trade", dur, cd, FeralSim.this.allDmgMods, -11, 1.15);
        }
    }
    
    class Spell_SkullBanner extends Spell_BuffCooldown_MultiMod
    {
        Spell_SkullBanner(final int dur, final int cd) {
            super("Skull Banner", dur, cd, FeralSim.this.critDmgMods, -8, 1.2);
        }
    }
    
    class Spell_ShatteringThrow extends Spell_BuffCooldown
    {
        Enemy selected;
        
        Spell_ShatteringThrow(final int dur, final int cd) {
            super("Shattering Throw", dur, cd);
        }
        
        @Override
        boolean usable() {
            return super.usable() && FeralSim.this.target != null;
        }
        
        @Override
        void buffAdded() {
            this.selected = FeralSim.this.target;
            this.selected.armor.add(-4, 0.8);
        }
        
        @Override
        void buffFaded() {
            this.selected.armor.remove(-4);
            this.selected = null;
        }
    }
    
    class Spell_Stormlash extends Spell_BuffCooldown
    {
        static final int DUR = 10000;
        final Proc proc;
        final ProcHandler handler;
        
        Spell_Stormlash(final int dur, final int cd) {
            super("Stormlash Totem", dur, cd);
            this.proc = new Proc("Stormlash") {
                int next;
                
                @Override
                void reset() {
                    super.reset();
                    this.next = 0;
                }
                
                @Override
                void run(final Enemy enemy, final String triggerName, final Source src, final double dmg_ignored, final boolean crit_ignored, final int baseTime) {
                    if (FeralSim.this.clock < this.next || !FeralSim.this.chance(0.5)) {
                        return;
                    }
                    this.next = FeralSim.this.clock + 100;
                    final AttackT atk = FeralSim.this.yellow_spell(enemy, FeralSim.this.getSpellCrit());
                    String mods = null;
                    if (FeralSim.this.printLog) {
                        final StringBuilder sb = FeralSim.this.sb();
                        FeralSim.this.appendProc_ap(sb);
                        FeralSim.this.appendMulti(sb, FeralSim.this.allDmgMods.product);
                        mods = sb.toString();
                    }
                    if (atk.hit) {
                        double swingMod = 1.0;
                        switch (src) {
                            case SWING: {
                                swingMod *= 0.4 * FeralSim.this.curGear.speed / 2600.0;
                                break;
                            }
                            case MELEE: {
                                break;
                            }
                            default: {
                                swingMod *= Math.max(baseTime, 1500) / 1500.0;
                                break;
                            }
                        }
                        double dmg = 0.4 * swingMod * FeralSim.this.getAP() * FeralSim.this.flux(0.15) * FeralSim.this.allDmgMods.product * enemy.spellDmgMod();
                        if (atk.crit) {
                            dmg *= FeralSim.this.critDmgMods.product;
                        }
                        FeralSim.this.recordDamage(enemy, this.name, FeralSim.this.printLog ? (this.name + "#" + triggerName) : this.name, mods, atk, dmg);
                    }
                    else {
                        FeralSim.this.logMiss(enemy, this.name, mods, atk);
                    }
                }
            };
            this.handler = new ProcHandler(this.proc, false, new Source[] { Source.SWING, Source.MELEE, Source.SPELL });
        }
        
        @Override
        void reset() {
            super.reset();
            this.proc.reset();
        }
        
        @Override
        void buffAdded() {
            FeralSim.this.procs.add(this.handler);
        }
        
        @Override
        void buffFaded() {
            FeralSim.this.procs.remove(this.handler);
        }
    }
    
    static class Origin
    {
        final Object key;
        final int spellId;
        final Object spell;
        
        Origin(final int spellId, final Object key, final Object spell) {
            this.spellId = spellId;
            this.key = key;
            this.spell = spell;
        }
    }
    
    public static class TrinketResults
    {
        final double[] dps;
        final double[] t1_up;
        final double[] t2_up;
        
        TrinketResults(final double[] dps, final double[] t1_up, final double[] t2_up) {
            this.dps = dps;
            this.t1_up = t1_up;
            this.t2_up = t2_up;
        }
    }
    
    enum ModType
    {
        DAMAGE, 
        STAT, 
        ARMOR, 
        CRIT;
    }
    
    static class TableMod
    {
        String name;
        ModType type;
        double mod;
        int[] stats;
        
        TableMod(final String name, final ModType type, final double mod, final int[] stats) {
            this.name = name;
            this.type = type;
            this.mod = mod;
            this.stats = stats;
        }
        
        static TableMod make(final String name, final double mod) {
            return new TableMod(name, ModType.DAMAGE, mod, null);
        }
        
        static TableMod make(final String name, final ModType type, final double mod) {
            return new TableMod(name, type, mod, null);
        }
        
        static TableMod make(final String name, final int... stats) {
            return new TableMod(name, ModType.STAT, 1.0, stats);
        }
        
        void apply(final int[] outStats) {
            if (this.stats == null) {
                return;
            }
            for (int j = 0; j < this.stats.length; j += 2) {
                final int n = this.stats[j];
                outStats[n] += this.stats[j + 1];
            }
        }
        
        int tot() {
            if (this.stats == null) {
                return 0;
            }
            int sum = 0;
            for (int j = 1; j < this.stats.length; j += 2) {
                sum += this.stats[j];
            }
            return sum;
        }
    }
    
    static class DamageEntry
    {
        final DamageTable table;
        final String extra;
        final TableMod[] mods;
        final int ap;
        final double crit;
        final double mast;
        final double hitDmg;
        final double critDmg;
        final double avgDmg;
        static final Comparator<DamageEntry> CMP;
        
        DamageEntry(final DamageTable table, final String name, final TableMod[] mods, final double hitDmg, final double critDmg, final double avgDmg, final int ap, final double crit, final double mast) {
            this.table = table;
            this.extra = name;
            this.mods = mods;
            this.hitDmg = hitDmg;
            this.critDmg = critDmg;
            this.avgDmg = avgDmg;
            this.ap = ap;
            this.crit = crit;
            this.mast = mast;
        }
        
        static {
            CMP = new Comparator<DamageEntry>() {
                @Override
                public int compare(final DamageEntry a, final DamageEntry b) {
                    if (a.avgDmg != b.avgDmg) {
                        return Double.compare(-a.avgDmg, -b.avgDmg);
                    }
                    if (a.table == b.table) {
                        return b.mods.length - a.mods.length;
                    }
                    return a.table.spell.actionName.compareTo(b.table.spell.actionName);
                }
            };
        }
    }
    
    static class DamageTable
    {
        String name;
        String extra;
        final Spell spell;
        final boolean bleed;
        final TableMod[] permMods;
        final TableMod[] tempMods;
        final ArrayList<DamageEntry> rows;
        
        DamageTable(final Spell spell, final boolean bleed, final TableMod[] permMods, final TableMod[] tempMods) {
            this.rows = new ArrayList<DamageEntry>();
            this.spell = spell;
            this.bleed = bleed;
            this.permMods = permMods;
            this.tempMods = tempMods;
        }
        
        double baseDmg() {
            for (final DamageEntry x : this.rows) {
                if (x.mods.length == 0) {
                    return x.avgDmg;
                }
            }
            return 1.0;
        }
    }
    
    abstract class Spell_Cooldown extends Spell
    {
        final int baseCooldown;
        int uptime;
        int cooldown;
        
        Spell_Cooldown(final String name, final int cd) {
            super(name);
            this.baseCooldown = cd;
            this.cooldown = this.baseCooldown;
        }
        
        void chainCooldown(final int players) {
            this.cooldown = this.baseCooldown / players;
        }
        
        void reduceCooldown(final double crr) {
            this.cooldown = (int)(this.baseCooldown / (1.0 + crr));
        }
        
        void restoreCooldown() {
            this.cooldown = this.baseCooldown;
        }
        
        @Override
        void reset() {
            super.reset();
            this.uptime = 0;
        }
        
        @Override
        boolean casted() {
            this.beginCooldown();
            this.exec();
            return true;
        }
        
        abstract void exec();
        
        void beginCooldown() {
            this.beginCooldown(this.cooldown);
        }
        
        void beginCooldown(final int cd) {
            this.ready = FeralSim.this.clock + cd;
        }
    }
    
    abstract class Spell_EffectCooldown extends Spell_Cooldown
    {
        Spell_EffectCooldown(final String name, final int cd) {
            super(name, cd);
        }
        
        abstract void appendEffect(final StringBuilder p0);
    }
    
    class Spell_BuffCooldown extends Spell_Cooldown
    {
        final int dur;
        
        Spell_BuffCooldown(final String name, final int dur, final int cd) {
            super(name, cd);
            this.dur = dur;
        }
        
        void buffAdded() {
        }
        
        void buffFaded() {
        }
        
        boolean fadeProjection() {
            return false;
        }
        
        boolean up() {
            return this.eventKey.exists;
        }
        
        boolean stillUp(final int t) {
            return this.eventKey.stillUp(t);
        }
        
        boolean fallsOff(final int t) {
            return this.eventKey.fallsOff(t);
        }
        
        int timeLeft() {
            return this.eventKey.timeLeft();
        }
        
        boolean cancelAura() {
            return FeralSim.this.removeEvent(this.eventKey, true);
        }
        
        String gainName() {
            return this.actionName;
        }
        
        @Override
        void exec() {
            FeralSim.this.addEvent(new Fader(FeralSim.this.clock + this.dur, this.eventKey, this.gainName(), this.actionName) {
                @Override
                void uptime(final int t) {
                    final Spell_BuffCooldown this$1 = Spell_BuffCooldown.this;
                    this$1.uptime += t;
                }
                
                @Override
                void faded(final boolean executed) {
                    super.faded(executed);
                    Spell_BuffCooldown.this.buffFaded();
                }
                
                @Override
                void added(final boolean replaced) {
                    super.added(replaced);
                    Spell_BuffCooldown.this.buffAdded();
                }
                
                @Override
                boolean futureProc() {
                    return Spell_BuffCooldown.this.fadeProjection();
                }
            });
        }
    }
    
    abstract static class StatEffect
    {
        public final int index;
        public final int value;
        
        StatEffect(final int index, final int value) {
            this.index = index;
            this.value = value;
        }
        
        abstract boolean up();
    }
    
    class Spell_BuffCooldown_Stat extends Spell_BuffCooldown implements StatEffectOrigin
    {
        final StatEffect eff;
        
        Spell_BuffCooldown_Stat(final String name, final int dur, final int cd, final int index, final int value) {
            super(name, dur, cd);
            this.eff = new StatEffect(index, value) {
                @Override
                boolean up() {
                    return Spell_BuffCooldown_Stat.this.up();
                }
            };
        }
        
        @Override
        void buffAdded() {
            final int[] procStats = FeralSim.this.procStats;
            final int index = this.eff.index;
            procStats[index] += this.eff.value;
            FeralSim.this.fireStatChange();
        }
        
        @Override
        void buffFaded() {
            final int[] procStats = FeralSim.this.procStats;
            final int index = this.eff.index;
            procStats[index] -= this.eff.value;
            FeralSim.this.fireStatChange();
        }
        
        @Override
        boolean fadeProjection() {
            final int[] procStats = FeralSim.this.procStats;
            final int index = this.eff.index;
            procStats[index] -= this.eff.value;
            return true;
        }
        
        @Override
        public StatEffect getStatEffect() {
            return this.eff;
        }
    }
    
    class Spell_BuffCooldown_Stat_Shared extends Spell_BuffCooldown_Stat
    {
        Spell_BuffCooldown_Stat_Shared(final String name, final int dur, final int cd, final int index, final int value) {
            super(name, dur, cd, index, value);
        }
        
        @Override
        boolean usable() {
            return super.usable() && FeralSim.this.clock >= FeralSim.this.shared_use_ready;
        }
        
        @Override
        void exec() {
            super.exec();
            FeralSim.this.shared_use_ready = this.eventKey.expires;
        }
    }
    
    class Spell_BuffCooldown_MultiMod extends Spell_BuffCooldown
    {
        final Multiplicative multi;
        double value;
        final int key;
        
        Spell_BuffCooldown_MultiMod(final String name, final int dur, final int cd, final Multiplicative multi, final int key, final double value) {
            super(name, dur, cd);
            this.multi = multi;
            this.key = key;
            this.value = value;
        }
        
        @Override
        void buffAdded() {
            this.multi.add(this.key, this.value);
        }
        
        @Override
        void buffFaded() {
            this.multi.remove(this.key);
        }
    }
    
    class DebugAction extends Action
    {
        final Action inner;
        final String debug;
        
        DebugAction(final Action inner, final String debug) {
            super("Debug(" + inner.actionName + ")");
            this.inner = inner;
            this.debug = debug;
        }
        
        @Override
        public void runAction() {
            if (FeralSim.this.printDebug) {
                FeralSim.this.print("%s: %s", this.actionName, this.debug);
            }
            this.inner.runAction();
        }
        
        @Override
        public boolean actionable() {
            return this.inner.actionable();
        }
    }
    
    static class SimcAction
    {
        final Action action;
        final Expr cond;
        final boolean cycle;
        
        SimcAction(final Action action, final Expr cond, final boolean cycle) {
            this.action = action;
            this.cond = cond;
            this.cycle = cycle;
        }
        
        boolean runnable() {
            return this.action.actionable() && this.cond.getBoolean();
        }
    }
    
    class SimcLogic extends Logic
    {
        final SimcAction[] main;
        SimcAction[] current;
        
        SimcLogic(final SimcAction[] main) {
            this.main = main;
        }
        
        @Override
        void reset() {
            this.current = this.main;
        }
        
        public void run() {
            this.execute(this.current);
        }
        
        void execute(final SimcAction[] actions) {
            for (final SimcAction x : actions) {
                if (x.cycle) {
                    final Enemy save = FeralSim.this.target;
                    try {
                        for (final Enemy e : FeralSim.this.activeTargets) {
                            FeralSim.this.target = e;
                            if (x.runnable()) {
                                x.action.runAction();
                            }
                        }
                    }
                    finally {
                        FeralSim.this.target = save;
                    }
                }
                else if (x.runnable()) {
                    x.action.runAction();
                }
            }
        }
    }
    
    static class Input
    {
        final char[] buf;
        int pos;
        
        Input(final char[] v) {
            this.buf = v;
        }
        
        boolean more() {
            return this.pos < this.buf.length;
        }
        
        char peek() {
            return this.buf[this.pos];
        }
        
        char peek(final int extra) {
            return this.buf[this.pos + extra];
        }
        
        void require(final char ch, final String err) {
            if (this.peek() != ch) {
                throw new RuntimeException(err);
            }
            ++this.pos;
        }
    }
    
    abstract static class Expr
    {
        double getDouble() {
            return this.getBoolean() ? 1.0 : 0.0;
        }
        
        boolean getBoolean() {
            final double x = this.getDouble();
            return x == x && x != 0.0;
        }
    }
    
    abstract static class UnaryExpr extends Expr
    {
        final Expr expr;
        
        public UnaryExpr(final Expr inner) {
            this.expr = inner;
        }
    }
    
    static class UnaryExpr_Not extends UnaryExpr
    {
        UnaryExpr_Not(final Expr inner) {
            super(inner);
        }
        
        @Override
        boolean getBoolean() {
            return !this.expr.getBoolean();
        }
        
        @Override
        public String toString() {
            return "!" + this.expr;
        }
    }
    
    static class UnaryExpr_Minus extends UnaryExpr
    {
        UnaryExpr_Minus(final Expr inner) {
            super(inner);
        }
        
        @Override
        double getDouble() {
            return -this.expr.getDouble();
        }
        
        @Override
        public String toString() {
            return "-" + this.expr;
        }
    }
    
    abstract static class NamedExpr extends Expr
    {
        final String name;
        
        NamedExpr(final String name) {
            this.name = name;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
    }
    
    abstract static class BinaryExpr extends NamedExpr
    {
        final Expr left;
        final Expr right;
        
        BinaryExpr(final String name, final Expr left, final Expr right) {
            super(name);
            this.left = left;
            this.right = right;
        }
        
        @Override
        public String toString() {
            return String.format("%s[%s,%s]", this.name, this.left, this.right);
        }
    }
    
    static class BinaryExpr_And extends BinaryExpr
    {
        BinaryExpr_And(final Expr left, final Expr right) {
            super("And", left, right);
        }
        
        public boolean getBoolean() {
            return this.left.getBoolean() && this.right.getBoolean();
        }
    }
    
    interface StatEffectOrigin
    {
        StatEffect getStatEffect();
    }
    
    interface SpellGetter
    {
        Spell get();
    }
    
    interface EncounterTime
    {
        int getRemaining();
    }
    
    interface Condition
    {
        boolean test();
    }
}
