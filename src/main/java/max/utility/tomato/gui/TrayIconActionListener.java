package max.utility.tomato.gui;

import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import max.utility.tomato.domain.Tomato;

import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrayIconActionListener implements ActionListener {

	public static final Logger logger = LoggerFactory.getLogger(TrayIconActionListener.class);
	private Tomato tomato;
	private TrayIcon trayIcon;

	public TrayIconActionListener(TrayIcon trayIcon, Tomato tomato) {
		this.tomato = tomato;
		this.trayIcon = trayIcon;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		LocalDateTime startTime = tomato.getStartTime();
		LocalDateTime now = new LocalDateTime();
		Interval interval = new Interval(startTime.toDate().getTime(), now.toDate().getTime());
		Duration duration = new Duration(startTime.toDate().getTime(), now.toDate().getTime());
		String string = interval.toString() + ", " + duration.toString();
		logger.debug(string);
		trayIcon.setToolTip(string);
		// JOptionPane.showMessageDialog(null,
		// "This dialog box is run from System Tray");
	}

}
