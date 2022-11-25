// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Comparator;
import java.util.HashMap;

public class Simc
{
    private static final HashMap<SlotT, String> slotNameMap;
    private static final Pair.Map<StatT, String> statMap;
    
    public static String fmt_slotName(final SlotT x) {
        String name = Simc.slotNameMap.get(x);
        if (name == null) {
            name = x.name.toLowerCase();
        }
        return name;
    }
    
    public static String fmt_stat(final StatT x) {
        return Simc.statMap.getB(x, x.name);
    }
    
    public static String fmt_str(final String x) {
        final char[] buf = x.toCharArray();
        int len = 0;
        for (int i = 0; i < buf.length; ++i) {
            char ch = buf[i];
            if (ch < '\u0080') {
                ch = Character.toLowerCase(ch);
                if (ch < 'a' || ch > 'z') {
                    if (ch < '0' || ch > '9') {
                        if (ch == ' ') {
                            ch = '_';
                        }
                        else if (ch != '_' && ch != '+' && ch != '.' && ch != '%') {
                            continue;
                        }
                    }
                }
                buf[len++] = ch;
            }
        }
        return new String(buf, 0, len);
    }
    
    public static String fmt_enchant(final EnchantT x) {
        if (x instanceof EnchantT.One) {
            final EnchantT.One temp = (EnchantT.One)x;
            if (temp.stat.type == StatT.SPEED) {
                return null;
            }
            return temp.stat.value + fmt_stat(temp.stat.type);
        }
        else if (x instanceof EnchantT.Two) {
            final EnchantT.Two temp2 = (EnchantT.Two)x;
            if (temp2.stat1.type == StatT.SPEED) {
                return temp2.stat2.value + fmt_stat(temp2.stat2.type);
            }
            if (temp2.stat2.type == StatT.SPEED) {
                return temp2.stat1.value + fmt_stat(temp2.stat1.type);
            }
            final StringBuilder sb = new StringBuilder(32);
            sb.append(temp2.stat1.value);
            sb.append(fmt_stat(temp2.stat1.type));
            sb.append('_');
            sb.append(temp2.stat2.value);
            sb.append(fmt_stat(temp2.stat2.type));
            return sb.toString();
        }
        else {
            if (x instanceof EnchantT.All) {
                final EnchantT.All temp3 = (EnchantT.All)x;
                return temp3.value + "all";
            }
            return fmt_str(x.shortName);
        }
    }
    
    public static String fmt_tinker(final TinkerT x) {
        String name = x.name;
        if (x == TinkerT.SYNAPSE_SPRINGS) {
            name = "synapse_springs_mark_ii";
        }
        return fmt_str(name);
    }
    
    public static String exportProfile(final Profile p) {
        final StringBuilder sb = new StringBuilder(1024);
        for (final Profile.Slot x : p.SLOTS) {
            if (x.item != null) {
                if (sb.length() > 0) {
                    sb.append('\n');
                }
                sb.append(fmt_slotName(x.type));
                sb.append('=');
                sb.append(fmt_str(x.item.name));
                sb.append(",id=");
                sb.append(Math.abs(x.item.itemId));
                if (x.reforge != null) {
                    sb.append(",reforge=");
                    sb.append(fmt_stat(x.reforge.src));
                    sb.append('_');
                    sb.append(fmt_stat(x.reforge.dst));
                }
                if (x.enchant != null) {
                    final String temp = fmt_enchant(x.enchant);
                    if (temp != null) {
                        sb.append(",enchant=");
                        sb.append(temp);
                    }
                }
                if (x.tinker != null) {
                    sb.append(",addon=");
                    sb.append(fmt_tinker(x.tinker));
                }
                if (x.canSuffix()) {
                    final Suffix suf = x.getSuffixData();
                    if (suf != null) {
                        sb.append(",suffix=");
                        sb.append(-suf.id);
                    }
                }
                if (x.isCustomItemLevel()) {
                    final int up = x.getEffectiveUpgradeLevel(true);
                    if (up == -1) {
                        throw new RuntimeException("Only upgraded items are supported by Simc");
                    }
                    sb.append(",upgrade=");
                    sb.append(up);
                }
                final int n = x.getSocketCount();
                if (n > 0) {
                    sb.append(",gems=");
                    boolean first = true;
                    for (int i = 0; i < n; ++i) {
                        final Gem gem = x.getGemAt(i);
                        if (gem != null) {
                            if (gem.getType() == GemT.META) {
                                String name = gem.name;
                                final String[] v = name.split("\\s+", 3);
                                if (v.length == 3) {
                                    name = v[0] + " " + v[1];
                                }
                                if (first) {
                                    first = false;
                                }
                                else {
                                    sb.append('_');
                                }
                                sb.append(fmt_str(name));
                            }
                            else {
                                first = appendStats(sb, gem.stats, first);
                            }
                        }
                    }
                    if (x.hasSocketBonus()) {
                        sb.append('_');
                        sb.append(x.item.socketBonus.value);
                        sb.append(fmt_stat(x.item.socketBonus.type));
                    }
                }
            }
        }
        return sb.toString();
    }
    
    private static boolean appendStats(final StringBuilder sb, final StatMap stats, boolean first) {
        for (final StatT x : StatT.MAP.by_id) {
            final int amt = stats.get(x);
            if (amt != 0) {
                if (first) {
                    first = false;
                }
                else {
                    sb.append('_');
                }
                sb.append(amt);
                sb.append(fmt_stat(x));
            }
        }
        return first;
    }
    
    static {
        (slotNameMap = new HashMap<SlotT, String>()).put(SlotT.HEAD, "head");
        Simc.slotNameMap.put(SlotT.NECK, "neck");
        Simc.slotNameMap.put(SlotT.SHOULDER, "shoulders");
        Simc.slotNameMap.put(SlotT.BACK, "back");
        Simc.slotNameMap.put(SlotT.CHEST, "chest");
        Simc.slotNameMap.put(SlotT.WRIST, "wrists");
        Simc.slotNameMap.put(SlotT.HANDS, "hands");
        Simc.slotNameMap.put(SlotT.WAIST, "waist");
        Simc.slotNameMap.put(SlotT.LEGS, "legs");
        Simc.slotNameMap.put(SlotT.FEET, "feet");
        Simc.slotNameMap.put(SlotT.TRINKET_1, "trinket1");
        Simc.slotNameMap.put(SlotT.TRINKET_2, "trinket2");
        Simc.slotNameMap.put(SlotT.FINGER_1, "finger1");
        Simc.slotNameMap.put(SlotT.FINGER_2, "finger2");
        Simc.slotNameMap.put(SlotT.MAIN_HAND, "main_hand");
        Simc.slotNameMap.put(SlotT.OFF_HAND, "off_hand");
        statMap = Pair.makeMap(BlizzT.CMP_NAME, String.CASE_INSENSITIVE_ORDER, (Pair<StatT, String>[])new Pair[] { new Pair((A)StatT.EXP, (B)"exp"), new Pair((A)StatT.HIT, (B)"hit"), new Pair((A)StatT.HASTE, (B)"haste"), new Pair((A)StatT.MASTERY, (B)"mastery"), new Pair((A)StatT.CRIT, (B)"crit"), new Pair((A)StatT.DODGE, (B)"dodge"), new Pair((A)StatT.PARRY, (B)"parry"), new Pair((A)StatT.SPI, (B)"spi"), new Pair((A)StatT.STA, (B)"sta"), new Pair((A)StatT.AGI, (B)"agi"), new Pair((A)StatT.INT, (B)"int"), new Pair((A)StatT.STR, (B)"str") });
    }
}
