package jdos.win.builtin;

import jdos.hardware.Memory;
import jdos.win.loader.BuiltinModule;
import jdos.win.system.Scheduler;
import jdos.win.system.WinObject;
import jdos.win.system.WinSystem;
import jdos.win.utils.Error;

public class WinAPI extends Error {
    public static final boolean LOG = false;
    public static final boolean LOG_GDI = LOG;
    public static final boolean LOG_MM = false;
    public static final boolean MSGLOG = false;
    public static final int MAX_PATH = 260;
    public static final int HFILE_ERROR = -1;
    public static final int TRUE = 1;
    public static final int FALSE = 0;
    public static final int WHITE_BRUSH = 0;
    public static final int LTGRAY_BRUSH = 1;
    public static final int GRAY_BRUSH = 2;
    public static final int DKGRAY_BRUSH = 3;
    public static final int BLACK_BRUSH = 4;
    public static final int NULL_BRUSH = 5;
    public static final int HOLLOW_BRUSH = 5;
    public static final int WHITE_PEN = 6;
    public static final int BLACK_PEN = 7;
    public static final int NULL_PEN = 8;
    public static final int OEM_FIXED_FONT = 10;
    public static final int ANSI_FIXED_FONT = 11;
    public static final int ANSI_VAR_FONT = 12;
    public static final int SYSTEM_FONT = 13;
    public static final int DEVICE_DEFAULT_FONT = 14;
    public static final int DEFAULT_PALETTE = 15;
    public static final int SYSTEM_FIXED_FONT = 16;
    public static final int DEFAULT_GUI_FONT = 17;
    public static final int DC_BRUSH = 18;
    public static final int DC_PEN = 19;
    public static final int STOCK_LAST = 19;
    /* Brush styles */
    public static final int BS_SOLID = 0;
    public static final int BS_NULL = 1;
    public static final int BS_HOLLOW = 1;
    public static final int BS_HATCHED = 2;
    public static final int BS_PATTERN = 3;
    public static final int BS_INDEXED = 4;
    public static final int BS_DIBPATTERN = 5;
    public static final int BS_DIBPATTERNPT = 6;
    public static final int BS_PATTERN8X8 = 7;
    public static final int BS_DIBPATTERN8X8 = 8;
    public static final int BS_MONOPATTERN = 9;
    /* Hatch styles */
    public static final int HS_HORIZONTAL = 0;
    public static final int HS_VERTICAL = 1;
    public static final int HS_FDIAGONAL = 2;
    public static final int HS_BDIAGONAL = 3;
    public static final int HS_CROSS = 4;
    public static final int HS_DIAGCROSS = 5;
    public static final int PS_SOLID = 0x00000000;
    public static final int PS_DASH = 0x00000001;
    public static final int PS_DOT = 0x00000002;
    public static final int PS_DASHDOT = 0x00000003;
    public static final int PS_DASHDOTDOT = 0x00000004;
    public static final int PS_NULL = 0x00000005;
    public static final int PS_INSIDEFRAME = 0x00000006;
    public static final int PS_USERSTYLE = 0x00000007;
    public static final int PS_ALTERNATE = 0x00000008;
    public static final int PS_STYLE_MASK = 0x0000000f;
    public static final int PS_ENDCAP_ROUND = 0x00000000;
    public static final int PS_ENDCAP_SQUARE = 0x00000100;
    public static final int PS_ENDCAP_FLAT = 0x00000200;
    public static final int PS_ENDCAP_MASK = 0x00000f00;
    public static final int PS_JOIN_ROUND = 0x00000000;
    public static final int PS_JOIN_BEVEL = 0x00001000;
    public static final int PS_JOIN_MITER = 0x00002000;
    public static final int PS_JOIN_MASK = 0x0000f000;
    public static final int PS_COSMETIC = 0x00000000;
    public static final int PS_GEOMETRIC = 0x00010000;
    public static final int PS_TYPE_MASK = 0x000f0000;
    /* lfWeight values */
    public static final int FW_DONTCARE = 0;
    public static final int FW_THIN = 100;
    public static final int FW_EXTRALIGHT = 200;
    public static final int FW_ULTRALIGHT = 200;
    public static final int FW_LIGHT = 300;
    public static final int FW_NORMAL = 400;
    public static final int FW_REGULAR = 400;
    public static final int FW_MEDIUM = 500;
    public static final int FW_SEMIBOLD = 600;
    public static final int FW_DEMIBOLD = 600;
    public static final int FW_BOLD = 700;
    public static final int FW_EXTRABOLD = 800;
    public static final int FW_ULTRABOLD = 800;
    public static final int FW_HEAVY = 900;
    public static final int FW_BLACK = 900;
    /* lfCharSet values */
    public static final int ANSI_CHARSET = 0; /* CP1252, ansi-0, iso8859-{1,15} */
    public static final int DEFAULT_CHARSET = 1;
    public static final int SYMBOL_CHARSET = 2;
    public static final int OEM_CHARSET = 255;
    public static final int FS_LATIN1 = 0x00000001;
    public static final int FS_LATIN2 = 0x00000002;
    public static final int FS_SYMBOL = 0x80000000;
    /* lfOutPrecision values */
    public static final int OUT_DEFAULT_PRECIS = 0;
    public static final int OUT_STRING_PRECIS = 1;
    public static final int OUT_CHARACTER_PRECIS = 2;
    public static final int OUT_STROKE_PRECIS = 3;
    public static final int OUT_TT_PRECIS = 4;
    public static final int OUT_DEVICE_PRECIS = 5;
    public static final int OUT_RASTER_PRECIS = 6;
    public static final int OUT_TT_ONLY_PRECIS = 7;
    public static final int OUT_OUTLINE_PRECIS = 8;
    /* lfClipPrecision values */
    public static final int CLIP_DEFAULT_PRECIS = 0x00;
    public static final int CLIP_CHARACTER_PRECIS = 0x01;
    public static final int CLIP_STROKE_PRECIS = 0x02;
    public static final int CLIP_MASK = 0x0F;
    public static final int CLIP_LH_ANGLES = 0x10;
    public static final int CLIP_TT_ALWAYS = 0x20;
    public static final int CLIP_EMBEDDED = 0x80;
    /* lfQuality values */
    public static final int DEFAULT_QUALITY = 0;
    public static final int DRAFT_QUALITY = 1;
    public static final int PROOF_QUALITY = 2;
    public static final int NONANTIALIASED_QUALITY = 3;
    public static final int ANTIALIASED_QUALITY = 4;
    public static final int CLEARTYPE_QUALITY = 5;
    public static final int CLEARTYPE_NATURAL_QUALITY = 6;
    /* lfPitchAndFamily pitch values */
    public static final int DEFAULT_PITCH = 0x00;
    public static final int FIXED_PITCH = 0x01;
    public static final int VARIABLE_PITCH = 0x02;
    public static final int MONO_FONT = 0x08;
    public static final int FF_DONTCARE = 0x00;
    public static final int FF_ROMAN = 0x10;
    public static final int FF_SWISS = 0x20;
    public static final int FF_MODERN = 0x30;
    public static final int FF_SCRIPT = 0x40;
    public static final int FF_DECORATIVE = 0x50;
    public static final int CLR_INVALID = -1;
    public static final int INVALID_HANDLE_VALUE = -1;
    /* Unicode char type flags */
    public static final int CT_CTYPE1 = 0x0001; /* usual ctype */
    public static final int CT_CTYPE2 = 0x0002; /* bidirectional layout info */
    public static final int CT_CTYPE3 = 0x0004; /* textprocessing info */
    public static final int GENERIC_READ = 0x80000000;
    public static final int GENERIC_WRITE = 0x40000000;
    public static final int GENERIC_EXECUTE = 0x20000000;
    public static final int GENERIC_ALL = 0x10000000;
    public static final String SYSTEM32_PATH = "C:\\Windows\\System32\\";
    public static final String WIN32_PATH = "C:\\WINDOWS";
    public static final String TEMP_PATH = "C:\\WINDOWS\\TEMP\\";
    public static final int WM_NULL = 0x0000;
    public static final int WM_CREATE = 0x0001;
    public static final int WM_DESTROY = 0x0002;
    public static final int WM_MOVE = 0x0003;
    public static final int WM_SIZE = 0x0005;
    public static final int WM_ACTIVATE = 0x0006;
    public static final int WM_SETFOCUS = 0x0007;
    public static final int WM_KILLFOCUS = 0x0008;
    public static final int WM_ENABLE = 0x000A;
    public static final int WM_SETREDRAW = 0x000B;
    public static final int WM_SETTEXT = 0x000C;
    public static final int WM_GETTEXT = 0x000D;
    public static final int WM_GETTEXTLENGTH = 0x000E;
    public static final int WM_PAINT = 0x000F;
    public static final int WM_CLOSE = 0x0010;
    public static final int WM_QUERYENDSESSION = 0x0011;
    public static final int WM_QUIT = 0x0012;
    public static final int WM_QUERYOPEN = 0x0013;
    public static final int WM_ERASEBKGND = 0x0014;
    public static final int WM_SYSCOLORCHANGE = 0x0015;
    public static final int WM_ENDSESSION = 0x0016;
    public static final int WM_SHOWWINDOW = 0x0018;
    public static final int WM_CTLCOLOR = 0x0019;
    public static final int WM_ACTIVATEAPP = 0x001C;
    public static final int WM_CANCELMODE = 0x001F;
    public static final int WM_SETCURSOR = 0x0020;
    public static final int WM_MOUSEACTIVATE = 0x0021;
    public static final int WM_CHILDACTIVATE = 0x0022;
    public static final int WM_QUEUESYNC = 0x0023;
    public static final int WM_GETMINMAXINFO = 0x0024;
    public static final int WM_PAINTICON = 0x0026;
    public static final int WM_ICONERASEBKGND = 0x0027;
    public static final int WM_NEXTDLGCTL = 0x0028;
    public static final int WM_SPOOLERSTATUS = 0x002A;
    public static final int WM_DRAWITEM = 0x002B;
    public static final int WM_MEASUREITEM = 0x002C;
    public static final int WM_DELETEITEM = 0x002D;
    public static final int WM_VKEYTOITEM = 0x002E;
    public static final int WM_CHARTOITEM = 0x002F;
    public static final int WM_SETFONT = 0x0030;
    public static final int WM_GETFONT = 0x0031;
    public static final int WM_SETHOTKEY = 0x0032;
    public static final int WM_GETHOTKEY = 0x0033;
    public static final int WM_QUERYDRAGICON = 0x0037;
    public static final int WM_COMPAREITEM = 0x0039;
    public static final int WM_WINDOWPOSCHANGING = 0x0046;
    public static final int WM_WINDOWPOSCHANGED = 0x0047;
    public static final int WM_CONTEXTMENU = 0x007b;
    public static final int WM_STYLECHANGING = 0x007c;
    public static final int WM_STYLECHANGED = 0x007d;
    public static final int WM_DISPLAYCHANGE = 0x007e;
    public static final int WM_GETICON = 0x007f;
    public static final int WM_SETICON = 0x0080;
    public static final int WM_NCCREATE = 0x0081;
    public static final int WM_NCDESTROY = 0x0082;
    public static final int WM_NCCALCSIZE = 0x0083;
    public static final int WM_NCHITTEST = 0x0084;
    public static final int WM_NCPAINT = 0x0085;
    public static final int WM_NCACTIVATE = 0x0086;
    public static final int WM_GETDLGCODE = 0x0087;
    public static final int WM_SYNCPAINT = 0x0088;
    public static final int WM_NCMOUSEMOVE = 0x00A0;
    public static final int WM_NCLBUTTONDOWN = 0x00A1;
    public static final int WM_NCLBUTTONUP = 0x00A2;
    public static final int WM_NCLBUTTONDBLCLK = 0x00A3;
    public static final int WM_NCRBUTTONDOWN = 0x00A4;
    public static final int WM_NCRBUTTONUP = 0x00A5;
    public static final int WM_NCRBUTTONDBLCLK = 0x00A6;
    public static final int WM_NCMBUTTONDOWN = 0x00A7;
    public static final int WM_NCMBUTTONUP = 0x00A8;
    public static final int WM_NCMBUTTONDBLCLK = 0x00A9;
    public static final int WM_KEYFIRST = 0x0100;
    public static final int WM_KEYDOWN = 0x0100;
    public static final int WM_KEYUP = 0x0101;
    public static final int WM_CHAR = 0x0102;
    public static final int WM_DEADCHAR = 0x0103;
    public static final int WM_SYSKEYDOWN = 0x0104;
    public static final int WM_SYSKEYUP = 0x0105;
    public static final int WM_SYSCHAR = 0x0106;
    public static final int WM_SYSDEADCHAR = 0x0107;
    public static final int WM_KEYLAST = 0x0108;
    public static final int WM_INITDIALOG = 0x0110;
    public static final int WM_COMMAND = 0x0111;
    public static final int WM_SYSCOMMAND = 0x0112;
    public static final int WM_TIMER = 0x0113;
    public static final int WM_HSCROLL = 0x0114;
    public static final int WM_VSCROLL = 0x0115;
    public static final int WM_INITMENU = 0x0116;
    public static final int WM_INITMENUPOPUP = 0x0117;
    public static final int WM_MENUSELECT = 0x011F;
    public static final int WM_MENUCHAR = 0x0120;
    public static final int WM_ENTERIDLE = 0x0121;
    public static final int WM_CTLCOLORMSGBOX = 0x0132;
    public static final int WM_CTLCOLOREDIT = 0x0133;
    public static final int WM_CTLCOLORLISTBOX = 0x0134;
    public static final int WM_CTLCOLORBTN = 0x0135;
    public static final int WM_CTLCOLORDLG = 0x0136;
    public static final int WM_CTLCOLORSCROLLBAR = 0x0137;
    public static final int WM_CTLCOLORSTATIC = 0x0138;
    public static final int WM_MOUSEFIRST = 0x0200;
    public static final int WM_MOUSEMOVE = 0x0200;
    public static final int WM_LBUTTONDOWN = 0x0201;
    public static final int WM_LBUTTONUP = 0x0202;
    public static final int WM_LBUTTONDBLCLK = 0x0203;
    public static final int WM_RBUTTONDOWN = 0x0204;
    public static final int WM_RBUTTONUP = 0x0205;
    public static final int WM_RBUTTONDBLCLK = 0x0206;
    public static final int WM_MBUTTONDOWN = 0x0207;
    public static final int WM_MBUTTONUP = 0x0208;
    public static final int WM_MBUTTONDBLCLK = 0x0209;
    public static final int WM_MOUSEWHEEL = 0x020A;
    /* Win32 4.0 messages */
    public static final int WM_SIZING = 0x0214;
    public static final int WM_CAPTURECHANGED = 0x0215;
    public static final int WM_MOVING = 0x0216;
    public static final int WM_POWERBROADCAST = 0x0218;
    public static final int WM_DEVICECHANGE = 0x0219;
    public static final int WM_PARENTNOTIFY = 0x0210;
    public static final int WM_ENTERMENULOOP = 0x0211;
    public static final int WM_EXITMENULOOP = 0x0212;
    public static final int WM_DRAWCLIPBOARD = 0x0308;
    public static final int WM_PAINTCLIPBOARD = 0x0309;
    public static final int WM_VSCROLLCLIPBOARD = 0x030A;
    public static final int WM_SIZECLIPBOARD = 0x030B;
    public static final int WM_ASKCBFORMATNAME = 0x030C;
    public static final int WM_CHANGECBCHAIN = 0x030D;
    public static final int WM_HSCROLLCLIPBOARD = 0x030E;
    public static final int WM_QUERYNEWPALETTE = 0x030F;
    public static final int WM_PALETTEISCHANGING = 0x0310;
    public static final int WM_PALETTECHANGED = 0x0311;
    public static final int WM_HOTKEY = 0x0312;
    public static final int WM_PRINT = 0x0317;
    public static final int WM_PRINTCLIENT = 0x0318;
    public static final int WM_APPCOMMAND = 0x0319;
    public static final int WM_THEMECHANGED = 0x031A;
    public static final int WM_CLIPBOARDUPDATE = 0x031D;
    public static final int MM_MCINOTIFY = 0x03B9;
    public static final int WM_USER = 0x0400;
    public static final int WS_OVERLAPPED = 0x00000000;
    public static final int WS_POPUP = 0x80000000;
    public static final int WS_CHILD = 0x40000000;
    public static final int WS_MINIMIZE = 0x20000000;
    public static final int WS_VISIBLE = 0x10000000;
    public static final int WS_DISABLED = 0x08000000;
    public static final int WS_CLIPSIBLINGS = 0x04000000;
    public static final int WS_CLIPCHILDREN = 0x02000000;
    public static final int WS_MAXIMIZE = 0x01000000;
    public static final int WS_CAPTION = 0x00C00000; /* WS_BORDER | WS_DLGFRAME  */
    public static final int WS_BORDER = 0x00800000;
    public static final int WS_DLGFRAME = 0x00400000;
    public static final int WS_VSCROLL = 0x00200000;
    public static final int WS_HSCROLL = 0x00100000;
    public static final int WS_SYSMENU = 0x00080000;
    public static final int WS_THICKFRAME = 0x00040000;
    public static final int WS_GROUP = 0x00020000;
    public static final int WS_TABSTOP = 0x00010000;
    public static final int WS_MINIMIZEBOX = 0x00020000;
    public static final int WS_MAXIMIZEBOX = 0x00010000;
    public static final int WS_EX_DLGMODALFRAME = 0x00000001;
    public static final int WS_EX_NOPARENTNOTIFY = 0x00000004;
    public static final int WS_EX_TOPMOST = 0x00000008;
    public static final int WS_EX_ACCEPTFILES = 0x00000010;
    public static final int WS_EX_TRANSPARENT = 0x00000020;
    public static final int WS_EX_MDICHILD = 0x00000040;
    public static final int WS_EX_TOOLWINDOW = 0x00000080;
    public static final int WS_EX_WINDOWEDGE = 0x00000100;
    public static final int WS_EX_CLIENTEDGE = 0x00000200;
    public static final int WS_EX_CONTEXTHELP = 0x00000400;
    public static final int WS_EX_RIGHT = 0x00001000;
    public static final int WS_EX_LEFT = 0x00000000;
    public static final int WS_EX_RTLREADING = 0x00002000;
    public static final int WS_EX_LTRREADING = 0x00000000;
    public static final int WS_EX_LEFTSCROLLBAR = 0x00004000;
    public static final int WS_EX_RIGHTSCROLLBAR = 0x00000000;
    public static final int WS_EX_CONTROLPARENT = 0x00010000;
    public static final int WS_EX_STATICEDGE = 0x00020000;
    public static final int WS_EX_APPWINDOW = 0x00040000;
    public static final int HTERROR = -2;
    public static final int HTTRANSPARENT = -1;
    public static final int HTNOWHERE = 0;
    public static final int HTCLIENT = 1;
    public static final int HTCAPTION = 2;
    public static final int HTSYSMENU = 3;
    public static final int HTSIZE = 4;
    public static final int HTGROWBOX = HTSIZE;
    public static final int HTMENU = 5;
    public static final int HTHSCROLL = 6;
    public static final int HTVSCROLL = 7;
    public static final int HTMINBUTTON = 8;
    public static final int HTREDUCE = HTMINBUTTON;
    public static final int HTMAXBUTTON = 9;
    public static final int HTZOOM = HTMAXBUTTON;
    public static final int HTLEFT = 10;
    public static final int HTSIZEFIRST = HTLEFT;
    public static final int HTRIGHT = 11;
    public static final int HTTOP = 12;
    public static final int HTTOPLEFT = 13;
    public static final int HTTOPRIGHT = 14;
    public static final int HTBOTTOM = 15;
    public static final int HTBOTTOMLEFT = 16;
    public static final int HTBOTTOMRIGHT = 17;
    public static final int HTSIZELAST = HTBOTTOMRIGHT;
    public static final int HTBORDER = 18;
    public static final int HTOBJECT = 19;
    public static final int HTCLOSE = 20;
    public static final int HTHELP = 21;
    /* GetSystemMetrics() codes */
    public static final int SM_CXSCREEN = 0;
    public static final int SM_CYSCREEN = 1;
    public static final int SM_CXVSCROLL = 2;
    public static final int SM_CYHSCROLL = 3;
    public static final int SM_CYCAPTION = 4;
    public static final int SM_CXBORDER = 5;
    public static final int SM_CYBORDER = 6;
    public static final int SM_CXDLGFRAME = 7;
    public static final int SM_CYDLGFRAME = 8;
    public static final int SM_CYVTHUMB = 9;
    public static final int SM_CXHTHUMB = 10;
    public static final int SM_CXICON = 11;
    public static final int SM_CYICON = 12;
    public static final int SM_CXCURSOR = 13;
    public static final int SM_CYCURSOR = 14;
    public static final int SM_CYMENU = 15;
    public static final int SM_CXFULLSCREEN = 16;
    public static final int SM_CYFULLSCREEN = 17;
    public static final int SM_CYKANJIWINDOW = 18;
    public static final int SM_MOUSEPRESENT = 19;
    public static final int SM_CYVSCROLL = 20;
    public static final int SM_CXHSCROLL = 21;
    public static final int SM_DEBUG = 22;
    public static final int SM_SWAPBUTTON = 23;
    public static final int SM_RESERVED1 = 24;
    public static final int SM_RESERVED2 = 25;
    public static final int SM_RESERVED3 = 26;
    public static final int SM_RESERVED4 = 27;
    public static final int SM_CXMIN = 28;
    public static final int SM_CYMIN = 29;
    public static final int SM_CXSIZE = 30;
    public static final int SM_CYSIZE = 31;
    public static final int SM_CXFRAME = 32;
    public static final int SM_CYFRAME = 33;
    public static final int SM_CXMINTRACK = 34;
    public static final int SM_CYMINTRACK = 35;
    public static final int SM_CXDOUBLECLK = 36;
    public static final int SM_CYDOUBLECLK = 37;
    public static final int SM_CXICONSPACING = 38;
    public static final int SM_CYICONSPACING = 39;
    public static final int SM_MENUDROPALIGNMENT = 40;
    public static final int SM_PENWINDOWS = 41;
    public static final int SM_DBCSENABLED = 42;
    public static final int SM_CMOUSEBUTTONS = 43;
    public static final int SM_CXFIXEDFRAME = SM_CXDLGFRAME;
    public static final int SM_CYFIXEDFRAME = SM_CYDLGFRAME;
    public static final int SM_CXSIZEFRAME = SM_CXFRAME;
    public static final int SM_CYSIZEFRAME = SM_CYFRAME;
    public static final int SM_SECURE = 44;
    public static final int SM_CXEDGE = 45;
    public static final int SM_CYEDGE = 46;
    public static final int SM_CXMINSPACING = 47;
    public static final int SM_CYMINSPACING = 48;
    public static final int SM_CXSMICON = 49;
    public static final int SM_CYSMICON = 50;
    public static final int SM_CYSMCAPTION = 51;
    public static final int SM_CXSMSIZE = 52;
    public static final int SM_CYSMSIZE = 53;
    public static final int SM_CXMENUSIZE = 54;
    public static final int SM_CYMENUSIZE = 55;
    public static final int SM_ARRANGE = 56;
    public static final int SM_CXMINIMIZED = 57;
    public static final int SM_CYMINIMIZED = 58;
    public static final int SM_CXMAXTRACK = 59;
    public static final int SM_CYMAXTRACK = 60;
    public static final int SM_CXMAXIMIZED = 61;
    public static final int SM_CYMAXIMIZED = 62;
    public static final int SM_NETWORK = 63;
    public static final int SM_CLEANBOOT = 67;
    public static final int SM_CXDRAG = 68;
    public static final int SM_CYDRAG = 69;
    public static final int SM_SHOWSOUNDS = 70;
    public static final int SM_CXMENUCHECK = 71;
    public static final int SM_CYMENUCHECK = 72;
    public static final int SM_SLOWMACHINE = 73;
    public static final int SM_MIDEASTENABLED = 74;
    public static final int SM_MOUSEWHEELPRESENT = 75;
    public static final int SM_XVIRTUALSCREEN = 76;
    public static final int SM_YVIRTUALSCREEN = 77;
    public static final int SM_CXVIRTUALSCREEN = 78;
    public static final int SM_CYVIRTUALSCREEN = 79;
    public static final int SM_CMONITORS = 80;
    public static final int SM_SAMEDISPLAYFORMAT = 81;
    public static final int SM_IMMENABLED = 82;
    public static final int SM_CXFOCUSBORDER = 83;
    public static final int SM_CYFOCUSBORDER = 84;
    public static final int SM_TABLETPC = 86;
    public static final int SM_MEDIACENTER = 87;
    public static final int SM_STARTER = 88;
    public static final int SM_SERVERR2 = 89;
    public static final int SM_CMETRICS = 90;
    public static final int SM_MOUSEHORIZONTALWHEELPRESENT = 91;
    public static final int SM_REMOTESESSION = 0x1000;
    public static final int SM_SHUTTINGDOWN = 0x2000;
    public static final int SM_REMOTECONTROL = 0x2001;
    public static final int SM_CARETBLINKINGENABLED = 0x2002;
    /* Offsets for GetWindowLongPtr() and SetWindowLongPtr() */
    public static final int GWLP_USERDATA = -21;
    public static final int GWLP_ID = -12;
    public static final int GWLP_HWNDPARENT = -8;
    public static final int GWLP_HINSTANCE = -6;
    public static final int GWLP_WNDPROC = -4;
    public static final int DWLP_MSGRESULT = 0;
    public static final int DWLP_DLGPROC = DWLP_MSGRESULT + 4;
    public static final int DWLP_USER = DWLP_DLGPROC + 4;
    public static final int GWL_WNDPROC = -4;
    public static final int GWL_HINSTANCE = -6;
    public static final int GWL_HWNDPARENT = -8;
    public static final int GWL_STYLE = -16;
    public static final int GWL_EXSTYLE = -20;
    public static final int GWL_USERDATA = -21;
    public static final int GWL_ID = -12;
    public static final int GW_HWNDFIRST = 0;
    public static final int GW_HWNDLAST = 1;
    public static final int GW_HWNDNEXT = 2;
    public static final int GW_HWNDPREV = 3;
    public static final int GW_OWNER = 4;
    public static final int GW_CHILD = 5;
    public static final int GA_MIC = 1;
    public static final int GA_PARENT = 1;
    public static final int GA_ROOT = 2;
    public static final int GA_ROOTOWNER = 3;
    public static final int GA_MAC = 4;
    public static final int WA_INACTIVE = 0;
    public static final int WA_ACTIVE = 1;
    public static final int WA_CLICKACTIVE = 2;
    public static final int WH_MIN = -1;
    public static final int WH_MSGFILTER = -1;
    public static final int WH_JOURNALRECORD = 0;
    public static final int WH_JOURNALPLAYBACK = 1;
    public static final int WH_KEYBOARD = 2;
    public static final int WH_GETMESSAGE = 3;
    public static final int WH_CALLWNDPROC = 4;
    public static final int WH_CBT = 5;
    public static final int WH_SYSMSGFILTER = 6;
    public static final int WH_MOUSE = 7;
    public static final int WH_HARDWARE = 8;
    public static final int WH_DEBUG = 9;
    public static final int WH_SHELL = 10;
    public static final int WH_FOREGROUNDIDLE = 11;
    public static final int WH_CALLWNDPROCRET = 12;
    public static final int WH_KEYBOARD_LL = 13;
    public static final int WH_MOUSE_LL = 14;
    public static final int WH_MAX = 14;
    public static final int HCBT_MOVESIZE = 0;
    public static final int HCBT_MINMAX = 1;
    public static final int HCBT_QS = 2;
    public static final int HCBT_CREATEWND = 3;
    public static final int HCBT_DESTROYWND = 4;
    public static final int HCBT_ACTIVATE = 5;
    public static final int HCBT_CLICKSKIPPED = 6;
    public static final int HCBT_KEYSKIPPED = 7;
    public static final int HCBT_SYSCOMMAND = 8;
    public static final int HCBT_SETFOCUS = 9;
    public static final int HC_ACTION = 0;
    public static final int HC_GETNEXT = 1;
    public static final int HC_SKIP = 2;
    public static final int HC_NOREMOVE = 3;
    public static final int HC_NOREM = HC_NOREMOVE;
    public static final int HC_SYSMODALON = 4;
    public static final int HC_SYSMODALOFF = 5;
    public static final int HWND_DESKTOP = 0;
    public static final int HWND_BROADCAST = 0xffff;
    /* SetWindowPos() and WINDOWPOS flags */
    public static final int SWP_NOSIZE = 0x0001;
    public static final int SWP_NOMOVE = 0x0002;
    public static final int SWP_NOZORDER = 0x0004;
    public static final int SWP_NOREDRAW = 0x0008;
    public static final int SWP_NOACTIVATE = 0x0010;
    public static final int SWP_FRAMECHANGED = 0x0020; /* The frame changed: send WM_NCCALCSIZE */
    public static final int SWP_SHOWWINDOW = 0x0040;
    public static final int SWP_HIDEWINDOW = 0x0080;
    public static final int SWP_NOCOPYBITS = 0x0100;
    public static final int SWP_NOOWNERZORDER = 0x0200; /* Don't do owner Z ordering */
    public static final int SWP_DRAWFRAME = SWP_FRAMECHANGED;
    public static final int SWP_NOREPOSITION = SWP_NOOWNERZORDER;
    public static final int SWP_NOSENDCHANGING = 0x0400;
    public static final int SWP_DEFERERASE = 0x2000;
    public static final int SWP_ASYNCWINDOWPOS = 0x4000;
    /* undocumented SWP flags - from SDK 3.1 */
    public static final int SWP_NOCLIENTSIZE = 0x0800;
    public static final int SWP_NOCLIENTMOVE = 0x1000;
    public static final int SWP_STATECHANGED = 0x8000;
    /* SetWindowPos() hwndInsertAfter field values */
    public static final int HWND_TOP = 0;
    public static final int HWND_BOTTOM = 1;
    public static final int HWND_TOPMOST = -1;
    public static final int HWND_NOTOPMOST = -2;
    public static final int HWND_MESSAGE = -3;
    /* WM_SIZE message wParam values */
    public static final int SIZE_RESTORED = 0;
    public static final int SIZE_MINIMIZED = 1;
    public static final int SIZE_MAXIMIZED = 2;
    public static final int SIZE_MAXSHOW = 3;
    public static final int SIZE_MAXHIDE = 4;
    public static final int SIZENORMAL = SIZE_RESTORED;
    public static final int SIZEICONIC = SIZE_MINIMIZED;
    public static final int SIZEFULLSCREEN = SIZE_MAXIMIZED;
    public static final int SIZEZOOMSHOW = SIZE_MAXSHOW;
    public static final int SIZEZOOMHIDE = SIZE_MAXHIDE;
    /* Shell hook values */
    public static final int HSHELL_WINDOWCREATED = 1;
    public static final int HSHELL_WINDOWDESTROYED = 2;
    public static final int HSHELL_ACTIVATESHELLWINDOW = 3;
    public static final int HSHELL_WINDOWACTIVATED = 4;
    public static final int HSHELL_GETMINRECT = 5;
    public static final int HSHELL_REDRAW = 6;
    public static final int HSHELL_TASKMAN = 7;
    public static final int HSHELL_LANGUAGE = 8;
    public static final int HSHELL_SYSMENU = 9;
    public static final int HSHELL_ENDTASK = 10;
    public static final int HSHELL_ACCESSIBILITYSTATE = 11;
    public static final int HSHELL_APPCOMMAND = 12;
    public static final int HSHELL_WINDOWREPLACED = 13;
    public static final int HSHELL_WINDOWREPLACING = 14;
    /*** ShowWindow() codes ***/
    public static final int SW_HIDE = 0;
    public static final int SW_SHOWNORMAL = 1;
    public static final int SW_NORMAL = SW_SHOWNORMAL;
    public static final int SW_SHOWMINIMIZED = 2;
    public static final int SW_SHOWMAXIMIZED = 3;
    public static final int SW_MAXIMIZE = SW_SHOWMAXIMIZED;
    public static final int SW_SHOWNOACTIVATE = 4;
    public static final int SW_SHOW = 5;
    public static final int SW_MINIMIZE = 6;
    public static final int SW_SHOWMINNOACTIVE = 7;
    public static final int SW_SHOWNA = 8;
    public static final int SW_RESTORE = 9;
    public static final int SW_SHOWDEFAULT = 10;
    public static final int SW_FORCEMINIMIZE = 11;
    public static final int SW_MAX = 11;
    public static final int SW_NORMALNA = 0xCC; /* Undocumented. Flag in MinMaximize */
    public static final int IDOK = 1;
    public static final int IDCANCEL = 2;
    public static final int IDABORT = 3;
    public static final int IDRETRY = 4;
    public static final int IDIGNORE = 5;
    public static final int IDYES = 6;
    public static final int IDNO = 7;
    public static final int IDCLOSE = 8;
    public static final int IDHELP = 9;
    public static final int IDTRYAGAIN = 10;
    public static final int IDCONTINUE = 11;
    public static final int DLGC_WANTARROWS = 0x0001;
    public static final int DLGC_WANTTAB = 0x0002;
    public static final int DLGC_WANTALLKEYS = 0x0004;
    public static final int DLGC_WANTMESSAGE = 0x0004;
    public static final int DLGC_HASSETSEL = 0x0008;
    public static final int DLGC_DEFPUSHBUTTON = 0x0010;
    public static final int DLGC_UNDEFPUSHBUTTON = 0x0020;
    public static final int DLGC_RADIOBUTTON = 0x0040;
    public static final int DLGC_WANTCHARS = 0x0080;
    public static final int DLGC_STATIC = 0x0100;
    public static final int DLGC_BUTTON = 0x2000;
    public static final int CB_GETEDITSEL = 0x0140;
    public static final int CB_LIMITTEXT = 0x0141;
    public static final int CB_SETEDITSEL = 0x0142;
    public static final int CB_ADDSTRING = 0x0143;
    public static final int CB_DELETESTRING = 0x0144;
    public static final int CB_DIR = 0x0145;
    public static final int CB_GETCOUNT = 0x0146;
    public static final int CB_GETCURSEL = 0x0147;
    public static final int CB_GETLBTEXT = 0x0148;
    public static final int CB_GETLBTEXTLEN = 0x0149;
    public static final int CB_INSERTSTRING = 0x014a;
    public static final int CB_RESETCONTENT = 0x014b;
    public static final int CB_FINDSTRING = 0x014c;
    public static final int CB_SELECTSTRING = 0x014d;
    public static final int CB_SETCURSEL = 0x014e;
    public static final int CB_SHOWDROPDOWN = 0x014f;
    public static final int CB_GETITEMDATA = 0x0150;
    public static final int CB_SETITEMDATA = 0x0151;
    public static final int CB_GETDROPPEDCONTROLRECT = 0x0152;
    public static final int CB_SETITEMHEIGHT = 0x0153;
    public static final int CB_GETITEMHEIGHT = 0x0154;
    public static final int CB_SETEXTENDEDUI = 0x0155;
    public static final int CB_GETEXTENDEDUI = 0x0156;
    public static final int CB_GETDROPPEDSTATE = 0x0157;
    public static final int CB_FINDSTRINGEXACT = 0x0158;
    public static final int CB_SETLOCALE = 0x0159;
    public static final int CB_GETLOCALE = 0x015a;
    public static final int CB_GETTOPINDEX = 0x015b;
    public static final int CB_SETTOPINDEX = 0x015c;
    public static final int CB_GETHORIZONTALEXTENT = 0x015d;
    public static final int CB_SETHORIZONTALEXTENT = 0x015e;
    public static final int CB_GETDROPPEDWIDTH = 0x015f;
    public static final int CB_SETDROPPEDWIDTH = 0x0160;
    public static final int CB_INITSTORAGE = 0x0161;
    public static final int CB_MULTIPLEADDSTRING = 0x0163;
    public static final int CB_GETCOMBOBOXINFO = 0x0164;
    public static final int CB_MSGMAX = 0x0165;
    /*** Button control styles ***/
    public static final int BS_PUSHBUTTON = 0x00000000;
    public static final int BS_DEFPUSHBUTTON = 0x00000001;
    public static final int BS_CHECKBOX = 0x00000002;
    public static final int BS_AUTOCHECKBOX = 0x00000003;
    public static final int BS_RADIOBUTTON = 0x00000004;
    public static final int BS_3STATE = 0x00000005;
    public static final int BS_AUTO3STATE = 0x00000006;
    public static final int BS_GROUPBOX = 0x00000007;
    public static final int BS_USERBUTTON = 0x00000008;
    public static final int BS_AUTORADIOBUTTON = 0x00000009;
    public static final int BS_PUSHBOX = 0x0000000A;
    public static final int BS_OWNERDRAW = 0x0000000B;
    public static final int BS_TYPEMASK = 0x0000000F;
    public static final int BS_LEFTTEXT = 0x00000020;
    public static final int BS_RIGHTBUTTON = BS_LEFTTEXT;
    public static final int BS_TEXT = 0x00000000;
    public static final int BS_ICON = 0x00000040;
    public static final int BS_BITMAP = 0x00000080;
    public static final int BS_LEFT = 0x00000100;
    public static final int BS_RIGHT = 0x00000200;
    public static final int BS_CENTER = 0x00000300;
    public static final int BS_TOP = 0x00000400;
    public static final int BS_BOTTOM = 0x00000800;
    public static final int BS_VCENTER = 0x00000C00;
    public static final int BS_PUSHLIKE = 0x00001000;
    public static final int BS_MULTILINE = 0x00002000;
    public static final int BS_NOTIFY = 0x00004000;
    public static final int BS_FLAT = 0x00008000;
    /*** Button notification codes ***/
    public static final int BN_CLICKED = 0;
    public static final int BN_PAINT = 1;
    public static final int BN_HILITE = 2;
    public static final int BN_UNHILITE = 3;
    public static final int BN_DISABLE = 4;
    public static final int BN_DOUBLECLICKED = 5;
    public static final int BN_PUSHED = BN_HILITE;
    public static final int BN_UNPUSHED = BN_UNHILITE;
    public static final int BN_DBLCLK = BN_DOUBLECLICKED;
    public static final int BN_SETFOCUS = 6;
    public static final int BN_KILLFOCUS = 7;
    /*** Win32 button control messages ***/
    public static final int BM_GETCHECK = 0x00f0;
    public static final int BM_SETCHECK = 0x00f1;
    public static final int BM_GETSTATE = 0x00f2;
    public static final int BM_SETSTATE = 0x00f3;
    public static final int BM_SETSTYLE = 0x00f4;
    public static final int BM_CLICK = 0x00f5;
    public static final int BM_GETIMAGE = 0x00f6;
    public static final int BM_SETIMAGE = 0x00f7;
    public static final int BM_SETDONTCLICK = 0x00f8;
    /* Button states */
    public static final int BST_UNCHECKED = 0x0000;
    public static final int BST_CHECKED = 0x0001;
    public static final int BST_INDETERMINATE = 0x0002;
    public static final int BST_PUSHED = 0x0004;
    public static final int BST_FOCUS = 0x0008;
    public static final int EM_GETSEL = 0x00B0;
    public static final int EM_SETSEL = 0x00B1;
    public static final int EM_GETRECT = 0x00B2;
    public static final int EM_SETRECT = 0x00B3;
    public static final int EM_SETRECTNP = 0x00B4;
    public static final int EM_SCROLL = 0x00B5;
    public static final int EM_LINESCROLL = 0x00B6;
    public static final int EM_SCROLLCARET = 0x00B7;
    public static final int EM_GETMODIFY = 0x00B8;
    public static final int EM_SETMODIFY = 0x00B9;
    public static final int EM_GETLINECOUNT = 0x00BA;
    public static final int EM_LINEINDEX = 0x00BB;
    public static final int EM_SETHANDLE = 0x00BC;
    public static final int EM_GETHANDLE = 0x00BD;
    public static final int EM_GETTHUMB = 0x00BE;
    public static final int EM_LINELENGTH = 0x00C1;
    public static final int EM_REPLACESEL = 0x00C2;
    public static final int EM_GETLINE = 0x00C4;
    public static final int EM_LIMITTEXT = 0x00C5;
    public static final int EM_CANUNDO = 0x00C6;
    public static final int EM_UNDO = 0x00C7;
    public static final int EM_FMTLINES = 0x00C8;
    public static final int EM_LINEFROMCHAR = 0x00C9;
    public static final int EM_SETTABSTOPS = 0x00CB;
    public static final int EM_SETPASSWORDCHAR = 0x00CC;
    public static final int EM_EMPTYUNDOBUFFER = 0x00CD;
    public static final int EM_GETFIRSTVISIBLELINE = 0x00CE;
    public static final int EM_SETREADONLY = 0x00CF;
    public static final int EM_SETWORDBREAKPROC = 0x00D0;
    public static final int EM_GETWORDBREAKPROC = 0x00D1;
    public static final int EM_GETPASSWORDCHAR = 0x00D2;
    public static final int EM_SETMARGINS = 0x00D3;
    public static final int EM_GETMARGINS = 0x00D4;
    public static final int EM_SETLIMITTEXT = EM_LIMITTEXT;
    public static final int EM_GETLIMITTEXT = 0x00D5;
    public static final int EM_POSFROMCHAR = 0x00D6;
    public static final int EM_CHARFROMPOS = 0x00D7;
    public static final int EM_SETIMESTATUS = 0x00D8;
    public static final int EM_GETIMESTATUS = 0x00D9;
    public static final int ERROR = 0;
    public static final int NULLREGION = 1;
    public static final int SIMPLEREGION = 2;
    public static final int COMPLEXREGION = 3;
    public static final int RGN_ERROR = ERROR;
    public static final int RGN_AND = 1;
    public static final int RGN_OR = 2;
    public static final int RGN_XOR = 3;
    public static final int RGN_DIFF = 4;
    public static final int RGN_COPY = 5;
    public static final int RGN_MIN = RGN_AND;
    public static final int RGN_MAX = RGN_COPY;
    /* GetDCEx flags */
    public static final int DCX_WINDOW = 0x00000001;
    public static final int DCX_CACHE = 0x00000002;
    public static final int DCX_NORESETATTRS = 0x00000004;
    public static final int DCX_CLIPCHILDREN = 0x00000008;
    public static final int DCX_CLIPSIBLINGS = 0x00000010;
    public static final int DCX_PARENTCLIP = 0x00000020;
    public static final int DCX_EXCLUDERGN = 0x00000040;
    public static final int DCX_INTERSECTRGN = 0x00000080;
    public static final int DCX_EXCLUDEUPDATE = 0x00000100;
    public static final int DCX_INTERSECTUPDATE = 0x00000200;
    public static final int DCX_LOCKWINDOWUPDATE = 0x00000400;
    public static final int DCX_USESTYLE = 0x00010000;
    public static final int DCX_NORECOMPUTE = 0x00100000;
    public static final int DCX_VALIDATE = 0x00200000;
    public static final int GCL_MENUNAME = -8;
    public static final int GCL_HBRBACKGROUND = -10;
    public static final int GCL_HCURSOR = -12;
    public static final int GCL_HICON = -14;
    public static final int GCL_HMODULE = -16;
    public static final int GCL_WNDPROC = -24;
    public static final int GCL_HICONSM = -34;
    public static final int GCL_CBWNDEXTRA = -18;
    public static final int GCL_CBCLSEXTRA = -20;
    public static final int GCL_STYLE = -26;
    public static final int GCW_ATOM = -32;
    public static final int GCLP_MENUNAME = -8;
    public static final int GCLP_HBRBACKGROUND = -10;
    public static final int GCLP_HCURSOR = -12;
    public static final int GCLP_HICON = -14;
    public static final int GCLP_HMODULE = -16;
    public static final int GCLP_WNDPROC = -24;
    public static final int GCLP_HICONSM = -34;
    public static final int CS_VREDRAW = 0x00000001;
    public static final int CS_HREDRAW = 0x00000002;
    public static final int CS_KEYCVTWINDOW = 0x00000004; /* DDK / Win16 */
    public static final int CS_DBLCLKS = 0x00000008;
    public static final int CS_OWNDC = 0x00000020;
    public static final int CS_CLASSDC = 0x00000040;
    public static final int CS_PARENTDC = 0x00000080;
    public static final int CS_NOKEYCVT = 0x00000100; /* DDK / Win16 */
    public static final int CS_NOCLOSE = 0x00000200;
    public static final int CS_SAVEBITS = 0x00000800;
    public static final int CS_BYTEALIGNCLIENT = 0x00001000;
    public static final int CS_BYTEALIGNWINDOW = 0x00002000;
    public static final int CS_GLOBALCLASS = 0x00004000;
    public static final int CS_IME = 0x00010000;
    public static final int CS_DROPSHADOW = 0x00020000;
    public static final int COLOR_SCROLLBAR = 0;
    public static final int COLOR_BACKGROUND = 1;
    public static final int COLOR_ACTIVECAPTION = 2;
    public static final int COLOR_INACTIVECAPTION = 3;
    public static final int COLOR_MENU = 4;
    public static final int COLOR_WINDOW = 5;
    public static final int COLOR_WINDOWFRAME = 6;
    public static final int COLOR_MENUTEXT = 7;
    public static final int COLOR_WINDOWTEXT = 8;
    public static final int COLOR_CAPTIONTEXT = 9;
    public static final int COLOR_ACTIVEBORDER = 10;
    public static final int COLOR_INACTIVEBORDER = 11;
    public static final int COLOR_APPWORKSPACE = 12;
    public static final int COLOR_HIGHLIGHT = 13;
    public static final int COLOR_HIGHLIGHTTEXT = 14;
    public static final int COLOR_BTNFACE = 15;
    public static final int COLOR_BTNSHADOW = 16;
    public static final int COLOR_GRAYTEXT = 17;
    public static final int COLOR_BTNTEXT = 18;
    public static final int COLOR_INACTIVECAPTIONTEXT = 19;
    public static final int COLOR_BTNHIGHLIGHT = 20;
    /* win95 colors */
    public static final int COLOR_3DDKSHADOW = 21;
    public static final int COLOR_3DLIGHT = 22;
    public static final int COLOR_INFOTEXT = 23;
    public static final int COLOR_INFOBK = 24;
    public static final int COLOR_DESKTOP = COLOR_BACKGROUND;
    public static final int COLOR_3DFACE = COLOR_BTNFACE;
    public static final int COLOR_3DSHADOW = COLOR_BTNSHADOW;
    public static final int COLOR_3DHIGHLIGHT = COLOR_BTNHIGHLIGHT;
    public static final int COLOR_3DHILIGHT = COLOR_BTNHIGHLIGHT;
    public static final int COLOR_BTNHILIGHT = COLOR_BTNHIGHLIGHT;
    /* win98 colors */
    public static final int COLOR_ALTERNATEBTNFACE = 25; /* undocumented, constant name unknown */
    public static final int COLOR_HOTLIGHT = 26;
    public static final int COLOR_GRADIENTACTIVECAPTION = 27;
    public static final int COLOR_GRADIENTINACTIVECAPTION = 28;
    /* win2k/xp colors */
    public static final int COLOR_MENUHILIGHT = 29;
    public static final int COLOR_MENUBAR = 30;
    public static final int COLOR_MAX = COLOR_MENUBAR;
    public static final int SRCCOPY = 0xcc0020;
    public static final int SRCPAINT = 0xee0086;
    public static final int SRCAND = 0x8800c6;
    public static final int SRCINVERT = 0x660046;
    public static final int SRCERASE = 0x440328;
    public static final int NOTSRCCOPY = 0x330008;
    public static final int NOTSRCERASE = 0x1100a6;
    public static final int MERGECOPY = 0xc000ca;
    public static final int MERGEPAINT = 0xbb0226;
    public static final int PATCOPY = 0xf00021;
    public static final int PATPAINT = 0xfb0a09;
    public static final int PATINVERT = 0x5a0049;
    public static final int DSTINVERT = 0x550009;
    public static final int BLACKNESS = 0x000042;
    public static final int WHITENESS = 0xff0062;
    /* WINDOWPLACEMENT flags */
    public static final int WPF_SETMINPOSITION = 0x0001;
    public static final int WPF_RESTORETOMAXIMIZED = 0x0002;
    public static final int WAIT_FAILED = 0xffffffff;
    public static final int IDC_ARROW = 32512;
    public static final int IDC_IBEAM = 32513;
    public static final int IDC_WAIT = 32514;
    public static final int IDC_CROSS = 32515;
    public static final int IDC_UPARROW = 32516;
    public static final int IDC_SIZE = 32640;
    public static final int IDC_ICON = 32641;
    public static final int IDC_SIZENWSE = 32642;
    public static final int IDC_SIZENESW = 32643;
    public static final int IDC_SIZEWE = 32644;
    public static final int IDC_SIZENS = 32645;
    public static final int IDC_SIZEALL = 32646;
    public static final int IDC_NO = 32648;
    public static final int IDC_HAND = 32649;
    public static final int IDC_APPSTARTING = 32650;
    public static final int IDC_HELP = 32651;
    public static final int SYSPAL_ERROR = 0;
    public static final int SYSPAL_STATIC = 1;
    public static final int SYSPAL_NOSTATIC = 2;
    public static final int SYSPAL_NOSTATIC256 = 3;
    /*** Dialog styles ***/
    public static final int DS_ABSALIGN = 0x00000001;
    public static final int DS_SYSMODAL = 0x00000002;
    public static final int DS_3DLOOK = 0x00000004; /* win95 */
    public static final int DS_FIXEDSYS = 0x00000008; /* win95 */
    public static final int DS_NOFAILCREATE = 0x00000010; /* win95 */
    public static final int DS_LOCALEDIT = 0x00000020;
    public static final int DS_SETFONT = 0x00000040;
    public static final int DS_MODALFRAME = 0x00000080;
    public static final int DS_NOIDLEMSG = 0x00000100;
    public static final int DS_SETFOREGROUND = 0x00000200; /* win95 */
    public static final int DS_CONTROL = 0x00000400; /* win95 */
    public static final int DS_CENTER = 0x00000800; /* win95 */
    public static final int DS_CENTERMOUSE = 0x00001000; /* win95 */
    public static final int DS_CONTEXTHELP = 0x00002000; /* win95 */
    public static final int DS_USEPIXELS = 0x00008000;
    public static final int DS_SHELLFONT = DS_SETFONT | DS_FIXEDSYS;
    /*** Static Control Styles ***/
    public static final int SS_LEFT = 0x00000000;
    public static final int SS_CENTER = 0x00000001;
    public static final int SS_RIGHT = 0x00000002;
    public static final int SS_ICON = 0x00000003;
    public static final int SS_BLACKRECT = 0x00000004;
    public static final int SS_GRAYRECT = 0x00000005;
    public static final int SS_WHITERECT = 0x00000006;
    public static final int SS_BLACKFRAME = 0x00000007;
    public static final int SS_GRAYFRAME = 0x00000008;
    public static final int SS_WHITEFRAME = 0x00000009;
    public static final int SS_USERITEM = 0x0000000A;
    public static final int SS_SIMPLE = 0x0000000B;
    public static final int SS_LEFTNOWORDWRAP = 0x0000000C;
    public static final int SS_OWNERDRAW = 0x0000000D;
    public static final int SS_BITMAP = 0x0000000E;
    public static final int SS_ENHMETAFILE = 0x0000000F;
    public static final int SS_ETCHEDHORZ = 0x00000010;
    public static final int SS_ETCHEDVERT = 0x00000011;
    public static final int SS_ETCHEDFRAME = 0x00000012;
    public static final int SS_TYPEMASK = 0x0000001F;
    public static final int SS_REALSIZECONTROL = 0x00000040;
    public static final int SS_NOPREFIX = 0x00000080;
    public static final int SS_NOTIFY = 0x00000100;
    public static final int SS_CENTERIMAGE = 0x00000200;
    public static final int SS_RIGHTJUST = 0x00000400;
    public static final int SS_REALSIZEIMAGE = 0x00000800;
    public static final int SS_SUNKEN = 0x00001000;
    public static final int SS_EDITCONTROL = 0x00002000;
    public static final int SS_ENDELLIPSIS = 0x00004000;
    public static final int SS_PATHELLIPSIS = 0x00008000;
    public static final int SS_WORDELLIPSIS = 0x0000C000;
    public static final int SS_ELLIPSISMASK = SS_WORDELLIPSIS;
    public static final int DRIVERVERSION = 0;
    public static final int TECHNOLOGY = 2;
    public static final int HORZSIZE = 4;
    public static final int VERTSIZE = 6;
    public static final int HORZRES = 8;
    public static final int VERTRES = 10;
    public static final int BITSPIXEL = 12;
    public static final int PLANES = 14;
    public static final int NUMBRUSHES = 16;
    public static final int NUMPENS = 18;
    public static final int NUMMARKERS = 20;
    public static final int NUMFONTS = 22;
    public static final int NUMCOLORS = 24;
    public static final int PDEVICESIZE = 26;
    public static final int CURVECAPS = 28;
    public static final int LINECAPS = 30;
    public static final int POLYGONALCAPS = 32;
    public static final int TEXTCAPS = 34;
    public static final int CLIPCAPS = 36;
    public static final int RASTERCAPS = 38;
    public static final int ASPECTX = 40;
    public static final int ASPECTY = 42;
    public static final int ASPECTXY = 44;
    public static final int LOGPIXELSX = 88;
    public static final int LOGPIXELSY = 90;
    public static final int CAPS1 = 94;
    public static final int SIZEPALETTE = 104;
    public static final int NUMRESERVED = 106;
    public static final int COLORRES = 108;
    public static final int CW_USEDEFAULT = 0x80000000;
    public static final int CTLCOLOR_MSGBOX = 0;
    public static final int CTLCOLOR_EDIT = 1;
    public static final int CTLCOLOR_LISTBOX = 2;
    public static final int CTLCOLOR_BTN = 3;
    public static final int CTLCOLOR_DLG = 4;
    public static final int CTLCOLOR_SCROLLBAR = 5;
    public static final int CTLCOLOR_STATIC = 6;
    /* types of LoadImage */
    public static final int IMAGE_BITMAP = 0;
    public static final int IMAGE_ICON = 1;
    public static final int IMAGE_CURSOR = 2;
    public static final int IMAGE_ENHMETAFILE = 3;
    public static final int LR_DEFAULTCOLOR = 0x0000;
    public static final int LR_MONOCHROME = 0x0001;
    public static final int LR_COLOR = 0x0002;
    public static final int LR_COPYRETURNORG = 0x0004;
    public static final int LR_COPYDELETEORG = 0x0008;
    public static final int LR_LOADFROMFILE = 0x0010;
    public static final int LR_LOADTRANSPARENT = 0x0020;
    public static final int LR_DEFAULTSIZE = 0x0040;
    public static final int LR_VGA_COLOR = 0x0080;
    public static final int LR_LOADMAP3DCOLORS = 0x1000;
    public static final int LR_CREATEDIBSECTION = 0x2000;
    public static final int LR_COPYFROMRESOURCE = 0x4000;
    public static final int LR_SHARED = 0x8000;
    /* Static Control Messages */
    public static final int STM_SETICON = 0x0170;
    public static final int STM_GETICON = 0x0171;
    public static final int STM_SETIMAGE = 0x0172;
    public static final int STM_GETIMAGE = 0x0173;
    public static final int STM_MSGMAX = 0x0174;
    public static final int STN_CLICKED = 0;
    public static final int STN_DBLCLK = 1;
    public static final int STN_ENABLE = 2;
    public static final int STN_DISABLE = 3;
    /* RedrawWindow() flags */
    public static final int RDW_INVALIDATE = 0x0001;
    public static final int RDW_INTERNALPAINT = 0x0002;
    public static final int RDW_ERASE = 0x0004;
    public static final int RDW_VALIDATE = 0x0008;
    public static final int RDW_NOINTERNALPAINT = 0x0010;
    public static final int RDW_NOERASE = 0x0020;
    public static final int RDW_NOCHILDREN = 0x0040;
    public static final int RDW_ALLCHILDREN = 0x0080;
    public static final int RDW_UPDATENOW = 0x0100;
    public static final int RDW_ERASENOW = 0x0200;
    public static final int RDW_FRAME = 0x0400;
    public static final int RDW_NOFRAME = 0x0800;
    /* Bit flags for DRAWITEMSTRUCT.CtlType */
    public static final int ODT_MENU = 1;
    public static final int ODT_LISTBOX = 2;
    public static final int ODT_COMBOBOX = 3;
    public static final int ODT_BUTTON = 4;
    public static final int ODT_STATIC = 5;
    /* Bit flags for DRAWITEMSTRUCT.itemAction */
    public static final int ODA_DRAWENTIRE = 0x1;
    public static final int ODA_SELECT = 0x2;
    public static final int ODA_FOCUS = 0x4;
    /* Bit flags for DRAWITEMSTRUCT.itemState */
    public static final int ODS_SELECTED = 0x0001; /* Selected */
    public static final int ODS_GRAYED = 0x0002; /* Grayed (Menus only) */
    public static final int ODS_DISABLED = 0x0004; /* Disabled */
    public static final int ODS_CHECKED = 0x0008; /* Checked (Menus only) */
    public static final int ODS_FOCUS = 0x0010; /* Has focus */
    public static final int ODS_DEFAULT = 0x0020; /* Default */
    public static final int ODS_HOTLIGHT = 0x0040; /* Highlighted when under mouse */
    public static final int ODS_INACTIVE = 0x0080; /* Inactive */
    public static final int ODS_NOACCEL = 0x0100; /* No keyboard accelerator */
    public static final int ODS_NOFOCUSRECT = 0x0200; /* No focus rectangle */
    public static final int ODS_COMBOBOXEDIT = 0x1000; /* Edit of a combo box */
    public static final int DT_TOP = 0x00000000;
    public static final int DT_LEFT = 0x00000000;
    public static final int DT_CENTER = 0x00000001;
    public static final int DT_RIGHT = 0x00000002;
    public static final int DT_VCENTER = 0x00000004;
    public static final int DT_BOTTOM = 0x00000008;
    public static final int DT_WORDBREAK = 0x00000010;
    public static final int DT_SINGLELINE = 0x00000020;
    public static final int DT_EXPANDTABS = 0x00000040;
    public static final int DT_TABSTOP = 0x00000080;
    public static final int DT_NOCLIP = 0x00000100;
    public static final int DT_EXTERNALLEADING = 0x00000200;
    public static final int DT_CALCRECT = 0x00000400;
    public static final int DT_NOPREFIX = 0x00000800;
    public static final int DT_INTERNAL = 0x00001000;
    public static final int DT_EDITCONTROL = 0x00002000;
    public static final int DT_PATH_ELLIPSIS = 0x00004000;
    public static final int DT_END_ELLIPSIS = 0x00008000;
    public static final int DT_MODIFYSTRING = 0x00010000;
    public static final int DT_RTLREADING = 0x00020000;
    public static final int DT_WORD_ELLIPSIS = 0x00040000;
    /* ExtTextOut() parameters */
    public static final int ETO_GRAYED = 0x0001;
    public static final int ETO_OPAQUE = 0x0002;
    public static final int ETO_CLIPPED = 0x0004;
    public static final int ETO_GLYPH_INDEX = 0x0010;
    public static final int ETO_RTLREADING = 0x0080;
    public static final int ETO_NUMERICSLOCAL = 0x0400;
    public static final int ETO_NUMERICSLATIN = 0x0800;
    public static final int ETO_IGNORELANGUAGE = 0x1000;
    public static final int ETO_PDY = 0x2000;
    /* Flags for DrawIconEx.  */
    public static final int DI_MASK = 0x0001;
    public static final int DI_IMAGE = 0x0002;
    public static final int DI_NORMAL = DI_MASK | DI_IMAGE;
    public static final int DI_COMPAT = 0x0004;
    public static final int DI_DEFAULTSIZE = 0x0008;
    public static final int DI_NOMIRROR = 0x0010;
    /* DrawEdge() flags */
    public static final int BDR_RAISEDOUTER = 0x0001;
    public static final int BDR_SUNKENOUTER = 0x0002;
    public static final int BDR_RAISEDINNER = 0x0004;
    public static final int BDR_SUNKENINNER = 0x0008;
    public static final int BDR_OUTER = 0x0003;
    public static final int BDR_INNER = 0x000c;
    public static final int BDR_RAISED = 0x0005;
    public static final int BDR_SUNKEN = 0x000a;
    public static final int EDGE_RAISED = BDR_RAISEDOUTER | BDR_RAISEDINNER;
    public static final int EDGE_SUNKEN = BDR_SUNKENOUTER | BDR_SUNKENINNER;
    public static final int EDGE_ETCHED = BDR_SUNKENOUTER | BDR_RAISEDINNER;
    public static final int EDGE_BUMP = BDR_RAISEDOUTER | BDR_SUNKENINNER;
    /* border flags */
    public static final int BF_LEFT = 0x0001;
    public static final int BF_TOP = 0x0002;
    public static final int BF_RIGHT = 0x0004;
    public static final int BF_BOTTOM = 0x0008;
    public static final int BF_DIAGONAL = 0x0010;
    public static final int BF_MIDDLE = 0x0800; /* Fill in the middle */
    public static final int BF_SOFT = 0x1000; /* For softer buttons */
    public static final int BF_ADJUST = 0x2000; /* Calculate the space left over */
    public static final int BF_FLAT = 0x4000; /* For flat rather than 3D borders */
    public static final int BF_MONO = 0x8000; /* For monochrome borders */
    public static final int BF_TOPLEFT = BF_TOP | BF_LEFT;
    public static final int BF_TOPRIGHT = BF_TOP | BF_RIGHT;
    public static final int BF_BOTTOMLEFT = BF_BOTTOM | BF_LEFT;
    public static final int BF_BOTTOMRIGHT = BF_BOTTOM | BF_RIGHT;
    public static final int BF_RECT = BF_LEFT | BF_TOP | BF_RIGHT | BF_BOTTOM;
    public static final int BF_DIAGONAL_ENDTOPRIGHT = BF_DIAGONAL | BF_TOP | BF_RIGHT;
    public static final int BF_DIAGONAL_ENDTOPLEFT = BF_DIAGONAL | BF_TOP | BF_LEFT;
    public static final int BF_DIAGONAL_ENDBOTTOMLEFT = BF_DIAGONAL | BF_BOTTOM | BF_LEFT;
    public static final int BF_DIAGONAL_ENDBOTTOMRIGHT = BF_DIAGONAL | BF_BOTTOM | BF_RIGHT;
    /*** OEM Resource Ordinal Numbers ***/
    public static final int OBM_RDRVERT = 32559;
    public static final int OBM_RDRHORZ = 32660;
    public static final int OBM_RDR2DIM = 32661;
    public static final int OBM_TRTYPE = 32732; /* FIXME: Wine-only */
    public static final int OBM_LFARROWI = 32734;
    public static final int OBM_RGARROWI = 32735;
    public static final int OBM_DNARROWI = 32736;
    public static final int OBM_UPARROWI = 32737;
    public static final int OBM_COMBO = 32738;
    public static final int OBM_MNARROW = 32739;
    public static final int OBM_LFARROWD = 32740;
    public static final int OBM_RGARROWD = 32741;
    public static final int OBM_DNARROWD = 32742;
    public static final int OBM_UPARROWD = 32743;
    public static final int OBM_RESTORED = 32744;
    public static final int OBM_ZOOMD = 32745;
    public static final int OBM_REDUCED = 32746;
    public static final int OBM_RESTORE = 32747;
    public static final int OBM_ZOOM = 32748;
    public static final int OBM_REDUCE = 32749;
    public static final int OBM_LFARROW = 32750;
    public static final int OBM_RGARROW = 32751;
    public static final int OBM_DNARROW = 32752;
    public static final int OBM_UPARROW = 32753;
    public static final int OBM_CLOSE = 32754;
    public static final int OBM_OLD_RESTORE = 32755;
    public static final int OBM_OLD_ZOOM = 32756;
    public static final int OBM_OLD_REDUCE = 32757;
    public static final int OBM_BTNCORNERS = 32758;
    public static final int OBM_CHECKBOXES = 32759;
    public static final int OBM_CHECK = 32760;
    public static final int OBM_BTSIZE = 32761;
    public static final int OBM_OLD_LFARROW = 32762;
    public static final int OBM_OLD_RGARROW = 32763;
    public static final int OBM_OLD_DNARROW = 32764;
    public static final int OBM_OLD_UPARROW = 32765;
    public static final int OBM_SIZE = 32766;
    public static final int OBM_OLD_CLOSE = 32767;
    public static final int OCR_NORMAL = 32512;
    public static final int OCR_IBEAM = 32513;
    public static final int OCR_WAIT = 32514;
    public static final int OCR_CROSS = 32515;
    public static final int OCR_UP = 32516;
    public static final int OCR_SIZE = 32640;
    public static final int OCR_ICON = 32641;
    public static final int OCR_SIZENWSE = 32642;
    public static final int OCR_SIZENESW = 32643;
    public static final int OCR_SIZEWE = 32644;
    public static final int OCR_SIZENS = 32645;
    public static final int OCR_SIZEALL = 32646;
    public static final int OCR_ICOCUR = 32647;
    public static final int OCR_NO = 32648;
    public static final int OCR_HAND = 32649;
    public static final int OCR_APPSTARTING = 32650;
    public static final int OCR_HELP = 32651; /* DDK / Win16 */
    public static final int OCR_RDRVERT = 32652; /* DDK / Win16 */
    public static final int OCR_RDRHORZ = 32653; /* DDK / Win16 */
    public static final int OCR_DRAGOBJECT = OCR_RDRHORZ; /* FIXME: Wine-only */
    public static final int OCR_RDR2DIM = 32654; /* DDK / Win16 */
    public static final int OCR_RDRNORTH = 32655; /* DDK / Win16 */
    public static final int OCR_RDRSOUTH = 32656; /* DDK / Win16 */
    public static final int OCR_RDRWEST = 32657; /* DDK / Win16 */
    public static final int OCR_RDREAST = 32658; /* DDK / Win16 */
    public static final int OCR_RDRNORTHWEST = 32659; /* DDK / Win16 */
    public static final int OCR_RDRNORTHEAST = 32660; /* DDK / Win16 */
    public static final int OCR_RDRSOUTHWEST = 32661; /* DDK / Win16 */
    public static final int OCR_RDRSOUTHEAST = 32662; /* DDK / Win16 */
    public static final int OIC_SAMPLE = 32512;
    public static final int OIC_HAND = 32513;
    public static final int OIC_ERROR = OIC_HAND;
    public static final int OIC_QUES = 32514;
    public static final int OIC_BANG = 32515;
    public static final int OIC_WARNING = OIC_BANG;
    public static final int OIC_NOTE = 32516;
    public static final int OIC_INFORMATION = OIC_NOTE;
    public static final int OIC_WINLOGO = 32517;
    public static final int OIC_SHIELD = 32518;
    public static final int TRANSPARENT = 1;
    public static final int OPAQUE = 2;
    /*** Virtual key codes ***/
    public static final int VK_LBUTTON = 0x01;
    public static final int VK_RBUTTON = 0x02;
    public static final int VK_CANCEL = 0x03;
    public static final int VK_MBUTTON = 0x04;
    public static final int VK_XBUTTON1 = 0x05;
    public static final int VK_XBUTTON2 = 0x06;
    /*                             0x07  Undefined */
    public static final int VK_BACK = 0x08;
    public static final int VK_TAB = 0x09;
    /*                             0x0A-0x0B  Undefined */
    public static final int VK_CLEAR = 0x0C;
    public static final int VK_RETURN = 0x0D;
    /*                             0x0E-0x0F  Undefined */
    public static final int VK_SHIFT = 0x10;
    public static final int VK_CONTROL = 0x11;
    public static final int VK_MENU = 0x12;
    public static final int VK_PAUSE = 0x13;
    public static final int VK_CAPITAL = 0x14;
    public static final int VK_KANA = 0x15;
    public static final int VK_HANGEUL = VK_KANA;
    public static final int VK_HANGUL = VK_KANA;
    public static final int VK_JUNJA = 0x17;
    public static final int VK_FINAL = 0x18;
    public static final int VK_HANJA = 0x19;
    public static final int VK_KANJI = VK_HANJA;
    /*                             0x1A       Undefined */
    public static final int VK_ESCAPE = 0x1B;
    public static final int VK_CONVERT = 0x1C;
    public static final int VK_NONCONVERT = 0x1D;
    public static final int VK_ACCEPT = 0x1E;
    public static final int VK_MODECHANGE = 0x1F;
    public static final int VK_SPACE = 0x20;
    public static final int VK_PRIOR = 0x21;
    public static final int VK_NEXT = 0x22;
    public static final int VK_END = 0x23;
    public static final int VK_HOME = 0x24;
    public static final int VK_LEFT = 0x25;
    public static final int VK_UP = 0x26;
    public static final int VK_RIGHT = 0x27;
    public static final int VK_DOWN = 0x28;
    public static final int VK_SELECT = 0x29;
    public static final int VK_PRINT = 0x2A; /* OEM specific in Windows 3.1 SDK */
    public static final int VK_EXECUTE = 0x2B;
    public static final int VK_SNAPSHOT = 0x2C;
    public static final int VK_INSERT = 0x2D;
    public static final int VK_DELETE = 0x2E;
    public static final int VK_HELP = 0x2F;
    /* VK_0 - VK-9                 0x30-0x39  Use ASCII instead */
    /*                             0x3A-0x40  Undefined */
    /* VK_A - VK_Z                 0x41-0x5A  Use ASCII instead */
    public static final int VK_LWIN = 0x5B;
    public static final int VK_RWIN = 0x5C;
    public static final int VK_APPS = 0x5D;
    /*                             0x5E Unassigned */
    public static final int VK_SLEEP = 0x5F;
    public static final int VK_NUMPAD0 = 0x60;
    public static final int VK_NUMPAD1 = 0x61;
    public static final int VK_NUMPAD2 = 0x62;
    public static final int VK_NUMPAD3 = 0x63;
    public static final int VK_NUMPAD4 = 0x64;
    public static final int VK_NUMPAD5 = 0x65;
    public static final int VK_NUMPAD6 = 0x66;
    public static final int VK_NUMPAD7 = 0x67;
    public static final int VK_NUMPAD8 = 0x68;
    public static final int VK_NUMPAD9 = 0x69;
    public static final int VK_MULTIPLY = 0x6A;
    public static final int VK_ADD = 0x6B;
    public static final int VK_SEPARATOR = 0x6C;
    public static final int VK_SUBTRACT = 0x6D;
    public static final int VK_DECIMAL = 0x6E;
    public static final int VK_DIVIDE = 0x6F;
    public static final int VK_F1 = 0x70;
    public static final int VK_F2 = 0x71;
    public static final int VK_F3 = 0x72;
    public static final int VK_F4 = 0x73;
    public static final int VK_F5 = 0x74;
    public static final int VK_F6 = 0x75;
    public static final int VK_F7 = 0x76;
    public static final int VK_F8 = 0x77;
    public static final int VK_F9 = 0x78;
    public static final int VK_F10 = 0x79;
    public static final int VK_F11 = 0x7A;
    public static final int VK_F12 = 0x7B;
    public static final int VK_F13 = 0x7C;
    public static final int VK_F14 = 0x7D;
    public static final int VK_F15 = 0x7E;
    public static final int VK_F16 = 0x7F;
    public static final int VK_F17 = 0x80;
    public static final int VK_F18 = 0x81;
    public static final int VK_F19 = 0x82;
    public static final int VK_F20 = 0x83;
    public static final int VK_F21 = 0x84;
    public static final int VK_F22 = 0x85;
    public static final int VK_F23 = 0x86;
    public static final int VK_F24 = 0x87;
    /*                             0x88-0x8F  Unassigned */
    public static final int VK_NUMLOCK = 0x90;
    public static final int VK_SCROLL = 0x91;
    public static final int VK_OEM_NEC_EQUAL = 0x92;
    public static final int VK_OEM_FJ_JISHO = 0x92;
    public static final int VK_OEM_FJ_MASSHOU = 0x93;
    public static final int VK_OEM_FJ_TOUROKU = 0x94;
    public static final int VK_OEM_FJ_LOYA = 0x95;
    public static final int VK_OEM_FJ_ROYA = 0x96;
    /*                             0x97-0x9F  Unassigned */
    /*
     * differencing between right and left shift/control/alt key.
     * Used only by GetAsyncKeyState() and GetKeyState().
     */
    public static final int VK_LSHIFT = 0xA0;
    public static final int VK_RSHIFT = 0xA1;
    public static final int VK_LCONTROL = 0xA2;
    public static final int VK_RCONTROL = 0xA3;
    public static final int VK_LMENU = 0xA4;
    public static final int VK_RMENU = 0xA5;
    public static final int VK_BROWSER_BACK = 0xA6;
    public static final int VK_BROWSER_FORWARD = 0xA7;
    public static final int VK_BROWSER_REFRESH = 0xA8;
    public static final int VK_BROWSER_STOP = 0xA9;
    public static final int VK_BROWSER_SEARCH = 0xAA;
    public static final int VK_BROWSER_FAVORITES = 0xAB;
    public static final int VK_BROWSER_HOME = 0xAC;
    public static final int VK_VOLUME_MUTE = 0xAD;
    public static final int VK_VOLUME_DOWN = 0xAE;
    public static final int VK_VOLUME_UP = 0xAF;
    public static final int VK_MEDIA_NEXT_TRACK = 0xB0;
    public static final int VK_MEDIA_PREV_TRACK = 0xB1;
    public static final int VK_MEDIA_STOP = 0xB2;
    public static final int VK_MEDIA_PLAY_PAUSE = 0xB3;
    public static final int VK_LAUNCH_MAIL = 0xB4;
    public static final int VK_LAUNCH_MEDIA_SELECT = 0xB5;
    public static final int VK_LAUNCH_APP1 = 0xB6;
    public static final int VK_LAUNCH_APP2 = 0xB7;
    /*                             0xB8-0xB9  Unassigned */
    public static final int VK_OEM_1 = 0xBA;
    public static final int VK_OEM_PLUS = 0xBB;
    public static final int VK_OEM_COMMA = 0xBC;
    public static final int VK_OEM_MINUS = 0xBD;
    public static final int VK_OEM_PERIOD = 0xBE;
    public static final int VK_OEM_2 = 0xBF;
    public static final int VK_OEM_3 = 0xC0;
    /*                             0xC1-0xDA  Unassigned */
    public static final int VK_OEM_4 = 0xDB;
    public static final int VK_OEM_5 = 0xDC;
    public static final int VK_OEM_6 = 0xDD;
    public static final int VK_OEM_7 = 0xDE;
    public static final int VK_OEM_8 = 0xDF;
    /*                             0xE0       OEM specific */
    public static final int VK_OEM_AX = 0xE1; /* "AX" key on Japanese AX keyboard */
    public static final int VK_OEM_102 = 0xE2; /* "<>" or "\|" on RT 102-key keyboard */
    public static final int VK_ICO_HELP = 0xE3; /* Help key on ICO */
    public static final int VK_ICO_00 = 0xE4; /* 00 key on ICO */
    public static final int VK_PROCESSKEY = 0xE5;
    public static final int VK_ICO_CLEAR = 0xE6;
    public static final int VK_PACKET = 0xE7;
    public static final int VK_OEM_RESET = 0xE9;
    public static final int VK_OEM_JUMP = 0xEA;
    public static final int VK_OEM_PA1 = 0xEB;
    public static final int VK_OEM_PA2 = 0xEC;
    public static final int VK_OEM_PA3 = 0xED;
    public static final int VK_OEM_WSCTRL = 0xEE;
    public static final int VK_OEM_CUSEL = 0xEF;
    public static final int VK_OEM_ATTN = 0xF0;
    public static final int VK_OEM_FINISH = 0xF1;
    public static final int VK_OEM_COPY = 0xF2;
    public static final int VK_OEM_AUTO = 0xF3;
    public static final int VK_OEM_ENLW = 0xF4;
    public static final int VK_OEM_BACKTAB = 0xF5;
    public static final int VK_ATTN = 0xF6;
    public static final int VK_CRSEL = 0xF7;
    public static final int VK_EXSEL = 0xF8;
    public static final int VK_EREOF = 0xF9;
    public static final int VK_PLAY = 0xFA;
    public static final int VK_ZOOM = 0xFB;
    public static final int VK_NONAME = 0xFC;
    public static final int VK_PA1 = 0xFD;
    /*                             0xE8       Unassigned */
    public static final int VK_OEM_CLEAR = 0xFE;
    public static final int MK_LBUTTON = 0x0001;
    public static final int MK_RBUTTON = 0x0002;
    public static final int MK_SHIFT = 0x0004;
    public static final int MK_CONTROL = 0x0008;
    public static final int MK_MBUTTON = 0x0010;
    public static final int MK_XBUTTON1 = 0x0020;
    public static final int MK_XBUTTON2 = 0x0040;
    /* Image type */
    public static final int DST_COMPLEX = 0x0000;
    public static final int DST_TEXT = 0x0001;
    public static final int DST_PREFIXTEXT = 0x0002;
    public static final int DST_ICON = 0x0003;
    public static final int DST_BITMAP = 0x0004;
    /* State type */
    public static final int DSS_NORMAL = 0x0000;
    public static final int DSS_UNION = 0x0010; /* Gray string appearance */
    public static final int DSS_DISABLED = 0x0020;
    public static final int DSS_DEFAULT = 0x0040; /* Make it bold */
    public static final int DSS_MONO = 0x0080;
    public static final int DSS_HIDEPREFIX = 0x0200;
    public static final int DSS_PREFIXONLY = 0x0400;
    public static final int DSS_RIGHT = 0x8000;
    /* Object Definitions for EnumObjects() */
    public static final int OBJ_PEN = 1;
    public static final int OBJ_BRUSH = 2;
    public static final int OBJ_DC = 3;
    public static final int OBJ_METADC = 4;
    public static final int OBJ_PAL = 5;
    public static final int OBJ_FONT = 6;
    public static final int OBJ_BITMAP = 7;
    public static final int OBJ_REGION = 8;
    public static final int OBJ_METAFILE = 9;
    public static final int OBJ_MEMDC = 10;
    public static final int OBJ_EXTPEN = 11;
    public static final int OBJ_ENHMETADC = 12;
    public static final int OBJ_ENHMETAFILE = 13;
    public static final int OBJ_COLORSPACE = 14;
    public static final int DFC_CAPTION = 1;
    public static final int DFC_MENU = 2;
    public static final int DFC_SCROLL = 3;
    public static final int DFC_BUTTON = 4;
    public static final int DFC_POPUPMENU = 5;
    public static final int DFCS_CAPTIONCLOSE = 0x0000;
    public static final int DFCS_CAPTIONMIN = 0x0001;
    public static final int DFCS_CAPTIONMAX = 0x0002;
    public static final int DFCS_CAPTIONRESTORE = 0x0003;
    public static final int DFCS_CAPTIONHELP = 0x0004; /* Windows 95 only */
    public static final int DFCS_MENUARROW = 0x0000;
    public static final int DFCS_MENUCHECK = 0x0001;
    public static final int DFCS_MENUBULLET = 0x0002;
    public static final int DFCS_MENUARROWRIGHT = 0x0004;
    public static final int DFCS_SCROLLUP = 0x0000;
    public static final int DFCS_SCROLLDOWN = 0x0001;
    public static final int DFCS_SCROLLLEFT = 0x0002;
    public static final int DFCS_SCROLLRIGHT = 0x0003;
    public static final int DFCS_SCROLLCOMBOBOX = 0x0005;
    public static final int DFCS_SCROLLSIZEGRIP = 0x0008;
    public static final int DFCS_SCROLLSIZEGRIPRIGHT = 0x0010;

    /* DrawFrameControl() uType's */
    public static final int DFCS_BUTTONCHECK = 0x0000;
    public static final int DFCS_BUTTONRADIOIMAGE = 0x0001;
    public static final int DFCS_BUTTONRADIOMASK = 0x0002; /* to draw nonsquare button */
    public static final int DFCS_BUTTONRADIO = 0x0004;
    public static final int DFCS_BUTTON3STATE = 0x0008;

    /* uState's */
    public static final int DFCS_BUTTONPUSH = 0x0010;
    public static final int DFCS_INACTIVE = 0x0100;
    public static final int DFCS_PUSHED = 0x0200;
    public static final int DFCS_CHECKED = 0x0400;
    public static final int DFCS_TRANSPARENT = 0x0800;
    public static final int DFCS_HOT = 0x1000;
    public static final int DFCS_ADJUSTRECT = 0x2000; /* exclude surrounding edge */
    public static final int DFCS_FLAT = 0x4000;
    public static final int DFCS_MONO = 0x8000;
    public static final int R2_BLACK = 1;
    public static final int R2_NOTMERGEPEN = 2;
    public static final int R2_MASKNOTPEN = 3;
    public static final int R2_NOTCOPYPEN = 4;
    public static final int R2_MASKPENNOT = 5;
    public static final int R2_NOT = 6;
    public static final int R2_XORPEN = 7;
    public static final int R2_NOTMASKPEN = 8;
    public static final int R2_MASKPEN = 9;
    public static final int R2_NOTXORPEN = 10;
    public static final int R2_NOP = 11;
    public static final int R2_MERGENOTPEN = 12;
    public static final int R2_COPYPEN = 13;

    /* additional state of the control */
    public static final int R2_MERGEPENNOT = 14;
    public static final int R2_MERGEPEN = 15;
    public static final int R2_WHITE = 16;
    public static final int R2_LAST = 16;
    public static final int MEM_COMMIT = 0x1000;
    public static final int MEM_RESERVE = 0x2000;
    public static final int MEM_DECOMMIT = 0x4000;
    public static final int MEM_RELEASE = 0x8000;

    /* Raster operations */
    public static final int MEM_RESET = 0x80000;
    public static final int MEM_LARGE_PAGES = 0x20000000;
    public static final int MEM_PHYSICAL = 0x400000;
    public static final int MEM_TOP_DOWN = 0x100000;
    public static final int MEM_WRITE_WATCH = 0x200000;
    public static final int PAGE_NOACCESS = 0x01;
    public static final int PAGE_READONLY = 0x02;
    public static final int PAGE_READWRITE = 0x04;
    public static final int PAGE_WRITECOPY = 0x08;
    public static final int PAGE_EXECUTE = 0x10;
    public static final int PAGE_EXECUTE_READ = 0x20;
    public static final int PAGE_EXECUTE_READWRITE = 0x40;
    public static final int PAGE_EXECUTE_WRITECOPY = 0x80;
    public static final int PAGE_GUARD = 0x100;
    public static final int PAGE_NOCACHE = 0x200;
    public static final int PAGE_WRITECOMBINE = 0x400;
    /* MapVirtualKey translation types */
    public static final int MAPVK_VK_TO_VSC = 0;
    public static final int MAPVK_VSC_TO_VK = 1;
    public static final int MAPVK_VK_TO_CHAR = 2;
    public static final int MAPVK_VSC_TO_VK_EX = 3;
    public static final int MAPVK_VK_TO_VSC_EX = 4;
    public static final int CALLBACK_TYPEMASK = 0x00070000; /* callback type mask */
    public static final int CALLBACK_NULL = 0x00000000; /* no callback */
    public static final int CALLBACK_WINDOW = 0x00010000; /* dwCallback is a HWND */
    public static final int CALLBACK_TASK = 0x00020000; /* dwCallback is a HTASK */
    public static final int CALLBACK_THREAD = CALLBACK_TASK; /* dwCallback is a thread ID */
    public static final int CALLBACK_FUNCTION = 0x00030000; /* dwCallback is a FARPROC */
    public static final int CALLBACK_EVENT = 0x00050000; /* dwCallback is an EVENT Handler */
    public static final int MMSYSERR_BASE = 0;
    public static final int WAVERR_BASE = 32;
    public static final int MIDIERR_BASE = 64;
    public static final int TIMERR_BASE = 96;
    public static final int JOYERR_BASE = 160;
    public static final int MCIERR_BASE = 256;
    public static final int MCI_STRING_OFFSET = 512;
    public static final int MCI_VD_OFFSET = 1024;
    public static final int MCI_CD_OFFSET = 1088;
    public static final int MCI_WAVE_OFFSET = 1152;
    public static final int MCI_SEQ_OFFSET = 1216;
    public static final int MMSYSERR_NOERROR = 0;
    public static final int MMSYSERR_ERROR = MMSYSERR_BASE + 1;
    public static final int MMSYSERR_BADDEVICEID = MMSYSERR_BASE + 2;
    public static final int MMSYSERR_NOTENABLED = MMSYSERR_BASE + 3;
    public static final int MMSYSERR_ALLOCATED = MMSYSERR_BASE + 4;
    public static final int MMSYSERR_INVALHANDLE = MMSYSERR_BASE + 5;
    public static final int MMSYSERR_NODRIVER = MMSYSERR_BASE + 6;
    public static final int MMSYSERR_NOMEM = MMSYSERR_BASE + 7;
    public static final int MMSYSERR_NOTSUPPORTED = MMSYSERR_BASE + 8;
    public static final int MMSYSERR_BADERRNUM = MMSYSERR_BASE + 9;
    public static final int MMSYSERR_INVALFLAG = MMSYSERR_BASE + 10;
    public static final int MMSYSERR_INVALPARAM = MMSYSERR_BASE + 11;
    public static final int MMSYSERR_HANDLEBUSY = MMSYSERR_BASE + 12;
    public static final int MMSYSERR_INVALIDALIAS = MMSYSERR_BASE + 13;
    public static final int MMSYSERR_BADDB = MMSYSERR_BASE + 14;
    public static final int MMSYSERR_KEYNOTFOUND = MMSYSERR_BASE + 15;
    public static final int MMSYSERR_READERROR = MMSYSERR_BASE + 16;
    public static final int MMSYSERR_WRITEERROR = MMSYSERR_BASE + 17;
    public static final int MMSYSERR_DELETEERROR = MMSYSERR_BASE + 18;
    public static final int MMSYSERR_VALNOTFOUND = MMSYSERR_BASE + 19;
    public static final int MMSYSERR_NODRIVERCB = MMSYSERR_BASE + 20;
    public static final int MMSYSERR_MOREDATA = MMSYSERR_BASE + 21;
    public static final int MMSYSERR_LASTERROR = MMSYSERR_BASE + 21;
    public static final int WAVE_FORMAT_QUERY = 0x0001;
    public static final int WAVE_ALLOWSYNC = 0x0002;
    public static final int WAVE_MAPPED = 0x0004;
    public static final int WAVE_FORMAT_DIRECT = 0x0008;
    public static final int WAVE_FORMAT_DIRECT_QUERY = WAVE_FORMAT_QUERY | WAVE_FORMAT_DIRECT;
    public static final int WODM_OPEN = 5;
    public static final int WAVE_MAPPER = -1;
    public static final int WAVERR_BADFORMAT = WAVERR_BASE + 0; /* unsupported wave format */
    public static final int WAVERR_STILLPLAYING = WAVERR_BASE + 1; /* still something playing */
    public static final int WAVERR_UNPREPARED = WAVERR_BASE + 2; /* header not prepared */
    public static final int WAVERR_SYNC = WAVERR_BASE + 3; /* device is synchronous */
    public static final int WAVERR_LASTERROR = WAVERR_BASE + 3; /* last error in range */
    public static final int DIB_RGB_COLORS = 0;
    public static final int DIB_PAL_COLORS = 1;
    public static final int ACM_METRIC_COUNT_DRIVERS = 1;
    public static final int ACM_METRIC_COUNT_CODECS = 2;
    public static final int ACM_METRIC_COUNT_CONVERTERS = 3;
    public static final int ACM_METRIC_COUNT_FILTERS = 4;
    public static final int ACM_METRIC_COUNT_DISABLED = 5;
    public static final int ACM_METRIC_COUNT_HARDWARE = 6;
    public static final int ACM_METRIC_COUNT_LOCAL_DRIVERS = 20;
    public static final int ACM_METRIC_COUNT_LOCAL_CODECS = 21;
    public static final int ACM_METRIC_COUNT_LOCAL_CONVERTERS = 22;
    public static final int ACM_METRIC_COUNT_LOCAL_FILTERS = 23;
    public static final int ACM_METRIC_COUNT_LOCAL_DISABLED = 24;
    public static final int ACM_METRIC_HARDWARE_WAVE_INPUT = 30;
    public static final int ACM_METRIC_HARDWARE_WAVE_OUTPUT = 31;
    public static final int ACM_METRIC_MAX_SIZE_FORMAT = 50;
    public static final int ACM_METRIC_MAX_SIZE_FILTER = 51;
    public static final int ACM_METRIC_DRIVER_SUPPORT = 100;
    public static final int ACM_METRIC_DRIVER_PRIORITY = 101;
    public static final int MMIOERR_BASE = 256;
    public static final int MMIOERR_FILENOTFOUND = MMIOERR_BASE + 1; /* file not found */
    public static final int MMIOERR_OUTOFMEMORY = MMIOERR_BASE + 2; /* out of memory */
    public static final int MMIOERR_CANNOTOPEN = MMIOERR_BASE + 3; /* cannot open */
    public static final int MMIOERR_CANNOTCLOSE = MMIOERR_BASE + 4; /* cannot close */
    public static final int MMIOERR_CANNOTREAD = MMIOERR_BASE + 5; /* cannot read */
    public static final int MMIOERR_CANNOTWRITE = MMIOERR_BASE + 6; /* cannot write */
    public static final int MMIOERR_CANNOTSEEK = MMIOERR_BASE + 7; /* cannot seek */
    public static final int MMIOERR_CANNOTEXPAND = MMIOERR_BASE + 8; /* cannot expand file */
    public static final int MMIOERR_CHUNKNOTFOUND = MMIOERR_BASE + 9; /* chunk not found */
    public static final int MMIOERR_UNBUFFERED = MMIOERR_BASE + 10; /* file is unbuffered */
    public static final int MMIOERR_PATHNOTFOUND = MMIOERR_BASE + 11;
    public static final int MMIOERR_ACCESSDENIED = MMIOERR_BASE + 12;
    public static final int MMIOERR_SHARINGVIOLATION = MMIOERR_BASE + 13;
    public static final int MMIOERR_NETWORKERROR = MMIOERR_BASE + 14;
    public static final int MMIOERR_TOOMANYOPENFILES = MMIOERR_BASE + 15;
    public static final int MMIOERR_INVALIDFILE = MMIOERR_BASE + 16;
    public static final int MMIO_RWMODE = 0x00000003; /* open file for reading/writing/both */
    public static final int MMIO_SHAREMODE = 0x00000070; /* file sharing mode number */
    public static final int MMIO_CREATE = 0x00001000; /* create new file (or truncate file) */
    public static final int MMIO_PARSE = 0x00000100; /* parse new file returning path */
    public static final int MMIO_DELETE = 0x00000200; /* create new file (or truncate file) */
    public static final int MMIO_EXIST = 0x00004000; /* checks for existence of file */
    public static final int MMIO_ALLOCBUF = 0x00010000; /* mmioOpen() should allocate a buffer */
    public static final int MMIO_GETTEMP = 0x00020000; /* mmioOpen() should retrieve temp name */
    public static final int MMIO_DIRTY = 0x10000000; /* I/O buffer is dirty */
    public static final int MMIO_READ = 0x00000000; /* open file for reading only */
    public static final int MMIO_WRITE = 0x00000001; /* open file for writing only */
    public static final int MMIO_READWRITE = 0x00000002; /* open file for reading and writing */
    public static final int MMIO_COMPAT = 0x00000000; /* compatibility mode */
    public static final int MMIO_EXCLUSIVE = 0x00000010; /* exclusive-access mode */
    public static final int MMIO_DENYWRITE = 0x00000020; /* deny writing to other processes */
    public static final int MMIO_DENYREAD = 0x00000030; /* deny reading to other processes */
    public static final int MMIO_DENYNONE = 0x00000040; /* deny nothing to other processes */
    public static final int MMIO_FHOPEN = 0x0010; /* mmioClose: keep file handle open */
    public static final int MMIO_EMPTYBUF = 0x0010; /* mmioFlush: empty the I/O buffer */
    public static final int MMIO_TOUPPER = 0x0010; /* mmioStringToFOURCC: to u-case */
    public static final int MMIO_INSTALLPROC = 0x00010000; /* mmioInstallIOProc: install MMIOProc */
    public static final int MMIO_GLOBALPROC = 0x10000000; /* mmioInstallIOProc: install globally */
    public static final int MMIO_REMOVEPROC = 0x00020000; /* mmioInstallIOProc: remove MMIOProc */
    public static final int MMIO_FINDPROC = 0x00040000; /* mmioInstallIOProc: find an MMIOProc */
    public static final int MMIO_FINDCHUNK = 0x0010; /* mmioDescend: find a chunk by ID */
    public static final int MMIO_FINDRIFF = 0x0020; /* mmioDescend: find a LIST chunk */
    public static final int MMIO_FINDLIST = 0x0040; /* mmioDescend: find a RIFF chunk */
    public static final int MMIO_CREATERIFF = 0x0020; /* mmioCreateChunk: make a LIST chunk */
    public static final int MMIO_CREATELIST = 0x0040; /* mmioCreateChunk: make a RIFF chunk */
    public static final int FOURCC_RIFF = mmioFOURCC('R', 'I', 'F', 'F');
    public static final int FOURCC_LIST = mmioFOURCC('L', 'I', 'S', 'T');
    public static final int FOURCC_DOS = mmioFOURCC('D', 'O', 'S', ' ');
    public static final int FOURCC_MEM = mmioFOURCC('M', 'E', 'M', ' ');
    public static final int MMIO_DEFAULTBUFFER = 8192; /* default buffer size */
    public static final int SEEK_SET = 0;
    public static final int SEEK_CUR = 1;
    public static final int SEEK_END = 2;
    public static final int SND_SYNC = 0x0000; /* play synchronously (default) */
    public static final int SND_ASYNC = 0x0001; /* play asynchronously */
    public static final int SND_NODEFAULT = 0x0002; /* don't use default sound */
    public static final int SND_MEMORY = 0x0004; /* lpszSoundName points to a memory file */
    public static final int SND_LOOP = 0x0008; /* loop the sound until next sndPlaySound */
    public static final int SND_NOSTOP = 0x0010; /* don't stop any currently playing sound */
    public static final int SND_NOWAIT = 0x00002000; /* don't wait if the driver is busy */
    public static final int SND_ALIAS = 0x00010000; /* name is a registry alias */
    public static final int SND_ALIAS_ID = 0x00110000; /* alias is a predefined ID */
    public static final int SND_FILENAME = 0x00020000; /* name is file name */
    public static final int SND_RESOURCE = 0x00040004; /* name is resource name or atom */
    public static final int SND_PURGE = 0x00000040; /* purge all sounds */
    public static final int SND_APPLICATION = 0x00000080; /* look for application specific association */
    public static final int SND_ALIAS_START = 0;
    public static final int SND_ALIAS_SYSTEMASTERISK = sndAlias('S', '*');
    public static final int SND_ALIAS_SYSTEMQUESTION = sndAlias('S', '?');
    public static final int SND_ALIAS_SYSTEMHAND = sndAlias('S', 'H');
    public static final int SND_ALIAS_SYSTEMEXIT = sndAlias('S', 'E');
    public static final int SND_ALIAS_SYSTEMSTART = sndAlias('S', 'S');
    public static final int SND_ALIAS_SYSTEMWELCOME = sndAlias('S', 'W');
    public static final int SND_ALIAS_SYSTEMEXCLAMATION = sndAlias('S', '!');
    public static final int SND_ALIAS_SYSTEMDEFAULT = sndAlias('S', 'D');
    /* STARTUPINFO.dwFlags */
    public static final int STARTF_USESHOWWINDOW = 0x00000001;
    public static final int STARTF_USESIZE = 0x00000002;
    public static final int STARTF_USEPOSITION = 0x00000004;
    public static final int STARTF_USECOUNTCHARS = 0x00000008;
    public static final int STARTF_USEFILLATTRIBUTE = 0x00000010;
    public static final int STARTF_RUNFULLSCREEN = 0x00000020;
    public static final int STARTF_FORCEONFEEDBACK = 0x00000040;
    public static final int STARTF_FORCEOFFFEEDBACK = 0x00000080;
    public static final int STARTF_USESTDHANDLES = 0x00000100;
    public static final int STARTF_USEHOTKEY = 0x00000200;
    public static final int OF_READ = 0x0000;
    public static final int OF_WRITE = 0x0001;
    public static final int OF_READWRITE = 0x0002;
    public static final int OF_SHARE_COMPAT = 0x0000;
    public static final int OF_SHARE_EXCLUSIVE = 0x0010;
    public static final int OF_SHARE_DENY_WRITE = 0x0020;
    public static final int OF_SHARE_DENY_READ = 0x0030;
    public static final int OF_SHARE_DENY_NONE = 0x0040;
    public static final int OF_PARSE = 0x0100;
    public static final int OF_DELETE = 0x0200;
    public static final int OF_VERIFY = 0x0400; /* Used with OF_REOPEN */
    public static final int OF_SEARCH = 0x0400; /* Used without OF_REOPEN */
    public static final int OF_CANCEL = 0x0800;
    public static final int OF_CREATE = 0x1000;
    public static final int OF_PROMPT = 0x2000;
    public static final int OF_EXIST = 0x4000;
    public static final int OF_REOPEN = 0x8000;
    /* WAVE form wFormatTag IDs */
    public static final int WAVE_FORMAT_UNKNOWN = 0x0000; /*  Microsoft Corporation  */
    public static final int WAVE_FORMAT_ADPCM = 0x0002; /*  Microsoft Corporation  */
    public static final int WAVE_FORMAT_IEEE_FLOAT = 0x0003; /*  Microsoft Corporation  */
    public static final int WAVE_FORMAT_IBM_CVSD = 0x0005; /*  IBM Corporation  */
    public static final int WAVE_FORMAT_ALAW = 0x0006; /*  Microsoft Corporation  */
    public static final int WAVE_FORMAT_MULAW = 0x0007; /*  Microsoft Corporation  */
    public static final int WAVE_FORMAT_OKI_ADPCM = 0x0010; /*  OKI  */
    public static final int WAVE_FORMAT_DVI_ADPCM = 0x0011; /*  Intel Corporation  */
    public static final int WAVE_FORMAT_IMA_ADPCM = WAVE_FORMAT_DVI_ADPCM; /*  Intel Corporation  */
    public static final int WAVE_FORMAT_MEDIASPACE_ADPCM = 0x0012; /*  Videologic  */
    public static final int WAVE_FORMAT_SIERRA_ADPCM = 0x0013; /*  Sierra Semiconductor Corp  */
    public static final int WAVE_FORMAT_G723_ADPCM = 0x0014; /*  Antex Electronics Corporation  */
    public static final int WAVE_FORMAT_DIGISTD = 0x0015; /*  DSP Solutions, Inc.  */
    public static final int WAVE_FORMAT_DIGIFIX = 0x0016; /*  DSP Solutions, Inc.  */
    public static final int WAVE_FORMAT_DIALOGIC_OKI_ADPCM = 0x0017; /*  Dialogic Corporation  */
    public static final int WAVE_FORMAT_YAMAHA_ADPCM = 0x0020; /*  Yamaha Corporation of America  */
    public static final int WAVE_FORMAT_SONARC = 0x0021; /*  Speech Compression  */
    public static final int WAVE_FORMAT_DSPGROUP_TRUESPEECH = 0x0022; /*  DSP Group, Inc  */
    public static final int WAVE_FORMAT_ECHOSC1 = 0x0023; /*  Echo Speech Corporation  */
    public static final int WAVE_FORMAT_AUDIOFILE_AF36 = 0x0024; /*    */
    public static final int WAVE_FORMAT_APTX = 0x0025; /*  Audio Processing Technology  */
    public static final int WAVE_FORMAT_AUDIOFILE_AF10 = 0x0026; /*    */
    public static final int WAVE_FORMAT_DOLBY_AC2 = 0x0030; /*  Dolby Laboratories  */
    public static final int WAVE_FORMAT_GSM610 = 0x0031; /*  Microsoft Corporation  */
    public static final int WAVE_FORMAT_ANTEX_ADPCME = 0x0033; /*  Antex Electronics Corporation  */
    public static final int WAVE_FORMAT_CONTROL_RES_VQLPC = 0x0034; /*  Control Resources Limited  */
    public static final int WAVE_FORMAT_DIGIREAL = 0x0035; /*  DSP Solutions, Inc.  */
    public static final int WAVE_FORMAT_DIGIADPCM = 0x0036; /*  DSP Solutions, Inc.  */
    public static final int WAVE_FORMAT_CONTROL_RES_CR10 = 0x0037; /*  Control Resources Limited  */
    public static final int WAVE_FORMAT_NMS_VBXADPCM = 0x0038; /*  Natural MicroSystems  */
    public static final int WAVE_FORMAT_G721_ADPCM = 0x0040; /*  Antex Electronics Corporation  */
    public static final int WAVE_FORMAT_MPEG = 0x0050; /*  Microsoft Corporation  */
    public static final int WAVE_FORMAT_MPEGLAYER3 = 0x0055;
    public static final int WAVE_FORMAT_CREATIVE_ADPCM = 0x0200; /*  Creative Labs, Inc  */
    public static final int WAVE_FORMAT_CREATIVE_FASTSPEECH8 = 0x0202; /*  Creative Labs, Inc  */
    public static final int WAVE_FORMAT_CREATIVE_FASTSPEECH10 = 0x0203; /*  Creative Labs, Inc  */
    public static final int WAVE_FORMAT_FM_TOWNS_SND = 0x0300; /*  Fujitsu Corp.  */
    public static final int WAVE_FORMAT_OLIGSM = 0x1000; /*  Ing C. Olivetti & C., S.p.A.  */
    public static final int WAVE_FORMAT_OLIADPCM = 0x1001; /*  Ing C. Olivetti & C., S.p.A.  */
    public static final int WAVE_FORMAT_OLICELP = 0x1002; /*  Ing C. Olivetti & C., S.p.A.  */
    public static final int WAVE_FORMAT_OLISBC = 0x1003; /*  Ing C. Olivetti & C., S.p.A.  */
    public static final int WAVE_FORMAT_OLIOPR = 0x1004; /*  Ing C. Olivetti & C., S.p.A.  */
    public static final int WAVE_FORMAT_EXTENSIBLE = 0xFFFE; /* Microsoft */
    public static final int DSBSIZE_MIN = 4;
    public static final int DSBSIZE_MAX = 0xFFFFFFF;
    public static final int DSBPAN_LEFT = -10000;
    public static final int DSBPAN_CENTER = 0;
    public static final int DSBPAN_RIGHT = 10000;
    public static final int DSBVOLUME_MAX = 0;
    public static final int DSBVOLUME_MIN = -10000;
    public static final int DSBFREQUENCY_MIN = 100;
    public static final int DSBFREQUENCY_MAX = 200000;
    public static final int DSBFREQUENCY_ORIGINAL = 0;
    public static final int WAIT_SWITCH = 0xFFFF;
    public static final int NUM_SYS_COLORS = COLOR_MENUBAR + 1;
    public static final int PAINT_INTERNAL = 0x01; /* internal WM_PAINT pending */
    public static final int PAINT_ERASE = 0x02; /* needs WM_ERASEBKGND */
    public static final int PAINT_NONCLIENT = 0x04; /* needs WM_NCPAINT */
    public static final int PAINT_DELAYED_ERASE = 0x08; /* still needs erase after WM_ERASEBKGND */
    public static final int COORDS_CLIENT = 0; /* relative to client area */
    public static final int COORDS_WINDOW = 1; /* relative to whole window area */
    public static final int COORDS_PARENT = 2; /* relative to parent's client area */
    public static final int COORDS_SCREEN = 3; /* relative to screen origin */
    /* WND flags values */
    public static final int WIN_RESTORE_MAX = 0x0001; /* Maximize when restoring */
    public static final int WIN_NEED_SIZE = 0x0002; /* Internal WM_SIZE is needed */
    public static final int WIN_NCACTIVATED = 0x0004; /* last WM_NCACTIVATE was positive */
    public static final int WIN_ISMDICLIENT = 0x0008; /* Window is an MDIClient */
    public static final int WIN_ISUNICODE = 0x0010; /* Window is Unicode */
    public static final int WIN_NEEDS_SHOW_OWNEDPOPUP = 0x0020; /* WM_SHOWWINDOW:SC_SHOW must be sent in the next ShowOwnedPopup call */
    public static final int WIN_CHILDREN_MOVED = 0x0040; /* children may have moved, ignore stored positions */
    public static final int DSOUND_FREQSHIFT = 20;
    public static int NULL = 0;
    public static int DF_END = 0x0001;
    public static int DF_OWNERENABLED = 0x0002;

    public static void log(String s) {
        if (BuiltinModule.inPre) {
            BuiltinModule.inPre = false;
        }
        if (BuiltinModule.indent > 0)
            System.out.println();
        for (int i = 0; i < BuiltinModule.indent; i++)
            System.out.print("    ");
        System.out.println(s);
    }

    public static void warn(String s) {
        log(HandlerBase.currentHandler.getName() + ": " + s);
    }

    // ************
    // * Internal *
    // ************

    public static void faked() {
        log(HandlerBase.currentHandler.getName() + " faked");
    }

    public static int MAKELONG(int low, int high) {
        return low & 0xFFFF | high << 16;
    }

    public static int MAKEWPARAM(int low, int high) {
        return low & 0xFFFF | high << 16;
    }

    public static void SetLastError(int error) {
        Scheduler.getCurrentThread().setLastError(error);
    }

    public static int RGB(int r, int g, int b) {
        return b | g << 8 | r << 16;
    }

    public static boolean IS_INTRESOURCE(int p) {
        return (p & 0xFFFF0000) == 0;
    }

    public static int LOWORD(int w) {
        return w & 0xFFFF;
    }

    public static int HIWORD(int w) {
        return w >>> 16;
    }

    public static int LOBYTE(int b) {
        return b & 0xFF;
    }

    public static void writed(int address, int value) {
        Memory.mem_writed(address, value);
    }

    public static void writew(int address, int value) {
        Memory.mem_writew(address, value);
    }

    public static void writeb(int address, int value) {
        Memory.mem_writeb(address, value);
    }

    public static int readb(int address) {
        return Memory.mem_readb(address);
    }

    public static int readw(int address) {
        return Memory.mem_readw(address);
    }

    public static int readd(int address) {
        return Memory.mem_readd(address);
    }

    public static int Handle(WinObject object) {
        if (object == null)
            return 0;
        return object.handle;
    }

    protected static int BOOL(boolean b) {
        return b ? WinAPI.TRUE : WinAPI.FALSE;
    }

    protected static int getTempBuffer(int size) {
        return WinSystem.getCurrentProcess().getTemp(size);
    }

    public static final int mmioFOURCC(int ch0, int ch1, int ch2, int ch3) {
        return ch0 & 0xFF | (ch1 & 0xFF) << 8 | (ch2 & 0xFF) << 16 | (ch3 & 0xFF) << 24;
    }

    public static int sndAlias(int ch0, int ch1) {
        return SND_ALIAS_START + (ch0 & 0xFF) | (ch1 & 0xFF) << 8;
    }
}
