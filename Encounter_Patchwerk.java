// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class Encounter_Patchwerk extends Encounter
{
    public static final String NAME = "Patchwerk";
    int levelDelta;
    int duration;
    int variance;
    long health;
    boolean front;
    boolean canBlock;
    boolean canParry;
    boolean canDodge;
    int customArmor;
    double earlyDeathPerc;
    
    public Encounter_Patchwerk() {
        super("Patchwerk");
    }
    
    @Override
    public String getDesc() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Standard single-target encounter with (+");
        sb.append(this.levelDelta);
        sb.append(") level boss");
        if (this.health > 0L) {
            sb.append(" with ");
            sb.append(Fmt.bigNum((double)this.health));
            sb.append(" health");
        }
        sb.append(" who dies when their health reaches ");
        sb.append(String.format("%.1f%%", this.earlyDeathPerc));
        if (this.health == 0L) {
            sb.append(" after approximately ");
            sb.append(Fmt.msDur(this.duration));
            sb.append(" of combat");
        }
        sb.append('.');
        if (this.front) {
            sb.append("  The boss is attacked from the front.");
        }
        if (this.canBlock) {
            sb.append("  The boss can block.");
        }
        if (this.canParry) {
            sb.append("  The boss can parry.");
        }
        if (this.canDodge) {
            sb.append("  The boss can dodge.");
        }
        if (this.customArmor >= 0) {
            sb.append("  The boss has a custom armor value of ");
            sb.append(this.customArmor);
            sb.append('.');
        }
        return sb.toString();
    }
}
