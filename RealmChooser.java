// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.awt.Container;
import java.awt.Component;
import java.awt.event.KeyListener;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Dialog;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JDialog;

public class RealmChooser extends JDialog
{
    static final int COLS = 4;
    Realm answer;
    final JButton[] buttons;
    
    public RealmChooser(final Window parent, final Pair<ClassT, Realm>[] realms, final boolean showClass) {
        super(parent, "Choose Realm", ModalityType.DOCUMENT_MODAL);
        this.setResizable(false);
        this.setDefaultCloseOperation(1);
        final UIPanel p = new UIPanel();
        final int num = realms.length;
        p.setLayout(new GridLayout(0, Math.min(num, 4)));
        this.buttons = new JButton[num];
        final KeyAdapter ka = new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                switch (e.getKeyCode()) {
                    case 38: {
                        RealmChooser.this.jump(true, -1);
                        break;
                    }
                    case 40: {
                        RealmChooser.this.jump(true, 1);
                        break;
                    }
                    case 37: {
                        RealmChooser.this.jump(false, -1);
                        break;
                    }
                    case 39: {
                        RealmChooser.this.jump(false, 1);
                        break;
                    }
                }
            }
        };
        for (int i = 0; i < num; ++i) {
            final Pair<ClassT, Realm> pair = realms[i];
            final JButton btn = new JButton(showClass ? String.format("<html><b>%s</b> %s</html>", pair.car, pair.cdr.name) : pair.cdr.name);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent ae) {
                    RealmChooser.this.answer = pair.cdr;
                    RealmChooser.this.setVisible(false);
                }
            });
            (this.buttons[i] = btn).registerKeyboardAction(btn.getActionForKeyStroke(KeyStroke.getKeyStroke(32, 0, false)), KeyStroke.getKeyStroke(10, 0, false), 0);
            btn.registerKeyboardAction(btn.getActionForKeyStroke(KeyStroke.getKeyStroke(32, 0, true)), KeyStroke.getKeyStroke(10, 0, true), 0);
            btn.addKeyListener(ka);
            p.add(btn);
        }
        p.pad(5);
        this.setContentPane(p);
        this.pack();
        this.setLocationRelativeTo(parent);
    }
    
    void jump(final boolean vert, final int jump) {
        final Component focus = this.getFocusOwner();
        for (int i = 0; i < this.buttons.length; ++i) {
            if (this.buttons[i] == focus) {
                if (vert) {
                    final int to = i + jump * 4;
                    if (to >= 0 && to < this.buttons.length) {
                        this.buttons[to].requestFocus();
                    }
                }
                else {
                    final int to = i + jump;
                    if (to >= 0 && to < this.buttons.length && to / 4 == i / 4) {
                        this.buttons[to].requestFocus();
                    }
                }
                return;
            }
        }
        this.buttons[0].requestFocus();
    }
    
    public Realm getRealm() {
        this.answer = null;
        this.setVisible(true);
        return this.answer;
    }
}
