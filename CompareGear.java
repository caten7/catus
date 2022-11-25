// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Iterator;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;

public class CompareGear
{
    public String title;
    public String message;
    
    static String formatScaled(final int scaled) {
        if (scaled > 0) {
            return String.format("To %d", scaled);
        }
        if (scaled < 0) {
            return String.format("Down-to %d", -scaled);
        }
        return null;
    }
    
    static String formatItemLevel(final Profile.Slot slot) {
        if (slot.item == null) {
            return null;
        }
        final int itemLevel0 = slot.item.itemLevel;
        final int itemLevel2 = slot.getItemLevel();
        if (slot.isScaledItemLevel()) {
            return String.format("%d (scaled)", slot.getScaledItemLevel());
        }
        String suffix = slot.item.nameDesc;
        if (suffix == null) {
            suffix = "Normal";
        }
        if (itemLevel2 == itemLevel0) {
            return String.format("%d <%s>", itemLevel2, suffix);
        }
        return String.format("%d <%s> (%+d)", itemLevel2, suffix, itemLevel2 - itemLevel0);
    }
    
    public boolean shoppingList(final Profile p0, final Profile p1) {
        final ArrayList<Profile.Slot> items = new ArrayList<Profile.Slot>();
        final ArrayList<String> gems = new ArrayList<String>();
        final HashMap<Gem, Tally<Gem>> gemMap = new HashMap<Gem, Tally<Gem>>();
        final ArrayList<String> enchants = new ArrayList<String>();
        for (int n = p0.SLOTS.length, i = 0; i < n; ++i) {
            final Profile.Slot s0 = p0.SLOTS[i];
            final Profile.Slot s2 = p1.SLOTS[i];
            final boolean same = p0.SLOTS[i].isSameItem(p1.SLOTS[i]);
            if (same) {
                if (s0.item == null) {
                    continue;
                }
            }
            else if (s2.item != null) {
                items.add(s2);
            }
            final int n2 = s0.getSocketCount();
            final int n3 = s2.getSocketCount();
            for (int j = 0, e = Math.max(n2, n3); j < e; ++j) {
                final Gem g0 = s0.getGemAt(j);
                final Gem g2 = s2.getGemAt(j);
                if (g2 != null && (!same || !Gem.sameAs(g0, g2))) {
                    Tally tally = gemMap.get(g2);
                    if (tally == null) {
                        tally = new Tally((T)g2);
                        gemMap.put(g2, tally);
                    }
                    final Tally tally2 = tally;
                    ++tally2.count;
                    gems.add(String.format("%20s: %s", String.format("%s/Gem#%d", s0.type.name, j + 1), g2.name));
                }
            }
            if (s2.enchant != null && (!same || s0.enchant != s2.enchant)) {
                enchants.add(String.format("%12s: %s: %s", s2.type.name, s2.enchant.name, s2.enchant.effect));
            }
            if (s2.tinker != null && (!same || s0.tinker != s2.tinker)) {
                enchants.add(String.format("%12s: %s: %s", s2.type.name, s2.tinker.name, s2.tinker.effect));
            }
        }
        final ArrayList<Tally> gemTally = new ArrayList<Tally>(gemMap.values());
        Collections.sort(gemTally, Tally.CMP);
        int gemCount = 0;
        for (final Tally x : gemTally) {
            gemCount += x.count;
        }
        final int things = items.size() + gemCount + enchants.size();
        if (things == 0) {
            this.title = "Identical Profiles";
            this.message = "No purchases required.";
            return false;
        }
        final StringBuilder sb = new StringBuilder();
        if (!items.isEmpty()) {
            sb.append("Items: ");
            sb.append(items.size());
            sb.append('\n');
            final boolean asia = p0.region == null || p0.region.asia;
            final ArrayList<Profile.Slot> upgrades = new ArrayList<Profile.Slot>();
            for (final Profile.Slot x2 : items) {
                sb.append(String.format("%12s: %s", x2.type.name, x2.getItemName(true)));
                if (x2.isCustomItemLevel()) {
                    final int up = x2.getEffectiveUpgradeLevel(asia);
                    if (up > 0) {
                        upgrades.add(x2);
                    }
                }
                sb.append(' ');
                sb.append('\n');
            }
            if (!upgrades.isEmpty()) {
                sb.append('\n');
                sb.append("Upgrades: ");
                sb.append(upgrades.size());
                sb.append('\n');
                for (final Profile.Slot x2 : upgrades) {
                    sb.append(String.format("%12s: [%+d] %d => %+d => %d \n", x2.type.name, x2.getEffectiveUpgradeLevel(asia), x2.item.itemLevel, x2.getItemLevelDelta(), x2.getItemLevel()));
                }
            }
        }
        if (gemCount > 0) {
            if (sb.length() > 0) {
                sb.append('\n');
            }
            sb.append("Gems: ");
            sb.append(gemCount);
            sb.append('\n');
            for (final Tally x3 : gemTally) {
                sb.append(String.format("%6s %s \n", "(" + x3.count + ")", x3.key));
            }
            sb.append('\n');
            for (final String x4 : gems) {
                sb.append(x4);
                sb.append(' ');
                sb.append('\n');
            }
        }
        if (!enchants.isEmpty()) {
            if (sb.length() > 0) {
                sb.append('\n');
            }
            sb.append("Enchants: ");
            sb.append(enchants.size());
            sb.append('\n');
            for (final String x4 : enchants) {
                sb.append(x4);
                sb.append(' ');
                sb.append('\n');
            }
        }
        final StringBuilder titleStr = new StringBuilder();
        titleStr.append("Shopping List: ");
        Fmt.plural(titleStr, things, "thing", "s");
        this.title = titleStr.toString();
        this.message = sb.toString();
        return true;
    }
    
    public boolean compare(final Profile p0, final Profile p1) {
        final ArrayList<Diff> itemDiffs = new ArrayList<Diff>();
        final ArrayList<Diff> powerDiffs = new ArrayList<Diff>();
        final ArrayList<Diff> gemDiffs = new ArrayList<Diff>();
        final ArrayList<Diff> enchantDiffs = new ArrayList<Diff>();
        final ArrayList<Diff> reforgeDiffs = new ArrayList<Diff>();
        final ArrayList<Diff> tinkerDiffs = new ArrayList<Diff>();
        final ArrayList<Diff> socketDiffs = new ArrayList<Diff>();
        if (p0.scaledItemLevel != p1.scaledItemLevel) {
            powerDiffs.add(new Diff("Scaling", formatScaled(p0.scaledItemLevel), formatScaled(p1.scaledItemLevel)));
        }
        int reforgeCost = 0;
        for (int n = p0.SLOTS.length, i = 0; i < n; ++i) {
            final Profile.Slot s0 = p0.SLOTS[i];
            final Profile.Slot s2 = p1.SLOTS[i];
            final boolean same = p0.SLOTS[i].isSameItem(p1.SLOTS[i]);
            if (same) {
                if (s0.item == null) {
                    continue;
                }
            }
            else if (s0.item != null && s2.item != null && s0.item.name.equals(s2.item.name)) {
                powerDiffs.add(new Diff(s0.type.name, formatItemLevel(s0), formatItemLevel(s2)));
            }
            else {
                itemDiffs.add(new Diff(s0.type.name, s0.getItemName(false), s2.getItemName(false)));
            }
            final StatValue sb0 = s0.getSocketBonus();
            final StatValue sb2 = s2.getSocketBonus();
            if (!StatValue.sameAs(sb0, sb2)) {
                socketDiffs.add(new Diff(String.format("%s/Bonus", s0.type.name), sb0, sb2));
            }
            if (s0.reforge != s2.reforge) {
                reforgeDiffs.add(new Diff(s0.type.name, s0.reforge, s2.reforge));
                if (s2.reforge != null) {
                    reforgeCost += s2.item.sellPrice;
                }
            }
            final int n2 = s0.getSocketCount();
            final int n3 = s2.getSocketCount();
            for (int j = 0, e = Math.max(n2, n3); j < e; ++j) {
                final GemT h0 = s0.getSocketAt(j);
                final GemT h2 = s2.getSocketAt(j);
                if (h0 != h2) {
                    socketDiffs.add(new Diff(String.format("%s/Socket#%d", s0.type.name, j + 1), h0, h2));
                }
                final Gem g0 = s0.getGemAt(j);
                final Gem g2 = s2.getGemAt(j);
                if (!Gem.sameAs(g0, g2)) {
                    gemDiffs.add(new Diff(String.format("%s/Gem#%d", s0.type.name, j + 1), Item.getName(g0), Item.getName(g2)));
                }
            }
            if (s0.enchant != s2.enchant) {
                enchantDiffs.add(new Diff(s0.type.name, BlizzT.getName(s0.enchant), BlizzT.getName(s2.enchant)));
            }
            if (s0.tinker != s2.tinker) {
                tinkerDiffs.add(new Diff(s0.type.name, BlizzT.getName(s0.tinker), BlizzT.getName(s2.tinker)));
            }
        }
        final int totalDiffs = itemDiffs.size() + powerDiffs.size() + gemDiffs.size() + enchantDiffs.size() + reforgeDiffs.size() + tinkerDiffs.size();
        if (totalDiffs == 0) {
            this.title = "Identical Profiles";
            this.message = "Both profiles are the same.";
            return false;
        }
        final StringBuilder sb3 = new StringBuilder();
        Diff.formatDiff(sb3, "Gear", itemDiffs);
        if (!powerDiffs.isEmpty()) {
            Diff.formatDiff(sb3, "Item Power", powerDiffs);
        }
        Diff.formatDiff(sb3, (reforgeCost > 0 && itemDiffs.isEmpty()) ? String.format("Reforges (%s)", API.formatPrice(reforgeCost)) : "Reforges", reforgeDiffs);
        Diff.formatDiff(sb3, "Enchants", enchantDiffs);
        if (!socketDiffs.isEmpty()) {
            Diff.formatDiff(sb3, "Sockets", socketDiffs);
        }
        Diff.formatDiff(sb3, "Gems", gemDiffs);
        if (ProfT.ENG.isMemberOf(p0.profs | p1.profs)) {
            Diff.formatDiff(sb3, "Tinkers", tinkerDiffs);
        }
        final ArrayList<Diff> statDiffs = new ArrayList<Diff>();
        for (final StatT x : StatT.MAP.by_name) {
            final int stat0 = p0.totalStat(x);
            final int stat2 = p1.totalStat(x);
            if (stat0 != stat2) {
                statDiffs.add(new Diff(x.name, String.format("%+d %s", stat0 - stat2, x.abbr), String.format("%+d %s", stat2 - stat0, x.abbr)));
            }
        }
        Diff.formatDiff(sb3, "Stats", statDiffs);
        sb3.append("\nProfile #1:\n");
        sb3.append(CompactGear.toString(p0, true, true));
        sb3.append("\n\nProfile #2:\n");
        sb3.append(CompactGear.toString(p1, true, true));
        final StringBuilder titleStr = new StringBuilder();
        titleStr.append("Compare: ");
        Fmt.plural(titleStr, totalDiffs, "difference", "s");
        this.title = titleStr.toString();
        this.message = sb3.toString();
        return true;
    }
    
    static class Diff
    {
        String what;
        String left;
        String right;
        
        Diff(final Object what, final Object left, final Object right) {
            this.what = orNone(what);
            this.left = orNone(left);
            this.right = orNone(right);
        }
        
        static String orNone(final Object x) {
            return (x == null) ? "None" : x.toString();
        }
        
        static void formatDiff(final StringBuilder sb, final String heading, final Collection<Diff> diffs) {
            sb.append(heading);
            sb.append(": ");
            Fmt.plural(sb, diffs.size(), "difference", "s");
            sb.append('\n');
            for (final Diff x : diffs) {
                Fmt.padLeft(sb, x.what, 19);
                sb.append(": ");
                Fmt.padRight(sb, x.left, 40);
                sb.append(" => ");
                sb.append(x.right);
                sb.append('\n');
            }
            sb.append('\n');
        }
    }
}
