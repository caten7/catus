// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Arrays;

public class StatT extends BlizzT
{
    public static double REFORGE_COEFF;
    public static final StatT HP;
    public static final StatT MP;
    public static final StatT AGI;
    public static final StatT STR;
    public static final StatT INT;
    public static final StatT SPI;
    public static final StatT STA;
    public static final StatT DODGE;
    public static final StatT PARRY;
    public static final StatT HIT;
    public static final StatT CRIT;
    public static final StatT HASTE;
    public static final StatT EXP;
    public static final StatT MASTERY;
    public static final StatT AP;
    public static final StatT SP;
    public static final StatT ARMOR;
    public static final StatT PVP_POW;
    public static final StatT PVP_RES;
    public static final StatT WDMG;
    public static final StatT SPEED;
    public static final StatT[] STATS;
    public static int NUM;
    public static final TypeMap<StatT> MAP;
    static final StatT[] PRIMARY;
    static final StatT[] SECONDARY;
    static final StatT[] CUSTOM;
    static final StatT[] REFORGE;
    public static final StatT[] REFORGE_MELEE_DPS;
    final int index;
    final Category category;
    final boolean reforge;
    final String abbr;
    final String format;
    
    StatT(final int id, final String abbr, final String name, final String format, final Category category, final boolean reforge) {
        super(id, name);
        this.index = StatT.NUM++;
        this.abbr = ((abbr == null) ? name : abbr);
        this.format = ((format == null) ? ("%+d " + name) : format);
        this.category = category;
        this.reforge = reforge;
    }
    
    public boolean isPrimary() {
        return this.category == Category.PRIMARY;
    }
    
    public boolean isSecondary() {
        return this.category == Category.SECONDARY;
    }
    
    public String formatValue(final int x) {
        return String.format(this.format, x);
    }
    
    public static void addTo(final int[] dst, final int[] add) {
        for (int i = 0; i < dst.length; ++i) {
            final int n = i;
            dst[n] += add[i];
        }
    }
    
    public static void add(final StatValued sv, final StatT[] stats, final int[] dst) {
        if (sv != null) {
            for (int i = 0; i < stats.length; ++i) {
                final int n = i;
                dst[n] += sv.getStat(stats[i]);
            }
        }
    }
    
    public static double getWeight(final StatValue sv, final double[] weights) {
        return weights[sv.type.index] * sv.value;
    }
    
    public static double getWeight(final StatValued sv, final double[] weights) {
        if (sv == null) {
            return 0.0;
        }
        double sum = 0.0;
        for (int i = 0, e = weights.length; i < e; ++i) {
            final double w = weights[i];
            if (w != 0.0) {
                sum += w * sv.getStat(StatT.STATS[i]);
            }
        }
        return sum;
    }
    
    public static String str(final StatValued sv) {
        final StringBuilder sb = new StringBuilder();
        for (final StatT x : StatT.STATS) {
            final int s = sv.getStat(x);
            if (s > 0) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append(x.abbr);
                sb.append('=');
                sb.append(s);
            }
        }
        return sb.toString();
    }
    
    public static StatT findBestMatch(final StatT[] universe, String name) {
        if (name == null) {
            return null;
        }
        name = name.trim();
        if (name.isEmpty()) {
            return null;
        }
        name = name.replace("Rating", "");
        final char first = Character.toLowerCase(name.charAt(0));
        if (name.length() == 1) {
            final StatT stat = CompactGear.statMap.get(first);
            if (stat != null) {
                return stat;
            }
        }
        int sameCount = 0;
        StatT lastSameFirst = null;
        for (final StatT x : universe) {
            if (x.name.equalsIgnoreCase(name) || x.abbr.equalsIgnoreCase(name)) {
                return x;
            }
            if (x.name.startsWith(name)) {
                ++sameCount;
                lastSameFirst = x;
            }
        }
        if (sameCount == 1) {
            return lastSameFirst;
        }
        final int lastDiff = Integer.MAX_VALUE;
        for (final StatT x2 : universe) {
            final int diff = Utils.editDist(x2.abbr, name);
            if (diff < lastDiff) {
                lastSameFirst = x2;
            }
        }
        return lastSameFirst;
    }
    
    static {
        StatT.REFORGE_COEFF = 0.4;
        STATS = new StatT[] { HP = new StatT(1, "HP", "Health", null, Category.RESOURCE, false), MP = new StatT(2, "MP", "Mana", null, Category.RESOURCE, false), AGI = new StatT(3, "Agi", "Agility", null, Category.PRIMARY, false), STR = new StatT(4, "Str", "Strength", null, Category.PRIMARY, false), INT = new StatT(5, "Int", "Intellect", null, Category.PRIMARY, false), SPI = new StatT(6, "Spi", "Spirit", null, Category.PRIMARY, true), STA = new StatT(7, "Stam", "Stamina", null, Category.PRIMARY, false), DODGE = new StatT(13, null, "Dodge", null, Category.SECONDARY, true), PARRY = new StatT(14, null, "Parry", null, Category.SECONDARY, true), HIT = new StatT(31, null, "Hit", null, Category.SECONDARY, true), CRIT = new StatT(32, "Crit", "Critical Strike", "%+d Crit", Category.SECONDARY, true), PVP_RES = new StatT(35, "PvP Resil", "PvP Resilience", null, Category.PVP, false), HASTE = new StatT(36, null, "Haste", null, Category.SECONDARY, true), EXP = new StatT(37, null, "Expertise", null, Category.SECONDARY, true), AP = new StatT(38, "AP", "Attack Power", "%+d AP", Category.EXTRA, false), SP = new StatT(45, "SP", "Spell Power", "%+d SP", Category.EXTRA, false), MASTERY = new StatT(49, null, "Mastery", null, Category.SECONDARY, true), ARMOR = new StatT(50, null, "Extra Armor", null, Category.EXTRA, false), PVP_POW = new StatT(57, null, "PvP Power", null, Category.PVP, false), WDMG = new StatT(100, null, "Weapon Damage", null, Category.CUSTOM, false), SPEED = new StatT(101, null, "Movement Speed", "%+d%% Speed", Category.CUSTOM, false) };
        MAP = BlizzT.makeMap(StatT.STATS);
        final int len = StatT.STATS.length;
        final StatT[] pri = new StatT[len];
        final StatT[] sec = new StatT[len];
        final StatT[] cus = new StatT[len];
        final StatT[] ref = new StatT[len];
        int priNum = 0;
        int secNum = 0;
        int cusNum = 0;
        int refNum = 0;
        for (final StatT x : StatT.STATS) {
            if (x.reforge) {
                ref[refNum++] = x;
            }
            switch (x.category) {
                case PRIMARY: {
                    pri[priNum++] = x;
                    break;
                }
                case SECONDARY: {
                    sec[secNum++] = x;
                    break;
                }
                case CUSTOM: {
                    cus[cusNum++] = x;
                    break;
                }
            }
        }
        PRIMARY = Arrays.copyOf(pri, priNum);
        SECONDARY = Arrays.copyOf(sec, secNum);
        CUSTOM = Arrays.copyOf(cus, cusNum);
        REFORGE = Arrays.copyOf(ref, refNum);
        REFORGE_MELEE_DPS = new StatT[] { StatT.HIT, StatT.EXP, StatT.MASTERY, StatT.HASTE, StatT.CRIT };
    }
    
    public enum Category
    {
        RESOURCE, 
        PRIMARY, 
        SECONDARY, 
        PVP, 
        EXTRA, 
        CUSTOM;
    }
    
    static class Custom extends StatT
    {
        Custom(final int id, final String name) {
            super(id, null, name, null, Category.CUSTOM, false);
        }
        
        @Override
        public String formatValue(final int x) {
            return this.name;
        }
    }
}
