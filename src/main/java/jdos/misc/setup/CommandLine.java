package jdos.misc.setup;

import java.util.List;
import java.util.Vector;

import jdos.util.StringRef;

public class CommandLine {
    private final List<String> cmds;
    private String file_name;

    public CommandLine(String[] args) {
        cmds = new Vector<>(args.length);
        for (String arg : args)
            cmds.add(arg);
        // :TODO: file_name = ?
    }

    public CommandLine(String name, String cmdline) {
        cmds = new Vector<>();
        file_name = name;
        /* Parse the cmds and put them in the list */
        boolean inword, inquote;
        char c;
        inword = false;
        inquote = false;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < cmdline.length(); i++) {
            c = cmdline.charAt(i);
            if (inquote) {
                if (c != '"')
                    str.append(c);
                else {
                    inquote = false;
                    cmds.add(str.toString());
                    str = new StringBuilder();
                }
            } else if (inword) {
                if (c != ' ')
                    str.append(c);
                else {
                    inword = false;
                    cmds.add(str.toString());
                    str = new StringBuilder();
                }
            } else if (c == '"') {
                inquote = true;
            } else if (c != ' ') {
                str.append(c);
                inword = true;
            }
        }
        if (inword || inquote)
            cmds.add(str.toString());
    }

    public String GetFileName() {
        return file_name;
    }

    public boolean FindExist(String name) {
        return FindExist(name, false);
    }

    public boolean FindExist(String name, boolean remove) {
        int index = FindEntry(name, false);
        if (index < 0)
            return false;
        if (remove)
            cmds.remove(index);
        return true;
    }

    public Integer FindHex(String name) {
        return FindHex(name, false);
    }

    public Integer FindHex(String name, boolean remove) {
        int index = FindEntry(name, true);
        if (index < 0)
            return null;
        try {
            int result = Integer.parseInt(cmds.get(index + 1), 16);
            if (remove) {
                cmds.remove(index);
                cmds.remove(index);
            }
            return result;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Integer FindInt(String name) {
        return FindInt(name, false);
    }

    public Integer FindInt(String name, boolean remove) {
        int index = FindEntry(name, true);
        if (index < 0)
            return null;
        try {
            int result = Integer.parseInt(cmds.get(index + 1), 10);
            if (remove) {
                cmds.remove(index);
                cmds.remove(index);
            }
            return result;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public String FindString(String name) {
        return FindString(name, false);
    }

    public String FindString(String name, boolean remove) {
        int index = FindEntry(name, true);
        if (index < 0)
            return null;
        String result = cmds.get(index + 1);
        if (remove) {
            cmds.remove(index);
            cmds.remove(index);
        }
        return result;
    }

    public String FindCommand(int which) {
        if ((which < 1) || (which > cmds.size()))
            return null;
        return cmds.get(which - 1);
    }

    public String FindStringBegin(String begin) {
        return FindStringBegin(begin, false);
    }

    public String FindStringBegin(String begin, boolean remove) {
        begin = begin.toLowerCase();
        for (int i = 0; i < cmds.size(); i++) {
            if (cmds.get(i).toLowerCase().startsWith(begin)) {
                String result = cmds.get(i);
                if (remove)
                    cmds.remove(i);
                return result;
            }
        }
        return null;
    }

    public String FindStringRemain(String name) {
        int index = FindEntry(name, false);
        if (index < 0)
            return null;
        index++;
        StringBuilder value = new StringBuilder();
        for (int i = index; i < cmds.size(); i++) {
            value.append(" ");
            value.append(cmds.get(i));
        }
        return value.toString();
    }

    /* Only used for parsing command.com /C
     * Allowing /C dir and /Cdir
     * Restoring quotes back into the commands so command /C mount d "/tmp/a b" works as intended
     */
    public boolean FindStringRemainBegin(String name, StringRef value) {
        int i = -1;
        value.value = "";
        if ((i = FindEntry(name, false)) < 0) {
            int len = name.length();
            boolean found = false;
            for (i = 0; i < cmds.size(); i++) {
                String s = cmds.get(i);
                if (s.length() > len)
                    s = s.substring(0, len);
                if (s.equalsIgnoreCase(name)) {
                    String temp = "";
                    s = cmds.get(i);
                    if (s.length() > len)
                        temp = s.substring(len);
                    //Restore quotes for correct parsing in later stages
                    if (temp.contains(" "))
                        value.value = "\"" + temp + "\"";
                    else
                        value.value = temp;
                    found = true;
                    break;
                }
            }
            if (!found)
                return false;
        }
        i++;
        for (; i < cmds.size(); i++) {
            value.value += " ";
            String temp = cmds.get(i);
            if (temp.contains(" "))
                value.value += "\"" + temp + "\"";
            else
                value.value += temp;
        }
        return true;
    }

    public String GetStringRemain() {
        if (cmds.isEmpty())
            return null;
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < cmds.size(); i++) {
            if (i > 0)
                value.append(" ");
            value.append(cmds.get(i));
        }
        return value.toString();
    }

    public void Shift() {
        Shift(1);
    }

    public void Shift(int amount) {
        for (int i = 0; i < amount; i++) {
            file_name = !cmds.isEmpty() ? cmds.get(0) : "";
            if (!cmds.isEmpty())
                cmds.remove(0);
        }
    }

    public int GetCount() {
        return cmds.size();
    }

    public int Get_arglength() {
        int result = 0;
        for (int i = 0; i < cmds.size(); i++) {
            if (i > 0)
                result++;
            result += cmds.get(i).length();
        }
        return result;
    }

    private int FindEntry(String name, boolean needNext) {
        for (int i = 0; i < cmds.size(); i++) {
            if (cmds.get(i).equalsIgnoreCase(name)) {
                if (needNext && i == cmds.size() - 1)
                    return -1;
                return i;
            }
        }
        return -1;
    }
}
