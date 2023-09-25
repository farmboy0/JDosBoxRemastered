package jdos.hardware;

import jdos.gui.Render;
import jdos.misc.setup.Module_base;
import jdos.misc.setup.Section;

public class Hardware extends Module_base {
    // OPL_Mode
    public static final int OPL_none = 0;
    public static final int OPL_cms = 1;
    public static final int OPL_opl2 = 2;
    public static final int OPL_dualopl2 = 3;
    public static final int OPL_opl3 = 4;

    public static final int CAPTURE_WAVE = 0x01;
    public static final int CAPTURE_OPL = 0x02;
    public static final int CAPTURE_MIDI = 0x04;
    public static final int CAPTURE_IMAGE = 0x08;
    public static final int CAPTURE_VIDEO = 0x10;

    public static final int CAPTURE_FLAG_DBLW = 0x1;
    public static final int CAPTURE_FLAG_DBLH = 0x2;

    public static int CaptureState = 0;
    public static Section.SectionFunction HARDWARE_Init = section -> System.out.println("HARDWARE_Init not finished yet: NO SCREEN SHOTS");

    public Hardware(Section configuration) {
        super(configuration);
    }

    public static void CAPTURE_AddImage(/*Bitu*/int width, /*Bitu*/int height, /*Bitu*/int bpp, /*Bitu*/int pitch,
        /*Bitu*/int flags, float fps, int[] data, Render.RenderPal_t.RGB[] pal) {
    }

    public static void CAPTURE_AddWave(/*Bit32u*/long freq, /*Bit32u*/long len, short[] data) {

    }
}
