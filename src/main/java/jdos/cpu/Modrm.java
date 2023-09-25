package jdos.cpu;

public class Modrm {
    public static final CPU_Regs.Reg[] Getrw = { CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax,
        CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx,
        CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx,
        CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx,
        CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx,
        CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_esp,
        CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp,
        CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi,
        CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_edi, CPU_Regs.reg_edi,
        CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi,

        CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax,
        CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx,
        CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_edx, CPU_Regs.reg_edx,
        CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx,
        CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx,
        CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp,
        CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi,
        CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi,
        CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi,

        CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax,
        CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx,
        CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_edx, CPU_Regs.reg_edx,
        CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx,
        CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx,
        CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp,
        CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi,
        CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi,
        CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi,

        CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax,
        CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx,
        CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_edx, CPU_Regs.reg_edx,
        CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx,
        CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx,
        CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp,
        CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi,
        CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi,
        CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, };
    public static final CPU_Regs.Reg[] Getrd = { CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax,
        CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx,
        CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx,
        CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx,
        CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx,
        CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_esp,
        CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp,
        CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi,
        CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_edi, CPU_Regs.reg_edi,
        CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi,

        CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax,
        CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx,
        CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_edx, CPU_Regs.reg_edx,
        CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx,
        CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx,
        CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp,
        CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi,
        CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi,
        CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi,

        CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax,
        CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx,
        CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_edx, CPU_Regs.reg_edx,
        CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx,
        CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx,
        CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp,
        CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi,
        CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi,
        CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi,

        CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_eax,
        CPU_Regs.reg_eax, CPU_Regs.reg_eax, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx,
        CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_ecx, CPU_Regs.reg_edx, CPU_Regs.reg_edx,
        CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx, CPU_Regs.reg_edx,
        CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_ebx,
        CPU_Regs.reg_ebx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp,
        CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_esp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_esi,
        CPU_Regs.reg_esi, CPU_Regs.reg_esi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi,
        CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi, CPU_Regs.reg_edi };
    public static final CPU_Regs.Reg[] GetEArw = {
        /* 12 lines of 16*0 should give nice errors when used */
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, CPU_Regs.reg_eax, CPU_Regs.reg_ecx,
        CPU_Regs.reg_edx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_ebp, CPU_Regs.reg_esi, CPU_Regs.reg_edi,
        CPU_Regs.reg_eax, CPU_Regs.reg_ecx, CPU_Regs.reg_edx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_esi, CPU_Regs.reg_edi, CPU_Regs.reg_eax, CPU_Regs.reg_ecx, CPU_Regs.reg_edx, CPU_Regs.reg_ebx,
        CPU_Regs.reg_esp, CPU_Regs.reg_ebp, CPU_Regs.reg_esi, CPU_Regs.reg_edi, CPU_Regs.reg_eax, CPU_Regs.reg_ecx,
        CPU_Regs.reg_edx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_ebp, CPU_Regs.reg_esi, CPU_Regs.reg_edi,
        CPU_Regs.reg_eax, CPU_Regs.reg_ecx, CPU_Regs.reg_edx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_esi, CPU_Regs.reg_edi, CPU_Regs.reg_eax, CPU_Regs.reg_ecx, CPU_Regs.reg_edx, CPU_Regs.reg_ebx,
        CPU_Regs.reg_esp, CPU_Regs.reg_ebp, CPU_Regs.reg_esi, CPU_Regs.reg_edi, CPU_Regs.reg_eax, CPU_Regs.reg_ecx,
        CPU_Regs.reg_edx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_ebp, CPU_Regs.reg_esi, CPU_Regs.reg_edi,
        CPU_Regs.reg_eax, CPU_Regs.reg_ecx, CPU_Regs.reg_edx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_esi, CPU_Regs.reg_edi };
    public static final CPU_Regs.Reg[] GetEArd = {
        /* 12 lines of 16*0 should give nice errors when used */
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, CPU_Regs.reg_eax, CPU_Regs.reg_ecx,
        CPU_Regs.reg_edx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_ebp, CPU_Regs.reg_esi, CPU_Regs.reg_edi,
        CPU_Regs.reg_eax, CPU_Regs.reg_ecx, CPU_Regs.reg_edx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_esi, CPU_Regs.reg_edi, CPU_Regs.reg_eax, CPU_Regs.reg_ecx, CPU_Regs.reg_edx, CPU_Regs.reg_ebx,
        CPU_Regs.reg_esp, CPU_Regs.reg_ebp, CPU_Regs.reg_esi, CPU_Regs.reg_edi, CPU_Regs.reg_eax, CPU_Regs.reg_ecx,
        CPU_Regs.reg_edx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_ebp, CPU_Regs.reg_esi, CPU_Regs.reg_edi,
        CPU_Regs.reg_eax, CPU_Regs.reg_ecx, CPU_Regs.reg_edx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_esi, CPU_Regs.reg_edi, CPU_Regs.reg_eax, CPU_Regs.reg_ecx, CPU_Regs.reg_edx, CPU_Regs.reg_ebx,
        CPU_Regs.reg_esp, CPU_Regs.reg_ebp, CPU_Regs.reg_esi, CPU_Regs.reg_edi, CPU_Regs.reg_eax, CPU_Regs.reg_ecx,
        CPU_Regs.reg_edx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_ebp, CPU_Regs.reg_esi, CPU_Regs.reg_edi,
        CPU_Regs.reg_eax, CPU_Regs.reg_ecx, CPU_Regs.reg_edx, CPU_Regs.reg_ebx, CPU_Regs.reg_esp, CPU_Regs.reg_ebp,
        CPU_Regs.reg_esi, CPU_Regs.reg_edi };
    //    public static final Move[] earb_to_rb = new Move[] {
//            null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,
//            null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,
//            null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,
//            null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,
//            null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,
//            null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,
//            null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,
//            null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,
//            null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,
//            null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,
//            null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,
//            null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,
//            new Move() {final public void call() { /*CPU_Regs.reg_eax.low(CPU_Regs.reg_eax.low());*/ }},
//            new Move() {final public void call() { CPU_Regs.reg_eax.low(CPU_Regs.reg_ecx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_eax.low(CPU_Regs.reg_edx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_eax.low(CPU_Regs.reg_ebx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_eax.low(CPU_Regs.reg_eax.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_eax.low(CPU_Regs.reg_ecx.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_eax.low(CPU_Regs.reg_edx.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_eax.low(CPU_Regs.reg_ebx.high()); }},
//
//            new Move() {final public void call() { CPU_Regs.reg_ecx.low(CPU_Regs.reg_eax.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ecx.low(CPU_Regs.reg_ecx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ecx.low(CPU_Regs.reg_edx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ecx.low(CPU_Regs.reg_ebx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ecx.low(CPU_Regs.reg_eax.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ecx.low(CPU_Regs.reg_ecx.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ecx.low(CPU_Regs.reg_edx.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ecx.low(CPU_Regs.reg_ebx.high()); }},
//
//            new Move() {final public void call() { CPU_Regs.reg_edx.low(CPU_Regs.reg_eax.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_edx.low(CPU_Regs.reg_ecx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_edx.low(CPU_Regs.reg_edx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_edx.low(CPU_Regs.reg_ebx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_edx.low(CPU_Regs.reg_eax.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_edx.low(CPU_Regs.reg_ecx.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_edx.low(CPU_Regs.reg_edx.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_edx.low(CPU_Regs.reg_ebx.high()); }},
//
//            new Move() {final public void call() { CPU_Regs.reg_ebx.low(CPU_Regs.reg_eax.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ebx.low(CPU_Regs.reg_ecx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ebx.low(CPU_Regs.reg_edx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ebx.low(CPU_Regs.reg_ebx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ebx.low(CPU_Regs.reg_eax.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ebx.low(CPU_Regs.reg_ecx.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ebx.low(CPU_Regs.reg_edx.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ebx.low(CPU_Regs.reg_ebx.high()); }},
//
//            new Move() {final public void call() { CPU_Regs.reg_eax.high(CPU_Regs.reg_eax.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_eax.high(CPU_Regs.reg_ecx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_eax.high(CPU_Regs.reg_edx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_eax.high(CPU_Regs.reg_ebx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_eax.high(CPU_Regs.reg_eax.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_eax.high(CPU_Regs.reg_ecx.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_eax.high(CPU_Regs.reg_edx.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_eax.high(CPU_Regs.reg_ebx.high()); }},
//
//            new Move() {final public void call() { CPU_Regs.reg_ecx.high(CPU_Regs.reg_eax.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ecx.high(CPU_Regs.reg_ecx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ecx.high(CPU_Regs.reg_edx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ecx.high(CPU_Regs.reg_ebx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ecx.high(CPU_Regs.reg_eax.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ecx.high(CPU_Regs.reg_ecx.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ecx.high(CPU_Regs.reg_edx.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ecx.high(CPU_Regs.reg_ebx.high()); }},
//
//            new Move() {final public void call() { CPU_Regs.reg_edx.high(CPU_Regs.reg_eax.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_edx.high(CPU_Regs.reg_ecx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_edx.high(CPU_Regs.reg_edx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_edx.high(CPU_Regs.reg_ebx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_edx.high(CPU_Regs.reg_eax.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_edx.high(CPU_Regs.reg_ecx.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_edx.high(CPU_Regs.reg_edx.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_edx.high(CPU_Regs.reg_ebx.high()); }},
//
//            new Move() {final public void call() { CPU_Regs.reg_ebx.high(CPU_Regs.reg_eax.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ebx.high(CPU_Regs.reg_ecx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ebx.high(CPU_Regs.reg_edx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ebx.high(CPU_Regs.reg_ebx.low()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ebx.high(CPU_Regs.reg_eax.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ebx.high(CPU_Regs.reg_ecx.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ebx.high(CPU_Regs.reg_edx.high()); }},
//            new Move() {final public void call() { CPU_Regs.reg_ebx.high(CPU_Regs.reg_ebx.high()); }},
//    };
    public static final Move[] eard_to_rd = { null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        () -> {
            /*CPU_Regs.reg_eax.dword = CPU_Regs.reg_eax.dword;*/ }, () -> CPU_Regs.reg_eax.dword = CPU_Regs.reg_ecx.dword, () -> CPU_Regs.reg_eax.dword = CPU_Regs.reg_edx.dword, () -> CPU_Regs.reg_eax.dword = CPU_Regs.reg_ebx.dword, () -> CPU_Regs.reg_eax.dword = CPU_Regs.reg_esp.dword, () -> CPU_Regs.reg_eax.dword = CPU_Regs.reg_ebp.dword, () -> CPU_Regs.reg_eax.dword = CPU_Regs.reg_esi.dword, () -> CPU_Regs.reg_eax.dword = CPU_Regs.reg_edi.dword,

        () -> CPU_Regs.reg_ecx.dword = CPU_Regs.reg_eax.dword, () -> {
            /*CPU_Regs.reg_ecx.dword = CPU_Regs.reg_ecx.dword;*/ }, () -> CPU_Regs.reg_ecx.dword = CPU_Regs.reg_edx.dword, () -> CPU_Regs.reg_ecx.dword = CPU_Regs.reg_ebx.dword, () -> CPU_Regs.reg_ecx.dword = CPU_Regs.reg_esp.dword, () -> CPU_Regs.reg_ecx.dword = CPU_Regs.reg_ebp.dword, () -> CPU_Regs.reg_ecx.dword = CPU_Regs.reg_esi.dword, () -> CPU_Regs.reg_ecx.dword = CPU_Regs.reg_edi.dword,

        () -> CPU_Regs.reg_edx.dword = CPU_Regs.reg_eax.dword, () -> CPU_Regs.reg_edx.dword = CPU_Regs.reg_ecx.dword, () -> {
            /*CPU_Regs.reg_edx.dword = CPU_Regs.reg_edx.dword;*/ }, () -> CPU_Regs.reg_edx.dword = CPU_Regs.reg_ebx.dword, () -> CPU_Regs.reg_edx.dword = CPU_Regs.reg_esp.dword, () -> CPU_Regs.reg_edx.dword = CPU_Regs.reg_ebp.dword, () -> CPU_Regs.reg_edx.dword = CPU_Regs.reg_esi.dword, () -> CPU_Regs.reg_edx.dword = CPU_Regs.reg_edi.dword,

        () -> CPU_Regs.reg_ebx.dword = CPU_Regs.reg_eax.dword, () -> CPU_Regs.reg_ebx.dword = CPU_Regs.reg_ecx.dword, () -> CPU_Regs.reg_ebx.dword = CPU_Regs.reg_edx.dword, () -> {
            /*CPU_Regs.reg_ebx.dword = CPU_Regs.reg_ebx.dword;*/ }, () -> CPU_Regs.reg_ebx.dword = CPU_Regs.reg_esp.dword, () -> CPU_Regs.reg_ebx.dword = CPU_Regs.reg_ebp.dword, () -> CPU_Regs.reg_ebx.dword = CPU_Regs.reg_esi.dword, () -> CPU_Regs.reg_ebx.dword = CPU_Regs.reg_edi.dword,

        () -> CPU_Regs.reg_esp.dword = CPU_Regs.reg_eax.dword, () -> CPU_Regs.reg_esp.dword = CPU_Regs.reg_ecx.dword, () -> CPU_Regs.reg_esp.dword = CPU_Regs.reg_edx.dword, () -> CPU_Regs.reg_esp.dword = CPU_Regs.reg_ebx.dword, () -> {
            /*CPU_Regs.reg_esp.dword = CPU_Regs.reg_esp.dword;*/ }, () -> CPU_Regs.reg_esp.dword = CPU_Regs.reg_ebp.dword, () -> CPU_Regs.reg_esp.dword = CPU_Regs.reg_esi.dword, () -> CPU_Regs.reg_esp.dword = CPU_Regs.reg_edi.dword,

        () -> CPU_Regs.reg_ebp.dword = CPU_Regs.reg_eax.dword, () -> CPU_Regs.reg_ebp.dword = CPU_Regs.reg_ecx.dword, () -> CPU_Regs.reg_ebp.dword = CPU_Regs.reg_edx.dword, () -> CPU_Regs.reg_ebp.dword = CPU_Regs.reg_ebx.dword, () -> CPU_Regs.reg_ebp.dword = CPU_Regs.reg_esp.dword, () -> {
            /*CPU_Regs.reg_ebp.dword = CPU_Regs.reg_ebp.dword;*/ }, () -> CPU_Regs.reg_ebp.dword = CPU_Regs.reg_esi.dword, () -> CPU_Regs.reg_ebp.dword = CPU_Regs.reg_edi.dword,

        () -> CPU_Regs.reg_esi.dword = CPU_Regs.reg_eax.dword, () -> CPU_Regs.reg_esi.dword = CPU_Regs.reg_ecx.dword, () -> CPU_Regs.reg_esi.dword = CPU_Regs.reg_edx.dword, () -> CPU_Regs.reg_esi.dword = CPU_Regs.reg_ebx.dword, () -> CPU_Regs.reg_esi.dword = CPU_Regs.reg_esp.dword, () -> CPU_Regs.reg_esi.dword = CPU_Regs.reg_ebp.dword, () -> {
            /*CPU_Regs.reg_esi.dword = CPU_Regs.reg_esi.dword;*/ }, () -> CPU_Regs.reg_esi.dword = CPU_Regs.reg_edi.dword,

        () -> CPU_Regs.reg_edi.dword = CPU_Regs.reg_eax.dword, () -> CPU_Regs.reg_edi.dword = CPU_Regs.reg_ecx.dword, () -> CPU_Regs.reg_edi.dword = CPU_Regs.reg_edx.dword, () -> CPU_Regs.reg_edi.dword = CPU_Regs.reg_ebx.dword, () -> CPU_Regs.reg_edi.dword = CPU_Regs.reg_esp.dword, () -> CPU_Regs.reg_edi.dword = CPU_Regs.reg_ebp.dword, () -> CPU_Regs.reg_edi.dword = CPU_Regs.reg_esi.dword, () -> {
            /*CPU_Regs.reg_edi.dword = CPU_Regs.reg_edi.dword;*/ }, };
    private static final Getrb_interface al = new Getrb_interface() {
        @Override
        final public void set(int value) {
            CPU_Regs.reg_eax.low(value);
        }

        @Override
        final public int get() {
            return CPU_Regs.reg_eax.low();
        }
    };
    private static final Getrb_interface cl = new Getrb_interface() {
        @Override
        final public void set(int value) {
            CPU_Regs.reg_ecx.low(value);
        }

        @Override
        final public int get() {
            return CPU_Regs.reg_ecx.low();
        }
    };
    private static final Getrb_interface dl = new Getrb_interface() {
        @Override
        final public void set(int value) {
            CPU_Regs.reg_edx.low(value);
        }

        @Override
        final public int get() {
            return CPU_Regs.reg_edx.low();
        }
    };
    private static final Getrb_interface bl = new Getrb_interface() {
        @Override
        final public void set(int value) {
            CPU_Regs.reg_ebx.low(value);
        }

        @Override
        final public int get() {
            return CPU_Regs.reg_ebx.low();
        }
    };
    private static final Getrb_interface ah = new Getrb_interface() {
        @Override
        final public void set(int value) {
            CPU_Regs.reg_eax.high(value);
        }

        @Override
        final public int get() {
            return CPU_Regs.reg_eax.high();
        }
    };
//    public static void Getrb(int rm, short value) {
//        switch ((rm / 8) % 8) {
//            case 0: CPU_Regs.reg_eax.low(value);break;
//            case 1: CPU_Regs.reg_ecx.low(value);break;
//            case 2: CPU_Regs.reg_edx.low(value);break;
//            case 3: CPU_Regs.reg_ebx.low(value);break;
//            case 4: CPU_Regs.reg_eax.high(value);break;
//            case 5: CPU_Regs.reg_ecx.high(value);break;
//            case 6: CPU_Regs.reg_edx.high(value);break;
//            case 7: CPU_Regs.reg_ebx.high(value);break;
//            default:
//                throw new RuntimeException("WTF "+rm);
//        }
//    }

//    public static short Getrb(int rm) {
//        switch ((rm / 8) % 8) {
//            case 0: return CPU_Regs.reg_eax.low();
//            case 1: return CPU_Regs.reg_ecx.low();
//            case 2: return CPU_Regs.reg_edx.low();
//            case 3: return CPU_Regs.reg_ebx.low();
//            case 4: return CPU_Regs.reg_eax.high();
//            case 5: return CPU_Regs.reg_ecx.high();
//            case 6: return CPU_Regs.reg_edx.high();
//            case 7: return CPU_Regs.reg_ebx.high();
//            default:
//                throw new RuntimeException("WTF "+rm);
//        }
//    }
    private static final Getrb_interface ch = new Getrb_interface() {
        @Override
        final public void set(int value) {
            CPU_Regs.reg_ecx.high(value);
        }

        @Override
        final public int get() {
            return CPU_Regs.reg_ecx.high();
        }
    };
//    public static void Getrw(short rm, int value) {
//        switch ((rm / 8) % 8) {
//            case 0: CPU_Regs.reg_eax.word(value);break;
//            case 1: CPU_Regs.reg_ecx.word(value);break;
//            case 2: CPU_Regs.reg_edx.word(value);break;
//            case 3: CPU_Regs.reg_ebx.word(value);break;
//            case 4: CPU_Regs.reg_esp.word(value);break;
//            case 5: CPU_Regs.reg_ebp.word(value);break;
//            case 6: CPU_Regs.reg_esi.word(value);break;
//            case 7: CPU_Regs.reg_edi.word(value);break;
//            default:
//                throw new RuntimeException("WTF "+rm);
//        }
//    }
//    public static int Getrw(short rm) {
//        switch ((rm / 8) % 8) {
//            case 0: return CPU_Regs.reg_eax.word();
//            case 1: return CPU_Regs.reg_ecx.word();
//            case 2: return CPU_Regs.reg_edx.word();
//            case 3: return CPU_Regs.reg_ebx.word();
//            case 4: return CPU_Regs.reg_esp.word();
//            case 5: return CPU_Regs.reg_ebp.word();
//            case 6: return CPU_Regs.reg_esi.word();
//            case 7: return CPU_Regs.reg_edi.word();
//            default:
//                throw new RuntimeException("WTF "+rm);
//        }
//    }
    private static final Getrb_interface dh = new Getrb_interface() {
        @Override
        final public void set(int value) {
            CPU_Regs.reg_edx.high(value);
        }

        @Override
        final public int get() {
            return CPU_Regs.reg_edx.high();
        }
    };
    private static final Getrb_interface bh = new Getrb_interface() {
        @Override
        final public void set(int value) {
            CPU_Regs.reg_ebx.high(value);
        }

        @Override
        final public int get() {
            return CPU_Regs.reg_ebx.high();
        }
    };

//    public static void Getrd(short rm, long value) {
//        switch ((rm / 8) % 8) {
//            case 0: CPU_Regs.reg_eax.dword(value);break;
//            case 1: CPU_Regs.reg_ecx.dword(value);break;
//            case 2: CPU_Regs.reg_edx.dword(value);break;
//            case 3: CPU_Regs.reg_ebx.dword(value);break;
//            case 4: CPU_Regs.reg_esp.dword(value);break;
//            case 5: CPU_Regs.reg_ebp.dword(value);break;
//            case 6: CPU_Regs.reg_esi.dword(value);break;
//            case 7: CPU_Regs.reg_edi.dword(value);break;
//            default:
//                throw new RuntimeException("WTF "+rm);
//        }
//    }
//
//    public static long Getrd(short rm) {
//        switch ((rm / 8) % 8) {
//            case 0: return CPU_Regs.reg_eax.dword();
//            case 1: return CPU_Regs.reg_ecx.dword();
//            case 2: return CPU_Regs.reg_edx.dword();
//            case 3: return CPU_Regs.reg_ebx.dword();
//            case 4: return CPU_Regs.reg_esp.dword();
//            case 5: return CPU_Regs.reg_ebp.dword();
//            case 6: return CPU_Regs.reg_esi.dword();
//            case 7: return CPU_Regs.reg_edi.dword();
//            default:
//                throw new RuntimeException("WTF "+rm);
//        }
//    }
    public static final Getrb_interface[] Getrb = { al, al, al, al, al, al, al, al, cl, cl, cl, cl, cl, cl, cl, cl, dl,
        dl, dl, dl, dl, dl, dl, dl, bl, bl, bl, bl, bl, bl, bl, bl, ah, ah, ah, ah, ah, ah, ah, ah, ch, ch, ch, ch, ch,
        ch, ch, ch, dh, dh, dh, dh, dh, dh, dh, dh, bh, bh, bh, bh, bh, bh, bh, bh,

        al, al, al, al, al, al, al, al, cl, cl, cl, cl, cl, cl, cl, cl, dl, dl, dl, dl, dl, dl, dl, dl, bl, bl, bl, bl,
        bl, bl, bl, bl, ah, ah, ah, ah, ah, ah, ah, ah, ch, ch, ch, ch, ch, ch, ch, ch, dh, dh, dh, dh, dh, dh, dh, dh,
        bh, bh, bh, bh, bh, bh, bh, bh,

        al, al, al, al, al, al, al, al, cl, cl, cl, cl, cl, cl, cl, cl, dl, dl, dl, dl, dl, dl, dl, dl, bl, bl, bl, bl,
        bl, bl, bl, bl, ah, ah, ah, ah, ah, ah, ah, ah, ch, ch, ch, ch, ch, ch, ch, ch, dh, dh, dh, dh, dh, dh, dh, dh,
        bh, bh, bh, bh, bh, bh, bh, bh,

        al, al, al, al, al, al, al, al, cl, cl, cl, cl, cl, cl, cl, cl, dl, dl, dl, dl, dl, dl, dl, dl, bl, bl, bl, bl,
        bl, bl, bl, bl, ah, ah, ah, ah, ah, ah, ah, ah, ch, ch, ch, ch, ch, ch, ch, ch, dh, dh, dh, dh, dh, dh, dh, dh,
        bh, bh, bh, bh, bh, bh, bh, bh };
//    public static void GetEArb(short index, short value) {
//        /* 12 lines of 16*0 should give nice errors when used */
//        if (index<12*16) throw new NullPointerException();
//        switch (index % 8) {
//            case 0: CPU_Regs.reg_eax.low(value);break;
//            case 1: CPU_Regs.reg_ecx.low(value);break;
//            case 2: CPU_Regs.reg_edx.low(value);break;
//            case 3: CPU_Regs.reg_ebx.low(value);break;
//            case 4: CPU_Regs.reg_eax.high(value);break;
//            case 5: CPU_Regs.reg_ecx.high(value);break;
//            case 6: CPU_Regs.reg_edx.high(value);break;
//            case 7: CPU_Regs.reg_ebx.high(value);break;
//            default:
//                throw new RuntimeException("WTF "+index);
//        }
//    }
//
//    public static short GetEArb(short index) {
//        /* 12 lines of 16*0 should give nice errors when used */
//        if (index<12*16) throw new NullPointerException();
//        switch (index % 8) {
//            case 0: return CPU_Regs.reg_eax.low();
//            case 1: return CPU_Regs.reg_ecx.low();
//            case 2: return CPU_Regs.reg_edx.low();
//            case 3: return CPU_Regs.reg_ebx.low();
//            case 4: return CPU_Regs.reg_eax.high();
//            case 5: return CPU_Regs.reg_ecx.high();
//            case 6: return CPU_Regs.reg_edx.high();
//            case 7: return CPU_Regs.reg_ebx.high();
//            default:
//                throw new RuntimeException("WTF "+index);
//        }
//    }
    public static final Getrb_interface[] GetEArb = {
        /* 12 lines of 16*0 should give nice errors when used */
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, al, cl, dl, bl, ah, ch, dh, bh, al, cl,
        dl, bl, ah, ch, dh, bh, al, cl, dl, bl, ah, ch, dh, bh, al, cl, dl, bl, ah, ch, dh, bh, al, cl, dl, bl, ah, ch,
        dh, bh, al, cl, dl, bl, ah, ch, dh, bh, al, cl, dl, bl, ah, ch, dh, bh, al, cl, dl, bl, ah, ch, dh, bh };
//    public static void GetEArw(short index, int value) {
//        /* 12 lines of 16*0 should give nice errors when used */
//        if (index<12*16) throw new NullPointerException();
//        switch (index % 8) {
//            case 0: CPU_Regs.reg_eax.word(value);break;
//            case 1: CPU_Regs.reg_ecx.word(value);break;
//            case 2: CPU_Regs.reg_edx.word(value);break;
//            case 3: CPU_Regs.reg_ebx.word(value);break;
//            case 4: CPU_Regs.reg_esp.word(value);break;
//            case 5: CPU_Regs.reg_ebp.word(value);break;
//            case 6: CPU_Regs.reg_esi.word(value);break;
//            case 7: CPU_Regs.reg_edi.word(value);break;
//            default:
//                throw new RuntimeException("WTF "+index);
//        }
//    }
//
//    public static int GetEArw(short index) {
//        /* 12 lines of 16*0 should give nice errors when used */
//        if (index<12*16) throw new NullPointerException();
//        switch (index % 8) {
//            case 0: return CPU_Regs.reg_eax.word();
//            case 1: return CPU_Regs.reg_ecx.word();
//            case 2: return CPU_Regs.reg_edx.word();
//            case 3: return CPU_Regs.reg_ebx.word();
//            case 4: return CPU_Regs.reg_esp.word();
//            case 5: return CPU_Regs.reg_ebp.word();
//            case 6: return CPU_Regs.reg_esi.word();
//            case 7: return CPU_Regs.reg_edi.word();
//            default:
//                throw new RuntimeException("WTF "+index);
//        }
//    }

    public interface Getrb_interface {
        int get();

        void set(int value);
    }

    public interface Getrd_interface {
        long get();

        void set(long value);
    }

    public interface Move {
        void call();
    }
//    public static void GetEArd(short index, long value) {
//        /* 12 lines of 16*0 should give nice errors when used */
//        if (index<12*16) throw new NullPointerException();
//        switch (index % 8) {
//            case 0: CPU_Regs.reg_eax.dword(value);break;
//            case 1: CPU_Regs.reg_ecx.dword(value);break;
//            case 2: CPU_Regs.reg_edx.dword(value);break;
//            case 3: CPU_Regs.reg_ebx.dword(value);break;
//            case 4: CPU_Regs.reg_esp.dword(value);break;
//            case 5: CPU_Regs.reg_ebp.dword(value);break;
//            case 6: CPU_Regs.reg_esi.dword(value);break;
//            case 7: CPU_Regs.reg_edi.dword(value);break;
//            default:
//                throw new RuntimeException("WTF "+index);
//        }
//    }
//
//    public static long GetEArd(short index) {
//        /* 12 lines of 16*0 should give nice errors when used */
//        if (index<12*16) throw new NullPointerException();
//        switch (index % 8) {
//            case 0: return CPU_Regs.reg_eax.dword();
//            case 1: return CPU_Regs.reg_ecx.dword();
//            case 2: return CPU_Regs.reg_edx.dword();
//            case 3: return CPU_Regs.reg_ebx.dword();
//            case 4: return CPU_Regs.reg_esp.dword();
//            case 5: return CPU_Regs.reg_ebp.dword();
//            case 6: return CPU_Regs.reg_esi.dword();
//            case 7: return CPU_Regs.reg_edi.dword();
//            default:
//                throw new RuntimeException("WTF "+index);
//        }
//    }
}
