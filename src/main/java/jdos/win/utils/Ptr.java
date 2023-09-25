package jdos.win.utils;

public class Ptr {
    public static String toString(int v) {
        return Long.toString(v & 0xFFFFFFFFL, 16);
    }
}
