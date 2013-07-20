package max.utility.tomato.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import max.utility.tomato.Register;
import max.utility.tomato.dao.HibernateBasicDaoImpl;
import max.utility.tomato.domain.Tomato;
import max.utility.tomato.domain.TomatoReview;
import net.miginfocom.swing.MigLayout;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndTimerWindow extends JFrame {

    private static final String WINDOW_TITLE = "Pomodoro finito ...";

    private static final String LABEL_TITLE_FONT = "Comic Sans MS"; // Comic
                                                                    // Sans MS
                                                                    // NOI18N
    private static final String TEXT_AREA_FOCUS_ON_FONT = "Andalus"; // Andalus
                                                                     // NOI18N

    private static final String LABEL_FOCUS_ON = "volevo fare:";

    private static final String DONE_LABEL = "fatto:";

    private static final String DONE_TEXT_AREA_TOOL_TIP = "breve revisione di quanto fatto ..";

    private static final String DONE_TEXT_AREA_SETUP_TEXT = "salva le ultime modifiche.\ncommit -m \"fine timer\"\n";

    private static final String REVIEW_LABEL = "rivedi quanto fatto";

    private static final String NOTE_LABEL = "note:";

    private static final String TIMER_LOGGER_NOTE = "Note:";
    private static final String TIMER_LOGGER_FATTO = "Fatto:";

    private static final String BUTTON_OK_NEW_TEXT = "ok & New";
    private static final String BUTTON_OK_TEXT = "ok";

    public static final Logger logger = LoggerFactory
            .getLogger(EndTimerWindow.class);
    public static final Logger timerLogger = LoggerFactory
            .getLogger("TimerLogger");

    /**
	 * 
	 */
    private static final long serialVersionUID = 8849013985135065049L;

    private final HibernateBasicDaoImpl basicDao;

    private JTextArea taNote;
    private JTextArea taDone;
    private Tomato tomato;

    public EndTimerWindow(Tomato tomato) {
        setTitle(WINDOW_TITLE);
        this.tomato = tomato;
        basicDao = (HibernateBasicDaoImpl) Register
                .get(HibernateBasicDaoImpl.class);
        initComponents();
        setVisible(true);
        logger.debug(tomato.toString());
    }

    private void initComponents() {

        getContentPane()
                .setLayout(
                        new MigLayout("", "[grow][]",
                                "[][][grow][][grow][][grow][][]"));

        JLabel lblTitle = new JLabel(REVIEW_LABEL);
        lblTitle.setFont(new Font(LABEL_TITLE_FONT, 0, 25));
        lblTitle.setForeground(new Color(255, 153, 0));
        getContentPane().add(lblTitle, "cell 0 0");

        JLabel lblFocusOn = new JLabel(LABEL_FOCUS_ON);
        lblFocusOn.setBackground(new Color(0, 0, 0));
        lblFocusOn.setForeground(new Color(10, 2, 100));
        getContentPane().add(lblFocusOn, "cell 0 1");

        JTextArea taFocusOn = new JTextArea();
        taFocusOn.setEditable(false);
        taFocusOn.setBackground(new Color(0, 0, 0));
        taFocusOn.setColumns(20);
        taFocusOn.setFont(new Font(TEXT_AREA_FOCUS_ON_FONT, 0, 13));
        taFocusOn.setForeground(new Color(0, 255, 255));
        taFocusOn.setRows(10);
        taFocusOn.setTabSize(2);
        // ta_focusOn.setFocusable(false);
        taFocusOn.setText(tomato.getFocusOn());
        JScrollPane scFocusOn = new JScrollPane(taFocusOn);
        getContentPane().add(scFocusOn, "cell 0 2,grow");

        JLabel lblDone = new JLabel(DONE_LABEL);
        getContentPane().add(lblDone, "cell 0 3");

        taDone = new JTextArea();
        JScrollPane spDone = new JScrollPane(taDone);
        taDone.setRows(10);
        taDone.setTabSize(2);
        taDone.setText(DONE_TEXT_AREA_SETUP_TEXT);
        taDone.setToolTipText(DONE_TEXT_AREA_TOOL_TIP);
        getContentPane().add(spDone, "cell 0 4,grow");

        JLabel lblNote = new JLabel(NOTE_LABEL);
        getContentPane().add(lblNote, "cell 0 5");

        taNote = new JTextArea();
        taNote.setRows(10);
        taNote.setTabSize(2);
        JScrollPane spNote = new JScrollPane(taNote);
        getContentPane().add(spNote, "cell 0 6,grow");

        JButton btnOk = new JButton(BUTTON_OK_TEXT);
        getContentPane().add(btnOk, "flowx,cell 0 8");

        JButton btnOkNew = new JButton(BUTTON_OK_NEW_TEXT);
        getContentPane().add(btnOkNew, "cell 0 8");

        btnOk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                saveTomatoReview();
                closeWindow();
            }
        });

        btnOkNew.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                saveTomatoReview();
                closeWindow();
                StartTimerWindow startTimerWindow = new StartTimerWindow(tomato
                        .getTitle());
                startTimerWindow.setVisible(true);
            }

        });

        btnOk.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                logger.debug(evt.getKeyCode() + ", " + evt.getKeyChar());
                if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
                    saveTomatoReview();
                    closeWindow();
                }
            }
        });

        btnOkNew.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                logger.debug(evt.getKeyCode() + ", " + evt.getKeyChar());
                if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
                    saveTomatoReview();
                    closeWindow();
                    StartTimerWindow startTimerWindow = new StartTimerWindow(
                            tomato.getTitle());
                    startTimerWindow.setVisible(true);
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                logger.debug("start closing windows..");
                // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // http://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe
                // System.exit(0);
            }

        });

        pack();
    }

    private void saveTomatoReview() {
        TomatoReview review = new TomatoReview(tomato, taDone.getText(),
                taNote.getText());

        basicDao.save(review);

        StringBuffer sb = new StringBuffer();
        sb.append(tomato.getStartTime().toString("HH:mm"));
        sb.append(" - ");
        sb.append((new LocalDateTime()).toString("HH:mm"));

        timerLogger.info(sb.toString());

        timerLogger.info(TIMER_LOGGER_FATTO + "\n" + review.getReallyDone());
        timerLogger.info(TIMER_LOGGER_NOTE + "\n" + review.getProblemsRaised());
        // closeWindow();
    }

    private void closeWindow() {
        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);

        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);

        TrayIconMouseMotionListener listener = (TrayIconMouseMotionListener) Register
                .get(TrayIconMouseMotionListener.class);

        listener.setTomato(null);
    }
}
