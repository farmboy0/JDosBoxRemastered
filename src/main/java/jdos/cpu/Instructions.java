package jdos.cpu;

import jdos.util.LongHelper;
import jdos.util.OverflowException;

public class Instructions extends Table_ea {
    public static int ADDB(int op2, int l) {
        lf_var1d(l);
        lf_var2d(op2);
        lf_resd(l + op2 & 0xFF);
        type = t_ADDb;
        return lf_resb();
    }

    public static int ADCB(int op2, int l) {
        oldcf = get_CF();
        lf_var1d(l);
        lf_var2d(op2);
        lf_resd(l + op2 + (oldcf ? 1 : 0) & 0xFF);
        type = t_ADCb;
        return lf_resb();
    }

    public static int SBBB(int op2, int l) {
        oldcf = get_CF();
        lf_var1d(l);
        lf_var2d(op2);
        lf_resd(l - (op2 + (oldcf ? 1 : 0)) & 0xFF);
        type = t_SBBb;
        return lf_resb();
    }

    public static int SUBB(int op2, int l) {
        lf_var1d(l);
        lf_var2d(op2);
        lf_resd(l - op2 & 0xFF);
        type = t_SUBb;
        return lf_resb();
    }

    public static int ORB(int op2, int l) {
        lf_var1d(l);
        lf_var2d(op2);
        lf_resd((l | op2) & 0xFF);
        type = t_ORb;
        return lf_resb();
    }

    public static int XORB(int op2, int l) {
        lf_var1d(l);
        lf_var2d(op2);
        lf_resd((l ^ op2) & 0xFF);
        type = t_XORb;
        return lf_resb();
    }

    public static int ANDB(int op2, int l) {
        lf_var1d(l);
        lf_var2d(op2);
        lf_resd(l & op2 & 0xFF);
        type = t_ANDb;
        return lf_resb();
    }

    public static void CMPB(int op2, int l) {
        lf_var1d(l);
        lf_var2d(op2);
        lf_resd(l - op2 & 0xFF);
        type = t_CMPb;
    }

    public static void TESTB(int op2, int l) {
        lf_var1d(l);
        lf_var2d(op2);
        lf_resd(l & op2 & 0xFF);
        type = t_TESTb;
    }

    public static int ADDW(int op2, int l) {
        lf_var1d(l);
        lf_var2d(op2);
        lf_resd(l + op2 & 0xFFFF);
        type = t_ADDw;
        return lf_resw();
    }

    public static int ADCW(int op2, int l) {
        oldcf = get_CF();
        lf_var1d(l);
        lf_var2d(op2);
        lf_resd(l + op2 + (oldcf ? 1 : 0) & 0xFFFF);
        type = t_ADCw;
        return lf_resw();
    }

    public static int SBBW(int op2, int l) {
        oldcf = get_CF();
        lf_var1d(l);
        lf_var2d(op2);
        lf_resd(l - (op2 + (oldcf ? 1 : 0)) & 0xFFFF);
        type = t_SBBw;
        return lf_resw();
    }

    public static int SUBW(int op2, int l) {
        lf_var1d(l);
        lf_var2d(op2);
        lf_resd(l - op2 & 0xFFFF);
        type = t_SUBw;
        return lf_resw();
    }

    /* All Word General instructions */

    public static int ORW(int op2, int l) {
        lf_var1d(l);
        lf_var2d(op2);
        lf_resd((l | op2) & 0xFFFF);
        type = t_ORw;
        return lf_resw();
    }

    public static int XORW(int op2, int l) {
        lf_var1d(l);
        lf_var2d(op2);
        lf_resd((l ^ op2) & 0xFFFF);
        type = t_XORw;
        return lf_resw();
    }

    public static int ANDW(int op2, int l) {
        lf_var1d(l);
        lf_var2d(op2);
        lf_resd(l & op2 & 0xFFFF);
        type = t_ANDw;
        return lf_resw();
    }

    public static void CMPW(int op2, int l) {
        lf_var1d(l);
        lf_var2d(op2);
        lf_resd(l - op2 & 0xFFFF);
        type = t_CMPw;
    }

    public static void TESTW(int op2, int l) {
        lf_var1d(l);
        lf_var2d(op2);
        lf_resd(l & op2 & 0xFFFF);
        type = t_TESTw;
    }

    public static int ADDD(int op2, int l) {
        var1 = l;
        var2 = op2;
        res = l + op2;
        type = t_ADDd;
        return res;
    }

    public static int ADCD(int op2, int l) {
        oldcf = get_CF();
        var1 = l;
        var2 = op2;
        res = l + op2 + (oldcf ? 1 : 0);
        type = t_ADCd;
        return res;
    }

    public static int SBBD(int op2, int l) {
        oldcf = get_CF();
        var1 = l;
        var2 = op2;
        res = l - (op2 + (oldcf ? 1 : 0));
        type = t_SBBd;
        return res;
    }

    public static int SUBD(int op2, int l) {
        var1 = l;
        var2 = op2;
        res = l - op2;
        type = t_SUBd;
        return res;
    }

    /* All DWORD General Instructions */

    public static int ORD(int op2, int l) {
        var1 = l;
        var2 = op2;
        res = l | op2;
        type = t_ORd;
        return res;
    }

    public static int XORD(int op2, int l) {
        var1 = l;
        var2 = op2;
        res = l ^ op2;
        type = t_XORd;
        return res;
    }

    public static int ANDD(int op2, int l) {
        var1 = l;
        var2 = op2;
        res = l & op2;
        type = t_ANDd;
        return res;
    }

    public static void CMPD(int op2, int op1) {
        var1 = op1;
        var2 = op2;
        res = op1 - op2;
        type = t_CMPd;
    }

    public static void TESTD(int op2, int op1) {
        var1 = op1;
        var2 = op2;
        res = op1 & op2;
        type = t_TESTd;
    }

    public static int INCB(int l) {
        LoadCF();
        lf_var1b(l);
        lf_resb(lf_var1b() + 1);
        type = t_INCb;
        return lf_resb();
    }

    public static int INCW(int l) {
        LoadCF();
        lf_var1w(l);
        lf_resw(lf_var1w() + 1);
        type = t_INCw;
        return lf_resw();
    }

    public static int INCD(int l) {
        LoadCF();
        var1 = l;
        res = l + 1;
        type = t_INCd;
        return res;
    }

    public static int DECB(int l) {
        LoadCF();
        lf_var1d(l);
        lf_resd(l - 1 & 0xFF);
        type = t_DECb;
        return lf_resb();
    }

    public static int DECW(int l) {
        LoadCF();
        lf_var1d(l);
        lf_resd(l - 1 & 0xFFFF);
        type = t_DECw;
        return lf_resw();
    }

    public static int DECD(int l) {
        LoadCF();
        var1 = l;
        res = l - 1;
        type = t_DECd;
        return res;
    }

    public static boolean valid_ROLB(int op1, int op2) {
        if ((op2 & 0x7) == 0) {
            if ((op2 & 0x18) != 0) {
                FillFlagsNoCFOF();
                CPU_Regs.SETFLAGBIT(CPU_Regs.CF, (op1 & 1) != 0);
                CPU_Regs.SETFLAGBIT(CPU_Regs.OF, (op1 & 1 ^ op1 >>> 7) != 0);
            }
            return false;
        }
        return true;
    }

    public static int do_ROLB(int op2, int l) {
        FillFlagsNoCFOF();
        lf_var1b(l);
        lf_var2b(op2 & 0x07);
        lf_resb(lf_var1b() << lf_var2b() | lf_var1b() >> 8 - lf_var2b());
        CPU_Regs.SETFLAGBIT(CPU_Regs.CF, (lf_resb() & 1) != 0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.OF, (lf_resb() & 1 ^ lf_resb() >> 7) != 0);
        return lf_resb();
    }

    public static void ROLB(int op1, int op2, loadb l, saveb s) {
        if (valid_ROLB(op1, op2)) {
            s.call(do_ROLB(op2, l.call()));
        }
    }

    public static boolean valid_ROLW(int op1, int op2) {
        if ((op2 & 0xf) == 0) {
            if ((op2 & 0x10) != 0) {
                FillFlagsNoCFOF();
                CPU_Regs.SETFLAGBIT(CPU_Regs.CF, (op1 & 1) != 0);
                CPU_Regs.SETFLAGBIT(CPU_Regs.OF, (op1 & 1 ^ op1 >>> 15) != 0);
            }
            return false;
        }
        return true;
    }

    public static int do_ROLW(int op2, int l) {
        FillFlagsNoCFOF();
        lf_var1w(l);
        lf_var2b(op2 & 0xf);
        lf_resw(lf_var1w() << lf_var2b() | lf_var1w() >> 16 - lf_var2b());
        CPU_Regs.SETFLAGBIT(CPU_Regs.CF, (lf_resw() & 1) != 0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.OF, (lf_resw() & 1 ^ lf_resw() >> 15) != 0);
        return lf_resw();
    }

    public static void ROLW(int op1, int op2, loadw l, savew s) {
        if (valid_ROLW(op1, op2)) {
            s.call(do_ROLW(op2, l.call()));
        }
    }

    public static int ROLD(int op2, int l) {
        FillFlagsNoCFOF();
        var1 = l;
        var2 = op2;
        res = l << op2 | l >>> 32 - op2;
        CPU_Regs.SETFLAGBIT(CPU_Regs.CF, (res & 1) != 0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.OF, (res & 1 ^ res >>> 31) != 0);
        return res;
    }

    public static boolean valid_RORB(int op1, int op2) {
        if ((op2 & 0x7) == 0) {
            if ((op2 & 0x18) != 0) {
                FillFlagsNoCFOF();
                CPU_Regs.SETFLAGBIT(CPU_Regs.CF, op1 >> 7 != 0);
                CPU_Regs.SETFLAGBIT(CPU_Regs.OF, (op1 >> 7 ^ op1 >>> 6 & 1) != 0);
            }
            return false;
        }
        return true;
    }

    public static int do_RORB(int op2, int l) {

        FillFlagsNoCFOF();
        lf_var1b(l);
        lf_var2b(op2 & 0x07);
        lf_resb(lf_var1b() >> lf_var2b() | lf_var1b() << 8 - lf_var2b());
        CPU_Regs.SETFLAGBIT(CPU_Regs.CF, (lf_resb() & 0x80) != 0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.OF, ((lf_resb() ^ lf_resb() << 1) & 0x80) != 0);
        return lf_resb();
    }

    public static void RORB(int op1, int op2, loadb l, saveb s) {
        if (valid_RORB(op1, op2)) {
            s.call(do_RORB(op2, l.call()));
        }
    }

    public static boolean valid_RORW(int op1, int op2) {
        if ((op2 & 0xf) == 0) {
            if ((op2 & 0x10) != 0) {
                FillFlagsNoCFOF();
                CPU_Regs.SETFLAGBIT(CPU_Regs.CF, op1 >> 15 != 0);
                CPU_Regs.SETFLAGBIT(CPU_Regs.OF, (op1 >> 15 ^ op1 >>> 14 & 1) != 0);
            }
            return false;
        }
        return true;
    }

    public static int do_RORW(int op2, int l) {
        FillFlagsNoCFOF();
        lf_var1w(l);
        lf_var2b(op2 & 0xf);
        lf_resw(lf_var1w() >> lf_var2b() | lf_var1w() << 16 - lf_var2b());
        CPU_Regs.SETFLAGBIT(CPU_Regs.CF, (lf_resw() & 0x8000) != 0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.OF, ((lf_resw() ^ lf_resw() << 1) & 0x8000) != 0);
        return lf_resw();
    }

    public static void RORW(int op1, int op2, loadw l, savew s) {
        if (valid_RORW(op1, op2))
            s.call(do_RORW(op2, l.call()));
    }

    public static int RORD(int op2, int l) {
        FillFlagsNoCFOF();
        var1 = l;
        var2 = op2;
        res = l >>> op2 | l << 32 - op2;
        CPU_Regs.SETFLAGBIT(CPU_Regs.CF, (res & 0x80000000) != 0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.OF, ((res ^ res << 1) & 0x80000000) != 0);
        return res;
    }

    public static boolean valid_RCLB(int op2) {
        return op2 % 9 != 0;
    }

    public static int do_RCLB(int op2, int l) {
        /*Bit8u*/
        /*Bit8u*/
        int cf = FillFlags() & 0x1;
        lf_var1b(l);
        lf_var2b(op2 % 9);
        lf_resb(lf_var1b() << lf_var2b() | cf << lf_var2b() - 1 | lf_var1b() >> 9 - lf_var2b());
        CPU_Regs.SETFLAGBIT(CPU_Regs.CF, (lf_var1b() >> 8 - lf_var2b() & 1) != 0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.OF, (CPU_Regs.flags & 1 ^ lf_resb() >> 7) != 0);
        return lf_resb();
    }

    public static void RCLB(int op2, loadb l, saveb s) {
        if (valid_RCLB(op2)) {
            s.call(do_RCLB(op2, l.call()));
        }
    }

    public static boolean valid_RCLW(int op2) {
        return op2 % 17 != 0;
    }

    public static int do_RCLW(int op2, int l) {
        /*Bit16u*/
        /*Bit16u*/
        int cf = FillFlags() & 0x1;
        lf_var1w(l);
        lf_var2b(op2 % 17);
        lf_resw(lf_var1w() << lf_var2b() | cf << lf_var2b() - 1 | lf_var1w() >> 17 - lf_var2b());
        CPU_Regs.SETFLAGBIT(CPU_Regs.CF, (lf_var1w() >> 16 - lf_var2b() & 1) != 0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.OF, (CPU_Regs.flags & 1 ^ lf_resw() >> 15) != 0);
        return lf_resw();
    }

    public static void RCLW(int op2, loadw l, savew s) {
        if (valid_RCLW(op2)) {
            s.call(do_RCLW(op2, l.call()));
        }
    }

    public static int RCLD(int op2, int l) {
        /*Bit32u*/
        int cf = FillFlags() & 0x1;
        var1 = l;
        var2 = op2;
        if (op2 == 1) {
            res = l << 1 | cf;
        } else {
            res = l << op2 | cf << op2 - 1 | l >>> 33 - op2;
        }
        CPU_Regs.SETFLAGBIT(CPU_Regs.CF, (l >>> 32 - op2 & 1) != 0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.OF, (CPU_Regs.flags & 1 ^ res >>> 31) != 0);
        return res;
    }

    public static boolean valid_RCRB(int op2) {
        return op2 % 9 != 0;
    }

    public static int do_RCRB(int op2, int l) {
        /*Bit8u*/
        int cf = FillFlags() & 0x1;
        lf_var1b(l);
        lf_var2b(op2 % 9);
        lf_resb(lf_var1b() >> lf_var2b() | cf << 8 - lf_var2b() | lf_var1b() << 9 - lf_var2b());
        CPU_Regs.SETFLAGBIT(CPU_Regs.CF, (lf_var1b() >> lf_var2b() - 1 & 1) != 0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.OF, ((lf_resb() ^ lf_resb() << 1) & 0x80) != 0);
        return lf_resb();
    }

    public static void RCRB(int op2, loadb l, saveb s) {
        if (valid_RCRB(op2))
            s.call(do_RCRB(op2, l.call()));
    }

    public static boolean valid_RCRW(int op2) {
        return op2 % 17 != 0;
    }

    public static int do_RCRW(int op2, int l) {
        /*Bit16u*/
        int cf = FillFlags() & 0x1;
        lf_var1w(l);
        lf_var2b(op2 % 17);
        lf_resw(lf_var1w() >> lf_var2b() | cf << 16 - lf_var2b() | lf_var1w() << 17 - lf_var2b());
        CPU_Regs.SETFLAGBIT(CPU_Regs.CF, (lf_var1w() >> lf_var2b() - 1 & 1) != 0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.OF, ((lf_resw() ^ lf_resw() << 1) & 0x8000) != 0);
        return lf_resw();
    }

    public static void RCRW(int op2, loadw l, savew s) {
        if (valid_RCRW(op2))
            s.call(do_RCRW(op2, l.call()));
    }

    public static int RCRD(int op2, int l) {
        /*Bit32u*/
        int cf = FillFlags() & 0x1;
        var1 = l;
        var2 = op2;
        if (op2 == 1) {
            res = l >>> 1 | cf << 31;
        } else {
            res = l >>> op2 | cf << 32 - op2 | l << 33 - op2;
        }
        CPU_Regs.SETFLAGBIT(CPU_Regs.CF, (l >> op2 - 1 & 1) != 0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.OF, ((res ^ res << 1) & 0x80000000) != 0);
        return res;
    }

    public static boolean valid_SHLB(int op2) {
        return op2 != 0;
    }

    public static int do_SHLB(int op2, int l) {
        lf_var1b(l);
        lf_var2b(op2);
        lf_resb(lf_var1b() << lf_var2b());
        type = t_SHLb;
        return lf_resb();
    }

    public static void SHLB(int op2, loadb l, saveb s) {
        if (valid_SHLB(op2))
            s.call(do_SHLB(op2, l.call()));
    }

    public static boolean valid_SHLW(int op2) {
        return op2 != 0;
    }

    public static int do_SHLW(int op2, int l) {
        lf_var1w(l);
        lf_var2b(op2);
        lf_resw(lf_var1w() << lf_var2b());
        type = t_SHLw;
        return lf_resw();
    }

    public static void SHLW(int op2, loadw l, savew s) {
        if (valid_SHLW(op2))
            s.call(do_SHLW(op2, l.call()));
    }

    public static int SHLD(int op2, int l) {
        var1 = l;
        var2 = op2;
        res = l << op2;
        type = t_SHLd;
        return res;
    }

    public static boolean valid_SHRB(int op2) {
        return op2 != 0;
    }

    public static int do_SHRB(int op2, int l) {
        lf_var1b(l);
        lf_var2b(op2);
        lf_resb(lf_var1b() >> lf_var2b());
        type = t_SHRb;
        return lf_resb();
    }

    public static void SHRB(int op2, loadb l, saveb s) {
        if (valid_SHRB(op2))
            s.call(do_SHRB(op2, l.call()));
    }

    public static boolean valid_SHRW(int op2) {
        return op2 != 0;
    }

    public static int do_SHRW(int op2, int l) {
        lf_var1w(l);
        lf_var2b(op2);
        lf_resw(lf_var1w() >> lf_var2b());
        type = t_SHRw;
        return lf_resw();
    }

    public static void SHRW(int op2, loadw l, savew s) {
        if (valid_SHRW(op2))
            s.call(do_SHRW(op2, l.call()));
    }

    public static int SHRD(int op2, int l) {
        var1 = l;
        var2 = op2;
        res = l >>> op2;
        type = t_SHRd;
        return res;
    }

    public static boolean valid_SARB(int op2) {
        return op2 != 0;
    }

    public static int do_SARB(int op2, int l) {
        lf_var1b(l);
        lf_var2b(op2);
        if (lf_var2b() > 8)
            lf_var2b(8);
        if ((lf_var1b() & 0x80) != 0) {
            lf_resb(lf_var1b() >> lf_var2b() | 0xff << 8 - lf_var2b());
        } else {
            lf_resb(lf_var1b() >> lf_var2b());
        }
        type = t_SARb;
        return lf_resb();
    }

    public static void SARB(int op2, loadb l, saveb s) {
        if (valid_SARB(op2))
            s.call(do_SARB(op2, l.call()));
    }

    public static boolean valid_SARW(int op2) {
        return op2 != 0;
    }

    public static int do_SARW(int op2, int l) {
        lf_var1w(l);
        lf_var2b(op2);
        if (lf_var2b() > 16)
            lf_var2b(16);
        if ((lf_var1w() & 0x8000) != 0) {
            lf_resw(lf_var1w() >> lf_var2b() | 0xffff << 16 - lf_var2b());
        } else {
            lf_resw(lf_var1w() >> lf_var2b());
        }
        type = t_SARw;
        return lf_resw();
    }

    public static void SARW(int op2, loadw l, savew s) {
        if (valid_SARW(op2))
            s.call(do_SARW(op2, l.call()));
    }

    public static int SARD(int op2, int l) {
        var1 = l;
        var2 = op2;
        res = l >> op2; // intentional signed shift
        type = t_SARd;
        return res;
    }

    public static void DAA() {
        if ((CPU_Regs.reg_eax.low() & 0x0F) > 0x09 || get_AF()) {
            if (CPU_Regs.reg_eax.low() > 0x99 || get_CF()) {
                CPU_Regs.reg_eax.low(CPU_Regs.reg_eax.low() + 0x60);
                CPU_Regs.SETFLAGBIT(CPU_Regs.CF, true);
            } else {
                CPU_Regs.SETFLAGBIT(CPU_Regs.CF, false);
            }
            CPU_Regs.reg_eax.low(CPU_Regs.reg_eax.low() + 0x06);
            CPU_Regs.SETFLAGBIT(CPU_Regs.AF, true);
        } else {
            if (CPU_Regs.reg_eax.low() > 0x99 || get_CF()) {
                CPU_Regs.reg_eax.low(CPU_Regs.reg_eax.low() + 0x60);
                CPU_Regs.SETFLAGBIT(CPU_Regs.CF, true);
            } else {
                CPU_Regs.SETFLAGBIT(CPU_Regs.CF, false);
            }
            CPU_Regs.SETFLAGBIT(CPU_Regs.AF, false);
        }
        CPU_Regs.SETFLAGBIT(CPU_Regs.SF, (CPU_Regs.reg_eax.low() & 0x80) != 0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.ZF, CPU_Regs.reg_eax.low() == 0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.PF, parity_lookup[CPU_Regs.reg_eax.low()] != 0);
        type = t_UNKNOWN;
    }

    public static void DAS() {
        /*Bit8u*/
        int osigned = CPU_Regs.reg_eax.low() & 0x80;
        if ((CPU_Regs.reg_eax.low() & 0x0f) > 9 || get_AF()) {
            if (CPU_Regs.reg_eax.low() > 0x99 || get_CF()) {
                CPU_Regs.reg_eax.low(CPU_Regs.reg_eax.low() - 0x60);
                CPU_Regs.SETFLAGBIT(CPU_Regs.CF, true);
            } else {
                CPU_Regs.SETFLAGBIT(CPU_Regs.CF, CPU_Regs.reg_eax.low() <= 0x05);
            }
            CPU_Regs.reg_eax.low(CPU_Regs.reg_eax.low() - 6);
            CPU_Regs.SETFLAGBIT(CPU_Regs.AF, true);
        } else {
            if (CPU_Regs.reg_eax.low() > 0x99 || get_CF()) {
                CPU_Regs.reg_eax.low(CPU_Regs.reg_eax.low() - 0x60);
                CPU_Regs.SETFLAGBIT(CPU_Regs.CF, true);
            } else {
                CPU_Regs.SETFLAGBIT(CPU_Regs.CF, false);
            }
            CPU_Regs.SETFLAGBIT(CPU_Regs.AF, false);
        }
        CPU_Regs.SETFLAGBIT(CPU_Regs.OF, osigned != 0 && (CPU_Regs.reg_eax.low() & 0x80) == 0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.SF, (CPU_Regs.reg_eax.low() & 0x80) != 0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.ZF, CPU_Regs.reg_eax.low() == 0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.PF, parity_lookup[CPU_Regs.reg_eax.low()] != 0);
        type = t_UNKNOWN;
    }

    public static void AAA() {
        CPU_Regs.SETFLAGBIT(CPU_Regs.SF, CPU_Regs.reg_eax.low() >= 0x7a && CPU_Regs.reg_eax.low() <= 0xf9);
        if ((CPU_Regs.reg_eax.low() & 0xf) > 9) {
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, (CPU_Regs.reg_eax.low() & 0xf0) == 0x70);
            CPU_Regs.reg_eax.word(CPU_Regs.reg_eax.word() + 0x106);
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, true);
            CPU_Regs.SETFLAGBIT(CPU_Regs.ZF, CPU_Regs.reg_eax.low() == 0);
            CPU_Regs.SETFLAGBIT(CPU_Regs.AF, true);
        } else if (get_AF()) {
            CPU_Regs.reg_eax.word(CPU_Regs.reg_eax.word() + 0x106);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, false);
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, true);
            CPU_Regs.SETFLAGBIT(CPU_Regs.ZF, false);
            CPU_Regs.SETFLAGBIT(CPU_Regs.AF, true);
        } else {
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, false);
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, false);
            CPU_Regs.SETFLAGBIT(CPU_Regs.ZF, CPU_Regs.reg_eax.low() == 0);
            CPU_Regs.SETFLAGBIT(CPU_Regs.AF, false);
        }
        CPU_Regs.SETFLAGBIT(CPU_Regs.PF, parity_lookup[CPU_Regs.reg_eax.low()] != 0);
        CPU_Regs.reg_eax.low(CPU_Regs.reg_eax.low() & 0x0F);
        type = t_UNKNOWN;
    }

    public static void AAS() {
        if ((CPU_Regs.reg_eax.low() & 0x0f) > 9) {
            CPU_Regs.SETFLAGBIT(CPU_Regs.SF, CPU_Regs.reg_eax.low() > 0x85);
            CPU_Regs.reg_eax.word(CPU_Regs.reg_eax.word() - 0x106);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, false);
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, true);
            CPU_Regs.SETFLAGBIT(CPU_Regs.AF, true);
        } else if (get_AF()) {
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, CPU_Regs.reg_eax.low() >= 0x80 && CPU_Regs.reg_eax.low() <= 0x85);
            CPU_Regs.SETFLAGBIT(CPU_Regs.SF, CPU_Regs.reg_eax.low() < 0x06 || CPU_Regs.reg_eax.low() > 0x85);
            CPU_Regs.reg_eax.word(CPU_Regs.reg_eax.word() - 0x106);
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, true);
            CPU_Regs.SETFLAGBIT(CPU_Regs.AF, true);
        } else {
            CPU_Regs.SETFLAGBIT(CPU_Regs.SF, CPU_Regs.reg_eax.low() >= 0x80);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, false);
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, false);
            CPU_Regs.SETFLAGBIT(CPU_Regs.AF, false);
        }
        CPU_Regs.SETFLAGBIT(CPU_Regs.ZF, CPU_Regs.reg_eax.low() == 0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.PF, parity_lookup[CPU_Regs.reg_eax.low()] != 0);
        CPU_Regs.reg_eax.low(CPU_Regs.reg_eax.low() & 0x0F);
        type = t_UNKNOWN;
    }

    public static boolean AAM(int op1) {
        if (op1 != 0) {
            CPU_Regs.reg_eax.high(CPU_Regs.reg_eax.low() / op1);
            CPU_Regs.reg_eax.low(CPU_Regs.reg_eax.low() % op1);
            CPU_Regs.SETFLAGBIT(CPU_Regs.SF, (CPU_Regs.reg_eax.low() & 0x80) != 0);
            CPU_Regs.SETFLAGBIT(CPU_Regs.ZF, CPU_Regs.reg_eax.low() == 0);
            CPU_Regs.SETFLAGBIT(CPU_Regs.PF, parity_lookup[CPU_Regs.reg_eax.low()] != 0);
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, false);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, false);
            CPU_Regs.SETFLAGBIT(CPU_Regs.AF, false);
            type = t_UNKNOWN;
            return true;
        } else {
            CPU.CPU_PrepareException(0, 0);
            return false;
        }
    }

    //Took this from bochs, i seriously hate these weird bcd opcodes
    public static void AAD(int op1) {
        /*Bit16u*/
        int ax1 = CPU_Regs.reg_eax.high() * op1;
        /*Bit16u*/
        int ax2 = ax1 + CPU_Regs.reg_eax.low();
        CPU_Regs.reg_eax.low(ax2);
        CPU_Regs.reg_eax.high(0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.CF, false);
        CPU_Regs.SETFLAGBIT(CPU_Regs.OF, false);
        CPU_Regs.SETFLAGBIT(CPU_Regs.AF, false);
        CPU_Regs.SETFLAGBIT(CPU_Regs.SF, CPU_Regs.reg_eax.low() >= 0x80);
        CPU_Regs.SETFLAGBIT(CPU_Regs.ZF, CPU_Regs.reg_eax.low() == 0);
        CPU_Regs.SETFLAGBIT(CPU_Regs.PF, parity_lookup[CPU_Regs.reg_eax.low()] != 0);
        type = t_UNKNOWN;
    }

    public static void MULB(int l) {
        CPU_Regs.reg_eax.word(CPU_Regs.reg_eax.low() * l);
        FillFlagsNoCFOF();
        CPU_Regs.SETFLAGBIT(CPU_Regs.ZF, CPU_Regs.reg_eax.low() == 0);
        if ((CPU_Regs.reg_eax.word() & 0xff00) != 0) {
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, true);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, true);
        } else {
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, false);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, false);
        }
    }

    public static void MULW(int l) {
        /*Bitu*/
        int tempu = CPU_Regs.reg_eax.word() * l;
        CPU_Regs.reg_eax.word(tempu);
        CPU_Regs.reg_edx.word(tempu >>> 16);
        FillFlagsNoCFOF();
        CPU_Regs.SETFLAGBIT(CPU_Regs.ZF, CPU_Regs.reg_eax.word() == 0);
        if (CPU_Regs.reg_edx.word() != 0) {
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, true);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, true);
        } else {
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, false);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, false);
        }
    }

    public static void MULD(int l) {
        /*Bit64u*/
        long tempu = (CPU_Regs.reg_eax.dword & 0xFFFFFFFFL) * (l & 0xFFFFFFFFL);
        CPU_Regs.reg_eax.dword = (int) tempu;
        CPU_Regs.reg_edx.dword = (int) (tempu >> 32);
        FillFlagsNoCFOF();
        CPU_Regs.SETFLAGBIT(CPU_Regs.ZF, CPU_Regs.reg_eax.dword == 0);
        if (CPU_Regs.reg_edx.dword != 0) {
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, true);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, true);
        } else {
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, false);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, false);
        }
    }

    public static boolean DIVB(int val) {
        if (val == 0) {
            CPU.CPU_PrepareException(0, 0);
            return false;
        }
        /*Bitu*/
        int quo = CPU_Regs.reg_eax.word() / val;
        /*Bit8u*/
        int rem = CPU_Regs.reg_eax.word() % val;
        /*Bit8u*/
        int quo8 = quo & 0xff;
        if (quo > 0xff) {
            CPU.CPU_PrepareException(0, 0);
            return false;
        }
        CPU_Regs.reg_eax.high(rem);
        CPU_Regs.reg_eax.low(quo8);
        return true;
    }

    public static boolean DIVW(int val) {
        if (val == 0) {
            CPU.CPU_PrepareException(0, 0);
            return false;
        }
        /*Bitu*/
        long num = (/*Bit32u*/long) CPU_Regs.reg_edx.word() << 16 | CPU_Regs.reg_eax.word();
        /*Bitu*/
        long quo = num / val;
        /*intBit16u*/
        int rem = (int) (num % val);
        /*intBit16u*/
        int quo16 = (int) (quo & 0xffff);
        if (quo != quo16) {
            CPU.CPU_PrepareException(0, 0);
            return false;
        }
        CPU_Regs.reg_edx.word(rem);
        CPU_Regs.reg_eax.word(quo16);
        return true;
    }

    public static boolean DIVD(int val) {
        if (val == 0) {
            CPU.CPU_PrepareException(0, 0);
            return false;
        }
        /*Bit64u*/
        long num = (long) CPU_Regs.reg_edx.dword << 32 | CPU_Regs.reg_eax.dword & 0xFFFFFFFFL;
        try {
            /*Bit64u*/
            long quo = LongHelper.divideLongByInt(num, val);
            CPU_Regs.reg_edx.dword = (int) (quo >> 32);
            CPU_Regs.reg_eax.dword = (int) quo;
        } catch (OverflowException e) {
            CPU.CPU_PrepareException(0, 0);
            return false;
        }
        return true;
    }

    public static boolean IDIVB(int l) {
        /*Bits*/
        int val = (/*Bit8s*/byte) l;
        if (val == 0) {
            CPU.CPU_PrepareException(0, 0);
            return false;
        }
        /*Bits*/
        int quo = (/*Bit16s*/short) CPU_Regs.reg_eax.word() / val;
        /*Bit8s*/
        byte rem = (/*Bit8s*/byte) ((/*Bit16s*/short) CPU_Regs.reg_eax.word() % val);
        /*Bit8s*/
        byte quo8s = (/*Bit8s*/byte) (quo & 0xff);
        if (quo != quo8s) {
            CPU.CPU_PrepareException(0, 0);
            return false;
        }
        CPU_Regs.reg_eax.high(rem);
        CPU_Regs.reg_eax.low(quo8s);
        return true;
    }

    public static boolean IDIVW(int l) {
        /*Bits*/
        int val = (/*Bit16s*/short) l;
        if (val == 0) {
            CPU.CPU_PrepareException(0, 0);
            return false;
        }
        /*Bits*/
        int num = CPU_Regs.reg_edx.word() << 16 | CPU_Regs.reg_eax.word();
        /*Bits*/
        int quo = num / val;
        /*Bit16s*/
        int rem = num % val;
        /*Bit16s*/
        int quo16s = (/*Bit16s*/short) quo;
        if (quo != quo16s) {
            CPU.CPU_PrepareException(0, 0);
            return false;
        }
        CPU_Regs.reg_edx.word(rem);
        CPU_Regs.reg_eax.word(quo16s);
        return true;
    }

    public static boolean IDIVD(int val) {
        if (val == 0) {
            CPU.CPU_PrepareException(0, 0);
            return false;
        }
        /*Bit64s*/
        long num = (long) CPU_Regs.reg_edx.dword << 32 | CPU_Regs.reg_eax.dword & 0xFFFFFFFFL;
        /*Bit64s*/
        long quo = num / val;
        /*Bit32s*/
        int rem = (/*Bit32s*/int) (num % val);
        /*Bit32s*/
        int quo32s = (/*Bit32s*/int) (quo & 0xffffffffL);
        if (quo != quo32s) {
            CPU.CPU_PrepareException(0, 0);
            return false;
        }
        CPU_Regs.reg_edx.dword = rem;
        CPU_Regs.reg_eax.dword = quo32s;
        return true;
    }

    public static void IMULB(int l) {
        CPU_Regs.reg_eax.word((/*Bit8s*/byte) CPU_Regs.reg_eax.low() * (/*Bit8s*/byte) l);
        FillFlagsNoCFOF();
        if ((CPU_Regs.reg_eax.word() & 0xff80) == 0xff80 || (CPU_Regs.reg_eax.word() & 0xff80) == 0x0000) {
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, false);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, false);
        } else {
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, true);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, true);
        }
    }

    public static void IMULW(int l) {
        /*Bits*/
        int temps = (/*Bit16s*/short) CPU_Regs.reg_eax.word() * (/*Bit16s*/short) l;
        CPU_Regs.reg_eax.word(temps);
        CPU_Regs.reg_edx.word(temps >>> 16);
        FillFlagsNoCFOF();
        if ((temps & 0xffff8000) == 0xffff8000 || (temps & 0xffff8000) == 0x0000) {
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, false);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, false);
        } else {
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, true);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, true);
        }
    }

    public static void IMULD(int l) {
        /*Bit64s*/
        long temps = (long) CPU_Regs.reg_eax.dword * l;
        CPU_Regs.reg_eax.dword = (int) temps;
        CPU_Regs.reg_edx.dword = (int) (temps >> 32);
        FillFlagsNoCFOF();
        if (CPU_Regs.reg_edx.dword == 0xffffffff && (CPU_Regs.reg_eax.dword & 0x80000000) != 0) {
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, false);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, false);
        } else if (CPU_Regs.reg_edx.dword == 0x00000000 && CPU_Regs.reg_eax.dword >= 0) {
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, false);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, false);
        } else {
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, true);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, true);
        }
    }

    public static int DIMULW(int op2, int op3) {
        /*Bits*/
        int res = (/*Bit16s*/short) op2 * (/*Bit16s*/short) op3;
        FillFlagsNoCFOF();
        if (res >= -32768 && res <= 32767) {
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, false);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, false);
        } else {
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, true);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, true);
        }
        return res & 0xffff;
    }

    public static int DIMULD(int op2, int op3) {
        /*Bit64s*/
        long res = (long) op2 * op3;
        FillFlagsNoCFOF();
        if (res >= -((/*Bit64s*/long) 2147483647 + 1) && res <= 2147483647) {
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, false);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, false);
        } else {
            CPU_Regs.SETFLAGBIT(CPU_Regs.CF, true);
            CPU_Regs.SETFLAGBIT(CPU_Regs.OF, true);
        }
        return (int) res;
    }

    public static int DSHLD(int op2, int val, int l) {
        lf_var2b(val);
        var1 = l;
        res = l << val | op2 >>> 32 - val;
        type = t_DSHLd;
        return res;
    }

    public static int DSHRD(int op2, int val, int l) {
        lf_var2b(val);
        var1 = l;
        res = l >>> val | op2 << 32 - val;
        type = t_DSHRd;
        return res;
    }

    /* let's hope bochs has it correct with the higher than 16 shifts */
    /* double-precision shift left has low Bits in second argument */
    public static boolean valid_DSHLW(int op3) {
        return (op3 & 0x1f) != 0;
    }

    public static int do_DSHLW(int op2, int op3, int l) {
        int val = op3 & 0x1F;
        lf_var2b(val);
        var1 = l << 16 | op2;
        long tempd = (long) var1 << lf_var2b();
        if (lf_var2b() > 16)
            tempd |= op2 << lf_var2b() - 16;
        lf_resw((int) (tempd >>> 16));
        type = t_DSHLw;
        return lf_resw();
    }

    public static void DSHLW(int op2, int op3, loadw l, savew s) {
        if (valid_DSHLW(op3))
            s.call(do_DSHLW(op2, op3, l.call()));
    }

    /* double-precision shift right has high Bits in second argument */
    public static boolean valid_DSHRW(int op3) {
        return (op3 & 0x1f) != 0;
    }

    public static int do_DSHRW(int op2, int op3, int l) {
        /*Bit8u*/
        int val = op3 & 0x1F;
        lf_var2b(val);
        var1 = op2 << 16 | l;
        /*Bit32u*/
        long tempd = var1 >>> lf_var2b();
        if (lf_var2b() > 16)
            tempd |= op2 << 32 - lf_var2b();
        lf_resw((/*Bit16u*/int) tempd);
        type = t_DSHRw;
        return lf_resw();
    }

    public static void DSHRW(int op2, int op3, loadw l, savew s) {
        if (valid_DSHRW(op3))
            s.call(do_DSHRW(op2, op3, l.call()));
    }

    public static int BSWAPW(int op1) {
        return 0;
    }

    public static int BSWAPD(int op1) {
        return op1 >>> 24 | op1 >> 8 & 0xFF00 | op1 << 8 & 0xFF0000 | op1 << 24 & 0xFF000000;
    }

    public static int Negd(int op1) {
        Flags.type = Flags.t_NEGd;
        var1 = op1;
        res = 0 - op1;
        return res;
    }

    public static int Negb(int op1) {
        Flags.type = Flags.t_NEGb;
        Flags.lf_var1b(op1);
        Flags.lf_resb(0 - Flags.lf_var1b());
        return Flags.lf_resb();
    }

    public static int Negw(int op1) {
        Flags.type = Flags.t_NEGw;
        Flags.lf_var1w(op1);
        Flags.lf_resw(0 - Flags.lf_var1w());
        return Flags.lf_resw();
    }

    public interface loadb {
        int call();
    }

    public interface saveb {
        void call(int value);
    }

    public interface loadw {
        int call();
    }

    public interface savew {
        void call(int value);
    }
}
