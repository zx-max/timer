package zxmax.tools.timerreview;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyLoader {

    // XXX: cosa cambia tra i due ??
    // ClassLoader loader = ClassLoader.getSystemClassLoader();
    // ClassLoader loader = Thread.currentThread().getContextClassLoader();
    public static final Logger logger = LoggerFactory
            .getLogger(PropertyLoader.class);

    private static Properties props = new Properties();
    public static final String TIMER_MANAGER_PROP_FILE = "timer-manager.properties";

    public static String dump() {
        StringBuilder builder = new StringBuilder();
        builder.append("PropertyLoader [toString()=");
        builder.append(props.toString());
        builder.append("]");
        return builder.toString();
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }

    /**
     * 
     * @param propsName
     *            properties file name from classpath
     * @return
     */
    public static void loadFromClassPathAsInputStream(String propsName) {
        InputStream in = null;
        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();

            in = loader.getResourceAsStream(propsName);
            if (in == null) {
                throw new MissingResourceException(String.format(
                        "file: [%s] not found.", propsName), null, null);
            }

            try {
                props.load(in);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Throwable e) {
                    logger.error(null, e);
                }
            }
        }
    }

    // http://javahowto.blogspot.it/2006/05/debug-java-util-missingresourceexcepti.html
    // ResourceBundle.getBundle() is really intended to look up translatable
    // resources. In the example you give (connections.properties), it sounds
    // like you're using it as a general mechanism for loading properties files
    // from the classpath.
    //
    // Although that works, it's relatively inefficent (it first tries to find
    // locale specific versions of the properties file, which incurs a
    // classloading overhead, and then must create a PropertiesResourceBundle
    // instance for your properties file). If you're loading a nontranslated
    // file, you can achieve the same effect using something like:
    //
    //
    // URL resUrl = myclass.getResource( "/org/acme/connection.properties" );
    // Properties props = new Properties();
    // properties.load( resUrl.openStream() );

    // Brian, thanks for the comments. I agree ResourceBundle should be used for
    // i18n purpose. But I also found it's often used (misused) for general
    // properties-loading. Maybe I should change the example
    // connection.properties to messages_en.properties.

    public static void loadFromClassPathAsResourceBundle(String propsFile) {
        propsFile = propsFile.replace('/', '.');
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        // Throws MissingResourceException on lookup failures:
        final ResourceBundle rb = ResourceBundle.getBundle(propsFile,
                Locale.getDefault(), loader);

        for (Enumeration<String> keys = rb.getKeys(); keys.hasMoreElements();) {
            final String key = keys.nextElement();
            final String value = rb.getString(key);

            props.put(key, value);
        }
    }

    public static URL loadFromClassPathAsURL(String name) {
        URL resource = PropertyLoader.class.getClassLoader().getResource(name);
        return resource;
    }

    /**
     * 
     * @param propsFile
     *            from file system
     * @return
     */
    public static void loadFromFileSystem(File propsFile) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(propsFile);

            try {
                props.load(fis);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (FileNotFoundException e) {
            throw new MissingResourceException(e.getMessage(), null, null);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Throwable e) {
                    logger.error(null, e);
                }
            }
        }
    }
}
