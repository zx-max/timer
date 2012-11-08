package max.utility.tomato.tasks;

import java.util.TimerTask;
import java.util.concurrent.Callable;

import javax.swing.JFrame;

import max.utility.tomato.Main;
import max.utility.tomato.domain.Tomato;
import max.utility.tomato.gui.EndTimerWindow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenEndTimerWindow extends TimerTask implements Callable<JFrame> {

	private static final Logger logger = LoggerFactory.getLogger(OpenEndTimerWindow.class);

	private Tomato tomato;

	public OpenEndTimerWindow(Tomato tomato) {
		this.tomato = tomato;
	}

	// Timer timer = new Timer();
	// timer.schedule(task, delay);
	@Override
	public void run() {
		openEndTimerWindow();
	}

	@Override
	public JFrame call() throws Exception {
		return openEndTimerWindow();
	}

	private JFrame openEndTimerWindow() {
		try {
			EndTimerWindow endTimer = new EndTimerWindow(tomato);
			endTimer.openWindow();
			logger.debug("open endTomato");
			return endTimer;
		} catch (Exception e) {
			logger.error(Main.CATCH_BLOCK, e);
			return null;
		}
	}

}
