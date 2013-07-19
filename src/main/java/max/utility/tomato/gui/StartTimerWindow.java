package max.utility.tomato.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.TrayIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.Callable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import max.utility.tomato.Countdown;
import max.utility.tomato.Register;
import max.utility.tomato.dao.HibernateBasicDaoImpl;
import max.utility.tomato.domain.Tomato;
import max.utility.tomato.domain.TomatoReview;
import max.utility.tomato.tasks.OpenEndTimerWindow;
import net.miginfocom.swing.MigLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class StartTimerWindow extends JFrame {

	public static final Logger logger = LoggerFactory
			.getLogger(StartTimerWindow.class);
	public static final Logger timerLogger = LoggerFactory
			.getLogger("TimerLogger");

	/**
	 * 
	 */
	private static final long serialVersionUID = 4105760033118242900L;

	private final HibernateBasicDaoImpl basicDao;
	private JTextArea taFocusOn;
	private JTextField tfTitle;
	private JTextField txtDurata;

	public StartTimerWindow(HibernateBasicDaoImpl tomatoDao) {
		basicDao = tomatoDao;
		initComponents();
	}

	public StartTimerWindow() {
		basicDao = (HibernateBasicDaoImpl) Register
				.get(HibernateBasicDaoImpl.class);
		initComponents();
	}

	public StartTimerWindow(String title) {
		basicDao = (HibernateBasicDaoImpl) Register
				.get(HibernateBasicDaoImpl.class);
		initComponents();
		tfTitle.setText(title);
	}

	private Component getPnlNewTimer() {

		JPanel pnlNewTimer = new JPanel();
		LayoutManager layout = new MigLayout("flowy", "[328.00][grow,fill]",
				"[][][][][]");
		taFocusOn = new JTextArea();
		taFocusOn.setWrapStyleWord(true);
		JScrollPane spFocusOn = new JScrollPane();
		tfTitle = new JTextField(30);
		JButton btnStart = new JButton();

		pnlNewTimer.setLayout(layout);
		btnStart.setText("inizia");
		taFocusOn.setColumns(20);
		taFocusOn.setForeground(new Color(0, 153, 0));
		taFocusOn.setRows(5);
		taFocusOn.setTabSize(2);
		taFocusOn
				.setText("descrivi cosa intendi fare nei prossimi 20 minuti... \n[Ctrl+tab] per togliere il \"focus\" dall' area di testo.\n max 250 caratteri");
		taFocusOn
				.setToolTipText("cosa intendi fare nei prossimi 20 minuti? \n[Ctrl+tab] per togliere il \"focus\" dall' area di testo");
		spFocusOn.setViewportView(taFocusOn);
		taFocusOn.getAccessibleContext().setAccessibleName("taFocusOn");

		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				btnStartTomatoMouseClicked(evt);
			}
		});
		btnStart.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				btnStartTomatoKeyPressed(evt);
			}
		});
		JLabel lblTitle = new JLabel("titolo:");

		pnlNewTimer.add(lblTitle, "cell 0 0");
		pnlNewTimer.add(tfTitle, "cell 0 1 2 1,growx");
		JLabel lblFocusOn = new JLabel("voglio fare:");
		pnlNewTimer.add(lblFocusOn, "cell 0 2");
		pnlNewTimer.add(spFocusOn, "cell 0 3 2 1,growx");
		pnlNewTimer.add(btnStart, "cell 0 4 2 1,growx");

		Box horizontalBox = Box.createHorizontalBox();
		pnlNewTimer.add(horizontalBox, "flowx,cell 1 0");

		JLabel lblDurata = new JLabel("durata");
		lblDurata.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlNewTimer.add(lblDurata, "cell 1 0,alignx right");
		lblDurata.setLabelFor(txtDurata);

		txtDurata = new JTextField("20");
		txtDurata.setToolTipText("durata del timer in minuti");
		pnlNewTimer.add(txtDurata, "cell 1 0,alignx center");
		txtDurata.setColumns(2);

		return pnlNewTimer;
	}

	private JPanel getItemDataPanel1(TomatoReview tomatoReview) {
		JPanel pnlShowItemData = new JPanel();
		// addSeparator(pnlShowItemData, "General");
		JLabel lblFocusOn = new JLabel("focus on:");
		JLabel lblDone = new JLabel("done:");
		JLabel lblProblemsRaised = new JLabel("issues:");
		JTextArea txtFocusOn = new JTextArea();
		JTextArea txtDone = new JTextArea();
		JTextArea txtProblemsRaised = new JTextArea();

		txtFocusOn.setColumns(10);
		txtDone.setColumns(10);
		txtProblemsRaised.setColumns(10);

		// txtFocusOn.setText(tomatoReview.getTomato().getFocusOn().trim());
		// txtDone.setText(tomatoReview.getReallyDone().trim());
		// txtProblemsRaised.setText(tomatoReview.getProblemsRaised().trim());

		// LayoutManager layout = new MigLayout("", "grow,fill", "[][][][]15");
		// pnlShowItemData.setLayout(layout);
		MigLayout migLayout = new MigLayout();
		pnlShowItemData.setLayout(new MigLayout("", "[grow][grow]"));

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.GREEN);
		pnlShowItemData.add(separator, "cell 0 0, growx, span 2");
		separator.setBackground(Color.GREEN);

		// "cell column row width height"
		// pnlShowItemData.add(lblFocusOn );
		// pnlShowItemData.add(lblDone, "");
		//
		// pnlShowItemData.add(txtFocusOn);
		// pnlShowItemData.add(txtDone);
		//
		// pnlShowItemData.add(lblProblemsRaised, "spanx 2");
		// pnlShowItemData.add(txtProblemsRaised, "spanx 2, grow");
		return pnlShowItemData;

	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JTabbedPane tabTimers = new JTabbedPane();
		tabTimers.addTab("nuovo timer", getPnlNewTimer());
		tabTimers.addTab("lista dei timer", new JPanel());
		
		
		ChangeListener changeListener = new TabbedPaneChangeListener();
		
		tabTimers.addChangeListener(changeListener);

		// tabTimers.addTab("nuovo timer", new JPanel());
		// tabTimers.addTab("lista dei timer", getFakePnlTimersList());

		getContentPane().add(tabTimers);
		// pack();
	}

	private Container getFakePnlTimersList() {
		JPanel pnlTimerList = new JPanel(new MigLayout("flowy, fill"));

		pnlTimerList.add(getFakeItemDataPanel());
		pnlTimerList.add(getFakeItemDataPanel());
		pnlTimerList.add(getFakeItemDataPanel());

		JScrollPane pnlScrollList = new JScrollPane(pnlTimerList);
		return pnlScrollList;
	}

	private JPanel getFakeItemDataPanel() {
		JPanel pnlShowItemData = new JPanel();
		JLabel lblFocusOn = new JLabel("focus");
		JLabel lblDone = new JLabel("done");
		JLabel lblProblemsRaised = new JLabel("note");

		JTextArea txtFocusOn = new JTextArea();
		JTextArea txtDone = new JTextArea();
		JTextArea txtProblemsRaised = new JTextArea();
		txtFocusOn.setColumns(10);
		txtDone.setColumns(10);
		txtProblemsRaised.setColumns(10);
		// txtFocusOn.setText(tomatoReview.getTomato().getFocusOn());
		// txtDone.setText(tomatoReview.getReallyDone());
		// txtProblemsRaised.setText(tomatoReview.getProblemsRaised());

		LayoutManager layout = new MigLayout("", "[grow][]", "[][grow][][][]");
		pnlShowItemData.setLayout(layout);
		pnlShowItemData
				.setLayout(new MigLayout("", "[grow][grow]", "[][][][]"));

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.GREEN);
		pnlShowItemData.add(separator, "cell 0 0, growx, span 2");

		// "cell column row width height"
		pnlShowItemData.add(lblFocusOn, "cell 0 1");
		pnlShowItemData.add(lblDone, "cell 1 1");
		pnlShowItemData.add(txtFocusOn, "cell 0 2");
		pnlShowItemData.add(txtDone, "cell 1 2");
		pnlShowItemData.add(lblProblemsRaised, "cell 0 3");
		pnlShowItemData.add(txtProblemsRaised, "cell 0 4 2 1");

		return pnlShowItemData;

	}

	private void btnStartTomatoKeyPressed(KeyEvent evt) {
		logger.debug(evt.getKeyCode() + ", " + evt.getKeyChar());
		if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
			storeDataAndStartTimer();
		}
	}

	private void btnStartTomatoMouseClicked(MouseEvent evt) {
		storeDataAndStartTimer();
	}

	private void storeDataAndStartTimer() {
		Tomato tomato = new Tomato(taFocusOn.getText());

		tomato.setTitle(tfTitle.getText());

		try {
			tomato.setDuration(Integer.parseInt(txtDurata.getText()));
		} catch (NumberFormatException ex) {
			tomato.setDuration(1);
		}

		basicDao.save(tomato);
		logger.debug(tomato.toString());
		timerLogger.info("\nTitolo: " + tomato.getTitle() + " \n Focus on:\n"
				+ tomato.getFocusOn());

		startCountdown(tomato);

		TrayIconMouseMotionListener listener = (TrayIconMouseMotionListener) Register
				.get(TrayIconMouseMotionListener.class);
		listener.setTomato(tomato);
		closeThisWindow();
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
}