// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class FeralConfig
{
    boolean enable_lotp;
    boolean enable_ooc;
    boolean enable_refund;
    boolean enable_0comboPS;
    boolean enable_retardFoN;
    boolean enable_tfDuringBerserk;
    boolean enable_properRipExtend;
    boolean enable_localRipExtend;
    boolean hotw_swap;
    boolean hotw_wrath;
    boolean hotw_hurricane;
    boolean hotw_beforeBerserk;
    boolean hotw_bitw;
    int energy0;
    boolean pre_ht;
    boolean pre_sr;
    int procResetTime;
    IntDist react;
    IntDist delay;
    int poolMax;
    boolean random_ranges;
    boolean debuff_bleeding;
    boolean debuff_meleeTaken;
    boolean debuff_spellTaken;
    FeralSim.WeakenedArmor debuff_armor;
    boolean buff_sta;
    boolean buff_stat;
    boolean buff_crit;
    boolean buff_ap;
    boolean buff_sp;
    boolean buff_mastery;
    boolean buff_meleeHaste;
    boolean buff_spellHaste;
    final StatMap extra_inert;
    final StatMap extra_active;
    StatValue food;
    Flask flask;
    StatValue pre_pot;
    StatValue pot;
    int num_skullBanner;
    int num_shatteringThrow;
    int num_stormlashTotem;
    int num_unholyFrenzy;
    int num_tricksOfTheTrade;
    boolean enable_lifeblood;
    boolean enable_berserking;
    boolean challengeMode;
    final SetBonusMap setBonuses;
    FeralSim.Heroism heroism;
    boolean heroismDrums;
    IntDist heroismDelay;
    FeralSim.Opener opener;
    FeralSim.Generator generator;
    FeralSim.Symbiosis symbiosis;
    FeralSim.ThrashStyle thrashStyle;
    FeralSim.Finisher0 finisher0;
    double rakeClipMultiplier;
    double ripClipMultiplier;
    
    public FeralConfig() {
        this.enable_lotp = true;
        this.enable_ooc = true;
        this.enable_refund = true;
        this.enable_0comboPS = true;
        this.enable_retardFoN = true;
        this.enable_tfDuringBerserk = false;
        this.enable_properRipExtend = false;
        this.enable_localRipExtend = false;
        this.hotw_swap = false;
        this.hotw_wrath = false;
        this.hotw_hurricane = false;
        this.hotw_beforeBerserk = false;
        this.hotw_bitw = false;
        this.procResetTime = -1;
        this.poolMax = 500;
        this.random_ranges = true;
        this.debuff_armor = FeralSim.WeakenedArmor.FF;
        this.extra_inert = new StatMap();
        this.extra_active = new StatMap();
        this.setBonuses = new SetBonusMap();
        this.heroism = FeralSim.Heroism.NONE;
        this.opener = FeralSim.Opener.NONE;
        this.generator = FeralSim.Generator.SHRANGLE;
        this.symbiosis = FeralSim.Symbiosis.NONE;
        this.thrashStyle = FeralSim.ThrashStyle.MAINTAIN;
        this.finisher0 = FeralSim.Finisher0.DEFAULT;
        this.rakeClipMultiplier = 1.12;
        this.ripClipMultiplier = 1.15;
    }
    
    public String heroismDesc() {
        final String suffix = this.heroismDrums ? " (Drums)" : "";
        if (this.heroism != FeralSim.Heroism.AFTER) {
            return this.heroism + suffix;
        }
        if (this.heroismDelay == null) {
            return this.heroism + ": 0sec" + suffix;
        }
        return this.heroism + ": " + this.heroismDelay.toTime() + suffix;
    }
    
    FeralConfig copy() {
        final FeralConfig copy = new FeralConfig();
        copy.enable_lotp = this.enable_lotp;
        copy.enable_ooc = this.enable_ooc;
        copy.enable_refund = this.enable_refund;
        copy.enable_0comboPS = this.enable_0comboPS;
        copy.enable_retardFoN = this.enable_retardFoN;
        copy.enable_tfDuringBerserk = this.enable_tfDuringBerserk;
        copy.enable_properRipExtend = this.enable_properRipExtend;
        copy.enable_localRipExtend = this.enable_localRipExtend;
        copy.energy0 = this.energy0;
        copy.hotw_swap = this.hotw_swap;
        copy.hotw_wrath = this.hotw_wrath;
        copy.hotw_hurricane = this.hotw_hurricane;
        copy.hotw_beforeBerserk = this.hotw_beforeBerserk;
        copy.hotw_bitw = this.hotw_bitw;
        copy.pre_ht = this.pre_ht;
        copy.pre_sr = this.pre_sr;
        copy.procResetTime = this.procResetTime;
        copy.react = this.react;
        copy.delay = this.delay;
        copy.poolMax = this.poolMax;
        copy.random_ranges = this.random_ranges;
        copy.debuff_bleeding = this.debuff_bleeding;
        copy.debuff_meleeTaken = this.debuff_meleeTaken;
        copy.debuff_spellTaken = this.debuff_spellTaken;
        copy.debuff_armor = this.debuff_armor;
        copy.buff_sta = this.buff_sta;
        copy.buff_stat = this.buff_stat;
        copy.buff_crit = this.buff_crit;
        copy.buff_ap = this.buff_ap;
        copy.buff_sp = this.buff_sp;
        copy.buff_mastery = this.buff_mastery;
        copy.buff_meleeHaste = this.buff_meleeHaste;
        copy.buff_spellHaste = this.buff_spellHaste;
        copy.extra_active.set(this.extra_active);
        copy.extra_inert.set(this.extra_inert);
        copy.food = this.food;
        copy.flask = this.flask;
        copy.pre_pot = this.pre_pot;
        copy.pot = this.pot;
        copy.num_skullBanner = this.num_skullBanner;
        copy.num_shatteringThrow = this.num_shatteringThrow;
        copy.num_stormlashTotem = this.num_stormlashTotem;
        copy.num_unholyFrenzy = this.num_unholyFrenzy;
        copy.num_tricksOfTheTrade = this.num_tricksOfTheTrade;
        copy.enable_lifeblood = this.enable_lifeblood;
        copy.enable_berserking = this.enable_berserking;
        copy.challengeMode = this.challengeMode;
        copy.setBonuses.set(this.setBonuses);
        copy.heroism = this.heroism;
        copy.heroismDrums = this.heroismDrums;
        copy.heroismDelay = this.heroismDelay;
        copy.opener = this.opener;
        copy.generator = this.generator;
        copy.symbiosis = this.symbiosis;
        copy.thrashStyle = this.thrashStyle;
        copy.finisher0 = this.finisher0;
        return copy;
    }
}
