package max.utility.tomato;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

import max.utility.tomato.dao.HibernateBasicDaoImpl;
import max.utility.tomato.gui.StartTimerWindow;
import max.utility.tomato.gui.TrayIconMouseMotionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	public static final Logger logger = LoggerFactory.getLogger(max.utility.tomato.Main.class);
	public static final String CATCH_BLOCK = "#catch_block#";

	public static void main(String[] args) {
		try {
			new Main();
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public Main() throws Exception {
		super();
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("H2FileTomatoPU");
		EntityManager entityManager = emFactory.createEntityManager();
		HibernateBasicDaoImpl basicDao = new HibernateBasicDaoImpl(entityManager);
		Register.put(HibernateBasicDaoImpl.class, basicDao);
		TrayIcon trayIcon = getTrayIcon();
		TrayIconMouseMotionListener listener = new TrayIconMouseMotionListener(trayIcon);
		trayIcon.addMouseMotionListener(listener);
		Register.put(TrayIcon.class, trayIcon);
		Register.put(TrayIconMouseMotionListener.class, listener);
		
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
			logger.error(null, ex);
		}
		
		StartTimerWindow timer = new StartTimerWindow();
		timer.setVisible(true);
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

	private TrayIcon getTrayIcon() throws AWTException {
		final TrayIcon trayIcon = new TrayIcon(createImage("images/bulb.gif", "tray icon"));


		MenuItem exitItem = new MenuItem("Exit");
		MenuItem newTimerItem = new MenuItem("Nuovo Timer");
		final PopupMenu popup = new PopupMenu();
		popup.add(exitItem);
		popup.add(newTimerItem);
		trayIcon.setPopupMenu(popup);
		
		final SystemTray tray = SystemTray.getSystemTray();
		tray.add(trayIcon);
		
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tray.remove(trayIcon);
				System.exit(0);
			}
		});
		
		newTimerItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StartTimerWindow timer = new StartTimerWindow();
				timer.setVisible(true);
			}
		});
		return trayIcon;
	}
}
