// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.HashMap;
import java.util.HashSet;

public class ReforgeAlgo
{
    static final int MIN = 0;
    static final int MAX = 999999;
    final HashSet<StatT> dontSet;
    final HashMap<StatT, Span> constMap;
    final HashMap<StatT, Double> weightMap;
    
    public ReforgeAlgo() {
        this.dontSet = new HashSet<StatT>();
        this.constMap = new HashMap<StatT, Span>();
        this.weightMap = new HashMap<StatT, Double>();
    }
    
    public ReforgeAlgo atLeast(final StatT x, final int value) {
        this.between(x, value, 999999);
        return this;
    }
    
    public ReforgeAlgo atMost(final StatT x, final int value) {
        this.between(x, 0, value);
        return this;
    }
    
    public ReforgeAlgo dontReforge(final StatT x) {
        this.dontSet.add(x);
        return this;
    }
    
    public ReforgeAlgo between(final StatT x, int lower, int upper) {
        lower = Math.max(0, lower);
        upper = Math.min(999999, upper);
        if (upper >= lower) {
            this.constMap.put(x, new Span(lower, upper));
        }
        return this;
    }
    
    public ReforgeAlgo setWeight(final StatT x, final double weight) {
        if (weight == 0.0) {
            this.weightMap.remove(x);
        }
        else {
            this.weightMap.put(x, weight);
        }
        return this;
    }
    
    static class Span
    {
        final int lower;
        final int upper;
        
        Span(final int lower, final int upper) {
            this.lower = lower;
            this.upper = upper;
        }
    }
}
