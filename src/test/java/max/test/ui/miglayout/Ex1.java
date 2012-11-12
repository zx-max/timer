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

		JPanel panel1 = constraintsByString();
		JPanel panel2 = constraintsByApi();

		getContentPane().add(panel1);
		// getContentPane().add(panel2);
	}

	private JPanel constraintsByString() {

		String layoutConstraint = "fillx";
		String columAxisConstraint = "[right]rel[grow,fill]";
		String rowAxisConstraint = "[]10[]";

		MigLayout layout = new MigLayout(layoutConstraint, columAxisConstraint, rowAxisConstraint);

		JPanel panel1 = new JPanel(layout);
		JLabel lblSize = new JLabel("Enter size:");
		JLabel lblWieght = new JLabel("Enter weight:");

		panel1.add(lblSize, "");
		panel1.add(new JTextField(""), "wrap");
		panel1.add(lblWieght, "");
		panel1.add(new JTextField(""), "");

		return panel1;
	}

	private JPanel constraintsByApi() {

		LC layoutConstraint = new LC().fillX();
		AC columAxisConstraint = new AC().align("right").gap("rel").grow().fill();
		AC rowAxisConstraint = new AC().gap("10");

		MigLayout layout = new MigLayout(layoutConstraint, columAxisConstraint, rowAxisConstraint);

		JLabel lblSize = new JLabel("Enter size:");
		JLabel lblWieght = new JLabel("Enter weight:");
		JPanel panel2 = new JPanel(layout);

		panel2.add(lblSize);
		panel2.add(new JTextField(""), new CC().wrap());
		panel2.add(lblWieght);
		panel2.add(new JTextField(""));

		return panel2;
	}
}
