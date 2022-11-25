// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Iterator;
import java.io.DataOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.io.FileWriter;
import java.awt.Component;
import javax.swing.JOptionPane;
import org.json.simple.JSONValue;
import java.util.Locale;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JFrame;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;

public class Main
{
    static final String UPDATER_NAME = "Updater.jar";
    
    static void tryUpdate(final File f) {
        if (!f.isFile()) {
            return;
        }
        final String fn = f.getName();
        URL url;
        try {
            url = new URL("https://dl.dropboxusercontent.com/u/2989349/catus_data/" + fn);
        }
        catch (MalformedURLException err) {
            throw new RuntimeException("Bad URL: " + err);
        }
        final File tempFile = new File(f.getParentFile(), fn + ".download");
        final DialogProg dp = new DialogProg((JFrame)null, "Downloading " + fn);
        final AtomicBoolean abort = new AtomicBoolean();
        dp.setAbortOnClose(abort);
        dp.execute(new Runnable() {
            @Override
            public void run() {
                BufferedOutputStream out = null;
                try {
                    final URLConnection c = url.openConnection();
                    final InputStream in = c.getInputStream();
                    out = new BufferedOutputStream(new FileOutputStream(tempFile));
                    final byte[] buf = new byte[4096];
                    while (true) {
                        final int num = in.read(buf);
                        if (num == -1) {
                            in.close();
                            out.close();
                            break;
                        }
                        if (num > 0) {
                            out.write(buf, 0, num);
                        }
                        if (abort.get()) {
                            throw new IOException("Aborted");
                        }
                    }
                }
                catch (IOException err) {
                    Utils.silentClose(out);
                    tempFile.delete();
                    if (!abort.get()) {
                        throw new RuntimeException("Download failed: " + err);
                    }
                    System.exit(0);
                }
            }
        });
        BufferedOutputStream out = null;
        try {
            final FileInputStream in = new FileInputStream(tempFile);
            final FileOutputStream fos = new FileOutputStream(f);
            out = new BufferedOutputStream(fos);
            final byte[] buf = new byte[4096];
            while (true) {
                final int len = in.read(buf);
                if (len == -1) {
                    break;
                }
                if (len <= 0) {
                    continue;
                }
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        }
        catch (IOException err2) {
            Utils.silentClose(out);
            tempFile.delete();
            f.delete();
            throw new RuntimeException("Replace failed " + err2);
        }
        tempFile.delete();
        try {
            if (Utils.isMac()) {
                final String[] cmd = { "java", "-jar", f.getAbsolutePath() };
                Runtime.getRuntime().exec(cmd).waitFor();
            }
            else {
                Desktop.getDesktop().open(f);
            }
            System.exit(0);
        }
        catch (Exception err3) {
            throw new RuntimeException("Launch failed: " + err3);
        }
    }
    
    static void tryUpdater() {
        final File jarFile = Utils.getJarFile();
        if (jarFile == null) {
            return;
        }
        final File updaterFile = new File(jarFile.getParentFile(), "Updater.jar");
        FileChannel src = null;
        FileChannel dst = null;
        try {
            src = new FileInputStream(jarFile).getChannel();
            dst = new FileOutputStream(updaterFile).getChannel();
            src.transferTo(0L, src.size(), dst);
            src.close();
            dst.close();
        }
        catch (IOException err) {
            Utils.silentClose(src);
            Utils.silentClose(dst);
            updaterFile.delete();
            return;
        }
        try {
            Desktop.getDesktop().open(updaterFile);
            System.exit(0);
        }
        catch (IOException ex) {}
    }
    
    public static void main(final String[] args) {
        System.setProperty("java.net.preferIPv4Stack", "true");
        System.setProperty("apple.awt.documentModalSheet", "true");
        Locale.setDefault(Locale.US);
        UI.lookAndFeel();
        if (args.length > 0 && args[0].equalsIgnoreCase("test-connect")) {
            return;
        }
        if (args.length > 0 && args[0].equalsIgnoreCase("test-cache")) {
            final HttpCache hc = new HttpCache();
            try {
                final Pair<String, Boolean> r = hc.getStr("https://dl.dropboxusercontent.com/u/2989349/catus_data/Taint.json", "", 5000);
                if (r.cdr) {
                    System.err.println("HTTP Error!");
                    System.err.println(r.car);
                    return;
                }
                System.out.println(JSONValue.parseWithException(r.car));
            }
            catch (Exception err) {
                err.printStackTrace();
            }
            return;
        }
        final File jarFile = Utils.getJarFile();
        if (jarFile != null) {
            if (jarFile.getName().equals("Updater.jar")) {
                try {
                    final File dir = jarFile.getParentFile();
                    tryUpdate(new File(dir, "Catus.jar"));
                    tryUpdate(new File(dir, "Zephyrus.jar"));
                }
                catch (RuntimeException err2) {
                    JOptionPane.showMessageDialog(null, err2.getMessage(), "Update Error", 2);
                    System.exit(0);
                    return;
                }
                JOptionPane.showMessageDialog(null, "Unable to find an application to update.", "Update Error", 2);
                System.exit(0);
                return;
            }
            final File updaterFile = new File(jarFile.getParentFile(), "Updater.jar");
            if (updaterFile.isFile() && !updaterFile.delete()) {
                updaterFile.deleteOnExit();
                final Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000L);
                        }
                        catch (InterruptedException ex) {}
                        updaterFile.delete();
                    }
                };
                t.setDaemon(true);
                t.start();
            }
        }
        if (jarFile != null) {
            final File jarDir = jarFile.getParentFile();
            final File dir2 = new File("");
            if (!dir2.equals(jarDir)) {
                try {
                    System.setProperty("user.dir", jarDir.getAbsolutePath());
                    System.err.println("Changed user.dir: " + jarDir);
                }
                catch (Exception err3) {
                    System.err.println("Error occured changing user.dir");
                }
            }
        }
        if (args.length > 0 && args[0].equalsIgnoreCase("monk")) {
            WWFrame.boot();
            return;
        }
        CatusFrame.boot();
    }
    
    public static void makeTrinketProfiles(final API api, final Profile p0, final File dir) {
        class T
        {
            final int id = 96781;
            final int delta = 8;
            Item item;
            
            T(final int id) {
                this(id, 0);
            }
            
            T(final int id, final int delta) {
            }
        }
        final T[] array;
        final T[] trinkets = array = new T[] { new T(8), new T(8), new T(8), new T(8), new T(8), new T(8), new T(8), new T(8), new T(8), new T(8), new T(8), new T(8), new T(8), new T(8), new T(8) };
        for (final T x : array) {
            x.item = api.loadItem(x.id);
            System.out.println(x.item.fullName());
        }
        System.out.println();
        System.out.println(CompactGear.toString(p0));
        dir.mkdir();
        for (int i = 1; i < trinkets.length; ++i) {
            for (int j = 0; j <= i; ++j) {
                p0.T1.clear();
                p0.T2.clear();
                try {
                    p0.T1.setItem(trinkets[i].item);
                    p0.T2.setItem(trinkets[j].item);
                    p0.T1.setItemLevelDelta(trinkets[i].delta);
                    p0.T2.setItemLevelDelta(trinkets[j].delta);
                }
                catch (RuntimeException err2) {
                    continue;
                }
                final File file = new File(dir, p0.T1.item.itemId + "_" + p0.T2.item.itemId + ".txt");
                System.out.println(p0.T1.getItemNameOrEmpty());
                System.out.println(p0.T2.getItemNameOrEmpty());
                System.out.println(file);
                if (p0.hasSpell(139116)) {
                    final Reforger111 r = new Reforger111();
                    r.canBreakBonuses = true;
                    r.changeGems = true;
                    r.masteryOverflow = 50;
                    r.searchRange = 200;
                    r.enchantBack = true;
                    r.enchantHands = true;
                    r.feral_sta = false;
                    if (p0.hasSpell(138894)) {
                        r.hasteGap = 6000;
                        r.searchRange = 1000;
                        r.masteryOverflow = 1000;
                    }
                    r.hitTarget = 2550;
                    r.expTarget = 2550;
                    r.reforge(api, p0, null);
                    CompactGear.fromString(api, p0, r.results[1].code);
                }
                else {
                    final ReforgerMax r2 = new ReforgerMax();
                    r2.canBreakBonuses = true;
                    r2.changeGems = true;
                    r2.enchantBack = true;
                    r2.enchantHands = true;
                    r2.reforge(api, p0, null);
                    CompactGear.fromString(api, p0, r2.results[r2.resultBestIndex].code);
                }
                System.out.println(p0.gearAndExtraStat(StatT.MASTERY) + ":" + p0.gearAndExtraStat(StatT.HASTE) + ":" + p0.gearAndExtraStat(StatT.CRIT));
                System.out.println();
                final String code = CompactGear.toString(p0);
                try {
                    final FileWriter w = new FileWriter(file);
                    w.write(code);
                    w.close();
                }
                catch (IOException err) {
                    System.err.println(file + ": " + err);
                }
            }
        }
    }
    
    static void rankProfiles(final API api, final File profDir, final String script, final File outDir) {
        class X
        {
            final int delay = 0;
            
            X(final int delay) {
            }
        }
        final ArrayDeque<Pair<Profile, X>> queue = new ArrayDeque<Pair<Profile, X>>();
        for (final File x : profDir.listFiles()) {
            Label_0205: {
                if (x.isFile() && !x.isHidden()) {
                    if (x.getName().endsWith(".txt")) {
                        String code;
                        try {
                            code = Utils.readFile(x);
                        }
                        catch (IOException err) {
                            System.err.println(x + ": " + err);
                            break Label_0205;
                        }
                        final Profile profile = new Profile();
                        profile.spec = SpecT.FERAL;
                        profile.race = RaceT.NIGHT_ELF;
                        profile.profs = (ProfT.ENG.bit | ProfT.ENCH.bit);
                        CompactGear.fromString(api, profile, code);
                        profile.validate();
                        profile.charName = x.getName();
                        queue.add(new Pair<Profile, X>(profile, new X()));
                    }
                }
            }
        }
        System.out.println("Loaded: " + queue.size());
        final int expect = queue.size();
        final Object guard = new Object();
        class R
        {
            final Profile profile = p;
            final FeralSim.TrinketResults results = sim.trinketSim(2000);
            
            R(final Profile profile, final FeralSim.TrinketResults results) {
            }
        }
        final ArrayList<R> results = new ArrayList<R>(expect);
        System.out.println();
        for (int i = 0, e = Runtime.getRuntime().availableProcessors(); i < e; ++i) {
            new Thread() {
                @Override
                public void run() {
                    final FeralSim sim = new FeralSim();
                    while (true) {
                        final Pair<Profile, X> pr;
                        synchronized (queue) {
                            if (queue.isEmpty()) {
                                break;
                            }
                            pr = queue.removeFirst();
                        }
                        final Profile p = pr.car;
                        System.out.println(p.T1.getItemNameOrEmpty() + ":" + p.T2.getItemNameOrEmpty());
                        final FeralSpec spec = new FeralSpec();
                        spec.glyph_sr = true;
                        spec.talent_doc = true;
                        spec.talent_sotf = true;
                        final FeralConfig cfg = new FeralConfig();
                        cfg.buff_ap = true;
                        cfg.buff_crit = true;
                        cfg.buff_mastery = true;
                        cfg.buff_meleeHaste = true;
                        cfg.buff_sp = true;
                        cfg.buff_spellHaste = true;
                        cfg.buff_sta = true;
                        cfg.buff_stat = true;
                        cfg.debuff_bleeding = true;
                        cfg.debuff_meleeTaken = true;
                        cfg.debuff_spellTaken = true;
                        cfg.enable_0comboPS = true;
                        cfg.enable_berserking = true;
                        cfg.enable_refund = true;
                        cfg.enable_ooc = true;
                        cfg.enable_lotp = true;
                        cfg.debuff_armor = FeralSim.WeakenedArmor.ALWAYS;
                        cfg.num_stormlashTotem = 1;
                        cfg.num_shatteringThrow = 0;
                        cfg.num_skullBanner = 1;
                        cfg.pre_ht = true;
                        final FeralConfig feralConfig = cfg;
                        final FeralConfig feralConfig2 = cfg;
                        final StatValue make = StatValue.make(StatT.AGI, 4000);
                        feralConfig2.pot = make;
                        feralConfig.pre_pot = make;
                        cfg.pre_sr = true;
                        cfg.food = StatValue.make(StatT.AGI, 300);
                        cfg.flask = Flask.FLASK_OF_SPRING_BLOSSOMS;
                        cfg.heroism = FeralSim.Heroism.AFTER;
                        cfg.heroismDelay = new IntDist.Const(5000);
                        cfg.opener = FeralSim.Opener.NONE;
                        cfg.generator = FeralSim.Generator.SHRANGLE;
                        cfg.react = new IntDist.Const(250);
                        cfg.delay = new IntDist.Uniform(0, 75);
                        p.syncSetBonuses(cfg.setBonuses);
                        sim.setup(p, null, spec, cfg, null);
                        sim.loadSimc(script, "main");
                        final Encounter_Patchwerk en = new Encounter_Patchwerk();
                        en.canBlock = true;
                        en.canDodge = true;
                        en.canParry = true;
                        en.variance = 0;
                        en.duration = 450000;
                        en.front = false;
                        en.customArmor = -1;
                        en.levelDelta = 3;
                        sim.setupCombat(en);
                        final R r = new R(sim.trinketSim(2000));
                        synchronized (results) {
                            results.add(r);
                        }
                    }
                    synchronized (guard) {
                        guard.notify();
                    }
                }
            }.start();
        }
        synchronized (guard) {
            while (results.size() != expect) {
                try {
                    guard.wait();
                }
                catch (InterruptedException stfu) {}
            }
        }
        for (final R x2 : results) {
            Arrays.sort(x2.results.dps);
            Arrays.sort(x2.results.t1_up);
            Arrays.sort(x2.results.t2_up);
            final int nq = x2.results.dps.length / 20;
            final double min = x2.results.dps[nq];
            final double med = x2.results.dps[x2.results.dps.length / 2];
            final double max = x2.results.dps[x2.results.dps.length - 1 - nq];
            final double mean = Maths.mean(x2.results.dps);
            final double stdev = Maths.stdev(x2.results.dps);
            System.out.println(String.format("%-20s %-48s  %-48s %10.0f %10.0f %10.0f %10.0f %10.0f %5.2f %5.2f %5.2f %5.2f %5.2f %5.2f", x2.profile.charName, x2.profile.T1.getItemName(true), x2.profile.T2.getItemName(true), min, med, max, mean, stdev, 100.0 * x2.results.t1_up[nq], 100.0 * Maths.mean(x2.results.t1_up), 100.0 * x2.results.t1_up[x2.results.t1_up.length - 1 - nq], 100.0 * x2.results.t2_up[nq], 100.0 * Maths.mean(x2.results.t2_up), 100.0 * x2.results.t2_up[x2.results.t2_up.length - 1 - nq]));
            final File outFile = new File(outDir, x2.profile.charName);
            DataOutputStream out = null;
            try {
                out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outFile)));
                final int n = x2.results.dps.length;
                out.writeUTF(x2.profile.T1.getItemName(true));
                out.writeUTF(x2.profile.T2.getItemName(true));
                out.writeInt(n);
                for (int j = 0; j < n; ++j) {
                    out.writeDouble(x2.results.dps[j]);
                    out.writeDouble(x2.results.t1_up[j]);
                    out.writeDouble(x2.results.t2_up[j]);
                }
                out.close();
            }
            catch (IOException err2) {
                Utils.silentClose(out);
                outFile.delete();
            }
        }
    }
}
