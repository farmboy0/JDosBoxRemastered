package jdos.win.builtin.user32;

import jdos.win.builtin.WinAPI;

public class Caret extends WinAPI {
    // BOOL WINAPI DestroyCaret(void);
    public static int DestroyCaret() {
        return TRUE;
    }

    // BOOL WINAPI HideCaret( HWND hwnd )
    public static int HideCaret(int hwnd) {
        return TRUE;
    }

    // BOOL WINAPI ShowCaret(HWND hWnd)
    public static int ShowCaret(int hWnd) {
        return TRUE;
    }
}
