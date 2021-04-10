package jdos.util;

public class StringRef {
    public String value;

    public StringRef() {
    }

    public StringRef(String value) {
        this.value = value;
    }

    public String toString() {
        throw new RuntimeException("Ooops");
    }
}
