package jdos.misc.setup;

import java.util.Vector;

import jdos.misc.Log;
import jdos.misc.Msg;

public abstract class Property {
    public final String propname;
    protected final int change;
    protected Value value = new Value();
    protected Vector suggested_values = new Vector();
    protected Value default_value = new Value();

    public Property(String _propname, int when) {
        propname = _propname;
        change = when;
    }

    public void Set_values(String[] in) {
        int type = default_value.type;
        for (String element : in) {
            suggested_values.add(new Value(element, type));
        }
    }

    public void Set_help(String str) {
        String result = "CONFIG_" + propname;
        result = result.toUpperCase();
        Msg.add(result, str);
    }

    public String Get_help() {
        String result = "CONFIG_" + propname;
        result = result.toUpperCase();
        return Msg.get(result);
    }

    public abstract void SetValue(String str);

    public Value GetValue() {
        return value;
    }

    public Value Get_Default_Value() {
        return default_value;
    }

    //CheckValue returns true  if value is in suggested_values;
    //Type specific properties are encouraged to override this and check for type
    //specific features.
    public boolean CheckValue(Value in, boolean warn) {
        if (suggested_values.isEmpty())
            return true;
        for (int i = 0; i < suggested_values.size(); i++) {
            Value v = (Value) suggested_values.elementAt(i);
            if (in.equals(v))
                return true;
        }
        if (warn)
            Log.log_msg("\"" + in.toString() + "\" is not a valid value for variable: " + propname
                + ".\nIt might now be reset to the default value: " + default_value.toString());
        return false;
    }

    //Set interval value to in or default if in is invalid. force always sets the value.
    public void SetVal(Value in, boolean forced) {
        SetVal(in, forced, true);
    }

    public void SetVal(Value in, boolean forced, boolean warn) {
        if (forced || CheckValue(in, warn))
            value = in;
        else
            value = default_value;
    }

    public Vector GetValues() {
        return suggested_values;
    }

    public int Get_type() {
        return default_value.type;
    }

    public static final class Changeable {
        public static final int Always = 0;
        public static final int WhenIdle = 1;
        public static final int OnlyAtStart = 2;
    }
}
