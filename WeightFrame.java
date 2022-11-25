// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Color;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.util.Comparator;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.File;
import javax.swing.JFrame;

public class WeightFrame extends JFrame
{
    final Profile mainProfile;
    final Profile swapProfile;
    final Row[] rows;
    final File file;
    final boolean inert;
    final AtomicInteger nextIndex;
    final AtomicInteger activeCores;
    final int iter;
    final int delta;
    final UIPanel_GridBag scrolled;
    final JScrollPane scroll;
    final JLabel time_text;
    final JProgressBar progBar0;
    final UIPanel_GridBag header;
    static final Comparator<Row> CMP_delta;
    long updateTime_next;
    long startTime;
    int utilizedCores;
    
    void updateTime() {
        final long now = System.currentTimeMillis();
        if (now < this.updateTime_next) {
            return;
        }
        this.updateTime_next = now + 250L;
        long acc = 0L;
        for (final Row x : this.rows) {
            acc += x.curIter;
        }
        final long tot = this.iter * (long)this.rows.length;
        final long left = (now - this.startTime) * (tot - acc) / acc;
        this.time_text.setText(Fmt.msDur(left) + " remaining");
        this.progBar0.setIndeterminate(false);
        this.progBar0.setValue((int)(this.progBar0.getMaximum() * acc / tot));
    }
    
    WeightFrame(final Profile mainProfile, final Profile swapProfile, final int iter, final int delta, final boolean inert, final File file, final FeralSim[] sims) {
        this.nextIndex = new AtomicInteger();
        this.activeCores = new AtomicInteger();
        this.mainProfile = mainProfile;
        this.swapProfile = swapProfile;
        this.iter = iter;
        this.delta = delta;
        this.inert = inert;
        this.file = file;
        this.setTitle("Simulator: Stat Weights (" + sims.length + ")");
        this.rows = new Row[sims.length];
        for (int i = 0; i < sims.length; ++i) {
            this.rows[i] = new Row(i, sims[i]);
        }
        this.scrolled = new UIPanel_GridBag();
        final int pad = 4;
        final Border border0 = BorderFactory.createEmptyBorder(pad, pad, pad, pad);
        final Border border2 = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, CatusFrame.DARK_GRAY), border0);
        for (int j = 0; j < this.rows.length; ++j) {
            final Row row = this.rows[j];
            row.panel.setBorder((j > 0) ? border2 : border0);
            this.scrolled.row(row.panel, true, 0);
        }
        this.scrolled.balloon();
        (this.scroll = new JScrollPane()).setVerticalScrollBarPolicy(22);
        this.scroll.setHorizontalScrollBarPolicy(30);
        this.scroll.getVerticalScrollBar().setUnitIncrement(15);
        this.scroll.getHorizontalScrollBar().setUnitIncrement(15);
        this.scroll.setViewportView(this.scrolled);
        this.scroll.getViewport().setOpaque(false);
        this.scroll.setBorder(null);
        this.scroll.setOpaque(false);
        (this.progBar0 = new JProgressBar()).setMinimum(0);
        this.progBar0.setMaximum(10000);
        this.progBar0.setValue(0);
        this.progBar0.setIndeterminate(true);
        (this.time_text = new JLabel("Estimating")).setForeground(Color.WHITE);
        this.time_text.setHorizontalAlignment(4);
        this.time_text.setFont(CatusFrame.boldFont);
        (this.header = new UIPanel_GridBag()).setOpaque(true);
        this.header.setBackground(Color.DARK_GRAY);
        this.header.pad(5);
        this.header.add(this.progBar0);
        this.header.spacer(this.time_text);
        final UIPanel_GridBag body = new UIPanel_GridBag();
        body.row(this.header, true, 0);
        body.row_both(this.scroll);
        this.setContentPane(body);
        final int wid = 350;
        this.setMinimumSize(new Dimension(wid, 250));
        this.pack();
        this.setSize(new Dimension(this.getWidth(), Math.min(wid, this.getHeight())));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(0);
        UI.onShortcut(body, 87, "Close", new Runnable() {
            @Override
            public void run() {
                WeightFrame.this.kill();
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent we) {
                WeightFrame.this.kill();
            }
        });
    }
    
    void kill() {
        for (final Row x : this.rows) {
            x.thread.interrupt();
        }
        this.dispose();
    }
    
    public void go(int cores) {
        if (cores > this.rows.length) {
            cores = this.rows.length;
        }
        this.startTime = System.currentTimeMillis();
        this.updateTime_next = this.startTime + 1000L;
        this.utilizedCores = cores;
        this.activeCores.set(cores);
        this.nextIndex.set(cores);
        for (int i = 0; i < cores; ++i) {
            this.rows[i].thread.start();
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WeightFrame.this.setVisible(true);
            }
        });
    }
    
    private void done() {
        this.dispose();
        final ArrayList<String> lines = new ArrayList<String>(1000);
        final LineWriter lw = LineWriter.buffered(lines);
        final FeralSim sim = this.rows[0].sim;
        sim.resetAll();
        lw.add(sim.catStatsStr());
        lw.add();
        lw.add("[Weights] (DPS/Stat)");
        for (final Row row : this.rows) {
            row.delta = (row.mean - this.rows[0].mean) * row.sign;
        }
        Arrays.sort(this.rows, 1, this.rows.length, WeightFrame.CMP_delta);
        final String fmt = "%-18s %8s  %6s  %6s  %6s  %6.2f  %6.2f  %s";
        lw.add("%-18s %8s  %6s  %6s  %6s  %6s  %6s  %s", "Name", "DPS", "Stdev", "Gain", "Error", "Weight", "Error", "Delta");
        for (final Row row2 : this.rows) {
            final double serr = Math.hypot((row2 != this.rows[0]) ? this.rows[0].stdev : 0.0, row2.stdev) / Math.sqrt(this.iter);
            lw.add(fmt, row2.sim.simName, Fmt.bigNum(row2.mean), Fmt.bigNum(row2.stdev), Fmt.bigNum(row2.delta), Fmt.bigNum(serr), row2.delta / this.delta, serr / this.delta, row2.sim.simDesc);
        }
        lw.add();
        lw.add("Delta: %d (%s)", this.delta, this.inert ? "Inert" : "Active");
        lw.add("Speed: %d sims of %d iterations in %s using %d cores", this.rows.length, this.iter, Fmt.msDur_since(this.startTime), this.utilizedCores);
        lw.add();
        lw.add("[Import String]");
        for (int i = 1; i < this.rows.length; ++i) {
            final Row row3 = this.rows[i];
            final double weight = row3.delta / this.delta;
            if (row3.sim.simName.equals("Secondary")) {
                lw.add("%s: %.2f", StatT.MASTERY.abbr, weight);
                lw.add("%s: %.2f", StatT.HASTE.abbr, weight);
                lw.add("%s: %.2f", StatT.CRIT.abbr, weight);
            }
            else if (!row3.sim.simName.endsWith("+")) {
                lw.add("%s: %.2f", row3.sim.simName, weight);
            }
        }
        lw.add();
        sim.dumpEncounterHeader(lw);
        lw.add();
        sim.dumpTalentsGlyphsBonuses(lw);
        lw.add();
        sim.dumpFeatures(lw);
        sim.dumpHotfixes(lw);
        lw.add();
        lw.add("[Profile]");
        lw.add(CompactGear.toString(this.mainProfile, true, true));
        if (sim.cfg.hotw_swap) {
            lw.add();
            lw.add("[Caster Weapon]");
            lw.add(CompactGear.toString(this.swapProfile));
        }
        for (final Row row2 : this.rows) {
            lw.add();
            lw.add("=== %s ===", row2.sim.simName);
            lw.add();
            if (!row2.sim.simDesc.isEmpty()) {
                lw.add("Delta: %s", row2.sim.simDesc);
            }
            row2.sim.resetAll();
            lw.add("Stats: %s", row2.sim.catStatsStr());
            lw.add();
            final double[] dpsArr = row2.dpsArr;
            lw.add("[DPS] = %.1fK", dpsArr[this.iter / 2] / 1000.0);
            lw.add("    Mean: %8.0f +/- %.2f (%.0f)", row2.mean, row2.stdev / Math.sqrt(this.iter), row2.stdev);
            lw.add("  Median: %8.0f", row2.median);
            final int q25 = this.iter / 4;
            final int q26 = this.iter / 10;
            lw.add(" 25%%/75%%: %8s - %s", Fmt.bigNum(dpsArr[q25]), Fmt.bigNum(dpsArr[this.iter - 1 - q25]));
            lw.add(" 10%%/90%%: %8s - %s", Fmt.bigNum(dpsArr[q26]), Fmt.bigNum(dpsArr[this.iter - 1 - q26]));
            lw.add(" Min/Max: %8s - %s", Fmt.bigNum(dpsArr[0]), Fmt.bigNum(dpsArr[this.iter - 1]));
            lw.add();
            final int[] durArr = row2.durArr;
            lw.add("[Duration]");
            lw.add("  Median: %s", Fmt.msDur(durArr[this.iter / 2]));
            lw.add("    Mean: %s", Fmt.msDur(Maths.mean(durArr, this.iter)));
            lw.add(" 25%%/75%%: %8s - %s", Fmt.msDur(durArr[q25]), Fmt.msDur(durArr[this.iter - 1 - q25]));
            lw.add(" 10%%/90%%: %8s - %s", Fmt.msDur(durArr[q26]), Fmt.msDur(durArr[this.iter - 1 - q26]));
            lw.add(" Min/Max: %8s - %s", Fmt.msDur(durArr[0]), Fmt.msDur(durArr[this.iter - 1]));
            lw.add();
            row2.sim.dumpDamage(lw);
        }
        lw.add();
        lw.add("[Time Variance]");
        for (final Row row2 : this.rows) {
            lw.add("%-16s %8d", row2.sim.simName, row2.durArr[this.iter / 2]);
        }
        try {
            Utils.writeLines(this.file, lines);
            Utils.openFile(this.file);
        }
        catch (IOException ex) {}
    }
    
    static {
        CMP_delta = new Comparator<Row>() {
            @Override
            public int compare(final Row a, final Row b) {
                return Double.compare(b.delta, a.delta);
            }
        };
    }
    
    class Row
    {
        final int index;
        final JProgressBar progBar;
        final JLabel nameLbl;
        final JLabel stateLbl;
        final double[] dpsArr;
        final int[] durArr;
        final Thread thread;
        volatile int curIter;
        double median;
        double mean;
        double stdev;
        double delta;
        final FeralSim sim;
        final UIPanel_GridBag panel;
        final int sign;
        
        Row(final int index, final FeralSim sim) {
            this.dpsArr = new double[WeightFrame.this.iter];
            this.durArr = new int[WeightFrame.this.iter];
            this.thread = new Thread() {
                @Override
                public void run() {
                    final long t_start = System.currentTimeMillis();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            Row.this.progBar.setVisible(true);
                        }
                    });
                    final long freq = 50L;
                    long t_next = System.currentTimeMillis() + freq;
                    Row.this.sim.dist_prep();
                    for (int i = 0; i < WeightFrame.this.iter; ++i) {
                        Row.this.sim.runSim();
                        Row.this.dpsArr[i] = Row.this.sim.damage_total / (Row.this.sim.clock / 1000.0);
                        Row.this.durArr[i] = Row.this.sim.clock;
                        final long t = System.currentTimeMillis();
                        if (t > t_next) {
                            t_next = t + freq;
                            final Row this$1 = Row.this;
                            final int curIter = i + 1;
                            this$1.curIter = curIter;
                            final int n = curIter;
                            final double mean = Maths.mean(Row.this.dpsArr, n);
                            final double serr = Maths.stdev(Row.this.dpsArr, n, mean) / Math.sqrt(n);
                            final String stateText = String.format("%s ± %s DPS", Fmt.bigNum(mean), Fmt.bigNum(serr));
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    Row.this.progBar.setValue(n);
                                    Row.this.stateLbl.setText(stateText);
                                    WeightFrame.this.updateTime();
                                }
                            });
                        }
                        if (interrupted()) {
                            return;
                        }
                    }
                    Row.this.curIter = WeightFrame.this.iter;
                    Arrays.sort(Row.this.dpsArr, 0, WeightFrame.this.iter);
                    Arrays.sort(Row.this.durArr, 0, WeightFrame.this.iter);
                    Row.this.median = Row.this.dpsArr[WeightFrame.this.iter / 2];
                    Row.this.mean = Maths.mean(Row.this.dpsArr, WeightFrame.this.iter);
                    Row.this.stdev = Maths.stdev(Row.this.dpsArr, WeightFrame.this.iter, Row.this.mean);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            Row.this.progBar.setVisible(false);
                            Row.this.stateLbl.setText(String.format("%s DPS (%s)", Fmt.bigNum(Row.this.mean), Fmt.msDur_since(t_start)));
                            WeightFrame.this.updateTime();
                        }
                    });
                    final int next = WeightFrame.this.nextIndex.getAndIncrement();
                    if (next < WeightFrame.this.rows.length) {
                        WeightFrame.this.rows[next].thread.start();
                    }
                    else if (WeightFrame.this.activeCores.decrementAndGet() == 0) {
                        WeightFrame.this.done();
                    }
                }
            };
            this.index = index;
            this.sim = sim;
            (this.progBar = new JProgressBar()).setVisible(false);
            this.progBar.setValue(0);
            this.progBar.setMaximum(WeightFrame.this.iter);
            (this.nameLbl = new JLabel()).setText(sim.simName);
            this.nameLbl.setFont(CatusFrame.boldFont);
            (this.stateLbl = new JLabel()).setText("Queued");
            this.stateLbl.setFont(CatusFrame.tinyFont);
            this.stateLbl.setHorizontalAlignment(4);
            this.panel = new UIPanel_GridBag();
            this.sign = ((sim.simData instanceof Integer && (int)sim.simData < 0) ? -1 : 1);
            final UIPanel_GridBag row = new UIPanel_GridBag();
            row.add(this.nameLbl);
            row.spacer(this.stateLbl);
            this.panel.row(row, true, 0);
            this.panel.row(this.progBar, true, 3);
            this.thread.setPriority(1);
        }
    }
}
