package max.utility.tomato.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import max.utility.tomato.DaoRegister;
import max.utility.tomato.dao.HibernateBasicDaoImpl;
import max.utility.tomato.domain.TomatoReview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JPAPaginationTableModel extends AbstractTableModel {

	public static final Logger logger = LoggerFactory.getLogger(JPAPaginationTableModel.class);

	private static final long serialVersionUID = 2632646700661917306L;

	private int counter;
	private final HibernateBasicDaoImpl dao = (HibernateBasicDaoImpl) DaoRegister.get(HibernateBasicDaoImpl.class);
	private int startPosition;
	private List<TomatoReview> theList;

	public JPAPaginationTableModel() {
		startPosition = 0;
		theList = getItems(startPosition, startPosition + 10);
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Data / Ora";
		case 1:
			return "Focus on";
		case 2:
			return "Cosa ho fatto realmente";
		case 3:
			return "Problemi incontrati";
		default:
			return "LAST NAME";
		}
	}

	@SuppressWarnings("unchecked")
	private List<TomatoReview> getItems(int startPosition2, int i) {
		logger.debug("numer of requests to the database " + counter++);

		List<TomatoReview> resultList = dao.namedQuery("TomatoReview.list");
		return resultList;
	}

	// cycle ....
	// 12:31:35.945 [AWT-EventQueue-0] DEBUG o.h.e.i.StatefulPersistenceContext
	// - Initializing non-lazy collections
	// java.lang.Exception: Stack trace
	// at java.lang.Thread.dumpStack(Unknown Source)
	// at
	// max.utility.tomato.gui.JPAPaginationTableModel.getRowCount(JPAPaginationTableModel.java:53)
	// at javax.swing.JTable.getRowCount(Unknown Source)
	// at javax.swing.JTable.getCellRect(Unknown Source)
	// at javax.swing.plaf.basic.BasicTableUI.paintCells(Unknown Source)
	// at javax.swing.plaf.basic.BasicTableUI.paint(Unknown Source)
	// at javax.swing.plaf.ComponentUI.update(Unknown Source)
	// at javax.swing.JComponent.paintComponent(Unknown Source)
	// at javax.swing.JComponent.paint(Unknown Source)
	// at javax.swing.JComponent.paintToOffscreen(Unknown Source)
	// at javax.swing.RepaintManager$PaintManager.paintDoubleBuffered(Unknown
	// Source)
	// at javax.swing.RepaintManager$PaintManager.paint(Unknown Source)
	// at javax.swing.RepaintManager.paint(Unknown Source)
	// at javax.swing.JComponent._paintImmediately(Unknown Source)
	// at javax.swing.JComponent.paintImmediately(Unknown Source)
	// at javax.swing.RepaintManager.paintDirtyRegions(Unknown Source)
	// at javax.swing.RepaintManager.paintDirtyRegions(Unknown Source)
	// at javax.swing.RepaintManager.prePaintDirtyRegions(Unknown Source)
	// at javax.swing.RepaintManager.access$700(Unknown Source)
	// at javax.swing.RepaintManager$ProcessingRunnable.run(Unknown Source)
	// at java.awt.event.InvocationEvent.dispatch(Unknown Source)
	// at java.awt.EventQueue.dispatchEventImpl(Unknown Source)
	// at java.awt.EventQueue.access$000(Unknown Source)
	// at java.awt.EventQueue$3.run(Unknown Source)
	// at java.awt.EventQueue$3.run(Unknown Source)
	// at java.security.AccessController.doPrivileged(Native Method)
	// at java.security.ProtectionDomain$1.doIntersectionPrivilege(Unknown
	// Source)
	// at java.awt.EventQueue.dispatchEvent(Unknown Source)
	// at java.awt.EventDispatchThread.pumpOneEventForFilters(Unknown Source)
	// at java.awt.EventDispatchThread.pumpEventsForFilter(Unknown Source)
	// at java.awt.EventDispatchThread.pumpEventsForHierarchy(Unknown Source)
	// at java.awt.EventDispatchThread.pumpEvents(Unknown Source)
	// at java.awt.EventDispatchThread.pumpEvents(Unknown Source)
	// at java.awt.EventDispatchThread.run(Unknown Source)
	@Override
	public int getRowCount() {
		return dao.count("Tomato.count").intValue();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		if ((rowIndex >= startPosition) && (rowIndex < (startPosition + 100))) {
		} else {
			theList = getItems(rowIndex, rowIndex + 100);
			startPosition = rowIndex;
		}
		TomatoReview tomatoReview = theList.get(rowIndex - startPosition);

		Object toReturn = null;
		switch (columnIndex) {
		case 0:
			toReturn = tomatoReview.getTomato().getStartTime().toString("dd/MM/yy - HH:mm");
			break;
		case 1:
			toReturn = tomatoReview.getTomato().getFocusOn();
			break;
		case 2:
			toReturn = tomatoReview.getReallyDone();
			break;
		case 3:
			toReturn = tomatoReview.getProblemsRaised();
			break;
		default:
			toReturn = tomatoReview.getId();

		}
		return toReturn;
	}

	// @Override
	// public boolean isCellEditable(int rowIndex, int mColIndex) {
	// return true;
	// }

}
