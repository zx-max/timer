package max.utility.tomato.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

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
	private JButton btn_reviewCompleted;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane3;
	private JScrollPane jScrollPane4;
	private JLabel lbl_focusOn;
	private JLabel lbl_problemsRaised;
	private JLabel lbl_reallyDone;
	private JLabel lbl_review;
	private JTextArea ta_focusOn;
	private JTextArea ta_problemsRaised;
	private JTextArea ta_reallyDone;
	private final Tomato tomato;

	// End of variables declaration

	public EndTimer(final Long tomatoId) {
		setTitle("End Timer");
		basicDao = (HibernateBasicDaoImpl) DaoRegister.get(HibernateBasicDaoImpl.class);
		tomato = basicDao.load(Tomato.class, tomatoId);
		initComponents();
		setVisible(true);
		logger.debug(tomato.toString());
	}

	private void btn_reviewCompletedKeyPressed(KeyEvent evt) {
		logger.debug(evt.getKeyCode() + ", " + evt.getKeyChar());
		if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
			saveAndCloseWindow();
		}
	}

	private void btn_reviewCompletedMouseClicked(MouseEvent evt) {
		saveAndCloseWindow();
	}

	private void initComponents() {

		lbl_review = new JLabel();
		lbl_focusOn = new JLabel();
		jScrollPane1 = new JScrollPane();
		ta_focusOn = new JTextArea();
		lbl_reallyDone = new JLabel();
		jScrollPane4 = new JScrollPane();
		ta_reallyDone = new JTextArea();
		lbl_problemsRaised = new JLabel();
		jScrollPane3 = new JScrollPane();
		ta_problemsRaised = new JTextArea();
		btn_reviewCompleted = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBackground(new Color(0, 0, 0));

		lbl_review.setFont(new Font("Comic Sans MS", 0, 14)); // NOI18N
		lbl_review.setForeground(new Color(255, 153, 0));
		lbl_review.setText("rivedi quanto fatto");

		lbl_focusOn.setBackground(new Color(0, 0, 0));
		lbl_focusOn.setForeground(new Color(0, 204, 204));
		lbl_focusOn.setLabelFor(ta_focusOn);
		lbl_focusOn.setText("volevo fare ...");

		ta_focusOn.setEditable(false);
		ta_focusOn.setBackground(new Color(0, 0, 0));
		ta_focusOn.setColumns(20);
		ta_focusOn.setFont(new Font("Andalus", 0, 13)); // NOI18N
		ta_focusOn.setForeground(new Color(0, 255, 255));
		ta_focusOn.setRows(10);
		ta_focusOn.setTabSize(2);
		ta_focusOn.setFocusable(false);
		ta_focusOn.setText(tomato.getFocusOn());
		ta_focusOn.setToolTipText("breve revisione di quanto fatto ..");
		ta_focusOn.setMargin(new Insets(0, 2, 2, 2));
		jScrollPane1.setViewportView(ta_focusOn);

		lbl_reallyDone.setForeground(new Color(0, 0, 255));
		lbl_reallyDone.setLabelFor(ta_focusOn);
		lbl_reallyDone.setText("ho fatto");

		ta_reallyDone.setColumns(20);
		ta_reallyDone.setFont(new Font("Andalus", 0, 13)); // NOI18N
		ta_reallyDone.setForeground(new Color(0, 0, 255));
		ta_reallyDone.setRows(10);
		ta_reallyDone.setTabSize(2);
		ta_reallyDone.setText("salva le ultime modifiche.\ncommit -m \"fine timer\"\n");
		ta_reallyDone.setToolTipText("breve revisione di quanto fatto ..");
		// ta_reallyDone.setFocusCycleRoot(true);
		ta_reallyDone.setMargin(new Insets(0, 2, 2, 2));
		ta_reallyDone.requestFocusInWindow();
		ta_reallyDone.setNextFocusableComponent(ta_problemsRaised);
		jScrollPane4.setViewportView(ta_reallyDone);

		lbl_problemsRaised.setForeground(new Color(204, 0, 0));
		lbl_problemsRaised.setLabelFor(ta_problemsRaised);
		lbl_problemsRaised.setText("difficolt�, impedimenti, problemi, ...");

		jScrollPane3.setBackground(new Color(204, 0, 0));

		ta_problemsRaised.setColumns(20);
		ta_problemsRaised.setFont(new Font("Andalus", 0, 13)); // NOI18N
		ta_problemsRaised.setForeground(new Color(204, 0, 0));
		ta_problemsRaised.setRows(10);
		ta_problemsRaised.setTabSize(2);
		ta_problemsRaised
				.setText("finestre di start e stop (versione base) ok\nformattazione del codice ok\neseguire il codice da riga di comando ok\navvio e stop del timer ok\n");
		ta_problemsRaised.setToolTipText("breve revisione di quanto fatto ..");
		// ta_problemsRaised.setFocusCycleRoot(true);
		ta_problemsRaised.setMargin(new Insets(0, 2, 2, 2));
		ta_problemsRaised.setNextFocusableComponent(btn_reviewCompleted);
		jScrollPane3.setViewportView(ta_problemsRaised);

		btn_reviewCompleted.setText("ok");
		// btn_reviewCompleted.setFocusCycleRoot(true);
		btn_reviewCompleted.setNextFocusableComponent(ta_focusOn);
		btn_reviewCompleted.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				btn_reviewCompletedMouseClicked(evt);
			}
		});
		btn_reviewCompleted.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				btn_reviewCompletedKeyPressed(evt);
			}
		});

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addGroup(
														GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addGap(0, 0, Short.MAX_VALUE)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addComponent(jScrollPane4,
																						GroupLayout.Alignment.TRAILING,
																						GroupLayout.PREFERRED_SIZE,
																						500, GroupLayout.PREFERRED_SIZE)
																				.addComponent(jScrollPane3,
																						GroupLayout.Alignment.TRAILING,
																						GroupLayout.PREFERRED_SIZE,
																						500, GroupLayout.PREFERRED_SIZE)))
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
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
																												GroupLayout.Alignment.LEADING)
																												.addComponent(
																														jScrollPane1,
																														GroupLayout.PREFERRED_SIZE,
																														500,
																														GroupLayout.PREFERRED_SIZE)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addComponent(
																																		lbl_focusOn,
																																		GroupLayout.PREFERRED_SIZE,
																																		79,
																																		GroupLayout.PREFERRED_SIZE)
																																.addGap(92,
																																		92,
																																		92)
																																.addComponent(
																																		lbl_review)))))
																.addGap(0, 0, Short.MAX_VALUE))).addContainerGap())
				.addGroup(
						layout.createSequentialGroup().addGap(237, 237, 237).addComponent(btn_reviewCompleted)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(
												layout.createSequentialGroup().addComponent(lbl_review)
														.addGap(15, 15, 15))
										.addGroup(
												GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup().addComponent(lbl_focusOn)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
						.addGap(19, 19, 19).addComponent(lbl_reallyDone)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
						.addGap(33, 33, 33).addComponent(lbl_problemsRaised).addGap(1, 1, 1)
						.addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(btn_reviewCompleted).addContainerGap(27, Short.MAX_VALUE)));

		pack();
	}

	public JFrame openWindow() {
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
		return this;
	}

	private void saveAndCloseWindow() {
		basicDao.save(new TomatoReview(tomato, ta_reallyDone.getText(), ta_problemsRaised.getText()));
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}

}
