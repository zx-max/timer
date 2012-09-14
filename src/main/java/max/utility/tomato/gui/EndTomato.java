package max.utility.tomato.gui;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndTomato extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8849013985135065049L;
	public static final Logger logger = LoggerFactory.getLogger(EndTomato.class);

	/**
	 * Creates new form EndTomato
	 */
	public EndTomato() {
		initComponents();
		setVisible(true);
		// pack();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		this.setTitle("end timer");

		jLabel1 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText("end of the timer");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addGap(154, 154, 154).addComponent(jLabel1)
						.addContainerGap(169, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addGap(78, 78, 78).addComponent(jLabel1)
						.addContainerGap(208, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	/**
	 * @param args
	 *            the command line arguments
	 */
	public JFrame openWindow() {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
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
		// </editor-fold>

		/* Create and display the form */
		// java.awt.EventQueue.invokeLater(new Runnable() {
		// public void run() {
		// new EndTomato().setVisible(true);
		// }
		// });
	}

	// Variables declaration - do not modify
	private javax.swing.JLabel jLabel1;
	// End of variables declaration
}
