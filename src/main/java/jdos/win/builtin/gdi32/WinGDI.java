package jdos.win.builtin.gdi32;

import jdos.win.system.WinObject;

public class WinGDI extends WinObject {
    public WinGDI(int handle) {
        super(handle);
    }

    public static WinGDI getGDI(int handle) {
        WinObject object = getObject(handle);
        if (object == null || !(object instanceof WinGDI))
            return null;
        return (WinGDI) object;
    }
}
