package jdos.gui;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
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
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

import jdos.Dosbox;
import jdos.sdl.GUI;
import jdos.util.FileHelper;
import jdos.util.Progress;
import jdos.util.StringHelper;
import jdos.util.UnZip;

public class MainApplet extends Applet implements GUI, KeyListener, Runnable, MouseListener, MouseMotionListener {
    private static final String base_dir = ".jdosbox";
    static Thread thread;
    private static int current_id = 0;
    private static int fullscreen_cx = 0;
    private static int fullscreen_cy = 0;
    private static int monitor_cx = 0;
    private static int monitor_cy = 0;
    private static int fullscreen_cx_offset = 0;
    int[] pixels = new int[16 * 16];
    Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));
    Cursor transparentCursor = Toolkit.getDefaultToolkit()
        .createCustomCursor(image, new Point(0, 0), "invisibleCursor");
    Frame frame = new Frame();
    Container parent;
    boolean fullscreen = false;
    Graphics bufferGraphics;
    Image offscreen;
    private String progressMsg = null;
    private int progressPercent = 0;
    private long progressTotal = 0;
    private long progressCompleted = 0;
    Progress progressBar = new Progress() {
        @Override
        public void set(int value) {
        }

        @Override
        public void status(String value) {
            progressMsg = value;
            repaint();
        }

        @Override
        public void done() {
        }

        @Override
        public boolean hasCancelled() {
            return false;
        }

        @Override
        public void speed(String value) {
        }

        @Override
        public void initializeSpeedValue(long totalExpected) {
            progressTotal = totalExpected;
            progressCompleted = 0;
        }

        @Override
        public void incrementSpeedValue(long value) {
            progressCompleted += value;
            progressPercent = (int) (progressCompleted * 100 / progressTotal);
            repaint();
        }
    };
    private Color backgroundColor = Color.darkGray;
    private int id;

    @Override
    public void showProgress(String msg, int percent) {
        progressMsg = msg;
        progressPercent = percent;
        repaint();
    }

    @Override
    public void captureMouse(boolean on) {
        if (MainFrame.robot != null) {
            MainFrame.robotCenter(getLocationOnScreen());
        }
    }

    @Override
    public void showCursor(boolean on) {
        if (on)
            setCursor(Cursor.getDefaultCursor());
        else
            setCursor(transparentCursor);
    }

    @Override
    public void fullScreenToggle() {
        if (!fullscreen) {
            if (this.parent == null)
                this.parent = getParent();
            this.frame = new Frame();

            Toolkit tk = Toolkit.getDefaultToolkit();
            monitor_cx = fullscreen_cx = (int) tk.getScreenSize().getWidth();
            monitor_cy = fullscreen_cy = (int) tk.getScreenSize().getHeight();
            frame.dispose();
            frame.setVisible(false);
            this.frame.add(this);
            frame.setUndecorated(true);
            frame.setResizable(false);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            frame.setVisible(true);
            fullscreen_cx_offset = 0;
            if ((float) fullscreen_cx / fullscreen_cy > 4.0 / 3.0) {
                int new_fullscreen_cx = fullscreen_cy * 4 / 3;
                fullscreen_cx_offset = (fullscreen_cx - new_fullscreen_cx) / 2;
                fullscreen_cx = new_fullscreen_cx;
            }
            this.fullscreen = true;
        } else {
            if (this.parent != null)
                this.parent.add(this);
            if (this.frame != null) {
                this.frame.dispose();
                this.frame = null;
            }
            this.fullscreen = false;
        }
        this.setBounds(0, 0, this.getParent().getSize().width, this.getParent().getSize().height);
        this.requestFocus();
    }

    @Override
    public void setSize(int width, int height) {
        //resize(width, height);
    }

    @Override
    public void dopaint() {
        repaint();
    }

    @Override
    public void setTitle(String title) {
    }

    private boolean download(String urlLocation, File location) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            progressMsg = "Downloading " + location.getName();
            progressPercent = 0;
            repaint();
            if (location.exists()) {
                location.delete();
            }
            if (!location.getParentFile().exists()) {
                location.getParentFile().mkdirs();
            }
            URL url = new URL(urlLocation);
            URLConnection urlc = url.openConnection();
            long size = -1;
            String s = urlc.getHeaderField("content-length");
            if (s != null) {
                size = Long.parseLong(s);
            }
            bis = new BufferedInputStream(urlc.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(location));

            byte[] buffer = new byte[4096];
            int read = 0;
            if (size > 0)
                progressBar.initializeSpeedValue(size);
            do {
                read = bis.read(buffer);
                if (read > 0) {
                    progressBar.incrementSpeedValue(read);
                    bos.write(buffer, 0, read);
                }
            } while (read > 0);
            bis.close();
            bis = null;
            bos.close();
            bos = null;
            if (size > 0 && location.length() != size) {
                System.out.println("FAILED to download file: " + location.getAbsolutePath());
                System.out.println("   expected " + size + " bytes and got " + location.length() + " bytes");
                progressMsg = "FAILED to download file: " + urlLocation;
                progressPercent = 0;
                repaint();
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                try {
                    bis.close();
                } catch (Exception e) {
                }
            if (bos != null)
                try {
                    bos.close();
                } catch (Exception e) {
                }
        }
        progressMsg = "FAILED to download file: " + urlLocation;
        progressPercent = 0;
        repaint();
        return false;
    }

    private void unzip(File file, File directory) {
        UnZip.unzip(file.getAbsolutePath(), directory.getAbsolutePath(), progressBar);
    }

    private Color parseColorStr(String s) {
        int r, g, b;
        if (s.length() == 7 && s.charAt(0) == '#') {
            try {
                r = Integer.parseInt(s.substring(1, 3), 16);
                g = Integer.parseInt(s.substring(3, 5), 16);
                b = Integer.parseInt(s.substring(5, 7), 16);
                return new Color(r, g, b);
            } catch (Exception e) {
            }
        }
        return Color.black;
    }

    @Override
    public void run() {
        System.out.println("About to start DosBox");
        // Not sure why this pause helps so much or what the right value is
        // Perhaps during a page reload this gives the first copy of this applet
        // a chance to clean up
        //try {Thread.sleep(5000);} catch (Exception e) {};
        for (int i = 1; i < 10; i++) {
            try {
                String url = getParameter("download" + i);
                if (url != null) {
                    String fullName = url.substring(url.lastIndexOf('/') + 1);
                    String name = url.substring(url.lastIndexOf('/') + 1, url.lastIndexOf('.'));
                    File dir = new File(
                        FileHelper.getHomeDirectory() + File.separator + base_dir + File.separator + name);
                    if (!dir.exists()) {
                        File downloadFile = new File(FileHelper.getHomeDirectory() + File.separator + base_dir
                            + File.separator + "temp" + File.separator + fullName);
                        if (download(url, downloadFile)) {
                            unzip(downloadFile, dir);
                            downloadFile.delete();
                        } else {
                            return; // no reason to start dosbox
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("download parameter should be the url of a .zip");
                e.printStackTrace();
            }
        }
        String bg = getParameter("background-color");
        if (bg != null) {
            backgroundColor = parseColorStr(bg);
        }
        progressMsg = null;
        progressPercent = 0;
        repaint();
        Vector params = new Vector();
        params.add("-applet");
        for (int i = 1; i < 10; i++) {
            String param = getParameter("param" + i);
            if (param == null) {
                break;
            }
            if (param.startsWith("-")) {
                String[] p = StringHelper.split(param, " ");
                for (String element : p) {
                    params.addElement(element);
                }
            } else {
                params.add("-c");
                params.add(param);
            }
        }
        String[] cmds = new String[params.size()];
        params.copyInto(cmds);

        // should help with the tab key in a browser
        setFocusTraversalKeysEnabled(false);
        MainBase.main(this, cmds);
    }

    @Override
    public void init() {
        System.out.println("Applet.init()");
        try {
            if (MainFrame.robot == null) {
                MainFrame.robot = new Robot();
            }
        } catch (Throwable e) {
            System.out.println("Applet is not signed");
            System.out.println("    mouse capture will not work");
            System.out.println("    disabling compiler");
            Dosbox.allPrivileges = false;
        }
        if (thread != null) {
            System.out.println("Applet.init force stop");
            i_stop();
        }
        setBackground(Color.black);
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);

        addFocusListener(new FocusListener() {
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
    }

    @Override
    public void destroy() {
        removeKeyListener(this);
        removeMouseMotionListener(this);
        removeMouseListener(this);
    }

    @Override
    public void start() {
        current_id++;
        id = current_id;
        System.out.println("Applet.start");
        if (thread != null) {
            System.out.println("Applet.start force stop");
            i_stop();
        }
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void stop() {
        if (id == current_id)
            i_stop();
    }

    public void i_stop() {
        System.out.println("Applet.stop");
        synchronized (MainBase.pauseMutex) {
            MainBase.pauseMutex.notify();
        }
        MainBase.addEvent(null);
        try {
            thread.join(5000);
        } catch (Exception e) {
        }
        thread = null;
        // Without this the 2nd time you run the applet after starting a browswer
        // it might run out of memory.  Not sure why
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
    }

    private void drawProgress(Graphics g, int width, int height) {
        int yOffset = 5;

        FontMetrics fm = g.getFontMetrics(g.getFont());
        java.awt.geom.Rectangle2D rect = fm.getStringBounds(progressMsg, g);
        int barHeight = (int) (rect.getHeight() * 1.7);
        int textHeight = (int) rect.getHeight();
        int textWidth = (int) rect.getWidth();
        int x = (width - textWidth) / 2;
        int y = height - barHeight + (barHeight - textHeight) / 2 + fm.getAscent();

        int right = width * progressPercent / 100;
        g.setColor(Color.white);
        g.fillRect(0, height - barHeight - yOffset, right, barHeight);

        g.setClip(right, 0, width, height);
        g.setColor(Color.yellow);
        g.drawString(progressMsg, x, y - yOffset);

        g.setClip(0, 0, right, height);
        g.setColor(Color.black);
        g.drawString(progressMsg, x, y - yOffset);
        g.setClip(0, 0, width, height);
    }

    private int getScreenX() {
        if (Main.screen_width < this.getWidth()) {
            return (getWidth() - Main.screen_width) / 2;
        }
        return 0;
    }

    private int getScreenY() {
        if (Main.screen_height < this.getHeight()) {
            return (getHeight() - Main.screen_height) / 2;
        }
        return 0;
    }

    private void draw(Graphics g) {
        if (fullscreen) {
            if (fullscreen_cx_offset > 0) {
                g.setColor(backgroundColor);
                g.fillRect(0, 0, fullscreen_cx_offset, fullscreen_cy);
                g.fillRect(monitor_cx - fullscreen_cx_offset, 0, fullscreen_cx_offset, fullscreen_cy);
            }
            g.drawImage(Main.buffer2[Main.front], fullscreen_cx_offset, 0, fullscreen_cx + fullscreen_cx_offset,
                fullscreen_cy, 0, 0, Main.buffer_width, Main.buffer_height, null);
        } else {
            int x = 0;
            int y = 0;
            if (Main.screen_width < this.getWidth()) {
                x = getScreenX();
                g.setColor(backgroundColor);
                g.fillRect(0, 0, x, getHeight());
                g.fillRect(x + Main.screen_width, 0, getWidth() - (x + Main.screen_width), getHeight());
            }
            if (Main.screen_height < this.getHeight()) {
                y = getScreenY();
                g.setColor(backgroundColor);
                g.fillRect(0, 0, getWidth(), y);
                g.fillRect(0, y + Main.screen_height, getWidth(), getHeight() - (y + Main.screen_height));
            }
            if (Render.render != null && Render.render.aspect && Main.screen_height % Main.buffer_height != 0) {
                BufferedImage resized = MainFrame.resizeImage(Main.buffer2[Main.front], Main.screen_width,
                    Main.screen_height, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g.drawImage(resized, x, y, Main.screen_width + x, Main.screen_height + y, 0, 0, Main.screen_width,
                    Main.screen_height, null);
            } else {
                g.drawImage(Main.buffer2[Main.front], x, y, Main.screen_width + x, Main.screen_height + y, 0, 0,
                    Main.buffer_width, Main.buffer_height, null);
            }
        }
    }

    @Override
    public void update(Graphics g) {
        if (Main.buffer2[Main.front] != null) {
            synchronized (MainBase.paintMutex) {
                if (progressMsg != null) {
                    drawProgress(Main.buffer2[Main.front].getGraphics(), Main.screen_width, Main.screen_height);
                }
                draw(g);
            }
        } else if (progressMsg != null) {
            if (bufferGraphics == null) {
                Rectangle r = g.getClipBounds();
                offscreen = createImage(r.width, r.height);
                bufferGraphics = offscreen.getGraphics();
            }
            bufferGraphics.clearRect(0, 0, offscreen.getWidth(null), offscreen.getHeight(null));
            drawProgress(bufferGraphics, offscreen.getWidth(null), offscreen.getHeight(null));
            g.drawImage(offscreen, 0, 0, null);
        }
    }

    @Override
    public void paint(Graphics g) {
        update(g);
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
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
            MainBase.GFX_CaptureMouse();
        }
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
    public void mouseDragged(MouseEvent e) {
        MainFrame.robotMouse(e, getLocationOnScreen(), getScreenX(), getScreenY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MainFrame.robotMouse(e, getLocationOnScreen(), getScreenX(), getScreenY());
    }
}