// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class StatMap implements StatValued
{
    private final int[] value;
    
    public static StatMap make(final StatValue... v) {
        final StatMap temp = new StatMap();
        for (final StatValue x : v) {
            temp.set(x);
        }
        return temp;
    }
    
    public static StatMap makeSingle(final StatT stat, final int amt) {
        final StatMap temp = new StatMap();
        temp.set(stat, amt);
        return temp;
    }
    
    public static StatMap makePrimary(final int amt) {
        final StatMap temp = new StatMap();
        for (final StatT x : StatT.PRIMARY) {
            temp.set(x, amt);
        }
        return temp;
    }
    
    public StatMap() {
        this.value = new int[StatT.NUM];
    }
    
    public StatMap copy() {
        final StatMap copy = new StatMap();
        copy.set(this);
        return copy;
    }
    
    public int get(final StatT x) {
        return this.value[x.index];
    }
    
    public StatValue statValue(final StatT x) {
        return StatValue.make(x, this.get(x));
    }
    
    public void set(final StatMap map) {
        System.arraycopy(map.value, 0, this.value, 0, this.value.length);
    }
    
    public void set(final StatT x, final int y) {
        this.value[x.index] = y;
    }
    
    public void set(final StatValue x) {
        this.set(x.type, x.value);
    }
    
    public void add(final StatValue x) {
        final int[] value = this.value;
        final int index = x.type.index;
        value[index] += x.value;
    }
    
    public void add(final StatT x, final int y) {
        final int[] value = this.value;
        final int index = x.index;
        value[index] += y;
    }
    
    public void add(final StatT[] stats, final int[] delta) {
        for (int i = 0, e = stats.length; i < e; ++i) {
            final int[] value = this.value;
            final int index = stats[i].index;
            value[index] += delta[i];
        }
    }
    
    public void add(final StatMap map) {
        for (int i = 0, e = this.value.length; i < e; ++i) {
            final int[] value = this.value;
            final int n = i;
            value[n] += map.value[i];
        }
    }
    
    public void add(final StatValued x) {
        for (int i = 0, e = this.value.length; i < e; ++i) {
            final int[] value = this.value;
            final int n = i;
            value[n] += x.getStat(StatT.STATS[i]);
        }
    }
    
    public int sum() {
        int sum = 0;
        for (int i = 0, e = this.value.length; i < e; ++i) {
            sum += this.value[i];
        }
        return sum;
    }
    
    public void clear() {
        for (int i = 0, e = this.value.length; i < e; ++i) {
            this.value[i] = 0;
        }
    }
    
    public StatT anyStat() {
        for (int i = 0, e = this.value.length; i < e; ++i) {
            if (this.value[i] != 0) {
                return StatT.STATS[i];
            }
        }
        return null;
    }
    
    public boolean any() {
        for (int i = 0, e = this.value.length; i < e; ++i) {
            if (this.value[i] != 0) {
                return true;
            }
        }
        return false;
    }
    
    public boolean any(final StatT[] stats) {
        for (final StatT x : stats) {
            if (this.value[x.index] != 0) {
                return true;
            }
        }
        return false;
    }
    
    public double dot(final double[] weights) {
        double sum = 0.0;
        for (int i = 0, e = this.value.length; i < e; ++i) {
            sum += this.value[i] * weights[i];
        }
        return sum;
    }
    
    public boolean sameAs(final StatMap other) {
        for (int i = 0, e = this.value.length; i < e; ++i) {
            if (this.value[i] != other.value[i]) {
                return false;
            }
        }
        return true;
    }
    
    public void setAll(final StatMap other) {
        System.arraycopy(other.value, 0, this.value, 0, this.value.length);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(64);
        int primaryCount = 0;
        int primaryValue = 0;
        for (int i = 0, e = this.value.length; i < e; ++i) {
            final int x = this.value[i];
            if (x != 0) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                final StatT stat = StatT.STATS[i];
                if (primaryCount >= 0) {
                    if (stat.isPrimary()) {
                        if (primaryCount++ == 0) {
                            primaryValue = x;
                        }
                        else if (primaryValue != x) {
                            primaryCount = -1;
                        }
                    }
                    else {
                        primaryCount = -1;
                    }
                }
                sb.append(stat.formatValue(x));
            }
        }
        if (primaryCount > 0 && primaryCount == StatT.PRIMARY.length) {
            sb.setLength(0);
            sb.append('+');
            sb.append(primaryValue);
            sb.append(' ');
            sb.append("Stats");
        }
        return (sb.length() > 0) ? sb.toString() : "None";
    }
    
    @Override
    public int getStat(final StatT stat) {
        return this.get(stat);
    }
}
