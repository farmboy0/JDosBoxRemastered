package jdos.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

public class JDOSWindowAdapter extends WindowAdapter {
    @Override
    public void windowOpened(WindowEvent e) {
        // Initialze native hook.
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            ex.printStackTrace();

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new JDOSNativeKeyAdapter());
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //Clean up the native hook.
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem unregistering the native hook.");
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
