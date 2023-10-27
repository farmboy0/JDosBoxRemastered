package jdos.gui;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import jdos.sdl.JavaMapper;

public class KeyboardKey {
    public static final JavaMapper.DefaultKey[] DefaultKeys = { //
        new JavaMapper.DefaultKey("f1", NativeKeyEvent.VC_F1), //
        new JavaMapper.DefaultKey("f2", NativeKeyEvent.VC_F2), //
        new JavaMapper.DefaultKey("f3", NativeKeyEvent.VC_F3), //
        new JavaMapper.DefaultKey("f4", NativeKeyEvent.VC_F4), //
        new JavaMapper.DefaultKey("f5", NativeKeyEvent.VC_F5), //
        new JavaMapper.DefaultKey("f6", NativeKeyEvent.VC_F6), //
        new JavaMapper.DefaultKey("f7", NativeKeyEvent.VC_F7), //
        new JavaMapper.DefaultKey("f8", NativeKeyEvent.VC_F8), //
        new JavaMapper.DefaultKey("f9", NativeKeyEvent.VC_F9), //
        new JavaMapper.DefaultKey("f10", NativeKeyEvent.VC_F10), //
        new JavaMapper.DefaultKey("f11", NativeKeyEvent.VC_F11), //
        new JavaMapper.DefaultKey("f12", NativeKeyEvent.VC_F12), //

        new JavaMapper.DefaultKey("1", NativeKeyEvent.VC_1), //
        new JavaMapper.DefaultKey("2", NativeKeyEvent.VC_2), //
        new JavaMapper.DefaultKey("3", NativeKeyEvent.VC_3), //
        new JavaMapper.DefaultKey("4", NativeKeyEvent.VC_4), //
        new JavaMapper.DefaultKey("5", NativeKeyEvent.VC_5), //
        new JavaMapper.DefaultKey("6", NativeKeyEvent.VC_6), //
        new JavaMapper.DefaultKey("7", NativeKeyEvent.VC_7), //
        new JavaMapper.DefaultKey("8", NativeKeyEvent.VC_8), //
        new JavaMapper.DefaultKey("9", NativeKeyEvent.VC_9), //
        new JavaMapper.DefaultKey("0", NativeKeyEvent.VC_0), //

        new JavaMapper.DefaultKey("a", NativeKeyEvent.VC_A), //
        new JavaMapper.DefaultKey("b", NativeKeyEvent.VC_B), //
        new JavaMapper.DefaultKey("c", NativeKeyEvent.VC_C), //
        new JavaMapper.DefaultKey("d", NativeKeyEvent.VC_D), //
        new JavaMapper.DefaultKey("e", NativeKeyEvent.VC_E), //
        new JavaMapper.DefaultKey("f", NativeKeyEvent.VC_F), //
        new JavaMapper.DefaultKey("g", NativeKeyEvent.VC_G), //
        new JavaMapper.DefaultKey("h", NativeKeyEvent.VC_H), //
        new JavaMapper.DefaultKey("i", NativeKeyEvent.VC_I), //
        new JavaMapper.DefaultKey("j", NativeKeyEvent.VC_J), //
        new JavaMapper.DefaultKey("k", NativeKeyEvent.VC_K), //
        new JavaMapper.DefaultKey("l", NativeKeyEvent.VC_L), //
        new JavaMapper.DefaultKey("m", NativeKeyEvent.VC_M), //
        new JavaMapper.DefaultKey("n", NativeKeyEvent.VC_N), //
        new JavaMapper.DefaultKey("o", NativeKeyEvent.VC_O), //
        new JavaMapper.DefaultKey("p", NativeKeyEvent.VC_P), //
        new JavaMapper.DefaultKey("q", NativeKeyEvent.VC_Q), //
        new JavaMapper.DefaultKey("r", NativeKeyEvent.VC_R), //
        new JavaMapper.DefaultKey("s", NativeKeyEvent.VC_S), //
        new JavaMapper.DefaultKey("t", NativeKeyEvent.VC_T), //
        new JavaMapper.DefaultKey("u", NativeKeyEvent.VC_U), //
        new JavaMapper.DefaultKey("v", NativeKeyEvent.VC_V), //
        new JavaMapper.DefaultKey("w", NativeKeyEvent.VC_W), //
        new JavaMapper.DefaultKey("x", NativeKeyEvent.VC_X), //
        new JavaMapper.DefaultKey("y", NativeKeyEvent.VC_Y), //
        new JavaMapper.DefaultKey("z", NativeKeyEvent.VC_Z), //
        new JavaMapper.DefaultKey("space", NativeKeyEvent.VC_SPACE), //

        new JavaMapper.DefaultKey("esc", NativeKeyEvent.VC_ESCAPE), //
        new JavaMapper.DefaultKey("equals", NativeKeyEvent.VC_EQUALS), //
        new JavaMapper.DefaultKey("grave", NativeKeyEvent.VC_BACKQUOTE), //
        new JavaMapper.DefaultKey("tab", NativeKeyEvent.VC_TAB), //
        new JavaMapper.DefaultKey("enter", NativeKeyEvent.VC_ENTER), //
        new JavaMapper.DefaultKey("bspace", NativeKeyEvent.VC_BACKSPACE), //
        new JavaMapper.DefaultKey("lbracket", NativeKeyEvent.VC_OPEN_BRACKET), //
        new JavaMapper.DefaultKey("rbracket", NativeKeyEvent.VC_CLOSE_BRACKET), //
        new JavaMapper.DefaultKey("minus", NativeKeyEvent.VC_MINUS), //
        new JavaMapper.DefaultKey("capslock", NativeKeyEvent.VC_CAPS_LOCK), //
        new JavaMapper.DefaultKey("semicolon", NativeKeyEvent.VC_SEMICOLON), //
        new JavaMapper.DefaultKey("quote", NativeKeyEvent.VC_QUOTE), //
        new JavaMapper.DefaultKey("backslash", NativeKeyEvent.VC_BACK_SLASH), //
        new JavaMapper.DefaultKey("lshift", NativeKeyEvent.VC_SHIFT, true, false, false), //
        new JavaMapper.DefaultKey("rshift", NativeKeyEvent.VC_SHIFT, false, true, false), //
        new JavaMapper.DefaultKey("lalt", NativeKeyEvent.VC_ALT, true, false, false), //
        new JavaMapper.DefaultKey("ralt", NativeKeyEvent.VC_ALT, false, true, false), //
        new JavaMapper.DefaultKey("lctrl", NativeKeyEvent.VC_CONTROL, true, false, false), //
        new JavaMapper.DefaultKey("rctrl", NativeKeyEvent.VC_CONTROL, false, true, false), //
        new JavaMapper.DefaultKey("comma", NativeKeyEvent.VC_COMMA), //
        new JavaMapper.DefaultKey("period", NativeKeyEvent.VC_PERIOD), //
        new JavaMapper.DefaultKey("slash", NativeKeyEvent.VC_SLASH), //

        new JavaMapper.DefaultKey("printscreen", NativeKeyEvent.VC_PRINTSCREEN), //
        new JavaMapper.DefaultKey("scrolllock", NativeKeyEvent.VC_SCROLL_LOCK), //
        new JavaMapper.DefaultKey("pause", NativeKeyEvent.VC_PAUSE), //

        new JavaMapper.DefaultKey("pagedown", NativeKeyEvent.VC_PAGE_DOWN), //
        new JavaMapper.DefaultKey("pageup", NativeKeyEvent.VC_PAGE_UP), //
        new JavaMapper.DefaultKey("insert", NativeKeyEvent.VC_INSERT), //
        new JavaMapper.DefaultKey("home", NativeKeyEvent.VC_HOME), //
        new JavaMapper.DefaultKey("delete", NativeKeyEvent.VC_DELETE), //
        new JavaMapper.DefaultKey("end", NativeKeyEvent.VC_END), //

        new JavaMapper.DefaultKey("up", NativeKeyEvent.VC_UP), //
        new JavaMapper.DefaultKey("left", NativeKeyEvent.VC_LEFT), //
        new JavaMapper.DefaultKey("down", NativeKeyEvent.VC_DOWN), //
        new JavaMapper.DefaultKey("right", NativeKeyEvent.VC_RIGHT), //

        new JavaMapper.DefaultKey("kp_0", NativeKeyEvent.VC_0, false, false, true), //
        new JavaMapper.DefaultKey("kp_1", NativeKeyEvent.VC_1, false, false, true), //
        new JavaMapper.DefaultKey("kp_2", NativeKeyEvent.VC_2, false, false, true), //
        new JavaMapper.DefaultKey("kp_3", NativeKeyEvent.VC_3, false, false, true), //
        new JavaMapper.DefaultKey("kp_4", NativeKeyEvent.VC_4, false, false, true), //
        new JavaMapper.DefaultKey("kp_5", NativeKeyEvent.VC_5, false, false, true), //
        new JavaMapper.DefaultKey("kp_6", NativeKeyEvent.VC_6, false, false, true), //
        new JavaMapper.DefaultKey("kp_7", NativeKeyEvent.VC_7, false, false, true), //
        new JavaMapper.DefaultKey("kp_8", NativeKeyEvent.VC_8, false, false, true), //
        new JavaMapper.DefaultKey("kp_9", NativeKeyEvent.VC_9, false, false, true), //
        new JavaMapper.DefaultKey("numlock", NativeKeyEvent.VC_NUM_LOCK), //
        new JavaMapper.DefaultKey("kp_divide", NativeKeyEvent.VC_SLASH, false, false, true), //
        new JavaMapper.DefaultKey("kp_multiply", NativeKeyEvent.VC_PRINTSCREEN, false, false, true), //
        new JavaMapper.DefaultKey("kp_minus", JDOSNativeKeyAdapter.VC_SUBTRACT, false, false, true), //
        new JavaMapper.DefaultKey("kp_plus", JDOSNativeKeyAdapter.VC_PLUS, false, false, true), //
        new JavaMapper.DefaultKey("kp_period", NativeKeyEvent.VC_PERIOD, false, false, true), //
        new JavaMapper.DefaultKey("kp_enter", NativeKeyEvent.VC_ENTER, false, false, true), //
        new JavaMapper.DefaultKey("lessthan", 0) //
    };
    static boolean ctrAltDel = false;

    public static boolean isLeft(NativeKeyEvent key) {
        return key.getKeyLocation() == NativeKeyEvent.KEY_LOCATION_LEFT;
    }

    public static boolean isRight(NativeKeyEvent key) {
        return key.getKeyLocation() == NativeKeyEvent.KEY_LOCATION_RIGHT;
    }

    public static boolean isNumPad(NativeKeyEvent key) {
        return key.getKeyLocation() == NativeKeyEvent.KEY_LOCATION_NUMPAD;
    }

    public static boolean isPressed(NativeKeyEvent key) {
        return key.getID() == NativeKeyEvent.NATIVE_KEY_PRESSED;
    }

    public static boolean isReleased(NativeKeyEvent key) {
        return key.getID() == NativeKeyEvent.NATIVE_KEY_RELEASED;
    }

    public static int getKeyCode(NativeKeyEvent key) {
        return key.getKeyCode();
    }

    public static void CreateDefaultBinds() {
        JavaMapper.CreateStringBind("mod_1 \"key " + NativeKeyEvent.VC_CONTROL + " right\"");
        JavaMapper.CreateStringBind("mod_2 \"key " + NativeKeyEvent.VC_ALT + " right\"");
    }

    public static int translateMapKey(int key) {
        switch (key) {
            case Mapper.MapKeys.MK_f1:
            case Mapper.MapKeys.MK_f2:
            case Mapper.MapKeys.MK_f3:
            case Mapper.MapKeys.MK_f4:
            case Mapper.MapKeys.MK_f5:
            case Mapper.MapKeys.MK_f6:
            case Mapper.MapKeys.MK_f7:
            case Mapper.MapKeys.MK_f8:
            case Mapper.MapKeys.MK_f9:
            case Mapper.MapKeys.MK_f10:
            case Mapper.MapKeys.MK_f11:
            case Mapper.MapKeys.MK_f12:
                key = NativeKeyEvent.VC_F1 + key - Mapper.MapKeys.MK_f1;
                break;
            case Mapper.MapKeys.MK_return:
                key = NativeKeyEvent.VC_ENTER;
                break;
            case Mapper.MapKeys.MK_kpminus:
                key = NativeKeyEvent.VC_MINUS;
                break;
            case Mapper.MapKeys.MK_scrolllock:
                key = NativeKeyEvent.VC_SCROLL_LOCK;
                break;
            case Mapper.MapKeys.MK_pause:
                key = NativeKeyEvent.VC_PAUSE;
                break;
            case Mapper.MapKeys.MK_printscreen:
                key = NativeKeyEvent.VC_PRINTSCREEN;
                break;
            case Mapper.MapKeys.MK_home:
                key = NativeKeyEvent.VC_HOME;
                break;
        }
        return key;
    }

    public static void CheckEvent(NativeKeyEvent event) {
        if (JavaMapper.mapper.mods == 3 && event.getKeyCode() == NativeKeyEvent.VC_INSERT
            && event.getID() == NativeKeyEvent.NATIVE_KEY_PRESSED) {
            ctrAltDel = true;
        }
        if (ctrAltDel && event.getKeyCode() == NativeKeyEvent.VC_INSERT) {
            event.setKeyCode(NativeKeyEvent.VC_DELETE);
        }
        JavaMapper.MAPPER_CheckEvent(event);

        if (ctrAltDel && event.getKeyCode() == NativeKeyEvent.VC_INSERT
            && event.getID() == NativeKeyEvent.NATIVE_KEY_RELEASED) {
            ctrAltDel = false;
        }
    }
}
