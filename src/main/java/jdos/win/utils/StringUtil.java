package jdos.win.utils;

import java.util.Vector;

import jdos.hardware.Memory;
import jdos.win.builtin.WinAPI;
import jdos.win.system.WinSystem;

public class StringUtil extends WinAPI {
    public static String[] split(final String input, String delimiter) {
        if (input != null && input.length() > 0) {
            int index1 = 0;
            int index2 = input.indexOf(delimiter);
            Vector result = new Vector();
            while (index2 >= 0) {
                String token = input.substring(index1, index2);
                result.addElement(token);
                index1 = index2 + delimiter.length();
                index2 = input.indexOf(delimiter, index1);
            }
            if (index1 <= input.length() - 1) {
                result.addElement(input.substring(index1));
            }
            String[] sda = new String[result.size()];
            result.copyInto(sda);
            return sda;
        }
        return new String[0];
    }

    public static int strlenA(int str) {
        int s = str;
        while (Memory.mem_readb(s) != 0)
            s++;
        return s - str;
    }

    public static int strlenW(int str) {
        int s = str;
        while (Memory.mem_readw(s) != 0)
            s += 2;
        return (s - str) / 2;
    }

    public static String getString(int address) {
        StringBuilder result = new StringBuilder();
        while (true) {
            char c = (char) Memory.mem_readb(address++); // :TODO: need to research converting according to 1252
            if (c == 0)
                break;
            result.append(c);
        }
        return result.toString();
    }

    public static String getStringW(int address) {
        StringBuilder result = new StringBuilder();
        while (true) {
            char c = (char) Memory.mem_readw(address);
            address += 2;
            if (c == 0)
                break;
            result.append(c);
        }
        return result.toString();
    }

    public static String getString(int address, int count) {
        if (count == -1)
            return getString(address);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            char c = (char) Memory.mem_readb(address++); // :TODO: need to research converting according to 1252
            result.append(c);
        }
        return result.toString();
    }

    public static String getStringW(int address, int count) {
        if (count == -1)
            return getStringW(address);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            char c = (char) Memory.mem_readw(address);
            address += 2;
            result.append(c);
        }
        return result.toString();
    }

    public static int strrchr(int address, int c) {
        int len = strlenA(address);
        for (int i = len - 1; i >= 0; i++) {
            if (readb(address + i) == c)
                return address + i;
        }
        return 0;
    }

    public static void strcat(int address, int address2) {
        int len = strlenA(address);
        strcpy(address + len, address2);
    }

    public static void strcpy(int address, String value) {
        byte[] b = value.getBytes();
        Memory.mem_memcpy(address, b, 0, b.length);
        Memory.mem_writeb(address + b.length, 0);
    }

    public static void strcpy(int address, int src) {
        int len = strlenA(src);
        Memory.mem_memcpy(address, src, len + 1);
    }

    public static int strncpy(int address, String value, int count) {
        byte[] b = value.getBytes();
        if (b.length + 1 < count)
            count = b.length + 1;
        Memory.mem_memcpy(address, b, 0, count - 1);
        Memory.mem_writeb(address + count - 1, 0);
        return count - 1;
    }

    public static int strncpy(int address, int address2, int count) {
        int i;
        for (i = 0; i < count - 1; i++) {
            int c = Memory.mem_readb(address2 + i);
            if (c == 0)
                break;
            Memory.mem_writeb(address + i, c);
        }
        Memory.mem_writeb(address + count - 1, 0);
        return i;
    }

    public static int strncmp(int s1, int s2, int count) {
        for (int i = 0; i < count; i++) {
            int c1 = Memory.mem_readb(s1 + i);
            int c2 = Memory.mem_readb(s2 + i);
            if (c1 == c2) {
                if (c1 == 0)
                    return 0;
            } else if (c1 < c2)
                return -1;
            else
                return 1;
        }
        return 0;
    }

    public static int strncmp(int s1, String s2, int count) {
        for (int i = 0; i < count && i < s2.length(); i++) {
            int c1 = Memory.mem_readb(s1 + i);
            int c2 = s2.charAt(i);
            if (c1 == c2) {
                if (c1 == 0)
                    return 0;
            } else if (c1 < c2)
                return -1;
            else
                return 1;
        }
        return 0;
    }

    public static int strcmp(int s1, int s2) {
        while (true) {
            int c1 = Memory.mem_readb(s1++);
            int c2 = Memory.mem_readb(s2++);

            if (c1 < c2)
                return -1;
            else if (c1 > c2)
                return 1;

            if (c1 == 0 && c1 == c2) {
                return 0;
            }
            if (c1 == 0)
                return -1;
            if (c2 == 0)
                return 1;
        }
    }

    public static void strcpyW(int address, String value) {
        char[] c = value.toCharArray();
        for (char element : c) {
            Memory.mem_writew(address, element);
            address += 2;
        }
        Memory.mem_writew(address, 0);
    }

    public static void strncpyW(int address, String value, int count) {
        char[] c = value.toCharArray();
        if (c.length + 1 < count)
            count = c.length + 1;
        for (int i = 0; i < count - 1; i++) {
            Memory.mem_writew(address, c[i]);
            address += 2;
        }
        Memory.mem_writew(address, 0);
    }

    public static char tolowerW(char w) {
        return Character.toString(w).toLowerCase().charAt(0);
    }

    public static char toupperW(char w) {
        return Character.toString(w).toUpperCase().charAt(0);
    }

    public static void _strupr(int str) {
        while (true) {
            char c = (char) Memory.mem_readb(str);
            if (c == 0)
                break;
            c = toupperW(c);
            Memory.mem_writeb(str++, c);
        }
    }

    public static String[] parseQuotedString(String s) {
        s = s.trim();
        Vector results = new Vector();
        StringBuilder buffer = new StringBuilder();
        boolean quote = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\"') {
                quote = !quote;
            } else if (quote || c != ' ') {
                buffer.append(c);
            } else {
                results.add(buffer.toString());
                buffer = new StringBuilder();
            }
        }
        results.add(buffer.toString());
        String[] r = new String[results.size()];
        results.copyInto(r);
        return r;
    }

    public static int allocateA(String s) {
        if (s == null)
            return 0;
        byte[] b = s.getBytes();
        int address = WinSystem.getCurrentProcess().heap.alloc(b.length + 1, false);
        strcpy(address, s);
        return address;
    }

    public static int allocateTempA(String s) {
        if (s == null)
            return 0;
        byte[] b = s.getBytes();
        int address = getTempBuffer(b.length + 1);
        strcpy(address, s);
        return address;
    }
}
