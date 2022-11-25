// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class ConsumeT extends BlizzT
{
    public static final ConsumeT POTION;
    public static final ConsumeT ELIXIR;
    public static final ConsumeT FLASK;
    public static final ConsumeT SCROLL;
    public static final ConsumeT FOOD;
    public static final ConsumeT ENCHANT;
    public static final ConsumeT BANDAGE;
    public static final ConsumeT[] ALL;
    public static final TypeMap<ConsumeT> MAP;
    
    private ConsumeT(final int id, final String name) {
        super(id, name);
    }
    
    static {
        ALL = new ConsumeT[] { POTION = new ConsumeT(1, "Potion"), ELIXIR = new ConsumeT(2, "Exilir"), FLASK = new ConsumeT(3, "Flask"), SCROLL = new ConsumeT(4, "Scroll"), FOOD = new ConsumeT(5, "Food"), ENCHANT = new ConsumeT(6, "Enchant"), BANDAGE = new ConsumeT(7, "Bandage") };
        MAP = BlizzT.makeMap(ConsumeT.ALL);
    }
}
