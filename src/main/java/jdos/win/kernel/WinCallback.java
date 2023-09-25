package jdos.win.kernel;

import jdos.Dosbox;
import jdos.cpu.CPU_Regs;
import jdos.cpu.Callback;
import jdos.hardware.Memory;

public class WinCallback {
    public static Callback.Handler[] dosCallbacks;
    private static final Callback.Handler[] handlers = new Callback.Handler[2048];
    private static int nextCB = 1;
    private static int idle_eip;
    private static final Callback.Handler idle = new Callback.Handler() {
        @Override
        public int call() {
            CPU_Regs.reg_eip = idle_eip;
            return 0;
        }

        @Override
        public String getName() {
            return null;
        }
    };

    public static void start(KernelMemory memory) {
        dosCallbacks = Callback.CallBack_Handlers;
        Callback.CallBack_Handlers = handlers;
        createIdleCallback(memory);
    }

    public static int addCallback(Callback.Handler handler) {
        int result = nextCB;
        handlers[nextCB++] = handler;
        return result;
    }

    public static int install(KernelMemory memory, boolean popErrorCode, Callback.Handler handler) {
        int callback = nextCB++;
        handlers[callback] = handler;
        int physAddress = memory.kmalloc(popErrorCode ? 11 : 5);
        Memory.phys_writeb(physAddress, 0xFE); //GRP 4
        Memory.phys_writeb(physAddress + 0x01, 0x38); //Extra Callback instruction
        Memory.phys_writew(physAddress + 0x02, callback); //The immediate word
        physAddress += 4;
        if (popErrorCode) {
            Memory.phys_writeb(physAddress, 0x81); // Grpl Ed,Id
            Memory.phys_writeb(physAddress + 0x01, 0xC4); // ADD ESP
            Memory.phys_writed(physAddress + 0x02, 0x00000004); // 4
            physAddress += 6;
        }
        Memory.phys_writeb(physAddress, 0xCF); //IRET
        return physAddress;
    }

    private static void createIdleCallback(KernelMemory memory) {
        int cb = addCallback(idle);
        idle_eip = memory.kmalloc(16);
        for (int i = 0; i <= 11; i++)
            Memory.mem_writeb(idle_eip + i, 0x90);
        Memory.mem_writeb(idle_eip + 12, 0xFE);
        Memory.mem_writeb(idle_eip + 13, 0x38);
        Memory.mem_writew(idle_eip + 14, cb);
    }

    public static void doIdle() {
        CPU_Regs.SETFLAGBIT(CPU_Regs.IF, true);
        CPU_Regs.reg_eip = idle_eip;
        Dosbox.DOSBOX_RunMachine();
    }
}
