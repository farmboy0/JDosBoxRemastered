package jdos.hardware.pci;

public abstract class PCI_BAR {
    abstract int getBAR(int currentValue);

    abstract void setBAR(int newValue);
}
