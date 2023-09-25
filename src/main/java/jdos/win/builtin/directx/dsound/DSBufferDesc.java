package jdos.win.builtin.directx.dsound;

import jdos.hardware.Memory;
import jdos.win.builtin.winmm.WAVEFORMATEX;

public class DSBufferDesc {
    public static final int SIZE = 20;

    public static final int DSBCAPS_PRIMARYBUFFER = 0x00000001;
    public static final int DSBCAPS_STATIC = 0x00000002;
    public static final int DSBCAPS_LOCHARDWARE = 0x00000004;
    public static final int DSBCAPS_LOCSOFTWARE = 0x00000008;
    public static final int DSBCAPS_CTRL3D = 0x00000010;
    public static final int DSBCAPS_CTRLFREQUENCY = 0x00000020;
    public static final int DSBCAPS_CTRLPAN = 0x00000040;
    public static final int DSBCAPS_CTRLVOLUME = 0x00000080;
    public static final int DSBCAPS_CTRLDEFAULT = 0x000000E0; /* Pan + volume + frequency. */
    public static final int DSBCAPS_CTRLPOSITIONNOTIFY = 0x00000100;
    public static final int DSBCAPS_CTRLFX = 0x00000200;
    public static final int DSBCAPS_CTRLALL = 0x000001F0; /* All control capabilities */
    public static final int DSBCAPS_STICKYFOCUS = 0x00004000;
    public static final int DSBCAPS_GLOBALFOCUS = 0x00008000;
    public static final int DSBCAPS_GETCURRENTPOSITION2 = 0x00010000; /* More accurate play cursor under emulation*/
    public static final int DSBCAPS_MUTE3DATMAXDISTANCE = 0x00020000;
    public static final int DSBCAPS_LOCDEFER = 0x00040000;
    public int dwSize;
    public int dwFlags;
    public int dwBufferBytes;
    public int dwReserved;
    public WAVEFORMATEX lpwfxFormat = null;

    public DSBufferDesc(int address) {
        dwSize = Memory.mem_readd(address);
        address += 4;
        dwFlags = Memory.mem_readd(address);
        address += 4;
        dwBufferBytes = Memory.mem_readd(address);
        address += 4;
        dwReserved = Memory.mem_readd(address);
        address += 4;
        address = Memory.mem_readd(address);
        if (address != 0)
            lpwfxFormat = new WAVEFORMATEX(address);
    }
}
