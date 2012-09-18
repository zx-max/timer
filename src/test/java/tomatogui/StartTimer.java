/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tomatogui;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;

import max.utility.tomato.gui.VerticalLayout;

/**
 * 
 * @author MAX
 */
public class StartTimer extends JFrame {

	public static void main(String args[]) {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(StartTimer.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new StartTimer().setVisible(true);
			}
		});
	}

	public StartTimer() {
		initComponents();
	}

	private Component getRow() {
		JPanel row = new JPanel();
		row.setLayout(new VerticalLayout());
		JPanel tomatoSection = new JPanel();
		JPanel tomatoReviewSection = new JPanel();
		row.add(tomatoSection);
		row.add(tomatoReviewSection);
		return row;
	}

	private void initComponents() {
		setResizable(true);
		Container cp = getContentPane();
		cp.setLayout(new VerticalLayout());
		for (int i = 0; i < 5; i++) {
			Component row = getRow();
			// if (i % 2 == 0) {
			// row.setBackground(Color.cyan);
			// } else {
			// row.setBackground(Color.white);
			// }
			cp.add(row);
			JSeparator separator = new JSeparator();
			separator.setPreferredSize(getPreferredSize());
			separator.setMaximumSize(getMaximumSize());
			separator.setMinimumSize(getMinimumSize());
			separator.setBackground(new java.awt.Color(51, 51, 255));
			cp.add(separator);
		}
		pack();
	}
}
