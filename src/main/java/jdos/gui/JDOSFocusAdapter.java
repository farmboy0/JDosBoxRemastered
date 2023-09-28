package jdos.gui;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

public class JDOSFocusAdapter extends FocusAdapter {
    private final KeyEventDispatcher altDisabler = e -> {
        if (e.getKeyCode() == KeyEvent.VK_ALT) {
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
}
