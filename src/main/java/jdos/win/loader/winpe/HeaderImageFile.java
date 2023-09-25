package jdos.win.loader.winpe;

import java.io.IOException;
import java.io.OutputStream;

import jdos.win.system.WinFile;
import jdos.win.utils.LittleEndian;

// From Wine project
public class HeaderImageFile {
    public static final int SIZE = 20;

    public int Machine;
    public int NumberOfSections;
    public long TimeDateStamp;
    public long PointerToSymbolTable;
    public long NumberOfSymbols;
    public int SizeOfOptionalHeader;
    public int Characteristics;

    public void load(OutputStream os, WinFile fis) throws IOException {
        byte[] buffer = new byte[SIZE];
        fis.read(buffer);
        os.write(buffer);
        LittleEndian is = new LittleEndian(buffer);
        Machine = is.readUnsignedShort();
        NumberOfSections = is.readUnsignedShort();
        TimeDateStamp = is.readUnsignedInt();
        PointerToSymbolTable = is.readUnsignedInt();
        NumberOfSymbols = is.readUnsignedInt();
        SizeOfOptionalHeader = is.readUnsignedShort();
        Characteristics = is.readUnsignedShort();
    }
}
