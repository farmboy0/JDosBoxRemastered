package jdos.win.builtin.directx.dplay;

import jdos.cpu.CPU;
import jdos.cpu.CPU_Regs;
import jdos.cpu.Callback;
import jdos.hardware.Memory;
import jdos.win.builtin.HandlerBase;
import jdos.win.builtin.directx.Guid;
import jdos.win.builtin.directx.ddraw.IUnknown;
import jdos.win.utils.Error;

public class IDirectPlayLobby extends IUnknown {
    static final int VTABLE_SIZE = 10;
    static final int DATA_SIZE = 4;
    private static final Guid v2 = new Guid(0x194c220, 0xa303, 0x11d0, 0x9c, 0x4f, 0x0, 0xa0, 0xc9, 0x5, 0x42, 0x5e);
    private static final Guid v2a = new Guid(0x1bb4af80, 0xa303, 0x11d0, 0x9c, 0x4f, 0x0, 0xa0, 0xc9, 0x5, 0x42, 0x5e);
    private static final Guid v3 = new Guid(0x2db72490, 0x652c, 0x11d1, 0xa7, 0xa8, 0x0, 0x0, 0xf8, 0x3, 0xab, 0xfc);
    private static final Guid v3a = new Guid(0x2db72491, 0x652c, 0x11d1, 0xa7, 0xa8, 0x0, 0x0, 0xf8, 0x3, 0xab, 0xfc);
    static int OFFSET_FLAGS = 0;
    private static final Callback.Handler QueryInterface = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectPlayLobby.QueryInterface";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int riid = CPU.CPU_Pop32();
            int ppvObject = CPU.CPU_Pop32();
            if (v2a.IsEqual(riid)) {
                Memory.mem_writed(ppvObject, This);
                AddRef(This);
                CPU_Regs.reg_eax.dword = Error.S_OK;
            } else {
                CPU_Regs.reg_eax.dword = jdos.win.utils.Error.E_NOINTERFACE;
            }
        }
    };
    // HRESULT Connect(this, DWORD dwFlags, LPDIRECTPLAY2* lplpDP, IUnknown* pUnk)
    private static final Callback.Handler Connect = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectPlayLobby.Connect";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            int lplpDP = CPU.CPU_Pop32();
            int pUnkOuter = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT CreateAddress(this, REFGUID guidSP, REFGUID guidDataType, LPCVOID lpData, DWORD dwDataSize, LPVOID lpAddress, LPDWORD lpdwAddressSize)
    private static final Callback.Handler CreateAddress = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectPlayLobby.CreateAddress";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int guidSP = CPU.CPU_Pop32();
            int guidDataType = CPU.CPU_Pop32();
            int lpData = CPU.CPU_Pop32();
            int dwDataSize = CPU.CPU_Pop32();
            int lpAddress = CPU.CPU_Pop32();
            int lpdwAddressSize = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT EnumAddress(this, LPDPENUMADDRESSCALLBACK lpEnumAddressCallback, LPCVOID lpAddress, DWORD dwAddressSize, LPVOID lpContext)
    private static final Callback.Handler EnumAddress = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectPlayLobby.EnumAddress";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpEnumAddressCallback = CPU.CPU_Pop32();
            int lpAddress = CPU.CPU_Pop32();
            int dwAddressSize = CPU.CPU_Pop32();
            int lpContext = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT EnumAddressTypes(this, LPDPLENUMADDRESSTYPESCALLBACK lpEnumAddressTypeCallback, REFGUID guidSP, LPVOID lpContext, DWORD dwFlags)
    private static final Callback.Handler EnumAddressTypes = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectPlayLobby.EnumAddressTypes";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpEnumAddressTypeCallback = CPU.CPU_Pop32();
            int guidSP = CPU.CPU_Pop32();
            int lpContext = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT EnumLocalApplications(this, LPDPLENUMLOCALAPPLICATIONSCALLBACK lpEnumLocalAppCallback, LPVOID lpContext, DWORD dwFlags)
    private static final Callback.Handler EnumLocalApplications = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectPlayLobby.EnumLocalApplications";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpEnumLocalAppCallback = CPU.CPU_Pop32();
            int lpContext = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT GetConnectionSettings(this, DWORD dwAppID, LPVOID lpData, LPDWORD lpdwDataSize)
    private static final Callback.Handler GetConnectionSettings = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectPlayLobby.GetConnectionSettings";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int dwAppID = CPU.CPU_Pop32();
            int lpData = CPU.CPU_Pop32();
            int lpdwDataSize = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT ReceiveLobbyMessage(this, DWORD dwFlags, DWORD dwAppID, LPDWORD lpdwMessageFlags, LPVOID lpData, LPDWORD lpdwDataSize)
    private static final Callback.Handler ReceiveLobbyMessage = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectPlayLobby.ReceiveLobbyMessage";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            int dwAppID = CPU.CPU_Pop32();
            int lpdwMessageFlags = CPU.CPU_Pop32();
            int lpData = CPU.CPU_Pop32();
            int lpdwDataSize = CPU.CPU_Pop32();
            Memory.mem_writed(lpdwDataSize, 0);
            CPU_Regs.reg_eax.dword = Error.E_OUTOFMEMORY;
        }
    };
    // HRESULT RunApplication(this, DWORD dwFlags, LPDWORD lpdwAppID, LPDPLCONNECTION lpConn, HANDLE hReceiveEvent)
    private static final Callback.Handler RunApplication = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectPlayLobby.RunApplication";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            int lpdwAppID = CPU.CPU_Pop32();
            int lpConn = CPU.CPU_Pop32();
            int hReceiveEvent = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT SendLobbyMessage(this, DWORD dwFlags, DWORD dwAppID, LPVOID lpData, DWORD dwDataSize)
    private static final Callback.Handler SendLobbyMessage = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectPlayLobby.SendLobbyMessage";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            int dwAppID = CPU.CPU_Pop32();
            int lpData = CPU.CPU_Pop32();
            int dwDataSize = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT SetConnectionSettings(this, DWORD dwFlags, DWORD dwAppID, LPDPLCONNECTION lpConn)
    private static final Callback.Handler SetConnectionSettings = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectPlayLobby.SetConnectionSettings";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            int dwAppID = CPU.CPU_Pop32();
            int lpConn = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT SetLobbyMessageEvent(this, DWORD dwFlags, DWORD dwAppID, HANDLE hReceiveEvent)
    private static final Callback.Handler SetLobbyMessageEvent = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectPlayLobby.SetLobbyMessageEvent";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            int dwAppID = CPU.CPU_Pop32();
            int hReceiveEvent = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    /*** IDirectPlayLobby2 methods ***/
    // HRESULT CreateCompoundAddress)(THIS_ LPCDPCOMPOUNDADDRESSELEMENT lpElements, DWORD dwElementCount, LPVOID lpAddress, LPDWORD lpdwAddressSize)
    private static final Callback.Handler CreateCompoundAddress = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectPlayLobby.CreateCompoundAddress";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpElements = CPU.CPU_Pop32();
            int dwElementCount = CPU.CPU_Pop32();
            int lpdwAddressSize = CPU.CPU_Pop32();
            int lpAddress = CPU.CPU_Pop32();
            notImplemented();
        }
    };

    private static int createVTable() {
        int address = allocateVTable("IDirectPlayLobby", VTABLE_SIZE);
        addIDirectPlayLobby(address);
        return address;
    }

    static int addIDirectPlayLobby(int address) {
        address = addIUnknown(address, QueryInterface);
        address = add(address, Connect);
        address = add(address, CreateAddress);
        address = add(address, EnumAddress);
        address = add(address, EnumAddressTypes);
        address = add(address, EnumLocalApplications);
        address = add(address, ReceiveLobbyMessage);
        address = add(address, RunApplication);
        address = add(address, SendLobbyMessage);
        address = add(address, SetConnectionSettings);
        address = add(address, SetLobbyMessageEvent);
        return add(address, CreateCompoundAddress);
    }

    public static int create() {
        return create("IDirectPlayLobby", 0);
    }

    public static int create(String name, int flags) {
        int vtable = getVTable(name);
        if (vtable == 0)
            vtable = createVTable();
        int address = allocate(vtable, DATA_SIZE, 0);
        setData(address, OFFSET_FLAGS, flags);
        return address;
    }
}
