package jdos.gui;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import jdos.ints.Mouse;

public class JDOSMouseAdapter extends MouseAdapter {
    private boolean eatNextMouseMove = false;
    private int lastX;
    private int lastY;
    private Robot robot;
    private final JPanel panel;

    public JDOSMouseAdapter(JPanel panel) throws AWTException {
        this.panel = panel;
        this.robot = new Robot();
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

    void centerMouse() {
        robotCenter(panel.getLocationOnScreen());
    }

    private void robotMouse(MouseEvent e, Point rel, int offX, int offY) {
        if (eatNextMouseMove) {
            lastX = e.getX() - offX;
            lastY = e.getY() - offY;
            eatNextMouseMove = false;
            return;
        }
        if (MainBase.mouse_locked) {
            int rel_x = e.getX() - lastX - offX;
            int rel_y = e.getY() - lastY - offY;
            float abs_x = (Mouse.mouse.x + rel_x * MainBase.mouse_sensitivity / 100.0f) / Mouse.mouse.max_x;
            float abs_y = (Mouse.mouse.y + rel_y * MainBase.mouse_sensitivity / 100.0f) / Mouse.mouse.max_y;
            Main.addMouseEvent(new Main.MouseEvent2(e, rel_x, rel_y, abs_x, abs_y, offX, offY));
            robotCenter(rel);
        } else {
            Main.addMouseEvent(new Main.MouseEvent1(e, offX, offY));
        }
    }

    private void robotCenter(Point rel) {
        eatNextMouseMove = true;
        robot.mouseMove(rel.x + 200, rel.y + 200);
    }
}
