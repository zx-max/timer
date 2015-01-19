/**
 * Timer Review  -  a personal time management tool
 *
 *
 * Copyright (C)  2012 - 2014 Parentini Massimiliano
 * Project home page: http://www.timer-review.net
 *
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */
package zxmax.tools.timerreview.gui;


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

import net.miginfocom.swing.MigLayout;
import zxmax.tools.timerreview.I18N;
import zxmax.tools.timerreview.Register;
import zxmax.tools.timerreview.dao.HibernateBasicDaoImpl;
import zxmax.tools.timerreview.domain.TomatoReview;

public class TabbedPaneChangeListener implements ChangeListener {
    private static final String TAB_TIMER_LIST_LABEL = "tab.timer.list.label";

    private static final String TAB_TIMER_LIST_NOTE_LABEL = "tab.timer.list.note.label";

    private static final String TAB_TIMER_LIST_DONE_LABEL = "tab.timer.list.done.label";

    private static final String TAB_TIMER_LIST_FOCUS_LABEL = "tab.timer.list.focus.on.label";

    private boolean listTimerLoaded = false;

    @Override
    public void stateChanged(ChangeEvent changeEvent) {

        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();

        int index = sourceTabbedPane.getSelectedIndex();

        if (index == 1 && !listTimerLoaded) {

            listTimerLoaded = true;
            sourceTabbedPane.remove(index);
            sourceTabbedPane.addTab(
                    I18N.getKey(StartTimerWindow.class, TAB_TIMER_LIST_LABEL),
                    getPnlTimersList());
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

        HibernateBasicDaoImpl basicDao = getBasicDao();
        return basicDao.namedQuery("TomatoReview.list");
    }

    private HibernateBasicDaoImpl getBasicDao() {
        HibernateBasicDaoImpl basicDao = (HibernateBasicDaoImpl) Register
                .get(HibernateBasicDaoImpl.class);

        if (basicDao != null) {
            return basicDao;
        } else {

            basicDao = new HibernateBasicDaoImpl();
            Register.put(HibernateBasicDaoImpl.class, basicDao);

        }
        return basicDao;
    }

    public JPanel getItemDataPanel(TomatoReview tomatoReview) {
        JPanel pnlShowItemData = new JPanel();
        JLabel lblFocusOn = new JLabel(I18N.getKey(StartTimerWindow.class,
                TAB_TIMER_LIST_FOCUS_LABEL));
        JLabel lblDone = new JLabel(I18N.getKey(StartTimerWindow.class,
                TAB_TIMER_LIST_DONE_LABEL));
        JLabel lblProblemsRaised = new JLabel(I18N.getKey(
                StartTimerWindow.class, TAB_TIMER_LIST_NOTE_LABEL));

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
