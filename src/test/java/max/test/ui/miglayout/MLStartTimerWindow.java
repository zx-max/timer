package max.test.ui.miglayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import max.utility.tomato.gui.EvenOddRowCellRenderer;
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
		JScrollPane spTimerList = new JScrollPane();
		final JTable jTable1 = new JTable();
		spTimerList.setViewportView(jTable1);
		jTable1.setRowHeight(40);
		jTable1.setModel(new FakePaginationTableModel());
		jTable1.setDefaultRenderer(Object.class, new EvenOddRowCellRenderer());
		jTable1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					JTextArea ta = new JTextArea();
					ta.setLineWrap(true);
					int constant = 400;
					int constant1 = 300;
					ta.setSize(constant, constant1);
					ta.setEditable(false);
					String text = (String) jTable1.getModel().getValueAt(row, column);
					ta.setText(text);
					ta.setLineWrap(true);
					JScrollPane sp = new JScrollPane();
					sp.setViewportView(ta);
					sp.setSize(constant, constant1);
					sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
					sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					JOptionPane.showMessageDialog(jTable1, sp);
				}

			}
		});
		pnlTimersList.add(spTimerList);
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
