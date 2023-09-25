package jdos.win.system;

public class WinUser extends WinObject {
    private WinUser(int id) {
        super(id);
    }

    public static WinUser create() {
        return new WinUser(nextObjectId());
    }

    public static WinUser get(int handle) {
        WinObject object = getObject(handle);
        if (object == null || !(object instanceof WinUser))
            return null;
        return (WinUser) object;
    }
}
