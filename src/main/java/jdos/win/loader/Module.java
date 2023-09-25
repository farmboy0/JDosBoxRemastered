package jdos.win.loader;

import java.util.Vector;

import jdos.util.IntRef;
import jdos.util.LongRef;
import jdos.util.StringRef;
import jdos.win.builtin.WinAPI;
import jdos.win.loader.winpe.HeaderImageImportDescriptor;

public abstract class Module extends WinAPI {
    public static final int DLL_PROCESS_DETACH = 0;
    public static final int DLL_PROCESS_ATTACH = 1;
    public static final int DLL_THREAD_ATTACH = 2;
    public static final int DLL_THREAD_DETACH = 3;
    public String name;
    protected boolean threadLibraryCalls = true;
    private final int handle;

    public Module(int handle) {
        this.handle = handle;
    }

    public int getHandle() {
        return handle;
    }

    public void disableThreadLibraryCalls() {
        threadLibraryCalls = false;
    }

    public abstract boolean RtlImageDirectoryEntryToData(int dir, LongRef address, LongRef size);

    public abstract Vector getImportDescriptors(long address);

    public abstract String getVirtualString(long address);

    public abstract long[] getImportList(HeaderImageImportDescriptor desc);

    public abstract long findNameExport(long exportAddress, long exportsSize, String name, int hint);

    public abstract long findOrdinalExport(long exportAddress, long exportsSize, int ordinal);

    public abstract void getImportFunctionName(long address, StringRef name, IntRef hint);

    public abstract void writeThunk(HeaderImageImportDescriptor desc, int index, long value);

    public abstract void unload();

    public abstract int getProcAddress(String name, boolean loadFake);

    public abstract String getFileName(boolean fullPath);

    public abstract void callDllMain(int dwReason);
}
