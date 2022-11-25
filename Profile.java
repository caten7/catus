// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collection;
import java.util.Map;
import javax.swing.JProgressBar;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;

public class Profile
{
    public static final SlotT[] $;
    public static final int[] SLOT_IDX;
    public final Slot HEAD;
    public final Slot NECK;
    public final Slot SHOULDER;
    public final Slot BACK;
    public final Slot CHEST;
    public final Slot WRIST;
    public final Slot HANDS;
    public final Slot LEGS;
    public final Slot WAIST;
    public final Slot FEET;
    public final Slot F1;
    public final Slot F2;
    public final Slot T1;
    public final Slot T2;
    public final Slot MH;
    public final Slot OH;
    public final Slot[] SLOTS;
    public SpecT spec;
    public final HashMap<ProfT, Integer> skillMap;
    public int profs;
    public int charLevel;
    public String charName;
    public String realmName;
    public String realmSlug;
    public String talents;
    public String glyphs;
    public RegionT region;
    public RaceT race;
    public boolean dollMode;
    public int scaledItemLevel;
    private final StatMap baseStats;
    private double _scaledMod;
    static final String AMR_EOL = ";";
    
    public Profile() {
        this.skillMap = new HashMap<ProfT, Integer>();
        this.baseStats = new StatMap();
        this.SLOTS = new Slot[Profile.$.length];
        for (int i = 0; i < Profile.$.length; ++i) {
            this.SLOTS[i] = new Slot(i, Profile.$[i]);
        }
        this.HEAD = this.getSlot(SlotT.HEAD);
        this.NECK = this.getSlot(SlotT.NECK);
        this.SHOULDER = this.getSlot(SlotT.SHOULDER);
        this.BACK = this.getSlot(SlotT.BACK);
        this.CHEST = this.getSlot(SlotT.CHEST);
        this.WRIST = this.getSlot(SlotT.WRIST);
        this.HANDS = this.getSlot(SlotT.HANDS);
        this.LEGS = this.getSlot(SlotT.LEGS);
        this.WAIST = this.getSlot(SlotT.WAIST);
        this.FEET = this.getSlot(SlotT.FEET);
        this.F1 = this.getSlot(SlotT.FINGER_1);
        this.F2 = this.getSlot(SlotT.FINGER_2);
        this.T1 = this.getSlot(SlotT.TRINKET_1);
        this.T2 = this.getSlot(SlotT.TRINKET_2);
        this.MH = this.getSlot(SlotT.MAIN_HAND);
        this.OH = this.getSlot(SlotT.OFF_HAND);
        this.clear();
    }
    
    public Profile(final SpecT spec) {
        this();
        this.spec = spec;
    }
    
    public final Slot getSlot(final SlotT type) {
        final int idx = Profile.SLOT_IDX[type.id];
        return (idx >= 0) ? this.SLOTS[idx] : null;
    }
    
    public Set<Slot> findSlots(final EquipT eq) {
        final HashSet<Slot> found = new HashSet<Slot>();
        for (final Slot x : this.SLOTS) {
            if (x.type.accepts(eq)) {
                found.add(x);
            }
        }
        return found;
    }
    
    public void setScaledItemLevel(final int ilvl) {
        this.scaledItemLevel = ilvl;
        if (ilvl < 0) {
            this._updateAllItemData();
        }
        else {
            this._scaledMod = 0.0;
            for (final Slot x : this.SLOTS) {
                x._updateItemData(true);
            }
        }
    }
    
    private void _updateAllItemData() {
        final int saved = this.scaledItemLevel;
        this.scaledItemLevel = 0;
        this._scaledMod = 0.0;
        for (final Slot x : this.SLOTS) {
            x._updateItemData(false);
            x._priorHit = x.sumStat_reforgedItem(StatT.HIT);
            x._priorExp = x.sumStat_reforgedItem(StatT.EXP);
        }
        int accSec = 0;
        int accGap = 0;
        this.scaledItemLevel = saved;
        for (final Slot x2 : this.SLOTS) {
            x2._updateItemData(false);
            for (final StatT y : StatT.REFORGE) {
                if (y != StatT.HIT) {
                    if (y != StatT.EXP) {
                        accSec += x2.sumStat_reforgedItem(y);
                    }
                }
            }
            accGap += x2._priorHit - x2.sumStat_reforgedItem(StatT.HIT);
            accGap += x2._priorExp - x2.sumStat_reforgedItem(StatT.EXP);
        }
        this._scaledMod = (accSec - accGap) / (double)accSec;
    }
    
    public boolean isProf(final ProfT prof) {
        return prof != null && prof.isMemberOf(this.profs);
    }
    
    public void validate() {
        final boolean bs = ProfT.BS.isMemberOf(this.profs);
        if (!bs || (bs && this.dollMode)) {
            this.HANDS.extraSocket = bs;
            this.WRIST.extraSocket = bs;
        }
        for (final Slot x : this.SLOTS) {
            if (x.item != null) {
                if (!x.item.validate(this.spec.classType, this.race, this.profs)) {
                    x.clear();
                }
                else {
                    if (x.enchant != null && x.enchant.requiredProf != null && !x.enchant.requiredProf.isMemberOf(this.profs)) {
                        x.enchant = null;
                    }
                    if (x.tinker != null && !ProfT.ENG.isMemberOf(this.profs)) {
                        x.tinker = null;
                    }
                    x.validateSocketsAndGems();
                }
            }
        }
        this.baseStats.clear();
        if (this.race != null) {
            this.baseStats.add(RaceT.STATS, this.race.stats);
        }
        if (this.spec != null) {
            this.baseStats.add(ClassT.STATS, this.spec.classType.stats);
        }
        this.baseStats.set(StatT.HP, 146663);
        this.baseStats.set(StatT.AP, 260);
        this.baseStats.set(StatT.MASTERY, 4800);
    }
    
    public final void clear() {
        this.spec = null;
        this.race = null;
        this.profs = 0;
        this.charName = null;
        this.realmName = null;
        this.realmSlug = null;
        this.talents = null;
        this.glyphs = null;
        this.region = null;
        this.baseStats.clear();
        this.clearSlots();
    }
    
    public final void clearSlots() {
        for (final Slot x : this.SLOTS) {
            x.clear();
        }
    }
    
    public boolean hasAnyWeaponType(final BlizzT.TypeSet<WeaponT> set) {
        return this.MH.isWeapon(set) || this.OH.isWeapon(set);
    }
    
    public boolean hasOnlyWeaponType(final BlizzT.TypeSet<WeaponT> set) {
        return this.MH.isWeapon(set) && (!this.OH.isWeapon() || this.OH.isWeapon(set));
    }
    
    public boolean hasTrinketNamed(final String test) {
        return this.T1.isNamed(test) || this.T2.isNamed(test);
    }
    
    public boolean hasItemNamed(final String test) {
        for (final Slot x : this.SLOTS) {
            if (x.isNamed(test)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasItem(final int id) {
        for (final Slot x : this.SLOTS) {
            if (x.item != null && x.item.itemId == id) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasSpell(final int id) {
        for (final Slot x : this.SLOTS) {
            if (x.item != null && x.item.hasSpell(id)) {
                return true;
            }
        }
        return false;
    }
    
    public String getWeaponStr() {
        return this.getWeaponStr(" + ", false);
    }
    
    public String getWeaponStr(final String join, final boolean suffix) {
        return this.hasTwoHander() ? this.MH.getItemNameOrEmpty(suffix) : (this.MH.getItemNameOrEmpty(suffix) + join + this.OH.getItemNameOrEmpty(suffix));
    }
    
    public boolean hasTwoHander() {
        return WeaponT.isTwoHander(this.MH.item);
    }
    
    public int[] gearSpells() {
        final ArrayList<int[]> found = new ArrayList<int[]>();
        int tot = 0;
        for (final Slot x : this.SLOTS) {
            if (x.item != null) {
                final int[] spells = x.item.itemSpells;
                if (spells != null) {
                    found.add(spells);
                    tot += spells.length;
                }
            }
        }
        final int[] spells2 = new int[tot];
        int pos = 0;
        for (final int[] array : found) {
            final int[] v = array;
            for (final int x2 : array) {
                spells2[pos++] = x2;
            }
        }
        return spells2;
    }
    
    public Profile copyProfile() {
        final Profile copy = new Profile();
        copy.importProfile(this);
        return copy;
    }
    
    public void importProfile(final Profile p) {
        this.spec = p.spec;
        this.race = p.race;
        this.profs = p.profs;
        this.talents = p.talents;
        this.glyphs = p.glyphs;
        this.charName = p.charName;
        this.charLevel = p.charLevel;
        this.realmName = p.realmName;
        this.realmSlug = p.realmSlug;
        this.region = p.region;
        this.scaledItemLevel = p.scaledItemLevel;
        this.copySlots(p);
        this.baseStats.setAll(p.baseStats);
    }
    
    public void copySlots(final Profile p) {
        this.clearSlots();
        for (int i = 0; i < this.SLOTS.length; ++i) {
            this.SLOTS[i].copy(p.SLOTS[i]);
        }
    }
    
    public int gearArmor(final int bodyParts) {
        int sum = 0;
        for (final Slot x : this.SLOTS) {
            if ((x.type.bodyPart & bodyParts) == x.type.bodyPart) {
                sum += x.getItemArmor();
            }
        }
        return sum;
    }
    
    public int raceAndProfStat(final StatT type) {
        if (this.spec == null) {
            return 0;
        }
        if (type == StatT.EXP) {
            final int onePercExp = 340;
            if (this.race == RaceT.ORC) {
                return this.hasOnlyWeaponType(WeaponT.SPEC_ORC) ? 340 : 0;
            }
            if (this.race == RaceT.TROLL) {
                return this.hasOnlyWeaponType(WeaponT.SPEC_TROLL) ? 340 : 0;
            }
            if (this.race == RaceT.GNOME) {
                return this.hasOnlyWeaponType(WeaponT.SPEC_GNOME) ? 340 : 0;
            }
            if (this.race == RaceT.HUMAN) {
                return this.hasOnlyWeaponType(WeaponT.SPEC_HUMAN) ? 340 : 0;
            }
            if (this.race == RaceT.DWARF) {
                return this.hasOnlyWeaponType(WeaponT.SPEC_DWARF) ? 340 : 0;
            }
        }
        else if (type == StatT.HIT) {
            final int onePercHit = 340;
            if (this.race == RaceT.DRAENEI) {
                return 340;
            }
        }
        else if (type == StatT.CRIT && ProfT.SKIN.isMemberOf(this.profs)) {
            return 480;
        }
        return 0;
    }
    
    public int baseAndGearAndExtraStat(final StatT type) {
        return this.baseStat(type) + this.gearAndExtraStat(type);
    }
    
    public int gearAndExtraStat(final StatT type) {
        return this.totalStat(type) + this.raceAndProfStat(type);
    }
    
    public int baseStat(final StatT type) {
        return this.baseStats.get(type);
    }
    
    public int enchantsStat(final StatT type) {
        int sum = 0;
        for (final Slot x : this.SLOTS) {
            sum += x.sumStat_enchant(type);
        }
        return sum;
    }
    
    public int gemsStat(final StatT type) {
        int sum = 0;
        for (final Slot x : this.SLOTS) {
            sum += x.sumStat_gems(type);
        }
        return sum;
    }
    
    public int gearStat(final StatT type, final boolean reforged) {
        int sum = 0;
        for (final Slot x : this.SLOTS) {
            sum += (reforged ? x.sumStat_reforgedItem(type) : x.sumStat_originalItem(type));
        }
        return sum;
    }
    
    public int totalStat(final StatT type) {
        return this.totalStat(type, 15);
    }
    
    public int totalStat(final StatT type, final int bodyParts) {
        int sum = 0;
        for (final Slot x : this.SLOTS) {
            if ((x.type.bodyPart & bodyParts) == x.type.bodyPart) {
                sum += x.sumStat(type);
            }
        }
        return sum;
    }
    
    public void reforge(final ReforgeAlgo strat, final Set<SlotT> slotSet, final StatT[] uni, final long iterCnt, final int coreCnt, final LineWriter lw, final AtomicBoolean abort, final JProgressBar prog) {
        final HashMap<StatT, Integer> initMap = new HashMap<StatT, Integer>();
        final Reforger r = new Reforger();
        for (final StatT t : uni) {
            int sum = this.raceAndProfStat(t);
            for (final Slot x : this.SLOTS) {
                if (x.item != null) {
                    if (slotSet == null || slotSet.contains(x.type)) {
                        sum += x.sumStat_unreforgeable(t);
                        r.addSlot(x);
                    }
                    else {
                        sum += x.sumStat(t);
                    }
                }
            }
            if (sum > 0) {
                initMap.put(t, sum);
            }
        }
        r.reforge(strat, initMap, uni, iterCnt, coreCnt, lw, abort, prog);
        if (lw != null) {
            lw.add();
            int tot = 0;
            for (final StatT x2 : uni) {
                final int sum2 = this.totalStat(x2, 15);
                lw.add("%-20s = %5d", x2, sum2);
                tot += sum2;
            }
            lw.add("Total: %d", tot);
            lw.add();
            lw.add("Reforgings:");
            for (final Slot x3 : this.SLOTS) {
                lw.add("%-10s %s", x3.type.name, x3.reforge);
            }
        }
    }
    
    public String toReforgerade() {
        final HashMap<StatT, String> statMap = new HashMap<StatT, String>();
        statMap.put(StatT.CRIT, "CritRating");
        statMap.put(StatT.HASTE, "HasteRating");
        statMap.put(StatT.MASTERY, "MasteryRating");
        statMap.put(StatT.HIT, "HitRating");
        statMap.put(StatT.EXP, "ExpertiseRating");
        statMap.put(StatT.SPI, "Spirit");
        final HashMap<SlotT, String> slotMap = new HashMap<SlotT, String>();
        slotMap.put(SlotT.TRINKET_1, "Trinket1");
        slotMap.put(SlotT.TRINKET_2, "Trinket2");
        slotMap.put(SlotT.FINGER_1, "Ring1");
        slotMap.put(SlotT.FINGER_2, "Ring2");
        slotMap.put(SlotT.WRIST, "Wrists");
        slotMap.put(SlotT.SHOULDER, "Shoulders");
        slotMap.put(SlotT.MAIN_HAND, "MainHand");
        slotMap.put(SlotT.OFF_HAND, "OffHand");
        final StringBuilder sb = new StringBuilder(512);
        for (final Slot x : this.SLOTS) {
            if (x.reforge != null) {
                String slotName = slotMap.get(x.type);
                if (slotName == null) {
                    slotName = x.type.name;
                }
                if (sb.length() > 0) {
                    sb.append('\n');
                }
                sb.append(String.format("%s : %s -> %s", slotName, statMap.get(x.reforge.src), statMap.get(x.reforge.dst)));
            }
        }
        return sb.toString();
    }
    
    public String toAskMrRobot() {
        final StringBuilder sb = new StringBuilder();
        final boolean asia = this.region != null && this.region.asia;
        if (this.charName != null) {
            sb.append("name=");
            sb.append(this.charName);
            sb.append(";");
        }
        if (this.realmName != null) {
            sb.append("realm=");
            sb.append(this.realmName);
            sb.append(";");
        }
        if (this.race != null) {
            sb.append("race=");
            sb.append(this.race.key);
            sb.append(";");
            sb.append("faction=");
            sb.append(this.race.faction.name);
            sb.append(";");
        }
        boolean first = true;
        sb.append("professions=");
        for (final Map.Entry<ProfT, Integer> e : this.skillMap.entrySet()) {
            if (first) {
                first = false;
            }
            else {
                sb.append(',');
            }
            sb.append(e.getKey().name);
            sb.append(':');
            sb.append(e.getValue());
        }
        sb.append(";");
        if (this.spec != null) {
            sb.append("spec=");
            sb.append(this.spec.specId);
            sb.append(";");
        }
        if (this.talents != null) {
            sb.append("talents=");
            sb.append(this.talents);
            sb.append(";");
        }
        sb.append(";");
        for (final Slot slot : this.SLOTS) {
            if (!slot.isEmpty()) {
                sb.append("item=");
                sb.append(slot.type.id - 1);
                sb.append(':');
                sb.append(slot.item.itemId);
                sb.append(':');
                sb.append((slot.item.suffixes != null) ? (-slot.getSuffixData().id) : 0);
                sb.append(':');
                int up = slot.getEffectiveUpgradeLevel(asia);
                if (up < 0) {
                    up = 0;
                }
                else if (!slot.item.hasIntermediateUpgrade()) {
                    up += 451;
                }
                else if (asia) {
                    if (up > 4) {
                        throw new RuntimeException("AskMrRobot addon doesn't support */6 upgrades for Asia.");
                    }
                    up += 494;
                }
                else if (up > 2) {
                    up += 501;
                }
                else {
                    up += 491;
                }
                sb.append(up);
                sb.append(':');
                final int n = slot.getSocketCount();
                if (n > 0) {
                    for (int i = 0; i < n; ++i) {
                        if (i > 0) {
                            sb.append(',');
                        }
                        sb.append(Character.toLowerCase(slot.getSocketAt(i).type.charAt(0)));
                    }
                    sb.append(':');
                    for (int i = 0; i < n; ++i) {
                        if (i > 0) {
                            sb.append(',');
                        }
                        sb.append('0');
                    }
                    sb.append(':');
                    for (int i = 0; i < n; ++i) {
                        if (i > 0) {
                            sb.append(',');
                        }
                        final Gem gem = slot.getGemAt(i);
                        sb.append((gem == null) ? 0 : gem.itemId);
                    }
                }
                else {
                    sb.append("0:0:0");
                }
                sb.append(':');
                sb.append((slot.enchant != null) ? slot.enchant.id : 0);
                sb.append(':');
                sb.append((slot.reforge != null) ? slot.reforge.getCode() : 0);
                sb.append(";");
            }
        }
        return sb.toString();
    }
    
    public boolean importReforges(final String code, final Collection<String> errors) {
        if (errors != null) {
            errors.clear();
        }
        boolean ok = true;
        class S<T>
        {
            T thing;
            int dist;
            
            S(final T x) {
                this.thing = x;
            }
        }
        final Comparator<S> cmp = new Comparator<S>() {
            @Override
            public int compare(final S a, final S b) {
                return a.dist - b.dist;
            }
        };
        final int n = this.SLOTS.length;
        final S<Slot>[] slots = (S<Slot>[])new S[n];
        for (int i = 0; i < n; ++i) {
            slots[i] = new S<Slot>(this.SLOTS[i]);
        }
        for (String line2 : code.split("\n")) {
            final String line0 = line2;
            int pos = line2.indexOf("#");
            if (pos >= 0) {
                line2 = line2.substring(0, pos);
            }
            line2 = line2.trim();
            if (!line2.isEmpty()) {
                pos = line2.indexOf(58);
                if (pos == -1) {
                    if (errors != null) {
                        errors.add("Missing (:) separator: " + line0);
                    }
                    ok = false;
                }
                else {
                    String slotName = line2.substring(0, pos).trim();
                    if (slotName.isEmpty()) {
                        if (errors != null) {
                            errors.add("Missing slot name:  " + line0);
                        }
                        ok = false;
                    }
                    else {
                        slotName = slotName.replace("Ring", "Finger");
                        for (final S<Slot> s : slots) {
                            s.dist = Utils.editDist(s.thing.type.name, slotName);
                        }
                        Arrays.sort(slots, cmp);
                        final Slot slot = slots[0].thing;
                        line2 = line2.substring(pos + 1).trim();
                        final String[] temp = line2.split("\\b");
                        if (temp.length < 3) {
                            if (errors != null) {
                                errors.add("Unable to determine reforges:  " + line0);
                            }
                            ok = false;
                        }
                        else {
                            final String src = temp[1];
                            final String dst = temp[temp.length - 1];
                            final StatT srcStat = StatT.findBestMatch(StatT.REFORGE, src);
                            if (srcStat == null) {
                                if (errors != null) {
                                    errors.add("Unable to decipher source stat: " + src);
                                }
                                ok = false;
                            }
                            else {
                                final StatT dstStat = StatT.findBestMatch(StatT.REFORGE, dst);
                                if (srcStat == null) {
                                    if (errors != null) {
                                        errors.add("Unable to decipher destination stat: " + dst);
                                    }
                                    ok = false;
                                }
                                else {
                                    final ReforgePair reforge = ReforgePair.make(srcStat, dstStat);
                                    try {
                                        slot.setReforge(reforge);
                                    }
                                    catch (RuntimeException err) {
                                        if (errors != null) {
                                            errors.add(err.getMessage());
                                        }
                                        ok = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return ok;
    }
    
    public void clearReforges() {
        for (final Slot x : this.SLOTS) {
            x.reforge = null;
        }
    }
    
    public void clearEnchantsAndTinkers() {
        for (final Slot x : this.SLOTS) {
            x.enchant = null;
            x.tinker = null;
        }
    }
    
    public void clearGems() {
        for (final Slot x : this.SLOTS) {
            Arrays.fill(x.gems, null);
        }
    }
    
    public int getTinkerCount() {
        int count = 0;
        for (final Slot x : this.SLOTS) {
            if (x.hasTinker()) {
                ++count;
            }
        }
        return count;
    }
    
    public int getEmptyTinkerCount() {
        int count = 0;
        for (final Slot x : this.SLOTS) {
            if (!x.hasTinker() && x.isTinkerable()) {
                ++count;
            }
        }
        return count;
    }
    
    public int getEmptyEnchantCount() {
        int count = 0;
        for (final Slot x : this.SLOTS) {
            if (!x.hasEnchant() && x.isEnchantable()) {
                ++count;
            }
        }
        return count;
    }
    
    public int getEnchantCount() {
        int count = 0;
        for (final Slot x : this.SLOTS) {
            if (x.hasEnchant()) {
                ++count;
            }
        }
        return count;
    }
    
    public int getGemCount() {
        int count = 0;
        for (final Slot x : this.SLOTS) {
            if (x.item != null) {
                for (int i = 0, e = x.getSocketCount(); i < e; ++i) {
                    if (x.getGemAt(i) != null) {
                        ++count;
                    }
                }
            }
        }
        return count;
    }
    
    public int getSocketCount() {
        int count = 0;
        for (final Slot x : this.SLOTS) {
            if (x.item != null) {
                count += x.getSocketCount();
            }
        }
        return count;
    }
    
    public int getEquippedCount() {
        int count = 0;
        for (final Slot x : this.SLOTS) {
            if (x.item != null) {
                ++count;
            }
        }
        return count;
    }
    
    public boolean isNaked() {
        for (final Slot x : this.SLOTS) {
            if (x.item != null) {
                return false;
            }
        }
        return true;
    }
    
    public int getEmptySlotCount() {
        int count = 0;
        for (final Slot x : this.SLOTS) {
            if (x.item == null) {
                ++count;
            }
        }
        if (this.hasTwoHander()) {
            --count;
        }
        return count;
    }
    
    public int getReforgedCount() {
        int count = 0;
        for (final Slot x : this.SLOTS) {
            if (x.reforge != null) {
                ++count;
            }
        }
        return count;
    }
    
    public boolean hasCustomItemLevel() {
        for (final Slot x : this.SLOTS) {
            if (x.isCustomItemLevel()) {
                return true;
            }
        }
        return false;
    }
    
    public void setSmartSockets(final Map<GemT, Gem> map, final Gem gem0, final double[] weights) {
        final StatMap temp = new StatMap();
        final int[] colored = new int[10];
        for (final Slot x : this.SLOTS) {
            Label_0413: {
                if (x.item != null) {
                    if (x.extraSocket) {
                        x.setGemAt(x.getSocketCount() - 1, gem0);
                    }
                    final GemT[] sockets = x.item.sockets;
                    if (sockets != null) {
                        int count = 0;
                        for (int i = 0; i < sockets.length; ++i) {
                            if (sockets[i].colored()) {
                                colored[count++] = i;
                            }
                        }
                        if (count != 0) {
                            if (x.item.socketBonus != null) {
                                int i = 0;
                                while (i < count) {
                                    if (!sockets[colored[i]].matches(gem0)) {
                                        temp.clear();
                                        for (i = 0; i < count; ++i) {
                                            temp.add(gem0.stats);
                                        }
                                        final double w0 = temp.dot(weights);
                                        temp.clear();
                                        temp.set(x.item.socketBonus);
                                        for (int j = 0; j < count; ++j) {
                                            final Gem gem = map.get(sockets[colored[j]]);
                                            if (gem != null) {
                                                temp.add(gem.stats);
                                            }
                                        }
                                        final double w2 = temp.dot(weights);
                                        if (w0 > w2) {
                                            for (int k = 0; k < count; ++k) {
                                                x.setGemAt(colored[k], gem0);
                                            }
                                            break Label_0413;
                                        }
                                        for (final int index : colored) {
                                            x.setGemAt(index, map.get(sockets[index]));
                                        }
                                        break Label_0413;
                                    }
                                    else {
                                        ++i;
                                    }
                                }
                            }
                            for (int i = 0; i < count; ++i) {
                                x.setGemAt(colored[i], gem0);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void setGemsOnce(final Collection<Gem> gems, final boolean onlyEmpty) {
    Label_0007:
        while (true) {
            for (final Gem gem : gems) {
                for (final Slot x : this.SLOTS) {
                    if (x.item != null) {
                        for (int count = x.getSocketCount(), i = 0; i < count; ++i) {
                            if (!onlyEmpty || x.getGemAt(i) == null) {
                                final GemT socket = x.getSocketAt(i);
                                if (socket.matches(gem)) {
                                    try {
                                        x.setGemAt(i, gem);
                                        continue Label_0007;
                                    }
                                    catch (RuntimeException ex) {}
                                }
                            }
                        }
                    }
                }
            }
            break;
        }
    }
    
    public void setSomeSockets(final Collection<Gem> gems, final GemT[] priority) {
        final HashSet<Integer> used = new HashSet<Integer>();
        final LinkedList<Gem> temp = new LinkedList<Gem>(gems);
    Label_0050_Outer:
        for (final GemT p : priority) {
            final Iterator<Gem> iter = temp.iterator();
        Label_0050:
            while (true) {
                while (iter.hasNext()) {
                    final Gem gem = iter.next();
                    if (!p.matches(gem)) {
                        continue Label_0050_Outer;
                    }
                    for (final Slot x : this.SLOTS) {
                        if (x.item != null) {
                            for (int count = x.getSocketCount(), i = 0; i < count; ++i) {
                                final GemT socket = x.getSocketAt(i);
                                if (socket.matches(gem)) {
                                    final Integer gemHash = 1000 * x.type.id + i;
                                    if (!used.contains(gemHash)) {
                                        System.out.println(socket + ":" + gem);
                                        x.setGemAt(i, gem);
                                        used.add(gemHash);
                                        iter.remove();
                                        continue Label_0050;
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            }
            if (temp.isEmpty()) {
                return;
            }
        }
        if (!temp.isEmpty()) {
            throw new RuntimeException("Unable to find sockets for the following gems:\n\n" + Fmt.join(temp, "\n"));
        }
    }
    
    public void replaceGems(final GemT replaceType, final GemT socketType, final Gem replace) {
        this.replaceGems(replaceType, Collections.singleton(socketType), replace);
    }
    
    public void replaceGems(final GemT replaceType, final Set<GemT> socketSet, final Gem replace) {
        for (final Slot x : this.SLOTS) {
            if (x.item != null) {
                for (int count = x.getSocketCount(), i = 0; i < count; ++i) {
                    final Gem gem = x.getGemAt(i);
                    if (gem == null && socketSet != null) {
                        final GemT socket = x.getSocketAt(i);
                        if (socket.accepts(replace) && socketSet.contains(socket)) {
                            x.setGemAt(i, replace);
                        }
                    }
                    else if (gem.minorType == replaceType) {
                        x.setGemAt(i, replace);
                    }
                }
            }
        }
    }
    
    public void swapGems(final int id, final Gem replace) {
        for (final Slot x : this.SLOTS) {
            if (x.item != null) {
                for (int count = x.getSocketCount(), i = 0; i < count; ++i) {
                    final Gem gem = x.getGemAt(i);
                    if (gem != null && gem.itemId == id) {
                        x.setGemAt(i, replace);
                    }
                }
            }
        }
    }
    
    public void setAllSockets(final GemT socket, final Gem gem) {
        this.setAllSockets(Collections.singletonMap(socket, gem), false);
    }
    
    public void setAllSockets(final Map<GemT, Gem> map, final boolean onlyEmpty) {
        for (final Slot x : this.SLOTS) {
            if (x.item != null) {
                for (int count = x.getSocketCount(), i = 0; i < count; ++i) {
                    final GemT socket = x.getSocketAt(i);
                    if (map.containsKey(socket)) {
                        if (!onlyEmpty || x.getGemAt(i) == null) {
                            final Gem gem = map.get(socket);
                            x.setGemAt(i, gem);
                        }
                    }
                }
            }
        }
    }
    
    public double avgItemLevel() {
        int sum = 0;
        for (final Slot x : this.SLOTS) {
            sum += x._actualItemLevel;
        }
        return sum / (double)(this.hasTwoHander() ? (this.SLOTS.length - 1) : this.SLOTS.length);
    }
    
    public void collectItemSets(final Set<ItemSet> set) {
        for (final Slot x : this.SLOTS) {
            if (x.item != null && x.item.itemSet != null) {
                set.add(x.item.itemSet);
            }
        }
    }
    
    public ItemSet[] itemSets() {
        final HashSet<ItemSet> set = new HashSet<ItemSet>();
        this.collectItemSets(set);
        return set.toArray(new ItemSet[set.size()]);
    }
    
    public Slot firstSlotFromItemSet(final int id) {
        for (final Slot x : this.SLOTS) {
            if (x.item != null && x.item.itemSet != null && x.item.itemSet.id == id) {
                return x;
            }
        }
        return null;
    }
    
    public void syncSetBonuses(final SetBonusMap map) {
        map.clear();
        final HashSet<ItemSet> sets = new HashSet<ItemSet>();
        this.collectItemSets(sets);
        for (final ItemSet set : sets) {
            final int have = this.itemSetCount(set.id);
            for (final SetBonus x : set.bonuses) {
                if (have >= x.threshold) {
                    map.setBonus(set.id, x.index);
                }
            }
        }
    }
    
    public int itemSetCount(final int id) {
        int count = 0;
        for (final Slot x : this.SLOTS) {
            if (x.item != null && x.item.itemSet != null && x.item.itemSet.id == id) {
                ++count;
            }
        }
        return count;
    }
    
    public boolean hasArmorSpecialization() {
        return this.hasSpecificArmorType(this.spec.armorType);
    }
    
    public boolean hasSpecificArmorType(final ArmorT type) {
        for (final Slot x : this.SLOTS) {
            if (x.type.bodyPart == 1 && (x.item == null || x.item.minorType != type)) {
                return false;
            }
        }
        return true;
    }
    
    public Slot nextEmptySlot(final EquipT equip) {
        for (final Slot x : this.SLOTS) {
            if (x.item == null && x.type.accepts(equip)) {
                return x;
            }
        }
        return null;
    }
    
    public int getUniqueCount(final String key) {
        int count = 0;
        final ArrayList<Item> list = new ArrayList<Item>();
        for (final Slot x : this.SLOTS) {
            list.clear();
            x.collectItems(list);
            for (final Item i : list) {
                if (key.equals(i.uniqueKey)) {
                    ++count;
                }
            }
        }
        return count;
    }
    
    public int[][] getSocketCoords(final Set<SlotT> slotSet) {
        final ArrayList<int[]> temp = new ArrayList<int[]>();
        for (final Slot x : this.SLOTS) {
            if (slotSet == null || slotSet.contains(x.type)) {
                for (int i = 0, n = x.getSocketCount(); i < n; ++i) {
                    temp.add(new int[] { x.index, i });
                }
            }
        }
        return temp.toArray(new int[temp.size()][]);
    }
    
    public int minimizeGems(final Profile p0, final Set<SlotT> slotSet) {
        final int[][] coords = p0.getSocketCoords(slotSet);
        final int num = coords.length;
        final boolean[] matched = new boolean[num];
        for (int i = 0; i < num; ++i) {
            final int[] v = coords[i];
            final int slotIndex0 = v[0];
            final int gemIndex0 = v[1];
            final Gem g0 = p0.SLOTS[slotIndex0].getGemAt(gemIndex0);
            if (g0 != null) {
                final GemT s1 = this.SLOTS[slotIndex0].getSocketAt(gemIndex0);
                if (s1 != null) {
                    final Gem g2 = this.SLOTS[slotIndex0].getGemAt(gemIndex0);
                    if (Gem.sameAs(g0, g2)) {
                        matched[i] = true;
                    }
                    else if (g2 == null || g0.getType() == g2.getType()) {
                        for (int j = 0; j < num; ++j) {
                            if (i != j) {
                                if (!matched[j]) {
                                    final int[] u = coords[j];
                                    final int slotIndex2 = u[0];
                                    final int gemIndex2 = u[1];
                                    final Gem g3 = this.SLOTS[slotIndex2].getGemAt(gemIndex2);
                                    if (Gem.sameAs(g0, g3)) {
                                        this.SLOTS[slotIndex2].setGemAt(gemIndex2, null);
                                        this.SLOTS[slotIndex0].setGemAt(gemIndex0, g3);
                                        this.SLOTS[slotIndex2].setGemAt(gemIndex2, g2);
                                        matched[i] = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        int changed = 0;
        for (int k = 0; k < num; ++k) {
            if (!matched[k]) {
                ++changed;
            }
        }
        return changed;
    }
    
    public String statStr(final StatT[] stats) {
        final StringBuilder sb = new StringBuilder();
        for (final StatT x : stats) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(this.gearAndExtraStat(x));
            sb.append(' ');
            sb.append(x.abbr);
        }
        return sb.toString();
    }
    
    static {
        $ = new SlotT[] { SlotT.HEAD, SlotT.NECK, SlotT.SHOULDER, SlotT.BACK, SlotT.CHEST, SlotT.WRIST, SlotT.HANDS, SlotT.WAIST, SlotT.LEGS, SlotT.FEET, SlotT.FINGER_1, SlotT.FINGER_2, SlotT.TRINKET_1, SlotT.TRINKET_2, SlotT.MAIN_HAND, SlotT.OFF_HAND };
        Arrays.fill(SLOT_IDX = new int[SlotT.NUM], -1);
        for (int i = 0; i < Profile.$.length; ++i) {
            Profile.SLOT_IDX[Profile.$[i].id] = i;
        }
    }
    
    public class Slot implements StatValued
    {
        public final SlotT type;
        public final int index;
        private int _priorHit;
        private int _priorExp;
        private int _sec;
        public Item item;
        public EnchantT enchant;
        public TinkerT tinker;
        public boolean extraSocket;
        public final Gem[] gems;
        public ReforgePair reforge;
        private int _suffixIndex;
        private int _itemLevel;
        static final double socketCost = 160.0;
        private int _actualItemLevel;
        private int _itemBaseArmor;
        private double _itemMinDmg;
        private double _itemMaxDmg;
        private final StatMap _itemExtraStats;
        
        private Slot(final int index, final SlotT info) {
            this.gems = new Gem[GemT.KEYS.length];
            this._itemExtraStats = new StatMap();
            this.index = index;
            this.type = info;
        }
        
        @Override
        public String toString() {
            return this.type.name + ": " + this.item;
        }
        
        public StatValue getSocketBonus() {
            return this.hasSocketBonus() ? this.item.socketBonus : null;
        }
        
        public int getSocketCount() {
            return (this.item == null) ? 0 : ((this.extraSocket ? 1 : 0) + ((this.item.sockets == null) ? 0 : this.item.sockets.length));
        }
        
        public int calcDiff(final StatMap map) {
            int diff = 0;
            for (final StatT x : StatT.STATS) {
                diff += Math.abs(map.get(x) - this.sumStat(x));
            }
            return diff;
        }
        
        private void checkItem(final Item x) {
            if (x.allowableClasses != 0 && Profile.this.spec != null && (Profile.this.spec.classType.bit & x.allowableClasses) == 0x0) {
                throw new RuntimeException(String.format("Unable to equip %s: %s - %s not allowed", this.type.name, x.name, Profile.this.spec.classType));
            }
            if (x.allowableRaces != 0 && Profile.this.race != null && (Profile.this.race.bit & x.allowableRaces) == 0x0) {
                throw new RuntimeException(String.format("Unable to equip %s: %s - %s not allowed", this.type.name, x.name, Profile.this.race));
            }
            if (x.requiredProf != null && !x.requiredProf.isMemberOf(Profile.this.profs)) {
                throw new RuntimeException(String.format("Unable to equip %s: %s - %s required", this.type.name, x.name, x.requiredProf));
            }
            if (x.uniqueKey != null && Profile.this.getUniqueCount(x.uniqueKey) >= x.uniqueCount) {
                throw new RuntimeException(String.format("Unable to equip %s: %s - maximum number (%d) of %s have been equipped", this.type.name, x.name, x.uniqueCount, x.uniqueKey));
            }
        }
        
        public void swapItem(final Item x) {
            final TinkerT t = this.tinker;
            final EnchantT e = this.enchant;
            final int delta = this.getItemLevelDelta();
            final boolean es = this.extraSocket;
            final Gem[] g = this.gems.clone();
            this.setItem(x);
            this.setItemLevelDelta(delta);
            this.setEnchant(e);
            this.setTinker(t);
            this.extraSocket = es;
            for (int i = 0, n = this.getSocketCount(); i < n; ++i) {
                this.setGemAt(i, g[i]);
            }
        }
        
        public void setItem(final Item x) {
            if (x == null) {
                this.clear();
                return;
            }
            if (!this.type.accepts(x.equipType)) {
                throw new RuntimeException(String.format("Unable to equip %s: %s does not accept %s", x.name, this.type.name, x.equipType));
            }
            if (Profile.this.spec != null && x instanceof Weapon) {
                if (!Profile.this.spec.classType.weaponSet.contains((WeaponT)x.minorType)) {
                    throw new RuntimeException(String.format("Unable to equip %s: cannot use %s", x.name, x.minorType));
                }
                if (this == Profile.this.OH && !Profile.this.spec.canDualWield) {
                    throw new RuntimeException(String.format("Unable to equip %s: cannot dual wield", x.name));
                }
            }
            if (this == Profile.this.MH && WeaponT.isTwoHander(x)) {
                Profile.this.OH.clear();
            }
            else if (this == Profile.this.OH && Profile.this.hasTwoHander()) {
                Profile.this.MH.clear();
            }
            this.clear();
            this.checkItem(x);
            this.item = x;
            this.validateSocketsAndGems();
            this.setItemLevelDelta(0);
        }
        
        public void collectSpells(final IntSet set) {
            if (this.item == null) {
                return;
            }
            if (this.item.itemSpells != null) {
                set.addAll(this.item.itemSpells);
            }
            for (int i = 0, n = this.getSocketCount(); i < n; ++i) {
                final Gem gem = this.gems[i];
                if (gem != null && gem.itemSpells != null) {
                    set.addAll(gem.itemSpells);
                }
            }
        }
        
        void validateSocketsAndGems() {
            if (Profile.this.dollMode && this.item != null) {
                if (this.isWeapon() && ((Weapon)this.item).extraSocket) {
                    this.extraSocket = true;
                }
                else if (this.type == SlotT.WAIST) {
                    this.extraSocket = true;
                }
                else if ((this.type == SlotT.HANDS || this.type == SlotT.WRIST) && ProfT.BS.isMemberOf(Profile.this.profs)) {
                    this.extraSocket = true;
                }
            }
            for (int i = 0; i < this.gems.length; ++i) {
                final GemT socket = this.getSocketAt(i);
                final Gem gem = this.getGemAt(i);
                if (socket == null || gem == null || !socket.accepts(gem) || !gem.validate(Profile.this.spec.classType, Profile.this.race, Profile.this.profs)) {
                    this.gems[i] = null;
                }
            }
        }
        
        public void setSuffixId(final int id) {
            if (this.item == null) {
                return;
            }
            final Suffix[] v = this.item.suffixes;
            if (v == null) {
                return;
            }
            for (int i = 0; i < v.length; ++i) {
                if (v[i].id == id) {
                    this.setSuffixIndex(i);
                    return;
                }
            }
        }
        
        public void setSuffixIndex(final int index) {
            if (this.item == null) {
                this._suffixIndex = 0;
            }
            else {
                if (this.item.suffixes == null) {
                    throw new RuntimeException(String.format("Suffix is not supported for %s", this.item.name));
                }
                if (index < 0 || index >= this.item.suffixes.length) {
                    throw new RuntimeException(String.format("Invalid suffix for %s", this.item.name));
                }
                this._suffixIndex = index;
            }
            this._updateItemData(true);
        }
        
        public void setTinker(final TinkerT tink) {
            if (this.item == null) {
                if (tink != null) {
                    throw new RuntimeException(String.format("Unable to tinker %s: %s is empty", tink.name, this.type.name));
                }
            }
            else if (tink == null) {
                this.tinker = null;
            }
            else {
                if (!ProfT.ENG.isMemberOf(Profile.this.profs)) {
                    throw new RuntimeException(String.format("Unable to tinker %s: %s requires %s", this.item.name, tink.name, ProfT.ENG.name));
                }
                if (!tink.set.contains(this.item.equipType)) {
                    throw new RuntimeException(String.format("Unable to tinker %s: %s does not work with %s", this.item.name, tink.name, this.item.equipType));
                }
                this.tinker = tink;
            }
        }
        
        public void setEnchant(final EnchantT ench) {
            if (this.item == null) {
                if (ench != null) {
                    throw new RuntimeException(String.format("Unable to enchant %s: %s is empty", ench.name, this.type.name));
                }
            }
            else if (ench == null) {
                this.enchant = null;
            }
            else {
                if (!ench.set.contains(this.item.equipType)) {
                    throw new RuntimeException(String.format("Unable to enchant %s: %s does not work with %s", this.item.name, ench.name, this.item.equipType));
                }
                if (ench.requiredProf != null && !ench.requiredProf.isMemberOf(Profile.this.profs)) {
                    throw new RuntimeException(String.format("Unable to enchant %s: %s requires %s", this.item.name, ench.name, ench.requiredProf));
                }
                this.enchant = ench;
            }
        }
        
        public Profile getProfile() {
            return Profile.this;
        }
        
        public String getItemName(final boolean suffix) {
            if (this.item == null) {
                return null;
            }
            if (this._itemLevel != this._actualItemLevel) {
                return String.format("(%d) [%d] %s", this._actualItemLevel, this._itemLevel, suffix ? this.item.fullName() : this.item.name);
            }
            return String.format("[%d] %s", this._itemLevel, suffix ? this.item.fullName() : this.item.name);
        }
        
        public String getItemNameOrEmpty() {
            return this.getItemNameOrEmpty(true);
        }
        
        public String getItemNameOrEmpty(final boolean suffix) {
            return (this.item == null) ? ("Empty " + this.type.name) : this.getItemName(suffix);
        }
        
        public String getItemAbbrOrNone() {
            return (this.item == null) ? "None" : String.format("[%d] %s", this._actualItemLevel, API.abbrName(this.item.name));
        }
        
        public boolean isCustomItemLevel() {
            return this.item != null && this._itemLevel != this.item.itemLevel;
        }
        
        public boolean isScaledItemLevel() {
            return this.item != null && this._itemLevel != this._actualItemLevel;
        }
        
        public int getItemLevelDelta() {
            return (this.item == null) ? 0 : (this._itemLevel - this.item.itemLevel);
        }
        
        public int getEffectiveUpgradeLevel(final boolean asia) {
            if (this.item == null) {
                return 0;
            }
            final int diff = this._itemLevel - this.item.itemLevel;
            if (diff == 0) {
                return 0;
            }
            if (diff < 0 || !this.item.upgradable) {
                return -1;
            }
            if (!this.item.hasIntermediateUpgrade()) {
                return (diff == 8) ? 1 : -1;
            }
            if (asia) {
                switch (diff) {
                    case 4: {
                        return 1;
                    }
                    case 8: {
                        return 2;
                    }
                    case 12: {
                        return 3;
                    }
                    case 16: {
                        return 4;
                    }
                    case 20: {
                        return this.item.hasExtraUpgrade() ? 5 : -1;
                    }
                    case 24: {
                        return this.item.hasExtraUpgrade() ? 6 : -1;
                    }
                    default: {
                        return -1;
                    }
                }
            }
            else {
                switch (diff) {
                    case 4: {
                        return 1;
                    }
                    case 8: {
                        return 2;
                    }
                    case 12: {
                        return this.item.hasExtraUpgrade() ? 3 : -1;
                    }
                    case 16: {
                        return this.item.hasExtraUpgrade() ? 4 : -1;
                    }
                    default: {
                        return -1;
                    }
                }
            }
        }
        
        public int getItemLevelUpgradeMaxLevel(final boolean asia) {
            return (this.item == null) ? 0 : this.item.getUpgradeLimit(asia);
        }
        
        public int getItemLevelUpdateMaxDelta(final boolean asia) {
            if (this.item == null) {
                return 0;
            }
            return this.item.getUpgradeLimit(asia) * (this.item.hasIntermediateUpgrade() ? 4 : 8);
        }
        
        public void setItemLevelUpgradeMax(final boolean asia) {
            if (this.item != null) {
                this.setItemLevelDelta(this.getItemLevelUpdateMaxDelta(asia));
            }
        }
        
        public void setItemLevelDelta(final int delta) {
            if (this.item != null) {
                this.setItemLevel(this.item.itemLevel + delta);
            }
        }
        
        public void setItemLevel(final int level) {
            if (this.item == null) {
                return;
            }
            if (level < 1) {
                throw new RuntimeException(String.format("Unable to change %s item level: must be positive (%d)", this.item.name, level));
            }
            if (level == this._itemLevel) {
                return;
            }
            this._itemLevel = level;
            this._updateItemData(true);
        }
        
        private void _updateItemData(final boolean local) {
            if (local && Profile.this.scaledItemLevel < 0) {
                Profile.this._updateAllItemData();
                return;
            }
            this._itemBaseArmor = 0;
            this._itemMinDmg = 0.0;
            this._itemMaxDmg = 0.0;
            this._actualItemLevel = 0;
            this._itemExtraStats.clear();
            if (this.item == null) {
                return;
            }
            int ilvl = this._itemLevel;
            if (Profile.this.scaledItemLevel < 0) {
                ilvl = Math.min(ilvl, -Profile.this.scaledItemLevel);
            }
            else if (Profile.this.scaledItemLevel > 0) {
                ilvl = Profile.this.scaledItemLevel;
            }
            this._actualItemLevel = ilvl;
            final int budget = ItemT.statBudget(ilvl, this.item.equipType.slotMod);
            if (this.item instanceof Armor) {
                this._itemBaseArmor = ((ArmorT)this.item.minorType).armor(ilvl, this.item.equipType.armorMod);
            }
            else if (this.item instanceof Weapon) {
                final Weapon w = (Weapon)this.item;
                double dmg = WeaponT.dps(ilvl, this.item.quality) * w.dpsToDmgMod();
                double rng;
                if (w.isTwoHander()) {
                    rng = 0.2;
                }
                else {
                    dmg *= 0.7416666666666667;
                    rng = 0.3;
                }
                this._itemMinDmg = (1.0 - rng) * dmg;
                this._itemMaxDmg = (1.0 + rng) * dmg;
            }
            this.applyAlloc(this.item.allocDist, budget, 160.0, true);
            if (this.item.suffixes != null) {
                this.applyAlloc(this.item.suffixes[this._suffixIndex].allocDist, budget, 0.0, false);
            }
            this.applyAlloc(this.item.spellStats, budget);
        }
        
        private void applyAlloc(final SpellAlloc[] allocDist, final int budget) {
            if (allocDist == null) {
                return;
            }
            for (final SpellAlloc x : allocDist) {
                final int value0 = this.itemStat(x.stat);
                final int value2 = (int)(0.5 + x.scale * budget);
                final int extra = value2 - value0;
                if (extra != 0) {
                    this._itemExtraStats.add(x.stat, extra);
                }
            }
        }
        
        private void applyAlloc(final StatAlloc[] allocDist, final int budget1, final double socketCost, final boolean round) {
            if (allocDist == null) {
                return;
            }
            for (final StatAlloc x : allocDist) {
                final int value0 = this.itemStat(x.stat);
                final double left = x.alloc / 10000.0 * budget1;
                final double right = x.mod * socketCost;
                int value2;
                if (round) {
                    value2 = (int)(0.5 + left) - (int)(0.5 + right);
                }
                else {
                    value2 = (int)(left + right);
                }
                final int extra = value2 - value0;
                if (extra != 0) {
                    this._itemExtraStats.add(x.stat, extra);
                }
            }
        }
        
        public final int getSuffixIndex() {
            return this._suffixIndex;
        }
        
        public final Suffix getSuffixData() {
            return (this.item != null && this.item.suffixes != null) ? this.item.suffixes[this._suffixIndex] : null;
        }
        
        public final int getItemLevel() {
            return this._itemLevel;
        }
        
        public final int getScaledItemLevel() {
            return this._actualItemLevel;
        }
        
        public final int getItemArmor() {
            return this._itemBaseArmor + this.sumStat(StatT.ARMOR);
        }
        
        public final double getWeapDmgMin() {
            return this.isWeapon() ? this._itemMinDmg : 0.0;
        }
        
        public final double getWeapDmgMax() {
            return this.isWeapon() ? this._itemMaxDmg : 0.0;
        }
        
        public final double getWeapSpeed() {
            return this.isWeapon() ? ((Weapon)this.item).speed : 0.0;
        }
        
        public final boolean isWeapon() {
            return this.item instanceof Weapon;
        }
        
        public final boolean isArmor() {
            return this.item instanceof Armor;
        }
        
        public final boolean isEmpty() {
            return this.item == null;
        }
        
        public final boolean isEquip() {
            return this.item != null;
        }
        
        public final boolean isWeapon(final EquipT type) {
            return this.item instanceof Weapon && ((Weapon)this.item).minorType == type;
        }
        
        public final boolean isWeapon(final BlizzT.TypeSet<WeaponT> set) {
            return this.item instanceof Weapon && set.contains(((Weapon)this.item).getType());
        }
        
        public boolean isSameOriginalItem(final Slot other) {
            return (this.item == null) ? (other.item == null) : (other.item != null && this.item == other.item);
        }
        
        public boolean isSameItem(final Slot other) {
            if (this.item == null) {
                return other.item == null;
            }
            return this.item == other.item && this._actualItemLevel == other._actualItemLevel && this._suffixIndex == other._suffixIndex;
        }
        
        public boolean isTinkerable() {
            return this.item != null && (this.tinker != null || (ProfT.ENG.isMemberOf(Profile.this.profs) && TinkerT.EQUIP_MAP.get(this.item.equipType).length > 0));
        }
        
        public boolean hasTinker() {
            return this.tinker != null;
        }
        
        public boolean hasEnchant() {
            return this.enchant != null;
        }
        
        public boolean isEnchantable() {
            if (this.item == null) {
                return false;
            }
            if (this.enchant != null) {
                return true;
            }
            for (final EnchantT x : EnchantT.EQUIP_MAP.get(this.item.equipType)) {
                if (x.requiredProf == null || x.requiredProf.isMemberOf(Profile.this.profs)) {
                    return true;
                }
            }
            return false;
        }
        
        public boolean hasProfEnchant() {
            return this.item != null && this.enchant != null && this.enchant.requiredProf != null;
        }
        
        public void setGemAt(final int index, final Gem gem) {
            if (this.item == null) {
                if (gem != null) {
                    throw new RuntimeException(String.format("Unable to gem %s: no item", gem.name));
                }
            }
            else {
                final GemT socket = this.getSocketAt(index);
                if (socket == null) {
                    throw new RuntimeException(String.format("Unable to gem %s: no socket available (%d)", gem.name, index));
                }
                this.gems[index] = null;
                if (gem == null) {
                    return;
                }
                this.checkItem(gem);
                if (this.item.itemLevel < gem.minItemLevel) {
                    throw new RuntimeException(String.format("Unable to gem %s: %s item level too low (%d required.)", gem.name, this.item.fullName(), gem.minItemLevel));
                }
                if (!socket.accepts(gem)) {
                    throw new RuntimeException(String.format("Unable to gem %s: %s socket does not accept %s", gem.name, socket, gem.minorType));
                }
                this.gems[index] = gem;
            }
        }
        
        public GemT getSocketAt(final int index) {
            if (this.item == null || this.gems == null) {
                return null;
            }
            final int count = (this.item.sockets == null) ? 0 : this.item.sockets.length;
            if (index >= 0 && index < count) {
                return this.item.sockets[index];
            }
            if (this.extraSocket && index == count) {
                return GemT.WHITE;
            }
            return null;
        }
        
        public boolean isNamed(final String test) {
            return this.item != null && this.item.name.contains(test);
        }
        
        public Gem getGemAt(final int index) {
            return (this.item == null || index < 0 || index >= this.gems.length) ? null : this.gems[index];
        }
        
        public void unsafeCopy(final Slot slot) {
            this.clear();
            if (slot.item == null) {
                return;
            }
            this.setItem(slot.item);
            this.setReforge(slot.reforge);
            this.setEnchant(slot.enchant);
            this.setTinker(slot.tinker);
            this.extraSocket = slot.extraSocket;
            for (int i = 0, e = slot.getSocketCount(); i < e; ++i) {
                this.setGemAt(i, slot.getGemAt(i));
            }
            this._suffixIndex = slot._suffixIndex;
            this._itemLevel = slot._itemLevel;
            this._updateItemData(true);
        }
        
        public void copy(final Slot slot) {
            this.clear();
            if (slot.item == null) {
                return;
            }
            if (!this.type.accepts(slot.item.equipType)) {
                return;
            }
            this.item = slot.item;
            this.reforge = slot.reforge;
            System.arraycopy(slot.gems, 0, this.gems, 0, this.gems.length);
            this.extraSocket = slot.extraSocket;
            this.enchant = slot.enchant;
            this.tinker = slot.tinker;
            this._suffixIndex = slot._suffixIndex;
            this._itemLevel = slot._itemLevel;
            this._updateItemData(true);
        }
        
        public void clearGems() {
            for (int i = 0; i < this.gems.length; ++i) {
                this.gems[i] = null;
            }
        }
        
        public void clear() {
            this.item = null;
            this.enchant = null;
            this.tinker = null;
            this.extraSocket = false;
            this.reforge = null;
            this._suffixIndex = 0;
            this._itemLevel = 0;
            this.clearGems();
            this._updateItemData(true);
        }
        
        public boolean hasType(final ItemT type) {
            return this.item != null && this.item.majorType == type;
        }
        
        public int itemStat(final StatT stat) {
            return (this.item != null) ? this.item.stats.get(stat) : 0;
        }
        
        public void setReforge(final ReforgePair pair) {
            if (this.item == null) {
                if (pair != null) {
                    throw new RuntimeException("Cannot reforge empty " + this.type.name);
                }
            }
            else if (pair != null && (this.sumStat_originalItem(pair.src) == 0 || this.sumStat_originalItem(pair.dst) != 0)) {
                throw new RuntimeException(String.format("%s cannot be reforged as %s", this.item.name, pair));
            }
            this.reforge = pair;
            this._updateItemData(true);
        }
        
        public boolean isUnique() {
            return this.item != null && this.item.uniqueKey != null;
        }
        
        public boolean isUpgradable() {
            return this.item != null && this.item.upgradable;
        }
        
        public boolean canSuffix() {
            return this.item != null && this.item.suffixes != null;
        }
        
        public boolean canReforge(final StatT[] uni) {
            if (this.item == null) {
                return false;
            }
            if (this.reforge != null) {
                return true;
            }
            int num = 0;
            for (final StatT x : uni) {
                if (this.sumStat_originalItem(x) > 0) {
                    ++num;
                }
            }
            return num > 0 && num < uni.length;
        }
        
        public int sumStat_unreforgeable(final StatT x) {
            return this.sumStat_gems(x) + this.sumStat_enchant(x);
        }
        
        public int sumStat_enchant(final StatT x) {
            return (this.item != null && this.enchant != null) ? this.enchant.getStat(x) : 0;
        }
        
        public int sumStat_gems(final StatT x) {
            if (this.item == null) {
                return 0;
            }
            int sum = 0;
            final int n = this.getSocketCount();
            for (int i = 0; i < n; ++i) {
                final Gem gem = this.getGemAt(i);
                if (gem != null) {
                    sum += gem.getStat(x);
                }
            }
            if (n > 0) {
                final StatValue b = this.item.socketBonus;
                if (b != null && b.type == x && this.hasSocketBonus()) {
                    sum += b.value;
                }
            }
            return sum;
        }
        
        public int sumStat_originalItem(final StatT x) {
            if (this.item == null) {
                return 0;
            }
            return this.itemStat(x) + this._itemExtraStats.get(x);
        }
        
        public int sumStat_reforgedItem(final StatT x) {
            if (this.item == null) {
                return 0;
            }
            if (Profile.this._scaledMod > 0.0) {
                if (x == StatT.HIT) {
                    return this._priorHit;
                }
                if (x == StatT.EXP) {
                    return this._priorExp;
                }
                if (x.reforge) {
                    final int sum = this.sumStat_originalItem(x);
                    if (this.reforge != null) {
                        if (x == this.reforge.src) {
                            return (int)(0.5 + Profile.this._scaledMod * (sum - StatT.REFORGE_COEFF * sum));
                        }
                        if (x == this.reforge.dst) {
                            return (int)(0.5 + Profile.this._scaledMod * StatT.REFORGE_COEFF * this.sumStat_originalItem(this.reforge.src));
                        }
                    }
                    return (int)(0.5 + Profile.this._scaledMod * sum);
                }
            }
            int sum = this.sumStat_originalItem(x);
            if (this.reforge != null) {
                if (x == this.reforge.src) {
                    sum -= (int)(StatT.REFORGE_COEFF * sum);
                }
                else if (x == this.reforge.dst) {
                    sum = (int)(StatT.REFORGE_COEFF * this.sumStat_originalItem(this.reforge.src));
                }
            }
            return sum;
        }
        
        public int sumStat(final StatT x) {
            return this.sumStat_reforgedItem(x) + this.sumStat_unreforgeable(x);
        }
        
        int collectItems(final Collection<Item> list) {
            if (this.item == null) {
                return 0;
            }
            final int size0 = list.size();
            list.add(this.item);
            for (int i = 0, e = this.getSocketCount(); i < e; ++i) {
                final Gem gem = this.gems[i];
                if (gem != null) {
                    list.add(gem);
                }
            }
            return list.size() - size0;
        }
        
        public boolean hasSocketBonus() {
            return this.item != null && this.item.hasSocketBonus(this.gems);
        }
        
        @Override
        public int getStat(final StatT stat) {
            return this.sumStat(stat);
        }
        
        public void setGems(final Collection<Gem> gems, final boolean onlyEmpty) {
            if (this.item == null) {
                return;
            }
            final int count = this.getSocketCount();
            if (count == 0) {
                return;
            }
            for (final Gem gem : gems) {
                for (int i = 0; i < count; ++i) {
                    if (!onlyEmpty || this.getGemAt(i) == null) {
                        final GemT socket = this.getSocketAt(i);
                        if (socket.matches(gem)) {
                            try {
                                this.setGemAt(i, gem);
                            }
                            catch (RuntimeException ex) {}
                        }
                    }
                }
            }
        }
    }
}
