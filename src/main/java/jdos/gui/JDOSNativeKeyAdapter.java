package jdos.gui;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class JDOSNativeKeyAdapter implements NativeKeyListener {

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println(e.paramString());
        Main.addKeyEvent(e);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        Main.addKeyEvent(e);
    }

}
