// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Arrays;

public class IntSet
{
    public static final int INITIAL_CAPACITY = 16;
    public int count;
    public int[] member;
    
    public static void test() {
        final IntSet s = new IntSet(2);
        s.add(6);
        s.add(3);
        System.out.println(s);
        s.add(2);
        System.out.println(s);
        s.remove(6);
        s.remove(5);
        System.out.println(s);
        s.add(10);
        s.add(-1);
        System.out.println(s);
        s.remove(2);
        System.out.println(s);
        for (int i = 1; i < 133; ++i) {
            s.add(i);
        }
        System.out.println(s);
        System.out.println(s.indexOf(1));
    }
    
    public IntSet() {
    }
    
    public IntSet(final int cap) {
        if (cap > 0) {
            this.member = new int[cap];
        }
    }
    
    public int[] toArray() {
        return Arrays.copyOf(this.member, this.count);
    }
    
    @Override
    public String toString() {
        if (this.member == null) {
            return "IntSet[empty]";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("IntSet[");
        sb.append(this.count);
        sb.append("/");
        sb.append(this.member.length);
        sb.append("]{");
        if (this.count < 20) {
            for (int i = 0; i < this.count; ++i) {
                if (i > 0) {
                    sb.append(',');
                }
                sb.append(this.member[i]);
            }
        }
        else {
            sb.append(this.member[0]);
            sb.append("...");
            sb.append(this.member[this.count - 1]);
        }
        sb.append("}");
        return sb.toString();
    }
    
    public void trimToSize() {
        if (this.count == 0) {
            this.member = null;
        }
        else if (this.count < this.member.length) {
            this.member = Arrays.copyOf(this.member, this.count);
        }
    }
    
    private void add(final int mem, final int at) {
        if (this.member == null) {
            (this.member = new int[16])[0] = mem;
        }
        else if (this.count == this.member.length) {
            final int cap = Math.max(16, this.count + this.count);
            final int[] temp = new int[cap];
            if (at == 0) {
                System.arraycopy(this.member, 0, temp, 1, this.count);
                temp[0] = mem;
            }
            else if (at < this.count) {
                System.arraycopy(this.member, 0, temp, 0, at);
                temp[at] = mem;
                System.arraycopy(this.member, at, temp, at + 1, this.count - at);
            }
            else {
                System.arraycopy(this.member, 0, temp, 0, this.count);
                temp[this.count] = mem;
            }
            this.member = temp;
        }
        else if (at < this.count) {
            System.arraycopy(this.member, at, this.member, at + 1, this.count - at);
            this.member[at] = mem;
        }
        else {
            this.member[this.count] = mem;
        }
        ++this.count;
    }
    
    public IntSet copy() {
        final IntSet copy = new IntSet();
        copy.setAll(this);
        return copy;
    }
    
    public void setAll(final IntSet other) {
        this.count = other.count;
        if (this.count > 0) {
            this.member = Arrays.copyOf(other.member, this.count);
        }
    }
    
    public int addAll(final int... a) {
        int added = 0;
        for (final int x : a) {
            if (this.add(x)) {
                ++added;
            }
        }
        return added;
    }
    
    public boolean add(final int mem) {
        final int i = this.indexOf(mem);
        if (i >= 0) {
            return false;
        }
        this.add(mem, ~i);
        return true;
    }
    
    public int setAndIndexOf(final int mem) {
        int i = this.indexOf(mem);
        if (i < 0) {
            i ^= -1;
            this.add(mem, i);
        }
        return i;
    }
    
    public int indexOf(final int mem) {
        return (this.count > 0) ? Arrays.binarySearch(this.member, 0, this.count, mem) : -1;
    }
    
    public boolean contains(final int mem) {
        return this.indexOf(mem) >= 0;
    }
    
    public void remove(final IntSet other) {
        for (int i = 0; i < other.count; ++i) {
            this.remove(other.member[i]);
        }
    }
    
    public boolean remove(final int mem) {
        final int i = this.indexOf(mem);
        if (i < 0) {
            return false;
        }
        --this.count;
        if (i < this.count) {
            System.arraycopy(this.member, i, this.member, i - 1, 1 + this.count - i);
        }
        return true;
    }
    
    public void clear() {
        this.count = 0;
    }
    
    public void clearAndFree() {
        this.clear();
        this.member = null;
    }
}
