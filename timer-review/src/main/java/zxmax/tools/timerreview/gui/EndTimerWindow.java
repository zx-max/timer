/**
 * This file is part of timer-review.
 *
 * timer-review is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * timer-review is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with timer-review.  If not, see <http://www.gnu.org/licenses/>.
 */

package zxmax.tools.timerreview.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zxmax.tools.timerreview.I18N;
import zxmax.tools.timerreview.Register;
import zxmax.tools.timerreview.dao.HibernateBasicDaoImpl;
import zxmax.tools.timerreview.domain.ApplicationInfo;
import zxmax.tools.timerreview.domain.Tomato;
import zxmax.tools.timerreview.domain.TomatoReview;
import zxmax.tools.timerreview.serviceses.validators.ValidatorService;

public class EndTimerWindow extends JFrame {

    private static final String WINDOW_TITLE = "window.title";

    private static final String TITLE_LABEL_FONT = "label.title.font";

    private static final String FOCUS_ON_TEXT_AREA_FONT = "focus.on.textarea.font";

    private static final String FOCUS_ON_LABEL = "focus.on.label";

    private static final String DONE_LABEL = "done.label";

    private static final String DONE_TEXT_AREA_TOOL_TIP = "done.textarea.tooltip";

    private static final String DONE_TEXT_AREA_TEXT = "done.textarea.text";

    private static final String REVIEW_LABEL = "review.label";

    private static final String NOTE_LABEL = "note.label";

    private static final String TIMER_LOGGER_NOTE = "timer.logger.note";
    private static final String TIMER_LOGGER_FATTO = "timer.logger.fatto";

    private static final String BUTTON_OK_NEW_TEXT = "button.ok.new.text";
    private static final String BUTTON_OK_TEXT = "button.ok.text";

    public static final Logger logger = LoggerFactory
            .getLogger(EndTimerWindow.class);
    public static final Logger reportLogger = LoggerFactory
            .getLogger(ApplicationInfo.REPORT_LOGGER);

    private static final long serialVersionUID = 8849013985135065049L;

    private JTextArea taNote;
    private JTextArea taDone;

    private HibernateBasicDaoImpl basicDao;
    private Tomato tomato;
    private ValidatorService validatorService;

    public EndTimerWindow(Tomato tomato) {
        setTitle(I18N.getLabel(this.getClass(), WINDOW_TITLE));
        this.tomato = tomato;
        initComponents();
        setVisible(true);
        logger.debug(tomato.toString());
        basicDao = (HibernateBasicDaoImpl) Register
                .get(HibernateBasicDaoImpl.class);

        validatorService = (ValidatorService) Register
                .get(ValidatorService.class);
    }

    private void initComponents() {

        getContentPane()
                .setLayout(
                        new MigLayout("", "[grow][]",
                                "[][][grow][][grow][][grow][][]"));

        JLabel lblTitle = new JLabel(I18N.getLabel(this.getClass(),
                REVIEW_LABEL));
        lblTitle.setFont(new Font(I18N.getLabel(this.getClass(),
                TITLE_LABEL_FONT), 0, 25));
        lblTitle.setForeground(new Color(255, 153, 0));
        getContentPane().add(lblTitle, "cell 0 0");

        JLabel lblFocusOn = new JLabel(I18N.getLabel(this.getClass(),
                FOCUS_ON_LABEL));
        lblFocusOn.setBackground(new Color(0, 0, 0));
        lblFocusOn.setForeground(new Color(10, 2, 100));
        getContentPane().add(lblFocusOn, "cell 0 1");

        JTextArea taFocusOn = new JTextArea();
        taFocusOn.setEditable(false);
        taFocusOn.setBackground(new Color(0, 0, 0));
        taFocusOn.setColumns(20);
        taFocusOn.setFont(new Font(I18N.getLabel(this.getClass(),
                FOCUS_ON_TEXT_AREA_FONT), 0, 13));
        taFocusOn.setForeground(new Color(0, 255, 255));
        taFocusOn.setRows(10);
        taFocusOn.setTabSize(2);
        // ta_focusOn.setFocusable(false);
        taFocusOn.setText(tomato.getFocusOn());
        JScrollPane scFocusOn = new JScrollPane(taFocusOn);
        getContentPane().add(scFocusOn, "cell 0 2,grow");

        JLabel lblDone = new JLabel(I18N.getLabel(this.getClass(), DONE_LABEL));
        getContentPane().add(lblDone, "cell 0 3");

        taDone = new JTextArea();
        JScrollPane spDone = new JScrollPane(taDone);
        taDone.setRows(10);
        taDone.setTabSize(2);
        taDone.setText(I18N.getLabel(this.getClass(), DONE_TEXT_AREA_TEXT));
        taDone.setToolTipText(I18N.getLabel(this.getClass(),
                DONE_TEXT_AREA_TOOL_TIP));
        getContentPane().add(spDone, "cell 0 4,grow");

        JLabel lblNote = new JLabel(I18N.getLabel(this.getClass(), NOTE_LABEL));
        getContentPane().add(lblNote, "cell 0 5");

        taNote = new JTextArea();
        taNote.setRows(10);
        taNote.setTabSize(2);
        JScrollPane spNote = new JScrollPane(taNote);
        getContentPane().add(spNote, "cell 0 6,grow");

        JButton btnOk = new JButton(I18N.getLabel(this.getClass(),
                BUTTON_OK_TEXT));
        getContentPane().add(btnOk, "flowx,cell 0 8");

        JButton btnOkNew = new JButton(I18N.getLabel(this.getClass(),
                BUTTON_OK_NEW_TEXT));
        getContentPane().add(btnOkNew, "cell 0 8");

        btnOk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                saveTomatoReview();
                closeWindow();
                enableNewTimerMenuItem();
            }
        });

        btnOk.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                logger.debug(evt.getKeyCode() + ", " + evt.getKeyChar());
                if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
                    saveTomatoReview();
                    closeWindow();
                    enableNewTimerMenuItem();
                }
            }

        });

        btnOkNew.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                TomatoReview review = saveTomatoReview();
                closeWindow();
                final String note = review.getProblemsRaised();
                StartTimerWindow startTimerWindow = new StartTimerWindow(tomato
                        .getTitle(), note);
                startTimerWindow.setVisible(true);
            }

        });

        btnOkNew.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                logger.debug(evt.getKeyCode() + ", " + evt.getKeyChar());
                if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
                    TomatoReview review = saveTomatoReview();
                    closeWindow();

                    StartTimerWindow startTimerWindow;
                    final String note = review.getProblemsRaised();
                    startTimerWindow = new StartTimerWindow(tomato.getTitle(),
                            note);
                    startTimerWindow.setVisible(true);
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                logger.debug("start closing windows..");
                saveTomatoReview();
                enableNewTimerMenuItem();
                // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // http://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe
                // System.exit(0);
            }

        });

        pack();
    }

    private TomatoReview saveTomatoReview() {

        // create and save a tomato review only if no one is linked to the
        // current tomato.
        List list = basicDao.namedQuery("ExistTomatoReviewForTomato",
                "tomatoId", tomato.getId());

        TomatoReview review = null;
        if (list.size() == 0) {
            review = new TomatoReview(tomato, taDone.getText(),
                    taNote.getText());

            basicDao.save(review);
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            sb.append(" " + (new LocalDateTime()).toString("dd/MM/yyyy E")
                    + " ");
            sb.append(tomato.getStartTime().toString("HH:mm"));
            sb.append(" - ");
            sb.append(new LocalDateTime().toString("HH:mm"));
            sb.append(" ]");
            sb.append(System.getProperty("line.separator"));

            reportLogger.info(sb.toString());

            reportLogger
                    .info(I18N.getLabel(this.getClass(), TIMER_LOGGER_FATTO)
                            + System.getProperty("line.separator")
                            + review.getReallyDone());
            reportLogger.info(I18N.getLabel(this.getClass(), TIMER_LOGGER_NOTE)
                    + System.getProperty("line.separator")
                    + review.getProblemsRaised());

            return review;

        } else {
            // closeWindow();
            return basicDao.load(TomatoReview.class,
                    ((TomatoReview) list.get(0)).getId());
        }
    }

    private void closeWindow() {
        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);

        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);

    }

    private void enableNewTimerMenuItem() {
        TrayIcon trayIcon = (TrayIcon) Register.get(TrayIcon.class);
        MenuItem item = trayIcon.getPopupMenu().getItem(1);
        item.setEnabled(true);
    }
}
