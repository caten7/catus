// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public interface Statusable
{
    public static final Statusable NULL = new Statusable() {
        @Override
        public void updateStatus(final String status) {
        }
    };
    public static final Statusable OUT = new Statusable() {
        @Override
        public void updateStatus(final String status) {
            System.out.println(status);
        }
    };
    
    void updateStatus(final String p0);
}
