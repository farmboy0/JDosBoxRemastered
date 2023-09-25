package jdos.win.builtin.directx.ddraw;

import java.lang.reflect.Method;
import java.util.Hashtable;

import jdos.cpu.CPU;
import jdos.cpu.CPU_Regs;
import jdos.cpu.Callback;
import jdos.hardware.Memory;
import jdos.win.Win;
import jdos.win.builtin.HandlerBase;
import jdos.win.builtin.WinAPI;
import jdos.win.kernel.WinCallback;
import jdos.win.loader.BuiltinModule;
import jdos.win.system.WinSystem;
import jdos.win.utils.Error;

public class IUnknown extends WinAPI {
    public static final int OFFSET_DATA_START = 12;
    // static private final int OFFSET_VTABLE = 0;
    private static final int OFFSET_REF = 4;
    private static final int OFFSET_CLEANUP = 8;
    private static final Hashtable vtables = new Hashtable();
    private static final Hashtable names = new Hashtable();
    // HRESULT QueryInterface(this, REFIID riid, void** ppvObject)
    private static final Callback.Handler QueryInterface = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IUnknown.QueryInterface";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int riid = CPU.CPU_Pop32();
            int ppvObject = CPU.CPU_Pop32();
            if (ppvObject == 0)
                CPU_Regs.reg_eax.dword = Error.E_POINTER;
            else
                CPU_Regs.reg_eax.dword = Error.E_NOINTERFACE;
        }
    };
    // ULONG AddRef(this)
    private static final Callback.Handler AddRef = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IUnknown.AddRef";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            CPU_Regs.reg_eax.dword = AddRef(This);
        }
    };
    // ULONG Release(this)
    private static final Callback.Handler Release = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IUnknown.Release";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            CPU_Regs.reg_eax.dword = Release(This);
        }
    };

    protected static int getVTable(String name) {
        Integer result = (Integer) vtables.get(name);
        if (result == null)
            return 0;
        return result.intValue();
    }

    protected static int getData(int This, int offset) {
        return Memory.mem_readd(This + OFFSET_DATA_START + offset);
    }

    protected static void setData(int This, int offset, int data) {
        Memory.mem_writed(This + OFFSET_DATA_START + offset, data);
    }

    private static void setRefCount(int address, int i) {
        Memory.mem_writed(address + OFFSET_REF, i);
    }

    public static int getRefCount(int address) {
        return Memory.mem_readd(address + OFFSET_REF);
    }

    public static int getVTable(int address) {
        return Memory.mem_readd(address);
    }

    protected static int add(int address, Callback.Handler handler) {
        int cb = WinCallback.addCallback(handler);
        Memory.mem_writed(address, WinSystem.getCurrentProcess().loader.registerFunction(cb));
        return address + 4;
    }

    protected static int add(int address, Class c, String methodName, String[] params) {
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                if (method.getReturnType() == Integer.TYPE) {
                    return add(address, new BuiltinModule.ReturnHandler(methodName, method, true, params));
                } else {
                    return add(address, new BuiltinModule.NoReturnHandler(methodName, method, true, params));
                }
            }
        }
        Win.panic("Failed to find " + methodName);
        return 0;
    }

    protected static int allocateVTable(String name, int functions) {
        int result = WinSystem.getCurrentProcess().heap.alloc((functions + 3) * 4, false);
        vtables.put(name, Integer.valueOf(result));
        names.put(Integer.valueOf(result), name);
        return result;
    }

    protected static int allocate(int vtable, int extra, int cleanup) {
        int result = WinSystem.getCurrentProcess().heap.alloc(OFFSET_DATA_START + extra, false);
        Memory.mem_zero(result, OFFSET_DATA_START + extra);
        Memory.mem_writed(result, vtable);
        Memory.mem_writed(result + OFFSET_CLEANUP, cleanup);
        setRefCount(result, 1);
        return result;
    }

    protected static int addIUnknown(int address) {
        return addIUnknown(address, null);
    }

    protected static int addIUnknown(int address, Callback.Handler query) {
        address = add(address, query == null ? QueryInterface : query);
        address = add(address, AddRef);
        return add(address, Release);
    }

    public static int AddRef(int This) {
        int refCount = getRefCount(This);
        refCount++;
        setRefCount(This, refCount);
        return refCount;
    }

    public static int Release(int This) {
        if (WinAPI.LOG)
            System.out.println(names.get(Integer.valueOf(getVTable(This))) + ".Release");
        int refCount = getRefCount(This);
        refCount--;
        setRefCount(This, refCount);
        if (refCount == 0) {
            if (WinAPI.LOG)
                System.out.println("    Freed");
            int cb = Memory.mem_readd(This + OFFSET_CLEANUP);
            if (cb != 0) {
                CPU.CPU_Push32(This);
                Callback.CallBack_Handlers[cb].call();
            }
            WinSystem.getCurrentProcess().heap.free(This);
        }
        return refCount;
    }
}
