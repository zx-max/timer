package max.utility.tomato.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import max.utility.tomato.Register;
import max.utility.tomato.dao.HibernateBasicDaoImpl;
import max.utility.tomato.domain.TomatoReview;
import net.miginfocom.swing.MigLayout;

public class TabbedPaneChangeListener implements ChangeListener {
    private static final String TAB_TIMER_LIST_LABEL = "lista dei timer";

    private static final String TAB_TIMER_LIST_NOTE_LABEL = "note";

    private static final String TAB_TIMER_LIST_DONE_LABEL = "ho fatto";

    private static final String TAB_TIMER_LIST_FOCUS_LABEL = "focus";

    private boolean listTimerLoaded = false;

    public void stateChanged(ChangeEvent changeEvent) {

        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();

        int index = sourceTabbedPane.getSelectedIndex();

        if (index == 1 && !listTimerLoaded) {

            listTimerLoaded = true;
            sourceTabbedPane.remove(index);
            sourceTabbedPane.addTab(TAB_TIMER_LIST_LABEL, getPnlTimersList());
            sourceTabbedPane.repaint();
            sourceTabbedPane.setSelectedIndex(index);

        }

    }

    private Component getPnlTimersList() {

        JPanel pnlTimerList = new JPanel(new MigLayout("flowy, fill"));

        for (TomatoReview tomatoReview : getList()) {
            pnlTimerList.add(getItemDataPanel(tomatoReview));
        }

        JScrollPane pnlScrollList = new JScrollPane(pnlTimerList);
        return pnlScrollList;
    }

    private List<TomatoReview> getList() {

        HibernateBasicDaoImpl basicDao = (HibernateBasicDaoImpl) Register
                .get(HibernateBasicDaoImpl.class);
        return basicDao.namedQuery("TomatoReview.list");
    }

    public JPanel getItemDataPanel(TomatoReview tomatoReview) {
        JPanel pnlShowItemData = new JPanel();
        JLabel lblFocusOn = new JLabel(TAB_TIMER_LIST_FOCUS_LABEL);
        JLabel lblDone = new JLabel(TAB_TIMER_LIST_DONE_LABEL);
        JLabel lblProblemsRaised = new JLabel(TAB_TIMER_LIST_NOTE_LABEL);

        JTextArea txtFocusOn = new JTextArea();
        JTextArea txtDone = new JTextArea();
        JTextArea txtProblemsRaised = new JTextArea();
        txtFocusOn.setColumns(10);
        txtDone.setColumns(10);
        txtProblemsRaised.setColumns(10);

        txtFocusOn.setText(tomatoReview.getTomato().getFocusOn());
        txtDone.setText(tomatoReview.getReallyDone());
        txtProblemsRaised.setText(tomatoReview.getProblemsRaised());

        LayoutManager layout = new MigLayout("", "[grow][]", "[][grow][][][]");
        pnlShowItemData.setLayout(layout);
        pnlShowItemData.setLayout(new MigLayout("fill", "[][]"));

        JSeparator separator = new JSeparator();
        separator.setBackground(Color.GREEN);
        pnlShowItemData.add(separator, "cell 0 0, growx, span 2");

        // "cell column row width height"
        pnlShowItemData.add(lblFocusOn, "cell 0 1");
        pnlShowItemData.add(txtFocusOn, "cell 0 2, growx");

        pnlShowItemData.add(lblDone, "cell 1 1");
        pnlShowItemData.add(txtDone, "cell 1 2, growx");

        pnlShowItemData.add(lblProblemsRaised, "cell 0 3");
        pnlShowItemData.add(txtProblemsRaised, "cell 0 4, growx, span 2");

        return pnlShowItemData;

    }

}
