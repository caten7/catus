// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.PriorityQueue;
import java.util.Map;
import java.util.TreeSet;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class ReforgerMax extends ReforgerBase
{
    static final boolean debug = false;
    public static final int DEFAULT_SEARCH_DEPTH = 20000;
    public boolean respectProfEnchants;
    public boolean respectProfGems;
    public boolean respectNullGems;
    public int searchRange;
    public int searchDepth;
    public int resultCount;
    public double[] weights;
    public int hitTarget;
    public int expTarget;
    public boolean changeGems;
    public boolean enchantHands;
    public boolean enchantBack;
    public boolean enchantFeet;
    public boolean canBreakBonuses;
    public double critMax;
    public double critPerAgi;
    public double critPerCrit;
    static final EnchantT[] ENCHANTS_HANDS;
    static final EnchantT[] ENCHANTS_BACK;
    static final EnchantT[] ENCHANTS_FEET;
    static final StatT[] STATS;
    static final int NUM_EXTRA = 6;
    final Comparator<Gem> gemCmp;
    static final Comparator<SortedStat> CMP_sortedStat;
    
    public ReforgerMax() {
        this.respectProfEnchants = true;
        this.respectProfGems = true;
        this.respectNullGems = false;
        this.searchRange = 200;
        this.searchDepth = 20000;
        this.resultCount = 100;
        (this.weights = new double[StatT.NUM])[StatT.AGI.index] = 6.5;
        this.weights[StatT.STR.index] = 2.8;
        this.weights[StatT.AP.index] = 2.8;
        this.weights[StatT.MASTERY.index] = 3.2;
        this.weights[StatT.HASTE.index] = 2.5;
        this.weights[StatT.CRIT.index] = 2.5;
        this.hitTarget = 2550;
        this.expTarget = 2550;
        this.gemCmp = new Comparator<Gem>() {
            @Override
            public int compare(final Gem a, final Gem b) {
                return Double.compare(StatT.getWeight(b, ReforgerMax.this.weights), StatT.getWeight(a, ReforgerMax.this.weights));
            }
        };
    }
    
    private boolean isMutable(final Profile.Slot x) {
        return x.item != null && (this.slotSet == null || this.slotSet.contains(x.type));
    }
    
    static S[] makeForest(final List<S> list) {
        final int tooBig = 5000;
        final ArrayList<S> chunks = new ArrayList<S>();
        final ArrayList<S> queue = new ArrayList<S>();
        int prod = 1;
        for (final S x : list) {
            if (x.perms.length >= 5000) {
                chunks.add(x);
            }
            else {
                prod *= x.perms.length;
                if (prod >= 5000) {
                    chunks.add(makeTree(queue));
                    queue.clear();
                    prod = x.perms.length;
                }
                queue.add(x);
            }
        }
        if (!queue.isEmpty()) {
            chunks.add(makeTree(queue));
        }
        return chunks.toArray(new S[chunks.size()]);
    }
    
    static S makeTree(final Collection<S> list) {
        int len = list.size();
        final S[] temp = list.toArray(new S[list.size()]);
        while (len > 1) {
            final int half = len >>> 1;
            for (int i = 0; i < half; ++i) {
                temp[i] = join(temp[i], temp[i + half]);
            }
            if ((len & 0x1) > 0) {
                temp[half] = temp[len - 1];
            }
            len -= half;
        }
        return temp[0];
    }
    
    static S join(final S left, final S right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        final TreeMap<int[], int[]> map = new TreeMap<int[], int[]>(ReforgerMax.CMP_PERM);
        for (int i = 0; i < left.perms.length; ++i) {
            final int[] perm0 = left.perms[i];
            final int diff0 = left.diffs[i];
            for (int j = 0; j < right.perms.length; ++j) {
                final int[] perm2 = Arrays.copyOf(perm0, perm0.length);
                final int[] perm3 = right.perms[j];
                for (int k = 0; k < perm2.length; ++k) {
                    final int[] array = perm2;
                    final int n2 = k;
                    array[n2] += perm3[k];
                }
                final int diff2 = diff0 + right.diffs[j];
                final int[] ans = map.get(perm2);
                if (ans == null || diff2 < ans[2]) {
                    map.put(perm2, new int[] { i, j, diff2 });
                }
            }
        }
        final int n = map.size();
        final int[][] data = map.values().toArray(new int[n][]);
        final int[] diffs = new int[n];
        for (int l = 0; l < n; ++l) {
            diffs[l] = data[l][2];
        }
        final Joined temp = new Joined();
        temp.perms = map.keySet().toArray(new int[n][]);
        temp.diffs = diffs;
        temp.left = left;
        temp.right = right;
        temp.indexes = data;
        return temp;
    }
    
    private void enchantSlot(final Collection<S> list, final Profile.Slot slot, final EnchantT[] a) {
        if (!this.isMutable(slot)) {
            return;
        }
        if (this.respectProfEnchants && slot.hasProfEnchant()) {
            return;
        }
        final int num = ReforgerMax.STATS.length;
        final int[][] perms = new int[a.length][num];
        final int[] diffs = new int[a.length];
        for (int i = 0; i < a.length; ++i) {
            final int[] v = perms[i];
            for (int j = 0; j < num; ++j) {
                v[j] = a[i].getStat(ReforgerMax.STATS[j]);
            }
            diffs[i] = ((slot.enchant != a[i]) ? 1 : 0);
        }
        slot.setEnchant(null);
        final Slotted_Enchant temp = new Slotted_Enchant();
        temp.index = slot.index;
        temp.perms = perms;
        temp.diffs = diffs;
        temp.enchants = a;
        list.add(temp);
    }
    
    double obj_mhca(final int[] v) {
        double sum = 0.0;
        for (int i = 0; i < ReforgerMax.STATS.length; ++i) {
            sum += v[i] * this.weights[ReforgerMax.STATS[i].index];
        }
        return sum;
    }
    
    private Gem[] reduceGems(final GemT color, final Collection<Gem> all, final boolean breakable) {
        if (all.isEmpty()) {
            return new Gem[1];
        }
        final HashMap<String, ArrayList<Gem>> map = new HashMap<String, ArrayList<Gem>>();
        for (final Gem x : all) {
            final String key = (this.critMax > 0.0) ? (x.getStat(StatT.HIT) + "_" + x.getStat(StatT.EXP) + (breakable ? ("_" + (x.getStat(StatT.CRIT) > 0)) : "")) : ("" + color.matches(x));
            ArrayList<Gem> gems = map.get(key);
            if (gems == null) {
                gems = new ArrayList<Gem>();
                map.put(key, gems);
            }
            gems.add(x);
        }
        final ArrayList<Gem> keep = new ArrayList<Gem>();
        for (final ArrayList<Gem> gems2 : map.values()) {
            Collections.sort(gems2, this.gemCmp);
            int c = 1;
            for (double w0 = StatT.getWeight(gems2.get(0), this.weights); c < gems2.size() && StatT.getWeight(gems2.get(c), this.weights) == w0; ++c) {}
            keep.addAll(gems2.subList(0, c));
        }
        Collections.sort(keep, this.gemCmp);
        return keep.toArray(new Gem[keep.size()]);
    }
    
    @Override
    public void reforge(final API api, final Profile prof0, final Profile armory0) {
        final Profile armory = new Profile();
        if (armory0 != null) {
            armory.importProfile(armory0);
            for (final Profile.Slot x : armory.SLOTS) {
                if (!x.isSameOriginalItem(prof0.SLOTS[x.index])) {
                    x.clear();
                }
            }
        }
        final Profile prof = new Profile();
        prof.importProfile(prof0);
        final int statNum = ReforgerMax.STATS.length;
        final int secondaryNum = statNum - 6;
        final int[] statBase = new int[statNum];
        final ArrayList<S> dimList = new ArrayList<S>();
        if (this.enchantHands) {
            this.enchantSlot(dimList, prof.HANDS, ReforgerMax.ENCHANTS_HANDS);
        }
        if (this.enchantBack) {
            this.enchantSlot(dimList, prof.BACK, ReforgerMax.ENCHANTS_BACK);
        }
        if (this.enchantFeet) {
            this.enchantSlot(dimList, prof.FEET, ReforgerMax.ENCHANTS_FEET);
        }
        for (final Profile.Slot x2 : prof.SLOTS) {
            for (int i = 0; i < statNum; ++i) {
                final int[] array = statBase;
                final int n9 = i;
                array[n9] += x2.sumStat_enchant(ReforgerMax.STATS[i]);
            }
        }
        if (this.changeGems) {
            final IntSet gemIds = new IntSet();
            if (this.critMax > 0.0) {
                gemIds.addAll(76697, 76699, 76700);
                gemIds.addAll(76667, 76659, 76671);
                gemIds.addAll(76642, 76643, 76641);
            }
            else {
                gemIds.add(76692);
                gemIds.addAll(76670, 76666, 76658);
                gemIds.addAll(76680, 76687);
                gemIds.addAll(76697, 76699, 76700);
                gemIds.addAll(76667, 76659, 76671);
                gemIds.addAll(76642, 76643, 76641);
                final double pow = this.weights[StatT.PVP_POW.index];
                final double res = this.weights[StatT.PVP_RES.index];
                if (pow != 0.0) {
                    gemIds.addAll(76637);
                    gemIds.addAll(76644, 76649, 76650);
                    gemIds.addAll(89680);
                }
                if (pow != 0.0 && res != 0.0) {
                    gemIds.addAll(76647);
                }
                if (res != 0.0) {
                    gemIds.addAll(76646, 76657);
                    gemIds.addAll(76675, 76676);
                    gemIds.addAll(76701);
                }
                if (this.weights[StatT.STA.index] != 0.0) {
                    gemIds.add(76639);
                }
            }
            final Map<GemT, Gem[]> gemMap = new HashMap<GemT, Gem[]>();
            final Map<GemT, ArrayList<Gem>> map = new HashMap<GemT, ArrayList<Gem>>();
            for (final GemT x3 : GemT.COLORS) {
                map.put(x3, new ArrayList<Gem>());
            }
            for (final Gem x4 : api.loadGems_toArray(gemIds.toArray())) {
                for (final GemT c : GemT.COLORS) {
                    if (c.matches(x4)) {
                        map.get(c).add(x4);
                    }
                }
            }
            for (final GemT x3 : GemT.COLORS) {
                gemMap.put(x3, this.reduceGems(x3, map.get(x3), false));
            }
            if (this.canBreakBonuses) {
                final HashSet<Gem> set = new HashSet<Gem>();
                for (final GemT x5 : GemT.COLORS) {
                    set.addAll((Collection<?>)map.get(x5));
                }
                for (final GemT x5 : GemT.COLORS) {
                    gemMap.put(x5, this.reduceGems(x5, set, true));
                }
            }
            final ArrayList<S> gemList = new ArrayList<S>();
            for (final Profile.Slot slot : prof.SLOTS) {
                final int len = slot.getSocketCount();
                if (len != 0) {
                    if (this.isMutable(slot)) {
                        final Gem[][] gemSpace = new Gem[len][];
                        int tot = 1;
                        for (int j = 0; j < len; ++j) {
                            final GemT socket = slot.getSocketAt(j);
                            final Gem gem = slot.getGemAt(j);
                            Gem[] gems;
                            if (!socket.colored()) {
                                gems = new Gem[] { gem };
                            }
                            else if (gem != null && gem.requiredProf != null && this.respectProfGems) {
                                gems = new Gem[] { gem };
                            }
                            else if (gem == null && this.respectNullGems) {
                                gems = new Gem[] { gem };
                            }
                            else {
                                gems = gemMap.get(socket);
                            }
                            gemSpace[j] = gems;
                            tot *= gems.length;
                        }
                        final StatValue bonus = slot.item.socketBonus;
                        int bonusIndex = -1;
                        if (bonus != null) {
                            for (int k = 0; k < ReforgerMax.STATS.length; ++k) {
                                if (ReforgerMax.STATS[k] == bonus.type) {
                                    bonusIndex = k;
                                    break;
                                }
                            }
                        }
                        final TreeMap<int[], Set<Gem[]>> map2 = new TreeMap<int[], Set<Gem[]>>(ReforgerMax.CMP_PERM);
                        for (int p = 0; p < tot; ++p) {
                            final Gem[] gems2 = new Gem[len];
                            Utils.decompose(p, gemSpace, gems2);
                            final int[] stats = new int[ReforgerMax.STATS.length];
                            for (final Gem x6 : gems2) {
                                StatT.add(x6, ReforgerMax.STATS, stats);
                            }
                            if (slot.item.hasSocketBonus(gems2) && bonusIndex >= 0) {
                                final int[] array3 = stats;
                                final int n18 = bonusIndex;
                                array3[n18] += bonus.value;
                            }
                            Set<Gem[]> list = map2.get(stats);
                            if (list == null) {
                                list = new TreeSet<Gem[]>(Item.CMP_array);
                                map2.put(stats, list);
                            }
                            list.add(gems2);
                        }
                        final int num = map2.size();
                        final int[] diffs = new int[num];
                        final int[][] perms = new int[num][];
                        final Gem[][][] space = new Gem[num][][];
                        int idx = 0;
                        for (final Map.Entry<int[], Set<Gem[]>> e : map2.entrySet()) {
                            perms[idx] = e.getKey();
                            final Set<Gem[]> set2 = e.getValue();
                            final Gem[][] gems3 = set2.toArray(new Gem[set2.size()][]);
                            diffs[idx] = ReforgerBase.gemDiffs(slot.gems, gems3[0], len);
                            space[idx++] = gems3;
                        }
                        final Slotted_Gems temp = new Slotted_Gems();
                        temp.index = slot.index;
                        temp.perms = perms;
                        temp.diffs = diffs;
                        temp.space = space;
                        gemList.add(temp);
                    }
                    else {
                        for (int l = 0; l < statNum; ++l) {
                            final int[] array4 = statBase;
                            final int n19 = l;
                            array4[n19] += slot.sumStat_gems(ReforgerMax.STATS[l]);
                        }
                    }
                }
            }
            final S temp2 = makeTree(gemList);
            dimList.add(temp2);
        }
        else {
            for (final Profile.Slot x2 : prof.SLOTS) {
                for (int i = 0; i < statNum; ++i) {
                    final int[] array5 = statBase;
                    final int n21 = i;
                    array5[n21] += x2.sumStat_gems(ReforgerMax.STATS[i]);
                }
            }
        }
        for (final Profile.Slot x2 : prof.SLOTS) {
            if (this.isMutable(x2)) {
                final int[] slotStat = new int[statNum];
                int count = 0;
                for (int m = 0; m < statNum; ++m) {
                    final int[] array6 = slotStat;
                    final int n23 = m;
                    final int sumStat_originalItem = x2.sumStat_originalItem(ReforgerMax.STATS[m]);
                    array6[n23] = sumStat_originalItem;
                    final int sum = sumStat_originalItem;
                    if (m < secondaryNum && sum > 0) {
                        ++count;
                    }
                }
                final int len2 = 1 + count * (secondaryNum - count);
                final ReforgePair[] reforges = new ReforgePair[len2];
                final int[] diffs2 = new int[len2];
                final int[][] perms2 = new int[len2][];
                perms2[0] = slotStat;
                if (x2.reforge != null) {
                    diffs2[0] = 1;
                }
                int idx2 = 1;
                for (int j2 = 0; j2 < secondaryNum; ++j2) {
                    final int stat = slotStat[j2];
                    if (stat > 0) {
                        for (int k2 = 0; k2 < secondaryNum; ++k2) {
                            if (slotStat[k2] == 0) {
                                final int[] copy = Arrays.copyOf(slotStat, statNum);
                                final int chg = (int)(stat * StatT.REFORGE_COEFF);
                                copy[j2] = stat - chg;
                                copy[k2] = chg;
                                final ReforgePair r = ReforgePair.make(ReforgerMax.STATS[j2], ReforgerMax.STATS[k2]);
                                if (x2.reforge != (reforges[idx2] = r)) {
                                    diffs2[idx2] = 1;
                                }
                                perms2[idx2++] = copy;
                            }
                        }
                    }
                }
                final Slotted_Reforge temp3 = new Slotted_Reforge();
                temp3.perms = perms2;
                temp3.diffs = diffs2;
                temp3.reforges = reforges;
                temp3.index = x2.index;
                dimList.add(temp3);
            }
            else {
                for (int i = 0; i < statNum; ++i) {
                    final int[] array7 = statBase;
                    final int n24 = i;
                    array7[n24] += x2.sumStat_reforgedItem(ReforgerMax.STATS[i]);
                }
            }
        }
        if (dimList.isEmpty()) {
            throw new RuntimeException("There is nothing to reforge.");
        }
        final ArrayList<S> many = new ArrayList<S>();
        final ArrayList<S> ones = new ArrayList<S>();
        for (final S x7 : dimList) {
            if (x7.perms.length == 1) {
                ones.add(x7);
                final int[] perm = x7.perms[0];
                for (int l = 0; l < statNum; ++l) {
                    final int[] array8 = statBase;
                    final int n25 = l;
                    array8[n25] += perm[l];
                }
            }
            else {
                many.add(x7);
            }
        }
        final S decided = ones.isEmpty() ? null : makeTree(ones);
        final S[] dimArr = makeForest(many);
        final int dimNum = dimArr.length;
        for (int i2 = 0; i2 < statNum; ++i2) {
            final int[] array9 = statBase;
            final int n26 = i2;
            array9[n26] += prof.raceAndProfStat(ReforgerMax.STATS[i2]);
        }
        final int[][] sum_space = new int[dimNum][];
        for (int i = 0; i < dimNum; ++i) {
            final int[][] perms3 = dimArr[i].perms;
            final int n = perms3.length;
            final int[] dst = new int[n];
            for (int j3 = 0; j3 < n; ++j3) {
                int sum2 = 0;
                final int[] v = perms3[j3];
                for (int k3 = 0; k3 < statNum; ++k3) {
                    sum2 += v[k3];
                }
                dst[j3] = sum2;
            }
            sum_space[i] = dst;
        }
        final int[] statMin = new int[statNum];
        final int[] statMax = new int[statNum];
        for (int j4 = 0; j4 < statNum; ++j4) {
            int sumMax;
            int sumMin = sumMax = statBase[j4];
            for (int i3 = 0; i3 < dimNum; ++i3) {
                final int[][] perms4 = dimArr[i3].perms;
                int max;
                int min = max = perms4[0][j4];
                for (int k2 = 1; k2 < perms4.length; ++k2) {
                    final int val = perms4[k2][j4];
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
            statMin[j4] = sumMin;
            statMax[j4] = sumMax;
        }
        final int hitMin = Math.max(statMin[0], this.hitTarget);
        final int hitMax = Math.min(statMax[0], hitMin + this.searchRange);
        final int expMin = Math.max(statMin[1], this.expTarget);
        final int expMax = Math.min(statMax[1], expMin + this.searchRange);
        if (hitMax < hitMin) {
            throw new RuntimeException(String.format("Malformed hit search range: [%d, %d]", hitMin, hitMax));
        }
        if (expMax < expMin) {
            throw new RuntimeException(String.format("Malformed expertise search range: [%d, %d]", expMin, expMax));
        }
        final int sum_maxCap = 1000;
        final int hit0 = statBase[0];
        final int exp0 = statBase[1];
        int keep = this.searchDepth;
        PriorityQueue<Scored> pq = new PriorityQueue<Scored>(2 * keep, Scored.CMP);
        final int[] bound_h = new int[dimNum];
        final int[][] space_h = new int[dimNum][];
        long tot_h = 1L;
        for (int i4 = 0; i4 < dimNum; ++i4) {
            final int[][] perms5 = dimArr[i4].perms;
            final int n2 = perms5.length;
            final int[] dst2 = new int[n2];
            int c2 = 0;
            int j5 = 0;
        Label_2875:
            while (j5 < n2) {
                final int hit2 = perms5[j5][0];
                while (true) {
                    for (int k4 = 0; k4 < c2; ++k4) {
                        if (hit2 == dst2[k4]) {
                            ++j5;
                            continue Label_2875;
                        }
                    }
                    dst2[c2++] = hit2;
                    continue;
                }
            }
            space_h[i4] = dst2;
            final long n27 = tot_h;
            final int[] array10 = bound_h;
            final int n28 = i4;
            final int n29 = c2;
            array10[n28] = n29;
            tot_h = n27 * n29;
        }
        this.progBar.determinate();
        final long t_freq = 50L;
        long t_next = System.currentTimeMillis() + t_freq;
        final int[][] space_e = new int[dimNum][];
        for (int i5 = 0; i5 < dimNum; ++i5) {
            space_e[i5] = new int[dimArr[i5].perms.length];
        }
        final int[] cfg_h = new int[dimNum];
        final int[] cfg_e = new int[dimNum];
        final int[] bound_e = new int[dimNum];
        final int[] value = new int[dimNum];
        final int[] diff_cfgs = new int[sum_maxCap];
        final int[] diff_sums = new int[sum_maxCap];
        final int[][] cfgs = new int[sum_maxCap][];
        for (long p2 = 0L; p2 < tot_h; ++p2) {
            int hit3 = hit0;
            for (int i6 = 0; i6 < dimNum; ++i6) {
                hit3 += space_h[i6][cfg_h[i6]];
            }
            if (hit3 >= hitMin && hit3 <= hitMax) {
                for (int i6 = 0; i6 < dimNum; ++i6) {
                    cfg_e[i6] = 0;
                }
                long tot_e = 1L;
                for (int i7 = 0; i7 < dimNum; ++i7) {
                    final int[] array11 = value;
                    final int n30 = i7;
                    final int n31 = space_h[i7][cfg_h[i7]];
                    array11[n30] = n31;
                    final int match = n31;
                    final int[][] perms6 = dimArr[i7].perms;
                    final int n3 = perms6.length;
                    int c3 = 0;
                    final int[] dst3 = space_e[i7];
                    for (final int[] v2 : perms6) {
                        Label_3279: {
                            if (v2[0] == match) {
                                final int exp2 = v2[1];
                                for (int k5 = 0; k5 < c3; ++k5) {
                                    if (dst3[k5] == exp2) {
                                        break Label_3279;
                                    }
                                }
                                dst3[c3++] = exp2;
                            }
                        }
                    }
                    final long n32 = tot_e;
                    final int[] array12 = bound_e;
                    final int n33 = i7;
                    final int n34 = c3;
                    array12[n33] = n34;
                    tot_e = n32 * n34;
                }
                for (long q = 0L; q < tot_e; ++q) {
                    int exp3 = exp0;
                    for (int i8 = 0; i8 < dimNum; ++i8) {
                        exp3 += space_e[i8][cfg_e[i8]];
                    }
                    if (exp3 >= expMin && exp3 <= expMax) {
                        final int score = Math.abs(hit3 - this.hitTarget) + Math.abs(exp3 - this.expTarget);
                        if (pq.size() < keep || score < pq.peek().score) {
                            int diffs3 = 1;
                            cfgs[0] = new int[dimNum];
                            for (int i9 = 0; i9 < dimNum; ++i9) {
                                final int match_h = value[i9];
                                final int match_e = space_e[i9][cfg_e[i9]];
                                final int[] sums = sum_space[i9];
                                int c4 = 0;
                                final int[][] perms7 = dimArr[i9].perms;
                            Label_3567:
                                for (int j7 = 0; j7 < perms7.length; ++j7) {
                                    final int[] v3 = perms7[j7];
                                    if (v3[0] == match_h && v3[1] == match_e) {
                                        final int sum3 = sums[j7];
                                        for (int k6 = 0; k6 < c4; ++k6) {
                                            if (diff_sums[k6] == sum3) {
                                                continue Label_3567;
                                            }
                                        }
                                        diff_cfgs[c4] = j7;
                                        diff_sums[c4++] = sum3;
                                    }
                                }
                                for (int j7 = 0, e2 = diffs3; j7 < e2; ++j7) {
                                    final int[] src = cfgs[j7];
                                    src[i9] = diff_cfgs[0];
                                    for (int k6 = 1; k6 < c4; ++k6) {
                                        final int[] dst4 = Arrays.copyOf(src, dimNum);
                                        dst4[i9] = diff_cfgs[k6];
                                        cfgs[diffs3++] = dst4;
                                    }
                                }
                            }
                            for (int i9 = 0; i9 < diffs3; ++i9) {
                                pq.add(new Scored(score, cfgs[i9], null));
                            }
                            while (pq.size() > keep) {
                                pq.remove();
                            }
                        }
                    }
                    for (int i8 = 0; i8 < dimNum && ++cfg_e[i8] == bound_e[i8]; ++i8) {
                        cfg_e[i8] = 0;
                    }
                }
            }
            for (int i6 = 0; i6 < dimNum && ++cfg_h[i6] == bound_h[i6]; ++i6) {
                cfg_h[i6] = 0;
            }
            final long t = System.currentTimeMillis();
            if (t > t_next) {
                if (this.abort.get()) {
                    throw new RuntimeException("Aborted");
                }
                this.progBar.fraction(p2 / (double)tot_h);
                t_next = t_freq + t;
            }
        }
        this.progBar.indeterminate();
        if (pq.isEmpty()) {
            throw new RuntimeException("Found zero solutions.  Try relaxing the hit/exp constraints.");
        }
        final int num2 = pq.size();
        final Scored[] he_cfgs = pq.toArray(new Scored[num2]);
        Arrays.sort(he_cfgs, 0, num2, (Comparator<? super Scored>)Collections.reverseOrder((Comparator<? super T>)pq.comparator()));
        keep = this.resultCount;
        pq = new PriorityQueue<Scored>(2 * keep, Scored.CMP);
        final int[] bound = new int[dimNum];
        this.progBar.determinate();
        final long t_freq2 = 50L;
        long t_next2 = System.currentTimeMillis() + t_freq2;
        if (this.critMax > 0.0) {
            final double cr0 = this.critify(statBase);
            final int[][] index_c = new int[dimNum][];
            final double[][] space_c = new double[dimNum][];
            final int[][] index_hc = new int[dimNum][];
            final int[][][] space_hc = new int[dimNum][][];
            for (int i10 = 0; i10 < dimNum; ++i10) {
                final int n4 = dimArr[i10].perms.length;
                index_c[i10] = new int[n4];
                space_c[i10] = new double[n4];
                index_hc[i10] = new int[n4];
                space_hc[i10] = new int[n4][];
            }
            final int[] bound_he = new int[dimNum];
            final int[] bound_c = new int[dimNum];
            final int[] bound_hc = new int[dimNum];
            final int[] cfg_c = new int[dimNum];
            final int[] cfg_hc = new int[dimNum];
            final double[] value2 = new double[dimNum];
            double critMin = 0.0;
            for (int g = 0; g < he_cfgs.length; ++g) {
                final Scored he = he_cfgs[g];
                long tot_c = 1L;
                for (int i11 = 0; i11 < dimNum; ++i11) {
                    final int[][] perms8 = dimArr[i11].perms;
                    final int idx3 = he.cfg[i11];
                    final int[] sums2 = sum_space[i11];
                    final int[] match2 = perms8[idx3];
                    final int match_sum = sums2[idx3];
                    final int match_h2 = match2[0];
                    final int match_e2 = match2[1];
                    final int[] index = index_c[i11];
                    final double[] dst5 = space_c[i11];
                    int n5 = 0;
                    int c5 = 0;
                Label_4337:
                    for (int j8 = 0; j8 < perms8.length; ++j8) {
                        final int[] v4 = perms8[j8];
                        if (v4[0] == match_h2 && v4[1] == match_e2 && sums2[j8] == match_sum) {
                            index[n5++] = j8;
                            final double cr2 = this.critify(v4);
                            for (int k7 = 0; k7 < c5; ++k7) {
                                if (dst5[k7] == cr2) {
                                    continue Label_4337;
                                }
                            }
                            dst5[c5++] = cr2;
                        }
                    }
                    bound_he[i11] = n5;
                    final long n35 = tot_c;
                    final int[] array13 = bound_c;
                    final int n36 = i11;
                    final int n37 = c5;
                    array13[n36] = n37;
                    tot_c = n35 * n37;
                }
                for (int i11 = 0; i11 < dimNum; ++i11) {
                    cfg_c[i11] = 0;
                }
                for (long p3 = 0L; p3 < tot_c; ++p3) {
                    double cr3 = cr0;
                    for (int i12 = 0; i12 < dimNum; ++i12) {
                        final double n38 = cr3;
                        final double[] array14 = value2;
                        final int n39 = i12;
                        final double n40 = space_c[i12][cfg_c[i12]];
                        array14[n39] = n40;
                        cr3 = n38 + n40;
                    }
                    if (cr3 <= this.critMax && cr3 >= critMin) {
                        long tot_hc = 1L;
                        for (int i13 = 0; i13 < dimNum; ++i13) {
                            int c4 = 0;
                            final double match3 = value2[i13];
                            final int[] sub = index_hc[i13];
                            final int[][] src2 = dimArr[i13].perms;
                            final int[] index2 = index_c[i13];
                            final int[][] dst6 = space_hc[i13];
                            for (int j9 = 0, e3 = bound_he[i13]; j9 < e3; ++j9) {
                                final int k7 = index2[j9];
                                final int[] v5 = src2[k7];
                                if (this.critify(v5) == match3) {
                                    sub[c4] = k7;
                                    dst6[c4++] = v5;
                                }
                            }
                            final long n41 = tot_hc;
                            final int[] array15 = bound_hc;
                            final int n42 = i13;
                            final int n43 = c4;
                            array15[n42] = n43;
                            tot_hc = n41 * n43;
                        }
                        for (int i13 = 0; i13 < dimNum; ++i13) {
                            cfg_hc[i13] = 0;
                        }
                        double best = 0.0;
                        long bestIndex = -1L;
                        for (long q2 = 0L; q2 < tot_hc; ++q2) {
                            double sum4 = 0.0;
                            for (int i14 = 0; i14 < dimNum; ++i14) {
                                final int[] v6 = space_hc[i14][cfg_hc[i14]];
                                sum4 += this.obj_mhca(v6);
                            }
                            if (sum4 > best) {
                                best = sum4;
                                bestIndex = q2;
                            }
                            for (int i14 = 0; i14 < dimNum && ++cfg_hc[i14] == bound_hc[i14]; ++i14) {
                                cfg_hc[i14] = 0;
                            }
                        }
                        if (bestIndex >= 0L) {
                            for (int i15 = 0; i15 < dimNum; ++i15) {
                                final int b = bound_hc[i15];
                                cfg_hc[i15] = (int)(bestIndex % b);
                                bestIndex /= b;
                            }
                            final double score2 = -best;
                            if (pq.size() < keep || score2 < pq.peek().score) {
                                final int[] cfg = new int[dimNum];
                                final int[] sum5 = Arrays.copyOf(statBase, statNum);
                                for (int i14 = 0; i14 < dimNum; ++i14) {
                                    final int idx4 = cfg_hc[i14];
                                    cfg[i14] = index_hc[i14][idx4];
                                    final int[] perm2 = space_hc[i14][idx4];
                                    for (int j10 = 0; j10 < statNum; ++j10) {
                                        final int[] array16 = sum5;
                                        final int n44 = j10;
                                        array16[n44] += perm2[j10];
                                    }
                                }
                                final Scored temp4 = new Scored(score2, cfg, sum5);
                                pq.add(temp4);
                                while (pq.size() > keep) {
                                    pq.remove();
                                }
                                critMin = critMin * 0.95 + 0.05 * (cr3 - 0.005);
                            }
                        }
                    }
                    for (int i12 = 0; i12 < dimNum && ++cfg_c[i12] == bound_c[i12]; ++i12) {
                        cfg_c[i12] = 0;
                    }
                }
                final long t2 = System.currentTimeMillis();
                if (t2 > t_next2) {
                    if (this.abort.get()) {
                        break;
                    }
                    this.progBar.fraction(g / (double)he_cfgs.length);
                    t_next2 = t_freq2 + t2;
                }
            }
        }
        else {
            final int[][] index_mhc = new int[dimNum][];
            final int[][][] space_mhc = new int[dimNum][][];
            for (int i16 = 0; i16 < dimNum; ++i16) {
                final int n6 = dimArr[i16].perms.length;
                index_mhc[i16] = new int[n6];
                space_mhc[i16] = new int[n6][];
            }
            for (int g2 = 0; g2 < he_cfgs.length; ++g2) {
                final Scored he2 = he_cfgs[g2];
                long tot2 = 1L;
                double score3 = 0.0;
                for (int i17 = 0; i17 < dimNum; ++i17) {
                    final int[][] perms9 = dimArr[i17].perms;
                    final int idx5 = he2.cfg[i17];
                    final int[] sums3 = sum_space[i17];
                    final int[] match4 = perms9[idx5];
                    final int match_sum2 = sums3[idx5];
                    final int match_h3 = match4[0];
                    final int match_e3 = match4[1];
                    final int[] index3 = index_mhc[i17];
                    final int[][] space2 = space_mhc[i17];
                    int c6 = 0;
                    double best2 = 0.0;
                    for (int j11 = 0; j11 < perms9.length; ++j11) {
                        final int[] v7 = perms9[j11];
                        if (v7[0] == match_h3 && v7[1] == match_e3 && sums3[j11] == match_sum2) {
                            final double s = this.obj_mhca(v7);
                            if (s >= best2) {
                                if (s > best2) {
                                    c6 = 0;
                                    best2 = s;
                                }
                                index3[c6] = j11;
                                space2[c6++] = v7;
                            }
                        }
                    }
                    score3 -= best2;
                    final long n45 = tot2;
                    final int[] array17 = bound;
                    final int n46 = i17;
                    final int n47 = c6;
                    array17[n46] = n47;
                    tot2 = n45 * n47;
                }
                if (pq.size() < keep || score3 < pq.peek().score) {
                    final int[] cfg2 = new int[dimNum];
                    final int[] sum6 = new int[statNum];
                    for (int i18 = 0; i18 < dimNum; ++i18) {
                        cfg2[i18] = index_mhc[i18][0];
                        final int[] perm3 = space_mhc[i18][0];
                        for (int j12 = 0; j12 < statNum; ++j12) {
                            final int[] array18 = sum6;
                            final int n48 = j12;
                            array18[n48] += perm3[j12];
                        }
                    }
                    final Scored temp5 = new Scored(score3, cfg2, sum6);
                    pq.add(temp5);
                }
                final long t3 = System.currentTimeMillis();
                if (t3 > t_next2) {
                    if (this.abort.get()) {
                        break;
                    }
                    this.progBar.fraction(g2 / (double)he_cfgs.length);
                    t_next2 = t_freq2 + t3;
                }
            }
        }
        if (!pq.isEmpty()) {
            this.progBar.indeterminate();
            int num3 = pq.size();
            final Scored[] cfgs2 = pq.toArray(new Scored[num3]);
            Arrays.sort(cfgs2, 0, num3, (Comparator<? super Scored>)Collections.reverseOrder((Comparator<? super T>)pq.comparator()));
            if (num3 > this.resultCount) {
                num3 = this.resultCount;
            }
            final ArrayList<ProfilePerm> list2 = new ArrayList<ProfilePerm>(num3);
            final StringBuilder sb = new StringBuilder();
            Profile rel = prof0;
            this.resultBestIndex = 1;
            if (armory0 != null) {
                list2.add(this.makeProfilePerm("Armory", armory0));
                rel = armory0;
                ++this.resultBestIndex;
            }
            list2.add(this.makeProfilePerm("Prior Reforging", prof0));
            final SortedStat[] sorted = { new SortedStat(StatT.MASTERY), new SortedStat(StatT.HASTE), new SortedStat(StatT.CRIT) };
            final StatT[] relative = Arrays.copyOfRange(ReforgerMax.STATS, secondaryNum, statNum);
            for (final Scored temp6 : cfgs2) {
                if (decided != null) {
                    decided.applyChoice(prof, 0);
                }
                for (int i19 = 0; i19 < dimNum; ++i19) {
                    dimArr[i19].applyChoice(prof, temp6.cfg[i19]);
                }
                prof.minimizeGems(armory, this.slotSet);
                final int hit4 = prof.gearAndExtraStat(StatT.HIT);
                final int exp4 = prof.gearAndExtraStat(StatT.EXP);
                for (final SortedStat x8 : sorted) {
                    x8.value = prof.gearAndExtraStat(x8.stat);
                }
                Arrays.sort(sorted, ReforgerMax.CMP_sortedStat);
                sb.setLength(0);
                sb.append(hit4);
                sb.append(' ');
                sb.append(StatT.HIT.abbr);
                sb.append(", ");
                sb.append(exp4);
                sb.append(' ');
                sb.append(StatT.EXP.abbr);
                for (final SortedStat x8 : sorted) {
                    sb.append(", ");
                    sb.append(x8.value);
                    sb.append(' ');
                    sb.append(x8.stat.abbr);
                }
                for (final StatT x9 : relative) {
                    final int delta = prof.gearAndExtraStat(x9) - rel.gearAndExtraStat(x9);
                    if (delta != 0) {
                        sb.append(String.format(", %+d %s", delta, x9));
                    }
                }
                list2.add(this.makeProfilePerm(sb.toString(), prof));
            }
            this.results = list2.toArray(new ProfilePerm[list2.size()]);
            return;
        }
        if (this.abort.get()) {
            throw new RuntimeException("Aborted");
        }
        throw new RuntimeException("Found zero solutions.");
    }
    
    ProfilePerm makeProfilePerm(final String name, final Profile p) {
        return new ProfilePerm(String.format("[%.0f] %s", this.obj_fromProfile(p), name), CompactGear.toString(p));
    }
    
    double obj_fromProfile(final Profile p) {
        final int n = ReforgerMax.STATS.length;
        final int[] v = new int[n];
        for (int i = 0; i < n; ++i) {
            v[i] = p.gearAndExtraStat(ReforgerMax.STATS[i]);
        }
        return this.obj_mhca(v);
    }
    
    double critify(final int[] v) {
        return this.critPerCrit * v[3] + this.critPerAgi * v[5];
    }
    
    static {
        ENCHANTS_HANDS = new EnchantT[] { EnchantT.HANDS_EXP, EnchantT.HANDS_HASTE, EnchantT.HANDS_MASTERY, EnchantT.HANDS_STR };
        ENCHANTS_BACK = new EnchantT[] { EnchantT.BACK_CRIT, EnchantT.BACK_HIT };
        ENCHANTS_FEET = new EnchantT[] { EnchantT.FEET_AGI, EnchantT.FEET_MASTERY, EnchantT.FEET_HASTE, EnchantT.FEET_HIT };
        STATS = new StatT[] { StatT.HIT, StatT.EXP, StatT.MASTERY, StatT.CRIT, StatT.HASTE, StatT.AGI, StatT.STR, StatT.AP, StatT.PVP_POW, StatT.PVP_RES, StatT.STA };
        CMP_sortedStat = new Comparator<SortedStat>() {
            @Override
            public int compare(final SortedStat a, final SortedStat b) {
                return b.value - a.value;
            }
        };
    }
    
    abstract static class S
    {
        int[][] perms;
        int[] diffs;
        
        abstract void applyChoice(final Profile p0, final int p1);
        
        void dump() {
            System.out.println(this.getClass().toString());
            this.dumpPerms();
            System.out.println();
        }
        
        void dumpPerms() {
            for (int i = 0; i < this.perms.length; ++i) {
                final StringBuilder sb = new StringBuilder();
                sb.append("[");
                sb.append(i);
                sb.append("] ");
                final int[] perm = this.perms[i];
                for (int j = 0; j < perm.length; ++j) {
                    sb.append(String.format("%6d", perm[j]));
                }
                sb.append(" (");
                sb.append(this.diffs[i]);
                sb.append(")");
                System.out.println(sb.toString());
            }
        }
    }
    
    abstract static class Slotted extends S
    {
        int index;
    }
    
    static class Joined extends S
    {
        S left;
        S right;
        int[][] indexes;
        
        @Override
        void dumpPerms() {
            System.out.println("<Joined>");
        }
        
        @Override
        void applyChoice(final Profile p, final int c) {
            final int[] v = this.indexes[c];
            this.left.applyChoice(p, v[0]);
            this.right.applyChoice(p, v[1]);
        }
    }
    
    static class Slotted_Gems extends Slotted
    {
        Gem[][][] space;
        
        @Override
        void applyChoice(final Profile p, final int c) {
            final Gem[][] m = this.space[c];
            final Gem[] best = m[0];
            System.arraycopy(best, 0, p.SLOTS[this.index].gems, 0, best.length);
        }
    }
    
    static class Slotted_Enchant extends Slotted
    {
        EnchantT[] enchants;
        
        @Override
        void applyChoice(final Profile p, final int c) {
            p.SLOTS[this.index].setEnchant(this.enchants[c]);
        }
    }
    
    static class Slotted_Reforge extends Slotted
    {
        ReforgePair[] reforges;
        
        @Override
        void applyChoice(final Profile p, final int c) {
            p.SLOTS[this.index].setReforge(this.reforges[c]);
        }
    }
    
    static class SortedStat
    {
        final StatT stat;
        int value;
        
        SortedStat(final StatT stat) {
            this.stat = stat;
        }
    }
}
