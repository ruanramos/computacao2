import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.TreeSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Canvas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class GuiApp {    
    private BufferStrategy strategy;
    private TreeSet<String> keySet = new TreeSet<String>();
    private Canvas canvas = new Canvas();
    
    ArrayList<Componente> componentes;
    HashMap<String, Componente> teclas;
    Componente foco;
    String titulo;
    int larg, alt;
    
    public GuiApp(String titulo, int larg, int alt) {
        this.titulo = titulo; this.larg = larg; this.alt = alt;
        componentes = new ArrayList<Componente>();
        teclas = new HashMap<String, Componente>();
        JFrame container = new JFrame(titulo);
        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(new Dimension(
                larg, alt));
        panel.setLayout(null);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        Rectangle bounds = gs[gs.length-1].getDefaultConfiguration().getBounds();
        container.setResizable(false);
        container.setBounds(bounds.x+(bounds.width - larg)/2,
                            bounds.y+(bounds.height - alt)/2,
                            larg,alt);
        canvas.setBounds(0,0,larg,alt);
        panel.add(canvas);        
        canvas.setIgnoreRepaint(true);
        container.pack();
        container.setVisible(true);
        container.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                sair();
            }
        });
        canvas.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {
                keySet.add(keyString(evt));
            }
            @Override
            public void keyReleased(KeyEvent evt) {
                keySet.remove(keyString(evt));
            }
            @Override
            public void keyTyped(KeyEvent evt) {
                tecla(keyString(evt));
            }
        });
        canvas.createBufferStrategy(2);
        strategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        canvas.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent arg0) {
                arrasto(arg0.getX(), arg0.getY());
            }
            @Override
            public void mouseMoved(MouseEvent arg0) {
                movimento(arg0.getX(), arg0.getY());
            }
            
        });
        canvas.addMouseListener(new MouseListener() {
            int x, y;
            
            @Override
            public void mouseClicked(MouseEvent arg0) {}
            @Override
            public void mouseEntered(MouseEvent arg0) {}
            @Override
            public void mouseExited(MouseEvent arg0) {}
            @Override
            public void mousePressed(MouseEvent arg0) {
                x = arg0.getX(); y = arg0.getY();
                aperto(arg0.getX(), arg0.getY());
            }
            @Override
            public void mouseReleased(MouseEvent arg0) {
                if(Math.abs(x - arg0.getX()) < 5 &&
                   Math.abs(y - arg0.getY()) < 5) {
                    clique(arg0.getX(), arg0.getY());
                }
                solta(arg0.getX(), arg0.getY());
            }
        });
    }

    public void run() {
        Timer t = new Timer(5, new ActionListener() {
            public long t0;
            public void actionPerformed(ActionEvent evt) {
                long t1 = System.currentTimeMillis();
                if(t0 == 0)
                    t0 = t1;
                if(t1 > t0) {
                    double dt = (t1 - t0) / 1000.0;
                    t0 = t1;
                    tique(keySet, dt);     
                    Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
                    g.setColor(Color.black);
                    g.fillRect(0,0,larg,
                          alt);
                    desenhar(new Tela(g));
                    strategy.show();
                }
            }
        });
            
        t.start();
    }

    private static String keyString(KeyEvent evt) {
        if(evt.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
            return String.valueOf(evt.getKeyChar()).toLowerCase();
        } else {
            switch(evt.getKeyCode()) {
            case KeyEvent.VK_ALT: return "alt";
            case KeyEvent.VK_CONTROL: return "control";
            case KeyEvent.VK_SHIFT: return "shift";
            case KeyEvent.VK_LEFT: return "left";
            case KeyEvent.VK_RIGHT: return "right";
            case KeyEvent.VK_UP: return "up";
            case KeyEvent.VK_DOWN: return "down";
            case KeyEvent.VK_ENTER: return "enter";
            case KeyEvent.VK_DELETE: return "delete";
            case KeyEvent.VK_TAB: return "tab";
            case KeyEvent.VK_WINDOWS: return "windows";
            case KeyEvent.VK_BACK_SPACE: return "backspace";
            case KeyEvent.VK_ALT_GRAPH: return "altgr";
            case KeyEvent.VK_F1: return "F1";
            case KeyEvent.VK_F2: return "F2";
            case KeyEvent.VK_F3: return "F3";
            case KeyEvent.VK_F4: return "F4";
            case KeyEvent.VK_F5: return "F5";
            case KeyEvent.VK_F6: return "F6";
            case KeyEvent.VK_F7: return "F7";
            case KeyEvent.VK_F8: return "F8";
            case KeyEvent.VK_F9: return "F9";
            case KeyEvent.VK_F10: return "F10";
            case KeyEvent.VK_F11: return "F11";
            case KeyEvent.VK_F12: return "F12";
            default: return "";
            }
        }
    }

    public static void tocar(String filename) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();
        } catch(Exception e) {
        }
    }
    
    public void tique(java.util.Set<String> ts, double dt) {}
    
    public void tecla(String t) {
        Componente c = teclas.get(t);
        if(c != null) c.clique(0, 0);
    }
    
    public void desenhar(Tela t) {
        for(Componente c: componentes) c.desenhar(t);
    }
    
    public void movimento(int x, int y) {}
    
    public void clique(int x, int y) {
        for(Componente c: componentes) {
            if(c.dentro(x, y)) {
                c.clique(x, y);
            }
        }
    }

    public void arrasto(int x, int y) {
        if(foco != null) {
            foco.arrasto(x, y);
        }
    }

    public void solta(int x, int y) {
        if(foco != null) {
            foco.solta(x, y);
            foco = null;
        }
    }

    public void aperto(int x, int y) {
        for(Componente c: componentes) {
            if(c.dentro(x, y)) {
                c.aperto(x, y);
                foco = c;
            }
        }
    }
    
    public abstract void sair(); // template method
}