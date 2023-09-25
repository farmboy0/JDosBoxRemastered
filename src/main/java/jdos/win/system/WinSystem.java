package jdos.win.system;

import java.awt.image.BufferedImage;

import jdos.Dosbox;
import jdos.cpu.CPU;
import jdos.cpu.CPU_Regs;
import jdos.cpu.Callback;
import jdos.gui.Main;
import jdos.win.builtin.kernel32.WinProcess;
import jdos.win.builtin.kernel32.WinThread;
import jdos.win.kernel.DescriptorTables;
import jdos.win.kernel.Interrupts;
import jdos.win.kernel.KernelMemory;
import jdos.win.kernel.Timer;
import jdos.win.kernel.WinCallback;
import jdos.win.utils.Pixel;

public class WinSystem {
    public static KernelMemory memory;
    public static Interrupts interrupts;
    public static Timer timer;
    public static WinRegistry registry;
    private static WinCallback callbacks;
    private static DescriptorTables descriptorTables;
    private static long startTime = System.currentTimeMillis();
    private static final Callback.Handler returnCallback = new Callback.Handler() {
        @Override
        public String getName() {
            return "WinProc";
        }

        @Override
        public int call() {
            return 1; // return from SendMessage
        }
    };
    private static int returnEip = 0;

    public static void start() {
        registry = new WinRegistry();

        memory = new KernelMemory();
        WinCallback.start(memory);
        interrupts = new Interrupts(memory);
        descriptorTables = new DescriptorTables(interrupts, memory);
        timer = new Timer(50); // 50MHz timer

        final int stackSize = 16 * 1024;
        int stackEnd = memory.kmalloc(stackSize);
        CPU_Regs.reg_esp.dword = stackEnd + stackSize;

        //memory.registerPageFault(interrupts);
        memory.initialise_paging();
        setScreenSize(640, 480, 32);
        StaticData.init();

        new WinFile(WinFile.FILE_TYPE_CHAR, WinFile.STD_OUT);
        new WinFile(WinFile.FILE_TYPE_CHAR, WinFile.STD_IN);
        new WinFile(WinFile.FILE_TYPE_CHAR, WinFile.STD_ERROR);
        startTime = System.currentTimeMillis();
    }

    public static JavaBitmap getScreen() {
        return StaticData.screen;
    }

    public static int getScreenWidth() {
        return StaticData.screen.getWidth();
    }

    public static int getScreenHeight() {
        return StaticData.screen.getHeight();
    }

    public static int getScreenBpp() {
        return StaticData.screen.getBpp();
    }

    public static void setScreenSize(int dwWidth, int dwHeight, int dwBPP) {
        if (StaticData.screen == null || dwWidth != StaticData.screen.getWidth()
            || dwHeight != StaticData.screen.getHeight() || StaticData.screen.getBpp() != dwBPP) {
            int[] palette = null;

            if (StaticData.screen != null) {
                palette = StaticData.screen.getPalette();
                StaticData.screen.close();
            }
            if (palette == null) {
                palette = JavaBitmap.getDefaultPalette();
            }
            BufferedImage bi = Pixel.createImage(0, dwBPP, palette, dwWidth, dwHeight, false);
            if (StaticData.screen == null)
                StaticData.screen = new JavaBitmap(bi, dwBPP, dwWidth, dwHeight, JavaBitmap.getDefaultPalette());
            else
                StaticData.screen.set(bi, dwBPP, dwWidth, dwHeight, JavaBitmap.getDefaultPalette()); // existing dc's will be point to screen, so update it instead of assigning a new one
            Main.GFX_SetSize(dwWidth, dwHeight, dwWidth, dwHeight, false, dwBPP);
        }
    }

    public static int getTickCount() {
        return (int) (System.currentTimeMillis() - startTime);
    }

    public static void call(int eip, int param1, int param2, int param3, int param4, int param5) {
        internalCall(eip, 5, param1, param2, param3, param4, param5);
    }

    public static void call(int eip, int param1, int param2, int param3, int param4) {
        internalCall(eip, 4, param1, param2, param3, param4, 0);
    }

    public static void call(int eip, int param1, int param2, int param3) {
        internalCall(eip, 3, param1, param2, param3, 0, 0);
    }

    public static void call(int eip, int param1, int param2) {
        internalCall(eip, 2, param1, param2, 0, 0, 0);
    }

    private static void internalCall(int eip, int paramCount, int param1, int param2, int param3, int param4,
        int param5) {
        if (returnEip == 0) {
            int callback = WinCallback.addCallback(returnCallback);
            returnEip = WinSystem.getCurrentProcess().loader.registerFunction(callback);
        }
        int oldEsp = CPU_Regs.reg_esp.dword;
        if (paramCount >= 5)
            CPU.CPU_Push32(param5);
        if (paramCount >= 4)
            CPU.CPU_Push32(param4);
        if (paramCount >= 3)
            CPU.CPU_Push32(param3);
        if (paramCount >= 2)
            CPU.CPU_Push32(param2);
        if (paramCount >= 1)
            CPU.CPU_Push32(param1);
        CPU.CPU_Push32(returnEip);
        int saveEip = CPU_Regs.reg_eip;
        CPU_Regs.reg_eip = eip;
        Dosbox.DOSBOX_RunMachine();
        CPU_Regs.reg_eip = saveEip;
        CPU_Regs.reg_esp.dword = oldEsp;
    }

    public static WinProcess getCurrentProcess() {
        WinThread currentThread = Scheduler.getCurrentThread();
        if (currentThread != null)
            return currentThread.getProcess();
        return null;
    }
}
