package jdos.util;

public class ShortRef {
    public short value;

    public ShortRef() {
        this.value = 0;
    }

    public ShortRef(int value) {
        this.value = (short) value;
    }

    public ShortRef(short value) {
        this.value = value;
    }

    public String toString() {
        throw new RuntimeException("Ooops");
    }
}
