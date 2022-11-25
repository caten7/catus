// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public enum RegionT
{
    US("us.battle.net", false), 
    EU("eu.battle.net", false), 
    TW("tw.battle.net", true), 
    KR("kr.battle.net", true), 
    CN("www.battlenet.com.cn", true);
    
    public final String host;
    public final boolean asia;
    
    private RegionT(final String host, final boolean asia) {
        this.host = host;
        this.asia = asia;
    }
}
