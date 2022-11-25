// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public abstract class Flask implements StatValued
{
    static final Flask NONE;
    static final Flask FLASK_OF_SPRING_BLOSSOMS;
    static final Flask[] ALL;
    public final String name;
    
    public Flask(final String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.toString(false);
    }
    
    public String toString(final boolean mixology) {
        return this.name + ": " + this.statStr(mixology);
    }
    
    @Override
    public int getStat(final StatT stat) {
        return this.getStat(stat, false);
    }
    
    public abstract String statStr(final boolean p0);
    
    public abstract int getStat(final StatT p0, final boolean p1);
    
    static {
        NONE = new Flask("No Flask") {
            @Override
            public String statStr(final boolean mixology) {
                return this.name;
            }
            
            @Override
            public String toString(final boolean mixology) {
                return this.name;
            }
            
            @Override
            public int getStat(final StatT stat, final boolean mixology) {
                return 0;
            }
        };
        ALL = new Flask[] { FLASK_OF_SPRING_BLOSSOMS = new Stat("Flask of Spring Blossoms", StatT.AGI, 1000, 1320), new Stat("Flask of Winter's Bite", StatT.STR, 1000, 1320), new Stat("Flask of Warm Sun", StatT.INT, 1000, 1320), new Stat("Flask of the Earth", StatT.STA, 1500, 1980), new Stat("Darkmoon Firewater", StatT.AP, 1736), new Stat("Elixir of Weaponry", StatT.EXP, 750), new Stat("Elixir of Perfection", StatT.HIT, 750), new Stat("Mad Hozen Elixir", StatT.CRIT, 750), new Stat("Monk's Elixir", StatT.MASTERY, 750), new Stat("Elixir of the Rapids", StatT.HASTE, 750), new All("Visions of Insanity", 500), new Stat("Alchemist's Flask", StatT.AGI, 320, 320), new Stat("Flask of the Winds", StatT.AGI, 300, 450), Flask.NONE };
    }
    
    static class Custom extends Flask
    {
        final StatMap basic;
        final StatMap alch;
        
        public Custom(final String name) {
            super(name);
            this.basic = new StatMap();
            this.alch = new StatMap();
        }
        
        @Override
        public int getStat(final StatT stat, final boolean mixology) {
            return (mixology ? this.alch : this.basic).get(stat);
        }
        
        @Override
        public String statStr(final boolean mixology) {
            return (mixology ? this.alch : this.basic).toString();
        }
    }
    
    static class All extends Flask
    {
        public final int value;
        
        public All(final String name, final int value) {
            super(name);
            this.value = value;
        }
        
        @Override
        public int getStat(final StatT x, final boolean mixology) {
            return x.isPrimary() ? this.value : 0;
        }
        
        @Override
        public String statStr(final boolean mixology) {
            return String.format("+%d Stats", this.value);
        }
    }
    
    static class Stat extends Flask
    {
        public final StatT stat;
        final int basic;
        final int alch;
        
        public Stat(final String name, final StatT type, final int basic) {
            this(name, type, basic, basic);
        }
        
        public Stat(final String name, final StatT type, final int basic, final int alch) {
            super(name);
            this.stat = type;
            this.basic = basic;
            this.alch = alch;
        }
        
        @Override
        public int getStat(final StatT x, final boolean mixology) {
            return (this.stat == x) ? (mixology ? this.alch : this.basic) : 0;
        }
        
        @Override
        public String statStr(final boolean mixology) {
            return this.stat.formatValue(this.getStat(this.stat, mixology));
        }
    }
}
