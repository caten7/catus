// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class Encounter_Cleave extends Encounter
{
    public static final String NAME = "Cleave";
    boolean fixed;
    int duration;
    int levelDelta;
    int adds;
    int minCleaveSize;
    boolean at_start;
    IntDist freq;
    int health;
    double frontProb;
    IntDist lifetime;
    
    public Encounter_Cleave() {
        super("Cleave");
    }
    
    @Override
    public String getDesc() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AoE encounter with a single boss (+3), attacking from behind.  Cleaving begins when there are ");
        sb.append(this.minCleaveSize);
        sb.append(" or more targets.  Combat ends ");
        if (this.fixed) {
            sb.append("after ");
            sb.append(Fmt.msDur(this.duration));
            sb.append(".");
        }
        else {
            sb.append("when the boss is killed.");
        }
        if (this.adds > 0) {
            sb.append("  ");
            sb.append(this.adds);
            sb.append(" adds (+");
            sb.append(this.levelDelta);
            sb.append(") are spawned");
            if (this.health > 0) {
                sb.append(" with ");
                sb.append(Fmt.bigNum(this.health));
                sb.append(" health");
            }
            if (this.at_start) {
                sb.append(" at the start");
            }
            if (this.freq != null) {
                if (this.at_start) {
                    sb.append(" and");
                }
                sb.append(" every ");
                sb.append(this.freq.toTime());
            }
            sb.append(".");
            if (this.lifetime != null) {
                sb.append("  They despawn after ");
                sb.append(this.lifetime.toTime());
                sb.append('.');
            }
        }
        return sb.toString();
    }
}
