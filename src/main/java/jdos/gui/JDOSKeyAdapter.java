package jdos.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JDOSKeyAdapter extends KeyAdapter {
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
}
