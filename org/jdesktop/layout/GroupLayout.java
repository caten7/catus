// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.layout;

import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JComponent;
import java.util.List;
import java.awt.Component;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.awt.Container;
import java.util.Map;
import java.awt.LayoutManager2;

public class GroupLayout implements LayoutManager2
{
    private static final int MIN_SIZE = 0;
    private static final int PREF_SIZE = 1;
    private static final int MAX_SIZE = 2;
    private static final int SPECIFIC_SIZE = 3;
    private static final int UNSET = Integer.MIN_VALUE;
    public static final int HORIZONTAL = 1;
    public static final int VERTICAL = 2;
    private static final int NO_ALIGNMENT = 0;
    public static final int LEADING = 1;
    public static final int TRAILING = 2;
    public static final int CENTER = 4;
    public static final int BASELINE = 3;
    public static final int DEFAULT_SIZE = -1;
    public static final int PREFERRED_SIZE = -2;
    private boolean autocreatePadding;
    private boolean autocreateContainerPadding;
    private Group horizontalGroup;
    private Group verticalGroup;
    private Map componentInfos;
    private Container host;
    private Set tmpParallelSet;
    private boolean springsChanged;
    private boolean isValid;
    private boolean hasPreferredPaddingSprings;
    private LayoutStyle layoutStyle;
    private boolean honorsVisibility;
    
    private static void checkSize(final int n, final int n2, final int n3, final boolean b) {
        checkResizeType(n, b);
        if (!b && n2 < 0) {
            throw new IllegalArgumentException("Pref must be >= 0");
        }
        if (b) {
            checkResizeType(n2, true);
        }
        checkResizeType(n3, b);
        checkLessThan(n, n2);
        checkLessThan(n2, n3);
    }
    
    private static void checkResizeType(final int n, final boolean b) {
        if (n < 0 && ((b && n != -1 && n != -2) || (!b && n != -2))) {
            throw new IllegalArgumentException("Invalid size");
        }
    }
    
    private static void checkLessThan(final int n, final int n2) {
        if (n >= 0 && n2 >= 0 && n > n2) {
            throw new IllegalArgumentException("Following is not met: min<=pref<=max");
        }
    }
    
    public GroupLayout(final Container host) {
        if (host == null) {
            throw new IllegalArgumentException("Container must be non-null");
        }
        this.honorsVisibility = true;
        this.host = host;
        this.setHorizontalGroup(this.createParallelGroup(1, true));
        this.setVerticalGroup(this.createParallelGroup(1, true));
        this.componentInfos = new HashMap();
        this.tmpParallelSet = new HashSet();
    }
    
    public void setHonorsVisibility(final boolean honorsVisibility) {
        if (this.honorsVisibility != honorsVisibility) {
            this.honorsVisibility = honorsVisibility;
            this.springsChanged = true;
            this.isValid = false;
            this.invalidateHost();
        }
    }
    
    public boolean getHonorsVisibility() {
        return this.honorsVisibility;
    }
    
    public void setHonorsVisibility(final Component component, final Boolean honorsVisibility) {
        if (component == null) {
            throw new IllegalArgumentException("Component must be non-null");
        }
        this.getComponentInfo(component).setHonorsVisibility(honorsVisibility);
        this.springsChanged = true;
        this.isValid = false;
        this.invalidateHost();
    }
    
    public String toString() {
        if (this.springsChanged) {
            this.registerComponents(this.horizontalGroup, 1);
            this.registerComponents(this.verticalGroup, 2);
        }
        final StringBuffer sb = new StringBuffer();
        sb.append("HORIZONTAL\n");
        this.dump(sb, this.horizontalGroup, "  ", 1);
        sb.append("\nVERTICAL\n");
        this.dump(sb, this.verticalGroup, "  ", 2);
        return sb.toString();
    }
    
    private void dump(final StringBuffer sb, final Spring spring, String string, final int n) {
        String str = "";
        String string2 = "";
        if (spring instanceof ComponentSpring) {
            final ComponentSpring componentSpring = (ComponentSpring)spring;
            str = Integer.toString(componentSpring.getOrigin()) + " ";
            final String name = componentSpring.getComponent().getName();
            if (name != null) {
                str = "name=" + name + ", ";
            }
        }
        if (spring instanceof AutopaddingSpring) {
            final AutopaddingSpring autopaddingSpring = (AutopaddingSpring)spring;
            string2 = ", userCreated=" + autopaddingSpring.getUserCreated() + ", matches=" + autopaddingSpring.getMatchDescription();
        }
        sb.append(string + spring.getClass().getName() + " " + Integer.toHexString(spring.hashCode()) + " " + str + ", size=" + spring.getSize() + ", alignment=" + spring.getAlignment() + " prefs=[" + spring.getMinimumSize(n) + " " + spring.getPreferredSize(n) + " " + spring.getMaximumSize(n) + string2 + "]\n");
        if (spring instanceof Group) {
            final List springs = ((Group)spring).springs;
            string += "  ";
            for (int i = 0; i < springs.size(); ++i) {
                this.dump(sb, springs.get(i), string, n);
            }
        }
    }
    
    public void setAutocreateGaps(final boolean autocreatePadding) {
        if (this.autocreatePadding != autocreatePadding) {
            this.autocreatePadding = autocreatePadding;
            this.invalidateHost();
        }
    }
    
    public boolean getAutocreateGaps() {
        return this.autocreatePadding;
    }
    
    public void setAutocreateContainerGaps(final boolean autocreateContainerPadding) {
        if (autocreateContainerPadding != this.autocreateContainerPadding) {
            this.autocreateContainerPadding = autocreateContainerPadding;
            this.horizontalGroup = this.createTopLevelGroup(this.getHorizontalGroup());
            this.verticalGroup = this.createTopLevelGroup(this.getVerticalGroup());
            this.invalidateHost();
        }
    }
    
    public boolean getAutocreateContainerGaps() {
        return this.autocreateContainerPadding;
    }
    
    public void setHorizontalGroup(final Group group) {
        if (group == null) {
            throw new IllegalArgumentException("Group must be non-null");
        }
        this.horizontalGroup = this.createTopLevelGroup(group);
        this.invalidateHost();
    }
    
    public Group getHorizontalGroup() {
        int n = 0;
        if (this.horizontalGroup.springs.size() > 1) {
            n = 1;
        }
        return (Group)this.horizontalGroup.springs.get(n);
    }
    
    public void setVerticalGroup(final Group group) {
        if (group == null) {
            throw new IllegalArgumentException("Group must be non-null");
        }
        this.verticalGroup = this.createTopLevelGroup(group);
        this.invalidateHost();
    }
    
    public Group getVerticalGroup() {
        int n = 0;
        if (this.verticalGroup.springs.size() > 1) {
            n = 1;
        }
        return (Group)this.verticalGroup.springs.get(n);
    }
    
    private Group createTopLevelGroup(final Group group) {
        final SequentialGroup sequentialGroup = this.createSequentialGroup();
        if (this.getAutocreateContainerGaps()) {
            sequentialGroup.addSpring(new ContainerAutopaddingSpring());
            sequentialGroup.add(group);
            sequentialGroup.addSpring(new ContainerAutopaddingSpring());
        }
        else {
            sequentialGroup.add(group);
        }
        return sequentialGroup;
    }
    
    public SequentialGroup createSequentialGroup() {
        return new SequentialGroup();
    }
    
    public ParallelGroup createParallelGroup() {
        return this.createParallelGroup(1);
    }
    
    public ParallelGroup createParallelGroup(final int n) {
        return this.createParallelGroup(n, true);
    }
    
    public ParallelGroup createParallelGroup(final int n, final boolean b) {
        if (n == 3) {
            return new BaselineGroup(b);
        }
        return new ParallelGroup(n, b);
    }
    
    public ParallelGroup createBaselineGroup(final boolean b, final boolean b2) {
        return new BaselineGroup(b, b2);
    }
    
    public void linkSize(final Component[] array) {
        this.linkSize(array, 3);
    }
    
    public void linkSize(final Component[] array, final int n) {
        if (array == null) {
            throw new IllegalArgumentException("Components must be non-null");
        }
        final boolean b = (n & 0x1) == 0x1;
        final boolean b2 = (n & 0x2) == 0x2;
        if (!b2 && !b) {
            throw new IllegalArgumentException("Axis must contain HORIZONTAL or VERTICAL");
        }
        for (int i = array.length - 1; i >= 0; --i) {
            final Component component = array[i];
            if (array[i] == null) {
                throw new IllegalArgumentException("Components must be non-null");
            }
            this.getComponentInfo(component);
        }
        if (b) {
            this.linkSize0(array, 1);
        }
        if (b2) {
            this.linkSize0(array, 2);
        }
        this.invalidateHost();
    }
    
    private void linkSize0(final Component[] array, final int n) {
        final LinkInfo linkInfo = this.getComponentInfo(array[array.length - 1]).getLinkInfo(n);
        for (int i = array.length - 2; i >= 0; --i) {
            linkInfo.add(this.getComponentInfo(array[i]));
        }
    }
    
    public void replace(final Component comp, final Component component) {
        if (comp == null || component == null) {
            throw new IllegalArgumentException("Components must be non-null");
        }
        if (this.springsChanged) {
            this.registerComponents(this.horizontalGroup, 1);
            this.registerComponents(this.verticalGroup, 2);
        }
        final ComponentInfo componentInfo = this.componentInfos.remove(comp);
        if (componentInfo == null) {
            throw new IllegalArgumentException("Component must already exist");
        }
        this.host.remove(comp);
        if (component.getParent() != this.host) {
            this.host.add(component);
        }
        componentInfo.setComponent(component);
        this.componentInfos.put(component, componentInfo);
        this.invalidateHost();
    }
    
    public void setLayoutStyle(final LayoutStyle layoutStyle) {
        this.layoutStyle = layoutStyle;
        this.invalidateHost();
    }
    
    public LayoutStyle getLayoutStyle() {
        return this.layoutStyle;
    }
    
    private LayoutStyle getLayoutStyle0() {
        LayoutStyle layoutStyle = this.getLayoutStyle();
        if (layoutStyle == null) {
            layoutStyle = LayoutStyle.getSharedInstance();
        }
        return layoutStyle;
    }
    
    private void invalidateHost() {
        if (this.host instanceof JComponent) {
            ((JComponent)this.host).revalidate();
        }
        else {
            this.host.invalidate();
        }
        this.host.repaint();
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public void removeLayoutComponent(final Component component) {
        final ComponentInfo componentInfo = this.componentInfos.remove(component);
        if (componentInfo != null) {
            componentInfo.dispose();
            this.springsChanged = true;
            this.isValid = false;
        }
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        this.checkParent(container);
        this.prepare(1);
        return this.adjustSize(this.horizontalGroup.getPreferredSize(1), this.verticalGroup.getPreferredSize(2));
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        this.checkParent(container);
        this.prepare(0);
        return this.adjustSize(this.horizontalGroup.getMinimumSize(1), this.verticalGroup.getMinimumSize(2));
    }
    
    public void layoutContainer(final Container container) {
        this.prepare(3);
        final Insets insets = container.getInsets();
        final int n = container.getWidth() - insets.left - insets.right;
        final int n2 = container.getHeight() - insets.top - insets.bottom;
        final boolean leftToRight = this.isLeftToRight();
        if (this.getAutocreateGaps() || this.getAutocreateContainerGaps() || this.hasPreferredPaddingSprings) {
            this.calculateAutopadding(this.horizontalGroup, 1, 3, 0, n);
            this.calculateAutopadding(this.verticalGroup, 2, 3, 0, n2);
        }
        this.horizontalGroup.setSize(1, 0, n);
        this.verticalGroup.setSize(2, 0, n2);
        for (final ComponentInfo componentInfo : this.componentInfos.values()) {
            componentInfo.getComponent();
            componentInfo.setBounds(insets, n, leftToRight);
        }
    }
    
    public void addLayoutComponent(final Component component, final Object o) {
    }
    
    public Dimension maximumLayoutSize(final Container container) {
        this.checkParent(container);
        this.prepare(2);
        return this.adjustSize(this.horizontalGroup.getMaximumSize(1), this.verticalGroup.getMaximumSize(2));
    }
    
    public float getLayoutAlignmentX(final Container container) {
        this.checkParent(container);
        return 0.5f;
    }
    
    public float getLayoutAlignmentY(final Container container) {
        this.checkParent(container);
        return 0.5f;
    }
    
    public void invalidateLayout(final Container container) {
        this.checkParent(container);
        synchronized (container.getTreeLock()) {
            this.isValid = false;
        }
    }
    
    private void prepare(final int n) {
        boolean b = false;
        if (!this.isValid) {
            this.isValid = true;
            this.horizontalGroup.setSize(1, Integer.MIN_VALUE, Integer.MIN_VALUE);
            this.verticalGroup.setSize(2, Integer.MIN_VALUE, Integer.MIN_VALUE);
            for (final ComponentInfo componentInfo : this.componentInfos.values()) {
                if (componentInfo.updateVisibility()) {
                    b = true;
                }
                componentInfo.clearCachedSize();
            }
        }
        if (this.springsChanged) {
            this.registerComponents(this.horizontalGroup, 1);
            this.registerComponents(this.verticalGroup, 2);
        }
        if (this.springsChanged || b) {
            this.checkComponents();
            this.horizontalGroup.removeAutopadding();
            this.verticalGroup.removeAutopadding();
            if (this.getAutocreateGaps()) {
                this.insertAutopadding(true);
            }
            else if (this.hasPreferredPaddingSprings || this.getAutocreateContainerGaps()) {
                this.insertAutopadding(false);
            }
            this.springsChanged = false;
        }
        if (n != 3 && (this.getAutocreateGaps() || this.getAutocreateContainerGaps() || this.hasPreferredPaddingSprings)) {
            this.calculateAutopadding(this.horizontalGroup, 1, n, 0, 0);
            this.calculateAutopadding(this.verticalGroup, 2, n, 0, 0);
        }
    }
    
    private void calculateAutopadding(final Group group, final int n, final int n2, final int n3, int n4) {
        group.unsetAutopadding();
        switch (n2) {
            case 0: {
                n4 = group.getMinimumSize(n);
                break;
            }
            case 1: {
                n4 = group.getPreferredSize(n);
                break;
            }
            case 2: {
                n4 = group.getMaximumSize(n);
                break;
            }
        }
        group.setSize(n, n3, n4);
        group.calculateAutopadding(n);
    }
    
    private void checkComponents() {
        for (final ComponentInfo componentInfo : this.componentInfos.values()) {
            if (componentInfo.horizontalSpring == null) {
                throw new IllegalStateException(componentInfo.component + " is not attached to a horizontal group");
            }
            if (componentInfo.verticalSpring == null) {
                throw new IllegalStateException(componentInfo.component + " is not attached to a vertical group");
            }
        }
    }
    
    private void registerComponents(final Group group, final int n) {
        final List springs = group.springs;
        for (int i = springs.size() - 1; i >= 0; --i) {
            final Spring spring = springs.get(i);
            if (spring instanceof ComponentSpring) {
                ((ComponentSpring)spring).installIfNecessary(n);
            }
            else if (spring instanceof Group) {
                this.registerComponents((Group)spring, n);
            }
        }
    }
    
    private Dimension adjustSize(final int n, final int n2) {
        final Insets insets = this.host.getInsets();
        return new Dimension(n + insets.left + insets.right, n2 + insets.top + insets.bottom);
    }
    
    private void checkParent(final Container container) {
        if (container != this.host) {
            throw new IllegalArgumentException("GroupLayout can only be used with one Container at a time");
        }
    }
    
    private ComponentInfo getComponentInfo(final Component comp) {
        ComponentInfo componentInfo = this.componentInfos.get(comp);
        if (componentInfo == null) {
            componentInfo = new ComponentInfo(comp);
            this.componentInfos.put(comp, componentInfo);
            if (comp.getParent() != this.host) {
                this.host.add(comp);
            }
        }
        return componentInfo;
    }
    
    private void insertAutopadding(final boolean b) {
        this.horizontalGroup.insertAutopadding(1, new ArrayList(1), new ArrayList(1), new ArrayList(1), new ArrayList(1), b);
        this.verticalGroup.insertAutopadding(2, new ArrayList(1), new ArrayList(1), new ArrayList(1), new ArrayList(1), b);
    }
    
    private boolean areParallelSiblings(final Component component, final Component component2, final int n) {
        final ComponentInfo componentInfo = this.getComponentInfo(component);
        final ComponentInfo componentInfo2 = this.getComponentInfo(component2);
        ComponentSpring componentSpring;
        ComponentSpring componentSpring2;
        if (n == 1) {
            componentSpring = componentInfo.horizontalSpring;
            componentSpring2 = componentInfo2.horizontalSpring;
        }
        else {
            componentSpring = componentInfo.verticalSpring;
            componentSpring2 = componentInfo2.verticalSpring;
        }
        final Set tmpParallelSet = this.tmpParallelSet;
        tmpParallelSet.clear();
        for (Spring spring = componentSpring.getParent(); spring != null; spring = spring.getParent()) {
            tmpParallelSet.add(spring);
        }
        for (Spring spring2 = componentSpring2.getParent(); spring2 != null; spring2 = spring2.getParent()) {
            if (tmpParallelSet.contains(spring2)) {
                tmpParallelSet.clear();
                while (spring2 != null) {
                    if (spring2 instanceof ParallelGroup) {
                        return true;
                    }
                    spring2 = spring2.getParent();
                }
                return false;
            }
        }
        tmpParallelSet.clear();
        return false;
    }
    
    private boolean isLeftToRight() {
        return this.host.getComponentOrientation().isLeftToRight();
    }
    
    abstract class Spring
    {
        private int size;
        private int min;
        private int max;
        private int pref;
        private Spring parent;
        private int alignment;
        
        Spring() {
            final int min = Integer.MIN_VALUE;
            this.max = min;
            this.pref = min;
            this.min = min;
        }
        
        abstract int calculateMinimumSize(final int p0);
        
        abstract int calculatePreferredSize(final int p0);
        
        abstract int calculateMaximumSize(final int p0);
        
        void setParent(final Spring parent) {
            this.parent = parent;
        }
        
        Spring getParent() {
            return this.parent;
        }
        
        void setAlignment(final int alignment) {
            this.alignment = alignment;
        }
        
        int getAlignment() {
            return this.alignment;
        }
        
        final int getMinimumSize(final int n) {
            if (this.min == Integer.MIN_VALUE) {
                this.min = this.constrain(this.calculateMinimumSize(n));
            }
            return this.min;
        }
        
        final int getPreferredSize(final int n) {
            if (this.pref == Integer.MIN_VALUE) {
                this.pref = this.constrain(this.calculatePreferredSize(n));
            }
            return this.pref;
        }
        
        final int getMaximumSize(final int n) {
            if (this.max == Integer.MIN_VALUE) {
                this.max = this.constrain(this.calculateMaximumSize(n));
            }
            return this.max;
        }
        
        void unset() {
            final int n = Integer.MIN_VALUE;
            this.max = n;
            this.pref = n;
            this.min = n;
            this.size = n;
        }
        
        void setSize(final int n, final int n2, final int size) {
            this.size = size;
            if (size == Integer.MIN_VALUE) {
                this.unset();
            }
        }
        
        int getSize() {
            return this.size;
        }
        
        int constrain(final int a) {
            return Math.min(a, 32767);
        }
        
        int getBaseline() {
            return -1;
        }
        
        int getBaselineResizeBehavior() {
            return 4;
        }
        
        final boolean isResizable(final int n) {
            final int minimumSize = this.getMinimumSize(n);
            final int preferredSize = this.getPreferredSize(n);
            return minimumSize != preferredSize || preferredSize != this.getMaximumSize(n);
        }
        
        abstract boolean willHaveZeroSize(final boolean p0);
    }
    
    public abstract class Group extends Spring
    {
        List springs;
        
        Group() {
            this.springs = new ArrayList();
        }
        
        int indexOf(final Spring spring) {
            return this.springs.indexOf(spring);
        }
        
        Group addSpring(final Spring spring) {
            this.springs.add(spring);
            spring.setParent(this);
            if (!(spring instanceof AutopaddingSpring) || !((AutopaddingSpring)spring).getUserCreated()) {
                GroupLayout.this.springsChanged = true;
            }
            return this;
        }
        
        void setSize(final int n, final int n2, final int n3) {
            super.setSize(n, n2, n3);
            if (n3 == Integer.MIN_VALUE) {
                for (int i = this.springs.size() - 1; i >= 0; --i) {
                    this.getSpring(i).setSize(n, n2, n3);
                }
            }
            else {
                this.setValidSize(n, n2, n3);
            }
        }
        
        abstract void setValidSize(final int p0, final int p1, final int p2);
        
        int calculateMinimumSize(final int n) {
            return this.calculateSize(n, 0);
        }
        
        int calculatePreferredSize(final int n) {
            return this.calculateSize(n, 1);
        }
        
        int calculateMaximumSize(final int n) {
            return this.calculateSize(n, 2);
        }
        
        abstract int operator(final int p0, final int p1);
        
        int calculateSize(final int n, final int n2) {
            final int size = this.springs.size();
            if (size == 0) {
                return 0;
            }
            if (size == 1) {
                return this.getSpringSize(this.getSpring(0), n, n2);
            }
            int n3 = this.constrain(this.operator(this.getSpringSize(this.getSpring(0), n, n2), this.getSpringSize(this.getSpring(1), n, n2)));
            for (int i = 2; i < size; ++i) {
                n3 = this.constrain(this.operator(n3, this.getSpringSize(this.getSpring(i), n, n2)));
            }
            return n3;
        }
        
        Spring getSpring(final int n) {
            return this.springs.get(n);
        }
        
        int getSpringSize(final Spring spring, final int n, final int n2) {
            switch (n2) {
                case 0: {
                    return spring.getMinimumSize(n);
                }
                case 1: {
                    return spring.getPreferredSize(n);
                }
                case 2: {
                    return spring.getMaximumSize(n);
                }
                default: {
                    assert false;
                    return 0;
                }
            }
        }
        
        abstract void insertAutopadding(final int p0, final List p1, final List p2, final List p3, final List p4, final boolean p5);
        
        void removeAutopadding() {
            this.unset();
            for (int i = this.springs.size() - 1; i >= 0; --i) {
                final Spring spring = this.springs.get(i);
                if (spring instanceof AutopaddingSpring) {
                    if (((AutopaddingSpring)spring).getUserCreated()) {
                        ((AutopaddingSpring)spring).reset();
                    }
                    else {
                        this.springs.remove(i);
                    }
                }
                else if (spring instanceof Group) {
                    ((Group)spring).removeAutopadding();
                }
            }
        }
        
        void unsetAutopadding() {
            this.unset();
            for (int i = this.springs.size() - 1; i >= 0; --i) {
                final Spring spring = this.springs.get(i);
                if (spring instanceof AutopaddingSpring) {
                    ((AutopaddingSpring)spring).unset();
                }
                else if (spring instanceof Group) {
                    ((Group)spring).unsetAutopadding();
                }
            }
        }
        
        void calculateAutopadding(final int n) {
            for (int i = this.springs.size() - 1; i >= 0; --i) {
                final Spring spring = this.springs.get(i);
                if (spring instanceof AutopaddingSpring) {
                    spring.unset();
                    ((AutopaddingSpring)spring).calculatePadding(n);
                }
                else if (spring instanceof Group) {
                    ((Group)spring).calculateAutopadding(n);
                }
            }
            this.unset();
        }
        
        boolean willHaveZeroSize(final boolean b) {
            for (int i = this.springs.size() - 1; i >= 0; --i) {
                if (!((Spring)this.springs.get(i)).willHaveZeroSize(b)) {
                    return false;
                }
            }
            return true;
        }
    }
    
    public class SequentialGroup extends Group
    {
        private Spring baselineSpring;
        
        SequentialGroup() {
        }
        
        public SequentialGroup add(final Group group) {
            return (SequentialGroup)this.addSpring(group);
        }
        
        public SequentialGroup add(final boolean b, final Group baselineSpring) {
            this.add(baselineSpring);
            if (b) {
                this.baselineSpring = baselineSpring;
            }
            return this;
        }
        
        public SequentialGroup add(final Component component) {
            return this.add(component, -1, -1, -1);
        }
        
        public SequentialGroup add(final boolean b, final Component component) {
            this.add(component);
            if (b) {
                this.baselineSpring = this.getSpring(this.springs.size() - 1);
            }
            return this;
        }
        
        public SequentialGroup add(final Component component, final int n, final int n2, final int n3) {
            return (SequentialGroup)this.addSpring(new ComponentSpring(component, n, n2, n3));
        }
        
        public SequentialGroup add(final boolean b, final Component component, final int n, final int n2, final int n3) {
            this.add(component, n, n2, n3);
            if (b) {
                this.baselineSpring = this.getSpring(this.springs.size() - 1);
            }
            return this;
        }
        
        public SequentialGroup add(final int n) {
            return this.add(n, n, n);
        }
        
        public SequentialGroup add(final int n, final int n2, final int n3) {
            return (SequentialGroup)this.addSpring(new GapSpring(n, n2, n3));
        }
        
        public SequentialGroup addPreferredGap(final JComponent component, final JComponent component2, final int n) {
            return this.addPreferredGap(component, component2, n, false);
        }
        
        public SequentialGroup addPreferredGap(final JComponent component, final JComponent component2, final int n, final boolean b) {
            if (n != 0 && n != 1 && n != 3) {
                throw new IllegalArgumentException("Invalid type argument");
            }
            if (component == null || component2 == null) {
                throw new IllegalArgumentException("Components must be non-null");
            }
            return (SequentialGroup)this.addSpring(new PaddingSpring(component, component2, n, b));
        }
        
        public SequentialGroup addPreferredGap(final int n) {
            return this.addPreferredGap(n, -1, -1);
        }
        
        public SequentialGroup addPreferredGap(final int n, final int n2, final int n3) {
            if (n != 0 && n != 1) {
                throw new IllegalArgumentException("Padding type must be one of Padding.RELATED or Padding.UNRELATED");
            }
            if ((n2 < 0 && n2 != -1 && n2 != -2) || (n3 < 0 && n3 != -1 && n3 != -2) || (n2 >= 0 && n3 >= 0 && n2 > n3)) {
                throw new IllegalArgumentException("Pref and max must be either DEFAULT_SIZE, PREFERRED_SIZE, or >= 0 and pref <= max");
            }
            GroupLayout.this.hasPreferredPaddingSprings = true;
            return (SequentialGroup)this.addSpring(new AutopaddingSpring(n, n2, n3));
        }
        
        public SequentialGroup addContainerGap() {
            return this.addContainerGap(-1, -1);
        }
        
        public SequentialGroup addContainerGap(final int n, final int n2) {
            if ((n < 0 && n != -1) || (n2 < 0 && n2 != -1 && n2 != -2) || (n >= 0 && n2 >= 0 && n > n2)) {
                throw new IllegalArgumentException("Pref and max must be either DEFAULT_VALUE or >= 0 and pref <= max");
            }
            GroupLayout.this.hasPreferredPaddingSprings = true;
            return (SequentialGroup)this.addSpring(new ContainerAutopaddingSpring(n, n2));
        }
        
        int operator(final int n, final int n2) {
            return this.constrain(n) + this.constrain(n2);
        }
        
        void setValidSize(final int n, int n2, final int a) {
            if (a == this.getPreferredSize(n)) {
                for (int i = 0; i < this.springs.size(); ++i) {
                    final Spring spring = this.getSpring(i);
                    final int preferredSize = spring.getPreferredSize(n);
                    spring.setSize(n, n2, preferredSize);
                    n2 += preferredSize;
                }
            }
            else if (this.springs.size() == 1) {
                final Spring spring2 = this.getSpring(0);
                spring2.setSize(n, n2, Math.min(Math.max(a, spring2.getMinimumSize(n)), spring2.getMaximumSize(n)));
            }
            else if (this.springs.size() > 1) {
                this.setValidSizeNotPreferred(n, n2, a);
            }
        }
        
        private void setValidSizeNotPreferred(final int n, int n2, final int n3) {
            int n4 = n3 - this.getPreferredSize(n);
            assert n4 != 0;
            final boolean b = n4 < 0;
            final int size = this.springs.size();
            if (b) {
                n4 *= -1;
            }
            final List buildResizableList = this.buildResizableList(n, b);
            final int size2 = buildResizableList.size();
            if (size2 > 0) {
                int a = n4 / size2;
                int n5 = n4 - a * size2;
                final int[] array = new int[size];
                final int n6 = b ? -1 : 1;
                for (int i = 0; i < size2; ++i) {
                    final SpringDelta springDelta = buildResizableList.get(i);
                    if (i + 1 == size2) {
                        a += n5;
                    }
                    springDelta.delta = Math.min(a, springDelta.delta);
                    n4 -= springDelta.delta;
                    if (springDelta.delta != a && i + 1 < size2) {
                        a = n4 / (size2 - i - 1);
                        n5 = n4 - a * (size2 - i - 1);
                    }
                    array[springDelta.index] = n6 * springDelta.delta;
                }
                for (int j = 0; j < size; ++j) {
                    final Spring spring = this.getSpring(j);
                    final int n7 = spring.getPreferredSize(n) + array[j];
                    spring.setSize(n, n2, n7);
                    n2 += n7;
                }
            }
            else {
                for (int k = 0; k < size; ++k) {
                    final Spring spring2 = this.getSpring(k);
                    int n8;
                    if (b) {
                        n8 = spring2.getMinimumSize(n);
                    }
                    else {
                        n8 = spring2.getMaximumSize(n);
                    }
                    spring2.setSize(n, n2, n8);
                    n2 += n8;
                }
            }
        }
        
        private List buildResizableList(final int n, final boolean b) {
            final int size = this.springs.size();
            final ArrayList list = new ArrayList<SpringDelta>(size);
            for (int i = 0; i < size; ++i) {
                final Spring spring = this.getSpring(i);
                int n2;
                if (b) {
                    n2 = spring.getPreferredSize(n) - spring.getMinimumSize(n);
                }
                else {
                    n2 = spring.getMaximumSize(n) - spring.getPreferredSize(n);
                }
                if (n2 > 0) {
                    list.add(new SpringDelta(i, n2));
                }
            }
            Collections.sort((List<Comparable>)list);
            return list;
        }
        
        private int indexOfNextNonZeroSpring(int i, final boolean b) {
            while (i < this.springs.size()) {
                if (!this.springs.get(i).willHaveZeroSize(b)) {
                    return i;
                }
                ++i;
            }
            return i;
        }
        
        void insertAutopadding(final int n, final List c, final List list, final List c2, final List list2, final boolean b) {
            final ArrayList<Object> list3 = new ArrayList<Object>(c);
            final ArrayList<AutopaddingSpring> list4 = new ArrayList<AutopaddingSpring>(1);
            final ArrayList sources = new ArrayList<ComponentSpring>(c2);
            List<? extends ComponentSpring> list5 = null;
            int i = 0;
            while (i < this.springs.size()) {
                final Spring spring = this.getSpring(i);
                if (spring instanceof AutopaddingSpring) {
                    if (list3.size() == 0) {
                        final AutopaddingSpring autopaddingSpring = (AutopaddingSpring)spring;
                        autopaddingSpring.setSources(sources);
                        sources.clear();
                        final int indexOfNextNonZeroSpring = this.indexOfNextNonZeroSpring(i + 1, true);
                        if (indexOfNextNonZeroSpring == this.springs.size()) {
                            if (!(autopaddingSpring instanceof ContainerAutopaddingSpring)) {
                                list.add(autopaddingSpring);
                            }
                        }
                        else {
                            list3.clear();
                            list3.add(autopaddingSpring);
                        }
                        i = indexOfNextNonZeroSpring;
                    }
                    else {
                        i = this.indexOfNextNonZeroSpring(i + 1, true);
                    }
                }
                else if (sources.size() > 0 && b) {
                    this.springs.add(i, new AutopaddingSpring());
                }
                else if (spring instanceof ComponentSpring) {
                    final ComponentSpring componentSpring = (ComponentSpring)spring;
                    if (!componentSpring.isVisible()) {
                        ++i;
                    }
                    else {
                        for (int j = 0; j < list3.size(); ++j) {
                            list3.get(j).addTarget(componentSpring, n);
                        }
                        sources.clear();
                        list3.clear();
                        final int indexOfNextNonZeroSpring2 = this.indexOfNextNonZeroSpring(i + 1, false);
                        if (indexOfNextNonZeroSpring2 == this.springs.size()) {
                            list2.add(componentSpring);
                        }
                        else {
                            sources.add(componentSpring);
                        }
                        i = indexOfNextNonZeroSpring2;
                    }
                }
                else if (spring instanceof Group) {
                    if (list5 == null) {
                        list5 = new ArrayList<ComponentSpring>(1);
                    }
                    else {
                        list5.clear();
                    }
                    list4.clear();
                    ((Group)spring).insertAutopadding(n, list3, list4, sources, list5, b);
                    sources.clear();
                    list3.clear();
                    final int indexOfNextNonZeroSpring3 = this.indexOfNextNonZeroSpring(i + 1, list5.size() == 0);
                    if (indexOfNextNonZeroSpring3 == this.springs.size()) {
                        list2.addAll(list5);
                        list.addAll(list4);
                    }
                    else {
                        sources.addAll(list5);
                        list3.addAll(list4);
                    }
                    i = indexOfNextNonZeroSpring3;
                }
                else {
                    list3.clear();
                    sources.clear();
                    ++i;
                }
            }
        }
        
        int getBaseline() {
            if (this.baselineSpring != null) {
                final int baseline = this.baselineSpring.getBaseline();
                if (baseline >= 0) {
                    int n = 0;
                    for (int i = 0; i < this.springs.size(); ++i) {
                        final Spring spring = this.getSpring(i);
                        if (spring == this.baselineSpring) {
                            return n + baseline;
                        }
                        n += spring.getPreferredSize(2);
                    }
                }
            }
            return -1;
        }
        
        int getBaselineResizeBehavior() {
            if (this.isResizable(2)) {
                if (!this.baselineSpring.isResizable(2)) {
                    boolean b = false;
                    for (int i = 0; i < this.springs.size(); ++i) {
                        final Spring spring = this.getSpring(i);
                        if (spring == this.baselineSpring) {
                            break;
                        }
                        if (spring.isResizable(2)) {
                            b = true;
                            break;
                        }
                    }
                    boolean b2 = false;
                    for (int j = this.springs.size() - 1; j >= 0; --j) {
                        final Spring spring2 = this.getSpring(j);
                        if (spring2 == this.baselineSpring) {
                            break;
                        }
                        if (spring2.isResizable(2)) {
                            b2 = true;
                            break;
                        }
                    }
                    if (b && !b2) {
                        return 2;
                    }
                    if (!b && b2) {
                        return 1;
                    }
                }
                else {
                    final int baselineResizeBehavior = this.baselineSpring.getBaselineResizeBehavior();
                    if (baselineResizeBehavior == 1) {
                        for (int k = 0; k < this.springs.size(); ++k) {
                            final Spring spring3 = this.getSpring(k);
                            if (spring3 == this.baselineSpring) {
                                return 1;
                            }
                            if (spring3.isResizable(2)) {
                                return 4;
                            }
                        }
                    }
                    else if (baselineResizeBehavior == 2) {
                        for (int l = this.springs.size() - 1; l >= 0; --l) {
                            final Spring spring4 = this.getSpring(l);
                            if (spring4 == this.baselineSpring) {
                                return 2;
                            }
                            if (spring4.isResizable(2)) {
                                return 4;
                            }
                        }
                    }
                }
                return 4;
            }
            return 1;
        }
    }
    
    private static final class SpringDelta implements Comparable
    {
        public final int index;
        public int delta;
        
        public SpringDelta(final int index, final int delta) {
            this.index = index;
            this.delta = delta;
        }
        
        public int compareTo(final Object o) {
            return this.delta - ((SpringDelta)o).delta;
        }
        
        public String toString() {
            return super.toString() + "[index=" + this.index + ", delta=" + this.delta + "]";
        }
    }
    
    public class ParallelGroup extends Group
    {
        private final int childAlignment;
        private final boolean resizable;
        
        ParallelGroup(final int childAlignment, final boolean resizable) {
            this.childAlignment = childAlignment;
            this.resizable = resizable;
        }
        
        public ParallelGroup add(final Group group) {
            return (ParallelGroup)this.addSpring(group);
        }
        
        public ParallelGroup add(final Component component) {
            return this.add(component, -1, -1, -1);
        }
        
        public ParallelGroup add(final Component component, final int n, final int n2, final int n3) {
            return (ParallelGroup)this.addSpring(new ComponentSpring(component, n, n2, n3));
        }
        
        public ParallelGroup add(final int n) {
            return this.add(n, n, n);
        }
        
        public ParallelGroup add(final int n, final int n2, final int n3) {
            return (ParallelGroup)this.addSpring(new GapSpring(n, n2, n3));
        }
        
        public ParallelGroup add(final int alignment, final Group group) {
            this.checkChildAlignment(alignment);
            group.setAlignment(alignment);
            return (ParallelGroup)this.addSpring(group);
        }
        
        public ParallelGroup add(final int n, final Component component) {
            return this.add(n, component, -1, -1, -1);
        }
        
        public ParallelGroup add(final int alignment, final Component component, final int n, final int n2, final int n3) {
            this.checkChildAlignment(alignment);
            final ComponentSpring componentSpring = new ComponentSpring(component, n, n2, n3);
            componentSpring.setAlignment(alignment);
            return (ParallelGroup)this.addSpring(componentSpring);
        }
        
        boolean isResizable() {
            return this.resizable;
        }
        
        int operator(final int a, final int b) {
            return Math.max(a, b);
        }
        
        int calculateMinimumSize(final int n) {
            if (!this.isResizable()) {
                return this.getPreferredSize(n);
            }
            return super.calculateMinimumSize(n);
        }
        
        int calculateMaximumSize(final int n) {
            if (!this.isResizable()) {
                return this.getPreferredSize(n);
            }
            return super.calculateMaximumSize(n);
        }
        
        void setValidSize(final int n, final int n2, final int n3) {
            for (int i = 0; i < this.springs.size(); ++i) {
                this.setChildSize(this.getSpring(i), n, n2, n3);
            }
        }
        
        void setChildSize(final Spring spring, final int n, final int n2, final int b) {
            int n3 = spring.getAlignment();
            final int min = Math.min(Math.max(spring.getMinimumSize(n), b), spring.getMaximumSize(n));
            if (n3 == 0) {
                n3 = this.childAlignment;
            }
            switch (n3) {
                case 2: {
                    spring.setSize(n, n2 + b - min, min);
                    break;
                }
                case 4: {
                    spring.setSize(n, n2 + (b - min) / 2, min);
                    break;
                }
                default: {
                    spring.setSize(n, n2, min);
                    break;
                }
            }
        }
        
        void insertAutopadding(final int n, final List list, final List list2, final List sources, final List list3, final boolean b) {
            for (int i = 0; i < this.springs.size(); ++i) {
                final Spring spring = this.getSpring(i);
                if (spring instanceof ComponentSpring) {
                    if (((ComponentSpring)spring).isVisible()) {
                        for (int j = 0; j < list.size(); ++j) {
                            list.get(j).addTarget((ComponentSpring)spring, n);
                        }
                        list3.add(spring);
                    }
                }
                else if (spring instanceof Group) {
                    ((Group)spring).insertAutopadding(n, list, list2, sources, list3, b);
                }
                else if (spring instanceof AutopaddingSpring) {
                    ((AutopaddingSpring)spring).setSources(sources);
                    list2.add(spring);
                }
            }
        }
        
        private void checkChildAlignment(final int n) {
            if (!(this instanceof BaselineGroup) && n == 3) {
                throw new IllegalArgumentException("Alignment must be one of:LEADING, TRAILING or CENTER");
            }
            if (n != 4 && n != 3 && n != 1 && n != 2) {
                throw new IllegalArgumentException("Alignment must be one of:LEADING, TRAILING or CENTER");
            }
        }
    }
    
    private class BaselineGroup extends ParallelGroup
    {
        private boolean allSpringsHaveBaseline;
        private int prefAscent;
        private int prefDescent;
        private boolean baselineAnchorSet;
        private boolean baselineAnchoredToTop;
        private boolean calcedBaseline;
        
        BaselineGroup(final boolean b) {
            super(1, b);
            final int n = -1;
            this.prefDescent = n;
            this.prefAscent = n;
            this.calcedBaseline = false;
        }
        
        BaselineGroup(final GroupLayout groupLayout, final boolean b, final boolean baselineAnchoredToTop) {
            this(groupLayout, b);
            this.baselineAnchoredToTop = baselineAnchoredToTop;
            this.baselineAnchorSet = true;
        }
        
        void unset() {
            super.unset();
            final int n = -1;
            this.prefDescent = n;
            this.prefAscent = n;
            this.calcedBaseline = false;
        }
        
        void setValidSize(final int n, final int n2, final int n3) {
            this.checkAxis(n);
            if (this.prefAscent == -1) {
                super.setValidSize(n, n2, n3);
            }
            else {
                this.baselineLayout(n2, n3);
            }
        }
        
        int calculateSize(final int n, final int n2) {
            this.checkAxis(n);
            if (!this.calcedBaseline) {
                this.calculateBaselineAndResizeBehavior();
            }
            if (n2 == 0) {
                return this.calculateMinSize();
            }
            if (n2 == 2) {
                return this.calculateMaxSize();
            }
            if (this.allSpringsHaveBaseline) {
                return this.prefAscent + this.prefDescent;
            }
            return Math.max(this.prefAscent + this.prefDescent, super.calculateSize(n, n2));
        }
        
        private void calculateBaselineAndResizeBehavior() {
            this.prefAscent = 0;
            this.prefDescent = 0;
            int n = 0;
            int n2 = 0;
            for (int i = this.springs.size() - 1; i >= 0; --i) {
                final Spring spring = this.getSpring(i);
                if (spring.getAlignment() == 0 || spring.getAlignment() == 3) {
                    final int baseline = spring.getBaseline();
                    if (baseline >= 0) {
                        if (spring.isResizable(2)) {
                            final int baselineResizeBehavior = spring.getBaselineResizeBehavior();
                            if (n2 == 0) {
                                n2 = baselineResizeBehavior;
                            }
                            else if (baselineResizeBehavior != n2) {
                                n2 = 1;
                            }
                        }
                        this.prefAscent = Math.max(this.prefAscent, baseline);
                        this.prefDescent = Math.max(this.prefDescent, spring.getPreferredSize(2) - baseline);
                        ++n;
                    }
                }
            }
            if (!this.baselineAnchorSet) {
                if (n2 == 2) {
                    this.baselineAnchoredToTop = false;
                }
                else {
                    this.baselineAnchoredToTop = true;
                }
            }
            this.allSpringsHaveBaseline = (n == this.springs.size());
            this.calcedBaseline = true;
        }
        
        private int calculateMaxSize() {
            int a = this.prefAscent;
            int a2 = this.prefDescent;
            int max = 0;
            for (int i = this.springs.size() - 1; i >= 0; --i) {
                final Spring spring = this.getSpring(i);
                final int maximumSize = spring.getMaximumSize(2);
                final int baseline;
                if ((spring.getAlignment() == 0 || spring.getAlignment() == 3) && (baseline = spring.getBaseline()) >= 0) {
                    final int preferredSize = spring.getPreferredSize(2);
                    if (preferredSize != maximumSize) {
                        switch (spring.getBaselineResizeBehavior()) {
                            case 1: {
                                if (this.baselineAnchoredToTop) {
                                    a2 = Math.max(a2, maximumSize - baseline);
                                    break;
                                }
                                break;
                            }
                            case 2: {
                                if (!this.baselineAnchoredToTop) {
                                    a = Math.max(a, maximumSize - preferredSize + baseline);
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
                else {
                    max = Math.max(max, maximumSize);
                }
            }
            return Math.max(max, a + a2);
        }
        
        private int calculateMinSize() {
            int b = 0;
            int b2 = 0;
            int max = 0;
            if (this.baselineAnchoredToTop) {
                b = this.prefAscent;
            }
            else {
                b2 = this.prefDescent;
            }
            for (int i = this.springs.size() - 1; i >= 0; --i) {
                final Spring spring = this.getSpring(i);
                final int minimumSize = spring.getMinimumSize(2);
                final int baseline;
                if ((spring.getAlignment() == 0 || spring.getAlignment() == 3) && (baseline = spring.getBaseline()) >= 0) {
                    final int preferredSize = spring.getPreferredSize(2);
                    switch (spring.getBaselineResizeBehavior()) {
                        case 1: {
                            if (this.baselineAnchoredToTop) {
                                b2 = Math.max(minimumSize - baseline, b2);
                                break;
                            }
                            b = Math.max(baseline, b);
                            break;
                        }
                        case 2: {
                            if (!this.baselineAnchoredToTop) {
                                b = Math.max(baseline - (preferredSize - minimumSize), b);
                                break;
                            }
                            b2 = Math.max(preferredSize - baseline, b2);
                            break;
                        }
                        default: {
                            b = Math.max(baseline, b);
                            b2 = Math.max(preferredSize - baseline, b2);
                            break;
                        }
                    }
                }
                else {
                    max = Math.max(max, minimumSize);
                }
            }
            return Math.max(max, b + b2);
        }
        
        private void baselineLayout(final int n, final int n2) {
            int prefAscent;
            int prefDescent;
            if (this.baselineAnchoredToTop) {
                prefAscent = this.prefAscent;
                prefDescent = n2 - prefAscent;
            }
            else {
                prefAscent = n2 - this.prefDescent;
                prefDescent = this.prefDescent;
            }
            for (int i = this.springs.size() - 1; i >= 0; --i) {
                final Spring spring = this.getSpring(i);
                final int alignment = spring.getAlignment();
                if (alignment == 0 || alignment == 3) {
                    final int baseline = spring.getBaseline();
                    if (baseline >= 0) {
                        final int maximumSize = spring.getMaximumSize(2);
                        int preferredSize;
                        final int n3 = preferredSize = spring.getPreferredSize(2);
                        int n4 = 0;
                        switch (spring.getBaselineResizeBehavior()) {
                            case 1: {
                                n4 = n + prefAscent - baseline;
                                preferredSize = Math.min(prefDescent, maximumSize - baseline) + baseline;
                                break;
                            }
                            case 2: {
                                preferredSize = Math.min(prefAscent, maximumSize - n3 + baseline) + (n3 - baseline);
                                n4 = n + prefAscent + (n3 - baseline) - preferredSize;
                                break;
                            }
                            default: {
                                n4 = n + prefAscent - baseline;
                                break;
                            }
                        }
                        spring.setSize(2, n4, preferredSize);
                    }
                    else {
                        this.setChildSize(spring, 2, n, n2);
                    }
                }
                else {
                    this.setChildSize(spring, 2, n, n2);
                }
            }
        }
        
        int getBaseline() {
            if (this.springs.size() > 1) {
                this.getPreferredSize(2);
                return this.prefAscent;
            }
            if (this.springs.size() == 1) {
                return this.getSpring(0).getBaseline();
            }
            return -1;
        }
        
        int getBaselineResizeBehavior() {
            if (this.springs.size() == 1) {
                return this.getSpring(0).getBaselineResizeBehavior();
            }
            if (this.baselineAnchoredToTop) {
                return 1;
            }
            return 2;
        }
        
        private void checkAxis(final int n) {
            if (n == 1) {
                throw new IllegalStateException("Baseline must be used along vertical axis");
            }
        }
    }
    
    private final class ComponentSpring extends Spring
    {
        private Component component;
        private int origin;
        private final int min;
        private final int pref;
        private final int max;
        private int baseline;
        private boolean installed;
        
        private ComponentSpring(final Component component, final int min, final int pref, final int max) {
            this.baseline = -1;
            this.component = component;
            if (component == null) {
                throw new IllegalArgumentException("Component must be non-null");
            }
            checkSize(min, pref, max, true);
            this.min = min;
            this.max = max;
            this.pref = pref;
            GroupLayout.this.getComponentInfo(component);
        }
        
        int calculateMinimumSize(final int n) {
            if (this.isLinked(n)) {
                return this.getLinkSize(n, 0);
            }
            return this.calculateNonlinkedMinimumSize(n);
        }
        
        int calculatePreferredSize(final int n) {
            if (this.isLinked(n)) {
                return this.getLinkSize(n, 1);
            }
            return Math.min(this.getMaximumSize(n), Math.max(this.getMinimumSize(n), this.calculateNonlinkedPreferredSize(n)));
        }
        
        int calculateMaximumSize(final int n) {
            if (this.isLinked(n)) {
                return this.getLinkSize(n, 2);
            }
            return Math.max(this.getMinimumSize(n), this.calculateNonlinkedMaximumSize(n));
        }
        
        boolean isVisible() {
            return GroupLayout.this.getComponentInfo(this.getComponent()).isVisible();
        }
        
        int calculateNonlinkedMinimumSize(final int n) {
            if (!this.isVisible()) {
                return 0;
            }
            if (this.min >= 0) {
                return this.min;
            }
            if (this.min == -2) {
                return this.calculateNonlinkedPreferredSize(n);
            }
            assert this.min == -1;
            return this.getSizeAlongAxis(n, this.component.getMinimumSize());
        }
        
        int calculateNonlinkedPreferredSize(final int n) {
            if (!this.isVisible()) {
                return 0;
            }
            if (this.pref >= 0) {
                return this.pref;
            }
            assert this.pref == -2;
            return this.getSizeAlongAxis(n, this.component.getPreferredSize());
        }
        
        int calculateNonlinkedMaximumSize(final int n) {
            if (!this.isVisible()) {
                return 0;
            }
            if (this.max >= 0) {
                return this.max;
            }
            if (this.max == -2) {
                return this.calculateNonlinkedPreferredSize(n);
            }
            assert this.max == -1;
            return this.getSizeAlongAxis(n, this.component.getMaximumSize());
        }
        
        private int getSizeAlongAxis(final int n, final Dimension dimension) {
            return (n == 1) ? dimension.width : dimension.height;
        }
        
        private int getLinkSize(final int n, final int n2) {
            if (!this.isVisible()) {
                return 0;
            }
            return GroupLayout.this.getComponentInfo(this.component).getLinkSize(n, n2);
        }
        
        void setSize(final int n, final int origin, final int n2) {
            super.setSize(n, origin, n2);
            this.origin = origin;
            if (n2 == Integer.MIN_VALUE) {
                this.baseline = -1;
            }
        }
        
        int getOrigin() {
            return this.origin;
        }
        
        void setComponent(final Component component) {
            this.component = component;
        }
        
        Component getComponent() {
            return this.component;
        }
        
        int getBaseline() {
            if (this.baseline == -1 && this.component instanceof JComponent) {
                final int preferredSize = GroupLayout.this.getComponentInfo(this.component).horizontalSpring.getPreferredSize(1);
                final int preferredSize2 = this.getPreferredSize(2);
                if (preferredSize > 0 && preferredSize2 > 0) {
                    this.baseline = Baseline.getBaseline((JComponent)this.component, preferredSize, this.getPreferredSize(2));
                }
            }
            return this.baseline;
        }
        
        int getBaselineResizeBehavior() {
            return Baseline.getBaselineResizeBehavior(this.getComponent());
        }
        
        private boolean isLinked(final int n) {
            return GroupLayout.this.getComponentInfo(this.component).isLinked(n);
        }
        
        void installIfNecessary(final int n) {
            if (!this.installed) {
                this.installed = true;
                if (n == 1) {
                    GroupLayout.this.getComponentInfo(this.component).horizontalSpring = this;
                }
                else {
                    GroupLayout.this.getComponentInfo(this.component).verticalSpring = this;
                }
            }
        }
        
        boolean willHaveZeroSize(final boolean b) {
            return !this.isVisible();
        }
    }
    
    private final class PaddingSpring extends Spring
    {
        private final JComponent source;
        private final JComponent target;
        private final int type;
        private final boolean canGrow;
        
        PaddingSpring(final JComponent source, final JComponent target, final int type, final boolean canGrow) {
            this.source = source;
            this.target = target;
            this.type = type;
            this.canGrow = canGrow;
        }
        
        int calculateMinimumSize(final int n) {
            return this.getPadding(n);
        }
        
        int calculatePreferredSize(final int n) {
            return this.getPadding(n);
        }
        
        int calculateMaximumSize(final int n) {
            if (this.canGrow) {
                return 32767;
            }
            return this.getPadding(n);
        }
        
        private int getPadding(final int n) {
            int n2;
            if (n == 1) {
                n2 = 3;
            }
            else {
                n2 = 5;
            }
            return GroupLayout.this.getLayoutStyle0().getPreferredGap(this.source, this.target, this.type, n2, GroupLayout.this.host);
        }
        
        boolean willHaveZeroSize(final boolean b) {
            return false;
        }
    }
    
    private final class GapSpring extends Spring
    {
        private final int min;
        private final int pref;
        private final int max;
        
        GapSpring(final int min, final int pref, final int max) {
            checkSize(min, pref, max, false);
            this.min = min;
            this.pref = pref;
            this.max = max;
        }
        
        int calculateMinimumSize(final int n) {
            if (this.min == -2) {
                return this.getPreferredSize(n);
            }
            return this.min;
        }
        
        int calculatePreferredSize(final int n) {
            return this.pref;
        }
        
        int calculateMaximumSize(final int n) {
            if (this.max == -2) {
                return this.getPreferredSize(n);
            }
            return this.max;
        }
        
        boolean willHaveZeroSize(final boolean b) {
            return false;
        }
    }
    
    private class AutopaddingSpring extends Spring
    {
        List sources;
        ComponentSpring source;
        private List matches;
        int size;
        int lastSize;
        private final int pref;
        private final int max;
        private int type;
        private boolean userCreated;
        
        private AutopaddingSpring() {
            this.pref = -2;
            this.max = -2;
            this.type = 0;
        }
        
        AutopaddingSpring(final int pref, final int max) {
            this.pref = pref;
            this.max = max;
        }
        
        AutopaddingSpring(final int type, final int pref, final int max) {
            this.type = type;
            this.pref = pref;
            this.max = max;
            this.userCreated = true;
        }
        
        public void setSource(final ComponentSpring source) {
            this.source = source;
        }
        
        public void setSources(final List c) {
            this.sources = new ArrayList(c);
        }
        
        public void setUserCreated(final boolean userCreated) {
            this.userCreated = userCreated;
        }
        
        public boolean getUserCreated() {
            return this.userCreated;
        }
        
        void unset() {
            this.lastSize = this.getSize();
            super.unset();
            this.size = 0;
        }
        
        public void reset() {
            this.size = 0;
            this.sources = null;
            this.source = null;
            this.matches = null;
        }
        
        public void calculatePadding(final int n) {
            this.size = Integer.MIN_VALUE;
            int max = Integer.MIN_VALUE;
            if (this.matches != null) {
                final LayoutStyle access$800 = GroupLayout.this.getLayoutStyle0();
                int n2;
                if (n == 1) {
                    if (GroupLayout.this.isLeftToRight()) {
                        n2 = 3;
                    }
                    else {
                        n2 = 7;
                    }
                }
                else {
                    n2 = 5;
                }
                for (int i = this.matches.size() - 1; i >= 0; --i) {
                    final AutopaddingMatch autopaddingMatch = this.matches.get(i);
                    max = Math.max(max, this.calculatePadding(access$800, n2, autopaddingMatch.source, autopaddingMatch.target));
                }
            }
            if (this.size == Integer.MIN_VALUE) {
                this.size = 0;
            }
            if (max == Integer.MIN_VALUE) {
                max = 0;
            }
            if (this.lastSize != Integer.MIN_VALUE) {
                this.size += Math.min(max, this.lastSize);
            }
        }
        
        private int calculatePadding(final LayoutStyle layoutStyle, final int n, final ComponentSpring componentSpring, final ComponentSpring componentSpring2) {
            final int n2 = componentSpring2.getOrigin() - (componentSpring.getOrigin() + componentSpring.getSize());
            if (n2 >= 0) {
                int preferredGap;
                if (componentSpring.getComponent() instanceof JComponent && componentSpring2.getComponent() instanceof JComponent) {
                    preferredGap = layoutStyle.getPreferredGap((JComponent)componentSpring.getComponent(), (JComponent)componentSpring2.getComponent(), this.type, n, GroupLayout.this.host);
                }
                else {
                    preferredGap = 10;
                }
                if (preferredGap > n2) {
                    this.size = Math.max(this.size, preferredGap - n2);
                }
                return preferredGap;
            }
            return 0;
        }
        
        public void addTarget(final ComponentSpring componentSpring, final int n) {
            final int n2 = (n == 1) ? 2 : 1;
            if (this.source != null) {
                if (GroupLayout.this.areParallelSiblings(this.source.getComponent(), componentSpring.getComponent(), n2)) {
                    this.addValidTarget(this.source, componentSpring);
                }
            }
            else {
                final Component component = componentSpring.getComponent();
                for (int i = this.sources.size() - 1; i >= 0; --i) {
                    final ComponentSpring componentSpring2 = this.sources.get(i);
                    if (GroupLayout.this.areParallelSiblings(componentSpring2.getComponent(), component, n2)) {
                        this.addValidTarget(componentSpring2, componentSpring);
                    }
                }
            }
        }
        
        private void addValidTarget(final ComponentSpring componentSpring, final ComponentSpring componentSpring2) {
            if (this.matches == null) {
                this.matches = new ArrayList(1);
            }
            this.matches.add(new AutopaddingMatch(componentSpring, componentSpring2));
        }
        
        int calculateMinimumSize(final int n) {
            return this.size;
        }
        
        int calculatePreferredSize(final int n) {
            if (this.pref == -2 || this.pref == -1) {
                return this.size;
            }
            return Math.max(this.size, this.pref);
        }
        
        int calculateMaximumSize(final int n) {
            if (this.max >= 0) {
                return Math.max(this.getPreferredSize(n), this.max);
            }
            return this.size;
        }
        
        String getMatchDescription() {
            return (this.matches == null) ? "" : this.matches.toString();
        }
        
        public String toString() {
            return super.toString() + this.getMatchDescription();
        }
        
        boolean willHaveZeroSize(final boolean b) {
            return b;
        }
    }
    
    private static final class AutopaddingMatch
    {
        public final ComponentSpring source;
        public final ComponentSpring target;
        
        AutopaddingMatch(final ComponentSpring source, final ComponentSpring target) {
            this.source = source;
            this.target = target;
        }
        
        private String toString(final ComponentSpring componentSpring) {
            return componentSpring.getComponent().getName();
        }
        
        public String toString() {
            return "[" + this.toString(this.source) + "-" + this.toString(this.target) + "]";
        }
    }
    
    private class ContainerAutopaddingSpring extends AutopaddingSpring
    {
        private List targets;
        
        ContainerAutopaddingSpring() {
            this.setUserCreated(true);
        }
        
        ContainerAutopaddingSpring(final int n, final int n2) {
            super(n, n2);
            this.setUserCreated(true);
        }
        
        public void addTarget(final ComponentSpring componentSpring, final int n) {
            if (this.targets == null) {
                this.targets = new ArrayList(1);
            }
            this.targets.add(componentSpring);
        }
        
        public void calculatePadding(final int n) {
            final LayoutStyle access$800 = GroupLayout.this.getLayoutStyle0();
            int n2 = 0;
            this.size = 0;
            if (this.targets != null) {
                int n3;
                if (n == 1) {
                    if (GroupLayout.this.isLeftToRight()) {
                        n3 = 7;
                    }
                    else {
                        n3 = 3;
                    }
                }
                else {
                    n3 = 5;
                }
                for (int i = this.targets.size() - 1; i >= 0; --i) {
                    final ComponentSpring componentSpring = this.targets.get(i);
                    int n4 = 10;
                    if (componentSpring.getComponent() instanceof JComponent) {
                        final int containerGap = access$800.getContainerGap((JComponent)componentSpring.getComponent(), n3, GroupLayout.this.host);
                        n2 = Math.max(containerGap, n2);
                        n4 = containerGap - componentSpring.getOrigin();
                    }
                    else {
                        n2 = Math.max(n4, n2);
                    }
                    this.size = Math.max(this.size, n4);
                }
            }
            else {
                int n5;
                if (n == 1) {
                    if (GroupLayout.this.isLeftToRight()) {
                        n5 = 3;
                    }
                    else {
                        n5 = 7;
                    }
                }
                else {
                    n5 = 5;
                }
                if (this.sources != null) {
                    for (int j = this.sources.size() - 1; j >= 0; --j) {
                        n2 = Math.max(n2, this.updateSize(access$800, (ComponentSpring)this.sources.get(j), n5));
                    }
                }
                else if (this.source != null) {
                    n2 = this.updateSize(access$800, this.source, n5);
                }
            }
            if (this.lastSize != Integer.MIN_VALUE) {
                this.size += Math.min(n2, this.lastSize);
            }
        }
        
        private int updateSize(final LayoutStyle layoutStyle, final ComponentSpring componentSpring, final int n) {
            int containerGap = 10;
            if (componentSpring.getComponent() instanceof JComponent) {
                containerGap = layoutStyle.getContainerGap((JComponent)componentSpring.getComponent(), n, GroupLayout.this.host);
            }
            this.size = Math.max(this.size, containerGap - Math.max(0, this.getParent().getSize() - componentSpring.getSize() - componentSpring.getOrigin()));
            return containerGap;
        }
        
        String getMatchDescription() {
            if (this.targets != null) {
                return "leading: " + this.targets.toString();
            }
            if (this.sources != null) {
                return "trailing: " + this.sources.toString();
            }
            return "--";
        }
    }
    
    private static final class LinkInfo
    {
        private final int axis;
        private final List linked;
        private int size;
        
        LinkInfo(final int axis) {
            this.linked = new ArrayList();
            this.size = Integer.MIN_VALUE;
            this.axis = axis;
        }
        
        public void add(final ComponentInfo componentInfo) {
            final LinkInfo access$1100 = componentInfo.getLinkInfo(this.axis, false);
            if (access$1100 == null) {
                this.linked.add(componentInfo);
                componentInfo.setLinkInfo(this.axis, this);
            }
            else if (access$1100 != this) {
                this.linked.addAll(access$1100.linked);
                for (int i = 0; i < access$1100.linked.size(); ++i) {
                    ((ComponentInfo)access$1100.linked.get(i)).setLinkInfo(this.axis, this);
                }
            }
            this.clearCachedSize();
        }
        
        public void remove(final ComponentInfo componentInfo) {
            this.linked.remove(componentInfo);
            componentInfo.setLinkInfo(this.axis, null);
            if (this.linked.size() == 1) {
                this.linked.get(0).setLinkInfo(this.axis, null);
            }
            this.clearCachedSize();
        }
        
        public void clearCachedSize() {
            this.size = Integer.MIN_VALUE;
        }
        
        public int getSize(final int n) {
            if (this.size == Integer.MIN_VALUE) {
                this.size = this.calculateLinkedSize(n);
            }
            return this.size;
        }
        
        private int calculateLinkedSize(final int n) {
            int max = 0;
            for (int i = 0; i < this.linked.size(); ++i) {
                final ComponentInfo componentInfo = this.linked.get(i);
                ComponentSpring componentSpring;
                if (n == 1) {
                    componentSpring = componentInfo.horizontalSpring;
                }
                else {
                    assert n == 2;
                    componentSpring = componentInfo.verticalSpring;
                }
                max = Math.max(max, componentSpring.calculateNonlinkedPreferredSize(n));
            }
            return max;
        }
    }
    
    private final class ComponentInfo
    {
        private Component component;
        ComponentSpring horizontalSpring;
        ComponentSpring verticalSpring;
        private LinkInfo horizontalMaster;
        private LinkInfo verticalMaster;
        private boolean visible;
        private Boolean honorsVisibility;
        
        ComponentInfo(final Component component) {
            this.component = component;
            this.updateVisibility();
        }
        
        public void dispose() {
            this.removeSpring(this.horizontalSpring);
            this.horizontalSpring = null;
            this.removeSpring(this.verticalSpring);
            this.verticalSpring = null;
            if (this.horizontalMaster != null) {
                this.horizontalMaster.remove(this);
            }
            if (this.verticalMaster != null) {
                this.verticalMaster.remove(this);
            }
        }
        
        void setHonorsVisibility(final Boolean honorsVisibility) {
            this.honorsVisibility = honorsVisibility;
        }
        
        private void removeSpring(final Spring spring) {
            if (spring != null) {
                ((Group)spring.getParent()).springs.remove(spring);
            }
        }
        
        public boolean isVisible() {
            return this.visible;
        }
        
        boolean updateVisibility() {
            boolean b;
            if (this.honorsVisibility == null) {
                b = GroupLayout.this.getHonorsVisibility();
            }
            else {
                b = this.honorsVisibility;
            }
            final boolean visible = !b || this.component.isVisible();
            if (this.visible != visible) {
                this.visible = visible;
                return true;
            }
            return false;
        }
        
        public void setBounds(final Insets insets, final int n, final boolean b) {
            int origin = this.horizontalSpring.getOrigin();
            final int size = this.horizontalSpring.getSize();
            final int origin2 = this.verticalSpring.getOrigin();
            final int size2 = this.verticalSpring.getSize();
            if (!b) {
                origin = n - origin - size;
            }
            this.component.setBounds(origin + insets.left, origin2 + insets.top, size, size2);
        }
        
        public void setComponent(final Component component) {
            this.component = component;
            if (this.horizontalSpring != null) {
                this.horizontalSpring.setComponent(component);
            }
            if (this.verticalSpring != null) {
                this.verticalSpring.setComponent(component);
            }
        }
        
        public Component getComponent() {
            return this.component;
        }
        
        public boolean isLinked(final int n) {
            if (n == 1) {
                return this.horizontalMaster != null;
            }
            assert n == 2;
            return this.verticalMaster != null;
        }
        
        private void setLinkInfo(final int n, final LinkInfo linkInfo) {
            if (n == 1) {
                this.horizontalMaster = linkInfo;
            }
            else {
                assert n == 2;
                this.verticalMaster = linkInfo;
            }
        }
        
        public LinkInfo getLinkInfo(final int n) {
            return this.getLinkInfo(n, true);
        }
        
        private LinkInfo getLinkInfo(final int n, final boolean b) {
            if (n == 1) {
                if (this.horizontalMaster == null && b) {
                    new LinkInfo(1).add(this);
                }
                return this.horizontalMaster;
            }
            assert n == 2;
            if (this.verticalMaster == null && b) {
                new LinkInfo(2).add(this);
            }
            return this.verticalMaster;
        }
        
        public void clearCachedSize() {
            if (this.horizontalMaster != null) {
                this.horizontalMaster.clearCachedSize();
            }
            if (this.verticalMaster != null) {
                this.verticalMaster.clearCachedSize();
            }
        }
        
        int getLinkSize(final int n, final int n2) {
            if (n == 1) {
                return this.horizontalMaster.getSize(n);
            }
            assert n == 2;
            return this.verticalMaster.getSize(n);
        }
    }
}
