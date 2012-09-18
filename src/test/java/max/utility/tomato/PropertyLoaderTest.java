package max.utility.tomato;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class PropertyLoaderTest {
	// System.out.println("0: "
	// +
	// getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring(6));
	//
	// CodeSource codeSource =
	// this.getClass().getProtectionDomain().getCodeSource();
	// File jarFile = new File(codeSource.getLocation().toURI().getPath());
	// System.out.println("1: " + jarFile.getParentFile().getPath());
	// System.out.println("2: " + getClass().getResource("").getPath());
	// 0:
	// C:/Users/MAX/Documents/groovy/plugins/tomato/bin/timer-manager-0.0.1-SNAPSHOT-20-min.jar
	// 1: C:\Users\MAX\Documents\groovy\plugins\tomato\bin
	// 2:
	// file:/C:/Users/MAX/Documents/groovy/plugins/tomato/bin/timer-manager-0.0.1-SNAPSHOT-20-min.jar!/max/utility/tomato/

	@Test
	public void loadFromClassPathAsInputStream() {
		PropertyLoader.loadFromClassPathAsInputStream("timer-manager.properties");
		assertEquals("3", PropertyLoader.getProperty("duration"));
		assertEquals("SECONDS", PropertyLoader.getProperty("time.measurement.unit"));
	}

	@Test
	public void loadFromClassPathAsResourceBundle() {
		PropertyLoader.loadFromClassPathAsResourceBundle("timer-manager");
		assertEquals("3", PropertyLoader.getProperty("duration"));
		assertEquals("SECONDS", PropertyLoader.getProperty("time.measurement.unit"));
	}

	@Test
	public void loadFromFileSystem() throws IOException {
		String path = ClassLoader.getSystemClassLoader().getResource(".").getPath();
		File currentPath = new File(path);
		StringBuilder sb = new StringBuilder();
		sb.append(currentPath.getCanonicalPath());
		sb.append(File.separator);
		sb.append("timer-manager.properties");
		String fileAbsPath = sb.toString();
		File file = new File(fileAbsPath);
		PropertyLoader.loadFromFileSystem(file);
		assertEquals("3", PropertyLoader.getProperty("duration"));
		assertEquals("SECONDS", PropertyLoader.getProperty("time.measurement.unit"));
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
