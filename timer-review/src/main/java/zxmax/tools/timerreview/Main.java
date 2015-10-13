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
package zxmax.tools.timerreview;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zxmax.tools.timerreview.domain.ApplicationInfo;
import zxmax.tools.timerreview.domain.Tomato;
import zxmax.tools.timerreview.domain.TomatoReview;
import zxmax.tools.timerreview.gui.InfoWindow;
import zxmax.tools.timerreview.gui.StartTimerWindow;
import zxmax.tools.timerreview.gui.TrayIconMouseMotionListener;
import zxmax.tools.timerreview.serviceses.validators.ObjectValidator;
import zxmax.tools.timerreview.serviceses.validators.TomatoReviewValidator;
import zxmax.tools.timerreview.serviceses.validators.TomatoValidator;
import zxmax.tools.timerreview.serviceses.validators.ValidatorService;

public class Main {

    public static final Logger LOGGER = LoggerFactory
            .getLogger(zxmax.tools.timerreview.Main.class);

    public static void main(final String[] args) {
        try {

            JustOneLock ua = new JustOneLock(".timerreview");
            if (ua.isAppActive()) {
                String message = I18N.getLabel(
                        MyUncaughtExceptionHandler.class,
                        "another.instance.already.open");

                JOptionPane.showOptionDialog(null, message, I18N.getLabel(
                        MyUncaughtExceptionHandler.class, "error.box.title"),
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, new Object[] {}, null);

                System.exit(0);
            }
            new Main();
        } catch (Exception e) {

            if (e instanceof IllegalStateException) {
                JOptionPane
                        .showMessageDialog(null,
                                "controlla i file di log e contatta, se ti va, lo sviluppatore, ciao.");
            }
            LOGGER.error("", e);
        }
    }

    public Main() throws Exception {

        MyUncaughtExceptionHandler myUncaughtExceptionHandler = new MyUncaughtExceptionHandler(
                LOGGER);

        Thread.setDefaultUncaughtExceptionHandler(myUncaughtExceptionHandler);
        final TrayIcon trayIcon = getTrayIcon();
        final TrayIconMouseMotionListener listener = new TrayIconMouseMotionListener(
                trayIcon);
        trayIcon.addMouseMotionListener(listener);

        Register.put(TrayIcon.class, trayIcon);
        Register.put(TrayIconMouseMotionListener.class, listener);
        String implementationVersion = zxmax.tools.timerreview.Main.class
                .getPackage().getImplementationVersion();
        Register.put(ApplicationInfo.class, new ApplicationInfo(
                implementationVersion));

        // ObjectValidator[] validators = new ObjectValidator[] {
        // new TomatoValidator(), new TomatoReviewValidator() };

        Map<Class, ObjectValidator> validationCommands = new HashedMap();
        validationCommands.put(Tomato.class, new TomatoValidator());
        validationCommands.put(TomatoReview.class, new TomatoReviewValidator());

        Register.put(ValidatorService.class, new ValidatorService(
                validationCommands));

        for (UIManager.LookAndFeelInfo info : UIManager
                .getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }

        final StartTimerWindow timer = new StartTimerWindow();
        timer.setVisible(true);
        timer.createBufferStrategy(1);

        // HibernateBasicDaoImpl basicDao = new HibernateBasicDaoImpl();
        // Register.put(HibernateBasicDaoImpl.class, basicDao);

    }

    private Image createImage(final String path, final String description) {
        Image image = null;
        final URL imageURL = PropertyLoader.loadFromClassPathAsURL(path);

        if (imageURL == null) {
            LOGGER.error(String.format("%s: [%s].",
                    I18N.getLabel(this.getClass(), "resource.not.found"), path));
        } else {
            image = new ImageIcon(imageURL, description).getImage();
        }

        return image;
    }

    private TrayIcon getTrayIcon() throws AWTException {
        final TrayIcon trayIcon = new TrayIcon(createImage("images/bulb.gif",
                I18N.getLabel(getClass(), "tray.icon.description")));

        final SystemTray tray = SystemTray.getSystemTray();
        tray.add(trayIcon);

        final PopupMenu popup = new PopupMenu();
        trayIcon.setPopupMenu(popup);

        /*
         * Serve per aprire il popup sulla tray icon con il tasto sx del mouse.
         * Il problema è chiuderla/nasconderla ...
         */
        // trayIcon.addMouseListener(new TrayIconMouseAdapter(popup));

        final MenuItem exitItem = new MenuItem(
                I18N.getLabel(getClass(), "exit"));
        final MenuItem newTimerItem = new MenuItem(I18N.getLabel(getClass(),
                "new.timer"));
        newTimerItem.setEnabled(false);
        final MenuItem infoItem = new MenuItem(
                I18N.getLabel(getClass(), "info"));
        popup.add(exitItem);
        popup.add(newTimerItem);
        popup.add(infoItem);

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });

        newTimerItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StartTimerWindow timer = new StartTimerWindow();
                timer.setVisible(true);
            }
        });

        infoItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                InfoWindow infoWindow = new InfoWindow();
                infoWindow.setVisible(true);
                infoWindow.createBufferStrategy(1);
            }
        });
        return trayIcon;
    }
}
