// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Arrays;
import java.util.Random;

public abstract class IntDist
{
    public abstract int generate(final Random p0);
    
    public abstract String toTime();
    
    public void reset() {
    }
    
    static IntDist nullIfZero(final IntDist x) {
        return (x instanceof Const && ((Const)x).c == 0) ? null : x;
    }
    
    static class Const extends IntDist
    {
        final int c;
        
        Const(final int c) {
            this.c = c;
        }
        
        @Override
        public int generate(final Random rng) {
            return this.c;
        }
        
        @Override
        public String toString() {
            return this.getClass().getSimpleName() + "(" + this.c + ")";
        }
        
        @Override
        public String toTime() {
            return Fmt.msDur(this.c);
        }
    }
    
    static class Uniform extends IntDist
    {
        final int start;
        final int range;
        
        Uniform(final int lo, final int hi) {
            this.start = lo;
            this.range = hi - lo;
        }
        
        @Override
        public int generate(final Random rng) {
            return rng.nextInt(this.range) + this.start;
        }
        
        @Override
        public String toString() {
            return this.getClass().getSimpleName() + "(" + this.start + "," + (this.start + this.range) + ")";
        }
        
        @Override
        public String toTime() {
            return Fmt.msDur(this.start) + "-" + Fmt.msDur(this.start + this.range);
        }
    }
    
    static class Finite extends IntDist
    {
        final int[] values;
        int index;
        
        Finite(final int[] values) {
            this.index = 0;
            this.values = values;
        }
        
        @Override
        public int generate(final Random rng) {
            return (this.index < this.values.length) ? this.values[this.index++] : -1;
        }
        
        @Override
        public void reset() {
            this.index = 0;
        }
        
        @Override
        public String toString() {
            return this.getClass().getSimpleName() + Arrays.toString(this.values);
        }
        
        @Override
        public String toTime() {
            final StringBuilder sb = new StringBuilder();
            for (final int x : this.values) {
                if (sb.length() > 0) {
                    sb.append(';');
                }
                sb.append(Fmt.msDur(x));
            }
            return sb.toString();
        }
    }
}
