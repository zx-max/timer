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
		pnlNewTimer.setLayout(layout);
		JTextArea taFocusOn = new JTextArea();
		JScrollPane spFocusOn = new JScrollPane();
		JLabel lblFocusOn = new JLabel("will focus on:");

		JButton btnOk = new JButton();
		JButton btnOkAndNew = new JButton();

		taFocusOn.setColumns(20);
		taFocusOn.setForeground(new Color(0, 153, 0));
		taFocusOn.setRows(5);
		taFocusOn.setTabSize(2);
		taFocusOn
				.setText("descrivi cosa intendi fare nei prossimi 20 minuti... \n[Ctrl+tab] per togliere il \"focus\" dall' area di testo.\n max 250 caratteri");
		taFocusOn
				.setToolTipText("descrivi cosa intendi fare nei prossimi 20 minuti... \n[Ctrl+tab] per togliere il \"focus\" dall' area di testo");
		spFocusOn.setViewportView(taFocusOn);
		taFocusOn.getAccessibleContext().setAccessibleName("ta_focusOn");

		pnlNewTimer.add(lblFocusOn);
		pnlNewTimer.add(spFocusOn, "growx");
		pnlNewTimer.add(btnOk);
		pnlNewTimer.add(btnOkAndNew);
		return pnlNewTimer;
	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JTabbedPane tabTimers = new JTabbedPane();

		JPanel pnlTimersList = new JPanel();
		tabTimers.addTab("nuovo timer", getPnlNewTimer());
		tabTimers.addTab("lista dei timer", pnlTimersList);
		getContentPane().add(tabTimers);
	}
}
