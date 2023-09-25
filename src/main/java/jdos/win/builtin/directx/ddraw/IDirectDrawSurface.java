package jdos.win.builtin.directx.ddraw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import jdos.cpu.CPU;
import jdos.cpu.CPU_Regs;
import jdos.cpu.Callback;
import jdos.gui.Main;
import jdos.hardware.Memory;
import jdos.win.Console;
import jdos.win.Win;
import jdos.win.builtin.HandlerBase;
import jdos.win.builtin.directx.DError;
import jdos.win.builtin.directx.DirectCallback;
import jdos.win.builtin.gdi32.WinDC;
import jdos.win.kernel.WinCallback;
import jdos.win.system.JavaBitmap;
import jdos.win.system.Scheduler;
import jdos.win.system.StaticData;
import jdos.win.system.WinObject;
import jdos.win.system.WinSystem;
import jdos.win.utils.Error;
import jdos.win.utils.Pixel;
import jdos.win.utils.Ptr;

public class IDirectDrawSurface extends IUnknown {
    static final int DDSCAPS_ALPHA = 0x00000002; /* surface contains alpha information */
    static final int DDSCAPS_BACKBUFFER = 0x00000004; /* this surface is a backbuffer */
    static final int DDSCAPS_COMPLEX = 0x00000008; /* complex surface structure */
    static final int DDSCAPS_FLIP = 0x00000010; /* part of surface flipping structure */
    static final int DDSCAPS_FRONTBUFFER = 0x00000020; /* this surface is the frontbuffer surface */
    static final int DDSCAPS_OFFSCREENPLAIN = 0x00000040; /* this is a plain offscreen surface */
    static final int DDSCAPS_OVERLAY = 0x00000080; /* overlay */
    static final int DDSCAPS_PALETTE = 0x00000100; /* palette objects can be created and attached to us */
    static final int DDSCAPS_PRIMARYSURFACE = 0x00000200; /* primary surface (the one the user looks at currently)(right eye)*/
    static final int DDSCAPS_PRIMARYSURFACELEFT = 0x00000400; /* primary surface for left eye */
    static final int DDSCAPS_SYSTEMMEMORY = 0x00000800; /* surface exists in systemmemory */
    static final int DDSCAPS_TEXTURE = 0x00001000; /* surface can be used as a texture */
    static final int DDSCAPS_3DDEVICE = 0x00002000; /* surface may be destination for 3d rendering */
    static final int DDSCAPS_VIDEOMEMORY = 0x00004000; /* surface exists in videomemory */
    static final int DDSCAPS_VISIBLE = 0x00008000; /* surface changes immediately visible */
    static final int DDSCAPS_WRITEONLY = 0x00010000; /* write only surface */
    static final int DDSCAPS_ZBUFFER = 0x00020000; /* zbuffer surface */
    static final int DDSCAPS_OWNDC = 0x00040000; /* has its own DC */
    static final int DDSCAPS_LIVEVIDEO = 0x00080000; /* surface should be able to receive live video */
    static final int DDSCAPS_HWCODEC = 0x00100000; /* should be able to have a hw codec decompress stuff into it */
    static final int DDSCAPS_MODEX = 0x00200000; /* mode X (320x200 or 320x240) surface */
    static final int DDSCAPS_MIPMAP = 0x00400000; /* one mipmap surface (1 level) */
    static final int DDSCAPS_RESERVED2 = 0x00800000;
    static final int DDSCAPS_ALLOCONLOAD = 0x04000000; /* memory allocation delayed until Load() */
    static final int DDSCAPS_VIDEOPORT = 0x08000000; /* Indicates that the surface will receive data from a video port */
    static final int DDSCAPS_LOCALVIDMEM = 0x10000000; /* surface is in local videomemory */
    static final int DDSCAPS_NONLOCALVIDMEM = 0x20000000; /* surface is in nonlocal videomemory */
    static final int DDSCAPS_STANDARDVGAMODE = 0x40000000; /* surface is a standard VGA mode surface (NOT ModeX) */
    static final int DDSCAPS_OPTIMIZED = 0x80000000;

    static final int DDLOCK_SURFACEMEMORYPTR = 0x00000000;
    static final int DDLOCK_WAIT = 0x00000001;
    static final int DDLOCK_EVENT = 0x00000002;
    static final int DDLOCK_READONLY = 0x00000010;
    static final int DDLOCK_WRITEONLY = 0x00000020;
    static final int DDLOCK_NOSYSLOCK = 0x00000800;
    static final int DDLOCK_NOOVERWRITE = 0x00001000;
    static final int DDLOCK_DISCARDCONTENTS = 0x00002000;

    static final int DDCKEY_COLORSPACE = 0x00000001; /* Struct is single colour space */
    static final int DDCKEY_DESTBLT = 0x00000002; /* To be used as dest for blt */
    static final int DDCKEY_DESTOVERLAY = 0x00000004; /* To be used as dest for CK overlays */
    static final int DDCKEY_SRCBLT = 0x00000008; /* To be used as src for blt */
    static final int DDCKEY_SRCOVERLAY = 0x00000010; /* To be used as src for CK overlays */
    public static int lastPaletteChange = 0;
    public static int cleanupCallback;
    static int FLAGS_CAPS2 = 0x00000001;
    static int FLAGS_DESC2 = 0x00000002;
    static int FLAGS_LOCKED = 0x00000004;
    static int OFFSET_FLAGS = 0;
    static int OFFSET_PALETTE = 4;
    static int OFFSET_BACK_BUFFER = 8;
    static int OFFSET_DC = 12;
    static int OFFSET_IMAGE_CACHE = 16;
    static int OFFSET_IMAGE_CACHE_TIME = 20;
    static int OFFSET_DIRECT_DRAW = 24;
    static int OFFSET_CLIPPER = 28;
    // doesn't include description since that gets computed on the fly
    static int DATA_SIZE = 32;
    static int OFFSET_DESC = DATA_SIZE;
    static int VTABLE_COUNT = 33;
    private static final Callback.Handler CleanUp = new DirectCallback() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.CleanUp";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int palette = getData(This, OFFSET_PALETTE);
            if (palette != 0) {
                Release(palette);
            }
            int backBuffer = getData(This, OFFSET_BACK_BUFFER);
            if (backBuffer != 0) {
                Release(backBuffer);
            }
            int hdc = getData(This, OFFSET_DC);
            if (hdc != 0) {
                WinDC dc = WinDC.get(hdc);
                dc.close();
            }
            int clipper = getData(This, OFFSET_CLIPPER);
            if (clipper != 0) {
                Release(clipper);
            }
            int index = getData(This, OFFSET_IMAGE_CACHE);
            if (index > 0) {
                WinObject object = WinObject.getObject(index);
                JavaBitmap image = (JavaBitmap) object.data;
                if (image != StaticData.screen)
                    image.close();
                setData(This, OFFSET_IMAGE_CACHE, 0);
                object.data = null;
                object.close();
            }
            if (StaticData.currentPrimarySurface == This) {
                StaticData.currentPrimarySurface = 0;
            }
        }
    };
    // HRESULT AddAttachedSurface(this, LPDIRECTDRAWSURFACE lpDDSAttachedSurface)
    private static final Callback.Handler AddAttachedSurface = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.AddAttachedSurface";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpDDSAttachedSurface = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT AddOverlayDirtyRect(this, LPRECT lpRect)
    private static final Callback.Handler AddOverlayDirtyRect = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.AddOverlayDirtyRect";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpRect = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT Blt(this, LPRECT lpDestRect, LPDIRECTDRAWSURFACE lpDDSrcSurface, LPRECT lpSrcRect, DWORD dwFlags, LPDDBLTFX lpDDBltFx)
    private static final Callback.Handler Blt = new HandlerBase() {
        public static final int DDBLT_ALPHADEST = 0x00000001;
        public static final int DDBLT_ALPHADESTCONSTOVERRIDE = 0x00000002;
        public static final int DDBLT_ALPHADESTNEG = 0x00000004;
        public static final int DDBLT_ALPHADESTSURFACEOVERRIDE = 0x00000008;
        public static final int DDBLT_ALPHAEDGEBLEND = 0x00000010;
        public static final int DDBLT_ALPHASRC = 0x00000020;
        public static final int DDBLT_ALPHASRCCONSTOVERRIDE = 0x00000040;
        public static final int DDBLT_ALPHASRCNEG = 0x00000080;
        public static final int DDBLT_ALPHASRCSURFACEOVERRIDE = 0x00000100;
        public static final int DDBLT_ASYNC = 0x00000200;
        public static final int DDBLT_COLORFILL = 0x00000400;
        public static final int DDBLT_DDFX = 0x00000800;
        public static final int DDBLT_DDROPS = 0x00001000;
        public static final int DDBLT_KEYDEST = 0x00002000;
        public static final int DDBLT_KEYDESTOVERRIDE = 0x00004000;
        public static final int DDBLT_KEYSRC = 0x00008000;
        public static final int DDBLT_KEYSRCOVERRIDE = 0x00010000;
        public static final int DDBLT_ROP = 0x00020000;
        public static final int DDBLT_ROTATIONANGLE = 0x00040000;
        public static final int DDBLT_ZBUFFER = 0x00080000;
        public static final int DDBLT_ZBUFFERDESTCONSTOVERRIDE = 0x00100000;
        public static final int DDBLT_ZBUFFERDESTOVERRIDE = 0x00200000;
        public static final int DDBLT_ZBUFFERSRCCONSTOVERRIDE = 0x00400000;
        public static final int DDBLT_ZBUFFERSRCOVERRIDE = 0x00800000;
        public static final int DDBLT_WAIT = 0x01000000;
        public static final int DDBLT_DEPTHFILL = 0x02000000;
        public static final int DDBLT_DONOTWAIT = 0x08000000;

        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.Blt";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpDestRect = CPU.CPU_Pop32();
            int lpDDSrcSurface = CPU.CPU_Pop32();
            int lpSrcRect = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            int lpDDBltFx = CPU.CPU_Pop32();
            DDBltFx fx = null;

            if (lpDDBltFx != 0) {
                fx = new DDBltFx(lpDDBltFx);
            }
            int destX1 = 0;
            int destY1 = 0;
            int destX2;
            int destY2;

            if (lpDestRect == 0) {
                destX2 = getWidth(This);
                destY2 = getHeight(This);
            } else {
                destX1 = Memory.mem_readd(lpDestRect);
                destY1 = Memory.mem_readd(lpDestRect + 4);
                destX2 = Memory.mem_readd(lpDestRect + 8);
                destY2 = Memory.mem_readd(lpDestRect + 12);
            }

            BufferedImage dest = getImage(This, true).getImage();
            Graphics g = dest.getGraphics();
            dwFlags = dwFlags & ~DDBLT_WAIT;
            if ((dwFlags & DDBLT_COLORFILL) != 0) {
                int color = fx.dwFillColor;
                if (getBpp(This) <= 8)
                    color = getPaletteEntry(This, color);
                g.setColor(new Color(color));
                g.fillRect(destX1, destY1, destX2 - destX1, destY2 - destY1);
            } else if (dwFlags == 0) {
                BufferedImage src = getImage(lpDDSrcSurface, true).getImage();
                int srcX1 = Memory.mem_readd(lpSrcRect);
                int srcY1 = Memory.mem_readd(lpSrcRect + 4);
                int srcX2 = Memory.mem_readd(lpSrcRect + 8);
                int srcY2 = Memory.mem_readd(lpSrcRect + 12);
                g.drawImage(src, destX1, destY1, destX2, destY2, srcX1, srcY1, srcX2, srcY2, null);
            } else if ((dwFlags & DDBLT_KEYSRC) != 0) {
                BufferedImage src = getImage(lpDDSrcSurface, true)
                    .getImageColorKey(getData(lpDDSrcSurface, OFFSET_DESC + 0x40));
                int srcX1 = Memory.mem_readd(lpSrcRect);
                int srcY1 = Memory.mem_readd(lpSrcRect + 4);
                int srcX2 = Memory.mem_readd(lpSrcRect + 8);
                int srcY2 = Memory.mem_readd(lpSrcRect + 12);
                g.drawImage(src, destX1, destY1, destX2, destY2, srcX1, srcY1, srcX2, srcY2, null);
            } else {
                notImplemented();
            }
            if (StaticData.currentPrimarySurface == This)
                Main.drawImage(getImage(This, true).getImage());
            g.dispose();
            CPU_Regs.reg_eax.dword = Error.S_OK;
        }
    };
    // HRESULT BltBatch(this, LPDDBLTBATCH lpDDBltBatch, DWORD dwCount, DWORD dwFlags)
    private static final Callback.Handler BltBatch = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.BltBatch";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpDDBltBatch = CPU.CPU_Pop32();
            int dwCount = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT BltFast(this, DWORD dwX, DWORD dwY, LPDIRECTDRAWSURFACE lpDDSrcSurface, LPRECT lpSrcRect, DWORD dwTrans)
    private static final Callback.Handler BltFast = new HandlerBase() {
        private static final int DDBLTFAST_NOCOLORKEY = 0x00000000;
        private static final int DDBLTFAST_SRCCOLORKEY = 0x00000001;
        private static final int DDBLTFAST_DESTCOLORKEY = 0x00000002;
        private static final int DDBLTFAST_WAIT = 0x00000010;
        private static final int DDBLTFAST_DONOTWAIT = 0x00000020;

        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.BltFast";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int dwX = CPU.CPU_Pop32();
            int dwY = CPU.CPU_Pop32();
            int lpDDSrcSurface = CPU.CPU_Pop32();
            int lpSrcRect = CPU.CPU_Pop32();
            int dwTrans = CPU.CPU_Pop32();

            int srcX1 = Memory.mem_readd(lpSrcRect);
            int srcY1 = Memory.mem_readd(lpSrcRect + 4);
            int srcX2 = Memory.mem_readd(lpSrcRect + 8);
            int srcY2 = Memory.mem_readd(lpSrcRect + 12);
            int width = srcX2 - srcX1;
            int height = srcY2 - srcY1;

            BufferedImage dest = getImage(This, true).getImage();
            Graphics g = dest.getGraphics();

            if ((dwTrans & 0xF) == DDBLTFAST_NOCOLORKEY) {
                BufferedImage src = getImage(lpDDSrcSurface, true).getImage();
                g.drawImage(src, dwX, dwY, dwX + width, dwY + height, srcX1, srcY1, srcX2, srcY1 + height, null);
            } else if ((dwTrans & 0xF) == DDBLTFAST_SRCCOLORKEY) {
                BufferedImage src = getImage(lpDDSrcSurface, true).getImageColorKey(getData(This, OFFSET_DESC + 0x40));
                g.drawImage(src, dwX, dwY, dwX + width, dwY + height, srcX1, srcY1, srcX2, srcY1 + height, null);
            } else if ((dwTrans & 0xF) == DDBLTFAST_DESTCOLORKEY) {
                System.out.println(getName() + " DDBLTFAST_DESTCOLORKEY not implemented yet");
                notImplemented();
            }
            g.dispose();
            if (StaticData.currentPrimarySurface == This)
                Main.drawImage(getImage(This, true).getImage());
            CPU_Regs.reg_eax.dword = Error.S_OK;
        }
    };
    // HRESULT DeleteAttachedSurface(this, DWORD dwFlags, LPDIRECTDRAWSURFACE lpDDSAttachedSurface)
    private static final Callback.Handler DeleteAttachedSurface = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.DeleteAttachedSurface";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            int lpDDSAttachedSurface = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT EnumAttachedSurfaces(this, LPVOID lpContext, LPDDENUMSURFACESCALLBACK lpEnumSurfacesCallback)
    private static final Callback.Handler EnumAttachedSurfaces = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.EnumAttachedSurfaces";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpContext = CPU.CPU_Pop32();
            int lpEnumSurfacesCallback = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT EnumOverlayZOrders(this, DWORD dwFlags, LPVOID lpContext, LPDDENUMSURFACESCALLBACK lpfnCallback)
    private static final Callback.Handler EnumOverlayZOrders = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.EnumOverlayZOrders";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            int lpContext = CPU.CPU_Pop32();
            int lpfnCallback = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT Flip(this, LPDIRECTDRAWSURFACE lpDDSurfaceTargetOverride, DWORD dwFlags)
    private static final Callback.Handler Flip = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.Flip";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpDDSurfaceTargetOverride = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();

            int backBuffer = getData(This, OFFSET_BACK_BUFFER);

            BufferedImage src = getImage(backBuffer, true).getImage();
            Main.drawImage(src);
            // swap caches and memory
            int frontCache = getData(This, OFFSET_IMAGE_CACHE);
            int backCache = getData(backBuffer, OFFSET_IMAGE_CACHE);
            setData(This, OFFSET_IMAGE_CACHE, backCache);
            setData(backBuffer, OFFSET_IMAGE_CACHE, frontCache);

            StaticData.screen.set(getImage(backBuffer, true));

            int frontDC = getData(This, OFFSET_DC);
            int backDC = getData(backBuffer, OFFSET_DC);
            if (frontDC != 0 || backDC != 0) {
                Win.panic(getName() + " wans't expecting this call while a DC was open");
            }
        }
    };
    // HRESULT GetAttachedSurface(this, LPDDSCAPS lpDDSCaps, LPDIRECTDRAWSURFACE *lplpDDAttachedSurface)
    private static final Callback.Handler GetAttachedSurface = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.GetAttachedSurface";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpDDSCaps = CPU.CPU_Pop32();
            int lplpDDAttachedSurface = CPU.CPU_Pop32();
            if (lpDDSCaps == 0 || lplpDDAttachedSurface == 0) {
                CPU_Regs.reg_eax.dword = Error.E_POINTER;
            } else {
                int caps = Memory.mem_readd(lpDDSCaps);
                if (caps == DDSCAPS_BACKBUFFER) {
                    CPU_Regs.reg_eax.dword = Error.S_OK;
                    Memory.mem_writed(lplpDDAttachedSurface, getData(This, OFFSET_BACK_BUFFER));
                } else {
                    Win.panic(getName() + " currently only supports the flag DDSCAPS_BACKBUFFER");
                }
            }
        }
    };
    // HRESULT GetBltStatus(this, DWORD dwFlags)
    private static final Callback.Handler GetBltStatus = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.GetBltStatus";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            CPU_Regs.reg_eax.dword = Error.S_OK; // current the blits are not async so we are always ready
        }
    };
    // HRESULT GetCaps(this, LPDDSCAPS lpDDSCaps)
    private static final Callback.Handler GetCaps = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.GetCaps";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpDDSCaps = CPU.CPU_Pop32();
            if (lpDDSCaps == 0) {
                CPU_Regs.reg_eax.dword = Error.E_POINTER;
            } else {
                Memory.mem_writed(lpDDSCaps, Memory.mem_readd(This + OFFSET_DATA_START + OFFSET_DESC + 0x68));
                if (isCap2(This)) {
                    Memory.mem_writed(lpDDSCaps + 4,
                        Memory.mem_readd(This + OFFSET_DATA_START + OFFSET_DESC + 0x68 + 4));
                    Memory.mem_writed(lpDDSCaps + 8,
                        Memory.mem_readd(This + OFFSET_DATA_START + OFFSET_DESC + 0x68 + 8));
                    Memory.mem_writed(lpDDSCaps + 12,
                        Memory.mem_readd(This + OFFSET_DATA_START + OFFSET_DESC + 0x68 + 12));
                }
                CPU_Regs.reg_eax.dword = Error.S_OK;
            }
        }
    };
    // HRESULT GetClipper(this, LPDIRECTDRAWCLIPPER *lplpDDClipper)
    private static final Callback.Handler GetClipper = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.GetClipper";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lplpDDClipper = CPU.CPU_Pop32();
            int clipper = getData(This, OFFSET_CLIPPER);
            if (clipper == 0)
                CPU_Regs.reg_eax.dword = DError.DDERR_NOCLIPPERATTACHED;
            else {
                AddRef(clipper);
                Memory.mem_writed(lplpDDClipper, clipper);
                CPU_Regs.reg_eax.dword = Error.S_OK;
            }
        }
    };
    // HRESULT GetColorKey(this, DWORD dwFlags, LPDDCOLORKEY lpDDColorKey)
    private static final Callback.Handler GetColorKey = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.GetColorKey";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            int lpDDColorKey = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT GetDC(this, HDC *lphDC)
    private static final Callback.Handler GetDC = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.GetDC";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lphDC = CPU.CPU_Pop32();
            if (lphDC == 0) {
                CPU_Regs.reg_eax.dword = Error.DDERR_INVALIDPARAMS;
            } else {
                int hdc = getData(This, OFFSET_DC);
                if (hdc == 0) {
                    WinDC dc = WinDC.create(getImage(This, true), false);
                    hdc = dc.getHandle();
                    setData(This, OFFSET_DC, hdc);
                } else {
                    WinDC dc = WinDC.get(hdc);
                    dc.open();
                }
                Memory.mem_writed(lphDC, hdc);
                CPU_Regs.reg_eax.dword = Error.S_OK;
            }
        }
    };
    // HRESULT GetFlipStatus(this, DWORD dwFlags)
    private static final Callback.Handler GetFlipStatus = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.GetFlipStatus";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT GetOverlayPosition(this, LPLONG lplX, LPLONG lplY)
    private static final Callback.Handler GetOverlayPosition = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.GetOverlayPosition";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lplX = CPU.CPU_Pop32();
            int lplY = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT GetPalette(this, LPDIRECTDRAWPALETTE *lplpDDPalette)
    private static final Callback.Handler GetPalette = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.GetPalette";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lplpDDPalette = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT GetPixelFormat(this, LPDDPIXELFORMAT lpDDPixelFormat)
    private static final Callback.Handler GetPixelFormat = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.GetPixelFormat";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpDDPixelFormat = CPU.CPU_Pop32();
            if (lpDDPixelFormat == 0) {
                CPU_Regs.reg_eax.dword = Error.DDERR_INVALIDPARAMS;
            } else {
                int size = Memory.mem_readd(This + OFFSET_DATA_START + OFFSET_DESC + 0x48);
                if (Memory.mem_readd(lpDDPixelFormat) != size) {
                    System.out.println(
                        getName() + ": wrong size." + Memory.mem_readd(lpDDPixelFormat) + " does not equal " + size);
                    CPU_Regs.reg_eax.dword = Error.DDERR_INVALIDPARAMS;
                } else {
                    Memory.mem_memcpy(lpDDPixelFormat, This + OFFSET_DATA_START + OFFSET_DESC + 0x48, size);
                    CPU_Regs.reg_eax.dword = Error.S_OK;
                }
            }
        }
    };
    // HRESULT GetSurfaceDesc(this, LPDDSURFACEDESC lpDDSurfaceDesc)
    private static final Callback.Handler GetSurfaceDesc = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.GetSurfaceDesc";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpDDSurfaceDesc = CPU.CPU_Pop32();

            if (lpDDSurfaceDesc == 0) {
                CPU_Regs.reg_eax.dword = Error.DDERR_INVALIDPARAMS;
            } else {
                int size = Memory.mem_readd(This + OFFSET_DATA_START + OFFSET_DESC);
                if (Memory.mem_readd(lpDDSurfaceDesc) != size) {
                    System.out.println(
                        getName() + ": wrong size." + Memory.mem_readd(lpDDSurfaceDesc) + " does not equal " + size);
                    CPU_Regs.reg_eax.dword = Error.DDERR_INVALIDPARAMS;
                } else {
                    Memory.mem_memcpy(lpDDSurfaceDesc, This + OFFSET_DATA_START + OFFSET_DESC, size);
                    CPU_Regs.reg_eax.dword = Error.S_OK;
                }
            }
        }
    };
    // HRESULT Initialize(this, LPDIRECTDRAW lpDD, LPDDSURFACEDESC lpDDSurfaceDesc)
    private static final Callback.Handler Initialize = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.Initialize";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpDD = CPU.CPU_Pop32();
            int lpDDSurfaceDesc = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT IsLost(this)
    private static final Callback.Handler IsLost = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.IsLost";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            CPU_Regs.reg_eax.dword = Error.S_OK;
        }
    };
    // HRESULT Lock(this, LPRECT lpDestRect, LPDDSURFACEDESC lpDDSurfaceDesc, DWORD dwFlags, HANDLE hEvent)
    private static final Callback.Handler Lock = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.Lock";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpDestRect = CPU.CPU_Pop32();
            int lpDDSurfaceDesc = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            int hEvent = CPU.CPU_Pop32();

            if ((dwFlags & DDLOCK_EVENT) != 0) {
                Console.out(getName() + " flag DDLOCK_EVENT not implemented yet ");
                notImplemented();
            }
            if ((dwFlags & DDLOCK_NOSYSLOCK) != 0) {
                Console.out(getName() + " flag DDLOCK_NOSYSLOCK not implemented yet ");
                notImplemented();
            }
            if ((dwFlags & DDLOCK_NOOVERWRITE) != 0) {
                Console.out(getName() + " flag DDLOCK_NOOVERWRITE not implemented yet ");
                notImplemented();
            }
            if ((dwFlags & DDLOCK_DISCARDCONTENTS) != 0) {
                Console.out(getName() + " flag DDLOCK_DISCARDCONTENTS not implemented yet ");
                notImplemented();
            }
            if (lpDDSurfaceDesc == 0) {
                CPU_Regs.reg_eax.dword = Error.E_POINTER;
                return;
            }
            if (lpDestRect != 0) {
                Win.panic(getName() + " lpDestRect support is not implemented yet");
            }
            int address = getImage(This, true).map();
            Memory.mem_memcpy(lpDDSurfaceDesc, This + OFFSET_DATA_START + OFFSET_DESC,
                isDesc2(This) ? DDSurfaceDesc.SIZE2 : DDSurfaceDesc.SIZE);
            Memory.mem_writed(lpDDSurfaceDesc + 0x24, address);
            getImage(This, true).lock();
            CPU_Regs.reg_eax.dword = Error.S_OK;
        }
    };
    // HRESULT ReleaseDC(this, HDC hDC)
    private static final Callback.Handler ReleaseDC = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.ReleaseDC";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int hDC = CPU.CPU_Pop32();
            if (hDC != getData(This, OFFSET_DC)) {
                CPU_Regs.reg_eax.dword = Error.DDERR_INVALIDPARAMS;
            } else {
                WinDC dc = WinDC.get(hDC);
                dc.close();
                setData(This, OFFSET_DC, 0);
                if (StaticData.currentPrimarySurface == This)
                    Main.drawImage(getImage(This, true).getImage());
                CPU_Regs.reg_eax.dword = Error.S_OK;
            }
        }
    };
    // HRESULT Restore(this)
    private static final Callback.Handler Restore = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.Restore";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            // no need to do anything here since we never unload it from video memory
            CPU_Regs.reg_eax.dword = Error.S_OK;
        }
    };
    // HRESULT SetClipper(this, LPDIRECTDRAWCLIPPER lpDDClipper)
    private static final Callback.Handler SetClipper = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.SetClipper";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpDDClipper = CPU.CPU_Pop32();
            AddRef(lpDDClipper);
            setData(This, OFFSET_CLIPPER, lpDDClipper);
            CPU_Regs.reg_eax.dword = Error.S_OK;
        }
    };
    // HRESULT SetColorKey(this, DWORD dwFlags, LPDDCOLORKEY lpDDColorKey)
    private static final Callback.Handler SetColorKey = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.SetColorKey";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            int lpDDColorKey = CPU.CPU_Pop32();
            boolean single = (dwFlags & DDCKEY_COLORSPACE) == 0;

            if (lpDDColorKey == 0) {
                switch (dwFlags & ~DDCKEY_COLORSPACE) {
                    case DDCKEY_DESTBLT:
                        setData(This, OFFSET_DESC + 0x04,
                            getData(This, OFFSET_DESC + 0x04) & ~DDSurfaceDesc.DDSD_CKDESTBLT);
                        break;
                    case DDCKEY_DESTOVERLAY:
                        setData(This, OFFSET_DESC + 0x04,
                            getData(This, OFFSET_DESC + 0x04) & ~DDSurfaceDesc.DDSD_CKDESTOVERLAY);
                        break;
                    case DDCKEY_SRCBLT:
                        setData(This, OFFSET_DESC + 0x04,
                            getData(This, OFFSET_DESC + 0x04) & ~DDSurfaceDesc.DDSD_CKSRCBLT);
                        break;
                    case DDCKEY_SRCOVERLAY:
                        setData(This, OFFSET_DESC + 0x04,
                            getData(This, OFFSET_DESC + 0x04) & ~DDSurfaceDesc.DDSD_CKSRCOVERLAY);
                        break;
                }
            } else {
                int c1 = Memory.mem_readd(lpDDColorKey);
                int c2 = 0;
                if (!single) {
                    c2 = Memory.mem_readd(lpDDColorKey + 4);
                    System.out.println(getName() + " color space not implemented yet");
                    notImplemented();
                }

                switch (dwFlags & ~DDCKEY_COLORSPACE) {
                    case DDCKEY_DESTBLT:
                        setData(This, OFFSET_DESC + 0x30, c1);
                        setData(This, OFFSET_DESC + 0x04,
                            getData(This, OFFSET_DESC + 0x04) | DDSurfaceDesc.DDSD_CKDESTBLT);
                        break;
                    case DDCKEY_DESTOVERLAY:
                        setData(This, OFFSET_DESC + 0x28, c1);
                        setData(This, OFFSET_DESC + 0x04,
                            getData(This, OFFSET_DESC + 0x04) | DDSurfaceDesc.DDSD_CKDESTOVERLAY);
                        break;
                    case DDCKEY_SRCBLT:
                        setData(This, OFFSET_DESC + 0x40, c1);
                        setData(This, OFFSET_DESC + 0x04,
                            getData(This, OFFSET_DESC + 0x04) | DDSurfaceDesc.DDSD_CKSRCBLT);
                        boolean i = false;
                        break;
                    case DDCKEY_SRCOVERLAY:
                        setData(This, OFFSET_DESC + 0x48, c1);
                        setData(This, OFFSET_DESC + 0x04,
                            getData(This, OFFSET_DESC + 0x04) | DDSurfaceDesc.DDSD_CKSRCOVERLAY);
                        break;
                }
            }
            CPU_Regs.reg_eax.dword = Error.S_OK;
        }
    };
    // HRESULT SetOverlayPosition(this, LONG lX, LONG lY)
    private static final Callback.Handler SetOverlayPosition = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.SetOverlayPosition";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lX = CPU.CPU_Pop32();
            int lY = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT SetPalette(this, LPDIRECTDRAWPALETTE lpDDPalette)
    private static final Callback.Handler SetPalette = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.SetPalette";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpDDPalette = CPU.CPU_Pop32();
            int oldPalette = getData(This, OFFSET_PALETTE);
            if (oldPalette != 0) {
                Release(oldPalette);
            }
            if (lpDDPalette == 0) {
                setData(This, OFFSET_PALETTE, 0);
            } else {
                AddRef(lpDDPalette);
                setData(This, OFFSET_PALETTE, lpDDPalette);
                int backBuffer = getData(This, OFFSET_BACK_BUFFER);
                if (backBuffer != 0) {
                    oldPalette = getData(backBuffer, OFFSET_PALETTE);
                    if (oldPalette != 0) {
                        Release(oldPalette);
                    }
                    AddRef(lpDDPalette);
                    setData(backBuffer, OFFSET_PALETTE, lpDDPalette);
                }
                if ((getCaps(This) & DDSCAPS_PRIMARYSURFACE) != 0) {
                    // :TODO: this will leak
                    AddRef(lpDDPalette);
                    IDirectDraw.setPalette(getDirectDraw(This), lpDDPalette);
                }
            }
            // :TODO: why does this cause flicker on donuts.exe
            //lastPaletteChange = WinSystem.getTickCount();
            JavaBitmap image = getImage(This, true);
            if (image != null) {
                image.setPalette(getPalette(This) + IUnknown.OFFSET_DATA_START + IDirectDrawPalette.OFFSET_COLOR_DATA);
            }
            int backBuffer = getData(This, OFFSET_BACK_BUFFER);
            if (backBuffer != 0) {
                image = getImage(backBuffer, true);
                if (image != null) {
                    image.setPalette(
                        getPalette(This) + IUnknown.OFFSET_DATA_START + IDirectDrawPalette.OFFSET_COLOR_DATA);
                }
            }
            CPU_Regs.reg_eax.dword = Error.S_OK;
        }
    };
    // HRESULT Unlock(this, LPVOID lpSurfaceData)
    private static final Callback.Handler Unlock = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.Unlock";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpSurfaceData = CPU.CPU_Pop32();
            getImage(This, true).unlock();
            if (StaticData.currentPrimarySurface == This)
                Main.drawImage(getImage(This, true).getImage());
            CPU_Regs.reg_eax.dword = Error.S_OK;
        }
    };
    // HRESULT UpdateOverlay(this, LPRECT lpSrcRect, LPDIRECTDRAWSURFACE lpDDDestSurface, LPRECT lpDestRect, DWORD dwFlags, LPDDOVERLAYFX lpDDOverlayFx)
    private static final Callback.Handler UpdateOverlay = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.UpdateOverlay";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int lpSrcRect = CPU.CPU_Pop32();
            int lpDDDestSurface = CPU.CPU_Pop32();
            int lpDestRect = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            int lpDDOverlayFx = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT UpdateOverlayDisplay(this, DWORD dwFlags)
    private static final Callback.Handler UpdateOverlayDisplay = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.UpdateOverlayDisplay";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            notImplemented();
        }
    };
    // HRESULT UpdateOverlayZOrder(this, DWORD dwFlags, LPDIRECTDRAWSURFACE lpDDSReference)
    private static final Callback.Handler UpdateOverlayZOrder = new HandlerBase() {
        @Override
        public java.lang.String getName() {
            return "IDirectDrawSurface.UpdateOverlayZOrder";
        }

        @Override
        public void onCall() {
            int This = CPU.CPU_Pop32();
            int dwFlags = CPU.CPU_Pop32();
            int lpDDSReference = CPU.CPU_Pop32();
            notImplemented();
        }
    };

    public static boolean isCap2(int This) {
        int flags = getData(This, OFFSET_FLAGS);
        return (flags & FLAGS_CAPS2) != 0;
    }

    public static boolean isDesc2(int This) {
        int flags = getData(This, OFFSET_FLAGS);
        return (flags & FLAGS_DESC2) != 0;
    }

    public static int getPaletteEntry(int This, int entry) {
        int lpDDPalette = getPalette(This);
        if (lpDDPalette != 0) {
            return getData(lpDDPalette, IDirectDrawPalette.OFFSET_COLOR_DATA + 4 * entry);
        }
        Win.panic("Unexpected getPaletteEntry call");
        return 0;
    }

    public static JavaBitmap getImage(int This, boolean create) {
        int index = getData(This, OFFSET_IMAGE_CACHE);
        JavaBitmap image = null;
        if (index == 0) {
            Win.panic("IDirectDrawSurface bitmap should already be created when using the map ability");
        } else {
            WinObject object = WinObject.getObject(index);
            image = (JavaBitmap) object.data;
            int time = getData(This, OFFSET_IMAGE_CACHE_TIME);
            if (time < lastPaletteChange) {
                if (create) {
                    image.setPalette(
                        getPalette(This) + IUnknown.OFFSET_DATA_START + IDirectDrawPalette.OFFSET_COLOR_DATA);
                    setData(This, OFFSET_IMAGE_CACHE_TIME, WinSystem.getTickCount());
                } else {
                    // probably a color key cache, let it be re-created
                    object.close();
                    setData(This, OFFSET_IMAGE_CACHE, 0);
                }
            }
        }
        return image;
    }

    // while locked the surface will not cache a BufferedImage object, this will really slow things down, but allows
    // apps to access surface memory directly
    public static void lock(int This) {
        setData(This, OFFSET_FLAGS, getData(This, OFFSET_FLAGS) | FLAGS_LOCKED);
    }

    public static void unlock(int This) {
        setData(This, OFFSET_FLAGS, getData(This, OFFSET_FLAGS) & ~FLAGS_LOCKED);
    }

    public static int getWidth(int This) {
        return getData(This, OFFSET_DESC + 0x0C);
    }

    public static int getBpp(int This) {
        return getData(This, OFFSET_DESC + 0x54);
    }

    public static int getHeight(int This) {
        return getData(This, OFFSET_DESC + 0x08);
    }

    public static int create(int pDirectDraw, int pDesc) {
        return create("IDirectDrawSurface", pDirectDraw, pDesc, 0);
    }

    public static int getCaps(int This) {
        return getData(This, OFFSET_DESC + 0x68);
    }

    public static int getDirectDraw(int This) {
        return getData(This, OFFSET_DIRECT_DRAW);
    }

    public static int getPalette(int This) {
        int palette = getData(This, OFFSET_PALETTE);
        if (palette == 0)
            palette = IDirectDraw.getPalette(getDirectDraw(This));
        return palette;
    }

    public static int create(String name, int pDirectDraw, int pDesc, int flags) {
        int vtable = getVTable(name);
        if (vtable == 0) {
            vtable = createVTable();
            cleanupCallback = WinCallback.addCallback(CleanUp);
        }
        int result = allocate(vtable, DDSurfaceDesc.SIZE2 + DATA_SIZE, cleanupCallback);
        int descSize = (flags & FLAGS_DESC2) != 0 ? DDSurfaceDesc.SIZE2 : DDSurfaceDesc.SIZE;
        Memory.mem_memcpy(result + OFFSET_DATA_START + OFFSET_DESC, pDesc, descSize);

        DDSurfaceDesc d = new DDSurfaceDesc(pDesc, (flags & FLAGS_DESC2) != 0);

        int width = 0;
        int height = 0;
        int caps = d.ddsCaps;

        if ((d.ddsCaps & DDSCAPS_PRIMARYSURFACE) != 0) {
            width = StaticData.screen.getWidth();
            height = StaticData.screen.getHeight();
            caps |= DDSCAPS_VIDEOMEMORY;
            caps |= DDSCAPS_VISIBLE;
            caps |= DDSCAPS_LOCALVIDMEM;
        } else if ((d.ddsCaps & DDSCAPS_OFFSCREENPLAIN) != 0) {
            if ((d.dwFlags & DDSurfaceDesc.DDSD_WIDTH) == 0) {
                Win.panic(name + ".CreateSurface DDSD_WIDTH must be specified when using DDSCAPS_OFFSCREENPLAIN");
            }
            if ((d.dwFlags & DDSurfaceDesc.DDSD_HEIGHT) == 0) {
                Win.panic(name + ".CreateSurface DDSD_HEIGHT must be specified when using DDSCAPS_OFFSCREENPLAIN");
            }
            if ((d.dwFlags & DDSurfaceDesc.DDSD_BACKBUFFERCOUNT) != 0) {
                Win.panic(name + ".CreateSurface was expecting DDSD_BACKBUFFERCOUNT with DDSCAPS_OFFSCREENPLAIN");
            }
            width = d.dwWidth;
            height = d.dwHeight;
            caps &= ~DDSCAPS_VISIBLE;
        } else {
            Win.panic(name
                + ".CreateSurface currently DDSCAPS_PRIMARYSURFACE or DDSCAPS_OFFSCREENPLAIN must be specified. ddsCaps = 0x"
                + Ptr.toString(d.ddsCaps));
        }

        int bpp;
        if ((d.dwFlags & DDSurfaceDesc.DDSD_PIXELFORMAT) != 0
            && (d.ddpfPixelFormat.dwFlags & DDPixelFormat.DDPF_RGB) != 0 && d.ddpfPixelFormat.dwRGBBitCount != 0)
            bpp = d.ddpfPixelFormat.dwRGBBitCount;
        else
            bpp = StaticData.screen.getBpp();

        BufferedImage bi = Pixel.createImage(0, bpp, JavaBitmap.getDefaultPalette(), width, height, false);
        JavaBitmap bitmap = new JavaBitmap(bi, bpp, width, height, JavaBitmap.getDefaultPalette());

        if ((d.ddsCaps & DDSCAPS_PRIMARYSURFACE) != 0) {
            StaticData.screen = bitmap;
        }
        caps |= DDSCAPS_LOCALVIDMEM;

        WinObject object = WinObject.createWinObject();
        object.data = bitmap;
        setData(result, OFFSET_IMAGE_CACHE, object.getHandle());
        setData(result, OFFSET_IMAGE_CACHE_TIME, WinSystem.getTickCount());

        setData(result, OFFSET_DESC, descSize);
        setData(result, OFFSET_DESC + 0x04, DDSurfaceDesc.DDSD_CAPS | DDSurfaceDesc.DDSD_HEIGHT
            | DDSurfaceDesc.DDSD_WIDTH | DDSurfaceDesc.DDSD_PITCH | DDSurfaceDesc.DDSD_PIXELFORMAT);
        setData(result, OFFSET_DESC + 0x08, height);
        setData(result, OFFSET_DESC + 0x0C, width);
        setData(result, OFFSET_DESC + 0x10, width * bpp / 8); // pitch

        setData(result, OFFSET_DESC + 0x48, DDPixelFormat.SIZE);
        int pfFlags = DDPixelFormat.DDPF_RGB;
        if (bpp == 8) {
            caps |= DDSCAPS_PALETTE;
            pfFlags |= DDPixelFormat.DDPF_PALETTEINDEXED8;
        } else if (bpp == 16) {
            setData(result, OFFSET_DESC + 0x58, 0xF800);
            setData(result, OFFSET_DESC + 0x5C, 0x07E0);
            setData(result, OFFSET_DESC + 0x60, 0x001F);
        } else if (bpp == 24) {
            setData(result, OFFSET_DESC + 0x58, 0xFF0000);
            setData(result, OFFSET_DESC + 0x5C, 0x00FF00);
            setData(result, OFFSET_DESC + 0x60, 0x0000FF);
        } else if (bpp == 32) {
            setData(result, OFFSET_DESC + 0x58, 0x00FF0000);
            setData(result, OFFSET_DESC + 0x5C, 0x0000FF00);
            setData(result, OFFSET_DESC + 0x60, 0x000000FF);
            setData(result, OFFSET_DESC + 0x64, 0xFF000000);
        }
        setData(result, OFFSET_DESC + 0x4C, pfFlags);
        setData(result, OFFSET_DESC + 0x54, bpp);

        if ((d.dwFlags & DDSurfaceDesc.DDSD_BACKBUFFERCOUNT) != 0) {
            if ((d.ddsCaps & DDSCAPS_COMPLEX) == 0) {
                Win.panic(name + ".CreateSurface wasn't expecting a back buffer without DDSCAPS_COMPLEX");
            }
            // The back buffer will not contain child surfaces and it is never visible on the screen
            setData(result, OFFSET_DESC + 0x68, caps & ~(DDSCAPS_VISIBLE | DDSCAPS_COMPLEX)); // :TODO: just guessing what the backbuffer flags look like, need to investigate
            int backBuffer = create(name, pDirectDraw, result + OFFSET_DATA_START + OFFSET_DESC, flags);
            setData(result, OFFSET_BACK_BUFFER, backBuffer);
            // Set after we create the back buffer
            setData(result, OFFSET_DESC + 0x04,
                getData(result, OFFSET_DESC + 0x04) | DDSurfaceDesc.DDSD_BACKBUFFERCOUNT);
            setData(result, OFFSET_DESC + 0x14, 1);
            if (d.dwBackBufferCount != 1) {
                System.out.println(name + ".CreateSurface faking " + d.dwBackBufferCount + " back buffers.");
            }
            Scheduler.monitor = 0;
        } else if ((d.ddsCaps & DDSCAPS_PRIMARYSURFACE) != 0) {
            Scheduler.monitor = result;
        }
        if ((d.ddsCaps & DDSCAPS_PRIMARYSURFACE) != 0)
            StaticData.currentPrimarySurface = result;
        setData(result, OFFSET_DESC + 0x68, caps);
        setData(result, OFFSET_DIRECT_DRAW, pDirectDraw);

        return result;
    }

    private static int createVTable() {
        int address = allocateVTable("IDirectDrawSurface", VTABLE_COUNT);
        addIDirectDrawSurface(address);
        return address;
    }

    static int addIDirectDrawSurface(int address) {
        address = addIUnknown(address);

        address = add(address, AddAttachedSurface);
        address = add(address, AddOverlayDirtyRect);
        address = add(address, Blt);
        address = add(address, BltBatch);
        address = add(address, BltFast);
        address = add(address, DeleteAttachedSurface);
        address = add(address, EnumAttachedSurfaces);
        address = add(address, EnumOverlayZOrders);
        address = add(address, Flip);
        address = add(address, GetAttachedSurface);
        address = add(address, GetBltStatus);
        address = add(address, GetCaps);
        address = add(address, GetClipper);
        address = add(address, GetColorKey);
        address = add(address, GetDC);
        address = add(address, GetFlipStatus);
        address = add(address, GetOverlayPosition);
        address = add(address, GetPalette);
        address = add(address, GetPixelFormat);
        address = add(address, GetSurfaceDesc);
        address = add(address, Initialize);
        address = add(address, IsLost);
        address = add(address, Lock);
        address = add(address, ReleaseDC);
        address = add(address, Restore);
        address = add(address, SetClipper);
        address = add(address, SetColorKey);
        address = add(address, SetOverlayPosition);
        address = add(address, SetPalette);
        address = add(address, Unlock);
        address = add(address, UpdateOverlay);
        address = add(address, UpdateOverlayDisplay);
        return add(address, UpdateOverlayZOrder);
    }
}