// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class NamedThing
{
    public final String name;
    
    public NamedThing(final String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    static class Int extends NamedThing
    {
        public final int value;
        
        public Int(final String name, final int value) {
            super(name);
            this.value = value;
        }
    }
    
    static class Obj<T> extends NamedThing
    {
        public final T obj;
        
        public Obj(final String name, final T thing) {
            super(name);
            this.obj = thing;
        }
    }
    
    static class Str extends Obj<String>
    {
        public Str(final String name, final String thing) {
            super(name, thing);
        }
    }
}
