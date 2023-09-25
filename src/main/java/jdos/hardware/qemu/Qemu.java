package jdos.hardware.qemu;

import java.io.FileInputStream;

import jdos.gui.Main;
import jdos.hardware.IoHandler;
import jdos.hardware.RAM;
import jdos.misc.Log;
import jdos.util.FileIO;
import jdos.util.FileIOFactory;

public class Qemu {
    public static void vm_stop(int runstate) {
        throw new Main.KillException();
    }

    public static long get_ticks_per_sec() {
        return 100;
    }

    public static long qemu_get_clock_ns() {
        return System.nanoTime();
    }

    public static long qemu_get_clock_ms() {
        return System.currentTimeMillis();
    }

    public static void rom_add_vga(String fileName, boolean registerBochsPorts) {
        try {
            byte[] videoData = new byte[0x10000];
            boolean videoBiosFound = false;
            try {
                FileInputStream videofis = new FileInputStream("vgabios.bin");
                videofis.read(videoData);
                videofis.close();
                videoBiosFound = true;
            } catch (Exception e) {
            }
            if (!videoBiosFound) {
                FileIO fileIO = FileIOFactory.open("jar://vgabios.bin", FileIOFactory.MODE_READ);
                fileIO.read(videoData, 0, (int) fileIO.length());
                fileIO.close();
            }
            int address = 0xC0000;
            for (int i = 0; i < videoData.length; i++)
                RAM.writeb(address + i, videoData[i]);
            if (registerBochsPorts) {
                IoHandler.IO_WriteHandler vga_write = (port, val, iolen) -> {
                    if (port == 0x500 || port == 0x503) {
                        System.out.print((char) val);
                    } else if (port == 0x501 || port == 0x502) {
                        System.out.println("panic in vgabios at line " + val);
                    }
                };
                new IoHandler.IO_WriteHandleObject().Install(0x500, vga_write, IoHandler.IO_MA);
                new IoHandler.IO_WriteHandleObject().Install(0x503, vga_write, IoHandler.IO_MA);
                new IoHandler.IO_WriteHandleObject().Install(0x501, vga_write, IoHandler.IO_MA);
                new IoHandler.IO_WriteHandleObject().Install(0x502, vga_write, IoHandler.IO_MA);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.exit(e.getMessage());
        }
    }
}
