// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public enum MetaEffect
{
    INCREASED_CRIT_EFFECT("+3% Critical Effect"), 
    INCREASED_MAX_MANA("+2% Max Mana"), 
    INCREASED_ARMOR("+2% Armor"), 
    INCREASED_BLOCK_VALUE("+1% Shield Block"), 
    REDUCED_SPELL_DAMAGE("-2% Spell Damage"), 
    REDUCED_SPELL_REFLECT("+1% Spell Reflect"), 
    REDUCED_STUN_DURATION("-10% Stun"), 
    REDUCED_ROOT_DURATION("-10% Root"), 
    REDUCED_FEAR_DURATION("-10% Fear"), 
    REDUCED_SILENCE_DURATION("-10% Silence"), 
    CAPACITANCE("Capacitance"), 
    INDOMITABLE("Indomitable"), 
    COURAGEOUS("Courageous");
    
    public final String desc;
    
    private MetaEffect(final String desc) {
        this.desc = desc;
    }
}
