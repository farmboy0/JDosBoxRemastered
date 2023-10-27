package jdos.win.system;

import java.util.BitSet;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import jdos.gui.Main;
import jdos.misc.Log;
import jdos.types.LogSeverities;
import jdos.types.LogTypes;
import jdos.win.builtin.WinAPI;
import jdos.win.builtin.user32.Input;

public class WinKeyboard {
    public static BitSet keyState = new BitSet();
    public static Main.KeyboardHandler defaultKeyboardHandler = key -> {
        int result = 0;
        int additional = 0;
        boolean extended = false;

        switch (key.getKeyCode()) {
            case NativeKeyEvent.VC_ESCAPE:
                result = 0x1B;
                break;
            case NativeKeyEvent.VC_NUMPAD1:
                result = 0x61;
                break;
            case NativeKeyEvent.VC_1:
                result = 0x31;
                break;
            case NativeKeyEvent.VC_NUMPAD2:
                result = 0x62;
                break;
            case NativeKeyEvent.VC_2:
                result = 0x32;
                break;
            case NativeKeyEvent.VC_NUMPAD3:
                result = 0x63;
                break;
            case NativeKeyEvent.VC_3:
                result = 0x33;
                break;
            case NativeKeyEvent.VC_NUMPAD4:
                result = 0x64;
                break;
            case NativeKeyEvent.VC_4:
                result = 0x34;
                break;
            case NativeKeyEvent.VC_NUMPAD5:
                result = 0x65;
                break;
            case NativeKeyEvent.VC_5:
                result = 0x35;
                break;
            case NativeKeyEvent.VC_NUMPAD6:
                result = 0x66;
                break;
            case NativeKeyEvent.VC_6:
                result = 0x36;
                break;
            case NativeKeyEvent.VC_NUMPAD7:
                result = 0x67;
                break;
            case NativeKeyEvent.VC_7:
                result = 0x37;
                break;
            case NativeKeyEvent.VC_NUMPAD8:
                result = 0x68;
                break;
            case NativeKeyEvent.VC_8:
                result = 0x38;
                break;
            case NativeKeyEvent.VC_NUMPAD9:
                result = 0x69;
                break;
            case NativeKeyEvent.VC_9:
                result = 0x39;
                break;
            case NativeKeyEvent.VC_NUMPAD0:
                result = 0x60;
                break;
            case NativeKeyEvent.VC_0:
                result = 0x30;
                break;
            case NativeKeyEvent.VC_SUBTRACT:
                result = 0x6D;
                break;
            case NativeKeyEvent.VC_MINUS:
                result = 0xBD;
                break;
            case NativeKeyEvent.VC_EQUALS:
                result = 0xBB;
                break;
            case NativeKeyEvent.VC_BACK_SPACE:
                result = 0x08;
                break;
            case NativeKeyEvent.VC_TAB:
                result = 0x09;
                break;

            case NativeKeyEvent.VC_Q:
                result = 0x51;
                break;
            case NativeKeyEvent.VC_W:
                result = 0x57;
                break;
            case NativeKeyEvent.VC_E:
                result = 0x45;
                break;
            case NativeKeyEvent.VC_R:
                result = 0x52;
                break;
            case NativeKeyEvent.VC_T:
                result = 0x54;
                break;
            case NativeKeyEvent.VC_Y:
                result = 0x59;
                break;
            case NativeKeyEvent.VC_U:
                result = 0x55;
                break;
            case NativeKeyEvent.VC_I:
                result = 0x49;
                break;
            case NativeKeyEvent.VC_O:
                result = 0x4F;
                break;
            case NativeKeyEvent.VC_P:
                result = 0x50;
                break;

            case NativeKeyEvent.VC_OPEN_BRACKET:
                result = 0xDB;
                break;
            case NativeKeyEvent.VC_CLOSE_BRACKET:
                result = 0xDD;
                break;
            case NativeKeyEvent.VC_ENTER:
                result = 0x0D;
                break;
            case NativeKeyEvent.VC_CONTROL:
                if (key.getKeyLocation() == NativeKeyEvent.KEY_LOCATION_LEFT) {
                    additional = 0xA2;
                    extended = false;
                } else {
                    additional = 0xA3;
                    extended = true;
                }
                result = 0x11;
                break;
            case NativeKeyEvent.VC_A:
                result = 0x41;
                break;
            case NativeKeyEvent.VC_S:
                result = 0x53;
                break;
            case NativeKeyEvent.VC_D:
                result = 0x44;
                break;
            case NativeKeyEvent.VC_F:
                result = 0x46;
                break;
            case NativeKeyEvent.VC_G:
                result = 0x47;
                break;
            case NativeKeyEvent.VC_H:
                result = 0x48;
                break;
            case NativeKeyEvent.VC_J:
                result = 0x4A;
                break;
            case NativeKeyEvent.VC_K:
                result = 0x4B;
                break;
            case NativeKeyEvent.VC_L:
                result = 0x4C;
                break;

            case NativeKeyEvent.VC_SEMICOLON:
                result = 0xBA;
                break;
            case NativeKeyEvent.VC_QUOTE:
                result = 0xDE;
                break;
            case NativeKeyEvent.VC_BACK_QUOTE:
                result = 0xCE;
                break;
            case NativeKeyEvent.VC_SHIFT:
                if (key.getKeyLocation() == NativeKeyEvent.KEY_LOCATION_LEFT) {
                    extended = false;
                    additional = 0xA0;
                } else {
                    extended = true;
                    additional = 0xA1;
                }
                result = 0x10;
                break;
            case NativeKeyEvent.VC_BACK_SLASH:
                result = 0xDC;
                break;
            case NativeKeyEvent.VC_Z:
                result = 0x5A;
                break;
            case NativeKeyEvent.VC_X:
                result = 0x58;
                break;
            case NativeKeyEvent.VC_C:
                result = 0x43;
                break;
            case NativeKeyEvent.VC_V:
                result = 0x56;
                break;
            case NativeKeyEvent.VC_B:
                result = 0x42;
                break;
            case NativeKeyEvent.VC_N:
                result = 0x4E;
                break;
            case NativeKeyEvent.VC_M:
                result = 0x4D;
                break;

            case NativeKeyEvent.VC_COMMA:
                result = 0xBC;
                break;
            case NativeKeyEvent.VC_PERIOD:
                result = 0xBE;
                break;
            case NativeKeyEvent.VC_DECIMAL:
                result = 0x6E;
                break;
            case NativeKeyEvent.VC_SLASH:
                result = 0xBF;
                break;
            case NativeKeyEvent.VC_MULTIPLY:
                result = 0x6A;
                break;
            case NativeKeyEvent.VC_ALT:
                if (key.getKeyLocation() == NativeKeyEvent.KEY_LOCATION_LEFT) {
                    additional = 0xA4;
                    extended = false;
                } else {
                    additional = 0xA5;
                    extended = true;
                }
                result = 0x12;
                break;
            case NativeKeyEvent.VC_SPACE:
                result = 0x20;
                break;
            case NativeKeyEvent.VC_CAPS_LOCK:
                result = 0x14;
                break;

            case NativeKeyEvent.VC_F1:
                result = 0x70;
                break;
            case NativeKeyEvent.VC_F2:
                result = 0x71;
                break;
            case NativeKeyEvent.VC_F3:
                result = 0x72;
                break;
            case NativeKeyEvent.VC_F4:
                result = 0x73;
                break;
            case NativeKeyEvent.VC_F5:
                result = 0x74;
                break;
            case NativeKeyEvent.VC_F6:
                result = 0x75;
                break;
            case NativeKeyEvent.VC_F7:
                result = 0x76;
                break;
            case NativeKeyEvent.VC_F8:
                result = 0x77;
                break;
            case NativeKeyEvent.VC_F9:
                result = 0x78;
                break;
            case NativeKeyEvent.VC_F10:
                result = 0x79;
                break;

            case NativeKeyEvent.VC_NUM_LOCK:
                result = 0x90;
                break;
            case NativeKeyEvent.VC_SCROLL_LOCK:
                result = 0x91;
                break;

            case NativeKeyEvent.VC_PLUS:
                break;

            case NativeKeyEvent.VC_LESS:
                break;
            case NativeKeyEvent.VC_F11:
                result = 0x7A;
                break;
            case NativeKeyEvent.VC_F12:
                result = 0x7B;
                break;

            //The Extended keys

            case NativeKeyEvent.VC_DIVIDE:
                result = 0x6F;
                break;
            case NativeKeyEvent.VC_ADD:
                result = 0x6B;
                break;
            case NativeKeyEvent.VC_HOME:
                result = 0x24;
                break;
            case NativeKeyEvent.VC_UP:
                result = 0x26;
                break;
            case NativeKeyEvent.VC_PAGE_UP:
                result = 0x21;
                break;
            case NativeKeyEvent.VC_LEFT:
                result = 0x25;
                break;
            case NativeKeyEvent.VC_RIGHT:
                result = 0x27;
                break;
            case NativeKeyEvent.VC_END:
                result = 0x23;
                break;
            case NativeKeyEvent.VC_DOWN:
                result = 0x28;
                break;
            case NativeKeyEvent.VC_PAGE_DOWN:
                result = 0x22;
                break;
            case NativeKeyEvent.VC_INSERT:
                result = 0x2D;
                break;
            case NativeKeyEvent.VC_DELETE:
                result = 0x2E;
                break;
            case NativeKeyEvent.VC_PAUSE:
                result = 0x13;
                break;
            case NativeKeyEvent.VC_PRINTSCREEN:
                result = 0x2C;
                break;
            default:
                if (Log.level <= LogSeverities.LOG_WARN)
                    Log.log(LogTypes.LOG_GUI, LogSeverities.LOG_WARN, "Unknown key code: " + key.getKeyCode());
                return;
        }
        if (result != 0) {
            int repeatCount = 1;
            int oem = 0;
            int reserved = 0;
            int contextCode = 0;
            int previousState = 0;
            int transitionState = 0;
            int msg = 0;

            if (key.getID() == NativeKeyEvent.NATIVE_KEY_PRESSED) {
                msg = WinAPI.WM_KEYDOWN;
                transitionState = 0; // always 0
                if (keyState.get(result))
                    return;
                keyState.set(result);
                if (additional != 0)
                    keyState.set(additional);
            } else if (key.getID() == NativeKeyEvent.NATIVE_KEY_RELEASED) {
                msg = WinAPI.WM_KEYUP;
                repeatCount = 1; // repeat count is always 1
                previousState = 1; // always 1
                transitionState = 1; // always 1
                keyState.clear(result);
                if (additional != 0)
                    keyState.clear(additional);
            }
            if (msg != 0)
                Input.addKeyboardMsg(msg, result, repeatCount | oem << 16 | (extended ? 1 << 24 : 0) | contextCode << 29
                    | previousState << 30 | transitionState << 31, (BitSet) keyState.clone());
        }
    };

    public static int win2java(int winVirtualKeyCode) {
        switch (winVirtualKeyCode) {
            case 0x1B:
                return NativeKeyEvent.VC_ESCAPE;
            case 0x61:
                return NativeKeyEvent.VC_NUMPAD1;
            case 0x31:
                return NativeKeyEvent.VC_1;
            case 0x62:
                return NativeKeyEvent.VC_NUMPAD2;
            case 0x32:
                return NativeKeyEvent.VC_2;
            case 0x63:
                return NativeKeyEvent.VC_NUMPAD3;
            case 0x33:
                return NativeKeyEvent.VC_3;
            case 0x64:
                return NativeKeyEvent.VC_NUMPAD4;
            case 0x34:
                return NativeKeyEvent.VC_4;
            case 0x65:
                return NativeKeyEvent.VC_NUMPAD5;
            case 0x35:
                return NativeKeyEvent.VC_5;
            case 0x66:
                return NativeKeyEvent.VC_NUMPAD6;
            case 0x36:
                return NativeKeyEvent.VC_6;
            case 0x67:
                return NativeKeyEvent.VC_NUMPAD7;
            case 0x37:
                return NativeKeyEvent.VC_7;
            case 0x68:
                return NativeKeyEvent.VC_NUMPAD8;
            case 0x38:
                return NativeKeyEvent.VC_8;
            case 0x69:
                return NativeKeyEvent.VC_NUMPAD9;
            case 0x39:
                return NativeKeyEvent.VC_9;
            case 0x60:
                return NativeKeyEvent.VC_NUMPAD0;
            case 0x30:
                return NativeKeyEvent.VC_0;
            case 0x6D:
                return NativeKeyEvent.VC_SUBTRACT;
            //case NativeKeyEvent.VC_MINUS:break;
            //case NativeKeyEvent.VC_EQUALS:break;
            case 0x08:
                return NativeKeyEvent.VC_BACK_SPACE;
            case 0x09:
                return NativeKeyEvent.VC_TAB;

            case 0x51:
                return NativeKeyEvent.VC_Q;
            case 0x57:
                return NativeKeyEvent.VC_W;
            case 0x45:
                return NativeKeyEvent.VC_E;
            case 0x52:
                return NativeKeyEvent.VC_R;
            case 0x54:
                return NativeKeyEvent.VC_T;
            case 0x59:
                return NativeKeyEvent.VC_Y;
            case 0x55:
                return NativeKeyEvent.VC_U;
            case 0x49:
                return NativeKeyEvent.VC_I;
            case 0x4F:
                return NativeKeyEvent.VC_O;
            case 0x50:
                return NativeKeyEvent.VC_P;

            //case NativeKeyEvent.VC_OPEN_BRACKET:break;
            //case NativeKeyEvent.VC_CLOSE_BRACKET:break;
            case 0x0D:
                return NativeKeyEvent.VC_ENTER;
            case 0x11:
                return NativeKeyEvent.VC_CONTROL;
            case 0x41:
                return NativeKeyEvent.VC_A;
            case 0x53:
                return NativeKeyEvent.VC_S;
            case 0x44:
                return NativeKeyEvent.VC_D;
            case 0x46:
                return NativeKeyEvent.VC_F;
            case 0x47:
                return NativeKeyEvent.VC_G;
            case 0x48:
                return NativeKeyEvent.VC_H;
            case 0x4A:
                return NativeKeyEvent.VC_J;
            case 0x4B:
                return NativeKeyEvent.VC_K;
            case 0x4C:
                return NativeKeyEvent.VC_L;

            //case NativeKeyEvent.VC_SEMICOLON:break;
            //case NativeKeyEvent.VC_QUOTE:break;
            //case NativeKeyEvent.VC_BACK_QUOTE:break;
            case 0x10:
                return NativeKeyEvent.VC_SHIFT;

            //case NativeKeyEvent.VC_BACK_SLASH:break;
            case 0x5A:
                return NativeKeyEvent.VC_Z;
            case 0x58:
                return NativeKeyEvent.VC_X;
            case 0x43:
                return NativeKeyEvent.VC_C;
            case 0x56:
                return NativeKeyEvent.VC_V;
            case 0x42:
                return NativeKeyEvent.VC_B;
            case 0x4E:
                return NativeKeyEvent.VC_N;
            case 0x4D:
                return NativeKeyEvent.VC_M;

            //case NativeKeyEvent.VC_COMMA:break;
            //case NativeKeyEvent.VC_PERIOD:break;
            case 0x6E:
                return NativeKeyEvent.VC_DECIMAL;
            //case NativeKeyEvent.VC_SLASH:break;
            case 0x6A:
                return NativeKeyEvent.VC_MULTIPLY;
            case 0x12:
                return NativeKeyEvent.VC_ALT;
            case 0x20:
                return NativeKeyEvent.VC_SPACE;
            case 0x14:
                return NativeKeyEvent.VC_CAPS_LOCK;

            case 0x70:
                return NativeKeyEvent.VC_F1;
            case 0x71:
                return NativeKeyEvent.VC_F2;
            case 0x72:
                return NativeKeyEvent.VC_F3;
            case 0x73:
                return NativeKeyEvent.VC_F4;
            case 0x74:
                return NativeKeyEvent.VC_F5;
            case 0x75:
                return NativeKeyEvent.VC_F6;
            case 0x76:
                return NativeKeyEvent.VC_F7;
            case 0x77:
                return NativeKeyEvent.VC_F8;
            case 0x78:
                return NativeKeyEvent.VC_F9;
            case 0x79:
                return NativeKeyEvent.VC_F10;

            case 0x90:
                return NativeKeyEvent.VC_NUM_LOCK;
            case 0x91:
                return NativeKeyEvent.VC_SCROLL_LOCK;

            // case NativeKeyEvent.VC_PLUS:break;

            // case NativeKeyEvent.VC_LESS:break;
            case 0x7A:
                return NativeKeyEvent.VC_F11;
            case 0x7B:
                return NativeKeyEvent.VC_F12;

            //The Extended keys

            case 0x6F:
                return NativeKeyEvent.VC_DIVIDE;
            case 0x6B:
                return NativeKeyEvent.VC_ADD;
            case 0x24:
                return NativeKeyEvent.VC_HOME;
            case 0x26:
                return NativeKeyEvent.VC_UP;
            case 0x21:
                return NativeKeyEvent.VC_PAGE_UP;
            case 0x25:
                return NativeKeyEvent.VC_LEFT;
            case 0x27:
                return NativeKeyEvent.VC_RIGHT;
            case 0x23:
                return NativeKeyEvent.VC_END;
            case 0x28:
                return NativeKeyEvent.VC_DOWN;
            case 0x22:
                return NativeKeyEvent.VC_PAGE_DOWN;
            case 0x2D:
                return NativeKeyEvent.VC_INSERT;
            case 0x2E:
                return NativeKeyEvent.VC_DELETE;
            case 0x13:
                return NativeKeyEvent.VC_PAUSE;
            case 0x2C:
                return NativeKeyEvent.VC_PRINTSCREEN;
        }
        return -1;
    }
}