// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Comparator;

public class RecentArmory
{
    public final String name;
    public final String realm;
    public final RegionT region;
    long time;
    static final Comparator<RecentArmory> CMP_TIME;
    
    public RecentArmory(final String name, final String realm, final RegionT region, final long time) {
        this.name = name;
        this.realm = realm;
        this.region = region;
        this.time = time;
    }
    
    @Override
    public String toString() {
        return String.format("%s - %s/%s", this.name, this.realm, this.region);
    }
    
    static {
        CMP_TIME = new Comparator<RecentArmory>() {
            @Override
            public int compare(final RecentArmory a, final RecentArmory b) {
                return Double.compare((double)b.time, (double)a.time);
            }
        };
    }
}
