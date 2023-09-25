package jdos.win.builtin.directx.ddraw;

import jdos.cpu.CPU;
import jdos.cpu.Callback;
import jdos.win.builtin.HandlerBase;

public class IDirectDraw7 extends IUnknown {
    /* added in v2 */
    // HRESULT GetAvailableVidMem(this, LPDDSCAPS2 lpDDCaps, LPDWORD lpdwTotal, LPDWORD lpdwFree)
    private static final Callback.Handler GetAvailableVidMem = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDraw7.GetAvailableVidMem";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpDDCaps = CPU.CPU_Pop32();
            int lpdwTotal = CPU.CPU_Pop32();
            int lpdwFree = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    /* added in v4 */
    // HRESULT GetSurfaceFromDC(this HDC hdc, LPDIRECTDRAWSURFACE7 *pSurf)
    private static final Callback.Handler GetSurfaceFromDC = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDraw7.GetSurfaceFromDC";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int hdc = CPU.CPU_Pop32();
            int pSurf = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT RestoreAllSurfaces(this)
    private static final Callback.Handler RestoreAllSurfaces = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDraw7.RestoreAllSurfaces";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT TestCooperativeLevel(this)
    private static final Callback.Handler TestCooperativeLevel = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDraw7.TestCooperativeLevel";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT GetDeviceIdentifier(this, LPDDDEVICEIDENTIFIER2 pDDDI, DWORD dwFlags)
    private static final Callback.Handler GetDeviceIdentifier = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDraw7.GetDeviceIdentifier";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int pDDDI = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    /* added in v7 */
    // HRESULT StartModeTest(this, LPSIZE pModes, DWORD dwNumModes, DWORD dwFlags)
    private static final Callback.Handler StartModeTest = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDraw7.StartModeTest";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int pModes = CPU.CPU_Pop32();
            int dwNumModes = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT EvaluateMode(this, DWORD dwFlags, DWORD  *pTimeout)
    private static final Callback.Handler EvaluateMode = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDraw7.EvaluateMode";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            int pTimeout = CPU.CPU_Pop32();
            notImplemented();
        }
    };

    private static int createVTable() {
        int address = allocateVTable("IDirectDraw7", IDirectDraw.VTABLE_SIZE + 7);
        int result = address;
        address = IDirectDraw.addIDirectDraw(address, true);

        /* added in v2 */
        address = add(address, GetAvailableVidMem);
        /* added in v4 */
        address = add(address, GetSurfaceFromDC);
        address = add(address, RestoreAllSurfaces);
        address = add(address, TestCooperativeLevel);
        address = add(address, GetDeviceIdentifier);
        /* added in v7 */
        address = add(address, StartModeTest);
        address = add(address, EvaluateMode);
        return result;
    }

    public static int create() {
        int vtable = getVTable("IDirectDraw7");
        if (vtable == 0)
            createVTable();
        return IDirectDraw.create("IDirectDraw7",
            IDirectDraw.FLAGS_CALLBACK2 | IDirectDraw.FLAGS_DESC2 | IDirectDraw.FLAGS_V7);
    }
}
