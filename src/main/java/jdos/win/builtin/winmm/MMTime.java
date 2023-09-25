package jdos.win.builtin.winmm;

import java.util.Hashtable;

import jdos.cpu.CPU;
import jdos.cpu.CPU_Regs;
import jdos.cpu.Callback;
import jdos.win.builtin.HandlerBase;
import jdos.win.builtin.WinAPI;
import jdos.win.builtin.kernel32.WinEvent;
import jdos.win.builtin.kernel32.WinProcess;
import jdos.win.builtin.kernel32.WinThread;
import jdos.win.kernel.WinCallback;
import jdos.win.system.Scheduler;
import jdos.win.system.WinSystem;

public class MMTime extends WinAPI {
    public static final int MMSYSTIME_MININTERVAL = 1;
    public static final int MMSYSTIME_MAXINTERVAL = 65535;

    public static final int TIMERR_BASE = 96;
    public static final int TIMERR_NOERROR = 0;
    public static final int TIMERR_NOCANDO = TIMERR_BASE + 1;

    public static final int TIME_ONESHOT = 0x0000; /* program timer for single event */
    public static final int TIME_PERIODIC = 0x0001; /* program for continuous periodic event */
    public static final int TIME_CALLBACK_FUNCTION = 0x0000; /* callback is function */
    public static final int TIME_CALLBACK_EVENT_SET = 0x0010; /* callback is event - use SetEvent */
    public static final int TIME_CALLBACK_EVENT_PULSE = 0x0020; /* callback is event - use PulseEvent */
    public static final int TIME_KILL_SYNCHRONOUS = 0x0100;
    private static final Hashtable<Integer, MMTimer> timers = new Hashtable<>();
    private static final Callback.Handler mmTimerThread = new HandlerBase() {
        private long lastCall;

        @Override
        public String getName() {
            return "mmTimerThread";
        }

        @Override
        public void onCall() {
            int esp = CPU_Regs.reg_esp.dword - 4;
            int eip = CPU.CPU_Pop32();
            int id = CPU.CPU_Pop32();
            int threadHandle = CPU.CPU_Pop32();
            int callback = CPU.CPU_Pop32();
            int dwUser = CPU.CPU_Pop32();
            int dwDelay = CPU.CPU_Pop32();

            CPU_Regs.reg_esp.dword = esp; // protect our variables in the stack
            WinThread thread = WinThread.get(threadHandle);
            long start = System.currentTimeMillis();
            //System.out.println("last call "+(start-lastCall)+"ms");
            WinSystem.call(callback, id, 0, dwUser, 0, 0);
            //lastCall = System.currentTimeMillis();
            CPU_Regs.reg_eip = eip;
            CPU_Regs.reg_esp.dword = esp;
            if (dwDelay == 0) {
                Scheduler.removeThread(thread);
                timers.remove(id);
            } else
                Scheduler.sleep(thread, dwDelay - (int) (System.currentTimeMillis() - start));
        }
    };
    private static int nextTimerId = 1;

    // MMRESULT timeBeginPeriod(UINT uPeriod)
    public static int timeBeginPeriod(int wPeriod) {
        if (wPeriod < MMSYSTIME_MININTERVAL || wPeriod > MMSYSTIME_MAXINTERVAL)
            return TIMERR_NOCANDO;

        if (wPeriod > MMSYSTIME_MININTERVAL) {
            log("Stub; we set our timer resolution at minimum\n");
        }

        return 0;
    }

    // MMRESULT timeGetDevCaps(LPTIMECAPS ptc, UINT cbtc)
    public static int timeGetDevCaps(int ptc, int cbtc) {
        if (ptc == 0 || cbtc < 8)
            return TIMERR_NOCANDO;
        writed(ptc, MMSYSTIME_MININTERVAL);
        writed(ptc, MMSYSTIME_MAXINTERVAL);
        return TIMERR_NOERROR;
    }

    // DWORD timeGetTime(void)
    public static int timeGetTime() {
        return WinSystem.getTickCount();
    }

    // MMRESULT timeEndPeriod(UINT uPeriod)
    public static int timeEndPeriod(int uPeriod) {
        return TIMERR_NOERROR;
    }

    // MMRESULT timeKillEvent(UINT uTimerID)
    public static int timeKillEvent(int uTimerID) {
        MMTimer timer = timers.get(uTimerID);
        if (timer == null)
            return MMSYSERR_INVALPARAM;
        timer.close();
        return TIMERR_NOERROR;
    }

    // MMRESULT timeSetEvent(UINT uDelay, UINT uResolution, LPTIMECALLBACK lpTimeProc, DWORD_PTR dwUser, UINT fuEvent)
    public static int timeSetEvent(int uDelay, int uResolution, int lpTimeProc, int dwUser, int fuEvent) {
        if (uDelay < MMSYSTIME_MININTERVAL || uDelay > MMSYSTIME_MAXINTERVAL)
            return 0;
        MMTimer timer = new MMTimer(nextTimerId++, uDelay, lpTimeProc, dwUser, fuEvent);
        timers.put(timer.id, timer);
        return timer.id;
    }

    private static class MMTimer extends Thread {
        final WinThread thread;
        int delay;
        int callback;
        int dwUser;
        int flags;
        int id;
        boolean bExit = false;

        public MMTimer(int id, int delay, int callback, int dwUser, int flags) {
            this.delay = delay;
            this.callback = callback;
            this.dwUser = dwUser;
            this.flags = flags;
            this.id = id;
            if ((flags & TIME_CALLBACK_EVENT_SET) == 0 && (flags & TIME_CALLBACK_EVENT_PULSE) == 0) {
                WinProcess process = WinSystem.getCurrentProcess();
                if (process.mmTimerThreadEIP == 0) {
                    int cb = WinCallback.addCallback(mmTimerThread);
                    process.mmTimerThreadEIP = process.loader.registerFunction(cb);
                }
                this.thread = WinThread.create(process, process.mmTimerThreadEIP, 8192, 8192, true); // primary=true so that we don't call dllmain's with this thread
                thread.pushStack32((flags & TIME_PERIODIC) == 0 ? 0 : delay);
                thread.pushStack32(dwUser);
                thread.pushStack32(callback);
                thread.pushStack32(thread.handle);
                thread.pushStack32(id);
                thread.pushStack32(thread.cpuState.eip);
                thread.pushStack32(0); // bogus callback return address
                Scheduler.addThread(thread, false);
                Scheduler.sleep(thread, delay);
            } else {
                this.thread = null;
                this.start();
            }
        }

        public void close() {
            if (thread == null) {
                bExit = true;
            } else {
                Scheduler.removeThread(thread);
                thread.close();
            }
        }

        @Override
        public void run() {
            while (!bExit) {
                try {
                    sleep(delay);
                } catch (Exception e) {
                }
                if (!bExit) {
                    WinEvent event = WinEvent.get(callback);
                    if (event == null)
                        continue;
                    if ((flags & TIME_CALLBACK_EVENT_SET) != 0) {
                        event.set();
                    } else {
                        event.pulse();
                    }
                }
                if ((flags & TIME_PERIODIC) == 0)
                    break;
            }
            timers.remove(this);
        }
    }
}
