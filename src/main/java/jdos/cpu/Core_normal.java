package jdos.cpu;

import jdos.cpu.core_normal.Prefix_66_0f;
import jdos.hardware.Memory;
import jdos.misc.Log;
import jdos.misc.setup.Config;
import jdos.types.LogSeverities;
import jdos.types.LogTypes;

public class Core_normal extends Prefix_66_0f {
    public static int start = 0;

    public static final CPU.CPU_Decoder CPU_Core_Normal_Run = Core_normal::cpu_core_normal_run;
    public static final CPU.CPU_Decoder CPU_Core_Normal_Trap_Run = Core_normal::cpu_core_normal_trap_run;

    static int cpu_core_normal_run() {
        while (CPU.CPU_Cycles-- > 0) {
            LOADIP();
            if (CPU.cpu.code.big) {
                opcode_index = 0x200;
                prefixes = 1;
                EA16 = false;
            } else {
                opcode_index = 0;
                prefixes = 0;
                EA16 = true;
            }
            base_ds = CPU_Regs.reg_dsPhys.dword;
            base_ss = CPU_Regs.reg_ssPhys.dword;
            base_val_ds = ds;

            int result;
            do {
                int c = opcode_index + Fetchb();
                if ((prefixes & PREFIX_LOCK) != 0) {
                    if (Core.isInvalidLock(c & ~0x200)) {
                        CPU.CPU_Exception(6, 0);
                    }
                    prefixes &= ~PREFIX_LOCK; // only check the prefix once
                }

                result = ops[c].call();
                switch (result) {
                    case HANDLED:
                        SAVEIP();
                        break;
                    case RETURN:
                        return returnValue;
                    case CBRET_NONE:
                        return Callback.CBRET_NONE;
                    case DECODE_END:
                        SAVEIP();
                        Flags.FillFlags();
                        return Callback.CBRET_NONE;
                    case NOT_HANDLED, ILLEGAL_OPCODE:
                        if (Config.C_DEBUG) {
                            printInstruction();
                        }
                        CPU.CPU_Exception(6, 0);
                }
            } while (result == RESTART);
        }
        Flags.FillFlags();
        return Callback.CBRET_NONE;
    }

    static int cpu_core_normal_trap_run() {
        /*Bits*/
        int oldCycles = CPU.CPU_Cycles;
        CPU.CPU_Cycles = 1;
        CPU.cpu.trap_skip = false;

        /*Bits*/
        int ret = cpu_core_normal_run();
        if (!CPU.cpu.trap_skip)
            CPU.CPU_HW_Interrupt(1);
        CPU.CPU_Cycles = oldCycles - 1;
        CPU.cpudecoder = CPU_Core_Normal_Run;
        return ret;
    }

    private static void printInstruction() {
        if (Log.level > LogSeverities.LOG_NORMAL)
            return;

        /*Bitu*/
        int len = GETIP() - reg_eip;
        LOADIP();
        if (len > 16)
            len = 16;
        StringBuilder tempcode = new StringBuilder();
        for (; len > 0; len--) {
            tempcode.append(String.format("%02X", Memory.mem_readb(cseip++)));
        }
        Log.log(LogTypes.LOG_CPU, LogSeverities.LOG_NORMAL, "Illegal/Unhandled opcode " + tempcode.toString());
    }

    public static void saveState(State state) {
        state.s_opcode_index = opcode_index;
        state.s_cseip = cseip;
        state.s_prefixes = prefixes;
        state.EA16 = EA16;
        state.s_base_ds = base_ds;
        state.s_base_ss = base_ss;
        state.s_base_val_ds = base_val_ds;
        state.rep_zero = rep_zero;
    }

    public static void loadState(State state) {
        opcode_index = state.s_opcode_index;
        cseip = state.s_cseip;
        prefixes = state.s_prefixes;
        EA16 = state.EA16;
        base_ds = state.s_base_ds;
        base_ss = state.s_base_ss;
        base_val_ds = state.s_base_val_ds;
        rep_zero = state.rep_zero;
    }

    public static void CPU_Core_Normal_Init() {
    }

    public static class State {
        public int s_opcode_index;
        public int s_cseip;
        public int s_prefixes;
        public boolean EA16;
        public int s_base_ds;
        public int s_base_ss;
        public int s_base_val_ds;
        public boolean rep_zero;
    }
}
