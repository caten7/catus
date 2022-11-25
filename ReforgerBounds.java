// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Arrays;
import java.util.Set;

public class ReforgerBounds
{
    final StatT[] uni;
    final int[] statMin;
    final int[] statMax;
    
    public ReforgerBounds(final StatT[] uni) {
        this.uni = uni;
        this.statMin = new int[uni.length];
        this.statMax = new int[uni.length];
    }
    
    public void update(final Profile prof, final Set<SlotT> slotSet) {
        final int slotCap = prof.SLOTS.length;
        final int statNum = this.uni.length;
        final int[] statBase = new int[statNum];
        final int[][][] space = new int[slotCap][][];
        int num = 0;
        for (int s = 0; s < slotCap; ++s) {
            final Profile.Slot x = prof.SLOTS[s];
            if (x.item != null) {
                if (slotSet == null || slotSet.contains(x.type)) {
                    final int[] slotStat = new int[statNum];
                    int count = 0;
                    for (int i = 0; i < statNum; ++i) {
                        final StatT t = this.uni[i];
                        final int[] array = statBase;
                        final int n = i;
                        array[n] += x.sumStat_unreforgeable(t);
                        final int[] array2 = slotStat;
                        final int n2 = i;
                        final int sumStat_originalItem = x.sumStat_originalItem(t);
                        array2[n2] = sumStat_originalItem;
                        final int sum = sumStat_originalItem;
                        if (sum > 0) {
                            ++count;
                        }
                    }
                    if (count > 0 && count < statNum) {
                        final int len = 1 + count * (statNum - count);
                        final int[][] perms = new int[len][];
                        perms[0] = slotStat;
                        int idx = 1;
                        for (int j = 0; j < statNum; ++j) {
                            final int stat = slotStat[j];
                            if (stat > 0) {
                                for (int k = 0; k < statNum; ++k) {
                                    if (slotStat[k] == 0) {
                                        final int[] copy = Arrays.copyOf(slotStat, statNum);
                                        final int chg = (int)(stat * StatT.REFORGE_COEFF);
                                        copy[j] = stat - chg;
                                        copy[k] = chg;
                                        perms[idx++] = copy;
                                    }
                                }
                            }
                        }
                        space[num++] = perms;
                    }
                }
                else {
                    for (int l = 0; l < statNum; ++l) {
                        final int[] array3 = statBase;
                        final int n3 = l;
                        array3[n3] += x.sumStat(this.uni[l]);
                    }
                }
            }
        }
        final int slotNum = num;
        for (int m = 0; m < statNum; ++m) {
            final int[] array4 = statBase;
            final int n4 = m;
            array4[n4] += prof.raceAndProfStat(this.uni[m]);
        }
        for (int j2 = 0; j2 < statNum; ++j2) {
            int sumMax;
            int sumMin = sumMax = statBase[j2];
            for (final int[][] perms2 : space) {
                int max;
                int min = max = perms2[0][j2];
                for (int k2 = 1; k2 < perms2.length; ++k2) {
                    final int val = perms2[k2][j2];
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
            this.statMin[j2] = sumMin;
            this.statMax[j2] = sumMax;
        }
    }
    
    public int getMin(final StatT stat) {
        for (int i = 0; i < this.uni.length; ++i) {
            if (this.uni[i] == stat) {
                return this.statMin[i];
            }
        }
        return 0;
    }
    
    public int getMax(final StatT stat) {
        for (int i = 0; i < this.uni.length; ++i) {
            if (this.uni[i] == stat) {
                return this.statMax[i];
            }
        }
        return 0;
    }
}
