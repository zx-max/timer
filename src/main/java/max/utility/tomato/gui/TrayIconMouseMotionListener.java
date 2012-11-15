package max.utility.tomato.gui;

import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import max.utility.tomato.PropertyLoader;
import max.utility.tomato.domain.Tomato;

import org.joda.time.LocalDateTime;
import org.joda.time.format.PeriodFormatterBuilder;

public class TrayIconMouseMotionListener implements MouseMotionListener {
	private TrayIcon trayIcon;
	private Tomato tomato;

	public TrayIconMouseMotionListener(TrayIcon trayIcon, Tomato tomato) {
		this.trayIcon = trayIcon;
		this.tomato = tomato;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		LocalDateTime now = new LocalDateTime();
		PeriodFormatterBuilder formatter = new PeriodFormatterBuilder();
		formatter.appendMinutes().appendSeparator(":").appendSeconds();

		String timeLeft = "min: "
				+ (Integer.valueOf(PropertyLoader.getProperty("duration")) - (now.getMinuteOfHour() - tomato.getStartTime()
						.getMinuteOfHour()));

		trayIcon.setToolTip(timeLeft + "\n" + tomato.getFocusOn());
	}

}
