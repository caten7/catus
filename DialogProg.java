// 
// Decompiled by Procyon v0.5.36
// 

package catus;

import java.util.Iterator;
import java.util.Collection;
import java.util.Arrays;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.concurrent.atomic.AtomicBoolean;
import java.awt.Dimension;
import java.awt.Container;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Window;
import java.awt.Dialog;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JDialog;

public class DialogProg extends JDialog
{
    public final JProgressBar pb;
    public final ProgBarProxy proxy;
    private volatile RuntimeException error;
    static final Each<Runnable> runnableRunner;
    
    DialogProg(final JFrame parent, final String title) {
        super(parent, title, ModalityType.DOCUMENT_MODAL);
        this.setDefaultCloseOperation(0);
        this.setResizable(false);
        final JPanel p = new JPanel();
        p.setLayout(new GridLayout(0, 1));
        this.pb = new JProgressBar();
        this.proxy = new ProgBarProxy.Real(this.pb);
        this.pb.setIndeterminate(true);
        p.add(this.pb);
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setContentPane(p);
        this.pack();
        this.setSize(new Dimension(400, this.getHeight()));
        this.setSize(new Dimension(400, this.getHeight()));
        this.setLocationRelativeTo(parent);
    }
    
    void setAbortOnClose(final AtomicBoolean b) {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent we) {
                b.set(true);
                DialogProg.this.pb.setIndeterminate(true);
            }
        });
    }
    
    public void forEach(final Runnable... a) {
        this.forEach(Arrays.asList(a));
    }
    
    public void forEach(final Collection<Runnable> list) {
        this.forEach(list, DialogProg.runnableRunner);
    }
    
    public <T> void forEach(final Collection<T> list, final Each<T> each) {
        if (list.isEmpty()) {
            return;
        }
        new Thread() {
            @Override
            public void run() {
                try {
                    int index = 0;
                    if (list.size() == 1) {
                        DialogProg.this.proxy.indeterminate();
                    }
                    else {
                        DialogProg.this.proxy.determinate(0, list.size());
                    }
                    for (final T x : list) {
                        each.each(index, x);
                        DialogProg.this.proxy.value(++index);
                    }
                }
                catch (RuntimeException err) {
                    DialogProg.this.error = err;
                }
                finally {
                    DialogProg.this.dispose();
                }
            }
        }.start();
        this.setVisible(true);
        if (this.error != null) {
            throw this.error;
        }
    }
    
    public void execute(final Runnable r) {
        new Thread() {
            @Override
            public void run() {
                try {
                    r.run();
                }
                catch (RuntimeException err) {
                    DialogProg.this.error = err;
                }
                finally {
                    DialogProg.this.dispose();
                }
            }
        }.start();
        this.setVisible(true);
        if (this.error != null) {
            throw this.error;
        }
    }
    
    public <T> T compute(final Getter<T> r) {
        final Object[] ret = { null };
        this.error = null;
        new Thread() {
            @Override
            public void run() {
                try {
                    ret[0] = r.get();
                }
                catch (RuntimeException err) {
                    DialogProg.this.error = err;
                }
                finally {
                    DialogProg.this.dispose();
                }
            }
        }.start();
        this.setVisible(true);
        if (this.error != null) {
            throw this.error;
        }
        return (T)ret[0];
    }
    
    static {
        runnableRunner = new Each<Runnable>() {
            @Override
            public void each(final int index, final Runnable value) {
                value.run();
            }
        };
    }
    
    public interface Getter<T>
    {
        T get();
    }
    
    public interface Each<T>
    {
        void each(final int p0, final T p1);
    }
}
