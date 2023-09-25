package jdos.win.builtin;

import jdos.win.Win;
import jdos.win.loader.BuiltinModule;
import jdos.win.loader.Loader;
import jdos.win.utils.StringUtil;

public class Wsock32 extends BuiltinModule {
    /*
     * All Windows Sockets error constants are biased by WSABASEERR from
     * the "normal". They are also defined in winerror.h.
     */
    public static final int WSABASEERR = 10000;
    /*
     * Windows Sockets definitions of regular Microsoft C error constants
     */
    public static final int WSAEINTR = WSABASEERR + 4;
    public static final int WSAEBADF = WSABASEERR + 9;
    public static final int WSAEACCES = WSABASEERR + 13;
    public static final int WSAEFAULT = WSABASEERR + 14;
    public static final int WSAEINVAL = WSABASEERR + 22;
    public static final int WSAEMFILE = WSABASEERR + 24;
    /*
     * Windows Sockets definitions of regular Berkeley error constants
     */
    public static final int WSAEWOULDBLOCK = WSABASEERR + 35;
    public static final int WSAEINPROGRESS = WSABASEERR + 36;
    public static final int WSAEALREADY = WSABASEERR + 37;
    public static final int WSAENOTSOCK = WSABASEERR + 38;
    public static final int WSAEDESTADDRREQ = WSABASEERR + 39;
    public static final int WSAEMSGSIZE = WSABASEERR + 40;
    public static final int WSAEPROTOTYPE = WSABASEERR + 41;
    public static final int WSAENOPROTOOPT = WSABASEERR + 42;
    public static final int WSAEPROTONOSUPPORT = WSABASEERR + 43;
    public static final int WSAESOCKTNOSUPPORT = WSABASEERR + 44;
    public static final int WSAEOPNOTSUPP = WSABASEERR + 45;
    public static final int WSAEPFNOSUPPORT = WSABASEERR + 46;
    public static final int WSAEAFNOSUPPORT = WSABASEERR + 47;
    public static final int WSAEADDRINUSE = WSABASEERR + 48;
    public static final int WSAEADDRNOTAVAIL = WSABASEERR + 49;
    public static final int WSAENETDOWN = WSABASEERR + 50;
    public static final int WSAENETUNREACH = WSABASEERR + 51;
    public static final int WSAENETRESET = WSABASEERR + 52;
    public static final int WSAECONNABORTED = WSABASEERR + 53;
    public static final int WSAECONNRESET = WSABASEERR + 54;
    public static final int WSAENOBUFS = WSABASEERR + 55;
    public static final int WSAEISCONN = WSABASEERR + 56;
    public static final int WSAENOTCONN = WSABASEERR + 57;
    public static final int WSAESHUTDOWN = WSABASEERR + 58;
    public static final int WSAETOOMANYREFS = WSABASEERR + 59;
    public static final int WSAETIMEDOUT = WSABASEERR + 60;
    public static final int WSAECONNREFUSED = WSABASEERR + 61;
    public static final int WSAELOOP = WSABASEERR + 62;
    public static final int WSAENAMETOOLONG = WSABASEERR + 63;
    public static final int WSAEHOSTDOWN = WSABASEERR + 64;
    public static final int WSAEHOSTUNREACH = WSABASEERR + 65;
    public static final int WSAENOTEMPTY = WSABASEERR + 66;
    public static final int WSAEPROCLIM = WSABASEERR + 67;
    public static final int WSAEUSERS = WSABASEERR + 68;
    public static final int WSAEDQUOT = WSABASEERR + 69;
    public static final int WSAESTALE = WSABASEERR + 70;
    public static final int WSAEREMOTE = WSABASEERR + 71;
    /*
     * Extended Windows Sockets error constant definitions
     */
    public static final int WSASYSNOTREADY = WSABASEERR + 91;
    public static final int WSAVERNOTSUPPORTED = WSABASEERR + 92;
    public static final int WSANOTINITIALISED = WSABASEERR + 93;
    public static final int WSAEDISCON = WSABASEERR + 101;
    public static final int WSAENOMORE = WSABASEERR + 102;
    public static final int WSAECANCELLED = WSABASEERR + 103;
    public static final int WSAEINVALIDPROCTABLE = WSABASEERR + 104;
    public static final int WSAEINVALIDPROVIDER = WSABASEERR + 105;
    public static final int WSAEPROVIDERFAILEDINIT = WSABASEERR + 106;
    public static final int WSASYSCALLFAILURE = WSABASEERR + 107;
    public static final int WSASERVICE_NOT_FOUND = WSABASEERR + 108;
    public static final int WSATYPE_NOT_FOUND = WSABASEERR + 109;
    public static final int WSA_E_NO_MORE = WSABASEERR + 110;
    public static final int WSA_E_CANCELLED = WSABASEERR + 111;
    public static final int WSAEREFUSED = WSABASEERR + 112;

    public Wsock32(Loader loader, int handle) {
        super(loader, "Wsock32.dll", handle);
        add(Wsock32.class, "gethostbyname", new String[] { "(STRING)name", "(HEX)result" }, 52);
        add(Wsock32.class, "gethostname", new String[] { "(HEX)name", "namelen", "result", "00(STRING)name" }, 57);
        add(Wsock32.class, "WSACleanup", new String[0], 115);
        add(Wsock32.class, "WSAStartup", new String[] { "wVersionRequested", "(HEX)lpWSAData" }, 116);
    }

    // struct hostent* FAR gethostbyname(const char *name)
    public static int gethostbyname(int name) {
        Win.panic("gethostbyname not implemented yet");
        return 0;
    }

    // int gethostname(char *name, int namelen)
    public static int gethostname(int name, int namelen) {
        Win.panic("gethostname not implemented yet");
        return 0;
    }

    // int WSACleanup(void);
    public static int WSACleanup() {
        return 0;
    }

    // int WSAStartup(WORD wVersionRequested, LPWSADATA lpWSAData)
    public static int WSAStartup(int wVersionRequested, int lpWSAData) {
        if (LOBYTE(wVersionRequested) < 1)
            return WSAVERNOTSUPPORTED;

        if (lpWSAData == 0)
            return WSAEINVAL;

        writew(lpWSAData, wVersionRequested);
        lpWSAData += 2;
        writew(lpWSAData, 0x0202);
        lpWSAData += 2;
        StringUtil.strcpy(lpWSAData, "WinSock 2.0");
        lpWSAData += 4;
        StringUtil.strcpy(lpWSAData, "Running");
        lpWSAData += 4;
        writew(lpWSAData, 128);
        lpWSAData += 2;
        writew(lpWSAData, 1024);
        lpWSAData += 2;
        return 0;
    }
}
