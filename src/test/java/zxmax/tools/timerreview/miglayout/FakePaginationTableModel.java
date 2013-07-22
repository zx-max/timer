package zxmax.tools.timerreview.miglayout;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import zxmax.tools.timerreview.domain.Tomato;
import zxmax.tools.timerreview.domain.TomatoReview;

public class FakePaginationTableModel extends AbstractTableModel {
    private int startPosition;
    private List<TomatoReview> theList;

    public FakePaginationTableModel() {
        theList = new ArrayList<TomatoReview>();
        for (int j = 0; j < 20; j++) {
            Tomato tomato = new Tomato("eeeeeeeeee -->   " + j);
            TomatoReview tr = new TomatoReview(tomato, "afasdfasdf -->   " + j,
                    "rrrrrrrrrrr -->   " + j);
            theList.add(tr);
        }
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

    private List<TomatoReview> getItems(int rowIndex, int i) {

        return theList;
    }

    @Override
    public int getRowCount() {
        return 20;
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
            toReturn = tomatoReview.getTomato().getStartTime()
                    .toString("dd/MM/yy - HH:mm");
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

}
