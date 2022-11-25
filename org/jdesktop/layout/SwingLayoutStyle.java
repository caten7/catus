// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.layout;

import java.awt.Container;
import javax.swing.JComponent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class SwingLayoutStyle extends LayoutStyle
{
    private static final Method SWING_GET_LAYOUT_STYLE_METHOD;
    private static final Method SWING_GET_PREFERRED_GAP_METHOD;
    private static final Method SWING_GET_CONTAINER_GAP_METHOD;
    private static final Object RELATED_TYPE;
    private static final Object UNRELATED_TYPE;
    private static final Object INDENT_TYPE;
    
    private static final Object layoutStyleTypeToComponentPlacement(final int n) {
        if (n == 0) {
            return SwingLayoutStyle.RELATED_TYPE;
        }
        if (n == 1) {
            return SwingLayoutStyle.UNRELATED_TYPE;
        }
        assert n == 3;
        return SwingLayoutStyle.INDENT_TYPE;
    }
    
    private static final Object getSwingLayoutStyle() {
        try {
            return SwingLayoutStyle.SWING_GET_LAYOUT_STYLE_METHOD.invoke(null, (Object[])null);
        }
        catch (IllegalAccessException ex) {}
        catch (InvocationTargetException ex2) {}
        return null;
    }
    
    public int getPreferredGap(final JComponent component, final JComponent component2, final int n, final int value, final Container container) {
        super.getPreferredGap(component, component2, n, value, container);
        final Object layoutStyleTypeToComponentPlacement = layoutStyleTypeToComponentPlacement(n);
        final Object swingLayoutStyle = getSwingLayoutStyle();
        try {
            return (int)SwingLayoutStyle.SWING_GET_PREFERRED_GAP_METHOD.invoke(swingLayoutStyle, component, component2, layoutStyleTypeToComponentPlacement, new Integer(value), container);
        }
        catch (IllegalAccessException ex) {}
        catch (InvocationTargetException ex2) {}
        return 0;
    }
    
    public int getContainerGap(final JComponent component, final int value, final Container container) {
        super.getContainerGap(component, value, container);
        final Object swingLayoutStyle = getSwingLayoutStyle();
        try {
            return (int)SwingLayoutStyle.SWING_GET_CONTAINER_GAP_METHOD.invoke(swingLayoutStyle, component, new Integer(value), container);
        }
        catch (IllegalAccessException ex) {}
        catch (InvocationTargetException ex2) {}
        return 0;
    }
    
    static {
        Method method = null;
        Method method2 = null;
        Method method3 = null;
        Object value = null;
        Object value2 = null;
        Object value3 = null;
        try {
            Class.forName("javax.swing.LayoutStyle");
            final Class<?> forName = Class.forName("javax.swing.LayoutStyle$ComponentPlacement");
            final Class<?> forName2 = Class.forName("javax.swing.LayoutStyle");
            method = forName2.getMethod("getInstance", (Class[])null);
            method2 = forName2.getMethod("getPreferredGap", JComponent.class, JComponent.class, forName, Integer.TYPE, Container.class);
            method3 = forName2.getMethod("getContainerGap", JComponent.class, Integer.TYPE, Container.class);
            value = forName.getField("RELATED").get(null);
            value2 = forName.getField("UNRELATED").get(null);
            value3 = forName.getField("INDENT").get(null);
        }
        catch (ClassNotFoundException ex) {}
        catch (NoSuchMethodException ex2) {}
        catch (NoSuchFieldException ex3) {}
        catch (IllegalAccessException ex4) {}
        SWING_GET_LAYOUT_STYLE_METHOD = method;
        SWING_GET_PREFERRED_GAP_METHOD = method2;
        SWING_GET_CONTAINER_GAP_METHOD = method3;
        RELATED_TYPE = value;
        UNRELATED_TYPE = value2;
        INDENT_TYPE = value3;
    }
}
