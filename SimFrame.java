// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Comparator;
import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import java.io.IOException;
import java.io.Closeable;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import javax.swing.JComponent;
import java.awt.AWTEvent;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.Container;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JFrame;

public class SimFrame extends JFrame
{
    private static final AtomicInteger _seq;
    final int seq;
    final FeralSim sim;
    final File reportFile;
    final File csvFile;
    final JProgressBar prog;
    final JLabel status_label;
    final JLabel time_label;
    final JTextField name_field;
    final UIComboBox<NamedThing.Str> name_combo;
    final UIPanel_GridBag name_row;
    final JButton done_btn;
    final JButton pause_btn;
    final AtomicInteger state;
    static final int STATE_NORMAL = 0;
    static final int STATE_KILL = 1;
    static final int STATE_PAUSED = 2;
    static final int STATE_DONE = 3;
    final Object guard;
    LockableActionListener name_combo_al;
    
    public SimFrame(final String configName, final FeralSim sim0, final File reportFile, final File csvFile) {
        this.seq = SimFrame._seq.incrementAndGet();
        this.state = new AtomicInteger();
        this.guard = new Object();
        this.name_combo_al = new LockableActionListener() {
            @Override
            public void action(final ActionEvent ae) {
                SimFrame.this.name_field.setText((String)SimFrame.this.name_combo.getPick().obj);
                SimFrame.this.name_field.setSelectionStart(0);
                SimFrame.this.name_field.setSelectionEnd(0);
                SimFrame.this.resetNameCombo();
            }
        };
        this.sim = sim0;
        this.reportFile = reportFile;
        this.csvFile = csvFile;
        this.setTitle("Simulation: " + configName);
        this.status_label = new JLabel("Estimating...");
        this.time_label = new JLabel("");
        this.name_field = new JTextField();
        final String defaultName = configName + "#" + this.seq;
        this.name_field.setText(defaultName);
        this.prog = new JProgressBar();
        (this.pause_btn = UI.makeButton("Pause")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                if (SimFrame.this.state.get() == 2) {
                    SimFrame.this.state.set(0);
                    synchronized (SimFrame.this.guard) {
                        SimFrame.this.guard.notify();
                    }
                }
                else {
                    SimFrame.this.state.set(2);
                }
                SimFrame.this.pause_btn.setEnabled(false);
            }
        });
        (this.done_btn = UI.makeButton("Done")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                if (SimFrame.this.state.get() == 2) {
                    SimFrame.this.state.set(3);
                    synchronized (SimFrame.this.guard) {
                        SimFrame.this.guard.notify();
                    }
                }
                else {
                    SimFrame.this.state.set(3);
                }
            }
        });
        this.sim.resetAll();
        (this.name_combo = new UIComboBox<NamedThing.Str>()).addItem(new NamedThing.Str("Default", defaultName));
        this.name_combo.addItem(new NamedThing.Str("Config", configName));
        final StringBuilder sb = new StringBuilder();
        if (this.sim.spec.talent_sotf) {
            Fmt.append(sb, "SotF");
        }
        if (this.sim.spec.talent_fon) {
            Fmt.append(sb, "FoN");
        }
        if (this.sim.spec.talent_kotj) {
            Fmt.append(sb, "KotJ");
        }
        if (this.sim.spec.talent_doc) {
            Fmt.append(sb, "DoC");
        }
        if (this.sim.spec.talent_hotw) {
            Fmt.append(sb, "HotW");
            if (this.sim.cfg.hotw_wrath) {
                sb.append("/Wrath");
            }
            if (this.sim.cfg.hotw_hurricane) {
                sb.append("/Hurricane");
            }
            if (this.sim.cfg.hotw_swap) {
                sb.append("/Swap");
            }
            if (this.sim.cfg.hotw_bitw) {
                sb.append("/BitW");
            }
            if (this.sim.cfg.hotw_beforeBerserk) {
                sb.append("/Before");
            }
        }
        if (this.sim.spec.talent_nv) {
            Fmt.append(sb, "NV");
        }
        this.name_combo.addItem(new NamedThing.Str("Talents", sb.toString()));
        this.name_combo.addItem(new NamedThing.Str("Reforge", this.sim.mainProfile.statStr(FeralSpec.REFORGE_STATS)));
        this.name_combo.addItem(new NamedThing.Str("Trinkets", this.sim.mainProfile.T1.getItemNameOrEmpty() + " / " + this.sim.mainProfile.T2.getItemNameOrEmpty()));
        this.name_combo.addItem(new NamedThing.Str("Cat Stats", String.format("%d AP, %d Agility, %.2f%% Mastery, %.2f%% Crit, %.2f%% Haste, %.2f%% Hit, %.2f%% Exp", this.sim.getAP(), this.sim.getAgi(), (this.sim.getRazorClawsMod() - 1.0) * 100.0, this.sim.getMeleeCrit() * 100.0, (this.sim.getMeleeHasteMod() - 1.0) * 100.0, this.sim.getMeleeHit() * 100.0, this.sim.getMeleeExp() * 100.0)));
        if (this.sim.mainProfile.hasTwoHander()) {
            this.name_combo.addItem(new NamedThing.Str("Weapon", this.sim.mainProfile.MH.getItemNameOrEmpty()));
        }
        else {
            this.name_combo.addItem(new NamedThing.Str("Weapon", this.sim.mainProfile.MH.getItemNameOrEmpty() + " + " + this.sim.mainProfile.OH.getItemNameOrEmpty()));
        }
        this.name_combo.addItem(new NamedThing.Str("Custom Stats", this.sim.cfg.extra_active.toString()));
        this.name_combo.addItem(new NamedThing.Str("Item Level", String.format("%.2f", this.sim.mainProfile.avgItemLevel())));
        this.name_combo.addItem(new NamedThing.Str("Sim Number", "#" + this.seq));
        this.name_combo.addItem(new NamedThing.Str("Gear Hash", Utils.sha1(CompactGear.toString(this.sim.mainProfile, true, true))));
        this.name_combo.setPrototypeDisplayValue("Name");
        this.name_combo.setWide(true);
        this.name_combo.addActionListener(this.name_combo_al);
        this.resetNameCombo();
        final UIPanel_GridBag body = new UIPanel_GridBag();
        body.pad(6);
        final int pad = 4;
        (this.name_row = new UIPanel_GridBag()).add(this.name_combo);
        this.name_row.spacer(this.name_field);
        body.row(this.name_row, true, 0);
        body.row(this.prog, true, 4);
        UIPanel_GridBag row = new UIPanel_GridBag();
        row.add(this.status_label);
        row.spacer();
        row.add(this.time_label);
        body.row(row, true, 4);
        row = new UIPanel_GridBag();
        row.add(this.done_btn);
        row.spacer();
        row.add(this.pause_btn);
        body.row(row, true, 4);
        this.setContentPane(body);
        UI.onShortcut(body, 87, "Close", new Runnable() {
            @Override
            public void run() {
                SimFrame.this.dispatchEvent(new WindowEvent(SimFrame.this, 201));
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent we) {
                if (SimFrame.this.state.get() == 2) {
                    synchronized (SimFrame.this.guard) {
                        SimFrame.this.guard.notify();
                    }
                }
                SimFrame.this.state.set(1);
            }
        });
    }
    
    private void goVisible0() {
        this.pack();
        this.setSize(new Dimension(500, this.getHeight()));
        this.setSize(new Dimension(500, this.getHeight()));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }
    
    public void goVisible() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SimFrame.this.goVisible0();
            }
        });
    }
    
    private void resetNameCombo() {
        this.name_combo_al.lock();
        this.name_combo.setEditable(true);
        this.name_combo.setSelectedItem(this.name_combo.getPrototypeDisplayValue());
        this.name_combo.setEditable(false);
        this.name_combo_al.unlock();
    }
    
    public void random(final int iter0, final int delta, final boolean inert, final StatT[] stats) {
        this.name_row.setVisible(false);
        this.prog.setMinimum(0);
        this.prog.setValue(0);
        this.prog.setMaximum(iter0);
        new Thread() {
            @Override
            public void run() {
                final long freq = 50L;
                long resumed;
                final long started = resumed = System.currentTimeMillis();
                long elapsed = 0L;
                long next = started + 50L;
                SimFrame.this.sim.dist_prep();
                SimFrame.this.sim.saveDamage = false;
                int iter = iter0;
                final double[] dpsArr = new double[iter];
                final int[][] delMat = new int[iter][stats.length];
                final int[] deltas = new int[StatT.NUM];
                final int[] extra0 = SimFrame.this.sim.inertStats.clone();
                final int[] gear0 = SimFrame.this.sim.mainGear.stats.clone();
                final int[] swap0 = (int[])((SimFrame.this.sim.mainGear != SimFrame.this.sim.swapGear) ? ((int[])SimFrame.this.sim.swapGear.stats.clone()) : null);
                double acc0 = 0.0;
                final Random rng = new Random();
            Label_0830:
                for (int curIter = 0; curIter < iter; ++curIter) {
                    final int[] delArr = delMat[curIter];
                    for (int i = 0; i < stats.length; ++i) {
                        final StatT stat = stats[i];
                        final int amt = delta - rng.nextInt(2 * delta + 1);
                        if (stat == null) {
                            deltas[FeralSim.STAT_MASTERY] = amt;
                            deltas[FeralSim.STAT_HASTE] = amt;
                            deltas[FeralSim.STAT_CRIT] = amt;
                        }
                        else {
                            deltas[FeralSim.STAT_MAP.get(stat)] = amt;
                        }
                        delArr[i] = amt;
                    }
                    if (inert) {
                        StatT.addTo(SimFrame.this.sim.inertStats, deltas);
                    }
                    StatT.addTo(SimFrame.this.sim.mainGear.stats, deltas);
                    if (swap0 != null) {
                        StatT.addTo(SimFrame.this.sim.swapGear.stats, deltas);
                    }
                    SimFrame.this.sim.runSim();
                    dpsArr[curIter] = SimFrame.this.sim.getSimDPS();
                    System.arraycopy(extra0, 0, SimFrame.this.sim.inertStats, 0, extra0.length);
                    System.arraycopy(gear0, 0, SimFrame.this.sim.mainGear.stats, 0, gear0.length);
                    if (swap0 != null) {
                        System.arraycopy(swap0, 0, SimFrame.this.sim.swapGear.stats, 0, swap0.length);
                    }
                    SimFrame.this.sim.runSim();
                    acc0 += SimFrame.this.sim.getSimDPS();
                    final long t = System.currentTimeMillis();
                    if (t > next) {
                        next = t + 50L;
                        final int n = curIter + 1;
                        String status_text;
                        if (curIter < 10) {
                            status_text = "Estimating...";
                        }
                        else {
                            final double mean = Maths.mean(dpsArr, n);
                            final double serr = Maths.stdev(dpsArr, n, mean) / Math.sqrt(n);
                            status_text = String.format("%s ± %s DPS", Fmt.bigNum(mean), Fmt.bigNum(serr));
                        }
                        final long est = (long)((iter - n) * (elapsed + (t - resumed)) / (double)n);
                        final String time_text = Fmt.msDur(est) + " remaining";
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                SimFrame.this.status_label.setText(status_text);
                                SimFrame.this.time_label.setText(time_text);
                                SimFrame.this.prog.setValue(n);
                            }
                        });
                    }
                    switch (SimFrame.this.state.get()) {
                        case 2: {
                            try {
                                elapsed += System.currentTimeMillis() - resumed;
                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        SimFrame.this.pause_btn.setText("Resume");
                                        SimFrame.this.pause_btn.setEnabled(true);
                                        SimFrame.this.time_label.setEnabled(false);
                                        SimFrame.this.prog.setEnabled(false);
                                    }
                                });
                                synchronized (SimFrame.this.guard) {
                                    SimFrame.this.guard.wait();
                                }
                                switch (SimFrame.this.state.get()) {
                                    case 1: {
                                        SimFrame.this.dispose();
                                        return;
                                    }
                                    case 3: {
                                        break;
                                    }
                                    default: {
                                        SwingUtilities.invokeLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                SimFrame.this.pause_btn.setText("Pause");
                                                SimFrame.this.pause_btn.setEnabled(true);
                                                SimFrame.this.time_label.setEnabled(true);
                                                SimFrame.this.prog.setEnabled(true);
                                            }
                                        });
                                        resumed = System.currentTimeMillis();
                                        break;
                                    }
                                }
                            }
                            catch (InterruptedException err) {}
                            break;
                        }
                        case 3: {
                            iter = curIter + 1;
                            break Label_0830;
                        }
                        case 1: {
                            SimFrame.this.dispose();
                            return;
                        }
                    }
                }
                SimFrame.this.dispose();
                elapsed += System.currentTimeMillis() - resumed;
                final double mean2 = acc0 / iter;
                BufferedWriter w = null;
                try {
                    w = new BufferedWriter(new FileWriter(SimFrame.this.csvFile));
                    w.write("DPS,DeltaDPS");
                    for (final StatT x : stats) {
                        w.write(44);
                        w.write((x == null) ? "Secondary" : x.abbr);
                    }
                    for (int j = 0; j < iter; ++j) {
                        w.newLine();
                        w.write(String.format("%.0f,%.0f", dpsArr[j], dpsArr[j] - mean2));
                        final int[] delArr2 = delMat[j];
                        for (int k = 0; k < stats.length; ++k) {
                            w.write(44);
                            w.write(Integer.toString(delArr2[k]));
                        }
                    }
                    w.close();
                    Utils.openFile(SimFrame.this.csvFile);
                }
                catch (IOException err2) {
                    Utils.silentClose(w);
                }
            }
        }.start();
        this.goVisible();
    }
    
    public void scale(final int iter0, final int lower, final int upper) {
        this.name_row.setVisible(false);
        this.prog.setMinimum(0);
        this.prog.setValue(0);
        this.prog.setMaximum(iter0);
        new Thread() {
            @Override
            public void run() {
                final long freq = 50L;
                final long resumed;
                final long started = resumed = System.currentTimeMillis();
                final long elapsed = 0L;
                final long next = started + 50L;
                SimFrame.this.sim.dist_prep();
                SimFrame.this.sim.saveDamage = false;
            }
        }.start();
        this.goVisible();
    }
    
    public void go(final int iter0, final double dpsErr, final int step0) {
        if (iter0 > 0) {
            this.prog.setMinimum(0);
            this.prog.setValue(0);
            this.prog.setMaximum(iter0);
        }
        else {
            this.prog.setIndeterminate(true);
            this.time_label.setText(String.format("Until Err < %s", Fmt.bigNum(dpsErr)));
        }
        new Thread() {
            @Override
            public void run() {
                final long freq = 50L;
                int iter = (iter0 > 0) ? iter0 : 1000000;
                final double[] dpsArr = new double[iter];
                final Result[] resArr = new Result[iter];
                long t_est;
                final long t0 = t_est = System.currentTimeMillis();
                long next = t0 + 50L;
                SimFrame.this.sim.dist_prep();
                int i_est = 0;
                int iter_pos = 0;
            Label_0523:
                while (iter_pos < iter) {
                    SimFrame.this.sim.runSim();
                    dpsArr[iter_pos] = SimFrame.this.sim.damage_total / (SimFrame.this.sim.clock / 1000.0);
                    resArr[iter_pos++] = new Result(iter_pos, SimFrame.this.sim);
                    final long t2 = System.currentTimeMillis();
                    if (t2 > next) {
                        next = t2 + 50L;
                        final int value;
                        String status_text;
                        if ((value = iter_pos) < 10) {
                            status_text = "Estimating...";
                        }
                        else {
                            final double mean = Maths.mean(dpsArr, value);
                            final double serr = Maths.stdev(dpsArr, value, mean) / Math.sqrt(value);
                            status_text = String.format("%s ± %s DPS", Fmt.bigNum(mean), Fmt.bigNum(serr));
                            if (dpsErr > 0.0 && serr <= dpsErr) {
                                iter = iter_pos;
                                break;
                            }
                        }
                        if (iter0 > 0) {
                            final long est = (long)((iter - iter_pos) * (t2 - t_est) / (double)(iter_pos - i_est));
                            final String time_text = Fmt.msDur(est) + " remaining";
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    SimFrame.this.status_label.setText(status_text);
                                    SimFrame.this.time_label.setText(time_text);
                                    SimFrame.this.prog.setValue(value);
                                }
                            });
                        }
                        else {
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    SimFrame.this.status_label.setText(status_text);
                                }
                            });
                        }
                    }
                    switch (SimFrame.this.state.get()) {
                        case 2: {
                            try {
                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        SimFrame.this.pause_btn.setText("Resume");
                                        SimFrame.this.pause_btn.setEnabled(true);
                                        SimFrame.this.time_label.setEnabled(false);
                                        SimFrame.this.prog.setEnabled(false);
                                    }
                                });
                                synchronized (SimFrame.this.guard) {
                                    SimFrame.this.guard.wait();
                                }
                                switch (SimFrame.this.state.get()) {
                                    case 1: {
                                        SimFrame.this.dispose();
                                        return;
                                    }
                                    case 3: {
                                        iter = iter_pos;
                                        break Label_0523;
                                    }
                                    default: {
                                        SwingUtilities.invokeLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                SimFrame.this.pause_btn.setText("Pause");
                                                SimFrame.this.pause_btn.setEnabled(true);
                                                SimFrame.this.time_label.setEnabled(true);
                                                SimFrame.this.prog.setEnabled(true);
                                            }
                                        });
                                        i_est = iter_pos;
                                        t_est = System.currentTimeMillis();
                                        continue;
                                    }
                                }
                            }
                            catch (InterruptedException err) {}
                            continue;
                        }
                        case 3: {
                            iter = iter_pos;
                            break Label_0523;
                        }
                        case 1: {
                            SimFrame.this.dispose();
                            return;
                        }
                    }
                }
                SimFrame.this.dispose();
                final ArrayList<String> lines = new ArrayList<String>();
                final LineWriter lw = LineWriter.buffered(lines);
                Arrays.sort(dpsArr, 0, iter);
                final double mean2 = Maths.mean(dpsArr, iter);
                final double sdev = Maths.stdev(dpsArr, iter, mean2);
                final double serr = sdev / Math.sqrt(iter);
                final String name = SimFrame.this.name_field.getText().trim();
                if (!name.isEmpty()) {
                    lw.add(name);
                    lw.add();
                }
                SimFrame.this.sim.dumpEncounterHeader(lw);
                lw.add();
                lw.add("[DPS] = %.1fK", dpsArr[iter / 2] / 1000.0);
                lw.add("    Mean: %8.0f +/- %.2f (%.0f)", mean2, serr, sdev);
                lw.add("  Median: %8.0f", dpsArr[iter / 2]);
                final int q25 = iter / 4;
                final int q26 = iter / 10;
                lw.add(" 25%%/75%%: %8s - %s", Fmt.bigNum(dpsArr[q25]), Fmt.bigNum(dpsArr[iter - 1 - q25]));
                lw.add(" 10%%/90%%: %8s - %s", Fmt.bigNum(dpsArr[q26]), Fmt.bigNum(dpsArr[iter - 1 - q26]));
                lw.add(" Min/Max: %8s - %s", Fmt.bigNum(dpsArr[0]), Fmt.bigNum(dpsArr[iter - 1]));
                lw.add();
                final int[] durArr = new int[iter];
                for (int i = 0; i < iter; ++i) {
                    durArr[i] = resArr[i].time;
                }
                Arrays.sort(durArr, 0, iter);
                lw.add("[Duration]");
                lw.add("    Mean: %6s", Fmt.msDur(Maths.mean(durArr, iter)));
                lw.add("  Median: %6s", Fmt.msDur(durArr[iter / 2]));
                lw.add(" 25%%/75%%: %6s - %s", Fmt.msDur(durArr[q25]), Fmt.msDur(durArr[iter - 1 - q25]));
                lw.add(" 10%%/90%%: %6s - %s", Fmt.msDur(durArr[q26]), Fmt.msDur(durArr[iter - 1 - q26]));
                lw.add(" Min/Max: %6s - %s", Fmt.msDur(durArr[0]), Fmt.msDur(durArr[iter - 1]));
                final int[] steps = { 1, 2, 5, 10, 30, 60 };
                int stepIndex = 0;
                int step;
                int min;
                int len;
                if (step0 > 0) {
                    step = step0;
                    min = durArr[0] / step;
                    final int max = durArr[iter - 1] / step;
                    len = 1 + max - min;
                }
                else {
                    do {
                        step = steps[stepIndex++] * 1000;
                        min = durArr[0] / step;
                        final int max = durArr[iter - 1] / step;
                        len = 1 + max - min;
                    } while (len > 25 && stepIndex < steps.length);
                }
                final int[] occArr = new int[len];
                for (int j = 0; j < iter; ++j) {
                    final int[] array = occArr;
                    final int n = durArr[j] / step - min;
                    ++array[n];
                }
                int tb_max = occArr[0];
                for (int k = 1; k < len; ++k) {
                    tb_max = Math.max(tb_max, occArr[k]);
                }
                final int width = 60;
                lw.add();
                lw.add("[Time Distribution] (every %s)", Fmt.msDur(step));
                lw.add("%-11s %5s %7s %7s %7s", "Interval", "Occ#", "DPS", "PMF%", "CDF%");
                int acc = 0;
                for (int l = 0; l < len; ++l) {
                    final int tmin = (min + l) * step;
                    final int tmax = (min + l + 1) * step - 1;
                    final int occ = occArr[l];
                    acc += occ;
                    double avg = 0.0;
                    if (occ > 0) {
                        for (int m = 0; m < iter; ++m) {
                            final int t3 = resArr[m].time;
                            if (t3 >= tmin && t3 <= tmax) {
                                avg += resArr[m].dps;
                            }
                        }
                    }
                    lw.add("%s-%s %5d %7s %6.2f%% %6.2f%% %s", Fmt.mm_ss(tmin), Fmt.mm_ss(tmax), occ, Fmt.bigNum(avg / occ), 100.0 * occ / iter, 100.0 * acc / iter, Fmt.repeat('#', width * occ / tb_max));
                }
                lw.add();
                lw.add("Speed: %d iterations in %s", iter, Fmt.msDur_since(t0));
                lw.add();
                SimFrame.this.sim.dumpDamage(lw);
                lw.add();
                SimFrame.this.sim.dumpFeatures(lw);
                SimFrame.this.sim.dumpHotfixes(lw);
                lw.add();
                SimFrame.this.sim.dumpTalentsGlyphsBonuses(lw);
                lw.add();
                lw.add("[Profile]");
                lw.add(CompactGear.toString(SimFrame.this.sim.mainProfile, true, true));
                if (SimFrame.this.sim.mainGear != SimFrame.this.sim.swapGear) {
                    lw.add();
                    lw.add("[Caster Weapon]");
                    lw.add(CompactGear.toString(SimFrame.this.sim.swapProfile));
                }
                try {
                    Utils.writeLines(SimFrame.this.reportFile, lines);
                    Utils.openFile(SimFrame.this.reportFile);
                }
                catch (IOException ex) {}
                if (SimFrame.this.csvFile != null) {
                    BufferedWriter w = null;
                    try {
                        w = new BufferedWriter(new FileWriter(SimFrame.this.csvFile));
                        w.write("Index,Damage,Time(ms),DPS");
                        for (int i2 = 0; i2 < iter; ++i2) {
                            w.newLine();
                            final Result res = resArr[i2];
                            w.write(String.format("%d,%d,%d,%.0f", res.index, res.damage, res.time, res.dps));
                        }
                        w.close();
                        Utils.openFile(SimFrame.this.csvFile);
                    }
                    catch (IOException err2) {
                        Utils.silentClose(w);
                    }
                }
            }
        }.start();
        this.goVisible();
    }
    
    static {
        _seq = new AtomicInteger();
    }
    
    static class Result
    {
        final int index;
        final int time;
        final long damage;
        final double dps;
        static final Comparator<Result> cmp_time;
        static final Comparator<Result> cmp_dps;
        
        Result(final int index, final FeralSim sim) {
            this.index = index;
            this.time = sim.clock;
            this.damage = sim.damage_total;
            this.dps = this.damage / (this.time / 1000.0);
        }
        
        static {
            cmp_time = new Comparator<Result>() {
                @Override
                public int compare(final Result a, final Result b) {
                    return b.time - a.time;
                }
            };
            cmp_dps = new Comparator<Result>() {
                @Override
                public int compare(final Result a, final Result b) {
                    return Double.compare(-a.dps, -b.dps);
                }
            };
        }
    }
}
