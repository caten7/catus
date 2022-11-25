// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.JProgressBar;
import javax.swing.text.Highlighter;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.JTextArea;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import javax.swing.ToolTipManager;
import java.awt.image.ImageObserver;
import org.json.simple.parser.ParseException;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import java.util.LinkedList;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import javax.swing.Box;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSeparator;
import javax.swing.AbstractButton;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.KeyStroke;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.io.IOException;
import java.io.Closeable;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import javax.swing.JFileChooser;
import java.util.Calendar;
import javax.swing.JOptionPane;
import java.awt.SystemColor;
import org.json.simple.JSONArray;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import java.awt.AWTEvent;
import java.awt.event.WindowEvent;
import java.awt.Container;
import java.awt.Window;
import javax.swing.JDialog;
import java.awt.Dialog;
import java.util.List;
import java.util.Collections;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.awt.Dimension;
import java.awt.Point;
import java.util.prefs.BackingStoreException;
import java.util.Iterator;
import org.json.simple.JSONValue;
import javax.swing.SwingUtilities;
import java.util.Collection;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Set;
import java.util.Arrays;
import javax.swing.ButtonModel;
import java.awt.Component;
import javax.swing.Icon;
import java.util.HashSet;
import java.awt.Font;
import java.util.Map;
import java.util.Comparator;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JPopupMenu;
import org.json.simple.JSONObject;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import java.util.HashMap;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.util.prefs.Preferences;
import javax.swing.JFrame;

public class CatusFrame extends JFrame
{
    static final int VERSION = 44;
    static final Preferences PREFS;
    static final Color LIGHT_RED;
    static final Color LIGHT_GREEN;
    static final Color DARK_GRAY;
    static final Color DARK_GRAY_INFO;
    static final Color DARK_GRAY_SIMS;
    static final Color DARK_GRAY_BUFF;
    static final Color DARK_RED;
    static final Color DARK_GREEN;
    static final Color DARK_BLUE;
    static final Color LIGHT_YELLOW;
    static final Color LIGHT_CYAN;
    static final Color DARK_YELLOW;
    static final String URL_SEARCH = "http://www.wowhead.com/search?q=%s";
    static final String URL_SPELL = "http://wowhead.com/spell=%d#comments";
    static final String URL_ITEM = "http://www.wowhead.com/item=%d#comments";
    static final String URL_ITEM_SET = "http://www.wowhead.com/itemset=%d#comments";
    static final UpgradeWrap UPGRADE_0;
    static final UpgradeWrap UPGRADE_1;
    static final UpgradeWrap UPGRADE_2;
    static final UpgradeWrap UPGRADE_3;
    static final UpgradeWrap UPGRADE_4;
    static final UpgradeWrap UPGRADE_5;
    static final UpgradeWrap UPGRADE_6;
    static ImageIcon redCrossImage;
    static ImageIcon greenPlusImage;
    static ImageIcon orangeArrowImage;
    static HashMap<GemT, ImageIcon> socketImageMap;
    static HashMap<GemT, ImageIcon> gemBgImageMap;
    static Border WRAP_ICON_BORDER;
    static final Handler<JToggleButton> BOLDENER;
    static final Border ICON_BORDER;
    static final Border TOOLTIP_BORDER;
    private String catus_email;
    private String catus_id;
    final ArrayList<Config2> config_list;
    Config2 selectedConfig;
    final LockableActionListener encounter_combo_al;
    static final StatT[] REFORGE_UNIVERSE;
    static final double[] WEIGHTS;
    static final File REPORTS_DIR;
    final ArrayList<RecentArmory> recentList;
    final UIPanel_GridBag backing;
    final JPanel scrolled;
    final JScrollPane scroll;
    final JPanel equipGrid;
    final JPanel altWeapGrid;
    final JPanel enchantGrid;
    final JPanel gemGrid;
    final JLabel warning_label;
    final UIPanel_GridBag setBonus_grid;
    final UIPanel_GridBag setBonus_botRow;
    final UIPanel_GridBag scaled_panel;
    final JLabel scaled_label;
    final UIPanel_GridBag challengeMode_panel;
    final PrefPane hotfix_pane;
    final JTextField hotfix_rake_field;
    final JTextField hotfix_rip_field;
    final JTextField hotfix_shrangle_field;
    final JTextField hotfix_ravage_field;
    final JTextField hotfix_sr_field;
    final JTextField hotfix_fb_field;
    final JTextField hotfix_thrash_field;
    final JTextField hotfix_swipe_field;
    final JTextField hotfix_nv_field;
    final JTextField hotfix_doc_field;
    final JTextField hotfix_tf_field;
    final JTextField hotfix_hurricane_field;
    final JTextField hotfix_wrath_field;
    final JTextField hotfix_catAuto_field;
    final JTextField[] hotfix_fields;
    final PrefPane import_pane;
    final PrefPane gearPane;
    final PrefPane setBonusPane;
    final PrefPane gemEnchantTinkerPane;
    final PrefPane statPane;
    final PrefPane regemPane;
    final PrefPane reforgePane;
    final PrefPane raceAndProf_pane;
    final PrefPane talent_pane;
    final PrefPane glyphPane;
    final PrefPane groupBuffPane;
    final PrefPane tempEffectPane;
    final PrefPane consumePane;
    final PrefPane debuffPane;
    final PrefPane prePane;
    final PrefPane rot_pane;
    final PrefPane featurePane;
    final PrefPane compareGearPane;
    final PrefPane hotw_pane;
    final PrefPane swapPane;
    final PrefPane calcPane;
    final PrefPane ilvl_pane;
    final JTextField ilvl_iter_field;
    final JButton ilvl_sim_btn;
    final JTextField ilvl_lower_field;
    final JTextField ilvl_upper_field;
    final JCheckBox ilvl_saveCSV_check;
    final PrefPane trink_pane;
    final JCheckBox trink_report_check;
    final JCheckBox trink_csv_check;
    final JCheckBox trink_ignoreErrors_check;
    final UIComboBox<NamedThing.Int> trink_cores_combo;
    Profile[] trink_pairs;
    final JTextField trink_iter_field;
    final IntSet trink_basic_selectedSet;
    final UIComboBox<NamedThing.Int> trink_basic_replace_combo;
    final JRadioButton trink_basic_radio;
    final UIComboBox<NamedThing.Int> trink_basic_upgrade_combo;
    final JButton trink_basic_edit_btn;
    final JRadioButton trink_advanced_radio;
    final JButton trink_advanced_edit_btn;
    final JButton trink_advanced_useSelected_btn;
    final UIComboBox<NamedThing.Int> trink_reforge_combo;
    final JButton trink_sim_btn;
    final PrefPane compare_pane;
    final JTextField compare_iter_field;
    final UIComboBox<NamedThing.Int> compare_objective_combo;
    final UIComboBox<NamedThing.Int> compare_settings_combo;
    final UIComboBox<NamedRunnable> compare_special_combo;
    final UIComboBox<Config2> compare_config_combo;
    final JButton compare_armory_btn;
    final LockableActionListener compare_config_combo_al;
    final PrefPane weight_pane;
    final UIComboBox<NamedThing.Int> weight_cores_combo;
    final JTextField weight_iter_field;
    final JTextField weight_delta_field;
    final JTextField weight_hitMax_field;
    final JTextField weight_expMax_field;
    final JCheckBox weight_forceHit_check;
    final JCheckBox weight_forceExp_check;
    final UIComboBox<String> weight_exp_combo;
    final StatCheck[] weight_includes;
    final StatCheck weight_includeMastery_check;
    final StatCheck weight_includeHaste_check;
    final StatCheck weight_includeCrit_check;
    final StatCheck weight_includeHit_check;
    final StatCheck weight_includeExp_check;
    final StatCheck weight_includeAgi_check;
    final StatCheck weight_includeStr_check;
    final StatCheck weight_includeInt_check;
    final StatCheck weight_includeAP_check;
    final StatCheck weight_includeSP_check;
    final StatCheck weight_includeWeapDmg_check;
    final JCheckBox weight_group_check;
    final JCheckBox weight_inert_check;
    final JLabel weight_hit_lbl;
    final JLabel weight_exp_lbl;
    final JButton weight_sim_btn;
    final JButton weight_space_btn;
    final PrefPane mirror_pane;
    final UIComboBox<NamedThing.Str> mirror_report_recent_combo;
    final JTextField mirror_report_field;
    final UIComboBox<WLFight> mirror_fight_combo;
    final UIComboBox<WLActor> mirror_actor_combo;
    final UIComboBox<WLEnemy> mirror_boss_combo;
    final JButton mirror_importReport_btn;
    final JButton mirror_viewReport_btn;
    final JButton mirror_importChar_btn;
    final JButton mirror_viewChar_btn;
    final JButton mirror_viewDPS_btn;
    final JButton mirror_viewDamageDone_btn;
    final JButton mirror_viewDamageAlloc_btn;
    final JButton mirror_viewAuras_btn;
    final JButton mirror_parse_btn;
    final JButton mirror_cache_btn;
    final JCheckBox mirror_parse_check;
    volatile boolean mirror_lock;
    final PrefPane fight_pane;
    final SmartTabPane fight_tabs;
    final UIComboBox<NamedThing.Obj<JSONObject>> fight_encounter_combo;
    final JButton fight_stdEffects_btn;
    final JButton fight_stdFights_btn;
    final UIComboBox<String> fight_patch_life_combo;
    final JTextField fight_patch_life_field;
    final UIComboBox<NamedThing.Int> fight_patch_timeVariance_combo;
    final JCheckBox fight_patch_front_check;
    final UIComboBox<NamedThing.Int> fight_patch_class_combo;
    final JCheckBox fight_patch_block_check;
    final JCheckBox fight_patch_dodge_check;
    final JCheckBox fight_patch_parry_check;
    final JTextField fight_patch_armor_field;
    final JTextField fight_patch_early_field;
    final UIComboBox<String> fight_cleave_style_combo;
    final JTextField fight_cleave_time_field;
    final JCheckBox fight_cleave_start_check;
    final UIComboBox<NamedThing.Int> fight_cleave_level_combo;
    final Spinner fight_cleave_adds_spinner;
    final Spinner fight_cleave_minSize_spinner;
    final JTextField fight_cleave_freq_field;
    final JTextField fight_cleave_health_field;
    final JTextField fight_cleave_lifetime_field;
    final JTextField fight_cleave_frontProb_field;
    final JCheckBox[] fight_mirror_checks;
    final JCheckBox fight_mirror_idle_check;
    final JCheckBox fight_mirror_self_check;
    final JCheckBox fight_mirror_health_check;
    final JCheckBox fight_mirror_external_check;
    final JButton fight_mirror_edit_btn;
    final JButton fight_mirror_filtered_btn;
    final JButton fight_mirror_check_btn;
    final JLabel fight_mirror_source_lbl;
    String fight_mirror_script;
    final JCheckBox fight_advanced_check;
    String fight_advanced_script;
    final JCheckBox fight_entry_check;
    final JTextField fight_entry_field;
    final JCheckBox fight_periodicDmgMod_check;
    final JTextField fight_periodicDmgMod_freq_field;
    final JTextField fight_periodicDmgMod_time_field;
    final JTextField fight_periodicDmgMod_mod_field;
    final JCheckBox fight_cooldownDelay_check;
    final JTextField fight_cooldownDelay_field;
    final JCheckBox fight_periodicIdle_check;
    final JTextField fight_periodicIdle_freq_field;
    final JTextField fight_periodicIdle_time_field;
    final JTextField fight_periodicIdle_limit_field;
    final JCheckBox fight_periodicCast_check;
    final JTextField fight_periodicCast_freq_field;
    final JCheckBox fight_autosave_check;
    final JPopupMenu config2_rename_menu;
    final UIComboBox<Config2> config2_combo;
    final LockableActionListener config2_combo_al;
    final JButton config2_clone_btn;
    final JButton config2_delete_btn;
    final JLabel config2_name_text;
    final UIComboBox<NamedActionListener> config2_manage_combo;
    final JLabel statHP;
    final JLabel statSta;
    final JLabel statSpi;
    final JLabel statArmor;
    final JLabel statAgi;
    final JLabel statStr;
    final JLabel statInt;
    final JLabel statAP;
    final JLabel statSP;
    final JLabel statNatureSP;
    final JLabel statMeleeCrit;
    final JLabel statSpellCrit;
    final JLabel statMeleeHaste;
    final JLabel statSpellHaste;
    final JLabel statHit;
    final JLabel statExp;
    final JLabel statMastery;
    final JLabel statDPS;
    final JLabel statSwing;
    final JLabel statPvPPower;
    final JLabel statPvPResil;
    final JLabel statDodge;
    final JLabel statCritDmg;
    final JLabel statSpeed;
    final JButton registerCatus_btn;
    final JToggleButton form_humanBtn;
    final JToggleButton form_catBtn;
    final JToggleButton form_bearBtn;
    final JToggleButton form_guardBtn;
    final JButton import_char_btn;
    final UIComboBox<RecentArmory> import_recent_combo;
    final JTabbedPane reforge_tabs;
    final JButton reforge_export_btn;
    final JButton reforge_exportAMR_btn;
    final JButton reforge_shoppingList_btn;
    final JButton reforge_stats_btn;
    final JButton reforge_bounds_btn;
    final JButton reforge_map_btn;
    final JButton regem_clearBtn;
    final JButton regem_regemBtn;
    final UIComboBox<RaceT> race_combo;
    final LockableActionListener raceAndProfs_al;
    final LockableActionListener consume_al;
    final LockableActionListener groupBuff_al;
    final LockableActionListener debuff_al;
    final LockableActionListener tempEffect_al;
    final LockableActionListener region_combo_al;
    final LockableActionListener talent_btns_al;
    final LockableActionListener glyph_combo_al;
    final JComboBox[] prof_combos;
    final JComboBox[] glyph_combos;
    final JToggleButton[] tempEffect_toggles;
    final Spinner[] tempEffect_spinners;
    final JTextField import_char_field;
    final UIComboBox<RegionT> region_combo;
    final JLabel reforge_statHit;
    final JLabel reforge_statExp;
    final JLabel reforge_statMastery;
    final JLabel reforge_statHaste;
    final JLabel reforge_statCrit;
    final JLabel reforgeRune_stateText;
    final JPanel reforgeRune_stateRow;
    final UIPanel_GridBag reforgeRune_resultsRow;
    final JButton compareGear_toCode_btn;
    final JButton compareGear_toArmory_btn;
    final UIComboBox<Config2> compareGear_config_combo;
    final LockableActionListener compareGear_config_combo_al;
    final UIComboBox<RecentArmory> compareGear_recent_combo;
    final LockableActionListener compareGear_recent_combo_al;
    final JButton reforgeRune_reforge_btn;
    final JButton reforgeRune_similar_btn;
    final JComboBox reforgeRune_results_combo;
    final JCheckBox reforgeRune_critGreater_check;
    final JCheckBox reforgeRune_enchantHands_check;
    final JCheckBox reforgeRune_enchantBack_check;
    final JCheckBox reforgeRune_changeGems_check;
    final JCheckBox reforgeRune_keepGemColors_check;
    final JCheckBox reforgeRune_breakBonus_check;
    final JCheckBox reforgeRune_leaveEmpty_check;
    final UIComboBox<Reforger111.FeralGems> reforgeRune_gem_combo;
    final JCheckBox reforgeRune_useSta_check;
    final JCheckBox reforgeRune_useHit_check;
    final JButton reforgeRune_compare_btn;
    final JTextField reforgeRune_hit_field;
    final JTextField reforgeRune_exp_field;
    final JTextField reforgeRune_range_field;
    final JTextField reforgeRune_overflow_field;
    final JTextField reforgeRune_hasteGap_field;
    final JTextField reforgeRune_critGap_field;
    final UIComboBox<Reforger111.SearchMode> reforgeRune_hit_combo;
    final UIComboBox<Reforger111.SearchMode> reforgeRune_exp_combo;
    final JButton reforgeMax_reforge_btn;
    final JTextField reforgeMax_hit_field;
    final JTextField reforgeMax_exp_field;
    final JTextField reforgeMax_range_field;
    final StatT[] reforgeMax_stats;
    final JTextField[] reforgeMax_fields;
    final UIPanel_GridBag reforgeMax_bear_row;
    final JCheckBox reforgeMax_bear_check;
    final JTextField reforgeMax_bearMax_field;
    final JTextField reforgeMax_depth_field;
    final JCheckBox reforgeMax_changeGems_check;
    final JCheckBox reforgeMax_leaveEmpty_check;
    final JCheckBox reforgeMax_breakBonus_check;
    final JCheckBox reforgeMax_enchantHands_check;
    final JCheckBox reforgeMax_enchantFeet_check;
    final JCheckBox reforgeMax_enchantBack_check;
    final JComboBox reforgeMax_results_combo;
    final UIPanel_GridBag reforgeMax_results_panel;
    final JCheckBox calc_rip4_check;
    final JCheckBox calc_assumeSR_check;
    final JCheckBox calc_tempEffects_check;
    final JCheckBox calc_useDebuffs_check;
    final JButton calc_fon_btn;
    final Spinner calc_thrash_spinner;
    final Spinner calc_rip_spinner;
    final PrefPane combatLog_pane;
    final JButton combatLog_btn;
    final JCheckBox combatLog_printEnergy_check;
    final JCheckBox combatLog_printDebug_check;
    final JCheckBox combatLog_printMore_check;
    final JButton equip_batch_btn;
    final UIComboBox<NamedRunnable> equip_itemLevel_combo;
    final JButton equip_edit_btn;
    final JLabel equip_ilvl_lbl;
    final JButton equip_exportSimc_btn;
    final JButton equip_screenshot_btn;
    final JButton simc_import_btn;
    final UIComboBox<Config2> equip_config_combo;
    final LockableActionListener equip_config_combo_al;
    final UIComboBox<NamedRunnable> equip_clear_combo;
    final JButton equip_autoFill_btn;
    final JButton talents_webBtn;
    final JButton talents_clearBtn;
    final ReforgeToggle[] reforge_toggles;
    final JButton reforgeOld_btn;
    final JButton reforge_clearReforges_btn;
    final JButton reforge_clearGems_btn;
    final ReforgeConstraint[] reforgeOld_constraints;
    static final Border SPINNER_ICON_BORDER;
    final JButton groupBuff_noneBtn;
    final JButton groupBuff_selfBtn;
    final JButton groupBuff_allBtn;
    final JToggleButton[] debuff_toggles;
    final JToggleButton[] buff_toggles;
    final JCheckBox[] feature_checks;
    final JToggleButton[] feature_toggles;
    final JToggleButton[] feature_options;
    final JLabel customStats_lbl;
    final JButton customStats_clear_btn;
    final JToggleButton buff_crit_toggle;
    final JToggleButton buff_stats_toggle;
    final JToggleButton buff_stam_toggle;
    final JToggleButton buff_mastery_toggle;
    final JToggleButton buff_ap_toggle;
    final JToggleButton buff_sp_toggle;
    final JToggleButton buff_meleeHaste_toggle;
    final JToggleButton buff_spellHaste_toggle;
    final JToggleButton debuff_melee_toggle;
    final JToggleButton debuff_spell_toggle;
    final JToggleButton debuff_bleed_toggle;
    final UIComboBox<FeralSim.WeakenedArmor> debuff_armor_combo;
    final JCheckBox feature_smartFoN_check;
    final JCheckBox feature_disableRefund_check;
    final JCheckBox feature_disableOOC_check;
    final JCheckBox feature_disableLOTP_check;
    final JCheckBox feature_properRipExtend_check;
    final JCheckBox feature_allowTFDuringBerserk_check;
    final JCheckBox feature_disable0ComboPS_check;
    final JCheckBox feature_disableArmorSpec_check;
    final JCheckBox feature_disableRipExtend_check;
    final JCheckBox feature_disableBerserk_check;
    final JCheckBox feature_disableMastery_check;
    final JCheckBox feature_disableCrit_check;
    final JCheckBox feature_disablePrimalFury_check;
    final JCheckBox feature_disableRacials_check;
    final JCheckBox feature_disableGlancing_check;
    final JCheckBox feature_disableBitW_check;
    final JCheckBox feature_wodRacials_check;
    final JCheckBox feature_enableBerserkPerk_check;
    final JCheckBox feature_localRipExtend_check;
    final JToggleButton feature_setBonus_toggle;
    final JToggleButton feature_noob_toggle;
    final JToggleButton feature_averageRanges_toggle;
    final JComboBox pre_potion0_combo;
    final UIComboBox<FeralSim.Opener> pre_opener_combo;
    final UIComboBox<FeralSim.Finisher0> pre_finisher0_combo;
    final JToggleButton[] pre_toggles;
    final JToggleButton pre_castSR_toggle;
    final JToggleButton pre_castHT_toggle;
    final UIComboBox<NamedThing.Int> pre_procReset_combo;
    final JCheckBox pre_energy0_check;
    final JTextField pre_energy0_field;
    final JComboBox regem_white_combo;
    final JComboBox regem_sha_combo;
    final JComboBox regem_alwaysColor_combo;
    final JComboBox[] regem_profCombos;
    final JComboBox[] regem_primaryColor_combos;
    final UIPanel_GridBag regem_profRow;
    final JCheckBox regem_always_check;
    final JCheckBox regem_unless_check;
    final JTextField rot_delay_field;
    final JTextField rot_react_field;
    final JTextField rot_pool_field;
    final UIComboBox<FeralSim.Generator> rot_generator_combo;
    final UIComboBox<FeralSim.Symbiosis> rot_symbiosis_combo;
    final UIComboBox<FeralSim.ThrashStyle> rot_thrashStyle_combo;
    final UIComboBox<Flask> consume_flask_combo;
    final JComboBox consume_potion_combo;
    final JComboBox consume_food_combo;
    final JToggleButton tempEffect_berserking_toggle;
    final JToggleButton tempEffect_lifeblood_toggle;
    final UIComboBox<FeralSim.Heroism> tempEffect_hero_combo;
    final JTextField tempEffect_hero_field;
    final JLabel tempEffect_hero_icon;
    final Spinner stormlashTotemSpinner;
    final Spinner shatteringSpinner;
    final Spinner skullBannerSpinner;
    final Spinner unholyFrenzySpinner;
    final Spinner tricksSpinner;
    final JToggleButton hotw_swap_toggle;
    final JToggleButton hotw_wrath_toggle;
    final JToggleButton hotw_hurricane_toggle;
    final JToggleButton hotw_beforeBerserk_toggle;
    final JToggleButton hotw_bitw_toggle;
    final ButtonGroup[] talentGroups;
    final JToggleButton[][] talent_btns;
    final PrefPane sim_pane;
    final JButton sim_btn;
    final JTextField sim_iter_field;
    final JTextField sim_err_field;
    final JTextField sim_timeDist_field;
    final JCheckBox sim_csv_check;
    static final Comparator<StatValued> cmp_basicWeights;
    final HttpCache hc;
    final API api;
    final Profile mainProfile;
    final Profile swapProfile;
    final FeralSim sim;
    final FeralSpec spec;
    final FeralConfig cfg;
    final StatMap customStats;
    boolean setBonusIgnoreChange;
    final Map<EquipT, Map<Item, ItemWrap>> gearMap;
    final Map<GemT, Gem[]> gemsMap;
    final ItemSet[] itemSets;
    final Map<SetBonus, SetBonusHelper> bonusMap;
    final Gem[] jcGems;
    static final Font monoFont;
    static final Font normalFont;
    static final Font boldFont;
    static final Font italicFont;
    static final Font tinyFont;
    static final Font tinyBoldFont;
    final PrefPane[] prefPanes;
    boolean asiaMode;
    boolean challengeMode;
    boolean hitExpAsPerc;
    static final int PAD = 4;
    private static final WLEnemy FAKE;
    static final String ENCOUNTER_URL = "https://dl.dropboxusercontent.com/u/2989349/catus_data/Encounters/44/";
    private final LockableActionListener reforge_results_combo_al;
    public static String JOINT_NAME_REALM_SEP;
    private final LockableActionListener import_recent_combo_al;
    boolean keepReforgeVisible;
    boolean dontRebuild;
    final HashSet<IconStore> iconQueue;
    static final StatT[] TOOLTIP_STATS;
    final HashMap<Profile.Slot, SlotStore> slotStoreMap;
    static final String ITEM_ADD = "Add item...";
    static final String ITEM_RESTORE = "Restore from Armory";
    static final String ITEM_EDIT = "Edit...";
    final ArrayList<EnchantT> _enchantBuf;
    final ArrayList<TinkerT> _tinkerBuf;
    static final String NO_POTION = "No Potion";
    static final String USE_CONSUMABLE = "Same as Consumable";
    private final byte[] talentBytes;
    
    static UIPanel wrapIcon_stripCheck(final JCheckBox check) {
        final Icon icon = check.getIcon();
        check.setIcon(null);
        return wrapIcon(icon, check);
    }
    
    static UIPanel_GridBag wrapIcon(final Icon icon, final Component right) {
        final UIPanel_GridBag row = new UIPanel_GridBag();
        final JLabel left = new JLabel(icon);
        left.setBorder(CatusFrame.WRAP_ICON_BORDER);
        row.add(left);
        row.spacer(right);
        return row;
    }
    
    JCheckBox makeIconCheck(final String name, final InterfaceIcon icon) {
        final JCheckBox temp = UI.makeCheck(name);
        temp.setIcon(this.api.getIconImage(icon.slug, API.IconSize.SMALL));
        return temp;
    }
    
    JToggleButton makeIconToggle(final String name, final InterfaceIcon icon) {
        final JToggleButton temp = new JToggleButton(name, this.api.getIconImage(icon.slug, API.IconSize.SMALL));
        temp.setModel(new BetterToggleModel());
        temp.setHorizontalAlignment(2);
        temp.setFocusable(false);
        UI.onChange(temp, CatusFrame.BOLDENER);
        return temp;
    }
    
    static void parseItemIds(final IntSet set, final String code) {
        if (code != null) {
            for (String line : code.split("\n")) {
                final int pos = line.indexOf(35);
                if (pos >= 0) {
                    line = line.substring(0, pos);
                }
                line = line.trim();
                if (!line.isEmpty()) {
                    for (final String x : line.split("\\s+")) {
                        try {
                            set.add(Integer.parseInt(x.trim()));
                        }
                        catch (RuntimeException ex) {}
                    }
                }
            }
        }
    }
    
    static void parseStatWeights(final String code, final double[] weights) {
        Arrays.fill(weights, 0.0);
        for (String line : code.split("\n")) {
            int pos = line.indexOf(35);
            if (pos >= 0) {
                line = line.substring(0, pos);
            }
            line = line.trim();
            if (!line.isEmpty()) {
                pos = line.indexOf(58);
                if (pos == -1) {
                    throw new RuntimeException("Missing field separator (:)");
                }
                final String key = line.substring(0, pos).trim();
                final String val = line.substring(pos + 1).trim();
                final StatT stat = StatT.findBestMatch(StatT.STATS, key);
                if (stat == null) {
                    throw new RuntimeException("Unknown stat: " + key);
                }
                double weight;
                try {
                    weight = Double.parseDouble(val);
                }
                catch (RuntimeException err) {
                    throw new RuntimeException(String.format("Invalid %s weight (%s)", stat, val));
                }
                weights[stat.index] = weight;
            }
        }
    }
    
    public static void boot() {
        final ArrayList<BootFrame.Phase> phases = new ArrayList<BootFrame.Phase>();
        final HttpCache hc = new HttpCache();
        phases.add(BootFrame.makePhase_taint(hc, CatusFrame.PREFS, "Catus", 44, new BootFrame.Phase("New Version") {
            @Override
            public void run(final BootFrame bf) {
                bf.updateStatus("Clearing Old Data");
                hc.cleanCache_matchingURL("https://dl.dropboxusercontent.com/u/2989349/catus_data/");
                bf.updateStatus("Clearing Heirlooms");
                final int[] ids = { 102248, 104399, 104400, 104401, 104402, 104403, 104404, 104405, 104406, 104407, 104408, 104409, 105670, 105671, 105672, 105673, 105674, 105675, 105676, 105677, 105678, 105679, 105680, 105683, 105684, 105685, 105686, 105687, 105688, 105689, 105690, 105691, 105692, 105693 };
                final HashSet<String> files = new HashSet<String>();
                for (final int id : ids) {
                    files.add("Item_" + id + ".json");
                }
                hc.cleanCache_matchingURL_files("http://raffy.antistupid.com", files);
            }
        }));
        final API api = new API(hc);
        phases.add(new BootFrame.Phase("Loading External Data") {
            @Override
            public void run(final BootFrame bf) {
                bf.updateStatus("Downloading Stat Weights");
                final String dat = hc.getStr_silent("https://dl.dropboxusercontent.com/u/2989349/catus_data/CatusWeights.txt", "", 604800000);
                if (dat != null) {
                    try {
                        CatusFrame.parseStatWeights(dat, CatusFrame.WEIGHTS);
                    }
                    catch (RuntimeException err) {
                        System.err.println("StatWeightErr: " + err);
                    }
                }
            }
        });
        final Map<EquipT, Set<Item>> gearMap = new HashMap<EquipT, Set<Item>>();
        for (final EquipT x : EquipT.ALL) {
            gearMap.put(x, new HashSet<Item>());
        }
        phases.add(new BootFrame.Phase("Loading Equipment") {
            @Override
            public void run(final BootFrame bf) {
                bf.updateStatus("Fetching Gear List");
                final IntSet allItems = new IntSet(1000);
                CatusFrame.parseItemIds(allItems, hc.getStr_silent("https://dl.dropboxusercontent.com/u/2989349/catus_data/CatusGear.txt", "", 86400000));
                final IntSet permaSet = allItems.copy();
                final Preferences node = CatusFrame.PREFS.node("Gear");
                bf.updateStatus("Downloading Latest Gear (" + allItems.count + ")");
                int index = 0;
                while (true) {
                    final int itemId = node.getInt(Integer.toString(index), 0);
                    if (itemId == 0) {
                        break;
                    }
                    allItems.add(itemId);
                    ++index;
                }
                bf.updateProgress(0, allItems.count);
                for (int i = 0; i < allItems.count; ++i) {
                    final int itemId = allItems.member[i];
                    final boolean perma = permaSet.contains(itemId);
                    try {
                        for (final Item x : api.loadItem_bothFactions(itemId)) {
                            if (perma) {
                                gearMap.get(x.equipType).add(x);
                            }
                        }
                    }
                    catch (RuntimeException err) {
                        System.err.println("Unable to load item: " + err.getMessage());
                    }
                    bf.updateProgress(i + 1, allItems.count);
                }
            }
        });
        final Map<GemT, Set<Gem>> gemsMap = new HashMap<GemT, Set<Gem>>();
        for (final GemT x2 : GemT.ALL) {
            gemsMap.put(x2, new HashSet<Gem>());
        }
        phases.add(new BootFrame.Phase("Loading Accessories") {
            @Override
            public void run(final BootFrame bf) {
                bf.updateStatus("Fetching Gem List");
                final IntSet items = new IntSet(1000);
                CatusFrame.parseItemIds(items, hc.getStr_silent("https://dl.dropboxusercontent.com/u/2989349/catus_data/CatusGems.txt", "", 86400000));
                bf.updateStatus("Downloading Latest Gems (" + items.count + ")");
                bf.updateProgress(0, items.count);
                for (int i = 0; i < items.count; ++i) {
                    try {
                        final Gem gem = (Gem)api.loadItem(items.member[i]);
                        gemsMap.get(gem.getType()).add(gem);
                    }
                    catch (RuntimeException err) {
                        System.err.println("Unable to load gem: " + err.getMessage());
                    }
                    bf.updateProgress(i + 1, items.count);
                }
            }
        });
        phases.add(new BootFrame.Phase("Loading Assets") {
            @Override
            public void run(final BootFrame bf) {
                bf.updateStatus("Loading Local Icons");
                final Toolkit tk = Toolkit.getDefaultToolkit();
                CatusFrame.redCrossImage = new ImageIcon(tk.createImage(Main.class.getResource("/assets/RedCross.png")));
                CatusFrame.greenPlusImage = new ImageIcon(tk.createImage(Main.class.getResource("/assets/GreenPlus.png")));
                CatusFrame.orangeArrowImage = new ImageIcon(tk.createImage(Main.class.getResource("/assets/OrangeArrow.png")));
                final int s = API.IconSize.SMALL.size;
                for (final GemT x : GemT.SOCKETS) {
                    Image img = tk.createImage(Main.class.getResource("/assets/sockets/" + x.type + ".gif"));
                    CatusFrame.socketImageMap.put(x, new ImageIcon(img.getScaledInstance(s, s, 4)));
                    img = tk.createImage(Main.class.getResource("/assets/gembgs/" + x.type + ".png"));
                    CatusFrame.gemBgImageMap.put(x, new ImageIcon(img));
                }
                bf.updateStatus("Downloading Interface Icons");
                for (final InterfaceIcon x2 : InterfaceIcon.values()) {
                    api.getIconImage(x2.slug, API.IconSize.SMALL);
                }
                bf.updateStatus("Downloading Talent Icons");
                for (final Talent[] array : FeralSpec.TALENTS) {
                    final Talent[] row = array;
                    for (final Talent x3 : array) {
                        api.getIconImage(x3.icon, API.IconSize.SMALL);
                    }
                }
                bf.updateStatus("Checking Directories");
                CatusFrame.REPORTS_DIR.mkdir();
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BootFrame().execute(phases);
                new CatusFrame(hc, api, gearMap, gemsMap).initAndShow();
            }
        });
    }
    
    static void tryURL(final Object thing) {
        final String url = getURL(thing);
        if (url != null) {
            Utils.openURL(url);
        }
    }
    
    static String getURL(final Object thing) {
        if (thing instanceof Item) {
            return String.format("http://www.wowhead.com/item=%d#comments", Math.abs(((Item)thing).itemId));
        }
        if (thing instanceof ItemSet) {
            final ItemSet set = (ItemSet)thing;
            if (set.id >= 10000000) {
                return null;
            }
            return String.format("http://www.wowhead.com/itemset=%d#comments", set.id);
        }
        else if (thing instanceof EnchantT) {
            final EnchantT enchant = (EnchantT)thing;
            if (enchant.itemId != 0) {
                return String.format("http://www.wowhead.com/item=%d#comments", enchant.itemId);
            }
            if (enchant.spellId != 0) {
                return String.format("http://wowhead.com/spell=%d#comments", enchant.spellId);
            }
            return String.format("http://www.wowhead.com/search?q=%s", Utils.urlEncode(enchant.name));
        }
        else {
            if (thing instanceof TinkerT) {
                return String.format("http://wowhead.com/spell=%d#comments", ((TinkerT)thing).profSpellId);
            }
            if (thing instanceof Integer) {
                return String.format("http://wowhead.com/spell=%d#comments", thing);
            }
            return String.format("http://www.wowhead.com/search?q=%s", Utils.urlEncode(thing.toString()));
        }
    }
    
    static void markProfileIcons(final API api, final Set<IconStore> queue, final Profile p) {
        for (final Profile.Slot s : p.SLOTS) {
            if (s.item != null) {
                if (api.markImageIcon(s.item.icon, API.IconSize.LARGE)) {
                    queue.add(s.item.icon);
                }
                for (int i = 0, e = s.getSocketCount(); i < e; ++i) {
                    final Gem gem = s.getGemAt(i);
                    if (gem != null && api.markImageIcon(gem.icon, API.IconSize.SMALL)) {
                        queue.add(gem.icon);
                    }
                }
            }
        }
    }
    
    private void applyRegion(final String name) {
        this.region_combo_al.lock();
        this.region_combo.importPick(name, RegionT.US);
        this.region_combo_al.unlock();
        this.asiaMode = this.region_combo.getPick().asia;
    }
    
    private Set<Item> loadItems(final IntSet itemIds) {
        final HashSet<Item> loaded = new HashSet<Item>(itemIds.count);
        final HashSet<Integer> unloaded = new HashSet<Integer>();
        for (int i = 0; i < itemIds.count; ++i) {
            final int itemId = itemIds.member[i];
            final Item item = this.api.getItem(itemId);
            if (item == null) {
                unloaded.add(itemId);
            }
            else {
                loaded.add(item);
                if (item.otherFaction_itemId != 0) {
                    final Item otherItem = this.api.getItem(item.otherFaction_itemId);
                    if (otherItem == null) {
                        unloaded.add(item.otherFaction_itemId);
                    }
                    else {
                        loaded.add(otherItem);
                    }
                }
            }
        }
        if (!unloaded.isEmpty()) {
            final DialogProg dp = new DialogProg(this, "Loading More Gear");
            dp.forEach(unloaded, new DialogProg.Each<Integer>() {
                @Override
                public void each(final int index, final Integer value) {
                    try {
                        loaded.add(CatusFrame.this.api.loadItem(value));
                    }
                    catch (RuntimeException ex) {}
                }
            });
        }
        return loaded;
    }
    
    private void updateRegisteredState() {
        if (this.catus_id == null) {
            this.registerCatus_btn.setText("Register...");
            this.registerCatus_btn.setToolTipText(Utils.tt_simple("Catus is currently unregistered.", "Please register your email address."));
        }
        else {
            this.registerCatus_btn.setText("Registered: " + this.catus_email);
            this.registerCatus_btn.setToolTipText(Utils.tt_simple("Catus is currently registered to " + this.catus_email, "Catus-Id: <tt>" + this.catus_id + "</tt>", "Click to unregister.", "Hold <tt>[alt]</tt> to copy your Catus-Id."));
        }
    }
    
    private void loadPrefs() {
        for (final Map<Item, ItemWrap> x : this.gearMap.values()) {
            final Iterator<ItemWrap> iter = x.values().iterator();
            while (iter.hasNext()) {
                if (iter.next().custom) {
                    iter.remove();
                }
            }
        }
        final IntSet itemIds = new IntSet();
        final Preferences node = CatusFrame.PREFS.node("Gear");
        int index = 0;
        while (true) {
            final int itemId = node.getInt(Integer.toString(index), 0);
            if (itemId == 0) {
                break;
            }
            itemIds.add(itemId);
            ++index;
        }
        for (final Item x2 : this.loadItems(itemIds)) {
            final Map<Item, ItemWrap> map = this.gearMap.get(x2.equipType);
            final ItemWrap prev = map.get(x2);
            if (prev == null) {
                map.put(x2, new ItemWrap(x2, true));
            }
        }
        Preferences node2 = CatusFrame.PREFS.node("Recent");
        this.recentList.clear();
        HashSet<String> dups = new HashSet<String>();
        index = 0;
        while (true) {
            final String val = node2.get(Integer.toString(index), null);
            if (val == null) {
                break;
            }
            if (!dups.add(val)) {
                break;
            }
            final String[] comps = val.split("/");
            try {
                this.recentList.add(new RecentArmory(comps[0], comps[1], RegionT.valueOf(comps[2]), 0L));
            }
            catch (RuntimeException err) {
                break;
            }
            ++index;
        }
        this._updateRecentCombo();
        node2 = CatusFrame.PREFS.node("Catus");
        this.catus_id = node2.get("Id", null);
        this.catus_email = node2.get("Email", "");
        final String hash = node2.get("Hash", null);
        if (this.catus_id == null || this.catus_email == null || this.catus_id.isEmpty()) {
            this.catus_id = null;
        }
        else if (!Utils.sha1(this.catus_email + this.catus_id).equalsIgnoreCase(hash)) {
            System.err.println("Registered email was modified!");
            this.catus_id = null;
        }
        this.updateRegisteredState();
        node2 = CatusFrame.PREFS.node("Stats");
        final String form = node2.get("Form", "Cat");
        if ("Human".equalsIgnoreCase(form)) {
            this.form_humanBtn.setSelected(true);
        }
        else if ("Bear".equalsIgnoreCase(form)) {
            this.form_bearBtn.setSelected(true);
        }
        else if ("Guardian".equalsIgnoreCase(form)) {
            this.form_guardBtn.setSelected(true);
        }
        else {
            this.form_catBtn.setSelected(true);
        }
        this.hitExpAsPerc = node2.getBoolean("HitExpPerc", false);
        this.loadPrefs_reforgers();
        node2 = CatusFrame.PREFS.node("Regem");
        this.regem_always_check.setSelected(node2.getBoolean("Always", true));
        UI.setComboFromString(this.regem_alwaysColor_combo, node2.get("AlwaysGem", null));
        this.regem_unless_check.setSelected(node2.getBoolean("Unless", true));
        UI.setComboFromString(this.regem_white_combo, node2.get("WhiteGem", null));
        UI.setComboFromString(this.regem_sha_combo, node2.get("ShaGem", null));
        for (int i = 0; i < GemT.PRIMARY_SOCKETS.length; ++i) {
            UI.setComboFromString(this.regem_primaryColor_combos[i], node2.get(GemT.PRIMARY_SOCKETS[i].name + "Gem", null));
        }
        node2 = CatusFrame.PREFS.node("CombatLog");
        this.combatLog_printEnergy_check.setSelected(node2.getBoolean("PrintEnergy", false));
        this.combatLog_printDebug_check.setSelected(node2.getBoolean("PrintDebug", false));
        this.combatLog_printMore_check.setSelected(node2.getBoolean("PrintMore", false));
        node2 = CatusFrame.PREFS.node("Sim");
        this.sim_iter_field.setText(node2.get("Iter", "10000"));
        this.sim_err_field.setText(node2.get("Err", ""));
        this.sim_timeDist_field.setText(node2.get("Step", ""));
        node2 = CatusFrame.PREFS.node("Compare");
        this.compare_objective_combo.importPick(node2.get("Objective", null), 0);
        this.compare_iter_field.setText(node2.get("Iter", "10000"));
        this.compare_settings_combo.importPick(node2.get("Settings", null), 0);
        node2 = CatusFrame.PREFS.node("Calcs");
        this.calc_rip4_check.setSelected(node2.getBoolean("Rip4", false));
        this.calc_assumeSR_check.setSelected(node2.getBoolean("AssumeSR", true));
        this.calc_tempEffects_check.setSelected(node2.getBoolean("TempEffects", true));
        this.calc_useDebuffs_check.setSelected(node2.getBoolean("Debuffs", true));
        this.calc_thrash_spinner.setValue(node2.getInt("ThrashTicks", 1));
        this.calc_rip_spinner.setValue(node2.getInt("RipTicks", 2));
        node2 = CatusFrame.PREFS.node("Weights");
        this.weight_cores_combo.importPick(node2.get("Cores", null), 0);
        this.weight_iter_field.setText(node2.get("Iter", "10000"));
        this.weight_delta_field.setText(node2.get("Delta", "900"));
        this.weight_hitMax_field.setText(node2.get("HitMax", "7.5%"));
        this.weight_expMax_field.setText(node2.get("ExpMax", "7.5%"));
        this.weight_group_check.setSelected(node2.getBoolean("Group", false));
        this.weight_inert_check.setSelected(node2.getBoolean("Inert", true));
        this.weight_forceHit_check.setSelected(node2.getBoolean("ForceHit", true));
        this.weight_forceExp_check.setSelected(node2.getBoolean("ForceExp", true));
        this.weight_exp_combo.importPick(node2.get("DirExp", null), 0);
        for (final StatCheck x3 : this.weight_includes) {
            x3.setSelected(node2.getBoolean(x3.key(), true));
        }
        node2 = CatusFrame.PREFS.node("Trinkets");
        this.trink_iter_field.setText(node2.get("Iter", "10000"));
        this.trink_cores_combo.importPick(node2.get("Cores", null), 0);
        if (node2.getBoolean("Basic", true)) {
            this.trink_basic_radio.setSelected(true);
        }
        else {
            this.trink_advanced_radio.setSelected(true);
        }
        this.trink_reforge_combo.importPick(node2.get("Reforge", null), 0);
        this.trink_ignoreErrors_check.setSelected(node2.getBoolean("ReforgeIgnore", false));
        this.trink_basic_upgrade_combo.importPick(node2.get("Upgrade", null), -1);
        this.trink_basic_replace_combo.importPick(node2.get("Replace", null), 0);
        this.trink_basic_selectedSet.clear();
        for (final String x4 : PrefHelp.getLargeString(node2, "BasicCode", "").split("\\s+")) {
            try {
                this.trink_basic_selectedSet.add(Integer.parseInt(x4));
            }
            catch (NumberFormatException ex) {}
        }
        this.trink_report_check.setSelected(node2.getBoolean("Report", false));
        this.trink_csv_check.setSelected(node2.getBoolean("CSV", false));
        this.trink_validatePairs(PrefHelp.getLargeString(node2, "AdvancedCode", ""), null);
        node2 = CatusFrame.PREFS.node("Scaling");
        this.ilvl_iter_field.setText(node2.get("Iter", "10000"));
        this.ilvl_lower_field.setText(node2.get("Lower", "450"));
        this.ilvl_upper_field.setText(node2.get("Upper", "680"));
        this.ilvl_saveCSV_check.setSelected(node2.getBoolean("CSV", false));
        node2 = CatusFrame.PREFS.node("Mirror");
        this.mirror_report_field.setText(node2.get("Report", ""));
        this.mirror_parse_check.setSelected(node2.getBoolean("Autoload", true));
        this.resetMirrorCombos();
        this.mirror_lock = true;
        this.mirror_report_recent_combo.removeAllItems();
        dups = new HashSet<String>();
        index = 0;
        while (true) {
            final String val = node2.get(Integer.toString(index), null);
            if (val == null) {
                break;
            }
            final int pos = val.indexOf(47);
            if (pos != -1) {
                final String reportId = val.substring(0, pos);
                final String name = val.substring(pos + 1);
                try {
                    this.mirror_report_recent_combo.addItem(new NamedThing.Str(name, reportId));
                }
                catch (RuntimeException err2) {
                    break;
                }
            }
            ++index;
        }
        UI.setComboText(this.mirror_report_recent_combo);
        this.mirror_lock = false;
        node2 = CatusFrame.PREFS.node("Configs");
        this.config_list.clear();
        int index2 = 0;
        while (true) {
            final String json = PrefHelp.getLargeString(node2, Integer.toString(index2), null);
            if (json == null) {
                break;
            }
            final Object obj = JSONValue.parse(json);
            if (obj instanceof JSONObject) {
                try {
                    this.config_list.add(this.config_create((JSONObject)obj));
                }
                catch (RuntimeException ex2) {}
            }
            ++index2;
        }
        if (this.config_list.isEmpty()) {
            final Config2 config = new Config2();
            config.name = "Untitled Configuration";
            config.root = new JSONObject();
            this.config_list.add(config);
        }
        index2 = node2.getInt("Selected", 0);
        if (index2 >= this.config_list.size()) {
            index2 = 0;
        }
        this.config_select(this.config_list.get(index2));
        this.config_rebuildList();
        node2 = CatusFrame.PREFS.node("PrefPanes");
        for (final PrefPane x5 : this.prefPanes) {
            x5.setExpanded(node2.getBoolean(x5.name, x5.defaultExpanded));
        }
        node2 = CatusFrame.PREFS.node("WindowState");
        final String val2 = node2.get("Bounds", null);
        Label_1994: {
            if (val2 != null) {
                final String[] xywh = val2.split("\\s+");
                if (xywh.length == 4) {
                    try {
                        final int x6 = Integer.parseInt(xywh[0]);
                        final int y = Integer.parseInt(xywh[1]);
                        final int w = Integer.parseInt(xywh[2]);
                        final int h = Integer.parseInt(xywh[3]);
                        this.setBounds(x6, y, w, h);
                        break Label_1994;
                    }
                    catch (NumberFormatException ex3) {}
                }
            }
            this.setLocationRelativeTo(null);
            try {
                this.reforge_tabs.setSelectedIndex(node2.getInt("ReforgeTab", 0));
            }
            catch (IndexOutOfBoundsException err3) {
                this.reforge_tabs.setSelectedIndex(0);
            }
        }
        this.scroll.validate();
        final int scrollX = node2.getInt("ScrollX", 0);
        final int scrollY = node2.getInt("ScrollY", 0);
        if (scrollX > 0 || scrollY > 0) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    CatusFrame.this.scroll.getHorizontalScrollBar().setValue(scrollX);
                    CatusFrame.this.scroll.getVerticalScrollBar().setValue(scrollY);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            CatusFrame.this.scroll.getHorizontalScrollBar().setValue(scrollX);
                            CatusFrame.this.scroll.getVerticalScrollBar().setValue(scrollY);
                        }
                    });
                }
            });
        }
    }
    
    private void loadPrefs_reforgers() {
        Preferences node = CatusFrame.PREFS.node("ReforgeSlots");
        for (final ReforgeToggle x : this.reforge_toggles) {
            x.setSelected(node.getBoolean(x.slot.name, true));
        }
        node = CatusFrame.PREFS.node("ReforgeRune");
        this.reforgeRune_hit_field.setText(node.get("HitValue", "7.5%"));
        this.reforgeRune_exp_field.setText(node.get("ExpValue", "7.5%"));
        this.reforgeRune_hit_combo.importPick(node.get("HitMode", null), Reforger111.SearchMode.AT_LEAST);
        this.reforgeRune_exp_combo.importPick(node.get("ExpMode", null), Reforger111.SearchMode.AT_LEAST);
        this.reforgeRune_range_field.setText(node.get("SearchRange", "150"));
        this.reforgeRune_overflow_field.setText(node.get("MasteryOverflow", "100"));
        this.reforgeRune_critGap_field.setText(node.get("CritGap", "0"));
        this.reforgeRune_hasteGap_field.setText(node.get("HasteGap", "0"));
        this.reforgeRune_critGreater_check.setSelected(node.getBoolean("CritGreater", false));
        this.reforgeRune_enchantBack_check.setSelected(node.getBoolean("EnchantBack", true));
        this.reforgeRune_enchantHands_check.setSelected(node.getBoolean("EnchantHands", true));
        this.reforgeRune_changeGems_check.setSelected(node.getBoolean("ChangeGems", true));
        this.reforgeRune_keepGemColors_check.setSelected(node.getBoolean("KeepGemColors", false));
        this.reforgeRune_breakBonus_check.setSelected(node.getBoolean("CanBreakBonuses", true));
        this.reforgeRune_leaveEmpty_check.setSelected(node.getBoolean("RespectNullGems", false));
        this.reforgeRune_gem_combo.importPick(node.get("GemColor", null), Reforger111.FeralGems.RED);
        this.reforgeRune_useSta_check.setSelected(node.getBoolean("AllowStamina", false));
        this.reforgeRune_useHit_check.setSelected(node.getBoolean("AllowPureHit", false));
        node = CatusFrame.PREFS.node("ReforgeMax");
        this.reforgeMax_hit_field.setText(node.get("HitValue", "7.5%"));
        this.reforgeMax_exp_field.setText(node.get("ExpValue", "7.5%"));
        this.reforgeMax_range_field.setText(node.get("SearchRange", "100"));
        this.reforgeMax_depth_field.setText(node.get("Depth", ""));
        for (int i = 0; i < this.reforgeMax_stats.length; ++i) {
            final StatT stat = this.reforgeMax_stats[i];
            this.reforgeMax_fields[i].setText(node.get(stat.abbr + "Weight", String.format("%.2f", CatusFrame.WEIGHTS[stat.index])));
        }
        this.reforgeMax_enchantBack_check.setSelected(node.getBoolean("EnchantBack", true));
        this.reforgeMax_enchantHands_check.setSelected(node.getBoolean("EnchantHands", true));
        this.reforgeMax_enchantFeet_check.setSelected(node.getBoolean("EnchantFeet", false));
        this.reforgeMax_changeGems_check.setSelected(node.getBoolean("ChangeGems", true));
        this.reforgeMax_breakBonus_check.setSelected(node.getBoolean("CanBreakBonuses", true));
        this.reforgeMax_leaveEmpty_check.setSelected(node.getBoolean("RespectNullGems", false));
        this.reforgeMax_bear_check.setSelected(node.getBoolean("BearCap", false));
        this.reforgeMax_bearMax_field.setText(node.get("BearCrit", "79.05%"));
        node = CatusFrame.PREFS.node("ReforgeOld");
        for (final ReforgeConstraint x2 : this.reforgeOld_constraints) {
            final String prefix = x2.stat.abbr;
            x2.min_field.setText(node.get(prefix + "Min", ""));
            x2.max_field.setText(node.get(prefix + "Max", ""));
            x2.weight_field.setText(node.get(prefix + "Weight", ""));
            x2.prevent_check.setSelected(node.getBoolean(prefix + "Prevent", false));
        }
    }
    
    private void savePrefs_catusStuff(final boolean commit) {
        final Preferences node = CatusFrame.PREFS.node("Catus");
        node.put("Email", (this.catus_email == null) ? "" : this.catus_email);
        if (this.catus_id != null) {
            node.put("Id", this.catus_id);
            node.put("Hash", Utils.sha1(this.catus_email + this.catus_id));
        }
        else {
            node.put("Id", "");
            node.put("Hash", "");
        }
        if (commit) {
            try {
                CatusFrame.PREFS.flush();
            }
            catch (BackingStoreException ex) {}
        }
    }
    
    private void savePrefs() {
        try {
            Preferences node = CatusFrame.PREFS.node("WindowState");
            final Point xy = this.getLocation();
            final Dimension wh = this.getSize();
            node.put("Bounds", String.format("%d %d %d %d", xy.x, xy.y, wh.width, wh.height));
            node.putInt("ReforgeTab", this.reforge_tabs.getSelectedIndex());
            node.putInt("ScrollX", this.scroll.getHorizontalScrollBar().getValue());
            node.putInt("ScrollY", this.scroll.getVerticalScrollBar().getValue());
            this.savePrefs_catusStuff(false);
            node = CatusFrame.PREFS.node("Gear");
            int index = 0;
            for (final Map<Item, ItemWrap> map : this.gearMap.values()) {
                for (final ItemWrap x : map.values()) {
                    if (x.custom) {
                        node.putInt(Integer.toString(index++), x.item.itemId);
                    }
                }
            }
            this.config_save();
            node = CatusFrame.PREFS.node("Configs");
            PrefHelp.silentClear(node);
            index = 0;
            for (final Config2 x2 : this.config_list) {
                if (x2 == this.selectedConfig) {
                    node.putInt("Selected", index);
                }
                PrefHelp.setLargeString(node, Integer.toString(index++), x2.toJSONString());
            }
            node = CatusFrame.PREFS.node("Recent");
            PrefHelp.silentClear(node);
            index = 0;
            for (final RecentArmory x3 : this.recentList) {
                node.put(Integer.toString(index++), x3.name + "/" + x3.realm + "/" + x3.region);
            }
            node = CatusFrame.PREFS.node("Stats");
            String form = "Cat";
            if (this.form_humanBtn.isSelected()) {
                form = "Humam";
            }
            else if (this.form_bearBtn.isSelected()) {
                form = "Bear";
            }
            else if (this.form_guardBtn.isSelected()) {
                form = "Guardian";
            }
            node.put("Form", form);
            node.putBoolean("HitExpPerc", this.hitExpAsPerc);
            node = CatusFrame.PREFS.node("PrefPanes");
            for (final PrefPane x4 : this.prefPanes) {
                node.putBoolean(x4.name, x4.isExpanded());
            }
            node = CatusFrame.PREFS.node("ReforgeSlots");
            for (final ReforgeToggle x5 : this.reforge_toggles) {
                node.putBoolean(x5.slot.name, x5.isSelected());
            }
            node = CatusFrame.PREFS.node("ReforgeRune");
            node.put("HitValue", this.reforgeRune_hit_field.getText());
            node.put("ExpValue", this.reforgeRune_exp_field.getText());
            node.put("HitMode", this.reforgeRune_hit_combo.exportPick());
            node.put("ExpMode", this.reforgeRune_exp_combo.exportPick());
            node.put("SearchRange", this.reforgeRune_range_field.getText());
            node.put("MasteryOverflow", this.reforgeRune_overflow_field.getText());
            node.put("CritGap", this.reforgeRune_critGap_field.getText());
            node.put("HasteGap", this.reforgeRune_hasteGap_field.getText());
            node.putBoolean("CritGreater", this.reforgeRune_critGreater_check.isSelected());
            node.putBoolean("EnchantBack", this.reforgeRune_enchantBack_check.isSelected());
            node.putBoolean("EnchantHands", this.reforgeRune_enchantHands_check.isSelected());
            node.putBoolean("ChangeGems", this.reforgeRune_changeGems_check.isSelected());
            node.putBoolean("KeepGemColors", this.reforgeRune_keepGemColors_check.isSelected());
            node.putBoolean("CanBreakBonuses", this.reforgeRune_breakBonus_check.isSelected());
            node.putBoolean("RespectNullGems", this.reforgeRune_leaveEmpty_check.isSelected());
            node.putBoolean("AllowStamina", this.reforgeRune_useSta_check.isSelected());
            node.putBoolean("AllowPureHit", this.reforgeRune_useHit_check.isSelected());
            node.put("GemColor", this.reforgeRune_gem_combo.exportPick());
            node = CatusFrame.PREFS.node("ReforgeMax");
            node.put("HitValue", this.reforgeMax_hit_field.getText());
            node.put("ExpValue", this.reforgeMax_exp_field.getText());
            node.put("SearchRange", this.reforgeMax_range_field.getText());
            node.put("SearchDepth", this.reforgeMax_depth_field.getText());
            for (int i = 0; i < this.reforgeMax_stats.length; ++i) {
                final StatT stat = this.reforgeMax_stats[i];
                node.put(stat.abbr + "Weight", this.reforgeMax_fields[i].getText());
            }
            node.putBoolean("EnchantBack", this.reforgeMax_enchantBack_check.isSelected());
            node.putBoolean("EnchantHands", this.reforgeMax_enchantHands_check.isSelected());
            node.putBoolean("EnchantFeet", this.reforgeMax_enchantFeet_check.isSelected());
            node.putBoolean("ChangeGems", this.reforgeMax_changeGems_check.isSelected());
            node.putBoolean("CanBreakBonuses", this.reforgeMax_breakBonus_check.isSelected());
            node.putBoolean("RespectNullGems", this.reforgeMax_leaveEmpty_check.isSelected());
            node.putBoolean("BearCap", this.reforgeMax_bear_check.isSelected());
            node.put("BearCrit", this.reforgeMax_bearMax_field.getText());
            node = CatusFrame.PREFS.node("ReforgeOld");
            for (final ReforgeConstraint x6 : this.reforgeOld_constraints) {
                final String prefix = x6.stat.abbr;
                node.put(prefix + "Min", x6.min_field.getText());
                node.put(prefix + "Max", x6.max_field.getText());
                node.put(prefix + "Weight", x6.weight_field.getText());
                node.putBoolean(prefix + "Prevent", x6.prevent_check.isSelected());
            }
            node = CatusFrame.PREFS.node("CombatLog");
            node.putBoolean("PrintEnergy", this.combatLog_printEnergy_check.isSelected());
            node.putBoolean("PrintDebug", this.combatLog_printDebug_check.isSelected());
            node.putBoolean("PrintMore", this.combatLog_printMore_check.isSelected());
            node = CatusFrame.PREFS.node("Sim");
            node.put("Iter", this.sim_iter_field.getText());
            node.put("Err", this.sim_err_field.getText());
            node.put("Step", this.sim_timeDist_field.getText());
            node = CatusFrame.PREFS.node("Compare");
            node.put("Iter", this.compare_iter_field.getText());
            node.put("Objective", this.compare_objective_combo.exportPick());
            node.put("Settings", this.compare_settings_combo.exportPick());
            node = CatusFrame.PREFS.node("Calcs");
            node.putBoolean("Rip4", this.calc_rip4_check.isSelected());
            node.putBoolean("AssumeSR", this.calc_assumeSR_check.isSelected());
            node.putBoolean("TempEffects", this.calc_tempEffects_check.isSelected());
            node.putBoolean("Debuffs", this.calc_useDebuffs_check.isSelected());
            node.putInt("ThrashTicks", this.calc_thrash_spinner.getValue());
            node.putInt("RipTicks", this.calc_rip_spinner.getValue());
            node = CatusFrame.PREFS.node("Weights");
            node.put("Cores", this.weight_cores_combo.exportPick());
            node.put("Iter", this.weight_iter_field.getText());
            node.put("Delta", this.weight_delta_field.getText());
            node.put("HitMax", this.weight_hitMax_field.getText());
            node.put("ExpMax", this.weight_expMax_field.getText());
            node.putBoolean("Group", this.weight_group_check.isSelected());
            node.putBoolean("Inert", this.weight_inert_check.isSelected());
            node.put("DirExp", this.weight_exp_combo.exportPick());
            node.putBoolean("ForceHit", this.weight_forceHit_check.isSelected());
            node.putBoolean("ForceExp", this.weight_forceExp_check.isSelected());
            for (final StatCheck x7 : this.weight_includes) {
                node.putBoolean(x7.key(), x7.isSelected());
            }
            node = CatusFrame.PREFS.node("Trinkets");
            node.put("Iter", this.trink_iter_field.getText());
            node.put("Cores", this.trink_cores_combo.exportPick());
            node.putBoolean("Basic", this.trink_basic_radio.isSelected());
            node.put("Upgrade", this.trink_basic_upgrade_combo.exportPick());
            node.put("Reforge", this.trink_reforge_combo.exportPick());
            node.putBoolean("ReforgeIgnore", this.trink_ignoreErrors_check.isSelected());
            node.put("Replace", this.trink_basic_replace_combo.exportPick());
            final StringBuilder sb = new StringBuilder();
            for (int j = 0; j < this.trink_basic_selectedSet.count; ++j) {
                if (sb.length() > 0) {
                    sb.append(' ');
                }
                sb.append(this.trink_basic_selectedSet.member[j]);
            }
            PrefHelp.setLargeString(node, "BasicCode", sb.toString());
            sb.setLength(0);
            for (final Profile p : this.trink_pairs) {
                sb.append(CompactGear.toString(p, false, false).replace("\n", " / "));
                sb.append("\n");
            }
            PrefHelp.setLargeString(node, "AdvancedCode", sb.toString());
            node.putBoolean("Report", this.trink_report_check.isSelected());
            node.putBoolean("CSV", this.trink_csv_check.isSelected());
            node = CatusFrame.PREFS.node("Scaling");
            node.put("Iter", this.ilvl_iter_field.getText());
            node.put("Lower", this.ilvl_lower_field.getText());
            node.put("Upper", this.ilvl_upper_field.getText());
            node.putBoolean("CSV", this.ilvl_saveCSV_check.isSelected());
            node = CatusFrame.PREFS.node("Mirror");
            PrefHelp.silentClear(node);
            node.put("Report", this.mirror_report_field.getText());
            node.putBoolean("Autoload", this.mirror_parse_check.isSelected());
            for (int i = 0; i < this.mirror_report_recent_combo.getItemCount(); ++i) {
                final NamedThing.Str item = this.mirror_report_recent_combo.at(i);
                node.put(Integer.toString(i), (String)item.obj + "/" + item.name);
            }
            CatusFrame.PREFS.flush();
        }
        catch (BackingStoreException ex) {}
    }
    
    UIComboBox<NamedThing.Int> _createCoresCombo(final boolean inf) {
        final UIComboBox combo = new UIComboBox();
        final int max = Runtime.getRuntime().availableProcessors();
        combo.addItem(new NamedThing.Int("All Cores", max));
        if (max > 1) {
            combo.addItem(new NamedThing.Int("1 Core", 1));
        }
        for (int i = 2; i < max; i *= 2) {
            combo.addItem(new NamedThing.Int(i + " Cores", i));
        }
        if (inf) {
            combo.addItem(new NamedThing.Int("\u221e Cores", 100));
        }
        return (UIComboBox<NamedThing.Int>)combo;
    }
    
    void manageAll() {
        final StringBuilder sb = new StringBuilder();
        sb.append("# Enter item id's separated by whitespace\n");
        sb.append("# Any text following (#) is ignored\n");
        final HashSet<Item> before = new HashSet<Item>();
        for (final Map.Entry<EquipT, Map<Item, ItemWrap>> e : this.gearMap.entrySet()) {
            boolean first = true;
            for (final ItemWrap x : e.getValue().values()) {
                if (x.custom) {
                    if (first) {
                        sb.append("\n# [");
                        sb.append(e.getKey().name);
                        sb.append("]\n");
                        first = false;
                    }
                    Fmt.padRight(sb, Integer.toString(x.item.itemId), 10);
                    sb.append("# ");
                    sb.append(x.toString());
                    sb.append("\n");
                    before.add(x.item);
                }
            }
        }
        final String code = new DialogText(this, "Manage Gear: All", "Save").editText(sb.toString());
        if (code == null) {
            return;
        }
        final IntSet itemIds = new IntSet();
        for (String line : code.split("\n")) {
            final int pos = line.indexOf(35);
            if (pos >= 0) {
                line = line.substring(0, pos);
            }
            line = line.trim();
            if (!line.isEmpty()) {
                for (final String x2 : line.split("\\s+")) {
                    try {
                        itemIds.add(Integer.parseInt(x2));
                    }
                    catch (RuntimeException err) {}
                }
            }
        }
        for (final Item x3 : before) {
            this.gearMap.get(x3.equipType).remove(x3);
        }
        for (final Item x3 : this.loadItems(itemIds)) {
            final Map<Item, ItemWrap> map = this.gearMap.get(x3.equipType);
            if (!map.containsKey(x3)) {
                map.put(x3, new ItemWrap(x3, true));
            }
        }
        this._rebuildGear();
    }
    
    void manageSlot(final SlotT slot) {
        final StringBuilder sb = new StringBuilder();
        sb.append("# Enter item id's separated by whitespace\n");
        sb.append("# Any text following (#) is ignored\n\n");
        final HashSet<Item> before = new HashSet<Item>();
        for (final ItemWrap x : this.matchesForSlot(slot)) {
            if (x.custom) {
                Fmt.padRight(sb, Integer.toString(x.item.itemId), 10);
                sb.append("# ");
                sb.append(x.toString());
                sb.append("\n");
                before.add(x.item);
            }
        }
        final String code = new DialogText(this, "Manage Gear: " + slot.equipName(), "Save").editText(sb.toString());
        if (code == null) {
            return;
        }
        final IntSet itemIds = new IntSet();
        for (String line : code.split("\n")) {
            final int pos = line.indexOf(35);
            if (pos >= 0) {
                line = line.substring(0, pos);
            }
            line = line.trim();
            if (!line.isEmpty()) {
                for (final String x2 : line.split("\\s+")) {
                    try {
                        itemIds.add(Integer.parseInt(x2));
                    }
                    catch (RuntimeException err) {}
                }
            }
        }
        for (final Item x3 : before) {
            this.gearMap.get(x3.equipType).remove(x3);
        }
        for (final Item x3 : this.loadItems(itemIds)) {
            if (slot.accepts(x3.equipType)) {
                final Map<Item, ItemWrap> map = this.gearMap.get(x3.equipType);
                if (map.containsKey(x3)) {
                    continue;
                }
                map.put(x3, new ItemWrap(x3, true));
            }
        }
        this._rebuildGear();
    }
    
    ReforgerMax reforgerMax_extract() {
        final ReforgerMax r = new ReforgerMax();
        try {
            r.hitTarget = UI.parse_nonNegIntOrPercentWithScale(this.reforgeMax_hit_field, 0, 34000.0);
            r.expTarget = UI.parse_nonNegIntOrPercentWithScale(this.reforgeMax_exp_field, 0, 34000.0);
            r.searchRange = UI.parse_nonNegInt(this.reforgeMax_range_field);
            r.searchDepth = (int)UI.parse_double(this.reforgeMax_depth_field, 20000.0, 0.0, 5.0E7);
            Arrays.fill(r.weights, 0.0);
            for (int i = 0; i < this.reforgeMax_stats.length; ++i) {
                final StatT stat = this.reforgeMax_stats[i];
                r.weights[stat.index] = UI.parse_double(this.reforgeMax_fields[i], 0.0, -1000.0, 1000.0);
            }
            if (this.reforgeMax_bear_row.isVisible() && this.reforgeMax_bear_check.isSelected()) {
                r.critPerAgi = this.sim.statMods[FeralSim.STAT_AGI].product / 125951.806640625;
                r.critPerCrit = this.sim.statMods[FeralSim.STAT_CRIT].product / 60000.0;
                r.critMax = UI.parse_percent(this.reforgeMax_bearMax_field, 0.0, 0.0, 1.0, true) - this.sim.getMeleeCritBase();
            }
        }
        catch (RuntimeException err) {
            this.showError("Reforge Constraints Error", err);
            return null;
        }
        r.slotSet = this.getSlotSet();
        r.enchantBack = this.reforgeMax_enchantBack_check.isSelected();
        r.enchantHands = this.reforgeMax_enchantHands_check.isSelected();
        r.enchantFeet = this.reforgeMax_enchantFeet_check.isSelected();
        r.changeGems = this.reforgeMax_changeGems_check.isSelected();
        r.canBreakBonuses = this.reforgeMax_breakBonus_check.isSelected();
        r.respectNullGems = this.reforgeMax_leaveEmpty_check.isSelected();
        return r;
    }
    
    static ReforgerMax reforgerMax_default() {
        final ReforgerMax r = new ReforgerMax();
        r.hitTarget = 2550;
        r.expTarget = 2550;
        r.searchRange = 200;
        System.arraycopy(CatusFrame.WEIGHTS, 0, r.weights, 0, CatusFrame.WEIGHTS.length);
        r.enchantBack = true;
        r.enchantHands = true;
        r.changeGems = true;
        r.canBreakBonuses = true;
        r.respectNullGems = false;
        return r;
    }
    
    void reforger111_gems(final Reforger111.FeralGems gems) {
        final Reforger111 r = this.reforger111_extract();
        if (r == null) {
            return;
        }
        r.feral_gems = gems;
        this.doReforge(r, gems.toString());
    }
    
    void doReforge(final ReforgerBase r, final String name) {
        final DialogProg pb = new DialogProg(this, "Reforging: " + name);
        pb.setAbortOnClose(r.abort);
        try {
            pb.execute(new Runnable() {
                @Override
                public void run() {
                    r.reforge(CatusFrame.this.api, CatusFrame.this.mainProfile, null);
                    CompactGear.fromString(CatusFrame.this.api, CatusFrame.this.mainProfile, r.results[r.resultBestIndex].code);
                }
            });
        }
        catch (RuntimeException err) {
            if (!r.abort.get()) {
                this.showError("Reforge " + name + " Error", err);
            }
            throw new Abort();
        }
    }
    
    private Reforger111 reforger111_default() {
        final Reforger111 r = new Reforger111();
        r.hitTarget = 2550;
        r.expTarget = 2550;
        r.searchRange = 250;
        r.masteryOverflow = 500;
        r.hasteGap = 0;
        r.critGap = 0;
        r.slotSet = null;
        r.hitSearchMode = Reforger111.SearchMode.AT_LEAST;
        r.expSearchMode = Reforger111.SearchMode.AT_LEAST;
        r.critGreater = false;
        r.enchantBack = true;
        r.enchantHands = true;
        r.changeGems = true;
        r.keepGemColors = false;
        r.canBreakBonuses = true;
        r.respectNullGems = false;
        r.feral_gems = this.reforgeRune_gem_combo.getPick();
        return r;
    }
    
    Reforger111 reforger111_extract() {
        final Reforger111 r = new Reforger111();
        try {
            r.hitTarget = UI.parse_nonNegIntOrPercentWithScale(this.reforgeRune_hit_field, 0, 34000.0);
            r.expTarget = UI.parse_nonNegIntOrPercentWithScale(this.reforgeRune_exp_field, 0, 34000.0);
            r.searchRange = UI.parse_nonNegInt(this.reforgeRune_range_field);
            r.masteryOverflow = UI.parse_nonNegInt(this.reforgeRune_overflow_field);
            r.hasteGap = UI.parse_nonNegInt(this.reforgeRune_hasteGap_field);
            r.critGap = UI.parse_nonNegInt(this.reforgeRune_critGap_field);
        }
        catch (RuntimeException err) {
            this.showError("Reforge Constraints Error", err);
            return null;
        }
        r.slotSet = this.getSlotSet();
        r.hitSearchMode = this.reforgeRune_hit_combo.getPick();
        r.expSearchMode = this.reforgeRune_exp_combo.getPick();
        r.critGreater = this.reforgeRune_critGreater_check.isSelected();
        r.enchantBack = this.reforgeRune_enchantBack_check.isSelected();
        r.enchantHands = this.reforgeRune_enchantHands_check.isSelected();
        r.changeGems = this.reforgeRune_changeGems_check.isSelected();
        r.keepGemColors = this.reforgeRune_keepGemColors_check.isSelected();
        r.canBreakBonuses = this.reforgeRune_breakBonus_check.isSelected();
        r.respectNullGems = this.reforgeRune_leaveEmpty_check.isSelected();
        r.feral_gems = this.reforgeRune_gem_combo.getPick();
        r.feral_sta = this.reforgeRune_useSta_check.isSelected();
        r.feral_hit = this.reforgeRune_useHit_check.isSelected();
        return r;
    }
    
    Profile reforger_safeCopy() {
        final Profile copy = this.mainProfile.copyProfile();
        if (copy.scaledItemLevel < 0) {
            copy.setScaledItemLevel(0);
        }
        return copy;
    }
    
    void reforgerMax_select(final ReforgerMax r) {
        this.reforge_results_combo_al.lock();
        this.reforgeMax_results_combo.removeAllItems();
        for (final ProfilePerm x : r.results) {
            this.reforgeMax_results_combo.addItem(x);
        }
        this.reforge_results_combo_al.unlock();
        this.reforgeMax_results_combo.setSelectedItem(r.results[r.resultBestIndex]);
        this.reforgeMax_results_panel.setVisible(true);
    }
    
    void trink_autoFill(final Profile.Slot p) {
        if (ProfT.ENG.isMemberOf(this.mainProfile.profs)) {
            p.setGems(Arrays.asList((Gem[])this.gemsMap.get(GemT.COG)), true);
        }
    }
    
    void trink_applyUpgrade(final Profile.Slot p, final int up) {
        if (up == -1) {
            p.setItemLevelUpgradeMax(this.asiaMode);
        }
        else {
            p.setItemLevelDelta(up);
        }
    }
    
    void trink_sim(final boolean debug, final boolean asEdit) {
        final Profile template = new Profile();
        template.importProfile(this.mainProfile);
        final ArrayList<Profile> profiles = new ArrayList<Profile>();
        if (this.trink_basic_radio.isSelected() || asEdit) {
            final HashSet<Item> set = new HashSet<Item>();
            for (int i = 0; i < this.trink_basic_selectedSet.count; ++i) {
                final Item item = this.api.quickLoader.loadItem(this.trink_basic_selectedSet.member[i]);
                if (item != null) {
                    if (item.equipType == EquipT.TRINKET) {
                        set.add(item);
                    }
                }
            }
            final Item[] itemArr = set.toArray(new Item[set.size()]);
            Arrays.sort(itemArr, Item.CMP_itemLevel);
            final int up = this.trink_basic_upgrade_combo.getPick().value;
            final int replaceMode = this.trink_basic_replace_combo.getPick().value;
            if (replaceMode == 1 || replaceMode == 2) {
                final Profile.Slot slot = (replaceMode == 1) ? template.T1 : template.T2;
                for (final Item x : itemArr) {
                    slot.clear();
                    Label_0293: {
                        try {
                            slot.setItem(x);
                            this.trink_autoFill(slot);
                            this.trink_applyUpgrade(slot, up);
                        }
                        catch (RuntimeException err2) {
                            break Label_0293;
                        }
                        final Profile copy = new Profile();
                        copy.importProfile(template);
                        profiles.add(copy);
                    }
                }
            }
            else {
                for (int j = 1; j < itemArr.length; ++j) {
                    template.T1.clear();
                    template.T2.clear();
                    try {
                        template.T1.setItem(itemArr[j]);
                        this.trink_autoFill(template.T1);
                        this.trink_applyUpgrade(template.T1, up);
                    }
                    catch (RuntimeException err3) {
                        continue;
                    }
                    for (int k = 0; k <= j; ++k) {
                        template.T2.clear();
                        try {
                            template.T2.setItem(itemArr[k]);
                            this.trink_autoFill(template.T2);
                            this.trink_applyUpgrade(template.T2, up);
                        }
                        catch (RuntimeException err4) {
                            continue;
                        }
                        final Profile copy2 = new Profile();
                        copy2.importProfile(template);
                        profiles.add(copy2);
                    }
                }
            }
        }
        else {
            for (final Profile x2 : this.trink_pairs) {
                template.T1.clear();
                template.T2.clear();
                Label_0555: {
                    try {
                        template.T1.copy(x2.T1);
                        template.T2.copy(x2.T2);
                    }
                    catch (RuntimeException err5) {
                        break Label_0555;
                    }
                    final Profile copy3 = new Profile();
                    copy3.importProfile(template);
                    profiles.add(copy3);
                }
            }
        }
        if (asEdit) {
            for (final Profile x3 : profiles) {
                final Profile save = x3.copyProfile();
                x3.clearSlots();
                x3.T1.copy(save.T1);
                x3.T2.copy(save.T2);
            }
            this.trink_editAdvanced(profiles.toArray(new Profile[profiles.size()]));
            return;
        }
        if (profiles.isEmpty()) {
            this.showError(this.trink_pane.name, "None of the specified trinkets produce a profile for simulation.");
            return;
        }
        if (debug) {
            final StringBuilder sb = new StringBuilder();
            final boolean suffix = false;
            int index = 0;
            int max = 0;
            for (final Profile x4 : profiles) {
                max = Math.max(max, x4.T1.getItemNameOrEmpty(suffix).length());
            }
            for (final Profile x4 : profiles) {
                if (sb.length() > 0) {
                    sb.append('\n');
                }
                sb.append(String.format("%3d. ", ++index));
                Fmt.padRight(sb, x4.T1.getItemNameOrEmpty(suffix), max);
                sb.append("  ");
                sb.append(x4.T2.getItemNameOrEmpty(suffix));
            }
            new DialogText(this, String.format("%s (%d)", this.trink_pane.name, profiles.size()), "OK").showText(sb.toString());
            return;
        }
        int iter;
        try {
            iter = UI.parse_posInt(this.trink_iter_field, true);
        }
        catch (RuntimeException err) {
            this.showError(this.trink_pane.name, err);
            return;
        }
        File reportFile = null;
        if (this.trink_report_check.isSelected()) {
            reportFile = this.getReportFile_text("Trinkets", null);
            if (reportFile == null) {
                return;
            }
        }
        File csvFile = null;
        if (this.trink_csv_check.isSelected()) {
            csvFile = this.getReportFile("Trinkets", null, ".csv");
            if (csvFile == null) {
                return;
            }
        }
        try {
            new DialogProg(this, "Testing Configuration").execute(new Runnable() {
                @Override
                public void run() {
                    final FeralSim sim = CatusFrame.this.copySim(true);
                    if (sim == null) {
                        throw new RuntimeException("Gear Error");
                    }
                    if (!CatusFrame.this.prepareSim(sim)) {
                        throw new RuntimeException("Encounter Error");
                    }
                    Utils.sleep(500L);
                }
            });
        }
        catch (RuntimeException err6) {
            return;
        }
        final int reforgeMode = this.trink_reforge_combo.getPick().value;
        if (reforgeMode > 0) {
            Reforger111 rune = null;
            ReforgerMax mast = null;
            switch (reforgeMode) {
                case 1: {
                    rune = this.reforger111_default();
                    mast = reforgerMax_default();
                    break;
                }
                case 2: {
                    rune = this.reforger111_extract();
                    mast = this.reforgerMax_extract();
                    break;
                }
                default: {
                    this.showError("Unknown Reforge Mode", "Mode: " + reforgeMode);
                    return;
                }
            }
            if (rune == null || mast == null) {
                return;
            }
            final ArrayList<Profile> copy4 = new ArrayList<Profile>(profiles);
            profiles.clear();
            final boolean propagateError = !this.trink_ignoreErrors_check.isSelected();
            final AtomicBoolean abort = new AtomicBoolean();
            try {
                final DialogProg pb = new DialogProg(this, String.format("Reforging (%d)", copy4.size()));
                pb.setAbortOnClose(abort);
                pb.forEach(copy4, new DialogProg.Each<Profile>() {
                    @Override
                    public void each(final int index, final Profile p) {
                        if (abort.get()) {
                            throw new RuntimeException("Aborted");
                        }
                        try {
                            if (p.hasSpell(139116)) {
                                rune.reforge(CatusFrame.this.api, p, null);
                                CompactGear.fromString(CatusFrame.this.api, p, rune.results[rune.resultBestIndex].code);
                            }
                            else {
                                mast.reforge(CatusFrame.this.api, p, null);
                                CompactGear.fromString(CatusFrame.this.api, p, mast.results[mast.resultBestIndex].code);
                            }
                            profiles.add(p);
                        }
                        catch (RuntimeException err) {
                            if (propagateError) {
                                throw new RuntimeException(String.format("Unable to reforge:\nT1: %s\nT2: %s\n\n%s", p.T1.getItemNameOrEmpty(), p.T2.getItemNameOrEmpty(), err.getMessage()));
                            }
                        }
                    }
                });
            }
            catch (RuntimeException err2) {
                if (!abort.get()) {
                    this.showError(this.trink_pane.name, err2);
                }
                return;
            }
            if (profiles.isEmpty()) {
                this.showError(this.trink_pane.name, "There are profiles remain after reforging.");
                return;
            }
        }
        final Profile savedProfile = new Profile();
        savedProfile.copySlots(this.mainProfile);
        final ArrayDeque<FeralSim> sims = new ArrayDeque<FeralSim>();
        try {
            new DialogProg(this, "Generating Simulators").forEach(profiles, new DialogProg.Each<Profile>() {
                @Override
                public void each(final int index, final Profile p) {
                    CatusFrame.this.mainProfile.copySlots(p);
                    final FeralSim sim = CatusFrame.this.copySim(false);
                    CatusFrame.this.prepareSim(sim);
                    sims.add(sim);
                }
            });
        }
        finally {
            this.mainProfile.copySlots(savedProfile);
        }
        for (final Profile x5 : profiles) {
            if (x5.T1.item != null && this.api.markImageIcon(x5.T1.item.icon, API.IconSize.SMALL)) {
                this.iconQueue.add(x5.T1.item.icon);
            }
            if (x5.T2.item != null && this.api.markImageIcon(x5.T2.item.icon, API.IconSize.SMALL)) {
                this.iconQueue.add(x5.T2.item.icon);
            }
        }
        this._processIconQueue();
        new TrinkFrame(this.trink_pane.name, sims.toArray(new FeralSim[sims.size()]), iter, reportFile, csvFile).go(this.trink_cores_combo.getPick().value);
    }
    
    boolean trink_editAdvanced(final Profile[] pairs) {
        final StringBuilder sb = new StringBuilder();
        sb.append("# Enter trinket pairs, one per line\n");
        sb.append("#     Pair Format: <Trinket1> / <Trinket2>\n");
        sb.append("#  Trinket Format: <ItemId> (+<ItemLevelDelta>) (i<ItemlLevelAbsolute>)\n");
        sb.append("#         Example: 96918 i600 / 105527 +8 == [600] Rune / [580] Haromm\n");
        sb.append("# Any text following (#) is ignored\n\n");
        for (final Profile p : pairs) {
            sb.append("# T1: ");
            sb.append(p.T1.getItemNameOrEmpty());
            sb.append("\n# T2: ");
            sb.append(p.T2.getItemNameOrEmpty());
            sb.append("\n");
            sb.append(CompactGear.toString(p, false, false).replace("\n", " / "));
            sb.append("\n\n");
        }
        String code = sb.toString();
        final ArrayList<String> errors = new ArrayList<String>();
        while (true) {
            code = new DialogText(this, "Edit: Trinket Pairs", "OK").editText(code);
            if (code == null) {
                return false;
            }
            if (this.trink_validatePairs(code, errors)) {
                return true;
            }
            this.showError_html("CompactGear Error", CompactGear.formatErrors_html(errors));
        }
    }
    
    boolean trink_validatePairs(final String code, final ArrayList<String> errors) {
        if (errors != null) {
            errors.clear();
        }
        final ArrayList<Profile> list = new ArrayList<Profile>();
        int lineNum = 0;
        for (String line : code.split("\n")) {
            ++lineNum;
            int pos = line.indexOf(35);
            if (pos >= 0) {
                line = line.substring(0, pos);
            }
            line = line.trim();
            if (!line.isEmpty()) {
                pos = line.indexOf(47);
                if (pos == -1 && errors != null) {
                    errors.add(CompactGear.makeErr(lineNum, line, "Missing pair-separator (/)", new Object[0]));
                }
                final String explode = line.substring(0, pos) + "\n" + line.substring(pos + 1);
                final Profile p = new Profile();
                if (CompactGear.fromString(this.api, p, explode, errors, lineNum - 1)) {
                    list.add(p);
                }
            }
        }
        final boolean success = errors == null || errors.isEmpty();
        if (success) {
            this.trink_pairs = list.toArray(new Profile[list.size()]);
        }
        return success;
    }
    
    boolean trink_editBasic() {
        final Set<Item> itemSet = this.gearMap.get(EquipT.TRINKET).keySet();
        if (itemSet.isEmpty()) {
            this.showError("No Trinkets", "There are no trinkets loaded.");
            return false;
        }
        final HashSet<ArrayList<Item>> groups = new HashSet<ArrayList<Item>>();
        for (final Item x : itemSet) {
            if (this.api.markImageIcon(x.icon, API.IconSize.SMALL)) {
                this.iconQueue.add(x.icon);
            }
            groups.add(x.groupSet);
        }
        this._processIconQueue();
        final ArrayList<ArrayList<Item>> sorted = new ArrayList<ArrayList<Item>>(groups);
        Collections.sort(sorted, new Comparator<ArrayList<Item>>() {
            @Override
            public int compare(final ArrayList<Item> a, final ArrayList<Item> b) {
                final Item aa = a.get(0);
                final Item bb = b.get(0);
                final int cmp = bb.itemLevel - aa.itemLevel;
                return (cmp == 0) ? aa.name.compareTo(bb.name) : cmp;
            }
        });
        final UIPanel_GridBag p = new UIPanel_GridBag();
        p.pad(8);
        final HashMap<Item, JToggleButton> toggleMap = new HashMap<Item, JToggleButton>();
        for (final ArrayList<Item> group : sorted) {
            final UIPanel_GridBag row = new UIPanel_GridBag();
            final Item item0 = group.get(0);
            row.add(new JLabel(item0.icon.getIcon(API.IconSize.SMALL)));
            final LinkButton name_btn = new LinkButton(item0);
            name_btn.setText(item0.name);
            name_btn.setFont(CatusFrame.tinyFont);
            name_btn.setForeground(item0.quality.color);
            row.gap_add(4, name_btn);
            row.spacer();
            for (final Item x2 : group) {
                final JToggleButton btn = UI.makeCheck(Integer.toString(x2.itemLevel));
                btn.setEnabled(x2.validate(ClassT.DRUID, this.mainProfile.race, this.mainProfile.profs));
                btn.setSelected(this.trink_basic_selectedSet.contains(x2.itemId));
                toggleMap.put(x2, btn);
                row.add(btn);
            }
            p.row(row, true, 0);
        }
        final JButton save_btn = UI.makeButton("Select");
        final JButton clear_btn = UI.makeButton("Clear");
        final UIPanel_GridBag bot_row = new UIPanel_GridBag();
        bot_row.add(clear_btn);
        bot_row.spacer();
        bot_row.add(save_btn);
        p.row(bot_row, true, 4);
        final JDialog d = new JDialog(this, String.format("Select Trinkets (%d)", itemSet.size()), Dialog.ModalityType.DOCUMENT_MODAL);
        d.setDefaultCloseOperation(1);
        d.setResizable(false);
        d.setContentPane(p);
        d.pack();
        UI.onShortcut(p, 87, "Close", new Runnable() {
            @Override
            public void run() {
                d.dispatchEvent(new WindowEvent(d, 201));
            }
        });
        clear_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                for (final Map.Entry<Item, JToggleButton> e : toggleMap.entrySet()) {
                    e.getValue().setSelected(false);
                }
            }
        });
        final AtomicBoolean saved = new AtomicBoolean();
        save_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.trink_basic_selectedSet.clear();
                for (final Map.Entry<Item, JToggleButton> e : toggleMap.entrySet()) {
                    if (e.getValue().isSelected()) {
                        CatusFrame.this.trink_basic_selectedSet.add(e.getKey().itemId);
                    }
                }
                saved.set(true);
                d.setVisible(false);
            }
        });
        d.setLocationRelativeTo(this);
        d.setVisible(true);
        d.dispose();
        return saved.get();
    }
    
    int compare_iter() {
        int iter = this.compare_objective_combo.getPick().value;
        if (iter == 0) {
            try {
                if (iter == 0) {
                    iter = UI.parse_posInt(this.compare_iter_field, false);
                }
            }
            catch (RuntimeException err) {
                this.showError(this.compare_pane.name, err);
                return 0;
            }
        }
        return iter;
    }
    
    void compare_sim_armory(final boolean force) {
        this.compare_sim(new NamedTweaker("Catus") {
            @Override
            public void run() {
            }
        }, new NamedTweaker("Armory") {
            @Override
            public void run() {
                if (!CatusFrame.this.importChar(force)) {
                    throw new Abort();
                }
            }
        });
    }
    
    void compare_sim(final Config2 config) {
        final int iter = this.compare_iter();
        if (iter == 0) {
            return;
        }
        this.config_save();
        final int styleMode = this.compare_settings_combo.getPick().value;
        FeralSim simA;
        FeralSim simB;
        try {
            this.dontRebuild = true;
            if (styleMode == 1) {
                this.loadConfig_effects0();
            }
            simA = this.copySimAndEncounter();
            if (simA == null) {
                return;
            }
            simA.simName = this.selectedConfig.name;
            if (config == null) {
                if (!this.importChar(false)) {
                    return;
                }
            }
            else {
                this.loadConfig(config.root, styleMode != 2);
            }
            simB = this.copySimAndEncounter();
            if (simB == null) {
                return;
            }
            simB.simName = config.name;
        }
        finally {
            this.loadConfig(this.selectedConfig.root);
            this.dontRebuild = false;
        }
        this.compare_sim0(simA, simB, iter);
    }
    
    void compare_talents(final int talentRow, final int talent0, final int talent1) {
        this.config_save();
        final byte b = this.talentBytes[talentRow];
        this.talentBytes[talentRow] = (byte)talent0;
        final JSONObject configA = this.exportConfig(API.abbrName(FeralSpec.TALENTS[talentRow][talent0].name));
        this.talentBytes[talentRow] = (byte)talent1;
        final JSONObject configB = this.exportConfig(API.abbrName(FeralSpec.TALENTS[talentRow][talent1].name));
        this.talentBytes[talentRow] = b;
        this.compare_sim(configA, configB);
    }
    
    void compare_talents(final int talentRowA, final int talent0A, final int talent1A, final int talentRowB, final int talent0B, final int talent1B) {
        this.config_save();
        final byte saveA = this.talentBytes[talentRowA];
        final byte saveB = this.talentBytes[talentRowB];
        this.talentBytes[talentRowA] = (byte)talent0A;
        this.talentBytes[talentRowB] = (byte)talent0B;
        final JSONObject configA = this.exportConfig(API.abbrName(FeralSpec.TALENTS[talentRowA][talent0A].name) + "/" + API.abbrName(FeralSpec.TALENTS[talentRowB][talent0B].name));
        this.talentBytes[talentRowA] = (byte)talent1A;
        this.talentBytes[talentRowB] = (byte)talent1B;
        final JSONObject configB = this.exportConfig(API.abbrName(FeralSpec.TALENTS[talentRowA][talent1A].name) + "/" + API.abbrName(FeralSpec.TALENTS[talentRowB][talent1B].name));
        this.talentBytes[talentRowA] = saveA;
        this.talentBytes[talentRowB] = saveB;
        this.compare_sim(configA, configB);
    }
    
    void compare_sim(final NamedTweaker runA, final NamedTweaker runB) {
        final int iter = this.compare_iter();
        if (iter == 0) {
            return;
        }
        this.config_save();
        final int styleMode = this.compare_settings_combo.getPick().value;
        FeralSim simA;
        FeralSim simB;
        try {
            this.dontRebuild = true;
            this.loadConfig(this.selectedConfig.root);
            if (styleMode == 1) {
                this.loadConfig_effects0();
            }
            runA.run();
            simA = this.copySimAndEncounter();
            if (simA == null) {
                return;
            }
            runA.postTweak(simA);
            this.loadConfig(this.selectedConfig.root);
            if (styleMode == 1) {
                this.loadConfig_effects0();
            }
            runB.run();
            simB = this.copySimAndEncounter();
            if (simB == null) {
                return;
            }
            runB.postTweak(simB);
            simA.simName = runA.name;
            simB.simName = runB.name;
        }
        catch (Abort ignore) {
            return;
        }
        catch (RuntimeException err) {
            this.showError(this.compare_pane.name, err);
            return;
        }
        finally {
            this.loadConfig(this.selectedConfig.root);
            this.dontRebuild = false;
        }
        this.compare_sim0(simA, simB, iter);
    }
    
    void compare_sim(final JSONObject rootA, final JSONObject rootB) {
        final int iter = this.compare_iter();
        if (iter == 0) {
            return;
        }
        this.config_save();
        final int styleMode = this.compare_settings_combo.getPick().value;
        FeralSim simA;
        FeralSim simB;
        try {
            this.dontRebuild = true;
            this.loadConfig(rootA);
            if (styleMode == 1) {
                this.loadConfig_effects0();
            }
            simA = this.copySimAndEncounter();
            if (simA == null) {
                return;
            }
            this.loadConfig(rootB);
            if (styleMode == 1) {
                this.loadConfig_effects0();
            }
            simB = this.copySimAndEncounter();
            if (simB == null) {
                return;
            }
            simA.simName = JSONHelp.getStr(rootA, "Name", "A");
            simB.simName = JSONHelp.getStr(rootB, "Name", "B");
        }
        finally {
            this.loadConfig(this.selectedConfig.root);
            this.dontRebuild = false;
        }
        this.compare_sim0(simA, simB, iter);
    }
    
    void compare_sim0(final FeralSim simA, final FeralSim simB, final int iter) {
        try {
            new DialogProg(this, "Generating Simulators").forEach(Arrays.asList(simA, simB), new DialogProg.Each<FeralSim>() {
                @Override
                public void each(final int index, final FeralSim sim) {
                    try {
                        sim.setupCombat();
                    }
                    catch (RuntimeException err) {
                        throw new NamedRuntimeException(sim.simName + ": " + sim.encounter.name + " Encounter", err);
                    }
                }
            });
        }
        catch (NamedRuntimeException err) {
            this.showError(err);
            return;
        }
        catch (RuntimeException err2) {
            this.showError(this.compare_pane.name, err2);
            return;
        }
        new FightFrame(this.compare_pane.name, simA, simB, iter).go();
    }
    
    void config_updateName() {
        final FontMetrics metrics = this.getFontMetrics(CatusFrame.boldFont);
        this.config2_name_text.setFont((metrics.stringWidth(this.selectedConfig.name) > 400) ? CatusFrame.normalFont : CatusFrame.boldFont);
        this.config2_name_text.setText(this.selectedConfig.name);
    }
    
    void config_ensureSelected() {
        this.config2_combo_al.lock();
        this.config2_combo.setSelectedItem(this.selectedConfig);
        this.config2_combo_al.unlock();
        this.config_rebuildConfigPickers();
    }
    
    void config_select_ifDiff(final Config2 config) {
        if (config != this.selectedConfig) {
            this.config_select(config);
        }
    }
    
    void config_save() {
        if (this.selectedConfig != null) {
            this.selectedConfig.root = this.exportConfig(this.selectedConfig.name);
        }
    }
    
    void config_select(final Config2 config) {
        this.config_save();
        this.selectedConfig = config;
        this.config_ensureSelected();
        this.config_updateName();
        this.loadConfig(config.root);
    }
    
    void config_rename() {
        final AtomicBoolean b = new AtomicBoolean();
        final JDialog d = new JDialog(this, "Rename", Dialog.ModalityType.DOCUMENT_MODAL);
        d.setDefaultCloseOperation(1);
        final UIPanel_GridBag p = new UIPanel_GridBag();
        final JButton ok_btn = UI.makeButton("Rename");
        final JTextField name_field = new JTextField();
        name_field.setText(this.selectedConfig.name);
        ok_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                b.set(true);
                d.setVisible(false);
            }
        });
        p.row(name_field, true, 0);
        final UIPanel_GridBag row = new UIPanel_GridBag();
        row.spacer();
        row.add(ok_btn);
        p.row(row, true, 4);
        p.pad(8);
        d.setContentPane(p);
        d.getRootPane().setDefaultButton(ok_btn);
        Utils.makeSheet(d);
        d.pack();
        d.setSize(new Dimension(this.getWidth(), d.getHeight()));
        d.setLocationRelativeTo(this);
        d.setVisible(true);
        d.dispose();
        if (!b.get()) {
            return;
        }
        this.config_setName(name_field.getText());
    }
    
    void config_setName(String name) {
        this.selectedConfig.name = null;
        name = name.trim();
        this.selectedConfig.name = (name.isEmpty() ? this._getNextConfigName("Untitled Configuration") : name);
        this.config_updateName();
    }
    
    private String _getNextConfigName(final String name) {
        int last = 0;
        final int pos = name.lastIndexOf(32);
        String prefix = name + " ";
        if (pos >= 0) {
            final String num = name.substring(pos + 1).trim();
            if (num.matches("^\\d+$")) {
                try {
                    last = Integer.parseInt(num);
                    prefix = name.substring(0, pos + 1);
                }
                catch (NumberFormatException ex) {}
            }
        }
        for (final Config2 x : this.config_list) {
            if (x.name == null) {
                continue;
            }
            if (!x.name.startsWith(prefix)) {
                if (!x.name.equals(name)) {
                    continue;
                }
                last = 1;
            }
            else {
                final String num2 = x.name.substring(prefix.length());
                if (!num2.matches("^\\d+$")) {
                    continue;
                }
                try {
                    last = Math.max(last, Integer.parseInt(num2));
                }
                catch (NumberFormatException ex2) {}
            }
        }
        return (last == 0) ? name : (prefix + (last + 1));
    }
    
    void config_new(final boolean blank) {
        this.config_save();
        final Config2 config = new Config2();
        config.name = this._getNextConfigName(this.selectedConfig.name);
        if (blank) {
            this.config_list.add(config);
            this.selectedConfig = config;
            this.config_makeDefault();
        }
        else {
            this.config_list.add(this.config_list.indexOf(this.selectedConfig) + 1, config);
            config.root = (JSONObject)JSONValue.parse(this.selectedConfig.root.toJSONString());
            this.selectedConfig = config;
            this.config_ensureSelected();
            this.config_updateName();
        }
        this.config_rebuildList();
    }
    
    void config_deleteAll() {
        this.config_list.clear();
        this.config_list.add(this.selectedConfig);
        this.config_makeDefault();
        this.config_rebuildList();
    }
    
    void config_makeDefault() {
        final Config2 config = this.selectedConfig;
        config.name = "Untitled Configuration";
        config.root = new JSONObject();
        this.selectedConfig = null;
        this.config_select(config);
    }
    
    void config_delete() {
        if (this.config_list.size() > 1) {
            final int index = this.config_list.indexOf(this.selectedConfig);
            this.config_list.remove(index);
            this.config_select(this.config_list.get(Math.max(0, index - 1)));
        }
        else {
            this.config_makeDefault();
        }
        this.config_rebuildList();
    }
    
    void config_rebuildList() {
        this.config2_combo_al.lock();
        this.config2_combo.removeAllItems();
        int index = 0;
        for (final Config2 x : this.config_list) {
            x.index = index++;
            this.config2_combo.addItem(x);
        }
        this.config2_combo.setSelectedItem(this.selectedConfig);
        this.config2_combo_al.unlock();
        this.config_rebuildConfigPickers();
    }
    
    void config_rebuildConfigPickers() {
        this.compareGear_config_combo_al.lock();
        this.compareGear_config_combo.removeAllItems();
        for (final Config2 x : this.config_list) {
            if (x == this.selectedConfig) {
                continue;
            }
            this.compareGear_config_combo.addItem(x);
        }
        this.compareGear_config_combo.setEnabled(this.config_list.size() > 1);
        UI.setComboText(this.compareGear_config_combo);
        this.compareGear_config_combo_al.unlock();
        this.equip_config_combo_al.lock();
        this.equip_config_combo.removeAllItems();
        this.equip_config_combo.addItem("Restore from Armory");
        for (final Config2 x : this.config_list) {
            if (x == this.selectedConfig) {
                continue;
            }
            this.equip_config_combo.addItem(x);
        }
        UI.setComboText(this.equip_config_combo);
        this.equip_config_combo_al.unlock();
        this.compare_config_combo_al.lock();
        this.compare_config_combo.removeAllItems();
        for (final Config2 x : this.config_list) {
            if (x == this.selectedConfig) {
                continue;
            }
            this.compare_config_combo.addItem(x);
        }
        this.compareGear_config_combo.setEnabled(this.config_list.size() > 1);
        UI.setComboText(this.compare_config_combo);
        this.compare_config_combo_al.unlock();
    }
    
    Config2 config_create(final JSONObject root) {
        String name = JSONHelp.getStr(root, "Name", null);
        if (name == null || name.isEmpty()) {
            name = this._getNextConfigName("Untitled Configuration");
        }
        final Config2 config = new Config2();
        config.name = name;
        config.root = root;
        return config;
    }
    
    void resetEncounterCombo() {
        this.encounter_combo_al.lock();
        this.fight_encounter_combo.setEditable(true);
        this.fight_encounter_combo.setSelectedItem(this.fight_encounter_combo.getPrototypeDisplayValue());
        this.fight_encounter_combo.setEditable(false);
        this.encounter_combo_al.unlock();
    }
    
    void refreshEncounterCombo() {
        this.encounter_combo_al.lock();
        this.fight_encounter_combo.removeAllItems();
        this.fight_encounter_combo.addItem(new NamedThing.Obj<Object>("Reset to Defaults", null));
        int num = 0;
        final String json = this.hc.getStr_silent("https://dl.dropboxusercontent.com/u/2989349/catus_data/Encounters/44/StandardFights.json", "", 604800000);
        if (json != null) {
            final Object o = JSONValue.parse(json);
            if (o instanceof JSONArray) {
                final JSONArray root = (JSONArray)o;
                for (final Object x : root) {
                    if (x instanceof JSONObject) {
                        final JSONObject map = (JSONObject)x;
                        String name = JSONHelp.getStr(map, "Name", null);
                        final JSONObject fightMap = JSONHelp.getObject(map, "Fights");
                        if (name == null || fightMap == null) {
                            continue;
                        }
                        name = name + " (" + JSONHelp.getStr(fightMap, "Selected", "Unknown") + ")";
                        this.fight_encounter_combo.addItem(new NamedThing.Obj<JSONObject>(name, fightMap));
                        ++num;
                    }
                }
            }
        }
        this.fight_encounter_combo.setPrototypeDisplayValue("Standard Encounters (" + num + ")");
        this.encounter_combo_al.unlock();
        this.resetEncounterCombo();
    }
    
    private static JPanel _makePanel() {
        final JPanel temp = new JPanel();
        temp.setOpaque(false);
        return temp;
    }
    
    static JLabel _makeStatLabel(final String name, final boolean right) {
        final JLabel temp = new JLabel();
        temp.setText(name);
        temp.setHorizontalAlignment(right ? 4 : 2);
        temp.setForeground(SystemColor.textInactiveText);
        return temp;
    }
    
    private static void _addComboItemAndPrototype(final JComboBox combo, final Object item) {
        combo.setPrototypeDisplayValue(item);
        combo.addItem(item);
    }
    
    void showError(final NamedRuntimeException err) {
        this.showError(err.name, err);
    }
    
    void showError(final String title, final Throwable err) {
        this.showError(title, Fmt.exception(err));
    }
    
    void showError(final String title, final String msg) {
        this.showError_html(title, UI.wrapAsHtml(msg));
    }
    
    void showError_html(final String title, final String html) {
        JOptionPane.showMessageDialog(this, html, title, 0);
    }
    
    void screenshot(final JPanel comp, final String name, final boolean pick) {
        final String defName = String.format("%2$s_%1$tY%1$tm%1$td_%1$tH-%1$tM-%1$tS.png", Calendar.getInstance(), name);
        File saveFile;
        if (pick) {
            final JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Save " + name + " Screenshot");
            fc.setSelectedFile(new File(defName));
            final int ret = fc.showSaveDialog(this);
            if (ret != 0) {
                return;
            }
            final File file = fc.getSelectedFile();
            String fileName = file.getName().trim();
            if (fileName.isEmpty()) {
                return;
            }
            if (!fileName.endsWith(".png")) {
                fileName += ".png";
            }
            saveFile = new File(file.getParent(), fileName);
        }
        else {
            saveFile = new File(defName);
        }
        final int w = comp.getWidth();
        final int h = comp.getHeight();
        final GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        final BufferedImage bi = gc.createCompatibleImage(w, h, 1);
        final boolean op = comp.isOpaque();
        comp.setOpaque(true);
        final Graphics2D g = bi.createGraphics();
        final long t = System.currentTimeMillis();
        comp.paint(g);
        System.out.println("Screenshot paint(): " + Fmt.msDur_since(t));
        g.dispose();
        comp.setOpaque(op);
        try {
            new DialogProg(this, "Saving Screenshot").execute(new Runnable() {
                @Override
                public void run() {
                    OutputStream out = null;
                    try {
                        out = new BufferedOutputStream(new FileOutputStream(saveFile));
                        ImageIO.write(bi, "png", out);
                        out.close();
                    }
                    catch (IOException err) {
                        Utils.silentClose(out);
                        saveFile.delete();
                        throw new RuntimeException(err);
                    }
                }
            });
            Utils.openFile(saveFile);
        }
        catch (RuntimeException err) {
            this.showError("Screenshot Failed", err.getCause());
        }
    }
    
    File getReportFile_text(final String name, final String filePrefix) {
        return this.getReportFile(name, filePrefix, ".txt");
    }
    
    File getReportFile(final String name, String filePrefix, final String fileExt) {
        if (filePrefix == null) {
            filePrefix = name;
        }
        if (this.fight_autosave_check.isSelected()) {
            if (!CatusFrame.REPORTS_DIR.isDirectory() && !CatusFrame.REPORTS_DIR.mkdir()) {
                this.showError(name + " Error", "Unable to create directory: " + CatusFrame.REPORTS_DIR);
                return null;
            }
            return new File(CatusFrame.REPORTS_DIR, String.format("%2$s_%1$tY%1$tm%1$td_%1$tH-%1$tM-%1$tS" + fileExt, Calendar.getInstance(), filePrefix));
        }
        else {
            try {
                return File.createTempFile(filePrefix, fileExt);
            }
            catch (IOException err) {
                this.showError(name + " Error", "Unable to create temporary file: " + err);
                return null;
            }
        }
    }
    
    CatusFrame(final HttpCache hc_, final API api_, final Map<EquipT, Set<Item>> gearMap0, final Map<GemT, Set<Gem>> gemsMap0) {
        this.config_list = new ArrayList<Config2>();
        this.encounter_combo_al = new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                final NamedThing.Obj<JSONObject> sel = CatusFrame.this.fight_encounter_combo.getPick();
                CatusFrame.this.fight_encounter_combo.hidePopup();
                if (sel == null) {
                    return;
                }
                CatusFrame.this.loadConfig_fights(sel.obj);
                CatusFrame.this.resetEncounterCombo();
            }
        };
        this.recentList = new ArrayList<RecentArmory>();
        this.trink_basic_selectedSet = new IntSet();
        this.compare_config_combo_al = new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                final Config2 config = CatusFrame.this.compare_config_combo.getPick();
                CatusFrame.this.compare_config_combo.hidePopup();
                this.lock();
                UI.setComboText(CatusFrame.this.compare_config_combo);
                this.unlock();
                if (config == null) {
                    return;
                }
                CatusFrame.this.compare_sim(config);
            }
        };
        this.config2_combo_al = new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                final Config2 sel = CatusFrame.this.config2_combo.getPick();
                CatusFrame.this.config2_combo.hidePopup();
                CatusFrame.this.config_select(sel);
            }
        };
        this.raceAndProfs_al = new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                CatusFrame.this.applyRaceAndProfs();
            }
        };
        this.consume_al = new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                CatusFrame.this._updateConsumeOptions();
                CatusFrame.this._rebuildGear();
            }
        };
        this.groupBuff_al = new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                CatusFrame.this._updateGroupBuffOptions();
                CatusFrame.this._rebuildGear();
            }
        };
        this.debuff_al = new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                CatusFrame.this._updateDebuffOptions();
            }
        };
        this.tempEffect_al = new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                CatusFrame.this._updateTempEffectOptions();
            }
        };
        this.region_combo_al = new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                final boolean b = CatusFrame.this.region_combo.getPick().asia;
                if (CatusFrame.this.asiaMode != b) {
                    CatusFrame.this.asiaMode = b;
                    CatusFrame.this._rebuildGear();
                }
            }
        };
        this.talent_btns_al = new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                CatusFrame.this.applyTalents();
                CatusFrame.this._rebuildGear();
            }
        };
        this.glyph_combo_al = new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                CatusFrame.this.applyGlyphs();
                CatusFrame.this._rebuildGear();
            }
        };
        this.compareGear_config_combo_al = new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                final Config2 config = CatusFrame.this.compareGear_config_combo.getPick();
                CatusFrame.this.compareGear_config_combo.hidePopup();
                this.lock();
                UI.setComboText(CatusFrame.this.compareGear_config_combo);
                this.unlock();
                if (config == null) {
                    return;
                }
                CatusFrame.this.compareGear(CatusFrame.this.mainProfile, config.getGear());
            }
        };
        this.compareGear_recent_combo_al = new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                final RecentArmory recent = CatusFrame.this.compareGear_recent_combo.getPick();
                CatusFrame.this.compareGear_recent_combo.hidePopup();
                this.lock();
                UI.setComboText(CatusFrame.this.compareGear_recent_combo);
                this.unlock();
                if (recent == null) {
                    return;
                }
                try {
                    new DialogProg(CatusFrame.this, "Importing").execute(new Runnable() {
                        @Override
                        public void run() {
                            final Profile p = CatusFrame.this.api.loadChar(recent.name, recent.realm, recent.region);
                            CatusFrame.this.compareGear(CatusFrame.this.mainProfile, p);
                        }
                    });
                }
                catch (RuntimeException err) {
                    CatusFrame.this.showError("Unable to Import", err);
                }
            }
        };
        this.equip_config_combo_al = new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                final Object sel = CatusFrame.this.equip_config_combo.getSelectedItem();
                CatusFrame.this.equip_config_combo.hidePopup();
                this.lock();
                UI.setComboText(CatusFrame.this.equip_config_combo);
                this.unlock();
                if (sel instanceof Config2) {
                    final Config2 config = (Config2)sel;
                    CatusFrame.this.loadConfig(config.root, true);
                }
                else if ("Restore from Armory" == sel) {
                    final Profile p = CatusFrame.this.importArmory(false);
                    if (p == null) {
                        return;
                    }
                    try {
                        CatusFrame.this.mainProfile.copySlots(p);
                        CatusFrame.this.mainProfile.validate();
                    }
                    catch (RuntimeException err) {
                        CatusFrame.this.showError("Equip Error", err);
                        return;
                    }
                    CatusFrame.this._rebuildGear();
                }
            }
        };
        this.talentGroups = new ButtonGroup[6];
        this.talent_btns = new JToggleButton[6][3];
        this.mainProfile = new Profile();
        this.swapProfile = new Profile();
        this.sim = new FeralSim();
        this.spec = new FeralSpec();
        this.cfg = new FeralConfig();
        this.customStats = new StatMap();
        this.gearMap = new HashMap<EquipT, Map<Item, ItemWrap>>();
        this.gemsMap = new HashMap<GemT, Gem[]>();
        this.bonusMap = new HashMap<SetBonus, SetBonusHelper>();
        this.reforge_results_combo_al = new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                final Object sel = ((JComboBox)ae.getSource()).getSelectedItem();
                if (sel instanceof ProfilePerm) {
                    final ArrayList<String> errors = new ArrayList<String>();
                    if (!CompactGear.fromString(CatusFrame.this.api, CatusFrame.this.mainProfile, ((ProfilePerm)sel).code, errors, 0)) {
                        CatusFrame.this.showError("Unexpected Error", "WTF!\n\n" + Fmt.join(errors, "\n"));
                    }
                    CatusFrame.this.keepReforgeVisible = true;
                    CatusFrame.this._rebuildGear();
                    CatusFrame.this.keepReforgeVisible = false;
                }
            }
        };
        this.import_recent_combo_al = new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                final Object sel = CatusFrame.this.import_recent_combo.getSelectedItem();
                CatusFrame.this.import_recent_combo.hidePopup();
                this.lock();
                UI.setComboText(CatusFrame.this.import_recent_combo);
                this.unlock();
                if (sel instanceof RecentArmory) {
                    final RecentArmory temp = (RecentArmory)sel;
                    CatusFrame.this.region_combo_al.lock();
                    CatusFrame.this.region_combo.setSelectedItem(temp.region);
                    CatusFrame.this.region_combo_al.unlock();
                    CatusFrame.this.asiaMode = temp.region.asia;
                    CatusFrame.this._rebuildGear();
                    CatusFrame.this.import_char_field.setText(temp.name + CatusFrame.JOINT_NAME_REALM_SEP + temp.realm + "!");
                    CatusFrame.this.import_char_btn.doClick();
                }
                else if (new DialogRecentArmory(CatusFrame.this).edit(CatusFrame.this.recentList)) {
                    CatusFrame.this._updateRecentCombo();
                }
            }
        };
        this.keepReforgeVisible = false;
        this.dontRebuild = false;
        this.iconQueue = new HashSet<IconStore>();
        this.slotStoreMap = new HashMap<Profile.Slot, SlotStore>();
        this._enchantBuf = new ArrayList<EnchantT>();
        this._tinkerBuf = new ArrayList<TinkerT>();
        this.talentBytes = new byte[6];
        this.hc = hc_;
        this.api = api_;
        final String tit = Utils.windowTitle("Catus", 44);
        this.setTitle(tit);
        if (Utils.isWindows()) {
            this.setIconImage(this.api.getIconImage(InterfaceIcon.CAT_FORM.slug, API.IconSize.LARGE).getImage());
        }
        else if (Utils.isMac()) {
            final File jarFile = new File("Catus.jar");
            if (jarFile.isFile()) {
                this.rootPane.putClientProperty("Window.documentFile", jarFile);
            }
        }
        this.setDefaultCloseOperation(3);
        this.setMinimumSize(new Dimension(800, 800));
        this.mainProfile.dollMode = true;
        this.swapProfile.dollMode = true;
        this.mainProfile.spec = SpecT.FERAL;
        this.swapProfile.spec = this.mainProfile.spec;
        final Dimension numberFieldSize = new JTextField("1234567").getPreferredSize();
        final Dimension wideNumberFieldSize = new JTextField("1234567890").getPreferredSize();
        final Dimension tinyNumberFieldSize = new JTextField("12.34").getPreferredSize();
        final HashSet<ItemSet> sets = new HashSet<ItemSet>();
        sets.add(new ItemSet(10000001, "WoD: T17", QualityT.ARTIFACT, new SetBonus[] { new SetBonus(0, 2, "Shred critical strikes reduce the cooldown of Berserk by 5sec."), new SetBonus(1, 2, "Dealing bleed damage generates 3 Energy."), new SetBonus(2, 4, "While Berserk is active, special attacks deal an additional 15% bleed damage to the target over 6 sec.") }, 5));
        sets.add(new ItemSet(10000002, "WoD: PvP", QualityT.ARTIFACT, new SetBonus[] { new SetBonus(0, 2, "Interrupting a spell with Skull Bash resets the cooldown of Tiger's Fury."), new SetBonus(1, 4, "Shred critical strikes cause Bloodletting on the target.  Bloodletting increases all bleed damage taken by 10% for 6sec.") }, 5));
        for (final EquipT x : EquipT.ALL) {
            final HashMap<Item, ItemWrap> map = new HashMap<Item, ItemWrap>();
            final Set<Item> gear = gearMap0.get(x);
            if (gear != null) {
                for (final Item item : gear) {
                    map.put(item, new ItemWrap(item, false));
                    if (item.itemSet != null) {
                        sets.add(item.itemSet);
                    }
                }
            }
            this.gearMap.put(x, map);
        }
        final IntSet special = new IntSet();
        special.add(1127);
        special.add(1153);
        special.add(1199);
        special.add(10000001);
        special.add(10000002);
        Arrays.sort(this.itemSets = sets.toArray(new ItemSet[sets.size()]), new Comparator<ItemSet>() {
            @Override
            public int compare(final ItemSet a, final ItemSet b) {
                final boolean aa = special.contains(a.id);
                final boolean bb = special.contains(b.id);
                return (aa == bb) ? (b.id - a.id) : (aa ? -1 : 1);
            }
        });
        final ArrayList<Gem> jcList = new ArrayList<Gem>();
        for (final GemT x2 : GemT.ALL) {
            final Set<Gem> src = gemsMap0.get(x2);
            final HashSet<Gem> dst = new HashSet<Gem>();
            if (src != null) {
                dst.addAll((Collection<?>)src);
            }
            final Gem[] array;
            final Gem[] gems = array = dst.toArray(new Gem[dst.size()]);
            for (final Gem gem : array) {
                if (gem.requiredProf == ProfT.JC) {
                    jcList.add(gem);
                }
            }
            Arrays.sort(gems, CatusFrame.cmp_basicWeights);
            this.gemsMap.put(x2, gems);
        }
        Arrays.sort(this.jcGems = jcList.toArray(new Gem[jcList.size()]), CatusFrame.cmp_basicWeights);
        for (final ItemSet set : this.itemSets) {
            for (final SetBonus x3 : set.bonuses) {
                this.bonusMap.put(x3, new SetBonusHelper(set, x3));
            }
        }
        (this.import_char_field = new JTextField(32)).registerKeyboardAction(this.import_char_field.getActionForKeyStroke(KeyStroke.getKeyStroke(10, 0)), KeyStroke.getKeyStroke(10, 512), 0);
        this.import_char_field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.import_char_btn.doClick();
            }
        });
        final JButton webBtn = UI.makeButton("Armory");
        this.import_char_btn = UI.makeButton("Import");
        (this.region_combo = new UIComboBox<RegionT>(RegionT.values())).addActionListener(this.region_combo_al);
        webBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final RecentArmory a = CatusFrame.this.parsePlayer(UI.isAltKeyDown(ae));
                if (a == null) {
                    return;
                }
                try {
                    API.showArmory(a.name, a.realm, a.region);
                }
                catch (RuntimeException err) {
                    CatusFrame.this.showError("Go to Armory Error", err.getMessage());
                }
            }
        });
        this.import_char_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                if (!UI.isShiftKeyDown(ae)) {
                    if (CatusFrame.this.importChar(UI.isAltKeyDown(ae)) && (CatusFrame.this.selectedConfig.name.startsWith("Untitled Configuration") || CatusFrame.this.selectedConfig.name.endsWith("!"))) {
                        CatusFrame.this.config_setName(CatusFrame.this.import_char_field.getText());
                    }
                    return;
                }
                final Config2 old = CatusFrame.this.selectedConfig;
                CatusFrame.this.config_new(false);
                if (!CatusFrame.this.importChar(UI.isAltKeyDown(ae)) || !CatusFrame.this.compareGear(old.getGear(), CatusFrame.this.mainProfile)) {
                    CatusFrame.this.config_delete();
                }
            }
        });
        (this.import_recent_combo = new UIComboBox<RecentArmory>()).setPrototypeDisplayValue("Recent");
        this.import_recent_combo.setWide(true);
        this.import_recent_combo.addActionListener(this.import_recent_combo_al);
        final UIPanel_GridBag topRow = new UIPanel_GridBag();
        topRow.add(this.import_recent_combo);
        topRow.add(this.region_combo);
        topRow.spacer(this.import_char_field);
        topRow.add(webBtn);
        topRow.add(this.import_char_btn);
        this.import_pane = new PrefPane("Import from Armory", topRow, false, true, null);
        (this.equip_batch_btn = UI.makeButton("Additional Gear...")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.manageAll();
            }
        });
        (this.equip_itemLevel_combo = new UIComboBox<NamedRunnable>()).setPrototypeDisplayValue("Scaling");
        this.equip_itemLevel_combo.setWide(true);
        UI.setComboText(this.equip_itemLevel_combo);
        this.equip_itemLevel_combo.addActionListener(new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                final NamedRunnable r = CatusFrame.this.equip_itemLevel_combo.getPick();
                CatusFrame.this.equip_itemLevel_combo.hidePopup();
                this.lock();
                UI.setComboText(CatusFrame.this.equip_itemLevel_combo);
                this.unlock();
                if (r == null) {
                    return;
                }
                try {
                    r.run();
                }
                catch (RuntimeException ex) {}
                CatusFrame.this._rebuildGear();
            }
        });
        this.equip_itemLevel_combo.addItem(new NamedRunnable("Max Upgrades") {
            @Override
            public void run() {
                for (final Profile.Slot x : CatusFrame.this.mainProfile.SLOTS) {
                    if (x.isUpgradable()) {
                        x.setItemLevelUpgradeMax(CatusFrame.this.asiaMode);
                    }
                }
                CatusFrame.this._rebuildGear();
            }
        });
        this.equip_itemLevel_combo.addItem(new NamedRunnable("Upgrade Report...") {
            @Override
            public void run() {
                final int pad = 48;
                int acc_ups = 0;
                int acc_max = 0;
                final StringBuilder sb = new StringBuilder();
                for (final Profile.Slot x : CatusFrame.this.mainProfile.SLOTS) {
                    if (x.item != null) {
                        if (x.type != SlotT.OFF_HAND || !CatusFrame.this.mainProfile.hasTwoHander()) {
                            if (sb.length() > 0) {
                                sb.append("\n");
                            }
                            Fmt.padRight(sb, x.getItemName(false), 48);
                            if (!x.isUpgradable()) {
                                sb.append(" 0 / 0");
                            }
                            else {
                                final int ups = x.getEffectiveUpgradeLevel(CatusFrame.this.asiaMode);
                                final int max = x.getItemLevelUpgradeMaxLevel(CatusFrame.this.asiaMode);
                                if (ups == -1) {
                                    sb.append(String.format(" ? /%2d", max));
                                }
                                else {
                                    acc_ups += ups;
                                    sb.append(String.format("%2d /%2d", ups, max));
                                }
                                acc_max += max;
                            }
                        }
                    }
                }
                sb.append("\n\n");
                String summary;
                if (acc_ups == acc_max) {
                    summary = "Fully Upgraded!";
                }
                else if (acc_max > 0) {
                    final int diff = acc_max - acc_ups;
                    if (diff == 1) {
                        summary = "1 Upgrade Needed!";
                    }
                    else {
                        summary = diff + " Upgrades Needed!";
                    }
                }
                else {
                    summary = "";
                }
                Fmt.padRight(sb, summary, 48);
                sb.append(String.format("%3d/%2d", acc_ups, acc_max));
                new DialogText(CatusFrame.this, "Upgrade Report", "OK").showText(sb.toString());
            }
        });
        this.equip_itemLevel_combo.addItem(new NamedRunnable("Challenge Mode (463)") {
            @Override
            public void run() {
                CatusFrame.this.challengeMode = true;
                CatusFrame.this.mainProfile.setScaledItemLevel(-463);
            }
        });
        this.equip_itemLevel_combo.addItem(new NamedRunnable("PvP: Season 13 (496)") {
            @Override
            public void run() {
                CatusFrame.this.mainProfile.setScaledItemLevel(-496);
            }
        });
        this.equip_itemLevel_combo.addItem(new NamedRunnable("PvP: Season 14 (522)") {
            @Override
            public void run() {
                CatusFrame.this.mainProfile.setScaledItemLevel(-522);
            }
        });
        this.equip_itemLevel_combo.addItem(new NamedRunnable("PvP: Season 15 (550)") {
            @Override
            public void run() {
                CatusFrame.this.mainProfile.setScaledItemLevel(-550);
            }
        });
        this.equip_itemLevel_combo.addItem(new NamedRunnable("Custom Item Level...") {
            @Override
            public void run() {
                final String input = JOptionPane.showInputDialog(CatusFrame.this, ">0 = Scaled (PTR)\n<0 = Down-scaled (Challenge Mode)", "Custom Item Level", 1);
                if (input == null) {
                    return;
                }
                final int level = Integer.parseInt(input);
                CatusFrame.this.mainProfile.setScaledItemLevel(level);
            }
        });
        this.equip_itemLevel_combo.addItem(new NamedRunnable("Challenge Mode Restrictions") {
            @Override
            public void run() {
                CatusFrame.this.challengeMode = true;
            }
        });
        (this.equip_autoFill_btn = UI.makeButton("Autofill")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                try {
                    if (CatusFrame.this.mainProfile.HEAD.getSocketAt(0) == GemT.META && CatusFrame.this.mainProfile.HEAD.getGemAt(0) == null) {
                        final int[] array;
                        final int[] ids = array = new int[] { 95346, 76884 };
                        final int length = array.length;
                        int i = 0;
                        while (i < length) {
                            final int id = array[i];
                            try {
                                CatusFrame.this.mainProfile.HEAD.setGemAt(0, (Gem)CatusFrame.this.api.loadItem(id));
                            }
                            catch (RuntimeException err2) {
                                ++i;
                                continue;
                            }
                            break;
                        }
                    }
                    final HashMap<GemT, Gem> map = new HashMap<GemT, Gem>();
                    map.put(GemT.RED, (Gem)CatusFrame.this.api.loadItem(76692));
                    map.put(GemT.WHITE, (Gem)CatusFrame.this.api.loadItem(76692));
                    map.put(GemT.YELLOW, (Gem)CatusFrame.this.api.loadItem(76670));
                    map.put(GemT.BLUE, (Gem)CatusFrame.this.api.loadItem(76680));
                    CatusFrame.this.mainProfile.setAllSockets(map, true);
                    this.tryEnchant(CatusFrame.this.mainProfile.HANDS, EnchantT.HANDS_MASTERY);
                    this.tryEnchant(CatusFrame.this.mainProfile.CHEST, EnchantT.CHEST_STATS);
                    this.tryEnchant(CatusFrame.this.mainProfile.FEET, EnchantT.FEET_AGI);
                    this.tryEnchant(CatusFrame.this.mainProfile.MH, EnchantT.WEAPON_DANCING_STEEL);
                    this.tryEnchant(CatusFrame.this.mainProfile.SHOULDER, CatusFrame.this.mainProfile.isProf(ProfT.INS) ? EnchantT.INS_SHOULDER_AGI : EnchantT.SHOULDER_AGI);
                    this.tryEnchant(CatusFrame.this.mainProfile.BACK, CatusFrame.this.mainProfile.isProf(ProfT.TAILOR) ? EnchantT.BACK_SWORDGUARD : EnchantT.BACK_CRIT);
                    final boolean lw = CatusFrame.this.mainProfile.isProf(ProfT.LW);
                    this.tryEnchant(CatusFrame.this.mainProfile.WRIST, lw ? EnchantT.LW_WRIST_AGI : EnchantT.WRIST_AGI);
                    this.tryEnchant(CatusFrame.this.mainProfile.LEGS, lw ? EnchantT.LW_LEGS_AGI : EnchantT.LEGS_AGI);
                    if (CatusFrame.this.mainProfile.isProf(ProfT.ENG)) {
                        CatusFrame.this.mainProfile.HANDS.setTinker(TinkerT.SYNAPSE_SPRINGS);
                        CatusFrame.this.mainProfile.WAIST.setTinker(TinkerT.FRAG_BELT);
                        CatusFrame.this.mainProfile.BACK.setTinker(TinkerT.GLIDER);
                        CatusFrame.this.mainProfile.setGemsOnce(Arrays.asList((Gem[])CatusFrame.this.gemsMap.get(GemT.COG)), true);
                    }
                    if (CatusFrame.this.mainProfile.isProf(ProfT.ENCH)) {
                        this.tryEnchant(CatusFrame.this.mainProfile.F1, EnchantT.RING_AGI);
                        this.tryEnchant(CatusFrame.this.mainProfile.F2, EnchantT.RING_AGI);
                    }
                    if (CatusFrame.this.mainProfile.MH.getSocketAt(0) == GemT.SHA) {
                        CatusFrame.this.mainProfile.MH.setGemAt(0, (Gem)CatusFrame.this.api.loadItem(89873));
                    }
                }
                catch (RuntimeException err) {
                    CatusFrame.this.showError("Unexpected Autofill Error", err);
                }
                CatusFrame.this._rebuildGear();
            }
            
            void tryEnchant(final Profile.Slot s, final EnchantT e) {
                if (s.item != null) {
                    s.setEnchant(e);
                }
            }
        });
        (this.equip_config_combo = new UIComboBox<Config2>()).setWide(true);
        this.equip_config_combo.setPrototypeDisplayValue("Copy");
        this.equip_config_combo.addActionListener(this.equip_config_combo_al);
        (this.equip_clear_combo = new UIComboBox<NamedRunnable>()).setWide(true);
        this.equip_clear_combo.setPrototypeDisplayValue("Clear");
        UI.setComboText(this.equip_clear_combo);
        this.equip_clear_combo.addActionListener(new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                final NamedRunnable r = CatusFrame.this.equip_clear_combo.getPick();
                CatusFrame.this.equip_clear_combo.hidePopup();
                this.lock();
                UI.setComboText(CatusFrame.this.equip_clear_combo);
                this.unlock();
                if (r != null) {
                    r.run();
                    CatusFrame.this._rebuildGear();
                }
            }
        });
        this.equip_clear_combo.addItem(new NamedRunnable("Clear Gear") {
            @Override
            public void run() {
                CatusFrame.this.mainProfile.clearSlots();
            }
        });
        this.equip_clear_combo.addItem(new NamedRunnable("Clear Gems") {
            @Override
            public void run() {
                CatusFrame.this.mainProfile.clearGems();
            }
        });
        this.equip_clear_combo.addItem(new NamedRunnable("Clear Reforges") {
            @Override
            public void run() {
                CatusFrame.this.mainProfile.clearReforges();
            }
        });
        this.equip_clear_combo.addItem(new NamedRunnable("Clear Enchants/Tinkers") {
            @Override
            public void run() {
                CatusFrame.this.mainProfile.clearEnchantsAndTinkers();
            }
        });
        this.equip_clear_combo.addItem(new NamedRunnable("Clear Upgrades") {
            @Override
            public void run() {
                for (final Profile.Slot x : CatusFrame.this.mainProfile.SLOTS) {
                    x.setItemLevelDelta(0);
                }
            }
        });
        (this.equip_ilvl_lbl = new JLabel()).setForeground(QualityT.EPIC.color);
        this.equip_ilvl_lbl.setFont(CatusFrame.boldFont);
        (this.equip_edit_btn = UI.makeButton("Edit")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final DialogText dt = new DialogText(CatusFrame.this, "Edit: CompactGear", "Save");
                String code = CompactGear.toString(CatusFrame.this.mainProfile, true, true);
                final Profile p = new Profile();
                final ArrayList<String> errors = new ArrayList<String>();
                while (true) {
                    code = dt.editText(code);
                    if (code == null) {
                        return;
                    }
                    p.race = CatusFrame.this.mainProfile.race;
                    p.spec = CatusFrame.this.mainProfile.spec;
                    p.profs = CatusFrame.this.mainProfile.profs;
                    if (CompactGear.fromString(CatusFrame.this.api, p, code, errors, 0)) {
                        CatusFrame.this.mainProfile.importProfile(p);
                        CatusFrame.this.mainProfile.validate();
                        CatusFrame.this.matchRaceAndProfs();
                        CatusFrame.this._rebuildGear();
                        return;
                    }
                    CatusFrame.this.showError_html("CompactGear Error", CompactGear.formatErrors_html(errors));
                }
            }
        });
        (this.equip_exportSimc_btn = UI.makeButton("Simc")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                new DialogText(CatusFrame.this, "Export: SimulationCraft", "OK").showText(Simc.exportProfile(CatusFrame.this.mainProfile));
            }
        });
        (this.equip_screenshot_btn = UI.makeButton("Screenshot")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.screenshot(CatusFrame.this.equipGrid, "Gear", (ae.getModifiers() & 0x8) > 0);
            }
        });
        
        (this.simc_import_btn = UI.makeButton("Amongus")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final DialogText amongus_text_window = new DialogText(CatusFrame.this, "test", "test");
            }
        });
        this.equipGrid = _makePanel();
        (this.scaled_label = new JLabel()).setIcon(this.api.getIconImage(InterfaceIcon.SCALED.slug, API.IconSize.SMALL));
        this.scaled_label.setFont(CatusFrame.boldFont);
        this.scaled_label.setHorizontalAlignment(0);
        this.scaled_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent me) {
                final Profile p = new Profile();
                p.importProfile(CatusFrame.this.mainProfile);
                p.setScaledItemLevel(0);
                CatusFrame.this.compareGear(p, CatusFrame.this.mainProfile);
            }
        });
        (this.scaled_panel = new UIPanel_GridBag()).setBackground(CatusFrame.LIGHT_YELLOW);
        this.scaled_panel.setBorder(CatusFrame.TOOLTIP_BORDER);
        this.scaled_panel.setOpaque(true);
        JButton clearBtn = new JButton();
        clearBtn.setPreferredSize(new Dimension(16, 16));
        clearBtn.setContentAreaFilled(false);
        clearBtn.setBorderPainted(false);
        clearBtn.setFocusable(false);
        clearBtn.setIcon(CatusFrame.redCrossImage);
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.mainProfile.setScaledItemLevel(0);
                CatusFrame.this._rebuildGear();
            }
        });
        this.scaled_panel.gap();
        this.scaled_panel.spacer(this.scaled_label);
        this.scaled_panel.add(clearBtn);
        (this.challengeMode_panel = new UIPanel_GridBag()).setBackground(CatusFrame.LIGHT_CYAN);
        this.challengeMode_panel.setBorder(CatusFrame.TOOLTIP_BORDER);
        this.challengeMode_panel.setOpaque(true);
        clearBtn = new JButton();
        clearBtn.setPreferredSize(new Dimension(16, 16));
        clearBtn.setContentAreaFilled(false);
        clearBtn.setBorderPainted(false);
        clearBtn.setFocusable(false);
        clearBtn.setIcon(CatusFrame.redCrossImage);
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.challengeMode = false;
                CatusFrame.this._rebuildGear();
            }
        });
        this.challengeMode_panel.gap();
        final JLabel label = new JLabel();
        label.setIcon(this.api.getIconImage(InterfaceIcon.CHALLENGE_MODE.slug, API.IconSize.SMALL));
        label.setText("<html><b>Challenge Mode Restrictions:</b> Set Bonuses and Legendary Effects Disabled</html>");
        label.setHorizontalAlignment(0);
        this.challengeMode_panel.spacer(label);
        this.challengeMode_panel.add(clearBtn);
        (this.warning_label = new JLabel()).setIcon(this.api.getIconImage(InterfaceIcon.WARNING.slug, API.IconSize.SMALL));
        this.warning_label.setFont(CatusFrame.boldFont);
        this.warning_label.setHorizontalAlignment(0);
        this.warning_label.setForeground(CatusFrame.DARK_RED);
        this.warning_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent me) {
                final ArrayList<Pair<String, String>> things = new ArrayList<Pair<String, String>>();
                for (final Profile.Slot x : CatusFrame.this.mainProfile.SLOTS) {
                    Label_0258: {
                        if (x.item == null) {
                            if (x == CatusFrame.this.mainProfile.OH && CatusFrame.this.mainProfile.hasTwoHander()) {
                                break Label_0258;
                            }
                            things.add(new Pair<String, String>(x.type.name, "Item"));
                        }
                        if (!x.hasEnchant() && x.isEnchantable()) {
                            things.add(new Pair<String, String>(x.type.name, "Enchant"));
                        }
                        for (int len = x.getSocketCount(), i = 0; i < len; ++i) {
                            if (x.getGemAt(i) == null) {
                                things.add(new Pair<String, String>(x.type.name, String.format("Gem#%d (%s)", i + 1, x.getSocketAt(i))));
                            }
                        }
                        if (!x.hasTinker() && x.isTinkerable()) {
                            things.add(new Pair<String, String>(x.type.name, "Tinker"));
                        }
                    }
                }
                if (CatusFrame.this.mainProfile.isProf(ProfT.JC)) {
                    for (int uc = 2 - CatusFrame.this.mainProfile.getUniqueCount("Jeweler's Facet"), j = 0; j < uc; ++j) {
                        things.add(new Pair<String, String>("Gem", "Jeweler's Facet"));
                    }
                }
                final StringBuilder sb = new StringBuilder();
                for (final Pair<String, String> pr : things) {
                    if (sb.length() > 0) {
                        sb.append("\n");
                    }
                    sb.append(String.format("%16s: %-16s", pr.car, pr.cdr));
                }
                final String body = sb.toString();
                sb.setLength(0);
                sb.append("Missing: ");
                Fmt.plural(sb, things.size(), "thing", "s");
                new DialogText(CatusFrame.this, sb.toString(), "OK").showText(body);
            }
        });
        final UIPanel_GridBag top = new UIPanel_GridBag();
        top.add(this.equip_ilvl_lbl);
        top.add(this.equip_edit_btn);
        top.add(this.equip_exportSimc_btn);
        top.add(this.equip_screenshot_btn);
        top.add(this.simc_import_btn);
        top.spacer();
        top.add(this.equip_autoFill_btn);
        top.add(this.equip_itemLevel_combo);
        top.add(this.equip_config_combo);
        top.add(this.equip_clear_combo);
        JPanel p = _makePanel();
        p.setLayout(new GridBagLayout());
        _addGridBagRow(p, top, false);
        _addGridBagRow(p, this.equipGrid, false);
        this.gearPane = new PrefPane("Equipment", p, false, true, null);
        JPanel p2 = _makePanel();
        p2.setLayout(new GridLayout(0, 2, 20, 0));
        this.gemGrid = _makePanel();
        this.enchantGrid = _makePanel();
        p2.add(this.gemGrid);
        p2.add(this.enchantGrid);
        this.gemEnchantTinkerPane = new PrefPane("Gems, Enchants, and Tinkers", p2, true, false, null);
        this.setBonus_grid = new UIPanel_GridBag();
        final JButton disable_btn = UI.makeButton("Disable Override");
        disable_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                CatusFrame.this.feature_setBonus_toggle.doClick(0);
            }
        });
        final JButton sync_btn = UI.makeButton("Copy Bonuses from Gear");
        sync_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                CatusFrame.this.mainProfile.syncSetBonuses(CatusFrame.this.cfg.setBonuses);
                for (final SetBonusHelper x : CatusFrame.this.bonusMap.values()) {
                    x.check.setSelected(CatusFrame.this.cfg.setBonuses.hasBonus(x.set.id, x.bonus.index));
                }
                CatusFrame.this._rebuildGear();
            }
        });
        final JButton clear_btn = UI.makeButton("Unselect All");
        clear_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                for (final SetBonusHelper x : CatusFrame.this.bonusMap.values()) {
                    x.check.setSelected(false);
                }
                CatusFrame.this._rebuildGear();
            }
        });
        (this.setBonus_botRow = new UIPanel_GridBag()).add(clear_btn);
        this.setBonus_botRow.add(sync_btn);
        this.setBonus_botRow.spacer();
        this.setBonus_botRow.add(disable_btn);
        this.setBonusPane = new PrefPane("Set Bonuses", this.setBonus_grid, false, false, null);
        final JButton swapBtn = UI.makeButton("Swap");
        swapBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.swapWeapon();
            }
        });
        this.altWeapGrid = _makePanel();
        final JPanel row = _makePanel();
        row.setLayout(new GridBagLayout());
        _addGridBagSpacer(row);
        row.add(swapBtn);
        JPanel p3 = _makePanel();
        p3.setLayout(new GridBagLayout());
        _addGridBagRow(p3, this.altWeapGrid, false);
        _addGridBagRow(p3, row, true);
        this.swapPane = new PrefPane("Weapon Swap", p3, false, false, null);
        this.statHP = new JLabel();
        this.statSta = new JLabel();
        this.statSpi = new JLabel();
        this.statStr = new JLabel();
        this.statAgi = new JLabel();
        this.statInt = new JLabel();
        this.statAP = new JLabel();
        this.statSP = new JLabel();
        this.statNatureSP = new JLabel();
        this.statMeleeCrit = new JLabel();
        this.statSpellCrit = new JLabel();
        this.statSpellHaste = new JLabel();
        this.statMeleeHaste = new JLabel();
        this.statHit = new JLabel();
        this.statExp = new JLabel();
        this.statMastery = new JLabel();
        this.statDPS = new JLabel();
        this.statSwing = new JLabel();
        this.statPvPPower = new JLabel();
        this.statPvPResil = new JLabel();
        this.statArmor = new JLabel();
        this.statDodge = new JLabel();
        this.statCritDmg = new JLabel();
        this.statSpeed = new JLabel();
        this.statPvPResil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent me) {
                final StringBuilder sb = new StringBuilder();
                sb.append("Rating      DR     Rel");
                final double base = FeralSim.pvpResilMod(0);
                for (int i = 0; i <= 40; ++i) {
                    final int res = 250 * i;
                    final double mod = FeralSim.pvpResilMod(res);
                    sb.append(String.format("\n%6d %6.2f%% %6.2f%%", res, 100.0 * (1.0 - mod), 100.0 * (base / mod - 1.0)));
                }
                new DialogText(CatusFrame.this, "PvP Resilience", "OK").showText(sb.toString());
            }
        });
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this._updateStats();
            }
        };
        (this.form_humanBtn = this.makeIconToggle("Human Form", InterfaceIcon.HUMAN_FORM)).setHorizontalAlignment(2);
        this.form_humanBtn.addActionListener(al);
        (this.form_catBtn = this.makeIconToggle("Cat Form", InterfaceIcon.STAMP_ROAR)).setHorizontalAlignment(2);
        this.form_catBtn.addActionListener(al);
        (this.form_bearBtn = this.makeIconToggle("Bear Form", InterfaceIcon.STAMP_ROAR_BEAR)).setHorizontalAlignment(2);
        this.form_bearBtn.addActionListener(al);
        (this.form_guardBtn = this.makeIconToggle("Guardian", InterfaceIcon.BEAR_FORM)).setHorizontalAlignment(2);
        this.form_guardBtn.addActionListener(al);
        final ButtonGroup group = new ButtonGroup();
        group.add(this.form_humanBtn);
        group.add(this.form_catBtn);
        group.add(this.form_bearBtn);
        group.add(this.form_guardBtn);
        UIPanel_GridBag p4 = new UIPanel_GridBag();
        UIPanel row2 = new UIPanel();
        row2.setLayout(new GridLayout(0, 4, 3, 0));
        row2.add(this.form_humanBtn);
        row2.add(this.form_catBtn);
        row2.add(this.form_bearBtn);
        row2.add(this.form_guardBtn);
        p4.row(row2, true, 0);
        final JPanel grid = _makePanel();
        grid.setLayout(new GridLayout(0, 6));
        final boolean a = false;
        grid.add(_makeStatLabel("Health", a));
        grid.add(this.statHP);
        grid.add(_makeStatLabel("Stamina", a));
        grid.add(this.statSta);
        grid.add(_makeStatLabel("Spirit", a));
        grid.add(this.statSpi);
        grid.add(_makeStatLabel("Strength", a));
        grid.add(this.statStr);
        grid.add(_makeStatLabel("Agility", a));
        grid.add(this.statAgi);
        grid.add(_makeStatLabel("Intellect", a));
        grid.add(this.statInt);
        grid.add(_makeStatLabel("AP", a));
        grid.add(this.statAP);
        grid.add(_makeStatLabel("SP", a));
        grid.add(this.statSP);
        grid.add(_makeStatLabel("SP Nature", a));
        grid.add(this.statNatureSP);
        grid.add(_makeStatLabel("Hit", a));
        grid.add(this.statHit);
        grid.add(_makeStatLabel("Crit", a));
        grid.add(this.statMeleeCrit);
        grid.add(_makeStatLabel("Haste", a));
        grid.add(this.statMeleeHaste);
        grid.add(_makeStatLabel("Exp", a));
        grid.add(this.statExp);
        grid.add(_makeStatLabel("Spell Crit", a));
        grid.add(this.statSpellCrit);
        grid.add(_makeStatLabel("Spell Haste", a));
        grid.add(this.statSpellHaste);
        grid.add(_makeStatLabel("Mastery", a));
        grid.add(this.statMastery);
        grid.add(_makeStatLabel("Crit Damage", a));
        grid.add(this.statCritDmg);
        grid.add(_makeStatLabel("Swing", a));
        grid.add(this.statSwing);
        grid.add(_makeStatLabel("PvP Power", a));
        grid.add(this.statPvPPower);
        grid.add(_makeStatLabel("PvP Resil", a));
        grid.add(this.statPvPResil);
        grid.add(_makeStatLabel("Armor", a));
        grid.add(this.statArmor);
        grid.add(_makeStatLabel("DPS", a));
        grid.add(this.statDPS);
        grid.add(_makeStatLabel("Movement", a));
        grid.add(this.statSpeed);
        grid.add(_makeStatLabel("Dodge", a));
        grid.add(this.statDodge);
        p4.row(grid, true, 4);
        final JButton edit_btn = UI.makeButton("Edit...");
        edit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final StringBuilder sb = new StringBuilder();
                for (final StatT x : StatT.STATS) {
                    final int delta = CatusFrame.this.customStats.get(x);
                    if (sb.length() > 0) {
                        sb.append("\n");
                    }
                    Fmt.padLeft(sb, x.name, 24);
                    sb.append(" = ");
                    if (delta != 0) {
                        sb.append(delta);
                    }
                }
                final String ret = new DialogText(CatusFrame.this, "Edit: Custom Stats", "Save").editText(sb.toString());
                if (ret == null) {
                    return;
                }
                CatusFrame.this.customStats.clear();
                for (String line : ret.split("\n")) {
                    int pos = line.indexOf(35);
                    if (pos >= 0) {
                        line = line.substring(0, pos);
                    }
                    line = line.trim();
                    Label_0320: {
                        if (!line.isEmpty()) {
                            pos = line.indexOf(61);
                            if (pos != -1) {
                                final String key = line.substring(0, pos).trim();
                                if (!key.isEmpty()) {
                                    final StatT stat = StatT.findBestMatch(StatT.STATS, key);
                                    if (stat != null) {
                                        final String val = line.substring(pos + 1).trim();
                                        if (!val.isEmpty()) {
                                            int delta2;
                                            try {
                                                delta2 = Integer.parseInt(val);
                                            }
                                            catch (NumberFormatException err) {
                                                break Label_0320;
                                            }
                                            CatusFrame.this.customStats.add(stat, delta2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                CatusFrame.this._rebuildGear();
                CatusFrame.this._updateCustomStats();
            }
        });
        (this.customStats_clear_btn = UI.makeButton("Clear")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.customStats.clear();
                CatusFrame.this._rebuildGear();
                CatusFrame.this._updateCustomStats();
            }
        });
        this.customStats_lbl = new JLabel();
        p4.row(new JSeparator(), true, 4);
        final UIPanel_GridBag row3 = new UIPanel_GridBag();
        row3.add(UI.L("Custom Stats:", CatusFrame.boldFont));
        row3.gap();
        row3.add(this.customStats_lbl);
        row3.gap();
        row3.add(edit_btn);
        row3.add(this.customStats_clear_btn);
        p4.row(row3, false, 4);
        this.statPane = new PrefPane("Cat Stats", p4, false, true, null);
        final UIPanel_GridBag rorTab = new UIPanel_GridBag();
        final UIPanel_GridBag maxTab = new UIPanel_GridBag();
        final UIPanel_GridBag oldTab = new UIPanel_GridBag();
        rorTab.pad(4);
        maxTab.pad(4);
        oldTab.pad(4);
        final JPanel rorTab_fake = _makePanel();
        final JPanel maxTab_fake = _makePanel();
        final JPanel oldTab_fake = _makePanel();
        (this.reforge_tabs = new JTabbedPane()).setFocusable(false);
        this.reforge_tabs.addTab("Rune of Re-Origination", rorTab);
        this.reforge_tabs.addTab("Maximize Stats", maxTab_fake);
        this.reforge_tabs.addTab("Constraints and Weights", oldTab_fake);
        this.reforge_tabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent ce) {
                final Component sel = CatusFrame.this.reforge_tabs.getSelectedComponent();
                if (sel == rorTab_fake) {
                    CatusFrame.this.reforge_tabs.setComponentAt(0, rorTab);
                    CatusFrame.this.reforge_tabs.setComponentAt(1, maxTab_fake);
                    CatusFrame.this.reforge_tabs.setComponentAt(2, oldTab_fake);
                }
                else if (sel == maxTab_fake) {
                    CatusFrame.this.reforge_tabs.setComponentAt(0, rorTab_fake);
                    CatusFrame.this.reforge_tabs.setComponentAt(1, maxTab);
                    CatusFrame.this.reforge_tabs.setComponentAt(2, oldTab_fake);
                }
                else if (sel == oldTab_fake) {
                    CatusFrame.this.reforge_tabs.setComponentAt(0, rorTab_fake);
                    CatusFrame.this.reforge_tabs.setComponentAt(1, maxTab_fake);
                    CatusFrame.this.reforge_tabs.setComponentAt(2, oldTab);
                }
            }
        });
        final JPanel slotRow = _makePanel();
        final int len = Profile.$.length;
        slotRow.setLayout(new GridLayout(0, (len + 1) / 2));
        this.reforge_toggles = new ReforgeToggle[len];
        for (int i = 0; i < len; ++i) {
            final ReforgeToggle toggle = new ReforgeToggle(Profile.$[i]);
            slotRow.add(this.reforge_toggles[i] = toggle);
        }
        (this.reforge_map_btn = UI.makeButton("Reforge Map")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                class X
                {
                    ReforgePair r;
                    int src;
                    int dst;
                }
                final HashMap<ReforgePair, X> map = new HashMap<ReforgePair, X>();
                for (final Profile.Slot x : CatusFrame.this.mainProfile.SLOTS) {
                    if (!x.isEmpty()) {
                        if (x.reforge != null) {
                            X temp = map.get(x.reforge);
                            if (temp == null) {
                                temp = new X();
                                temp.r = x.reforge;
                                map.put(x.reforge, temp);
                            }
                            final X x3 = temp;
                            x3.src += x.sumStat_reforgedItem(x.reforge.src);
                            final X x4 = temp;
                            x4.dst += x.sumStat_reforgedItem(x.reforge.dst);
                        }
                    }
                }
                final StringBuilder sb = new StringBuilder(512);
                final ArrayList<X> buf = new ArrayList<X>();
                final int indent = 16;
                final String sep = " => ";
                final String tail = " ";
                final Comparator<X> cmp = new Comparator<X>() {
                    @Override
                    public int compare(final X a, final X b) {
                        return b.dst - a.dst;
                    }
                };
                for (final StatT stat : StatT.REFORGE) {
                    buf.clear();
                    int sum = 0;
                    for (final X x2 : map.values()) {
                        if (x2.r.src == stat) {
                            buf.add(x2);
                            sum += x2.src;
                        }
                    }
                    if (!buf.isEmpty()) {
                        Collections.sort(buf, cmp);
                        if (sb.length() > 0) {
                            sb.append("\n\n");
                        }
                        Fmt.padLeft(sb, stat.formatValue(sum), 16);
                        sb.append(" => ");
                        final Iterator<X> iter = buf.iterator();
                        X temp2 = iter.next();
                        sb.append(temp2.r.dst.formatValue(temp2.dst));
                        sb.append(" ");
                        while (iter.hasNext()) {
                            temp2 = iter.next();
                            sb.append("\n");
                            Fmt.padRight(sb, "", 16);
                            sb.append(" => ");
                            sb.append(temp2.r.dst.formatValue(temp2.dst));
                            sb.append(" ");
                        }
                    }
                }
                new DialogText(CatusFrame.this, "Reforge: Map", "OK").showText(sb.toString());
            }
        });
        final JButton reforge_reset_btn = UI.makeButton("Reset to Defaults");
        reforge_reset_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                PrefHelp.silentClear(CatusFrame.PREFS.node("ReforgeSlots"));
                PrefHelp.silentClear(CatusFrame.PREFS.node("ReforgeRune"));
                PrefHelp.silentClear(CatusFrame.PREFS.node("ReforgeMax"));
                PrefHelp.silentClear(CatusFrame.PREFS.node("ReforgeOld"));
                CatusFrame.this.loadPrefs_reforgers();
            }
        });
        (this.reforge_bounds_btn = UI.makeButton("Find Bounds")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final ReforgerBounds rb = new ReforgerBounds(CatusFrame.REFORGE_UNIVERSE);
                final Set<SlotT> slotSet = CatusFrame.this.getSlotSet();
                final Profile p = new Profile();
                p.importProfile(CatusFrame.this.mainProfile);
                rb.update(p, slotSet);
                final StringBuilder sb = new StringBuilder(512);
                this.f(sb, rb);
                p.clearGems();
                p.clearEnchantsAndTinkers();
                rb.update(p, slotSet);
                sb.append("\n\nw/o Gems and Enchants:\n");
                this.f(sb, rb);
                new DialogText(CatusFrame.this, "Reforge: Bounds", "OK").showText(sb.toString());
            }
            
            void f(final StringBuilder sb, final ReforgerBounds rb) {
                for (int i = 0; i < CatusFrame.REFORGE_UNIVERSE.length; ++i) {
                    if (i > 0) {
                        sb.append('\n');
                    }
                    Fmt.padLeft(sb, CatusFrame.REFORGE_UNIVERSE[i].abbr, 10);
                    Fmt.padLeft(sb, Integer.toString(rb.statMin[i]), 10);
                    Fmt.padLeft(sb, Integer.toString(rb.statMax[i]), 10);
                }
            }
        });
        (this.reforgeRune_similar_btn = UI.makeButton("Minimize Cost")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final Profile armory = CatusFrame.this.importArmory(false);
                if (armory == null) {
                    return;
                }
                final Profile copy = CatusFrame.this.reforger_safeCopy();
                final int mr = copy.gearAndExtraStat(StatT.MASTERY);
                final int hr = copy.gearAndExtraStat(StatT.HASTE);
                final int cr = copy.gearAndExtraStat(StatT.CRIT);
                if (hr >= mr || cr >= mr) {
                    CatusFrame.this.showError("Not Mastery Reforged", "The current profile is not 1:1:1 reforged.");
                    return;
                }
                final Reforger111 r = new Reforger111();
                r.hitTarget = copy.gearAndExtraStat(StatT.HIT);
                r.expTarget = copy.gearAndExtraStat(StatT.EXP);
                r.slotSet = CatusFrame.this.getSlotSet();
                final int tol = 3;
                r.searchRange = tol;
                r.masteryOverflow = tol;
                r.hasteGap = Math.max(mr - hr - 1 - tol, 0);
                r.critGap = Math.max(mr - cr - 1 - tol, 0);
                r.removeDups = false;
                r.hitSearchMode = Reforger111.SearchMode.AT_LEAST;
                r.expSearchMode = Reforger111.SearchMode.AT_LEAST;
                r.critGreater = CatusFrame.this.reforgeRune_critGreater_check.isSelected();
                r.enchantBack = CatusFrame.this.reforgeRune_enchantBack_check.isSelected();
                r.enchantHands = CatusFrame.this.reforgeRune_enchantHands_check.isSelected();
                r.changeGems = CatusFrame.this.reforgeRune_changeGems_check.isSelected();
                r.keepGemColors = CatusFrame.this.reforgeRune_keepGemColors_check.isSelected();
                r.canBreakBonuses = CatusFrame.this.reforgeRune_breakBonus_check.isSelected();
                r.respectNullGems = CatusFrame.this.reforgeRune_leaveEmpty_check.isSelected();
                r.feral_gems = CatusFrame.this.reforgeRune_gem_combo.getPick();
                r.feral_sta = CatusFrame.this.reforgeRune_useSta_check.isSelected();
                r.feral_hit = CatusFrame.this.reforgeRune_useHit_check.isSelected();
                final DialogProg pd = new DialogProg(CatusFrame.this, "Minimizing Cost");
                r.progBar = pd.proxy;
                try {
                    pd.execute(new Runnable() {
                        @Override
                        public void run() {
                            r.reforge(CatusFrame.this.api, copy, armory);
                        }
                    });
                }
                catch (RuntimeException err) {
                    if (!r.abort.get()) {
                        CatusFrame.this.showError("Reforge Error", err);
                    }
                    return;
                }
                CatusFrame.this.reforge_results_combo_al.lock();
                CatusFrame.this.reforgeRune_results_combo.removeAllItems();
                for (final ProfilePerm x : r.results) {
                    CatusFrame.this.reforgeRune_results_combo.addItem(x);
                }
                CatusFrame.this.reforge_results_combo_al.unlock();
                CatusFrame.this.reforgeRune_results_combo.setSelectedItem(r.results[r.resultBestIndex]);
                CatusFrame.this.reforgeRune_resultsRow.setVisible(true);
            }
        });
        (this.reforgeRune_reforge_btn = UI.makeButton("Reforge")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final Reforger111 r = CatusFrame.this.reforger111_extract();
                if (r == null) {
                    return;
                }
                final Profile copy = CatusFrame.this.reforger_safeCopy();
                final DialogProg pd = new DialogProg(CatusFrame.this, "Reforging");
                r.progBar = pd.proxy;
                pd.setAbortOnClose(r.abort);
                try {
                    pd.execute(new Runnable() {
                        @Override
                        public void run() {
                            r.reforge(CatusFrame.this.api, copy, null);
                        }
                    });
                }
                catch (RuntimeException err) {
                    if (!r.abort.get()) {
                        CatusFrame.this.showError("Reforge Error", err);
                        err.printStackTrace();
                    }
                    return;
                }
                CatusFrame.this.reforge_results_combo_al.lock();
                CatusFrame.this.reforgeRune_results_combo.removeAllItems();
                for (final ProfilePerm x : r.results) {
                    CatusFrame.this.reforgeRune_results_combo.addItem(x);
                }
                CatusFrame.this.reforge_results_combo_al.unlock();
                CatusFrame.this.reforgeRune_results_combo.setSelectedItem(r.results[1]);
                CatusFrame.this.reforgeRune_resultsRow.setVisible(true);
            }
        });
        (this.reforgeOld_btn = UI.makeButton("Reforge")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.reforge();
            }
        });
        (this.reforge_clearGems_btn = UI.makeButton("Clear Gems")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.mainProfile.clearGems();
                CatusFrame.this._rebuildGear();
            }
        });
        (this.reforge_clearReforges_btn = UI.makeButton("Clear Reforges")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.mainProfile.clearReforges();
                CatusFrame.this._rebuildGear();
            }
        });
        (this.reforge_shoppingList_btn = UI.makeButton("Shopping List")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final Profile p = CatusFrame.this.importArmory(false);
                if (p == null) {
                    return;
                }
                final CompareGear cg = new CompareGear();
                if (cg.shoppingList(p, CatusFrame.this.reforger_safeCopy())) {
                    new DialogText(CatusFrame.this, cg.title, "OK").showText(cg.message);
                }
                else {
                    CatusFrame.this.showError(cg.title, cg.message);
                }
            }
        });
        (this.reforge_export_btn = UI.makeButton("Reforgerade")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                if (UI.isAltKeyDown(ae)) {
                    Utils.openURL("http://www.curse.com/addons/wow/reforgerade");
                }
                else {
                    new DialogText(CatusFrame.this, "Export: Reforgerade", "OK").showText(CatusFrame.this.mainProfile.toReforgerade());
                }
            }
        });
        (this.reforge_exportAMR_btn = UI.makeButton("AskMrRobot")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                if (UI.isAltKeyDown(ae)) {
                    Utils.openURL("http://blog.askmrrobot.com/2013/01/how-to-install-use-the-amr-mod/");
                }
                else {
                    final Profile p = CatusFrame.this.prepareExport("AskMrRobot");
                    if (p == null) {
                        return;
                    }
                    p.talents = API.getTalentStr(CatusFrame.this.talentBytes, 1);
                    p.glyphs = "";
                    p.copySlots(CatusFrame.this.mainProfile);
                    try {
                        new DialogText(CatusFrame.this, "Export: AskMrRobot", "OK").showWrappedText(p.toAskMrRobot());
                    }
                    catch (RuntimeException err) {
                        CatusFrame.this.showError("Error: AskMrRobot", err.getMessage());
                    }
                }
            }
        });
        (this.reforge_stats_btn = UI.makeButton("Copy Stats")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final StringBuilder sb = new StringBuilder();
                for (final StatT x : CatusFrame.REFORGE_UNIVERSE) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(x.abbr);
                    sb.append(": ");
                    sb.append(CatusFrame.this.mainProfile.gearAndExtraStat(x));
                }
                sb.append('\n');
                for (final StatT x : CatusFrame.REFORGE_UNIVERSE) {
                    sb.append('\n');
                    Fmt.padLeft(sb, x.abbr, 12);
                    sb.append(": ");
                    sb.append(CatusFrame.this.mainProfile.gearAndExtraStat(x));
                }
                new DialogText(CatusFrame.this, "Export: Stats", "OK").showText(sb.toString());
            }
        });
        final UIPanel_GridBag topRow2 = new UIPanel_GridBag();
        topRow2.setLayout(new GridBagLayout());
        topRow2.add(this.reforge_export_btn);
        topRow2.add(this.reforge_shoppingList_btn);
        topRow2.add(this.reforge_exportAMR_btn);
        topRow2.spacer();
        topRow2.add(reforge_reset_btn);
        final UIPanel_GridBag botRow = new UIPanel_GridBag();
        botRow.add(this.reforge_stats_btn);
        botRow.add(this.reforge_bounds_btn);
        botRow.add(this.reforge_map_btn);
        botRow.spacer();
        botRow.add(this.reforge_clearGems_btn);
        botRow.add(this.reforge_clearReforges_btn);
        final JPanel statRow = _makePanel();
        statRow.setLayout(new GridBagLayout());
        _addGridBagSpacer(statRow);
        final JPanel statRow_inner = _makePanel();
        statRow_inner.setLayout(new GridLayout());
        statRow_inner.add(_makeStatLabel("Hit: ", true));
        statRow_inner.add(this.reforge_statHit = new JLabel());
        statRow_inner.add(_makeStatLabel("Exp: ", true));
        statRow_inner.add(this.reforge_statExp = new JLabel());
        statRow_inner.add(_makeStatLabel("Mastery: ", true));
        statRow_inner.add(this.reforge_statMastery = new JLabel());
        statRow_inner.add(_makeStatLabel("Haste: ", true));
        statRow_inner.add(this.reforge_statHaste = new JLabel());
        statRow_inner.add(_makeStatLabel("Crit: ", true));
        statRow_inner.add(this.reforge_statCrit = new JLabel());
        statRow.add(statRow_inner);
        _addGridBagSpacer(statRow);
        final JLabel text = new JLabel("Warning: this reforger is deprecated.");
        text.setFont(CatusFrame.boldFont);
        text.setForeground(CatusFrame.DARK_RED);
        oldTab.row(text, false, 0);
        final JPanel grid2 = _makePanel();
        grid2.setLayout(new GridLayout(0, 5, 3, 1));
        grid2.add(Box.createGlue());
        grid2.add(_makeBottomCenterLabel("Lower Bound"));
        grid2.add(_makeBottomCenterLabel("Upper Bound"));
        grid2.add(_makeBottomCenterLabel("Weight"));
        grid2.add(Box.createGlue());
        this.reforgeOld_constraints = new ReforgeConstraint[CatusFrame.REFORGE_UNIVERSE.length];
        for (int j = 0; j < CatusFrame.REFORGE_UNIVERSE.length; ++j) {
            final ReforgeConstraint temp = new ReforgeConstraint(CatusFrame.REFORGE_UNIVERSE[j]);
            final JLabel name = new JLabel(temp.stat.abbr);
            name.setFont(CatusFrame.boldFont);
            name.setHorizontalAlignment(4);
            grid2.add(name);
            grid2.add(temp.min_field);
            grid2.add(temp.max_field);
            grid2.add(temp.weight_field);
            grid2.add(temp.prevent_check);
            this.reforgeOld_constraints[j] = temp;
        }
        oldTab.row(grid2, true, 4);
        final UIPanel_GridBag row4 = new UIPanel_GridBag();
        row4.spacer();
        row4.add(this.reforgeOld_btn);
        oldTab.row(row4, true, 4);
        (this.reforgeRune_stateRow = _makePanel()).setLayout(new GridBagLayout());
        this.reforgeRune_stateText = new JLabel();
        final JLabel runeText = new JLabel("Rune of Re-Origination detected!");
        runeText.setIcon(this.api.getIconImage(InterfaceIcon.RUNE.slug, API.IconSize.SMALL));
        runeText.setFont(CatusFrame.boldFont);
        this.reforgeRune_stateRow.add(runeText);
        this.reforgeRune_stateRow.add(this.reforgeRune_stateText);
        rorTab.row(this.reforgeRune_stateRow, true, 4);
        this.reforgeRune_exp_field = new JTextField();
        this.reforgeRune_hit_field = new JTextField();
        this.reforgeRune_exp_combo = new UIComboBox<Reforger111.SearchMode>(Reforger111.SearchMode.values());
        this.reforgeRune_hit_combo = new UIComboBox<Reforger111.SearchMode>(Reforger111.SearchMode.values());
        this.reforgeRune_range_field = new JTextField();
        this.reforgeRune_overflow_field = new JTextField();
        this.reforgeRune_hasteGap_field = new JTextField();
        this.reforgeRune_critGap_field = new JTextField();
        this.reforgeRune_critGreater_check = UI.makeCheck("Crit > Haste");
        this.reforgeRune_hit_field.setName("Hit");
        this.reforgeRune_exp_field.setName("Expertise");
        this.reforgeRune_range_field.setName("Range");
        this.reforgeRune_overflow_field.setName("Mastery Overflow");
        this.reforgeRune_hasteGap_field.setName("Haste Gap");
        this.reforgeRune_critGap_field.setName("Crit Gap");
        this.reforgeRune_hit_field.setHorizontalAlignment(0);
        this.reforgeRune_exp_field.setHorizontalAlignment(0);
        this.reforgeRune_range_field.setHorizontalAlignment(0);
        this.reforgeRune_overflow_field.setHorizontalAlignment(0);
        this.reforgeRune_hasteGap_field.setHorizontalAlignment(0);
        this.reforgeRune_critGap_field.setHorizontalAlignment(0);
        this.reforgeRune_hit_field.setPreferredSize(numberFieldSize);
        this.reforgeRune_exp_field.setPreferredSize(numberFieldSize);
        this.reforgeRune_range_field.setPreferredSize(numberFieldSize);
        this.reforgeRune_overflow_field.setPreferredSize(numberFieldSize);
        this.reforgeRune_hasteGap_field.setPreferredSize(numberFieldSize);
        this.reforgeRune_critGap_field.setPreferredSize(numberFieldSize);
        (this.reforgeRune_compare_btn = UI.makeButton("Quick Compare")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!CatusFrame.this.reforgeRune_stateRow.isVisible()) {
                    CatusFrame.this.showError(CatusFrame.this.compare_pane.name, "Requires Rune of Re-Origination.");
                    return;
                }
                final Reforger111.FeralGems gems = CatusFrame.this.reforgeRune_gem_combo.getPick();
                CatusFrame.this.compare_sim(new NamedTweaker("Current") {
                    @Override
                    public void run() {
                    }
                }, new NamedTweaker(gems.name) {
                    @Override
                    public void run() {
                        CatusFrame.this.reforger111_gems(gems);
                    }
                });
            }
        });
        UIPanel_GridBag row5 = new UIPanel_GridBag();
        row5.add(UI.makeBold(new JLabel(this.reforgeRune_hit_field.getName() + ": ")));
        row5.add(this.reforgeRune_hit_combo);
        row5.add(this.reforgeRune_hit_field);
        row5.gap();
        row5.add(UI.makeBold(new JLabel(this.reforgeRune_exp_field.getName() + ": ")));
        row5.add(this.reforgeRune_exp_combo);
        row5.add(this.reforgeRune_exp_field);
        row5.gap();
        row5.add(UI.makeBold(new JLabel(this.reforgeRune_range_field.getName() + ": ")));
        row5.add(this.reforgeRune_range_field);
        rorTab.row(row5, false, 4);
        row5 = new UIPanel_GridBag();
        row5.add(new JLabel(this.reforgeRune_overflow_field.getName() + ": "));
        row5.add(this.reforgeRune_overflow_field);
        row5.gap();
        row5.add(new JLabel(this.reforgeRune_hasteGap_field.getName() + ": "));
        row5.add(this.reforgeRune_hasteGap_field);
        row5.gap();
        row5.add(new JLabel(this.reforgeRune_critGap_field.getName() + ": "));
        row5.add(this.reforgeRune_critGap_field);
        row5.gap();
        row5.add(this.reforgeRune_critGreater_check);
        rorTab.row(row5, false, 4);
        this.reforgeRune_enchantHands_check = UI.makeCheck("Hands");
        this.reforgeRune_enchantBack_check = UI.makeCheck("Back");
        this.reforgeRune_changeGems_check = UI.makeCheck("Change Gems:");
        this.reforgeRune_keepGemColors_check = UI.makeCheck("Keep Colors");
        this.reforgeRune_leaveEmpty_check = UI.makeCheck("Leave Empty");
        this.reforgeRune_breakBonus_check = UI.makeCheck("Break Bonuses");
        this.reforgeRune_gem_combo = new UIComboBox<Reforger111.FeralGems>(Reforger111.FeralGems.values());
        this.reforgeRune_useSta_check = UI.makeCheck("Stamina");
        this.reforgeRune_useHit_check = UI.makeCheck("Pure Hit");
        UI.onChange_enable(this.reforgeRune_changeGems_check, this.reforgeRune_keepGemColors_check, this.reforgeRune_breakBonus_check, this.reforgeRune_leaveEmpty_check, this.reforgeRune_gem_combo, this.reforgeRune_useSta_check, this.reforgeRune_useHit_check);
        row5 = new UIPanel_GridBag();
        row5.add(UI.makeBold(this.reforgeRune_changeGems_check));
        row5.add(this.reforgeRune_breakBonus_check);
        row5.add(this.reforgeRune_keepGemColors_check);
        row5.add(this.reforgeRune_leaveEmpty_check);
        rorTab.row(row5, false, 4);
        row5 = new UIPanel_GridBag();
        row5.add(UI.makeBold(new JLabel("Gem Colors: ")));
        row5.add(this.reforgeRune_gem_combo);
        row5.gap();
        row5.add(UI.L("Optional Gems: ", CatusFrame.boldFont));
        row5.add(this.reforgeRune_useSta_check);
        row5.add(this.reforgeRune_useHit_check);
        rorTab.row(row5, false, 4);
        row5 = new UIPanel_GridBag();
        row5.add(UI.makeBold(new JLabel("Enchants: ")));
        row5.add(this.reforgeRune_enchantHands_check);
        row5.add(this.reforgeRune_enchantBack_check);
        rorTab.row(row5, false, 4);
        row5 = new UIPanel_GridBag();
        row5.add(this.reforgeRune_compare_btn);
        row5.spacer();
        row5.add(this.reforgeRune_similar_btn);
        row5.add(this.reforgeRune_reforge_btn);
        rorTab.row(row5, true, 4);
        (this.reforgeRune_results_combo = new JComboBox()).setPrototypeDisplayValue("");
        this.reforgeRune_results_combo.addActionListener(this.reforge_results_combo_al);
        (this.reforgeRune_resultsRow = new UIPanel_GridBag()).row(this.reforgeRune_results_combo, true, 0);
        rorTab.row(this.reforgeRune_resultsRow, true, 4);
        this.reforgeMax_reforge_btn = UI.makeButton("Reforge");
        this.reforgeMax_hit_field = new JTextField();
        this.reforgeMax_exp_field = new JTextField();
        this.reforgeMax_range_field = new JTextField();
        this.reforgeMax_stats = new StatT[] { StatT.AGI, StatT.STR, StatT.AP, StatT.MASTERY, StatT.HASTE, StatT.CRIT, StatT.PVP_POW, StatT.PVP_RES };
        this.reforgeMax_fields = new JTextField[this.reforgeMax_stats.length];
        for (int j = 0; j < this.reforgeMax_stats.length; ++j) {
            final JTextField f = new JTextField();
            f.setName(this.reforgeMax_stats[j].abbr);
            f.setHorizontalAlignment(0);
            this.reforgeMax_fields[j] = f;
        }
        (this.reforgeMax_bear_check = UI.makeCheck("Enforce Crit Cap: ")).setFont(CatusFrame.boldFont);
        (this.reforgeMax_bearMax_field = new JTextField()).setName("Guardian Crit Cap");
        this.reforgeMax_bearMax_field.setPreferredSize(numberFieldSize);
        this.reforgeMax_bearMax_field.setHorizontalAlignment(0);
        (this.reforgeMax_bear_row = new UIPanel_GridBag()).add(new JLabel(this.api.getIconImage(InterfaceIcon.BEAR_FORM.slug, API.IconSize.SMALL)));
        this.reforgeMax_bear_row.add(this.reforgeMax_bear_check);
        this.reforgeMax_bear_row.add(this.reforgeMax_bearMax_field);
        this.reforgeMax_hit_field.setName("Hit");
        this.reforgeMax_exp_field.setName("Expertise");
        this.reforgeMax_range_field.setName("Range");
        this.reforgeMax_hit_field.setHorizontalAlignment(0);
        this.reforgeMax_exp_field.setHorizontalAlignment(0);
        this.reforgeMax_range_field.setHorizontalAlignment(0);
        this.reforgeMax_hit_field.setPreferredSize(numberFieldSize);
        this.reforgeMax_exp_field.setPreferredSize(numberFieldSize);
        this.reforgeMax_range_field.setPreferredSize(numberFieldSize);
        (this.reforgeMax_depth_field = new JTextField()).setName("Depth");
        this.reforgeMax_depth_field.setHorizontalAlignment(0);
        this.reforgeMax_depth_field.setPreferredSize(numberFieldSize);
        this.reforgeMax_enchantHands_check = UI.makeCheck("Hands");
        this.reforgeMax_enchantBack_check = UI.makeCheck("Back");
        this.reforgeMax_enchantFeet_check = UI.makeCheck("Feet");
        this.reforgeMax_changeGems_check = UI.makeBold(UI.makeCheck("Change Gems: "));
        this.reforgeMax_breakBonus_check = UI.makeCheck("Break Bonuses");
        this.reforgeMax_leaveEmpty_check = UI.makeCheck("Leave Empty");
        (this.reforgeMax_results_combo = new JComboBox()).setPrototypeDisplayValue("");
        this.reforgeMax_results_combo.addActionListener(this.reforge_results_combo_al);
        final JButton importBtn = UI.makeButton("Import Weights");
        importBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final String tit = "Import: Stat Weights";
                final String text = "# Accepts one entry per line\n#  Format: 'Stat: Weight'\n# Example: 'Agility: 3'\n# Any text following (#) is ignored\n";
                final double[] weights = new double[StatT.STATS.length];
                while (true) {
                    final String code = new DialogText(CatusFrame.this, tit, "Import").editText(text);
                    if (code == null) {
                        return;
                    }
                    try {
                        CatusFrame.parseStatWeights(code, weights);
                    }
                    catch (RuntimeException err) {
                        CatusFrame.this.showError(tit, err);
                        continue;
                    }
                    for (int i = 0; i < CatusFrame.this.reforgeMax_stats.length; ++i) {
                        final StatT stat = CatusFrame.this.reforgeMax_stats[i];
                        CatusFrame.this.reforgeMax_fields[i].setText(String.format("%.2f", weights[stat.index]));
                    }
                }
            }
        });
        final JButton resetBtn = UI.makeButton("Default Weights");
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                for (int i = 0; i < CatusFrame.this.reforgeMax_stats.length; ++i) {
                    final StatT stat = CatusFrame.this.reforgeMax_stats[i];
                    CatusFrame.this.reforgeMax_fields[i].setText(String.format("%.2f", CatusFrame.WEIGHTS[stat.index]));
                }
            }
        });
        this.reforgeMax_reforge_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final Profile armory = CatusFrame.this.importArmory(false);
                final ReforgerMax r = CatusFrame.this.reforgerMax_extract();
                final Profile copy = CatusFrame.this.reforger_safeCopy();
                final DialogProg pd = new DialogProg(CatusFrame.this, "Reforging");
                r.progBar = pd.proxy;
                pd.setAbortOnClose(r.abort);
                try {
                    pd.execute(new Runnable() {
                        @Override
                        public void run() {
                            r.reforge(CatusFrame.this.api, copy, armory);
                        }
                    });
                }
                catch (RuntimeException err) {
                    if (!r.abort.get()) {
                        CatusFrame.this.showError("Reforge Error", err);
                        err.printStackTrace();
                    }
                    return;
                }
                CatusFrame.this.reforgerMax_select(r);
            }
        });
        UI.onChange(this.reforgeMax_changeGems_check, new Handler<JCheckBox>() {
            @Override
            public void handle(final JCheckBox x) {
                final boolean b = x.isSelected();
                CatusFrame.this.reforgeMax_breakBonus_check.setEnabled(b);
                CatusFrame.this.reforgeMax_leaveEmpty_check.setEnabled(b);
            }
        });
        UIPanel_GridBag row6 = new UIPanel_GridBag();
        row6.add(UI.makeBold(new JLabel(this.reforgeMax_hit_field.getName() + ": ")));
        row6.add(this.reforgeMax_hit_field);
        row6.gap();
        row6.add(UI.makeBold(new JLabel(this.reforgeMax_exp_field.getName() + ": ")));
        row6.add(this.reforgeMax_exp_field);
        row6.gap();
        row6.add(UI.makeBold(new JLabel(this.reforgeMax_range_field.getName() + ": ")));
        row6.add(this.reforgeMax_range_field);
        row6.gap();
        row6.add(UI.makeBold(new JLabel(this.reforgeMax_depth_field.getName() + ": ")));
        row6.add(this.reforgeMax_depth_field);
        maxTab.row(row6, false, 0);
        row6 = new UIPanel_GridBag();
        row6.add(UI.makeBold(new JLabel("Stat Weights:")));
        row6.gap();
        final UIPanel grid3 = new UIPanel();
        grid3.setLayout(new GridLayout(0, 6));
        for (final JTextField f2 : this.reforgeMax_fields) {
            grid3.add(new JLabel(f2.getName() + ": ", 4));
            grid3.add(f2);
        }
        row6.add(grid3);
        maxTab.row(row6, false, 4);
        maxTab.row(this.reforgeMax_bear_row, false, 4);
        row6 = new UIPanel_GridBag();
        row6.add(this.reforgeMax_changeGems_check);
        row6.add(this.reforgeMax_breakBonus_check);
        row6.add(this.reforgeMax_leaveEmpty_check);
        maxTab.row(row6, false, 4);
        row6 = new UIPanel_GridBag();
        row6.add(UI.makeBold(new JLabel("Enchants: ")));
        row6.add(this.reforgeMax_enchantHands_check);
        row6.add(this.reforgeMax_enchantBack_check);
        row6.add(this.reforgeMax_enchantFeet_check);
        maxTab.row(row6, false, 4);
        row6 = new UIPanel_GridBag();
        row6.add(importBtn);
        row6.add(resetBtn);
        row6.spacer();
        row6.add(this.reforgeMax_reforge_btn);
        maxTab.row(row6, true, 4);
        (this.reforgeMax_results_panel = new UIPanel_GridBag()).row(this.reforgeMax_results_combo, true, 0);
        maxTab.row(this.reforgeMax_results_panel, true, 4);
        final UIPanel_GridBag p5 = new UIPanel_GridBag();
        p5.row(topRow2, true, 0);
        p5.row(slotRow, true, 4);
        p5.row(botRow, true, 4);
        p5.row(statRow, true, 4);
        p5.row(this.reforge_tabs, true, 4);
        this.reforgePane = new PrefPane("Reforge", p5, true, false, CatusFrame.DARK_GRAY_INFO);
        this.regem_profRow = new UIPanel_GridBag();
        this.regem_profCombos = new JComboBox[2];
        final UIPanel grid4 = new UIPanel();
        grid4.setLayout(new GridLayout(0, 2, 5, 0));
        for (int k = 0; k < 2; ++k) {
            final JComboBox combo = UI.makeCombo();
            combo.setPrototypeDisplayValue("");
            combo.setFont(CatusFrame.tinyFont);
            if (k > 0) {
                combo.addItem("Same");
            }
            for (final Gem x4 : this.jcGems) {
                combo.addItem(x4);
            }
            grid4.add(combo);
            this.regem_profCombos[k] = combo;
        }
        this.regem_profRow.add(UI.makeBold(new JLabel(" Jeweler Facets: ")));
        this.regem_profRow.spacer(grid4);
        final JPanel colorRow = _makePanel();
        colorRow.setLayout(new GridBagLayout());
        final GridBagConstraints gbc_icon = new GridBagConstraints();
        gbc_icon.insets = new Insets(0, 5, 0, 5);
        final GridBagConstraints gbc_wide = new GridBagConstraints();
        gbc_wide.weightx = 1.0;
        gbc_wide.fill = 2;
        final GemT[] sockets = { GemT.RED, GemT.YELLOW, GemT.BLUE };
        final int len2 = sockets.length;
        this.regem_primaryColor_combos = new JComboBox[len2];
        for (int l = 0; l < len2; ++l) {
            final GemT socket = sockets[l];
            final JComboBox combo2 = UI.makeCombo();
            combo2.setFont(CatusFrame.tinyFont);
            combo2.setPrototypeDisplayValue("");
            final ArrayList<Gem> list = new ArrayList<Gem>();
            for (final GemT x5 : GemT.ALL) {
                if (socket.matches(x5)) {
                    for (final Gem gem2 : this.gemsMap.get(x5)) {
                        if (gem2.requiredProf == null) {
                            list.add(gem2);
                        }
                    }
                }
            }
            Collections.sort(list, CatusFrame.cmp_basicWeights);
            for (final Gem x6 : list) {
                combo2.addItem(x6);
            }
            makeGemCombo(combo2);
            final JLabel outer = new JLabel(CatusFrame.gemBgImageMap.get(socket));
            outer.setLayout(new GridBagLayout());
            outer.setPreferredSize(new Dimension(22, 22));
            outer.add(new JLabel(CatusFrame.socketImageMap.get(socket)));
            colorRow.add(outer, gbc_icon);
            colorRow.add(combo2, gbc_wide);
            this.regem_primaryColor_combos[l] = combo2;
        }
        final UIPanel_GridBag row7 = new UIPanel_GridBag();
        JLabel outer2 = new JLabel(CatusFrame.gemBgImageMap.get(GemT.WHITE));
        outer2.setLayout(new GridBagLayout());
        outer2.setPreferredSize(new Dimension(22, 22));
        outer2.add(new JLabel(CatusFrame.socketImageMap.get(GemT.WHITE)));
        row7.add(outer2, gbc_icon);
        (this.regem_white_combo = UI.makeCombo()).setFont(CatusFrame.tinyFont);
        for (final GemT x7 : sockets) {
            this.regem_white_combo.addItem(x7);
        }
        row7.add(this.regem_white_combo);
        outer2 = new JLabel(CatusFrame.gemBgImageMap.get(GemT.SHA));
        outer2.setLayout(new GridBagLayout());
        outer2.setPreferredSize(new Dimension(22, 22));
        outer2.add(new JLabel(CatusFrame.socketImageMap.get(GemT.SHA)));
        row7.add(outer2, gbc_icon);
        (this.regem_sha_combo = UI.makeCombo()).setFont(CatusFrame.tinyFont);
        for (final Gem x8 : this.gemsMap.get(GemT.SHA)) {
            this.regem_sha_combo.addItem(x8);
        }
        row7.add(this.regem_sha_combo);
        row7.spacer();
        final UIPanel_GridBag row8 = new UIPanel_GridBag();
        (this.regem_always_check = UI.makeCheck("Always use")).setSelected(true);
        row8.add(this.regem_always_check);
        (this.regem_alwaysColor_combo = UI.makeCombo()).setFont(CatusFrame.tinyFont);
        for (final GemT x9 : sockets) {
            this.regem_alwaysColor_combo.addItem(x9);
        }
        row8.add(this.regem_alwaysColor_combo);
        (this.regem_unless_check = UI.makeCheck("unless socket bonus is better.")).setSelected(true);
        row8.add(this.regem_unless_check);
        final ActionListener al2 = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this._updateRegemOptions();
            }
        };
        this.regem_always_check.addActionListener(al2);
        al2.actionPerformed(null);
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.fill = 2;
        row8.add(new JLabel(), gbc);
        (this.regem_regemBtn = UI.makeButton("Regem")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final HashMap<GemT, Gem> map = new HashMap<GemT, Gem>();
                for (int i = 0; i < sockets.length; ++i) {
                    final Object sel = CatusFrame.this.regem_primaryColor_combos[i].getSelectedItem();
                    if (sel instanceof Gem) {
                        map.put(sockets[i], (Gem)sel);
                    }
                }
                Object sel2 = CatusFrame.this.regem_white_combo.getSelectedItem();
                if (sel2 instanceof GemT) {
                    map.put(GemT.WHITE, map.get(sel2));
                }
                sel2 = CatusFrame.this.regem_sha_combo.getSelectedItem();
                if (sel2 instanceof Gem) {
                    map.put(GemT.SHA, (Gem)sel2);
                }
                try {
                    if (CatusFrame.this.regem_always_check.isSelected()) {
                        final GemT always = sockets[CatusFrame.this.regem_alwaysColor_combo.getSelectedIndex()];
                        if (CatusFrame.this.regem_unless_check.isSelected()) {
                            CatusFrame.this.mainProfile.setSmartSockets(map, map.get(always), CatusFrame.WEIGHTS);
                        }
                        else {
                            final Gem gem = map.get(always);
                            for (final GemT x : sockets) {
                                map.put(x, gem);
                            }
                            CatusFrame.this.mainProfile.setAllSockets(map, true);
                        }
                    }
                    else {
                        CatusFrame.this.mainProfile.setAllSockets(map, true);
                    }
                    if (CatusFrame.this.regem_profRow.isVisible()) {
                        final LinkedList<Gem> gems = new LinkedList<Gem>();
                        Gem first = null;
                        sel2 = CatusFrame.this.regem_profCombos[0].getSelectedItem();
                        if (sel2 instanceof Gem) {
                            first = (Gem)sel2;
                            gems.add(first);
                        }
                        sel2 = CatusFrame.this.regem_profCombos[1].getSelectedItem();
                        if (sel2 instanceof Gem) {
                            gems.add((Gem)sel2);
                        }
                        else if (sel2 == "Same") {
                            gems.add(first);
                        }
                        if (!gems.isEmpty()) {
                            CatusFrame.this.mainProfile.setSomeSockets(gems, new GemT[] { GemT.BLUE, GemT.YELLOW, GemT.WHITE, GemT.RED });
                        }
                    }
                }
                catch (RuntimeException err) {
                    CatusFrame.this.showError("Unexpected Regem Error", err.getMessage());
                }
                CatusFrame.this._rebuildGear();
            }
        });
        (this.regem_clearBtn = UI.makeButton("Clear Gems")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.mainProfile.clearGems();
                CatusFrame.this._rebuildGear();
            }
        });
        row8.add(this.regem_clearBtn);
        row8.add(this.regem_regemBtn);
        final JPanel p6 = _makePanel();
        p6.setLayout(new GridBagLayout());
        _addGridBagRow(p6, this.regem_profRow, false);
        _addGridBagRow(p6, colorRow, true);
        _addGridBagRow(p6, row7, true);
        _addGridBagRow(p6, row8, true);
        this.regemPane = new PrefPane("Regem", p6, true, false, CatusFrame.DARK_GRAY_INFO);
        p2 = _makePanel();
        p2.setLayout(new GridLayout(0, 3, 5, 0));
        (this.race_combo = new UIComboBox<RaceT>(SpecT.FERAL.classType.races)).addActionListener(this.raceAndProfs_al);
        p2.add(this.race_combo);
        this.prof_combos = new JComboBox[2];
        for (int k = 0; k < this.prof_combos.length; ++k) {
            final JComboBox combo = UI.makeCombo();
            combo.addActionListener(this.raceAndProfs_al);
            p2.add(combo);
            this.prof_combos[k] = combo;
        }
        this.raceAndProf_pane = new PrefPane("Race and Professions", p2, false, true, null);
        (this.talents_webBtn = UI.makeButton("View in Calculator")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                Utils.openURL(String.format("http://us.battle.net/wow/en/tool/talent-calculator#UZ!%s!%s", API.getTalentStr(CatusFrame.this.talentBytes, 0), CatusFrame.this.getGlyphsAsString()));
            }
        });
        (this.talents_clearBtn = UI.makeButton("Clear Talents")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.loadTalentsFromString("");
                CatusFrame.this._rebuildGear();
            }
        });
        final JPanel top2 = _makePanel();
        top2.setLayout(new GridBagLayout());
        top2.add(this.talents_webBtn);
        _addGridBagSpacer(top2);
        top2.add(this.talents_clearBtn);
        final JPanel grid5 = _makePanel();
        grid5.setLayout(new GridLayout(0, 3, 3, 3));
        for (int row9 = 0; row9 < 6; ++row9) {
            final ButtonGroup[] talentGroups = this.talentGroups;
            final int n13 = row9;
            final ButtonGroup buttonGroup = new ButtonGroup() {
                @Override
                public void setSelected(final ButtonModel m, final boolean b) {
                    if (this.getSelection() == m && m.isSelected()) {
                        this.clearSelection();
                    }
                    else {
                        super.setSelected(m, b);
                    }
                }
            };
            talentGroups[n13] = buttonGroup;
            final ButtonGroup bg = buttonGroup;
            final Talent[] talents = FeralSpec.TALENTS[row9];
            for (int col = 0; col < 3; ++col) {
                final JToggleButton[] array6 = this.talent_btns[row9];
                final int n14 = col;
                final JToggleButton toggleButton = new JToggleButton();
                array6[n14] = toggleButton;
                final JToggleButton btn = toggleButton;
                final Talent t = talents[col];
                final ImageIcon icon = this.api.getIconImage(t.icon, API.IconSize.SMALL);
                if (icon != null) {
                    btn.setIcon(icon);
                }
                btn.setText(talents[col].name);
                btn.setFocusable(false);
                btn.setHorizontalAlignment(2);
                bg.add(btn);
                grid5.add(btn);
                btn.setActionCommand(Integer.toString(col));
                btn.addActionListener(this.talent_btns_al);
            }
        }
        p3 = _makePanel();
        p3.setLayout(new GridBagLayout());
        _addGridBagRow(p3, top2, false);
        _addGridBagRow(p3, grid5, true);
        this.talent_pane = new PrefPane("Talents", p3, false, true, null);
        this.glyph_combos = new JComboBox[3];
        JPanel grid6 = _makePanel();
        grid6.setLayout(new GridLayout(0, 3, 5, 0));
        for (int k = 0; k < 3; ++k) {
            final JComboBox combo = UI.makeCombo();
            (this.glyph_combos[k] = combo).addActionListener(this.glyph_combo_al);
            grid6.add(combo);
        }
        this.glyphPane = new PrefPane("Major Glyphs", grid6, false, true, null);
        final UIPanel_GridBag tab_patch = new UIPanel_GridBag();
        tab_patch.setName("Patchwerk");
        tab_patch.pad(4);
        (this.fight_patch_life_combo = new UIComboBox<String>("Duration", (String[])new Object[] { "Health" })).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.fight_patch_timeVariance_combo.setVisible(CatusFrame.this.fight_patch_life_combo.getSelectedIndex() == 0);
            }
        });
        (this.fight_patch_life_field = new JTextField()).setPreferredSize(wideNumberFieldSize);
        this.fight_patch_timeVariance_combo = new UIComboBox<NamedThing.Int>(new NamedThing.Int("Automatic", 0), (NamedThing.Int[])new Object[] { new NamedThing.Int("Automatic (Old)", -2), new NamedThing.Int("Fixed", -1), new NamedThing.Int("Simc: ±20%", 20), new NamedThing.Int("Simc: ±10%", 10), new NamedThing.Int("Simc: ±5%", 5) });
        this.fight_patch_class_combo = new UIComboBox<NamedThing.Int>(new NamedThing.Int("+3 (Boss)", 3), (NamedThing.Int[])new Object[] { new NamedThing.Int("+2", 2), new NamedThing.Int("+1", 1), new NamedThing.Int("+0", 0) });
        this.fight_patch_front_check = UI.makeCheck("From Front");
        this.fight_patch_dodge_check = UI.makeCheck("Can Dodge");
        this.fight_patch_parry_check = UI.makeCheck("Can Parry");
        this.fight_patch_block_check = UI.makeCheck("Can Block");
        (this.fight_patch_early_field = new JTextField()).setName("Early Death");
        this.fight_patch_early_field.setPreferredSize(tinyNumberFieldSize);
        (this.fight_patch_armor_field = new JTextField()).setName("Custom Armor");
        this.fight_patch_armor_field.setPreferredSize(numberFieldSize);
        final JLabel desc = new JLabel("Traditional single-target encounter.  Target is always in range.");
        desc.setForeground(CatusFrame.DARK_BLUE);
        desc.setIcon(this.api.getIconImage(InterfaceIcon.FIGHT_PATCHWERK.slug, API.IconSize.SMALL));
        desc.setFont(CatusFrame.tinyFont);
        desc.setHorizontalAlignment(0);
        tab_patch.row(desc, true, 0);
        UIPanel_GridBag row10 = new UIPanel_GridBag();
        row10.add(UI.L("Class: ", CatusFrame.boldFont));
        row10.add(this.fight_patch_class_combo);
        row10.gap();
        row10.add(this.fight_patch_life_combo);
        row10.add(this.fight_patch_life_field);
        row10.add(this.fight_patch_timeVariance_combo);
        tab_patch.row(row10, false, 4);
        UIPanel row11 = new UIPanel_GridBag();
        row11.add(UI.L("Avoidance: ", CatusFrame.boldFont));
        row11.add(this.fight_patch_front_check);
        row11.add(this.fight_patch_dodge_check);
        row11.add(this.fight_patch_parry_check);
        row11.add(this.fight_patch_block_check);
        tab_patch.row(row11, false, 4);
        row11 = new UIPanel_GridBag();
        row11.add(UI.L("Modifications: ", CatusFrame.boldFont));
        row11.gap();
        row11.add(UI.L(this.fight_patch_early_field.getName() + ": ", CatusFrame.normalFont));
        row11.add(this.fight_patch_early_field);
        row11.add(new JLabel(" %"));
        row11.gap();
        row11.add(UI.L(this.fight_patch_armor_field.getName() + ": ", CatusFrame.normalFont));
        row11.add(this.fight_patch_armor_field);
        tab_patch.row(row11, false, 4);
        this.fight_cleave_level_combo = new UIComboBox<NamedThing.Int>(new NamedThing.Int("+3", 3), (NamedThing.Int[])new Object[] { new NamedThing.Int("+2", 2), new NamedThing.Int("+1", 1), new NamedThing.Int("+0", 0) });
        this.fight_cleave_style_combo = new UIComboBox<String>("Boss Death", (String[])new Object[] { "Fixed Time" });
        (this.fight_cleave_time_field = new JTextField()).setName("Duration");
        this.fight_cleave_time_field.setPreferredSize(wideNumberFieldSize);
        this.fight_cleave_start_check = UI.makeCheck("At Start");
        (this.fight_cleave_freq_field = new JTextField()).setName("Frequency");
        this.fight_cleave_freq_field.setPreferredSize(wideNumberFieldSize);
        (this.fight_cleave_health_field = new JTextField()).setName("Health");
        this.fight_cleave_health_field.setPreferredSize(wideNumberFieldSize);
        (this.fight_cleave_lifetime_field = new JTextField()).setName("Lifetime");
        this.fight_cleave_lifetime_field.setPreferredSize(wideNumberFieldSize);
        (this.fight_cleave_frontProb_field = new JTextField()).setName("Facing Probability");
        this.fight_cleave_frontProb_field.setPreferredSize(wideNumberFieldSize);
        this.fight_cleave_adds_spinner = new Spinner("Adds", null, 0, 25);
        this.fight_cleave_minSize_spinner = new Spinner("Targets", null, 0, 25);
        final UIPanel_GridBag tab_cleave = new UIPanel_GridBag();
        tab_cleave.setName("Cleave");
        tab_cleave.pad(4);
        final JLabel desc2 = new JLabel("Primary boss-level target with (periodic) add spawns.  Boss and adds are always in range.");
        desc2.setForeground(CatusFrame.DARK_BLUE);
        desc2.setIcon(this.api.getIconImage(InterfaceIcon.FIGHT_CLEAVE.slug, API.IconSize.SMALL));
        desc2.setFont(CatusFrame.tinyFont);
        desc2.setHorizontalAlignment(0);
        tab_cleave.row(desc2, true, 0);
        UIPanel_GridBag row12 = new UIPanel_GridBag();
        row12.add(UI.L("End of Combat: ", CatusFrame.boldFont));
        row12.add(this.fight_cleave_style_combo);
        row12.gap();
        row12.add(UI.L(this.fight_cleave_time_field.getName() + ": ", CatusFrame.boldFont));
        row12.add(this.fight_cleave_time_field);
        tab_cleave.row(row12, false, 4);
        UIPanel row13 = new UIPanel_GridBag();
        row13.add(UI.L("Spawn:  ", CatusFrame.boldFont));
        row13.add(this.fight_cleave_adds_spinner.panel);
        row13.gap();
        row13.add(this.fight_cleave_start_check);
        row13.gap();
        row13.add(UI.L(this.fight_cleave_freq_field.getName() + ": ", CatusFrame.normalFont));
        row13.add(this.fight_cleave_freq_field);
        tab_cleave.row(row13, false, 4);
        row13 = new UIPanel_GridBag();
        row13.add(UI.L("Mortality:", CatusFrame.boldFont));
        row13.gap();
        row13.add(UI.L("Level: ", CatusFrame.normalFont));
        row13.add(this.fight_cleave_level_combo);
        row13.gap();
        row13.add(UI.L(this.fight_cleave_health_field.getName() + ": ", CatusFrame.normalFont));
        row13.add(this.fight_cleave_health_field);
        row13.gap();
        row13.add(UI.L(this.fight_cleave_lifetime_field.getName() + ": ", CatusFrame.normalFont));
        row13.add(this.fight_cleave_lifetime_field);
        tab_cleave.row(row13, false, 4);
        row13 = new UIPanel_GridBag();
        row13.add(UI.L("Avoidance:", CatusFrame.boldFont));
        row13.gap();
        row13.add(UI.L(this.fight_cleave_frontProb_field.getName() + ": ", CatusFrame.normalFont));
        row13.add(this.fight_cleave_frontProb_field);
        row13.add(new JLabel(" %"));
        tab_cleave.row(row13, false, 4);
        row13 = new UIPanel_GridBag();
        row13.add(UI.L("Minimum Cleave Size:  ", CatusFrame.boldFont));
        row13.add(this.fight_cleave_minSize_spinner.panel);
        tab_cleave.row(row13, false, 4);
        this.fight_mirror_checks = new JCheckBox[] { this.fight_mirror_idle_check = UI.makeCheck("Idle"), this.fight_mirror_health_check = UI.makeCheck("Health"), this.fight_mirror_self_check = UI.makeCheck("Procs"), this.fight_mirror_external_check = UI.makeCheck("Externals") };
        this.fight_mirror_idle_check.setEnabled(false);
        for (final JCheckBox x10 : this.fight_mirror_checks) {
            x10.setName(x10.getText());
        }
        (this.fight_mirror_source_lbl = new JLabel()).setForeground(CatusFrame.DARK_GREEN);
        (this.fight_mirror_check_btn = UI.makeButton("Spell Map")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                Encounter_Mirror.Trigger[] triggers;
                try {
                    triggers = Encounter_Mirror.parseTriggers(CatusFrame.this.fight_mirror_script, Collections.singleton(Encounter_Mirror.Type.SELF));
                }
                catch (RuntimeException err) {
                    CatusFrame.this.showError_html("Mirror Script Error", err.getMessage());
                    return;
                }
                final FeralSim copySim = CatusFrame.this.copySim(true);
                if (copySim == null) {
                    return;
                }
                final HashMap<Integer, ArrayList<Encounter_Mirror.Trigger>> map = new HashMap<Integer, ArrayList<Encounter_Mirror.Trigger>>();
                for (final Encounter_Mirror.Trigger x : triggers) {
                    final Integer key = (int)x.data;
                    ArrayList<Encounter_Mirror.Trigger> list = map.get(key);
                    if (list == null) {
                        list = new ArrayList<Encounter_Mirror.Trigger>();
                        map.put(key, list);
                    }
                    list.add(x);
                }
                map.remove(22812);
                map.remove(768);
                map.remove(5487);
                map.remove(106951);
                map.remove(144865);
                map.remove(146874);
                map.remove(24932);
                map.remove(69369);
                map.remove(127538);
                map.remove(93622);
                map.remove(44203);
                map.remove(774);
                map.remove(5225);
                class X
                {
                    String name;
                    String handler;
                    int num;
                }
                final HashMap<Object, X> totalMap = new HashMap<Object, X>();
                for (final Map.Entry<Integer, Object> e : copySim.selfMap.entrySet()) {
                    final ArrayList<Encounter_Mirror.Trigger> list2 = map.remove(e.getKey());
                    X total = totalMap.get(e.getValue());
                    if (total == null) {
                        total = new X();
                        total.name = "?";
                        total.handler = e.getValue().toString();
                        totalMap.put(e.getValue(), total);
                    }
                    if (list2 != null) {
                        total.name = list2.get(0).desc;
                        final X x4 = total;
                        x4.num += list2.size();
                    }
                }
                for (final ArrayList<Encounter_Mirror.Trigger> list3 : map.values()) {
                    final Encounter_Mirror.Trigger t = list3.get(0);
                    final X total = new X();
                    total.name = t.desc;
                    total.handler = "? <" + t.data + ">";
                    total.num = list3.size();
                    totalMap.put(total, total);
                }
                final X[] totals = totalMap.values().toArray(new X[totalMap.size()]);
                Arrays.sort(totals, new Comparator<X>() {
                    @Override
                    public int compare(final X a, final X b) {
                        final int diff = b.num - a.num;
                        return (diff == 0) ? a.name.compareTo(b.name) : diff;
                    }
                });
                int max = 0;
                for (final X x2 : totals) {
                    max = Math.max(max, x2.name.length());
                }
                final StringBuilder sb = new StringBuilder();
                for (final X x3 : totals) {
                    if (sb.length() > 0) {
                        sb.append("\n");
                    }
                    sb.append(String.format("%4d", x3.num));
                    sb.append("   ");
                    Fmt.padLeft(sb, x3.name, max);
                    sb.append(" => ");
                    sb.append(x3.handler);
                    sb.append(' ');
                }
                if (sb.length() == 0) {
                    sb.append("Equipped gear has no spells.");
                }
                new DialogText(CatusFrame.this, "Spell Map", "OK").showText(sb.toString());
            }
        });
        (this.fight_mirror_filtered_btn = UI.makeButton("Show Filtered")).setFont(CatusFrame.tinyFont);
        this.fight_mirror_filtered_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final Encounter_Mirror en = new Encounter_Mirror();
                if (!CatusFrame.this.checkMirrorScript(en)) {
                    return;
                }
                new DialogText(CatusFrame.this, "Filtered: Mirror Script", "OK").showText(Encounter_Mirror.formatTriggers(en.triggers));
            }
        });
        (this.fight_mirror_edit_btn = UI.makeButton("Edit...")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                String code = CatusFrame.this.fight_mirror_script;
                while (true) {
                    code = new DialogText(CatusFrame.this, "Edit: Mirror Script", "OK").editText(code);
                    if (code == null) {
                        break;
                    }
                    try {
                        final Encounter_Mirror.Trigger[] t = Encounter_Mirror.parseTriggers(code, null);
                        if (!CatusFrame.this.fight_mirror_script.equalsIgnoreCase(code)) {
                            CatusFrame.this.fight_mirror_script = code;
                            CatusFrame.this.fight_mirror_source_lbl.setText("Custom (" + t.length + " events) <" + Fmt.msDur((t.length > 0) ? ((long)t[t.length - 1].time) : 0L) + ">");
                        }
                    }
                    catch (RuntimeException err) {
                        CatusFrame.this.showError_html("Mirror Script Error", err.getMessage());
                    }
                }
            }
        });
        final UIPanel_GridBag tab_mirror = new UIPanel_GridBag();
        tab_mirror.setName("Mirror");
        tab_mirror.pad(4);
        final JLabel desc3 = new JLabel("<html>Emulate a parsed report by matching various aspects of the recorded combat.</html>");
        desc3.setForeground(CatusFrame.DARK_BLUE);
        desc3.setIcon(this.api.getIconImage(InterfaceIcon.ATTACK.slug, API.IconSize.SMALL));
        desc3.setFont(CatusFrame.tinyFont);
        desc3.setHorizontalAlignment(0);
        tab_mirror.row(desc3, true, 0);
        UIPanel_GridBag row14 = new UIPanel_GridBag();
        row14.add(UI.L("Mirror Script: ", CatusFrame.boldFont));
        row14.gap();
        row14.spacer(this.fight_mirror_source_lbl);
        row14.gap();
        row14.add(this.fight_mirror_edit_btn);
        row14.add(this.fight_mirror_check_btn);
        tab_mirror.row(row14, false, 4);
        row2 = new UIPanel_GridBag();
        row2.add(UI.L("Filters: ", CatusFrame.boldFont));
        for (final JCheckBox x11 : this.fight_mirror_checks) {
            row2.add(x11);
        }
        row2.add(this.fight_mirror_filtered_btn);
        tab_mirror.row(row2, false, 4);
        this.fight_tabs = new SmartTabPane(new Component[] { tab_patch, tab_cleave, tab_mirror });
        (this.fight_encounter_combo = new UIComboBox<NamedThing.Obj<JSONObject>>()).setWide(true);
        this.fight_encounter_combo.addActionListener(this.encounter_combo_al);
        final JButton fight_export_btn = UI.makeButton("Export to JSON");
        fight_export_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final JSONObject map = new JSONObject();
                map.put("Name", CatusFrame.this.fight_tabs.getSelectedTitle());
                CatusFrame.this.exportConfig_fights(map, true);
                new DialogText(CatusFrame.this, "Export: Encounter JSON", "OK").showWrappedText(map.toJSONString());
            }
        });
        (this.fight_stdEffects_btn = UI.makeButton("Use Recommended Settings")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.loadConfig_effects0();
                CatusFrame.this._updateConfigOptions();
                CatusFrame.this._rebuildGear();
            }
        });
        (this.fight_stdFights_btn = UI.makeButton("Use Recommended Encounter")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.loadConfig_fights(new JSONObject());
                CatusFrame.this._updateConfigOptions();
                CatusFrame.this._rebuildGear();
            }
        });
        final UIPanel_GridBag p7 = new UIPanel_GridBag();
        final UIPanel_GridBag row15 = new UIPanel_GridBag();
        row15.add(this.fight_stdEffects_btn);
        row15.add(this.fight_stdFights_btn);
        row15.spacer();
        row15.add(this.fight_encounter_combo);
        p7.row(row15, true, 0);
        p7.row(this.fight_tabs, true, 4);
        (this.fight_periodicDmgMod_check = UI.makeCheck("Periodic Damage Modifier:")).setFont(CatusFrame.boldFont);
        (this.fight_periodicDmgMod_mod_field = new JTextField()).setName("Damage Modifier");
        this.fight_periodicDmgMod_mod_field.setPreferredSize(numberFieldSize);
        (this.fight_periodicDmgMod_freq_field = new JTextField()).setName("Frequency");
        this.fight_periodicDmgMod_freq_field.setPreferredSize(wideNumberFieldSize);
        (this.fight_periodicDmgMod_time_field = new JTextField()).setName("Duration");
        this.fight_periodicDmgMod_time_field.setPreferredSize(wideNumberFieldSize);
        UI.onChange_enable(this.fight_periodicDmgMod_check, this.fight_periodicDmgMod_mod_field, this.fight_periodicDmgMod_freq_field, this.fight_periodicDmgMod_time_field);
        (this.fight_cooldownDelay_check = UI.makeCheck("Cooldown Delay:")).setFont(CatusFrame.boldFont);
        (this.fight_cooldownDelay_field = new JTextField()).setName("Cooldown Delay");
        this.fight_cooldownDelay_field.setPreferredSize(wideNumberFieldSize);
        UI.onChange_enable(this.fight_cooldownDelay_check, this.fight_cooldownDelay_field);
        (this.fight_periodicIdle_check = UI.makeCheck("Periodic Intermission")).setFont(CatusFrame.boldFont);
        (this.fight_periodicIdle_freq_field = new JTextField()).setName("Frequency");
        this.fight_periodicIdle_freq_field.setPreferredSize(wideNumberFieldSize);
        (this.fight_periodicIdle_time_field = new JTextField()).setName("Duration");
        this.fight_periodicIdle_time_field.setPreferredSize(wideNumberFieldSize);
        (this.fight_periodicIdle_limit_field = new JTextField()).setName("Limit");
        this.fight_periodicIdle_limit_field.setPreferredSize(tinyNumberFieldSize);
        UI.onChange_enable(this.fight_periodicIdle_check, this.fight_periodicIdle_freq_field, this.fight_periodicIdle_time_field, this.fight_periodicIdle_limit_field);
        (this.fight_periodicCast_check = UI.makeCheck("Periodic Enemy Cast")).setFont(CatusFrame.boldFont);
        (this.fight_periodicCast_freq_field = new JTextField()).setName("Frequency");
        this.fight_periodicCast_freq_field.setPreferredSize(wideNumberFieldSize);
        UI.onChange_enable(this.fight_periodicCast_check, this.fight_periodicCast_freq_field);
        (this.fight_advanced_check = UI.makeCheck("Encounter Script:")).setFont(CatusFrame.boldFont);
        final JButton advanced_edit_btn = UI.makeButton("Edit...");
        advanced_edit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final String ret = new DialogText(CatusFrame.this, "Edit: Encounter Script", "Save").editText(CatusFrame.this.fight_advanced_script);
                if (ret != null) {
                    CatusFrame.this.fight_advanced_script = ret;
                }
            }
        });
        final JButton advanced_help_btn = UI.makeButton("Help...");
        advanced_help_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                new DialogProg(CatusFrame.this, "Downloading").execute(new Runnable() {
                    @Override
                    public void run() {
                        ActionListener.this.go();
                    }
                });
            }
            
            void go() {
                final String text = CatusFrame.this.hc.getStr_silent("https://dl.dropboxusercontent.com/u/2989349/catus_data/Encounters/44/EncounterScriptHelp.txt", "", 604800000);
                if (text == null) {
                    CatusFrame.this.showError("Unable to Download", "An error occurred downloading the latest Encounter Script information.");
                    return;
                }
                new DialogText(CatusFrame.this, "Help: Encounter Script", "OK").showText(text);
            }
        });
        UI.onChange_enable(this.fight_advanced_check, advanced_edit_btn, advanced_help_btn);
        (this.fight_entry_check = UI.makeCheck("Alternative Entry Point:")).setFont(CatusFrame.boldFont);
        (this.fight_entry_field = new JTextField()).setPreferredSize(wideNumberFieldSize);
        UI.onChange_enable(this.fight_entry_check, this.fight_entry_field);
        UIPanel_GridBag row16 = new UIPanel_GridBag();
        row16.gap();
        row16.add(new JLabel(this.api.getIconImage(InterfaceIcon.PERIODIC_DMG.slug, API.IconSize.SMALL)));
        row16.add(this.fight_periodicDmgMod_check);
        row16.add(this.fight_periodicDmgMod_mod_field);
        row16.add(new JLabel(" %"));
        row16.gap();
        row16.add(new JLabel(this.fight_periodicDmgMod_freq_field.getName() + ": "));
        row16.add(this.fight_periodicDmgMod_freq_field);
        row16.gap();
        row16.add(new JLabel(this.fight_periodicDmgMod_time_field.getName() + ": "));
        row16.add(this.fight_periodicDmgMod_time_field);
        row16.spacer();
        p7.row(row16, true, 4);
        row16 = new UIPanel_GridBag();
        row16.gap();
        row16.add(new JLabel(this.api.getIconImage(InterfaceIcon.TIME.slug, API.IconSize.SMALL)));
        row16.add(this.fight_periodicIdle_check);
        row16.gap();
        row16.add(new JLabel(this.fight_periodicIdle_freq_field.getName() + ": "));
        row16.add(this.fight_periodicIdle_freq_field);
        row16.gap();
        row16.add(new JLabel(this.fight_periodicIdle_time_field.getName() + ": "));
        row16.add(this.fight_periodicIdle_time_field);
        row16.gap();
        row16.add(new JLabel(this.fight_periodicIdle_limit_field.getName() + ": "));
        row16.add(this.fight_periodicIdle_limit_field);
        row16.spacer();
        p7.row(row16, true, 4);
        row16 = new UIPanel_GridBag();
        row16.gap();
        row16.add(new JLabel(this.api.getIconImage(InterfaceIcon.STARFIRE.slug, API.IconSize.SMALL)));
        row16.add(this.fight_periodicCast_check);
        row16.gap();
        row16.add(new JLabel(this.fight_periodicCast_freq_field.getName() + ": "));
        row16.add(this.fight_periodicCast_freq_field);
        row16.spacer();
        p7.row(row16, true, 4);
        row16 = new UIPanel_GridBag();
        row16.gap();
        row16.add(new JLabel(this.api.getIconImage(InterfaceIcon.HIBERNATE.slug, API.IconSize.SMALL)));
        row16.add(this.fight_cooldownDelay_check);
        row16.add(this.fight_cooldownDelay_field);
        row16.spacer();
        p7.row(row16, true, 4);
        row16 = new UIPanel_GridBag();
        row16.gap();
        row16.add(new JLabel(this.api.getIconImage(InterfaceIcon.SCRIPT.slug, API.IconSize.SMALL)));
        row16.add(this.fight_advanced_check);
        row16.add(advanced_edit_btn);
        row16.add(advanced_help_btn);
        final JLabel temp2 = new JLabel(" (Experimental)");
        temp2.setFont(CatusFrame.tinyBoldFont);
        temp2.setForeground(CatusFrame.DARK_RED);
        row16.add(temp2);
        row16.spacer();
        p7.row(row16, true, 4);
        row16 = new UIPanel_GridBag();
        row16.gap();
        row16.add(new JLabel(this.api.getIconImage(InterfaceIcon.ENTRY.slug, API.IconSize.SMALL)));
        row16.add(this.fight_entry_check);
        row16.add(this.fight_entry_field);
        row16.spacer();
        p7.row(row16, true, 4);
        p7.row(new JSeparator(), true, 4);
        final JButton script_btn = UI.makeButton("Show Action List");
        script_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String fight = CatusFrame.this.fight_tabs.getSelectedTitle();
                final String script = CatusFrame.this.getScript(fight);
                if (script == null) {
                    return;
                }
                new DialogText(CatusFrame.this, "Action List: " + fight, "OK").showText(script);
            }
        });
        this.fight_autosave_check = UI.makeCheck("Autosave Reports");
        final JButton reveal_btn = UI.makeButton("Reveal Directory");
        reveal_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                Utils.openFile(CatusFrame.REPORTS_DIR);
            }
        });
        final UIPanel_GridBag row17 = new UIPanel_GridBag();
        row17.add(this.fight_autosave_check);
        row17.gap();
        row17.add(reveal_btn);
        row17.spacer();
        row17.add(fight_export_btn);
        row17.add(script_btn);
        p7.row(row17, true, 4);
        this.fight_pane = new PrefPane("Encounter Configuration", p7, false, false, null);
        final ChangeListener chg = new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent e) {
                CatusFrame.this.fight_pane.desc.setText(CatusFrame.this.fight_tabs.getSelectedTitle());
            }
        };
        this.fight_tabs.addChangeListener(chg);
        chg.stateChanged(null);
        _addComboItemAndPrototype(this.consume_food_combo = UI.makeCombo(), "No Food");
        this.consume_food_combo.addItem(StatValue.make(StatT.AGI, 300));
        this.consume_food_combo.addItem(StatValue.make(StatT.AGI, 275));
        this.consume_food_combo.addItem(StatValue.make(StatT.AGI, 250));
        this.consume_food_combo.addItem(StatValue.make(StatT.STR, 300));
        this.consume_food_combo.addItem(StatValue.make(StatT.STR, 270));
        this.consume_food_combo.addItem(StatValue.make(StatT.STR, 250));
        this.consume_food_combo.addItem(StatValue.make(StatT.INT, 300));
        this.consume_food_combo.addItem(StatValue.make(StatT.INT, 275));
        this.consume_food_combo.addItem(StatValue.make(StatT.INT, 250));
        this.consume_food_combo.addItem(StatValue.make(StatT.MASTERY, 200));
        this.consume_food_combo.addItem(StatValue.make(StatT.HASTE, 200));
        this.consume_food_combo.addItem(StatValue.make(StatT.CRIT, 200));
        this.consume_food_combo.addItem(StatValue.make(StatT.HIT, 300));
        this.consume_food_combo.addItem(StatValue.make(StatT.HIT, 275));
        this.consume_food_combo.addItem(StatValue.make(StatT.HIT, 200));
        this.consume_food_combo.addItem(StatValue.make(StatT.EXP, 300));
        this.consume_food_combo.addItem(StatValue.make(StatT.EXP, 275));
        this.consume_food_combo.addItem(StatValue.make(StatT.EXP, 200));
        this.consume_food_combo.addItem(StatValue.make(StatT.STA, 450));
        this.consume_food_combo.addItem(StatValue.make(StatT.STA, 415));
        this.consume_food_combo.addItem(StatValue.make(StatT.STA, 375));
        this.consume_food_combo.addActionListener(this.consume_al);
        final ArrayList<Flask> copy = new ArrayList<Flask>();
        Collections.addAll(copy, Flask.ALL);
        Collections.sort(copy, CatusFrame.cmp_basicWeights);
        copy.remove(Flask.NONE);
        copy.add(0, Flask.NONE);
        (this.consume_flask_combo = new UIComboBox<Flask>(copy.toArray(new Flask[copy.size()]))).setWide(true);
        this.consume_flask_combo.addActionListener(this.consume_al);
        final ListCellRenderer lcr = this.consume_flask_combo.getRenderer();
        this.consume_flask_combo.setRenderer(new ListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(final JList list, Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
                if (index == -1) {
                    value = ((Flask)value).statStr(ProfT.ALCH.isMemberOf(CatusFrame.this.mainProfile.profs));
                }
                return lcr.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        (this.consume_potion_combo = this._makePotCombo(false)).addActionListener(this.consume_al);
        p = _makePanel();
        p.setLayout(new GridLayout(0, 3, 4, 4));
        p.add(wrapIcon(this.api.getIconImage(InterfaceIcon.FOOD.slug, API.IconSize.SMALL), this.consume_food_combo));
        p.add(wrapIcon(this.api.getIconImage(InterfaceIcon.FLASK.slug, API.IconSize.SMALL), this.consume_flask_combo));
        p.add(wrapIcon(this.api.getIconImage(InterfaceIcon.POTION.slug, API.IconSize.SMALL), this.consume_potion_combo));
        this.consumePane = new PrefPane("Consumables", p, false, false, CatusFrame.DARK_GRAY_BUFF);
        this.buff_toggles = new JToggleButton[] { this.buff_stats_toggle = this.makeIconToggle("Stats", InterfaceIcon.BUFF_STATS), this.buff_crit_toggle = this.makeIconToggle("Crit", InterfaceIcon.BUFF_CRIT), this.buff_mastery_toggle = this.makeIconToggle("Mastery", InterfaceIcon.BUFF_MASTERY), this.buff_stam_toggle = this.makeIconToggle("Stamina", InterfaceIcon.BUFF_STAM), this.buff_ap_toggle = this.makeIconToggle("Attack Power", InterfaceIcon.BUFF_AP), this.buff_sp_toggle = this.makeIconToggle("Spell Power", InterfaceIcon.BUFF_SP), this.buff_meleeHaste_toggle = this.makeIconToggle("Melee Haste", InterfaceIcon.BUFF_MELEE_HASTE), this.buff_spellHaste_toggle = this.makeIconToggle("Spell Haste", InterfaceIcon.BUFF_SPELL_HASTE) };
        for (final JToggleButton x12 : this.buff_toggles) {
            x12.addActionListener(this.groupBuff_al);
        }
        (this.groupBuff_noneBtn = UI.makeButton("None")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.groupBuff_al.lock();
                for (final JToggleButton x : CatusFrame.this.buff_toggles) {
                    x.setSelected(false);
                }
                CatusFrame.this.groupBuff_al.unlock();
                CatusFrame.this.groupBuff_al.action(null);
            }
        });
        (this.groupBuff_selfBtn = UI.makeButton("Self")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this._setBuffs_self();
                CatusFrame.this.groupBuff_al.action(null);
            }
        });
        (this.groupBuff_allBtn = UI.makeButton("All")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this._setBuffs_all();
                CatusFrame.this.groupBuff_al.action(null);
            }
        });
        final UIPanel_GridBag btnRow = new UIPanel_GridBag();
        btnRow.spacer();
        btnRow.add(this.groupBuff_noneBtn);
        btnRow.add(this.groupBuff_selfBtn);
        btnRow.add(this.groupBuff_allBtn);
        final UIPanel p8 = new UIPanel();
        p8.setLayout(new GridLayout(0, 3));
        p8.add(this.buff_stats_toggle);
        p8.add(this.buff_crit_toggle);
        p8.add(btnRow);
        p8.add(this.buff_ap_toggle);
        p8.add(this.buff_sp_toggle);
        p8.add(this.buff_mastery_toggle);
        p8.add(this.buff_meleeHaste_toggle);
        p8.add(this.buff_spellHaste_toggle);
        p8.add(this.buff_stam_toggle);
        this.groupBuffPane = new PrefPane("Group Buffs", p8, false, false, CatusFrame.DARK_GRAY_BUFF);
        (this.tempEffect_hero_combo = new UIComboBox<FeralSim.Heroism>(FeralSim.Heroism.values())).addActionListener(this.tempEffect_al);
        (this.tempEffect_hero_field = new JTextField()).setName("Heroism Time");
        this.tempEffect_hero_field.setPreferredSize(numberFieldSize);
        (this.tempEffect_hero_icon = new JLabel()).setBorder(CatusFrame.WRAP_ICON_BORDER);
        this.tempEffect_hero_icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent me) {
                CatusFrame.this.cfg.heroismDrums = !CatusFrame.this.cfg.heroismDrums;
                CatusFrame.this._updateTempEffectOptions();
            }
        });
        this.tempEffect_toggles = new JToggleButton[] { this.tempEffect_berserking_toggle = this.makeIconToggle("Troll Berserking", InterfaceIcon.BERSERKING), this.tempEffect_lifeblood_toggle = this.makeIconToggle("Lifeblood", InterfaceIcon.LIFEBLOOD) };
        for (final JToggleButton x12 : this.tempEffect_toggles) {
            x12.addActionListener(this.tempEffect_al);
        }
        this.tempEffect_spinners = new Spinner[] { this.stormlashTotemSpinner = new Spinner("Stormlash", this.api.getIconImage(InterfaceIcon.STORMLASH.slug, API.IconSize.SMALL), 0, 10), this.skullBannerSpinner = new Spinner("Skull Banner", this.api.getIconImage(InterfaceIcon.SKULL_BANNER.slug, API.IconSize.SMALL), 0, 10), this.shatteringSpinner = new Spinner("Shattering", this.api.getIconImage(InterfaceIcon.SHATTERING.slug, API.IconSize.SMALL), 0, 10), this.tricksSpinner = new Spinner("Tricks of the Trade", this.api.getIconImage(InterfaceIcon.TRICKS_OF_THE_TRADE.slug, API.IconSize.SMALL), 0, 6), this.unholyFrenzySpinner = new Spinner("Unholy Frenzy", this.api.getIconImage(InterfaceIcon.UNHOLY_FRENZY.slug, API.IconSize.SMALL), 0, 10) };
        final ChangeListener temp3 = new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent ce) {
                CatusFrame.this.tempEffect_al.actionPerformed(null);
            }
        };
        for (final Spinner x13 : this.tempEffect_spinners) {
            x13.spinner.addChangeListener(temp3);
        }
        UIPanel_GridBag p9 = new UIPanel_GridBag();
        row11 = new UIPanel();
        row11.setLayout(new GridLayout(0, 3));
        final UIPanel_GridBag temp4 = new UIPanel_GridBag();
        temp4.add(this.tempEffect_hero_icon);
        temp4.spacer(this.tempEffect_hero_combo);
        temp4.add(this.tempEffect_hero_field);
        row11.add(temp4);
        for (final JToggleButton x14 : this.tempEffect_toggles) {
            row11.add(x14);
        }
        p9.row(row11, true, 0);
        row11 = new UIPanel();
        row11.setLayout(new GridLayout(0, 3));
        for (final Spinner x15 : this.tempEffect_spinners) {
            row11.add(x15.panel);
        }
        p9.row(row11, true, 4);
        this.tempEffectPane = new PrefPane("Temporary Effects", p9, false, false, CatusFrame.DARK_GRAY_BUFF);
        this.feature_toggles = new JToggleButton[] { this.feature_setBonus_toggle = this.makeIconToggle("Override Set Bonuses", InterfaceIcon.OVERRIDE_SET_BONUSES), this.feature_averageRanges_toggle = this.makeIconToggle("Average Ranges", InterfaceIcon.AVERAGE_RANGES), this.feature_noob_toggle = this.makeIconToggle("Noob Mode", InterfaceIcon.NOOB) };
        this.feature_checks = new JCheckBox[] { this.feature_disableCrit_check = UI.makeCheck("Disable Critical Strikes"), this.feature_disablePrimalFury_check = UI.makeCheck("Disable Primary Fury"), this.feature_disableLOTP_check = UI.makeCheck("Disable Leader of the Pack"), this.feature_disableGlancing_check = UI.makeCheck("Disable Glancing Blows"), this.feature_disableRefund_check = UI.makeCheck("Disable Miss Refund"), this.feature_disableOOC_check = UI.makeCheck("Disable Clearcasting"), this.feature_disableMastery_check = UI.makeCheck("Disable Mastery"), this.feature_disableArmorSpec_check = UI.makeCheck("Disable Armor Specialization"), this.feature_smartFoN_check = UI.makeCheck("FoN from Behind"), this.feature_disableBerserk_check = UI.makeCheck("Disable Berserk"), this.feature_allowTFDuringBerserk_check = UI.makeCheck("Allow TF during Berserk"), this.feature_enableBerserkPerk_check = UI.makeCheck("WoD Berserk Perk"), this.feature_disableRipExtend_check = UI.makeCheck("Disable Rip Extensions"), this.feature_properRipExtend_check = UI.makeCheck("Proper Rip Extensions"), this.feature_localRipExtend_check = UI.makeCheck("Per-target Rip Extensions"), this.feature_disableBitW_check = UI.makeCheck("Disable BitW"), this.feature_disableRacials_check = UI.makeCheck("Disable Racials"), this.feature_wodRacials_check = UI.makeCheck("WoD Racials"), this.feature_disable0ComboPS_check = UI.makeCheck("Prevent 0-Combo PS") };
        final ArrayList<JToggleButton> temp5 = new ArrayList<JToggleButton>();
        Collections.addAll(temp5, this.feature_toggles);
        Collections.addAll(temp5, this.feature_checks);
        this.feature_options = temp5.toArray(new JToggleButton[temp5.size()]);
        this.feature_enableBerserkPerk_check.setForeground(QualityT.ARTIFACT.color);
        this.feature_wodRacials_check.setForeground(QualityT.ARTIFACT.color);
        final ActionListener updateStats = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this._updateStats();
            }
        };
        this.feature_disableArmorSpec_check.addActionListener(updateStats);
        this.feature_disableMastery_check.addActionListener(updateStats);
        this.feature_disableCrit_check.addActionListener(updateStats);
        this.feature_disableBerserk_check.addActionListener(updateStats);
        this.feature_wodRacials_check.addActionListener(updateStats);
        this.feature_disableRacials_check.addActionListener(updateStats);
        UI.onChange_enable(false, this.feature_disableBerserk_check, this.feature_allowTFDuringBerserk_check, this.feature_enableBerserkPerk_check);
        UI.onChange_enable(false, this.feature_disableRacials_check, this.feature_wodRacials_check);
        UI.onChange_enable(false, this.feature_disableCrit_check, this.feature_disablePrimalFury_check);
        UI.onChange_enable(false, this.feature_disableRipExtend_check, this.feature_properRipExtend_check, this.feature_localRipExtend_check);
        this.feature_setBonus_toggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this._rebuildGear();
            }
        });
        this.feature_noob_toggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this._updateNoobMode();
                CatusFrame.this._updateConfigOptions();
                CatusFrame.this._rebuildGear();
            }
        });
        UIPanel_GridBag p10 = new UIPanel_GridBag();
        row13 = new UIPanel();
        row13.setLayout(new GridLayout(0, 3, 4, 0));
        for (final JToggleButton x14 : this.feature_toggles) {
            row13.add(x14);
        }
        p10.row(row13, true, 4);
        row13 = new UIPanel();
        row13.setLayout(new GridLayout(0, 3, 4, 0));
        for (final JCheckBox x16 : this.feature_checks) {
            row13.add(x16);
        }
        p10.row(row13, true, 4);
        (this.registerCatus_btn = UI.makeButton("Register")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (CatusFrame.this.catus_id == null) {
                    final DialogRegister r = new DialogRegister(CatusFrame.this, CatusFrame.this.hc, CatusFrame.this.catus_email);
                    r.go();
                    if (r.id != null) {
                        CatusFrame.this.catus_id = r.id;
                        CatusFrame.this.catus_email = r.email;
                        CatusFrame.this.savePrefs_catusStuff(true);
                        CatusFrame.this.updateRegisteredState();
                    }
                }
                else if (UI.isAltKeyDown(e)) {
                    new DialogText(CatusFrame.this, "Export: CatusId", "OK").showText(CatusFrame.this.catus_id);
                }
                else if (JOptionPane.showConfirmDialog(CatusFrame.this, "Are you sure you want to unregister?", "Warning: Unregister Catus", 0, 2) == 0) {
                    try {
                        new DialogProg(CatusFrame.this, "Unregistering Catus").execute(new Runnable() {
                            @Override
                            public void run() {
                                final Pair<String, Boolean> r = CatusFrame.this.hc.getStr("http://raffy.antistupid.com/wow/catus/unregister.php?id=" + CatusFrame.this.catus_id, null, -1, 1);
                                if (r.cdr) {
                                    throw new RuntimeException("Unexpected error occurred while unregistering.  Try again later.");
                                }
                            }
                        });
                        CatusFrame.this.catus_id = null;
                        CatusFrame.this.savePrefs_catusStuff(true);
                        CatusFrame.this.updateRegisteredState();
                    }
                    catch (RuntimeException err) {
                        CatusFrame.this.showError("Unregister Catus Error", err);
                    }
                }
            }
        });
        final JButton cache_btn = UI.makeButton("Reveal Cache");
        cache_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                Utils.openFile(CatusFrame.this.hc.cacheDir);
            }
        });
        final JButton defaults_btn = UI.makeButton("Reset Preferences");
        defaults_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (0 != JOptionPane.showConfirmDialog(CatusFrame.this, "Are you sure you want to restore Catus to its default state?  This cannot be undone.", "Reset Preferences", 0, 2)) {
                    return;
                }
                PrefHelp.silentClear(CatusFrame.PREFS);
                CatusFrame.this.savePrefs_catusStuff(false);
                CatusFrame.this.loadPrefs();
            }
        });
        UIPanel_GridBag row18 = new UIPanel_GridBag();
        row18.add(this.equip_batch_btn);
        row18.add(this.registerCatus_btn);
        row18.spacer();
        row18.add(cache_btn);
        row18.add(defaults_btn);
        p10.row(row18, true, 4);
        this.featurePane = new PrefPane("Internal Features", p10, false, false, null);
        this.debuff_toggles = new JToggleButton[] { this.debuff_bleed_toggle = this.makeIconToggle("Always Bleeding", InterfaceIcon.DEBUFF_BLEED), this.debuff_melee_toggle = this.makeIconToggle("+Melee Damage", InterfaceIcon.DEBUFF_MELEE), this.debuff_spell_toggle = this.makeIconToggle("+Spell Damage", InterfaceIcon.DEBUFF_SPELL) };
        for (final JToggleButton x12 : this.debuff_toggles) {
            x12.addActionListener(this.debuff_al);
        }
        (this.debuff_armor_combo = new UIComboBox<FeralSim.WeakenedArmor>(FeralSim.WeakenedArmor.values())).addActionListener(this.debuff_al);
        grid6 = _makePanel();
        grid6.setLayout(new GridLayout(0, 4, 4, 4));
        grid6.add(wrapIcon(this.api.getIconImage(InterfaceIcon.FF.slug, API.IconSize.SMALL), this.debuff_armor_combo));
        grid6.add(this.debuff_bleed_toggle);
        grid6.add(this.debuff_melee_toggle);
        grid6.add(this.debuff_spell_toggle);
        this.debuffPane = new PrefPane("Debuffs", grid6, false, false, CatusFrame.DARK_GRAY_BUFF);
        this.hotw_wrath_toggle = this.makeIconToggle("Wrath", InterfaceIcon.WRATH);
        this.hotw_hurricane_toggle = this.makeIconToggle("Hurricane", InterfaceIcon.HURRICANE);
        this.hotw_swap_toggle = this.makeIconToggle("Swap Weapon", InterfaceIcon.ATTACK);
        this.hotw_beforeBerserk_toggle = this.makeIconToggle("Use before Berserk", InterfaceIcon.BERSERK);
        this.hotw_bitw_toggle = this.makeIconToggle("Maintain Rip during BitW", InterfaceIcon.FB);
        al = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this._updateHotWOptions();
            }
        };
        this.hotw_wrath_toggle.addActionListener(al);
        this.hotw_hurricane_toggle.addActionListener(al);
        this.hotw_swap_toggle.addActionListener(al);
        this.hotw_beforeBerserk_toggle.addActionListener(al);
        this.hotw_bitw_toggle.addActionListener(al);
        p10 = new UIPanel_GridBag();
        final JPanel grid7 = _makePanel();
        grid7.setLayout(new GridLayout(0, 3, 4, 0));
        grid7.add(this.hotw_wrath_toggle);
        grid7.add(this.hotw_hurricane_toggle);
        grid7.add(UI.gap());
        grid7.add(this.hotw_swap_toggle);
        grid7.add(this.hotw_bitw_toggle);
        grid7.add(this.hotw_beforeBerserk_toggle);
        p10.row(grid7, true, 0);
        this.hotw_pane = new PrefPane("Talent Options: Heart of the Wild", p10, false, false, null);
        this.pre_castSR_toggle = this.makeIconToggle("Savage Roar", InterfaceIcon.SAVAGE_ROAR);
        this.pre_castHT_toggle = this.makeIconToggle("Healing Touch", InterfaceIcon.HEALING_TOUCH);
        this.pre_procReset_combo = new UIComboBox<NamedThing.Int>(new NamedThing.Int("Default Proc Reset", -1), (NamedThing.Int[])new Object[] { new NamedThing.Int("0 seconds", 0), new NamedThing.Int("5 seconds", 5000), new NamedThing.Int("10 seconds", 10000), new NamedThing.Int("15 seconds", 15000), new NamedThing.Int("30 seconds", 30000), new NamedThing.Int("60 seconds", 60000), new NamedThing.Int("90 seconds", 90000), new NamedThing.Int("2 minutes", 120000), new NamedThing.Int("5 minutes", 300000), new NamedThing.Int("10 minutes", 600000), new NamedThing.Int("15 minutes", 900000), new NamedThing.Int("Eternity", 1000000), new NamedThing.Int("Random", -2) });
        this.pre_opener_combo = new UIComboBox<FeralSim.Opener>(FeralSim.Opener.values());
        this.pre_finisher0_combo = new UIComboBox<FeralSim.Finisher0>(FeralSim.Finisher0.values());
        this.pre_potion0_combo = this._makePotCombo(true);
        this.pre_toggles = new JToggleButton[] { this.pre_castSR_toggle, this.pre_castHT_toggle };
        (this.pre_energy0_field = new JTextField()).setName("Custom Starting Energy");
        this.pre_energy0_field.setPreferredSize(numberFieldSize);
        this.pre_energy0_field.setHorizontalAlignment(0);
        UI.onChange_enable(this.pre_energy0_check = UI.makeCheck(this.pre_energy0_field.getName() + ": "), this.pre_energy0_field);
        p9 = new UIPanel_GridBag();
        row11 = new UIPanel();
        row11.setLayout(new GridLayout(0, 3, 4, 4));
        row11.add(wrapIcon(this.api.getIconImage(InterfaceIcon.POTION.slug, API.IconSize.SMALL), this.pre_potion0_combo));
        row11.add(wrapIcon(this.api.getIconImage(InterfaceIcon.TIME.slug, API.IconSize.SMALL), this.pre_procReset_combo));
        row11.add(wrapIcon(this.api.getIconImage(InterfaceIcon.PROWL.slug, API.IconSize.SMALL), this.pre_opener_combo));
        p9.row(row11, true, 0);
        row11 = new UIPanel();
        row11.setLayout(new GridLayout(0, 3, 4, 4));
        for (final JToggleButton x17 : this.pre_toggles) {
            row11.add(x17);
        }
        row11.add(wrapIcon(this.api.getIconImage(InterfaceIcon.FB.slug, API.IconSize.SMALL), this.pre_finisher0_combo));
        p9.row(row11, true, 0);
        row11 = new UIPanel();
        row11.add(this.pre_energy0_check);
        row11.add(this.pre_energy0_field);
        p9.row(row11, false, 0);
        this.prePane = new PrefPane("Combat Preparation", p9, false, false, CatusFrame.DARK_GRAY_BUFF);
        (this.rot_delay_field = new JTextField()).setName("Action Delay");
        this.rot_delay_field.setPreferredSize(numberFieldSize);
        this.rot_delay_field.setHorizontalAlignment(0);
        (this.rot_react_field = new JTextField()).setName("Reaction Time");
        this.rot_react_field.setPreferredSize(numberFieldSize);
        this.rot_react_field.setHorizontalAlignment(0);
        (this.rot_pool_field = new JTextField()).setName("Maximum Pool Resource Duration");
        this.rot_pool_field.setPreferredSize(numberFieldSize);
        this.rot_pool_field.setHorizontalAlignment(0);
        this.rot_generator_combo = new UIComboBox<FeralSim.Generator>(FeralSim.Generator.values());
        this.rot_thrashStyle_combo = new UIComboBox<FeralSim.ThrashStyle>(FeralSim.ThrashStyle.values());
        (this.rot_symbiosis_combo = new UIComboBox<FeralSim.Symbiosis>(FeralSim.Symbiosis.values())).setEnabled(false);
        p9 = new UIPanel_GridBag();
        row11 = new UIPanel_GridBag();
        row11.add(UI.L("Realistic Emulation: ", CatusFrame.boldFont));
        row11.gap();
        row11.add(new JLabel(this.rot_delay_field.getName() + ": "));
        row11.add(this.rot_delay_field);
        row11.gap();
        row11.add(new JLabel(this.rot_react_field.getName() + ": "));
        row11.add(this.rot_react_field);
        p9.row(row11, true, 0);
        row11 = new UIPanel_GridBag();
        row11.add(UI.L("Options: ", CatusFrame.boldFont));
        row11.gap();
        row11.add(new JLabel(this.rot_pool_field.getName() + ": "));
        row11.add(this.rot_pool_field);
        p9.row(row11, true, 0);
        row11 = new UIPanel();
        row11.setLayout(new GridLayout(0, 3, 4, 4));
        row11.add(wrapIcon(this.api.getIconImage(InterfaceIcon.SHRED.slug, API.IconSize.SMALL), this.rot_generator_combo));
        row11.add(wrapIcon(this.api.getIconImage(InterfaceIcon.THRASH.slug, API.IconSize.SMALL), this.rot_thrashStyle_combo));
        row11.add(wrapIcon(this.api.getIconImage(InterfaceIcon.SYMBIOSIS.slug, API.IconSize.SMALL), this.rot_symbiosis_combo));
        p9.row(row11, true, 4);
        this.rot_pane = new PrefPane("Rotation", p9, false, false, null);
        final String tit2 = "Damage Table";
        final String tit3 = "Damage Table Comparison";
        final JButton rake_btn = UI.makeButton("Rake");
        rake_btn.setIcon(this.api.getIconImage(InterfaceIcon.RAKE.slug, API.IconSize.SMALL));
        rake_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final FeralSim copySim = CatusFrame.this.copySim(true);
                if (copySim == null) {
                    return;
                }
                new DialogText(CatusFrame.this, "Damage Table: " + rake_btn.getText(), "OK").showText(copySim.rakeTable(CatusFrame.this.calc_assumeSR_check.isSelected(), CatusFrame.this.calc_useDebuffs_check.isSelected(), CatusFrame.this.calc_tempEffects_check.isSelected()));
            }
        });
        (this.calc_rip4_check = UI.makeCheck("Include 4 CP")).setFont(CatusFrame.tinyFont);
        this.calc_tempEffects_check = UI.makeCheck("Include Temporary Effects");
        this.calc_assumeSR_check = UI.makeCheck("Assume Savage Roar");
        this.calc_useDebuffs_check = UI.makeCheck("Use Debuffs");
        final JButton rip_btn = UI.makeButton("Rip");
        rip_btn.setIcon(this.api.getIconImage(InterfaceIcon.RIP.slug, API.IconSize.SMALL));
        rip_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final FeralSim copySim = CatusFrame.this.copySim(true);
                if (copySim == null) {
                    return;
                }
                new DialogText(CatusFrame.this, "Damage Table: " + rip_btn.getText(), "OK").showText(copySim.ripTable(CatusFrame.this.calc_rip4_check.isSelected(), CatusFrame.this.calc_assumeSR_check.isSelected(), CatusFrame.this.calc_useDebuffs_check.isSelected(), CatusFrame.this.calc_tempEffects_check.isSelected()));
            }
        });
        this.calc_thrash_spinner = new Spinner("Ticks", null, 0, 5);
        this.calc_thrash_spinner.label.setFont(CatusFrame.tinyFont);
        this.calc_rip_spinner = new Spinner("Ticks", null, 1, 8);
        this.calc_rip_spinner.label.setFont(CatusFrame.tinyFont);
        final JButton thrash_btn = UI.makeButton("Thrash");
        thrash_btn.setIcon(this.api.getIconImage(InterfaceIcon.THRASH.slug, API.IconSize.SMALL));
        thrash_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final FeralSim copySim = CatusFrame.this.copySim(true);
                if (copySim == null) {
                    return;
                }
                new DialogText(CatusFrame.this, "Damage Table: " + thrash_btn.getText(), "OK").showText(copySim.thrashTable(CatusFrame.this.calc_thrash_spinner.getValue(), CatusFrame.this.calc_assumeSR_check.isSelected(), CatusFrame.this.calc_useDebuffs_check.isSelected(), CatusFrame.this.calc_tempEffects_check.isSelected()));
            }
        });
        (this.calc_fon_btn = UI.makeButton("FoN")).setIcon(this.api.getIconImage(InterfaceIcon.FON.slug, API.IconSize.SMALL));
        this.calc_fon_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final FeralSim copySim = CatusFrame.this.copySim(true);
                if (copySim == null) {
                    return;
                }
                if (UI.isAltKeyDown(ae)) {
                    new DialogProg(CatusFrame.this, "Calculating").execute(new Runnable() {
                        @Override
                        public void run() {
                            copySim.optimizeFoN();
                        }
                    });
                    new DialogText(CatusFrame.this, "Threshold: " + CatusFrame.this.calc_fon_btn.getText(), "OK").showText(String.format("Trigger: %.0f (%.2fx)", copySim._treantRakeThresDmg, copySim._treantRakeThresDmg / copySim._treantRakeThresBase));
                }
                else {
                    new DialogText(CatusFrame.this, "Damage Table: " + CatusFrame.this.calc_fon_btn.getText(), "OK").showText(copySim.fonTable(CatusFrame.this.calc_useDebuffs_check.isSelected()));
                }
            }
        });
        final JButton mangle_btn = UI.makeButton("Mangle");
        mangle_btn.setIcon(this.api.getIconImage(InterfaceIcon.MANGLE.slug, API.IconSize.SMALL));
        mangle_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final FeralSim copySim = CatusFrame.this.copySim(true);
                if (copySim == null) {
                    return;
                }
                new DialogText(CatusFrame.this, "Damage Table: " + mangle_btn.getText(), "OK").showText(copySim.mangleTable(CatusFrame.this.calc_assumeSR_check.isSelected(), CatusFrame.this.calc_useDebuffs_check.isSelected(), CatusFrame.this.calc_tempEffects_check.isSelected()));
            }
        });
        final JButton shred_btn = UI.makeButton("Shred");
        shred_btn.setIcon(this.api.getIconImage(InterfaceIcon.SHRED.slug, API.IconSize.SMALL));
        shred_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final FeralSim copySim = CatusFrame.this.copySim(true);
                if (copySim == null) {
                    return;
                }
                new DialogText(CatusFrame.this, "Damage Table: " + shred_btn.getText(), "OK").showText(copySim.shredTable(CatusFrame.this.calc_assumeSR_check.isSelected(), CatusFrame.this.calc_useDebuffs_check.isSelected(), CatusFrame.this.calc_tempEffects_check.isSelected()));
            }
        });
        final JButton ravage_btn = UI.makeButton("Ravage");
        ravage_btn.setIcon(this.api.getIconImage(InterfaceIcon.RAVAGE.slug, API.IconSize.SMALL));
        ravage_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final FeralSim copySim = CatusFrame.this.copySim(true);
                if (copySim == null) {
                    return;
                }
                new DialogText(CatusFrame.this, "Damage Table: " + ravage_btn.getText(), "OK").showText(copySim.ravageTable(CatusFrame.this.calc_assumeSR_check.isSelected(), CatusFrame.this.calc_useDebuffs_check.isSelected(), CatusFrame.this.calc_tempEffects_check.isSelected()));
            }
        });
        final JButton swipe_btn = UI.makeButton("Swipe");
        swipe_btn.setIcon(this.api.getIconImage(InterfaceIcon.SWIPE.slug, API.IconSize.SMALL));
        swipe_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final FeralSim copySim = CatusFrame.this.copySim(true);
                if (copySim == null) {
                    return;
                }
                new DialogText(CatusFrame.this, "Damage Table: " + swipe_btn.getText(), "OK").showText(copySim.swipeTable(CatusFrame.this.calc_assumeSR_check.isSelected(), CatusFrame.this.calc_useDebuffs_check.isSelected(), CatusFrame.this.calc_tempEffects_check.isSelected()));
            }
        });
        final JButton fb_btn = UI.makeButton("Ferocious Bite");
        fb_btn.setIcon(this.api.getIconImage(InterfaceIcon.FB.slug, API.IconSize.SMALL));
        fb_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final FeralSim copySim = CatusFrame.this.copySim(true);
                if (copySim == null) {
                    return;
                }
                new DialogText(CatusFrame.this, "Damage Table: " + fb_btn.getText(), "OK").showText(copySim.fbTable(CatusFrame.this.calc_assumeSR_check.isSelected(), CatusFrame.this.calc_useDebuffs_check.isSelected(), CatusFrame.this.calc_tempEffects_check.isSelected()));
            }
        });
        final JButton mangleRake_btn = UI.makeButton("Mangle & Rake");
        mangleRake_btn.setIcon(sideBySideIcon(this.api.getIconImage(InterfaceIcon.MANGLE.slug, API.IconSize.SMALL), this.api.getIconImage(InterfaceIcon.RAKE.slug, API.IconSize.SMALL)));
        mangleRake_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final FeralSim copySim = CatusFrame.this.copySim(true);
                if (copySim == null) {
                    return;
                }
                new DialogText(CatusFrame.this, "Damage Table Comparison: " + mangleRake_btn.getText(), "OK").showText(copySim.mangleRakeTable(CatusFrame.this.calc_assumeSR_check.isSelected(), CatusFrame.this.calc_useDebuffs_check.isSelected(), CatusFrame.this.calc_tempEffects_check.isSelected()));
            }
        });
        final JButton swipeThrash_btn = UI.makeButton("Swipe & Thrash");
        swipeThrash_btn.setIcon(sideBySideIcon(this.api.getIconImage(InterfaceIcon.SWIPE.slug, API.IconSize.SMALL), this.api.getIconImage(InterfaceIcon.THRASH.slug, API.IconSize.SMALL)));
        swipeThrash_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final FeralSim copySim = CatusFrame.this.copySim(true);
                if (copySim == null) {
                    return;
                }
                new DialogText(CatusFrame.this, "Damage Table Comparison: " + swipeThrash_btn.getText(), "OK").showText(copySim.swipeThrashTable(CatusFrame.this.calc_thrash_spinner.getValue(), CatusFrame.this.calc_assumeSR_check.isSelected(), CatusFrame.this.calc_useDebuffs_check.isSelected(), CatusFrame.this.calc_tempEffects_check.isSelected()));
            }
        });
        final JButton fbRip_btn = UI.makeButton("FB & Rip");
        fbRip_btn.setIcon(sideBySideIcon(this.api.getIconImage(InterfaceIcon.FB.slug, API.IconSize.SMALL), this.api.getIconImage(InterfaceIcon.RIP.slug, API.IconSize.SMALL)));
        fbRip_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final FeralSim copySim = CatusFrame.this.copySim(true);
                if (copySim == null) {
                    return;
                }
                new DialogText(CatusFrame.this, "Damage Table Comparison: " + fbRip_btn.getText(), "OK").showText(copySim.fbRipTable(CatusFrame.this.calc_rip_spinner.getValue(), CatusFrame.this.calc_assumeSR_check.isSelected(), CatusFrame.this.calc_useDebuffs_check.isSelected(), CatusFrame.this.calc_tempEffects_check.isSelected()));
            }
        });
        final UIPanel_GridBag p11 = new UIPanel_GridBag();
        row5 = new UIPanel_GridBag();
        row5.add(UI.L("Bleed:", CatusFrame.boldFont));
        row5.gap();
        row5.add(rake_btn);
        row5.gap();
        row5.add(rip_btn);
        row5.add(this.calc_rip4_check);
        row5.gap();
        row5.add(thrash_btn);
        row5.add(this.calc_thrash_spinner.panel);
        row5.gap();
        row5.add(this.calc_fon_btn);
        p11.row(row5, false, 0);
        row5 = new UIPanel_GridBag();
        row5.add(UI.L("Melee:", CatusFrame.boldFont));
        row5.gap();
        row5.add(mangle_btn);
        row5.gap();
        row5.add(shred_btn);
        row5.gap();
        row5.add(ravage_btn);
        row5.gap();
        row5.add(fb_btn);
        row5.gap();
        row5.add(swipe_btn);
        p11.row(row5, false, 4);
        row5 = new UIPanel_GridBag();
        row5.add(UI.L("Compare:", CatusFrame.boldFont));
        row5.gap();
        row5.add(mangleRake_btn);
        row5.gap();
        row5.add(swipeThrash_btn);
        row5.gap();
        row5.add(fbRip_btn);
        row5.add(this.calc_rip_spinner.panel);
        p11.row(row5, false, 4);
        row5 = new UIPanel_GridBag();
        row5.add(this.calc_assumeSR_check);
        row5.add(this.calc_tempEffects_check);
        row5.add(this.calc_useDebuffs_check);
        p11.row(row5, false, 4);
        this.calcPane = new PrefPane("Computable Statistics", p11, false, false, null);
        this.hotfix_fields = new JTextField[] { this.hotfix_catAuto_field = new JTextField(), this.hotfix_rake_field = new JTextField(), this.hotfix_rip_field = new JTextField(), this.hotfix_thrash_field = new JTextField(), this.hotfix_ravage_field = new JTextField(), this.hotfix_shrangle_field = new JTextField(), this.hotfix_swipe_field = new JTextField(), this.hotfix_fb_field = new JTextField(), this.hotfix_sr_field = new JTextField(), this.hotfix_tf_field = new JTextField(), this.hotfix_nv_field = new JTextField(), this.hotfix_doc_field = new JTextField(), this.hotfix_wrath_field = new JTextField(), this.hotfix_hurricane_field = new JTextField() };
        this.hotfix_catAuto_field.setName("Cat Auto");
        this.hotfix_rip_field.setName("Rip");
        this.hotfix_rake_field.setName("Rake");
        this.hotfix_shrangle_field.setName("Shrangle");
        this.hotfix_ravage_field.setName("Ravage");
        this.hotfix_swipe_field.setName("Swipe");
        this.hotfix_thrash_field.setName("Thrash");
        this.hotfix_sr_field.setName("SR");
        this.hotfix_tf_field.setName("TF");
        this.hotfix_fb_field.setName("FB");
        this.hotfix_doc_field.setName("DoC");
        this.hotfix_nv_field.setName("NV");
        this.hotfix_hurricane_field.setName("Hurricane");
        this.hotfix_wrath_field.setName("Wrath");
        for (final JTextField f3 : this.hotfix_fields) {
            f3.setPreferredSize(tinyNumberFieldSize);
            f3.setHorizontalAlignment(0);
        }
        final JButton clear_btn2 = UI.makeButton("Clear");
        clear_btn2.setFont(CatusFrame.tinyFont);
        clear_btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                for (final JTextField f : CatusFrame.this.hotfix_fields) {
                    f.setText("");
                }
            }
        });
        final UIPanel grid8 = new UIPanel();
        grid8.setLayout(new GridLayout(0, 8, 2, 2));
        for (final JTextField f4 : this.hotfix_fields) {
            final JLabel lbl = new JLabel(" " + f4.getName() + ": ", 4);
            lbl.setFont(CatusFrame.tinyFont);
            grid8.add(lbl);
            grid8.add(f4);
        }
        p4 = new UIPanel_GridBag();
        row14 = new UIPanel_GridBag();
        row14.add(UI.makeBold(new JLabel("Multiplier:")));
        row14.gap();
        row14.add(grid8);
        row14.gap();
        row14.add(clear_btn2);
        p4.row(row14, false, 0);
        final JButton dump_btn = UI.makeButton("Confirm Hotfixes");
        dump_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final FeralSim sim = CatusFrame.this.copySim(false);
                if (sim == null) {
                    return;
                }
                if (!sim.hotfixed) {
                    CatusFrame.this.showError("No Hotfixes", "Nothing was hotfixed.");
                    return;
                }
                final StringBuilder sb = new StringBuilder();
                sim.dumpHotfixTable(LineWriter.buffered(sb));
                new DialogText(CatusFrame.this, "Hotfixes", "OK").showText(sb.toString());
            }
        });
        row18 = new UIPanel_GridBag();
        row18.spacer();
        row18.add(dump_btn);
        p4.row(row18, true, 4);
        this.hotfix_pane = new PrefPane("Hotfixes", p4, false, false, CatusFrame.DARK_GRAY_BUFF);
        this.combatLog_printEnergy_check = UI.makeCheck("Energy Updates");
        this.combatLog_printDebug_check = UI.makeCheck("Debug Messages");
        this.combatLog_printMore_check = UI.makeCheck("Internal State");
        UI.onChange_enable(this.combatLog_printDebug_check, this.combatLog_printMore_check);
        (this.combatLog_btn = UI.makeButton("Generate Log")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.sim_log();
            }
        });
        p9 = new UIPanel_GridBag();
        p9.setLayout(new GridBagLayout());
        p9.add(UI.L(" Print: ", CatusFrame.boldFont));
        p9.add(this.combatLog_printEnergy_check);
        p9.add(this.combatLog_printDebug_check);
        p9.add(this.combatLog_printMore_check);
        p9.spacer();
        p9.add(this.combatLog_btn);
        this.combatLog_pane = new PrefPane("Simulator: Combat Log", p9, false, false, CatusFrame.DARK_GRAY_SIMS);
        (this.sim_iter_field = new JTextField()).setName("Iterations");
        this.sim_iter_field.setPreferredSize(numberFieldSize);
        (this.sim_timeDist_field = new JTextField()).setName("Time Distribution Segments");
        this.sim_timeDist_field.setPreferredSize(numberFieldSize);
        (this.sim_err_field = new JTextField()).setName("DPS Error Threshold");
        this.sim_err_field.setPreferredSize(numberFieldSize);
        this.sim_csv_check = UI.makeCheck("CSV File");
        (this.sim_btn = UI.makeButton("Generate Distribution")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.sim_dist();
            }
        });
        p9 = new UIPanel_GridBag();
        row10 = new UIPanel_GridBag();
        row10.add(UI.makeBold(new JLabel(" " + this.sim_iter_field.getName() + ": ")));
        row10.add(this.sim_iter_field);
        row10.gap();
        row10.add(new JLabel(this.sim_err_field.getName() + ": "));
        row10.add(this.sim_err_field);
        row10.gap();
        row10.add(new JLabel(this.sim_timeDist_field.getName() + ": "));
        row10.add(this.sim_timeDist_field);
        p9.row(row10, false, 0);
        row10 = new UIPanel_GridBag();
        row10.add(UI.L("Additional Output: ", CatusFrame.boldFont));
        row10.add(this.sim_csv_check);
        p9.row(row10, false, 4);
        row10 = new UIPanel_GridBag();
        row10.spacer();
        row10.add(this.sim_btn);
        p9.row(row10, true, 4);
        this.sim_pane = new PrefPane("Simulator: Distribution", p9, false, false, CatusFrame.DARK_GRAY_SIMS);
        (this.compare_iter_field = new JTextField()).setName("Iterations");
        this.compare_iter_field.setPreferredSize(numberFieldSize);
        this.compare_settings_combo = new UIComboBox<NamedThing.Int>(new NamedThing.Int("Use Current", 0), (NamedThing.Int[])new Object[] { new NamedThing.Int("Use Recommended", 1), new NamedThing.Int("Use Configuration", 2) });
        (this.compare_armory_btn = UI.makeButton("Armory")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.compare_sim_armory(UI.isAltKeyDown(ae));
            }
        });
        (this.compare_objective_combo = new UIComboBox<NamedThing.Int>(new NamedThing.Int("Standard Iteration", 10000), (NamedThing.Int[])new Object[] { new NamedThing.Int("Custom Iteration", 0), new NamedThing.Int("Quantify", -3) })).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.compare_iter_field.setVisible(CatusFrame.this.compare_objective_combo.getPick().value == 0);
                CatusFrame.this.compare_iter_field.invalidate();
                CatusFrame.this.compare_pane.panel.validate();
            }
        });
        this.compare_iter_field.setVisible(false);
        (this.compare_config_combo = new UIComboBox<Config2>()).setWide(true);
        this.compare_config_combo.setPrototypeDisplayValue("Configuration");
        this.compare_config_combo.addActionListener(this.compare_config_combo_al);
        (this.compare_special_combo = new UIComboBox<NamedRunnable>(new NamedRunnable("Talents: SotF vs FoN") {
            @Override
            public void run() {
                CatusFrame.this.compare_talents(3, 0, 2);
            }
        }, (NamedRunnable[])new Object[] { new NamedRunnable("Talents: HotW vs DoC") {
                @Override
                public void run() {
                    CatusFrame.this.compare_talents(5, 0, 1);
                }
            }, new NamedRunnable("Talents: HotW/FoN vs DoC/SotF") {
                @Override
                public void run() {
                    CatusFrame.this.compare_talents(5, 0, 1, 3, 2, 0);
                }
            }, new NamedRunnable("Glyph: Savagery") {
                @Override
                public void run() {
                    CatusFrame.this.compare_sim(new NamedTweaker("Savagery") {
                        @Override
                        public void run() {
                            CatusFrame.this.pre_castSR_toggle.setSelected(true);
                        }
                        
                        public void postTweak(final FeralSim sim) {
                            sim.spec.glyph_sr = true;
                        }
                    }, new NamedTweaker("No Savagery") {
                        @Override
                        public void run() {
                        }
                        
                        public void postTweak(final FeralSim sim) {
                            sim.spec.glyph_sr = false;
                        }
                    });
                }
            }, new NamedRunnable("Race: " + RaceT.TROLL.name + " vs " + RaceT.WORGEN.name) {
                @Override
                public void run() {
                    CatusFrame.this.compare_sim(new NamedTweaker(RaceT.TROLL.name) {
                        @Override
                        public void run() {
                            CatusFrame.this._setRace(RaceT.TROLL);
                            CatusFrame.this.mainProfile.validate();
                            CatusFrame.this.swapProfile.validate();
                            CatusFrame.this.tempEffect_berserking_toggle.setSelected(true);
                            CatusFrame.this.updateRaceAndProfsSpecial();
                        }
                    }, new NamedTweaker(RaceT.WORGEN.name) {
                        @Override
                        public void run() {
                            CatusFrame.this._setRace(RaceT.WORGEN);
                            CatusFrame.this.mainProfile.validate();
                            CatusFrame.this.swapProfile.validate();
                        }
                    });
                }
            }, new NamedRunnable("Race: Current vs " + RaceT.TROLL.name) {
                @Override
                public void run() {
                    CatusFrame.this.compare_sim(new NamedTweaker(CatusFrame.this.mainProfile.race.name) {
                        @Override
                        public void run() {
                        }
                    }, new NamedTweaker(RaceT.TROLL.name) {
                        @Override
                        public void run() {
                            CatusFrame.this._setRace(RaceT.TROLL);
                            CatusFrame.this.mainProfile.validate();
                            CatusFrame.this.swapProfile.validate();
                            CatusFrame.this.tempEffect_berserking_toggle.setSelected(true);
                            CatusFrame.this.updateRaceAndProfsSpecial();
                        }
                    });
                }
            }, new NamedRunnable("Race: Current vs " + RaceT.WORGEN.name) {
                @Override
                public void run() {
                    CatusFrame.this.compare_sim(new NamedTweaker(CatusFrame.this.mainProfile.race.name) {
                        @Override
                        public void run() {
                        }
                    }, new NamedTweaker(RaceT.WORGEN.name) {
                        @Override
                        public void run() {
                            CatusFrame.this._setRace(RaceT.WORGEN);
                            CatusFrame.this.mainProfile.validate();
                            CatusFrame.this.swapProfile.validate();
                        }
                    });
                }
            }, new NamedRunnable("Buffs: All vs Self") {
                @Override
                public void run() {
                    CatusFrame.this.compare_sim(new NamedTweaker("All") {
                        @Override
                        public void run() {
                            CatusFrame.this._setBuffs_all();
                        }
                    }, new NamedTweaker("Self") {
                        @Override
                        public void run() {
                            CatusFrame.this._setBuffs_self();
                        }
                    });
                }
            }, new NamedRunnable("Buffs: All/Consumables vs Self/None") {
                @Override
                public void run() {
                    CatusFrame.this.compare_sim(new NamedTweaker("All/Consumables") {
                        @Override
                        public void run() {
                            CatusFrame.this._setBuffs_all();
                            if (CatusFrame.this.consume_food_combo.getSelectedIndex() == 0) {
                                CatusFrame.this.consume_food_combo.setSelectedIndex(1);
                            }
                            if (CatusFrame.this.consume_flask_combo.getSelectedIndex() == 0) {
                                CatusFrame.this.consume_flask_combo.setSelectedIndex(1);
                            }
                            if (CatusFrame.this.consume_potion_combo.getSelectedIndex() == 0) {
                                CatusFrame.this.consume_potion_combo.setSelectedIndex(1);
                            }
                            CatusFrame.this.pre_potion0_combo.setSelectedItem("Same as Consumable");
                        }
                    }, new NamedTweaker("Self/None") {
                        @Override
                        public void run() {
                            CatusFrame.this._setBuffs_self();
                            CatusFrame.this.consume_food_combo.setSelectedIndex(0);
                            CatusFrame.this.consume_flask_combo.setSelectedIndex(0);
                            CatusFrame.this.consume_potion_combo.setSelectedIndex(0);
                            CatusFrame.this.pre_potion0_combo.setSelectedItem("Same as Consumable");
                        }
                    });
                }
            }, new NamedRunnable("Pre-Potion and Potion vs None") {
                @Override
                public void run() {
                    final Object sel = CatusFrame.this.consume_potion_combo.getSelectedItem();
                    final StatValue pot = (StatValue)((sel instanceof StatValue) ? sel : CatusFrame.this.consume_potion_combo.getItemAt(1));
                    CatusFrame.this.compare_sim(new NamedTweaker(pot.toString()) {
                        @Override
                        public void run() {
                            CatusFrame.this.consume_potion_combo.setSelectedItem(pot);
                            CatusFrame.this.pre_potion0_combo.setSelectedItem(pot);
                        }
                    }, new NamedTweaker("No Potions") {
                        @Override
                        public void run() {
                            CatusFrame.this.consume_potion_combo.setSelectedIndex(0);
                            CatusFrame.this.pre_potion0_combo.setSelectedIndex(1);
                        }
                    });
                }
            }, new NamedRunnable("Pre-Potion vs None") {
                @Override
                public void run() {
                    final Object sel = CatusFrame.this.consume_potion_combo.getSelectedItem();
                    final StatValue pot = (StatValue)((sel instanceof StatValue) ? sel : CatusFrame.this.consume_potion_combo.getItemAt(1));
                    CatusFrame.this.compare_sim(new NamedTweaker(pot.toString()) {
                        @Override
                        public void run() {
                            CatusFrame.this.pre_potion0_combo.setSelectedItem(pot);
                        }
                    }, new NamedTweaker("No Potion") {
                        @Override
                        public void run() {
                            CatusFrame.this.pre_potion0_combo.setSelectedIndex(1);
                        }
                    });
                }
            }, new NamedRunnable("Pre-Healing Touch (DoC)") {
                @Override
                public void run() {
                    if (!CatusFrame.this.spec.talent_doc) {
                        CatusFrame.this.showError(CatusFrame.this.compare_pane.name, "Requires talent Dream of Cenarius.");
                        return;
                    }
                    CatusFrame.this.compare_sim(new NamedTweaker("Healing Touch") {
                        @Override
                        public void run() {
                            CatusFrame.this.pre_castHT_toggle.setSelected(true);
                        }
                    }, new NamedTweaker("No Healing Touch") {
                        @Override
                        public void run() {
                            CatusFrame.this.pre_castHT_toggle.setSelected(false);
                        }
                    });
                }
            }, new NamedRunnable("Rune: Red vs Yellow") {
                @Override
                public void run() {
                    if (!CatusFrame.this.reforgeRune_stateRow.isVisible()) {
                        CatusFrame.this.showError(CatusFrame.this.compare_pane.name, "Requires Rune of Re-Origination.");
                        return;
                    }
                    CatusFrame.this.compare_sim(new NamedTweaker("Red") {
                        @Override
                        public void run() {
                            CatusFrame.this.reforger111_gems(Reforger111.FeralGems.RED);
                        }
                    }, new NamedTweaker("Yellow") {
                        @Override
                        public void run() {
                            CatusFrame.this.reforger111_gems(Reforger111.FeralGems.YELLOW);
                        }
                    });
                }
            }, new NamedRunnable("Rune: Red vs Orange") {
                @Override
                public void run() {
                    if (!CatusFrame.this.reforgeRune_stateRow.isVisible()) {
                        CatusFrame.this.showError(CatusFrame.this.compare_pane.name, "Requires Rune of Re-Origination.");
                        return;
                    }
                    CatusFrame.this.compare_sim(new NamedTweaker("Red") {
                        @Override
                        public void run() {
                            CatusFrame.this.reforger111_gems(Reforger111.FeralGems.RED);
                        }
                    }, new NamedTweaker("Orange") {
                        @Override
                        public void run() {
                            CatusFrame.this.reforger111_gems(Reforger111.FeralGems.ORANGE);
                        }
                    });
                }
            }, new NamedRunnable("Rune: Orange vs Yellow") {
                @Override
                public void run() {
                    if (!CatusFrame.this.reforgeRune_stateRow.isVisible()) {
                        CatusFrame.this.showError(CatusFrame.this.compare_pane.name, "Requires Rune of Re-Origination.");
                        return;
                    }
                    CatusFrame.this.compare_sim(new NamedTweaker("Orange") {
                        @Override
                        public void run() {
                            CatusFrame.this.reforger111_gems(Reforger111.FeralGems.ORANGE);
                        }
                    }, new NamedTweaker("Yellow") {
                        @Override
                        public void run() {
                            CatusFrame.this.reforger111_gems(Reforger111.FeralGems.YELLOW);
                        }
                    });
                }
            }, new NamedRunnable("Meta: Capacitive vs Agile") {
                @Override
                public void run() {
                    CatusFrame.this.compare_sim(new NamedTweaker("Capacitive") {
                        @Override
                        public void run() {
                            CatusFrame.this.mainProfile.HEAD.setGemAt(0, (Gem)CatusFrame.this.api.loadItem(95346));
                        }
                    }, new NamedTweaker("Agile") {
                        @Override
                        public void run() {
                            CatusFrame.this.mainProfile.HEAD.setGemAt(0, (Gem)CatusFrame.this.api.loadItem(76884));
                        }
                    });
                }
            }, new NamedRunnable("Weapon: Current vs Swap (Reforged)") {
                @Override
                public void run() {
                    if (CatusFrame.this.reforgeRune_stateRow.isVisible()) {
                        CatusFrame.this.compare_sim(new NamedTweaker(CatusFrame.this.mainProfile.getWeaponStr()) {
                            @Override
                            public void run() {
                            }
                        }, new NamedTweaker(CatusFrame.this.swapProfile.getWeaponStr()) {
                            @Override
                            public void run() {
                                CatusFrame.this.swapWeapon();
                                CatusFrame.this.reforger111_gems(CatusFrame.this.reforgeRune_gem_combo.getPick());
                            }
                        });
                    }
                    else {
                        CatusFrame.this.compare_sim(new NamedTweaker(CatusFrame.this.mainProfile.getWeaponStr()) {
                            @Override
                            public void run() {
                            }
                        }, new NamedTweaker(CatusFrame.this.swapProfile.getWeaponStr()) {
                            @Override
                            public void run() {
                                CatusFrame.this.swapWeapon();
                                final ReforgerMax r = CatusFrame.this.reforgerMax_extract();
                                if (r == null) {
                                    return;
                                }
                                CatusFrame.this.doReforge(r, "Maximize");
                            }
                        });
                    }
                }
            }, new NamedRunnable("Weapon: Current vs Swap (No Reforge)") {
                @Override
                public void run() {
                    CatusFrame.this.compare_sim(new NamedTweaker(CatusFrame.this.mainProfile.getWeaponStr()) {
                        @Override
                        public void run() {
                        }
                    }, new NamedTweaker(CatusFrame.this.swapProfile.getWeaponStr()) {
                        @Override
                        public void run() {
                            CatusFrame.this.swapWeapon();
                        }
                    });
                }
            }, new NamedRunnable("WoD Perk: Berserk") {
                @Override
                public void run() {
                    CatusFrame.this.compare_sim(new NamedTweaker("No Perk") {
                        @Override
                        public void run() {
                            CatusFrame.this.feature_enableBerserkPerk_check.setSelected(false);
                        }
                    }, new NamedTweaker("Perk") {
                        @Override
                        public void run() {
                            CatusFrame.this.feature_enableBerserkPerk_check.setSelected(true);
                        }
                    });
                }
            }, new NamedRunnable("Mangle vs Shred") {
                @Override
                public void run() {
                    CatusFrame.this.compare_sim(new NamedTweaker("Mangle") {
                        @Override
                        public void run() {
                            CatusFrame.this.rot_generator_combo.setSelectedItem(FeralSim.Generator.MANGLE);
                        }
                    }, new NamedTweaker("Shred") {
                        @Override
                        public void run() {
                            CatusFrame.this.rot_generator_combo.setSelectedItem(FeralSim.Generator.SHRED);
                        }
                    });
                }
            }, new NamedRunnable(FeralSim.ThrashStyle.NEVER + " vs " + FeralSim.ThrashStyle.MAINTAIN) {
                @Override
                public void run() {
                    CatusFrame.this.compare_sim(new NamedTweaker(FeralSim.ThrashStyle.NEVER.toString()) {
                        @Override
                        public void run() {
                            CatusFrame.this.rot_thrashStyle_combo.setSelectedItem(FeralSim.ThrashStyle.NEVER);
                        }
                    }, new NamedTweaker(FeralSim.ThrashStyle.MAINTAIN.toString()) {
                        @Override
                        public void run() {
                            CatusFrame.this.rot_thrashStyle_combo.setSelectedItem(FeralSim.ThrashStyle.MAINTAIN);
                        }
                    });
                }
            } })).setWide(true);
        this.compare_special_combo.setPrototypeDisplayValue("Special");
        UI.setComboText(this.compare_special_combo);
        this.compare_special_combo.addActionListener(new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                final NamedRunnable r = CatusFrame.this.compare_special_combo.getPick();
                CatusFrame.this.compare_special_combo.hidePopup();
                this.lock();
                UI.setComboText(CatusFrame.this.compare_special_combo);
                this.unlock();
                if (r != null) {
                    r.run();
                }
            }
        });
        p9 = new UIPanel_GridBag();
        row10 = new UIPanel_GridBag();
        row10.add(UI.L("Objective: ", CatusFrame.boldFont));
        row10.add(this.compare_objective_combo);
        row10.add(this.compare_iter_field);
        row10.gap();
        row10.add(UI.L("Settings: ", CatusFrame.boldFont));
        row10.add(this.compare_settings_combo);
        p9.row(row10, true, 0);
        row10 = new UIPanel_GridBag();
        row10.spacer();
        row10.add(UI.L("Benchmark: ", CatusFrame.boldFont));
        row10.add(this.compare_special_combo);
        row10.add(this.compare_config_combo);
        row10.add(this.compare_armory_btn);
        p9.row(row10, true, 4);
        this.compare_pane = new PrefPane("Simulator: Quick Compare", p9, false, false, CatusFrame.DARK_GRAY_SIMS);
        this.weight_cores_combo = this._createCoresCombo(true);
        (this.weight_iter_field = new JTextField()).setName("Iterations");
        this.weight_iter_field.setPreferredSize(numberFieldSize);
        (this.weight_delta_field = new JTextField()).setName("Delta");
        this.weight_delta_field.setPreferredSize(numberFieldSize);
        (this.weight_hitMax_field = new JTextField()).setName("Hit Max");
        this.weight_hitMax_field.setPreferredSize(numberFieldSize);
        (this.weight_expMax_field = new JTextField()).setName("Expertise Max");
        this.weight_expMax_field.setPreferredSize(numberFieldSize);
        this.weight_exp_combo = new UIComboBox<String>("Decrease", (String[])new Object[] { "Both", "Increase" });
        this.weight_includes = new StatCheck[] { this.weight_includeHit_check = new StatCheck(StatT.HIT, "Hit: "), this.weight_includeExp_check = new StatCheck(StatT.EXP, "Expertise: "), this.weight_includeMastery_check = new StatCheck(StatT.MASTERY), this.weight_includeHaste_check = new StatCheck(StatT.HASTE), this.weight_includeCrit_check = new StatCheck(StatT.CRIT), this.weight_includeAgi_check = new StatCheck(StatT.AGI), this.weight_includeStr_check = new StatCheck(StatT.STR), this.weight_includeInt_check = new StatCheck(StatT.INT), this.weight_includeAP_check = new StatCheck(StatT.AP), this.weight_includeSP_check = new StatCheck(StatT.SP), this.weight_includeWeapDmg_check = new StatCheck(StatT.WDMG) };
        this.weight_forceHit_check = UI.makeCheck("Force Cap");
        this.weight_forceExp_check = UI.makeCheck("Force Cap");
        this.weight_includeHit_check.setFont(CatusFrame.boldFont);
        this.weight_includeExp_check.setFont(CatusFrame.boldFont);
        UI.onChange_enable(this.weight_includeExp_check, this.weight_exp_combo);
        (this.weight_hit_lbl = new JLabel()).setForeground(CatusFrame.DARK_GREEN);
        (this.weight_exp_lbl = new JLabel()).setForeground(CatusFrame.DARK_GREEN);
        (this.weight_space_btn = UI.makeButton("Sample Space")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.weights_space();
            }
        });
        (this.weight_sim_btn = UI.makeButton("Generate Weights")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.weights_sim((ae.getModifiers() & 0x8) > 0);
            }
        });
        this.weight_inert_check = UI.makeCheck("Inert");
        UI.onChange(this.weight_group_check = UI.makeCheck("Group"), new Handler<JCheckBox>() {
            @Override
            public void handle(final JCheckBox x) {
                final boolean b = !x.isSelected();
                CatusFrame.this.weight_includeMastery_check.setVisible(b);
                CatusFrame.this.weight_includeHaste_check.setVisible(b);
                CatusFrame.this.weight_includeCrit_check.setVisible(b);
            }
        });
        p9 = new UIPanel_GridBag();
        row10 = new UIPanel_GridBag();
        row10.add(UI.makeBold(new JLabel(this.weight_iter_field.getName() + ": ")));
        row10.add(this.weight_iter_field);
        row10.gap();
        row10.add(new JLabel(this.weight_delta_field.getName() + ": "));
        row10.add(this.weight_delta_field);
        row10.add(this.weight_inert_check);
        p9.row(row10, false, 0);
        row10 = new UIPanel_GridBag();
        row10.add(this.weight_includeHit_check);
        row10.add(this.weight_hit_lbl);
        row10.gap();
        row10.add(new JLabel(" Cap: "));
        row10.add(this.weight_hitMax_field);
        row10.add(this.weight_forceHit_check);
        p9.row(row10, false, 4);
        row10 = new UIPanel_GridBag();
        row10.add(this.weight_includeExp_check);
        row10.add(this.weight_exp_lbl);
        row10.gap();
        row10.add(new JLabel(" Direction: "));
        row10.add(this.weight_exp_combo);
        row10.gap();
        row10.add(new JLabel("Soft-cap: "));
        row10.add(this.weight_expMax_field);
        row10.add(this.weight_forceExp_check);
        p9.row(row10, false, 4);
        row10 = new UIPanel_GridBag();
        row10.add(this.weight_includeAgi_check);
        row10.add(this.weight_includeStr_check);
        row10.add(this.weight_includeAP_check);
        row10.add(this.weight_includeWeapDmg_check);
        row10.add(this.weight_includeInt_check);
        row10.add(this.weight_includeSP_check);
        p9.row(row10, false, 4);
        row10 = new UIPanel_GridBag();
        row10.add(UI.L("Secondaries: ", CatusFrame.boldFont));
        row10.add(this.weight_includeMastery_check);
        row10.add(this.weight_includeHaste_check);
        row10.add(this.weight_includeCrit_check);
        row10.add(this.weight_group_check);
        p9.row(row10, false, 4);
        row10 = new UIPanel_GridBag();
        row10.add(this.weight_space_btn);
        row10.spacer();
        row10.add(this.weight_cores_combo);
        row10.add(this.weight_sim_btn);
        p9.row(row10, true, 4);
        this.weight_pane = new PrefPane("Simulator: Stat Weights", p9, false, false, CatusFrame.DARK_GRAY_SIMS);
        (this.trink_iter_field = new JTextField()).setName("Iterations");
        this.trink_iter_field.setPreferredSize(numberFieldSize);
        this.trink_cores_combo = this._createCoresCombo(false);
        this.trink_basic_radio = UI.makeRadio("Basic: ");
        this.trink_advanced_radio = UI.makeRadio("Advanced: ");
        this.trink_basic_radio.setFont(CatusFrame.boldFont);
        this.trink_advanced_radio.setFont(CatusFrame.boldFont);
        final ButtonGroup bg2 = new ButtonGroup();
        bg2.add(this.trink_basic_radio);
        bg2.add(this.trink_advanced_radio);
        this.trink_basic_radio.setSelected(true);
        (this.trink_basic_edit_btn = UI.makeButton("Select Trinkets...")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                if (UI.isAltKeyDown(ae)) {
                    CatusFrame.this.manageSlot(SlotT.TRINKET_1);
                }
                else {
                    CatusFrame.this.trink_editBasic();
                }
            }
        });
        this.trink_basic_replace_combo = new UIComboBox<NamedThing.Int>(new NamedThing.Int("Pairwise", 0), (NamedThing.Int[])new Object[] { new NamedThing.Int("Replace Trinket 1", 1), new NamedThing.Int("Replace Trinket 2", 2) });
        this.trink_basic_upgrade_combo = new UIComboBox<NamedThing.Int>(new NamedThing.Int("No Upgrade", 0), (NamedThing.Int[])new Object[] { new NamedThing.Int("Max Upgrade", -1), new NamedThing.Int("+4 Item Level", 4), new NamedThing.Int("+8 Item Level", 8), new NamedThing.Int("+12 Item Level", 12), new NamedThing.Int("+16 Item Level", 16), new NamedThing.Int("+20 Item Level", 20), new NamedThing.Int("+24 Item Level", 24) });
        (this.trink_advanced_edit_btn = UI.makeButton("Edit Trinket Pairs...")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.trink_editAdvanced(CatusFrame.this.trink_pairs);
            }
        });
        (this.trink_advanced_useSelected_btn = UI.makeButton("Import from Selected...")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                if (UI.isAltKeyDown(ae) || CatusFrame.this.trink_editBasic()) {
                    CatusFrame.this.trink_sim(false, true);
                }
            }
        });
        UI.onChange_enable(this.trink_basic_radio, this.trink_basic_upgrade_combo, this.trink_basic_edit_btn, this.trink_basic_replace_combo);
        UI.onChange_enable(this.trink_advanced_radio, this.trink_advanced_edit_btn, this.trink_advanced_useSelected_btn);
        this.trink_ignoreErrors_check = UI.makeCheck("Ignore Unreforgable");
        this.trink_reforge_combo = new UIComboBox<NamedThing.Int>(new NamedThing.Int("Smart Reforge", 1), (NamedThing.Int[])new Object[] { new NamedThing.Int("Use Reforger Settings", 2), new NamedThing.Int("Don't Reforge", 0) });
        this.trink_report_check = UI.makeCheck("Textual Report");
        this.trink_csv_check = UI.makeCheck("CSV File");
        (this.trink_sim_btn = UI.makeButton("Generate Trinkets")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.trink_sim(UI.isAltKeyDown(ae), false);
            }
        });
        p10 = new UIPanel_GridBag();
        row12 = new UIPanel_GridBag();
        row12.add(UI.makeBold(new JLabel(this.trink_iter_field.getName() + ": ")));
        row12.add(this.trink_iter_field);
        row12.gap();
        row12.add(this.trink_reforge_combo);
        row12.add(this.trink_ignoreErrors_check);
        p10.row(row12, true, 0);
        row12 = new UIPanel_GridBag();
        row12.add(this.trink_basic_radio);
        row12.add(this.trink_basic_edit_btn);
        row12.add(this.trink_basic_upgrade_combo);
        row12.add(this.trink_basic_replace_combo);
        p10.row(row12, true, 4);
        row12 = new UIPanel_GridBag();
        row12.add(this.trink_advanced_radio);
        row12.add(this.trink_advanced_edit_btn);
        row12.add(this.trink_advanced_useSelected_btn);
        p10.row(row12, true, 4);
        row12 = new UIPanel_GridBag();
        row12.add(UI.L("Additional Output: ", CatusFrame.boldFont));
        row12.add(this.trink_report_check);
        row12.add(this.trink_csv_check);
        p10.row(row12, true, 4);
        row12 = new UIPanel_GridBag();
        row12.spacer();
        row12.add(this.trink_cores_combo);
        row12.add(this.trink_sim_btn);
        p10.row(row12, true, 4);
        this.trink_pane = new PrefPane("Simulator: Trinkets", p10, false, false, CatusFrame.DARK_GRAY_SIMS);
        (this.ilvl_iter_field = new JTextField()).setName("Iterations");
        this.ilvl_iter_field.setPreferredSize(numberFieldSize);
        (this.ilvl_lower_field = new JTextField()).setName("Lower Item Level");
        this.ilvl_lower_field.setPreferredSize(numberFieldSize);
        (this.ilvl_upper_field = new JTextField()).setName("Upper Item Level");
        this.ilvl_upper_field.setPreferredSize(numberFieldSize);
        this.ilvl_saveCSV_check = UI.makeCheck("CSV File");
        (this.ilvl_sim_btn = UI.makeButton("Generate Scaling")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.ilvl_sim(UI.isAltKeyDown(ae));
            }
        });
        p9 = new UIPanel_GridBag();
        row10 = new UIPanel_GridBag();
        row10.add(UI.makeBold(new JLabel(this.ilvl_iter_field.getName() + ": ")));
        row10.add(this.ilvl_iter_field);
        row10.gap();
        row10.add(new JLabel("From: "));
        row10.add(this.ilvl_lower_field);
        row10.add(new JLabel(" to: "));
        row10.add(this.ilvl_upper_field);
        p9.row(row10, true, 0);
        row10 = new UIPanel_GridBag();
        row10.add(UI.L("Additional Output: ", CatusFrame.boldFont));
        row10.add(this.ilvl_saveCSV_check);
        p9.row(row10, false, 4);
        row10 = new UIPanel_GridBag();
        row10.spacer();
        row10.add(this.ilvl_sim_btn);
        p9.row(row10, true, 4);
        this.ilvl_pane = new PrefPane("Simulator: Item-Level Scaling", p9, false, false, CatusFrame.DARK_GRAY_SIMS);
        (this.mirror_report_field = new JTextField()).setName("Report");
        (this.mirror_fight_combo = new UIComboBox<WLFight>()).setName("Fight");
        (this.mirror_actor_combo = new UIComboBox<WLActor>()).setName("Druid");
        (this.mirror_boss_combo = new UIComboBox<WLEnemy>()).setName("Enemy");
        this.mirror_fight_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (CatusFrame.this.mirror_lock) {
                    return;
                }
                CatusFrame.this.updateMirrorFightSelection();
            }
        });
        this.mirror_actor_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (CatusFrame.this.mirror_lock) {
                    return;
                }
                CatusFrame.this.updateMirrorActorSelection();
            }
        });
        (this.mirror_importChar_btn = UI.makeButton("Import")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final WLActor actor = CatusFrame.this.mirror_actor_combo.getPick();
                CatusFrame.this.region_combo_al.lock();
                CatusFrame.this.region_combo.setSelectedItem(actor.region);
                CatusFrame.this.region_combo_al.unlock();
                CatusFrame.this.asiaMode = actor.region.asia;
                CatusFrame.this.import_char_field.setText((actor.realm == null) ? actor.player : (actor.player + CatusFrame.JOINT_NAME_REALM_SEP + actor.realm));
                CatusFrame.this.import_char_btn.doClick();
            }
        });
        (this.mirror_viewChar_btn = UI.makeButton("Armory")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final WLActor actor = CatusFrame.this.mirror_actor_combo.getPick();
                final String realmSlug = CatusFrame.this.getRealmSlug(actor.player, actor.realm, actor.region, UI.isAltKeyDown(e));
                if (realmSlug == null) {
                    return;
                }
                try {
                    API.showArmory(actor.player, realmSlug, actor.region);
                }
                catch (RuntimeException err) {
                    CatusFrame.this.showError("Go to Armory Error", err.getMessage());
                }
            }
        });
        (this.mirror_viewDPS_btn = UI.makeButton("Damage Profile")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final WLActor actor = CatusFrame.this.mirror_actor_combo.getPick();
                final WLFight fight = CatusFrame.this.mirror_fight_combo.getPick();
                final WLEnemy boss = CatusFrame.this.mirror_boss_combo.getPick();
                Utils.openURL("http://www.warcraftlogs.com/reports/" + actor.reportId + "#type=damage-done&source=" + actor.value + "&fight=" + fight.value + "&target=" + boss.value + "&by=source");
            }
        });
        (this.mirror_viewDamageDone_btn = UI.makeButton("Ability Breakdown")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final WLActor actor = CatusFrame.this.mirror_actor_combo.getPick();
                final WLFight fight = CatusFrame.this.mirror_fight_combo.getPick();
                final WLEnemy boss = CatusFrame.this.mirror_boss_combo.getPick();
                Utils.openURL("http://www.warcraftlogs.com/reports/" + actor.reportId + "#type=damage-done&source=" + actor.value + "&fight=" + fight.value + "&target=" + boss.value);
            }
        });
        (this.mirror_viewDamageAlloc_btn = UI.makeButton("Damage Allocation")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final WLFight fight = CatusFrame.this.mirror_fight_combo.getPick();
                final WLActor actor = CatusFrame.this.mirror_actor_combo.getPick();
                final WLEnemy boss = CatusFrame.this.mirror_boss_combo.getPick();
                Utils.openURL("http://www.warcraftlogs.com/reports/" + actor.reportId + "#type=damage-taken&hostility=1&fight=" + fight.value + "&target=" + actor.value);
            }
        });
        (this.mirror_viewAuras_btn = UI.makeButton("Uptimes")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final WLActor actor = CatusFrame.this.mirror_actor_combo.getPick();
                final WLFight fight = CatusFrame.this.mirror_fight_combo.getPick();
                Utils.openURL("http://www.warcraftlogs.com/reports/" + actor.reportId + "#type=auras&source=" + actor.value + "&fight=" + fight.value + "&spells=buffs.debuffs");
            }
        });
        (this.mirror_viewReport_btn = UI.makeButton("Go to Report")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String reportId = CatusFrame.this.parseReportId();
                if (reportId == null) {
                    return;
                }
                Utils.openURL("http://www.warcraftlogs.com/reports/" + reportId);
            }
        });
        (this.mirror_report_recent_combo = new UIComboBox<NamedThing.Str>()).setWide(true);
        this.mirror_report_recent_combo.setPrototypeDisplayValue("Recent");
        this.mirror_report_recent_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (CatusFrame.this.mirror_lock) {
                    return;
                }
                final NamedThing.Str sel = CatusFrame.this.mirror_report_recent_combo.getPick();
                CatusFrame.this.mirror_lock = true;
                UI.setComboText(CatusFrame.this.mirror_report_recent_combo);
                CatusFrame.this.mirror_lock = false;
                CatusFrame.this.mirror_report_recent_combo.hidePopup();
                if (sel != null) {
                    CatusFrame.this.mirror_report_field.setText((String)sel.obj);
                    CatusFrame.this.mirror_importReport_btn.doClick();
                }
            }
        });
        (this.mirror_importReport_btn = UI.makeButton("Import")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                CatusFrame.this.resetMirrorCombos();
                final String reportId = CatusFrame.this.parseReportId();
                if (reportId == null) {
                    return;
                }
                CatusFrame.this.mirror_report_field.setText(reportId);
                final AtomicBoolean aborted = new AtomicBoolean();
                ArrayList<WLActor> actors;
                try {
                    final boolean force = UI.isAltKeyDown(e);
                    final boolean skipSpecCheck = UI.isShiftKeyDown(e);
                    final DialogProg dp = new DialogProg(CatusFrame.this, "Downloading Report" + (force ? " (Forced)" : ""));
                    dp.setAbortOnClose(aborted);
                    actors = dp.compute((DialogProg.Getter<ArrayList<WLActor>>)new DialogProg.Getter<ArrayList<WLActor>>() {
                        @Override
                        public ArrayList<WLActor> get() {
                            final int mode = force ? 1 : 0;
                            final String url_prefix = "http://www.warcraftlogs.com/reports/";
                            Pair<String, Boolean> r = CatusFrame.this.hc.getStr(url_prefix + "fights_and_participants/" + reportId + "/0", reportId + "-Report.json", mode);
                            if (r.cdr) {
                                throw new RuntimeException("Unknown report identifier.");
                            }
                            final String fightJSON = r.car;
                            r = CatusFrame.this.hc.getStr(url_prefix + reportId, reportId + "-Report.html", mode);
                            if (r.cdr) {
                                throw new RuntimeException("Unable to import report HTML.");
                            }
                            final String reportHTML = r.car;
                            RegionT region = CatusFrame.this.region_combo.getPick();
                            String realm0 = null;
                            final String prefix = "report-guild-container";
                            int pos = reportHTML.indexOf(prefix);
                            if (pos >= 0) {
                                pos += prefix.length();
                                final int end = reportHTML.indexOf("</div>", pos);
                                if (end >= 0) {
                                    String temp = reportHTML.substring(pos, end).trim();
                                    pos = temp.lastIndexOf("\">");
                                    if (pos >= 0 && temp.endsWith("</a>")) {
                                        temp = temp.substring(pos + 2, temp.length() - 5);
                                        pos = temp.indexOf("(");
                                        if (pos >= 0) {
                                            realm0 = temp.substring(0, pos).trim();
                                            temp = temp.substring(pos + 1);
                                            for (final RegionT x : RegionT.values()) {
                                                if (x.name().equalsIgnoreCase(temp)) {
                                                    region = x;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            final ArrayList<WLActor> actors = new ArrayList<WLActor>();
                            final HashMap<Integer, WLFight> fightMap = new HashMap<Integer, WLFight>();
                            final HashMap<Integer, ArrayList<WLEnemy>> fightEnemyMap = new HashMap<Integer, ArrayList<WLEnemy>>();
                            try {
                                final JSONObject root = (JSONObject)JSONValue.parse(fightJSON);
                                for (final Object actorObj : root.get("enemies")) {
                                    final JSONObject actorInfo = (JSONObject)actorObj;
                                    final String type = JSONHelp.reqStr(actorInfo, "type");
                                    final String name = JSONHelp.reqStr(actorInfo, "name");
                                    final int id = JSONHelp.reqInt(actorInfo, "id");
                                    final int guid = JSONHelp.reqInt(actorInfo, "guid");
                                    int bossId = 0;
                                    if ("Boss".equalsIgnoreCase(type)) {
                                        final String bossIds = JSONHelp.reqStr(actorInfo, "fights");
                                        for (String x2 : bossIds.split("\\.")) {
                                            x2 = x2.trim();
                                            if (!x2.isEmpty()) {
                                                try {
                                                    final int bid = Integer.parseInt(x2);
                                                    if (bid > 0) {
                                                        bossId = bid;
                                                        break;
                                                    }
                                                }
                                                catch (RuntimeException ex) {}
                                            }
                                        }
                                    }
                                    final WLEnemy enemy = new WLEnemy(name, id, guid, bossId);
                                    final String fightIds = JSONHelp.reqStr(actorInfo, "fights");
                                    for (String x3 : fightIds.split("\\.")) {
                                        x3 = x3.trim();
                                        if (!x3.isEmpty()) {
                                            try {
                                                final int fid = Integer.parseInt(x3);
                                                if (fid > 0) {
                                                    ArrayList<WLEnemy> list = fightEnemyMap.get(fid);
                                                    if (list == null) {
                                                        list = new ArrayList<WLEnemy>();
                                                        fightEnemyMap.put(fid, list);
                                                    }
                                                    list.add(enemy);
                                                }
                                            }
                                            catch (RuntimeException ex2) {}
                                        }
                                    }
                                }
                                for (final Object fightObj : root.get("fights")) {
                                    final JSONObject fightInfo = (JSONObject)fightObj;
                                    final int id2 = JSONHelp.reqInt(fightInfo, "id");
                                    final ArrayList<WLEnemy> enemies = fightEnemyMap.get(id2);
                                    if (enemies == null) {
                                        continue;
                                    }
                                    final int t0 = JSONHelp.reqInt(fightInfo, "start_time");
                                    final int t2 = JSONHelp.reqInt(fightInfo, "end_time");
                                    if (t0 >= t2) {
                                        continue;
                                    }
                                    final String name2 = JSONHelp.reqStr(fightInfo, "name");
                                    final int bossId2 = JSONHelp.getInt(fightInfo, "boss", 0);
                                    final ArrayList<WLEnemy> targets = new ArrayList<WLEnemy>();
                                    String comp = null;
                                    String suffix = "";
                                    if (bossId2 == 0) {
                                        for (final WLEnemy x4 : enemies) {
                                            switch (x4.guId) {
                                                case 31146:
                                                case 65310:
                                                case 66374:
                                                case 67127: {
                                                    targets.add(x4);
                                                    continue;
                                                }
                                            }
                                        }
                                        suffix = " [Practice]";
                                    }
                                    else {
                                        if (!JSONHelp.getBoolean(fightInfo, "kill", false)) {
                                            suffix = " [Wipe]";
                                        }
                                        final int diff = JSONHelp.getInt(fightInfo, "difficulty", 2);
                                        final int size = JSONHelp.getInt(fightInfo, "size", 0);
                                        switch (diff) {
                                            case 1: {
                                                comp = "LFR";
                                                break;
                                            }
                                            case 2: {
                                                comp = "Flex";
                                                break;
                                            }
                                            case 3: {
                                                comp = size + "N";
                                                break;
                                            }
                                            case 4: {
                                                comp = size + "H";
                                                break;
                                            }
                                        }
                                        for (final WLEnemy x5 : enemies) {
                                            if (x5.isBoss()) {
                                                targets.add(x5);
                                            }
                                        }
                                    }
                                    if (targets.isEmpty()) {
                                        continue;
                                    }
                                    final String fightName = String.format("%s <%s>%s", (comp == null) ? name2 : (name2 + " (" + comp + ")"), Fmt.msDur(t2 - t0), suffix);
                                    final WLFight fight = new WLFight(fightName, id2, comp, t0, t2, name2, targets.toArray(new WLEnemy[targets.size()]));
                                    fightMap.put(id2, fight);
                                }
                                final HashMap<WLFight, IntSet> feralMap = new HashMap<WLFight, IntSet>();
                                if (!skipSpecCheck) {
                                    dp.proxy.determinate(0, fightMap.size());
                                    final String prefix2 = "composition-label\">DPS:";
                                    int done = 0;
                                    for (final WLFight f : fightMap.values()) {
                                        if (aborted.get()) {
                                            throw new RuntimeException("Aborted");
                                        }
                                        ++done;
                                        final String url = url_prefix + "summary/" + reportId + "/" + f.value + "/" + f.startTime + "/" + f.endTime + "/0/0/Any/0/-1/0/";
                                        String html = CatusFrame.this.hc.getStr_silent(url, reportId + "-Summary-F" + f.value + ".html", mode);
                                        dp.proxy.value(done);
                                        if (html == null) {
                                            continue;
                                        }
                                        int pos2 = html.indexOf(prefix2);
                                        if (pos2 == -1) {
                                            continue;
                                        }
                                        pos2 += prefix2.length();
                                        int end2 = html.indexOf("<tr", pos2);
                                        if (end2 == -1) {
                                            end2 = html.indexOf("</table>");
                                        }
                                        if (end2 == -1) {
                                            continue;
                                        }
                                        html = html.substring(pos2, end2);
                                        final String[] split3;
                                        final String[] players = split3 = html.split("<span");
                                        for (final String x3 : split3) {
                                            String p = "Druid-";
                                            pos2 = x3.indexOf(p);
                                            Label_1894: {
                                                if (pos2 != -1) {
                                                    pos2 += p.length();
                                                    end2 = x3.indexOf(".", pos2);
                                                    if (end2 != -1) {
                                                        final String spec = x3.substring(pos2, end2);
                                                        if (spec.equalsIgnoreCase("Feral")) {
                                                            p = "followRawLogLinkForActor(";
                                                            pos2 = x3.indexOf(p);
                                                            if (pos2 != -1) {
                                                                pos2 += p.length();
                                                                end2 = x3.indexOf(41, pos2);
                                                                if (end2 != -1) {
                                                                    final String[] args = x3.substring(pos2, end2).split(",");
                                                                    if (args.length == 7) {
                                                                        int id3;
                                                                        try {
                                                                            id3 = Integer.parseInt(args[4]);
                                                                        }
                                                                        catch (NumberFormatException err2) {
                                                                            break Label_1894;
                                                                        }
                                                                        IntSet set = feralMap.get(f);
                                                                        if (set == null) {
                                                                            set = new IntSet();
                                                                            feralMap.put(f, set);
                                                                        }
                                                                        set.add(id3);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    dp.proxy.indeterminate();
                                }
                                for (final Object actorObj2 : root.get("friendlies")) {
                                    final JSONObject actorInfo2 = (JSONObject)actorObj2;
                                    final String type2 = JSONHelp.reqStr(actorInfo2, "type");
                                    if (!"Druid".equalsIgnoreCase(type2)) {
                                        continue;
                                    }
                                    String player;
                                    final String name3 = player = JSONHelp.reqStr(actorInfo2, "name");
                                    String realm2 = realm0;
                                    final int pos3 = name3.indexOf(45);
                                    if (pos3 >= 0) {
                                        realm2 = name3.substring(pos3 + 1).trim();
                                        player = name3.substring(0, pos3).trim();
                                    }
                                    final int id4 = JSONHelp.reqInt(actorInfo2, "id");
                                    final int guid2 = JSONHelp.reqInt(actorInfo2, "guid");
                                    final String fightIds2 = JSONHelp.reqStr(actorInfo2, "fights");
                                    final ArrayList<WLFight> fights = new ArrayList<WLFight>();
                                    for (String x6 : fightIds2.split("\\.")) {
                                        x6 = x6.trim();
                                        if (!x6.isEmpty()) {
                                            try {
                                                final WLFight f2 = fightMap.get(Integer.parseInt(x6));
                                                if (f2 != null) {
                                                    final IntSet ferals = feralMap.get(f2);
                                                    if (skipSpecCheck || (ferals != null && ferals.contains(id4))) {
                                                        fights.add(f2);
                                                    }
                                                }
                                            }
                                            catch (RuntimeException ex3) {}
                                        }
                                    }
                                    if (fights.isEmpty()) {
                                        continue;
                                    }
                                    final WLActor actor = new WLActor((realm2 == null) ? player : (player + " - " + realm2), id4, guid2, player, realm2, region, fights.toArray(new WLFight[fights.size()]), reportId);
                                    actors.add(actor);
                                }
                            }
                            catch (RuntimeException err) {
                                throw new RuntimeException("JSON Error: " + err);
                            }
                            return actors;
                        }
                    });
                }
                catch (RuntimeException err) {
                    if (!aborted.get()) {
                        CatusFrame.this.showError("Import Report Error", err);
                    }
                    return;
                }
                if (actors.isEmpty()) {
                    CatusFrame.this.removeRecentReport(reportId);
                    CatusFrame.this.showError("Empty Report", "Report does not contain a Feral.");
                    return;
                }
                Collections.sort(actors, new Comparator<WLActor>() {
                    @Override
                    public int compare(final WLActor a, final WLActor b) {
                        return a.player.compareTo(b.player);
                    }
                });
                final HashSet<WLFight> fightSet = new HashSet<WLFight>();
                for (final WLActor a : actors) {
                    for (final WLFight f : a.fights) {
                        fightSet.add(f);
                    }
                }
                final WLFight[] fights = fightSet.toArray(new WLFight[fightSet.size()]);
                Arrays.sort(fights, new Comparator<WLFight>() {
                    @Override
                    public int compare(final WLFight a, final WLFight b) {
                        return a.startTime - b.startTime;
                    }
                });
                final StringBuilder sb = new StringBuilder();
                sb.append("(");
                Fmt.plural(sb, fights.length, "fight", "s");
                sb.append(") ");
                sb.append(fights[0].desc);
                if (fights.length > 1) {
                    sb.append(" to ");
                    sb.append(fights[fights.length - 1].desc);
                }
                sb.append(" w/");
                if (actors.size() < 6) {
                    boolean first = true;
                    for (final WLActor x : actors) {
                        if (first) {
                            first = false;
                        }
                        else {
                            sb.append(", ");
                        }
                        sb.append(x.player);
                    }
                }
                else {
                    sb.append(actors.size());
                    sb.append(" Druids");
                }
                sb.append(" [");
                sb.append(reportId);
                sb.append("] ");
                CatusFrame.this.mirror_lock = true;
                CatusFrame.this.removeRecentReport(reportId);
                final int max = 50;
                while (CatusFrame.this.mirror_report_recent_combo.getItemCount() > max) {
                    CatusFrame.this.mirror_report_recent_combo.removeItemAt(CatusFrame.this.mirror_report_recent_combo.getItemCount() - 1);
                }
                CatusFrame.this.mirror_report_recent_combo.insertItemAt(new NamedThing.Str(sb.toString(), reportId), 0);
                CatusFrame.this.mirror_actor_combo.removeAllItems();
                CatusFrame.this.updateMirrorEnabled(true);
                CatusFrame.this.mirror_fight_combo.setPrototypeDisplayValue(null);
                CatusFrame.this.mirror_actor_combo.setPrototypeDisplayValue(null);
                CatusFrame.this.mirror_boss_combo.setPrototypeDisplayValue(null);
                for (final WLActor x : actors) {
                    CatusFrame.this.mirror_actor_combo.addItem(x);
                }
                CatusFrame.this.updateMirrorActorSelection();
                CatusFrame.this.mirror_lock = false;
            }
        });
        this.mirror_report_field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                CatusFrame.this.mirror_importReport_btn.doClick();
            }
        });
        this.mirror_parse_check = UI.makeCheck("Load as Encounter");
        (this.mirror_cache_btn = UI.makeButton("Clear Log Cache")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (UI.isAltKeyDown(e)) {
                    final File dir = CatusFrame.this.hc.getDir_matchingURL("http://www.warcraftlogs.com");
                    Utils.openFile(dir);
                }
                else {
                    new DialogProg(CatusFrame.this, "Clearing Cache").execute(new Runnable() {
                        @Override
                        public void run() {
                            CatusFrame.this.hc.cleanCache_matchingURL("http://www.warcraftlogs.com");
                        }
                    });
                }
            }
        });
        (this.mirror_parse_btn = UI.makeButton("Parse Report")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final WLActor actor = CatusFrame.this.mirror_actor_combo.getPick();
                final WLFight fight = CatusFrame.this.mirror_fight_combo.getPick();
                final WLEnemy boss = CatusFrame.this.mirror_boss_combo.getPick();
                final AtomicBoolean aborted = new AtomicBoolean();
                String code;
                try {
                    final DialogProg dp = new DialogProg(CatusFrame.this, "Parsing Report");
                    dp.setAbortOnClose(aborted);
                    code = dp.compute((DialogProg.Getter<String>)new DialogProg.Getter<String>() {
                        @Override
                        public String get() {
                            final int mode = 0;
                            final ArrayList<Encounter_Mirror.Trigger> triggers = new ArrayList<Encounter_Mirror.Trigger>();
                            final String url_prefix = "http://www.warcraftlogs.com/reports/";
                            final String url_reportFightTime = actor.reportId + "/" + fight.value + "/" + fight.startTime + "/" + fight.endTime + "/";
                            final int duration = fight.endTime - fight.startTime;
                            String url = url_prefix + "graph/damage-taken/" + url_reportFightTime + "source/1/0/0/0/0/0/-1/0/Any/Any/0";
                            final String json = CatusFrame.this.hc.getStr_silent(url, actor.reportId + "-DamageTaken-F" + fight.value + ".json", 0);
                            if (json == null) {
                                throw new RuntimeException("Unable to download damage taken.");
                            }
                            try {
                                final JSONObject root = (JSONObject)JSONValue.parse(json);
                                final JSONArray seriesList = root.get("series");
                                JSONObject series = null;
                                for (final Object obj : seriesList) {
                                    series = (JSONObject)obj;
                                    final int id = JSONHelp.getInt(series, "id", 0);
                                    if (id == boss.value) {
                                        break;
                                    }
                                }
                                if (series == null) {
                                    throw new RuntimeException("Unable to find series for " + boss.name);
                                }
                                int start = JSONHelp.reqInt(series, "pointStart") - fight.startTime;
                                final int step = JSONHelp.reqInt(series, "pointInterval");
                                final JSONArray dataList = series.get("data");
                                final double[] vec = new double[dataList.size()];
                                int pos = 0;
                                double sum = 0.0;
                                for (final Object obj2 : dataList) {
                                    final double n = sum;
                                    final double[] array = vec;
                                    final int n2 = pos++;
                                    final double doubleValue = ((Number)obj2).doubleValue();
                                    array[n2] = doubleValue;
                                    sum = n + doubleValue;
                                }
                                if (sum < 1.0) {
                                    throw new RuntimeException(boss.name + " suffered no damage");
                                }
                                triggers.add(new Encounter_Mirror.Trigger(start, Encounter_Mirror.Type.HEALTH, (long)sum, null));
                                for (int i = 0; i < vec.length; ++i) {
                                    start += step;
                                    sum -= vec[i];
                                    final long hp = (long)sum;
                                    if (hp >= 1L) {
                                        if (start >= 0) {
                                            if (start < duration) {
                                                triggers.add(new Encounter_Mirror.Trigger(start, Encounter_Mirror.Type.HEALTH, hp, null));
                                            }
                                        }
                                    }
                                }
                            }
                            catch (RuntimeException err2) {
                                throw new RuntimeException("Unable to parse damage taken.");
                            }
                            url = ActionListener.this.url(actor, fight, "auras", 0);
                            final String auraHTML = CatusFrame.this.hc.getStr_silent(url, actor.reportId + "-AuraList-F" + fight.value + "-D" + actor.value + ".html", 0);
                            if (auraHTML == null) {
                                throw new RuntimeException("Unable to download aura list.");
                            }
                            int off = 0;
                            final IntSet spellIds = new IntSet();
                            final String prefix = " id=\"ability-";
                            while (true) {
                                final int pos2 = auraHTML.indexOf(prefix, off);
                                if (pos2 == -1) {
                                    break;
                                }
                                off = pos2 + prefix.length();
                                final int end = auraHTML.indexOf("-", off);
                                if (end == -1) {
                                    break;
                                }
                                final int spellId = Integer.parseInt(auraHTML.substring(off, end));
                                spellIds.add(spellId);
                                off = end;
                            }
                            dp.proxy.determinate(0, spellIds.count);
                            for (int j = 0; j < spellIds.count; ++j) {
                                if (aborted.get()) {
                                    throw new RuntimeException("Aborted");
                                }
                                dp.proxy.value(j + 1);
                                final int spellId2 = spellIds.member[j];
                                url = ActionListener.this.url(actor, fight, "auras_events", spellId2);
                                final String auraJSON = CatusFrame.this.hc.getStr_silent(url, actor.reportId + "-AuraEvents-F" + fight.value + "-S" + spellId2 + "-D" + actor.value + ".json", 0);
                                if (auraJSON != null) {
                                    try {
                                        final JSONObject root2 = (JSONObject)JSONValue.parse(auraJSON);
                                        final JSONArray eventList = root2.get("events");
                                        int num = 0;
                                        for (final Object x : eventList) {
                                            ++num;
                                            final JSONObject eventInfo = (JSONObject)x;
                                            int time = JSONHelp.reqInt(eventInfo, "timestamp") - fight.startTime;
                                            if (time >= 0) {
                                                if (time >= duration) {
                                                    continue;
                                                }
                                                boolean self = JSONHelp.getInt(eventInfo, "sourceID", -1) == actor.value;
                                                if (spellId2 == 120032) {
                                                    self = true;
                                                }
                                                final String type = JSONHelp.reqStr(eventInfo, "type");
                                                if (spellId2 == 137596) {
                                                    if (!type.equalsIgnoreCase("removebuff")) {
                                                        continue;
                                                    }
                                                }
                                                else if (spellId2 == 105697 && num == 1 && type.equalsIgnoreCase("removebuff")) {
                                                    time = Math.max(0, time - 25000);
                                                }
                                                else if (!type.equalsIgnoreCase("applybuff") && !type.equalsIgnoreCase("refreshbuff")) {
                                                    continue;
                                                }
                                                final JSONObject spellInfo = eventInfo.get("ability");
                                                final String desc = JSONHelp.getStr(spellInfo, "name", null);
                                                triggers.add(new Encounter_Mirror.Trigger(time, self ? Encounter_Mirror.Type.SELF : Encounter_Mirror.Type.EXTERNAL, spellId2, desc));
                                            }
                                        }
                                    }
                                    catch (RuntimeException err) {
                                        err.printStackTrace();
                                    }
                                }
                            }
                            dp.proxy.indeterminate();
                            url = url_prefix + "summons_events/" + url_reportFightTime + "0/0/Any/0/0/Any/114207/source/0/-1/0";
                            final String auraJSON2 = CatusFrame.this.hc.getStr_silent(url, actor.reportId + "-SkullBanner-F" + fight.value + ".json", 0);
                            if (auraJSON2 != null) {
                                try {
                                    final JSONObject root3 = (JSONObject)JSONValue.parse(auraJSON2);
                                    final JSONArray eventList2 = root3.get("events");
                                    for (final Object x2 : eventList2) {
                                        final JSONObject eventInfo2 = (JSONObject)x2;
                                        if (eventInfo2.isEmpty()) {
                                            continue;
                                        }
                                        final int time2 = JSONHelp.reqInt(eventInfo2, "timestamp") - fight.startTime;
                                        if (time2 < 0) {
                                            continue;
                                        }
                                        if (time2 >= duration) {
                                            continue;
                                        }
                                        triggers.add(new Encounter_Mirror.Trigger(time2, Encounter_Mirror.Type.EXTERNAL, 114207L, "Skull Banner"));
                                    }
                                }
                                catch (RuntimeException ex) {}
                            }
                            int spellId3 = 64382;
                            url = url_prefix + "auras_events/" + url_reportFightTime + "debuffs/" + spellId3 + "/0/0/0/0/source/1/-1/0/Any/Any/0";
                            String auraJSON3 = CatusFrame.this.hc.getStr_silent(url, actor.reportId + "-ShatteringThrow-F" + fight.value + ".json", 0);
                            if (auraJSON3 != null) {
                                try {
                                    final JSONObject root4 = (JSONObject)JSONValue.parse(auraJSON3);
                                    final JSONArray eventList3 = root4.get("events");
                                    for (final Object x3 : eventList3) {
                                        final JSONObject eventInfo3 = (JSONObject)x3;
                                        if (eventInfo3.isEmpty()) {
                                            continue;
                                        }
                                        final int time3 = JSONHelp.reqInt(eventInfo3, "timestamp") - fight.startTime;
                                        if (time3 < 0) {
                                            continue;
                                        }
                                        if (time3 >= duration) {
                                            continue;
                                        }
                                        final String type2 = JSONHelp.reqStr(eventInfo3, "type");
                                        if (!type2.equalsIgnoreCase("applydebuff")) {
                                            continue;
                                        }
                                        final int targetID = JSONHelp.reqInt(eventInfo3, "targetID");
                                        if (boss.value > 0 && targetID != boss.value) {
                                            continue;
                                        }
                                        triggers.add(new Encounter_Mirror.Trigger(time3, Encounter_Mirror.Type.EXTERNAL, spellId3, "Shattering Throw"));
                                    }
                                }
                                catch (RuntimeException ex2) {}
                            }
                            spellId3 = 113746;
                            url = url_prefix + "auras_events/" + url_reportFightTime + "debuffs/" + spellId3 + "/0/0/0/0/source/1/-1/0/Any/Any/0";
                            auraJSON3 = CatusFrame.this.hc.getStr_silent(url, actor.reportId + "-WeakenedArmor-F" + fight.value + ".json", 0);
                            if (auraJSON3 != null) {
                                try {
                                    final JSONObject root4 = (JSONObject)JSONValue.parse(auraJSON3);
                                    final JSONArray eventList3 = root4.get("events");
                                    for (final Object x3 : eventList3) {
                                        final JSONObject eventInfo3 = (JSONObject)x3;
                                        if (eventInfo3.isEmpty()) {
                                            continue;
                                        }
                                        final int time3 = JSONHelp.reqInt(eventInfo3, "timestamp") - fight.startTime;
                                        if (time3 < 0) {
                                            continue;
                                        }
                                        if (time3 >= duration) {
                                            continue;
                                        }
                                        final String type2 = JSONHelp.reqStr(eventInfo3, "type");
                                        if (type2.equalsIgnoreCase("applydebuff") || type2.equalsIgnoreCase("applydebuffstack")) {
                                            final boolean up = true;
                                        }
                                        else {
                                            if (!type2.equalsIgnoreCase("removedebuff")) {
                                                continue;
                                            }
                                            final boolean up = false;
                                        }
                                        final int targetID2 = JSONHelp.reqInt(eventInfo3, "targetID");
                                        if (boss.value > 0 && targetID2 != boss.value) {
                                            continue;
                                        }
                                        final int n3;
                                        triggers.add(new Encounter_Mirror.Trigger(time3, Encounter_Mirror.Type.EXTERNAL, (n3 != 0) ? spellId3 : ((long)(-spellId3)), "Weakened Armor"));
                                    }
                                }
                                catch (RuntimeException ex3) {}
                            }
                            Collections.sort(triggers, Encounter_Mirror.Trigger.CMP);
                            triggers.add(new Encounter_Mirror.Trigger(duration, Encounter_Mirror.Type.END, 0L, null));
                            return Encounter_Mirror.formatTriggers(triggers.toArray(new Encounter_Mirror.Trigger[triggers.size()]));
                        }
                    });
                }
                catch (RuntimeException err) {
                    if (!aborted.get()) {
                        CatusFrame.this.showError("Parse Report Error", err);
                    }
                    return;
                }
                if (CatusFrame.this.mirror_parse_check.isSelected()) {
                    CatusFrame.this.fight_tabs.setSelectedTitle("Mirror");
                    CatusFrame.this.fight_mirror_script = code;
                    CatusFrame.this.fight_mirror_source_lbl.setText(fight.name + " for " + actor.name);
                }
                new DialogText(CatusFrame.this, "Parsed Report", "OK").showText(code);
            }
            
            String url(final WLActor a, final WLFight f, final String fun, final int spellId) {
                return "http://www.warcraftlogs.com/reports/" + fun + "/" + a.reportId + "/" + f.value + "/" + f.startTime + "/" + f.endTime + "/buffs/" + spellId + "/" + a.value + "/0/0/0/source/0/-1/0/Any/Any/0";
            }
        });
        p9 = new UIPanel_GridBag();
        row10 = new UIPanel_GridBag();
        row10.add(this.mirror_report_recent_combo);
        row10.spacer(this.mirror_report_field);
        row10.add(this.mirror_importReport_btn);
        row10.add(this.mirror_viewReport_btn);
        p9.row(row10, true, 4);
        p9.row(new JSeparator(), true, 4);
        row10 = new UIPanel_GridBag();
        row10.add(UI.L(this.mirror_actor_combo.getName() + ": ", CatusFrame.boldFont));
        row10.add(this.mirror_actor_combo);
        row10.add(this.mirror_importChar_btn);
        row10.add(this.mirror_viewChar_btn);
        p9.row(row10, false, 4);
        row10 = new UIPanel_GridBag();
        row10.add(UI.L(this.mirror_fight_combo.getName() + ": ", CatusFrame.boldFont));
        row10.add(this.mirror_fight_combo);
        row10.add(this.mirror_viewDamageAlloc_btn);
        row10.add(this.mirror_viewAuras_btn);
        p9.row(row10, false, 4);
        row10 = new UIPanel_GridBag();
        row10.add(UI.L(this.mirror_boss_combo.getName() + ": ", CatusFrame.boldFont));
        row10.add(this.mirror_boss_combo);
        row10.add(this.mirror_viewDPS_btn);
        row10.add(this.mirror_viewDamageDone_btn);
        p9.row(row10, false, 4);
        row10 = new UIPanel_GridBag();
        row10.add(this.mirror_cache_btn);
        row10.spacer();
        row10.add(this.mirror_parse_check);
        row10.add(this.mirror_parse_btn);
        p9.row(row10, true, 4);
        this.mirror_pane = new PrefPane("Import from Warcraft Logs", p9, true, false, null);
        this.mirror_pane.desc.setText("warcraftlogs.com");
        (this.compareGear_toCode_btn = UI.makeButton("CompactGear")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final DialogText dt = new DialogText(CatusFrame.this, "Compare: CompactGear", "Compare");
                String code = CompactGear.toString(CatusFrame.this.mainProfile, true, true);
                final Profile p = new Profile();
                final ArrayList<String> errors = new ArrayList<String>();
                while (true) {
                    code = dt.editText(code);
                    if (code == null) {
                        return;
                    }
                    p.race = CatusFrame.this.mainProfile.race;
                    p.spec = CatusFrame.this.mainProfile.spec;
                    p.profs = CatusFrame.this.mainProfile.profs;
                    if (CompactGear.fromString(CatusFrame.this.api, p, code, errors, 0)) {
                        p.validate();
                        CatusFrame.this.compareGear(p, CatusFrame.this.mainProfile);
                        return;
                    }
                    CatusFrame.this.showError_html("CompactGear Error", CompactGear.formatErrors_html(errors));
                }
            }
        });
        (this.compareGear_toArmory_btn = UI.makeButton("Armory")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final Profile p = CatusFrame.this.importArmory(UI.isAltKeyDown(ae));
                if (p == null) {
                    return;
                }
                CatusFrame.this.compareGear(p, CatusFrame.this.mainProfile);
            }
        });
        (this.compareGear_config_combo = new UIComboBox<Config2>()).setWide(true);
        this.compareGear_config_combo.setPrototypeDisplayValue("Configuration");
        UI.setComboText(this.compareGear_config_combo);
        this.compareGear_config_combo.addActionListener(this.compareGear_config_combo_al);
        (this.compareGear_recent_combo = new UIComboBox<RecentArmory>()).setWide(true);
        this.compareGear_recent_combo.setPrototypeDisplayValue("Player");
        UI.setComboText(this.compareGear_recent_combo);
        this.compareGear_recent_combo.addActionListener(this.compareGear_recent_combo_al);
        UIPanel_GridBag row19 = new UIPanel_GridBag();
        row19.add(UI.makeBold(new JLabel("Compare to: ")));
        row19.gap_add(4, this.compareGear_toCode_btn);
        row19.gap_add(4, this.compareGear_config_combo);
        row19.gap_add(4, this.compareGear_recent_combo);
        row19.gap_add(4, this.compareGear_toArmory_btn);
        this.compareGearPane = new PrefPane("Gear Compare", row19, false, false, CatusFrame.DARK_GRAY_INFO);
        this.prefPanes = new PrefPane[] { this.import_pane, this.raceAndProf_pane, this.gearPane, this.swapPane, this.setBonusPane, this.statPane, this.gemEnchantTinkerPane, this.compareGearPane, this.reforgePane, this.regemPane, this.talent_pane, this.glyphPane, this.groupBuffPane, this.debuffPane, this.tempEffectPane, this.consumePane, this.prePane, this.rot_pane, this.hotw_pane, this.featurePane, this.hotfix_pane, this.calcPane, this.mirror_pane, this.fight_pane, this.combatLog_pane, this.sim_pane, this.compare_pane, this.weight_pane, this.trink_pane };
        final MouseListener ml = new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent me) {
                CatusFrame.this.hitExpAsPerc = !CatusFrame.this.hitExpAsPerc;
                CatusFrame.this._updateStats();
            }
        };
        this.reforge_statHit.addMouseListener(ml);
        this.reforge_statExp.addMouseListener(ml);
        this.weight_hit_lbl.addMouseListener(ml);
        this.weight_exp_lbl.addMouseListener(ml);
        (this.scrolled = new JPanel()).setLayout(new GridBagLayout());
        this.scrolled.setDoubleBuffered(true);
        this.scrolled.setOpaque(true);
        if (Utils.isMac()) {
            this.scrolled.setBackground(new Color(232, 232, 232));
        }
        (this.scroll = new JScrollPane()).setVerticalScrollBarPolicy(20);
        this.scroll.setHorizontalScrollBarPolicy(30);
        this.scroll.getVerticalScrollBar().setUnitIncrement(15);
        this.scroll.getHorizontalScrollBar().setUnitIncrement(15);
        this.scroll.setPreferredSize(new Dimension(1000, 800));
        this.scroll.setViewportView(this.scrolled);
        this.scroll.getViewport().setOpaque(false);
        this.scroll.setBorder(null);
        this.scroll.setOpaque(false);
        for (final PrefPane x18 : this.prefPanes) {
            _addGridBagRow(this.scrolled, x18.panel, false);
        }
        _addGridBagBalloon(this.scrolled);
        (this.config2_combo = new UIComboBox<Config2>()).setWide(true);
        this.config2_combo.addActionListener(this.config2_combo_al);
        final ListCellRenderer lcr2 = this.config2_combo.getRenderer();
        this.config2_combo.setRenderer(new ListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(final JList list, Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
                if (index == -1) {
                    value = ((value instanceof Config2) ? ("#" + Integer.toString(1 + ((Config2)value).index)) : "");
                }
                return lcr2.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        (this.config2_rename_menu = new JPopupMenu()).add(new AbstractAction("Rename...") {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.config_rename();
            }
        });
        this.config2_rename_menu.add(new AbstractAction("Character") {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.config_setName(CatusFrame.this.import_char_field.getText());
            }
        });
        this.config2_rename_menu.add(new AbstractAction("Current Stats") {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.sim.resetAll();
                CatusFrame.this.config_setName(CatusFrame.this.sim.catStatsStr());
            }
        });
        this.config2_rename_menu.add(new AbstractAction("Reforge") {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.config_setName(CatusFrame.this.mainProfile.statStr(FeralSpec.REFORGE_STATS));
            }
        });
        this.config2_rename_menu.add(new AbstractAction("Weapon") {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                if (CatusFrame.this.mainProfile.hasTwoHander()) {
                    CatusFrame.this.config_setName(CatusFrame.this.mainProfile.MH.getItemNameOrEmpty());
                }
                else {
                    CatusFrame.this.config_setName(CatusFrame.this.mainProfile.MH.getItemNameOrEmpty() + " + " + CatusFrame.this.mainProfile.OH.getItemNameOrEmpty());
                }
            }
        });
        this.config2_rename_menu.add(new AbstractAction("Trinkets") {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.config_setName(CatusFrame.this.mainProfile.T1.getItemNameOrEmpty() + " / " + CatusFrame.this.mainProfile.T2.getItemNameOrEmpty());
            }
        });
        this.config2_rename_menu.add(new AbstractAction("Item Level") {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.config_setName(String.format("%.2f Item Level", CatusFrame.this.mainProfile.avgItemLevel()));
            }
        });
        this.config2_rename_menu.add(new AbstractAction("Gear Hash") {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.config_setName(Utils.sha1(CompactGear.toString(CatusFrame.this.mainProfile, true, true)));
            }
        });
        (this.config2_name_text = new JLabel()).setForeground(Color.WHITE);
        this.config2_name_text.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent me) {
                CatusFrame.this.config2_rename_menu.show(CatusFrame.this.config2_name_text, 0, CatusFrame.this.config2_name_text.getHeight());
            }
        });
        (this.config2_manage_combo = new UIComboBox<NamedActionListener>()).setWide(true);
        this.config2_manage_combo.setPrototypeDisplayValue("Options");
        UI.setComboText(this.config2_manage_combo);
        this.config2_manage_combo.addActionListener(new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                final NamedActionListener al = CatusFrame.this.config2_manage_combo.getPick();
                CatusFrame.this.config2_manage_combo.hidePopup();
                this.lock();
                UI.setComboText(CatusFrame.this.config2_manage_combo);
                this.unlock();
                if (al != null) {
                    al.actionPerformed(ae);
                }
            }
        });
        this.config2_manage_combo.addItem(new NamedActionListener("Import...") {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                String code = new DialogText(CatusFrame.this, "Import: Configuration", "OK").getWrappedText();
                if (code == null) {
                    return;
                }
                code = code.trim();
                if (code.isEmpty()) {
                    return;
                }
                if (code.charAt(0) != '{') {
                    try {
                        code = Base64.decodeStr(code.replaceAll("\\s+", ""));
                    }
                    catch (RuntimeException err) {
                        CatusFrame.this.showError("Import: Base64 Error", err);
                        return;
                    }
                }
                Object value;
                try {
                    value = JSONValue.parseWithException(code);
                }
                catch (ParseException err2) {
                    CatusFrame.this.showError("Import: JSON Error", "Invalid JSON representation.");
                    return;
                }
                if (!(value instanceof JSONObject)) {
                    CatusFrame.this.showError("Import: JSON Error", "Malformed JSON representation.");
                    return;
                }
                CatusFrame.this.config_save();
                final Config2 config = CatusFrame.this.config_create((JSONObject)value);
                CatusFrame.this.config_list.add(config);
                CatusFrame.this.config_rebuildList();
                CatusFrame.this.config_select(config);
            }
        });
        this.config2_manage_combo.addItem(new NamedActionListener("Export to JSON...") {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.config_save();
                new DialogText(CatusFrame.this, "Export: Configuration", "OK").showWrappedText(CatusFrame.this.selectedConfig.toJSONString());
            }
        });
        this.config2_manage_combo.addItem(new NamedActionListener("Export to Base64...") {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.config_save();
                new DialogText(CatusFrame.this, "Export: Configuration", "OK").showTextWrapped(Base64.encodeStr(CatusFrame.this.selectedConfig.toJSONString()));
            }
        });
        this.config2_manage_combo.addItem(new NamedActionListener("Screenshot") {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.screenshot(CatusFrame.this.scrolled, "Config", UI.isAltKeyDown(ae));
            }
        });
        this.config2_manage_combo.addItem(new NamedActionListener("Clear Configuration") {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.config_makeDefault();
            }
        });
        this.config2_manage_combo.addItem(new NamedActionListener("Delete Configuration") {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.config_delete();
            }
        });
        this.config2_manage_combo.addItem(new NamedActionListener("Delete All...") {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                if (0 != JOptionPane.showConfirmDialog(CatusFrame.this, "Are you sure you want to delete all your configurations?  This cannot be undone.", "Delete Configurations", 0)) {
                    return;
                }
                CatusFrame.this.config_deleteAll();
            }
        });
        (this.config2_clone_btn = new JButton()).setPreferredSize(new Dimension(16, 16));
        this.config2_clone_btn.setContentAreaFilled(false);
        this.config2_clone_btn.setBorderPainted(false);
        this.config2_clone_btn.setFocusable(false);
        this.config2_clone_btn.setIcon(CatusFrame.greenPlusImage);
        this.config2_clone_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.config_new(UI.isAltKeyDown(ae));
            }
        });
        (this.config2_delete_btn = new JButton()).setPreferredSize(new Dimension(18, 18));
        this.config2_delete_btn.setContentAreaFilled(false);
        this.config2_delete_btn.setBorderPainted(false);
        this.config2_delete_btn.setFocusable(false);
        this.config2_delete_btn.setIcon(CatusFrame.redCrossImage);
        this.config2_delete_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.config_delete();
            }
        });
        this.backing = new UIPanel_GridBag();
        row19 = new UIPanel_GridBag();
        row19.pad(4, 4);
        row19.add(this.config2_combo);
        row19.gap(4);
        row19.spacer(this.config2_name_text);
        row19.add(this.config2_clone_btn);
        row19.gap_add(4, this.config2_manage_combo);
        row19.setOpaque(true);
        row19.setBackground(Color.DARK_GRAY);
        this.backing.row(row19, true, 0);
        this.backing.row_both(this.scroll);
        this.setContentPane(this.backing);
        this.setToolTips();
        this.registerKeyCombos();
        this.pack();
    }
    
    static ImageIcon sideBySideIcon(final ImageIcon a, final ImageIcon b) {
        final int w = a.getIconWidth();
        final Image img = new BufferedImage(w + b.getIconWidth(), a.getIconHeight(), 2);
        final Graphics g = img.getGraphics();
        g.drawImage(a.getImage(), 0, 0, null);
        g.drawImage(b.getImage(), w, 0, null);
        g.dispose();
        return new ImageIcon(img);
    }
    
    private void registerKeyCombos() {
        final int shortcut = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
        final AbstractAction a = new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.import_char_btn.doClick();
            }
        };
        this.registerKeyCombo(shortcut, 73, "Import", a);
        this.registerKeyCombo(shortcut | 0x8, 73, "Import (Forced)", a);
        this.registerKeyCombo(shortcut | 0x1, 73, "Import and Clone", a);
        this.registerKeyCombo(shortcut | 0x1 | 0x8, 73, "Import and Clone (Forced)", a);
        this.registerKeyCombo(shortcut, 68, "Distribution", new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.sim_dist();
            }
        });
        this.registerKeyCombo(shortcut, 69, "Sim Compare to Armory", new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.compare_sim_armory(UI.isAltKeyDown(ae));
            }
        });
        this.registerKeyCombo(shortcut, 71, "Gear Compare to Armory", new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final Profile p = CatusFrame.this.importArmory(UI.isAltKeyDown(ae));
                if (p == null) {
                    return;
                }
                CatusFrame.this.compareGear(p, CatusFrame.this.mainProfile);
            }
        });
        this.registerKeyCombo(shortcut, 76, "Log", new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.sim_log();
            }
        });
        this.registerKeyCombo(shortcut, 87, "Delete", new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                if (0 != JOptionPane.showConfirmDialog(CatusFrame.this, "Are you sure you want to delete this configuration?", "Delete Configuration", 0)) {
                    return;
                }
                CatusFrame.this.config_delete();
            }
        });
        this.registerKeyCombo(shortcut, 78, "Blank", new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.config_new(true);
            }
        });
        this.registerKeyCombo(shortcut | 0x200, 84, "Blank", new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.config_new(true);
            }
        });
        this.registerKeyCombo(shortcut, 84, "Clone", new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                CatusFrame.this.config_new(false);
            }
        });
        final int[] array;
        final int[] nums = array = new int[] { 49, 50, 51, 52, 53, 54, 55, 56, 57 };
        for (final int key : array) {
            final int index = key - nums[0];
            this.registerKeyCombo(shortcut, key, String.format("Go to Config %d", 1 + index), new AbstractAction() {
                @Override
                public void actionPerformed(final ActionEvent ae) {
                    if (index < CatusFrame.this.config_list.size()) {
                        CatusFrame.this.config_select_ifDiff(CatusFrame.this.config_list.get(index));
                    }
                }
            });
        }
    }
    
    private void registerKeyCombo(final int modifiers, final int key, final String name, final Action a) {
        this.scroll.getActionMap().put(name, a);
        this.scroll.getInputMap(2).put(KeyStroke.getKeyStroke(key, modifiers), name);
    }
    
    private void setToolTips() {
        ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
        final String special = Utils.isMac() ? "\u2318" : "ctrl";
        String tt = Utils.tt_simple(null, "Enter a Warcaft Logs <b>ReportIdentifier</b> to lookup a report.", "You may also enter a report URL.", "Hold <tt>[alt]</tt> to force refresh.", "Hold <tt>[shift]</tt> to import all Druids (which skips per-fight spec verification.)");
        this.mirror_report_field.setToolTipText(tt);
        this.mirror_importReport_btn.setToolTipText(tt);
        this.mirror_cache_btn.setToolTipText(Utils.tt_simple("Clear Cache", "Lots of tiny files are downloaded when a report is loaded.", "This will delete the cached copies.", "Hold <tt>[alt]</tt> to view directory instead."));
        this.mirror_importChar_btn.setToolTipText(Utils.tt_simple("Import Character", "Attempt to load the selected Druid into Catus.", "This is equivalent to importing the Druid directly.", "Note: some reports do not contain realm/region information.", "Hold <tt>[alt]</tt> to force refresh."));
        this.mirror_viewChar_btn.setToolTipText(Utils.tt_simple("Go to Armory", "Browse the selected Druid's armory website.", "Note: some reports do not contain realm/region information."));
        this.mirror_viewDamageAlloc_btn.setToolTipText(Utils.tt_simple("Go to " + this.mirror_viewDamageAlloc_btn.getText(), "Browse the damage allocation for the selected fight.", "Example: Boss 90%, Adds 10%"));
        this.mirror_viewDPS_btn.setToolTipText(Utils.tt_simple("Go to " + this.mirror_viewDPS_btn.getText(), "Browse the DPS and damage profile for the selected fight relative to the selected target.", "Example: Cat 500K, Treants 20K, ..."));
        this.mirror_viewDamageDone_btn.setToolTipText(Utils.tt_simple("Go to " + this.mirror_viewDamageDone_btn.getText(), "Browse the damage done and ability distribution for the selected fight relative to the selected target.", "Example: Rip 30%, Rake 20%, Melee 15%, ..."));
        this.mirror_viewAuras_btn.setToolTipText(Utils.tt_simple("Go to " + this.mirror_viewAuras_btn.getText(), "Browse the buff information for the selected fight.", "Example: 95% SR, 30% RoR, 20% TED, ..."));
        this.fight_mirror_self_check.setToolTipText(Utils.tt_simple("Self-originating Procs and Effects", "When enabled, your random-based effects are aligned with the parsed report", "Each simulation has the same timing and occurrences.", "Does not include: Lifeblood, Berserking, Springs, on-use trinkets", "Examples: trinket procs, Omen of Clarity, Legendary meta gem, Legendary cloak, ..."));
        this.fight_mirror_external_check.setToolTipText(Utils.tt_simple("External Temporary Effects", "When enabled, your existing raid cooldown settings are ignored.", "Instead, the cooldowns are aligned with the parsed report", "Examples: Heroism, Skull Banner, Tricks of the Trade, Stormlash, ..."));
        this.fight_mirror_health_check.setToolTipText(Utils.tt_simple("Enemy Health", "When enabled, your target losses health in lockstep with the parsed report.", "This ensures the BitW transition occurs at the same point."));
        for (final ReforgeToggle x : this.reforge_toggles) {
            x.setToolTipText(Utils.tt_simple("Mutable Slots", "If enabled, the reforge may change the slots Reforge and Gems.", "Disable to freeze a slot and prevent any changes.", "Reforged slots have green text.", "Empty slots have disabled checkboxes.", "All reforgers respect these settings."));
        }
        this.reforgeMax_depth_field.setToolTipText(Utils.tt_simple("Search Depth", "Number of unique Hit/Exp configurations to retain sorted by excess.", "Larger numbers slow down the reforging, but ensure sloppier reforgings aren't incorrectly ignored.", "Examples: 9000, 10k, 1m, 5m", "Default: 20000"));
        tt = Utils.tt_simple(null, "Enter <b>Name</b> to do a player search.", "Enter <b>Name Realm</b> to find a specific character.", "You may also enter an armory URL.", "Realm is validated by default.", "Follow realm with an exclamation-point `!` to prevent realm validation.", "Hold <tt>[alt]</tt> to force refresh.", "Hold <tt>[shift]</tt> to import as a cloned configuration and compare it.", "Shortcut: <tt>[" + special + "+I]</tt>");
        this.import_char_field.setToolTipText(tt);
        this.import_char_btn.setToolTipText(tt);
        this.config2_combo.setToolTipText("<html><b>List of available configurations</b><br/>The first 9 configurations are selectable via <tt>[" + special + "+#]</tt>.<br/><tt>[" + special + "+T]</tt> clones this configuration.<br/><tt>[" + special + "+N]</tt> creates a blank configuration.<br/><tt>[" + special + "+W]</tt> deletes this configuration.</html>");
        this.config2_name_text.setToolTipText("<html><b>Name of Configuration</b><br/>Click to rename this configuration.</html>");
        this.config2_clone_btn.setToolTipText(Utils.tt_simple("Duplicate Configuration", "Create a copy of this configuration.", "Hold <tt>[alt]</tt> to create a blank configuration.", "Shortcut: <tt>[" + special + "+T]</tt> to copy this configuration.", "Shortcut: <tt>[" + special + "+N]</tt> to create a blank configuration."));
        this.reforge_bounds_btn.setToolTipText(Utils.tt_simple("Compute Minimum and Maximum values for each Cat Stat", "Calculated with gems/enchants and w/o.", "Note: each extrema is calculated independently meaning they might be mutually exclusive."));
        this.reforge_map_btn.setToolTipText(Utils.tt_simple("Create a Reforging Map", "Show how each stat is reforged in aggregate.", "Useful for determining the style of reforging performed."));
        this.reforge_stats_btn.setToolTipText(Utils.tt_simple("Create a Human-readable Stat String", "Useful for sharing a reforge with a friend.", "Includes both vertical and horizontal formats."));
        this.reforge_clearReforges_btn.setToolTipText("<html>Remove all reforges.<br/>Any slot checkbox that has green text is currently reforged.</html>");
        this.reforge_clearGems_btn.setToolTipText("<html>Remove all gems.</html>");
        this.reforge_export_btn.setToolTipText(Utils.tt_simple("Export to Reforgerade", "Generate a Reforgerade-compatible addon string.", "This encoding only includes your item reforges.", "Use: <b>Shopping List</b> to export your gems and enchants.", "Hold <tt>[alt]</tt> to visit the Reforgerade addon website."));
        this.reforge_shoppingList_btn.setToolTipText(Utils.tt_simple("Export Shopping List", "Generate a list of gems and enchants required to obtain the current reforge.", "The list also includes the location of these replacements.", "Use: <b>Reforgerade</b> to export your item reforges."));
        this.reforge_exportAMR_btn.setToolTipText(Utils.tt_simple("Export to AskMrRobot", "Generate an AskMrRobot-compatible addon string.", "This encoding includes all gem and enchant information to obtain the current reforge.", "Hold <tt>[alt]</tt> to visit the AskMrRobot addon website."));
        this.pre_castSR_toggle.setToolTipText("<html><b>Cast Savage Roar optimally before combat begins.</b><br/>Requires glyph: Savagery</html>");
        this.pre_castHT_toggle.setToolTipText("<html><b>Cast Healing Touch before combat begins.</b><br/>(2 stacks of Dream of Cenarius)<br/>Requires talent: Dream of Cenarius</html>");
        this.pre_procReset_combo.setToolTipText("<html><b>RPPM/ICD Proc Reset Time</b><br/>The amount of idle time before combat begins.<br/>Note: Standard corresponds to boss encounters.</html>");
        this.tempEffect_berserking_toggle.setToolTipText("<html><b>Allow Berserking to be cast.</b><br/>Requires: Troll</html>");
        this.tempEffect_lifeblood_toggle.setToolTipText("<html><b>Allow Lifeblood to be cast.</b><br/>Requires: Herbalism</html>");
        this.reforgeRune_overflow_field.setToolTipText("<html>The <b>maximum</b> amount of excess Mastery to consider.<br/>Constraint: Mastery &le; Overflow + (Mastery + Haste + Crit)/3</html>");
        this.reforgeRune_hasteGap_field.setToolTipText("<html>The <b>minimum</b> amount of separation between Mastery and Haste.<br/>Constraint: Mastery &gt; Haste + Gap</html>");
        this.reforgeRune_critGap_field.setToolTipText("<html>The <b>minimum</b> amount of separation between Mastery and Crit.<br/>Constraint: Mastery &gt; Crit + Gap</html>");
        this.reforgeRune_critGreater_check.setToolTipText("<html>Ensure Crit &gt; Haste so Bear form procs will choose Critical strike.</html>");
        this.reforgeRune_enchantHands_check.setToolTipText("<html>+170 <b>Expertise</b>, <b>Mastery</b>, or <b>Haste</b>.<br>Ignored if slot is frozen.</html>");
        this.reforgeRune_enchantBack_check.setToolTipText("<html>+180 <b>Crit</b> or <b>Hit</b><br>Ignored if slot is frozen.<br/>Ignored if you use an Embroidery.</html>");
        this.reforgeRune_changeGems_check.setToolTipText("<html><b>Include gems in the reforging process.</b><br/>Gems with profession requirements are ignored.<br/><b>Warning:</b> this may slow down the reforging process.</html>");
        this.reforgeRune_keepGemColors_check.setToolTipText("<html>Use existing gem colors to determine possible replacement gems.<br/>Enable if you have purposefully broken multiple socket bonuses.<br/>If a socket is empty, the socket color will be matched.</html>");
        this.reforgeRune_breakBonus_check.setToolTipText("<html>Consider additional gem configurations if breaking the bonus is better.<br/>Red gems are used when bonuses are broken.</html>");
        this.reforgeRune_leaveEmpty_check.setToolTipText("<html>Leave open gem sockets empty.<br/>Useful if you lack a profession or quest-based socket enhancement.<br/>Typically, you should leave this unchecked.</html>");
        this.reforgeRune_useSta_check.setToolTipText(Utils.tt_simple("Allow Agi/Sta gems.", new String[0]));
        this.reforgeRune_useHit_check.setToolTipText(Utils.tt_simple("Allow Hit gems.", "Note: this will slow the reforger."));
        this.reforgeRune_similar_btn.setToolTipText("<html><b>Minimize the number of enchants and gems required</b> to achieve the current reforging.<br>The differences are computed relative to the last imported armory.<br/>Use <b>Gear Compare: Armory</b> to highlight the differences.<br/><b>Warning:</b> this may use a lot of memory.</html>");
        this.reforgeRune_range_field.setToolTipText("<html>The <b>maximum</b> distance from the target to search for Hit/Exp solutions.</html>");
        this.reforgeRune_reforge_btn.setToolTipText("<html><b>Exhaustively reforge your gear.</b><br/>This is always relative to your current profile.<br/>There are two phases of reforging:<br/>Interrupting the first phase will cancel the process.<br/>Interrupting the second phase will give you partial results.</html>");
        this.reforgeRune_compare_btn.setToolTipText(Utils.tt_simple("Simulator: Quick Compare", "Test your current reforging vs. the selected reforging.", "Requires: Rune of Re-Origination."));
        tt = "<html><b>At most</b>: cannot exceed this amount.<br/><b>Near</b>: centered on this value but dynamically adjusted based on bounds.<br/><b>At least</b>: cannot fall below this amount.</html>";
        this.reforgeRune_hit_combo.setToolTipText(tt);
        this.reforgeRune_hit_field.setToolTipText(tt);
        this.reforgeRune_exp_combo.setToolTipText(tt);
        this.reforgeRune_exp_field.setToolTipText(tt);
        this.equip_screenshot_btn.setToolTipText("<html><b>Save a gear screenshot.</b><br/>Default destination: " + new File("").getAbsolutePath() + "<br/>Hold down <tt>[alt]</tt> to choose a custom location.</html>");
        this.equip_autoFill_btn.setToolTipText("<html><b>Apply as many equipment modifications as possible.</b><br/>Note: this may replace existing enchants, gems, and tinkers.</html>");
        this.equip_batch_btn.setToolTipText(Utils.tt_simple("Batch Add/Remove Additional Gear", "Lists all custom item IDs across all slots.", "The same editor can be accessed (per slot) from the empty gear select menu."));
        this.equip_config_combo.setToolTipText(Utils.tt_simple("Copy Gear", "Import Gear, Race, and Professions from another profile.", "You can also restore all slots from the Armory."));
        this.equip_clear_combo.setToolTipText(Utils.tt_simple("Clear Gear", "Wipe out various parts of your character."));
        this.equip_itemLevel_combo.setToolTipText(Utils.tt_simple("Scale Gear", "Apply or remove upgrades.", "Emulate Challenge Mode functionality (Downscaling + Restrictions.)", "<b>Scaling</b>: item levels are forced to specified level.", "<b>Down-scaling:</b> item levels are scaled down to appropriate level.", "Note: to achieve custom down-scaling, use a negative item level."));
        this.equip_edit_btn.setToolTipText(Utils.tt_simple("Edit CompactGear", "Format gear using Catus equipment syntax.", "Changes are also accepted in this form.", "This code can be used share gear."));
        this.equip_exportSimc_btn.setToolTipText(Utils.tt_simple("Export to Simc", "Format gear using Simc equipment syntax."));
        this.compareGear_toCode_btn.setToolTipText("<html>Compare your gear to Compact Gear code.</html>");
        this.compareGear_toArmory_btn.setToolTipText("<html>Compare your gear to your armory.</html>");
        this.compareGear_recent_combo.setToolTipText("<html>Compare your gear to another player's armory.</html>");
        this.compareGear_config_combo.setToolTipText("<html>Compare your gear to gear from another configuration.</html>");
        this.groupBuff_noneBtn.setToolTipText("<html><b>No buffs.</b><br>Note: Leader of the Pack is always active in Cat/Bear form.</html>");
        this.groupBuff_selfBtn.setToolTipText("<html><b>Only group buffs providable by Feral.</b><br>Note: Leader of the Pack is always active in Cat/Bear form.</html>");
        this.groupBuff_allBtn.setToolTipText("<html><b>All group buffs.</b></html>");
        this.debuff_melee_toggle.setToolTipText("<html><b>All targets suffer 4% additional physical damage.</b></html>");
        this.debuff_spell_toggle.setToolTipText("<html><b>All targets suffer 5% additional magic damage.</b></html>");
        this.debuff_bleed_toggle.setToolTipText("<html><b>All targets are permanently Bleeding.</b><br/>Note: this is unrealistic for AoE encounters.</html>");
        this.hotw_wrath_toggle.setToolTipText("<html><b>Allow Wrath to be cast.</b></html>");
        this.hotw_hurricane_toggle.setToolTipText("<html><b>Allow Hurricane to be cast.</b></html>");
        tt = Utils.tt_simple("Duration", "Examples: '10.4s', 45sec', '300', '7.5m', '10min', '1hr', '1:32.450'", "Default unit is seconds.", "", "<b>Health</b>", "Examples: '50k', '9m', '2.1b'");
        this.fight_patch_life_field.setToolTipText(tt);
        this.fight_patch_life_combo.setToolTipText(tt);
        this.pre_energy0_field.setToolTipText(Utils.tt_simple("Starting Energy", "The amount of energy you have at the beginning of combat.", "Default and Maximum is 100 energy.", "Energy can be negative."));
        this.fight_patch_timeVariance_combo.setToolTipText(Utils.tt_simple("Duration Stability Mode", "<b>Automatic:</b> experiment, then maintain mean duration across all iterations.", "<b>Automatic (Old):</b> fixed-point the health that produces desired combat time, then constant health for all iterations.", "<b>Fixed:</b> combat time is exact and health percentage is proportional to remaining time.", "<b>Simc:</b> mimic the algorithm used by Simc."));
        this.fight_patch_armor_field.setToolTipText("<html><b>Custom Boss Armor</b><br/>Must be a non-negative integer.<br/>Note: leave blank for default armor based on level.</html>");
        this.fight_patch_early_field.setToolTipText("<html><b>Early Death Health Percentage</b><br/>Use to model premature boss death, eg. Ra-den (5%)<br/>Note: leave blank to disable.</html>");
        this.fight_patch_front_check.setToolTipText("<html><b>Attack from the Front</b><br/>When enabled, the boss may Parry or Block your attacks.</html>");
        this.fight_cleave_style_combo.setToolTipText("<html><b>Boss Death:</b> combat ends when your primary target dies.<br/><b>Fixed Time:</b> boss is immortal and combat ends exactly at the specified time.</html>");
        this.fight_cleave_time_field.setToolTipText("<html><b>Approximate Encounter Duration</b><br/>Examples: '45sec', '7.5m', '1hr', '1:32.450'</html>");
        this.fight_cleave_freq_field.setToolTipText("<html><b>Spawn Frequency</b><br/>Examples: '45sec', '7.5m', '1hr', '1:32.450'<br/>Can also be entered as a range: '90s-2m'<br/>Leave blank for no additional spawns.</html>");
        this.fight_cleave_health_field.setToolTipText("<html><b>Add Health</b><br/>Examples: '50k', '9m', '2.1b'<br/>Can also be entered as a range: '4-6m'<br/>Leave bank for immortality.</html>");
        this.fight_cleave_lifetime_field.setToolTipText("<html><b>Add Maximum Lifetime</b><br/>Examples: '45sec', '7.5m', '1hr', '1:32.450'<br/>Can also be entered as a range: '90s-2m'<br/>Leave blank to require damage-based death.</html>");
        this.feature_setBonus_toggle.setToolTipText("<html><b>Override Set Bonuses</b><br/>Select any combination of set bonuses using modified configuration panel above.<br/>Note: when enabled, set bonuses from your gear are ignored.</html>");
        this.feature_averageRanges_toggle.setToolTipText("<html><b>Replace Variable Ranges with Mean</b><br/>Example: Weapon Damage</html>");
        this.fight_stdEffects_btn.setToolTipText(Utils.tt_simple("Use Recommended Settings", "Restores Buffs, Debuffs, Consumables, Combat Preparation, and Hotfixes to their recommended values.", "<b>Warning: this will replace your existing settings.</b>"));
        this.fight_stdFights_btn.setToolTipText(Utils.tt_simple("Use Recommended Encounter", "Restores All Encounters to their defaults.", "Sets the current encounter to Patchwerk.", "<b>Warning: this will replace your existing encounter settings.</b>"));
        this.feature_properRipExtend_check.setToolTipText("<html>Receive exactly 1 extra Rip tick per extension.</html>");
        this.feature_localRipExtend_check.setToolTipText("<html>Makes Rip Extensions unique to each target, rathan than global, where they are reset on Rip.</html>");
        this.feature_smartFoN_check.setToolTipText("<html>Force Treants to always Melee and Rake from behind their target.</html>");
        this.feature_noob_toggle.setToolTipText(wrap_tt(true, "<b>Enable Noob Mode</b><br/>Restores Buffs, Debuffs, Temporary Effects, Consumables, Combat Preparation, and Hotfixes to their recommended values and hides their corresponding interfaces.<br/><b>Warning: this will replace your existing settings.</b>"));
        this.combatLog_btn.setToolTipText(wrap_tt(false, "<b>Create a textual Combat Log</b><br/>Your default text-editor will be launched when the log is ready.<br/>Shortcut: <tt>[" + special + "+L]</tt>"));
        this.sim_btn.setToolTipText(wrap_tt(false, "<b>Perform an repeated Simulation</b><br/>A simulation window display the simulation progress.<br/>Your default text-editor will be launched when the simulation is complete.<br/>You many perform more than simulation at a time.<br/>Shortcut: <tt>[" + special + "+D]</tt>"));
        this.sim_iter_field.setToolTipText(Utils.tt_simple("Fixed Termination", "Number of simulation iterations to perform.", "Leave blank for standard error based iteration."));
        this.sim_err_field.setToolTipText(Utils.tt_simple("Standard Error Termination", "The amount of acceptable variance in the mean DPS measurement.", "Leave blank for fixed iteration."));
        this.sim_timeDist_field.setToolTipText(Utils.tt_simple("Duration of Time Distribution Segments", "Examples: '1s', '5sec', '1:30'", "Leave blank for automatic sizing."));
        this.weight_sim_btn.setToolTipText(wrap_tt(false, "<b>Perform a Stat Weight Simulation</b><br/>Warning: this may take a while.<br/>Hold down <tt>[alt]</tt> to peek at the suggested deltas."));
        this.weight_space_btn.setToolTipText(Utils.tt_simple("Randomly Sample Stat Space", "Create a CSV file of Delta DPS and Delta Stats.", "Each entry is a single simulation with uniform random deltas.", "For each iteration, a normal-sim and delta-sim are performed.", "Warning: if selected, Hit/Exp may go positive regardless of settings."));
        this.weight_inert_check.setToolTipText(Utils.tt_simple("Inert or Active Deltas", "When Inert, deltas function like the Mastery buff and are not part of your gear stats.", "Inert prevents deltas from influencing Rune of Re-Origination and other similar effects."));
        this.scaled_label.setToolTipText("Click for Gear Comparsion");
        this.warning_label.setToolTipText("Click for Additional Information");
        this.pre_opener_combo.setToolTipText(wrap_tt(false, "<b>Opener</b><br/>Note: Pounce/Ravage require Stealth.<br/>Advanced: Aggixx's opener, requires 4pT16 and Glyph of Savagery."));
        this.pre_finisher0_combo.setToolTipText(Utils.tt_simple("First Finisher", "Choose what finisher is used after reaching 5 combos for the first time.", "Note: the first finisher sometimes gets overrode by higher priority actions."));
        this.trink_basic_edit_btn.setToolTipText(wrap_tt(false, "<b>Select Trinkets for Comparison</b><br/>Listed trinkets from <tt>CatusGear.txt</tt>.<br/>Additional trinkets may be added using Edit interface.<br/>Hold <tt>[alt]</tt> to add additional trinkets."));
        this.trink_ignoreErrors_check.setToolTipText(Utils.tt_simple("Ignore Unreforgable Errors", "Some profiles cannot be reforged according to the desired constraints.", "By default, when this occurs, the trinket simulation is aborted and an error is generated.", "Ignoring errors will prevent unsatisfable configurations from terminating the simulation."));
        this.trink_reforge_combo.setToolTipText(Utils.tt_simple("Reforge Technique", "Automatically choose between 1:1:1 or Maximize reforger.", "<b>Smart Reforge:</b> default settings for both reforgers are used (but respects 1:1:1 gem color.)", "<b>Use Reforger Settings:</b> current settings for both reforgers are used.", "<b>Don't Reforge:</b> leaves gear untouched."));
        this.trink_advanced_useSelected_btn.setToolTipText(Utils.tt_simple("Import Trinkets for Selected", "Copies valid trinkets pairs from Basic mode into Advanced so they can be customized.", "Hold <tt>[alt]</tt> to choose the trinkets used in Basic mode."));
        this.trink_sim_btn.setToolTipText(wrap_tt(false, "<b>Perform a Trinket Simulation</b><br/>Warning: this may take a while.<br/>Hold down <tt>[alt]</tt> to peek at the trinket combinations."));
        this.tempEffect_hero_icon.setToolTipText(Utils.tt_simple("Source of Heroism", "(Click to Change)", "Time Warp: 30% haste", "Drums of Rage: 25% haste"));
        this.feature_wodRacials_check.setToolTipText(Utils.tt_simple("<b>Night Elf</b>: +1% Haste", "<b>Tauren</b>: +2% Crit Damage", "<b>Troll</b>: +15% Berserking (-5%)"));
    }
    
    static String wrap_tt(final boolean wide, final String html) {
        return "<html>" + (wide ? "<p width='400'>" : "") + html + "</html>";
    }
    
    private boolean swapWeapon() {
        final Profile p = new Profile();
        p.MH.copy(this.mainProfile.MH);
        p.OH.copy(this.mainProfile.OH);
        try {
            this.mainProfile.MH.clear();
            this.mainProfile.OH.clear();
            this.mainProfile.MH.unsafeCopy(this.swapProfile.MH);
            this.mainProfile.OH.unsafeCopy(this.swapProfile.OH);
        }
        catch (RuntimeException err) {
            this.showError("Weapon Swap Error", err);
            this.mainProfile.MH.clear();
            this.mainProfile.OH.clear();
            this.mainProfile.MH.copy(p.MH);
            this.mainProfile.OH.copy(p.MH);
            return false;
        }
        this.swapProfile.MH.clear();
        this.swapProfile.OH.clear();
        this.swapProfile.MH.copy(p.MH);
        this.swapProfile.OH.copy(p.OH);
        this.mainProfile.validate();
        this._rebuildGear();
        return true;
    }
    
    private void _setBuffs_self() {
        this.groupBuff_al.lock();
        for (final JToggleButton x : this.buff_toggles) {
            x.setSelected(false);
        }
        this.buff_stats_toggle.setSelected(true);
        this.groupBuff_al.unlock();
    }
    
    private void _setBuffs_all() {
        this.groupBuff_al.lock();
        for (final JToggleButton x : this.buff_toggles) {
            x.setSelected(true);
        }
        this.groupBuff_al.unlock();
    }
    
    private Set<SlotT> getSlotSet() {
        final HashSet<SlotT> set = new HashSet<SlotT>();
        for (final ReforgeToggle x : this.reforge_toggles) {
            if (x.isSelected()) {
                set.add(x.slot);
            }
        }
        return set;
    }
    
    private void removeRecentReport(final String reportId) {
        for (int i = 0; i < this.mirror_report_recent_combo.getItemCount(); ++i) {
            final NamedThing.Str item = this.mirror_report_recent_combo.getItemAt(i);
            if (((String)item.obj).equalsIgnoreCase(reportId)) {
                this.mirror_report_recent_combo.removeItemAt(i);
                break;
            }
        }
    }
    
    private String parseReportId() {
        final String text = this.mirror_report_field.getText().trim();
        if (text.isEmpty()) {
            this.showError("Warcraft Logs Error", "Please enter a valid report identifier.");
            return null;
        }
        if (!text.contains("/")) {
            return text;
        }
        final String prefix = "/reports/";
        int pos = text.indexOf(prefix);
        if (pos >= 0) {
            pos += prefix.length();
            int end;
            for (end = pos + 1; end < text.length() && Character.isLetterOrDigit(text.charAt(end)); ++end) {}
            return text.substring(pos, end);
        }
        this.showError("Warcraft Logs Error", "Unable to extract the report identifier.");
        return null;
    }
    
    private void resetMirrorCombos() {
        this.mirror_lock = true;
        this.updateMirrorEnabled(false);
        this.mirror_fight_combo.setPrototypeDisplayValue("No Fights");
        this.mirror_actor_combo.setPrototypeDisplayValue("No Druids");
        this.mirror_boss_combo.setPrototypeDisplayValue("No Bosses");
        UI.setComboText(this.mirror_actor_combo);
        UI.setComboText(this.mirror_fight_combo);
        UI.setComboText(this.mirror_boss_combo);
        this.mirror_lock = false;
    }
    
    private void updateMirrorEnabled(final boolean b) {
        this.mirror_actor_combo.setEnabled(b);
        this.mirror_fight_combo.setEnabled(b);
        this.mirror_boss_combo.setEnabled(b);
        this.mirror_importChar_btn.setEnabled(b);
        this.mirror_viewChar_btn.setEnabled(b);
        this.mirror_viewDamageAlloc_btn.setEnabled(b);
        this.mirror_viewAuras_btn.setEnabled(b);
        this.mirror_viewDamageDone_btn.setEnabled(b);
        this.mirror_viewDPS_btn.setEnabled(b);
        this.mirror_parse_btn.setEnabled(b);
    }
    
    private void updateMirrorActorSelection() {
        final boolean save = this.mirror_lock;
        this.mirror_lock = true;
        final WLActor actor = this.mirror_actor_combo.getPick();
        final Object prev = this.mirror_fight_combo.getSelectedItem();
        this.mirror_fight_combo.removeAllItems();
        for (final WLFight x : actor.fights) {
            this.mirror_fight_combo.addItem(x);
            if (x == prev) {
                this.mirror_fight_combo.setSelectedItem(x);
            }
        }
        this.updateMirrorFightSelection();
        this.mirror_lock = save;
    }
    
    private void updateMirrorFightSelection() {
        final boolean save = this.mirror_lock;
        this.mirror_lock = true;
        final WLFight fight = this.mirror_fight_combo.getPick();
        final Object prev = this.mirror_boss_combo.getSelectedItem();
        this.mirror_boss_combo.removeAllItems();
        this.mirror_boss_combo.addItem(CatusFrame.FAKE);
        if (prev == CatusFrame.FAKE) {
            this.mirror_boss_combo.setSelectedIndex(0);
        }
        for (final WLEnemy x : fight.bosses) {
            this.mirror_boss_combo.addItem(x);
            if (x == prev) {
                this.mirror_boss_combo.setSelectedItem(x);
            }
        }
        this.mirror_lock = save;
    }
    
    private boolean importChar(final boolean force) {
        final Profile p = this.importArmory(force);
        if (p == null) {
            return false;
        }
        final int scaled = this.mainProfile.scaledItemLevel;
        this.mainProfile.importProfile(p);
        this.mainProfile.setScaledItemLevel(scaled);
        this.mainProfile.validate();
        this.applyRegion(p.region.name());
        this.matchRaceAndProfs();
        this.loadTalentsFromString(p.talents);
        this.loadGlyphsFromString(p.glyphs);
        this._rebuildGear();
        this.import_pane.desc.setText("Last Import: " + p.charName + "@" + p.realmName + "/" + p.region);
        return true;
    }
    
    private Profile prepareExport(final String why) {
        final Profile p = this.importArmory(false);
        if (p == null) {
            return null;
        }
        if (p.race != this.mainProfile.race) {
            this.showError("Export: " + why, "Your race doesn't match your armory.");
            return null;
        }
        if (p.profs != this.mainProfile.profs) {
            this.showError("Export: " + why, "Your professions don't match your armory.");
            return null;
        }
        if (this.sim.guardian) {
            p.spec = SpecT.GUARD;
        }
        if (!this.checkItemLevels(why)) {
            return null;
        }
        return p;
    }
    
    private boolean checkItemLevels(final String why) {
        for (final Profile.Slot x : this.mainProfile.SLOTS) {
            if (x.getEffectiveUpgradeLevel(this.asiaMode) < 0) {
                this.showError("Export: " + why, String.format("Your %s: %s is using an unobtainable item level (%+d).", x.type.name, x.getItemName(true), x.getItemLevelDelta()));
                return false;
            }
        }
        return true;
    }
    
    private String getRealmSlug(final String name, final String guess, final RegionT region, final boolean force) {
        if (guess != null && !guess.isEmpty()) {
            return this.api.properRealmSlug(region, guess);
        }
        final DialogProg pd = new DialogProg(this, "Searching " + region + " for \"" + name + "\"");
        Pair<ClassT, Realm>[] matches;
        try {
            matches = (Pair<ClassT, Realm>[])pd.compute((DialogProg.Getter<Pair[]>)new DialogProg.Getter<Pair<ClassT, Realm>[]>() {
                @Override
                public Pair<ClassT, Realm>[] get() {
                    return CatusFrame.this.api.findChar(name, region, force, ClassT.DRUID, new ClassT[0]);
                }
            });
        }
        catch (RuntimeException err) {
            this.showError("Search Failed", err);
            return null;
        }
        if (matches.length <= 1) {
            return matches[0].cdr.slug;
        }
        final Realm realm = new RealmChooser(this, matches, false).getRealm();
        if (realm == null) {
            return null;
        }
        return realm.slug;
    }
    
    private RecentArmory parsePlayer(final boolean force) {
        final String input = this.import_char_field.getText().trim();
        if (input.isEmpty()) {
            return null;
        }
        RegionT region0 = this.region_combo.getPick();
        final String urlTest = "/character/";
        int pos = input.indexOf(urlTest);
        String name;
        String rest;
        if (pos >= 0) {
            final String[] v = input.substring(pos + urlTest.length()).split("/");
            if (v.length < 2) {
                this.showError("Import Failed", "Malformed Armory URL:\n\n" + input);
                return null;
            }
            for (final RegionT x : RegionT.values()) {
                if (input.contains(x.host)) {
                    region0 = x;
                    break;
                }
            }
            name = Utils.urlDecode(v[1]);
            rest = Utils.urlDecode(v[0]);
        }
        else {
            pos = input.indexOf(" ");
            if (pos == -1) {
                name = input;
                rest = "";
            }
            else {
                name = input.substring(0, pos);
                rest = input.substring(pos + 1).trim();
            }
        }
        final String realmSlug = this.getRealmSlug(name, rest, region0, force);
        if (realmSlug == null) {
            return null;
        }
        return new RecentArmory(name, realmSlug, region0, 0L);
    }
    
    private Profile importArmory(final boolean force) {
        final RecentArmory a = this.parsePlayer(force);
        if (a == null) {
            return null;
        }
        final DialogProg dp = new DialogProg(this, "Importing \"" + a.name + "\" from " + a.realm + "/" + a.region + (force ? " (Forced)" : ""));
        Profile p;
        try {
            p = dp.compute((DialogProg.Getter<Profile>)new DialogProg.Getter<Profile>() {
                @Override
                public Profile get() {
                    return CatusFrame.this.api.loadChar(a.name, a.realm, a.region, ClassT.DRUID, 90, SpecT.FERAL.specIndex, force);
                }
            });
        }
        catch (RuntimeException err) {
            this.showError("Import Failed", err);
            return null;
        }
        this.import_char_field.setText(p.charName + CatusFrame.JOINT_NAME_REALM_SEP + p.realmName + "!");
        while (true) {
            for (final RecentArmory x : this.recentList) {
                if (x.name.equals(p.charName) && x.realm.equals(p.realmName) && x.region == p.region) {
                    x.time = System.currentTimeMillis();
                    Collections.sort(this.recentList, RecentArmory.CMP_TIME);
                    this._updateRecentCombo();
                    p.validate();
                    return p;
                }
            }
            this.recentList.add(new RecentArmory(p.charName, p.realmName, p.region, System.currentTimeMillis()));
            continue;
        }
    }
    
    private boolean compareGear(final Profile p0, final Profile p1) {
        final CompareGear cg = new CompareGear();
        if (cg.compare(p0, p1)) {
            new DialogText(this, cg.title, "OK").showText(cg.message);
            return true;
        }
        this.showError(cg.title, cg.message);
        return false;
    }
    
    private FeralSim copySim(final boolean warn) {
        final FeralConfig copyCfg = this._extractConfig();
        if (copyCfg == null) {
            return null;
        }
        return this.copySim(warn, copyCfg);
    }
    
    private FeralSim copySimAndEncounter() {
        final FeralSim sim = this.copySim(true);
        return (sim != null && this.copyEncounter(sim)) ? sim : null;
    }
    
    private FeralSim copySim(final boolean warn, final FeralConfig copyCfg) {
        final FeralSim copySim = new FeralSim();
        try {
            final double max = 10.0;
            final double min = 0.001;
            double temp = UI.parse_double(this.hotfix_catAuto_field, 1.0, min, max);
            if (temp != 1.0) {
                copySim.hotfixed = true;
                final FeralSim feralSim = copySim;
                feralSim.modDmg_catAuto *= temp;
            }
            temp = UI.parse_double(this.hotfix_rip_field, 1.0, min, max);
            if (temp != 1.0) {
                copySim.hotfixed = true;
                final FeralSim feralSim2 = copySim;
                feralSim2.modDmg_rip *= temp;
            }
            temp = UI.parse_double(this.hotfix_rake_field, 1.0, min, max);
            if (temp != 1.0) {
                copySim.hotfixed = true;
                final FeralSim feralSim3 = copySim;
                feralSim3.modDmg_rake *= temp;
            }
            temp = UI.parse_double(this.hotfix_thrash_field, 1.0, min, max);
            if (temp != 1.0) {
                copySim.hotfixed = true;
                final FeralSim feralSim4 = copySim;
                feralSim4.modDmg_thrash *= temp;
            }
            temp = UI.parse_double(this.hotfix_shrangle_field, 1.0, min, max);
            if (temp != 1.0) {
                copySim.hotfixed = true;
                final FeralSim feralSim5 = copySim;
                feralSim5.modDmg_shrangle *= temp;
            }
            temp = UI.parse_double(this.hotfix_ravage_field, 1.0, min, max);
            if (temp != 1.0) {
                copySim.hotfixed = true;
                final FeralSim feralSim6 = copySim;
                feralSim6.modDmg_ravage *= temp;
            }
            temp = UI.parse_double(this.hotfix_swipe_field, 1.0, min, max);
            if (temp != 1.0) {
                copySim.hotfixed = true;
                final FeralSim feralSim7 = copySim;
                feralSim7.modDmg_swipe *= temp;
            }
            temp = UI.parse_double(this.hotfix_fb_field, 1.0, min, max);
            if (temp != 1.0) {
                copySim.hotfixed = true;
                final FeralSim feralSim8 = copySim;
                feralSim8.modDmg_fb *= temp;
            }
            temp = UI.parse_double(this.hotfix_sr_field, 1.0, min, max);
            if (temp != 1.0) {
                copySim.hotfixed = true;
                final FeralSim feralSim9 = copySim;
                feralSim9.modDmg_sr *= temp;
            }
            temp = UI.parse_double(this.hotfix_tf_field, 1.0, min, max);
            if (temp != 1.0) {
                copySim.hotfixed = true;
                final FeralSim feralSim10 = copySim;
                feralSim10.modDmg_tf *= temp;
            }
            temp = UI.parse_double(this.hotfix_nv_field, 1.0, 0.001, 10.0);
            if (temp != 1.0) {
                copySim.hotfixed = true;
                final FeralSim feralSim11 = copySim;
                feralSim11.modDmg_nv *= temp;
            }
            temp = UI.parse_double(this.hotfix_doc_field, 1.0, min, max);
            if (temp != 1.0) {
                copySim.hotfixed = true;
                final FeralSim feralSim12 = copySim;
                feralSim12.modDmg_doc *= temp;
            }
            temp = UI.parse_double(this.hotfix_wrath_field, 1.0, min, max);
            if (temp != 1.0) {
                copySim.hotfixed = true;
                final FeralSim feralSim13 = copySim;
                feralSim13.modDmg_wrath *= temp;
            }
            temp = UI.parse_double(this.hotfix_hurricane_field, 1.0, min, max);
            if (temp != 1.0) {
                copySim.hotfixed = true;
                final FeralSim feralSim14 = copySim;
                feralSim14.modDmg_hurricane *= temp;
            }
        }
        catch (RuntimeException err) {
            this.showError("Invalid Hotfixes", err);
            return null;
        }
        final ArrayList<String> warnings = new ArrayList<String>();
        this.applyConfigToSim(copySim);
        copySim.setup(this.mainProfile.copyProfile(), this.swapProfile.copyProfile(), this.spec.copy(), copyCfg, warnings);
        if (warn && !warnings.isEmpty() && 0 != JOptionPane.showConfirmDialog(this, String.format("The following %d warning(s) were encountered when preparing the simulator:\n\n%s\n\nDo you want to continue?", warnings.size(), Fmt.join(warnings, "\n")), "Simulator Warning", 0, 2)) {
            return null;
        }
        return copySim;
    }
    
    String getScript(final String fight) {
        final String script = this.hc.getStr_silent("https://dl.dropboxusercontent.com/u/2989349/catus_data/Encounters/44/" + fight + ".txt", "Encounter-$N$", 0);
        if (script == null) {
            this.showError("Action List Error", "Unable to download action list: " + fight);
        }
        return script;
    }
    
    boolean extractCommon(final Encounter en) {
        if (this.fight_periodicDmgMod_check.isSelected()) {
            try {
                en.periodicDmgMod = UI.parse_double(this.fight_periodicDmgMod_mod_field, 0.0, -100.0, 500.0) / 100.0;
                if (en.periodicDmgMod == 0.0) {
                    throw new RuntimeException(this.fight_periodicDmgMod_mod_field.getName() + " must be non-zero.");
                }
                final String s = this.fight_periodicDmgMod_freq_field.getText();
                final int pos = s.indexOf(44);
                if (pos >= 0) {
                    en.periodicDmg_freq0 = InvFmt.parseIntDist_time(s.substring(0, pos), this.fight_periodicDmgMod_freq_field.getName(), 1000);
                    en.periodicDmg_freq = InvFmt.parseIntDist_time(s.substring(pos + 1), this.fight_periodicDmgMod_freq_field.getName(), 1000);
                }
                else {
                    en.periodicDmg_freq0 = null;
                    en.periodicDmg_freq = UI.parseIntDist_time(this.fight_periodicDmgMod_freq_field, 1000);
                }
                if (IntDist.nullIfZero(en.periodicDmg_freq) == null) {
                    throw new RuntimeException("Repeating frequency must be a positive time interval.");
                }
                en.periodicDmg_time = UI.parseIntDist_time(this.fight_periodicDmgMod_time_field, 1000);
            }
            catch (RuntimeException err) {
                this.showError("Periodic Damage Modifier Settings", err);
                return false;
            }
        }
        if (this.fight_periodicIdle_check.isSelected()) {
            try {
                en.periodicIdle_freq = UI.parseIntDist_time(this.fight_periodicIdle_freq_field, 1000);
                if (en.periodicIdle_freq == null) {
                    throw new RuntimeException(this.fight_periodicIdle_freq_field.getName() + " is required.");
                }
                en.periodicIdle_time = UI.parseIntDist_time(this.fight_periodicIdle_time_field, 1000);
                if (en.periodicIdle_time == null) {
                    throw new RuntimeException(this.fight_periodicIdle_time_field.getName() + " is required.");
                }
                en.periodicIdle_limit = UI.parse_posInt(this.fight_periodicIdle_limit_field, false);
            }
            catch (RuntimeException err) {
                this.showError("Periodic Intermission Settings", err);
                return false;
            }
        }
        if (this.fight_periodicCast_check.isSelected()) {
            try {
                en.periodicCast_freq = UI.parseIntDist_time(this.fight_periodicCast_freq_field, 1000);
                if (en.periodicCast_freq == null) {
                    throw new RuntimeException(this.fight_periodicCast_freq_field.getName() + " is required.");
                }
            }
            catch (RuntimeException err) {
                this.showError("Periodic Cast Settings", err);
                return false;
            }
        }
        if (this.fight_cooldownDelay_check.isSelected()) {
            try {
                en.cooldownDelay = UI.parseIntDist_time(this.fight_cooldownDelay_field, 1000);
            }
            catch (RuntimeException err) {
                this.showError("Cooldown Delay Settings", err);
                return false;
            }
        }
        if (this.fight_advanced_check.isSelected()) {
            try {
                en.commands = Encounter.parseScript(this.fight_advanced_script);
            }
            catch (RuntimeException err) {
                this.showError("Encounter Script Error", err);
                return false;
            }
        }
        return true;
    }
    
    boolean prepareSim(final FeralSim sim) {
        if (!this.copyEncounter(sim)) {
            return false;
        }
        try {
            sim.setupCombat();
            return true;
        }
        catch (RuntimeException err) {
            this.showError(sim.encounter.name + " Encounter", "Simulator Error: " + err.getMessage());
            err.printStackTrace();
            return false;
        }
    }
    
    boolean isDurationTooShort(final int dur) {
        return dur < 10000 && JOptionPane.showConfirmDialog(this, "<html>The specified combat time is very short (" + Fmt.msDur(dur) + ").<br/><br/>Do you want to continue?</html>", "Warning: Combat Time", 0, 2) == 1;
    }
    
    boolean copyEncounter(final FeralSim sim) {
        final String fight = this.fight_tabs.getSelectedTitle();
        if ("Patchwerk".equalsIgnoreCase(fight)) {
            final Encounter_Patchwerk en = new Encounter_Patchwerk();
            if (!this.extractCommon(en)) {
                return false;
            }
            try {
                this.fight_patch_life_field.setName(this.fight_patch_life_combo.getPick());
                if (this.fight_patch_life_combo.getSelectedIndex() == 0) {
                    en.duration = UI.parseInt_time(this.fight_patch_life_field, 1000);
                    if (this.isDurationTooShort(en.duration)) {
                        return false;
                    }
                }
                else {
                    final double hp = UI.parse_double(this.fight_patch_life_field, 0.0, 1.0, 9.223372036854776E18);
                    if (hp == 0.0) {
                        throw new RuntimeException(this.fight_patch_life_field.getName() + " is required.");
                    }
                    en.health = (long)hp;
                }
                en.variance = this.fight_patch_timeVariance_combo.getPick().value;
                en.customArmor = UI.parse_nonNegInt(this.fight_patch_armor_field, -1);
                en.earlyDeathPerc = UI.parse_double(this.fight_patch_early_field, 0.0, 0.0, 100.0) / 100.0;
            }
            catch (RuntimeException err) {
                this.showError(fight + " Encounter", err);
                return false;
            }
            en.levelDelta = this.fight_patch_class_combo.getPick().value;
            en.front = this.fight_patch_front_check.isSelected();
            en.canDodge = this.fight_patch_dodge_check.isSelected();
            en.canBlock = this.fight_patch_block_check.isSelected();
            en.canParry = this.fight_patch_parry_check.isSelected();
            if (en.front && sim.cfg.generator == FeralSim.Generator.SHRED) {
                this.showError(fight + " Encounter", "Attacking from Front with Shred.");
                return false;
            }
            sim.encounter = en;
        }
        else if ("Cleave".equalsIgnoreCase(fight)) {
            final Encounter_Cleave en2 = new Encounter_Cleave();
            if (!this.extractCommon(en2)) {
                return false;
            }
            try {
                en2.duration = UI.parseInt_time(this.fight_cleave_time_field, 1000);
                if (this.isDurationTooShort(en2.duration)) {
                    return false;
                }
                en2.freq = UI.parseIntDist_time(this.fight_cleave_freq_field, 1000);
                en2.health = (int)UI.parse_double(this.fight_cleave_health_field, 0.0, 0.0, Double.POSITIVE_INFINITY);
                en2.frontProb = UI.parse_double(this.fight_cleave_frontProb_field, 0.0, 0.0, 100.0) / 100.0;
                en2.lifetime = UI.parseIntDist_time(this.fight_cleave_lifetime_field, 1000);
            }
            catch (RuntimeException err) {
                this.showError(fight + " Encounter", err);
                return false;
            }
            en2.levelDelta = this.fight_cleave_level_combo.getPick().value;
            en2.fixed = (this.fight_cleave_style_combo.getSelectedIndex() == 1);
            en2.at_start = this.fight_cleave_start_check.isSelected();
            en2.adds = this.fight_cleave_adds_spinner.getValue();
            en2.minCleaveSize = this.fight_cleave_minSize_spinner.getValue();
            sim.encounter = en2;
        }
        else {
            if (!"Mirror".equalsIgnoreCase(fight)) {
                this.showError("Encounter Error", "Unsupported Encounter Type: " + fight);
                return false;
            }
            final Encounter_Mirror en3 = new Encounter_Mirror();
            if (!this.extractCommon(en3)) {
                return false;
            }
            if (!this.checkMirrorScript(en3)) {
                return false;
            }
            Encounter_Mirror.Trigger firstEnd = null;
            for (final Encounter_Mirror.Trigger x : en3.triggers) {
                if (x.type == Encounter_Mirror.Type.END) {
                    firstEnd = x;
                    break;
                }
            }
            if (firstEnd == null || en3.triggers[en3.triggers.length - 1] != firstEnd) {
                this.showError("Mirror Script Error", "Missing end of combat trigger.");
                return false;
            }
            en3.desc = "Mirror of " + this.fight_mirror_source_lbl.getText();
            en3.duration = firstEnd.time;
            if (this.isDurationTooShort(en3.duration)) {
                return false;
            }
            sim.encounter = en3;
        }
        final String script = this.getScript(fight);
        if (script == null) {
            return false;
        }
        String entry = "main";
        if (this.fight_entry_check.isSelected()) {
            entry = this.fight_entry_field.getText().trim();
            if (entry.isEmpty()) {
                this.showError("Encounter Error", "Undefined alternative entry point.");
                return false;
            }
        }
        try {
            sim.loadSimc(script, entry);
        }
        catch (RuntimeException err2) {
            this.showError(fight + " Encounter", "Script Error: " + err2.getMessage());
            err2.printStackTrace();
            return false;
        }
        sim.resetAll();
        return true;
    }
    
    private boolean checkMirrorScript(final Encounter_Mirror en) {
        en.enabled.add(Encounter_Mirror.Type.END);
        if (this.fight_mirror_idle_check.isSelected()) {
            en.enabled.add(Encounter_Mirror.Type.WAIT);
        }
        if (this.fight_mirror_health_check.isSelected()) {
            en.enabled.add(Encounter_Mirror.Type.HEALTH);
        }
        if (this.fight_mirror_self_check.isSelected()) {
            en.enabled.add(Encounter_Mirror.Type.SELF);
        }
        if (this.fight_mirror_external_check.isSelected()) {
            en.enabled.add(Encounter_Mirror.Type.EXTERNAL);
        }
        try {
            en.triggers = Encounter_Mirror.parseTriggers(this.fight_mirror_script, en.enabled);
            return true;
        }
        catch (RuntimeException err) {
            this.showError_html("Mirror Script Error", err.getMessage());
            return false;
        }
    }
    
    Pair<String, StatMap> makeNamedSingleStatMap(final StatT stat, final int amt) {
        return new Pair<String, StatMap>(stat.name, StatMap.makeSingle(stat, amt));
    }
    
    private void weights_space() {
        final File csvFile = this.getReportFile("Stat Weights", "Weights", ".csv");
        if (csvFile == null) {
            return;
        }
        int iter;
        int delta;
        try {
            iter = UI.parse_posInt(this.weight_iter_field, true);
            delta = UI.parse_posInt(this.weight_delta_field, true);
        }
        catch (RuntimeException err) {
            this.showError(this.weight_pane.name, err);
            return;
        }
        final ArrayList<StatT> stats = new ArrayList<StatT>();
        if (this.weight_includeHit_check.isSelected()) {
            stats.add(StatT.HIT);
        }
        if (this.weight_includeExp_check.isSelected()) {
            stats.add(StatT.EXP);
        }
        if (this.weight_includeAgi_check.isSelected()) {
            stats.add(StatT.AGI);
        }
        if (this.weight_includeStr_check.isSelected()) {
            stats.add(StatT.STR);
        }
        if (this.weight_includeAP_check.isSelected()) {
            stats.add(StatT.AP);
        }
        if (this.weight_includeInt_check.isVisible() && this.weight_includeInt_check.isSelected()) {
            stats.add(StatT.INT);
        }
        if (this.weight_includeSP_check.isVisible() && this.weight_includeSP_check.isSelected()) {
            stats.add(StatT.SP);
        }
        if (this.weight_group_check.isSelected()) {
            stats.add(null);
        }
        else {
            if (this.weight_includeMastery_check.isSelected()) {
                stats.add(StatT.MASTERY);
            }
            if (this.weight_includeHaste_check.isSelected()) {
                stats.add(StatT.HASTE);
            }
            if (this.weight_includeCrit_check.isSelected()) {
                stats.add(StatT.CRIT);
            }
        }
        final StatMap forceStats = new StatMap();
        if (this.weight_forceHit_check.isSelected()) {
            final int max = UI.parse_nonNegIntOrPercentWithScale(this.weight_hitMax_field, 0, 34000.0);
            if (max > 0) {
                forceStats.add(StatT.HIT, max - this.mainProfile.gearAndExtraStat(StatT.HIT));
            }
        }
        if (this.weight_forceExp_check.isSelected()) {
            final int max = UI.parse_nonNegIntOrPercentWithScale(this.weight_expMax_field, 0, 34000.0);
            if (max > 0) {
                forceStats.add(StatT.EXP, max - this.mainProfile.gearAndExtraStat(StatT.EXP));
            }
        }
        final FeralConfig cfg0 = this._extractConfig();
        if (cfg0 == null) {
            return;
        }
        cfg0.extra_inert.add(forceStats);
        final FeralSim copySim = this.copySim(true, cfg0);
        if (copySim == null || !this.copyEncounter(copySim)) {
            return;
        }
        new DialogProg(this, "Generating Simulator").execute(new Runnable() {
            @Override
            public void run() {
                copySim.setupCombat();
            }
        });
        final boolean inert = this.weight_inert_check.isSelected();
        new SimFrame("Stat Space", copySim, null, csvFile).random(iter, delta, inert, stats.toArray(new StatT[stats.size()]));
    }
    
    private void weights_sim(final boolean debug) {
        final File file = this.getReportFile_text("Stat Weights", "Weights");
        if (file == null) {
            return;
        }
        final ArrayList<Pair<String, StatMap>> stats = new ArrayList<Pair<String, StatMap>>();
        int iter;
        int delta;
        try {
            iter = UI.parse_posInt(this.weight_iter_field, true);
            delta = UI.parse_posInt(this.weight_delta_field, true);
            if (this.weight_includeHit_check.isSelected()) {
                final int hit = this.mainProfile.gearAndExtraStat(StatT.HIT);
                final int max = UI.parse_nonNegIntOrPercentWithScale(this.weight_hitMax_field, 100000, 34000.0);
                if (hit <= max || this.weight_forceHit_check.isSelected()) {
                    stats.add(this.makeNamedSingleStatMap(StatT.HIT, -delta));
                }
                else {
                    stats.add(this.makeNamedSingleStatMap(StatT.HIT, -delta + max - hit));
                }
            }
            if (this.weight_includeExp_check.isSelected()) {
                final int exp = this.mainProfile.gearAndExtraStat(StatT.EXP);
                final int max = UI.parse_nonNegIntOrPercentWithScale(this.weight_expMax_field, 100000, 34000.0);
                final int idx = this.weight_exp_combo.getSelectedIndex();
                if (idx < 2) {
                    stats.add(this.makeNamedSingleStatMap(StatT.EXP, -delta + ((exp <= max || this.weight_forceExp_check.isSelected()) ? 0 : (max - exp))));
                }
                if (idx > 0) {
                    stats.add(new Pair<String, StatMap>("Expertise++", StatMap.makeSingle(StatT.EXP, delta + ((exp >= max || this.weight_forceExp_check.isSelected()) ? 0 : (max - exp)))));
                }
            }
            if (this.weight_includeAgi_check.isSelected()) {
                stats.add(this.makeNamedSingleStatMap(StatT.AGI, delta));
            }
            if (this.weight_includeStr_check.isSelected()) {
                stats.add(this.makeNamedSingleStatMap(StatT.STR, delta));
            }
            if (this.weight_includeAP_check.isSelected()) {
                stats.add(this.makeNamedSingleStatMap(StatT.AP, delta));
            }
            if (this.weight_includeWeapDmg_check.isSelected()) {
                stats.add(this.makeNamedSingleStatMap(StatT.WDMG, delta));
            }
            if (this.weight_includeInt_check.isVisible() && this.weight_includeInt_check.isSelected()) {
                stats.add(this.makeNamedSingleStatMap(StatT.INT, delta));
            }
            if (this.weight_includeSP_check.isVisible() && this.weight_includeSP_check.isSelected()) {
                stats.add(this.makeNamedSingleStatMap(StatT.SP, delta));
            }
            if (this.weight_group_check.isSelected()) {
                final StatMap map = new StatMap();
                map.add(StatT.MASTERY, delta / 3);
                map.add(StatT.HASTE, delta / 3);
                map.add(StatT.CRIT, delta / 3);
                stats.add(new Pair<String, StatMap>("Secondary", map));
            }
            else {
                if (this.weight_includeMastery_check.isSelected()) {
                    stats.add(this.makeNamedSingleStatMap(StatT.MASTERY, delta));
                }
                if (this.weight_includeHaste_check.isSelected()) {
                    stats.add(this.makeNamedSingleStatMap(StatT.HASTE, delta));
                }
                if (this.weight_includeCrit_check.isSelected()) {
                    stats.add(this.makeNamedSingleStatMap(StatT.CRIT, delta));
                }
            }
        }
        catch (RuntimeException err) {
            this.showError(this.weight_pane.name, err);
            return;
        }
        if (stats.isEmpty()) {
            this.showError(this.weight_pane.name, "There are no stats weights to calculate.  Please select one or more stats and try again.");
            return;
        }
        final StatMap forceStats = new StatMap();
        if (this.weight_forceHit_check.isSelected()) {
            final int max = UI.parse_nonNegIntOrPercentWithScale(this.weight_hitMax_field, 0, 34000.0);
            if (max > 0) {
                forceStats.add(StatT.HIT, max - this.mainProfile.gearAndExtraStat(StatT.HIT));
            }
        }
        if (this.weight_forceExp_check.isSelected()) {
            final int max = UI.parse_nonNegIntOrPercentWithScale(this.weight_expMax_field, 0, 34000.0);
            if (max > 0) {
                forceStats.add(StatT.EXP, max - this.mainProfile.gearAndExtraStat(StatT.EXP));
            }
        }
        final ArrayList<FeralSim> sims = new ArrayList<FeralSim>();
        final FeralConfig cfg0 = this._extractConfig();
        if (cfg0 == null) {
            return;
        }
        final boolean inert = this.weight_inert_check.isSelected();
        cfg0.extra_inert.add(forceStats);
        final FeralSim sim0 = this.copySim(true, cfg0);
        if (sim0 == null) {
            return;
        }
        if (debug) {
            final StringBuilder sb = new StringBuilder();
            sb.append(String.format("Hit: %5.2f%% (%d)\n", sim0.getMeleeHit() * 100.0, sim0.getMeleeHitRating()));
            sb.append(String.format("Exp: %5.2f%% (%d)\n", sim0.getMeleeExp() * 100.0, sim0.getMeleeExpRating()));
            if (forceStats.any()) {
                sb.append("\nForced: ");
                sb.append(forceStats);
                sb.append("\n");
            }
            sb.append("\n[Deltas]");
            for (final Pair<String, StatMap> pr : stats) {
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                Fmt.padRight(sb, pr.car, 16);
                sb.append(pr.cdr.toString());
            }
            new DialogText(this, this.weight_pane.name, "OK").showText(sb.toString());
            return;
        }
        sim0.simName = "Baseline";
        sim0.simDesc = "";
        sims.add(sim0);
        for (final Pair<String, StatMap> pr2 : stats) {
            final FeralConfig cfg2 = cfg0.copy();
            if (inert) {
                cfg2.extra_inert.add(pr2.cdr);
            }
            else {
                cfg2.extra_active.add(pr2.cdr);
            }
            final FeralSim sim2 = this.copySim(false, cfg2);
            sim2.simName = pr2.car;
            sim2.simDesc = pr2.cdr.toString();
            sim2.simData = pr2.cdr.sum();
            sims.add(sim2);
        }
        final int cores = this.weight_cores_combo.getPick().value;
        try {
            new DialogProg(this, "Generating Simulators").forEach(sims, new DialogProg.Each<FeralSim>() {
                @Override
                public void each(final int index, final FeralSim value) {
                    if (!CatusFrame.this.prepareSim(value)) {
                        throw new RuntimeException();
                    }
                }
            });
        }
        catch (RuntimeException err2) {
            return;
        }
        new WeightFrame(this.mainProfile.copyProfile(), this.swapProfile.copyProfile(), iter, delta, inert, file, sims.toArray(new FeralSim[sims.size()])).go(cores);
    }
    
    private void ilvl_sim(final boolean alt) {
        File csvFile;
        if (this.ilvl_saveCSV_check.isSelected()) {
            csvFile = this.getReportFile("ScalingData", null, ".csv");
            if (csvFile == null) {
                return;
            }
        }
        else {
            csvFile = null;
        }
        int iter;
        int lower;
        int upper;
        try {
            iter = UI.parse_posInt(this.ilvl_iter_field, true);
            lower = UI.parse_posInt(this.ilvl_lower_field, true);
            upper = UI.parse_posInt(this.ilvl_upper_field, true);
            if (upper < lower) {
                throw new RuntimeException(String.format("Upper item level (%d) should be larger than lower item level (%d).", upper, lower));
            }
            if (lower < 420) {
                throw new RuntimeException(String.format("Lower item level should be in the MoP range (420 or higher.)", new Object[0]));
            }
        }
        catch (RuntimeException err) {
            this.showError("Scaling Settings", err);
            return;
        }
        new DialogProg(this, "Preparing Simulator").execute(new Runnable() {
            @Override
            public void run() {
                final FeralSim copySim = CatusFrame.this.copySim(true);
                if (copySim == null) {
                    return;
                }
                if (!CatusFrame.this.prepareSim(copySim)) {
                    return;
                }
                new SimFrame(CatusFrame.this.selectedConfig.name, copySim, null, csvFile).scale(iter, lower, upper);
            }
        });
    }
    
    private void sim_log() {
        final File file = this.getReportFile_text("Combat Log", "Log");
        if (file == null) {
            return;
        }
        new DialogProg(this, "Generating Combat Log").execute(new Runnable() {
            @Override
            public void run() {
                final FeralSim copySim = CatusFrame.this.copySim(true);
                if (copySim == null) {
                    return;
                }
                if (!CatusFrame.this.prepareSim(copySim)) {
                    return;
                }
                final ArrayList<String> lines = new ArrayList<String>(1000);
                final LineWriter lw = LineWriter.buffered(lines);
                copySim.printEnergy = CatusFrame.this.combatLog_printEnergy_check.isSelected();
                copySim.printDebug = CatusFrame.this.combatLog_printDebug_check.isSelected();
                copySim.printMore = (copySim.printDebug && CatusFrame.this.combatLog_printMore_check.isSelected());
                copySim.createCombatLog(lw);
                lw.add();
                lw.add("[Profile]");
                lw.add(CompactGear.toString(CatusFrame.this.mainProfile, true, true));
                if (copySim.mainGear != copySim.swapGear) {
                    lw.add();
                    lw.add("[Caster Weapon]");
                    lw.add(CompactGear.toString(CatusFrame.this.swapProfile));
                }
                try {
                    Utils.writeLines(file, lines);
                    Utils.openFile(file);
                }
                catch (IOException err) {
                    file.delete();
                    CatusFrame.this.showError("Combat Log Error", String.format("Unable to save combat log: %s\n\n%s", file.getName(), err));
                }
            }
        });
    }
    
    private void sim_dist() {
        final File reportFile = this.getReportFile_text("Distribution", "Dist");
        if (reportFile == null) {
            return;
        }
        File csvFile;
        if (this.sim_csv_check.isSelected()) {
            csvFile = this.getReportFile("Distribution", "Dist", ".csv");
            if (csvFile == null) {
                return;
            }
        }
        else {
            csvFile = null;
        }
        int iterNum;
        double dpsErr;
        int step;
        try {
            iterNum = UI.parse_posInt(this.sim_iter_field, false);
            dpsErr = UI.parse_double(this.sim_err_field, 0.0, 1.0, Double.POSITIVE_INFINITY);
            step = UI.parseInt_time(this.sim_timeDist_field, 1000);
            if (step > 0 && step < 100) {
                throw new RuntimeException(this.sim_timeDist_field.getName() + " time increment too small.");
            }
        }
        catch (RuntimeException err) {
            this.showError("Simulator Settings", err);
            return;
        }
        if (iterNum == 0 && dpsErr == 0.0) {
            this.showError("Simulator Settings", "Simulation is unbounded.");
            return;
        }
        final DialogProg dp = new DialogProg(this, "Preparing Simulator");
        final FeralSim copySim = dp.compute((DialogProg.Getter<FeralSim>)new DialogProg.Getter<FeralSim>() {
            @Override
            public FeralSim get() {
                final FeralSim copySim = CatusFrame.this.copySim(true);
                if (copySim == null) {
                    return null;
                }
                if (!CatusFrame.this.prepareSim(copySim)) {
                    return null;
                }
                return copySim;
            }
        });
        if (copySim != null) {
            new SimFrame(this.selectedConfig.name, copySim, reportFile, csvFile).go(iterNum, dpsErr, step);
        }
    }
    
    static JLabel _makeBottomCenterLabel(final String s) {
        final JLabel temp = new JLabel(s, 0);
        temp.setVerticalAlignment(3);
        return temp;
    }
    
    private void reforge() {
        final ReforgeAlgo algo = new ReforgeAlgo();
        for (final ReforgeConstraint x : this.reforgeOld_constraints) {
            final String minExpr = x.min_field.getText().trim();
            final String maxExpr = x.max_field.getText().trim();
            int min = 0;
            int max = 999999;
            if (!minExpr.isEmpty()) {
                try {
                    min = Math.max(min, Integer.parseInt(minExpr));
                }
                catch (NumberFormatException err) {
                    this.showError(String.format("Invalid Lower Bound: %s", x.stat), "Unable to parse: " + minExpr);
                    return;
                }
            }
            if (!maxExpr.isEmpty()) {
                try {
                    max = Math.min(max, Integer.parseInt(maxExpr));
                }
                catch (NumberFormatException err) {
                    this.showError(String.format("Invalid Upper Bound: %s", x.stat), "Unable to parse: " + maxExpr);
                    return;
                }
            }
            if (min > max) {
                this.showError(String.format("Invalid Bounds: %s", x.stat), String.format("Lower bound (%d) must be less than or equal to upper bound (%s)", min, max));
                return;
            }
            if (min > 0 || max < 999999) {
                algo.between(x.stat, min, max);
            }
            final String weightExpr = x.weight_field.getText().trim();
            if (!weightExpr.isEmpty()) {
                try {
                    final double weight = Double.parseDouble(weightExpr);
                    algo.setWeight(x.stat, weight);
                }
                catch (NumberFormatException err2) {
                    this.showError(String.format("Invalid %s Weight", x.stat), "Unable to parse: " + weightExpr);
                    return;
                }
            }
            if (x.prevent_check.isSelected()) {
                algo.dontReforge(x.stat);
            }
        }
        final StringBuilder sb = new StringBuilder();
        final LineWriter lw = LineWriter.buffered(sb);
        final ProgressDialog pd = new ProgressDialog(this, "Reforging");
        final Profile copy = new Profile();
        copy.importProfile(this.mainProfile);
        class Ret
        {
            String error;
        }
        final Ret ret = new Ret();
        final AtomicBoolean abort = new AtomicBoolean(false);
        pd.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent we) {
                abort.set(true);
            }
        });
        new Thread() {
            @Override
            public void run() {
                try {
                    copy.reforge(algo, CatusFrame.this.getSlotSet(), CatusFrame.REFORGE_UNIVERSE, 0L, 0, lw, abort, pd.pb);
                }
                catch (RuntimeException err) {
                    ret.error = err.getMessage();
                }
                pd.dispose();
            }
        }.start();
        pd.setVisible(true);
        if (ret.error != null) {
            if (!abort.get()) {
                this.showError("Reforge Error", ret.error);
            }
            return;
        }
        this.mainProfile.copySlots(copy);
        this._rebuildGear();
        if (sb.length() > 0) {
            new DialogText(this, "Reforge: Log", "OK").showText(sb.toString());
        }
    }
    
    private void _updateRecentCombo() {
        this.import_recent_combo_al.lock();
        this.import_recent_combo.removeAllItems();
        this.import_recent_combo.setVisible(!this.recentList.isEmpty());
        for (final RecentArmory x : this.recentList) {
            this.import_recent_combo.addItem(x);
        }
        this.import_recent_combo.addItem("Edit...");
        UI.setComboText(this.import_recent_combo);
        this.import_recent_combo_al.unlock();
        this.compareGear_recent_combo_al.lock();
        this.compareGear_recent_combo.removeAllItems();
        this.compareGear_recent_combo.setVisible(!this.recentList.isEmpty());
        for (final RecentArmory x : this.recentList) {
            this.compareGear_recent_combo.addItem(x);
        }
        UI.setComboText(this.compareGear_recent_combo);
        this.compareGear_recent_combo_al.unlock();
    }
    
    private void _updateNoobMode() {
        final boolean b = this.feature_noob_toggle.isSelected();
        this.groupBuffPane.panel.setVisible(!b);
        this.debuffPane.panel.setVisible(!b);
        this.prePane.panel.setVisible(!b);
        this.tempEffectPane.panel.setVisible(!b);
        this.consumePane.panel.setVisible(!b);
        this.hotfix_pane.panel.setVisible(!b);
        this.fight_stdEffects_btn.setVisible(!b);
        if (b) {
            this.loadConfig_effects0();
        }
    }
    
    private void _updateConfigOptions() {
        this._updateConsumeOptions();
        this._updateGroupBuffOptions();
        this._updateDebuffOptions();
        this._updateTempEffectOptions();
        this._updateHotWOptions();
    }
    
    private void _updateCustomStats() {
        if (this.customStats.any()) {
            this.customStats_clear_btn.setEnabled(true);
            this.customStats_lbl.setText(this.customStats.toString());
            this.customStats_lbl.setForeground(CatusFrame.DARK_RED);
        }
        else {
            this.customStats_clear_btn.setEnabled(false);
            this.customStats_lbl.setText("None");
            this.customStats_lbl.setForeground(null);
        }
    }
    
    public void initAndShow() {
        this.loadPrefs();
        this._updateConfigOptions();
        this._updateRegemOptions();
        this.refreshEncounterCombo();
        this.import_char_field.requestFocus();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                CatusFrame.this.savePrefs();
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CatusFrame.this.setVisible(true);
            }
        });
    }
    
    static void _setTextArea(final JTextArea ta, final String text) {
        ta.setText(text);
        ta.setSelectionStart(0);
        ta.setSelectionEnd(0);
    }
    
    static boolean actuallySelected(final JToggleButton btn) {
        final ButtonModel model = btn.getModel();
        if (model instanceof BetterToggleModel) {
            return ((BetterToggleModel)model).isActuallySelected();
        }
        return model.isSelected();
    }
    
    static JSONObject saveToggles(final JToggleButton[] toggles) {
        final JSONObject map = new JSONObject();
        for (final JToggleButton x : toggles) {
            map.put(x.getText(), actuallySelected(x));
        }
        return map;
    }
    
    static void loadToggles(final JSONObject map, final JToggleButton[] toggles) {
        for (final JToggleButton x : toggles) {
            x.setSelected(JSONHelp.getBoolean(map, x.getText(), false));
        }
    }
    
    public static String talentKey(final int index) {
        return "Talent" + API.TALENT_LEVELS[index];
    }
    
    JSONObject exportConfig(final String name) {
        final JSONObject root = new JSONObject();
        if (name != null) {
            root.put("Name", name);
        }
        root.put("Version", 44);
        root.put("Char", this.import_char_field.getText());
        root.put("Region", this.region_combo.exportPick());
        root.put("Race", this.race_combo.exportPick());
        root.put("Profs", this.mainProfile.profs);
        root.put("Gear", CompactGear.toString(this.mainProfile, false, false));
        root.put("Swap", CompactGear.toString(this.swapProfile, false, false));
        root.put("Talents", API.getTalentStr(this.talentBytes, 0));
        root.put("MajorGlyphs", this.getGlyphsAsString());
        root.put("ScaledItemLevel", this.mainProfile.scaledItemLevel);
        root.put("ChallengeMode", this.challengeMode);
        root.put("Food", this.consume_food_combo.getSelectedItem().toString());
        root.put("Flask", this.consume_flask_combo.exportPick());
        root.put("Potion", this.consume_potion_combo.getSelectedItem().toString());
        root.put("Potion0", this.pre_potion0_combo.getSelectedItem().toString());
        JSONObject map = new JSONObject();
        map.put("Swap", actuallySelected(this.hotw_swap_toggle));
        map.put("Wrath", actuallySelected(this.hotw_wrath_toggle));
        map.put("Hurricane", actuallySelected(this.hotw_hurricane_toggle));
        map.put("Before", actuallySelected(this.hotw_beforeBerserk_toggle));
        map.put("BitW", actuallySelected(this.hotw_bitw_toggle));
        root.put("HotW", map);
        map = new JSONObject();
        map.put("Delay", this.rot_delay_field.getText());
        map.put("React", this.rot_react_field.getText());
        map.put("Pool", this.rot_pool_field.getText());
        map.put("Generator", this.rot_generator_combo.exportPick());
        map.put("ThrashStyle", this.rot_thrashStyle_combo.exportPick());
        map.put("Symbiosis", this.rot_symbiosis_combo.exportPick());
        root.put("Rotation", map);
        root.put("Buffs", saveToggles(this.buff_toggles));
        map = saveToggles(this.debuff_toggles);
        map.put("WeakenedArmor", this.debuff_armor_combo.exportPick());
        root.put("Debuffs", map);
        map = saveToggles(this.tempEffect_toggles);
        for (final Spinner x : this.tempEffect_spinners) {
            map.put(x.name, x.getValue());
        }
        map.put("Heroism", this.tempEffect_hero_combo.exportPick());
        map.put("HeroismDelay", this.tempEffect_hero_field.getText());
        map.put("HeroismDrums", this.cfg.heroismDrums);
        root.put("TempEffects", map);
        map = saveToggles(this.pre_toggles);
        map.put("ProcReset", this.pre_procReset_combo.exportPick());
        map.put("Opener", this.pre_opener_combo.exportPick());
        map.put("Finisher0", this.pre_finisher0_combo.exportPick());
        map.put("UseEnergy0", this.pre_energy0_check.isSelected());
        map.put("Energy0", this.pre_energy0_field.getText());
        root.put("Precombat", map);
        root.put("Features", saveToggles(this.feature_options));
        map = new JSONObject();
        for (final StatT x2 : StatT.STATS) {
            final int delta = this.customStats.get(x2);
            if (delta != 0) {
                map.put(x2.name, delta);
            }
        }
        root.put("CustomStats", map);
        map = new JSONObject();
        for (final JTextField f : this.hotfix_fields) {
            final String val = f.getText().trim();
            if (!val.isEmpty()) {
                map.put(f.getName(), val);
            }
        }
        root.put("Hotfixes", map);
        map = new JSONObject();
        for (final SetBonusHelper x3 : this.bonusMap.values()) {
            if (x3.check.isSelected()) {
                map.put(x3.key(), true);
            }
        }
        root.put("SetBonuses", map);
        map = new JSONObject();
        map.put("Autosave", this.fight_autosave_check.isSelected());
        root.put("Sims", map);
        this.exportConfig_fights(root, false);
        return root;
    }
    
    void exportConfig_fights(final JSONObject root, final boolean onlySelected) {
        final String selected = this.fight_tabs.getSelectedTitle();
        final JSONObject fightMap = new JSONObject();
        fightMap.put("Selected", selected);
        if (!onlySelected || selected.equals("Patchwerk")) {
            final JSONObject map = new JSONObject();
            map.put("Class", this.fight_patch_class_combo.exportPick());
            map.put("LifeType", this.fight_patch_life_combo.exportPick());
            map.put("LifeValue", this.fight_patch_life_field.getText());
            map.put("Variance", this.fight_patch_timeVariance_combo.exportPick());
            map.put("CustomArmor", this.fight_patch_armor_field.getText());
            map.put("EarlyDeath", this.fight_patch_early_field.getText());
            map.put("Front", this.fight_patch_front_check.isSelected());
            map.put("CanDodge", this.fight_patch_dodge_check.isSelected());
            map.put("CanBlock", this.fight_patch_block_check.isSelected());
            map.put("CanParry", this.fight_patch_parry_check.isSelected());
            fightMap.put("Patchwerk", map);
        }
        if (!onlySelected || selected.equals("Cleave")) {
            final JSONObject map = new JSONObject();
            map.put("Level", this.fight_cleave_level_combo.exportPick());
            map.put("Style", this.fight_cleave_style_combo.exportPick());
            map.put("Duration", this.fight_cleave_time_field.getText());
            map.put("Adds", this.fight_cleave_adds_spinner.getValue());
            map.put("MinCleaveSize", this.fight_cleave_minSize_spinner.getValue());
            map.put("AtStart", this.fight_cleave_start_check.isSelected());
            map.put("Frequency", this.fight_cleave_freq_field.getText());
            map.put("Health", this.fight_cleave_health_field.getText());
            map.put("Lifetime", this.fight_cleave_lifetime_field.getText());
            map.put("FrontProb", this.fight_cleave_frontProb_field.getText());
            fightMap.put("Cleave", map);
        }
        if (!onlySelected || selected.equals("Mirror")) {
            final JSONObject map = new JSONObject();
            for (final JCheckBox x : this.fight_mirror_checks) {
                map.put(x.getName(), x.isSelected());
            }
            map.put("Script", this.fight_mirror_script);
            map.put("Source", this.fight_mirror_source_lbl.getText());
            fightMap.put("Mirror", map);
        }
        JSONObject map = new JSONObject();
        map.put("Enabled", this.fight_periodicDmgMod_check.isSelected());
        map.put("Mod", this.fight_periodicDmgMod_mod_field.getText());
        map.put("Frequency", this.fight_periodicDmgMod_freq_field.getText());
        map.put("Duration", this.fight_periodicDmgMod_time_field.getText());
        fightMap.put("PeriodicDmg", map);
        map = new JSONObject();
        map.put("Enabled", this.fight_periodicIdle_check.isSelected());
        map.put("Frequency", this.fight_periodicIdle_freq_field.getText());
        map.put("Duration", this.fight_periodicIdle_time_field.getText());
        map.put("Limit", this.fight_periodicIdle_limit_field.getText());
        fightMap.put("PeriodicIdle", map);
        map = new JSONObject();
        map.put("Enabled", this.fight_periodicCast_check.isSelected());
        map.put("Frequency", this.fight_periodicCast_freq_field.getText());
        fightMap.put("PeriodicCast", map);
        map = new JSONObject();
        map.put("Enabled", this.fight_advanced_check.isSelected());
        map.put("Script", this.fight_advanced_script);
        fightMap.put("Advanced", map);
        map = new JSONObject();
        map.put("Enabled", this.fight_entry_check.isSelected());
        map.put("Name", this.fight_entry_field.getText());
        fightMap.put("Entry", map);
        map = new JSONObject();
        map.put("Enabled", this.fight_cooldownDelay_check.isSelected());
        map.put("Delay", this.fight_cooldownDelay_field.getText());
        fightMap.put("CooldownDelay", map);
        root.put("Fights", fightMap);
    }
    
    void loadConfig(final JSONObject root) {
        this.loadConfig(root, false);
    }
    
    void loadConfig(final JSONObject root, final boolean justGear) {
        this.mainProfile.clearSlots();
        this.swapProfile.clearSlots();
        this.mainProfile.race = RaceT.MAP.get(JSONHelp.getStr(root, "Race", null), RaceT.NIGHT_ELF);
        this.mainProfile.profs = JSONHelp.getInt(root, "Profs", 0);
        this.swapProfile.race = this.mainProfile.race;
        this.swapProfile.profs = this.mainProfile.profs;
        final String mainCode = JSONHelp.getStr(root, "Gear", "");
        final String swapCode = JSONHelp.getStr(root, "Swap", "");
        boolean ok = true;
        ok &= CompactGear.fromString(this.api.quickLoader, this.mainProfile, mainCode);
        ok &= CompactGear.fromString(this.api.quickLoader, this.swapProfile, swapCode);
        if (!ok) {
            new DialogProg(this, "Loading Profiles").execute(new Runnable() {
                @Override
                public void run() {
                    CompactGear.fromString(CatusFrame.this.api, CatusFrame.this.mainProfile, mainCode);
                    CompactGear.fromString(CatusFrame.this.api, CatusFrame.this.swapProfile, swapCode);
                }
            });
        }
        this.mainProfile.validate();
        this.swapProfile.validate();
        if (!justGear) {
            this.import_char_field.setText(JSONHelp.getStr(root, "Char", "Edgy"));
            this.applyRegion(JSONHelp.getStr(root, "Region", null));
            this.mainProfile.setScaledItemLevel(JSONHelp.getInt(root, "ScaledItemLevel", 0));
            this.challengeMode = JSONHelp.getBoolean(root, "ChallengeMode", false);
            this.loadTalentsFromString(JSONHelp.getStr(root, "Talents", ""));
            this.loadGlyphsFromString(JSONHelp.getStr(root, "MajorGlyphs", ""));
            this.loadConfig_effects(root);
            JSONObject map = JSONHelp.getObject(root, "HotW");
            this.hotw_wrath_toggle.setSelected(JSONHelp.getBoolean(map, "Wrath", false));
            this.hotw_hurricane_toggle.setSelected(JSONHelp.getBoolean(map, "Hurricane", false));
            this.hotw_swap_toggle.setSelected(JSONHelp.getBoolean(map, "Swap", false));
            this.hotw_beforeBerserk_toggle.setSelected(JSONHelp.getBoolean(map, "Before", false));
            this.hotw_bitw_toggle.setSelected(JSONHelp.getBoolean(map, "BitW", true));
            loadToggles(JSONHelp.getObject(root, "Features"), this.feature_options);
            this._updateNoobMode();
            try {
                this.setBonusIgnoreChange = true;
                map = JSONHelp.getObject(root, "SetBonuses");
                if (map != null) {
                    for (final SetBonusHelper x : this.bonusMap.values()) {
                        if (JSONHelp.getBoolean(map, x.key(), false)) {
                            x.check.setSelected(true);
                        }
                    }
                }
            }
            finally {
                this.setBonusIgnoreChange = false;
            }
            map = JSONHelp.getObject(root, "Rotation");
            this.rot_delay_field.setText(JSONHelp.getStr(map, "Delay", "0-50"));
            this.rot_react_field.setText(JSONHelp.getStr(map, "React", "250"));
            this.rot_pool_field.setText(JSONHelp.getStr(map, "Pool", "500"));
            this.rot_generator_combo.importPick(JSONHelp.getStr(map, "Generator", null), FeralSim.Generator.SHRANGLE);
            this.rot_thrashStyle_combo.importPick(JSONHelp.getStr(map, "ThrashStyle", null), FeralSim.ThrashStyle.MAINTAIN);
            this.rot_symbiosis_combo.importPick(JSONHelp.getStr(map, "Symbiosis", null), 0);
            map = JSONHelp.getObject(root, "Sims");
            this.fight_autosave_check.setSelected(JSONHelp.getBoolean(map, "Autosave", false));
            this.loadConfig_fights(JSONHelp.getObject(root, "Fights"));
            this.customStats.clear();
            map = JSONHelp.getObject(root, "CustomStats");
            for (final StatT x2 : StatT.STATS) {
                this.customStats.set(x2, JSONHelp.getInt(map, x2.name, 0));
            }
            this._updateCustomStats();
        }
        this._updateConfigOptions();
        this.matchRaceAndProfs();
        this._rebuildGear();
    }
    
    void loadConfig_effects0() {
        try {
            this.loadConfig_effects((JSONObject)JSONValue.parse(new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/assets/StandardEffects.json")))));
        }
        catch (Exception err) {
            this.showError("Unexpected Error", err);
        }
    }
    
    void loadConfig_effects(final JSONObject root) {
        this.consume_al.lock();
        UI.setComboFromString(this.consume_food_combo, JSONHelp.getStr(root, "Food", null));
        this.consume_flask_combo.importPick(JSONHelp.getStr(root, "Flask", null), Flask.NONE);
        UI.setComboFromString(this.consume_potion_combo, JSONHelp.getStr(root, "Potion", null));
        this.consume_al.unlock();
        UI.setComboFromString(this.pre_potion0_combo, JSONHelp.getStr(root, "Potion0", null));
        this.groupBuff_al.lock();
        loadToggles(JSONHelp.getObject(root, "Buffs"), this.buff_toggles);
        this.groupBuff_al.unlock();
        this.debuff_al.lock();
        JSONObject map = JSONHelp.getObject(root, "Debuffs");
        loadToggles(map, this.debuff_toggles);
        this.debuff_armor_combo.importPick(JSONHelp.getStr(map, "WeakenedArmor", null), FeralSim.WeakenedArmor.NEVER);
        this.debuff_al.unlock();
        this.tempEffect_al.lock();
        map = JSONHelp.getObject(root, "TempEffects");
        loadToggles(map, this.tempEffect_toggles);
        for (final Spinner x : this.tempEffect_spinners) {
            x.setValue(JSONHelp.getInt(map, x.name, 0));
        }
        this.tempEffect_hero_combo.importPick(JSONHelp.getStr(map, "Heroism", null), FeralSim.Heroism.NONE);
        this.tempEffect_hero_field.setText(JSONHelp.getStr(map, "HeroismDelay", "5sec"));
        this.cfg.heroismDrums = JSONHelp.getBoolean(map, "HeroismDrums", false);
        this.tempEffect_al.unlock();
        map = JSONHelp.getObject(root, "Precombat");
        loadToggles(map, this.pre_toggles);
        this.pre_procReset_combo.importPick(JSONHelp.getStr(map, "ProcReset", null), 0);
        this.pre_opener_combo.importPick(JSONHelp.getStr(map, "Opener", null), FeralSim.Opener.NONE);
        this.pre_finisher0_combo.importPick(JSONHelp.getStr(map, "Finisher0", null), FeralSim.Finisher0.DEFAULT);
        this.pre_energy0_check.setSelected(JSONHelp.getBoolean(map, "UseEnergy0", false));
        this.pre_energy0_field.setText(JSONHelp.getStr(map, "Energy0", "100"));
        map = JSONHelp.getObject(root, "Hotfixes");
        for (final JTextField f : this.hotfix_fields) {
            final String val = JSONHelp.getStr(map, f.getName(), "");
            f.setText(val);
        }
    }
    
    void loadConfig_fights(final JSONObject fightMap) {
        this.fight_tabs.setSelectedTitle(JSONHelp.getStr(fightMap, "Selected", "Patchwerk"));
        JSONObject map = JSONHelp.getObject(fightMap, "Patchwerk");
        this.fight_patch_life_combo.importPick(JSONHelp.getStr(map, "LifeType", null), 0);
        this.fight_patch_life_field.setText(JSONHelp.getStr(map, "LifeValue", "7.5m"));
        this.fight_patch_timeVariance_combo.importPick(JSONHelp.getStr(map, "Variance", null), 0);
        this.fight_patch_class_combo.importPick(JSONHelp.getStr(map, "Class", null), 0);
        this.fight_patch_armor_field.setText(JSONHelp.getStr(map, "CustomArmor", ""));
        this.fight_patch_early_field.setText(JSONHelp.getStr(map, "EarlyDeath", ""));
        this.fight_patch_front_check.setSelected(JSONHelp.getBoolean(map, "Front", false));
        this.fight_patch_dodge_check.setSelected(JSONHelp.getBoolean(map, "CanDodge", true));
        this.fight_patch_block_check.setSelected(JSONHelp.getBoolean(map, "CanBlock", true));
        this.fight_patch_parry_check.setSelected(JSONHelp.getBoolean(map, "CanParry", true));
        map = JSONHelp.getObject(fightMap, "Cleave");
        this.fight_cleave_level_combo.importPick(JSONHelp.getStr(map, "Level", null), 1);
        this.fight_cleave_style_combo.importPick(JSONHelp.getStr(map, "Style", null), 0);
        this.fight_cleave_time_field.setText(JSONHelp.getStr(map, "Duration", "7.5m"));
        this.fight_cleave_adds_spinner.setValue(JSONHelp.getInt(map, "Adds", 8));
        this.fight_cleave_minSize_spinner.setValue(JSONHelp.getInt(map, "MinCleaveSize", 3));
        this.fight_cleave_freq_field.setText(JSONHelp.getStr(map, "Frequency", ""));
        this.fight_cleave_health_field.setText(JSONHelp.getStr(map, "Health", ""));
        this.fight_cleave_lifetime_field.setText(JSONHelp.getStr(map, "Lifetime", ""));
        this.fight_cleave_frontProb_field.setText(JSONHelp.getStr(map, "FrontProb", ""));
        this.fight_cleave_start_check.setSelected(JSONHelp.getBoolean(map, "AtStart", true));
        map = JSONHelp.getObject(fightMap, "Mirror");
        for (final JCheckBox x : this.fight_mirror_checks) {
            x.setSelected(JSONHelp.getBoolean(map, x.getName(), true));
        }
        this.fight_mirror_script = JSONHelp.getStr(map, "Script", "");
        this.fight_mirror_source_lbl.setText(JSONHelp.getStr(map, "Source", "Custom"));
        map = JSONHelp.getObject(fightMap, "PeriodicDmg");
        this.fight_periodicDmgMod_check.setSelected(JSONHelp.getBoolean(map, "Enabled", false));
        this.fight_periodicDmgMod_mod_field.setText(JSONHelp.getStr(map, "Mod", "40"));
        this.fight_periodicDmgMod_freq_field.setText(JSONHelp.getStr(map, "Frequency", "30s,105s"));
        this.fight_periodicDmgMod_time_field.setText(JSONHelp.getStr(map, "Duration", "50-70"));
        map = JSONHelp.getObject(fightMap, "PeriodicIdle");
        this.fight_periodicIdle_check.setSelected(JSONHelp.getBoolean(map, "Enabled", false));
        this.fight_periodicIdle_freq_field.setText(JSONHelp.getStr(map, "Frequency", "3m"));
        this.fight_periodicIdle_time_field.setText(JSONHelp.getStr(map, "Duration", "45s"));
        this.fight_periodicIdle_limit_field.setText(JSONHelp.getStr(map, "Limit", "2"));
        map = JSONHelp.getObject(fightMap, "PeriodicCast");
        this.fight_periodicCast_check.setSelected(JSONHelp.getBoolean(map, "Enabled", false));
        this.fight_periodicCast_freq_field.setText(JSONHelp.getStr(map, "Frequency", "20s"));
        map = JSONHelp.getObject(fightMap, "Advanced");
        this.fight_advanced_check.setSelected(JSONHelp.getBoolean(map, "Enabled", false));
        this.fight_advanced_script = JSONHelp.getStr(map, "Script", "");
        map = JSONHelp.getObject(fightMap, "Entry");
        this.fight_entry_check.setSelected(JSONHelp.getBoolean(map, "Enabled", false));
        this.fight_entry_field.setText(JSONHelp.getStr(map, "Name", "main"));
        map = JSONHelp.getObject(fightMap, "CooldownDelay");
        this.fight_cooldownDelay_check.setSelected(JSONHelp.getBoolean(map, "Enabled", false));
        this.fight_cooldownDelay_field.setText(JSONHelp.getStr(map, "Delay", "30sec"));
    }
    
    private static void _addGridBagBalloon(final JComponent parent) {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = 1;
        parent.add(Box.createGlue(), gbc);
    }
    
    private static void _addGridBagSpacer(final JComponent parent) {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.fill = 2;
        parent.add(Box.createGlue(), gbc);
    }
    
    private static void _addGridBagRow(final JComponent parent, final Component child, final boolean padTop) {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.fill = 2;
        if (padTop) {
            gbc.insets = new Insets(4, 0, 0, 0);
        }
        parent.add(child, gbc);
    }
    
    private void applyConfigToSim(final FeralSim s) {
        s.disable_armorSpec = this.feature_disableArmorSpec_check.isSelected();
        s.disable_mastery = this.feature_disableMastery_check.isSelected();
        s.disable_crit = this.feature_disableCrit_check.isSelected();
        s.disable_berserk = this.feature_disableBerserk_check.isSelected();
        s.disable_primalFury = this.feature_disablePrimalFury_check.isSelected();
        s.disable_racials = this.feature_disableRacials_check.isSelected();
        s.enable_wodRacials = this.feature_wodRacials_check.isSelected();
        s.disable_glancing = this.feature_disableGlancing_check.isSelected();
        s.disable_bitw = this.feature_disableBitW_check.isSelected();
        s.enable_berserkPerk = this.feature_enableBerserkPerk_check.isSelected();
        s.disable_ripExtend = this.feature_disableRipExtend_check.isSelected();
    }
    
    private void _extractConfig_buffs(final FeralConfig c) {
        c.buff_ap = this.buff_ap_toggle.isSelected();
        c.buff_crit = this.buff_crit_toggle.isSelected();
        c.buff_mastery = this.buff_mastery_toggle.isSelected();
        c.buff_meleeHaste = this.buff_meleeHaste_toggle.isSelected();
        c.buff_sp = this.buff_sp_toggle.isSelected();
        c.buff_spellHaste = this.buff_spellHaste_toggle.isSelected();
        c.buff_sta = this.buff_stam_toggle.isSelected();
        c.buff_stat = this.buff_stats_toggle.isSelected();
        final Object sel = this.consume_food_combo.getSelectedItem();
        c.food = ((sel instanceof StatValue) ? ((StatValue)sel) : null);
        c.flask = this.consume_flask_combo.getPick();
        c.extra_inert.set(this.customStats);
        c.extra_active.clear();
        c.random_ranges = !this.feature_averageRanges_toggle.isSelected();
        c.challengeMode = this.challengeMode;
        if (this.feature_setBonus_toggle.isSelected()) {
            c.setBonuses.clear();
            for (final SetBonusHelper x : this.bonusMap.values()) {
                if (x.check.isSelected()) {
                    c.setBonuses.setBonus(x.set.id, x.bonus.index);
                }
            }
        }
        else {
            this.mainProfile.syncSetBonuses(c.setBonuses);
        }
    }
    
    private FeralConfig _extractConfig() {
        final FeralConfig c = new FeralConfig();
        if (!this._extractConfig_else(c)) {
            return null;
        }
        this._extractConfig_buffs(c);
        return c;
    }
    
    private boolean _extractConfig_else(final FeralConfig c) {
        c.pre_ht = this.pre_castHT_toggle.isSelected();
        c.pre_sr = this.pre_castSR_toggle.isSelected();
        c.procResetTime = this.pre_procReset_combo.getPick().value;
        c.opener = this.pre_opener_combo.getPick();
        c.finisher0 = this.pre_finisher0_combo.getPick();
        c.generator = this.rot_generator_combo.getPick();
        c.thrashStyle = this.rot_thrashStyle_combo.getPick();
        c.symbiosis = this.rot_symbiosis_combo.getPick();
        c.hotw_swap = this.hotw_swap_toggle.isSelected();
        c.hotw_wrath = this.hotw_wrath_toggle.isSelected();
        c.hotw_hurricane = this.hotw_hurricane_toggle.isSelected();
        c.hotw_beforeBerserk = this.hotw_beforeBerserk_toggle.isSelected();
        c.hotw_bitw = this.hotw_bitw_toggle.isSelected();
        c.debuff_armor = this.debuff_armor_combo.getPick();
        c.debuff_meleeTaken = this.debuff_melee_toggle.isSelected();
        c.debuff_spellTaken = this.debuff_spell_toggle.isSelected();
        c.debuff_bleeding = this.debuff_bleed_toggle.isSelected();
        c.num_shatteringThrow = this.shatteringSpinner.getValue();
        c.num_skullBanner = this.skullBannerSpinner.getValue();
        c.num_stormlashTotem = this.stormlashTotemSpinner.getValue();
        c.num_unholyFrenzy = this.unholyFrenzySpinner.getValue();
        c.num_tricksOfTheTrade = this.tricksSpinner.getValue();
        c.heroismDrums = this.cfg.heroismDrums;
        c.heroism = this.tempEffect_hero_combo.getPick();
        if (c.heroism == FeralSim.Heroism.AFTER) {
            try {
                c.heroismDelay = UI.parseIntDist_time(this.tempEffect_hero_field, 1000);
            }
            catch (RuntimeException err) {
                this.showError("Heroism Settings", err.getMessage());
                return false;
            }
        }
        Object sel = this.consume_potion_combo.getSelectedItem();
        if (sel instanceof StatValue) {
            c.pot = (StatValue)sel;
        }
        else {
            c.pot = null;
        }
        sel = this.pre_potion0_combo.getSelectedItem();
        if (sel == "Same as Consumable") {
            c.pre_pot = c.pot;
        }
        else if (sel instanceof StatValue) {
            c.pre_pot = (StatValue)sel;
        }
        else {
            c.pre_pot = null;
        }
        c.enable_lifeblood = this.tempEffect_lifeblood_toggle.isSelected();
        c.enable_berserking = this.tempEffect_berserking_toggle.isSelected();
        c.enable_refund = !this.feature_disableRefund_check.isSelected();
        c.enable_ooc = !this.feature_disableOOC_check.isSelected();
        c.enable_lotp = !this.feature_disableLOTP_check.isSelected();
        c.enable_properRipExtend = this.feature_properRipExtend_check.isSelected();
        c.enable_localRipExtend = this.feature_localRipExtend_check.isSelected();
        c.enable_tfDuringBerserk = this.feature_allowTFDuringBerserk_check.isSelected();
        c.enable_0comboPS = !this.feature_disable0ComboPS_check.isSelected();
        c.enable_retardFoN = !this.feature_smartFoN_check.isSelected();
        if (this.pre_energy0_check.isSelected()) {
            int e;
            try {
                e = UI.parse_int(this.pre_energy0_field);
            }
            catch (RuntimeException err2) {
                this.showError(this.pre_energy0_field.getName(), err2.getMessage());
                return false;
            }
            if (e > 100) {
                this.showError(this.pre_energy0_field.getName(), "Too much energy.");
                return false;
            }
            c.energy0 = e;
        }
        else {
            c.energy0 = 100;
        }
        try {
            c.delay = UI.parseIntDist_time(this.rot_delay_field, 1);
            c.react = UI.parseIntDist_time(this.rot_react_field, 1);
            c.poolMax = UI.parseInt_time(this.rot_pool_field, 1);
            if (c.poolMax == 0) {
                throw new RuntimeException(this.rot_pool_field.getName() + " must have a duration.");
            }
        }
        catch (RuntimeException err) {
            this.showError("Rotation Settings", err.getMessage());
            return false;
        }
        return true;
    }
    
    private void _updateStats() {
        this._extractConfig_buffs(this.cfg);
        this.applyConfigToSim(this.sim);
        this.sim.guardian = this.form_guardBtn.isSelected();
        this.sim.setup(this.mainProfile, this.swapProfile, this.spec, this.cfg, null);
        if (this.form_humanBtn.isSelected()) {
            this.sim.form = 0;
        }
        else if (this.form_bearBtn.isSelected()) {
            this.sim.tempBearForm();
        }
        else if (this.sim.guardian) {
            this.sim.tempBearForm();
        }
        this.reforgeMax_bear_row.setVisible(this.form_guardBtn.isSelected());
        this.statHP.setText(Integer.toString(this.sim.getMaxHP()));
        this.statSta.setText(Integer.toString(this.sim.getSta()));
        this.statSpi.setText(Integer.toString(this.sim.getSpi()));
        this.statStr.setText(Integer.toString(this.sim.getStr()));
        this.statAgi.setText(Integer.toString(this.sim.getAgi()));
        this.statInt.setText(Integer.toString(this.sim.getInt()));
        this.statAP.setText(Integer.toString(this.sim.getAP()));
        this.statSP.setText(Integer.toString(this.sim.getSP()));
        this.statNatureSP.setText(Integer.toString(this.sim.getSP_nature()));
        this.statMeleeCrit.setText(String.format("%.2f%% (%+d)", this.sim.getMeleeCrit() * 100.0, this.sim.getCritRating()));
        this.statSpellCrit.setText(String.format("%.2f%%", this.sim.getSpellCrit() * 100.0));
        this.statMeleeHaste.setText(String.format("%+.2f%% (%+d)", (this.sim.getMeleeHasteMod() - 1.0) * 100.0, this.sim.getHasteRating()));
        this.statSpellHaste.setText(String.format("%+.2f%%", (this.sim.getSpellHasteMod() - 1.0) * 100.0));
        this.statExp.setText(String.format("%.2f%% (%d)", this.sim.getMeleeExp() * 100.0, this.sim.getMeleeExpRating()));
        this.statHit.setText(String.format("%.2f%% (%d)", this.sim.getMeleeHit() * 100.0, this.sim.getMeleeHitRating()));
        this.statMastery.setText(String.format("%.2f%% (%+d)", (this.sim.getMasteryMod() - 1.0) * 100.0, this.sim.getMasteryRating()));
        this.statDPS.setText(String.format("%.2f", this.sim.getPaperDPS()));
        this.statSwing.setText(String.format("+%.2f%% (%.3f)", (this.sim.getMeleeSpeedMod() - 1.0) * 100.0, this.sim.swingTime() / 1000.0));
        this.statPvPPower.setText(String.format("%.2f%% (%d)", (this.sim.getPvPPowerMod() - 1.0) * 100.0, this.sim.getPvPPowerRating()));
        this.statPvPResil.setText(String.format("%.2f%% (%d)", (1.0 - this.sim.getPvPResilMod()) * 100.0, this.sim.getPvPResilRating()));
        final int a = this.sim.getArmor();
        final JLabel statArmor = this.statArmor;
        final String format = "%d (%.2f%%)";
        final Object[] args = { a, null };
        final int n = 1;
        final double n2 = 100.0;
        final double n3 = 1.0;
        final FeralSim sim = this.sim;
        args[n] = n2 * (n3 - FeralSim.armorMod(a));
        statArmor.setText(String.format(format, args));
        this.statDodge.setText(String.format("%.2f%%", 100.0 * this.sim.getDodge()));
        this.statCritDmg.setText(String.format("%.2f%%", 100.0 * this.sim.critDmgMods.product));
        this.statSpeed.setText(String.format("%.2f%%", 100.0 * this.sim.getMovementSpeed()));
        this.statPane.desc.setText(String.format("%d AP, %d Agility, %.2f%% Mastery, %.2f%% Crit, %.2f%% Haste, %.2f%% Hit, %.2f%% Exp", this.sim.getAP(), this.sim.getAgi(), (this.sim.getRazorClawsMod() - 1.0) * 100.0, this.sim.getMeleeCrit() * 100.0, (this.sim.getMeleeHasteMod() - 1.0) * 100.0, this.sim.getMeleeHit() * 100.0, this.sim.getMeleeExp() * 100.0));
        this.reforge_statHit.setText(this.hitExpAsPerc ? String.format("%.2f%%", 100.0 * this.sim.getMeleeHit()) : Integer.toString(this.sim.getMeleeHitRating()));
        this.reforge_statExp.setText(this.hitExpAsPerc ? String.format("%.2f%%", 100.0 * this.sim.getMeleeExp()) : Integer.toString(this.sim.getMeleeExpRating()));
        this.weight_hit_lbl.setText(this.reforge_statHit.getText());
        this.weight_exp_lbl.setText(this.reforge_statExp.getText());
    }
    
    void _processIconQueue() {
        if (!this.iconQueue.isEmpty()) {
            new DialogProg(this, "Loading Icons").forEach(this.iconQueue, new DialogProg.Each<IconStore>() {
                @Override
                public void each(final int index, final IconStore value) {
                    CatusFrame.this.api.unmarkIconImage(value);
                }
            });
            this.iconQueue.clear();
        }
    }
    
    private void _rebuildGear() {
        if (this.dontRebuild) {
            return;
        }
        final long t = System.currentTimeMillis();
        markProfileIcons(this.api, this.iconQueue, this.mainProfile);
        markProfileIcons(this.api, this.iconQueue, this.swapProfile);
        this._processIconQueue();
        this.equipGrid.removeAll();
        this.equipGrid.setLayout(new GridBagLayout());
        this._updateStats();
        if (this.challengeMode) {
            _addGridBagRow(this.equipGrid, this.challengeMode_panel, true);
        }
        if (this.mainProfile.scaledItemLevel > 0) {
            this.scaled_label.setText(" Scaled to Item Level " + this.mainProfile.scaledItemLevel);
            _addGridBagRow(this.equipGrid, this.scaled_panel, true);
        }
        else if (this.mainProfile.scaledItemLevel < 0) {
            this.scaled_label.setText(" Down-scaled to Item Level " + -this.mainProfile.scaledItemLevel);
            _addGridBagRow(this.equipGrid, this.scaled_panel, true);
        }
        if (!this.keepReforgeVisible) {
            this.reforgeRune_resultsRow.setVisible(false);
            this.reforgeMax_results_panel.setVisible(false);
        }
        final int gearCount = this.mainProfile.getEquippedCount();
        final int socketCount = this.mainProfile.getSocketCount();
        final int gemCount = this.mainProfile.getGemCount();
        final int enchantCount = this.mainProfile.getEnchantCount();
        final int tinkerCount = this.mainProfile.getTinkerCount();
        final int emptySlotCount = this.mainProfile.getEmptySlotCount();
        final int emptySocketCount = socketCount - gemCount;
        final int emptyEnchantCount = this.mainProfile.getEmptyEnchantCount();
        final double ilvl = this.mainProfile.avgItemLevel();
        this.equip_ilvl_lbl.setText(String.format("%.2f ", ilvl));
        this.gearPane.desc.setText(String.format("%.2f item level, %d empty slots, %d empty sockets, %d missing enchants", ilvl, emptySlotCount, emptySocketCount, emptyEnchantCount));
        this.swapPane.desc.setText(this.swapProfile.getWeaponStr(" and ", true));
        this.equip_clear_combo.setEnabled(gearCount > 0);
        this.regem_regemBtn.setEnabled(socketCount > 0);
        this.regem_clearBtn.setEnabled(gemCount > 0);
        final int mr = this.mainProfile.gearAndExtraStat(StatT.MASTERY);
        final int hr = this.mainProfile.gearAndExtraStat(StatT.HASTE);
        final int cr = this.mainProfile.gearAndExtraStat(StatT.CRIT);
        this.reforge_statMastery.setText(Integer.toString(mr));
        this.reforge_statHaste.setText(Integer.toString(hr));
        this.reforge_statCrit.setText(Integer.toString(cr));
        boolean b = !this.mainProfile.BACK.hasProfEnchant();
        this.reforgeRune_enchantBack_check.setEnabled(b);
        this.reforgeMax_enchantBack_check.setEnabled(b);
        b = !this.mainProfile.HANDS.hasProfEnchant();
        this.reforgeRune_enchantHands_check.setEnabled(b);
        this.reforgeMax_enchantHands_check.setEnabled(b);
        this.reforgeMax_enchantFeet_check.setEnabled(b);
        final boolean hasRune = this.mainProfile.hasSpell(139116);
        this.reforge_statMastery.setForeground(hasRune ? ((mr > Math.max(hr, cr)) ? CatusFrame.DARK_GREEN : CatusFrame.DARK_RED) : null);
        this.reforge_statHaste.setForeground(hasRune ? ((hr > Math.max(mr, cr)) ? CatusFrame.DARK_GREEN : CatusFrame.DARK_RED) : null);
        this.reforge_statCrit.setForeground(hasRune ? ((cr > Math.max(hr, mr)) ? CatusFrame.DARK_GREEN : CatusFrame.DARK_RED) : null);
        this.reforgeRune_stateRow.setVisible(hasRune);
        if (hasRune) {
            if (mr > hr && mr > cr) {
                this.reforgeRune_stateText.setForeground(CatusFrame.DARK_GREEN);
                this.reforgeRune_stateText.setText(String.format(" with %+d Mastery proc", 2 * (cr + hr)));
            }
            else {
                this.reforgeRune_stateText.setForeground(CatusFrame.DARK_RED);
                if (cr > hr) {
                    this.reforgeRune_stateText.setText(String.format(" with %+d Crit proc", 2 * (hr + mr)));
                }
                else {
                    this.reforgeRune_stateText.setText(String.format(" with %+d Haste proc", 2 * (cr + mr)));
                }
            }
        }
        final LinkedList<String> errors = new LinkedList<String>();
        if (emptySlotCount == 1) {
            errors.add("1 empty slot");
        }
        else if (emptySlotCount > 1) {
            errors.add(emptySlotCount + " empty slots");
        }
        if (emptySocketCount == 1) {
            errors.add("1 empty socket");
        }
        else if (emptySocketCount > 1) {
            errors.add(emptySocketCount + " empty sockets");
        }
        if (emptyEnchantCount == 1) {
            errors.add("1 missing enchant");
        }
        else if (emptyEnchantCount > 1) {
            errors.add(emptyEnchantCount + " missing enchants");
        }
        if (this.mainProfile.isProf(ProfT.ENG)) {
            final int missingTinkers = this.mainProfile.getEmptyTinkerCount();
            if (missingTinkers == 1) {
                errors.add("1 missing tinker");
            }
            else if (missingTinkers > 1) {
                errors.add(missingTinkers + " missing tinkers");
            }
        }
        if (this.mainProfile.isProf(ProfT.INS) && this.mainProfile.SHOULDER.hasEnchant() && this.mainProfile.SHOULDER.enchant.requiredProf == null) {
            errors.add("No Secret Inscription");
        }
        if (this.mainProfile.isProf(ProfT.LW) && this.mainProfile.WRIST.hasEnchant() && this.mainProfile.WRIST.enchant.requiredProf == null) {
            errors.add("No Fur Lining");
        }
        if (this.mainProfile.isProf(ProfT.TAILOR) && this.mainProfile.BACK.hasEnchant() && this.mainProfile.BACK.enchant.requiredProf == null) {
            errors.add("No Embroidery");
        }
        if (this.mainProfile.isProf(ProfT.JC)) {
            final int uc = 2 - this.mainProfile.getUniqueCount("Jeweler's Facet");
            if (uc > 1) {
                errors.add(uc + " missing Jeweler's Facets");
            }
            else if (uc == 1) {
                errors.add("1 missing Jeweler's Facet");
            }
        }
        if (this.mainProfile.LEGS.hasEnchant() && this.mainProfile.LEGS.sumStat_enchant(StatT.AGI) == 0) {
            errors.add("Wrong Leg Enchant");
        }
        if (this.mainProfile.WRIST.hasEnchant() && this.mainProfile.WRIST.sumStat_enchant(StatT.AGI) == 0) {
            errors.add("Wrong Wrist Enchant");
        }
        if (this.mainProfile.CHEST.hasEnchant() && this.mainProfile.CHEST.sumStat_enchant(StatT.AGI) == 0 && this.mainProfile.CHEST.sumStat_enchant(StatT.PVP_RES) == 0) {
            errors.add("Wrong Chest Enchant");
        }
        if (this.mainProfile.SHOULDER.hasEnchant() && this.mainProfile.SHOULDER.sumStat_enchant(StatT.AGI) == 0) {
            errors.add("Wrong Shoulder Enchant");
        }
        if (!this.mainProfile.hasArmorSpecialization()) {
            errors.add("Leather Specialization not satisfied");
        }
        if (!errors.isEmpty()) {
            this.warning_label.setText("Warning: " + Fmt.join(errors, ", "));
            _addGridBagRow(this.equipGrid, this.warning_label, true);
        }
        this._addSlotPair(this.mainProfile.HEAD, this.mainProfile.HANDS);
        this._addSlotPair(this.mainProfile.NECK, this.mainProfile.WAIST);
        this._addSlotPair(this.mainProfile.SHOULDER, this.mainProfile.LEGS);
        this._addSlotPair(this.mainProfile.BACK, this.mainProfile.FEET);
        this._addSlotPair(this.mainProfile.CHEST, this.mainProfile.F1);
        this._addSlotPair(this.mainProfile.WRIST, this.mainProfile.F2);
        this._addSlotPair(this.mainProfile.T1, this.mainProfile.T2);
        if (this.mainProfile.hasTwoHander()) {
            _addGridBagRow(this.equipGrid, this._makeTooltip(this.mainProfile.MH, true), true);
        }
        else {
            this._addSlotPair(this.mainProfile.MH, this.mainProfile.OH);
        }
        this.regemPane.desc.setText(String.format("%d sockets", socketCount));
        final int reforgeCount = this.mainProfile.getReforgedCount();
        this.reforgePane.desc.setText(String.format("%d reforges", reforgeCount));
        for (final Profile.Slot x : this.mainProfile.SLOTS) {
            final ReforgeToggle toggle = this.reforge_toggles[x.index];
            toggle.setEnabled(x.canReforge(CatusFrame.REFORGE_UNIVERSE));
            toggle.setForeground((x.reforge == null) ? null : CatusFrame.DARK_GREEN);
        }
        this.reforge_clearReforges_btn.setEnabled(reforgeCount > 0);
        this.reforge_clearGems_btn.setEnabled(gemCount > 0);
        this.enchantGrid.removeAll();
        this.enchantGrid.setLayout(new GridBagLayout());
        if (enchantCount > 0 || tinkerCount > 0) {
            final GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = 2;
            gbc.weightx = 1.0;
            final String sep = ": ";
            for (final Profile.Slot x2 : this.mainProfile.SLOTS) {
                if (x2.enchant != null) {
                    final LinkButton nameBtn = new LinkButton(x2.enchant);
                    nameBtn.setText(x2.type.name + sep + x2.enchant.shortName);
                    nameBtn.setBorder(UI.EMPTY_BORDER_1);
                    nameBtn.setFont(CatusFrame.tinyFont);
                    nameBtn.setLayout(new BorderLayout());
                    final JLabel infoText = new JLabel(" " + x2.enchant.effect, 4);
                    infoText.setFont(CatusFrame.tinyFont);
                    nameBtn.add(infoText);
                    _addGridBagRow(this.enchantGrid, nameBtn, false);
                }
                if (x2.tinker != null) {
                    final LinkButton nameBtn = new LinkButton(x2.tinker);
                    nameBtn.setText(x2.type.name + sep + x2.tinker.name);
                    nameBtn.setFont(CatusFrame.tinyFont);
                    nameBtn.setBorder(UI.EMPTY_BORDER_1);
                    nameBtn.setLayout(new BorderLayout());
                    final JLabel infoText = new JLabel(x2.tinker.effect, 4);
                    infoText.setFont(CatusFrame.tinyFont);
                    nameBtn.add(infoText);
                    _addGridBagRow(this.enchantGrid, nameBtn, false);
                }
            }
        }
        else {
            this.enchantGrid.add(new JLabel("No enchants"));
        }
        _addGridBagBalloon(this.enchantGrid);
        if (ProfT.ENG.isMemberOf(this.mainProfile.profs)) {
            this.gemEnchantTinkerPane.desc.setText(String.format("%d gems, %d enchants, %d tinkers", gemCount, enchantCount, tinkerCount));
        }
        else {
            this.gemEnchantTinkerPane.desc.setText(String.format("%d gems, %d enchants", gemCount, enchantCount));
        }
        this.gemGrid.removeAll();
        this.gemGrid.setLayout(new GridBagLayout());
        if (gemCount > 0) {
            final HashMap<Gem, Tally<Gem>> tallyMap = new HashMap<Gem, Tally<Gem>>();
            for (final Profile.Slot x3 : this.mainProfile.SLOTS) {
                for (int i = 0, e = x3.getSocketCount(); i < e; ++i) {
                    final Gem gem = x3.getGemAt(i);
                    if (gem != null) {
                        Tally temp = tallyMap.get(gem);
                        if (temp == null) {
                            temp = new Tally((T)gem);
                            tallyMap.put(gem, temp);
                        }
                        final Tally tally = temp;
                        ++tally.count;
                    }
                }
            }
            final Tally<Gem>[] v = tallyMap.values().toArray(new Tally[tallyMap.size()]);
            Arrays.sort(v, Tally.CMP);
            for (final Tally<Gem> x4 : v) {
                final int h = 20;
                final JLabel countText = new JLabel(Integer.toString(x4.count) + " ", 4);
                countText.setFont(CatusFrame.boldFont);
                countText.setPreferredSize(new Dimension(24, 20));
                final JButton nameBtn2 = new JButton();
                nameBtn2.setText(x4.key.name);
                nameBtn2.setHorizontalAlignment(2);
                nameBtn2.setForeground(x4.key.quality.color);
                nameBtn2.setBorderPainted(false);
                nameBtn2.setContentAreaFilled(false);
                nameBtn2.setPreferredSize(new Dimension(100, 20));
                nameBtn2.setFocusable(false);
                nameBtn2.setOpaque(false);
                nameBtn2.setBorder(BorderFactory.createEmptyBorder());
                final ImageIcon icon = x4.key.icon.getIcon(API.IconSize.SMALL);
                if (icon != null) {
                    nameBtn2.setIcon(icon);
                }
                final Gem gem2 = x4.key;
                nameBtn2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent ae) {
                        CatusFrame.tryURL(gem2);
                    }
                });
                nameBtn2.setLayout(new BorderLayout());
                final JLabel infoText2 = new JLabel(x4.key.fancyStats(), 4);
                infoText2.setFont(CatusFrame.tinyFont);
                nameBtn2.add(infoText2);
                final UIPanel_GridBag row = new UIPanel_GridBag();
                row.add(countText);
                row.spacer(nameBtn2);
                _addGridBagRow(this.gemGrid, row, false);
            }
        }
        else {
            this.gemGrid.add(new JLabel("No gems"));
        }
        _addGridBagBalloon(this.gemGrid);
        this.setBonus_grid.removeAll();
        this.setBonus_grid.setLayout(new GridBagLayout());
        final StringBuilder sb = new StringBuilder();
        if (this.challengeMode) {
            this.setBonusPane.panel.setVisible(false);
        }
        else if (this.feature_setBonus_toggle.isSelected()) {
            this.setBonusPane.panel.setVisible(true);
            boolean first = true;
            for (final ItemSet set : this.itemSets) {
                if (!first) {
                    this.setBonus_grid.row(new JSeparator(), true, 0);
                }
                final LinkButton nameBtn = new LinkButton(set);
                nameBtn.setText(String.format("%s (%d)", set.name, set.bonuses.length));
                nameBtn.setForeground(set.quality.color);
                this.setBonus_grid.row(nameBtn, true, 0);
                int num = 0;
                for (final SetBonus x5 : set.bonuses) {
                    final SetBonusHelper temp2 = this.bonusMap.get(x5);
                    this.setBonus_grid.row(temp2.panel, true, 0);
                    if (temp2.check.isSelected()) {
                        ++num;
                    }
                }
                if (num > 0) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(set.name);
                    sb.append(" (");
                    sb.append(num);
                    sb.append(" of ");
                    sb.append(set.bonuses.length);
                    sb.append(')');
                }
                first = false;
            }
            this.setBonus_grid.row(this.setBonus_botRow, true, 4);
        }
        else {
            final ArrayList<SetBonus> valid = new ArrayList<SetBonus>();
            int diffBonus = 0;
            final HashSet<ItemSet> sets = new HashSet<ItemSet>();
            this.mainProfile.collectItemSets(sets);
            for (final ItemSet set : sets) {
                final int have = this.mainProfile.itemSetCount(set.id);
                valid.clear();
                for (final SetBonus x6 : set.bonuses) {
                    if (have >= x6.threshold) {
                        valid.add(x6);
                    }
                }
                if (!valid.isEmpty()) {
                    final LinkButton nameBtn3 = new LinkButton(set);
                    nameBtn3.setText(String.format("%s (%d/%d)", set.name, have, set.max));
                    nameBtn3.setForeground(this.mainProfile.firstSlotFromItemSet(set.id).item.quality.color);
                    if (diffBonus++ > 0) {
                        _addGridBagRow(this.setBonus_grid, new JSeparator(), false);
                    }
                    _addGridBagRow(this.setBonus_grid, nameBtn3, false);
                    for (final SetBonus x7 : valid) {
                        final JLabel countText2 = new JLabel(Integer.toString(x7.threshold), 0);
                        countText2.setFont(CatusFrame.boldFont);
                        countText2.setPreferredSize(new Dimension(20, 16));
                        final WrapLabel infoText3 = new WrapLabel(x7.description, CatusFrame.tinyFont);
                        final UIPanel_GridBag row = new UIPanel_GridBag();
                        row.setBorder(UI.EMPTY_BORDER_1);
                        row.add(countText2);
                        row.spacer(infoText3);
                        _addGridBagRow(this.setBonus_grid, row, false);
                    }
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(set.name);
                    sb.append(" (");
                    sb.append(have);
                    sb.append('/');
                    sb.append(set.max);
                    sb.append(')');
                }
            }
            this.setBonusPane.panel.setVisible(sb.length() > 0);
        }
        this.setBonusPane.desc.setText(sb.toString());
        this.altWeapGrid.removeAll();
        if (this.swapProfile.hasTwoHander()) {
            this.altWeapGrid.setLayout(new GridLayout());
            this.altWeapGrid.add(this._makeTooltip(this.swapProfile.MH, true));
        }
        else {
            this.altWeapGrid.setLayout(new GridLayout(1, 2, 4, 0));
            this.altWeapGrid.add(this._makeTooltip(this.swapProfile.MH, false));
            this.altWeapGrid.add(this._makeTooltip(this.swapProfile.OH, false));
        }
        this.equipGrid.invalidate();
        this.altWeapGrid.invalidate();
        this.enchantGrid.invalidate();
        this.gemGrid.invalidate();
        this.scroll.validate();
        this.scroll.revalidate();
    }
    
    private void _addSlotPair(final Profile.Slot left, final Profile.Slot right) {
        final JPanel p = _makePanel();
        p.setLayout(new GridLayout(1, 2, 4, 0));
        p.add(this._makeTooltip(left, false));
        p.add(this._makeTooltip(right, false));
        _addGridBagRow(this.equipGrid, p, true);
    }
    
    static void makeGemCombo(final JComboBox combo) {
        final ListCellRenderer lcr = combo.getRenderer();
        combo.setRenderer(new ListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(final JList list, Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
                if (index == -1 && value instanceof Gem) {
                    final Gem gem = (Gem)value;
                    if (gem.getType().colored()) {
                        value = gem.fancyStats();
                    }
                }
                return lcr.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
    }
    
    ArrayList<ItemWrap> matchesForSlot(final SlotT type) {
        final ArrayList<ItemWrap> matches = new ArrayList<ItemWrap>();
        for (final EquipT x : EquipT.ALL) {
            if (type.set.contains(x)) {
                matches.addAll(this.gearMap.get(x).values());
            }
        }
        return matches;
    }
    
    private JPanel _makeTooltip(final Profile.Slot slot, final boolean wide) {
        SlotStore store = this.slotStoreMap.get(slot);
        if (store == null) {
            store = new SlotStore(slot);
            this.slotStoreMap.put(slot, store);
        }
        store.locked = true;
        Component comp;
        if (slot.item == null) {
            final JComboBox combo = store.itemCombo;
            combo.removeAllItems();
            combo.addItem("Restore from Armory");
            final ArrayList<ItemWrap> matches = this.matchesForSlot(slot.type);
            Collections.sort(matches, ItemWrap.CMP_itemLevel);
            for (final ItemWrap x : matches) {
                if (x.item.validate(ClassT.DRUID, this.mainProfile.race, this.mainProfile.profs)) {
                    if (slot.type == SlotT.OFF_HAND && x.item instanceof Weapon) {
                        continue;
                    }
                    combo.addItem(x);
                }
            }
            combo.addItem("Add item...");
            combo.addItem("Edit...");
            UI.setComboText(combo, slot.type.name);
            comp = combo;
        }
        else {
            final UIPanel_GridBag p = new UIPanel_GridBag();
            p.setBorder(UI.EMPTY_BORDER_1);
            store.nameBtn.setText(slot.getItemName(false));
            store.nameBtn.setForeground(slot.item.quality.color);
            final UIPanel_GridBag row = new UIPanel_GridBag();
            row.spacer(store.nameBtn);
            row.add(store.clearBtn);
            p.row(row, true, 0);
            final ArrayList<Item> set = slot.item.groupSet;
            Label_0520: {
                if (set != null && set.size() > 1) {
                    store.groupCombo.removeAllItems();
                    int index = 0;
                    boolean found = false;
                    for (final Item x2 : set) {
                        if (x2.allowableRaces != 0 && !this.mainProfile.race.isMemberOf(x2.allowableRaces)) {
                            continue;
                        }
                        store.groupCombo.addItem(new ItemDescWrap(x2));
                        if (x2 == slot.item) {
                            found = true;
                        }
                        else {
                            if (found) {
                                continue;
                            }
                            ++index;
                        }
                    }
                    if (store.groupCombo.getItemCount() > 1) {
                        if (found) {
                            store.groupCombo.setSelectedIndex(index);
                        }
                        p.row(store.groupCombo, true, 1);
                        break Label_0520;
                    }
                }
                if (slot.item.nameDesc != null) {
                    final JLabel lbl = new JLabel(slot.item.nameDesc);
                    lbl.setFont(CatusFrame.tinyFont);
                    lbl.setForeground(CatusFrame.DARK_GREEN);
                    p.row(lbl, true, 1);
                }
            }
            if (slot.canSuffix()) {
                final JComboBox suffixCombo = store.suffixCombo;
                suffixCombo.removeAllItems();
                for (final Suffix x3 : slot.item.suffixes) {
                    suffixCombo.addItem(x3.name);
                }
                suffixCombo.setSelectedIndex(slot.getSuffixIndex());
                p.row(suffixCombo, true, 1);
            }
            if (slot.isUnique()) {
                final String text = (slot.item.uniqueCount > 1) ? String.format("Unique: %s (%d)", slot.item.uniqueKey, slot.item.uniqueCount) : String.format("Unique: %s", slot.item.uniqueKey);
                final JLabel lbl2 = new JLabel(text);
                lbl2.setFont(CatusFrame.tinyFont);
                p.row(lbl2, true, 1);
            }
            final boolean weapon = slot.isWeapon();
            if (weapon) {
                final UIPanel_GridBag row2 = new UIPanel_GridBag();
                final double min = slot.getWeapDmgMin();
                final double max = slot.getWeapDmgMax();
                final double speed = ((Weapon)slot.item).speed;
                final JLabel left = new JLabel(wide ? String.format("%.0f - %.0f Damage (%.1f damage per second)", Math.floor(min), Math.ceil(max + 0.5), 0.5 * (max + min) / speed) : String.format("%.0f - %.0f Damage", Math.floor(min), Math.ceil(max + 0.5)));
                final JLabel right = new JLabel(String.format("%.2f Speed %s ", speed, slot.item.minorType));
                left.setFont(CatusFrame.tinyFont);
                right.setFont(CatusFrame.tinyFont);
                row2.spacer(left);
                row2.add(right);
                p.row(row2, true, 1);
                if (!wide) {
                    final JLabel all = new JLabel(String.format("(%.1f damage per second)", 0.5 * (max + min) / speed));
                    all.setFont(CatusFrame.tinyFont);
                    p.row(all, true, 1);
                }
            }
            if (slot.item.itemSet != null) {
                final JButton btn = new LinkButton(slot.item.itemSet);
                btn.setForeground(CatusFrame.DARK_YELLOW);
                btn.setFont(CatusFrame.tinyFont);
                btn.setText(slot.item.itemSet.name);
                p.row(btn, true, 1);
            }
            final int armorValue = slot.getItemArmor();
            if (armorValue > 0) {
                final String text2 = (slot.type.bodyPart == 1) ? String.format("%d %s Armor", armorValue, slot.item.minorType) : String.format("%d Armor", armorValue);
                final JLabel lbl3 = new JLabel(text2);
                lbl3.setFont(CatusFrame.tinyFont);
                p.row(lbl3, true, 1);
            }
            final UIPanel grid = new UIPanel();
            grid.setLayout(new GridLayout(0, wide ? 5 : 3, 2, 2));
            for (final StatT x4 : CatusFrame.TOOLTIP_STATS) {
                final int stat = slot.sumStat_reforgedItem(x4);
                if (stat > 0) {
                    final JLabel lbl4 = new JLabel(x4.formatValue(stat));
                    lbl4.setFont(CatusFrame.tinyFont);
                    if (slot.reforge != null) {
                        if (slot.reforge.src == x4) {
                            lbl4.setForeground(CatusFrame.DARK_BLUE);
                        }
                        else if (slot.reforge.dst == x4) {
                            lbl4.setForeground(CatusFrame.DARK_GREEN);
                        }
                    }
                    grid.add(lbl4);
                }
            }
            if (grid.getComponentCount() > 0) {
                p.row(grid, true, 1);
            }
            final int race = slot.item.allowableRaces;
            if (race != 0) {
                String text3;
                if (race == RaceT.ALL_ALLIANCE) {
                    text3 = "Faction: Alliance";
                }
                else if (race == RaceT.ALL_HORDE) {
                    text3 = "Faction: Horde";
                }
                else {
                    text3 = "Races: " + BlizzT_SetMember.join(RaceT.ALL, slot.item.allowableRaces);
                }
                final JLabel lbl5 = new JLabel(text3);
                lbl5.setFont(CatusFrame.tinyFont);
                lbl5.setForeground(CatusFrame.DARK_BLUE);
                p.row(lbl5, true, 1);
            }
            if (slot.item.requiredProf != null) {
                final JLabel lbl6 = new JLabel("Requires " + slot.item.requiredProf);
                lbl6.setFont(CatusFrame.tinyFont);
                p.row(lbl6, true, 1);
            }
            final JComboBox reforgeCombo = store.reforgeCombo;
            reforgeCombo.removeAllItems();
            reforgeCombo.addItem("No Reforge");
            for (final StatT x5 : StatT.REFORGE) {
                if (slot.sumStat_originalItem(x5) > 0) {
                    for (final StatT y : CatusFrame.REFORGE_UNIVERSE) {
                        if (slot.sumStat_originalItem(y) == 0) {
                            reforgeCombo.addItem(ReforgePair.make(x5, y));
                        }
                    }
                }
            }
            if (slot.reforge != null) {
                reforgeCombo.setSelectedItem(slot.reforge);
                if (reforgeCombo.getSelectedItem() == null) {
                    reforgeCombo.addItem(slot.reforge);
                    reforgeCombo.setSelectedItem(slot.reforge);
                }
            }
            final JComboBox upgradeCombo = store.upgradeCombo;
            upgradeCombo.removeAllItems();
            upgradeCombo.addItem(CatusFrame.UPGRADE_0);
            if (slot.isUpgradable()) {
                if (slot.item.hasIntermediateUpgrade()) {
                    if (this.asiaMode) {
                        upgradeCombo.addItem(CatusFrame.UPGRADE_1);
                        upgradeCombo.addItem(CatusFrame.UPGRADE_2);
                        upgradeCombo.addItem(CatusFrame.UPGRADE_3);
                        upgradeCombo.addItem(CatusFrame.UPGRADE_4);
                        if (slot.item.hasExtraUpgrade()) {
                            upgradeCombo.addItem(CatusFrame.UPGRADE_5);
                            upgradeCombo.addItem(CatusFrame.UPGRADE_6);
                        }
                    }
                    else {
                        upgradeCombo.addItem(CatusFrame.UPGRADE_1);
                        upgradeCombo.addItem(CatusFrame.UPGRADE_2);
                        if (slot.item.hasExtraUpgrade()) {
                            upgradeCombo.addItem(CatusFrame.UPGRADE_3);
                            upgradeCombo.addItem(CatusFrame.UPGRADE_4);
                        }
                    }
                }
                else {
                    upgradeCombo.addItem(CatusFrame.UPGRADE_2);
                }
            }
            upgradeCombo.addItem("Custom...");
            final int index2 = slot.getEffectiveUpgradeLevel(this.asiaMode);
            if (index2 == -1) {
                upgradeCombo.setEditable(true);
                upgradeCombo.setSelectedItem(slot.getItemLevel() + " Item Level");
                upgradeCombo.setEditable(false);
            }
            else {
                upgradeCombo.setSelectedIndex(index2);
            }
            if (reforgeCombo.getItemCount() > 1) {
                final UIPanel_GridBag row3 = new UIPanel_GridBag();
                row3.setLayout(new GridBagLayout());
                row3.spacer(reforgeCombo);
                row3.add(upgradeCombo);
                p.row(row3, true, 1);
            }
            else {
                p.row(upgradeCombo, true, 1);
            }
            final int socketCount = slot.getSocketCount();
            if (socketCount > 0) {
                for (int i = 0; i < socketCount; ++i) {
                    this._addTooltipGem(p, slot, i, store.gems[i]);
                }
            }
            if (slot.item.socketBonus != null) {
                final JLabel bonusText = new JLabel("Socket Bonus: " + slot.item.socketBonus);
                bonusText.setForeground(slot.hasSocketBonus() ? CatusFrame.DARK_GREEN : SystemColor.textInactiveText);
                bonusText.setFont(CatusFrame.tinyFont);
                final GridBagConstraints gbc = new GridBagConstraints();
                gbc.fill = 2;
                gbc.gridx = 0;
                gbc.weightx = 1.0;
                gbc.insets = new Insets(0, 5, 0, 5);
                p.add(bonusText, gbc);
            }
            this._enchantBuf.clear();
            if (!weapon || (slot.item.minorType != WeaponT.FISHING_POLE && slot.item.minorType != WeaponT.MISC)) {
                for (final EnchantT x6 : EnchantT.EQUIP_MAP.get(slot.item.equipType)) {
                    if (x6.requiredProf == null || x6.requiredProf.isMemberOf(this.mainProfile.profs)) {
                        this._enchantBuf.add(x6);
                    }
                }
            }
            if (!this._enchantBuf.isEmpty()) {
                Collections.sort(this._enchantBuf, CatusFrame.cmp_basicWeights);
                final JComboBox combo2 = store.enchantCombo;
                combo2.removeAllItems();
                combo2.addItem("No Enchant");
                for (final EnchantT x7 : this._enchantBuf) {
                    combo2.addItem(x7);
                }
                if (slot.enchant != null) {
                    combo2.setSelectedItem(slot.enchant);
                }
                p.row(combo2, true, 1);
            }
            if (ProfT.ENG.isMemberOf(this.mainProfile.profs)) {
                this._tinkerBuf.clear();
                for (final TinkerT x8 : TinkerT.MAP.by_name) {
                    if (x8.set.contains(slot.item.equipType)) {
                        this._tinkerBuf.add(x8);
                    }
                }
                if (!this._tinkerBuf.isEmpty()) {
                    final JComboBox combo2 = store.tinkerCombo;
                    combo2.removeAllItems();
                    combo2.addItem("No Tinker");
                    for (final TinkerT x9 : this._tinkerBuf) {
                        combo2.addItem(x9);
                    }
                    if (slot.tinker != null) {
                        combo2.setSelectedItem(slot.tinker);
                    }
                    p.row(combo2, true, 1);
                }
            }
            final GridBagConstraints gbc2 = new GridBagConstraints();
            gbc2.fill = 2;
            gbc2.gridx = 0;
            gbc2.weightx = 1.0;
            gbc2.insets = new Insets(2, 5, 1, 5);
            boolean first = true;
            for (final IntPair<String> x10 : this.sim.getSpellDescriptions(slot.type, slot.getProfile() == this.mainProfile)) {
                final WrapLabel spellText = new WrapLabel(x10.val, CatusFrame.tinyFont);
                spellText.ta.setForeground(CatusFrame.DARK_GREEN);
                final JButton btn2 = new LinkButton(x10.key);
                btn2.setLayout(new GridLayout());
                btn2.add(spellText);
                if (first) {
                    first = false;
                }
                else {
                    final JPanel temp = new JPanel();
                    temp.setBackground(Color.LIGHT_GRAY);
                    temp.setPreferredSize(new Dimension(1, 1));
                    p.add(temp, gbc2);
                }
                p.add(btn2, gbc2);
            }
            final UIPanel_GridBag outer = new UIPanel_GridBag();
            final ImageIcon icon = slot.item.icon.getIcon(API.IconSize.LARGE);
            if (icon != null) {
                final GridBagConstraints gbc3 = new GridBagConstraints();
                gbc3.anchor = 11;
                outer.add(new IconButton(slot.item, icon), gbc3);
            }
            final GridBagConstraints gbc3 = new GridBagConstraints();
            gbc3.anchor = 11;
            gbc3.weightx = 1.0;
            gbc3.weighty = 1.0;
            gbc3.fill = 1;
            p.setBorder(CatusFrame.TOOLTIP_BORDER);
            p.setBackground(Color.WHITE);
            p.setOpaque(true);
            outer.add(p, gbc3);
            comp = outer;
        }
        store.locked = false;
        final UIPanel_GridBag cell = new UIPanel_GridBag();
        cell.row(comp, true, 0);
        cell.balloon();
        return cell;
    }
    
    private void _addTooltipGem(final UIPanel_GridBag p, final Profile.Slot slot, final int index, final GemStore store) {
        final GemT socketType = slot.getSocketAt(index);
        final Gem gem = slot.getGemAt(index);
        final int itemLevel = slot.getItemLevel();
        final JComboBox gemCombo = store.gemCombo;
        gemCombo.removeAllItems();
        final boolean colored = socketType.colored();
        if (colored) {
            if (gem == null) {
                store.colorCombo.setSelectedIndex(0);
            }
            else {
                final GemT gemType = (GemT)gem.minorType;
                store.colorCombo.setSelectedItem(gemType);
                for (final Gem x : this.gemsMap.get(gemType)) {
                    if (itemLevel >= x.minItemLevel && (x.requiredProf == null || x.requiredProf.isMemberOf(this.mainProfile.profs))) {
                        gemCombo.addItem(x);
                    }
                }
            }
        }
        else {
            gemCombo.addItem("No " + socketType.name);
            for (final Gem x2 : this.gemsMap.get(socketType)) {
                if (itemLevel >= x2.minItemLevel && (x2.requiredProf == null || x2.requiredProf.isMemberOf(this.mainProfile.profs))) {
                    gemCombo.addItem(x2);
                }
            }
        }
        if (gem != null) {
            gemCombo.setSelectedIndex(-1);
            gemCombo.setSelectedItem(gem);
            if (gemCombo.getSelectedItem() == null) {
                gemCombo.addItem(gem);
                gemCombo.setSelectedItem(gem);
            }
        }
        gemCombo.setEnabled(!colored || gem != null);
        final UIPanel_GridBag row = new UIPanel_GridBag();
        final JLabel outer = new JLabel(CatusFrame.gemBgImageMap.get(socketType));
        outer.setLayout(new GridBagLayout());
        outer.setPreferredSize(new Dimension(22, 22));
        if (gem == null) {
            outer.add(new JLabel(CatusFrame.socketImageMap.get(socketType)));
        }
        else {
            final ImageIcon icon = gem.icon.getIcon(API.IconSize.SMALL);
            if (icon != null) {
                final LinkButton linkBtn = new LinkButton(gem);
                UI.setIconAndSize(linkBtn, icon);
                outer.add(linkBtn);
            }
        }
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 5);
        row.add(outer, gbc);
        if (colored) {
            final GridBagConstraints gbc2 = new GridBagConstraints();
            gbc2.gridx = 2;
            gbc2.fill = 2;
            row.add(store.colorCombo, gbc2);
        }
        final GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 3;
        gbc2.fill = 2;
        gbc2.weightx = 1.0;
        row.add(gemCombo, gbc2);
        p.row(row, true, 1);
    }
    
    private void _trySetGem(final Profile.Slot slot, final int index, final Gem gem) {
        final Gem prev = slot.getGemAt(index);
        try {
            slot.setGemAt(index, gem);
        }
        catch (RuntimeException err) {
            slot.setGemAt(index, prev);
            this.showError("Unable to Socket Gem", err);
        }
        this._rebuildGear();
    }
    
    private JComboBox _makePotCombo(final boolean same) {
        final JComboBox combo = UI.makeCombo();
        if (same) {
            combo.addItem("Same as Consumable");
        }
        combo.addItem("No Potion");
        combo.addItem(StatValue.make(StatT.AGI, 4000));
        combo.addItem(StatValue.make(StatT.STR, 4000));
        combo.addItem(StatValue.make(StatT.INT, 4000));
        combo.addItem(StatValue.make(StatT.HASTE, 4000));
        combo.addItem(StatValue.make(StatT.AGI, 1200));
        combo.addItem(StatValue.make(StatT.STR, 1200));
        combo.addItem(StatValue.make(StatT.INT, 1200));
        return combo;
    }
    
    private static void _addSuffixIfNotString(final StringBuilder sb, final Object obj, final String suffix) {
        sb.append(obj);
        if (!(obj instanceof String)) {
            sb.append(suffix);
        }
    }
    
    private void _updateConsumeOptions() {
        final StringBuilder sb = new StringBuilder(64);
        _addSuffixIfNotString(sb, this.consume_food_combo.getSelectedItem(), " Food");
        sb.append(", ");
        sb.append(this.consume_flask_combo.getPick());
        sb.append(", and ");
        _addSuffixIfNotString(sb, this.consume_potion_combo.getSelectedItem(), " Potion");
        this.consumePane.desc.setText(sb.toString());
    }
    
    private void _updateTempEffectOptions() {
        final StringBuilder sb = new StringBuilder(128);
        final FeralSim.Heroism hero = this.tempEffect_hero_combo.getPick();
        this.tempEffect_hero_field.setVisible(hero == FeralSim.Heroism.AFTER);
        this.tempEffect_hero_icon.setIcon(this.api.getIconImage((this.cfg.heroismDrums ? InterfaceIcon.DRUMS_OF_RAGE : InterfaceIcon.HEROISM).slug, API.IconSize.SMALL));
        sb.append(this.cfg.heroismDrums ? "Drums of Rage" : "Heroism");
        sb.append(": ");
        sb.append(hero);
        if (this.tempEffect_berserking_toggle.isSelected() && this.tempEffect_berserking_toggle.isEnabled()) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(this.tempEffect_berserking_toggle.getText());
        }
        if (this.tempEffect_lifeblood_toggle.isSelected() && this.tempEffect_lifeblood_toggle.isEnabled()) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(this.tempEffect_lifeblood_toggle.getText());
        }
        final int num_shatteringThrow = this.shatteringSpinner.getValue();
        final int num_skullBanner = this.skullBannerSpinner.getValue();
        final int num_stormlashTotem = this.stormlashTotemSpinner.getValue();
        final int num_unholyFrenzy = this.unholyFrenzySpinner.getValue();
        final int num_tricks = this.tricksSpinner.getValue();
        if (num_stormlashTotem > 0) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(num_stormlashTotem);
            sb.append("x Stormlash");
        }
        if (num_skullBanner > 0) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(num_skullBanner);
            sb.append("x Skull Banner");
        }
        if (num_shatteringThrow > 0) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(num_shatteringThrow);
            sb.append("x Shattering");
        }
        if (num_tricks > 0) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(num_unholyFrenzy);
            sb.append("x Tricks");
        }
        if (num_unholyFrenzy > 0) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(num_unholyFrenzy);
            sb.append("x Unholy Frenzy");
        }
        this.tempEffectPane.desc.setText(sb.toString());
    }
    
    private void _updateHotWOptions() {
        final StringBuilder sb = new StringBuilder(128);
        if (this.hotw_wrath_toggle.isSelected()) {
            sb.append("Wrath");
        }
        if (this.hotw_hurricane_toggle.isSelected()) {
            if (sb.length() > 0) {
                sb.append(" and ");
            }
            sb.append("Hurricane");
        }
        final boolean b = sb.length() > 0;
        this.hotw_beforeBerserk_toggle.setEnabled(b);
        this.hotw_swap_toggle.setEnabled(b);
        this.hotw_bitw_toggle.setEnabled(b);
        if (b) {
            sb.append(" ");
            sb.append(this.hotw_beforeBerserk_toggle.isSelected() ? "before" : "after");
            sb.append(" Berserk");
        }
        else {
            sb.append(" Passive");
        }
        if (this.hotw_swap_toggle.isSelected()) {
            sb.append(", Weapon Swap");
        }
        if (this.hotw_bitw_toggle.isSelected()) {
            sb.append(", BitW");
        }
        this.hotw_pane.desc.setText(sb.toString());
    }
    
    private void _updateDebuffOptions() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append(this.debuff_armor_combo.getPick());
        for (final JToggleButton x : this.debuff_toggles) {
            if (x.isSelected()) {
                sb.append(", ");
                sb.append(x.getText());
            }
        }
        this.debuffPane.desc.setText(sb.toString());
    }
    
    private void _updateRegemOptions() {
        final boolean b = this.regem_always_check.isSelected();
        this.regem_alwaysColor_combo.setEnabled(b);
        this.regem_unless_check.setEnabled(b);
    }
    
    private void _updateGroupBuffOptions() {
        int num = 0;
        final StringBuilder sb = new StringBuilder(128);
        for (final JToggleButton x : this.buff_toggles) {
            if (x.isSelected()) {
                if (num++ > 0) {
                    sb.append(", ");
                }
                sb.append(x.getText());
            }
        }
        this.groupBuff_noneBtn.setEnabled(num > 0);
        this.groupBuff_selfBtn.setEnabled(num != 1 || !this.buff_stats_toggle.isSelected());
        this.groupBuff_allBtn.setEnabled(num < this.buff_toggles.length);
        this.groupBuffPane.desc.setText(String.format("%s (%d of %d)", (num == 0) ? "None" : sb.toString(), num, this.buff_toggles.length));
    }
    
    void loadTalentsFromString(String expr) {
        this.talent_btns_al.lock();
        for (final ButtonGroup bg : this.talentGroups) {
            bg.clearSelection();
        }
        if (expr != null) {
            expr = expr.trim();
            for (int len = Math.min(6, expr.length()), i = 0; i < len; ++i) {
                final int index = expr.charAt(i) - '0';
                if (index >= 0 && index < 3) {
                    this.talent_btns[i][index].setSelected(true);
                }
            }
        }
        this.talent_btns_al.unlock();
        this.applyTalents();
    }
    
    private void applyTalents() {
        for (int i = 0; i < 6; ++i) {
            final ButtonModel bm = this.talentGroups[i].getSelection();
            this.talentBytes[i] = (byte)((bm == null) ? -1 : (bm.getActionCommand().charAt(0) - '0'));
            for (final JToggleButton x : this.talent_btns[i]) {
                x.setFont(x.isSelected() ? CatusFrame.tinyBoldFont : CatusFrame.tinyFont);
            }
        }
        this.spec.loadTalents(this.talentBytes);
        this.hotw_pane.panel.setVisible(this.spec.talent_hotw);
        this.pre_castHT_toggle.setEnabled(this.spec.talent_doc);
        this.calc_fon_btn.setEnabled(this.spec.talent_fon);
        this.feature_smartFoN_check.setEnabled(this.spec.talent_fon);
        this.weight_includeInt_check.setVisible(this.spec.talent_hotw);
        this.weight_includeSP_check.setVisible(this.spec.talent_hotw);
        final StringBuilder sb = new StringBuilder(128);
        int num = 0;
        for (int j = 0; j < 6; ++j) {
            final int t = this.talentBytes[j];
            if (t >= 0) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(FeralSpec.TALENTS[j][t].name);
                ++num;
            }
        }
        if (num == 0) {
            sb.append("None");
        }
        sb.append(" (");
        sb.append(num);
        sb.append(" of 6)");
        this.talents_clearBtn.setEnabled(num > 0);
        this.talent_pane.desc.setText(sb.toString());
    }
    
    void loadGlyphsFromString(final String expr) {
        for (final JComboBox combo : this.glyph_combos) {
            combo.removeActionListener(this.glyph_combo_al);
            combo.removeAllItems();
        }
        int glyphIndex = 0;
        for (final Glyph x : FeralSpec.MAJOR) {
            if (x.isActive(expr)) {
                this.glyph_combos[glyphIndex].addItem(x);
                if (++glyphIndex == this.glyph_combos.length) {
                    break;
                }
            }
        }
        for (final JComboBox combo2 : this.glyph_combos) {
            combo2.addActionListener(this.glyph_combo_al);
        }
        this.applyGlyphs();
    }
    
    String getGlyphsAsString() {
        final StringBuilder sb = new StringBuilder(3);
        for (final JComboBox combo : this.glyph_combos) {
            final Object sel = combo.getSelectedItem();
            if (sel instanceof Glyph) {
                sb.append(((Glyph)sel).ch);
            }
        }
        return sb.toString();
    }
    
    private void applyGlyphs() {
        this.glyph_combo_al.lock();
        final HashSet<Glyph> selected = new HashSet<Glyph>();
        final StringBuilder sb = new StringBuilder(100);
        for (final JComboBox combo : this.glyph_combos) {
            final Object sel = combo.getSelectedItem();
            if (sel instanceof Glyph) {
                final Glyph g = (Glyph)sel;
                selected.add(g);
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(g.name);
            }
        }
        for (final JComboBox combo : this.glyph_combos) {
            final Object sel = combo.getSelectedItem();
            final Glyph g = (Glyph)((sel instanceof Glyph) ? sel : Glyph.NONE);
            combo.removeAllItems();
            combo.addItem("None");
            for (final Glyph x : FeralSpec.MAJOR) {
                if (x == g || !selected.contains(x)) {
                    combo.addItem(x);
                }
            }
            combo.setSelectedItem(g);
        }
        this.glyph_combo_al.unlock();
        if (selected.isEmpty()) {
            sb.append("None");
        }
        sb.append(" (");
        sb.append(selected.size());
        sb.append(" of 3)");
        this.glyphPane.desc.setText(sb.toString());
        sb.setLength(0);
        for (final Glyph g2 : selected) {
            sb.append(g2.ch);
        }
        this.spec.loadGlyphs(sb.toString());
        this.pre_castSR_toggle.setEnabled(this.spec.glyph_sr);
    }
    
    private void _setRace(final RaceT race) {
        final boolean flip = this.mainProfile.race.faction != race.faction;
        this.mainProfile.race = race;
        this.swapProfile.race = race;
        if (flip) {
            final Profile[] profiles = { this.mainProfile, this.swapProfile };
            final HashSet<Integer> unloaded = new HashSet<Integer>();
            for (final Profile p : profiles) {
                for (final Profile.Slot x : p.SLOTS) {
                    if (x.item != null) {
                        final int id = x.item.otherFaction_itemId;
                        if (id != 0) {
                            if (this.api.getItem(id) == null) {
                                unloaded.add(id);
                            }
                        }
                    }
                }
            }
            if (!unloaded.isEmpty()) {
                final DialogProg dp = new DialogProg(this, "Changing Faction");
                dp.forEach(unloaded, new DialogProg.Each<Integer>() {
                    @Override
                    public void each(final int index, final Integer value) {
                        try {
                            CatusFrame.this.api.loadItem(value);
                        }
                        catch (RuntimeException ex) {}
                    }
                });
            }
            for (final Profile p : profiles) {
                for (final Profile.Slot x : p.SLOTS) {
                    if (x.item != null && x.item.otherFaction_itemId != 0) {
                        final Item other = this.api.getItem(x.item.otherFaction_itemId);
                        if (other != null) {
                            x.swapItem(other);
                        }
                    }
                }
            }
        }
    }
    
    private void applyRaceAndProfs() {
        int profs = 0;
        for (final JComboBox x : this.prof_combos) {
            final Object sel = x.getSelectedItem();
            if (sel instanceof ProfT) {
                profs |= ((ProfT)sel).bit;
            }
        }
        final RaceT race = this.race_combo.getPick();
        this.mainProfile.profs = profs;
        this.swapProfile.profs = profs;
        this._setRace(race);
        this.mainProfile.validate();
        this.swapProfile.validate();
        this.consume_flask_combo.repaint();
        this.matchRaceAndProfs();
        this._rebuildGear();
    }
    
    private void matchRaceAndProfs() {
        this.raceAndProfs_al.lock();
        this.race_combo.setSelectedItem(this.mainProfile.race);
        int left = this.mainProfile.profs;
        final int n = this.prof_combos.length;
        final ProfT[] v = new ProfT[n];
        for (int i = 0; i < n; ++i) {
            final Object sel = this.prof_combos[i].getSelectedItem();
            if (sel instanceof ProfT) {
                final ProfT prof = (ProfT)sel;
                if (prof.isMemberOf(left)) {
                    v[i] = prof;
                    left ^= prof.bit;
                }
            }
        }
        for (int i = 0; i < n && left != 0; ++i) {
            if (v[i] == null) {
                final ProfT prof2 = ProfT.ALL[Integer.numberOfTrailingZeros(left)];
                v[i] = prof2;
                left ^= prof2.bit;
            }
        }
        for (int i = 0; i < n; ++i) {
            final JComboBox combo = this.prof_combos[i];
            combo.removeAllItems();
            combo.addItem("None");
            final ProfT prof = v[i];
            for (final ProfT x : ProfT.PRIMARY) {
                if (x == prof || !x.isMemberOf(this.mainProfile.profs)) {
                    combo.addItem(x);
                }
            }
            if (prof != null) {
                combo.setSelectedItem(prof);
            }
        }
        this.raceAndProfs_al.unlock();
        this.updateRaceAndProfsSpecial();
        this.regem_profRow.setVisible(ProfT.JC.isMemberOf(this.mainProfile.profs));
        this.raceAndProf_pane.desc.setText(String.format("%s: %s - %s (%d of 2)", this.mainProfile.race.faction, this.mainProfile.race, BlizzT_SetMember.join(ProfT.ALL, this.mainProfile.profs), Integer.bitCount(this.mainProfile.profs)));
        this._updateTempEffectOptions();
    }
    
    private void updateRaceAndProfsSpecial() {
        this.tempEffect_berserking_toggle.setEnabled(this.mainProfile.race == RaceT.TROLL);
        this.tempEffect_lifeblood_toggle.setEnabled(ProfT.HERB.isMemberOf(this.mainProfile.profs));
    }
    
    static {
        PREFS = Preferences.userRoot().node("com/antistupid/catus");
        LIGHT_RED = new Color(255, 230, 230);
        LIGHT_GREEN = new Color(230, 255, 230);
        DARK_GRAY = new Color(180, 180, 180);
        DARK_GRAY_INFO = new Color(160, 180, 180);
        DARK_GRAY_SIMS = new Color(200, 180, 180);
        DARK_GRAY_BUFF = new Color(180, 180, 200);
        DARK_RED = new Color(128, 0, 0);
        DARK_GREEN = new Color(0, 96, 0);
        DARK_BLUE = new Color(0, 0, 128);
        LIGHT_YELLOW = new Color(255, 255, 223);
        LIGHT_CYAN = new Color(223, 255, 255);
        DARK_YELLOW = new Color(155, 109, 0);
        UPGRADE_0 = new UpgradeWrap("No Upgrade", 0);
        UPGRADE_1 = new UpgradeWrap("+4 Item Level", 4);
        UPGRADE_2 = new UpgradeWrap("+8 Item Level", 8);
        UPGRADE_3 = new UpgradeWrap("+12 Item Level", 12);
        UPGRADE_4 = new UpgradeWrap("+16 Item Level", 16);
        UPGRADE_5 = new UpgradeWrap("+20 Item Level", 20);
        UPGRADE_6 = new UpgradeWrap("+24 Item Level", 24);
        CatusFrame.socketImageMap = new HashMap<GemT, ImageIcon>();
        CatusFrame.gemBgImageMap = new HashMap<GemT, ImageIcon>();
        CatusFrame.WRAP_ICON_BORDER = BorderFactory.createEmptyBorder(0, 2, 0, 2);
        BOLDENER = new Handler<JToggleButton>() {
            @Override
            public void handle(final JToggleButton x) {
                x.setFont(x.getFont().deriveFont((int)(x.isSelected() ? 1 : 0)));
            }
        };
        final Color c = CatusFrame.DARK_GRAY;
        final int w = 2;
        final int p = 2;
        ICON_BORDER = new CompoundBorder(BorderFactory.createMatteBorder(w, w, w, 0, c), BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
        TOOLTIP_BORDER = new CompoundBorder(BorderFactory.createLineBorder(c, w), BorderFactory.createEmptyBorder(p, p, p, p));
        REFORGE_UNIVERSE = StatT.REFORGE_MELEE_DPS;
        (WEIGHTS = new double[StatT.NUM])[StatT.AGI.index] = 6.5;
        CatusFrame.WEIGHTS[StatT.WDMG.index] = 5.7;
        CatusFrame.WEIGHTS[StatT.MASTERY.index] = 3.2;
        CatusFrame.WEIGHTS[StatT.STR.index] = 2.8;
        CatusFrame.WEIGHTS[StatT.AP.index] = 2.8;
        CatusFrame.WEIGHTS[StatT.CRIT.index] = 2.4;
        CatusFrame.WEIGHTS[StatT.HASTE.index] = 2.7;
        CatusFrame.WEIGHTS[StatT.EXP.index] = 2.5;
        CatusFrame.WEIGHTS[StatT.HIT.index] = 2.5;
        REPORTS_DIR = new File("Reports");
        SPINNER_ICON_BORDER = BorderFactory.createEmptyBorder(0, 4, 0, 4);
        cmp_basicWeights = new Comparator<StatValued>() {
            @Override
            public int compare(final StatValued a, final StatValued b) {
                return Double.compare(StatT.getWeight(b, CatusFrame.WEIGHTS), StatT.getWeight(a, CatusFrame.WEIGHTS));
            }
        };
        monoFont = Font.decode("Monospaced").deriveFont(11.0f);
        normalFont = UIManager.getFont("Label.font");
        boldFont = CatusFrame.normalFont.deriveFont(1);
        italicFont = CatusFrame.normalFont.deriveFont(2);
        tinyFont = CatusFrame.normalFont.deriveFont(11.0f);
        tinyBoldFont = CatusFrame.tinyFont.deriveFont(1);
        FAKE = new WLEnemy("Total", 0, 0, 0);
        CatusFrame.JOINT_NAME_REALM_SEP = " ";
        TOOLTIP_STATS = new StatT[] { StatT.STA, StatT.AGI, StatT.STR, StatT.INT, StatT.AP, StatT.SP, StatT.MASTERY, StatT.CRIT, StatT.HASTE, StatT.DODGE, StatT.HIT, StatT.EXP, StatT.PVP_POW, StatT.PVP_RES };
    }
    
    static class Abort extends RuntimeException
    {
    }
    
    static class WLFight extends Int
    {
        final int startTime;
        final int endTime;
        final String comp;
        final String desc;
        final WLEnemy[] bosses;
        
        WLFight(final String name, final int id, final String comp, final int startTime, final int endTime, final String desc, final WLEnemy[] bosses) {
            super(name, id);
            this.comp = comp;
            this.startTime = startTime;
            this.endTime = endTime;
            this.desc = desc;
            this.bosses = bosses;
        }
    }
    
    static class WLEnemy extends Int
    {
        final int guId;
        final int bossId;
        
        WLEnemy(final String name, final int id, final int guId, final int bossId) {
            super(name, id);
            this.guId = guId;
            this.bossId = bossId;
        }
        
        boolean isBoss() {
            return this.bossId > 0;
        }
    }
    
    static class WLActor extends Int
    {
        final int guid;
        final String reportId;
        final String player;
        final String realm;
        final RegionT region;
        final WLFight[] fights;
        
        WLActor(final String name, final int id, final int guid, final String player, final String realm, final RegionT region, final WLFight[] fights, final String reportId) {
            super(name, id);
            this.guid = guid;
            this.player = player;
            this.realm = realm;
            this.region = region;
            this.fights = fights;
            this.reportId = reportId;
        }
    }
    
    enum InterfaceIcon
    {
        TIME("spell_nature_timestop"), 
        GEAR("trade_engineering"), 
        PROWL("ability_druid_prowl"), 
        SCALED("inv_misc_pocketwatch_01"), 
        WARNING("priest_icon_chakra_red"), 
        RUNE("inv_offhand_1h_ulduarraid_d_01"), 
        FOOD("inv_misc_food_18"), 
        FLASK("inv_potion_112"), 
        POTION("trade_alchemy_potiond6"), 
        FIGHT_PATCHWERK("achievement_boss_patchwerk"), 
        FIGHT_CLEAVE("inv_misc_monsterclaw_03"), 
        ACTION_FIGHT("achievement_arena_2v2_1"), 
        ACTION_COMBAT_LOG("inv_inscription_armorscroll01"), 
        BUFF_STATS("spell_nature_regeneration"), 
        BUFF_CRIT("ability_hunter_pet_wolf"), 
        BUFF_MASTERY("spell_holy_greaterblessingofkings"), 
        BUFF_STAM("spell_holy_wordfortitude"), 
        BUFF_AP("ability_warrior_battleshout"), 
        BUFF_SP("spell_holy_magicalsentry"), 
        BUFF_MELEE_HASTE("ability_rogue_disembowel"), 
        PROFILE_A("achievement_pvp_a_06"), 
        PROFILE_B("achievement_pvp_h_06"), 
        BUFF_SPELL_HASTE("spell_shadow_spectralsight"), 
        HEROISM("ability_mage_timewarp"), 
        DRUMS_OF_RAGE("inv_misc_drum_05"), 
        BERSERKING("racial_troll_berserk"), 
        LIFEBLOOD("spell_nature_wispsplodegreen"), 
        STORMLASH("ability_shaman_tranquilmindtotem"), 
        SKULL_BANNER("warrior_skullbanner"), 
        UNHOLY_FRENZY("spell_shadow_unholyfrenzy"), 
        TRICKS_OF_THE_TRADE("ability_rogue_tricksofthetrade"), 
        SHATTERING("ability_warrior_shieldbreak"), 
        DEBUFF_ARMOR("ability_warrior_sunder"), 
        DEBUFF_MELEE("ability_deathknight_brittlebones"), 
        DEBUFF_SPELL("warlock_curse_shadow"), 
        DEBUFF_BLEED("ability_gouge"), 
        ATTACK("ability_dualwield"), 
        SHRED("spell_shadow_vampiricaura"), 
        BERSERK("ability_druid_berserk"), 
        BLOOD("spell_deathknight_bloodboil"), 
        FF("spell_nature_faeriefire"), 
        FB("ability_druid_ferociousbite"), 
        SYMBIOSIS("spell_druid_symbiosis"), 
        HURRICANE("spell_nature_cyclone"), 
        WRATH("spell_nature_wrathv2"), 
        RAKE("ability_druid_disembowel"), 
        THRASH("spell_druid_thrash"), 
        MANGLE("ability_druid_mangle2"), 
        RAVAGE("ability_druid_ravage"), 
        SWIPE("inv_misc_monsterclaw_03"), 
        RIP("ability_ghoulfrenzy"), 
        FON("ability_druid_forceofnature"), 
        SAVAGE_ROAR("ability_druid_skinteeth"), 
        PERIODIC_DMG("spell_holy_powerinfusion"), 
        HUMAN_FORM("spell_nature_wispsplode"), 
        CAT_FORM("ability_druid_catform"), 
        BEAR_FORM("ability_racial_bearform"), 
        HIBERNATE("spell_nature_sleep"), 
        RED_BRAIN("inv_misc_organ_03"), 
        STAMP_ROAR_BEAR("spell_druid_stamedingroar"), 
        STAMP_ROAR("spell_druid_stampedingroar_cat"), 
        SKULL_BASH("inv_bone_skull_04"), 
        STARFIRE("spell_arcane_starfire"), 
        SCRIPT("inv_scroll_06"), 
        ENTRY("spell_nature_wispsplodegreen"), 
        HEALING_TOUCH("spell_nature_healingtouch"), 
        OVERRIDE_SET_BONUSES("inv_misc_enggizmos_37"), 
        AVERAGE_RANGES("inv_throwingknife_04"), 
        ENRAGE("ability_creature_cursed_02"), 
        NUKE("inv_gizmo_supersappercharge"), 
        NOOB("spell_misc_emotionhappy"), 
        GREEN_VORTEX("achievment_boss_wellofeternity"), 
        SAVE_CSV("inv_box_03"), 
        TEETH("achievement_halloween_smiley_01"), 
        WILD_CHARGE_CAT("spell_druid_feralchargecat"), 
        WILD_CHARGE_BEAR("ability_hunter_pet_bear"), 
        CHALLENGE_MODE("spell_holy_championsbond");
        
        final String slug;
        
        private InterfaceIcon(final String name) {
            this.slug = name;
        }
    }
    
    static class UpgradeWrap extends NamedThing
    {
        public final int delta;
        
        public UpgradeWrap(final String name, final int delta) {
            super(name);
            this.delta = delta;
        }
    }
    
    static class BetterToggleModel extends JToggleButton.ToggleButtonModel
    {
        @Override
        public boolean isSelected() {
            return this.isEnabled() && super.isSelected();
        }
        
        public boolean isActuallySelected() {
            return super.isSelected();
        }
    }
    
    static class LinkButton extends JButton implements ActionListener
    {
        final Object thing;
        
        LinkButton(final Object thing) {
            this.thing = thing;
            this.setHorizontalAlignment(2);
            this.setBorderPainted(false);
            this.setContentAreaFilled(false);
            this.setFocusable(false);
            this.setOpaque(false);
            this.setBorder(BorderFactory.createEmptyBorder());
            this.addActionListener(this);
        }
        
        @Override
        public void actionPerformed(final ActionEvent ae) {
            CatusFrame.tryURL(this.thing);
        }
    }
    
    static class IconButton extends JButton implements ActionListener
    {
        final Item item;
        
        public IconButton(final Item item, final ImageIcon icon) {
            this.item = item;
            this.setContentAreaFilled(false);
            this.setFocusable(false);
            this.setBorder(CatusFrame.ICON_BORDER);
            UI.setIconAndSize(this, icon);
            this.addActionListener(this);
        }
        
        @Override
        public void actionPerformed(final ActionEvent ae) {
            CatusFrame.tryURL(this.item);
        }
    }
    
    abstract static class NamedTweaker extends NamedRunnable
    {
        NamedTweaker(final String name) {
            super(name);
        }
        
        void postTweak(final FeralSim sim) {
        }
    }
    
    class Config2
    {
        static final String DEFAULT_NAME = "Untitled Configuration";
        int index;
        String name;
        JSONObject root;
        
        @Override
        public String toString() {
            return this.name;
        }
        
        Profile getGear() {
            final Profile p = new Profile();
            p.spec = SpecT.FERAL;
            p.race = RaceT.MAP.get(JSONHelp.getStr(this.root, "Race", null), RaceT.NIGHT_ELF);
            p.profs = JSONHelp.getInt(this.root, "Profs", 0);
            final String mainCode = JSONHelp.getStr(this.root, "Gear", "");
            boolean ok = true;
            ok &= CompactGear.fromString(CatusFrame.this.api.quickLoader, p, mainCode);
            if (!ok) {
                new DialogProg(CatusFrame.this, "Loading Profiles").execute(new Runnable() {
                    @Override
                    public void run() {
                        CompactGear.fromString(CatusFrame.this.api, p, mainCode);
                    }
                });
            }
            p.validate();
            p.setScaledItemLevel(JSONHelp.getInt(this.root, "ScaledItemLevel", 0));
            return p;
        }
        
        String toJSONString() {
            this.root.put("Name", this.name);
            return this.root.toJSONString();
        }
    }
    
    static class NamedFile
    {
        final File file;
        final String name;
        
        NamedFile(final File f) {
            this.file = f;
            this.name = Fmt.dropOneExt(f.getName());
        }
        
        @Override
        public String toString() {
            return this.name;
        }
    }
    
    static class PremadeTalents
    {
        final String name;
        final String talents;
        
        PremadeTalents(final String name, final String talents) {
            this.name = name;
            this.talents = talents;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
    }
    
    abstract static class SimcScript
    {
        final String name;
        
        SimcScript(final String name) {
            this.name = name;
        }
        
        abstract String getScript();
        
        @Override
        public String toString() {
            return this.name;
        }
    }
    
    class FileScript extends SimcScript
    {
        final File file;
        
        FileScript(final File f) {
            super(f.getName());
            this.file = f;
        }
        
        @Override
        String getScript() {
            try {
                return Utils.readFile(this.file);
            }
            catch (IOException err) {
                CatusFrame.this.showError("Read File Error", String.format("Unable to read file: %s\n\n%s", this.file.getName(), err.toString()));
                return null;
            }
        }
    }
    
    class PrefPane
    {
        final boolean alwaysShowDesc;
        final boolean defaultExpanded;
        final String name;
        final JButton head;
        final JLabel desc;
        final JComponent body;
        final JPanel panel;
        
        PrefPane(final String name, final JComponent pane, final boolean alwaysShowDesc, final boolean defaultExpanded, final Color color) {
            this.name = name;
            this.body = pane;
            this.alwaysShowDesc = alwaysShowDesc;
            this.defaultExpanded = defaultExpanded;
            (this.head = new JButton()).setText(name);
            this.head.setForeground(Color.BLACK);
            this.head.setFont(CatusFrame.boldFont);
            this.head.setFocusable(false);
            this.head.setContentAreaFilled(false);
            this.head.setHorizontalAlignment(2);
            this.head.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
            this.head.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent ae) {
                    if (UI.isAltKeyDown(ae)) {
                        final boolean b = !PrefPane.this.isExpanded();
                        for (final PrefPane x : CatusFrame.this.prefPanes) {
                            x.setExpanded(b);
                        }
                    }
                    else {
                        PrefPane.this.toggle();
                        if (PrefPane.this.isExpanded()) {
                            UI.scrollY(PrefPane.this.body);
                        }
                    }
                }
            });
            (this.desc = new JLabel()).setHorizontalAlignment(4);
            this.desc.setForeground(Color.BLACK);
            this.desc.setFont(CatusFrame.tinyFont);
            final GridBagConstraints gbc = new GridBagConstraints();
            gbc.weightx = 1.0;
            gbc.fill = 2;
            this.head.setLayout(new GridBagLayout());
            this.head.add(new JLabel(" "));
            this.head.add(this.desc, gbc);
            (this.panel = _makePanel()).setLayout(new GridBagLayout());
            gbc.gridx = 0;
            gbc.insets = new Insets(0, 0, 1, 0);
            final JPanel header = new JPanel();
            header.setBackground((color == null) ? CatusFrame.DARK_GRAY : color);
            header.setLayout(new GridLayout());
            header.add(this.head);
            this.panel.add(header, gbc);
            gbc.insets = new Insets(4, 4, 4, 4);
            this.panel.add(this.body, gbc);
            this.setExpanded(defaultExpanded);
        }
        
        final boolean isExpanded() {
            return this.body.isVisible();
        }
        
        final void toggle() {
            this.setExpanded(!this.isExpanded());
        }
        
        final void setExpanded(final boolean b) {
            this.desc.setVisible(this.alwaysShowDesc || !b);
            this.body.setVisible(b);
        }
    }
    
    static class StatCheck extends JCheckBox
    {
        final StatT stat;
        
        StatCheck(final StatT stat) {
            this(stat, stat.name);
        }
        
        StatCheck(final StatT stat, final String name) {
            this.stat = stat;
            this.setText(name);
            this.setFocusable(false);
            this.setOpaque(false);
        }
        
        String key() {
            return "Use" + this.stat.abbr;
        }
    }
    
    class ReforgeToggle extends JCheckBox
    {
        final SlotT slot;
        
        ReforgeToggle(final SlotT slot) {
            super(slot.name);
            this.slot = slot;
            this.setFocusable(false);
            this.setFont(CatusFrame.tinyFont);
            this.setSelected(true);
            this.setOpaque(false);
        }
    }
    
    class ReforgeConstraint
    {
        final StatT stat;
        final JTextField min_field;
        final JTextField max_field;
        final JTextField weight_field;
        final JCheckBox prevent_check;
        
        ReforgeConstraint(final StatT stat) {
            this.stat = stat;
            this.min_field = UI.makeField();
            this.max_field = UI.makeField();
            this.weight_field = UI.makeField();
            (this.prevent_check = UI.makeCheck("Prevent")).setFont(CatusFrame.tinyFont);
            this.prevent_check.setFocusable(false);
            this.min_field.setHorizontalAlignment(0);
            this.max_field.setHorizontalAlignment(0);
            this.weight_field.setHorizontalAlignment(0);
        }
    }
    
    static class Field
    {
        final JPanel panel;
        final JTextField field;
        final JLabel label;
        
        Field(final String name, final String def, final boolean grid) {
            this.field = new JTextField(def);
            this.label = new JLabel(name + ": ", 4);
            this.panel = _makePanel();
            if (grid) {
                this.panel.setLayout(new GridLayout());
                this.panel.add(this.label);
                this.panel.add(this.field);
            }
            else {
                this.panel.setLayout(new GridBagLayout());
                this.panel.add(this.label);
                final GridBagConstraints gbc = new GridBagConstraints();
                gbc.weightx = 1.0;
                gbc.fill = 1;
                this.panel.add(this.field, gbc);
            }
        }
        
        String getValue() {
            return this.field.getText();
        }
        
        void setValue(final String value) {
            this.field.setText(value);
        }
    }
    
    static class Spinner
    {
        final String name;
        final UIPanel_GridBag panel;
        final JSpinner spinner;
        final JLabel label;
        
        Spinner(final String name, final ImageIcon icon, final int min, final int max) {
            this.name = name;
            (this.spinner = new JSpinner(new SpinnerNumberModel(min, min, max, 1))).setOpaque(false);
            final JTextField t = ((JSpinner.DefaultEditor)this.spinner.getEditor()).getTextField();
            t.setEditable(false);
            t.setHighlighter(null);
            t.setHorizontalAlignment(0);
            t.setColumns(2);
            (this.label = new JLabel()).setText(name);
            (this.panel = new UIPanel_GridBag()).setLayout(new GridBagLayout());
            this.panel.add(this.spinner);
            if (icon != null) {
                final JLabel temp = new JLabel(icon);
                temp.setBorder(CatusFrame.SPINNER_ICON_BORDER);
                this.panel.add(temp);
            }
            final GridBagConstraints gbc = new GridBagConstraints();
            gbc.weightx = 1.0;
            gbc.fill = 2;
            gbc.insets = new Insets(0, 5, 0, 0);
            this.panel.add(this.label, gbc);
        }
        
        int getValue() {
            return ((Number)this.spinner.getValue()).intValue();
        }
        
        void setValue(final int value) {
            this.spinner.setValue(value);
        }
    }
    
    static class ProgressDialog extends JDialog
    {
        final JProgressBar pb;
        
        ProgressDialog(final JFrame parent, final String title) {
            super(parent, title, ModalityType.DOCUMENT_MODAL);
            this.setDefaultCloseOperation(0);
            this.setResizable(false);
            final JPanel p = _makePanel();
            p.setLayout(new GridLayout(0, 1));
            (this.pb = new JProgressBar()).setIndeterminate(true);
            p.add(this.pb);
            p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            this.setContentPane(p);
            this.pack();
            this.setSize(new Dimension(300, this.getHeight()));
            this.setLocationRelativeTo(parent);
        }
    }
    
    static class ItemDescWrap
    {
        final Item item;
        
        public ItemDescWrap(final Item item) {
            this.item = item;
        }
        
        @Override
        public String toString() {
            final String s = this.item.nameDesc;
            return (s == null) ? "Normal" : s;
        }
    }
    
    static class ItemWrap
    {
        final Item item;
        final boolean custom;
        final boolean caster;
        static final Comparator<ItemWrap> CMP_itemLevel;
        
        public ItemWrap(final Item item, final boolean custom) {
            this.item = item;
            this.custom = custom;
            this.caster = (item.getStat(StatT.INT) > 0);
        }
        
        @Override
        public String toString() {
            if (this.caster) {
                return String.format("[%d] %s <Caster>", this.item.itemLevel, this.item.fullName());
            }
            return String.format("[%d] %s", this.item.itemLevel, this.item.fullName());
        }
        
        static {
            CMP_itemLevel = new Comparator<ItemWrap>() {
                @Override
                public int compare(final ItemWrap a, final ItemWrap b) {
                    final int cmp = b.item.itemLevel - a.item.itemLevel;
                    return (cmp == 0) ? (b.item.quality.id - a.item.quality.id) : cmp;
                }
            };
        }
    }
    
    class SetBonusHelper
    {
        final ItemSet set;
        final SetBonus bonus;
        final UIPanel_GridBag panel;
        final JCheckBox check;
        
        SetBonusHelper(final ItemSet set, final SetBonus bonus) {
            this.set = set;
            this.bonus = bonus;
            (this.check = UI.makeCheck(Integer.toString(bonus.threshold))).setHorizontalTextPosition(2);
            this.check.setHorizontalAlignment(4);
            this.check.setPreferredSize(new Dimension(48, 16));
            this.check.setFont(CatusFrame.boldFont);
            this.check.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent ae) {
                    if (CatusFrame.this.setBonusIgnoreChange) {
                        return;
                    }
                    CatusFrame.this._rebuildGear();
                }
            });
            final GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = 11;
            (this.panel = new UIPanel_GridBag()).setBorder(UI.EMPTY_BORDER_1);
            this.panel.add(this.check, gbc);
            this.panel.spacer(new WrapLabel(bonus.description, CatusFrame.tinyFont));
        }
        
        String key() {
            return this.set.id + "_" + this.bonus.index;
        }
    }
    
    static class GemWrap
    {
        final Gem gem;
        
        GemWrap(final Gem gem) {
            this.gem = gem;
        }
        
        @Override
        public String toString() {
            return this.gem.fancyStats();
        }
    }
    
    static class GemStore
    {
        final JComboBox colorCombo;
        final JComboBox gemCombo;
        
        GemStore(final JComboBox colorCombo, final JComboBox gemCombo) {
            this.colorCombo = colorCombo;
            CatusFrame.makeGemCombo(this.gemCombo = gemCombo);
        }
    }
    
    class SlotStore
    {
        final Profile.Slot slot;
        final JComboBox itemCombo;
        final JComboBox reforgeCombo;
        final JComboBox upgradeCombo;
        final JComboBox enchantCombo;
        final JComboBox tinkerCombo;
        final JComboBox suffixCombo;
        final JComboBox groupCombo;
        final JButton nameBtn;
        final JButton clearBtn;
        final GemStore[] gems;
        boolean locked;
        
        SlotStore(final Profile.Slot slot_) {
            this.slot = slot_;
            (this.nameBtn = new JButton()).setHorizontalAlignment(2);
            this.nameBtn.setBorderPainted(false);
            this.nameBtn.setContentAreaFilled(false);
            this.nameBtn.setFocusable(false);
            this.nameBtn.setOpaque(false);
            this.nameBtn.setBorder(BorderFactory.createEmptyBorder());
            this.nameBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent ae) {
                    Utils.openURL(String.format("http://www.wowhead.com/item=%d#comments", Math.abs(SlotStore.this.slot.item.itemId)));
                }
            });
            (this.clearBtn = new JButton()).setPreferredSize(new Dimension(16, 16));
            this.clearBtn.setContentAreaFilled(false);
            this.clearBtn.setBorderPainted(false);
            this.clearBtn.setFocusable(false);
            this.clearBtn.setIcon(CatusFrame.redCrossImage);
            this.clearBtn.setToolTipText(Utils.tt_simple("Clear Slot", "Allows you to select a different item for this slot.", "Hold <tt>[alt]</tt> to restore from armory."));
            this.clearBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent ae) {
                    if (UI.isAltKeyDown(ae)) {
                        final Profile p = CatusFrame.this.importArmory(false);
                        if (p == null) {
                            return;
                        }
                        try {
                            SlotStore.this.slot.copy(p.SLOTS[SlotStore.this.slot.index]);
                        }
                        catch (RuntimeException err) {
                            CatusFrame.this.showError("Equip Error", err);
                            return;
                        }
                        CatusFrame.this._rebuildGear();
                    }
                    else {
                        SlotStore.this.slot.clear();
                        CatusFrame.this._rebuildGear();
                    }
                }
            });
            (this.itemCombo = UI.makeCombo()).setPrototypeDisplayValue(this.slot.type.name);
            this.itemCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent ae) {
                    if (SlotStore.this.locked) {
                        return;
                    }
                    final Object sel = SlotStore.this.itemCombo.getSelectedItem();
                    SlotStore.this.itemCombo.hidePopup();
                    UI.setComboText(SlotStore.this.itemCombo);
                    if (sel == "Add item...") {
                        final String result = JOptionPane.showInputDialog(CatusFrame.this, "Item id:", "Add Item", 1);
                        if (result == null) {
                            return;
                        }
                        int id;
                        try {
                            id = Integer.parseInt(result);
                        }
                        catch (NumberFormatException err) {
                            CatusFrame.this.showError("Invalid Item Id", "Item id must be a number: " + err.getMessage());
                            return;
                        }
                        Item[] items;
                        try {
                            items = CatusFrame.this.api.loadItem_bothFactions(id);
                        }
                        catch (RuntimeException err2) {
                            CatusFrame.this.showError("Load Item Error", "Unable to load item: " + err2.getMessage());
                            return;
                        }
                        for (final Item x : items) {
                            final Map<Item, ItemWrap> map = CatusFrame.this.gearMap.get(x.equipType);
                            if (!map.containsKey(x)) {
                                map.put(x, new ItemWrap(x, true));
                            }
                        }
                        Item item = items[0];
                        if (items.length > 1 && item.allowableRaces != 0 && !CatusFrame.this.mainProfile.race.isMemberOf(item.allowableRaces)) {
                            item = items[1];
                        }
                        try {
                            SlotStore.this.slot.setItem(item);
                        }
                        catch (RuntimeException err3) {
                            CatusFrame.this.showError("Equip Error", err3);
                            return;
                        }
                        CatusFrame.this._rebuildGear();
                    }
                    else if (sel == "Restore from Armory") {
                        final Profile p = CatusFrame.this.importArmory(false);
                        if (p == null) {
                            return;
                        }
                        try {
                            SlotStore.this.slot.copy(p.SLOTS[SlotStore.this.slot.index]);
                        }
                        catch (RuntimeException err4) {
                            CatusFrame.this.showError("Equip Error", err4);
                            return;
                        }
                        CatusFrame.this._rebuildGear();
                    }
                    else if (sel == "Edit...") {
                        CatusFrame.this.manageSlot(SlotStore.this.slot.type);
                    }
                    else if (sel instanceof ItemWrap) {
                        try {
                            SlotStore.this.slot.setItem(((ItemWrap)sel).item);
                        }
                        catch (RuntimeException err5) {
                            CatusFrame.this.showError("Equip Error", err5);
                            return;
                        }
                        CatusFrame.this._rebuildGear();
                    }
                }
            });
            this.gems = new GemStore[4];
            for (int i = 0; i < 4; ++i) {
                final int index = i;
                final UIComboBox colorCombo = new UIComboBox();
                colorCombo.setWide(true);
                colorCombo.setFont(CatusFrame.tinyFont);
                colorCombo.setPrototypeDisplayValue("Orange");
                colorCombo.addItem("None");
                for (final GemT x : GemT.COLORS) {
                    colorCombo.addItem(x);
                }
                colorCombo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent ae) {
                        if (SlotStore.this.locked) {
                            return;
                        }
                        final Object sel = colorCombo.getSelectedItem();
                        Gem gem = null;
                        if (sel instanceof GemT) {
                            final GemT type = (GemT)sel;
                            for (final Gem x : CatusFrame.this.gemsMap.get(type)) {
                                if (x.requiredProf == null) {
                                    gem = x;
                                    break;
                                }
                            }
                        }
                        CatusFrame.this._trySetGem(SlotStore.this.slot, index, gem);
                    }
                });
                final UIComboBox gemCombo = new UIComboBox();
                gemCombo.setWide(true);
                gemCombo.setFont(CatusFrame.tinyFont);
                gemCombo.setPrototypeDisplayValue("This is a");
                gemCombo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent ae) {
                        if (SlotStore.this.locked) {
                            return;
                        }
                        final Object sel = gemCombo.getSelectedItem();
                        CatusFrame.this._trySetGem(SlotStore.this.slot, index, (sel instanceof Gem) ? ((Gem)sel) : null);
                    }
                });
                this.gems[i] = new GemStore(colorCombo, gemCombo);
            }
            (this.reforgeCombo = UI.makeCombo()).setPrototypeDisplayValue("");
            this.reforgeCombo.setFont(CatusFrame.tinyFont);
            this.reforgeCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent ae) {
                    if (SlotStore.this.locked) {
                        return;
                    }
                    try {
                        final Object sel = SlotStore.this.reforgeCombo.getSelectedItem();
                        SlotStore.this.slot.setReforge((sel instanceof ReforgePair) ? ((ReforgePair)sel) : null);
                    }
                    catch (RuntimeException err) {
                        CatusFrame.this.showError("Invalid Reforge", err);
                    }
                    CatusFrame.this._rebuildGear();
                }
            });
            (this.upgradeCombo = UI.makeCombo()).setPrototypeDisplayValue("1000 Item Level");
            this.upgradeCombo.setFont(CatusFrame.tinyFont);
            this.upgradeCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent ae) {
                    if (SlotStore.this.locked) {
                        return;
                    }
                    try {
                        final Object sel = SlotStore.this.upgradeCombo.getSelectedItem();
                        SlotStore.this.upgradeCombo.hidePopup();
                        if (sel instanceof UpgradeWrap) {
                            SlotStore.this.slot.setItemLevelDelta(((UpgradeWrap)sel).delta);
                        }
                        else {
                            String input = JOptionPane.showInputDialog(CatusFrame.this, "", "Custom Item Level", 1);
                            if (input != null) {
                                input = input.trim();
                                int level;
                                if (input.startsWith("+")) {
                                    level = SlotStore.this.slot.item.itemLevel + Integer.parseInt(input.substring(1).trim());
                                }
                                else if (input.startsWith("-")) {
                                    level = SlotStore.this.slot.item.itemLevel - Integer.parseInt(input.substring(1).trim());
                                }
                                else {
                                    level = Integer.parseInt(input);
                                }
                                SlotStore.this.slot.setItemLevel(level);
                            }
                        }
                    }
                    catch (RuntimeException err) {
                        CatusFrame.this.showError("Item Level Error", err);
                    }
                    CatusFrame.this._rebuildGear();
                }
            });
            (this.enchantCombo = UI.makeCombo()).setFont(CatusFrame.tinyFont);
            this.enchantCombo.setPrototypeDisplayValue("");
            this.enchantCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent ae) {
                    if (SlotStore.this.locked) {
                        return;
                    }
                    final Object sel = SlotStore.this.enchantCombo.getSelectedItem();
                    SlotStore.this.enchantCombo.hidePopup();
                    try {
                        SlotStore.this.slot.setEnchant((sel instanceof EnchantT) ? ((EnchantT)sel) : null);
                    }
                    catch (RuntimeException err) {
                        CatusFrame.this.showError("Apply Enchant Error", err);
                    }
                    CatusFrame.this._rebuildGear();
                }
            });
            (this.tinkerCombo = UI.makeCombo()).setPrototypeDisplayValue("");
            this.tinkerCombo.setFont(CatusFrame.tinyFont);
            this.tinkerCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent ae) {
                    if (SlotStore.this.locked) {
                        return;
                    }
                    final Object sel = SlotStore.this.tinkerCombo.getSelectedItem();
                    try {
                        SlotStore.this.slot.setTinker((sel instanceof TinkerT) ? ((TinkerT)sel) : null);
                    }
                    catch (RuntimeException err) {
                        CatusFrame.this.showError("Apply Tinker Error", err);
                    }
                    CatusFrame.this._rebuildGear();
                }
            });
            (this.suffixCombo = UI.makeCombo()).setFont(CatusFrame.tinyBoldFont);
            this.suffixCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent ae) {
                    if (SlotStore.this.locked) {
                        return;
                    }
                    try {
                        SlotStore.this.slot.setSuffixIndex(SlotStore.this.suffixCombo.getSelectedIndex());
                    }
                    catch (RuntimeException err) {
                        CatusFrame.this.showError("Invalid Suffix", err);
                    }
                    CatusFrame.this._rebuildGear();
                }
            });
            (this.groupCombo = UI.makeCombo()).setFont(CatusFrame.tinyFont);
            final ListCellRenderer lcr = this.groupCombo.getRenderer();
            this.groupCombo.setUI(new BasicComboBoxUI() {
                @Override
                protected JButton createArrowButton() {
                    return new JButton() {
                        @Override
                        public int getWidth() {
                            return 0;
                        }
                    };
                }
            });
            this.groupCombo.setBorder(BorderFactory.createEmptyBorder());
            this.groupCombo.setBackground(Color.WHITE);
            this.groupCombo.setFont(CatusFrame.tinyFont);
            this.groupCombo.setForeground(CatusFrame.DARK_GREEN);
            final JLabel lbl = new JLabel("Abc");
            lbl.setOpaque(true);
            this.groupCombo.setRenderer(new ListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
                    if (index == -1) {
                        lbl.setText(String.valueOf(value));
                        return lbl;
                    }
                    return lcr.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                }
            });
            this.groupCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent ae) {
                    if (SlotStore.this.locked) {
                        return;
                    }
                    final ItemDescWrap sel = (ItemDescWrap)SlotStore.this.groupCombo.getSelectedItem();
                    try {
                        SlotStore.this.slot.swapItem(sel.item);
                    }
                    catch (RuntimeException err) {
                        CatusFrame.this.showError("Unexpected Item Swap Error", err);
                        err.printStackTrace();
                    }
                    CatusFrame.this._rebuildGear();
                }
            });
        }
    }
}
