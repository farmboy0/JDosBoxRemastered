package jdos.gui;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public final class JDOSRenderPanel extends JPanel {
    private final JDOSMouseAdapter mouseAdapter;

    private BufferedImage tmpImage;
    private boolean fullscreen;
    private int fsWidth, fsHeight, fsXOffset;

    public JDOSRenderPanel() throws AWTException {
        this.mouseAdapter = new JDOSMouseAdapter(this);
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
        if (!fullscreen)
            return;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        fsWidth = (int) screenSize.getWidth();
        fsHeight = (int) screenSize.getHeight();
        fsXOffset = 0;
        if ((float) fsWidth / fsHeight > 4.0 / 3.0) {
            int newfsWidth = fsHeight * 4 / 3;
            fsXOffset = (fsWidth - newfsWidth) / 2;
            fsWidth = newfsWidth;
        }
    }

    public void centerMouse() {
        mouseAdapter.centerMouse();
    }

    @Override
    public void paint(Graphics g) {
        if (!Render.running || Main.buffer2[Main.front] == null) {
            return;
        }
        synchronized (MainBase.paintMutex) {
            if (isFullscreen()) {
                g.drawImage(Main.buffer2[Main.front], fsXOffset, 0, fsWidth + fsXOffset, fsHeight, 0, 0,
                    Main.buffer_width, Main.buffer_height, null);
            } else if (Render.render.aspect && Main.screen_height % Main.buffer_height != 0) {
                BufferedImage resized = resizeImage(Main.buffer2[Main.front], Main.screen_width, Main.screen_height,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g.drawImage(resized, 0, 0, Main.screen_width, Main.screen_height, 0, 0, Main.screen_width,
                    Main.screen_height, null);
            } else {
                g.drawImage(Main.buffer2[Main.front], 0, 0, Main.screen_width, Main.screen_height, 0, 0,
                    Main.buffer_width, Main.buffer_height, null);
            }
        }
    }

    private BufferedImage resizeImage(BufferedImage source, int destWidth, int destHeight, Object interpolation) {
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

    private GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }
}