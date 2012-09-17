package max.utility.tomato.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

//http://www.coderanch.com/t/340609/GUI/java/JTable-Custom-Cell-Renderer-JTextArea
public class FocusOnCellRenderer extends JTextArea implements TableCellRenderer {

	private final Color evenColor = new Color(230, 240, 255);

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(table.getBackground());
			setBackground((row % 2 == 0) ? evenColor : getBackground());
		}
		setFont(table.getFont());
		setText((value == null) ? "" : value.toString());
		return this;

	}
}
