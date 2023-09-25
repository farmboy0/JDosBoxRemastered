package jdos.win.system;

import java.awt.event.MouseEvent;

import jdos.gui.Main;
import jdos.win.builtin.WinAPI;
import jdos.win.builtin.user32.Input;

public class WinMouse {
    public static Main.MouseHandler defaultMouseHandler = event -> {
        int msg = 0;
        int wParam = 0;
        WinPoint point = new WinPoint(event.getX(), event.getY());

        if (event.getID() == MouseEvent.MOUSE_MOVED || event.getID() == MouseEvent.MOUSE_DRAGGED) {
            msg = WinAPI.WM_MOUSEMOVE;
        } else if (event.getID() == MouseEvent.MOUSE_PRESSED) {
            if (event.getButton() == MouseEvent.BUTTON1) {
                msg = WinAPI.WM_LBUTTONDOWN;
            } else if (event.getButton() == MouseEvent.BUTTON2) {
                msg = WinAPI.WM_MBUTTONDOWN;
            } else if (event.getButton() == MouseEvent.BUTTON3) {
                msg = WinAPI.WM_RBUTTONDOWN;
            }
        } else if (event.getID() == MouseEvent.MOUSE_RELEASED) {
            if (event.getButton() == MouseEvent.BUTTON1) {
                msg = WinAPI.WM_LBUTTONUP;
            } else if (event.getButton() == MouseEvent.BUTTON2) {
                msg = WinAPI.WM_MBUTTONUP;
            } else if (event.getButton() == MouseEvent.BUTTON3) {
                msg = WinAPI.WM_RBUTTONUP;
            }
        }
        if (msg == 0) {
            System.out.println("Unknown mouse message: " + event.toString());
            return;
        }
        StaticData.currentPos = point.copy();
        Input.addMouseMsg(msg, point, wParam);
    };
}
