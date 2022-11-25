// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Iterator;
import java.util.Collection;
import java.util.TreeSet;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONHelp
{
    public static String reqStr(final JSONObject obj, final String key) {
        if (obj == null) {
            throw new RuntimeException("Missing required key (" + key + ")");
        }
        final Object val = obj.get(key);
        if (val instanceof String) {
            return (String)val;
        }
        if (val == null) {
            throw new RuntimeException("Missing required key (" + key + ")");
        }
        throw new RuntimeException("Required key (" + key + ") is not a string.");
    }
    
    public static String getStr(final JSONObject obj, final String key, final String def) {
        if (obj == null) {
            return def;
        }
        final Object val = obj.get(key);
        return (String)((val instanceof String) ? val : def);
    }
    
    public static double getDouble(final JSONObject obj, final String key, final double def) {
        if (obj == null) {
            return def;
        }
        final Object val = obj.get(key);
        return (val instanceof Number) ? ((Number)val).doubleValue() : def;
    }
    
    public static int reqInt(final JSONObject obj, final String key) {
        if (obj == null) {
            throw new RuntimeException("Missing required key (" + key + ")");
        }
        final Object val = obj.get(key);
        if (val instanceof Number) {
            return ((Number)val).intValue();
        }
        if (val == null) {
            throw new RuntimeException("Missing required key (" + key + ")");
        }
        throw new RuntimeException("Required key (" + key + ") is not an integer.");
    }
    
    public static int getInt(final JSONObject obj, final String key, final int def) {
        if (obj == null) {
            return def;
        }
        final Object val = obj.get(key);
        return (val instanceof Number) ? ((Number)val).intValue() : def;
    }
    
    public static boolean getBoolean(final JSONObject obj, final String key, final boolean def) {
        if (obj == null) {
            return def;
        }
        final Object val = obj.get(key);
        return (boolean)((val instanceof Boolean) ? val : def);
    }
    
    public static JSONObject getObject(final JSONObject obj, final String key) {
        if (obj == null) {
            return null;
        }
        final Object val = obj.get(key);
        return (val instanceof JSONObject) ? ((JSONObject)val) : null;
    }
    
    public static JSONArray getArray(final JSONObject obj, final String key) {
        if (obj == null) {
            return null;
        }
        final Object val = obj.get(key);
        return (val instanceof JSONArray) ? ((JSONArray)val) : null;
    }
    
    public static ArrayList<String> diffKeys(final JSONObject a, final JSONObject b) {
        final ArrayList<String> list = new ArrayList<String>();
        diff(list, "", a, b);
        return list;
    }
    
    private static void diff(final ArrayList<String> buf, final String path, final JSONObject a, final JSONObject b) {
        final TreeSet<String> keys = new TreeSet<String>();
        keys.addAll((Collection<? extends String>)a.keySet());
        keys.addAll((Collection<? extends String>)b.keySet());
        for (final String key : keys) {
            final Object aa = a.get(key);
            final Object bb = b.get(key);
            if (aa == null != (bb == null) || aa.getClass() != bb.getClass()) {
                buf.add(path + key);
            }
            else if (aa instanceof JSONObject) {
                diff(buf, path + key + "/", (JSONObject)aa, (JSONObject)bb);
            }
            else {
                if (aa instanceof JSONArray) {
                    continue;
                }
                if (!aa.equals(bb)) {
                    continue;
                }
                buf.add(path + key);
            }
        }
    }
}
