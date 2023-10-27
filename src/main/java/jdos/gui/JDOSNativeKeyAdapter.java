package jdos.gui;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class JDOSNativeKeyAdapter implements NativeKeyListener {

    public static final int VC_LESS = 0; // TODO: no keycode with jnativehook
    public static final int VC_PLUS = 0xE4E; // TODO: no constant with jnativehook
    public static final int VC_SUBTRACT = 0xE4A; // TODO: no constant with jnativehook

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
//        System.out.println(e.paramString());
        Main.addKeyEvent(e);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        Main.addKeyEvent(e);
    }
}
