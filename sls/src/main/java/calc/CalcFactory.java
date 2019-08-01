package calc;

import com.sun.jna.Native;

public class CalcFactory {

    public static CalcJNA create(String name) {
        return Native.load(name, CalcJNA.class);
    }

    public static CalcJNA create() {
        return create("calc");
    }

}
