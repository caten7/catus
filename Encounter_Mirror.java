// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashSet;

public class Encounter_Mirror extends Encounter
{
    public static final String NAME = "Mirror";
    final HashSet<Type> enabled;
    int duration;
    String desc;
    Trigger[] triggers;
    private static final TreeMap<String, Type> typeMap;
    
    public Encounter_Mirror() {
        super("Mirror");
        this.enabled = new HashSet<Type>();
    }
    
    @Override
    public String getDesc() {
        return this.desc;
    }
    
    public static String formatTriggers(final Trigger[] triggers) {
        final StringBuilder sb = new StringBuilder();
        for (final Trigger t : triggers) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append(Fmt.msms(t.time));
            sb.append(" ");
            Fmt.padRight(sb, (t.data != 0L) ? String.format("%s %s", t.type, t.data) : t.type.toString(), 20);
            if (t.desc != null) {
                sb.append(" # ");
                sb.append(t.desc);
            }
        }
        return sb.toString();
    }
    
    public static Trigger[] parseTriggers(final String script, final Set<Type> allowable) {
        final ArrayList<Trigger> list = new ArrayList<Trigger>();
        int lineNum = 0;
        for (final String line0 : script.split("\n")) {
            ++lineNum;
            String line2 = line0;
            final int pos = line2.indexOf(35);
            String desc = null;
            if (pos >= 0) {
                desc = line2.substring(pos + 1).trim();
                line2 = line2.substring(0, pos);
            }
            line2 = line2.trim();
            if (!line2.isEmpty()) {
                final String[] comps = line2.split("\\s+");
                try {
                    long data = 0L;
                    if (comps.length < 2) {
                        throw new RuntimeException("Invalid trigger format");
                    }
                    final String timeStr = comps[0];
                    if (timeStr.length() != 9) {
                        throw new RuntimeException("Invalid time format: " + timeStr);
                    }
                    final int min = Integer.parseInt(timeStr.substring(0, 2));
                    final int sec = Integer.parseInt(timeStr.substring(3, 5));
                    final int rem = Integer.parseInt(timeStr.substring(6, 9));
                    if (min < 0 || sec < 0 || sec >= 60 || rem < 0 || rem > 999) {
                        throw new RuntimeException("Invalid time data: " + timeStr);
                    }
                    final int time = (min * 60 + sec) * 1000 + rem;
                    final String typeStr = comps[1];
                    final Type type = Encounter_Mirror.typeMap.get(typeStr);
                    if (type == null) {
                        throw new RuntimeException("Unknown type: " + typeStr);
                    }
                    if (allowable == null || allowable.contains(type)) {
                        if (comps.length >= 3) {
                            final String dataStr = comps[2];
                            try {
                                data = Long.parseLong(dataStr);
                            }
                            catch (NumberFormatException err2) {
                                throw new RuntimeException("Invalid data: " + dataStr);
                            }
                        }
                        list.add(new Trigger(time, type, data, desc));
                    }
                }
                catch (RuntimeException err) {
                    throw new RuntimeException("Parse Error: <b>Line #" + lineNum + "</b><br/><tt>" + err.getMessage() + "</tt>");
                }
            }
        }
        Collections.sort(list, Trigger.CMP);
        return list.toArray(new Trigger[list.size()]);
    }
    
    static {
        typeMap = new TreeMap<String, Type>(String.CASE_INSENSITIVE_ORDER);
        for (final Type x : Type.values()) {
            Encounter_Mirror.typeMap.put(x.name(), x);
        }
    }
    
    public static class Trigger
    {
        public final int time;
        public final Type type;
        public final long data;
        public final String desc;
        static final Comparator<Trigger> CMP;
        
        Trigger(final int time, final Type type, final long data, final String desc) {
            this.time = time;
            this.type = type;
            this.data = data;
            this.desc = desc;
        }
        
        static {
            CMP = new Comparator<Trigger>() {
                @Override
                public int compare(final Trigger a, final Trigger b) {
                    return a.time - b.time;
                }
            };
        }
    }
    
    public enum Type
    {
        SELF, 
        EXTERNAL, 
        HEALTH, 
        WAIT, 
        END;
    }
}
