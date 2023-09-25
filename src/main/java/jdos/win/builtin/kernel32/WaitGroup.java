package jdos.win.builtin.kernel32;

import java.util.Vector;

import jdos.win.system.Scheduler;

public class WaitGroup {
    public Vector<WaitObject> objects = new Vector<>();
    public WinThread thread;

    public WaitGroup(WinThread thread) {
        this.thread = thread;
    }

    public WaitGroup(WinThread thread, WaitObject waitObject) {
        this.thread = thread;
        this.objects.add(waitObject);
    }

    public boolean released() {
        for (WaitObject object : objects) {
            if (!object.isReady())
                return false;
        }
        for (WaitObject object : objects) {
            object.get(this);
        }
        Scheduler.addThread(thread, false);
        return true;
    }
}
