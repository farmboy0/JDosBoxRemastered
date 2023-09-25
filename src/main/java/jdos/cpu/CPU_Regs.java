package jdos.cpu;

import jdos.hardware.Memory;

public class CPU_Regs extends Flags {
    public static final int CF = 0x00000001;
    public static final int PF = 0x00000004;
    public static final int AF = 0x00000010;
    public static final int ZF = 0x00000040;
    public static final int SF = 0x00000080;
    public static final int OF = 0x00000800;

    public static final int TF = 0x00000100;
    public static final int IF = 0x00000200;
    public static final int DF = 0x00000400;

    public static final int IOPL = 0x00003000;
    public static final int NT = 0x00004000;
    public static final int VM = 0x00020000;
    public static final int AC = 0x00040000;
    public static final int ID = 0x00200000;

    public static final int MAYBE = 0x10000000;

    public static final int FMASK_TEST = CF | PF | AF | ZF | SF | OF;
    public static final int FMASK_NORMAL = FMASK_TEST | DF | TF | IF;
    public static final int FMASK_ALL = FMASK_NORMAL | IOPL | NT;
    // SegNames
    public static final int es = 0;
    public static final int cs = 1;
    public static final int ss = 2;
    public static final int ds = 3;
    public static final int fs = 4;
    public static final int gs = 5;
    public static final Reg reg_zero = new Reg("zero");
    public static final Reg reg_eax = new Reg("eax");
    public static final Reg reg_ebx = new Reg("ebx");
    public static final Reg reg_ecx = new Reg("ecx");
    public static final Reg reg_edx = new Reg("edx");
    public static final Reg reg_esi = new Reg("esi");
    public static final Reg reg_edi = new Reg("edi");
    public static final Reg reg_esp = new Reg("esp");
    public static final Reg reg_ebp = new Reg("ebp");
    public static final Reg reg_ah = new Reg(reg_eax);
    public static final Reg reg_bh = new Reg(reg_ebx);
    public static final Reg reg_ch = new Reg(reg_ecx);
    public static final Reg reg_dh = new Reg(reg_edx);
    public static final Reg reg_esPhys = new Reg("esPhys");
    public static final Reg reg_csPhys = new Reg("csPhys");
    public static final Reg reg_ssPhys = new Reg("ssPhys");
    public static final Reg reg_dsPhys = new Reg("dsPhys");
    public static final Reg reg_fsPhys = new Reg("fsPhys");
    public static final Reg reg_gsPhys = new Reg("gsPhys");
    public static final Reg reg_esVal = new Reg("es");
    public static final Reg reg_csVal = new Reg("cs");
    public static final Reg reg_ssVal = new Reg("ss");
    public static final Reg reg_dsVal = new Reg("ds");
    public static final Reg reg_fsVal = new Reg("fs");
    public static final Reg reg_gsVal = new Reg("gs");
    public static int reg_eip;
    public static /*Bitu*/ int flags;

    public CPU_Regs() {
    }

    public static void SETFLAGBIT(int flag, boolean set) {
        if (set)
            CPU_Regs.flags |= flag;
        else
            CPU_Regs.flags &= ~flag;
    }

    public static int GETFLAG(int flag) {
        return CPU_Regs.flags & flag;
    }

    public static boolean GETFLAGBOOL(int flag) {
        return (CPU_Regs.flags & flag) != 0;
    }

    public static int GETFLAG_IOPL() {
        return (CPU_Regs.flags & IOPL) >> 12;
    }

    //    public static /*PhysPt*/int SegPhys(int index) {
//        return CPU.Segs.phys[index];
//    }
//
//    public static /*Bit16u*/int SegValue(int index) {
//        return (int)(CPU.Segs.val[index] & 0xFFFFl);
//    }
//
    public static /*RealPt*/int RealMakeSegDS(/*Bit16u*/int off) {
        return Memory.RealMake(reg_dsVal.dword, off);
    }

    public static /*RealPt*/int RealMakeSegSS(/*Bit16u*/int off) {
        return Memory.RealMake(reg_ssVal.dword, off);
    }

    public static void SegSet16ES(/*Bit16u*/int val) {
        reg_esVal.dword = val;
        reg_esPhys.dword = val << 4;
    }

    public static void SegSet16CS(/*Bit16u*/int val) {
        reg_csVal.dword = val;
        reg_csPhys.dword = val << 4;
    }

    public static void SegSet16SS(/*Bit16u*/int val) {
        reg_ssVal.dword = val;
        reg_ssPhys.dword = val << 4;
    }

    public static void SegSet16DS(/*Bit16u*/int val) {
        reg_dsVal.dword = val;
        reg_dsPhys.dword = val << 4;
    }

    public static void SegSet16FS(/*Bit16u*/int val) {
        reg_fsVal.dword = val;
        reg_fsPhys.dword = val << 4;
    }

    public static void SegSet16GS(/*Bit16u*/int val) {
        reg_gsVal.dword = val;
        reg_gsPhys.dword = val << 4;
    }

    // IP
    public static int reg_ip() {
        return reg_eip & 0xFFFF;
    }

    public static void reg_ip(int value) {
        reg_eip = value & 0xFFFF | reg_eip & 0xFFFF0000;
    }

    public static final class Reg {
        public int dword;
        Reg parent;
        String name = null;
        String name8 = null;
        String name16 = null;
        String fullName32 = null;

        public Reg() {
        }

        public Reg(int value) {
            dword = value;
        }

        public Reg(String name) {
            this.name = name;
            this.fullName32 = "CPU_Regs.reg_" + name + ".dword";
            this.name16 = name.substring(1);
            this.name8 = this.name16.substring(0, 1) + "l";
        }

        public Reg(Reg parent) {
            this.parent = parent;
            this.name = parent.name;
            this.name8 = parent.name16.substring(0, 1) + "h";
            this.name16 = parent.name16;
        }

        public String getName() {
            return name;
        }

        public String getName8() {
            return name8;
        }

        public String getName16() {
            return name16;
        }

        public String getFullName32() {
            return fullName32;
        }

        public Reg getParent() {
            return parent;
        }

        public int get8() {
            if (parent == null)
                return low();
            else
                return parent.high();
        }

        public void set8(int s) {
            if (parent == null)
                low(s);
            else
                parent.high(s);
        }

        public void dword(long l) {
            dword = (int) l;
        }

        public void word_dec() {
            word(word() - 1);
        }

        public int word() {
            return dword & 0xFFFF;
        }

        public void word(int value) {
            dword = value & 0xFFFF | dword & 0xFFFF0000;
        }

        public int low() {
            return dword & 0xff;
        }

        public void low(int value) {
            dword = value & 0xFF | dword & 0xFFFFFF00;
        }

        public int high() {
            return dword >> 8 & 0xff;
        }

        public void high(int value) {
            dword = (value & 0xFF) << 8 | dword & 0xFFFF00FF;
        }
    }
}
