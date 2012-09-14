package max.utility.tomato.gui.window.listener;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.Callable;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import max.utility.tomato.Main;
import max.utility.tomato.gui.EndTomato;
import max.utility.tomato.gui.StartTimer;

public class CloseTimersListener extends  WindowAdapter {

	public static final Logger logger = LoggerFactory.getLogger(CloseTimersListener.class);
	private JFrame[] jFrames;

	public CloseTimersListener(JFrame[] jFrames) {
		this.jFrames = jFrames;
	}


	public void windowClosing(WindowEvent arg0) {
	logger.debug("start closing windows..");
		for (int i = 0; i < jFrames.length; i++) {
			WindowEvent wev = new WindowEvent(jFrames[i], WindowEvent.WINDOW_CLOSING);
			Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
			logger.debug(jFrames[i].getTitle());
			jFrames[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			jFrames[i].setVisible(false);
//			jFrames[i].dispose();
		}

	}


}
