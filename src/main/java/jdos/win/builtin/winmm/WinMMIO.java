package jdos.win.builtin.winmm;

import jdos.win.system.WinObject;

public class WinMMIO extends WinObject {
    public MMIOINFO info = new MMIOINFO();
    public boolean bTmpIOProc;
    public Mmio.IOProc ioProc;
    public boolean bBufferLoaded;
    public int dwFileSize;
    public WinMMIO(int id) {
        super(id);
    }

    static public WinMMIO create() {
        return new WinMMIO(nextObjectId());
    }

    static public WinMMIO get(int handle) {
        WinObject object = getObject(handle);
        if (object == null || !(object instanceof WinMMIO))
            return null;
        return (WinMMIO) object;
    }

    protected void onFree() {
        super.onFree();
    }
}
