package jdos.dos;

public class Dos_system {
    public static final int DOS_NAMELENGTH = 12;
    public static final int DOS_NAMELENGTH_ASCII = DOS_NAMELENGTH + 1;
    public static final int DOS_FCBNAME = 15;
    public static final int DOS_DIRDEPTH = 8;
    public static final int DOS_PATHLENGTH = 80;
    public static final int DOS_TEMPSIZE = 1024;
    public static final int DOS_ATTR_READ_ONLY = 0x01;
    public static final int DOS_ATTR_HIDDEN = 0x02;
    public static final int DOS_ATTR_SYSTEM = 0x04;
    public static final int DOS_ATTR_VOLUME = 0x08;
    public static final int DOS_ATTR_DIRECTORY = 0x10;
    public static final int DOS_ATTR_ARCHIVE = 0x20;
    public static final int DOS_ATTR_DEVICE = 0x40;

    public interface MultiplexHandler {
        boolean call();
    }
}
