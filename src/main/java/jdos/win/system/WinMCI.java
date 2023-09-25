package jdos.win.system;

import jdos.win.builtin.WinAPI;
import jdos.win.builtin.user32.WinWindow;

public abstract class WinMCI extends WinObject {
    public static final int MCI_NOTIFY_SUCCESSFUL = 0x0001;
    public static final int MCI_NOTIFY_SUPERSEDED = 0x0002;
    public static final int MCI_NOTIFY_ABORTED = 0x0004;
    public static final int MCI_NOTIFY_FAILURE = 0x0008;
    protected int hWnd;

    public WinMCI(int id) {
        super(id);
    }

    public static WinMCI getMCI(int handle) {
        WinObject object = getObject(handle);
        if (object == null || !(object instanceof WinMCI))
            return null;
        return (WinMCI) object;
    }

    public abstract void play(int from, int to, int hWndCallback, boolean wait);

    public abstract void stop(int hWndCallback, boolean wait);

    public abstract void close(int hWndCallback, boolean wait);

    public void sendNotification(int reason) {
        WinWindow window = WinWindow.get(hWnd);
        if (window != null) {
            window.getThread().postMessage(hWnd, WinAPI.MM_MCINOTIFY, reason, handle);
        }
    }
}
