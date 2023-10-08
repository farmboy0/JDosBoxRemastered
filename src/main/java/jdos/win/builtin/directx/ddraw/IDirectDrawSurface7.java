package jdos.win.builtin.directx.ddraw;

import jdos.cpu.CPU;
import jdos.cpu.Callback;
import jdos.win.builtin.HandlerBase;

public class IDirectDrawSurface7 extends IUnknown {
    /* added in v2 */
    // HRESULT GetDDInterface(this, LPVOID *lplpDD)
    private static final Callback.Handler GetDDInterface = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface7.GetDDInterface";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lplpDD = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT PageLock(this, DWORD dwFlags)
    private static final Callback.Handler PageLock = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface7.PageLock";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT PageUnlock(this, DWORD dwFlags)
    private static final Callback.Handler PageUnlock = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface7.PageUnlock";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    /* added in v3 */
    // HRESULT SetSurfaceDesc(this, LPDDSURFACEDESC2 lpDDSD, DWORD dwFlags)
    private static final Callback.Handler SetSurfaceDesc = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface7.SetSurfaceDesc";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpDDSD = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    /* added in v4 */
    // HRESULT SetPrivateData(this, REFGUID tag, LPVOID pData, DWORD cbSize, DWORD dwFlags)
    private static final Callback.Handler SetPrivateData = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface7.SetPrivateData";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int tag = CPU.CPU_Pop32();
            int pData = CPU.CPU_Pop32();
            int cbSize = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT GetPrivateData(this, REFGUID tag, LPVOID pBuffer, LPDWORD pcbBufferSize)
    private static final Callback.Handler GetPrivateData = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface7.GetPrivateData";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int tag = CPU.CPU_Pop32();
            int pBuffer = CPU.CPU_Pop32();
            int pcbBufferSize = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT FreePrivateData(this, REFGUID tag)
    private static final Callback.Handler FreePrivateData = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface7.FreePrivateData";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int tag = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT GetUniquenessValue(this, LPDWORD pValue)
    private static final Callback.Handler GetUniquenessValue = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface7.GetUniquenessValue";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int pValue = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT ChangeUniquenessValue(this)
    private static final Callback.Handler ChangeUniquenessValue = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface7.ChangeUniquenessValue";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    /* added in v7 */
    // HRESULT SetPriority(this, DWORD prio)
    private static final Callback.Handler SetPriority = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface7.SetPriority";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int prio = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT GetPriority(this, LPDWORD prio)
    private static final Callback.Handler GetPriority = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface7.GetPriority";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int prio = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT SetLOD(this, DWORD lod)
    private static final Callback.Handler SetLOD = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface7.SetLOD";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lod = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT GetLOD(this, LPDWORD lod)
    private static final Callback.Handler GetLOD = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface7.GetLOD";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lod = CPU.CPU_Pop32();
            notImplemented();
        }
    };

    public static int create(int pDirectDraw, int pDesc) {
        int vtable = getVTable("IDirectDrawSurface7");
        if (vtable == 0)
            createVTable();
        return IDirectDrawSurface.create("IDirectDrawSurface7", pDirectDraw, pDesc,
            IDirectDrawSurface.FLAGS_CAPS2 | IDirectDrawSurface.FLAGS_DESC2);
    }

    private static int createVTable() {
        int address = allocateVTable("IDirectDrawSurface7", IDirectDrawSurface.VTABLE_COUNT + 13);
        int result = address;
        address = IDirectDrawSurface.addIDirectDrawSurface(address);

        /* added in v2 */
        address = add(address, GetDDInterface);
        address = add(address, PageLock);
        address = add(address, PageUnlock);
        /* added in v3 */
        address = add(address, SetSurfaceDesc);
        /* added in v4 */
        address = add(address, SetPrivateData);
        address = add(address, GetPrivateData);
        address = add(address, SetSurfaceDesc);
        address = add(address, GetUniquenessValue);
        address = add(address, ChangeUniquenessValue);
        /* added in v7 */
        address = add(address, SetPriority);
        address = add(address, GetPriority);
        address = add(address, SetLOD);
        address = add(address, GetLOD);
        return result;
    }
}
