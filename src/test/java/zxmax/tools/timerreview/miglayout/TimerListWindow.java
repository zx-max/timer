package zxmax.tools.timerreview.miglayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import zxmax.tools.timerreview.domain.TomatoReview;
import net.miginfocom.swing.MigLayout;

public class TimerListWindow extends JFrame {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TimerListWindow frame = new TimerListWindow();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public TimerListWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        // setContentPane(getPnlTimersList() );

        JTabbedPane tabTimers = new JTabbedPane();
        tabTimers.addTab("nuovo timer", new JPanel());
        tabTimers.addTab("lista dei timer", getFakePnlTimersList());
        getContentPane().add(tabTimers);
    }

    private Container getFakePnlTimersList() {
        JPanel pnlTimerList = new JPanel(new MigLayout("flowy, fill"));

        pnlTimerList.add(getFakeItemDataPanel());
        pnlTimerList.add(getFakeItemDataPanel());
        pnlTimerList.add(getFakeItemDataPanel());

        JScrollPane pnlScrollList = new JScrollPane(pnlTimerList);
        return pnlScrollList;
    }

    private Container getCcontentPane() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new MigLayout("", "[47.00][]", "[][][][]"));

        JLabel lbltitle = new JLabel("_title_");
        contentPane.add(lbltitle, "cell 0 0");

        JLabel lblNewLabel = new JLabel("focus");
        contentPane.add(lblNewLabel, "cell 0 1");

        JLabel lbfocusContent = new JLabel("");
        contentPane.add(lbfocusContent, "cell 1 1");

        JLabel lblDone = new JLabel("fatto");
        contentPane.add(lblDone, "cell 0 2");

        JLabel lblDoneContent = new JLabel("");
        contentPane.add(lblDoneContent, "cell 1 2");

        JLabel lblNote = new JLabel("note");
        contentPane.add(lblNote, "cell 0 3");

        JLabel lblNoteContent = new JLabel("");
        contentPane.add(lblNoteContent, "cell 1 3");
        return contentPane;
    }

    private Container getTimerReviewPanel(TomatoReview tomatoReview) {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new MigLayout("", "[47.00][]", "[][][][]"));

        JLabel lbltitle = new JLabel(tomatoReview.getTomato().getTitle());
        contentPane.add(lbltitle, "cell 0 0");

        JLabel lblNewLabel = new JLabel("focus");
        contentPane.add(lblNewLabel, "cell 0 1");

        JLabel lblFocusContent = new JLabel(tomatoReview.getTomato()
                .getFocusOn());
        contentPane.add(lblFocusContent, "cell 1 1");

        JLabel lblDone = new JLabel("fatto");
        contentPane.add(lblDone, "cell 0 2");

        JLabel lblDoneContent = new JLabel(tomatoReview.getReallyDone());
        contentPane.add(lblDoneContent, "cell 1 2");

        JLabel lblNote = new JLabel("note");
        contentPane.add(lblNote, "cell 0 3");

        JLabel lblNoteContent = new JLabel(tomatoReview.getProblemsRaised());
        contentPane.add(lblNoteContent, "cell 1 3");

        return contentPane;
    }

    private JPanel getItemDataPanel1(TomatoReview tomatoReview) {
        JPanel pnlShowItemData = new JPanel();
        JLabel lblFocusOn = new JLabel("focus on:");
        JLabel lblDone = new JLabel("done:");
        JLabel lblProblemsRaised = new JLabel("issues:");
        JTextArea txtFocusOn = new JTextArea();
        JTextArea txtDone = new JTextArea();
        JTextArea txtProblemsRaised = new JTextArea();

        txtFocusOn.setText(tomatoReview.getTomato().getFocusOn());
        txtDone.setText(tomatoReview.getReallyDone());
        txtProblemsRaised.setText(tomatoReview.getProblemsRaised());

        LayoutManager layout = new MigLayout("", "grow,fill", "[][][][]15");
        pnlShowItemData.setLayout(layout);

        // "cell column row width height"
        pnlShowItemData.add(lblFocusOn, "cell 0 0");
        pnlShowItemData.add(lblDone, "cell 1 0");

        pnlShowItemData.add(txtFocusOn, "cell 0 1,growx ");
        pnlShowItemData.add(txtDone, "cell 1 1,growx ");

        pnlShowItemData.add(lblProblemsRaised, "cell 0 2");
        pnlShowItemData.add(txtProblemsRaised, "cell 0 3 2 1, growx");
        return pnlShowItemData;

    }

    private JPanel getFakeItemDataPanel() {
        JPanel pnlShowItemData = new JPanel();
        JLabel lblFocusOn = new JLabel("focus");
        JLabel lblDone = new JLabel("done");
        JLabel lblProblemsRaised = new JLabel("note");

        JTextArea txtFocusOn = new JTextArea();
        JTextArea txtDone = new JTextArea();
        JTextArea txtProblemsRaised = new JTextArea();
        txtFocusOn.setColumns(10);
        txtDone.setColumns(10);
        txtProblemsRaised.setColumns(10);
        // txtFocusOn.setText(tomatoReview.getTomato().getFocusOn());
        // txtDone.setText(tomatoReview.getReallyDone());
        // txtProblemsRaised.setText(tomatoReview.getProblemsRaised());

        LayoutManager layout = new MigLayout("", "[grow][]", "[][grow][][][]");
        pnlShowItemData.setLayout(layout);
        pnlShowItemData
                .setLayout(new MigLayout("", "[grow][grow]", "[][][][]"));

        JSeparator separator = new JSeparator();
        separator.setBackground(Color.GREEN);
        pnlShowItemData.add(separator, "cell 0 0, growx, span 2");

        // "cell column row width height"
        pnlShowItemData.add(lblFocusOn, "cell 0 1");
        pnlShowItemData.add(lblDone, "cell 1 1");
        pnlShowItemData.add(txtFocusOn, "cell 0 2");
        pnlShowItemData.add(txtDone, "cell 1 2");
        pnlShowItemData.add(lblProblemsRaised, "cell 0 3");
        pnlShowItemData.add(txtProblemsRaised, "cell 0 4 2 1");

        return pnlShowItemData;

    }
}
