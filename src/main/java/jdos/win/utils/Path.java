package jdos.win.utils;

public class Path {
    public String nativePath;
    public String winPath;
    public Path(String nativePath, String winPath) {
        this.nativePath = nativePath;
        this.winPath = winPath;
    }
}
