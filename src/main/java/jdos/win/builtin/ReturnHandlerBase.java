package jdos.win.builtin;

import jdos.cpu.CPU_Regs;

public abstract class ReturnHandlerBase extends HandlerBase {
    @Override
    public void onCall() {
        CPU_Regs.reg_eax.dword = processReturn();
    }

    public abstract int processReturn();
}
