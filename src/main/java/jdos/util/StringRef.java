package jdos.util;

public class StringRef {
    public String value;

    public StringRef() {
    }

    public StringRef(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        throw new RuntimeException("Ooops");
    }
}
