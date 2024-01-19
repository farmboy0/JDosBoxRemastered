package jdos.gui;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.MemoryImageSource;

import javax.swing.JFrame;

import jdos.Dosbox;
import jdos.host.FowardPCapEthernet;
import jdos.misc.Cross;
import jdos.misc.setup.Property;
import jdos.misc.setup.Section_prop;
import jdos.sdl.GUI;

public final class MainFrame implements GUI {

    private static MainFrame mainFrame;
    private static Thread mainThread;

    private final JFrame frame;
    private final JDOSRenderPanel panel;
    private final Cursor transparentCursor;

    public MainFrame() throws AWTException {
        panel = new JDOSRenderPanel();

        frame = new JFrame();
        frame.setFocusTraversalKeysEnabled(false);
        frame.addFocusListener(new JDOSFocusAdapter());
        frame.addKeyListener(new JDOSKeyAdapter());
        frame.addWindowFocusListener(new JDOSWindowAdapter());
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                synchronized (MainBase.pauseMutex) {
                    MainBase.pauseMutex.notifyAll();
                }
                MainBase.addEvent(null);
                try {
                    mainThread.join(5000);
                } catch (InterruptedException ex) {
                }
                mainFrame.saveFramePosition();
            }
        });
        frame.add(panel, BorderLayout.PAGE_START);

        int[] pixels = new int[16 * 16];
        Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));
        transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "invisibleCursor");
    }

    @Override
    public void showProgress(String msg, int percent) {

    }

    @Override
    public void captureMouse(boolean on) {
        panel.centerMouse();
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
        frame.dispose();
        frame.setVisible(false);

        if (panel.isFullscreen()) {
            panel.setFullscreen(false);

            frame.setUndecorated(false);
            frame.setResizable(true);
            frame.setExtendedState(Frame.NORMAL);
            setLocation();
            setSize(Main.screen_width, Main.screen_height);
        } else {
            frame.setUndecorated(true);
            frame.setResizable(false);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            frame.setLocation(0, 0);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            setSize(screenSize.width, screenSize.height);

            panel.setFullscreen(true);
        }
    }

    @Override
    public void setLocation() {
        Section_prop sdlSection = (Section_prop) Dosbox.control.GetSection("sdl");
        frame.setLocation(sdlSection.Get_int("posx"), sdlSection.Get_int("posy"));
    }

    @Override
    public void setSize(int width, int height) {
        if (panel.isFullscreen())
            return;
        Dimension size = new Dimension(width, height);
        panel.setMinimumSize(size);
        panel.setMaximumSize(size);
        panel.setPreferredSize(size);
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
        frame.setTitle(title);
    }

    public void saveFramePosition() {
        Section_prop sdlSection = (Section_prop) Dosbox.control.GetSection("sdl");
        Property xprop = sdlSection.byname("posx");
        if (xprop == null) {
            sdlSection.Add_int("posx", Property.Changeable.Always, (int) frame.getLocation().getX());
        } else {
            xprop.SetValue(String.valueOf((int) frame.getLocation().getX()));
        }
        Property yprop = sdlSection.byname("posy");
        if (yprop == null) {
            sdlSection.Add_int("posy", Property.Changeable.Always, (int) frame.getLocation().getY());
        } else {
            yprop.SetValue(String.valueOf((int) frame.getLocation().getY()));
        }

        String path = Cross.CreatePlatformConfigDir() + Cross.GetPlatformConfigName();
        Dosbox.control.PrintConfig(path);
    }

    public static void main(final String[] args) throws AWTException {
        if (args.length > 1 && args[0].equalsIgnoreCase("-pcap")) {
            startServer(args);
            return;
        }

        mainFrame = new MainFrame();
        mainThread = new Thread(() -> MainBase.main(mainFrame, args), "Main Thread");
        mainThread.start();
    }

    private static void startServer(String[] args) {
        String nic = args[1];
        int port = 15654;
        if (args.length > 3 && args[2].equalsIgnoreCase("-pcapport")) {
            try {
                port = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
            }
        }
        FowardPCapEthernet.startServer(nic, port);
    }
}
