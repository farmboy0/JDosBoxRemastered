package jdos.win.builtin.user32;

import jdos.win.builtin.gdi32.WinBitmap;
import jdos.win.builtin.gdi32.WinDC;
import jdos.win.loader.BuiltinModule;
import jdos.win.loader.Loader;
import jdos.win.system.WinSystem;

public class User32 extends BuiltinModule {
    public User32(Loader loader, int handle) {
        super(loader, "user32.dll", handle);
        add(NonClient.class, "AdjustWindowRectEx",
            new String[] { "(RECT)lpRect", "(HEX)dwStyle", "(BOOL)bMenu", "(HEX)dwExStyle" });
        add(WinMenu.class, "AppendMenuA",
            new String[] { "hMenu", "(HEX)uFlags", "uIDNewItem", "(STRING)lpNewItem", "(BOOL)result" });
        add(Painting.class, "BeginPaint", new String[] { "hWnd", "(HEX)lps" });
        add(Hook.class, "CallNextHookEx", new String[] { "hhk", "nCode", "wParam", "lParam" });
        add(Winproc.class, "CallWindowProcA",
            MSGLOG ? new String[] { "lpPrevWndFunc", "hWnd", "(HEX)Msg", "wParam", "lParam" } : null);
        add(Str.class, "CharUpperA", new String[] { "(STRING)lpsz", "(STRING)result" });
        add(Str.class, "CharUpperBuffA", new String[] { "(STRING)lpsz", "cchLength", "result", "00(STRING)lpsz" });
        add(WinMenu.class, "CheckMenuItem", new String[] { "hmenu", "uIDCheckItem", "uCheck" });
        add(WinPos.class, "ClientToScreen", new String[] { "hWnd", "(POINT)lpPoint", "(BOOL)result" });
        add(UiTools.class, "CopyRect",
            new String[] { "(HEX)lprcDst", "(RECT)lprcSrc", "(BOOL)result", "00(RECT)lprcDst" });
        add(WinDialog.class, "CreateDialogIndirectParamA",
            new String[] { "hInstance", "(HEX)lpTemplate", "hWndParent", "(HEX)lpDialogFunc", "lParamInit" });
        add(WinMenu.class, "CreatePopupMenu", new String[0]);
        //add(WinRegion.class, "CreateRectRgn", new String[] {"left", "top", "right", "bottom"});
        add(WinWindow.class, "CreateWindowExA",
            new String[] { "(HEX)dwExStyle", "(STRING)lpClassName", "(STRING)lpWindowName", "(HEX)dwStyle", "x", "y",
                "nWidth", "nHeight", "hWndParent", "hMenu", "hInstance", "lpParam" });
        add(DefDlg.class, "DefDlgProcA", MSGLOG ? new String[] { "hWnd", "(HEX)Msg", "wParam", "lParam" } : null);
        add(DefWnd.class, "DefWindowProcA", MSGLOG ? new String[] { "hWnd", "(HEX)Msg", "wParam", "lParam" } : null);
        add(WinWindow.class, "DestroyWindow", new String[] { "hWnd" });
        add(Message.class, "DispatchMessageA", MSGLOG ? new String[] { "(MSG)lpmsg" } : null);
        add(UiTools.class, "DrawEdge",
            new String[] { "hdc", "(RECT)qrc", "(HEX)edge", "(HEX)grfFlags", "(BOOL)result" });
        add(UiTools.class, "DrawFocusRect", new String[] { "hDC", "(RECT)lprc" });
        add(UiTools.class, "DrawFrameControl", new String[] { "hdc", "(RECT)lprc", "uType", "uState", "(BOOL)result" });
        add(WinIcon.class, "DrawIcon", new String[] { "hdc", "xLeft", "yTop", "hIcon", "(BOOL)result" });
        add(WinIcon.class, "DrawIconEx", new String[] { "hdc", "xLeft", "yTop", "hIcon", "cxWidth", "cyWidth",
            "istepIfAniCur", "hbrFlickerFreeDraw", "(HEX)diFlags", "(BOOL)result" });
        add(WinMenu.class, "DrawMenuBar", new String[] { "hWnd", "(BOOL)result" });
        add(UiTools.class, "DrawStateA", new String[] { "hdc", "hbr", "(HEX)lpOutputFunc", "lData", "wData", "x", "y",
            "cx", "cy", "(HEX)fuFlags", "(BOOL)result" });
        add(WinText.class, "DrawTextA",
            new String[] { "hDC", "(STRINGN2)lpchText", "nCount", "(RECT)lpRect", "(HEX)uFormat" });
        add(WinText.class, "DrawTextExA", new String[] { "hdc", "(STRINGN2)lpchText", "cchText", "(RECT)lprc",
            "(HEX)dwDTFormat", "(HEX)lpDTParams" });
        add(Painting.class, "Ellipse",
            new String[] { "hdc", "nLeftRect", "nTopRect", "nRightRect", "nBottomRect", "(BOOL)result" });
        add(WinMenu.class, "EnableMenuItem", new String[] { "hMenu", "uIDEnableItem", "uEnable", "(BOOL)result" });
        add(WinWindow.class, "EnableWindow", new String[] { "hWnd", "(BOOL)bEnable" });
        add(WinDialog.class, "EndDialog", new String[] { "hDlg", "nResult", "(BOOL)result" });
        add(Painting.class, "EndPaint", new String[] { "hWnd", "lpPaint" });
        add(WinWindow.class, "EnumWindows", new String[] { "(HEX)lpEnumFunc", "lParam" });
        add(WinDC.class, "FillRect", new String[] { "hDC", "(RECT)lprc", "(BRUSH)hbr" });
        add(WinWindow.class, "FindWindowA", new String[] { "(STRING)lpClassName", "(STRING)lpWindowName" });
        add(WinWindow.class, "FindWindowExA",
            new String[] { "hwndParent", "hwndChildAfter", "(STRING)lpszClass", "(STRING)lpszWindow" });
        add(UiTools.class, "FrameRect", new String[] { "hdc", "(RECT)rect", "hbrush" });
        add(Focus.class, "GetActiveWindow", new String[0]);
        add(Input.class, "GetAsyncKeyState", new String[] { "nVirtKey" });
        add(Input.class, "GetCapture", new String[0]);
        add(WinClass.class, "GetClassInfoA", new String[] { "hInstance", "(STRING)lpClassName", "(HEX)lpWndClass" });
        add(WinClass.class, "GetClassLongA", new String[] { "hWnd", "nIndex" });
        add(WinClass.class, "GetClassNameA",
            new String[] { "hWnd", "(HEX)lpClassName", "nMaxCount", "result", "01(STRING)lpClassName" });
        add(WinPos.class, "GetClientRect", new String[] { "hWnd", "(HEX)lpRect", "(BOOL)result", "01(RECT)lpRect" });
        add(Input.class, "GetCursorPos", new String[] { "(HEX)lpPoint", "(BOOL)result", "00(POINT)lpPoint" });
        add(Painting.class, "GetDC", new String[] { "hwnd" });
        add(WinWindow.class, "GetDesktopWindow", new String[0]);
        add(WinDialog.class, "GetDlgCtrlID", new String[] { "hwndCtl" });
        add(WinDialog.class, "GetDlgItem", new String[] { "hwndDlg", "id" });
        add(Focus.class, "GetForegroundWindow", new String[0]);
        add(Input.class, "GetKeyboardState", new String[] { "(HEX)lpKeyState" });
        add(Input.class, "GetKeyNameTextA",
            new String[] { "lParam", "(HEX)lpString", "cchSize", "result", "01(STRING)lpString" });
        add(Input.class, "GetKeyState", new String[] { "nVirtKey" });
        add(WinWindow.class, "GetLastActivePopup", new String[] { "hWnd" });
        add(WinMenu.class, "GetMenu", new String[] { "hWnd" });
        add(WinMenu.class, "GetMenuItemCount", new String[] { "hMenu" });
        add_wait(Message.class, "GetMessageA", MSGLOG ? new String[] { "(HEX)lpMsg", "hWnd", "wMsgFilterMin",
            "wMsgFilterMax", "(BOOL)result", "00(MSG)lpMsg" } : null);
        add(Message.class, "GetMessagePos", new String[0]);
        add(Message.class, "GetMessageTime", new String[0]);
        add(WinDialog.class, "GetNextDlgGroupItem", new String[] { "hDlg", "hCtl", "(BOOL)bPrevious" });
        add(WinWindow.class, "GetParent", new String[] { "hWnd" });
        add(Property.class, "GetPropA", new String[] { "hWnd", "(STRING)lpString" });
        add(WinMenu.class, "GetSubMenu", new String[] { "hMenu", "nPos" });
        add(SysParams.class, "GetSysColor", new String[] { "nIndex" });
        add(SysParams.class, "GetSystemMetrics", new String[] { "index" });
        add(SysParams.class, "GetSysColorBrush", new String[] { "nIndex" });
        add(WinMenu.class, "GetSystemMenu", new String[] { "hWnd", "(BOOL)bRevert" });
        add(WinWindow.class, "GetTopWindow", new String[] { "hWnd" });
        add(Painting.class, "GetUpdateRgn", new String[] { "hWnd", "hRgn", "(BOOL)bErase" });
        add(WinWindow.class, "GetWindow", new String[] { "hwnd", "rel" });
        add(WinWindow.class, "GetWindowLongA", new String[] { "hwnd", "offset" });
        add(WinPos.class, "GetWindowPlacement", new String[] { "hWnd", "lpwndpl" });
        add(WinPos.class, "GetWindowRect", new String[] { "hwnd", "(HEX)pRect", "(BOOL)result", "01(RECT)pRect" });
        add(WinWindow.class, "GetWindowTextA",
            new String[] { "hWnd", "(HEX)lpString", "nMaxCount", "result", "01(STRING)lpString" });
        add(WinWindow.class, "GetWindowTextLengthA", new String[] { "hWnd" });
        add(WinWindow.class, "GetWindowThreadProcessId", new String[] { "hWnd", "(HEX)lpProcess", "(HEX)result" });
        add(UiTools.class, "InflateRect", new String[] { "(RECT)lprc", "dx", "dy", "(BOOL)result", "00(RECT)lprc" });
        add(UiTools.class, "IntersectRect",
            new String[] { "(HEX)lprcDst", "(RECT)lprcSrc1", "(RECT)lprcSrc2", "(BOOL)result", "00(RECT)lprcDst" });
        add(Painting.class, "InvalidateRect", new String[] { "hWnd", "(RECT)lpRect", "(BOOL)bErase" });
        add(WinDialog.class, "IsDialogMessageA", new String[] { "hDlg", "(HEX)lpMsg" });
        add(WinPos.class, "IsIconic", new String[] { "hWnd", "(BOOL)result" });
        add(UiTools.class, "IsRectEmpty", new String[] { "(RECT)lprc", "(BOOL)result" });
        add(WinWindow.class, "IsWindow", new String[] { "hWnd" });
        add(WinWindow.class, "IsWindowEnabled", new String[] { "hWnd" });
        add(WinWindow.class, "IsWindowVisible", new String[] { "hWnd", "(BOOL)result" });
        add(Resource.class, "LoadAcceleratorsA", new String[] { "hInstance", "(STRING)lpTableName" });
        add(WinBitmap.class, "LoadBitmapA", new String[] { "hInstance", "(STRING)lpBitmapName" });
        add(WinCursor.class, "LoadCursorA", new String[] { "hInstance", "(STRING)lpCursorName" });
        add(WinIcon.class, "LoadIconA", new String[] { "hInstance", "(STRING)lpIconName" });
        add(Resource.class, "LoadImageA",
            new String[] { "hinst", "(STRING)lpszName", "uType", "cxDesired", "cyDesired", "fuLoad" });
        add(Resource.class, "LoadStringA",
            new String[] { "hInstance", "uID", "(HEX)lpBuffer", "nBufferMax", "result", "02(STRING)lpBuffer" });
        add(WinMenu.class, "LoadMenuA", new String[] { "hInstance", "(STRING)lpMenuName" });
        add(Input.class, "MapVirtualKeyA", new String[] { "uCode", "uMapType" });
        add(WinPos.class, "MapWindowPoints",
            new String[] { "hWndFrom", "hWndTo", "(POINT)lpPoints", "cPoints", "(HEX)result", "02(POINT)lpPoints" });
        add(Message.class, "MessageBeep", new String[] { "uType" });
        add(MsgBox.class, "MessageBoxA", new String[] { "hWnd", "(STRING)lpText", "(STRING)lpCaption", "uType" });
        add(WinMenu.class, "ModifyMenuA",
            new String[] { "hMnu", "uPosition", "(HEX)uFlags", "uIDNewItem", "(STRING)lpNewItem", "(BOOL)result" });
        add(WinPos.class, "MoveWindow",
            new String[] { "hWnd", "X", "Y", "nWidth", "nHeight", "(BOOL)bRepaint", "(BOOL)result" });
        add(UiTools.class, "OffsetRect", new String[] { "(RECT)lprc", "dx", "dy", "(BOOL)result", "00(RECT)lprc" });
        add(Message.class, "PeekMessageA", MSGLOG ? new String[] { "(HEX)lpMsg", "hWnd", "wMsgFilterMin",
            "wMsgFilterMax", "wRemoveMsg", "(BOOL)result", "00(MSG)lpMsg" } : null);
        add(Painting.class, "Pie", new String[] { "hdc", "nLeftRect", "nTopRect", "nRightRect", "nBottomRect",
            "nXRadial1", "nYRadial1", "nXRadial2", "nYRadial2" });
        add(Message.class, "PostMessageA", MSGLOG ? new String[] { "hWnd", "(MSG)Msg", "wParam", "lParam" } : null);
        add(Painting.class, "Rectangle",
            new String[] { "hdc", "nLeftRect", "nTopRect", "nRightRect", "nBottomRect", "(BOOL)result" });
        add(Painting.class, "RedrawWindow",
            new String[] { "hWnd", "(RECT)lprcUpdate", "hrgnUpdate", "(HEX)flags", "(BOOL)result" });
        add(WinClass.class, "RegisterClassA", new String[] { "(CLASS)lpWndClass" });
        add(WinClass.class, "RegisterClassExA", new String[] { "(CLASS)lpwcx" });
        add(Message.class, "RegisterWindowMessageA", new String[] { "(STRING)lpString" });
        add(Input.class, "ReleaseCapture", new String[] { "(BOOL)result" });
        add(Painting.class, "ReleaseDC", new String[] { "hWnd", "hDC" });
        add(Property.class, "RemovePropA", new String[] { "hWnd", "(STRING)lpString" });
        add(WinPos.class, "ScreenToClient",
            new String[] { "hWnd", "(POINT)lpPoint", "(BOOL)result", "01(POINT)lpPoint" });
        add(WinDialog.class, "SendDlgItemMessageA",
            new String[] { "hDlg", "nIDDlgItem", "(HEX)Msg", "wParam", "lParam" });
        add(Message.class, "SendMessageA", new String[] { "hWnd", "(HEX)msg", "wParam", "lParam" });
        add(Focus.class, "SetActiveWindow", new String[] { "hWnd" });
        add(Input.class, "SetCapture", new String[] { "hWnd" });
        add(WinClass.class, "SetClassLongA", new String[] { "hWnd", "nIndex", "dwNewLong" });
        add(WinCursor.class, "SetCursor", new String[] { "hCursor" });
        add(Input.class, "SetCursorPos", new String[] { "X", "Y", "(BOOL)result" });
        add(WinDialog.class, "SetDlgItemTextA",
            new String[] { "hDlg", "nIDDlgItem", "(STRING)lpString", "(BOOL)result" });
        add(Focus.class, "SetFocus", new String[] { "hwnd" });
        add(Focus.class, "SetForegroundWindow", new String[] { "hWnd", "(BOOL)result" });
        add(WinMenu.class, "SetMenu", new String[] { "hWnd", "hMenu", "(BOOL)result" });
        add(WinMenu.class, "SetMenuItemInfoA",
            new String[] { "hMenu", "uItem", "(BOOL)fByPosition", "lpmii", "(BOOL)result" });
        add(WinWindow.class, "SetParent", new String[] { "hWndChild", "hWndNewParent" });
        add(Property.class, "SetPropA", new String[] { "hWnd", "(STRING)lpString", "hData" });
        add(UiTools.class, "SetRect",
            new String[] { "(HEX)lprc", "xLeft", "yTop", "xRight", "yBottom", "(BOOL)result", "00(RECT)lprc" });
        add(UiTools.class, "SetRectEmpty", new String[] { "(RECT)lprc", "(BOOL)result", "00(RECT)lprc" });
        add(Message.class, "SetTimer", new String[] { "hWnd", "nIDEvent", "uElapse", "(HEX)lpTimerFunc" });
        add(WinWindow.class, "SetWindowLongA", new String[] { "hWnd", "nIndex", "dwNewLong" });
        add(WinPos.class, "SetWindowPos",
            new String[] { "hWnd", "hWndInsertAfter", "X", "Y", "cx", "cy", "(HEX)uFlags" });
        add(Hook.class, "SetWindowsHookExA", new String[] { "idHook", "(HEX)lpfn", "hMod", "dwThreadId" });
        add(WinWindow.class, "SetWindowTextA", new String[] { "hWnd", "(STRING)lpString", "(BOOL)result" });
        add(WinCursor.class, "ShowCursor", new String[] { "(BOOL)bShow" });
        add(WinPos.class, "ShowWindow", new String[] { "hWnd", "nCmdShow", "(BOOL)result" });
        add(SysParams.class, "SystemParametersInfoA", new String[] { "uiAction", "uiParam", "pvParam", "fWinIni" });
        add(WinMenu.class, "TranslateAcceleratorA", MSGLOG ? new String[] { "hWnd", "hAccTable", "(MSG)lpMsg" } : null);
        add(Message.class, "TranslateMessage", MSGLOG ? new String[] { "(MSG)lpMsg", "(BOOL)result" } : null);
        add(WinClass.class, "UnregisterClassA", new String[] { "(STRING)lpClassName", "hInstance", "(BOOL)result" });
        add(Painting.class, "UpdateWindow", new String[] { "hWnd", "(BOOL)result" });
        add(Painting.class, "ValidateRect", new String[] { "hWnd", "(RECT)lpRect", "(BOOL)result" });
        add_wait(Message.class, "WaitForInputIdle", new String[] { "hProcess", "dwMilliseconds" });
        add_wait(Message.class, "WaitMessage", new String[] { "(BOOL)result" });
        add(WinPos.class, "WindowFromPoint", new String[] { "(POINT)Point" });
        add_cdecl(Wsprintf.class, "wsprintfA",
            new String[] { "(HEX)lpOut", "(STRING)lpFmt", "result", "00(STRING)lpOut" });

        WinDialog.registerClass(this, WinSystem.getCurrentProcess());
    }
}
