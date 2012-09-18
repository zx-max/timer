package max.utility.tomato.gui;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import max.utility.tomato.DaoRegister;
import max.utility.tomato.dao.HibernateBasicDaoImpl;
import max.utility.tomato.domain.Tomato;
import max.utility.tomato.domain.TomatoReview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndTimer extends JFrame {

	public static final Logger logger = LoggerFactory.getLogger(EndTimer.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 8849013985135065049L;

	private final HibernateBasicDaoImpl basicDao;
	// Variables declaration - do not modify
	private javax.swing.JButton btn_reviewCompleted;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JLabel lbl_focusOn;
	private javax.swing.JLabel lbl_problemsRaised;
	private javax.swing.JLabel lbl_reallyDone;
	private javax.swing.JLabel lbl_review;
	private javax.swing.JTextArea ta_focusOn;
	private javax.swing.JTextArea ta_problemsRaised;
	private javax.swing.JTextArea ta_reallyDone;
	private final Tomato tomato;

	// End of variables declaration

	public EndTimer(final Long tomatoId) {
		setTitle("End Timer");
		basicDao = (HibernateBasicDaoImpl) DaoRegister.get(HibernateBasicDaoImpl.class);
		tomato = (Tomato) basicDao.load(Tomato.class, tomatoId);
		initComponents();
		setVisible(true);
		logger.debug(tomato.toString());
	}

	private void btn_reviewCompletedKeyPressed(java.awt.event.KeyEvent evt) {
		logger.debug(evt.getKeyCode() + ", " + evt.getKeyChar());
		if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
			saveAndCloseWindow();
		}
	}

	private void btn_reviewCompletedMouseClicked(java.awt.event.MouseEvent evt) {
		saveAndCloseWindow();
	}

	private void initComponents() {

		lbl_review = new javax.swing.JLabel();
		lbl_focusOn = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		ta_focusOn = new javax.swing.JTextArea();
		lbl_reallyDone = new javax.swing.JLabel();
		jScrollPane4 = new javax.swing.JScrollPane();
		ta_reallyDone = new javax.swing.JTextArea();
		lbl_problemsRaised = new javax.swing.JLabel();
		jScrollPane3 = new javax.swing.JScrollPane();
		ta_problemsRaised = new javax.swing.JTextArea();
		btn_reviewCompleted = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(new java.awt.Color(0, 0, 0));

		lbl_review.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
		lbl_review.setForeground(new java.awt.Color(255, 153, 0));
		lbl_review.setText("rivedi quanto fatto");

		lbl_focusOn.setBackground(new java.awt.Color(0, 0, 0));
		lbl_focusOn.setForeground(new java.awt.Color(0, 204, 204));
		lbl_focusOn.setLabelFor(ta_focusOn);
		lbl_focusOn.setText("volevo fare ...");

		ta_focusOn.setEditable(false);
		ta_focusOn.setBackground(new java.awt.Color(0, 0, 0));
		ta_focusOn.setColumns(20);
		ta_focusOn.setFont(new java.awt.Font("Andalus", 0, 13)); // NOI18N
		ta_focusOn.setForeground(new java.awt.Color(0, 255, 255));
		ta_focusOn.setRows(10);
		ta_focusOn.setTabSize(2);
		ta_focusOn.setFocusable(false);
		ta_focusOn.setText(tomato.getFocusOn());
		ta_focusOn.setToolTipText("breve revisione di quanto fatto ..");
		ta_focusOn.setMargin(new java.awt.Insets(0, 2, 2, 2));
		jScrollPane1.setViewportView(ta_focusOn);

		lbl_reallyDone.setForeground(new java.awt.Color(0, 0, 255));
		lbl_reallyDone.setLabelFor(ta_focusOn);
		lbl_reallyDone.setText("ho fatto");

		ta_reallyDone.setColumns(20);
		ta_reallyDone.setFont(new java.awt.Font("Andalus", 0, 13)); // NOI18N
		ta_reallyDone.setForeground(new java.awt.Color(0, 0, 255));
		ta_reallyDone.setRows(10);
		ta_reallyDone.setTabSize(2);
		ta_reallyDone.setText("salva le ultime modifiche.\ncommit -m \"fine timer\"\n");
		ta_reallyDone.setToolTipText("breve revisione di quanto fatto ..");
		// ta_reallyDone.setFocusCycleRoot(true);
		ta_reallyDone.setMargin(new java.awt.Insets(0, 2, 2, 2));
		ta_reallyDone.requestFocusInWindow();
		ta_reallyDone.setNextFocusableComponent(ta_problemsRaised);
		jScrollPane4.setViewportView(ta_reallyDone);

		lbl_problemsRaised.setForeground(new java.awt.Color(204, 0, 0));
		lbl_problemsRaised.setLabelFor(ta_problemsRaised);
		lbl_problemsRaised.setText("difficoltà, impedimenti, problemi, ...");

		jScrollPane3.setBackground(new java.awt.Color(204, 0, 0));

		ta_problemsRaised.setColumns(20);
		ta_problemsRaised.setFont(new java.awt.Font("Andalus", 0, 13)); // NOI18N
		ta_problemsRaised.setForeground(new java.awt.Color(204, 0, 0));
		ta_problemsRaised.setRows(10);
		ta_problemsRaised.setTabSize(2);
		ta_problemsRaised
				.setText("finestre di start e stop (versione base) ok\nformattazione del codice ok\neseguire il codice da riga di comando ok\navvio e stop del timer ok\n");
		ta_problemsRaised.setToolTipText("breve revisione di quanto fatto ..");
		// ta_problemsRaised.setFocusCycleRoot(true);
		ta_problemsRaised.setMargin(new java.awt.Insets(0, 2, 2, 2));
		ta_problemsRaised.setNextFocusableComponent(btn_reviewCompleted);
		jScrollPane3.setViewportView(ta_problemsRaised);

		btn_reviewCompleted.setText("ok");
		// btn_reviewCompleted.setFocusCycleRoot(true);
		btn_reviewCompleted.setNextFocusableComponent(ta_focusOn);
		btn_reviewCompleted.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btn_reviewCompletedMouseClicked(evt);
			}
		});
		btn_reviewCompleted.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				btn_reviewCompletedKeyPressed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addGap(0, 0, Short.MAX_VALUE)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jScrollPane4,
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						500,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jScrollPane3,
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						500,
																						javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(20, 20, 20)
																								.addComponent(
																										lbl_reallyDone))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(20, 20, 20)
																								.addComponent(
																										lbl_problemsRaised))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(19, 19, 19)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														jScrollPane1,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														500,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addComponent(
																																		lbl_focusOn,
																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																		79,
																																		javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addGap(92,
																																		92,
																																		92)
																																.addComponent(
																																		lbl_review)))))
																.addGap(0, 0, Short.MAX_VALUE))).addContainerGap())
				.addGroup(
						layout.createSequentialGroup().addGap(237, 237, 237).addComponent(btn_reviewCompleted)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												layout.createSequentialGroup().addComponent(lbl_review)
														.addGap(15, 15, 15))
										.addGroup(
												javax.swing.GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup()
														.addComponent(lbl_focusOn)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(19, 19, 19)
						.addComponent(lbl_reallyDone)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 111,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(33, 33, 33)
						.addComponent(lbl_problemsRaised)
						.addGap(1, 1, 1)
						.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 111,
								javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18)
						.addComponent(btn_reviewCompleted).addContainerGap(27, Short.MAX_VALUE)));

		pack();
	}

	public JFrame openWindow() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
			logger.error(null, ex);
		}
		return this;
	}

	private void saveAndCloseWindow() {
		basicDao.save(new TomatoReview(tomato, ta_reallyDone.getText(), ta_problemsRaised.getText()));
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}

}
