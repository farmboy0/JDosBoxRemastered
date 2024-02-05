package jdos.misc.setup;

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
    public void PrintData(StringBuilder sb) {
        Config.fputs(data, sb);
    }

    @Override
    public String GetPropValue(String _property) {
        return NO_SUCH_PROPERTY;
    }
}
