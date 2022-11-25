// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class Profs
{
    ProfT p1;
    ProfT p2;
    
    public boolean has(final ProfT x) {
        return this.p1 == x || this.p2 == x;
    }
}
