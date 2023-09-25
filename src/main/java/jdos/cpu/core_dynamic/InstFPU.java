package jdos.cpu.core_dynamic;

import jdos.cpu.CPU_Regs;
import jdos.cpu.Flags;
import jdos.fpu.FPU;

public class InstFPU {
    abstract static protected class FpuOpEA extends Op {
        EaaBase get_eaa;

        public FpuOpEA(int rm) {
            get_eaa = Mod.getEaa(rm);
        }

        @Override
        public int sets() {
            return 0;
        }

        @Override
        public int gets() {
            return 0;
        }

        @Override
        public boolean throwsException() {
            return false;
        }

        @Override
        public boolean accessesMemory() {
            return true;
        }

        @Override
        public boolean usesEip() {
            return false;
        }

        @Override
        public boolean setsEip() {
            return false;
        }
    }

    abstract static protected class FpuOpNormal extends Op {
        int rm;

        public FpuOpNormal(int rm) {
            this.rm = rm;
        }

        @Override
        public int sets() {
            return 0;
        }

        @Override
        public int gets() {
            return 0;
        }

        @Override
        public boolean throwsException() {
            return false;
        }

        @Override
        public boolean accessesMemory() {
            return false;
        }

        @Override
        public boolean usesEip() {
            return false;
        }

        @Override
        public boolean setsEip() {
            return false;
        }
    }

    public static final class FADD_SINGLE_REAL extends FpuOpEA {
        public FADD_SINGLE_REAL(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FADD_SINGLE_REAL(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FADD_SINGLE_REAL";
        }
    }

    public static final class FMUL_SINGLE_REAL extends FpuOpEA {
        public FMUL_SINGLE_REAL(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FMUL_SINGLE_REAL(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FMUL_SINGLE_REAL";
        }
    }

    public static final class FCOM_SINGLE_REAL extends FpuOpEA {
        boolean pop;

        public FCOM_SINGLE_REAL(int rm, boolean pop) {
            super(rm);
            this.pop = pop;
        }

        @Override
        public int call() {
            FPU.FCOM_SINGLE_REAL(get_eaa.call(), pop);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FCOM_SINGLE_REAL";
        }
    }

    public static final class FSUB_SINGLE_REAL extends FpuOpEA {
        public FSUB_SINGLE_REAL(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FSUB_SINGLE_REAL(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FSUB_SINGLE_REAL";
        }
    }

    public static final class FSUBR_SINGLE_REAL extends FpuOpEA {
        public FSUBR_SINGLE_REAL(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FSUBR_SINGLE_REAL(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FSUBR_SINGLE_REAL";
        }
    }

    public static final class FDIV_SINGLE_REAL extends FpuOpEA {
        public FDIV_SINGLE_REAL(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FDIV_SINGLE_REAL(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FDIV_SINGLE_REAL";
        }
    }

    public static final class FDIVR_SINGLE_REAL extends FpuOpEA {
        public FDIVR_SINGLE_REAL(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FDIVR_SINGLE_REAL(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FDIVR_SINGLE_REAL";
        }
    }

    public static final class FADD_ST0_STj extends FpuOpNormal {
        public FADD_ST0_STj(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FADD_ST0_STj(rm);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FADD_ST0_STj";
        }
    }

    public static final class FMUL_ST0_STj extends FpuOpNormal {
        public FMUL_ST0_STj(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FMUL_ST0_STj(rm);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FMUL_ST0_STj";
        }
    }

    public static final class FCOM_STi extends FpuOpNormal {
        boolean pop;

        public FCOM_STi(int rm, boolean pop) {
            super(rm);
            this.pop = pop;
        }

        @Override
        public int call() {
            FPU.FCOM_STi(rm, pop);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FCOM_STi";
        }
    }

    public static final class FSUB_ST0_STj extends FpuOpNormal {
        public FSUB_ST0_STj(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FSUB_ST0_STj(rm);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FSUB_ST0_STj";
        }
    }

    public static final class FSUBR_ST0_STj extends FpuOpNormal {
        public FSUBR_ST0_STj(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FSUBR_ST0_STj(rm);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FSUBR_ST0_STj";
        }
    }

    public static final class FDIV_ST0_STj extends FpuOpNormal {
        public FDIV_ST0_STj(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FDIV_ST0_STj(rm);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FDIV_ST0_STj";
        }
    }

    public static final class FDIVR_ST0_STj extends FpuOpNormal {
        public FDIVR_ST0_STj(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FDIVR_ST0_STj(rm);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FDIVR_ST0_STj";
        }
    }

    public static final class FLD_SINGLE_REAL extends FpuOpEA {
        public FLD_SINGLE_REAL(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FLD_SINGLE_REAL(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FLD_SINGLE_REAL";
        }
    }

    public static final class FST_SINGLE_REAL extends FpuOpEA {
        boolean pop;

        public FST_SINGLE_REAL(int rm, boolean pop) {
            super(rm);
            this.pop = pop;
        }

        @Override
        public int call() {
            FPU.FST_SINGLE_REAL(get_eaa.call(), pop);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FST_SINGLE_REAL";
        }
    }

    public static final class FLDENV extends FpuOpEA {
        public FLDENV(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FLDENV(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FLDENV";
        }
    }

    public static final class FLDCW extends FpuOpEA {
        public FLDCW(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FLDCW(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FLDCW";
        }
    }

    public static final class FNSTENV extends FpuOpEA {
        public FNSTENV(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FNSTENV(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FNSTENV";
        }
    }

    public static final class FNSTCW extends FpuOpEA {
        public FNSTCW(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FNSTCW(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FNSTCW";
        }
    }

    public static final class FLD_STi extends FpuOpNormal {
        public FLD_STi(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FLD_STi(rm);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FLD_STi";
        }
    }

    public static final class FXCH_STi extends FpuOpNormal {
        public FXCH_STi(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FXCH_STi(rm);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FXCH_STi";
        }
    }

    public static final class FNOP extends FpuOpNormal {
        public FNOP() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FNOP();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FNOP";
        }
    }

    public static final class FST_STi extends FpuOpNormal {
        boolean pop;

        public FST_STi(int rm, boolean pop) {
            super(rm);
            this.pop = pop;
        }

        @Override
        public int call() {
            FPU.FST_STi(rm, pop);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FST_STi";
        }
    }

    public static final class FCHS extends FpuOpNormal {
        public FCHS() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FCHS();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FCHS";
        }
    }

    public static final class FABS extends FpuOpNormal {
        public FABS() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FABS();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FABS";
        }
    }

    public static final class FTST extends FpuOpNormal {
        public FTST() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FTST();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FTST";
        }
    }

    public static final class FXAM extends FpuOpNormal {
        public FXAM() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FXAM();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FXAM";
        }
    }

    public static final class FLD1 extends FpuOpNormal {
        public FLD1() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FLD1();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FLD1";
        }
    }

    public static final class FLDL2T extends FpuOpNormal {
        public FLDL2T() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FLDL2T();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FLDL2T";
        }
    }

    public static final class FLDL2E extends FpuOpNormal {
        public FLDL2E() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FLDL2E();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FLDL2E";
        }
    }

    public static final class FLDPI extends FpuOpNormal {
        public FLDPI() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FLDPI();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FLDPI";
        }
    }

    public static final class FLDLG2 extends FpuOpNormal {
        public FLDLG2() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FLDLG2();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FLDLG2";
        }
    }

    public static final class FLDLN2 extends FpuOpNormal {
        public FLDLN2() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FLDLN2();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FLDLN2";
        }
    }

    public static final class FLDZ extends FpuOpNormal {
        public FLDZ() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FLDZ();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FLDZ";
        }
    }

    public static final class F2XM1 extends FpuOpNormal {
        public F2XM1() {
            super(0);
        }

        @Override
        public int call() {
            FPU.F2XM1();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "F2XM1";
        }
    }

    public static final class FYL2X extends FpuOpNormal {
        public FYL2X() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FYL2X();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FYL2X";
        }
    }

    public static final class FPTAN extends FpuOpNormal {
        public FPTAN() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FPTAN();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FPTAN";
        }
    }

    public static final class FPATAN extends FpuOpNormal {
        public FPATAN() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FPATAN();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FPATAN";
        }
    }

    public static final class FXTRACT extends FpuOpNormal {
        public FXTRACT() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FXTRACT();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FXTRACT";
        }
    }

    public static final class FPREM extends FpuOpNormal {
        boolean bRoundNearest;

        public FPREM(boolean bRoundNearest) {
            super(0);
            this.bRoundNearest = bRoundNearest;
        }

        @Override
        public int call() {
            FPU.FPREM(bRoundNearest);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FPREM" + (bRoundNearest ? " round nearest" : "");
        }
    }

    public static final class FDECSTP extends FpuOpNormal {
        public FDECSTP() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FDECSTP();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FDECSTP";
        }
    }

    public static final class FINCSTP extends FpuOpNormal {
        public FINCSTP() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FINCSTP();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FINCSTP";
        }
    }

    public static final class FYL2XP1 extends FpuOpNormal {
        public FYL2XP1() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FYL2XP1();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FYL2XP1";
        }
    }

    public static final class FSQRT extends FpuOpNormal {
        public FSQRT() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FSQRT();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FSQRT";
        }
    }

    public static final class FSINCOS extends FpuOpNormal {
        public FSINCOS() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FSINCOS();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FSINCOS";
        }
    }

    public static final class FRNDINT extends FpuOpNormal {
        public FRNDINT() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FRNDINT();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FRNDINT";
        }
    }

    public static final class FSCALE extends FpuOpNormal {
        public FSCALE() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FSCALE();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FSCALE";
        }
    }

    public static final class FSIN extends FpuOpNormal {
        public FSIN() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FSIN();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FSIN";
        }
    }

    public static final class FCOS extends FpuOpNormal {
        public FCOS() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FCOS();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FCOS";
        }
    }

    public static final class FIADD_DWORD_INTEGER extends FpuOpEA {
        public FIADD_DWORD_INTEGER(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FIADD_DWORD_INTEGER(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FIADD_DWORD_INTEGER";
        }
    }

    public static final class FIMUL_DWORD_INTEGER extends FpuOpEA {
        public FIMUL_DWORD_INTEGER(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FIMUL_DWORD_INTEGER(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FIMUL_DWORD_INTEGER";
        }
    }

    public static final class FICOM_DWORD_INTEGER extends FpuOpEA {
        boolean pop;

        public FICOM_DWORD_INTEGER(int rm, boolean pop) {
            super(rm);
            this.pop = pop;
        }

        @Override
        public int call() {
            FPU.FICOM_DWORD_INTEGER(get_eaa.call(), pop);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FICOM_DWORD_INTEGER";
        }
    }

    public static final class FISUB_DWORD_INTEGER extends FpuOpEA {
        public FISUB_DWORD_INTEGER(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FISUB_DWORD_INTEGER(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FISUB_DWORD_INTEGER";
        }
    }

    public static final class FISUBR_DWORD_INTEGER extends FpuOpEA {
        public FISUBR_DWORD_INTEGER(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FISUBR_DWORD_INTEGER(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FISUBR_DWORD_INTEGER";
        }
    }

    public static final class FIDIV_DWORD_INTEGER extends FpuOpEA {
        public FIDIV_DWORD_INTEGER(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FIDIV_DWORD_INTEGER(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FIDIV_DWORD_INTEGER";
        }
    }

    public static final class FIDIVR_DWORD_INTEGER extends FpuOpEA {
        public FIDIVR_DWORD_INTEGER(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FIDIVR_DWORD_INTEGER(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FIDIVR_DWORD_INTEGER";
        }
    }

    public static final class FCMOV_ST0_STj_CF extends FpuOpNormal {
        public FCMOV_ST0_STj_CF(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FCMOV_ST0_STj(rm, Flags.get_CF());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FCMOV_ST0_STj_CF";
        }
    }

    public static final class FCMOV_ST0_STj_ZF extends FpuOpNormal {
        public FCMOV_ST0_STj_ZF(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FCMOV_ST0_STj(rm, Flags.get_ZF());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FCMOV_ST0_STj_ZF";
        }
    }

    public static final class FCMOV_ST0_STj_CF_OR_ZF extends FpuOpNormal {
        public FCMOV_ST0_STj_CF_OR_ZF(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FCMOV_ST0_STj(rm, Flags.get_CF() || Flags.get_ZF());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FCMOV_ST0_STj_CF_OR_ZF";
        }
    }

    public static final class FCMOV_ST0_STj_PF extends FpuOpNormal {
        public FCMOV_ST0_STj_PF(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FCMOV_ST0_STj(rm, Flags.get_PF());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FCMOV_ST0_STj_PF";
        }
    }

    public static final class FUCOMPP extends FpuOpNormal {
        public FUCOMPP() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FUCOMPP();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FUCOMPP";
        }
    }

    public static final class FILD_DWORD_INTEGER extends FpuOpEA {
        public FILD_DWORD_INTEGER(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FILD_DWORD_INTEGER(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FILD_DWORD_INTEGER";
        }
    }

    public static final class FISTTP32 extends FpuOpEA {
        public FISTTP32(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FISTTP32(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FISTTP32";
        }
    }

    public static final class FIST_DWORD_INTEGER extends FpuOpEA {
        boolean pop;

        public FIST_DWORD_INTEGER(int rm, boolean pop) {
            super(rm);
            this.pop = pop;
        }

        @Override
        public int call() {
            FPU.FIST_DWORD_INTEGER(get_eaa.call(), pop);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FIST_DWORD_INTEGER";
        }
    }

    public static final class FLD_EXTENDED_REAL extends FpuOpEA {
        public FLD_EXTENDED_REAL(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FLD_EXTENDED_REAL(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FLD_EXTENDED_REAL";
        }
    }

    public static final class FSTP_EXTENDED_REAL extends FpuOpEA {
        public FSTP_EXTENDED_REAL(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FSTP_EXTENDED_REAL(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FSTP_EXTENDED_REAL";
        }
    }

    public static final class FCMOV_ST0_STj_NCF extends FpuOpNormal {
        public FCMOV_ST0_STj_NCF(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FCMOV_ST0_STj(rm, !Flags.get_CF());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FCMOV_ST0_STj_NCF";
        }
    }

    public static final class FCMOV_ST0_STj_NZF extends FpuOpNormal {
        public FCMOV_ST0_STj_NZF(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FCMOV_ST0_STj(rm, !Flags.get_ZF());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FCMOV_ST0_STj_NZF";
        }
    }

    public static final class FCMOV_ST0_STj_NCF_AND_NZF extends FpuOpNormal {
        public FCMOV_ST0_STj_NCF_AND_NZF(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FCMOV_ST0_STj(rm, !Flags.get_CF() && !Flags.get_ZF());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FCMOV_ST0_STj_NCF_AND_NZF";
        }
    }

    public static final class FCMOV_ST0_STj_NPF extends FpuOpNormal {
        public FCMOV_ST0_STj_NPF(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FCMOV_ST0_STj(rm, !Flags.get_PF());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FCMOV_ST0_STj_NPF";
        }
    }

    public static final class FNCLEX extends FpuOpNormal {
        public FNCLEX() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FNCLEX();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FNCLEX";
        }
    }

    public static final class FNINIT extends FpuOpNormal {
        public FNINIT() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FNINIT();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FNINIT";
        }
    }

    public static final class FUCOMI_ST0_STj extends FpuOpNormal {
        boolean pop;

        public FUCOMI_ST0_STj(int rm, boolean pop) {
            super(0);
            this.pop = pop;
        }

        @Override
        public int call() {
            FPU.FUCOMI_ST0_STj(rm, pop);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FUCOMI_ST0_STj";
        }
    }

    public static final class FCOMI_ST0_STj extends FpuOpNormal {
        boolean pop;

        public FCOMI_ST0_STj(int rm, boolean pop) {
            super(0);
            this.pop = pop;
        }

        @Override
        public int call() {
            FPU.FCOMI_ST0_STj(rm, pop);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FCOMI_ST0_STj";
        }
    }

    public static final class FADD_DOUBLE_REAL extends FpuOpEA {
        public FADD_DOUBLE_REAL(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FADD_DOUBLE_REAL(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FADD_DOUBLE_REAL";
        }
    }

    public static final class FMUL_DOUBLE_REAL extends FpuOpEA {
        public FMUL_DOUBLE_REAL(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FMUL_DOUBLE_REAL(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FMUL_DOUBLE_REAL";
        }
    }

    public static final class FCOM_DOUBLE_REAL extends FpuOpEA {
        boolean pop;

        public FCOM_DOUBLE_REAL(int rm, boolean pop) {
            super(rm);
            this.pop = pop;
        }

        @Override
        public int call() {
            FPU.FCOM_DOUBLE_REAL(get_eaa.call(), pop);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FCOM_DOUBLE_REAL";
        }
    }

    public static final class FSUB_DOUBLE_REAL extends FpuOpEA {
        public FSUB_DOUBLE_REAL(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FSUB_DOUBLE_REAL(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FSUB_DOUBLE_REAL";
        }
    }

    public static final class FSUBR_DOUBLE_REAL extends FpuOpEA {
        public FSUBR_DOUBLE_REAL(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FSUBR_DOUBLE_REAL(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FSUBR_DOUBLE_REAL";
        }
    }

    public static final class FDIV_DOUBLE_REAL extends FpuOpEA {
        public FDIV_DOUBLE_REAL(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FDIV_DOUBLE_REAL(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FDIV_DOUBLE_REAL";
        }
    }

    public static final class FDIVR_DOUBLE_REAL extends FpuOpEA {
        public FDIVR_DOUBLE_REAL(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FDIVR_DOUBLE_REAL(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FDIVR_DOUBLE_REAL";
        }
    }

    public static final class FADD_STi_ST0 extends FpuOpNormal {
        boolean pop;

        public FADD_STi_ST0(int rm, boolean pop) {
            super(rm);
            this.pop = pop;
        }

        @Override
        public int call() {
            FPU.FADD_STi_ST0(rm, pop);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FADD_STi_ST0";
        }
    }

    public static final class FMUL_STi_ST0 extends FpuOpNormal {
        boolean pop;

        public FMUL_STi_ST0(int rm, boolean pop) {
            super(rm);
            this.pop = pop;
        }

        @Override
        public int call() {
            FPU.FMUL_STi_ST0(rm, pop);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FMUL_STi_ST0";
        }
    }

    public static final class FSUBR_STi_ST0 extends FpuOpNormal {
        boolean pop;

        public FSUBR_STi_ST0(int rm, boolean pop) {
            super(rm);
            this.pop = pop;
        }

        @Override
        public int call() {
            FPU.FSUBR_STi_ST0(rm, pop);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FSUBR_STi_ST0";
        }
    }

    public static final class FSUB_STi_ST0 extends FpuOpNormal {
        boolean pop;

        public FSUB_STi_ST0(int rm, boolean pop) {
            super(rm);
            this.pop = pop;
        }

        @Override
        public int call() {
            FPU.FSUB_STi_ST0(rm, pop);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FSUB_STi_ST0";
        }
    }

    public static final class FDIVR_STi_ST0 extends FpuOpNormal {
        boolean pop;

        public FDIVR_STi_ST0(int rm, boolean pop) {
            super(rm);
            this.pop = pop;
        }

        @Override
        public int call() {
            FPU.FDIVR_STi_ST0(rm, pop);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FDIVR_STi_ST0";
        }
    }

    public static final class FDIV_STi_ST0 extends FpuOpNormal {
        boolean pop;

        public FDIV_STi_ST0(int rm, boolean pop) {
            super(rm);
            this.pop = pop;
        }

        @Override
        public int call() {
            FPU.FDIV_STi_ST0(rm, pop);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FDIV_STi_ST0";
        }
    }

    public static final class FLD_DOUBLE_REAL extends FpuOpEA {
        public FLD_DOUBLE_REAL(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FLD_DOUBLE_REAL(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FLD_DOUBLE_REAL";
        }
    }

    public static final class FISTTP64 extends FpuOpEA {
        public FISTTP64(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FISTTP64(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FISTTP64";
        }
    }

    public static final class FST_DOUBLE_REAL extends FpuOpEA {
        boolean pop;

        public FST_DOUBLE_REAL(int rm, boolean pop) {
            super(rm);
            this.pop = pop;
        }

        @Override
        public int call() {
            FPU.FST_DOUBLE_REAL(get_eaa.call(), pop);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FST_DOUBLE_REAL";
        }
    }

    public static final class FRSTOR extends FpuOpEA {
        public FRSTOR(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FRSTOR(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FRSTOR";
        }
    }

    public static final class FNSAVE extends FpuOpEA {
        public FNSAVE(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FNSAVE(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FNSAVE";
        }
    }

    public static final class FNSTSW extends FpuOpEA {
        public FNSTSW(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FNSTSW(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FNSTSW";
        }
    }

    public static final class FFREE_STi extends FpuOpNormal {
        public FFREE_STi(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FFREE_STi(rm);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FFREE_STi";
        }
    }

    public static final class FUCOM_STi extends FpuOpNormal {
        boolean pop;

        public FUCOM_STi(int rm, boolean pop) {
            super(rm);
            this.pop = pop;
        }

        @Override
        public int call() {
            FPU.FUCOM_STi(rm, pop);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FUCOM_STi";
        }
    }

    public static final class FIADD_WORD_INTEGER extends FpuOpEA {
        public FIADD_WORD_INTEGER(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FIADD_WORD_INTEGER(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FIADD_WORD_INTEGER";
        }
    }

    public static final class FIMUL_WORD_INTEGER extends FpuOpEA {
        public FIMUL_WORD_INTEGER(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FIMUL_WORD_INTEGER(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FIMUL_WORD_INTEGER";
        }
    }

    public static final class FICOM_WORD_INTEGER extends FpuOpEA {
        boolean pop;

        public FICOM_WORD_INTEGER(int rm, boolean pop) {
            super(rm);
            this.pop = pop;
        }

        @Override
        public int call() {
            FPU.FICOM_WORD_INTEGER(get_eaa.call(), pop);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FICOM_WORD_INTEGER";
        }
    }

    public static final class FISUB_WORD_INTEGER extends FpuOpEA {
        public FISUB_WORD_INTEGER(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FISUB_WORD_INTEGER(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FISUB_WORD_INTEGER";
        }
    }

    public static final class FISUBR_WORD_INTEGER extends FpuOpEA {
        public FISUBR_WORD_INTEGER(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FISUBR_WORD_INTEGER(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FISUBR_WORD_INTEGER";
        }
    }

    public static final class FIDIV_WORD_INTEGER extends FpuOpEA {
        public FIDIV_WORD_INTEGER(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FIDIV_WORD_INTEGER(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FIDIV_WORD_INTEGER";
        }
    }

    public static final class FIDIVR_WORD_INTEGER extends FpuOpEA {
        public FIDIVR_WORD_INTEGER(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FIDIVR_WORD_INTEGER(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FIDIVR_WORD_INTEGER";
        }
    }

    public static final class FCOMPP extends FpuOpNormal {
        public FCOMPP() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FCOMPP();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FCOMPP";
        }
    }

    public static final class FILD_WORD_INTEGER extends FpuOpEA {
        public FILD_WORD_INTEGER(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FILD_WORD_INTEGER(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FILD_WORD_INTEGER";
        }
    }

    public static final class FISTTP16 extends FpuOpEA {
        public FISTTP16(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FISTTP16(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FISTTP16";
        }
    }

    public static final class FIST_WORD_INTEGER extends FpuOpEA {
        boolean pop;

        public FIST_WORD_INTEGER(int rm, boolean pop) {
            super(rm);
            this.pop = pop;
        }

        @Override
        public int call() {
            FPU.FIST_WORD_INTEGER(get_eaa.call(), pop);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FIST_WORD_INTEGER";
        }
    }

    public static final class FBLD_PACKED_BCD extends FpuOpEA {
        public FBLD_PACKED_BCD(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FBLD_PACKED_BCD(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FBLD_PACKED_BCD";
        }
    }

    public static final class FILD_QWORD_INTEGER extends FpuOpEA {
        public FILD_QWORD_INTEGER(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FILD_QWORD_INTEGER(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FILD_QWORD_INTEGER";
        }
    }

    public static final class FBSTP_PACKED_BCD extends FpuOpEA {
        public FBSTP_PACKED_BCD(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FBSTP_PACKED_BCD(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FBSTP_PACKED_BCD";
        }
    }

    public static final class FISTP_QWORD_INTEGER extends FpuOpEA {
        public FISTP_QWORD_INTEGER(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FISTP_QWORD_INTEGER(get_eaa.call());
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FISTP_QWORD_INTEGER";
        }
    }

    public static final class FFREEP_STi extends FpuOpNormal {
        public FFREEP_STi(int rm) {
            super(rm);
        }

        @Override
        public int call() {
            FPU.FFREEP_STi(rm);
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FFREEP_STi";
        }
    }

    public static final class FNSTSW_AX extends FpuOpNormal {
        public FNSTSW_AX() {
            super(0);
        }

        @Override
        public int call() {
            FPU.FNSTSW_AX();
            CPU_Regs.reg_eip += eip_count;
            return next.call();
        }

        @Override
        public String description() {
            return "FNSTSW_AX";
        }
    }
}
