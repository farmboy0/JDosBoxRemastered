package jdos.misc;

import jdos.Dosbox;
import jdos.debug.Debug;

public class Log {
    public static final int level = 1;

    public static void log_msg(String msg) {
        System.out.println(msg);
    }

    public static void exit(String msg) {
        System.out.print(msg);
        try {
            throw new Exception("Stacktrace");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Debug.close();
        if (!Dosbox.applet)
            System.exit(0);
        else
            throw new RuntimeException("Exit");
    }

    public static void log(int type, int severity, String msg) {
//        System.out.println(msg);
    }
}
