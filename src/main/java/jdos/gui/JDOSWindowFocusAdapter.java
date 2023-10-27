package jdos.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class JDOSWindowFocusAdapter implements WindowFocusListener {

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
