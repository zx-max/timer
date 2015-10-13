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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import zxmax.tools.timerreview.I18N;
import zxmax.tools.timerreview.Register;
import zxmax.tools.timerreview.domain.ApplicationInfo;

public class InfoWindow extends JFrame {

    private JPanel contentPane;
    private JTextField txtWebAddress;
    private JTextField txtAuthorName;
    private JTextField txtAuthorEmail;
    private JLabel lblWebAddress;
    private JLabel lblSoftpedia;
    private JTextField txtSoftPediaLink;
    private JLabel lblSourceforge;
    private JTextField txtSourceForgeLink;

    private JLabel lblTimerLog;
    private JTextField txtTimerLog;
    private JLabel lblApplicationLog;
    private JTextField txtApplicationLog;
    private JLabel lblApplicationVersion;
    private JTextField txtApplicationVersion;

    public InfoWindow() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setBounds(100, 100, 720, 350);
        setResizable(false);
        setTitle(I18N.getLabel(getClass(), "window.title"));

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));

        addAuthorNameToContentPane("7");

        addEmailToContentPane("6");

        addWebAddressToContenPane("5");

        addSoftPediaToContenPane("4");

        addSourceForgeToContentPane("3");

        addReportFilePathToContentPane("2");

        addApplicationLogFilePathToContentPane("1");

        addApplicationVersionToContentPane("0");

    }

    private void addApplicationVersionToContentPane(String row) {
        lblApplicationVersion = new JLabel("version");
        txtApplicationVersion = new JTextField();
        txtApplicationVersion.setEditable(false);
        ApplicationInfo ai = (ApplicationInfo) Register
                .get(ApplicationInfo.class);
        txtApplicationVersion.setText(ai.getImplementationVersion());

        contentPane.add(lblApplicationVersion, "cell 0 " + row
                + ",alignx left,aligny baseline");
        contentPane.add(txtApplicationVersion, "cell 1 " + row
                + ",growx,aligny baseline");
    }

    private void addApplicationLogFilePathToContentPane(String row) {
        lblApplicationLog = new JLabel("log");
        txtApplicationLog = new JTextField();
        txtApplicationLog.setEditable(false);
        txtApplicationLog.setText(System.getProperty("user.home")
                + System.getProperty("file.separator") + ".timer-review"
                + System.getProperty("file.separator") + "logs"
                + System.getProperty("file.separator") + "timer-review.log");

        contentPane.add(lblApplicationLog, "cell 0 " + row
                + ",alignx left,aligny baseline");
        contentPane.add(txtApplicationLog, "cell 1 " + row
                + ",growx,aligny baseline");

    }

    private void addReportFilePathToContentPane(String row) {
        lblTimerLog = new JLabel("report");
        txtTimerLog = new JTextField();
        txtTimerLog.setEditable(false);
        txtTimerLog.setText(System.getProperty("user.home")
                + System.getProperty("file.separator") + ".timer-review"
                + System.getProperty("file.separator") + "logs"
                + System.getProperty("file.separator") + "report.txt");

        contentPane.add(lblTimerLog, "cell 0 " + row
                + ",alignx left,aligny baseline");
        contentPane
                .add(txtTimerLog, "cell 1 " + row + ",growx,aligny baseline");

    }

    private void addSourceForgeToContentPane(String row) {
        lblSourceforge = new JLabel("sourceforge");
        contentPane.add(lblSourceforge, "cell 0 " + row
                + ",alignx left,aligny baseline");

        txtSourceForgeLink = new JTextField(row);
        txtSourceForgeLink.setEditable(false);
        txtSourceForgeLink
                .setText("http://sourceforge.net/projects/timerreview/");
        contentPane.add(txtSourceForgeLink, "cell 1 " + row
                + ",growx,aligny baseline");
        txtSourceForgeLink.setColumns(10);
    }

    private void addSoftPediaToContenPane(String row) {
        lblSoftpedia = new JLabel("softpedia:");
        contentPane.add(lblSoftpedia, "cell 0 " + row
                + ",alignx left,aligny baseline");

        txtSoftPediaLink = new JTextField();
        txtSoftPediaLink.setEditable(false);
        txtSoftPediaLink
                .setText("http://www.softpedia.com/get/Desktop-Enhancements/Clocks-Time-Management/Timer-review.shtml");
        contentPane.add(txtSoftPediaLink, "cell 1 " + row
                + ",growx,aligny baseline");
        txtSoftPediaLink.setColumns(10);
    }

    private void addWebAddressToContenPane(String row) {
        lblWebAddress = new JLabel(I18N.getLabel(getClass(), "web"));
        contentPane.add(lblWebAddress, "cell 0 " + row
                + ",alignx left,aligny baseline");

        txtWebAddress = new JTextField();
        txtWebAddress.setToolTipText("http://www.timerreview.net/");
        txtWebAddress.setEditable(false);
        txtWebAddress.setText("http://www.timerreview.net/");
        contentPane.add(txtWebAddress, "cell 1 " + row
                + ",growx,aligny baseline");
        txtWebAddress.setColumns(10);
    }

    private void addEmailToContentPane(String row) {
        JLabel lblEmail = new JLabel(I18N.getLabel(getClass(), "mail"));
        contentPane.add(lblEmail, "cell 0 " + row
                + ",alignx left,aligny baseline");

        txtAuthorEmail = new JTextField();
        txtAuthorEmail.setEditable(false);
        txtAuthorEmail.setText("massimiliano.parentini@gmail.com");
        contentPane.add(txtAuthorEmail, "cell 1 " + row
                + ",growx,aligny baseline");
        txtAuthorEmail.setColumns(10);
    }

    private void addAuthorNameToContentPane(String row) {
        JLabel lblAuthorName = new JLabel(I18N.getLabel(getClass(), "author"));
        contentPane.add(lblAuthorName, "cell 0 " + row
                + ",alignx left,aligny baseline");

        txtAuthorName = new JTextField();
        txtAuthorName.setEditable(false);
        txtAuthorName.setText("Massimiliano Parentini");
        contentPane.add(txtAuthorName, "cell 1 " + row
                + ",growx,aligny baseline");
        txtAuthorName.setColumns(10);
    }
}
