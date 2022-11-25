// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import java.util.Collection;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.Window;
import java.awt.Dialog;
import java.awt.Frame;
import javax.swing.JDialog;

public class DialogRecentArmory extends JDialog
{
    int state;
    final UIPanel_GridBag grid;
    
    public DialogRecentArmory(final Frame parent) {
        super(parent, "Edit: Recent", ModalityType.DOCUMENT_MODAL);
        this.grid = new UIPanel_GridBag();
        final JScrollPane sp = new JScrollPane();
        sp.setOpaque(false);
        sp.setViewportView(this.grid);
        sp.setVerticalScrollBarPolicy(22);
        final UIPanel_GridBag content = new UIPanel_GridBag();
        content.pad(5);
        content.row_both(sp);
        final JButton clearBtn = UI.makeButton("Clear");
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                DialogRecentArmory.this.state = 2;
                DialogRecentArmory.this.setVisible(false);
            }
        });
        final JButton deleteBtn = UI.makeButton("Delete");
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                DialogRecentArmory.this.state = 1;
                DialogRecentArmory.this.setVisible(false);
            }
        });
        final UIPanel_GridBag p = new UIPanel_GridBag();
        p.add(clearBtn);
        p.spacer();
        p.add(deleteBtn);
        content.row(p, true, 5);
        this.setContentPane(content);
        this.setMinimumSize(new Dimension(300, 200));
        this.setMaximumSize(new Dimension(500, 600));
    }
    
    public boolean edit(final Collection<RecentArmory> recentList) {
        final int num = recentList.size();
        final RecentArmory[] recents = recentList.toArray(new RecentArmory[num]);
        final JCheckBox[] checks = new JCheckBox[num];
        this.grid.removeAll();
        this.grid.setLayout(new GridBagLayout());
        for (int i = 0; i < num; ++i) {
            final RecentArmory temp = recents[i];
            final JCheckBox check = UI.makeCheck(String.format("<html><b>%s</b> %s/%s</html>", temp.name, temp.realm, temp.region));
            checks[i] = check;
            this.grid.row(check, true, 0);
        }
        this.grid.balloon();
        this.pack();
        this.setLocationRelativeTo(this.getParent());
        this.state = 0;
        this.setVisible(true);
        this.dispose();
        switch (this.state) {
            case 1: {
                recentList.clear();
                for (int i = 0; i < num; ++i) {
                    if (!checks[i].isSelected()) {
                        recentList.add(recents[i]);
                    }
                }
                return true;
            }
            case 2: {
                recentList.clear();
                return true;
            }
            default: {
                return false;
            }
        }
    }
}
