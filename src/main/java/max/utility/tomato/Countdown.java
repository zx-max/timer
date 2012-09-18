package max.utility.tomato;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import max.utility.tomato.gui.EndTimer;
import max.utility.tomato.gui.StartTimer;
import max.utility.tomato.gui.window.listener.CloseTimersListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Countdown {

	public static final Logger logger = LoggerFactory.getLogger(Countdown.class);

	private long duration;

	private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

	private StartTimer startTimer;

	private TimeUnit timeUnit;

	public Countdown(StartTimer startTimer) {
		this.startTimer = startTimer;
		duration = Integer.valueOf(PropertyLoader.getProperty("duration"));
		timeUnit = Enum.valueOf(TimeUnit.class, PropertyLoader.getProperty("time.measurement.unit"));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void start(final Long tomatoId) {
		try {
			Callable actionToPerform = new Callable<JFrame>() {
				@Override
				public JFrame call() throws Exception {
					EndTimer endTimer = new EndTimer(tomatoId);
					endTimer.addWindowListener(new CloseTimersListener(new JFrame[] { endTimer, startTimer }));
					logger.debug("open endTomato");
					return endTimer.openWindow();
				}
			};

			logger.debug("before timer");
			ScheduledFuture future = scheduledExecutorService.schedule(actionToPerform, duration, timeUnit);
			future.get(); // throw exceptions if happened
			logger.debug("after timer");

		} catch (Exception e) {
			logger.error("#catch_block#", e);
		}

		scheduledExecutorService.shutdown();
	}

}
