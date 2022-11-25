// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.io.Closeable;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.HashMap;

public abstract class SimcReader
{
    private static final HashMap<String, SlotT> slotMap;
    private static final HashMap<String, StatT> reforgeMap;
    public static final String MAIN = "main";
    
    public void parse(String s) {
        s = s.trim();
        try {
            this._parse(new BufferedReader(new StringReader(s)));
        }
        catch (IOException err) {
            this.gotError(err.getMessage());
        }
    }
    
    public void parse(final File f) {
        BufferedReader r = null;
        try {
            r = new BufferedReader(new FileReader(f));
            this._parse(r);
            r.close();
        }
        catch (IOException err) {
            Utils.silentClose(r);
        }
    }
    
    private void _parse(final BufferedReader r) throws IOException {
        this.gotInit();
        final ArrayList<Pair<String, String>> replaceMap = new ArrayList<Pair<String, String>>();
        final StringBuilder actBuf = new StringBuilder(4096);
        final HashMap<String, String> kvMap = new HashMap<String, String>();
        int lineNum = 0;
        while (true) {
            String line = r.readLine();
            ++lineNum;
            if (line == null) {
                this.gotActions(this.cleanupActions(actBuf, replaceMap));
                return;
            }
            int pos = line.indexOf(35);
            if (pos >= 0) {
                line = line.substring(0, pos);
            }
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }
            pos = line.indexOf(61);
            if (pos == -1) {
                throw new IOException("Invalid line: " + line);
            }
            String key = line.substring(0, pos).trim();
            final String val = line.substring(pos + 1).trim();
            if ("race".equalsIgnoreCase(key)) {
                this.gotRace(RaceT.MAP.find(val));
            }
            else if ("talents".equalsIgnoreCase(key)) {
                this.gotTalents(val);
            }
            else if ("glyphs".equalsIgnoreCase(key)) {
                this.gotGlyphs(val.split("/"));
            }
            else if ("position".equalsIgnoreCase(key)) {
                this.gotPosition(val.equalsIgnoreCase("back"));
            }
            else if ("professions".equalsIgnoreCase(key)) {
                int bits = 0;
                for (final String x : val.split("/")) {
                    final ProfT prof = ProfT.MAP.find(x.split("=")[0]);
                    if (prof == null) {
                        this.gotError("Unknown profession: " + x);
                    }
                    else {
                        bits |= prof.bit;
                    }
                }
                if (Integer.bitCount(bits) > 2) {
                    this.gotError("Too many professions: " + val);
                }
                else {
                    this.gotProfs(bits);
                }
            }
            else if (key.matches("^\\$\\([_a-zA-Z0-9]+\\)$")) {
                replaceMap.add(new Pair<String, String>(key, val));
            }
            else if (key.startsWith("actions")) {
                pos = key.length() - 1;
                if (key.charAt(pos) == '+') {
                    key = key.substring(0, pos).trim();
                }
                pos = key.indexOf(46);
                String group = "main";
                if (pos >= 0) {
                    group = key.substring(pos + 1).trim();
                }
                for (String x : val.split("/")) {
                    x = x.trim();
                    if (!x.isEmpty()) {
                        if (actBuf.length() > 0) {
                            actBuf.append('\n');
                        }
                        actBuf.append(group);
                        actBuf.append(": ");
                        actBuf.append(x);
                    }
                }
            }
            else {
                final SlotT slot = SimcReader.slotMap.get(key);
                if (slot != null) {
                    final String[] comps = val.split(",");
                    final String name = comps[0];
                    replaceMap.add(new Pair<String, String>("=" + name, "=" + slot.name));
                    kvMap.clear();
                    for (int i = 1; i < comps.length; ++i) {
                        final String comp = comps[i];
                        pos = comp.indexOf(61);
                        if (pos == -1) {
                            throw new IOException("Invalid gear: " + line);
                        }
                        kvMap.put(comp.substring(0, pos).trim().toLowerCase(), comp.substring(pos + 1).trim());
                    }
                    int itemId;
                    try {
                        itemId = Integer.parseInt(kvMap.get("id"));
                    }
                    catch (RuntimeException err) {
                        throw new IOException("Missing item id: " + line);
                    }
                    ReforgePair reforge = null;
                    final String reforgeExpr = kvMap.get("reforge");
                    if (reforgeExpr != null) {
                        pos = reforgeExpr.indexOf(95);
                        if (pos == -1) {
                            throw new IOException(String.format("Missing reforge separator (%s): %s", reforgeExpr, line));
                        }
                        final StatT src = SimcReader.reforgeMap.get(reforgeExpr.substring(0, pos).trim());
                        if (src == null) {
                            throw new IOException(String.format("Unknown reforge source (%s): %s", reforgeExpr, line));
                        }
                        final StatT dst = SimcReader.reforgeMap.get(reforgeExpr.substring(pos + 1).trim());
                        if (dst == null) {
                            throw new IOException(String.format("Unknown reforge destination (%s): %s", reforgeExpr, line));
                        }
                        reforge = ReforgePair.make(src, dst);
                    }
                    this.gotSlot(slot, itemId, reforge, kvMap.get("gems"));
                }
                else {
                    this.gotUnknown(line);
                }
            }
        }
    }
    
    private String cleanupActions(final StringBuilder sb, final Collection<Pair<String, String>> replaceMap) {
        String str = sb.toString();
        for (final Pair<String, String> pr : replaceMap) {
            str = str.replace(pr.car, pr.cdr);
        }
        str = str.trim();
        return str;
    }
    
    public abstract void gotInit();
    
    public abstract void gotError(final String p0);
    
    public abstract void gotUnknown(final String p0);
    
    public abstract void gotSlot(final SlotT p0, final int p1, final ReforgePair p2, final String p3);
    
    public abstract void gotRace(final RaceT p0);
    
    public abstract void gotTalents(final String p0);
    
    public abstract void gotGlyphs(final String[] p0);
    
    public abstract void gotPosition(final boolean p0);
    
    public abstract void gotActions(final String p0);
    
    public abstract void gotProfs(final int p0);
    
    static {
        (slotMap = new HashMap<String, SlotT>()).put("head", SlotT.HEAD);
        SimcReader.slotMap.put("neck", SlotT.NECK);
        SimcReader.slotMap.put("shoulders", SlotT.SHOULDER);
        SimcReader.slotMap.put("back", SlotT.BACK);
        SimcReader.slotMap.put("chest", SlotT.CHEST);
        SimcReader.slotMap.put("wrists", SlotT.WRIST);
        SimcReader.slotMap.put("hands", SlotT.HANDS);
        SimcReader.slotMap.put("waist", SlotT.WAIST);
        SimcReader.slotMap.put("legs", SlotT.LEGS);
        SimcReader.slotMap.put("feet", SlotT.FEET);
        SimcReader.slotMap.put("finger1", SlotT.FINGER_1);
        SimcReader.slotMap.put("finger2", SlotT.FINGER_2);
        SimcReader.slotMap.put("trinket1", SlotT.TRINKET_1);
        SimcReader.slotMap.put("trinket2", SlotT.TRINKET_2);
        SimcReader.slotMap.put("main_hand", SlotT.MAIN_HAND);
        (reforgeMap = new HashMap<String, StatT>()).put("exp", StatT.EXP);
        SimcReader.reforgeMap.put("hit", StatT.HIT);
        SimcReader.reforgeMap.put("haste", StatT.HASTE);
        SimcReader.reforgeMap.put("mastery", StatT.MASTERY);
        SimcReader.reforgeMap.put("crit", StatT.CRIT);
    }
}
