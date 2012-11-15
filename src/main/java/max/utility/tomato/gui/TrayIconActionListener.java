package max.utility.tomato.gui;

import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrayIconActionListener implements ActionListener {

	public static final Logger logger = LoggerFactory.getLogger(TrayIconActionListener.class);
	private TrayIcon trayIcon;

	public TrayIconActionListener(TrayIcon trayIcon) {
		this.trayIcon = trayIcon;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("actionPerformed");
		LocalDateTime now = new LocalDateTime();
		logger.debug(now.toString("dd-MM-yyyy HH:mm"));
		trayIcon.setToolTip(now.toString("dd-MM-yyyy HH:mm"));
		// JOptionPane.showMessageDialog(null,
		// "This dialog box is run from System Tray");
	}

}
