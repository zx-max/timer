package max.utility.tomato;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyLoader {

	// XXX: cosa cambia tra i due ??
	// ClassLoader loader = ClassLoader.getSystemClassLoader();
	// ClassLoader loader = Thread.currentThread().getContextClassLoader();
	public static final Logger logger = LoggerFactory.getLogger(PropertyLoader.class);

	private static Properties props = new Properties();
	public static final String TIMER_MANAGER_PROP_FILE = "timer-manager.properties";

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
			if (in != null) {
				props.load(in); // Can throw IOException
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("could not load [" + propsName + "]" + " as classloader resource");
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

	public static void loadFromClassPathAsResourceBundle(String propsFile) {
		propsFile = propsFile.replace('/', '.');
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		// Throws MissingResourceException on lookup failures:
		final ResourceBundle rb = ResourceBundle.getBundle(propsFile, Locale.getDefault(), loader);

		for (Enumeration<String> keys = rb.getKeys(); keys.hasMoreElements();) {
			final String key = keys.nextElement();
			final String value = rb.getString(key);

			props.put(key, value);
		}
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
			props.load(fis);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
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
