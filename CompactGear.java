// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class CompactGear
{
    static final HashMap<Character, StatT> statMap;
    
    public static char statToChar(final StatT x) {
        for (final Map.Entry<Character, StatT> e : CompactGear.statMap.entrySet()) {
            if (e.getValue() == x) {
                return e.getKey();
            }
        }
        return '?';
    }
    
    public static String toString(final Profile p) {
        return toString(p, false, true);
    }
    
    public static String toString(final Profile p, final boolean header, final boolean comments) {
        final StringBuilder sb = new StringBuilder(2048);
        if (header) {
            sb.append('?');
            sb.append(p.race);
            if (Integer.bitCount(p.profs) <= 2) {
                for (final ProfT x : ProfT.ALL) {
                    if (x.isMemberOf(p.profs)) {
                        sb.append(", ");
                        sb.append(x.name);
                    }
                }
            }
        }
        for (final Profile.Slot slot : p.SLOTS) {
            if (slot.item != null) {
                if (sb.length() > 0) {
                    sb.append('\n');
                }
                final int comment = sb.length() + 60;
                sb.append(slot.item.itemId);
                if (slot.canSuffix()) {
                    sb.append(" /");
                    sb.append(slot.getSuffixData().id);
                }
                if (slot.isCustomItemLevel()) {
                    final int index = slot.getEffectiveUpgradeLevel(true);
                    if (index == -1) {
                        sb.append(" i");
                        sb.append(slot.getItemLevel());
                    }
                    else {
                        sb.append(" +");
                        sb.append(slot.getItemLevelDelta());
                    }
                }
                if (slot.reforge != null) {
                    sb.append(" >");
                    sb.append(statToChar(slot.reforge.src));
                    sb.append(statToChar(slot.reforge.dst));
                }
                for (int sc = slot.getSocketCount(), i = 0; i < sc; ++i) {
                    final Gem g = slot.getGemAt(i);
                    sb.append(" :");
                    if (g != null) {
                        sb.append(g.itemId);
                    }
                }
                if (slot.extraSocket) {
                    sb.append(" Socket");
                }
                if (slot.tinker != null) {
                    sb.append(" \"");
                    sb.append(slot.tinker.name);
                    sb.append('\"');
                }
                if (slot.enchant != null) {
                    final String temp = slot.enchant.shortName;
                    sb.append(' ');
                    final boolean sp = temp.contains(" ");
                    if (sp) {
                        sb.append('\"');
                    }
                    sb.append(temp.trim());
                    if (sp) {
                        sb.append('\"');
                    }
                }
                if (comments) {
                    while (sb.length() < comment) {
                        sb.append(' ');
                    }
                    sb.append("# ");
                    sb.append(slot.type.name);
                    sb.append(": [");
                    sb.append(slot.getItemLevel());
                    sb.append("] ");
                    sb.append(slot.item.name);
                }
            }
        }
        return sb.toString();
    }
    
    public static String formatErrors_html(final Collection<String> errors) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Please fix the following ");
        Fmt.plural(sb, errors.size(), "error", "s");
        sb.append(":");
        for (final String x : errors) {
            final int pos0 = x.indexOf(35);
            final int pos2 = x.indexOf(93);
            final int pos3 = x.lastIndexOf(" :: ");
            sb.append("<br/>");
            sb.append("<b>Line ");
            sb.append(x.substring(pos0, pos2));
            sb.append("</b><br/>");
            sb.append("<tt>");
            sb.append(x.substring(pos2 + 2, pos3).trim());
            sb.append("</tt><br/>");
            sb.append(x.substring(pos3 + 4).trim());
        }
        return UI.wrapAsHtml(sb.toString());
    }
    
    public static String makeErr(final int lineNum, final String line, final String fmt, final Object... args) {
        return String.format("[Line#%d] %s :: %s", lineNum, line, String.format(fmt, args));
    }
    
    public static boolean fromString(final ItemLoader loader, final Profile p, final String expr) {
        return fromString(loader, p, expr, null, 0);
    }
    
    public static boolean fromString(final ItemLoader loader, final Profile p, final String expr, final Collection<String> errors, final int lineNumOffset) {
        boolean success = true;
        if (errors != null) {
            errors.clear();
        }
        p.clearSlots();
        final HashMap<String, Gem> gemMap = new HashMap<String, Gem>();
        final String[] lines = expr.split("\n");
    Label_2581:
        for (int ln = 0; ln < lines.length; ++ln) {
            String line = lines[ln];
            final int lineNum = ln + lineNumOffset;
            int pos = line.indexOf(35);
            if (pos >= 0) {
                line = line.substring(0, pos);
            }
            line = line.trim();
            if (!line.isEmpty()) {
                char ch = line.charAt(0);
                if (Character.isDigit(ch) || ch == '-') {
                    final String[] comps = line.split("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    int id;
                    try {
                        id = Integer.parseInt(comps[0]);
                    }
                    catch (NumberFormatException err6) {
                        success = false;
                        if (errors != null) {
                            errors.add(makeErr(lineNum, line, "Invalid item id (%s)", comps[0]));
                        }
                        continue;
                    }
                    Item item;
                    try {
                        item = loader.loadItem(id);
                    }
                    catch (RuntimeException err) {
                        success = false;
                        if (errors != null) {
                            errors.add(makeErr(lineNum, line, err.getMessage(), new Object[0]));
                        }
                        continue;
                    }
                    if (item != null) {
                        final Profile.Slot slot = p.nextEmptySlot(item.equipType);
                        if (slot == null) {
                            if (errors != null) {
                                errors.add(makeErr(lineNum, line, String.format("No empty slot to equip %s.", item.name), new Object[0]));
                            }
                        }
                        else {
                            try {
                                slot.setItem(item);
                            }
                            catch (RuntimeException err2) {
                                success = false;
                                if (errors != null) {
                                    errors.add(makeErr(lineNum, line, err2.getMessage(), new Object[0]));
                                }
                                continue;
                            }
                            final ArrayList<Gem> gems = new ArrayList<Gem>();
                            for (int i = 1; i < comps.length; ++i) {
                                String comp = comps[i].trim();
                                if (!comp.isEmpty()) {
                                    if (comp.charAt(0) == '>') {
                                        if (comp.length() != 3) {
                                            success = false;
                                            if (errors != null) {
                                                errors.add(makeErr(lineNum, line, "Invalid reforge (%s)", comp));
                                            }
                                            slot.clear();
                                            continue Label_2581;
                                        }
                                        final StatT src = CompactGear.statMap.get(comp.charAt(1));
                                        if (src == null) {
                                            success = false;
                                            if (errors != null) {
                                                errors.add(makeErr(lineNum, line, "Invalid reforge source (%c)", comp.charAt(1)));
                                            }
                                            slot.clear();
                                            continue Label_2581;
                                        }
                                        final StatT dst = CompactGear.statMap.get(comp.charAt(2));
                                        if (dst == null) {
                                            success = false;
                                            if (errors != null) {
                                                errors.add(makeErr(lineNum, line, "Invalid reforge dest (%c)", comp.charAt(2)));
                                            }
                                            slot.clear();
                                            continue Label_2581;
                                        }
                                        final ReforgePair reforge = ReforgePair.make(src, dst);
                                        try {
                                            slot.setReforge(reforge);
                                        }
                                        catch (RuntimeException err3) {
                                            success = false;
                                            if (errors != null) {
                                                errors.add(makeErr(lineNum, line, err3.getMessage(), new Object[0]));
                                            }
                                            slot.clear();
                                            continue Label_2581;
                                        }
                                    }
                                    else if (comp.charAt(0) == ':') {
                                        comp = comp.substring(1);
                                        if (comp.isEmpty()) {
                                            gems.add(null);
                                        }
                                        else {
                                            ch = comp.charAt(0);
                                            if (!Character.isDigit(ch)) {
                                                if (ch != '-') {
                                                    int j = 0;
                                                    final int e = comp.length();
                                                Label_0890:
                                                    while (j < e) {
                                                        for (int k = e; k > j; --k) {
                                                            final String key = comp.substring(j, k);
                                                            final Gem gem = gemMap.get(key);
                                                            if (gem != null) {
                                                                gems.add(gem);
                                                                j = k;
                                                                continue Label_0890;
                                                            }
                                                        }
                                                        success = false;
                                                        if (errors != null) {
                                                            errors.add(makeErr(lineNum, line, "Unknown gem-key expression (%s)", comp));
                                                        }
                                                        slot.clear();
                                                        continue Label_2581;
                                                    }
                                                    continue;
                                                }
                                            }
                                            int gemId;
                                            try {
                                                gemId = Integer.parseInt(comp);
                                            }
                                            catch (NumberFormatException err7) {
                                                success = false;
                                                if (errors != null) {
                                                    errors.add(makeErr(lineNum, line, "Invalid gem id (%s)", comp));
                                                }
                                                slot.clear();
                                                continue Label_2581;
                                            }
                                            Item gemItem;
                                            try {
                                                gemItem = loader.loadItem(gemId);
                                            }
                                            catch (RuntimeException err4) {
                                                success = false;
                                                if (errors != null) {
                                                    errors.add(makeErr(lineNum, line, err4.getMessage(), new Object[0]));
                                                }
                                                slot.clear();
                                                continue Label_2581;
                                            }
                                            if (!(gemItem instanceof Gem)) {
                                                success = false;
                                                if (errors != null) {
                                                    errors.add(makeErr(lineNum, line, "Not a gem: %s", gemItem.name));
                                                }
                                                slot.clear();
                                                continue Label_2581;
                                            }
                                            gems.add((Gem)gemItem);
                                        }
                                    }
                                    else if (comp.charAt(0) == '/') {
                                        int su_id;
                                        try {
                                            su_id = Integer.parseInt(comp.substring(1));
                                        }
                                        catch (NumberFormatException err7) {
                                            success = false;
                                            if (errors != null) {
                                                errors.add(makeErr(lineNum, line, "Invalid suffix (%s)", comp));
                                            }
                                            continue Label_2581;
                                        }
                                        try {
                                            slot.setSuffixId(su_id);
                                        }
                                        catch (RuntimeException err5) {
                                            success = false;
                                            if (errors != null) {
                                                errors.add(makeErr(lineNum, line, err5.getMessage(), new Object[0]));
                                            }
                                            continue Label_2581;
                                        }
                                    }
                                    else if (comp.charAt(0) == '+') {
                                        int delta;
                                        try {
                                            delta = Integer.parseInt(comp.substring(1));
                                        }
                                        catch (NumberFormatException err7) {
                                            success = false;
                                            if (errors != null) {
                                                errors.add(makeErr(lineNum, line, "Invalid item level delta (%s)", comp));
                                            }
                                            continue Label_2581;
                                        }
                                        try {
                                            slot.setItemLevelDelta(delta);
                                        }
                                        catch (RuntimeException err5) {
                                            success = false;
                                            if (errors != null) {
                                                errors.add(makeErr(lineNum, line, err5.getMessage(), new Object[0]));
                                            }
                                            continue Label_2581;
                                        }
                                    }
                                    else if (comp.charAt(0) == 'i') {
                                        int level;
                                        try {
                                            level = Integer.parseInt(comp.substring(1));
                                        }
                                        catch (NumberFormatException err7) {
                                            success = false;
                                            if (errors != null) {
                                                errors.add(makeErr(lineNum, line, "Invalid item level (%s)", comp));
                                            }
                                            continue Label_2581;
                                        }
                                        try {
                                            slot.setItemLevel(level);
                                        }
                                        catch (RuntimeException err5) {
                                            success = false;
                                            if (errors != null) {
                                                errors.add(makeErr(lineNum, line, err5.getMessage(), new Object[0]));
                                            }
                                            continue Label_2581;
                                        }
                                    }
                                    else {
                                        if (comp.indexOf(34) >= 0) {
                                            comp = comp.replace("\"", "").trim();
                                        }
                                        if (!comp.isEmpty()) {
                                            if (comp.equalsIgnoreCase("socket")) {
                                                slot.extraSocket = true;
                                            }
                                            else {
                                                final EnchantT enchant = EnchantT.find(slot.item.equipType, comp);
                                                if (enchant != null) {
                                                    try {
                                                        slot.setEnchant(enchant);
                                                    }
                                                    catch (RuntimeException err5) {
                                                        success = false;
                                                        if (errors != null) {
                                                            errors.add(makeErr(lineNum, line, err5.getMessage(), new Object[0]));
                                                        }
                                                        slot.clear();
                                                    }
                                                }
                                                else {
                                                    final TinkerT tinker = TinkerT.find(slot.item.equipType, comp);
                                                    if (tinker == null) {
                                                        success = false;
                                                        if (errors != null) {
                                                            System.out.println(slot.item.name + ":" + slot.item.minorType + ":" + slot.item.equipType);
                                                            errors.add(makeErr(lineNum, line, "Unknown slot modifier (%s)", comp));
                                                        }
                                                        slot.clear();
                                                        continue Label_2581;
                                                    }
                                                    slot.tinker = tinker;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            final int sc = slot.getSocketCount();
                            if (gems.size() > sc) {
                                success = false;
                                if (errors != null) {
                                    errors.add(makeErr(lineNum, line, String.format("Too many gems (%d) for sockets (%d)", gems.size(), sc), new Object[0]));
                                }
                                slot.clear();
                            }
                            else if (gems.size() < sc) {
                                Iterator<Gem> iter = gems.iterator();
                                while (iter.hasNext()) {
                                    final Gem gem2 = iter.next();
                                    if (gem2 == null) {
                                        iter.remove();
                                    }
                                    else {
                                        for (int l = 0; l < sc; ++l) {
                                            if (slot.getSocketAt(l).matches(gem2)) {
                                                try {
                                                    slot.setGemAt(l, gem2);
                                                    iter.remove();
                                                    break;
                                                }
                                                catch (RuntimeException err4) {
                                                    success = false;
                                                    if (errors != null) {
                                                        errors.add(makeErr(lineNum, line, err4.getMessage(), new Object[0]));
                                                    }
                                                    slot.clear();
                                                    continue Label_2581;
                                                }
                                            }
                                        }
                                    }
                                }
                                iter = gems.iterator();
                                while (iter.hasNext()) {
                                    final Gem gem2 = iter.next();
                                    for (int l = 0; l < sc; ++l) {
                                        if (slot.getGemAt(l) == null && slot.getSocketAt(l).accepts(gem2)) {
                                            try {
                                                slot.setGemAt(l, gem2);
                                                iter.remove();
                                                break;
                                            }
                                            catch (RuntimeException err4) {
                                                success = false;
                                                if (errors != null) {
                                                    errors.add(makeErr(lineNum, line, err4.getMessage(), new Object[0]));
                                                }
                                                slot.clear();
                                                continue Label_2581;
                                            }
                                        }
                                    }
                                }
                                if (!gems.isEmpty()) {
                                    success = false;
                                    if (errors != null) {
                                        errors.add(makeErr(lineNum, line, "Unable to socket gems: " + Fmt.join(gems, ", "), new Object[0]));
                                    }
                                    slot.clear();
                                }
                            }
                            else {
                                for (int m = 0, e2 = gems.size(); m < e2; ++m) {
                                    try {
                                        slot.setGemAt(m, gems.get(m));
                                    }
                                    catch (RuntimeException err5) {
                                        success = false;
                                        if (errors != null) {
                                            errors.add(makeErr(lineNum, line, err5.getMessage(), new Object[0]));
                                        }
                                        slot.clear();
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                else if (ch == '?') {
                    final String[] tokens = line.substring(1).split(",");
                    for (int i2 = 0; i2 < tokens.length; ++i2) {
                        tokens[i2] = tokens[i2].trim();
                    }
                    int i2 = 0;
                    p.race = RaceT.MAP.get(tokens[0]);
                    if (p.race != null) {
                        ++i2;
                    }
                    int profs = 0;
                    while (i2 < tokens.length) {
                        final ProfT prof = ProfT.MAP.find(tokens[i2]);
                        if (prof == null) {
                            success = false;
                            if (errors != null) {
                                errors.add(makeErr(lineNum, line, "Unknown profession (%s)", tokens[i2]));
                            }
                        }
                        else {
                            profs |= prof.bit;
                        }
                        ++i2;
                    }
                    if (Integer.bitCount(profs) > 2) {
                        success = false;
                        if (errors != null) {
                            errors.add(makeErr(lineNum, line, "Too many professions", new Object[0]));
                        }
                    }
                    else {
                        p.profs = profs;
                    }
                }
                else {
                    pos = line.indexOf(61);
                    if (pos == -1) {
                        success = false;
                        if (errors != null) {
                            errors.add(makeErr(lineNum, line, "Invalid definition, missing equals (=)", new Object[0]));
                        }
                    }
                    else {
                        final String key2 = line.substring(0, pos).trim();
                        if (key2.isEmpty()) {
                            success = false;
                            if (errors != null) {
                                errors.add(makeErr(lineNum, line, "Bad definition, invalid key (%s)", key2));
                            }
                        }
                        else {
                            final String val = line.substring(pos + 1).trim();
                            int id2;
                            try {
                                id2 = Integer.parseInt(val);
                            }
                            catch (NumberFormatException err8) {
                                success = false;
                                if (errors != null) {
                                    errors.add(makeErr(lineNum, line, "Bad definition, invalid item id (%s)", val));
                                }
                                continue;
                            }
                            Item item2;
                            try {
                                item2 = loader.loadItem(id2);
                            }
                            catch (RuntimeException err2) {
                                success = false;
                                if (errors != null) {
                                    errors.add(makeErr(lineNum, line, err2.getMessage(), new Object[0]));
                                }
                                continue;
                            }
                            if (!(item2 instanceof Gem)) {
                                success = false;
                                if (errors != null) {
                                    errors.add(makeErr(lineNum, line, "Not a gem: %s", item2.name));
                                }
                            }
                            else {
                                gemMap.put(key2, (Gem)item2);
                            }
                        }
                    }
                }
            }
        }
        return success;
    }
    
    static {
        (statMap = new HashMap<Character, StatT>()).put('h', StatT.HIT);
        CompactGear.statMap.put('e', StatT.EXP);
        CompactGear.statMap.put('m', StatT.MASTERY);
        CompactGear.statMap.put('c', StatT.CRIT);
        CompactGear.statMap.put('t', StatT.HASTE);
        CompactGear.statMap.put('s', StatT.SPI);
        CompactGear.statMap.put('d', StatT.DODGE);
        CompactGear.statMap.put('p', StatT.PARRY);
    }
    
    public static class LineError
    {
    }
}
