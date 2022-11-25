// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class Weapon extends Item
{
    boolean extraSocket;
    double minDmg;
    double maxDmg;
    double speed;
    
    double dpsToDmgMod() {
        return (this.stats.get(StatT.SP) > 0) ? (this.speed / 2.0) : this.speed;
    }
    
    WeaponT getType() {
        return (WeaponT)this.minorType;
    }
    
    boolean isTwoHander() {
        return WeaponT.isTwoHander(this.getType());
    }
}
