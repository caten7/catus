// 
// Decompiled by Procyon v0.5.36
// 

package catus;

public class Sched
{
    int clock;
    Event head;
    Event butt;
    
    public static void test() {
        final Sched s = new Sched();
        final EventKey key = new EventKey("Swing");
        s.addEvent(5, new Event(key) {
            @Override
            void run() {
                s.addEvent(5, this);
            }
        });
        s.process();
    }
    
    boolean removeEvent(final EventKey key, final boolean consumed) {
        if (!key.up) {
            return false;
        }
        final Event e = key.event;
        if (e == null) {
            return false;
        }
        if (e == this.head) {
            this.head = e.next;
        }
        else if (e == this.butt) {
            this.butt = e.prev;
        }
        else {
            e.next.prev = e.prev;
            e.prev.next = e.next;
        }
        if (consumed) {
            e.run();
        }
        e.faded(consumed);
        final Event event = e;
        final Event event2 = e;
        final Event event3 = null;
        event2.prev = event3;
        event.next = event3;
        key.event = null;
        key.up = false;
        return true;
    }
    
    void advance() {
    }
    
    void process() {
        try {
            while (true) {
                if (this.head.time > this.clock) {
                    this.advance();
                }
                final Event e = this.head;
                this.head = e.next;
                this.clock = e.time;
                if (e.key != null) {
                    e.key.event = null;
                }
                final Event event = e;
                final Event event2 = e;
                final Event event3 = null;
                event2.prev = event3;
                event.next = event3;
                e.run();
                e.faded(true);
            }
        }
        catch (EndOfCombat err) {
            while (true) {
                final Event e2 = this.head;
                if (e2 == null) {
                    break;
                }
                this.head = e2.next;
                this.clock = e2.time;
                if (e2.key != null) {
                    e2.key.event = null;
                }
                final Event event4 = e2;
                final Event event5 = e2;
                final Event event6 = null;
                event5.prev = event6;
                event4.next = event6;
                e2.faded(false);
            }
            this.butt = null;
        }
    }
    
    void ensureFrozenClock() {
        if (this.head == null || this.head.time > this.clock) {
            final Event e = new Event(null);
            e.next = this.head;
            this.head = e;
            e.time = this.clock;
        }
    }
    
    void addEvent(final int delta, final Event e) {
        final int t = this.clock + delta;
        final EventKey k = e.key;
        boolean replaced = false;
        if (k != null) {
            replaced = this.removeEvent(k, false);
            k.event = e;
            k.added = this.clock;
            k.expires = t;
        }
        if (this.head == null) {
            this.butt = e;
            this.head = e;
            final Event event = null;
            e.prev = event;
            e.next = event;
        }
        else if (t < this.head.time) {
            this.head.prev = e;
            e.next = this.head;
            e.prev = null;
            this.head = e;
        }
        else if (t >= this.butt.time) {
            this.butt.next = e;
            e.next = null;
            e.prev = this.butt;
            this.butt = e;
        }
        else {
            Event cur;
            for (cur = this.head; t <= cur.time; cur = cur.next) {}
            cur.prev.next = e;
            e.prev = cur.prev;
            cur.prev = e;
            e.next = cur;
        }
        e.added(replaced);
    }
    
    static class EndOfCombat extends RuntimeException
    {
    }
    
    static class EventKey
    {
        final String name;
        boolean up;
        Event event;
        int added;
        int expires;
        
        public EventKey(final String name) {
            this.name = name;
        }
        
        int msLeft() {
            return this.up ? (this.expires - this.added) : 0;
        }
    }
    
    static class Event
    {
        Event next;
        Event prev;
        int time;
        final EventKey key;
        
        Event(final EventKey key) {
            this.key = key;
        }
        
        void run() {
        }
        
        void fakeFade() {
        }
        
        void faded(final boolean executed) {
        }
        
        void added(final boolean replaced) {
        }
    }
}
