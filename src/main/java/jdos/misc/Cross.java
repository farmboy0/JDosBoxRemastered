package jdos.misc;

import java.io.File;

import jdos.Dosbox;
import jdos.misc.setup.Config;
import jdos.util.BooleanRef;
import jdos.util.FileHelper;
import jdos.util.HomeDirectory;
import jdos.util.StringRef;

public class Cross {
    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;
    }

    public static boolean isCDRom(String path) {
        String label = HomeDirectory.getVolumeLabel(path).toLowerCase();
        return label.indexOf("bd-rom") >= 0 || label.indexOf("dvd") >= 0;
    }

    public static dir_information open_directory(String dirname) {
        if (!new File(dirname).exists())
            return null;
        dir_information result = new dir_information();

        result.list = new File(dirname).listFiles();
        result.index = -1;
        return result;
    }

    public static boolean read_directory_first(dir_information dirp, StringRef entry_name, BooleanRef is_directory) {
        if (dirp.list.length == 0)
            return false;
        entry_name.value = dirp.list[0].getName();
        is_directory.value = dirp.list[0].isDirectory();
        dirp.index = 0;
        return true;
    }

    public static boolean read_directory_next(dir_information dirp, StringRef entry_name, BooleanRef is_directory) {
        if (dirp.index + 1 >= dirp.list.length)
            return false;
        dirp.index++;
        entry_name.value = dirp.list[dirp.index].getName();
        is_directory.value = dirp.list[dirp.index].isDirectory();
        return true;
    }

    public static void close_directory(dir_information dirp) {
    }

    public static String ResolveHomedir(String temp_line) {
        return FileHelper.resolve_path(temp_line);
    }

    public static String CreatePlatformConfigDir() {
        if (!Dosbox.allPrivileges)
            return "";
        String result = System.getProperty("user.dir");
        if (result != null && result.length() != 0 && new File(result).exists()) {
            result += File.separator + ".dosbox";
            File f = new File(result);
            if (!f.exists()) {
                f.mkdir();
            }
            return f.getAbsolutePath() + File.separator;
        }
        return "";
    }

    public static String GetPlatformConfigName() {
        return "dosbox-" + Config.MAJOR_VERSION + ".conf";
    }

    public static void CreateDir(String dir) {
        new File(dir).mkdirs();
    }

    public static class dir_information {
        private File[] list;
        private int index;
    }
}
