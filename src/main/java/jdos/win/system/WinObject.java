package jdos.win.system;

import jdos.win.Win;
import jdos.win.builtin.WinAPI;

public class WinObject extends WinAPI {
    public String name;
    public int handle;
    public Object data;
    private int refCount = 0;

    public WinObject(String name, int handle) {
        this.name = name;
        this.handle = handle;
        if (handle > 0) {
            if (StaticData.objects.put(handle, this) != null) {
                Win.panic("Object handle collision: handle=" + handle);
            }
            if (name != null && name.length() > 0)
                StaticData.namedObjects.put(name, this);
            open();
        }
    }

    public WinObject(int handle) {
        this.handle = handle;
        this.name = null;
        if (handle > 0) {
            StaticData.objects.put(handle, this);
            open();
        }
    }

    public static WinObject createWinObject() {
        return new WinObject(nextObjectId());
    }

    protected static int nextObjectId() {
        return StaticData.nextObjectId++;
    }

    public static WinObject getObject(int handle) {
        return StaticData.objects.get(Integer.valueOf(handle));
    }

    public static WinObject getNamedObject(String name) {
        return StaticData.namedObjects.get(name);
    }

    public int getHandle() {
        return handle;
    }

    public void open() {
        if (refCount >= 0)
            refCount++;
    }

    public void close() {
        if (refCount >= 0) {
            refCount--;
            if (refCount == 0) {
                StaticData.objects.remove(handle);
                if (name != null && name.length() > 0)
                    StaticData.namedObjects.remove(name);
                onFree();
            }
        }
    }

    protected void onFree() {

    }

    public void makePermanent() {
        refCount = -1;
    }
}
