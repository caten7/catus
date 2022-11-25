// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import javax.swing.SwingUtilities;
import javax.swing.JProgressBar;

public abstract class ProgBarProxy
{
    final int NUM = 10000;
    public static final ProgBarProxy NONE;
    
    public abstract void indeterminate();
    
    public abstract void determinate(final int p0, final int p1);
    
    public void determinate() {
        this.determinate(0, 10000);
    }
    
    public abstract void value(final int p0);
    
    public void fraction(final double perc) {
        this.value((int)(perc * 10000.0));
    }
    
    static {
        NONE = new ProgBarProxy() {
            @Override
            public void indeterminate() {
            }
            
            @Override
            public void determinate(final int min, final int max) {
            }
            
            @Override
            public void value(final int value) {
            }
        };
    }
    
    public static class Real extends ProgBarProxy
    {
        final JProgressBar pb;
        
        Real(final JProgressBar pb) {
            this.pb = pb;
        }
        
        @Override
        public void indeterminate() {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Real.this.pb.setIndeterminate(true);
                }
            });
        }
        
        @Override
        public void determinate(final int min, final int max) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Real.this.pb.setIndeterminate(false);
                    Real.this.pb.setMinimum(min);
                    Real.this.pb.setValue(min);
                    Real.this.pb.setMaximum(max);
                }
            });
        }
        
        @Override
        public void value(final int value) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Real.this.pb.setValue(value);
                }
            });
        }
    }
}
