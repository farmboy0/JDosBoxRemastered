package jdos.misc.setup;

import java.io.IOException;
import java.io.OutputStream;

public class Section_line extends Section {
    public String data = "";

    public Section_line(String _sectionname) {
        super(_sectionname);
    }

    @Override
    public void HandleInputline(String input) {
        data += input;
        data += "\n";
    }

    @Override
    public void PrintData(OutputStream os) throws IOException {
        Config.fputs(data, os);
    }

    @Override
    public String GetPropValue(String _property) {
        return NO_SUCH_PROPERTY;
    }
}
