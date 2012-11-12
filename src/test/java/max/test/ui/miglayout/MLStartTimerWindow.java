package max.test.ui.miglayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

/**
 * Start Timer window based on Mig Layout.
 * 
 * @author MAX
 * 
 */
public class MLStartTimerWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1171031715582612973L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MLStartTimerWindow frame = new MLStartTimerWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MLStartTimerWindow() {

		init();
	}

	private Component getPnlNewTimer() {

		JPanel pnlNewTimer = new JPanel();
		LayoutManager layout = new MigLayout("flowy", "fill,grow");
		JTextArea taFocusOn = new JTextArea();
		JScrollPane spFocusOn = new JScrollPane();
		JLabel lblFocusOn = new JLabel("will focus on:");
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

		pnlNewTimer.add(lblFocusOn);
		pnlNewTimer.add(spFocusOn, "growx");
		pnlNewTimer.add(btnStart);

		return pnlNewTimer;
	}

	private Component getPnlTimersList() {
		JPanel pnlTimersList = new JPanel();
		JLabel lblFocusOn = new JLabel("focus on:");
		JLabel lblDone = new JLabel("done:");
		JLabel lblProblemsRaised = new JLabel("issues:");
		JTextArea txtFocusOn = new JTextArea();
		JTextArea txtDone = new JTextArea();
		JTextArea txtProblemsRaised = new JTextArea();

		txtFocusOn.setColumns(20);
		txtDone.setColumns(20);
		txtProblemsRaised.setColumns(20);

		LayoutManager layout = new MigLayout("", "", "");
		pnlTimersList.setLayout(layout);

		pnlTimersList.add(lblFocusOn, "cell 0 0");
		pnlTimersList.add(txtFocusOn, "cell 0 1");
		pnlTimersList.add(lblDone, "cell 1 0");
		pnlTimersList.add(txtDone, "cell 1 1 ");
		pnlTimersList.add(lblProblemsRaised, "cell 2 0");
		pnlTimersList.add(txtProblemsRaised, "cell 2 1");

		return pnlTimersList;
	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JTabbedPane tabTimers = new JTabbedPane();

		tabTimers.addTab("nuovo timer", getPnlNewTimer());
		tabTimers.addTab("lista dei timer", getPnlTimersList());
		getContentPane().add(tabTimers);
	}
}
