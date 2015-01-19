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

import javax.swing.ImageIcon;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zxmax.tools.timerreview.dao.HibernateBasicDaoImpl;
import zxmax.tools.timerreview.domain.ApplicationInfo;
import zxmax.tools.timerreview.gui.InfoWindow;
import zxmax.tools.timerreview.gui.StartTimerWindow;
import zxmax.tools.timerreview.gui.TrayIconMouseMotionListener;

public class Main {

    public static final Logger LOGGER = LoggerFactory
            .getLogger(zxmax.tools.timerreview.Main.class);

    public static void main(final String[] args) {
        try {
            new Main();
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    public Main() throws Exception {

        final TrayIcon trayIcon = getTrayIcon();
        final TrayIconMouseMotionListener listener = new TrayIconMouseMotionListener(
                trayIcon);
        trayIcon.addMouseMotionListener(listener);

        Register.put(TrayIcon.class, trayIcon);
        Register.put(TrayIconMouseMotionListener.class, listener);
        String implementationVersion = zxmax.tools.timerreview.Main.class.getPackage().getImplementationVersion();
        Register.put(ApplicationInfo.class, new ApplicationInfo(implementationVersion));

        for (UIManager.LookAndFeelInfo info : UIManager
                .getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }

        final StartTimerWindow timer = new StartTimerWindow();
        timer.setVisible(true);

        HibernateBasicDaoImpl basicDao = new HibernateBasicDaoImpl();
        Register.put(HibernateBasicDaoImpl.class, basicDao);

    }

    private Image createImage(final String path, final String description) {
        Image image = null;
        final URL imageURL = PropertyLoader.loadFromClassPathAsURL(path);

        if (imageURL == null) {
            LOGGER.error(String.format("%s: [%s].",
                    I18N.getKey(this.getClass(), "resource.not.found"), path));
        } else {
            image = new ImageIcon(imageURL, description).getImage();
        }

        return image;
    }

    private TrayIcon getTrayIcon() throws AWTException {
        final TrayIcon trayIcon = new TrayIcon(createImage("images/bulb.gif",
                I18N.getKey(getClass(), "tray.icon.description")));

        final MenuItem exitItem = new MenuItem(I18N.getKey(getClass(), "exit"));
        final MenuItem newTimerItem = new MenuItem(I18N.getKey(getClass(),
                "new.timer"));
        final MenuItem infoItem = new MenuItem(I18N.getKey(getClass(), "info"));
        final PopupMenu popup = new PopupMenu();
        popup.add(exitItem);
        popup.add(newTimerItem);
        popup.add(infoItem);
        trayIcon.setPopupMenu(popup);

        final SystemTray tray = SystemTray.getSystemTray();
        tray.add(trayIcon);

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
            }
        });
        return trayIcon;
    }
}
