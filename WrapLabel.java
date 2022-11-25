// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.awt.Font;
import java.awt.AWTEvent;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import java.awt.event.MouseWheelEvent;
import javax.swing.border.Border;
import java.awt.Component;
import javax.swing.UIManager;
import java.awt.Cursor;
import javax.swing.text.Highlighter;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class WrapLabel extends JScrollPane
{
    final JTextArea ta;
    
    public WrapLabel() {
        (this.ta = new JTextArea()).setEditable(false);
        this.ta.setLineWrap(true);
        this.ta.setHighlighter(null);
        this.ta.setCursor(null);
        this.ta.setOpaque(false);
        this.ta.setLineWrap(true);
        this.ta.setWrapStyleWord(true);
        this.ta.setColumns(1);
        this.ta.setFont(UIManager.getFont("Label.font"));
        this.setHorizontalScrollBarPolicy(31);
        this.setVerticalScrollBarPolicy(21);
        this.setViewportView(this.ta);
        this.setBorder(null);
        this.setViewportBorder(null);
        this.setOpaque(false);
        this.setEnabled(false);
        this.setWheelScrollingEnabled(false);
        this.getViewport().setOpaque(false);
    }
    
    @Override
    protected void processMouseWheelEvent(final MouseWheelEvent e) {
        if (!this.isWheelScrollingEnabled()) {
            if (this.getParent() != null) {
                this.getParent().dispatchEvent(SwingUtilities.convertMouseEvent(this, e, this.getParent()));
            }
            return;
        }
        super.processMouseWheelEvent(e);
    }
    
    @Override
    public boolean contains(final int x, final int y) {
        return false;
    }
    
    public WrapLabel(final String text) {
        this();
        this.ta.setText(text);
    }
    
    public WrapLabel(final String text, final Font font) {
        this(text);
        this.ta.setFont(font);
    }
    
    public final void setText(final String text) {
        this.ta.setText(text);
    }
}
