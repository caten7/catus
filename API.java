// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.List;
import java.io.File;
import java.awt.Image;
import java.util.Map;
import java.util.TreeSet;
import java.util.Iterator;
import org.json.simple.JSONArray;
import java.util.regex.Pattern;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Comparator;
import java.util.Arrays;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.util.HashMap;

public class API implements ItemLoader
{
    public static final String TRUST_REALM_SUFFIX = "!";
    public static final int MAX_PLAYER_LEVEL = 90;
    public static final int MIN_UPGRADE_ITEM_LEVEL = 458;
    public static final int MIN_UPGRADE_QUALITY = 3;
    final ItemLoader quickLoader;
    private final HttpCache hc;
    private final HashMap<String, IconStore> iconMap;
    static final int ITEM_AGE = 0;
    static final int CHAR_AGE = 60000;
    private final HashMap<Integer, ItemSet> itemSetMap;
    public static final ImageIcon BLANK_ICON;
    public static final ImageIcon NEED;
    private final HashMap<Integer, Item> itemMap;
    private final HashMap<String, ArrayList<Item>> itemGroupMap;
    public static final int[] TALENT_LEVELS;
    public static final int TALENT_15 = 0;
    public static final int TALENT_30 = 1;
    public static final int TALENT_45 = 2;
    public static final int TALENT_60 = 3;
    public static final int TALENT_75 = 4;
    public static final int TALENT_90 = 5;
    public static final int TALENT_NUM = 6;
    public static final int SET_BONUS_FAKE = 10000000;
    public static final int WOD_SET_BONUS_T17 = 10000001;
    public static final int WOD_SET_BONUS_PVP = 10000002;
    
    static String abbrName(final String s) {
        final String[] v = s.split("\\s+");
        final StringBuilder sb = new StringBuilder();
        for (final String x : v) {
            sb.append(x.charAt(0));
        }
        final int pos = s.indexOf(60);
        if (pos >= 0) {
            sb.append(s.substring(pos));
        }
        return sb.toString();
    }
    
    public API(final HttpCache hc) {
        this.quickLoader = new ItemLoader() {
            @Override
            public Item loadItem(final int itemId) {
                final Item item = API.this.getItem(itemId);
                if (item == null) {
                    throw new Err(String.format("Unable to load item (%d): not loaded", itemId));
                }
                return item;
            }
        };
        this.iconMap = new HashMap<String, IconStore>();
        this.itemSetMap = new HashMap<Integer, ItemSet>();
        this.itemMap = new HashMap<Integer, Item>();
        this.itemGroupMap = new HashMap<String, ArrayList<Item>>();
        this.hc = hc;
    }
    
    JSONObject blizz_json(final String name, final int maxAge, final String url) {
        Pair<String, Boolean> temp;
        try {
            temp = this.hc.getStr(url, name + ".json", maxAge);
        }
        catch (HttpCache.Err err) {
            throw new Err(err.getMessage());
        }
        JSONObject root;
        try {
            root = (JSONObject)JSONValue.parse(temp.car);
        }
        catch (RuntimeException err3) {
            throw new Err("Invalid JSON: " + url);
        }
        if (root == null) {
            throw new Err("Bad JSON: " + url);
        }
        final String status = root.get("status");
        if (status != null && status.equals("nok")) {
            final String err2 = root.get("reason");
            throw new Err("Blizzard API Error: " + err2);
        }
        return root;
    }
    
    public static String formatPrice(int cost) {
        if (cost == 0) {
            return "0g";
        }
        final StringBuilder sb = new StringBuilder();
        if (cost < 0) {
            sb.append('-');
            cost = -cost;
        }
        final int g = cost / 10000;
        if (g > 0) {
            sb.append(g);
            sb.append("g ");
        }
        final int s = cost / 100 % 100;
        if (s > 0) {
            sb.append(s);
            sb.append("s ");
        }
        final int c = cost % 100;
        if (c > 0) {
            sb.append(c);
            sb.append("c ");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
    
    public String properRealmSlug(final RegionT region, String realm) {
        realm = realm.trim();
        if (realm.isEmpty()) {
            return "";
        }
        if (realm.endsWith("!")) {
            return realm.substring(0, realm.length() - "!".length()).trim();
        }
        Realm[] realms;
        try {
            realms = this.getRealmList(region);
        }
        catch (RuntimeException err) {
            return realm;
        }
        if (realms.length == 0) {
            return realm;
        }
        for (final Realm x : realms) {
            if (x.slug.equalsIgnoreCase(realm) || x.name.equalsIgnoreCase(realm)) {
                return x.slug;
            }
        }
        for (final Realm x : realms) {
            x.index = Utils.editDist(realm, x.slug);
        }
        Arrays.sort(realms, Realm.CMP_INDEX);
        return realms[0].slug;
    }
    
    public Pair<ClassT, Realm>[] findChar(final String name, final RegionT region, final boolean force, final ClassT oneClass, final ClassT... moreClasses) {
        String html;
        try {
            html = this.hc.getStr(String.format("http://%s/wow/en/search?f=wowcharacter&sort=level&dir=d&page=1&q=%s", region.host, Utils.urlEncode(name)), "Search_$#$.html", force ? 1 : 86400000).car;
        }
        catch (RuntimeException err) {
            throw new Err("Unable to fetch search results for \"" + name + "\".\n\n" + err.getMessage());
        }
        Realm[] realms;
        try {
            realms = this.getRealmList(region);
        }
        catch (RuntimeException err2) {
            throw new Err("Unable to fetch realm list for " + region);
        }
        final HashSet<ClassT> classes = new HashSet<ClassT>();
        classes.add(oneClass);
        Collections.addAll(classes, moreClasses);
        final String prefix = "/character/";
        final String token = "/class_";
        final Pattern patt = Pattern.compile("\\>\\s*90\\s*\\<");
        final ArrayList<Pair<ClassT, Realm>> matches = new ArrayList<Pair<ClassT, Realm>>();
        int pos = 0;
        while (true) {
            int pos2 = html.indexOf(token, pos);
            if (pos2 == -1) {
                break;
            }
            pos2 += token.length();
            int pos3 = html.indexOf(46, pos2);
            if (pos3 == -1) {
                break;
            }
            pos = pos3;
            final String test = html.substring(pos2, pos3);
            ClassT cls;
            try {
                cls = ClassT.MAP.get(Integer.parseInt(test));
            }
            catch (NumberFormatException err3) {
                continue;
            }
            if (!classes.contains(cls)) {
                continue;
            }
            pos3 = html.indexOf("</tr>", pos2);
            if (pos3 == -1) {
                break;
            }
            pos = pos3;
            pos2 = html.lastIndexOf(prefix, pos2);
            if (pos2 == -1) {
                continue;
            }
            pos2 += prefix.length();
            final String sub = html.substring(pos2, pos3);
            if (!patt.matcher(sub).find()) {
                continue;
            }
            pos2 = sub.indexOf(47);
            if (pos2 == -1) {
                continue;
            }
            final String r = sub.substring(0, pos2);
            for (final Realm x : realms) {
                if (x.slug.equalsIgnoreCase(r)) {
                    matches.add(new Pair<ClassT, Realm>(cls, x));
                    break;
                }
            }
        }
        if (matches.isEmpty()) {
            throw new Err(String.format("Unable to find a level %d %s named \"%s\" in %s.", 90, Fmt.join(classes, "/"), name, region));
        }
        return matches.toArray(new Pair[matches.size()]);
    }
    
    public Realm[] getRealmList(final RegionT region) {
        final JSONObject root = this.blizz_json("Realms_" + region, 86400000, "http://" + region.host + "/api/wow/realm/status");
        try {
            final JSONArray realmList = root.get("realms");
            final Realm[] res = new Realm[realmList.size()];
            int index = 0;
            for (final Object x : realmList) {
                final JSONObject realmInfo = (JSONObject)x;
                final String name = realmInfo.get("name");
                final String slug = realmInfo.get("slug");
                final String group = realmInfo.get("battlegroup");
                res[index++] = new Realm(name, slug, group);
            }
            return res;
        }
        catch (RuntimeException err) {
            throw new Err("Unexpected parse error: " + err.getMessage());
        }
    }
    
    public Gem[] loadGems_toArray(final int... ids) {
        final TreeSet<Gem> map = new TreeSet<Gem>(Item.CMP_itemId);
        for (final int id : ids) {
            try {
                final Item item = this.loadItem(id);
                if (item instanceof Gem) {
                    map.add((Gem)item);
                }
            }
            catch (RuntimeException ex) {}
        }
        return map.toArray(new Gem[map.size()]);
    }
    
    public Item[] loadItems_toArray(final int... ids) {
        return this.loadItems(ids).values().toArray(new Item[0]);
    }
    
    public Map<Integer, Item> loadItems(final int... ids) {
        final HashMap<Integer, Item> map = new HashMap<Integer, Item>();
        for (final int id : ids) {
            try {
                final Item item = this.loadItem(id);
                map.put(id, item);
            }
            catch (RuntimeException err) {
                System.err.println(id + ":" + err);
            }
        }
        return map;
    }
    
    public ItemSet loadItemSet(final int id) {
        final ItemSet set = this.itemSetMap.get(id);
        if (set != null) {
            return set;
        }
        final JSONObject root = this.blizz_json("ItemSet_" + id, 0, "http://us.battle.net/api/wow/item/set/" + id);
        return this.loadItemSet_fromData(id, root);
    }
    
    private ItemSet loadItemSet_fromData(final int id, final JSONObject root) {
        final String name = root.get("name");
        final JSONArray array = root.get("setBonuses");
        final ArrayList<SetBonus> bonuses = new ArrayList<SetBonus>(array.size());
        int index = 0;
        for (final Object x : array) {
            final JSONObject bonusInfo = (JSONObject)x;
            final int thres = bonusInfo.get("threshold").intValue();
            final String desc = bonusInfo.get("description");
            bonuses.add(new SetBonus(index++, thres, desc.replace("\n\n", "\n")));
        }
        final JSONArray items = root.get("items");
        final QualityT quality = QualityT.ALL[JSONHelp.getInt(root, "quality", 4)];
        final ItemSet set = new ItemSet(id, name, quality, bonuses.toArray(new SetBonus[bonuses.size()]), items.size());
        this.itemSetMap.put(id, set);
        return set;
    }
    
    public IconStore getIconStore(final String name) {
        IconStore icon = this.iconMap.get(name);
        if (icon == null) {
            icon = new IconStore(name);
            this.iconMap.put(name, icon);
        }
        return icon;
    }
    
    public boolean markImageIcon(final IconStore store, final IconSize size) {
        final int i = size.ordinal();
        final ImageIcon icon = store.icons[i];
        if (icon == null) {
            store.icons[i] = API.NEED;
            return true;
        }
        return icon == API.NEED;
    }
    
    public void unmarkIconImage(final IconStore store) {
        for (final IconSize x : IconSize.values()) {
            this.getIconImage(store, x);
        }
    }
    
    public ImageIcon getIconImage(final String name, final IconSize size) {
        return this.getIconImage(this.getIconStore(name), size);
    }
    
    public ImageIcon getIconImage(final IconStore store, final IconSize size) {
        ImageIcon icon = store.icons[size.ordinal()];
        if (icon != null) {
            if (icon != API.NEED) {
                return icon;
            }
        }
        Image img;
        try {
            img = this.hc.getImage(String.format("http://wow.zamimg.com/images/wow/icons/%s/%s.jpg", size.name, store.name), String.format("%s_%d.jpg", store.name, size.size), 0);
        }
        catch (HttpCache.Err err) {
            img = null;
        }
        icon = ((img == null) ? API.BLANK_ICON : new ImageIcon(img));
        store.icons[size.ordinal()] = icon;
        return icon;
    }
    
    public void batchItems(final IntSet itemIds) {
        final IntSet buf = new IntSet();
        for (int i = 0; i < itemIds.count; ++i) {
            final int id = itemIds.member[i];
            if (id < 0) {
                buf.add(-id);
            }
        }
        final int chunk = 50;
        final File dir = new File(this.hc.cacheDir, "raffy.antistupid.com");
        int off = 0;
        while (off < buf.count) {
            final StringBuilder sb = new StringBuilder("http://us.battle.net/api/wow/batch.php?");
            sb.append(buf.member[off]);
            for (int end = Math.min(buf.count, off + chunk - 1); off < end; ++off) {
                sb.append(',');
                sb.append(buf.member[off]);
            }
            final String data = this.hc.getStr_silent(sb.toString(), "", -1, 1);
            if (data == null) {
                return;
            }
            JSONObject root;
            try {
                root = (JSONObject)JSONValue.parse(data);
            }
            catch (RuntimeException err) {
                System.err.println("Invalid JSON");
                return;
            }
            final JSONArray items = root.get("cached");
        }
    }
    
    public Item[] loadItem_bothFactions(final int id) {
        final Item item = this.loadItem(id);
        if (item.otherFaction_itemId == 0) {
            return new Item[] { item };
        }
        return new Item[] { item, this.loadItem(item.otherFaction_itemId) };
    }
    
    public Item getItem(final int id) {
        return this.itemMap.get(id);
    }
    
    @Override
    public Item loadItem(int id) {
        if (id < 0) {
            id = -id;
        }
        Item item = this.getItem(id);
        if (item != null) {
            return item;
        }
        try {
            final int trueItemId = Math.abs(id);
            final String idStr = "Item_" + trueItemId;
            final JSONObject root = this.blizz_json(idStr, 0, "http://raffy.antistupid.com/wow/items.php?id=" + trueItemId);
            final int majorId = root.get("itemClass").intValue();
            final int minorId = root.get("itemSubClass").intValue();
            final EquipT equipType = EquipT.MAP.req(root.get("inventoryType").intValue());
            final ItemT type = ItemT.fromId(majorId);
            switch (type) {
                case CONSUME: {
                    final Consume temp = new Consume();
                    temp.minorType = ConsumeT.MAP.req(minorId);
                    item = temp;
                    break;
                }
                case WEAPON: {
                    final Weapon temp2 = new Weapon();
                    temp2.minorType = WeaponT.MAP.req(minorId);
                    final Boolean eye = root.get("eye");
                    temp2.extraSocket = (eye != null && eye);
                    final JSONObject info = root.get("weaponInfo");
                    temp2.speed = info.get("weaponSpeed").doubleValue();
                    final JSONObject damageInfo = info.get("damage");
                    temp2.minDmg = damageInfo.get("exactMin").doubleValue();
                    temp2.maxDmg = damageInfo.get("exactMax").doubleValue();
                    item = temp2;
                    break;
                }
                case GEM: {
                    final Gem temp3 = new Gem();
                    final JSONObject info2 = root.get("gemInfo");
                    final JSONObject typeInfo = info2.get("type");
                    final GemT gemType = GemT.fromType(typeInfo.get("type"));
                    temp3.minorType = gemType;
                    temp3.minItemLevel = JSONHelp.getInt(info2, "minItemLevel", 0);
                    item = temp3;
                    break;
                }
                case ARMOR: {
                    final Armor temp4 = new Armor();
                    temp4.minorType = ArmorT.MAP.req(minorId);
                    temp4.baseArmor = root.get("baseArmor").intValue();
                    item = temp4;
                    break;
                }
                default: {
                    throw new Err("Unsupported item type: " + type);
                }
            }
            item.upgradable = root.get("upgradable");
            item.itemId = id;
            item.majorType = type;
            item.equipType = equipType;
            item.name = root.get("name");
            String nameDesc = root.get("nameDescription");
            if (nameDesc != null && nameDesc.isEmpty()) {
                nameDesc = null;
            }
            item.name0 = item.name;
            if (nameDesc != null && nameDesc.startsWith("Season ")) {
                final int pos = item.name.indexOf(32);
                if (pos >= 0) {
                    item.pvpSeason = true;
                    nameDesc = nameDesc + ": " + item.name.substring(0, pos);
                    item.name0 = item.name.substring(pos + 1);
                }
            }
            item.nameDesc = nameDesc;
            item.itemLevel = root.get("itemLevel").intValue();
            item.quality = QualityT.ALL[root.get("quality").intValue()];
            item.requiredProf = ProfT.MAP.get(root.get("requiredSkill").intValue());
            item.icon = this.getIconStore(root.get("icon"));
            item.sellPrice = root.get("sellPrice").intValue();
            final JSONObject uniqueInfo = root.get("uniqueInfo");
            if (uniqueInfo != null) {
                item.uniqueKey = uniqueInfo.get("name");
                item.uniqueCount = uniqueInfo.get("count").intValue();
            }
            final JSONObject itemSet = root.get("itemSet");
            if (itemSet != null) {
                final int setId = itemSet.get("id").intValue();
                ItemSet set = this.itemSetMap.get(setId);
                if (set == null) {
                    set = this.loadItemSet_fromData(setId, itemSet);
                }
                item.itemSet = set;
            }
            final JSONArray stats = root.get("bonusStats");
            if (stats != null) {
                for (final Object x : stats) {
                    final JSONObject statInfo = (JSONObject)x;
                    final StatT statType = StatT.MAP.req(statInfo.get("stat").intValue());
                    final int statValue = statInfo.get("amount").intValue();
                    item.stats.set(statType, statValue);
                }
            }
            final JSONObject socketInfo = root.get("socketInfo");
            if (socketInfo != null) {
                final JSONArray sockets = socketInfo.get("sockets");
                final int num = sockets.size();
                if (num > 0) {
                    final JSONObject bonusData = socketInfo.get("bonusData");
                    if (bonusData != null) {
                        final StatT stat = StatT.MAP.req(bonusData.get("stat").intValue());
                        final int value = bonusData.get("value").intValue();
                        item.socketBonus = StatValue.make(stat, value);
                    }
                    item.sockets = new GemT[num];
                    for (int i = 0; i < num; ++i) {
                        final JSONObject info3 = sockets.get(i);
                        final String color = info3.get("type");
                        item.sockets[i] = GemT.fromType(color);
                    }
                }
            }
            final JSONArray itemSpells = root.get("itemSpells");
            if (itemSpells != null) {
                final int num = itemSpells.size();
                if (num > 0) {
                    final int[] temp5 = new int[num];
                    for (int i = 0; i < num; ++i) {
                        final JSONObject spellInfo = itemSpells.get(i);
                        temp5[i] = spellInfo.get("spellId").intValue();
                    }
                    Arrays.sort(temp5);
                    item.itemSpells = temp5;
                }
            }
            final JSONArray spellStats = root.get("spellStats");
            if (spellStats != null) {
                final ArrayList<SpellAlloc> temp6 = new ArrayList<SpellAlloc>();
                for (final Object x2 : spellStats) {
                    final JSONObject spellInfo2 = (JSONObject)x2;
                    final JSONArray statIds = spellInfo2.get("stats");
                    final double scale = JSONHelp.getDouble(spellInfo2, "scale", 0.0);
                    if (scale != 0.0) {
                        for (final Object y : statIds) {
                            temp6.add(new SpellAlloc(StatT.MAP.req(((Number)y).intValue()), scale));
                        }
                    }
                }
                if (!temp6.isEmpty()) {
                    item.spellStats = temp6.toArray(new SpellAlloc[temp6.size()]);
                }
            }
            final JSONArray allowableClasses = root.get("allowableClasses");
            if (allowableClasses != null) {
                int bits = 0;
                for (final Object x3 : allowableClasses) {
                    bits |= ClassT.MAP.req(((Number)x3).intValue()).bit;
                }
                item.allowableClasses = bits;
            }
            final JSONArray allowableRaces = root.get("allowableRaces");
            if (allowableRaces != null) {
                int bits2 = 0;
                for (final Object x4 : allowableRaces) {
                    bits2 |= RaceT.MAP.req(((Number)x4).intValue()).bit;
                }
                item.allowableRaces = bits2;
            }
            item.otherFaction_itemId = JSONHelp.getInt(root, "factionSwapId", 0);
            final JSONArray statData = root.get("statData");
            if (statData != null) {
                item.allocDist = this.parseStatData(statData);
            }
            final JSONArray suffixData = root.get("suffixes");
            if (suffixData != null) {
                final Suffix[] v = new Suffix[20];
                int num2 = 0;
                for (final Object x5 : suffixData) {
                    final JSONObject data = (JSONObject)x5;
                    final int suffixId = data.get("id").intValue();
                    final String name = data.get("name");
                    final StatAlloc[] allocDist = this.parseStatData(data.get("statData"));
                    v[num2++] = new Suffix(suffixId, name, null, null, allocDist);
                }
                if (num2 > 0) {
                    item.suffixes = Arrays.copyOf(v, num2);
                }
            }
            this.itemMap.put(item.itemId, item);
            switch (type) {
                case WEAPON:
                case ARMOR: {
                    final String key = item.name0 + equipType.name;
                    ArrayList<Item> list = this.itemGroupMap.get(key);
                    if (list == null) {
                        list = new ArrayList<Item>();
                        this.itemGroupMap.put(key, list);
                    }
                    (item.groupSet = list).add(item);
                    Collections.sort(list, Item.CMP_itemLevel);
                    break;
                }
            }
            return item;
        }
        catch (RuntimeException err) {
            err.printStackTrace();
            throw new Err(String.format("Unable to load item (%d): %s", id, (err instanceof Err) ? err.getMessage() : err.toString()));
        }
    }
    
    private StatAlloc[] parseStatData(final JSONArray array) {
        final StatAlloc[] v = new StatAlloc[10];
        int num = 0;
        for (final Object x : array) {
            final JSONObject data = (JSONObject)x;
            final StatT stat = StatT.MAP.get(data.get("type").intValue());
            if (stat == null) {
                continue;
            }
            final int alloc = data.get("alloc").intValue();
            final Number cost = data.get("cost");
            final double mod = (cost == null) ? 0.0 : cost.doubleValue();
            v[num++] = new StatAlloc(stat, alloc, mod);
        }
        return (StatAlloc[])((num > 0) ? ((StatAlloc[])Arrays.copyOf(v, num)) : null);
    }
    
    public TinkerT loadTinker(final int id) {
        return TinkerT.MAP.get(id);
    }
    
    public EnchantT loadEnchant(final int id) {
        if (id == 0) {
            return null;
        }
        final EnchantT ench = EnchantT.MAP.get(id);
        if (ench == null) {
            System.err.println("Unknown enchant effect: " + id);
        }
        return ench;
    }
    
    private void loadSlot(final Profile.Slot slot, final JSONObject slotInfo) {
        slot.clear();
        if (slotInfo == null) {
            return;
        }
        slot.setItem(this.loadItem(slotInfo.get("id").intValue()));
        final JSONObject paramMap = slotInfo.get("tooltipParams");
        if (paramMap != null) {
            if (slot.canSuffix()) {
                final Number suffixId = paramMap.get("suffix");
                if (suffixId != null) {
                    slot.setSuffixId(-suffixId.intValue());
                }
            }
            final Number reforgeId = paramMap.get("reforge");
            if (reforgeId != null) {
                final ReforgePair reforge = ReforgePair.decode(reforgeId.intValue());
                slot.setReforge(reforge);
            }
            final Number tinkerId = paramMap.get("tinker");
            if (tinkerId != null) {
                slot.tinker = this.loadTinker(tinkerId.intValue());
            }
            final Number enchantId = paramMap.get("enchant");
            if (enchantId != null) {
                slot.enchant = this.loadEnchant(enchantId.intValue());
            }
            slot.extraSocket = paramMap.containsKey("extraSocket");
            for (int i = 0; i < GemT.KEYS.length; ++i) {
                final Number gemId = paramMap.get(GemT.KEYS[i]);
                if (gemId != null) {
                    slot.setGemAt(i, (Gem)this.loadItem(gemId.intValue()));
                }
            }
            final JSONObject upgrade = paramMap.get("upgrade");
            if (upgrade != null) {
                final Number delta = upgrade.get("itemLevelIncrement");
                if (delta != null) {
                    slot.setItemLevelDelta(delta.intValue());
                }
            }
        }
    }
    
    static String slugifyRealm(final String realm) {
        return realm.trim().toLowerCase().replaceAll("\\s+", "-");
    }
    
    public Profile loadChar(final String name, final String realm, final RegionT region) {
        return this.loadChar(name, realm, region, null, 0, -1, false);
    }
    
    public Profile loadChar(String name, String realm, final RegionT region, final ClassT requireClass, final int requireLevel, final int specIndex, final boolean force) {
        name = name.trim();
        realm = slugifyRealm(realm);
        JSONObject root;
        try {
            root = this.blizz_json("Char_$#$", force ? 1 : 60000, String.format("http://%s/api/wow/character/%s/%s?fields=items,talents,professions&locale=en", region.host, Utils.urlEncode(realm), Utils.urlEncode(name)));
        }
        catch (RuntimeException err) {
            throw new Err(String.format("Unable to get profile for %s@%s/%s:\n\n%s", name, realm, region, err.getMessage()));
        }
        Label_0203: {
            if (region.asia) {
                try {
                    final JSONObject localized = this.blizz_json("LocalChar_$#$", 86400000, String.format("http://%s/api/wow/character/%s/%s", region.host, Utils.urlEncode(realm), Utils.urlEncode(name)));
                    break Label_0203;
                }
                catch (RuntimeException err2) {
                    throw new Err(String.format("Unable to get localized profile header for %s@%s/%s:\n\n%s", name, realm, region, err2.getMessage()));
                }
            }
            final JSONObject localized = root;
            try {
                if (localized.size() < 5) {
                    throw new RuntimeException("Bad armory data: " + localized);
                }
                final Profile p = new Profile();
                p.charName = JSONHelp.reqStr(localized, "name");
                p.realmName = JSONHelp.reqStr(localized, "realm");
                p.realmSlug = realm;
                p.region = region;
                final String identity = p.charName + "@" + p.realmName + "/" + p.region;
                final int classId = JSONHelp.reqInt(root, "class");
                final ClassT classType = ClassT.MAP.get(classId);
                if (classType == null) {
                    throw new RuntimeException("Unknown class type: " + classId);
                }
                if (requireClass != null && requireClass != classType) {
                    throw new Err(String.format("%s (%s) is not a %s.", identity, classType.name, requireClass.name));
                }
                p.charLevel = JSONHelp.reqInt(root, "level");
                if (p.charLevel < requireLevel) {
                    throw new Err(String.format("%s (%d) is not level %d.", identity, p.charLevel, requireLevel));
                }
                final int raceId = JSONHelp.reqInt(root, "race");
                p.race = RaceT.MAP.get(raceId);
                if (p.race == null) {
                    throw new RuntimeException("Unknown race type: " + raceId);
                }
                p.spec = null;
                p.talents = null;
                p.glyphs = null;
                final JSONArray talents = root.get("talents");
                final SpecT[] specs = SpecT.classMap.get(classType);
                if (specIndex >= 0 && specIndex < specs.length) {
                    p.spec = specs[specIndex];
                    for (final Object x : talents) {
                        final JSONObject talentsInfo = (JSONObject)x;
                        final int index = SpecT.specIndex(talentsInfo.get("calcSpec"));
                        if (index == specIndex) {
                            p.spec = specs[index];
                            p.talents = talentsInfo.get("calcTalent");
                            p.glyphs = talentsInfo.get("calcGlyph");
                            if (talentsInfo.containsKey("selected")) {
                                break;
                            }
                            continue;
                        }
                    }
                }
                else {
                    for (final Object x : talents) {
                        final JSONObject talentsInfo = (JSONObject)x;
                        if (talentsInfo.containsKey("selected")) {
                            p.talents = talentsInfo.get("calcTalent");
                            p.glyphs = talentsInfo.get("calcGlyph");
                            final int index = SpecT.specIndex(talentsInfo.get("calcSpec"));
                            if (index >= 0 && index < specs.length) {
                                p.spec = specs[index];
                                break;
                            }
                            break;
                        }
                    }
                }
                final JSONObject profs = root.get("professions");
                final JSONArray primary = profs.get("primary");
                p.profs = 0;
                for (final Object x2 : primary) {
                    final JSONObject info = (JSONObject)x2;
                    final ProfT prof = ProfT.MAP.get(info.get("id").intValue());
                    final int skill = JSONHelp.getInt(info, "rank", 0);
                    if (prof != null) {
                        final Profile profile = p;
                        profile.profs |= prof.bit;
                        p.skillMap.put(prof, skill);
                    }
                }
                final JSONObject items = root.get("items");
                this.loadSlot(p.HEAD, items.get("head"));
                this.loadSlot(p.BACK, items.get("back"));
                this.loadSlot(p.NECK, items.get("neck"));
                this.loadSlot(p.SHOULDER, items.get("shoulder"));
                this.loadSlot(p.CHEST, items.get("chest"));
                this.loadSlot(p.WRIST, items.get("wrist"));
                this.loadSlot(p.HANDS, items.get("hands"));
                this.loadSlot(p.WAIST, items.get("waist"));
                this.loadSlot(p.LEGS, items.get("legs"));
                this.loadSlot(p.FEET, items.get("feet"));
                this.loadSlot(p.F1, items.get("finger1"));
                this.loadSlot(p.F2, items.get("finger2"));
                this.loadSlot(p.T1, items.get("trinket1"));
                this.loadSlot(p.T2, items.get("trinket2"));
                this.loadSlot(p.MH, items.get("mainHand"));
                this.loadSlot(p.OH, items.get("offHand"));
                return p;
            }
            catch (Err err3) {
                throw err3;
            }
            catch (RuntimeException err2) {
                err2.printStackTrace();
                throw new Err(String.format("An unexpected error occured importing %s@%s/%s:\n\n%s", name, realm, region, err2.getMessage()));
            }
        }
    }
    
    public static void showArmory(final String name, final String realm, final RegionT region) {
        final String url = String.format("http://%s/wow/en/character/%s/%s/advanced", region.host, Utils.urlEncode(slugifyRealm(realm)), Utils.urlEncode(name));
        if (!Utils.openURL(url)) {
            throw new Err("Unable to open Armory URL: " + url);
        }
    }
    
    public static boolean validTalents(final String x) {
        return x != null && x.matches("^[0123]{0,6}$");
    }
    
    public static byte[] parseTalents(final String x) {
        final byte[] v = new byte[6];
        final int len = (x != null) ? Math.min(6, x.length()) : 0;
        for (int i = 0; i < len; ++i) {
            final int c = x.charAt(i) - '0';
            v[i] = (byte)((c >= 0 && c <= 2) ? c : -1);
        }
        for (int i = len; i < 6; ++i) {
            v[i] = -1;
        }
        return v;
    }
    
    public static String getTalentStr(final byte[] v, final int offset) {
        final char[] temp = new char[6];
        for (int i = 0; i < 6; ++i) {
            final int x = v[i];
            temp[i] = ((x < 0) ? '.' : ((char)(48 + x + offset)));
        }
        return new String(temp);
    }
    
    static {
        BLANK_ICON = new ImageIcon();
        NEED = new ImageIcon();
        TALENT_LEVELS = new int[] { 15, 30, 45, 60, 75, 90 };
    }
    
    public static class Err extends RuntimeException
    {
        private Err(final String message) {
            super(message);
        }
    }
    
    public enum IconSize
    {
        SMALL(18, "small"), 
        MEDIUM(36, "medium"), 
        LARGE(56, "large");
        
        public final int size;
        public final String name;
        
        private IconSize(final int size, final String suffix) {
            this.size = size;
            this.name = suffix;
        }
    }
}
