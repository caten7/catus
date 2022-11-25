// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import java.util.prefs.Preferences;
import java.util.Iterator;
import javax.swing.JOptionPane;
import java.util.Collection;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Window;
import java.awt.Dialog;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JDialog;

public class BootFrame extends JDialog implements Statusable
{
    final JLabel phase_lbl;
    final JLabel state_lbl;
    final JProgressBar phase_bar;
    final JProgressBar state_bar;
    
    BootFrame() {
        super(null, "Startup", ModalityType.DOCUMENT_MODAL);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent we) {
                System.exit(1);
            }
        });
        this.setResizable(false);
        this.phase_bar = new JProgressBar();
        this.state_bar = new JProgressBar();
        this.phase_lbl = UI.makeBold(new JLabel("Initializing..."));
        this.state_lbl = new JLabel("Doing stuff");
        final UIPanel_GridBag p = new UIPanel_GridBag();
        final int pad = 4;
        p.row(this.phase_lbl, true, 0);
        p.row(this.phase_bar, true, pad);
        p.row(this.state_lbl, true, pad);
        p.row(this.state_bar, true, pad);
        p.pad(10);
        this.setContentPane(p);
        this.pack();
        this.setSize(new Dimension(400, this.getHeight()));
        this.setSize(new Dimension(400, this.getHeight()));
        this.setLocationRelativeTo(null);
    }
    
    @Override
    public void updateStatus(final String status) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BootFrame.this.state_lbl.setText(status);
            }
        });
    }
    
    public void updateProgress(final int min, final int max) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (max > 0) {
                    BootFrame.this.state_bar.setIndeterminate(false);
                    BootFrame.this.state_bar.setMaximum(max);
                    BootFrame.this.state_bar.setValue(min);
                }
                else {
                    BootFrame.this.state_bar.setIndeterminate(true);
                }
            }
        });
    }
    
    public void execute(final Collection<Phase> phases) {
        this.phase_bar.setValue(0);
        this.phase_bar.setMaximum(phases.size());
        new Thread() {
            @Override
            public void run() {
                for (final Phase x : phases) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            BootFrame.this.phase_lbl.setText(x.name);
                            BootFrame.this.phase_bar.setValue(BootFrame.this.phase_bar.getValue() + 1);
                            BootFrame.this.state_bar.setIndeterminate(true);
                            BootFrame.this.state_lbl.setText("Loading...");
                        }
                    });
                    try {
                        x.run(BootFrame.this);
                    }
                    catch (RuntimeException err) {
                        JOptionPane.showMessageDialog(null, "<html><p style='width:400px'>An error occured while <b>" + x.name + "</b>:<br/><br/><b>" + err.getClass().getSimpleName() + "</b><br/>" + err.getMessage() + "</p></html>", "Startup Error", 0);
                        err.printStackTrace();
                        System.exit(1);
                    }
                }
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        BootFrame.this.setVisible(false);
                    }
                });
            }
        }.start();
        this.setVisible(true);
    }
    
    public static Phase makePhase_taint(final HttpCache hc, final Preferences prefs, final String appName, final int appVersion, final Phase newVersion) {
        return new Phase("Checking for Updates") {
            @Override
            public void run(final BootFrame bf) {
                this.handleTaint(bf);
                this.handleItems(bf);
            }
            
            void handleTaint(final BootFrame bf) {
                bf.updateStatus("Checking for Updates");
                final String json = hc.getStr_silent("https://dl.dropboxusercontent.com/u/2989349/catus_data/Taint.json", "", 3600000, 3);
                if (json == null) {
                    return;
                }
                JSONObject root;
                try {
                    root = (JSONObject)JSONValue.parse(json);
                }
                catch (RuntimeException err) {
                    System.err.println("Unable to parse taint: " + json);
                    return;
                }
                final int latestVersion = JSONHelp.getInt(root, appName, 0);
                final String downloadURL = JSONHelp.getStr(root, "Download", "http://fluiddruid.net/forum/");
                if (latestVersion > appVersion) {
                    switch (JOptionPane.showConfirmDialog(null, String.format("<html><b>%s %s</b> is now available (you have %d).<br/><br/>Do you want update to the new version?</html>", appName, latestVersion, appVersion), appName + " Update", 0, 1)) {
                        case 0: {
                            Main.tryUpdater();
                            Utils.openURL(downloadURL);
                            break;
                        }
                    }
                }
                final int lastVersion = prefs.getInt("LastVersion", 0);
                if (lastVersion < appVersion) {
                    if (newVersion != null) {
                        newVersion.run(bf);
                    }
                    prefs.putInt("LastVersion", appVersion);
                }
            }
            
            void handleItems(final BootFrame bf) {
                bf.updateStatus("Checking for Item Changes");
                final String fn = "version.json";
                final String url = "http://raffy.antistupid.com/wow/version.php";
                final String json0 = hc.getStr_silent(url, fn, 0, 2);
                final String json2 = hc.getStr_silent(url, fn, 3600000);
                if (json2 == null) {
                    return;
                }
                if (json0 != null) {
                    JSONObject root0;
                    JSONObject root2;
                    try {
                        root0 = (JSONObject)JSONValue.parse(json0);
                        root2 = (JSONObject)JSONValue.parse(json2);
                    }
                    catch (RuntimeException err) {
                        System.err.println("Unable to parse version data: " + json0 + " / " + json2);
                        return;
                    }
                    final int version0 = JSONHelp.getInt(root0, "version", 0);
                    final int version2 = JSONHelp.getInt(root2, "version", 0);
                    if (version0 >= version2) {
                        return;
                    }
                }
                bf.updateStatus("Removing Item Cache");
                hc.cleanCache_only("raffy.antistupid.com", fn);
            }
        };
    }
    
    public abstract static class Phase
    {
        public final String name;
        
        public Phase(final String name) {
            this.name = name;
        }
        
        public abstract void run(final BootFrame p0);
    }
}
