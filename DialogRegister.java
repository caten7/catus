// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import javax.swing.SwingUtilities;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import java.awt.Dimension;
import java.awt.Container;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Window;
import java.awt.Dialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JDialog;

public class DialogRegister extends JDialog
{
    String email;
    String id;
    final HttpCache hc;
    final JTextField email_field;
    final JButton btn;
    final JLabel error_lbl;
    final Object guard;
    private Thread thread;
    
    DialogRegister(final JFrame parent, final HttpCache hc, final String email) {
        super(parent, "Register Catus", ModalityType.DOCUMENT_MODAL);
        this.guard = new Object();
        this.hc = hc;
        this.setDefaultCloseOperation(1);
        this.setResizable(false);
        this.error_lbl = UI.L("", CatusFrame.tinyFont);
        (this.email_field = new JTextField()).setText((email != null) ? email : "");
        this.email_field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                DialogRegister.this.btn.doClick();
            }
        });
        (this.btn = UI.makeButton("Register")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                DialogRegister.this.btn.setEnabled(false);
                DialogRegister.this.btn.setText("Registering");
                DialogRegister.this.error_lbl.setText("");
                DialogRegister.this.doRegister();
            }
        });
        final UIPanel_GridBag p = new UIPanel_GridBag();
        UIPanel_GridBag row = new UIPanel_GridBag();
        row.add(UI.L("Email: ", CatusFrame.boldFont));
        row.spacer(this.email_field);
        p.row(row, true, 0);
        row = new UIPanel_GridBag();
        row.spacer(this.error_lbl);
        row.add(this.btn);
        p.row(row, true, 4);
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setContentPane(p);
        this.pack();
        this.setSize(new Dimension(400, this.getHeight()));
        this.setSize(new Dimension(400, this.getHeight()));
        this.setLocationRelativeTo(parent);
        this.email_field.requestFocus();
    }
    
    private void doRegister() {
        final Thread t = new Thread() {
            @Override
            public void run() {
                final JSONObject root = new JSONObject();
                root.put("Email", DialogRegister.this.email_field.getText().trim());
                final String q = Base64.encodeStr(root.toJSONString());
                try {
                    final Pair<String, Boolean> r = DialogRegister.this.hc.getStr("http://raffy.antistupid.com/wow/catus/register.php?q=" + q, "", -1, 1);
                    if (r.cdr) {
                        throw new RuntimeException("Server error: try later.");
                    }
                    JSONObject reply;
                    try {
                        reply = (JSONObject)JSONValue.parseWithException(r.car);
                    }
                    catch (Exception err) {
                        throw new RuntimeException("Bad JSON: " + err.getMessage());
                    }
                    final String error = JSONHelp.getStr(reply, "Error", null);
                    if (error != null) {
                        throw new RuntimeException(error);
                    }
                    DialogRegister.this.id = JSONHelp.reqStr(reply, "Id");
                    DialogRegister.this.email = JSONHelp.reqStr(reply, "Email");
                    if (this.stillOwned()) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                DialogRegister.this.setVisible(false);
                            }
                        });
                    }
                }
                catch (Exception err2) {
                    if (this.stillOwned()) {
                        final String error2 = err2.getMessage();
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                DialogRegister.this.error_lbl.setText(error2);
                                DialogRegister.this.btn.setText("Register");
                                DialogRegister.this.btn.setEnabled(true);
                            }
                        });
                    }
                }
            }
            
            private boolean stillOwned() {
                synchronized (DialogRegister.this.guard) {
                    final boolean b = DialogRegister.this.thread == this;
                    if (b) {
                        DialogRegister.this.thread = null;
                    }
                    return b;
                }
            }
        };
        synchronized (this.guard) {
            (this.thread = t).start();
        }
    }
    
    public void go() {
        this.setVisible(true);
        synchronized (this.guard) {
            final Thread t = this.thread;
            if (t != null) {
                this.thread = null;
                t.interrupt();
            }
        }
        this.dispose();
    }
}
