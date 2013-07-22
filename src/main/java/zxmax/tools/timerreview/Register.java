package zxmax.tools.timerreview;

import java.util.HashMap;
import java.util.Map;

public class Register {
    private static Map<Class, Object> register = new HashMap<Class, Object>();

    public static Object get(Class key) {
        return register.get(key);
    }

    public static void put(Class key, Object value) {
        register.put(key, value);
    }

}
