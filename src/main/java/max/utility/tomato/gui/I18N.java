package max.utility.tomato.gui;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18N {

    public static String getKey(Class clazz, String key) {
        String baseName = clazz.getCanonicalName();
        ResourceBundle labels = ResourceBundle.getBundle(baseName,
                Locale.ENGLISH);
        return labels.getString(key);
    }

}
