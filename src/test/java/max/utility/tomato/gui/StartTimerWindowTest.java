package max.utility.tomato.gui;

import java.awt.EventQueue;

import javax.swing.UIManager;

public class StartTimerWindowTest {

	public static void main(String[] args) {
//		try {
//			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//				if ("Nimbus".equals(info.getName())) {
//					UIManager.setLookAndFeel(info.getClassName());
//					break;
//				}
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartTimerWindow frame = new StartTimerWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
