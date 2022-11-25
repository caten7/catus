// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TinkerT extends BlizzT
{
    public static final TinkerT FRAG_BELT;
    public static final TinkerT SYNAPSE_SPRINGS;
    public static final TinkerT GLIDER;
    public static final TypeMap<TinkerT> MAP;
    public static final Map<EquipT, TinkerT[]> EQUIP_MAP;
    public final TypeSet<EquipT> set;
    public final int profSpellId;
    public final String effect;
    
    public static TinkerT find(final EquipT type, final String name) {
        for (final TinkerT x : TinkerT.MAP.by_id) {
            if (x.set.contains(type) && x.name.contains(name)) {
                return x;
            }
        }
        return null;
    }
    
    private TinkerT(final int id, final String name, final int spellId, final String effect, final EquipT... equips) {
        super(id, name);
        this.effect = effect;
        this.profSpellId = spellId;
        this.set = BlizzT.makeSet(equips);
    }
    
    @Override
    public String toString() {
        return this.name + ": " + this.effect;
    }
    
    static {
        MAP = BlizzT.makeMap(FRAG_BELT = new TinkerT(3601, "Frag Belt", 54793, "~875 Fire Damage (3yd) on Use", new EquipT[] { EquipT.WAIST }), SYNAPSE_SPRINGS = new TinkerT(4898, "Synapse Springs", 82175, "+1920 Agility for 10sec on Use", new EquipT[] { EquipT.HANDS }), new TinkerT(3605, "Flexweave Underlay", 55002, "Parachute for 30sec on Use", new EquipT[] { EquipT.CLOAK }), new TinkerT(3599, "EMP", 54736, "Stun all nearby Mechanicals for 3sec on Use", new EquipT[] { EquipT.WAIST }), GLIDER = new TinkerT(4897, "Goblin Glider", 126392, "Glide for 30sec on Use", new EquipT[] { EquipT.CLOAK }), new TinkerT(4223, "Nitro Boosts", 55016, "+150% run speed for 5sec on Use", new EquipT[] { EquipT.WAIST }), new TinkerT(5000, "Watergliding Jets", 109099, "+150% swim speed for 12sec on Use.", new EquipT[] { EquipT.WAIST }));
        final HashMap<EquipT, TinkerT[]> map = new HashMap<EquipT, TinkerT[]>();
        for (final EquipT e : EquipT.ALL) {
            final ArrayList<TinkerT> list = new ArrayList<TinkerT>();
            for (final TinkerT x : TinkerT.MAP.by_id) {
                if (x.set.contains(e)) {
                    list.add(x);
                }
            }
            map.put(e, list.toArray(new TinkerT[list.size()]));
        }
        EQUIP_MAP = Collections.unmodifiableMap((Map<? extends EquipT, ? extends TinkerT[]>)map);
    }
}
