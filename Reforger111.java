// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Set;
import java.util.Map;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Random;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Reforger111 extends ReforgerBase
{
    public int resultCount;
    public int coreCount;
    public boolean respectProfEnchants;
    public boolean respectProfGems;
    public boolean respectNullGems;
    public boolean removeDups;
    public StatT procStat;
    public int expTarget;
    public int hitTarget;
    public SearchMode expSearchMode;
    public SearchMode hitSearchMode;
    public int searchRange;
    public boolean critGreater;
    public int masteryOverflow;
    public int hasteGap;
    public int critGap;
    public boolean changeGems;
    public int wwGems_orangeMode;
    public int wwGems_greenMode;
    public double[] statWeights;
    public FeralGems feral_gems;
    public boolean feral_sta;
    public boolean feral_hit;
    public boolean ww_orange_useAgi;
    public boolean ww_orange_useExp;
    public boolean ww_green_useSta;
    public boolean ww_green_useHit;
    public boolean canBreakBonuses;
    public boolean keepGemColors;
    public boolean enchantHands;
    public boolean enchantFeet;
    public boolean enchantBack;
    final StatT[] STATS;
    static final Pair.Same<StatT> MASTERY_PROC;
    static final Pair.Same<StatT> HASTE_PROC;
    static final Pair.Same<StatT> CRIT_PROC;
    static final boolean debug = false;
    static final Comparator<int[]> CMP_PERM;
    static final StatT[] EXTRA_STATS;
    static final EnchantT[] ENCHANTS_HANDS;
    static final EnchantT[] ENCHANTS_BACK;
    static final EnchantT[] ENCHANTS_FEET;
    
    public Reforger111() {
        this.resultCount = 15000;
        this.coreCount = 0;
        this.respectProfEnchants = true;
        this.respectProfGems = true;
        this.respectNullGems = false;
        this.removeDups = true;
        this.expSearchMode = SearchMode.AT_LEAST;
        this.hitSearchMode = SearchMode.AT_LEAST;
        this.feral_gems = FeralGems.RED;
        this.STATS = new StatT[] { StatT.HIT, StatT.EXP, StatT.MASTERY, StatT.HASTE, StatT.CRIT, StatT.AGI };
    }
    
    public static Pair.Same<StatT> other(final StatT stat) {
        if (stat == StatT.HASTE) {
            return Reforger111.HASTE_PROC;
        }
        if (stat == StatT.CRIT) {
            return Reforger111.CRIT_PROC;
        }
        return Reforger111.MASTERY_PROC;
    }
    
    public static String scoreFmt(final double score) {
        return String.format("%.0f", score);
    }
    
    public static double score(final int mastery, final int haste, final int crit, final int agi) {
        return 1.8 * mastery + 2 * (haste + crit + agi);
    }
    
    private boolean isMutable(final Profile.Slot slot) {
        return slot.item != null && (this.slotSet == null || this.slotSet.contains(slot.type));
    }
    
    private S enchantSlot(final Profile.Slot slot, final EnchantT[] a) {
        if (!this.isMutable(slot)) {
            return null;
        }
        if (this.respectProfEnchants && slot.hasProfEnchant()) {
            return null;
        }
        final int n = this.STATS.length;
        final int[][] perms = new int[a.length][n];
        final int[] diffs = new int[a.length];
        for (int i = 0; i < a.length; ++i) {
            final int[] v = perms[i];
            for (int j = 0; j < n; ++j) {
                v[j] = a[i].getStat(this.STATS[j]);
            }
            diffs[i] = ((slot.enchant != a[i]) ? 1 : 0);
        }
        slot.setEnchant(null);
        final S temp = new S();
        temp.perms = perms;
        temp.diffs = diffs;
        temp.data = a;
        temp.index = slot.index;
        temp.type = Type.ENCHANT;
        return temp;
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
    
    static S[] makeForest_random(final Collection<S> list) {
        final int tooBig = 1000;
        final ArrayList<S> chunks = new ArrayList<S>();
        final ArrayList<S> comp = new ArrayList<S>();
        for (final S x : list) {
            if (x.perms.length >= 1000) {
                chunks.add(x);
            }
            else {
                comp.add(x);
            }
        }
        final ArrayList<S> best_chunked = new ArrayList<S>();
        final ArrayList<S> chunked = new ArrayList<S>();
        final Random r = new Random();
        long best_prod = Long.MAX_VALUE;
        for (int n = 0; n < 100; ++n) {
            Collections.shuffle(comp, r);
            chunked.clear();
            S cur = null;
            for (final S x2 : comp) {
                final S next = join(cur, x2);
                if (next.perms.length >= 1000) {
                    chunked.add(cur);
                    cur = x2;
                }
                else {
                    cur = next;
                }
            }
            if (cur != null) {
                chunked.add(cur);
            }
            long prod = 1L;
            for (final S x3 : chunked) {
                prod *= x3.perms.length;
            }
            if (prod < best_prod) {
                best_prod = prod;
                best_chunked.clear();
                best_chunked.addAll(chunked);
            }
        }
        for (final S x4 : best_chunked) {
            chunks.add(makeTree(unwind(x4)));
        }
        return chunks.toArray(new S[chunks.size()]);
    }
    
    static Collection<S> unwind(final S root) {
        final ArrayList<S> found = new ArrayList<S>();
        final LinkedList<S> queue = new LinkedList<S>();
        queue.add(root);
        while (!queue.isEmpty()) {
            final S next = queue.pop();
            if (next == null) {
                continue;
            }
            if (next.type == Type.JOIN) {
                final JoinHelper jh = (JoinHelper)next.data;
                queue.add(jh.left);
                queue.add(jh.right);
            }
            else {
                found.add(next);
            }
        }
        return found;
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
        final TreeMap<int[], int[]> map = new TreeMap<int[], int[]>(Reforger111.CMP_PERM);
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
        final S temp = new S();
        temp.type = Type.JOIN;
        temp.index = -1;
        final int n = map.size();
        temp.perms = map.keySet().toArray(new int[n][]);
        final int[][] data = map.values().toArray(new int[n][]);
        final int[] diffs = new int[n];
        for (int l = 0; l < n; ++l) {
            diffs[l] = data[l][2];
        }
        temp.diffs = diffs;
        temp.data = new JoinHelper(left, right, data);
        return temp;
    }
    
    static boolean matchless(final Gem[] cur, final Gem[] min, final int num) {
        for (int i = 0; i < num; ++i) {
            if (Gem.sameAs(cur[i], min[i])) {
                return false;
            }
        }
        return true;
    }
    
    private S makeGems(final Profile.Slot slot, final Map<GemT, GemT> colorMap, final Map<GemT, Gem[]> gemMap, final GemT bestColor) {
        final int len = slot.getSocketCount();
        if (len == 0) {
            return null;
        }
        final Gem[][] gemSpace = new Gem[len][];
        int tot = 1;
        for (int i = 0; i < len; ++i) {
            final GemT socket = slot.getSocketAt(i);
            final Gem gem = slot.getGemAt(i);
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
                GemT color = null;
                if (gem != null && this.keepGemColors) {
                    color = colorMap.get(gem.getType());
                }
                if (color == null) {
                    color = colorMap.get(socket);
                }
                gems = gemMap.get(color);
            }
            gemSpace[i] = gems;
            tot *= gems.length;
        }
        final StatValue bonus = slot.item.socketBonus;
        double bonusValue = 0.0;
        int bonusIndex = -1;
        if (bonus != null) {
            bonusValue = this.statWeights[bonus.type.index] * bonus.value;
            for (int j = 0; j < this.STATS.length; ++j) {
                if (this.STATS[j] == bonus.type) {
                    bonusIndex = j;
                    break;
                }
            }
        }
        final TreeMap<int[], Set<Gem[]>> map = new TreeMap<int[], Set<Gem[]>>(Reforger111.CMP_PERM);
        final boolean debugSlot = false;
        if (debugSlot) {
            System.out.println(slot);
            System.out.println("Bonus:" + bonusIndex + ":" + bonusValue);
            for (final Gem[] x : gemSpace) {
                System.out.println(Arrays.toString(x));
            }
        }
        double bestScore = 0.0;
        for (int p = 0; p < tot; ++p) {
            final Gem[] gems2 = new Gem[len];
            Utils.decompose(p, gemSpace, gems2);
            final int[] stats = new int[this.STATS.length];
            double score = 0.0;
            for (final Gem x2 : gems2) {
                score += StatT.getWeight(x2, this.statWeights);
                StatT.add(x2, this.STATS, stats);
            }
            final boolean has;
            if (has = slot.item.hasSocketBonus(gems2)) {
                score += bonusValue;
                if (bonusIndex >= 0) {
                    final int[] array3 = stats;
                    final int n2 = bonusIndex;
                    array3[n2] += bonus.value;
                }
            }
            Set<Gem[]> list = map.get(stats);
            if (list == null) {
                list = new TreeSet<Gem[]>(Item.CMP_array);
                map.put(stats, list);
            }
            list.add(gems2);
            bestScore = Math.max(bestScore, score);
            if (debugSlot) {
                System.out.println(score + ":" + has + ":" + Arrays.toString(gems2));
            }
        }
        if (this.canBreakBonuses && bonus != null && slot.item.sockets.length > 0) {
            final Gem[] bestGems = gemMap.get(bestColor);
            boolean one = false;
            tot = 1;
            for (int k = 0; k < len; ++k) {
                final GemT socket2 = slot.getSocketAt(k);
                final Gem gem2 = slot.getGemAt(k);
                Gem[] gems3;
                if (!socket2.colored()) {
                    gems3 = new Gem[] { gem2 };
                }
                else if (gem2 != null && gem2.requiredProf != null && this.respectProfGems) {
                    gems3 = new Gem[] { gem2 };
                }
                else if (gem2 == null && this.respectNullGems) {
                    gems3 = new Gem[] { gem2 };
                }
                else {
                    gems3 = bestGems;
                    one = true;
                }
                gemSpace[k] = gems3;
                tot *= gems3.length;
            }
            if (debugSlot) {
                System.out.println(slot + ":Best: " + bestScore);
            }
            if (one) {
                for (int p2 = 0; p2 < tot; ++p2) {
                    final Gem[] gems4 = new Gem[len];
                    Utils.decompose(p2, gemSpace, gems4);
                    double score2 = 0.0;
                    for (final Gem x3 : gems4) {
                        score2 += StatT.getWeight(x3, this.statWeights);
                    }
                    final boolean hasBonus = slot.item.hasSocketBonus(gems4);
                    if (hasBonus) {
                        score2 += bonusValue;
                    }
                    if (score2 >= bestScore) {
                        final int[] perm = new int[this.STATS.length];
                        for (final Gem x4 : gems4) {
                            StatT.add(x4, this.STATS, perm);
                        }
                        if (hasBonus && bonusIndex >= 0) {
                            final int[] array6 = perm;
                            final int n5 = bonusIndex;
                            array6[n5] += bonus.value;
                        }
                        Set<Gem[]> list2 = map.get(perm);
                        if (list2 == null) {
                            list2 = new TreeSet<Gem[]>(Item.CMP_array);
                            map.put(perm, list2);
                        }
                        list2.add(gems4);
                        if (debugSlot) {
                            System.out.println("Break:" + score2 + ":" + Arrays.toString(gems4));
                        }
                    }
                }
            }
        }
        final int num = map.size();
        final int[] diffs = new int[num];
        final int[][] perms = new int[num][];
        final Gem[][][] datas = new Gem[num][][];
        int idx = 0;
        for (final Map.Entry<int[], Set<Gem[]>> e : map.entrySet()) {
            perms[idx] = e.getKey();
            final Set<Gem[]> set = e.getValue();
            final Gem[][] gems5 = set.toArray(new Gem[set.size()][]);
            diffs[idx] = ReforgerBase.gemDiffs(slot.gems, gems5[0], len);
            datas[idx++] = gems5;
        }
        final S temp = new S();
        temp.index = slot.index;
        temp.perms = perms;
        temp.data = datas;
        temp.diffs = diffs;
        temp.type = Type.GEMS;
        return temp;
    }
    
    @Override
    public void reforge(final API api, final Profile prof0, final Profile armory0) {
        boolean sameGearAsArmory = true;
        final Profile armory = new Profile();
        if (armory0 != null) {
            armory.importProfile(armory0);
            for (final Profile.Slot x : armory.SLOTS) {
                if (this.isMutable(x) && !x.isSameOriginalItem(prof0.SLOTS[x.index])) {
                    x.clear();
                    sameGearAsArmory = false;
                }
            }
        }
        if (this.procStat != null) {
            this.STATS[2] = this.procStat;
            final Pair.Same<StatT> other = other(this.procStat);
            this.STATS[3] = (StatT)other.car;
            this.STATS[4] = (StatT)other.cdr;
        }
        this.progBar.indeterminate();
        this.abort.set(false);
        final Profile prof = new Profile();
        prof.importProfile(prof0);
        final int statNum = this.STATS.length;
        final int secondaryNum = statNum - 1;
        final int[] statBase = new int[statNum];
        final ArrayList<S> slotList = new ArrayList<S>();
        if (this.enchantHands) {
            slotList.add(this.enchantSlot(prof.HANDS, Reforger111.ENCHANTS_HANDS));
        }
        if (this.enchantBack) {
            slotList.add(this.enchantSlot(prof.BACK, Reforger111.ENCHANTS_BACK));
        }
        if (this.enchantFeet) {
            slotList.add(this.enchantSlot(prof.FEET, Reforger111.ENCHANTS_FEET));
        }
        if (this.changeGems) {
            final HashMap<GemT, GemT> colorMap = new HashMap<GemT, GemT>();
            final IntSet gemIds = new IntSet();
            GemT bestColor = null;
            if (prof0.spec == SpecT.FERAL) {
                if (this.feral_gems != FeralGems.YELLOW_NO_AGI) {
                    gemIds.add(76680);
                    gemIds.add(76670);
                    gemIds.add(76666);
                    gemIds.add(76658);
                    if (this.feral_sta) {
                        gemIds.add(76687);
                    }
                }
                if (this.feral_hit) {
                    gemIds.add(76636);
                }
                switch (this.feral_gems) {
                    case ORANGE: {
                        bestColor = GemT.ORANGE;
                        (this.statWeights = new double[StatT.NUM])[StatT.AGI.index] = 2.0;
                        this.statWeights[StatT.MASTERY.index] = 1.0;
                        this.statWeights[StatT.CRIT.index] = 1.0;
                        this.statWeights[StatT.HASTE.index] = 1.0;
                        colorMap.put(GemT.ORANGE, GemT.ORANGE);
                        colorMap.put(GemT.YELLOW, GemT.ORANGE);
                        colorMap.put(GemT.PURPLE, GemT.PURPLE);
                        colorMap.put(GemT.BLUE, GemT.PURPLE);
                        colorMap.put(GemT.RED, GemT.ORANGE);
                        colorMap.put(GemT.WHITE, GemT.WHITE);
                        break;
                    }
                    case YELLOW_NO_AGI:
                    case YELLOW_NO_EXP:
                    case YELLOW: {
                        bestColor = GemT.YELLOW;
                        (this.statWeights = new double[StatT.NUM])[StatT.AGI.index] = 2.0;
                        this.statWeights[StatT.MASTERY.index] = 1.0;
                        this.statWeights[StatT.CRIT.index] = 1.0;
                        this.statWeights[StatT.HASTE.index] = 1.0;
                        colorMap.put(GemT.RED, GemT.ORANGE);
                        colorMap.put(GemT.ORANGE, GemT.ORANGE);
                        colorMap.put(GemT.PURPLE, GemT.PURPLE);
                        colorMap.put(GemT.GREEN, GemT.GREEN);
                        colorMap.put(GemT.BLUE, GemT.GREEN);
                        colorMap.put(GemT.YELLOW, GemT.YELLOW);
                        colorMap.put(GemT.WHITE, GemT.WHITE);
                        gemIds.addAll(76697, 76699, 76700);
                        if (this.feral_gems != FeralGems.YELLOW_NO_EXP) {
                            gemIds.addAll(76667, 76659, 76671);
                        }
                        if (this.feral_sta) {
                            gemIds.addAll(76652, 76654, 76656);
                        }
                        gemIds.addAll(76642, 76643, 76641);
                        break;
                    }
                    default: {
                        bestColor = GemT.RED;
                        (this.statWeights = new double[StatT.NUM])[StatT.AGI.index] = 6.5;
                        this.statWeights[StatT.MASTERY.index] = 3.0;
                        this.statWeights[StatT.CRIT.index] = 3.0;
                        this.statWeights[StatT.HASTE.index] = 3.0;
                        colorMap.put(GemT.ORANGE, GemT.ORANGE);
                        colorMap.put(GemT.YELLOW, GemT.ORANGE);
                        colorMap.put(GemT.PURPLE, GemT.PURPLE);
                        colorMap.put(GemT.BLUE, GemT.PURPLE);
                        colorMap.put(GemT.RED, GemT.RED);
                        colorMap.put(GemT.WHITE, GemT.WHITE);
                        gemIds.add(76692);
                        break;
                    }
                }
            }
            else if (prof0.spec.classType == ClassT.MONK || prof0.spec.classType == ClassT.ROGUE) {
                bestColor = GemT.YELLOW;
                (this.statWeights = new double[StatT.NUM])[StatT.AGI.index] = 2.0;
                this.statWeights[StatT.MASTERY.index] = 1.0;
                this.statWeights[StatT.CRIT.index] = 1.0;
                this.statWeights[StatT.HASTE.index] = 1.0;
                colorMap.put(GemT.RED, GemT.ORANGE);
                colorMap.put(GemT.ORANGE, GemT.ORANGE);
                colorMap.put(GemT.GREEN, GemT.GREEN);
                colorMap.put(GemT.BLUE, GemT.GREEN);
                colorMap.put(GemT.YELLOW, GemT.YELLOW);
                colorMap.put(GemT.WHITE, GemT.YELLOW);
                gemIds.addAll(76697, 76699, 76700);
                if (this.ww_orange_useAgi) {
                    gemIds.addAll(76670, 76666, 76658);
                }
                if (this.ww_orange_useExp) {
                    gemIds.addAll(76667, 76659, 76671);
                }
                if (this.ww_green_useHit) {
                    gemIds.addAll(76642, 76643, 76641);
                }
                if (this.ww_green_useSta) {
                    gemIds.addAll(76652, 76654, 76656);
                }
            }
            else {
                if (prof0.spec.classType != ClassT.HUNTER) {
                    throw new RuntimeException("Unsupported spec: " + prof0.spec);
                }
                bestColor = GemT.RED;
                (this.statWeights = new double[StatT.NUM])[StatT.AGI.index] = 6.5;
                this.statWeights[StatT.MASTERY.index] = 3.0;
                this.statWeights[StatT.CRIT.index] = 3.0;
                this.statWeights[StatT.HASTE.index] = 3.0;
                colorMap.put(GemT.ORANGE, GemT.ORANGE);
                colorMap.put(GemT.YELLOW, GemT.ORANGE);
                colorMap.put(GemT.PURPLE, GemT.PURPLE);
                colorMap.put(GemT.BLUE, GemT.PURPLE);
                colorMap.put(GemT.RED, GemT.RED);
                colorMap.put(GemT.WHITE, GemT.RED);
                gemIds.add(76692);
                gemIds.add(76680);
                gemIds.add(76670);
                gemIds.add(76666);
                gemIds.add(76658);
            }
            final Map<GemT, Gem[]> gemMap = new HashMap<GemT, Gem[]>();
            final Map<GemT, ArrayList<Gem>> map = new HashMap<GemT, ArrayList<Gem>>();
            for (final GemT x2 : GemT.COLORS) {
                map.put(x2, new ArrayList<Gem>());
            }
            for (final Gem x3 : api.loadGems_toArray(gemIds.toArray())) {
                map.get(x3.getType()).add(x3);
            }
            for (final GemT x2 : GemT.COLORS) {
                ArrayList<Gem> list;
                if (x2 == GemT.WHITE) {
                    if (this.feral_sta || this.feral_hit) {
                        list = new ArrayList<Gem>();
                        list.addAll(map.get(x2));
                        list.addAll(map.get(bestColor));
                        list.addAll(map.get(GemT.BLUE));
                    }
                    else {
                        list = map.get(bestColor);
                    }
                }
                else {
                    list = map.get(x2);
                }
                gemMap.put(x2, list.toArray(new Gem[Math.max(1, list.size())]));
            }
            final ArrayList<S> gems = new ArrayList<S>();
            for (final Profile.Slot x4 : prof.SLOTS) {
                if (this.isMutable(x4)) {
                    final S temp = this.makeGems(x4, colorMap, gemMap, bestColor);
                    if (temp != null) {
                        gems.add(temp);
                    }
                }
                else {
                    for (int i = 0; i < statNum; ++i) {
                        final int[] array = statBase;
                        final int n11 = i;
                        array[n11] += x4.sumStat_gems(this.STATS[i]);
                    }
                }
            }
            if (!gems.isEmpty()) {
                slotList.add(makeTree(gems));
            }
        }
        else {
            for (final Profile.Slot x5 : prof.SLOTS) {
                for (int j = 0; j < statNum; ++j) {
                    final int[] array2 = statBase;
                    final int n13 = j;
                    array2[n13] += x5.sumStat_gems(this.STATS[j]);
                }
            }
        }
        for (final Profile.Slot x5 : prof.SLOTS) {
            if (this.isMutable(x5)) {
                final int[] slotStat = new int[statNum];
                int count = 0;
                for (int k = 0; k < secondaryNum; ++k) {
                    final int[] array3 = slotStat;
                    final int n15 = k;
                    final int sumStat_originalItem = x5.sumStat_originalItem(this.STATS[k]);
                    array3[n15] = sumStat_originalItem;
                    final int sum = sumStat_originalItem;
                    if (sum > 0) {
                        ++count;
                    }
                }
                final S temp2 = new S();
                final int len = 1 + count * (secondaryNum - count);
                final ReforgePair[] reforges = new ReforgePair[len];
                (temp2.perms = new int[len][])[0] = slotStat;
                int idx = 1;
                for (int l = 0; l < secondaryNum; ++l) {
                    final int stat = slotStat[l];
                    if (stat > 0) {
                        for (int m = 0; m < secondaryNum; ++m) {
                            if (slotStat[m] == 0) {
                                final int[] copy = Arrays.copyOf(slotStat, statNum);
                                final int chg = (int)(stat * StatT.REFORGE_COEFF);
                                copy[l] = stat - chg;
                                copy[m] = chg;
                                reforges[idx] = ReforgePair.make(this.STATS[l], this.STATS[m]);
                                temp2.perms[idx++] = copy;
                            }
                        }
                    }
                }
                temp2.type = Type.REFORGE;
                temp2.index = x5.index;
                temp2.diffs = new int[len];
                temp2.data = reforges;
                slotList.add(temp2);
            }
            else {
                for (int j = 0; j < secondaryNum; ++j) {
                    final int[] array4 = statBase;
                    final int n16 = j;
                    array4[n16] += x5.sumStat_reforgedItem(this.STATS[j]);
                }
            }
        }
        slotList.removeAll(Collections.singleton((Object)null));
        if (slotList.isEmpty()) {
            throw new RuntimeException("There is nothing to reforge.");
        }
        final ArrayList<S> many = new ArrayList<S>();
        final ArrayList<S> ones = new ArrayList<S>();
        for (final S x6 : slotList) {
            if (x6.perms.length == 1) {
                ones.add(x6);
                final int[] perm = x6.perms[0];
                for (int i2 = 0; i2 < statNum; ++i2) {
                    final int[] array5 = statBase;
                    final int n17 = i2;
                    array5[n17] += perm[i2];
                }
            }
            else {
                many.add(x6);
            }
        }
        final S decided = ones.isEmpty() ? null : makeTree(ones);
        final S[] dimArr = makeForest(many);
        final int dimNum = dimArr.length;
        final int[][][] space = new int[dimNum][][];
        for (int j = 0; j < dimNum; ++j) {
            space[j] = dimArr[j].perms;
        }
        for (final Profile.Slot x7 : prof.SLOTS) {
            for (int i2 = 0; i2 < secondaryNum; ++i2) {
                final int[] array6 = statBase;
                final int n19 = i2;
                array6[n19] += x7.sumStat_enchant(this.STATS[i2]);
            }
        }
        for (int j = 0; j < secondaryNum; ++j) {
            final int[] array7 = statBase;
            final int n20 = j;
            array7[n20] += prof.raceAndProfStat(this.STATS[j]);
        }
        int tot = 0;
        for (int k = 0; k < secondaryNum; ++k) {
            tot += statBase[k];
        }
        final int sum_base = tot;
        final int[][] sum_space = new int[dimNum][];
        int prod = 1;
        for (int i2 = 0; i2 < dimNum; ++i2) {
            final int[][] perms = space[i2];
            final int n = perms.length;
            final int[] dst = new int[n];
            for (int j2 = 0; j2 < n; ++j2) {
                int sum2 = 0;
                final int[] v = perms[j2];
                for (int k2 = 0; k2 < secondaryNum; ++k2) {
                    sum2 += v[k2];
                }
                dst[j2] = sum2;
            }
            sum_space[i2] = dst;
            final int[] temp3 = new int[n];
            int c = 0;
            int j3 = 0;
        Label_2865:
            while (j3 < n) {
                final int sum3 = dst[j3];
                while (true) {
                    for (int k3 = 0; k3 < c; ++k3) {
                        if (sum3 == temp3[k3]) {
                            ++j3;
                            continue Label_2865;
                        }
                    }
                    temp3[c++] = sum3;
                    continue;
                }
            }
            prod *= c;
        }
        final int sum_maxCap = prod;
        final int[] statMin = new int[statNum];
        final int[] statMax = new int[statNum];
        for (int j4 = 0; j4 < statNum; ++j4) {
            int sumMax;
            int sumMin = sumMax = statBase[j4];
            for (int i3 = 0; i3 < dimNum; ++i3) {
                final int[][] perms2 = dimArr[i3].perms;
                int max;
                int min = max = perms2[0][j4];
                for (int k3 = 1; k3 < perms2.length; ++k3) {
                    final int val = perms2[k3][j4];
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
        final int hitMin = this.hitSearchMode.getMin(this.hitTarget, statMin[0], statMax[0], this.searchRange);
        final int hitMax = this.hitSearchMode.getMax(this.hitTarget, statMin[0], statMax[0], this.searchRange);
        final int expMin = this.expSearchMode.getMin(this.expTarget, statMin[1], statMax[1], this.searchRange);
        final int expMax = this.expSearchMode.getMax(this.expTarget, statMin[1], statMax[1], this.searchRange);
        if (hitMax < hitMin) {
            throw new RuntimeException(String.format("Malformed hit search range: [%d, %d]", hitMin, hitMax));
        }
        if (expMax < expMin) {
            throw new RuntimeException(String.format("Malformed expertise search range: [%d, %d]", expMin, expMax));
        }
        final int hit0 = statBase[0];
        final int exp0 = statBase[1];
        int threadCount = Runtime.getRuntime().availableProcessors();
        threadCount = Math.min(threadCount, Math.max(1, (this.coreCount == 0) ? threadCount : Math.min(threadCount, this.coreCount)));
        final Object guard = new Object();
        final int[] bound_h = new int[dimNum];
        final int[][] space_h = new int[dimNum][];
        long tot_h = 1L;
        for (int i4 = 0; i4 < dimNum; ++i4) {
            final int[][] perms3 = space[i4];
            final int n2 = perms3.length;
            final int[] dst2 = new int[n2];
            int c2 = 0;
            int j5 = 0;
        Label_3392:
            while (j5 < n2) {
                final int hit2 = perms3[j5][0];
                while (true) {
                    for (int k4 = 0; k4 < c2; ++k4) {
                        if (hit2 == dst2[k4]) {
                            ++j5;
                            continue Label_3392;
                        }
                    }
                    dst2[c2++] = hit2;
                    continue;
                }
            }
            space_h[i4] = dst2;
            final long n21 = tot_h;
            final int[] array8 = bound_h;
            final int n22 = i4;
            final int n23 = c2;
            array8[n22] = n23;
            tot_h = n21 * n23;
        }
        final int keep = 5000;
        final PriorityQueue<HitExp> joint = new PriorityQueue<HitExp>(10000, new Comparator<HitExp>() {
            @Override
            public int compare(final HitExp a, final HitExp b) {
                return b.score - a.score;
            }
        });
        AtomicInteger running = new AtomicInteger(threadCount);
        final int chunkMax = 1000;
        final int chunkNum = (tot_h < 1000L) ? ((int)tot_h) : 1000;
        final ArrayDeque<Integer> chunks = new ArrayDeque<Integer>(chunkNum);
        final long chunkEnd = tot_h;
        final long chunkStep = (chunkEnd + chunkNum - 1L) / chunkNum;
        for (int i5 = 0; i5 < chunkNum; ++i5) {
            chunks.add(i5);
        }
        final Thread[] threads = new Thread[threadCount];
        for (int u = 0; u < threadCount; ++u) {
            threads[u] = new Thread() {
                @Override
                public void run() {
                    final PriorityQueue<HitExp> buf = new PriorityQueue<HitExp>(10000, joint.comparator());
                    final int[] cfg_h = new int[dimNum];
                    final int[][] space_e = new int[dimNum][];
                    for (int i = 0; i < dimNum; ++i) {
                        space_e[i] = new int[space[i].length];
                    }
                    final int[] bound_e = new int[dimNum];
                    final int[] cfg_e = new int[dimNum];
                    final int[] value = new int[dimNum];
                    final int[] diff_cfgs = new int[sum_maxCap];
                    final int[] diff_sums = new int[sum_maxCap];
                    final int[][] cfgs = new int[sum_maxCap][];
                    while (true) {
                        final long p0;
                        synchronized (chunks) {
                            if (this.isInterrupted() || chunks.isEmpty()) {
                                break;
                            }
                            p0 = chunks.remove() * chunkStep;
                            Reforger111.this.progBar.fraction((chunkNum - chunks.size()) / (double)chunkNum);
                        }
                        final long p2 = Math.min(p0 + chunkStep, chunkEnd);
                        long d = p0;
                        for (int j = 0; j < dimNum; ++j) {
                            final int b = bound_h[j];
                            cfg_h[j] = (int)(d % b);
                            d /= b;
                        }
                        for (long p3 = p0; p3 < p2; ++p3) {
                            int hit = hit0;
                            for (int k = 0; k < dimNum; ++k) {
                                hit += space_h[k][cfg_h[k]];
                            }
                            if (hit >= hitMin && hit <= hitMax) {
                                for (int k = 0; k < dimNum; ++k) {
                                    cfg_e[k] = 0;
                                }
                                long tot_e = 1L;
                                for (int l = 0; l < dimNum; ++l) {
                                    final int[] array = value;
                                    final int n2 = l;
                                    final int n3 = space_h[l][cfg_h[l]];
                                    array[n2] = n3;
                                    final int match = n3;
                                    final int[][] perms = space[l];
                                    final int n = perms.length;
                                    int c = 0;
                                    final int[] dst = space_e[l];
                                    for (final int[] v : perms) {
                                        Label_0502: {
                                            if (v[0] == match) {
                                                final int exp = v[1];
                                                for (int k2 = 0; k2 < c; ++k2) {
                                                    if (dst[k2] == exp) {
                                                        break Label_0502;
                                                    }
                                                }
                                                dst[c++] = exp;
                                            }
                                        }
                                    }
                                    final long n4 = tot_e;
                                    final int[] array2 = bound_e;
                                    final int n5 = l;
                                    final int n6 = c;
                                    array2[n5] = n6;
                                    tot_e = n4 * n6;
                                }
                                for (long q = 0L; q < tot_e; ++q) {
                                    int exp2 = exp0;
                                    for (int i2 = 0; i2 < dimNum; ++i2) {
                                        exp2 += space_e[i2][cfg_e[i2]];
                                    }
                                    if (exp2 >= expMin && exp2 <= expMax) {
                                        final int score = Math.abs(hit - Reforger111.this.hitTarget) + Math.abs(exp2 - Reforger111.this.expTarget);
                                        if (buf.size() < 5000 || score < buf.peek().score) {
                                            int diffs = 1;
                                            cfgs[0] = new int[dimNum];
                                            for (int i3 = 0; i3 < dimNum; ++i3) {
                                                final int match_h = value[i3];
                                                final int match_e = space_e[i3][cfg_e[i3]];
                                                final int[] sums = sum_space[i3];
                                                int c2 = 0;
                                                final int[][] perms2 = space[i3];
                                            Label_0807:
                                                for (int j2 = 0; j2 < perms2.length; ++j2) {
                                                    final int[] v2 = perms2[j2];
                                                    if (v2[0] == match_h && v2[1] == match_e) {
                                                        final int sum = sums[j2];
                                                        for (int k3 = 0; k3 < c2; ++k3) {
                                                            if (diff_sums[k3] == sum) {
                                                                continue Label_0807;
                                                            }
                                                        }
                                                        diff_cfgs[c2] = j2;
                                                        diff_sums[c2++] = sum;
                                                    }
                                                }
                                                for (int j2 = 0, e = diffs; j2 < e; ++j2) {
                                                    final int[] src = cfgs[j2];
                                                    src[i3] = diff_cfgs[0];
                                                    for (int k3 = 1; k3 < c2; ++k3) {
                                                        final int[] dst2 = Arrays.copyOf(src, dimNum);
                                                        dst2[i3] = diff_cfgs[k3];
                                                        cfgs[diffs++] = dst2;
                                                    }
                                                }
                                            }
                                            for (int i3 = 0; i3 < diffs; ++i3) {
                                                buf.add(new HitExp(score, cfgs[i3]));
                                            }
                                            while (buf.size() > 5000) {
                                                buf.remove();
                                            }
                                        }
                                    }
                                    for (int i2 = 0; i2 < dimNum && ++cfg_e[i2] == bound_e[i2]; ++i2) {
                                        cfg_e[i2] = 0;
                                    }
                                }
                            }
                            for (int k = 0; k < dimNum && ++cfg_h[k] == bound_h[k]; ++k) {
                                cfg_h[k] = 0;
                            }
                        }
                    }
                    synchronized (guard) {
                        joint.addAll(buf);
                        while (joint.size() > 5000) {
                            joint.remove();
                        }
                        if (running.decrementAndGet() == 0) {
                            guard.notify();
                        }
                    }
                }
            };
        }
        this.progBar.determinate();
        for (final Thread t : threads) {
            t.setPriority(1);
            t.start();
        }
        boolean aborted = false;
        synchronized (guard) {
            while (running.get() > 0) {
                try {
                    guard.wait(1000L);
                }
                catch (InterruptedException ex) {}
                if (!aborted && this.abort.get()) {
                    aborted = true;
                    this.progBar.indeterminate();
                    for (final Thread t2 : threads) {
                        t2.interrupt();
                    }
                }
            }
        }
        if (aborted) {
            throw new RuntimeException("Aborted");
        }
        if (joint.isEmpty()) {
            throw new RuntimeException("Found zero solutions.  Try enabling gems, enchants, or relaxing the hit/exp constraints.");
        }
        this.progBar.indeterminate();
        final int n3 = joint.size();
        final HitExp[] v2 = joint.toArray(new HitExp[n3]);
        Arrays.sort(v2, 0, n3, (Comparator<? super HitExp>)Collections.reverseOrder((Comparator<? super T>)joint.comparator()));
        final ArrayDeque<int[]> queue = new ArrayDeque<int[]>(n3);
        for (final HitExp x8 : v2) {
            queue.add(x8.cfg);
        }
        final int mr0 = statBase[2];
        final int hr0 = statBase[3];
        final int cr0 = statBase[4];
        final int agi0 = statBase[5];
        final PriorityQueue<ResultHolder> found = new PriorityQueue<ResultHolder>(this.resultCount * 2, ResultHolder.CMP_SCORE);
        final int posMax = queue.size();
        running = new AtomicInteger(threadCount);
        final Thread[] threads2 = new Thread[threadCount];
        for (int u2 = 0; u2 < threadCount; ++u2) {
            threads2[u2] = new Thread() {
                @Override
                public void run() {
                    final boolean ignore = !Reforger111.this.critGreater;
                    final boolean rd = Reforger111.this.removeDups && !Reforger111.this.feral_hit;
                    final int rc = Reforger111.this.resultCount;
                    final int gap_hr = Reforger111.this.hasteGap;
                    final int gap_cr = Reforger111.this.critGap;
                    final int gap_sum = gap_hr + gap_cr;
                    final int[] cfg_m = new int[dimNum];
                    final int[] cfg_hc = new int[dimNum];
                    final int[] bound_he = new int[dimNum];
                    final int[] bound_m = new int[dimNum];
                    final int[] bound_hc = new int[dimNum];
                    final int[][] index_m = new int[dimNum][];
                    final int[][] index_hc = new int[dimNum][];
                    final int[][] space_m = new int[dimNum][];
                    final int[][][] space_hc = new int[dimNum][][];
                    for (int i = 0; i < dimNum; ++i) {
                        final int n = space[i].length;
                        index_m[i] = new int[n];
                        index_hc[i] = new int[n];
                        space_m[i] = new int[n];
                        space_hc[i] = new int[n][];
                    }
                    final int[] value = new int[dimNum];
                    while (true) {
                        final int[] cfg_he;
                        synchronized (queue) {
                            if (queue.isEmpty() || this.isInterrupted()) {
                                break;
                            }
                            cfg_he = queue.removeFirst();
                            Reforger111.this.progBar.value(posMax - queue.size());
                        }
                        int hit = hit0;
                        int exp = exp0;
                        int sum = sum_base;
                        long tot_m = 1L;
                        for (int j = 0; j < dimNum; ++j) {
                            final int[][] perms = space[j];
                            final int idx = cfg_he[j];
                            final int[] sums = sum_space[j];
                            final int[] match = perms[idx];
                            final int match_sum = sums[idx];
                            final int match_h = match[0];
                            final int match_e = match[1];
                            final int[] index = index_m[j];
                            final int[] dst = space_m[j];
                            int n2 = 0;
                            int c = 0;
                        Label_0511:
                            for (int k = 0; k < perms.length; ++k) {
                                final int[] v = perms[k];
                                if (v[0] == match_h && v[1] == match_e && sums[k] == match_sum) {
                                    index[n2++] = k;
                                    final int mr = v[2];
                                    for (int l = 0; l < c; ++l) {
                                        if (dst[l] == mr) {
                                            continue Label_0511;
                                        }
                                    }
                                    dst[c++] = mr;
                                }
                            }
                            sum += match_sum;
                            hit += match_h;
                            exp += match_e;
                            bound_he[j] = n2;
                            final long n3 = tot_m;
                            final int[] array = bound_m;
                            final int n4 = j;
                            final int n5 = c;
                            array[n4] = n5;
                            tot_m = n3 * n5;
                        }
                        final int third = 1 + (sum + gap_sum - hit - exp) / 3;
                        final int upper = third + Reforger111.this.masteryOverflow;
                        for (int m = 0; m < dimNum; ++m) {
                            cfg_m[m] = 0;
                        }
                        for (long p = 0L; p < tot_m; ++p) {
                            int mr2 = mr0;
                            for (int i2 = 0; i2 < dimNum; ++i2) {
                                final int n6 = mr2;
                                final int[] array2 = value;
                                final int n7 = i2;
                                final int n8 = space_m[i2][cfg_m[i2]];
                                array2[n7] = n8;
                                mr2 = n6 + n8;
                            }
                            if (mr2 >= third && mr2 <= upper) {
                                long tot_hc = 1L;
                                for (int i3 = 0; i3 < dimNum; ++i3) {
                                    int c2 = 0;
                                    final int match2 = value[i3];
                                    final int[] sub = index_hc[i3];
                                    final int[][] src = space[i3];
                                    final int[] index2 = index_m[i3];
                                    final int[][] dst2 = space_hc[i3];
                                    for (int j2 = 0, e = bound_he[i3]; j2 < e; ++j2) {
                                        final int k2 = index2[j2];
                                        final int[] v2 = src[k2];
                                        if (v2[2] == match2) {
                                            sub[c2] = k2;
                                            dst2[c2++] = v2;
                                        }
                                    }
                                    final long n9 = tot_hc;
                                    final int[] array3 = bound_hc;
                                    final int n10 = i3;
                                    final int n11 = c2;
                                    array3[n10] = n11;
                                    tot_hc = n9 * n11;
                                }
                                for (int i3 = 0; i3 < dimNum; ++i3) {
                                    cfg_hc[i3] = 0;
                                }
                                for (long q = 0L; q < tot_hc; ++q) {
                                    int hr = hr0;
                                    int cr = cr0;
                                    int agi = agi0;
                                    for (int i4 = 0; i4 < dimNum; ++i4) {
                                        final int[] v = space_hc[i4][cfg_hc[i4]];
                                        hr += v[3];
                                        cr += v[4];
                                        agi += v[5];
                                    }
                                    final int hx = mr2 - gap_hr - hr;
                                    final int cx = mr2 - gap_cr - cr;
                                    Label_1217: {
                                        if (hx > 0 && cx > 0 && (ignore || cr > hr)) {
                                            synchronized (found) {
                                                final double score = Reforger111.score(mr2, hr, cr, agi);
                                                if (found.size() >= rc) {
                                                    final double worst = found.peek().score;
                                                    if (score < worst) {
                                                        if (rd) {
                                                            break;
                                                        }
                                                        break Label_1217;
                                                    }
                                                    else if (score > worst) {
                                                        while (!found.isEmpty() && found.peek().score == worst) {
                                                            found.remove();
                                                        }
                                                    }
                                                }
                                                final int[] cfg = new int[dimNum];
                                                for (int i5 = 0; i5 < dimNum; ++i5) {
                                                    cfg[i5] = index_hc[i5][cfg_hc[i5]];
                                                }
                                                found.add(new ResultHolder(score, cfg, new int[] { hit, exp, mr2, hr, cr, agi }));
                                                if (rd) {
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    for (int i6 = 0; i6 < dimNum && ++cfg_hc[i6] == bound_hc[i6]; ++i6) {
                                        cfg_hc[i6] = 0;
                                    }
                                }
                            }
                            for (int i2 = 0; i2 < dimNum && ++cfg_m[i2] == bound_m[i2]; ++i2) {
                                cfg_m[i2] = 0;
                            }
                        }
                    }
                    if (running.decrementAndGet() == 0) {
                        synchronized (guard) {
                            guard.notify();
                        }
                    }
                }
            };
        }
        if (posMax > 1) {
            this.progBar.determinate(0, posMax);
        }
        for (final Thread t3 : threads2) {
            t3.setPriority(1);
            t3.start();
        }
        boolean aborted2 = false;
        synchronized (guard) {
            while (running.get() > 0) {
                try {
                    guard.wait(1000L);
                }
                catch (InterruptedException ex2) {}
                if (!aborted2 && this.abort.get()) {
                    aborted2 = true;
                    this.progBar.indeterminate();
                    for (final Thread t4 : threads2) {
                        t4.interrupt();
                    }
                }
            }
        }
        if (found.isEmpty()) {
            throw new RuntimeException("None of the hit/exp solutions met your critera.  Try increasing the overflow.  Use 'Find Bounds' to check for abnormal imbalances.");
        }
        this.progBar.indeterminate();
        int num = found.size();
        final ResultHolder[] sorted = found.toArray(new ResultHolder[num]);
        Arrays.sort(sorted, (Comparator<? super ResultHolder>)Collections.reverseOrder((Comparator<? super T>)found.comparator()));
        if (this.removeDups) {
            int n4 = 0;
            final TreeSet<int[]> set = new TreeSet<int[]>(Ints.CMP_INTS);
            for (int i6 = 0; i6 < num && n4 < this.resultCount; ++i6) {
                if (set.add(sorted[i6].stats)) {
                    sorted[n4++] = sorted[i6];
                }
            }
            num = n4;
        }
        else {
            num = Math.min(num, this.resultCount);
        }
        final StringBuilder sb = new StringBuilder(255);
        final int[] extra0 = new int[Reforger111.EXTRA_STATS.length];
        for (int i6 = 0; i6 < Reforger111.EXTRA_STATS.length; ++i6) {
            extra0[i6] = prof0.totalStat(Reforger111.EXTRA_STATS[i6]);
        }
        final int[] index = new int[secondaryNum];
        for (int i5 = 0; i5 < secondaryNum; ++i5) {
            index[i5] = i5;
        }
        class X
        {
            ProfilePerm perm;
            double score;
            int[] stats;
            int diffs;
        }
        final X[] buf = new X[num];
        for (int i7 = 0; i7 < num; ++i7) {
            final ResultHolder r = sorted[i7];
            sb.setLength(0);
            sb.append("[");
            sb.append(scoreFmt(r.score));
            sb.append("] ");
            final boolean swap = r.stats[4] > r.stats[3];
            index[3] = (swap ? 4 : 3);
            index[4] = (swap ? 3 : 4);
            for (int j6 = 0; j6 < secondaryNum; ++j6) {
                if (j6 > 0) {
                    sb.append(", ");
                }
                final int idx2 = index[j6];
                sb.append(r.stats[idx2]);
                sb.append(' ');
                sb.append(this.STATS[idx2].abbr);
            }
            sb.append(" (");
            sb.append(2 * r.stats[2] - r.stats[3] - r.stats[4] - 2);
            sb.append(")");
            if (decided != null) {
                applyChoice(prof, armory0, decided, 0);
            }
            for (int j6 = 0; j6 < dimNum; ++j6) {
                applyChoice(prof, armory0, dimArr[j6], r.cfg[j6]);
            }
            final X temp4 = new X();
            if (sameGearAsArmory) {
                for (int j7 = 0; j7 < Reforger111.EXTRA_STATS.length; ++j7) {
                    final StatT stat2 = Reforger111.EXTRA_STATS[j7];
                    final int delta = prof.totalStat(stat2) - extra0[j7];
                    if (delta != 0) {
                        sb.append(' ');
                        sb.append(stat2.formatValue(delta));
                    }
                }
            }
            final int enchDiffs = (armory0 != null) ? diffEnchants(armory0, prof) : 0;
            final int gemDiffs = (armory0 != null && this.changeGems) ? prof.minimizeGems(armory0, this.slotSet) : 0;
            if (enchDiffs > 0) {
                sb.append(" +");
                Fmt.plural(sb, enchDiffs, "enchant", "s");
            }
            if (gemDiffs > 0) {
                sb.append(" +");
                Fmt.plural(sb, gemDiffs, "gem", "s");
            }
            if (enchDiffs == 0 && gemDiffs == 0 && !this.removeDups) {
                sb.append(" <same gems and enchants>");
            }
            temp4.diffs = enchDiffs + gemDiffs;
            temp4.stats = r.stats;
            temp4.perm = new ProfilePerm(sb.toString(), CompactGear.toString(prof));
            temp4.score = r.score;
            buf[i7] = temp4;
        }
        Arrays.sort(buf, 0, num, new Comparator<X>() {
            @Override
            public int compare(final X a, final X b) {
                int cmp = -Double.compare(a.score, b.score);
                if (cmp == 0) {
                    cmp = a.diffs - b.diffs;
                    if (cmp == 0) {
                        cmp = Ints.cmp(a.stats, b.stats);
                    }
                }
                return cmp;
            }
        });
        if (!this.removeDups) {
            int n5 = 0;
            final TreeSet<int[]> set2 = new TreeSet<int[]>(Ints.CMP_INTS);
            for (int i8 = 0; i8 < num; ++i8) {
                if (set2.add(buf[i8].stats)) {
                    buf[n5++] = buf[i8];
                }
            }
            num = n5;
        }
        this.results = new ProfilePerm[((armory0 != null) ? 2 : 1) + num];
        int idx3 = 0;
        this.resultBestIndex = 1;
        if (armory0 != null) {
            this.results[idx3++] = makeProfilePerm("Armory", armory0);
            this.resultBestIndex = 2;
        }
        this.results[idx3++] = makeProfilePerm("Prior Reforging", prof0);
        for (int i9 = 0; i9 < num; ++i9) {
            this.results[idx3++] = buf[i9].perm;
        }
    }
    
    static ProfilePerm makeProfilePerm(final String name, final Profile p) {
        return new ProfilePerm(String.format("[%s] %s", scoreFmt(score(p.gearAndExtraStat(StatT.MASTERY), p.gearAndExtraStat(StatT.HASTE), p.gearAndExtraStat(StatT.CRIT), p.gemsStat(StatT.AGI))), name), CompactGear.toString(p));
    }
    
    static void applyChoice(final Profile p, final Profile q, final S dim, final int c) {
        switch (dim.type) {
            case JOIN: {
                final JoinHelper jh = (JoinHelper)dim.data;
                final int[] v = jh.space[c];
                applyChoice(p, q, jh.left, v[0]);
                applyChoice(p, q, jh.right, v[1]);
            }
            case ENCHANT: {
                final EnchantT enchant = ((EnchantT[])dim.data)[c];
                p.SLOTS[dim.index].setEnchant(enchant);
            }
            case REFORGE: {
                final ReforgePair reforge = ((ReforgePair[])dim.data)[c];
                p.SLOTS[dim.index].setReforge(reforge);
            }
            case GEMS: {
                final Gem[][] space = ((Gem[][][])dim.data)[c];
                Gem[] best = space[0];
                if (space.length > 1 && q != null && q.SLOTS[dim.index] != null) {
                    final Gem[] current = q.SLOTS[dim.index].gems;
                    int bestSame = sameCount(best, current);
                    for (int i = 1; i < space.length; ++i) {
                        final int same = sameCount(space[i], current);
                        if (same > bestSame) {
                            best = space[i];
                            bestSame = same;
                        }
                    }
                }
                System.arraycopy(best, 0, p.SLOTS[dim.index].gems, 0, best.length);
            }
            default: {}
        }
    }
    
    static int sameCount(final Gem[] a, final Gem[] b) {
        int num = 0;
        for (int i = 0; i < a.length; ++i) {
            if (Gem.sameAs(a[i], b[i])) {
                ++num;
            }
        }
        return num;
    }
    
    public static int diffEnchants(final Profile p0, final Profile p1) {
        int diffs = 0;
        for (int i = 0; i < Profile.$.length; ++i) {
            if (p0.SLOTS[i].enchant != p1.SLOTS[i].enchant) {
                ++diffs;
            }
        }
        return diffs;
    }
    
    static {
        MASTERY_PROC = new Pair.Same<StatT>(StatT.HASTE, StatT.CRIT);
        HASTE_PROC = new Pair.Same<StatT>(StatT.MASTERY, StatT.CRIT);
        CRIT_PROC = new Pair.Same<StatT>(StatT.MASTERY, StatT.HASTE);
        CMP_PERM = new Comparator<int[]>() {
            @Override
            public int compare(final int[] a, final int[] b) {
                for (int i = 0; i < 6; ++i) {
                    final int cmp = a[i] - b[i];
                    if (cmp != 0) {
                        return cmp;
                    }
                }
                return 0;
            }
        };
        EXTRA_STATS = new StatT[] { StatT.AGI, StatT.STA };
        ENCHANTS_HANDS = new EnchantT[] { EnchantT.HANDS_EXP, EnchantT.HANDS_HASTE, EnchantT.HANDS_MASTERY };
        ENCHANTS_BACK = new EnchantT[] { EnchantT.BACK_CRIT, EnchantT.BACK_HIT };
        ENCHANTS_FEET = new EnchantT[] { EnchantT.FEET_AGI, EnchantT.FEET_MASTERY };
    }
    
    public enum FeralGems
    {
        RED("Red"), 
        ORANGE("Orange"), 
        YELLOW("Yellow"), 
        YELLOW_NO_EXP("Yellow (-Exp/Sec)"), 
        YELLOW_NO_AGI("Yellow (-Agi/Sec)");
        
        public final String name;
        
        private FeralGems(final String name) {
            this.name = name;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
    }
    
    private static class ResultHolder
    {
        final double score;
        final int[] cfg;
        final int[] stats;
        static final Comparator<ResultHolder> CMP_SCORE;
        
        ResultHolder(final double score, final int[] cfg, final int[] stat) {
            this.score = score;
            this.cfg = cfg;
            this.stats = stat;
        }
        
        static {
            CMP_SCORE = new Comparator<ResultHolder>() {
                @Override
                public int compare(final ResultHolder a, final ResultHolder b) {
                    final double aa = a.score;
                    final double bb = b.score;
                    return (aa < bb) ? -1 : ((aa > bb) ? 1 : 0);
                }
            };
        }
    }
    
    private static class HitExp
    {
        final int score;
        final int[] cfg;
        
        HitExp(final int score, final int[] cfg) {
            this.score = score;
            this.cfg = cfg;
        }
    }
    
    private static class S
    {
        int[][] perms;
        int[] diffs;
        Type type;
        int index;
        Object data;
        static final Comparator<S> CMP_PERMS;
        
        void dump() {
            for (int i = 0; i < this.perms.length; ++i) {
                System.out.println(String.format("%5d %s", i + 1, Arrays.toString(this.perms[i])));
            }
        }
        
        static {
            CMP_PERMS = new Comparator<S>() {
                @Override
                public int compare(final S a, final S b) {
                    return a.perms.length - b.perms.length;
                }
            };
        }
    }
    
    public enum SearchMode
    {
        AT_MOST("At most") {
            @Override
            int getMin(final int target, final int min, final int max, final int range) {
                return Math.min(target, max) - range;
            }
            
            @Override
            int getMax(final int target, final int min, final int max, final int range) {
                return target;
            }
        }, 
        NEAR("Near") {
            @Override
            int getMin(final int target, final int min, final int max, final int range) {
                return Math.min(target, max) - range;
            }
            
            @Override
            int getMax(final int target, final int min, final int max, final int range) {
                return Math.max(min, target) + range;
            }
        }, 
        AT_LEAST("At least") {
            @Override
            int getMin(final int target, final int min, final int max, final int range) {
                return target;
            }
            
            @Override
            int getMax(final int target, final int min, final int max, final int range) {
                return Math.max(min, target) + range;
            }
        };
        
        final String name;
        
        private SearchMode(final String name) {
            this.name = name;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
        
        abstract int getMin(final int p0, final int p1, final int p2, final int p3);
        
        abstract int getMax(final int p0, final int p1, final int p2, final int p3);
    }
    
    private enum Type
    {
        REFORGE, 
        ENCHANT, 
        GEMS, 
        JOIN;
    }
    
    static class JoinHelper
    {
        final S left;
        final S right;
        final int[][] space;
        
        JoinHelper(final S left, final S right, final int[][] space) {
            this.left = left;
            this.right = right;
            this.space = space;
        }
    }
}
