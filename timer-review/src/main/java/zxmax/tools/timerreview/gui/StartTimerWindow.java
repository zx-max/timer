/**
 * This file is part of timer-review.
 *
 * timer-review is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * timer-review is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with timer-review.  If not, see <http://www.gnu.org/licenses/>.
 */
package zxmax.tools.timerreview.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.TrayIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.concurrent.Callable;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zxmax.tools.timerreview.Countdown;
import zxmax.tools.timerreview.I18N;
import zxmax.tools.timerreview.Register;
import zxmax.tools.timerreview.dao.HibernateBasicDaoImpl;
import zxmax.tools.timerreview.domain.ApplicationInfo;
import zxmax.tools.timerreview.domain.Tomato;
import zxmax.tools.timerreview.serviceses.UINotificationService;
import zxmax.tools.timerreview.serviceses.validators.ValidatorService;
import zxmax.tools.timerreview.tasks.OpenEndTimerWindow;

public class StartTimerWindow extends JFrame {

	private static final String TAB_NEW_TIMER_LABEL = "tab.new.timer.label";

	private static final String TAB_TIMER_LIST_LABEL = "tab.timer.list.label";

	private static final String TAB_NEW_TIMER_TITLE_LABEL = "tab.new.timer.title.label";

	private static final String TAB_NEW_TIMER_DURATA_LABEL = "tab.new.timer.dirata.label";
	private static final String TAB_NEW_TIMER_DURATA_TOOL_TIP = "tab.new.timer.durata.tootip";

	private static final String TAB_NEW_TIMER_FOCUS_ON_LABEL = "tab.new.timer.focus.on.label";
	private static final String TAB_NEW_TIMER_FOCUS_ON_TEXT_AREA_TOOL_TIP = "tab.new.timer.focus.on.textarea.tooltip";
	private static final String TAB_NEW_TIMER_FOCUS_ON_TEXT_AREA_TEXT = "tab.new.timer.focus.on.textarea.text";
	private static final String TAB_NEW_TIMER_BTN_START_LABEL = "tab.new.timer.btn.start.label";

	private static final String TIMER_LOGGER_FOCUS_ON = "timerlogger.focus.on";
	private static final String TIMER_LOGGER_TITOLO = "timerlogger.title";

	public static final Logger logger = LoggerFactory
			.getLogger(StartTimerWindow.class);
	public static final Logger reportLogger = LoggerFactory
			.getLogger(ApplicationInfo.REPORT_LOGGER);

	private static final long serialVersionUID = 4105760033118242900L;

	private JTextArea taFocusOn;
	private JTextField tfTitle;
	private JTextField txtDurata;

	private ValidatorService validatorService = null;

	public StartTimerWindow() {
		initComponents();
	}

	public StartTimerWindow(String title, String note) {
		initComponents();
		tfTitle.setText(title);
		taFocusOn.setText(note);
	}

	private void initComponents() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JTabbedPane tabTimers = new JTabbedPane();

		tabTimers.addTab(I18N.getLabel(this.getClass(), TAB_NEW_TIMER_LABEL),
				getPnlNewTimer());

		tabTimers.addTab(I18N.getLabel(this.getClass(), TAB_TIMER_LIST_LABEL),
				new JPanel());

		ChangeListener changeListener = new TabbedPaneChangeListener();

		tabTimers.addChangeListener(changeListener);

		getContentPane().add(tabTimers);

		validatorService = (ValidatorService) Register
				.get(ValidatorService.class);

		getBasicDao();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				// do not close the application from here ...
			}

		});
	}

	private Component getPnlNewTimer() {
		setTitle(I18N.getLabel(getClass(), "window.title"));
		JPanel pnlNewTimer = new JPanel();
		LayoutManager layout = new MigLayout("flowy", "[328.00][grow,fill]",
				"[][][][][]");
		taFocusOn = new JTextArea();
		taFocusOn.setWrapStyleWord(true);
		JScrollPane spFocusOn = new JScrollPane();
		tfTitle = new JTextField(30);
		JButton btnStart = new JButton();

		pnlNewTimer.setLayout(layout);
		btnStart.setText(I18N.getLabel(this.getClass(),
				TAB_NEW_TIMER_BTN_START_LABEL));
		taFocusOn.setColumns(20);
		taFocusOn.setForeground(new Color(0, 153, 0));
		taFocusOn.setRows(5);
		taFocusOn.setTabSize(2);
		taFocusOn.setText(I18N.getLabel(this.getClass(),
				TAB_NEW_TIMER_FOCUS_ON_TEXT_AREA_TEXT));
		taFocusOn.setToolTipText(I18N.getLabel(this.getClass(),
				TAB_NEW_TIMER_FOCUS_ON_TEXT_AREA_TOOL_TIP));
		spFocusOn.setViewportView(taFocusOn);
		taFocusOn.getAccessibleContext().setAccessibleName("taFocusOn");

		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				storeDataAndStartTimer();
			}
		});
		btnStart.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				logger.debug(evt.getKeyCode() + ", " + evt.getKeyChar());
				if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
					storeDataAndStartTimer();
				}
			}
		});

		JLabel lblTitle = new JLabel(I18N.getLabel(this.getClass(),
				TAB_NEW_TIMER_TITLE_LABEL));

		pnlNewTimer.add(lblTitle, "cell 0 0");
		pnlNewTimer.add(tfTitle, "cell 0 1 2 1,growx");
		JLabel lblFocusOn = new JLabel(I18N.getLabel(this.getClass(),
				TAB_NEW_TIMER_FOCUS_ON_LABEL));
		pnlNewTimer.add(lblFocusOn, "cell 0 2");
		pnlNewTimer.add(spFocusOn, "cell 0 3 2 1,growx");
		pnlNewTimer.add(btnStart, "cell 0 4 2 1,growx");

		Box horizontalBox = Box.createHorizontalBox();
		pnlNewTimer.add(horizontalBox, "flowx,cell 1 0");

		JLabel lblDurata = new JLabel(I18N.getLabel(this.getClass(),
				TAB_NEW_TIMER_DURATA_LABEL));
		lblDurata.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlNewTimer.add(lblDurata, "cell 1 0,alignx right");
		lblDurata.setLabelFor(txtDurata);

		txtDurata = new JTextField("20");
		txtDurata.setToolTipText(I18N.getLabel(this.getClass(),
				TAB_NEW_TIMER_DURATA_TOOL_TIP));
		pnlNewTimer.add(txtDurata, "cell 1 0,alignx center");
		txtDurata.setColumns(2);

		return pnlNewTimer;
	}

	private void storeDataAndStartTimer() {

		// prepare data to store
		Tomato tomato = new Tomato(taFocusOn.getText());
		tomato.setTitle(tfTitle.getText());

		try {
			tomato.setDuration(Integer.parseInt(txtDurata.getText()));
		} catch (NumberFormatException ex) {
			tomato.setDuration(1);
		}

		List<String> errorMessages = validatorService.validate(tomato);
		int response = 0;
		if (errorMessages.size() > 0) {
			String message = StringUtils.join(errorMessages, "\n");
			response = JOptionPane.showOptionDialog(null, message,
					I18N.getLabel(this.getClass(), "error.box.title"),
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
					null, new Object[] {}, null);
		}

		if (response > -1) {

			getBasicDao().save(tomato);
			logger.debug(tomato.toString());

			StringBuilder sb = new StringBuilder();
			sb.append((new LocalDateTime()).toString("dd/MM/yyyy HH:mm"))
					.append("\n");
			sb.append(I18N.getLabel(this.getClass(), TIMER_LOGGER_TITOLO))
					.append(tomato.getTitle()).append("\n");
			sb.append(I18N.getLabel(this.getClass(), TIMER_LOGGER_FOCUS_ON))
					.append(tomato.getFocusOn());

			reportLogger.info(sb.toString());

			disableNewTimerMenuItem();

			startCountdown(tomato);

			UINotificationService uiNotificationService = new UINotificationService(
					tomato);
			Register.put(UINotificationService.class, uiNotificationService);

			closeThisWindow();
		}
	}

	private void disableNewTimerMenuItem() {
		TrayIcon trayIcon = (TrayIcon) Register.get(TrayIcon.class);
		trayIcon.getPopupMenu().getItem(1).setEnabled(false);
	}

	private void closeThisWindow() {
		setVisible(false);
		dispose();
		// WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		// Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}

	private void startCountdown(Tomato tomato) {
		Countdown countdown = new Countdown(tomato.getDuration());
		Callable<JFrame> task = new OpenEndTimerWindow(tomato);
		countdown.start(task);
	}

	private HibernateBasicDaoImpl getBasicDao() {
		HibernateBasicDaoImpl basicDao = (HibernateBasicDaoImpl) Register
				.get(HibernateBasicDaoImpl.class);

		if (basicDao != null) {
			return basicDao;
		} else {

			basicDao = new HibernateBasicDaoImpl();
			Register.put(HibernateBasicDaoImpl.class, basicDao);

		}
		return basicDao;
	}
}
