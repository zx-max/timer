package max.utility.tomato;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import max.utility.tomato.domain.Tomato;
import max.utility.tomato.gui.EndTimer;
import max.utility.tomato.gui.StartTimer;
import max.utility.tomato.gui.TrayIconActionListener;
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

		// trayIcon.addActionListener(new TrayIconActionListener(trayIcon,
		// tomato));
		// trayIcon.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent arg0) {
		// LocalDateTime startTime = tomato.getStartTime();
		// LocalDateTime now = new LocalDateTime();
		// Interval interval = new Interval(startTime.toDate().getTime(),
		// now.toDate().getTime());
		// Duration duration = new Duration(startTime.toDate().getTime(),
		// now.toDate().getTime());
		//
		// String string = interval.toString() + ", " + duration.toString();
		// logger.debug(string);
		// System.out.println(string);
		// trayIcon.setToolTip(string);
		//
		// }
		// });
	}

	private Image createImage(String path, String description) {
		URL imageURL = PropertyLoader.loadFromClassPathAsURL("images/bulb.gif");

		if (imageURL == null) {
			System.err.println("Resource not found: " + path);
			return null;
		} else {
			return (new ImageIcon(imageURL, description)).getImage();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void start(final Tomato tomato) {
		final TrayIcon trayIcon = new TrayIcon(createImage("images/bulb.gif", "tray icon"));
		final SystemTray tray = SystemTray.getSystemTray();

		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			logger.error("TrayIcon could not be added.", e);
			return;
		}
		try {
			Callable actionToPerform = new Callable<JFrame>() {
				@Override
				public JFrame call() throws Exception {
					trayIcon.addActionListener(new TrayIconActionListener(trayIcon, tomato));
					EndTimer endTimer = new EndTimer(tomato.getId());
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
