// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.prefs.AbstractPreferences;

public class PrefHelp
{
    public static final AbstractPreferences DEFAULTS;
    
    public static void setLargeString(final Preferences prefs, final String key, final String val) {
        final int len = val.length();
        if (len > 8192) {
            prefs.remove(key);
            try {
                prefs.node(key).removeNode();
            }
            catch (BackingStoreException ex) {}
            final Preferences node = prefs.node(key);
            int end;
            for (int pos = 0, i = 0; pos < len; pos = end, ++i) {
                end = Math.min(pos + 8192, len);
                node.put(Integer.toString(i), val.substring(pos, end));
            }
        }
        else {
            try {
                prefs.node(key).removeNode();
            }
            catch (BackingStoreException ex2) {}
            prefs.put(key, val);
        }
    }
    
    public static String getLargeString(final Preferences prefs, final String key, final String def) {
        try {
            if (!prefs.nodeExists(key)) {
                return prefs.get(key, def);
            }
        }
        catch (BackingStoreException err) {
            return def;
        }
        final Preferences node = prefs.node(key);
        final StringBuilder sb = new StringBuilder(24576);
        int index = 0;
        while (true) {
            final String more = node.get(Integer.toString(index), null);
            if (more == null) {
                break;
            }
            sb.append(more);
            ++index;
        }
        return sb.toString();
    }
    
    public static void silentClear(final Preferences prefs) {
        try {
            prefs.clear();
            for (final String child : prefs.childrenNames()) {
                prefs.node(child).removeNode();
            }
        }
        catch (BackingStoreException ex) {}
    }
    
    public static Preferences getNodeIfExists(final Preferences prefs, final String name) {
        try {
            return prefs.nodeExists(name) ? prefs.node(name) : null;
        }
        catch (BackingStoreException err) {
            return null;
        }
    }
    
    public static boolean nodeExists(final Preferences prefs, final String name) {
        try {
            return prefs.nodeExists(name);
        }
        catch (BackingStoreException err) {
            return false;
        }
    }
    
    static {
        DEFAULTS = new AbstractPreferences(null, "") {
            private void wtf() {
                throw new IllegalArgumentException("Attempt to modify default preferences");
            }
            
            @Override
            protected void putSpi(final String string, final String string1) {
                this.wtf();
            }
            
            @Override
            protected String getSpi(final String string) {
                return null;
            }
            
            @Override
            protected void removeSpi(final String string) {
                this.wtf();
            }
            
            @Override
            protected void removeNodeSpi() throws BackingStoreException {
                this.wtf();
            }
            
            @Override
            protected String[] keysSpi() throws BackingStoreException {
                return new String[0];
            }
            
            @Override
            protected String[] childrenNamesSpi() throws BackingStoreException {
                return new String[0];
            }
            
            @Override
            protected AbstractPreferences childSpi(final String string) {
                return this;
            }
            
            @Override
            protected void syncSpi() throws BackingStoreException {
            }
            
            @Override
            protected void flushSpi() throws BackingStoreException {
            }
        };
    }
}
