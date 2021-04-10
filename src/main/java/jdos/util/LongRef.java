package jdos.util;

public class LongRef {
    public long value;

    public LongRef(long value) {
        this.value = value;
    }

    public String toString() {
        throw new RuntimeException("Ooops");
    }
}
