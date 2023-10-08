package jdos.win.builtin.directx;

import jdos.cpu.CPU;
import jdos.cpu.CPU_Regs;
import jdos.cpu.Callback;
import jdos.win.builtin.HandlerBase;
import jdos.win.loader.BuiltinModule;
import jdos.win.loader.Loader;

public class Dplayx extends BuiltinModule {
    // HRESULT WINAPI DirectPlayCreate( LPGUID lpGUID, LPDIRECTPLAY *lplpDP, IUnknown *pUnkOuter )
    private final Callback.Handler DirectPlayCreate = new HandlerBase() {
        @Override
        public String getName() {
            return "Dplayx.DirectPlayCreate";
        }

        @Override
        public void onCall() {
            int lpGUID = CPU.CPU_Pop32();
            int lplpDP = CPU.CPU_Pop32();
            int pUnkOuter = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT WINAPI DirectPlayEnumerateA(LPDPENUMDPCALLBACKA lpEnumCallback, LPVOID lpContext);
    private final Callback.Handler DirectPlayEnumerateA = new HandlerBase() {
        @Override
        public String getName() {
            return "Dplayx.DirectPlayEnumerateA";
        }

        @Override
        public void onCall() {
            int lpEnumCallback = CPU.CPU_Pop32();
            int lpContext = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT WINAPI DirectPlayEnumerateW(LPDPENUMDPCALLBACKW lpEnumCallback, LPVOID lpContext);
    private final Callback.Handler DirectPlayEnumerateW = new HandlerBase() {
        @Override
        public String getName() {
            return "Dplayx.DirectPlayEnumerateW";
        }

        @Override
        public void onCall() {
            int lpEnumCallback = CPU.CPU_Pop32();
            int lpContext = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT WINAPI DirectPlayLobbyCreateA(LPGUID lpGUIDDSP, LPDIRECTPLAYLOBBYA *lplpDPL, IUnknown *lpUnk, LPVOID lpData, DWORD dwDataSize)
    private final Callback.Handler DirectPlayLobbyCreateA = new HandlerBase() {
        @Override
        public String getName() {
            return "Dplayx.DirectPlayLobbyCreateA";
        }

        @Override
        public void onCall() {
            int lpGUIDDSP = CPU.CPU_Pop32();
            int lplpDPL = CPU.CPU_Pop32();
            int lpUnk = CPU.CPU_Pop32();
            int lpData = CPU.CPU_Pop32();
            int dwDataSize = CPU.CPU_Pop32();
            //Memory.mem_writed(lplpDPL, IDirectPlayLobby.create());
            //CPU_Regs.reg_eax.dword = jdos.win.utils.Error.S_OK;
            CPU_Regs.reg_eax.dword = DError.DPERR_UNAVAILABLE;
        }
    };
    // HRESULT WINAPI DirectPlayLobbyCreateW( LPGUID lpGUIDDSP, LPDIRECTPLAYLOBBY *lplpDPL, IUnknown *lpUnk, LPVOID lpData, DWORD dwDataSize)
    private final Callback.Handler DirectPlayLobbyCreateW = new HandlerBase() {
        @Override
        public String getName() {
            return "Dplayx.DirectPlayLobbyCreateW";
        }

        @Override
        public void onCall() {
            int lpGUIDDSP = CPU.CPU_Pop32();
            int lplpDPL = CPU.CPU_Pop32();
            int lpUnk = CPU.CPU_Pop32();
            int lpData = CPU.CPU_Pop32();
            int dwDataSize = CPU.CPU_Pop32();
            notImplemented();
        }
    };

    public Dplayx(Loader loader, int handle) {
        super(loader, "Dplayx.dll", handle);
        add(DirectPlayCreate, 1);
        add(DirectPlayEnumerateA, 2);
        add(DirectPlayEnumerateW, 3);
        add(DirectPlayLobbyCreateA, 4);
        add(DirectPlayLobbyCreateW, 5);
    }
}
