package zxmax.tools.timerreview;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.MissingResourceException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zxmax.tools.timerreview.PropertyLoader;

public class PropertyLoaderTest {
    public static final Logger logger = LoggerFactory
            .getLogger(PropertyLoaderTest.class);

    @Test
    public void defaultConutndownConfigurationExist() throws IOException {
        String path = ClassLoader.getSystemClassLoader().getResource(".")
                .getPath();
        File currentPath = new File(path);
        StringBuilder sb = new StringBuilder();
        sb.append(currentPath.getParent());
        sb.append(File.separator);
        sb.append("classes");
        sb.append(File.separator);
        sb.append("timer-manager.properties");
        String fileAbsPath = sb.toString();
        File file = new File(fileAbsPath);
        logger.debug("load file: [{}]", fileAbsPath);
        PropertyLoader.loadFromFileSystem(file);
        assertTrue(PropertyLoader.getProperty("duration") != null);
        assertTrue(PropertyLoader.getProperty("time.measurement.unit") != null);
    }

    @Test
    public void loadFromClassPathAsInputStream() {
        PropertyLoader
                .loadFromClassPathAsInputStream("timer-manager.properties");
        assertEquals("3", PropertyLoader.getProperty("duration"));
        assertEquals("SECONDS",
                PropertyLoader.getProperty("time.measurement.unit"));
    }

    @Test
    public void loadFromClassPathAsResourceBundle() {
        PropertyLoader.loadFromClassPathAsResourceBundle("timer-manager");
        assertEquals("3", PropertyLoader.getProperty("duration"));
        assertEquals("SECONDS",
                PropertyLoader.getProperty("time.measurement.unit"));
    }

    @Test
    public void loadFromClassPathAsUrl() {
        URL resource = PropertyLoader.loadFromClassPathAsURL("images/bulb.gif");
        assertNotNull(resource.getFile());
    }

    @Test
    public void loadFromFileSystem() throws IOException {
        String path = ClassLoader.getSystemClassLoader().getResource(".")
                .getPath();
        File currentPath = new File(path);
        StringBuilder sb = new StringBuilder();
        sb.append(currentPath.getCanonicalPath());
        sb.append(File.separator);
        sb.append("timer-manager.properties");
        String fileAbsPath = sb.toString();
        File file = new File(fileAbsPath);
        PropertyLoader.loadFromFileSystem(file);
        assertEquals("3", PropertyLoader.getProperty("duration"));
        assertEquals("SECONDS",
                PropertyLoader.getProperty("time.measurement.unit"));
    }

    @Test(expected = MissingResourceException.class)
    public void throwExceptionIfResourceNotFound_FileSystem() {
        PropertyLoader.loadFromFileSystem(new File("caramba.properties"));
    }

    @Test(expected = MissingResourceException.class)
    public void throwExceptionIfResourceNotFound_InputStream() {
        PropertyLoader.loadFromClassPathAsInputStream("caramba.properties");
    }

    @Test(expected = MissingResourceException.class)
    public void throwExceptionIfResourceNotFound_ResourceBundle() {
        PropertyLoader.loadFromClassPathAsResourceBundle("caramba.properties");
    }

    // ------------------------------------------------
    // URL resource = ClassLoader.getSystemClassLoader().getResource(".");
    // System.out.println("3.1: " + resource.toString());
    // String path = resource.getPath();
    // System.out.println("3: " + path);
    // System.out.println("4: " + URLDecoder.decode(path, "UTF-8"));
    // ECLIPSE
    // 3.1: file:/C:/Users/MAX/Documents/groovy/plugins/tomato/target/classes/
    // 3: /C:/Users/MAX/Documents/groovy/plugins/tomato/target/classes/
    // 4: /C:/Users/MAX/Documents/groovy/plugins/tomato/target/classes/
    // COMMAND LINE
    // java.lang.NullPointerException: null
    // at max.utility.tomato.Main.<init>(Main.java:40)
    // ~[timer-manager-0.0.1-SNAPSHOT-20-min.jar:na]
    // at max.utility.tomato.Main.main(Main.java:24)
    // ~[timer-manager-0.0.1-SNAPSHOT-20-min.jar:na]
    // @Test
}
