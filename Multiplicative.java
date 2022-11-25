// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Arrays;

public class Multiplicative
{
    double identity;
    int count;
    final int[] keys;
    final double[] vals;
    int unique;
    public double product;
    
    public Multiplicative(final int cap) {
        this.identity = 1.0;
        this.product = this.identity;
        this.keys = new int[cap];
        this.vals = new double[cap];
    }
    
    public void clear() {
        this.count = 0;
        this.product = this.identity;
    }
    
    public void clearAndSetIdentity(final double x) {
        this.count = 0;
        this.identity = x;
        this.product = x;
    }
    
    public void setIdentity(final double x) {
        this.identity = x;
        this.update();
    }
    
    public void add(final int key, final double val) {
        int idx = Arrays.binarySearch(this.keys, 0, this.count, key);
        if (idx < 0) {
            idx ^= -1;
            for (int i = this.count; i > idx; --i) {
                this.keys[i] = this.keys[i - 1];
                this.vals[i] = this.vals[i - 1];
            }
            ++this.count;
        }
        this.keys[idx] = key;
        this.vals[idx] = val;
        this.update();
    }
    
    public void inc(final int key, final double val) {
        this.add(key, this.get(key, 1.0) + val);
    }
    
    public double get(final int key, final double val0) {
        final int i = Arrays.binarySearch(this.keys, 0, this.count, key);
        return (i >= 0) ? this.vals[i] : val0;
    }
    
    public boolean has(final int key) {
        return Arrays.binarySearch(this.keys, 0, this.count, key) >= 0;
    }
    
    public void dump() {
        System.out.println("Identity: " + this.identity);
        for (int i = 0; i < this.count; ++i) {
            System.out.println(String.format("%08d %.2f", this.keys[i], this.vals[i]));
        }
        System.out.println("Product: " + this.product);
    }
    
    void update() {
        double temp = this.identity;
        for (int i = 0; i < this.count; ++i) {
            temp *= this.vals[i];
        }
        this.product = temp;
    }
    
    public void remove(final int key) {
        final int idx = Arrays.binarySearch(this.keys, 0, this.count, key);
        if (idx < 0) {
            return;
        }
        final int count = this.count - 1;
        this.count = count;
        final int end = count;
        if (idx < end) {
            for (int i = idx; i < end; ++i) {
                this.keys[i] = this.keys[i + 1];
                this.vals[i] = this.vals[i + 1];
            }
        }
        this.update();
    }
}
