package jdos.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JDOSWindowAdapter extends WindowAdapter {

    @Override
    public void windowGainedFocus(WindowEvent e) {
        MainBase.addEvent(new Main.FocusChangeEvent(true));
        if (!MainBase.keyboardPaused) {
            synchronized (MainBase.pauseMutex) {
                MainBase.pauseMutex.notifyAll();
            }
        }
    }

    @Override
    public void windowLostFocus(WindowEvent e) {
        MainBase.addEvent(new Main.FocusChangeEvent(false));
    }
}
