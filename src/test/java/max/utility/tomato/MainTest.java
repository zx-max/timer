package max.utility.tomato;

import max.utility.tomato.gui.StartTimerWindow;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTest {
	public static final Logger logger = LoggerFactory.getLogger(StartTimerWindow.class);

	// the problem is: load timer-manager.properties
	// from command line it should be readed from the same folder of the jar
	// from the ide it should be loaded from classpath as default configuration
	// configuration should be logged

	@Test
	public void conutdown_Default_Configuration_IsLoaded() {
		try {
			new Main();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
