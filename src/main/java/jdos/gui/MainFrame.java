package jdos.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jdos.Dosbox;
import jdos.ints.Mouse;
import jdos.misc.Cross;
import jdos.misc.setup.Property;
import jdos.misc.setup.Section_prop;
import jdos.sdl.GUI;

public class MainFrame implements GUI {
    static Robot robot;
    static BufferedImage tmpImage;
    private static boolean eatNextMouseMove = false;
    private static int last_x;
    private static int last_y;
    private static boolean fullscreen = false;
    private static int fullscreen_cx = 0;
    private static int fullscreen_cy = 0;
    private static int fullscreen_cx_offset = 0;
    private static Thread mainThread;
    protected static JFrame frame;
    private static JPanel panel;
    int[] pixels = new int[16 * 16];
    Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));
    Cursor transparentCursor = Toolkit.getDefaultToolkit()
        .createCustomCursor(image, new Point(0, 0), "invisibleCursor");

    public static void robotMouse(MouseEvent e, Point rel, int offX, int offY) {
        if (eatNextMouseMove) {
            last_x = e.getX() - offX;
            last_y = e.getY() - offY;
            eatNextMouseMove = false;
            return;
        }
        if (!MainBase.mouse_locked || robot == null)
            Main.addMouseEvent(new Main.MouseEvent1(e, offX, offY));
        else {
            int rel_x = e.getX() - last_x - offX;
            int rel_y = e.getY() - last_y - offY;
            float abs_x = (Mouse.mouse.x + rel_x * MainBase.mouse_sensitivity / 100.0f) / Mouse.mouse.max_x;
            float abs_y = (Mouse.mouse.y + rel_y * MainBase.mouse_sensitivity / 100.0f) / Mouse.mouse.max_y;
            Main.addMouseEvent(new Main.MouseEvent2(e, rel_x, rel_y, abs_x, abs_y, offX, offY));
            robotCenter(rel);
        }
    }

    public static void robotCenter(Point rel) {
        eatNextMouseMove = true;
        robot.mouseMove(rel.x + 200, rel.y + 200);
    }

    public static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }

    public static BufferedImage resizeImage(BufferedImage source, int destWidth, int destHeight, Object interpolation) {
        if (source == null)
            throw new NullPointerException("source image is NULL!");
        if (destWidth <= 0 && destHeight <= 0)
            throw new IllegalArgumentException("destination width & height are both <=0!");
        int sourceWidth = source.getWidth();
        int sourceHeight = source.getHeight();
        double xScale = (double) destWidth / (double) sourceWidth;
        double yScale = (double) destHeight / (double) sourceHeight;
        if (destWidth <= 0) {
            xScale = yScale;
            destWidth = (int) Math.rint(xScale * sourceWidth);
        }
        if (destHeight <= 0) {
            yScale = xScale;
            destHeight = (int) Math.rint(yScale * sourceHeight);
        }
        if (tmpImage == null) {
            try {
                GraphicsConfiguration gc = getDefaultConfiguration();
                tmpImage = gc.createCompatibleImage(destWidth, destHeight, source.getColorModel().getTransparency());
            } catch (Throwable e) {
                tmpImage = new BufferedImage(destWidth, destHeight, source.getColorModel().getTransparency());
            }
        }
        Graphics2D g2d = null;
        try {
            g2d = tmpImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, interpolation);
            AffineTransform at = AffineTransform.getScaleInstance(xScale, yScale);
            g2d.drawRenderedImage(source, at);
        } finally {
            if (g2d != null)
                g2d.dispose();
        }
        return tmpImage;
    }

    public static void main(final String[] args) {
        if (args.length > 1 && args[0].equalsIgnoreCase("-pcap")) {
            String nic = args[1];
            int port = 15654;
            if (args.length > 3 && args[2].equalsIgnoreCase("-pcapport")) {
                try {
                    port = Integer.parseInt(args[3]);
                } catch (Exception e) {
                }
            }
            try {
                Class c = Class.forName("jdos.host.FowardPCapEthernet");
                Method method = c.getDeclaredMethod("startServer", String.class, Integer.TYPE);
                method.invoke(null, nic, Integer.valueOf(port));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        try {
            robot = new Robot();
        } catch (Throwable e) {
            System.out.println("Applet is not signed, mouse capture will not work");
        }

        frame = new MyFrame();
        frame.setFocusTraversalKeysEnabled(false);
        frame.addFocusListener(new FocusListener() {
            private final KeyEventDispatcher altDisabler = e -> {
                if (e.getKeyCode() == 18) {
                    MainBase.addEvent(e);
                    return true;
                }
                return false;
            };

            @Override
            public void focusGained(FocusEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(altDisabler);
            }

            @Override
            public void focusLost(FocusEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(altDisabler);
            }
        });

        panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                if (Main.buffer2[Main.front] != null) {
                    synchronized (MainBase.paintMutex) {
                        if (fullscreen) {
                            g.drawImage(Main.buffer2[Main.front], fullscreen_cx_offset, 0,
                                fullscreen_cx + fullscreen_cx_offset, fullscreen_cy, 0, 0, Main.buffer_width,
                                Main.buffer_height, null);
                        } else {
                            if (Render.render.aspect && Main.screen_height % Main.buffer_height != 0) {
                                BufferedImage resized = resizeImage(Main.buffer2[Main.front], Main.screen_width,
                                    Main.screen_height, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                                g.drawImage(resized, 0, 0, Main.screen_width, Main.screen_height, 0, 0,
                                    Main.screen_width, Main.screen_height, null);
                            } else {
                                g.drawImage(Main.buffer2[Main.front], 0, 0, Main.screen_width, Main.screen_height, 0, 0,
                                    Main.buffer_width, Main.buffer_height, null);
                            }
                        }
                    }
                }
            }
        };
        panel.addMouseMotionListener((MyFrame) frame);
        panel.addMouseListener((MyFrame) frame);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                synchronized (MainBase.pauseMutex) {
                    MainBase.pauseMutex.notify();
                }
                MainBase.addEvent(null);
                try {
                    mainThread.join(5000);
                } catch (Exception e1) {
                }
                if (!Dosbox.applet) {
                    Property xprop = ((Section_prop) Dosbox.control.GetSection("sdl")).byname("posx");
                    if (xprop == null) {
                        ((Section_prop) Dosbox.control.GetSection("sdl")).Add_int("posx", Property.Changeable.Always,
                            (int) frame.getLocation().getX());
                    } else {
                        xprop.SetValue(String.valueOf((int) frame.getLocation().getX()));
                    }
                    Property yprop = ((Section_prop) Dosbox.control.GetSection("sdl")).byname("posy");
                    if (yprop == null) {
                        ((Section_prop) Dosbox.control.GetSection("sdl")).Add_int("posy", Property.Changeable.Always,
                            (int) frame.getLocation().getY());
                    } else {
                        yprop.SetValue(String.valueOf((int) frame.getLocation().getY()));
                    }

                    String path = Cross.CreatePlatformConfigDir() + Cross.GetPlatformConfigName();
                    Dosbox.control.PrintConfig(path);
                    System.exit(0);
                }
            }
        });
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.PAGE_START);
        mainThread = new Thread(() -> MainBase.main(new MainFrame(), args));
        mainThread.start();
    }

    @Override
    public void showProgress(String msg, int percent) {

    }

    @Override
    public void captureMouse(boolean on) {
        if (robot != null) {
            robotCenter(panel.getLocationOnScreen());
        }
    }

    @Override
    public void showCursor(boolean on) {
        if (on)
            frame.setCursor(Cursor.getDefaultCursor());
        else
            frame.setCursor(transparentCursor);
    }

    public void setCursor(Cursor cursor) {
        if (cursor == null)
            cursor = transparentCursor;
        frame.setCursor(cursor);
    }

    @Override
    public void fullScreenToggle() {
        if (fullscreen) {
            frame.dispose();
            frame.setVisible(false);
            fullscreen = false;
            frame.setUndecorated(false);
            frame.setExtendedState(Frame.NORMAL);
            setSize(Main.screen_width, Main.screen_height);
        } else {
            Toolkit tk = Toolkit.getDefaultToolkit();
            fullscreen_cx = (int) tk.getScreenSize().getWidth();
            fullscreen_cy = (int) tk.getScreenSize().getHeight();
            frame.dispose();
            frame.setVisible(false);
            frame.setUndecorated(true);
            frame.setResizable(false);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            setSize(fullscreen_cx, fullscreen_cy);
            fullscreen_cx_offset = 0;
            if ((float) fullscreen_cx / fullscreen_cy > 4.0 / 3.0) {
                int new_fullscreen_cx = fullscreen_cy * 4 / 3;
                fullscreen_cx_offset = (fullscreen_cx - new_fullscreen_cx) / 2;
                fullscreen_cx = new_fullscreen_cx;
            }
            fullscreen = true;
        }
    }

    @Override
    public void setSize(int width, int height) {
        if (fullscreen)
            return;
        panel.setMinimumSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));
        panel.setPreferredSize(new Dimension(width, height));
        frame.getContentPane().doLayout();
        frame.pack();
        if (!frame.isVisible())
            frame.setVisible(true);
    }

    @Override
    public void dopaint() {
        panel.repaint();
    }

    @Override
    public void setTitle(String title) {
        if (frame != null)
            frame.setTitle(title);
    }

    private static class MyFrame extends JFrame
        implements KeyListener, WindowFocusListener, WindowListener, MouseListener, MouseMotionListener {
        public MyFrame() {
            addKeyListener(this);
            addWindowFocusListener(this);
            addWindowListener(this);
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        /**
         * Handle the key pressed event from the text field.
         */
        @Override
        public void keyPressed(KeyEvent e) {
            Main.addKeyEvent(e);
        }

        /**
         * Handle the key released event from the text field.
         */
        @Override
        public void keyReleased(KeyEvent e) {
            Main.addKeyEvent(e);
        }

        @Override
        public void windowGainedFocus(WindowEvent e) {
            MainBase.addEvent(new Main.FocusChangeEvent(true));
            if (!MainBase.keyboardPaused) {
                synchronized (MainBase.pauseMutex) {
                    MainBase.pauseMutex.notify();
                }
            }
        }

        @Override
        public void windowLostFocus(WindowEvent e) {
            MainBase.addEvent(new Main.FocusChangeEvent(false));
        }

        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            Main.addMouseEvent(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Main.addMouseEvent(e);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
                MainBase.GFX_CaptureMouse();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            robotMouse(e, panel.getLocationOnScreen(), 0, 0);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            robotMouse(e, panel.getLocationOnScreen(), 0, 0);
        }

    }
}