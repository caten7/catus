// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Encounter
{
    double periodicDmgMod;
    IntDist periodicDmg_freq0;
    IntDist periodicDmg_freq;
    IntDist periodicDmg_time;
    IntDist periodicIdle_freq;
    IntDist periodicIdle_time;
    int periodicIdle_limit;
    IntDist periodicCast_freq;
    IntDist cooldownDelay;
    Command[] commands;
    final String name;
    
    public Encounter(final String name) {
        this.name = name;
    }
    
    public String getDesc() {
        return "";
    }
    
    public static Command[] parseScript(final String script) {
        final ArrayList<Command> ret = new ArrayList<Command>();
        final String[] split;
        final String[] lines = split = script.split("\n");
        for (String line2 : split) {
            final String line0 = line2;
            int pos = line2.indexOf(35);
            if (pos >= 0) {
                line2 = line2.substring(0, pos);
            }
            line2 = line2.trim();
            if (!line2.isEmpty()) {
                try {
                    final char ch = line2.charAt(0);
                    if (ch != '@') {
                        throw new RuntimeException(String.format("Unsupported command prefix (%c)", ch));
                    }
                    line2 = line2.substring(1).trim();
                    final String[] v = line2.split("\\s+");
                    if (v.length < 2) {
                        throw new RuntimeException("Missing command");
                    }
                    final int time = InvFmt.parse_time(v[0], "Time", 1000, false);
                    final String name = v[1];
                    if (name.isEmpty()) {
                        throw new RuntimeException("Empty command");
                    }
                    final HashMap<String, String> argMap = new HashMap<String, String>();
                    for (int i = 2; i < v.length; ++i) {
                        final String comp = v[i];
                        pos = comp.indexOf(58);
                        if (pos == -1) {
                            throw new RuntimeException("Missing key-value separator (:)");
                        }
                        final String key = comp.substring(0, pos).trim();
                        if (key.isEmpty()) {
                            throw new RuntimeException("Empty key");
                        }
                        final String val = comp.substring(pos + 1).trim();
                        if (val.isEmpty()) {
                            throw new RuntimeException(String.format("Empty value for key (%s)", key));
                        }
                        argMap.put(key, val);
                    }
                    ret.add(new Command(time, name, new HashMap<String, String>(argMap)));
                }
                catch (RuntimeException err) {
                    throw new RuntimeException(err.getMessage() + " for line:\n\n" + line0);
                }
            }
        }
        return (Command[])(ret.isEmpty() ? null : ((Command[])ret.toArray(new Command[ret.size()])));
    }
    
    public static class Command
    {
        final int time;
        final String name;
        final HashMap<String, String> map;
        
        Command(final int time, final String cmd, final HashMap<String, String> map) {
            this.time = time;
            this.name = cmd;
            this.map = map;
        }
    }
}
