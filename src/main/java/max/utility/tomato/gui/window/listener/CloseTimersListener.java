package max.utility.tomato.gui.window.listener;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloseTimersListener extends WindowAdapter {

	public static final Logger logger = LoggerFactory.getLogger(CloseTimersListener.class);
	private final JFrame[] jFrames;

	public CloseTimersListener(JFrame[] jFrames) {
		this.jFrames = jFrames;
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		logger.debug("start closing windows..");
		for (JFrame jFrame : jFrames) {
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			WindowEvent wev = new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING);
			Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
			logger.debug(jFrame.getTitle());
		}

		// http://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe
		System.exit(0);
	}

}
