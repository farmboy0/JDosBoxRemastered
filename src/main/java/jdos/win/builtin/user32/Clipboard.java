package jdos.win.builtin.user32;

public class Clipboard {
    // HWND WINAPI GetClipboardOwner(void);
    public static int GetClipboardOwner() {
        return 0;
    }

    public static boolean CLIPBOARD_ReleaseOwner() {
        return true;
    }
}
