package jdos.util;

public class IntRef {
    public int value;

    public IntRef(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        throw new RuntimeException("Ooops");
    }
}
