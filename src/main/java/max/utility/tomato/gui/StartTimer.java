package max.utility.tomato.gui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import max.utility.tomato.Countdown;
import max.utility.tomato.DaoRegister;
import max.utility.tomato.dao.HibernateBasicDaoImpl;
import max.utility.tomato.domain.Tomato;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartTimer extends javax.swing.JFrame {

	public static final Logger logger = LoggerFactory.getLogger(StartTimer.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 4105760033118242900L;

	private final HibernateBasicDaoImpl basicDao;

	// Variables declaration - do not modify
	private javax.swing.JButton btnStartTomato;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTable jTable1;
	private javax.swing.JLabel lbl_focusOn;
	private javax.swing.JTextArea ta_focusOn;
	private javax.swing.JPanel tab_newTimer;
	private javax.swing.JPanel tab_timers;
	// End of variables declaration
	private javax.swing.JTabbedPane tab_Timers;

	/**
	 * Creates new form StartTimer
	 */
	public StartTimer() {
		initComponents();
		basicDao = (HibernateBasicDaoImpl) DaoRegister.get(HibernateBasicDaoImpl.class);
	}

	public StartTimer(HibernateBasicDaoImpl tomatoDao) {
		initComponents();
		basicDao = tomatoDao;
	}

	private void btnStartTomatoKeyPressed(java.awt.event.KeyEvent evt) {
		logger.debug(evt.getKeyCode() + ", " + evt.getKeyChar());
		if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
			saveTomato(ta_focusOn.getText());
		}
	}

	private void btnStartTomatoMouseClicked(java.awt.event.MouseEvent evt) {
		saveTomato(ta_focusOn.getText());
	}

	private void countDown(final Long tomatoId) {
		Countdown beeperControl = new Countdown(this);
		beeperControl.start(tomatoId);
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		setTitle("start timer");
		tab_Timers = new javax.swing.JTabbedPane();
		tab_newTimer = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		ta_focusOn = new javax.swing.JTextArea();
		lbl_focusOn = new javax.swing.JLabel();
		btnStartTomato = new javax.swing.JButton();
		tab_timers = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();

		jTable1.setRowHeight(40);
		jTable1.setModel(new JPAPaginationTableModel());
		jTable1.setDefaultRenderer(Object.class, new EvenOddRowCellRenderer());
		jTable1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					JTextArea ta = new JTextArea();
					ta.setLineWrap(true);
					int constant = 400;
					int constant1 = 300;
					ta.setSize(constant, constant1);
					ta.setEditable(false);
					String text = (String) jTable1.getModel().getValueAt(row, column);
					ta.setText(text);
					ta.setLineWrap(true);
					JScrollPane sp = new JScrollPane(ta);
					sp.setSize(constant, constant1);
					sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
					JOptionPane.showMessageDialog(jTable1, sp);
				}

			}
		});

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		ta_focusOn.setColumns(20);
		ta_focusOn.setForeground(new java.awt.Color(0, 153, 0));
		ta_focusOn.setRows(5);
		ta_focusOn.setTabSize(2);
		ta_focusOn
				.setText("descrivi cosa intendi fare nei prossimi 20 minuti... \n[Ctrl+tab] per togliere il \"focus\" dall' area di testo.\n max 250 caratteri");
		ta_focusOn
				.setToolTipText("descrivi cosa intendi fare nei prossimi 20 minuti... \n[Ctrl+tab] per togliere il \"focus\" dall' area di testo");
		jScrollPane1.setViewportView(ta_focusOn);
		ta_focusOn.getAccessibleContext().setAccessibleName("ta_focusOn");

		lbl_focusOn.setLabelFor(ta_focusOn);
		lbl_focusOn.setText("Focus");

		btnStartTomato.setText("inizia");
		btnStartTomato.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnStartTomatoMouseClicked(evt);
			}
		});
		btnStartTomato.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				btnStartTomatoKeyPressed(evt);
			}
		});

		javax.swing.GroupLayout tab_newTimerLayout = new javax.swing.GroupLayout(tab_newTimer);
		tab_newTimer.setLayout(tab_newTimerLayout);
		tab_newTimerLayout.setHorizontalGroup(tab_newTimerLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						tab_newTimerLayout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										tab_newTimerLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														tab_newTimerLayout
																.createSequentialGroup()
																.addComponent(lbl_focusOn,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 36,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(0, 0, Short.MAX_VALUE))).addContainerGap())
				.addGroup(
						tab_newTimerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
								tab_newTimerLayout.createSequentialGroup().addGap(228, 228, 228)
										.addComponent(btnStartTomato).addContainerGap(229, Short.MAX_VALUE))));
		tab_newTimerLayout.setVerticalGroup(tab_newTimerLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						tab_newTimerLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(lbl_focusOn)
								.addGap(1, 1, 1)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134,
										javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(78, Short.MAX_VALUE))
				.addGroup(
						tab_newTimerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
								tab_newTimerLayout.createSequentialGroup().addGap(194, 194, 194)
										.addComponent(btnStartTomato).addContainerGap(21, Short.MAX_VALUE))));

		tab_Timers.addTab("nuovo timer", tab_newTimer);

		jScrollPane2.setViewportView(jTable1);

		javax.swing.GroupLayout tab_timersLayout = new javax.swing.GroupLayout(tab_timers);
		tab_timers.setLayout(tab_timersLayout);
		tab_timersLayout
				.setHorizontalGroup(tab_timersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE));
		tab_timersLayout.setVerticalGroup(tab_timersLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane2,
				javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE));

		tab_Timers.addTab("lista dei timer", tab_timers);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap().addComponent(tab_Timers).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup()
						.addComponent(tab_Timers, javax.swing.GroupLayout.PREFERRED_SIZE, 245,
								javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(81, Short.MAX_VALUE)));

		tab_Timers.getAccessibleContext().setAccessibleName("lista dei timer");

		pack();
	}

	void saveTomato(String focusOn) {
		Tomato tomato = new Tomato(focusOn);
		basicDao.save(tomato);
		logger.info(tomato.toString());
		setVisible(false);
		dispose();
		countDown(tomato.getId());
	}
}