// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.ArrayList;
import java.util.Comparator;

public class Item implements StatValued
{
    static final Comparator<Item[]> CMP_array;
    static final Comparator<Item> CMP_itemId;
    static final Comparator<Item> CMP_itemLevel;
    String name;
    String nameDesc;
    ArrayList<Item> groupSet;
    IconStore icon;
    QualityT quality;
    int itemId;
    ItemSet itemSet;
    int itemLevel;
    ItemT majorType;
    EquipT equipType;
    StatAlloc[] allocDist;
    final StatMap stats;
    Object minorType;
    GemT[] sockets;
    StatValue socketBonus;
    String uniqueKey;
    int sellPrice;
    int uniqueCount;
    int allowableClasses;
    int allowableRaces;
    ProfT requiredProf;
    int[] itemSpells;
    boolean upgradable;
    Suffix[] suffixes;
    SpellAlloc[] spellStats;
    String name0;
    boolean pvpSeason;
    int otherFaction_itemId;
    
    public Item() {
        this.stats = new StatMap();
    }
    
    public static String getName(final Item x) {
        return (x == null) ? null : x.fullName();
    }
    
    @Override
    public String toString() {
        return (this.name == null) ? String.format("Item<%d>", this.itemId) : this.fullName();
    }
    
    public String fullName() {
        return (this.nameDesc == null) ? this.name : (this.name + " (" + this.nameDesc + ")");
    }
    
    public String fancyStats() {
        return this.stats.toString();
    }
    
    public boolean hasExtraUpgrade() {
        return this.hasIntermediateUpgrade() && this.itemId >= 98600;
    }
    
    public boolean hasIntermediateUpgrade() {
        return this.upgradable && this.quality.id >= 4;
    }
    
    public int getUpgradeLimit(final boolean asia) {
        if (!this.upgradable) {
            return 0;
        }
        if (!this.hasIntermediateUpgrade()) {
            return 1;
        }
        if (!this.hasExtraUpgrade()) {
            return asia ? 4 : 2;
        }
        return asia ? 6 : 4;
    }
    
    public boolean hasSpell(final int id) {
        if (this.itemSpells != null) {
            for (final int x : this.itemSpells) {
                if (x == id) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean hasSocket(final GemT socket) {
        if (this.sockets == null) {
            return false;
        }
        for (final GemT x : this.sockets) {
            if (x == socket) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasSocketBonus(final Gem[] gems) {
        if (this.socketBonus == null) {
            return false;
        }
        if (this.sockets == null || gems == null || gems.length < this.sockets.length) {
            return false;
        }
        for (int i = 0; i < this.sockets.length; ++i) {
            final Gem gem = gems[i];
            if (!this.sockets[i].matches(gem)) {
                return false;
            }
        }
        return true;
    }
    
    boolean checkClass(final ClassT cls) {
        return this.allowableClasses == 0 || (cls != null && cls.isMemberOf(this.allowableClasses));
    }
    
    boolean checkRace(final RaceT race) {
        return this.allowableRaces == 0 || (race != null && race.isMemberOf(this.allowableRaces));
    }
    
    boolean checkProf(final int profs) {
        return this.requiredProf == null || this.requiredProf.isMemberOf(profs);
    }
    
    boolean validate(final ClassT cls, final RaceT race, final int profs) {
        return this.checkClass(cls) && this.checkRace(race) && this.checkProf(profs);
    }
    
    @Override
    public int getStat(final StatT stat) {
        return this.stats.get(stat);
    }
    
    static {
        CMP_array = new Comparator<Item[]>() {
            @Override
            public int compare(final Item[] a, final Item[] b) {
                final int n = a.length;
                if (n == b.length) {
                    for (int i = 0; i < n; ++i) {
                        final Item aa = a[i];
                        final Item bb = b[i];
                        final int aaa = (aa == null) ? 0 : aa.itemId;
                        final int bbb = (bb == null) ? 0 : bb.itemId;
                        if (aaa != bbb) {
                            return aaa - bbb;
                        }
                    }
                    return 0;
                }
                return n - b.length;
            }
        };
        CMP_itemId = new Comparator<Item>() {
            @Override
            public int compare(final Item a, final Item b) {
                return a.itemId - b.itemId;
            }
        };
        CMP_itemLevel = new Comparator<Item>() {
            @Override
            public int compare(final Item a, final Item b) {
                return b.itemLevel - a.itemLevel;
            }
        };
    }
}
