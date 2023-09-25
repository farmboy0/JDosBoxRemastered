package jdos.win.system;

import jdos.win.builtin.kernel32.WaitObject;

public class WinMutex extends WaitObject {
    private WinMutex(int handle, String name) {
        super(handle, name);
    }

    public static WinMutex create(String name) {
        return new WinMutex(nextObjectId(), name);
    }

    public static WinMutex get(int handle) {
        WinObject object = getObject(handle);
        if (object == null || !(object instanceof WinMutex))
            return null;
        return (WinMutex) object;
    }
}
