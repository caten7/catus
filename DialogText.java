// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import javax.swing.JComponent;
import java.awt.AWTEvent;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Window;
import java.awt.Dialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JDialog;

public class DialogText extends JDialog
{
    final JTextArea ta;
    final JScrollPane sp;
    boolean result;
    
    public DialogText(final JFrame parent, final String title, final String okTitle) {
        super(parent, title, ModalityType.DOCUMENT_MODAL);
        this.setDefaultCloseOperation(1);
        (this.ta = new JTextArea()).setFont(Font.decode("Monospaced").deriveFont(11));
        final JButton btn = new JButton(okTitle);
        btn.setFocusable(false);
        btn.setOpaque(false);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                DialogText.this.result = true;
                DialogText.this.setVisible(false);
            }
        });
        (this.sp = new JScrollPane()).setVerticalScrollBarPolicy(22);
        this.sp.setHorizontalScrollBarPolicy(30);
        this.sp.setViewportView(this.ta);
        final UIPanel_GridBag row = new UIPanel_GridBag();
        row.spacer();
        row.add(btn);
        final UIPanel_GridBag p = new UIPanel_GridBag();
        p.row_both(this.sp);
        p.row(row, true, 4);
        p.pad(8);
        UI.onShortcut(p, 87, "Close", new Runnable() {
            @Override
            public void run() {
                DialogText.this.dispatchEvent(new WindowEvent(DialogText.this, 201));
            }
        });
        this.setContentPane(p);
    }
    
    public DialogText asSheet() {
        if (Utils.isMac()) {
            this.getRootPane().putClientProperty("apple.awt.documentModalSheet", "true");
        }
        return this;
    }
    
    public void showTextWrapped(final String wut) {
        final int len = wut.length();
        final int wrap = 80;
        final StringBuilder sb = new StringBuilder(len + (len + wrap - 1) / wrap);
        int pos = 0;
        while (true) {
            final int end = pos + wrap;
            if (end >= len) {
                break;
            }
            sb.append(wut.substring(pos, end));
            pos = end;
            sb.append("\n");
        }
        if (pos < len) {
            sb.append(wut.substring(pos));
        }
        this.showWrappedText(sb.toString(), 80, 0);
    }
    
    public void showWrappedText(final String wut) {
        this.showWrappedText(wut, 100, 0);
    }
    
    public void showWrappedText(final String wut, final int cols, final int rows) {
        this.ta.setEditable(false);
        this.ta.setWrapStyleWord(true);
        this.ta.setLineWrap(true);
        this.ta.setText(wut);
        this.ta.select(0, 0);
        this.ta.setColumns(cols);
        if (rows > 0) {
            this.ta.setRows(rows);
        }
        else {
            this.ta.setRows(Math.min(40, (wut.length() + cols - 1) / cols));
        }
        final Dimension size = this.ta.getPreferredScrollableViewportSize();
        final Insets insets = this.sp.getInsets();
        final Dimension dimension = size;
        dimension.width += insets.left + insets.right + UI.scrollBarWidth();
        final Dimension dimension2 = size;
        dimension2.height += insets.top + insets.bottom;
        this.sp.setPreferredSize(size);
        this.pack();
        this.setMinimumSize(this.getSize());
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
        this.dispose();
    }
    
    public void showText(final String wut) {
        this.ta.setEditable(false);
        this.sizeToFit(wut, 30, 10);
        this.setVisible(true);
        this.dispose();
    }
    
    private void sizeToFit(final String wut, final int cols, final int rows) {
        this.ta.setText(wut);
        this.ta.select(0, 0);
        this.ta.setRows(50);
        final int lines = Math.max(rows, this.ta.getLineCount());
        if (lines < this.ta.getRows()) {
            this.ta.setRows(lines);
        }
        this.ta.setColumns(cols);
        final int width = this.ta.getPreferredSize().width;
        this.ta.setColumns(0);
        final Dimension size = this.ta.getPreferredScrollableViewportSize();
        final Insets insets = this.sp.getInsets();
        size.width = Math.max(size.width + insets.left + insets.right + UI.scrollBarWidth(), width);
        final Dimension dimension = size;
        dimension.height += insets.top + insets.bottom;
        this.ta.setRows(0);
        this.sp.setPreferredSize(size);
        this.pack();
        this.setMinimumSize(this.getSize());
        this.setLocationRelativeTo(this.getParent());
    }
    
    public String editText(final String wut) {
        this.result = false;
        this.sizeToFit(wut, 80, 20);
        this.setVisible(true);
        this.dispose();
        return this.result ? this.ta.getText() : null;
    }
    
    public String getText() {
        this.ta.setRows(10);
        this.ta.setColumns(30);
        this.pack();
        this.setMinimumSize(this.getSize());
        this.ta.setRows(20);
        this.ta.setColumns(80);
        this.pack();
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
        final String text = this.ta.getText().trim();
        this.dispose();
        return (this.result && !text.isEmpty()) ? text : null;
    }
    
    public String getWrappedText() {
        this.ta.setWrapStyleWord(true);
        this.ta.setLineWrap(true);
        return this.getText();
    }
}
