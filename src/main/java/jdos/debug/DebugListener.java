package jdos.debug;

import jdos.cpu.CPU_Regs;
import jdos.dos.DOS_File;
import jdos.dos.Dos;
import jdos.dos.Dos_files;
import jdos.dos.drives.Drive_local.localFile;

public interface DebugListener {

    void overlay_loaded(String file, int loadsegment);

    void com_loaded(String file, int loadsegment, boolean execute, int initCS, int initEIP);

    void exe_loaded(String file, int loadsegment, boolean execute);

    void file_seek(String file, long fileOffset);

    void file_read(String file, int length, int targetSegment, int targetOffset);

    void file_read(String file, long fileOffset, int length, int targetSegment, int targetOffset);

    default String filename(short file) {
        int handle = Dos.RealHandle(file);
        DOS_File dosfile = Dos_files.Files[handle];
        if (dosfile instanceof localFile local) {
            return local.GetPath();
        }
        return dosfile.GetName();
    }

    default void file_seek(short file, long fileOffset) {
        file_seek(filename(file), fileOffset);
    }

    default void file_read(short file, int length, int targetSegment, int targetOffset) {
        file_read(filename(file), length, targetSegment, targetOffset);
    }

    default void file_read(short file, long fileOffset, int length, int targetSegment, int targetOffset) {
        file_read(filename(file), fileOffset, length, targetSegment, targetOffset);
    }

    void execution_start(int eax, int ebx, int ecx, int edx, int esi, int edi, int ebp, int eip, int esp, int cs,
        int ds, int es, int fs, int gs, int ss);

    default void execution_start() {
        int eax = CPU_Regs.reg_eax.dword;
        int ebx = CPU_Regs.reg_ebx.dword;
        int ecx = CPU_Regs.reg_ecx.dword;
        int edx = CPU_Regs.reg_edx.dword;

        int esi = CPU_Regs.reg_esi.dword;
        int edi = CPU_Regs.reg_edi.dword;

        int ebp = CPU_Regs.reg_ebp.dword;
        int eip = CPU_Regs.reg_eip;
        int esp = CPU_Regs.reg_esp.dword;

        int cs = CPU_Regs.reg_csVal.dword;
        int ds = CPU_Regs.reg_dsVal.dword;
        int es = CPU_Regs.reg_esVal.dword;
        int fs = CPU_Regs.reg_fsVal.dword;
        int gs = CPU_Regs.reg_gsVal.dword;
        int ss = CPU_Regs.reg_ssVal.dword;

        execution_start(eax, ebx, ecx, edx, esi, edi, ebp, eip, esp, cs, ds, es, fs, gs, ss);
    }

    void interrupt_start(int num, int type);

    void interrupt_exit();
}
