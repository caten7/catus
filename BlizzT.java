// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Arrays;
import java.util.Comparator;

public class BlizzT
{
    public final int id;
    public final String name;
    public static final Comparator<BlizzT> CMP_ID;
    public static final Comparator<BlizzT> CMP_NAME;
    public static final TypeSet ANY;
    public static final TypeSet EMPTY_SET;
    
    public BlizzT(final int id, final String name) {
        this.id = id;
        this.name = name;
    }
    
    public static String getName(final BlizzT x) {
        return (x == null) ? null : x.name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public TypeSet soloSet() {
        return makeSet(this);
    }
    
    public static <T extends BlizzT> TypeMap<T> makeMap(final T... v) {
        final T[] by_id = Arrays.copyOf(v, v.length);
        Arrays.sort(by_id, BlizzT.CMP_ID);
        final T[] by_name = Arrays.copyOf(v, v.length);
        Arrays.sort(by_name, BlizzT.CMP_NAME);
        return new TypeMap<T>((BlizzT[])by_id, (BlizzT[])by_name);
    }
    
    public static <T extends BlizzT> TypeSet<T> makeSet(final T... v) {
        if (v == null || v.length == 0) {
            return (TypeSet<T>)BlizzT.EMPTY_SET;
        }
        if (v.length == 1) {
            final T temp = v[0];
            return (TypeSet<T>)new TypeSet() {
                @Override
                public boolean contains(final BlizzT x) {
                    return x == temp;
                }
            };
        }
        long bits = 0L;
        for (final T x : v) {
            final int id = x.id;
            if (id >= 64) {
                final BlizzT[] by_id = Arrays.copyOf(v, v.length);
                Arrays.sort(by_id, BlizzT.CMP_ID);
                return (TypeSet<T>)new TypeSet() {
                    @Override
                    public boolean contains(final BlizzT x) {
                        return BlizzT.indexOf(by_id, x.id) >= 0;
                    }
                };
            }
            bits |= 1L << id;
        }
        final long _bits = bits;
        return (TypeSet<T>)new TypeSet() {
            @Override
            public boolean contains(final BlizzT x) {
                return (_bits >>> x.id & 0x1L) > 0L;
            }
        };
    }
    
    public static int indexOf(final BlizzT[] v, final int id) {
        int i = 0;
        int e = v.length;
        while (i < e) {
            final int m = e + i >>> 1;
            final int x = v[m].id;
            if (x == id) {
                return m;
            }
            if (x > id) {
                e = m;
            }
            else {
                i = m + 1;
            }
        }
        return ~i;
    }
    
    static {
        CMP_ID = new Comparator<BlizzT>() {
            @Override
            public int compare(final BlizzT a, final BlizzT b) {
                return a.id - b.id;
            }
        };
        CMP_NAME = new Comparator<BlizzT>() {
            @Override
            public int compare(final BlizzT a, final BlizzT b) {
                return a.name.compareToIgnoreCase(b.name);
            }
        };
        ANY = new TypeSet() {
            @Override
            public boolean contains(final BlizzT x) {
                return true;
            }
        };
        EMPTY_SET = new TypeSet() {
            @Override
            public boolean contains(final BlizzT x) {
                return false;
            }
        };
    }
    
    public static class TypeMap<T extends BlizzT>
    {
        final T[] by_id;
        final T[] by_name;
        
        private TypeMap(final T[] by_id, final T[] by_name) {
            this.by_id = by_id;
            this.by_name = by_name;
        }
        
        public T req(final int id) {
            final T temp = this.get(id);
            if (temp == null) {
                throw new IllegalArgumentException("Unknown id: " + id);
            }
            return temp;
        }
        
        public T get(final int id) {
            final int i = BlizzT.indexOf(this.by_id, id);
            return (T)((i >= 0) ? this.by_id[i] : null);
        }
        
        public T req(final String key) {
            final T temp = this.get(key);
            if (temp == null) {
                throw new IllegalArgumentException("Unknown key: " + key);
            }
            return temp;
        }
        
        public T get(final String key) {
            return this.get(key, null);
        }
        
        public T get(final String key, final T backup) {
            if (key == null) {
                return backup;
            }
            int i = 0;
            int e = this.by_name.length;
            while (i < e) {
                final int m = e + i >>> 1;
                final T x = this.by_name[m];
                final int cmp = x.name.compareToIgnoreCase(key);
                if (cmp == 0) {
                    return x;
                }
                if (cmp > 0) {
                    e = m;
                }
                else {
                    i = m + 1;
                }
            }
            return backup;
        }
        
        public T find(String name) {
            name = name.toLowerCase();
            for (final T x : this.by_name) {
                if (x.name.toLowerCase().contains(name)) {
                    return x;
                }
            }
            return null;
        }
    }
    
    public interface TypeSet<T extends BlizzT>
    {
        boolean contains(final T p0);
    }
}
