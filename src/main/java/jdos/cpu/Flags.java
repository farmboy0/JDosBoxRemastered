package jdos.cpu;

public class Flags {
    public static final GetFlags t_MUL = new GetFlags() {
        @Override
        public boolean CF() {
            return CPU_Regs.GETFLAG(CPU_Regs.CF) != 0;
        }

        @Override
        public boolean AF() {
            return false;
        }

        @Override
        public boolean ZF() {
            return false;
        }

        @Override
        public boolean SF() {
            return false;
        }

        @Override
        public boolean OF() {
            return CPU_Regs.GETFLAG(CPU_Regs.OF) != 0;
        }
    };
    public static final GetFlags t_DIV = new GetFlags() {
        @Override
        public boolean CF() {
            return false;
        }

        @Override
        public boolean AF() {
            return false;
        }

        @Override
        public boolean ZF() {
            return false;
        }

        @Override
        public boolean SF() {
            return false;
        }

        @Override
        public boolean OF() {
            return false;
        }
    };
    // needs to come before creating LazyFlags
    public static GetFlags t_UNKNOWN = new GetFlags() {
        @Override
        public boolean CF() {
            return CPU_Regs.GETFLAG(CPU_Regs.CF) != 0;
        }

        @Override
        public boolean AF() {
            return CPU_Regs.GETFLAG(CPU_Regs.AF) != 0;
        }

        @Override
        public boolean ZF() {
            return CPU_Regs.GETFLAG(CPU_Regs.ZF) != 0;
        }

        @Override
        public boolean SF() {
            return CPU_Regs.GETFLAG(CPU_Regs.SF) != 0;
        }

        @Override
        public boolean OF() {
            return CPU_Regs.GETFLAG(CPU_Regs.OF) != 0;
        }
    };
    public static int var1;
    public static int var2;
    public static int res;
    public static final GetFlags t_INCb = new GetFlags() {
        @Override
        public boolean CF() {
            return CPU_Regs.GETFLAG(CPU_Regs.CF) != 0;
        }

        @Override
        public boolean AF() {
            return (lf_resb() & 0x0f) == 0;
        }

        @Override
        public boolean ZF() {
            return lf_resb() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resb() & 0x80) != 0;
        }

        @Override
        public boolean OF() {
            return lf_resb() == 0x80;
        }
    };
    public static final GetFlags t_INCw = new GetFlags() {
        @Override
        public boolean CF() {
            return CPU_Regs.GETFLAG(CPU_Regs.CF) != 0;
        }

        @Override
        public boolean AF() {
            return (lf_resw() & 0x0f) == 0;
        }

        @Override
        public boolean ZF() {
            return lf_resw() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resw() & 0x8000) != 0;
        }

        @Override
        public boolean OF() {
            return lf_resw() == 0x8000;
        }
    };
    public static final GetFlags t_INCd = new GetFlags() {
        @Override
        public boolean CF() {
            return CPU_Regs.GETFLAG(CPU_Regs.CF) != 0;
        }

        @Override
        public boolean AF() {
            return (lf_resd() & 0x0f) == 0;
        }

        @Override
        public boolean ZF() {
            return lf_resd() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resd() & 0x80000000) != 0;
        }

        @Override
        public boolean OF() {
            return lf_resd() == 0x80000000;
        }
    };
    public static final GetFlags t_DECb = new GetFlags() {
        @Override
        public boolean CF() {
            return CPU_Regs.GETFLAG(CPU_Regs.CF) != 0;
        }

        @Override
        public boolean AF() {
            return (lf_resb() & 0x0f) == 0x0f;
        }

        @Override
        public boolean ZF() {
            return lf_resb() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resb() & 0x80) != 0;
        }

        @Override
        public boolean OF() {
            return lf_resb() == 0x7f;
        }
    };
    public static final GetFlags t_DECw = new GetFlags() {
        @Override
        public boolean CF() {
            return CPU_Regs.GETFLAG(CPU_Regs.CF) != 0;
        }

        @Override
        public boolean AF() {
            return (lf_resw() & 0x0f) == 0x0f;
        }

        @Override
        public boolean ZF() {
            return lf_resw() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resw() & 0x8000) != 0;
        }

        @Override
        public boolean OF() {
            return lf_resw() == 0x7fff;
        }
    };
    public static final GetFlags t_DECd = new GetFlags() {
        @Override
        public boolean CF() {
            return CPU_Regs.GETFLAG(CPU_Regs.CF) != 0;
        }

        @Override
        public boolean AF() {
            return (lf_resd() & 0x0f) == 0x0f;
        }

        @Override
        public boolean ZF() {
            return lf_resd() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resd() & 0x80000000L) != 0;
        }

        @Override
        public boolean OF() {
            return lf_resd() == 0x7fffffff;
        }
    };
    public static final GetFlags t_ADDb = new GetFlags() {
        @Override
        public boolean CF() {
            return lf_resb() < lf_var1b();
        }

        @Override
        public boolean AF() {
            return ((lf_var1b() ^ lf_var2b() ^ lf_resb()) & 0x10) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resb() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resb() & 0x80) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_var1b() ^ lf_var2b() ^ 0x80) & (lf_resb() ^ lf_var2b()) & 0x80) != 0;
        }
    };
    public static final GetFlags t_ADDw = new GetFlags() {
        @Override
        public boolean CF() {
            return lf_resw() < lf_var1w();
        }

        @Override
        public boolean AF() {
            return ((lf_var1w() ^ lf_var2w() ^ lf_resw()) & 0x10) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resw() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resw() & 0x8000) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_var1w() ^ lf_var2w() ^ 0x8000) & (lf_resw() ^ lf_var2w()) & 0x8000) != 0;
        }
    };
    public static final GetFlags t_ADDd = new GetFlags() {
        @Override
        public boolean CF() {
            return (lf_resd() & 0xFFFFFFFFL) < (lf_var1d() & 0xFFFFFFFFL);
        }

        @Override
        public boolean AF() {
            return ((lf_var1d() ^ lf_var2d() ^ lf_resd()) & 0x10) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resd() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resd() & 0x80000000) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_var1d() ^ lf_var2d() ^ 0x80000000) & (lf_resd() ^ lf_var2d()) & 0x80000000) != 0;
        }
    };
    public static final GetFlags t_ORb = new GetFlags() {
        @Override
        public boolean CF() {
            return false;
        }

        @Override
        public boolean AF() {
            return false;
        }

        @Override
        public boolean ZF() {
            return lf_resb() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resb() & 0x80) != 0;
        }

        @Override
        public boolean OF() {
            return false;
        }
    };

//    public static void lf_var2w(int s) {
//        var2=var2 & ~0xFFFF;
//        var2=var2 | s & 0xFFFF;
//    }
    public static final GetFlags t_ORw = new GetFlags() {
        @Override
        public boolean CF() {
            return false;
        }

        @Override
        public boolean AF() {
            return false;
        }

        @Override
        public boolean ZF() {
            return lf_resw() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resw() & 0x8000) != 0;
        }

        @Override
        public boolean OF() {
            return false;
        }
    };
    public static final GetFlags t_ORd = new GetFlags() {
        @Override
        public boolean CF() {
            return false;
        }

        @Override
        public boolean AF() {
            return false;
        }

        @Override
        public boolean ZF() {
            return lf_resd() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resd() & 0x80000000) != 0;
        }

        @Override
        public boolean OF() {
            return false;
        }
    };
    public static final GetFlags t_ANDb = new GetFlags() {
        @Override
        public boolean CF() {
            return false;
        }

        @Override
        public boolean AF() {
            return false;
        }

        @Override
        public boolean ZF() {
            return lf_resb() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resb() & 0x80) != 0;
        }

        @Override
        public boolean OF() {
            return false;
        }
    };
    public static final GetFlags t_ANDw = new GetFlags() {
        @Override
        public boolean CF() {
            return false;
        }

        @Override
        public boolean AF() {
            return false;
        }

        @Override
        public boolean ZF() {
            return lf_resw() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resw() & 0x8000) != 0;
        }

        @Override
        public boolean OF() {
            return false;
        }
    };
    public static final GetFlags t_ANDd = new GetFlags() {
        @Override
        public boolean CF() {
            return false;
        }

        @Override
        public boolean AF() {
            return false;
        }

        @Override
        public boolean ZF() {
            return lf_resd() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resd() & 0x80000000) != 0;
        }

        @Override
        public boolean OF() {
            return false;
        }
    };
    public static final GetFlags t_TESTb = new GetFlags() {
        @Override
        public boolean CF() {
            return false;
        }

        @Override
        public boolean AF() {
            return false;
        }

        @Override
        public boolean ZF() {
            return lf_resb() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resb() & 0x80) != 0;
        }

        @Override
        public boolean OF() {
            return false;
        }
    };
    public static final GetFlags t_TESTw = new GetFlags() {
        @Override
        public boolean CF() {
            return false;
        }

        @Override
        public boolean AF() {
            return false;
        }

        @Override
        public boolean ZF() {
            return lf_resw() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resw() & 0x8000) != 0;
        }

        @Override
        public boolean OF() {
            return false;
        }
    };
    public static final GetFlags t_TESTd = new GetFlags() {
        @Override
        public boolean CF() {
            return false;
        }

        @Override
        public boolean AF() {
            return false;
        }

        @Override
        public boolean ZF() {
            return lf_resd() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resd() & 0x80000000) != 0;
        }

        @Override
        public boolean OF() {
            return false;
        }
    };
    public static final GetFlags t_XORb = new GetFlags() {
        @Override
        public boolean CF() {
            return false;
        }

        @Override
        public boolean AF() {
            return false;
        }

        @Override
        public boolean ZF() {
            return lf_resb() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resb() & 0x80) != 0;
        }

        @Override
        public boolean OF() {
            return false;
        }
    };
    public static final GetFlags t_XORw = new GetFlags() {
        @Override
        public boolean CF() {
            return false;
        }

        @Override
        public boolean AF() {
            return false;
        }

        @Override
        public boolean ZF() {
            return lf_resw() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resw() & 0x8000) != 0;
        }

        @Override
        public boolean OF() {
            return false;
        }
    };
    public static final GetFlags t_XORd = new GetFlags() {
        @Override
        public boolean CF() {
            return false;
        }

        @Override
        public boolean AF() {
            return false;
        }

        @Override
        public boolean ZF() {
            return lf_resd() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resd() & 0x80000000) != 0;
        }

        @Override
        public boolean OF() {
            return false;
        }
    };
    public static final GetFlags t_SUBb = new GetFlags() {
        @Override
        public boolean CF() {
            return lf_var1b() < lf_var2b();
        }

        @Override
        public boolean AF() {
            return ((lf_var1b() ^ lf_var2b() ^ lf_resb()) & 0x10) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resb() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resb() & 0x80) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_var1b() ^ lf_var2b()) & (lf_var1b() ^ lf_resb()) & 0x80) != 0;
        }
    };
    public static final GetFlags t_SUBw = new GetFlags() {
        @Override
        public boolean CF() {
            return lf_var1w() < lf_var2w();
        }

        @Override
        public boolean AF() {
            return ((lf_var1w() ^ lf_var2w() ^ lf_resw()) & 0x10) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resw() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resw() & 0x8000) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_var1w() ^ lf_var2w()) & (lf_var1w() ^ lf_resw()) & 0x8000) != 0;
        }
    };
    public static final GetFlags t_SUBd = new GetFlags() {
        @Override
        public boolean CF() {
            return (lf_var1d() & 0xFFFFFFFFL) < (lf_var2d() & 0xFFFFFFFFL);
        }

        @Override
        public boolean AF() {
            return ((lf_var1d() ^ lf_var2d() ^ lf_resd()) & 0x10) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resd() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resd() & 0x80000000) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_var1d() ^ lf_var2d()) & (lf_var1d() ^ lf_resd()) & 0x80000000) != 0;
        }
    };
    public static final GetFlags t_CMPb = new GetFlags() {
        @Override
        public boolean CF() {
            return lf_var1b() < lf_var2b();
        }

        @Override
        public boolean AF() {
            return ((lf_var1b() ^ lf_var2b() ^ lf_resb()) & 0x10) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resb() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resb() & 0x80) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_var1b() ^ lf_var2b()) & (lf_var1b() ^ lf_resb()) & 0x80) != 0;
        }
    };
    public static final GetFlags t_CMPw = new GetFlags() {
        @Override
        public boolean CF() {
            return lf_var1w() < lf_var2w();
        }

        @Override
        public boolean AF() {
            return ((lf_var1w() ^ lf_var2w() ^ lf_resw()) & 0x10) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resw() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resw() & 0x8000) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_var1w() ^ lf_var2w()) & (lf_var1w() ^ lf_resw()) & 0x8000) != 0;
        }
    };
    public static final GetFlags t_CMPd = new GetFlags() {
        @Override
        public boolean CF() {
            return (lf_var1d() & 0xFFFFFFFFL) < (lf_var2d() & 0xFFFFFFFFL);
        }

        @Override
        public boolean AF() {
            return ((lf_var1d() ^ lf_var2d() ^ lf_resd()) & 0x10) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resd() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resd() & 0x80000000) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_var1d() ^ lf_var2d()) & (lf_var1d() ^ lf_resd()) & 0x80000000) != 0;
        }
    };
    public static final GetFlags t_SHLb = new GetFlags() {
        @Override
        public boolean CF() {
            return lf_var2b() <= 8 && (lf_var1b() >> 8 - lf_var2b() & 1) != 0;
        }

        @Override
        public boolean AF() {
            return (lf_var2b() & 0x1f) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resb() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resb() & 0x80) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_resb() ^ lf_var1b()) & 0x80) != 0;
        }
    };
    public static final GetFlags t_SHLw = new GetFlags() {
        @Override
        public boolean CF() {
            return lf_var2b() <= 16 && (lf_var1w() >> 16 - lf_var2b() & 1) != 0;
        }

        @Override
        public boolean AF() {
            return (lf_var2w() & 0x1f) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resw() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resw() & 0x8000) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_resw() ^ lf_var1w()) & 0x8000) != 0;
        }
    };
    public static final GetFlags t_SHLd = new GetFlags() {
        @Override
        public boolean CF() {
            return (lf_var1d() >>> 32 - lf_var2b() & 1) != 0;
        }

        @Override
        public boolean AF() {
            return (lf_var2d() & 0x1f) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resd() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resd() & 0x80000000) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_resd() ^ lf_var1d()) & 0x80000000) != 0;
        }
    };
    public static final GetFlags t_SHRb = new GetFlags() {
        @Override
        public boolean CF() {
            return (lf_var1b() >> lf_var2b() - 1 & 1) != 0;
        }

        @Override
        public boolean AF() {
            return (lf_var2b() & 0x1f) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resb() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resb() & 0x80) != 0;
        }

        @Override
        public boolean OF() {
            return (lf_var2b() & 0x1f) == 1 && lf_var1b() > 0x80;
        }
    };
    public static final GetFlags t_SHRw = new GetFlags() {
        @Override
        public boolean CF() {
            return (lf_var1w() >> lf_var2b() - 1 & 1) != 0;
        }

        @Override
        public boolean AF() {
            return (lf_var2w() & 0x1f) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resw() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resw() & 0x8000) != 0;
        }

        @Override
        public boolean OF() {
            return (lf_var2b() & 0x1f) == 1 && lf_var1w() > 0x8000;
        }
    };
    public static final GetFlags t_SHRd = new GetFlags() {
        @Override
        public boolean CF() {
            return (lf_var1d() >>> lf_var2b() - 1 & 1) != 0;
        }

        @Override
        public boolean AF() {
            return (lf_var2d() & 0x1f) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resd() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resd() & 0x80000000) != 0;
        }

        @Override
        public boolean OF() {
            return (lf_var2b() & 0x1f) == 1 && (lf_var1d() & 0xFFFFFFFFL) > 0x80000000L;
        }
    };
    public static final GetFlags t_SARb = new GetFlags() {
        @Override
        public boolean CF() {
            return (lf_var1b() >> lf_var2b() - 1 & 1) != 0;
        }

        @Override
        public boolean AF() {
            return (lf_var2b() & 0x1f) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resb() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resb() & 0x80) != 0;
        }

        @Override
        public boolean OF() {
            return false;
        }
    };
    public static final GetFlags t_SARw = new GetFlags() {
        @Override
        public boolean CF() {
            return (lf_var1w() >> lf_var2b() - 1 & 1) != 0;
        }

        @Override
        public boolean AF() {
            return (lf_var2w() & 0x1f) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resw() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resw() & 0x8000) != 0;
        }

        @Override
        public boolean OF() {
            return false;
        }
    };
    public static final GetFlags t_SARd = new GetFlags() {
        @Override
        public boolean CF() {
            return (lf_var1d() >> lf_var2b() - 1 & 1) != 0;
        }

        @Override
        public boolean AF() {
            return (lf_var2d() & 0x1f) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resd() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resd() & 0x80000000) != 0;
        }

        @Override
        public boolean OF() {
            return false;
        }
    };
    public static final GetFlags t_DSHLw = new GetFlags() {
        @Override
        public boolean CF() {
            return (lf_var1d() >>> 32 - lf_var2b() & 1) != 0;
        } /* Hmm this is not correct for shift higher than 16 */

        @Override
        public boolean AF() {
            return false;
        }

        @Override
        public boolean ZF() {
            return lf_resw() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resw() & 0x8000) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_resw() ^ lf_var1w()) & 0x8000) != 0;
        }
    };
    public static final GetFlags t_DSHLd = new GetFlags() {
        @Override
        public boolean CF() {
            return (lf_var1d() >>> 32 - lf_var2b() & 1) != 0;
        }

        @Override
        public boolean AF() {
            return false;
        }

        @Override
        public boolean ZF() {
            return lf_resd() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resd() & 0x80000000) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_resd() ^ lf_var1d()) & 0x80000000) != 0;
        }
    };
    public static final GetFlags t_DSHRw = new GetFlags() {
        @Override
        public boolean CF() {
            return (lf_var1d() >>> lf_var2b() - 1 & 1) != 0;
        } /* Hmm this is not correct for shift higher than 16 */

        @Override
        public boolean AF() {
            return false;
        }

        @Override
        public boolean ZF() {
            return lf_resw() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resw() & 0x8000) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_resw() ^ lf_var1w()) & 0x8000) != 0;
        }
    };
    public static final GetFlags t_DSHRd = new GetFlags() {
        @Override
        public boolean CF() {
            return (lf_var1d() >>> lf_var2b() - 1 & 1) != 0;
        }

        @Override
        public boolean AF() {
            return false;
        }

        @Override
        public boolean ZF() {
            return lf_resd() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resd() & 0x80000000) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_resd() ^ lf_var1d()) & 0x80000000) != 0;
        }
    };
    public static final GetFlags t_NEGb = new GetFlags() {
        @Override
        public boolean CF() {
            return lf_var1b() != 0;
        }

        @Override
        public boolean AF() {
            return (lf_var1b() & 0x0f) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resb() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resb() & 0x80) != 0;
        }

        @Override
        public boolean OF() {
            return lf_var1b() == 0x80;
        }
    };
    public static final GetFlags t_NEGw = new GetFlags() {
        @Override
        public boolean CF() {
            return lf_var1w() != 0;
        }

        @Override
        public boolean AF() {
            return (lf_var1w() & 0x0f) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resw() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resw() & 0x8000) != 0;
        }

        @Override
        public boolean OF() {
            return lf_var1w() == 0x8000;
        }
    };
    public static final GetFlags t_NEGd = new GetFlags() {
        @Override
        public boolean CF() {
            return lf_var1d() != 0;
        }

        @Override
        public boolean AF() {
            return (lf_var1d() & 0x0f) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resd() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resd() & 0x80000000) != 0;
        }

        @Override
        public boolean OF() {
            return lf_var1d() == 0x80000000;
        }
    };
    public static boolean oldcf;
    public static final GetFlags t_ADCb = new GetFlags() {
        @Override
        public boolean CF() {
            return lf_resb() < lf_var1b() || oldcf && lf_resb() == lf_var1b();
        }

        @Override
        public boolean AF() {
            return ((lf_var1b() ^ lf_var2b() ^ lf_resb()) & 0x10) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resb() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resb() & 0x80) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_var1b() ^ lf_var2b() ^ 0x80) & (lf_resb() ^ lf_var2b()) & 0x80) != 0;
        }
    };
    public static final GetFlags t_ADCw = new GetFlags() {
        @Override
        public boolean CF() {
            return lf_resw() < lf_var1w() || oldcf && lf_resw() == lf_var1w();
        }

        @Override
        public boolean AF() {
            return ((lf_var1w() ^ lf_var2w() ^ lf_resw()) & 0x10) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resw() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resw() & 0x8000) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_var1w() ^ lf_var2w() ^ 0x8000) & (lf_resw() ^ lf_var2w()) & 0x8000) != 0;
        }
    };
    public static final GetFlags t_ADCd = new GetFlags() {
        @Override
        public boolean CF() {
            return (lf_resd() & 0xFFFFFFFFL) < (lf_var1d() & 0xFFFFFFFFL) || oldcf && lf_resd() == lf_var1d();
        }

        @Override
        public boolean AF() {
            return ((lf_var1d() ^ lf_var2d() ^ lf_resd()) & 0x10) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resd() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resd() & 0x80000000) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_var1d() ^ lf_var2d() ^ 0x80000000) & (lf_resd() ^ lf_var2d()) & 0x80000000) != 0;
        }
    };
    public static final GetFlags t_SBBb = new GetFlags() {
        @Override
        public boolean CF() {
            return lf_var1b() < lf_resb() || oldcf && lf_var2b() == 0xff;
        }

        @Override
        public boolean AF() {
            return ((lf_var1b() ^ lf_var2b() ^ lf_resb()) & 0x10) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resb() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resb() & 0x80) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_var1b() ^ lf_var2b()) & (lf_var1b() ^ lf_resb()) & 0x80) != 0;
        }
    };
    public static final GetFlags t_SBBw = new GetFlags() {
        @Override
        public boolean CF() {
            return lf_var1w() < lf_resw() || oldcf && lf_var2w() == 0xffff;
        }

        @Override
        public boolean AF() {
            return ((lf_var1w() ^ lf_var2w() ^ lf_resw()) & 0x10) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resw() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resw() & 0x8000) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_var1w() ^ lf_var2w()) & (lf_var1w() ^ lf_resw()) & 0x8000) != 0;
        }
    };
    public static final GetFlags t_SBBd = new GetFlags() {
        @Override
        public boolean CF() {
            return (lf_var1d() & 0xFFFFFFFFL) < (lf_resd() & 0xFFFFFFFFL) || oldcf && lf_var2d() == 0xffffffff;
        }

        @Override
        public boolean AF() {
            return ((lf_var1d() ^ lf_var2d() ^ lf_resd()) & 0x10) != 0;
        }

        @Override
        public boolean ZF() {
            return lf_resd() == 0;
        }

        @Override
        public boolean SF() {
            return (lf_resd() & 0x80000000) != 0;
        }

        @Override
        public boolean OF() {
            return ((lf_var1d() ^ lf_var2d()) & (lf_var1d() ^ lf_resd()) & 0x80000000) != 0;
        }
    };
    public static Flags.GetFlags type = Flags.t_UNKNOWN;
    public static int[] parity_lookup = { CPU_Regs.PF, 0, 0, CPU_Regs.PF, 0, CPU_Regs.PF, CPU_Regs.PF, 0, 0,
        CPU_Regs.PF, CPU_Regs.PF, 0, CPU_Regs.PF, 0, 0, CPU_Regs.PF, 0, CPU_Regs.PF, CPU_Regs.PF, 0, CPU_Regs.PF, 0, 0,
        CPU_Regs.PF, CPU_Regs.PF, 0, 0, CPU_Regs.PF, 0, CPU_Regs.PF, CPU_Regs.PF, 0, 0, CPU_Regs.PF, CPU_Regs.PF, 0,
        CPU_Regs.PF, 0, 0, CPU_Regs.PF, CPU_Regs.PF, 0, 0, CPU_Regs.PF, 0, CPU_Regs.PF, CPU_Regs.PF, 0, CPU_Regs.PF, 0,
        0, CPU_Regs.PF, 0, CPU_Regs.PF, CPU_Regs.PF, 0, 0, CPU_Regs.PF, CPU_Regs.PF, 0, CPU_Regs.PF, 0, 0, CPU_Regs.PF,
        0, CPU_Regs.PF, CPU_Regs.PF, 0, CPU_Regs.PF, 0, 0, CPU_Regs.PF, CPU_Regs.PF, 0, 0, CPU_Regs.PF, 0, CPU_Regs.PF,
        CPU_Regs.PF, 0, CPU_Regs.PF, 0, 0, CPU_Regs.PF, 0, CPU_Regs.PF, CPU_Regs.PF, 0, 0, CPU_Regs.PF, CPU_Regs.PF, 0,
        CPU_Regs.PF, 0, 0, CPU_Regs.PF, CPU_Regs.PF, 0, 0, CPU_Regs.PF, 0, CPU_Regs.PF, CPU_Regs.PF, 0, 0, CPU_Regs.PF,
        CPU_Regs.PF, 0, CPU_Regs.PF, 0, 0, CPU_Regs.PF, 0, CPU_Regs.PF, CPU_Regs.PF, 0, CPU_Regs.PF, 0, 0, CPU_Regs.PF,
        CPU_Regs.PF, 0, 0, CPU_Regs.PF, 0, CPU_Regs.PF, CPU_Regs.PF, 0, 0, CPU_Regs.PF, CPU_Regs.PF, 0, CPU_Regs.PF, 0,
        0, CPU_Regs.PF, CPU_Regs.PF, 0, 0, CPU_Regs.PF, 0, CPU_Regs.PF, CPU_Regs.PF, 0, CPU_Regs.PF, 0, 0, CPU_Regs.PF,
        0, CPU_Regs.PF, CPU_Regs.PF, 0, 0, CPU_Regs.PF, CPU_Regs.PF, 0, CPU_Regs.PF, 0, 0, CPU_Regs.PF, CPU_Regs.PF, 0,
        0, CPU_Regs.PF, 0, CPU_Regs.PF, CPU_Regs.PF, 0, 0, CPU_Regs.PF, CPU_Regs.PF, 0, CPU_Regs.PF, 0, 0, CPU_Regs.PF,
        0, CPU_Regs.PF, CPU_Regs.PF, 0, CPU_Regs.PF, 0, 0, CPU_Regs.PF, CPU_Regs.PF, 0, 0, CPU_Regs.PF, 0, CPU_Regs.PF,
        CPU_Regs.PF, 0, CPU_Regs.PF, 0, 0, CPU_Regs.PF, 0, CPU_Regs.PF, CPU_Regs.PF, 0, 0, CPU_Regs.PF, CPU_Regs.PF, 0,
        CPU_Regs.PF, 0, 0, CPU_Regs.PF, 0, CPU_Regs.PF, CPU_Regs.PF, 0, CPU_Regs.PF, 0, 0, CPU_Regs.PF, CPU_Regs.PF, 0,
        0, CPU_Regs.PF, 0, CPU_Regs.PF, CPU_Regs.PF, 0, 0, CPU_Regs.PF, CPU_Regs.PF, 0, CPU_Regs.PF, 0, 0, CPU_Regs.PF,
        CPU_Regs.PF, 0, 0, CPU_Regs.PF, 0, CPU_Regs.PF, CPU_Regs.PF, 0, CPU_Regs.PF, 0, 0, CPU_Regs.PF, 0, CPU_Regs.PF,
        CPU_Regs.PF, 0, 0, CPU_Regs.PF, CPU_Regs.PF, 0, CPU_Regs.PF, 0, 0, CPU_Regs.PF };

    public static void copy(LazyFlags in) {
        var1 = in.var1;
        var2 = in.var2;
        res = in.res;
        type = in.type;
        oldcf = in.oldcf;
    }

    public static int lf_var1b() {
        return var1;
    }

    public static void lf_var1b(int b) {
        var1 = b & 0xFF;
    }

    public static int lf_var2b() {
        return var2;
    }

    public static void lf_var2b(int b) {
        var2 = b & 0xFF;
    }

    public static int lf_resb() {
        return res;
    }

    public static void lf_resb(int b) {
        res = b & 0xFF;
    }

    public static int lf_var1w() {
        return var1;
    }

    public static void lf_var1w(int s) {
        var1 = s & 0xFFFF;
    }

    public static int lf_var2w() {
        return var2;
    }

    public static int lf_resw() {
        return res;
    }

    public static void lf_resw(int s) {
        res = s & 0xFFFF;
    }

    public static int lf_var1d() {
        return var1;
    }

    public static void lf_var1d(int v) {
        var1 = v;
    }

    public static int lf_var2d() {
        return var2;
    }

    public static void lf_var2d(int v) {
        var2 = v;
    }

    public static int lf_resd() {
        return res;
    }

    public static void lf_resd(int v) {
        res = v;
    }

    public static void SETFLAGSb(int FLAGB) {
        CPU_Regs.SETFLAGBIT(CPU_Regs.OF, get_OF());
        type = t_UNKNOWN;
        CPU.CPU_SetFlags(FLAGB, CPU_Regs.FMASK_NORMAL & 0xff);
    }

    public static void LoadCF() {
        CPU_Regs.SETFLAGBIT(CPU_Regs.CF, get_CF());
    }

    public static void LoadZF() {
        CPU_Regs.SETFLAGBIT(CPU_Regs.ZF, get_ZF());
    }

    public static void LoadSF() {
        CPU_Regs.SETFLAGBIT(CPU_Regs.SF, get_SF());
    }

    public static void LoadOF() {
        CPU_Regs.SETFLAGBIT(CPU_Regs.OF, get_OF());
    }

    public static void LoadAF() {
        CPU_Regs.SETFLAGBIT(CPU_Regs.AF, get_AF());
    }

    /* CF     Carry Flag -- Set on high-order bit carry or borrow; cleared
          otherwise.
    */
    public static boolean get_CF() {
        return type.CF();
    }

    /* AF     Adjust flag -- Set on carry from or borrow to the low order
            four bits of   AL; cleared otherwise. Used for decimal
            arithmetic.
    */
    public static boolean get_AF() {
        return type.AF();
    }

    // ZF     Zero Flag -- Set if result is zero; cleared otherwise.
    public static boolean get_ZF() {
        return type.ZF();
    }

    /* SF     Sign Flag -- Set equal to high-order bit of result (0 is
            positive, 1 if negative).
    */
    public static boolean get_SF() {
        return type.SF();
    }

    public static boolean get_OF() {
        return type.OF();
    }

    public static boolean get_PF() {
        if (type != t_UNKNOWN) {
            return parity_lookup[lf_resb() & 0xFF] != 0;
        }
        return CPU_Regs.GETFLAG(CPU_Regs.PF) != 0;
    }

    public static boolean TFLG_O() {
        return get_OF();
    }

    public static boolean TFLG_NO() {
        return !get_OF();
    }

    public static boolean TFLG_B() {
        return get_CF();
    }

    public static boolean TFLG_NB() {
        return !get_CF();
    }

    public static boolean TFLG_Z() {
        return get_ZF();
    }

    public static boolean TFLG_NZ() {
        return !get_ZF();
    }

    public static boolean TFLG_BE() {
        return get_CF() || get_ZF();
    }

    public static boolean TFLG_NBE() {
        return !get_CF() && !get_ZF();
    }

    public static boolean TFLG_S() {
        return get_SF();
    }

    public static boolean TFLG_NS() {
        return !get_SF();
    }

    public static boolean TFLG_P() {
        return get_PF();
    }

    public static boolean TFLG_NP() {
        return !get_PF();
    }

    public static boolean TFLG_L() {
        return get_SF() != get_OF();
    }

    public static boolean TFLG_NL() {
        return get_SF() == get_OF();
    }

    public static boolean TFLG_LE() {
        return get_ZF() || get_SF() != get_OF();
    }

    public static boolean TFLG_NLE() {
        return !get_ZF() && get_SF() == get_OF();
    }

    private static void SET_FLAG(int flag, boolean set) {
        CPU_Regs.SETFLAGBIT(flag, set);
    }

    public static /*Bitu*/int FillFlags() {
        if (type != t_UNKNOWN) {
            SET_FLAG(CPU_Regs.CF, type.CF());
            SET_FLAG(CPU_Regs.AF, type.AF());
            SET_FLAG(CPU_Regs.ZF, type.ZF());
            SET_FLAG(CPU_Regs.SF, type.SF());
            SET_FLAG(CPU_Regs.OF, type.OF());
            SET_FLAG(CPU_Regs.PF, parity_lookup[lf_resb() & 0xFF] != 0);
            type = t_UNKNOWN;
        }
        return CPU_Regs.flags;
    }

    public static void FillFlagsNoCFOF() {
        if (type != t_UNKNOWN) {
            SET_FLAG(CPU_Regs.AF, type.AF());
            SET_FLAG(CPU_Regs.ZF, type.ZF());
            SET_FLAG(CPU_Regs.SF, type.SF());
            SET_FLAG(CPU_Regs.PF, parity_lookup[lf_resb() & 0xFF] != 0);
            type = t_UNKNOWN;
        }
    }

    public static void DestroyConditionFlags() {
        type = t_UNKNOWN;
    }

    public interface GetFlags {
        boolean CF();

        boolean AF();

        boolean ZF();

        boolean SF();

        boolean OF();
    }

}
