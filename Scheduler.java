// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class Scheduler
{
    private Frame head;
    private Frame butt;
    
    Frame getFrame(final int t) {
        final Frame f = new Frame(t);
        if (this.butt == null) {
            final Frame frame = f;
            this.head = frame;
            return this.butt = frame;
        }
        if (t > this.butt.t) {
            f.past = this.butt;
            this.butt.future = f;
            return this.butt = f;
        }
        if (t < this.head.t) {
            f.future = this.head;
            this.head.past = f;
            return this.head = f;
        }
        Frame i;
        for (i = this.butt; i.t < t; i = i.past) {}
        f.future = i.future;
        i.future = f;
        return f.past = i;
    }
    
    class Event
    {
        int t;
    }
    
    private class Frame
    {
        final int t;
        Frame past;
        Frame future;
        
        Frame(final int t) {
            this.t = t;
        }
    }
}
