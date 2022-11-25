// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import javax.swing.JOptionPane;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.awt.Window;
import java.util.Arrays;
import java.util.Set;
import javax.swing.KeyStroke;
import java.util.HashSet;
import java.util.Iterator;
import java.awt.Point;
import java.util.prefs.BackingStoreException;
import javax.swing.ToolTipManager;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Container;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
import java.util.Collection;
import java.awt.Color;
import javax.swing.Timer;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.prefs.Preferences;
import javax.swing.JFrame;

public class WWFrame extends JFrame
{
    static final int VERSION = 20;
    static final Preferences PREFS;
    final API api;
    final HttpCache hc;
    static String introText;
    static final NamedReforgerConfig[] orange_options;
    static final NamedReforgerConfig[] green_options;
    final JTextField char_field;
    final UIComboBox<RecentArmory> recent_combo;
    final JButton import_btn;
    final UIComboBox<RegionT> region_combo;
    final JButton armory_btn;
    final JButton reforge_btn;
    final JLabel reforgeTime_lbl;
    final JButton similar_btn;
    final JButton edit_btn;
    final JButton showCache_btn;
    final JButton exportSimc_btn;
    final JButton bounds_btn;
    final JButton copyStats_btn;
    final JButton reforgeClear_btn;
    final JButton reforgeExport_btn;
    final JButton askMrRobot_btn;
    final JButton shoppingList_btn;
    final JButton compareArmory_btn;
    final JTextArea code_ta;
    final JTextField hit_field;
    final JTextField exp_field;
    final JTextField range_field;
    final UIComboBox<StatT> stat_combo;
    final JTextField overflow_field;
    final JTextField gap1_field;
    final JTextField gap2_field;
    final JLabel gap1_lbl;
    final JLabel gap2_lbl;
    final UIComboBox<Reforger111.SearchMode> hit_combo;
    final UIComboBox<Reforger111.SearchMode> exp_combo;
    final JCheckBox enchantHands_check;
    final JCheckBox enchantBack_check;
    final JCheckBox enchantFeet_check;
    final JCheckBox changeGems_check;
    final JCheckBox keepGemColors_check;
    final JCheckBox canBreakBonuses_check;
    final UIComboBox<NamedReforgerConfig> orange_combo;
    final UIComboBox<NamedReforgerConfig> green_combo;
    final UIPanel_GridBag results_row;
    final JComboBox results_combo;
    final ReforgeToggle[] reforge_toggles;
    final SmartTabPane tabs;
    final StatT[] reforgeMax_stats;
    final JTextField reforgeMax_hit_field;
    final JTextField reforgeMax_exp_field;
    final JTextField reforgeMax_range_field;
    final JTextField reforgeMax_depth_field;
    final JTextField[] reforgeMax_fields;
    final JCheckBox reforgeMax_changeGems_check;
    final JCheckBox reforgeMax_leaveEmpty_check;
    final JCheckBox reforgeMax_breakBonus_check;
    final JCheckBox reforgeMax_enchantHands_check;
    final JCheckBox reforgeMax_enchantBack_check;
    final JCheckBox reforgeMax_enchantFeet_check;
    final UIPanel_GridBag reforgeMax_critPane;
    final JCheckBox reforgeMax_crit_check;
    final JTextField reforgeMax_crit_field;
    final JCheckBox reforgeMax_crit_statBuff_check;
    final JCheckBox reforgeMax_crit_critBuff_check;
    final JTextField reforgeMax_crit_agiBuff_field;
    final JLabel summary_lbl;
    final JLabel audit_lbl;
    final JLabel hit_lbl;
    final JLabel exp_lbl;
    final JLabel mr_lbl;
    final JLabel hr_lbl;
    final JLabel cr_lbl;
    final Profile mainProfile;
    final Profile saveProfile;
    final Profile armoryProfile;
    final ArrayList<RecentArmory> recentList;
    final StatT[] REFORGE_UNIVERSE;
    final LockableActionListener recent_combo_al;
    final LockableActionListener results_combo_al;
    final Timer timer;
    static final Color DARK_RED;
    static final Color DARK_GREEN;
    
    static void boot() {
        final ArrayList<BootFrame.Phase> phases = new ArrayList<BootFrame.Phase>();
        final HttpCache hc = new HttpCache();
        phases.add(BootFrame.makePhase_taint(hc, WWFrame.PREFS, "Zephyrus", 20, null));
        final API api = new API(hc);
        phases.add(new BootFrame.Phase("Loading Assets") {
            @Override
            public void run(final BootFrame bf) {
                bf.updateStatus("Downloading intro text");
                WWFrame.introText = hc.getStr_silent("https://dl.dropboxusercontent.com/u/2989349/catus_data/ZephyrusDefault.txt", "", 0);
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BootFrame().execute(phases);
                new WWFrame(hc, api).begin();
            }
        });
    }
    
    WWFrame(final HttpCache hc, final API api) {
        this.import_btn = UI.makeButton("Import");
        this.region_combo = new UIComboBox<RegionT>(RegionT.values());
        this.armory_btn = UI.makeButton("Armory");
        this.reforge_btn = UI.makeButton("Reforge");
        this.similar_btn = UI.makeButton("Minimize Cost");
        this.reforgeClear_btn = UI.makeButton("Clear Reforges");
        this.reforgeExport_btn = UI.makeButton("Reforgerade");
        this.askMrRobot_btn = UI.makeButton("AskMrRobot");
        this.shoppingList_btn = UI.makeButton("Shopping List");
        this.compareArmory_btn = UI.makeButton("Compare to Armory");
        this.code_ta = new JTextArea();
        this.reforgeMax_stats = new StatT[] { StatT.AGI, StatT.STR, StatT.AP, StatT.MASTERY, StatT.HASTE, StatT.CRIT, StatT.PVP_POW, StatT.PVP_RES };
        this.summary_lbl = new JLabel();
        this.audit_lbl = new JLabel();
        this.hit_lbl = new JLabel();
        this.exp_lbl = new JLabel();
        this.mr_lbl = new JLabel();
        this.hr_lbl = new JLabel();
        this.cr_lbl = new JLabel();
        this.mainProfile = new Profile();
        this.saveProfile = new Profile();
        this.armoryProfile = new Profile();
        this.recentList = new ArrayList<RecentArmory>();
        this.REFORGE_UNIVERSE = StatT.REFORGE_MELEE_DPS;
        this.recent_combo_al = new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                final Object sel = WWFrame.this.recent_combo.getSelectedItem();
                WWFrame.this.resetRecentCombo();
                if (sel instanceof RecentArmory) {
                    final RecentArmory temp = (RecentArmory)sel;
                    WWFrame.this.region_combo.setSelectedItem(temp.region);
                    WWFrame.this.char_field.setText(temp.name + " " + temp.realm + "!");
                    WWFrame.this.import_btn.doClick();
                }
                else if (new DialogRecentArmory(WWFrame.this).edit(WWFrame.this.recentList)) {
                    WWFrame.this.updateRecentCombo();
                }
            }
        };
        this.results_combo_al = new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                final Object sel = WWFrame.this.results_combo.getSelectedItem();
                if (sel instanceof ProfilePerm) {
                    final ArrayList<String> errors = new ArrayList<String>();
                    if (!CompactGear.fromString(WWFrame.this.api, WWFrame.this.mainProfile, ((ProfilePerm)sel).code, errors, 0)) {
                        WWFrame.this.showError("Unexpected Error", "WTF!\n\n" + Fmt.join(errors, "\n"));
                    }
                    WWFrame.this.updateAll();
                }
            }
        };
        this.timer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                WWFrame.this.reforgeTime_lbl.setVisible(false);
            }
        });
        this.hc = hc;
        this.api = api;
        this.setTitle(Utils.windowTitle("Zephyrus", 20));
        this.setDefaultCloseOperation(3);
        this.mainProfile.spec = SpecT.WIND;
        final Dimension numSize = new JTextField("123456").getPreferredSize();
        final int pad = 4;
        final UIPanel_GridBag p = new UIPanel_GridBag();
        this.char_field = new JTextField();
        (this.recent_combo = new UIComboBox<RecentArmory>()).setPrototypeDisplayValue("Recent");
        this.recent_combo.setWide(true);
        UIPanel_GridBag row = new UIPanel_GridBag();
        row.add(this.recent_combo);
        row.add(this.region_combo);
        row.spacer(this.char_field);
        row.add(this.armory_btn);
        row.add(this.import_btn);
        row.pad(pad);
        p.row(row, true, 0);
        this.code_ta.setRows(16);
        this.code_ta.setColumns(130);
        this.code_ta.setFont(UI.FONT_MONO);
        this.code_ta.setEditable(false);
        final JScrollPane sp = new JScrollPane();
        sp.setVerticalScrollBarPolicy(22);
        sp.setHorizontalScrollBarPolicy(30);
        sp.setViewportView(this.code_ta);
        p.row_both(sp);
        this.showCache_btn = UI.makeButton("Reveal Cache");
        this.edit_btn = UI.makeButton("Edit Profile");
        this.exportSimc_btn = UI.makeButton("Export Simc");
        row = new UIPanel_GridBag();
        row.add(this.summary_lbl);
        row.spacer();
        row.add(this.showCache_btn);
        row.add(this.exportSimc_btn);
        row.add(this.edit_btn);
        row.pad(pad);
        p.row(row, true, 0);
        final UIPanel row2 = new UIPanel();
        row2.setLayout(new GridLayout());
        row2.add(new L("Hit"));
        row2.add(this.hit_lbl);
        row2.add(new L("Exp"));
        row2.add(this.exp_lbl);
        row2.add(new L("Mastery"));
        row2.add(this.mr_lbl);
        row2.add(new L("Haste"));
        row2.add(this.hr_lbl);
        row2.add(new L("Crit"));
        row2.add(this.cr_lbl);
        p.row(row2, false, pad);
        this.audit_lbl.setHorizontalAlignment(0);
        UI.makeBold(this.audit_lbl);
        this.audit_lbl.setForeground(WWFrame.DARK_RED);
        p.row(this.audit_lbl, true, pad);
        final int n = this.mainProfile.SLOTS.length;
        this.reforge_toggles = new ReforgeToggle[n];
        for (int i = 0; i < n; ++i) {
            this.reforge_toggles[i] = new ReforgeToggle(this.mainProfile.SLOTS[i].type);
        }
        final UIPanel row3 = new UIPanel();
        row3.setLayout(new GridLayout(0, (n + 1) / 2));
        for (final JCheckBox x : this.reforge_toggles) {
            row3.add(x);
        }
        p.row(row3, true, pad);
        this.bounds_btn = UI.makeButton("Find Bounds");
        this.copyStats_btn = UI.makeButton("Copy Stats");
        row = new UIPanel_GridBag();
        row.add(this.reforgeExport_btn);
        row.add(this.shoppingList_btn);
        row.add(this.askMrRobot_btn);
        row.gap();
        row.add(this.compareArmory_btn);
        row.add(this.copyStats_btn);
        row.spacer();
        row.add(this.reforgeClear_btn);
        row.pad(pad);
        p.row(row, true, 0);
        final UIPanel_GridBag runeTab = new UIPanel_GridBag();
        runeTab.setName("Rune of Re-Origination");
        runeTab.pad(pad);
        final UIPanel_GridBag maxTab = new UIPanel_GridBag();
        maxTab.setName("Maximize Secondary");
        maxTab.pad(pad);
        this.hit_field = new JTextField();
        this.exp_field = new JTextField();
        this.range_field = new JTextField();
        this.hit_field.setName("Hit");
        this.exp_field.setName("Expertise");
        this.range_field.setName("Range");
        this.hit_field.setPreferredSize(numSize);
        this.exp_field.setPreferredSize(numSize);
        this.range_field.setPreferredSize(numSize);
        this.hit_combo = new UIComboBox<Reforger111.SearchMode>(Reforger111.SearchMode.values());
        this.exp_combo = new UIComboBox<Reforger111.SearchMode>(Reforger111.SearchMode.values());
        UIPanel_GridBag row4 = new UIPanel_GridBag();
        row4.add(UI.makeBold(new JLabel(this.hit_field.getName() + ": ")));
        row4.add(this.hit_combo);
        row4.add(this.hit_field);
        row4.gap();
        row4.add(UI.makeBold(new JLabel(this.exp_field.getName() + ": ")));
        row4.add(this.exp_combo);
        row4.add(this.exp_field);
        row4.gap();
        row4.add(UI.makeBold(new JLabel(this.range_field.getName() + ": ")));
        row4.add(this.range_field);
        runeTab.row(row4, false, pad);
        this.stat_combo = new UIComboBox<StatT>(StatT.MASTERY, (StatT[])new Object[] { StatT.CRIT, StatT.HASTE });
        this.overflow_field = new JTextField();
        this.gap2_field = new JTextField();
        this.gap1_field = new JTextField();
        this.gap1_lbl = new JLabel();
        this.gap2_lbl = new JLabel();
        this.overflow_field.setName("Overflow");
        this.overflow_field.setPreferredSize(numSize);
        this.gap1_field.setPreferredSize(numSize);
        this.gap2_field.setPreferredSize(numSize);
        row4 = new UIPanel_GridBag();
        row4.add(UI.makeBold(new JLabel("Proc: ")));
        row4.add(this.stat_combo);
        row4.gap();
        row4.add(new JLabel("Overflow: "));
        row4.add(this.overflow_field);
        row4.gap();
        row4.add(this.gap1_lbl);
        row4.add(this.gap1_field);
        row4.gap();
        row4.add(this.gap2_lbl);
        row4.add(this.gap2_field);
        runeTab.row(row4, false, pad);
        this.enchantHands_check = UI.makeCheck("Hands");
        this.enchantBack_check = UI.makeCheck("Back");
        this.enchantFeet_check = UI.makeCheck("Feet");
        this.changeGems_check = UI.makeCheck("Change Gems:");
        this.keepGemColors_check = UI.makeCheck("Keep Colors");
        this.canBreakBonuses_check = UI.makeCheck("Break Bonuses");
        row4 = new UIPanel_GridBag();
        row4.add(UI.makeBold(new JLabel("Enchants: ")));
        row4.add(this.enchantHands_check);
        row4.add(this.enchantBack_check);
        row4.add(this.enchantFeet_check);
        row4.gap();
        row4.add(UI.makeBold(this.changeGems_check));
        row4.add(this.keepGemColors_check);
        row4.add(this.canBreakBonuses_check);
        runeTab.row(row4, false, pad);
        this.orange_combo = new UIComboBox<NamedReforgerConfig>(WWFrame.orange_options);
        this.green_combo = new UIComboBox<NamedReforgerConfig>(WWFrame.green_options);
        row4 = new UIPanel_GridBag();
        ImageIcon icon = api.getIconImage("inv_misc_gem_x4_rare_cut_orange", API.IconSize.SMALL);
        if (icon != null) {
            row4.add(new JLabel(icon));
        }
        row4.add(this.orange_combo);
        row4.gap();
        icon = api.getIconImage("inv_misc_gem_x4_rare_cut_green", API.IconSize.SMALL);
        if (icon != null) {
            row4.add(new JLabel(icon));
        }
        row4.add(this.green_combo);
        runeTab.row(row4, false, pad);
        this.reforgeMax_hit_field = new JTextField();
        this.reforgeMax_exp_field = new JTextField();
        this.reforgeMax_range_field = new JTextField();
        this.reforgeMax_depth_field = new JTextField();
        this.reforgeMax_fields = new JTextField[this.reforgeMax_stats.length];
        for (int j = 0; j < this.reforgeMax_stats.length; ++j) {
            final JTextField f = new JTextField();
            f.setName(this.reforgeMax_stats[j].abbr);
            f.setHorizontalAlignment(0);
            this.reforgeMax_fields[j] = f;
        }
        this.reforgeMax_hit_field.setName("Hit");
        this.reforgeMax_exp_field.setName("Expertise");
        this.reforgeMax_range_field.setName("Range");
        this.reforgeMax_depth_field.setName("Depth");
        this.reforgeMax_hit_field.setPreferredSize(numSize);
        this.reforgeMax_exp_field.setPreferredSize(numSize);
        this.reforgeMax_range_field.setPreferredSize(numSize);
        this.reforgeMax_depth_field.setPreferredSize(numSize);
        this.reforgeMax_enchantHands_check = UI.makeCheck("Hands");
        this.reforgeMax_enchantBack_check = UI.makeCheck("Back");
        this.reforgeMax_enchantFeet_check = UI.makeCheck("Feet");
        this.reforgeMax_changeGems_check = UI.makeBold(UI.makeCheck("Change Gems: "));
        this.reforgeMax_breakBonus_check = UI.makeCheck("Break Bonuses");
        this.reforgeMax_leaveEmpty_check = UI.makeCheck("Leave Empty");
        this.reforgeMax_critPane = new UIPanel_GridBag();
        (this.reforgeMax_crit_field = new JTextField()).setName("Crit");
        this.reforgeMax_crit_field.setPreferredSize(numSize);
        (this.reforgeMax_crit_critBuff_check = UI.makeCheck("+Stats")).setFont(CatusFrame.tinyFont);
        (this.reforgeMax_crit_statBuff_check = UI.makeCheck("+Crit")).setFont(CatusFrame.tinyFont);
        (this.reforgeMax_crit_agiBuff_field = new JTextField()).setName("Agility");
        this.reforgeMax_crit_agiBuff_field.setPreferredSize(numSize);
        this.reforgeMax_crit_check = UI.makeBold(UI.makeCheck("Enforce Crit Cap:"));
        row4 = new UIPanel_GridBag();
        row4.add(UI.makeBold(new JLabel(this.reforgeMax_hit_field.getName() + ": ")));
        row4.add(this.reforgeMax_hit_field);
        row4.gap();
        row4.add(UI.makeBold(new JLabel(this.reforgeMax_exp_field.getName() + ": ")));
        row4.add(this.reforgeMax_exp_field);
        row4.gap();
        row4.add(UI.makeBold(new JLabel(this.reforgeMax_range_field.getName() + ": ")));
        row4.add(this.reforgeMax_range_field);
        row4.gap();
        row4.add(UI.makeBold(new JLabel(this.reforgeMax_depth_field.getName() + ": ")));
        row4.add(this.reforgeMax_depth_field);
        maxTab.row(row4, false, 0);
        row4 = new UIPanel_GridBag();
        row4.add(UI.makeBold(new JLabel("Stat Weights:")));
        row4.gap();
        final UIPanel grid = new UIPanel();
        grid.setLayout(new GridLayout(0, 6));
        for (final JTextField f2 : this.reforgeMax_fields) {
            grid.add(new JLabel(f2.getName() + ": ", 4));
            grid.add(f2);
        }
        row4.add(grid);
        maxTab.row(row4, false, pad);
        final JLabel reforgeMax_crit_label = UI.L("  Agility: ", CatusFrame.tinyFont);
        UI.onChange_enable(this.reforgeMax_crit_check, this.reforgeMax_crit_field, this.reforgeMax_crit_statBuff_check, this.reforgeMax_crit_critBuff_check, reforgeMax_crit_label, this.reforgeMax_crit_agiBuff_field);
        this.reforgeMax_critPane.add(this.reforgeMax_crit_check);
        this.reforgeMax_critPane.add(this.reforgeMax_crit_field);
        this.reforgeMax_critPane.gap();
        this.reforgeMax_critPane.add(this.reforgeMax_crit_statBuff_check);
        this.reforgeMax_critPane.add(this.reforgeMax_crit_critBuff_check);
        this.reforgeMax_critPane.add(reforgeMax_crit_label);
        this.reforgeMax_critPane.add(this.reforgeMax_crit_agiBuff_field);
        maxTab.row(this.reforgeMax_critPane, false, pad);
        UIPanel_GridBag row5 = new UIPanel_GridBag();
        row5.add(this.reforgeMax_changeGems_check);
        row5.add(this.reforgeMax_breakBonus_check);
        row5.add(this.reforgeMax_leaveEmpty_check);
        maxTab.row(row5, false, pad);
        row5 = new UIPanel_GridBag();
        row5.add(UI.makeBold(new JLabel("Enchants: ")));
        row5.add(this.reforgeMax_enchantHands_check);
        row5.add(this.reforgeMax_enchantBack_check);
        row5.add(this.reforgeMax_enchantFeet_check);
        maxTab.row(row5, false, pad);
        p.row(this.tabs = new SmartTabPane(new Component[] { runeTab, maxTab }) {
            @Override
            public void tabChanged(final int index) {
                WWFrame.this.similar_btn.setEnabled(index == 0);
            }
        }, true, pad);
        this.reforgeTime_lbl = new JLabel();
        row5 = new UIPanel_GridBag();
        row5.add(this.bounds_btn);
        row5.spacer();
        row5.add(this.reforgeTime_lbl);
        row5.add(this.similar_btn);
        row5.add(this.reforge_btn);
        row5.pad(pad);
        p.row(row5, true, 0);
        (this.results_combo = new JComboBox()).addActionListener(this.results_combo_al);
        (this.results_row = new UIPanel_GridBag()).row(new UISeparator(), true, 0);
        this.results_row.row(this.results_combo, true, 0);
        this.results_row.cap_bot(pad);
        p.row(this.results_row, true, 0);
        this.setContentPane(p);
        this.updateUI();
        this.code_ta.setText(WWFrame.introText);
        this.setToolTips();
        this.updateProcType();
        this.results_row.setVisible(false);
        this.packAndSize();
    }
    
    void setToolTips() {
        ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
        String tt = "<html>Enter <b>Name</b> to do a player search.<br/>Enter <b>Name Realm</b> to find a specific character.<br/>Realm is validated by default.<br/>Follow realm with an exclamation-point `!` to prevent realm validation.<br/>Hold down <tt>[alt]</tt> while importing to force refresh.</html>";
        this.char_field.setToolTipText(tt);
        this.import_btn.setToolTipText(tt);
        this.reforgeExport_btn.setToolTipText("<html>Export current reforgings to <b>Reforgerade</b> instructions.</html>");
        this.bounds_btn.setToolTipText("<html>Compute the minimum and maximum obtainable values for each stat.<br/>Note: each extrema is calculated independantly meaning they might be mutually exclusive.</html>");
        this.stat_combo.setToolTipText("<html>Choose which stat is the highest.</html>");
        this.enchantHands_check.setToolTipText("<html>+170 <b>Expertise</b>, <b>Mastery</b>, or <b>Haste</b>.<br>Ignored if slot is frozen.</html>");
        this.enchantBack_check.setToolTipText("<html>+180 <b>Crit</b> or <b>Hit</b><br/>Ignored if you use an Embroidery.<br>Ignored if slot is frozen.</html>");
        this.enchantFeet_check.setToolTipText("<html>+140 <b>Agility</b> or <b>Mastery</b>.<br>Ignored if slot is frozen.</html>");
        this.range_field.setToolTipText("<html>The <b>maximum</b> distance from the target to search for Hit/Exp solutions.</html>");
        this.armory_btn.setToolTipText("<html>Show the armory in a web browser.</html>");
        this.showCache_btn.setToolTipText("<html>Show the location of the Zephyrus Cache.</html>");
        this.exportSimc_btn.setToolTipText("<html>Export current profile to <b>SimulationCraft</b> profile format.</html>");
        this.copyStats_btn.setToolTipText("<html>Get a human-readable copy of your secondary stats.<br/>Helpful if you're doing a reforge for someone.</html>");
        tt = "<html><b>At most</b>: cannot exceed this amount.<br/><b>Near</b>: centered on this value but dynamically adjusted based on bounds.<br/><b>At least</b>: cannot fall below this amount.</html>";
        this.hit_combo.setToolTipText(tt);
        this.hit_field.setToolTipText(tt);
        this.exp_combo.setToolTipText(tt);
        this.exp_field.setToolTipText(tt);
        this.shoppingList_btn.setToolTipText("<html>Consolidated list of changes needed to achieve current reforge.</html>");
        this.compareArmory_btn.setToolTipText("<html>Compare your current profile to the last imported profile.</html>");
        this.edit_btn.setToolTipText("<html><b>Modify your gear</b> using CompactGear representation.<br/>This will always restore your gear to your last imported or edited profile.<br/>Errors will be generated if you enter invalid instructions.</html>");
        this.reforge_btn.setToolTipText("<html><b>Exhaustively reforge your gear.</b><br/>This will always start from your last imported or edited profile.<br/>There are two phases of reforging:<br/>Interrupting the first phase will cancel the process.<br/>Interrupting the second phase will give you partial results.</html>");
        this.similar_btn.setToolTipText("<html><b>Minimize the number of enchants and gems required.</b><br/>Maintains approximately the current reforging.<br>The differences are computed relative to the last imported armory.<br/>Use Compare to Armory to highlight the differences.</html>");
        this.changeGems_check.setToolTipText("<html><b>Include gems in the reforging process.</b><br/>Gems with profession requirements are ignored.<br/>Ignores gems in frozen items.<br/><b>Warning:</b> this may slow down the reforging process.</html>");
        this.keepGemColors_check.setToolTipText("<html>Use existing gem colors to determine possible replacement gems.<br/>Enable if you have purposefully broken multiple socket bonuses.<br/>If a socket is empty, the socket color will be matched.<br/>You can manually clear sockets by editing your profile.</html>");
        this.canBreakBonuses_check.setToolTipText("<html>Consider additional gem configurations if breaking the bonus is better.<br/>Yellow gems are used when bonuses are broken.<br/>Assumes: 1 Agility = 2 Secondary.</html>");
        this.reforgeMax_depth_field.setToolTipText(Utils.tt_simple("Search Depth", "Number of unique Hit/Exp configurations to retain sorted by excess.", "Larger numbers slow down the reforging, but ensure sloppier reforgings aren't incorrectly ignored.", "Examples: 9000, 10k, 1m, 5m"));
    }
    
    void begin() {
        this.bindInterface();
        this.enableStuff(false);
        this.loadPrefs();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                WWFrame.this.savePrefs();
            }
        });
        this.setVisible(true);
    }
    
    void savePrefs() {
        try {
            Preferences node = WWFrame.PREFS.node("WindowState");
            final Point xy = this.getLocation();
            node.put("Location", String.format("%d %d", xy.x, xy.y));
            node.put("Tab", this.tabs.getSelectedTitle());
            node = WWFrame.PREFS.node("Recent");
            PrefHelp.silentClear(node);
            int index = 0;
            for (final RecentArmory x : this.recentList) {
                node.put(Integer.toString(index++), x.name + "/" + x.realm + "/" + x.region);
            }
            node = WWFrame.PREFS.node("ReforgeRune");
            node.put("HitValue", this.hit_field.getText());
            node.put("ExpValue", this.exp_field.getText());
            node.put("HitMode", this.hit_combo.exportPick());
            node.put("ExpMode", this.exp_combo.exportPick());
            node.put("ProcStat", this.stat_combo.exportPick());
            node.put("SearchRange", this.range_field.getText());
            node.put("Overflow", this.overflow_field.getText());
            node.put("Gap2", this.gap2_field.getText());
            node.put("Gap1", this.gap1_field.getText());
            node.putBoolean("EnchantBack", this.enchantBack_check.isSelected());
            node.putBoolean("EnchantHands", this.enchantHands_check.isSelected());
            node.putBoolean("EnchantFeet", this.enchantFeet_check.isSelected());
            node.putBoolean("ChangeGems", this.changeGems_check.isSelected());
            node.putBoolean("KeepColors", this.keepGemColors_check.isSelected());
            node.putBoolean("BreakBonus", this.canBreakBonuses_check.isSelected());
            node.put("GreenGems", this.green_combo.exportPick());
            node.put("OrangeGems", this.orange_combo.exportPick());
            node = WWFrame.PREFS.node("ReforgeMax");
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
            node.putBoolean("CritCap", this.reforgeMax_crit_check.isSelected());
            node.put("CritCrit", this.reforgeMax_crit_field.getText());
            node.putBoolean("CritStatBuff", this.reforgeMax_crit_statBuff_check.isSelected());
            node.putBoolean("CritCritBuff", this.reforgeMax_crit_statBuff_check.isSelected());
            node.put("CritAgi", this.reforgeMax_crit_agiBuff_field.getText());
            node = WWFrame.PREFS.node("Import");
            node.put("Char", this.char_field.getText());
            node.put("Region", this.region_combo.exportPick());
            WWFrame.PREFS.flush();
        }
        catch (BackingStoreException ex) {}
    }
    
    void loadPrefs() {
        Preferences node = WWFrame.PREFS.node("Import");
        this.char_field.setText(node.get("Char", "Iota"));
        this.region_combo.importPick(node.get("Region", null), RegionT.US);
        node = WWFrame.PREFS.node("Recent");
        this.recentList.clear();
        final HashSet<String> dups = new HashSet<String>();
        int index = 0;
        while (true) {
            final String val = node.get(Integer.toString(index), null);
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
        this.updateRecentCombo();
        node = WWFrame.PREFS.node("ReforgeRune");
        this.hit_field.setText(node.get("HitValue", "2550"));
        this.exp_field.setText(node.get("ExpValue", "2550"));
        this.hit_combo.importPick(node.get("HitMode", null), Reforger111.SearchMode.NEAR);
        this.exp_combo.importPick(node.get("ExpMode", null), Reforger111.SearchMode.NEAR);
        this.stat_combo.importPick(node.get("ProcStat", null), StatT.MASTERY);
        this.range_field.setText(node.get("SearchRange", "50"));
        this.overflow_field.setText(node.get("Overflow", "50"));
        this.gap1_field.setText(node.get("Gap1", "0"));
        this.gap2_field.setText(node.get("Gap2", "0"));
        this.enchantBack_check.setSelected(node.getBoolean("EnchantBack", true));
        this.enchantHands_check.setSelected(node.getBoolean("EnchantHands", true));
        this.enchantFeet_check.setSelected(node.getBoolean("EnchantFeet", false));
        this.changeGems_check.setSelected(node.getBoolean("ChangeGems", true));
        this.keepGemColors_check.setSelected(node.getBoolean("KeepColors", false));
        this.canBreakBonuses_check.setSelected(node.getBoolean("BreakBonus", false));
        this.green_combo.importPick(node.get("GreenGems", null), WWFrame.green_options[1]);
        this.orange_combo.importPick(node.get("OrangeGems", null), WWFrame.orange_options[0]);
        node = WWFrame.PREFS.node("ReforgeMax");
        this.reforgeMax_hit_field.setText(node.get("HitValue", "2550"));
        this.reforgeMax_exp_field.setText(node.get("ExpValue", "2550"));
        this.reforgeMax_range_field.setText(node.get("SearchRange", "100"));
        this.reforgeMax_depth_field.setText(node.get("SearchDepth", ""));
        for (int i = 0; i < this.reforgeMax_stats.length; ++i) {
            final StatT stat = this.reforgeMax_stats[i];
            this.reforgeMax_fields[i].setText(node.get(stat.abbr + "Weight", "0"));
        }
        this.reforgeMax_enchantBack_check.setSelected(node.getBoolean("EnchantBack", false));
        this.reforgeMax_enchantHands_check.setSelected(node.getBoolean("EnchantHands", false));
        this.reforgeMax_enchantFeet_check.setSelected(node.getBoolean("EnchantFeet", false));
        this.reforgeMax_changeGems_check.setSelected(node.getBoolean("ChangeGems", false));
        this.reforgeMax_breakBonus_check.setSelected(node.getBoolean("CanBreakBonuses", false));
        this.reforgeMax_leaveEmpty_check.setSelected(node.getBoolean("RespectNullGems", false));
        this.reforgeMax_crit_check.setSelected(node.getBoolean("CritCap", false));
        this.reforgeMax_crit_field.setText(node.get("CritCrit", "79%"));
        this.reforgeMax_crit_statBuff_check.setSelected(node.getBoolean("CritStatBuff", false));
        this.reforgeMax_crit_critBuff_check.setSelected(node.getBoolean("CritCritBuff", false));
        this.reforgeMax_crit_agiBuff_field.setText(node.get("CritAgi", "0"));
        node = WWFrame.PREFS.node("WindowState");
        this.tabs.setSelectedTitle(node.get("Tab", null));
        final String val2 = node.get("Location", null);
        if (val2 != null) {
            final String[] xywh = val2.split("\\s+");
            if (xywh.length == 2) {
                try {
                    final int x = Integer.parseInt(xywh[0]);
                    final int y = Integer.parseInt(xywh[1]);
                    this.setLocation(x, y);
                    return;
                }
                catch (NumberFormatException ex) {}
            }
        }
        this.setLocationRelativeTo(null);
    }
    
    void updateRecentCombo() {
        this.recent_combo_al.lock();
        this.recent_combo.removeAllItems();
        this.recent_combo.setVisible(!this.recentList.isEmpty());
        for (final RecentArmory x : this.recentList) {
            this.recent_combo.addItem(x);
        }
        this.recent_combo.addItem("Edit...");
        this.resetRecentCombo();
        this.recent_combo_al.unlock();
    }
    
    void resetRecentCombo() {
        this.recent_combo_al.lock();
        this.recent_combo.setEditable(true);
        this.recent_combo.setSelectedItem("Recent");
        this.recent_combo.setEditable(false);
        this.recent_combo_al.unlock();
    }
    
    private void enableStuff(final boolean b) {
        this.edit_btn.setEnabled(b);
        this.copyStats_btn.setEnabled(b);
        this.exportSimc_btn.setEnabled(b);
        this.compareArmory_btn.setEnabled(b);
        this.shoppingList_btn.setEnabled(b);
        this.askMrRobot_btn.setEnabled(b);
    }
    
    private void updateProcType() {
        final StatT stat = this.stat_combo.getPick();
        final Pair.Same<StatT> other = Reforger111.other(stat);
        final String stat2 = stat.abbr;
        final String stat3 = ((StatT)other.car).abbr;
        final String stat4 = ((StatT)other.cdr).abbr;
        this.overflow_field.setToolTipText(String.format("<html>The <b>maximum</b> amount of excess %1$s to consider.<br/>Constraint: %1$s &le; Overflow + (%1$s + %2$s + %3$s)/3</html>", stat2, stat3, stat4));
        this.gap1_lbl.setText(stat3 + " Gap: ");
        this.gap2_lbl.setText(stat4 + " Gap: ");
        final String fmt = "<html>The <b>minimum</b> amount of separation between %1$s and %2$s.<br/>Constraint: %1$s &gt; %2$s + Gap</html>";
        this.gap1_field.setName(stat3 + " Gap");
        this.gap2_field.setName(stat4 + " Gap");
        this.gap1_field.setToolTipText(String.format(fmt, stat2, stat3));
        this.gap2_field.setToolTipText(String.format(fmt, stat2, stat4));
    }
    
    private void bindInterface() {
        this.stat_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                WWFrame.this.updateProcType();
            }
        });
        this.showCache_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                Utils.openFile(WWFrame.this.hc.cacheDir);
            }
        });
        this.copyStats_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final StringBuilder sb = new StringBuilder();
                for (final StatT x : WWFrame.this.REFORGE_UNIVERSE) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(x.abbr);
                    sb.append(": ");
                    sb.append(WWFrame.this.trueStat(x));
                }
                sb.append('\n');
                for (final StatT x : WWFrame.this.REFORGE_UNIVERSE) {
                    sb.append('\n');
                    Fmt.padLeft(sb, x.abbr, 12);
                    sb.append(": ");
                    sb.append(WWFrame.this.trueStat(x));
                }
                new DialogText(WWFrame.this, "Export: Stats", "OK").showText(sb.toString());
            }
        });
        this.recent_combo.addActionListener(this.recent_combo_al);
        UI.onChange(this.changeGems_check, new Handler<JCheckBox>() {
            @Override
            public void handle(final JCheckBox x) {
                final boolean b = x.isSelected();
                WWFrame.this.keepGemColors_check.setEnabled(b);
                WWFrame.this.canBreakBonuses_check.setEnabled(b);
                WWFrame.this.orange_combo.setEnabled(b);
                WWFrame.this.green_combo.setEnabled(b);
            }
        });
        this.char_field.registerKeyboardAction(this.char_field.getActionForKeyStroke(KeyStroke.getKeyStroke(10, 0)), KeyStroke.getKeyStroke(10, 512), 0);
        this.char_field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                WWFrame.this.import_btn.doClick();
            }
        });
        this.armory_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final Profile p = WWFrame.this.importArmory(false);
                if (p == null) {
                    return;
                }
                try {
                    API.showArmory(p.charName, p.realmSlug, p.region);
                }
                catch (RuntimeException err) {
                    WWFrame.this.showError("Go to Armory Error", err);
                }
            }
        });
        this.import_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final Profile p = WWFrame.this.importArmory((ae.getModifiers() & 0x8) > 0);
                if (p == null) {
                    return;
                }
                p.validate();
                WWFrame.this.armoryProfile.importProfile(p);
                WWFrame.this.saveProfile.importProfile(p);
                WWFrame.this.enableStuff(true);
                WWFrame.this.char_field.setText(p.charName + " " + p.realmName + "!");
                WWFrame.this.saveProfileChanged();
            }
        });
        final ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                WWFrame.this.reforge_btn.doClick();
            }
        };
        this.hit_field.addActionListener(al);
        this.exp_field.addActionListener(al);
        this.range_field.addActionListener(al);
        this.overflow_field.addActionListener(al);
        this.gap1_field.addActionListener(al);
        this.gap2_field.addActionListener(al);
        this.exportSimc_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                new DialogText(WWFrame.this, "Export: SimulationCraft", "OK").showText(Simc.exportProfile(WWFrame.this.mainProfile));
            }
        });
        this.edit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final DialogText dt = new DialogText(WWFrame.this, "Edit: CompactGear", "Save");
                String code = CompactGear.toString(WWFrame.this.saveProfile, true, true);
                final Profile p = new Profile();
                p.race = WWFrame.this.saveProfile.race;
                p.spec = WWFrame.this.saveProfile.spec;
                p.profs = WWFrame.this.saveProfile.profs;
                final ArrayList<String> errors = new ArrayList<String>();
                while (true) {
                    code = dt.editText(code);
                    if (code == null) {
                        return;
                    }
                    if (CompactGear.fromString(WWFrame.this.api, p, code, errors, 0)) {
                        WWFrame.this.saveProfile.copySlots(p);
                        WWFrame.this.saveProfile.validate();
                        WWFrame.this.saveProfileChanged();
                        return;
                    }
                    WWFrame.this.showError_html("CompactGear Error", CompactGear.formatErrors_html(errors));
                }
            }
        });
        this.reforgeClear_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                WWFrame.this.mainProfile.clearReforges();
                WWFrame.this.updateAll();
            }
        });
        this.shoppingList_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final CompareGear cg = new CompareGear();
                if (cg.shoppingList(WWFrame.this.armoryProfile, WWFrame.this.mainProfile)) {
                    new DialogText(WWFrame.this, cg.title, "OK").showText(cg.message);
                }
                else {
                    WWFrame.this.showError(cg.title, cg.message);
                }
            }
        });
        this.reforgeExport_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                new DialogText(WWFrame.this, "Export: Reforgerade", "OK").showText(WWFrame.this.mainProfile.toReforgerade());
            }
        });
        this.askMrRobot_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final Profile p = WWFrame.this.prepareExport("AskMrRobot");
                if (p == null) {
                    return;
                }
                p.talents = API.getTalentStr(API.parseTalents(p.talents), 1);
                p.glyphs = "";
                p.copySlots(WWFrame.this.mainProfile);
                new DialogText(WWFrame.this, "Export: AskMrRobot", "OK").showWrappedText(p.toAskMrRobot());
            }
        });
        this.compareArmory_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final CompareGear cg = new CompareGear();
                if (cg.compare(WWFrame.this.armoryProfile, WWFrame.this.mainProfile)) {
                    new DialogText(WWFrame.this, cg.title, "OK").showText(cg.message);
                }
                else {
                    WWFrame.this.showError(cg.title, cg.message);
                }
            }
        });
        this.bounds_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final ReforgerBounds rb = new ReforgerBounds(WWFrame.this.REFORGE_UNIVERSE);
                final Set<SlotT> slotSet = WWFrame.this.getSlotSet();
                final Profile p = new Profile();
                p.importProfile(WWFrame.this.mainProfile);
                rb.update(p, slotSet);
                final StringBuilder sb = new StringBuilder(512);
                this.f(sb, rb);
                p.clearGems();
                p.clearEnchantsAndTinkers();
                rb.update(p, slotSet);
                sb.append("\n\nw/o Gems and Enchants:\n");
                this.f(sb, rb);
                new DialogText(WWFrame.this, "Reforge: Bounds", "OK").showText(sb.toString());
            }
            
            void f(final StringBuilder sb, final ReforgerBounds rb) {
                for (int i = 0; i < WWFrame.this.REFORGE_UNIVERSE.length; ++i) {
                    if (i > 0) {
                        sb.append('\n');
                    }
                    Fmt.padLeft(sb, WWFrame.this.REFORGE_UNIVERSE[i].abbr, 10);
                    Fmt.padLeft(sb, Integer.toString(rb.statMin[i]), 10);
                    Fmt.padLeft(sb, Integer.toString(rb.statMax[i]), 10);
                }
            }
        });
        this.similar_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                final StatT stat = WWFrame.this.stat_combo.getPick();
                final Pair.Same<StatT> other = Reforger111.other(stat);
                final int s0 = WWFrame.this.trueStat(stat);
                final int s2 = WWFrame.this.trueStat((StatT)other.car);
                final int s3 = WWFrame.this.trueStat((StatT)other.cdr);
                if (s2 >= s0 || s3 >= s0) {
                    WWFrame.this.showError("Not 1:1:1 Reforged", "The current profile is not properly reforged.  Please click Reforge to find a valid candidate for cost minimization.");
                    return;
                }
                final Reforger111 r = new Reforger111();
                r.hitTarget = WWFrame.this.trueStat(StatT.HIT);
                r.expTarget = WWFrame.this.trueStat(StatT.EXP);
                r.searchRange = 0;
                final int tol = 3;
                r.masteryOverflow = tol;
                r.hasteGap = Math.max(s0 - s2 - 1 - tol, 0);
                r.critGap = Math.max(s0 - s3 - 1 - tol, 0);
                r.removeDups = false;
                r.hitSearchMode = Reforger111.SearchMode.NEAR;
                r.expSearchMode = Reforger111.SearchMode.NEAR;
                WWFrame.this.continueReforge("Minimizing Cost", r);
            }
        });
        this.reforge_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                if (WWFrame.this.tabs.getSelectedIndex() == 0) {
                    final Reforger111 r = new Reforger111();
                    try {
                        r.hitTarget = UI.parse_nonNegInt(WWFrame.this.hit_field);
                        r.expTarget = UI.parse_nonNegInt(WWFrame.this.exp_field);
                        r.searchRange = UI.parse_nonNegInt(WWFrame.this.range_field);
                        r.masteryOverflow = UI.parse_nonNegInt(WWFrame.this.overflow_field);
                        r.hasteGap = UI.parse_nonNegInt(WWFrame.this.gap1_field);
                        r.critGap = UI.parse_nonNegInt(WWFrame.this.gap2_field);
                    }
                    catch (RuntimeException err) {
                        WWFrame.this.showError("Invalid Reforge Settings", err);
                        return;
                    }
                    r.hitSearchMode = WWFrame.this.hit_combo.getPick();
                    r.expSearchMode = WWFrame.this.exp_combo.getPick();
                    r.removeDups = true;
                    WWFrame.this.continueReforge("Reforging", r);
                }
                else {
                    final ReforgerMax r2 = new ReforgerMax();
                    try {
                        r2.hitTarget = UI.parse_nonNegInt(WWFrame.this.reforgeMax_hit_field);
                        r2.expTarget = UI.parse_nonNegInt(WWFrame.this.reforgeMax_exp_field);
                        r2.searchRange = UI.parse_nonNegInt(WWFrame.this.reforgeMax_range_field);
                        r2.searchDepth = (int)UI.parse_double(WWFrame.this.reforgeMax_depth_field, r2.searchDepth, 0.0, 5.0E7);
                        Arrays.fill(r2.weights, 0.0);
                        for (int i = 0; i < WWFrame.this.reforgeMax_stats.length; ++i) {
                            final StatT stat = WWFrame.this.reforgeMax_stats[i];
                            r2.weights[stat.index] = UI.parse_double(WWFrame.this.reforgeMax_fields[i], 0.0, -1000.0, 1000.0);
                        }
                        if (WWFrame.this.reforgeMax_critPane.isVisible() && WWFrame.this.reforgeMax_crit_check.isSelected()) {
                            final double perc = UI.parse_percent(WWFrame.this.reforgeMax_crit_field, Double.NaN, 0.0, 1.0, false);
                            final double critMod = 1.0;
                            final double agiMod = WWFrame.this.reforgeMax_crit_statBuff_check.isSelected() ? 1.05 : 1.0;
                            r2.critPerCrit = critMod / 60000.0;
                            r2.critPerAgi = agiMod / 125951.806640625;
                            final double meleeCritBase = 0.075 + ((WWFrame.this.mainProfile.race == RaceT.WORGEN) ? 0.01 : 0.0) + (WWFrame.this.reforgeMax_crit_critBuff_check.isSelected() ? 0.05 : 0.0) + (WWFrame.this.mainProfile.baseAndGearAndExtraStat(StatT.CRIT) + 0) * r2.critPerCrit + (WWFrame.this.mainProfile.baseAndGearAndExtraStat(StatT.AGI) + UI.parse_int(WWFrame.this.reforgeMax_crit_agiBuff_field)) * r2.critPerAgi;
                            r2.critMax = perc - meleeCritBase;
                        }
                    }
                    catch (RuntimeException err) {
                        WWFrame.this.showError("Invalid Reforge Settings", err);
                        return;
                    }
                    WWFrame.this.continueReforge("Reforging", r2);
                }
            }
        });
        UI.onChange(this.reforgeMax_changeGems_check, new Handler<JCheckBox>() {
            @Override
            public void handle(final JCheckBox x) {
                final boolean b = x.isSelected();
                WWFrame.this.reforgeMax_breakBonus_check.setEnabled(b);
                WWFrame.this.reforgeMax_leaveEmpty_check.setEnabled(b);
            }
        });
    }
    
    void saveProfileChanged() {
        this.mainProfile.importProfile(this.saveProfile);
        this.results_row.setVisible(false);
        this.packAndSize();
        final StringBuilder sb = new StringBuilder();
        final String sep = ", ";
        sb.append(' ');
        sb.append(this.saveProfile.spec.classType.name);
        sb.append(sep);
        sb.append(this.saveProfile.spec.name);
        sb.append(sep);
        sb.append(this.saveProfile.race);
        for (final ProfT x : ProfT.ALL) {
            if (x.isMemberOf(this.saveProfile.profs)) {
                sb.append(sep);
                sb.append(x.name);
            }
        }
        this.summary_lbl.setText(sb.toString());
        this.updateAll();
    }
    
    void continueReforge(final String title, final ReforgerBase r) {
        final long t = System.currentTimeMillis();
        r.slotSet = this.getSlotSet();
        if (r instanceof Reforger111) {
            final Reforger111 temp = (Reforger111)r;
            temp.procStat = this.stat_combo.getPick();
            temp.enchantHands = this.enchantHands_check.isSelected();
            temp.enchantBack = this.enchantBack_check.isSelected();
            temp.enchantFeet = this.enchantFeet_check.isSelected();
            temp.changeGems = this.changeGems_check.isSelected();
            temp.canBreakBonuses = this.canBreakBonuses_check.isSelected();
            temp.keepGemColors = this.keepGemColors_check.isSelected();
            this.orange_combo.getPick().apply(temp);
            this.green_combo.getPick().apply(temp);
        }
        else {
            final ReforgerMax temp2 = (ReforgerMax)r;
            temp2.enchantHands = this.reforgeMax_enchantHands_check.isSelected();
            temp2.enchantBack = this.reforgeMax_enchantBack_check.isSelected();
            temp2.enchantFeet = this.reforgeMax_enchantFeet_check.isSelected();
            temp2.changeGems = this.reforgeMax_changeGems_check.isSelected();
            temp2.canBreakBonuses = this.reforgeMax_breakBonus_check.isSelected();
        }
        final DialogProg pd = new DialogProg(this, title);
        r.progBar = pd.proxy;
        pd.setAbortOnClose(r.abort);
        try {
            pd.execute(new Runnable() {
                @Override
                public void run() {
                    r.reforge(WWFrame.this.api, WWFrame.this.saveProfile, WWFrame.this.armoryProfile);
                }
            });
        }
        catch (RuntimeException err) {
            if (!r.abort.get()) {
                this.showError("Reforge Error", err);
                err.printStackTrace();
            }
            return;
        }
        this.results_combo_al.lock();
        this.results_combo.removeAllItems();
        for (final ProfilePerm x : r.results) {
            this.results_combo.addItem(x);
        }
        this.results_combo_al.unlock();
        this.results_combo.setSelectedItem(r.results[r.resultBestIndex]);
        this.results_row.setVisible(true);
        this.reforgeTime_lbl.setText(title + " took " + Fmt.msDur_since(t));
        this.reforgeTime_lbl.setVisible(true);
        this.timer.restart();
        this.packAndSize();
    }
    
    Profile importArmory(final boolean force) {
        final String input = this.char_field.getText().trim();
        if (input.isEmpty()) {
            return null;
        }
        final int pos = input.indexOf(" ");
        String name;
        String rest;
        if (pos == -1) {
            name = input;
            rest = "";
        }
        else {
            name = input.substring(0, pos);
            rest = input.substring(pos + 1).trim();
        }
        final RegionT region = (RegionT)this.region_combo.getSelectedItem();
        String realmSlug;
        if (rest.isEmpty()) {
            final DialogProg pd = new DialogProg(this, "Searching");
            Pair<ClassT, Realm>[] matches;
            try {
                matches = (Pair<ClassT, Realm>[])pd.compute((DialogProg.Getter<Pair[]>)new DialogProg.Getter<Pair<ClassT, Realm>[]>() {
                    @Override
                    public Pair<ClassT, Realm>[] get() {
                        return WWFrame.this.api.findChar(name, region, force, ClassT.MONK, ClassT.SHAMAN, ClassT.ROGUE, ClassT.HUNTER);
                    }
                });
            }
            catch (RuntimeException err) {
                this.showError("Search Failed", err);
                return null;
            }
            if (matches.length > 1) {
                final Realm realm = new RealmChooser(this, matches, true).getRealm();
                if (realm == null) {
                    return null;
                }
                realmSlug = realm.slug;
            }
            else {
                realmSlug = matches[0].cdr.slug;
            }
        }
        else {
            realmSlug = this.api.properRealmSlug(region, rest);
        }
        final DialogProg pd = new DialogProg(this, "Importing from Armory" + (force ? " (Forced)" : ""));
        Profile p;
        try {
            p = pd.compute((DialogProg.Getter<Profile>)new DialogProg.Getter<Profile>() {
                @Override
                public Profile get() {
                    return WWFrame.this.api.loadChar(name, realmSlug, region, null, 90, -1, force);
                }
            });
        }
        catch (RuntimeException err) {
            this.showError("Import Failed", err.getMessage());
            return null;
        }
        while (true) {
            for (final RecentArmory x : this.recentList) {
                if (x.name.equals(p.charName) && x.realm.equals(p.realmName) && x.region == p.region) {
                    x.time = System.currentTimeMillis();
                    Collections.sort(this.recentList, RecentArmory.CMP_TIME);
                    this.updateRecentCombo();
                    return p;
                }
            }
            this.recentList.add(new RecentArmory(p.charName, p.realmName, p.region, System.currentTimeMillis()));
            continue;
        }
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
        if (!this.checkItemLevels(why)) {
            return null;
        }
        return p;
    }
    
    private boolean checkItemLevels(final String why) {
        for (final Profile.Slot x : this.mainProfile.SLOTS) {
            if (x.getEffectiveUpgradeLevel(this.mainProfile.region.asia) < 0) {
                this.showError("Export: " + why, String.format("Your %s: %s is using an unobtainable item level (%+d).", x.type.name, x.getItemName(true), x.getItemLevelDelta()));
                return false;
            }
        }
        return true;
    }
    
    void updateAll() {
        this.code_ta.setText(CompactGear.toString(this.mainProfile));
        this.code_ta.select(0, 0);
        this.updateUI();
    }
    
    void packAndSize() {
        this.setMinimumSize(null);
        this.pack();
        this.setMinimumSize(this.getSize());
    }
    
    HashSet<SlotT> getSlotSet() {
        final HashSet<SlotT> set = new HashSet<SlotT>();
        for (final ReforgeToggle x : this.reforge_toggles) {
            if (x.isSelected()) {
                set.add(x.slot);
            }
        }
        return set;
    }
    
    void updateUI() {
        for (int i = 0; i < this.reforge_toggles.length; ++i) {
            final JCheckBox toggle = this.reforge_toggles[i];
            final Profile.Slot s = this.mainProfile.SLOTS[i];
            toggle.setEnabled(s.canReforge(this.REFORGE_UNIVERSE));
            toggle.setForeground((s.reforge == null) ? null : WWFrame.DARK_GREEN);
        }
        final int reforgeCount = this.mainProfile.getReforgedCount();
        this.reforgeClear_btn.setEnabled(reforgeCount > 0);
        final boolean naked = this.mainProfile.isNaked();
        this.bounds_btn.setEnabled(!naked);
        this.reforge_btn.setEnabled(!naked);
        this.similar_btn.setEnabled(!naked && this.tabs.getSelectedIndex() == 0);
        this.reforgeExport_btn.setEnabled(!naked);
        this.reforgeMax_critPane.setVisible(this.mainProfile.spec == SpecT.BREW);
        final LinkedList<String> errors = new LinkedList<String>();
        final int emptySlotCount = this.mainProfile.getEmptySlotCount();
        if (emptySlotCount == 1) {
            System.out.println(this.mainProfile.MH.item.equipType);
            errors.add("1 empty slot");
        }
        else if (emptySlotCount > 1) {
            errors.add(emptySlotCount + " empty slots");
        }
        final int emptySocketCount = this.mainProfile.getSocketCount() - this.mainProfile.getGemCount();
        if (emptySocketCount == 1) {
            errors.add("1 empty socket");
        }
        else if (emptySocketCount > 1) {
            errors.add(emptySocketCount + " empty sockets");
        }
        final int emptyEnchantCount = this.mainProfile.getEmptyEnchantCount();
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
        if (!this.mainProfile.hasArmorSpecialization()) {
            errors.add("Leather Specialization not satisfied");
        }
        final boolean audit = !naked && !errors.isEmpty();
        if (audit) {
            this.audit_lbl.setText("Warning: " + Fmt.join(errors, ", "));
        }
        if (audit != this.audit_lbl.isVisible()) {
            this.audit_lbl.setVisible(audit);
            this.packAndSize();
        }
        final int hit = this.trueStat(StatT.HIT);
        final int exp = this.trueStat(StatT.EXP);
        final int mr = this.trueStat(StatT.MASTERY);
        final int hr = this.trueStat(StatT.HASTE);
        final int cr = this.trueStat(StatT.CRIT);
        this.hit_lbl.setText(Integer.toString(hit));
        this.exp_lbl.setText(Integer.toString(exp));
        this.mr_lbl.setText(Integer.toString(mr));
        this.hr_lbl.setText(Integer.toString(hr));
        this.cr_lbl.setText(Integer.toString(cr));
        this.mr_lbl.setForeground((mr > Math.max(hr, cr)) ? WWFrame.DARK_GREEN : WWFrame.DARK_RED);
        this.hr_lbl.setForeground((hr > Math.max(mr, cr)) ? WWFrame.DARK_GREEN : WWFrame.DARK_RED);
        this.cr_lbl.setForeground((cr > Math.max(hr, mr)) ? WWFrame.DARK_GREEN : WWFrame.DARK_RED);
    }
    
    int trueStat(final StatT stat) {
        final int sum = this.mainProfile.gearAndExtraStat(stat);
        return sum;
    }
    
    void showError(final String title, final Exception err) {
        this.showError(title, Fmt.exception(err));
    }
    
    void showError(final String title, final String msg) {
        this.showError_html(title, UI.wrapAsHtml(msg));
    }
    
    void showError_html(final String title, final String html) {
        JOptionPane.showMessageDialog(this, html, title, 0);
    }
    
    static {
        PREFS = Preferences.userRoot().node("com/antistupid/zephyrus");
        orange_options = new NamedReforgerConfig[] { new NamedReforgerConfig("Agility Only") {
                @Override
                void apply(final Reforger111 r) {
                    r.ww_orange_useAgi = true;
                    r.ww_orange_useExp = false;
                }
            }, new NamedReforgerConfig("Agility and Expertise") {
                @Override
                void apply(final Reforger111 r) {
                    r.ww_orange_useAgi = true;
                    r.ww_orange_useExp = true;
                }
            }, new NamedReforgerConfig("Expertise Only") {
                @Override
                void apply(final Reforger111 r) {
                    r.ww_orange_useAgi = false;
                    r.ww_orange_useExp = true;
                }
            } };
        green_options = new NamedReforgerConfig[] { new NamedReforgerConfig("Hit Only") {
                @Override
                void apply(final Reforger111 r) {
                    r.ww_green_useHit = true;
                    r.ww_green_useSta = false;
                }
            }, new NamedReforgerConfig("Hit and Stamina") {
                @Override
                void apply(final Reforger111 r) {
                    r.ww_green_useHit = true;
                    r.ww_green_useSta = true;
                }
            }, new NamedReforgerConfig("Stamina Only") {
                @Override
                void apply(final Reforger111 r) {
                    r.ww_green_useHit = false;
                    r.ww_green_useSta = true;
                }
            } };
        DARK_RED = new Color(128, 0, 0);
        DARK_GREEN = new Color(0, 128, 0);
    }
    
    abstract static class NamedReforgerConfig extends NamedThing
    {
        NamedReforgerConfig(final String name) {
            super(name);
        }
        
        abstract void apply(final Reforger111 p0);
    }
    
    static class ReforgeToggle extends JCheckBox
    {
        final SlotT slot;
        
        ReforgeToggle(final SlotT slot) {
            super(slot.name);
            this.slot = slot;
            this.setFocusable(false);
            this.setFont(this.getFont().deriveFont(10.0f));
            this.setSelected(true);
        }
    }
}
