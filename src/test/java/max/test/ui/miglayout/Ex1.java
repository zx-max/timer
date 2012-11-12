package max.test.ui.miglayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class Ex1 extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ex1 frame = new Ex1();
					frame.run();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void run() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		// Layout, Column and Row constraints as arguments.
		MigLayout layout1 = new MigLayout("fillx", "[right]rel[grow,fill]", "[]10[]");
		JPanel panel1 = new JPanel(layout1);

		panel1.add(new JLabel("Enter size:"), "");
		panel1.add(new JTextField(""), "wrap");
		panel1.add(new JLabel("Enter weight:"), "");
		panel1.add(new JTextField(""), "");

		// Or the same layout with the API constraint building. Sice they are so
		// similar the API version will not be handled much further in this
		// white paper.

		// Layout, Column and Row constraints as arguments.
		MigLayout layout2 = new MigLayout(new LC().fillX(), new AC().align("right").gap("rel").grow().fill(), new AC().gap("10"));

		JPanel panel2 = new JPanel(layout2);

		panel2.add(new JLabel("Enter size:"));
		panel2.add(new JTextField(""), new CC().wrap());
		panel2.add(new JLabel("Enter weight:"));
		panel2.add(new JTextField(""));

		getContentPane().add(panel1);
	}
}
