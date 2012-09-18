package max.utility.tomato.gui;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellEditor;

public class FocusOnCellEditor extends AbstractCellEditor implements TableCellEditor {

	private static final long serialVersionUID = 7031564668283411234L;
	private final JScrollPane scroll;
	private final JTextArea textArea;

	public FocusOnCellEditor() {
		super();
		scroll = new JScrollPane();
		textArea = new JTextArea();
		scroll.setViewportView(textArea);
	}

	public Object getCellEditorValue() {
		return textArea.getText();
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		textArea.setText((String) value);
		return scroll;
	}

}
