package max.utility.tomato;


import java.awt.event.WindowAdapter;
import java.util.concurrent.*;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import max.utility.tomato.gui.EndTomato;
import max.utility.tomato.gui.StartTimer;
import max.utility.tomato.gui.window.listener.CloseTimersListener;

public class Countdown {

	public static final Logger logger = LoggerFactory.getLogger(Countdown.class);

	private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

	private StartTimer startTimer;

	public Countdown(StartTimer startTimer) {
		this.startTimer = startTimer;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void start() {
		try {
			Callable actionToPerform = new Callable<JFrame>() {
				public JFrame call() throws Exception {
					EndTomato endTomato = new EndTomato();
					endTomato.addWindowListener(new CloseTimersListener(new JFrame[]{endTomato,startTimer} ));
					logger.debug("open endTomato");
					return endTomato.openWindow();
				}
			};

			logger.debug("before timer");
			ScheduledFuture scheduledFuture = scheduledExecutorService.schedule(actionToPerform, 3, TimeUnit.SECONDS);
			logger.debug("after timer");

		} catch (Exception e) {
			logger.error("#catch_block#", e);
		}

		scheduledExecutorService.shutdown();
	}

	// private Callable getActionToPerform() {
	// Callable actionToPerform = new Callable() {
	// public Object call() throws Exception {
	// EndTomato endTomato = new EndTomato();
	// endTomato.openWindow();
	// logger.debug("after open end tomato");
	// return "timer ended...";
	// }
	// };
	// return actionToPerform;
	// }
}
