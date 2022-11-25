// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class DamageWindow
{
    final int[] clock;
    final int[] value;
    final int interval;
    int start;
    int count;
    int findPos;
    int findEnd;
    
    DamageWindow(final int len, final int interval) {
        this.clock = new int[len];
        this.value = new int[len];
        this.interval = interval;
    }
    
    int idx(final int x) {
        return (this.start + x) % this.clock.length;
    }
    
    public void add(final int c, final int v) {
        int idx;
        if (this.count < this.clock.length) {
            idx = this.count++;
        }
        else {
            if (this.clock[this.start] + this.interval > c) {
                throw new RuntimeException(String.format("Window overflow: %d + %d > %d", this.clock[this.start], this.interval, c));
            }
            idx = this.start;
            this.start = this.idx(1);
        }
        this.clock[idx] = c;
        this.value[idx] = v;
    }
    
    public void dump() {
        this.findPos = 0;
        this.findEnd = this.count;
        this.dump0();
    }
    
    public void dumpTime(final int now, final int ago) {
        if (this.selectTime(now - ago, now) > 0) {
            this.dump0();
        }
    }
    
    void dump0() {
        for (int i = this.findPos; i < this.findEnd; ++i) {
            final int idx = this.idx(i);
            System.out.println(String.format("%05d %05d %08d %8d", i, idx, this.clock[idx], this.value[idx]));
        }
    }
    
    public void clear() {
        this.start = 0;
        this.count = 0;
    }
    
    public boolean valid(final int t) {
        return this.count > 0 && this.clock[this.idx(0)] < t;
    }
    
    public int minTime() {
        return (this.count > 0) ? this.clock[this.idx(0)] : -1;
    }
    
    public double mean(final int t, int last) {
        final int num = this.selectTime(t, last);
        if (num < 0) {
            return Double.NaN;
        }
        if (num == 0) {
            return 0.0;
        }
        double sum = 0.0;
        for (int i = this.findPos; i < this.findEnd; ++i) {
            sum += this.value[this.idx(i)];
        }
        if (this.findPos == 0) {
            last = t + 1 - this.clock[this.findPos];
        }
        return sum / last;
    }
    
    int selectTime(final int t, final int last) {
        if (last < 1 && last > this.interval) {
            return -1;
        }
        final int t2 = t - last;
        for (int i = 0; i < this.count; ++i) {
            final int idx0 = this.idx(i);
            if (this.clock[idx0] > t2) {
                this.findPos = i;
                for (int j = this.count - 1; j > i; --j) {
                    final int idx2 = this.idx(j);
                    if (this.clock[idx2] <= t) {
                        this.findEnd = j + 1;
                        return this.findEnd - i;
                    }
                }
                this.findEnd = i + 1;
                return 1;
            }
        }
        return 0;
    }
}
