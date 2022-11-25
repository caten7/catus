// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Arrays;
import java.util.TreeSet;
import java.util.Comparator;

public class Pair<A, B>
{
    public final A car;
    public final B cdr;
    
    public Pair(final A car, final B cdr) {
        this.car = car;
        this.cdr = cdr;
    }
    
    @Override
    public String toString() {
        return this.car + "+" + this.cdr;
    }
    
    public static <A, B> Map<A, B> makeMap(final Comparator<? super A> cmpOnlyA, final Comparator<? super B> cmpOnlyB, final Pair<A, B>... pairs) {
        final Comparator<Pair<A, B>> cmpA = new Comparator<Pair<A, B>>() {
            @Override
            public int compare(final Pair<A, B> a, final Pair<A, B> b) {
                return cmpOnlyA.compare(a.car, b.car);
            }
        };
        final TreeSet<Pair<A, B>> setA = new TreeSet<Pair<A, B>>(cmpA);
        for (final Pair<A, B> x : pairs) {
            if (!setA.add(x)) {
                throw new IllegalArgumentException("Duplicate pair (A): " + x);
            }
        }
        final Comparator<Pair<A, B>> cmpB = new Comparator<Pair<A, B>>() {
            @Override
            public int compare(final Pair<A, B> a, final Pair<A, B> b) {
                return cmpOnlyB.compare(a.cdr, b.cdr);
            }
        };
        final TreeSet<Pair<A, B>> setB = new TreeSet<Pair<A, B>>(cmpB);
        for (final Pair<A, B> x2 : pairs) {
            if (!setB.add(x2)) {
                throw new IllegalArgumentException("Duplicate pair (B): " + x2);
            }
        }
        final Pair<A, B>[] byA = Arrays.copyOf(pairs, pairs.length);
        final Pair<A, B>[] byB = Arrays.copyOf(pairs, pairs.length);
        Arrays.sort(byA, cmpA);
        Arrays.sort(byB, cmpB);
        return new Map<A, B>((Pair[])byA, (Pair[])byB, (Comparator)cmpOnlyA, (Comparator)cmpOnlyB);
    }
    
    public static class Same<T> extends Pair<T, T>
    {
        public Same(final T car, final T cdr) {
            super(car, cdr);
        }
    }
    
    public static class Map<A, B>
    {
        final Pair<A, B>[] byA;
        final Pair<A, B>[] byB;
        final Comparator<A> cmpA;
        final Comparator<B> cmpB;
        
        private Map(final Pair<A, B>[] byA, final Pair<A, B>[] byB, final Comparator<A> cmpA, final Comparator<B> cmpB) {
            this.byA = byA;
            this.byB = byB;
            this.cmpA = cmpA;
            this.cmpB = cmpB;
        }
        
        public A getA(final B key, final A def) {
            int a = 0;
            int b = this.byB.length;
            while (a < b) {
                final int m = b + a >>> 1;
                final int c = this.cmpB.compare(this.byB[m].cdr, key);
                if (c < 0) {
                    a = m + 1;
                }
                else {
                    if (c <= 0) {
                        return this.byB[m].car;
                    }
                    b = m;
                }
            }
            return def;
        }
        
        public B getB(final A key, final B def) {
            int a = 0;
            int b = this.byA.length;
            while (a < b) {
                final int m = b + a >>> 1;
                final int c = this.cmpA.compare(this.byA[m].car, key);
                if (c < 0) {
                    a = m + 1;
                }
                else {
                    if (c <= 0) {
                        return this.byA[m].cdr;
                    }
                    b = m;
                }
            }
            return def;
        }
    }
}
