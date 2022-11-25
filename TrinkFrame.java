// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import javax.swing.Icon;
import java.io.Closeable;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.SwingUtilities;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.border.Border;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JScrollPane;
import java.util.Comparator;
import javax.swing.JFrame;

public class TrinkFrame extends JFrame
{
    static final Comparator<Row> cmp_score;
    final Row[] rows;
    final UIPanel_GridBag scrolled;
    final UIPanel_GridBag header;
    final JScrollPane scroll;
    final int iter;
    final AtomicInteger nextIndex;
    final AtomicInteger activeCores;
    final File reportFile;
    final File csvFile;
    final JLabel time_text;
    final JProgressBar progBar0;
    static final Border sepBorder;
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
        this.time_text.setText(String.format("%s remaining (%.0f%%)", Fmt.msDur(left), 100.0 * acc / tot));
        this.progBar0.setIndeterminate(false);
        this.progBar0.setValue((int)(this.progBar0.getMaximum() * acc / tot));
    }
    
    private void rebuildRows() {
        this.scrolled.removeAll();
        this.scrolled.setLayout(new GridBagLayout());
        final int pad = 4;
        final Border border0 = BorderFactory.createEmptyBorder(pad, pad, pad, pad);
        final Border border2 = BorderFactory.createCompoundBorder(TrinkFrame.sepBorder, border0);
        for (int i = 0; i < this.rows.length; ++i) {
            final Row row = this.rows[i];
            row.panel.setBorder((i > 0) ? border2 : border0);
            this.scrolled.row(row.panel, true, 0);
        }
        this.scrolled.balloon();
    }
    
    TrinkFrame(final String title, final FeralSim[] sims, final int iter, final File reportFile, final File csvFile) {
        this.nextIndex = new AtomicInteger();
        this.activeCores = new AtomicInteger();
        this.iter = iter;
        this.reportFile = reportFile;
        this.csvFile = csvFile;
        this.rows = new Row[sims.length];
        for (int i = 0; i < sims.length; ++i) {
            this.rows[i] = new Row(i, sims[i]);
        }
        this.scrolled = new UIPanel_GridBag();
        this.rebuildRows();
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
        this.setTitle(title + " (" + sims.length + ")");
        final int sq = 500;
        this.setMinimumSize(new Dimension(sq, sq / 2));
        this.pack();
        this.setSize(new Dimension(this.getWidth(), Math.min(sq, this.getHeight())));
        this.setDefaultCloseOperation(0);
        this.setLocationRelativeTo(null);
        UI.onShortcut(this.scroll, 87, "Close", new Runnable() {
            @Override
            public void run() {
                TrinkFrame.this.kill();
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent we) {
                TrinkFrame.this.kill();
            }
        });
    }
    
    void go(int cores) {
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
                TrinkFrame.this.setVisible(true);
            }
        });
    }
    
    void kill() {
        for (final Row x : this.rows) {
            x.thread.interrupt();
        }
        this.dispose();
    }
    
    void done() {
        Arrays.sort(this.rows, TrinkFrame.cmp_score);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TrinkFrame.this.header.setVisible(false);
                final double score0 = TrinkFrame.this.rows[0].score();
                for (int i = 0; i < TrinkFrame.this.rows.length; ++i) {
                    final Row row = TrinkFrame.this.rows[i];
                    row.status1_text.setText(String.format("#%d", i + 1));
                    row.status1_text.setFont(CatusFrame.boldFont);
                    row.status2_text.setText(Fmt.bigNum((i == 0) ? row.score() : (row.score() - score0)) + " DPS");
                    row.stats_text.setVisible(false);
                }
                TrinkFrame.this.rebuildRows();
            }
        });
        if (this.reportFile != null) {
            final ArrayList<String> lines = new ArrayList<String>(1000);
            final LineWriter lw = LineWriter.buffered(lines);
            lw.add("[Trinket Ranking]");
            int max = 0;
            for (int i = 0; i < this.rows.length; ++i) {
                max = Math.max(max, this.rows[i].sim.mainProfile.T1.getItemNameOrEmpty(false).length());
            }
            final String fmt = "%3d.  %6s  %6s  %-" + max + "s  %s";
            lw.add("%3s   %6s  %6s  %-" + max + "s  %s", "", "DPS", "Error", "Trinket 1", "Trinket 2");
            for (int j = 0; j < this.rows.length; ++j) {
                final Row row = this.rows[j];
                final double serr = Maths.stdev(row.dpsArr, this.iter, row.mean) / Math.sqrt(this.iter);
                lw.add(String.format(fmt, 1 + j, Fmt.bigNum(row.score()), Fmt.bigNum(serr), row.sim.mainProfile.T1.getItemNameOrEmpty(false), row.sim.mainProfile.T2.getItemNameOrEmpty(false)));
            }
            lw.add();
            lw.add("Speed: %d sims of %d iterations in %s using %d cores", this.rows.length, this.iter, Fmt.msDur_since(this.startTime), this.utilizedCores);
            final FeralSim sim = this.rows[0].sim;
            lw.add();
            sim.dumpEncounterHeader(lw);
            lw.add();
            sim.dumpTalentsGlyphsBonuses(lw);
            lw.add();
            sim.dumpFeatures(lw);
            sim.dumpHotfixes(lw);
            lw.add();
            lw.add("[Profile]");
            final Profile p = new Profile();
            p.importProfile(sim.mainProfile);
            p.T1.clear();
            p.T2.clear();
            lw.add(CompactGear.toString(p, true, true));
            if (sim.cfg.hotw_swap) {
                lw.add();
                lw.add("[Caster Weapon]");
                p.clear();
                p.T1.copy(sim.swapProfile.T1);
                p.T2.copy(sim.swapProfile.T2);
                lw.add(CompactGear.toString(p, false, true));
            }
            for (int k = 0; k < this.rows.length; ++k) {
                final Row row2 = this.rows[k];
                row2.sim.resetAll();
                lw.add();
                lw.add("=== #%d ===", k + 1);
                lw.add();
                lw.add("   T1: %s", row2.sim.mainProfile.T1.getItemNameOrEmpty());
                lw.add("   T2: %s", row2.sim.mainProfile.T2.getItemNameOrEmpty());
                lw.add(" Gear: %s", row2.sim.mainProfile.statStr(FeralSpec.REFORGE_STATS));
                lw.add("Stats: %s", row2.sim.catStatsStr());
                lw.add();
                lw.add("[DPS]");
                final double[] dpsArr = row2.dpsArr;
                final double stdev = Maths.stdev(dpsArr, this.iter, row2.mean);
                lw.add("    Mean: %8.0f +/- %.2f (%.0f)", row2.mean, stdev / Math.sqrt(this.iter), stdev);
                lw.add("  Median: %8.0f", row2.median);
                final int q25 = this.iter / 4;
                final int q26 = this.iter / 10;
                lw.add(" 25%%/75%%: %8s - %s", Fmt.bigNum(dpsArr[q25]), Fmt.bigNum(dpsArr[this.iter - 1 - q25]));
                lw.add(" 10%%/90%%: %8s - %s", Fmt.bigNum(dpsArr[q26]), Fmt.bigNum(dpsArr[this.iter - 1 - q26]));
                lw.add(" Min/Max: %8s - %s", Fmt.bigNum(dpsArr[0]), Fmt.bigNum(dpsArr[this.iter - 1]));
                lw.add();
                row2.sim.dumpDamage(lw);
            }
            try {
                Utils.writeLines(this.reportFile, lines);
                Utils.openFile(this.reportFile);
            }
            catch (IOException ex) {}
        }
        if (this.csvFile != null) {
            BufferedWriter w = null;
            try {
                w = new BufferedWriter(new FileWriter(this.csvFile));
                for (int l = 0; l < this.rows.length; ++l) {
                    if (l > 0) {
                        w.write(44);
                    }
                    w.write(34);
                    final Profile p2 = this.rows[l].sim.mainProfile;
                    w.write(p2.T1.getItemNameOrEmpty(false));
                    w.write(47);
                    w.write(p2.T2.getItemNameOrEmpty(false));
                    w.write(34);
                }
                for (int m = 0; m < this.iter; ++m) {
                    w.newLine();
                    for (int j2 = 0; j2 < this.rows.length; ++j2) {
                        if (j2 > 0) {
                            w.write(44);
                        }
                        w.write(String.format("%.0f", this.rows[j2].dpsArr[m]));
                    }
                }
                w.close();
                Utils.openFile(this.csvFile);
            }
            catch (IOException err) {
                Utils.silentClose(w);
            }
        }
    }
    
    static {
        cmp_score = new Comparator<Row>() {
            @Override
            public int compare(final Row a, final Row b) {
                return Double.compare(-a.score(), -b.score());
            }
        };
        sepBorder = BorderFactory.createMatteBorder(1, 0, 0, 0, CatusFrame.DARK_GRAY);
    }
    
    class Row
    {
        final int index;
        final FeralSim sim;
        final UIPanel_GridBag panel;
        final JLabel status1_text;
        final JLabel status2_text;
        final JLabel stats_text;
        final double[] dpsArr;
        final JProgressBar progBar;
        final Thread thread;
        volatile int curIter;
        double mean;
        double median;
        
        double score() {
            return this.median;
        }
        
        Row(final int index, final FeralSim sim) {
            this.dpsArr = new double[TrinkFrame.this.iter];
            this.thread = new Thread() {
                @Override
                public void run() {
                    final long t_start = System.currentTimeMillis();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            Row.this.progBar.setValue(0);
                            Row.this.progBar.setMaximum(TrinkFrame.this.iter);
                            Row.this.progBar.setVisible(true);
                            Row.this.status1_text.setText("Estimating...");
                        }
                    });
                    final long freq = 50L;
                    long t_next = System.currentTimeMillis() + freq;
                    Row.this.sim.dist_prep();
                    Row.this.sim.saveDamage = (TrinkFrame.this.reportFile != null || TrinkFrame.this.csvFile != null);
                    for (int i = 0; i < TrinkFrame.this.iter; ++i) {
                        Row.this.sim.runSim();
                        Row.this.dpsArr[i] = Row.this.sim.damage_total / (Row.this.sim.clock / 1000.0);
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
                                    Row.this.status1_text.setText(stateText);
                                    TrinkFrame.this.updateTime();
                                }
                            });
                        }
                        if (interrupted()) {
                            return;
                        }
                    }
                    Row.this.curIter = TrinkFrame.this.iter;
                    Arrays.sort(Row.this.dpsArr, 0, TrinkFrame.this.iter);
                    Row.this.mean = Maths.mean(Row.this.dpsArr, TrinkFrame.this.iter);
                    Row.this.median = Row.this.dpsArr[TrinkFrame.this.iter / 2];
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            Row.this.progBar.setVisible(false);
                            Row.this.status1_text.setText(String.format("%s DPS (%s)", Fmt.bigNum(Row.this.median), Fmt.msDur_since(t_start)));
                            TrinkFrame.this.updateTime();
                        }
                    });
                    final int next = TrinkFrame.this.nextIndex.getAndIncrement();
                    if (next < TrinkFrame.this.rows.length) {
                        TrinkFrame.this.rows[next].thread.start();
                    }
                    else if (TrinkFrame.this.activeCores.decrementAndGet() == 0) {
                        TrinkFrame.this.done();
                    }
                }
            };
            this.index = index;
            this.sim = sim;
            (this.progBar = new JProgressBar()).setVisible(false);
            (this.status1_text = new JLabel("Queued")).setFont(CatusFrame.tinyFont);
            (this.status2_text = new JLabel()).setFont(CatusFrame.tinyFont);
            this.panel = new UIPanel_GridBag();
            final Item t1 = sim.mainProfile.T1.item;
            final Item t2 = sim.mainProfile.T2.item;
            final JLabel t1_text = new JLabel();
            t1_text.setFont(CatusFrame.tinyFont);
            t1_text.setText(sim.mainProfile.T1.getItemNameOrEmpty());
            if (t1 != null) {
                t1_text.setIcon(t1.icon.getIcon(API.IconSize.SMALL));
                t1_text.setForeground(t1.quality.color);
            }
            final JLabel t2_text = new JLabel();
            t2_text.setFont(CatusFrame.tinyFont);
            t2_text.setText(sim.mainProfile.T2.getItemNameOrEmpty());
            if (t2 != null) {
                t2_text.setIcon(t2.icon.getIcon(API.IconSize.SMALL));
                t2_text.setForeground(t2.quality.color);
            }
            final UIPanel_GridBag row1 = new UIPanel_GridBag();
            row1.spacer(t1_text);
            row1.add(this.status1_text);
            this.panel.row(row1, true, 0);
            final UIPanel_GridBag row2 = new UIPanel_GridBag();
            row2.spacer(t2_text);
            row2.add(this.status2_text);
            this.panel.row(row2, true, 1);
            (this.stats_text = new JLabel()).setText(sim.mainProfile.statStr(FeralSpec.REFORGE_STATS));
            this.stats_text.setFont(CatusFrame.tinyFont);
            this.stats_text.setEnabled(false);
            this.panel.row(this.stats_text, true, 1);
            this.panel.row(this.progBar, true, 4);
            this.thread.setPriority(1);
        }
    }
}
