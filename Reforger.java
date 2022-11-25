// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.HashSet;
import java.util.Arrays;
import javax.swing.SwingUtilities;
import javax.swing.JProgressBar;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;

public class Reforger
{
    final HashMap<Profile.Slot, StatValue[]> itemMap;
    int[] lowerBound;
    int[] upperBound;
    int[] bound;
    int[][][] space;
    static final Algo RANDOM;
    static final int STEP = 100000;
    static final Algo RANDOM_111;
    static final Algo ALL;
    
    public Reforger() {
        this.itemMap = new HashMap<Profile.Slot, StatValue[]>();
    }
    
    public void clearItems() {
        this.itemMap.clear();
    }
    
    public boolean addSlot(final Profile.Slot slot) {
        if (slot == null || slot.item == null) {
            return false;
        }
        final ArrayList<StatValue> list = new ArrayList<StatValue>();
        for (final StatT x : StatT.REFORGE) {
            final int stat = slot.sumStat_originalItem(x);
            if (stat > 0) {
                list.add(StatValue.make(x, stat));
            }
        }
        if (list.isEmpty()) {
            return false;
        }
        this.itemMap.put(slot, list.toArray(new StatValue[list.size()]));
        return true;
    }
    
    void dump() {
        final HashMap<StatT, ArrayList<StatValue>> valueMap = new HashMap<StatT, ArrayList<StatValue>>();
        for (final StatValue[] array : this.itemMap.values()) {
            final StatValue[] values = array;
            for (final StatValue x : array) {
                ArrayList<StatValue> list = valueMap.get(x.type);
                if (list == null) {
                    list = new ArrayList<StatValue>();
                    valueMap.put(x.type, list);
                }
                list.add(x);
            }
        }
        System.out.println("Reforge Slots: " + this.itemMap.size());
        System.out.println("Reforge Stats: " + valueMap.size());
        for (final ArrayList<StatValue> list2 : valueMap.values()) {
            final StatT type = list2.get(0).type;
            int sum = 0;
            for (final StatValue x : list2) {
                sum += x.value;
            }
            System.out.println(type + " = " + sum + " (" + list2.size() + ")");
        }
    }
    
    static String rowHdr(final StatT[] row) {
        final StringBuilder sb = new StringBuilder(7 + row.length * 10);
        sb.append("      ");
        for (final StatT x : row) {
            sb.append(String.format("%10s", x.abbr));
        }
        return sb.toString();
    }
    
    static String rowStr(final int idx, final int[] row) {
        final StringBuilder sb = new StringBuilder(7 + row.length * 10);
        sb.append(String.format("%3d. ", idx));
        sb.append('[');
        for (final int x : row) {
            sb.append(String.format("%10d", x));
        }
        sb.append(']');
        return sb.toString();
    }
    
    public void reforge(final ReforgeAlgo strat, final Map<StatT, Integer> initMap, final StatT[] universe, long iterCnt, int coreCnt, final LineWriter lw, final AtomicBoolean abort, final JProgressBar prog) {
        if (universe.length == 0) {
            throw new RuntimeException("There are no stats to reforge");
        }
        if (this.itemMap.isEmpty()) {
            throw new RuntimeException("There are no items to reforge");
        }
        final long startTime = System.currentTimeMillis();
        for (final Profile.Slot x : this.itemMap.keySet()) {
            x.setReforge(null);
        }
        if (prog != null) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    prog.setIndeterminate(false);
                    prog.setMaximum(1000);
                    prog.setMinimum(0);
                    prog.setValue(0);
                }
            });
        }
        final HashMap<StatT, Integer> idxMap = new HashMap<StatT, Integer>();
        final StatT[] st = new StatT[universe.length];
        final int[] lb = new int[universe.length];
        final int[] ub = new int[universe.length];
        for (final StatT x2 : universe) {
            final ReforgeAlgo.Span span = strat.constMap.get(x2);
            if (span != null) {
                final int idx = idxMap.size();
                lb[idx] = span.lower;
                ub[idx] = span.upper;
                idxMap.put(st[idx] = x2, idx);
            }
        }
        final int constNum = idxMap.size();
        final int[] lowerBound = Arrays.copyOf(lb, constNum);
        final int[] upperBound = Arrays.copyOf(ub, constNum);
        for (final StatT x3 : strat.weightMap.keySet()) {
            final Integer idxObj = idxMap.get(x3);
            if (idxObj == null) {
                final int idx2 = idxMap.size();
                idxMap.put(st[idx2] = x3, idx2);
            }
        }
        final int statNum = idxMap.size();
        final StatT[] statType = Arrays.copyOf(st, statNum);
        final int[] stat0 = new int[statNum];
        if (initMap != null) {
            for (int i = 0; i < statNum; ++i) {
                final StatT type = statType[i];
                final Integer value = initMap.get(type);
                if (value != null) {
                    stat0[i] = value;
                }
            }
        }
        if (lw != null) {
            lw.add("Universe: %s", Arrays.toString(statType));
        }
        class X
        {
            Profile.Slot slot;
            ReforgePair[] pairs;
            int[][] combos;
        }
        final ArrayList<X> active = new ArrayList<X>(this.itemMap.size());
        final int COMBO_MAX = 64;
        final int[][] comboBuf = new int[64][];
        final ReforgePair[] pairs = new ReforgePair[64];
        for (final Map.Entry<Profile.Slot, StatValue[]> e : this.itemMap.entrySet()) {
            final StatValue[] have = e.getValue();
            final int[] combo0 = new int[statNum];
            final HashSet<StatT> haveSet = new HashSet<StatT>();
            for (final StatValue x4 : have) {
                haveSet.add(x4.type);
                final Integer idx3 = idxMap.get(x4.type);
                if (idx3 != null) {
                    combo0[idx3] = x4.value;
                }
            }
            int num = 0;
            boolean addNull = true;
            final ArrayList<ReforgePair> ignored = new ArrayList<ReforgePair>();
            for (final StatValue src : have) {
                if (!strat.dontSet.contains(src.type)) {
                    final double srcWeight = Utils.getMapDouble(strat.weightMap, src.type, Double.NaN);
                    final Integer srcIdx = idxMap.get(src.type);
                    final boolean srcConst = srcIdx != null && srcIdx < constNum;
                    if (addNull && !srcConst) {
                        for (final Map.Entry<StatT, Double> en : strat.weightMap.entrySet()) {
                            if (haveSet.contains(en.getKey())) {
                                continue;
                            }
                            if (srcWeight != srcWeight || en.getValue() > srcWeight) {
                                ignored.add(null);
                                addNull = false;
                                break;
                            }
                        }
                    }
                    for (final StatT dst : universe) {
                        Label_1193: {
                            if (src.type != dst) {
                                if (!haveSet.contains(dst)) {
                                    final Integer dstIdx = idxMap.get(dst);
                                    final boolean dstConst = dstIdx != null && dstIdx < constNum;
                                    final double dstWeight = Utils.getMapDouble(strat.weightMap, dst, Double.NaN);
                                    final ReforgePair pair = ReforgePair.make(src.type, dst);
                                    Label_1117: {
                                        if (!srcConst && !dstConst) {
                                            if (dstWeight == dstWeight) {
                                                if (srcWeight <= dstWeight) {
                                                    if (dstWeight >= 0.0 || srcWeight == srcWeight) {
                                                        break Label_1117;
                                                    }
                                                }
                                            }
                                            else if (srcWeight <= 0.0 && srcWeight == srcWeight) {
                                                break Label_1117;
                                            }
                                            ignored.add(pair);
                                            break Label_1193;
                                        }
                                        if (!dstConst) {
                                            for (final Map.Entry<StatT, Double> en2 : strat.weightMap.entrySet()) {
                                                if (haveSet.contains(en2.getKey())) {
                                                    continue;
                                                }
                                                if (dstWeight != dstWeight || en2.getValue() > dstWeight) {
                                                    ignored.add(pair);
                                                    break Label_1193;
                                                }
                                            }
                                        }
                                    }
                                    final int[] combo2 = Arrays.copyOf(combo0, statNum);
                                    final int stat2 = src.value;
                                    final int diff = (int)(stat2 * StatT.REFORGE_COEFF);
                                    if (srcIdx != null) {
                                        combo2[srcIdx] = stat2 - diff;
                                    }
                                    if (dstIdx != null) {
                                        combo2[dstIdx] = diff;
                                    }
                                    pairs[num] = pair;
                                    comboBuf[num++] = combo2;
                                }
                            }
                        }
                    }
                }
            }
            if (addNull) {
                System.arraycopy(pairs, 0, pairs, 1, num);
                System.arraycopy(comboBuf, 0, comboBuf, 1, num);
                pairs[0] = null;
                comboBuf[0] = Arrays.copyOf(combo0, statNum);
                ++num;
            }
            if (lw != null) {
                lw.add();
                lw.add("%s > %s", e.getKey(), Arrays.toString(have));
                lw.add(rowHdr(statType));
                for (int j = 0; j < num; ++j) {
                    lw.add(rowStr(j, comboBuf[j]));
                }
                for (final ReforgePair x5 : ignored) {
                    lw.add("Ignored: %s", x5);
                }
            }
            if (num == 1) {
                e.getKey().setReforge(pairs[0]);
                final int[] stats = comboBuf[0];
                for (int k = 0; k < statNum; ++k) {
                    final int[] array3 = stat0;
                    final int n5 = k;
                    array3[n5] += stats[k];
                }
                if (lw == null) {
                    continue;
                }
                lw.add("Decided: %s", pairs[0]);
            }
            else {
                final X temp = new X();
                temp.slot = e.getKey();
                temp.pairs = Arrays.copyOf(pairs, num);
                temp.combos = Arrays.copyOf(comboBuf, num);
                active.add(temp);
            }
        }
        final int itemNum = active.size();
        final int[][][] space = new int[itemNum][][];
        final int[] bound = new int[itemNum];
        long perm = 1L;
        for (int l = 0; l < itemNum; ++l) {
            final int[][] temp2 = active.get(l).combos;
            space[l] = temp2;
            bound[l] = temp2.length;
            perm *= temp2.length;
        }
        if (lw != null) {
            lw.add();
            lw.add("Constraints:");
        }
        for (int idx4 = 0; idx4 < constNum; ++idx4) {
            int sumMax;
            int sumMin = sumMax = stat0[idx4];
            for (int j = 0; j < itemNum; ++j) {
                final int[][] combos = space[j];
                int max;
                int min = max = combos[0][idx4];
                for (int m = 1, k2 = bound[j]; m < k2; ++m) {
                    final int val = combos[m][idx4];
                    if (val < min) {
                        min = val;
                    }
                    else if (val > max) {
                        max = val;
                    }
                }
                sumMin += min;
                sumMax += max;
            }
            if (sumMin > upperBound[idx4]) {
                throw new RuntimeException(String.format("%s constraint impossible: %d > %d", statType[idx4], sumMin, upperBound[idx4]));
            }
            if (sumMax < lowerBound[idx4]) {
                throw new RuntimeException(String.format("%s constraint impossible: %d < %d", statType[idx4], sumMax, lowerBound[idx4]));
            }
            if (lw != null) {
                lw.add("%-20s %6d <= [%6d,%6d] <= %6d", statType[idx4], lowerBound[idx4], sumMin, sumMax, upperBound[idx4]);
            }
        }
        final double[] weight = new double[statNum];
        for (final Map.Entry<StatT, Double> e2 : strat.weightMap.entrySet()) {
            final Integer idx5 = idxMap.get(e2.getKey());
            if (idx5 != null) {
                weight[idx5] = e2.getValue();
            }
        }
        if (lw != null) {
            lw.add();
            lw.add("%-20s %6s %10s", "", "Init", "Weight");
            for (int i2 = 0; i2 < statNum; ++i2) {
                lw.add("%-20s %6d %10.2f", statType[i2], stat0[i2], weight[i2]);
            }
            lw.add();
            lw.add("Bounds: %s", Arrays.toString(bound));
        }
        if (coreCnt < 1) {
            coreCnt = Runtime.getRuntime().availableProcessors();
        }
        Algo algo;
        if (iterCnt < 1L || iterCnt >= perm) {
            iterCnt = perm;
            algo = Reforger.ALL;
            final int[] array4 = bound;
            final int n6 = itemNum - 1;
            ++array4[n6];
        }
        else {
            algo = Reforger.RANDOM_111;
        }
        if (lw != null) {
            lw.add();
            lw.add("Permutations: %s", Fmt.bigNum((double)perm));
            lw.add("Iterations: %d", iterCnt);
            lw.add("Cores: %d", coreCnt);
        }
        final int[] mhc = { -1, -1, -1 };
        Integer idx5 = idxMap.get(StatT.MASTERY);
        if (idx5 != null) {
            mhc[0] = idx5;
        }
        idx5 = idxMap.get(StatT.HASTE);
        if (idx5 != null) {
            mhc[1] = idx5;
        }
        idx5 = idxMap.get(StatT.CRIT);
        if (idx5 != null) {
            mhc[2] = idx5;
        }
        double best_pow = Double.NEGATIVE_INFINITY;
        int[] best_cfg = null;
        if (coreCnt > 1) {
            long start = 0L;
            final long step = (iterCnt + coreCnt - 1L) / coreCnt;
            final Object guard = new Object();
            final ArrayList<Best> bests = new ArrayList<Best>(coreCnt);
            synchronized (guard) {
                final AtomicInteger done = new AtomicInteger();
                long end;
                for (int i3 = 0; start < iterCnt; start = end, ++i3) {
                    end = Math.min(start + step, iterCnt);
                    final Best best = new Best();
                    best.pow = best_pow;
                    best.cfg = new int[itemNum];
                    bests.add(best);
                    final long pos = start;
                    final JProgressBar pb = (i3 == 0) ? prog : null;
                    final Thread t = new Thread() {
                        @Override
                        public void run() {
                            algo.run(pos, end, space, bound, stat0, lowerBound, upperBound, weight, best, mhc, abort, pb);
                            synchronized (guard) {
                                done.incrementAndGet();
                                guard.notify();
                            }
                            if (pb != null) {
                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        pb.setIndeterminate(true);
                                    }
                                });
                            }
                        }
                    };
                    t.setPriority(1);
                    t.start();
                }
                while (done.get() < bests.size()) {
                    try {
                        guard.wait();
                    }
                    catch (InterruptedException err2) {}
                }
            }
            for (final Best x6 : bests) {
                final double pow = x6.pow;
                if (pow > best_pow) {
                    best_pow = pow;
                    best_cfg = x6.cfg;
                }
            }
        }
        else {
            final Best best2 = new Best();
            best2.pow = best_pow;
            best2.cfg = new int[itemNum];
            algo.run(0L, iterCnt, space, bound, stat0, lowerBound, upperBound, weight, best2, mhc, abort, prog);
            best_pow = best2.pow;
            best_cfg = best2.cfg;
        }
        if (abort != null && abort.get()) {
            throw new RuntimeException("Aborted");
        }
        if (best_cfg != null) {
            for (int j2 = 0; j2 < itemNum; ++j2) {
                final X temp3 = active.get(j2);
                try {
                    temp3.slot.setReforge(temp3.pairs[best_cfg[j2]]);
                }
                catch (RuntimeException err) {
                    err.printStackTrace();
                }
            }
        }
        if (lw != null) {
            lw.add();
            lw.add("Best: %s", best_pow);
            lw.add("Config: %s", Arrays.toString(best_cfg));
            lw.add("Reforged: %s", Fmt.msDur(System.currentTimeMillis() - startTime));
        }
    }
    
    static void updateProg(final JProgressBar prog, final double perc) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                prog.setValue((int)(perc * 1000.0));
            }
        });
    }
    
    static {
        RANDOM = new Algo() {
            @Override
            public void run(final long off, final long end, final int[][][] space, final int[] bound, final int[] stat0, final int[] lowerBound, final int[] upperBound, final double[] weight, final Best best, final int[] mhc, final AtomicBoolean abort, final JProgressBar prog) {
                final int statNum = stat0.length;
                final int itemNum = space.length;
                final int[] sum = new int[statNum];
                final int[] cfg = new int[itemNum];
                final Random rng = new Random();
                final int[] best_cfg = best.cfg;
                final int constNum = lowerBound.length;
                double best_pow = best.pow;
                long pos = off;
            Label_0053:
                while (pos < end) {
                    for (long e = Math.min(end, pos + 10000L); pos < e; ++pos) {
                        System.arraycopy(stat0, 0, sum, 0, statNum);
                        for (int j = 0; j < itemNum; ++j) {
                            final int[] array = cfg;
                            final int n = j;
                            final int nextInt = rng.nextInt(bound[j]);
                            array[n] = nextInt;
                            final int c = nextInt;
                            final int[] combo = space[j][c];
                            for (int i = 0; i < constNum; ++i) {
                                final int[] array2 = sum;
                                final int n2 = i;
                                array2[n2] += combo[i];
                            }
                        }
                        for (int k = 0; k < constNum; ++k) {
                            final int val = sum[k];
                            if (val < lowerBound[k]) {
                                continue Label_0053;
                            }
                            if (val > upperBound[k]) {
                                continue Label_0053;
                            }
                        }
                        for (int j = 0; j < itemNum; ++j) {
                            final int[] combo2 = space[j][cfg[j]];
                            for (int l = constNum; l < statNum; ++l) {
                                final int[] array3 = sum;
                                final int n3 = l;
                                array3[n3] += combo2[l];
                            }
                        }
                        double pow = 0.0;
                        for (int l = 0; l < statNum; ++l) {
                            pow += sum[l] * weight[l];
                        }
                        if (pow > best_pow) {
                            best_pow = pow;
                            System.arraycopy(cfg, 0, best_cfg, 0, itemNum);
                        }
                    }
                    if (abort != null && abort.get()) {
                        return;
                    }
                }
                best.pow = best_pow;
            }
        };
        RANDOM_111 = new Algo() {
            @Override
            public void run(final long off, final long end, final int[][][] space, final int[] bound, final int[] stat0, final int[] lowerBound, final int[] upperBound, final double[] weight, final Best best, final int[] mhc, final AtomicBoolean abort, final JProgressBar prog) {
                final int statNum = stat0.length;
                final int itemNum = space.length;
                final int[] sum = new int[statNum];
                final int[] cfg = new int[itemNum];
                final Random rng = new Random();
                final int[] best_cfg = best.cfg;
                final int constNum = lowerBound.length;
                final int mi = mhc[0];
                final int hi = mhc[1];
                final int ci = mhc[2];
                long p0 = 1L;
                for (final int x : bound) {
                    p0 *= x;
                }
                long best_p = 0L;
                double best_pow = best.pow;
                long pos = off;
                while (pos < end) {
                    final long e = Math.min(end, pos + 100000L);
                Label_0484_Outer:
                    while (pos < e) {
                        long p_div;
                        final long p2 = p_div = (long)(rng.nextDouble() * p0);
                        for (int i = 0; i < itemNum; ++i) {
                            final int b = bound[i];
                            cfg[i] = (int)(p_div % b);
                            p_div /= b;
                        }
                        int i = 0;
                        while (true) {
                            while (i < constNum) {
                                int tot = stat0[i];
                                for (int j = 0; j < itemNum; ++j) {
                                    tot += space[j][cfg[j]][i];
                                }
                                if (tot >= lowerBound[i]) {
                                    if (tot <= upperBound[i]) {
                                        sum[i] = tot;
                                        ++i;
                                        continue Label_0484_Outer;
                                    }
                                }
                                ++pos;
                                continue Label_0484_Outer;
                            }
                            for (i = constNum; i < statNum; ++i) {
                                sum[i] = stat0[i];
                            }
                            for (int k = 0; k < itemNum; ++k) {
                                final int[] combo = space[k][cfg[k]];
                                for (int l = constNum; l < statNum; ++l) {
                                    final int[] array = sum;
                                    final int n2 = l;
                                    array[n2] += combo[l];
                                }
                            }
                            final int m = sum[mi];
                            final int h = sum[hi];
                            if (h >= m) {
                                continue;
                            }
                            final int c = sum[ci];
                            if (c >= m) {
                                continue;
                            }
                            double pow = -0.2 * (m - Math.min(h, c));
                            for (int i2 = 0; i2 < statNum; ++i2) {
                                pow += sum[i2] * weight[i2];
                            }
                            if (pow > best_pow) {
                                best_pow = pow;
                                best_p = p2;
                            }
                            continue;
                        }
                    }
                    if (prog != null) {
                        Reforger.updateProg(prog, (pos - off) / (double)(end - off));
                    }
                    if (abort != null && abort.get()) {
                        return;
                    }
                }
                for (int i3 = 0; i3 < itemNum; ++i3) {
                    final int b2 = bound[i3];
                    best_cfg[i3] = (int)(best_p % b2);
                    best_p /= b2;
                }
                best.pow = best_pow;
            }
        };
        ALL = new Algo() {
            @Override
            public void run(final long off, final long end, final int[][][] space, final int[] bound, final int[] stat0, final int[] lowerBound, final int[] upperBound, final double[] weight, final Best best, final int[] mhc, final AtomicBoolean abort, final JProgressBar prog) {
                final int statNum = stat0.length;
                final int itemNum = space.length;
                final int constNum = lowerBound.length;
                final int[] sum = new int[statNum];
                final int[] cfg = new int[itemNum];
                final int[] best_cfg = best.cfg;
                double best_pow = best.pow;
                long temp = off;
                for (int i = 0; i < itemNum; ++i) {
                    final int base = bound[i];
                    cfg[i] = (int)(temp % base);
                    temp /= base;
                }
                long pos = off;
                while (pos < end) {
                    final long next = Math.min(end, pos + 100000L);
                Label_0365_Outer:
                    while (pos < next) {
                        int j = 0;
                        while (true) {
                            while (j < constNum) {
                                int tot = stat0[j];
                                for (int k = 0; k < itemNum; ++k) {
                                    tot += space[k][cfg[k]][j];
                                }
                                if (tot >= lowerBound[j]) {
                                    if (tot <= upperBound[j]) {
                                        sum[j] = tot;
                                        ++j;
                                        continue Label_0365_Outer;
                                    }
                                }
                                for (j = 0; ++cfg[j] == bound[j]; ++j) {
                                    cfg[j] = 0;
                                }
                                ++pos;
                                continue Label_0365_Outer;
                            }
                            for (j = constNum; j < statNum; ++j) {
                                sum[j] = stat0[j];
                            }
                            for (int l = 0; l < itemNum; ++l) {
                                final int[] combo = space[l][cfg[l]];
                                for (int m = constNum; m < statNum; ++m) {
                                    final int[] array = sum;
                                    final int n = m;
                                    array[n] += combo[m];
                                }
                            }
                            double pow = 0.0;
                            for (int m = 0; m < statNum; ++m) {
                                pow += sum[m] * weight[m];
                            }
                            if (pow > best_pow) {
                                best_pow = pow;
                                for (int m = 0; m < itemNum; ++m) {
                                    best_cfg[m] = cfg[m];
                                }
                            }
                            continue;
                        }
                    }
                    if (prog != null) {
                        Reforger.updateProg(prog, (pos - off) / (double)(end - off));
                    }
                    if (abort != null && abort.get()) {
                        return;
                    }
                }
                best.pow = best_pow;
            }
        };
    }
    
    static class Best
    {
        double pow;
        int[] cfg;
    }
    
    interface Algo
    {
        void run(final long p0, final long p1, final int[][][] p2, final int[] p3, final int[] p4, final int[] p5, final int[] p6, final double[] p7, final Best p8, final int[] p9, final AtomicBoolean p10, final JProgressBar p11);
    }
}
