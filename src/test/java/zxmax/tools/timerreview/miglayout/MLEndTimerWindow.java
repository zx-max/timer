package zxmax.tools.timerreview.miglayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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

import net.miginfocom.swing.MigLayout;

public class MLEndTimerWindow extends JFrame {
    public MLEndTimerWindow() {
        initComponent();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MLEndTimerWindow frame = new MLEndTimerWindow();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initComponent() {
        getContentPane()
                .setLayout(
                        new MigLayout("", "[grow][]",
                                "[][][grow][][grow][][grow][][]"));

        JLabel lblTitle = new JLabel();
        lblTitle.setFont(new Font("Comic Sans MS", 0, 25)); // NOI18N
        lblTitle.setForeground(new Color(255, 153, 0));
        lblTitle.setText("rivedi quanto fatto");
        getContentPane().add(lblTitle, "cell 0 0");

        JLabel lblFocusOn = new JLabel("focusOn:");
        lblFocusOn.setBackground(new Color(0, 0, 0));
        lblFocusOn.setForeground(new Color(10, 2, 100));
        lblFocusOn.setText(" volevo fare:");
        getContentPane().add(lblFocusOn, "cell 0 1");

        JTextArea taFocusOn = new JTextArea();
        JScrollPane scFocusOn = new JScrollPane(taFocusOn);
        getContentPane().add(scFocusOn, "cell 0 2,grow");

        JLabel lblDone = new JLabel("fatto:");
        getContentPane().add(lblDone, "cell 0 3");

        JTextArea taDone = new JTextArea();
        JScrollPane spDone = new JScrollPane(taDone);
        taDone.setRows(10);
        taDone.setTabSize(2);
        taDone.setText("salva le ultime modifiche.\ncommit -m \"fine timer\"\n");
        taDone.setToolTipText("breve revisione di quanto fatto ..");
        getContentPane().add(spDone, "cell 0 4,grow");

        JLabel lblNote = new JLabel("note:");
        getContentPane().add(lblNote, "cell 0 5");

        JTextArea taNote = new JTextArea();
        JScrollPane spNote = new JScrollPane(taNote);
        getContentPane().add(spNote, "cell 0 6,grow");

        JButton btnOk = new JButton("ok");
        getContentPane().add(btnOk, "flowx,cell 0 8");

        JButton btnOkNew = new JButton("ok & New");
        getContentPane().add(btnOkNew, "cell 0 8");

        btnOk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                // btnOkCompletedMouseClicked(evt);
            }
        });
        btnOk.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                // btnOkCompletedKeyPressed(evt);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                // logger.debug("start closing windows..");
                // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // http://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe
                // System.exit(0);
            }

        });
    }

}
