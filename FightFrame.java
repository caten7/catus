// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.HashMap;
import java.awt.Font;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.Container;
import javax.swing.JComponent;
import java.awt.AWTEvent;
import java.awt.Window;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import java.awt.Component;
import java.awt.SystemColor;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.SwingUtilities;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class FightFrame extends JFrame
{
    final FeralSim simA;
    final FeralSim simB;
    final JLabel major_a;
    final JLabel major_b;
    final JLabel delta_a;
    final JLabel delta_b;
    final JLabel error_a;
    final JLabel error_b;
    final JLabel perc_a;
    final JLabel perc_b;
    final UIPanel_GridBag body;
    final UIPanel_GridBag col_a;
    final UIPanel_GridBag col_b;
    final int iter;
    final boolean quantify;
    final int minIter;
    final JProgressBar progBar;
    final String title0;
    final double tt;
    private final Thread worker;
    
    public FightFrame(final String title0, final FeralSim simA, final FeralSim simB, final int iter0) {
        this.worker = new Thread() {
            boolean update(final double[] arrA, final double[] arrB, final int cur) {
                final double dpsA = Maths.mean(arrA, cur);
                final double dpsB = Maths.mean(arrB, cur);
                final double varA = Maths.var(arrA, cur, dpsA);
                final double varB = Maths.var(arrB, cur, dpsB);
                final double errA = Math.sqrt(varA / cur);
                final double errB = Math.sqrt(varB / cur);
                final double s = Math.sqrt((varA + varB) / cur);
                final double t = (dpsA - dpsB) / s;
                final double d = Math.pow((varA + varB) / cur, 2.0) * (cur - 1) / (Math.pow(varA / cur, 2.0) + Math.pow(varB / cur, 2.0));
                final String percA = String.format("%+.2f%%", 100.0 * (dpsA - dpsB) / (0.5 * (dpsA + dpsB)));
                final String percB = String.format("%+.2f%%", 100.0 * (dpsB - dpsA) / (0.5 * (dpsA + dpsB)));
                final String majorA = Fmt.bigNum(dpsA);
                final String majorB = Fmt.bigNum(dpsB);
                final String errorA = "±" + Fmt.bigNum(errA);
                final String errorB = "±" + Fmt.bigNum(errB);
                final String deltaA = Fmt.bigNum_signed(dpsA - dpsB);
                final String deltaB = Fmt.bigNum_signed(dpsB - dpsA);
                Color colorA;
                Color colorB;
                if (t > FightFrame.this.tt) {
                    colorA = CatusFrame.DARK_GREEN;
                    colorB = CatusFrame.DARK_RED;
                }
                else if (-t > FightFrame.this.tt) {
                    colorA = CatusFrame.DARK_RED;
                    colorB = CatusFrame.DARK_GREEN;
                }
                else {
                    colorA = null;
                    colorB = null;
                }
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        FightFrame.this.perc_a.setText(percA);
                        FightFrame.this.perc_b.setText(percB);
                        FightFrame.this.major_a.setText(majorA);
                        FightFrame.this.major_b.setText(majorB);
                        FightFrame.this.error_a.setText(errorA);
                        FightFrame.this.error_b.setText(errorB);
                        FightFrame.this.delta_a.setText(deltaA);
                        FightFrame.this.delta_b.setText(deltaB);
                        FightFrame.this.perc_a.setForeground(colorA);
                        FightFrame.this.perc_b.setForeground(colorB);
                        FightFrame.this.major_a.setForeground(colorA);
                        FightFrame.this.major_b.setForeground(colorB);
                        FightFrame.this.error_a.setForeground(colorA);
                        FightFrame.this.error_b.setForeground(colorB);
                        FightFrame.this.delta_a.setForeground(colorA);
                        FightFrame.this.delta_b.setForeground(colorB);
                        if (FightFrame.this.quantify && cur > FightFrame.this.minIter) {
                            FightFrame.this.progBar.setIndeterminate(true);
                        }
                        else {
                            FightFrame.this.progBar.setValue(cur);
                        }
                    }
                });
                return FightFrame.this.quantify && cur >= FightFrame.this.minIter && Math.abs(t) > FightFrame.this.tt;
            }
            
            @Override
            public void run() {
                final long t0 = System.currentTimeMillis();
                final int freq = 50;
                FightFrame.this.simA.dist_prep();
                FightFrame.this.simB.dist_prep();
                long next = t0 + 50L;
                int completed = FightFrame.this.iter;
                final double[] arrA = new double[FightFrame.this.iter];
                final double[] arrB = new double[FightFrame.this.iter];
                for (int i = 0; i < FightFrame.this.iter; ++i) {
                    FightFrame.this.simA.runSim();
                    FightFrame.this.simB.runSim();
                    arrA[i] = FightFrame.this.simA.damage_total / (FightFrame.this.simA.clock / 1000.0);
                    arrB[i] = FightFrame.this.simB.damage_total / (FightFrame.this.simB.clock / 1000.0);
                    final long t2 = System.currentTimeMillis();
                    if (t2 > next) {
                        next = t2 + 50L;
                        if (this.update(arrA, arrB, i + 1)) {
                            completed = i + 1;
                            break;
                        }
                        if (Thread.interrupted()) {
                            FightFrame.this.dispose();
                            return;
                        }
                    }
                }
                this.update(arrA, arrB, completed);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        FightFrame.this.progBar.setVisible(false);
                        FightFrame.this.setTitle(FightFrame.this.title0);
                        FightFrame.this.pack();
                    }
                });
            }
        };
        this.simA = simA;
        this.simB = simB;
        this.title0 = title0;
        final Font bigFont = CatusFrame.normalFont.deriveFont(24.0f);
        (this.major_a = new JLabel("?")).setHorizontalAlignment(0);
        this.major_a.setFont(bigFont);
        (this.major_b = new JLabel("?")).setHorizontalAlignment(0);
        this.major_b.setFont(bigFont);
        this.delta_a = new JLabel("+3");
        this.delta_b = new JLabel("+3");
        this.perc_a = new JLabel("?");
        this.perc_b = new JLabel("?");
        (this.error_a = new JLabel("0")).setEnabled(false);
        (this.error_b = new JLabel("0")).setEnabled(false);
        (this.progBar = new JProgressBar()).setMinimum(0);
        this.progBar.setValue(0);
        if (iter0 == -3) {
            this.iter = 10000000;
            this.minIter = 1000;
            this.progBar.setMaximum(this.minIter);
            this.quantify = true;
            this.tt = 2.57632;
            this.setTitle(title0 + " (Quantify)");
        }
        else {
            this.tt = 0.0;
            this.iter = iter0;
            this.minIter = 0;
            this.quantify = false;
            this.progBar.setMaximum(this.iter);
            this.setTitle(title0 + " (" + Fmt.bigNum(iter0) + ")");
        }
        final HashMap<String, String> mapA = this.statArr(simA);
        final HashMap<String, String> mapB = this.statArr(simB);
        final UIPanel statGridA = new UIPanel();
        final UIPanel statGridB = new UIPanel();
        statGridA.setLayout(new GridLayout(0, 2));
        statGridB.setLayout(new GridLayout(0, 2));
        final HashSet<String> set = new HashSet<String>(mapA.keySet());
        set.addAll((Collection<?>)mapB.keySet());
        final String[] keys = set.toArray(new String[set.size()]);
        Arrays.sort(keys);
        for (String key : keys) {
            final String valA = mapA.get(key);
            final String valB = mapB.get(key);
            if (valA != null || valB != null) {
                if (valA == null || valB == null || !valA.equals(valB)) {
                    key = key.substring(1);
                    final JLabel lblA = new JLabel(key);
                    final JLabel lblB = new JLabel(key);
                    lblA.setHorizontalAlignment(2);
                    lblB.setHorizontalAlignment(2);
                    lblA.setForeground(SystemColor.textInactiveText);
                    lblB.setForeground(SystemColor.textInactiveText);
                    statGridA.add(lblA);
                    statGridA.add(new JLabel((valA == null) ? "" : valA));
                    statGridB.add(lblB);
                    statGridB.add(new JLabel((valB == null) ? "" : valB));
                }
            }
        }
        final int pad = 4;
        final int gap = 8;
        this.col_a = new UIPanel_GridBag();
        UIPanel_GridBag row = new UIPanel_GridBag();
        row.add(this.delta_a);
        row.gap_add(8, this.major_a);
        row.gap_add(8, this.error_a);
        this.col_a.row(row, false, 0);
        this.col_a.row(this.perc_a, false, 0);
        this.col_a.row(UI.L(simA.simName, CatusFrame.boldFont), false, 4);
        this.col_a.row(statGridA, true, 4);
        this.col_a.row(Box.createHorizontalStrut(250), true, 0);
        this.col_b = new UIPanel_GridBag();
        row = new UIPanel_GridBag();
        row.add(this.delta_b);
        row.gap_add(8, this.major_b);
        row.gap_add(8, this.error_b);
        this.col_b.row(row, false, 0);
        this.col_b.row(this.perc_b, false, 0);
        this.col_b.row(UI.L(simB.simName, CatusFrame.boldFont), false, 4);
        this.col_b.row(statGridB, true, 4);
        final UIPanel grid = new UIPanel();
        grid.setLayout(new GridLayout(1, 2, 20, 0));
        grid.add(this.col_a);
        grid.add(this.col_b);
        (this.body = new UIPanel_GridBag()).pad(8);
        this.body.row(grid, true, 0);
        this.body.row(this.progBar, true, 4);
        UI.onShortcut(this.body, 87, "Close", new Runnable() {
            @Override
            public void run() {
                FightFrame.this.dispatchEvent(new WindowEvent(FightFrame.this, 201));
            }
        });
        this.setContentPane(this.body);
        this.body.invalidate();
        this.body.validate();
        this.pack();
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent we) {
                FightFrame.this.worker.interrupt();
            }
        });
    }
    
    private HashMap<String, String> statArr(final FeralSim sim) {
        final HashMap map = new HashMap();
        map.put("0Agility", Integer.toString(sim.getAgi()));
        map.put("0Strength", Integer.toString(sim.getStr()));
        map.put("0AP", Integer.toString(sim.getAP()));
        map.put("1Exp", String.format("%.2f%% (%d)", sim.getMeleeExp() * 100.0, sim.getMeleeExpRating()));
        map.put("1Hit", String.format("%.2f%% (%d)", sim.getMeleeHit() * 100.0, sim.getMeleeHitRating()));
        map.put("2Mastery", String.format("%.2f%% (%+d)", (sim.getRazorClawsMod() - 1.0) * 100.0, sim.getMasteryRating()));
        map.put("2Crit", String.format("%.2f%% (%+d)", sim.getMeleeCrit() * 100.0, sim.getCritRating()));
        map.put("2Haste", String.format("%+.2f%% (%+d)", (sim.getMeleeHasteMod() - 1.0) * 100.0, sim.getHasteRating()));
        map.put("2Swing", String.format("+%.2f%% (%.3f)", (sim.getMeleeSpeedMod() - 1.0) * 100.0, sim.swingTime() / 1000.0));
        map.put("3HP", Integer.toString(sim.getMaxHP()));
        map.put("3Stamina", Integer.toString(sim.getSta()));
        map.put("4Intellect", Integer.toString(sim.getInt()));
        map.put("4SP", Integer.toString(sim.getSP()));
        map.put("4SP Nature", Integer.toString(sim.getSP_nature()));
        map.put("4Spell Crit", String.format("%.2f%%", sim.getSpellCrit() * 100.0));
        map.put("4Spell Haste", String.format("%+.2f%%", (sim.getSpellHasteMod() - 1.0) * 100.0));
        map.put("5Talents", sim.spec.talentStr());
        map.put("5Race", sim.mainProfile.race.name);
        map.put("5Profs", ProfT.fmt(sim.mainProfile.profs, true));
        map.put("5Item Level", String.format("%.2f", sim.mainProfile.avgItemLevel()));
        map.put("5Trinkets", sim.mainProfile.T1.getItemAbbrOrNone() + " / " + sim.mainProfile.T2.getItemAbbrOrNone());
        return (HashMap<String, String>)map;
    }
    
    public void go() {
        this.worker.start();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FightFrame.this.setVisible(true);
                FightFrame.this.setResizable(false);
            }
        });
    }
}
