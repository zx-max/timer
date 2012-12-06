package max.test.ui.miglayout;

import java.awt.EventQueue;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import max.utility.tomato.domain.Tomato;
import max.utility.tomato.domain.TomatoReview;
import net.miginfocom.swing.MigLayout;

public class Ex1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4299592918557121279L;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Ex1 frame = new Ex1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ex1() {

		init();
	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 456);

		LayoutManager mgr = new MigLayout("flowy", "[grow,fill]", "[grow]");
		getContentPane().setLayout(mgr);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, "cell 0 0,grow");

		JPanel tabPanel = new JPanel();
		tabbedPane.addTab("New tab", null, tabPanel, null);
		tabPanel.setLayout(new MigLayout("flowy", "[grow]", "[][]"));// , "[]",
																		// "[][15.00][15.00][]"));

		setNavigationPanel(tabPanel);

		setListPanel(tabPanel);

		for (int i = 0; i < 3; i++) {
			getContentPane().add(useCellsForComponents(), "cell 0 0,grow");
		}
	}

	private void setNavigationPanel(JPanel tabPanel) {
		JPanel navigationPanel = new JPanel();
		tabPanel.add(navigationPanel, "cell 0 0,grow");

		JLabel lblTot = new JLabel("tot: 30 ");
		navigationPanel.add(lblTot);

		JLabel lblRighePerPagina = new JLabel("righe per pagina:");
		navigationPanel.add(lblRighePerPagina);

		textField_2 = new JTextField();
		textField_2.setText("10");
		navigationPanel.add(textField_2);
		textField_2.setColumns(10);

		JButton button = new JButton("<");
		navigationPanel.add(button);

		JButton button_1 = new JButton(">");
		navigationPanel.add(button_1);
	}

	private void setListPanel(JPanel tabPanel) {
		JPanel listPanel = new JPanel();
		tabPanel.add(listPanel, "cell 0 1,grow");// , "cell 0 1 1 3,grow");
		listPanel.setLayout(new MigLayout("flowy", "grow", "[grow][grow][grow][grow][grow]"));

		JPanel listItem_1 = new JPanel();
		listPanel.add(getItemDataPanel(new TomatoReview(new Tomato("focusOn"), "done", "exsdafasdf")), "cell 0 0,grow");

		JPanel listItem_2 = new JPanel();
		listPanel.add(listItem_2, "cell 0 1,grow");
		listItem_2.setLayout(new MigLayout("", "[]", "[]"));

		JPanel listItem_3 = new JPanel();
		listPanel.add(listItem_3, "cell 0 2,grow");

		JPanel listItem_4 = new JPanel();
		listPanel.add(listItem_4, "cell 0 3,grow");

		JPanel listItem_5 = new JPanel();
		listPanel.add(listItem_5, "flowy,cell 0 4,grow");
	}

	private JPanel getItemDataPanel(TomatoReview tomatoReview) {
		JPanel pnlShowItemData = new JPanel();
		JLabel lblFocusOn = new JLabel("focus on:");
		JLabel lblDone = new JLabel("done:");
		JLabel lblProblemsRaised = new JLabel("issues:");
		JTextArea txtFocusOn = new JTextArea();
		JTextArea txtDone = new JTextArea();
		JTextArea txtProblemsRaised = new JTextArea();

		txtFocusOn.setText(tomatoReview.getTomato().getFocusOn());
		txtDone.setText(tomatoReview.getReallyDone());
		txtProblemsRaised.setText(tomatoReview.getProblemsRaised());

		LayoutManager layout = new MigLayout("", "grow,fill", "[][][][]15");
		pnlShowItemData.setLayout(layout);

		// "cell column row width height"
		pnlShowItemData.add(lblFocusOn, "cell 0 0");
		pnlShowItemData.add(lblDone, "cell 1 0");

		pnlShowItemData.add(txtFocusOn, "cell 0 1,growx ");
		pnlShowItemData.add(txtDone, "cell 1 1,growx ");

		pnlShowItemData.add(lblProblemsRaised, "cell 0 2");
		pnlShowItemData.add(txtProblemsRaised, "cell 0 3 2 1, growx");
		return pnlShowItemData;

	}

	private JPanel useCellsForComponents() {
		JTextField textField;
		JTextField textField_1;

		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("", "[][grow]", "[][]"));

		JLabel lblSize = new JLabel("Enter size:");
		textField = new JTextField();
		textField.setColumns(10);
		JLabel lblWieght = new JLabel("Enter weight:");
		textField_1 = new JTextField();
		textField_1.setColumns(10);

		panel.add(lblSize, "cell 0 0,alignx trailing");
		panel.add(textField, "cell 1 0,growx");
		panel.add(lblWieght, "cell 0 1,alignx trailing");
		panel.add(textField_1, "cell 1 1");

		return panel;
	}
}
