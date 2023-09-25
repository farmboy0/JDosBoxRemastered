package jdos.win.builtin.gdi32;

import jdos.win.system.WinObject;

public class WinEnhancedMetaFile extends WinObject {
    public WinEnhancedMetaFile(int id) {
        super(id);
    }

    public static WinEnhancedMetaFile create(int style, int color, int hatch) {
        return new WinEnhancedMetaFile(nextObjectId());
    }

    public static WinEnhancedMetaFile get(int handle) {
        WinObject object = getObject(handle);
        if (object == null || !(object instanceof WinEnhancedMetaFile))
            return null;
        return (WinEnhancedMetaFile) object;
    }
}
