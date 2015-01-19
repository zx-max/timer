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

import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zxmax.tools.timerreview.Register;

import zxmax.tools.timerreview.serviceses.UINotificationService;

public class TrayIconMouseMotionListener implements MouseMotionListener {

    private TrayIcon trayIcon;

    public static final Logger logger = LoggerFactory
            .getLogger(TrayIconMouseMotionListener.class);

    public TrayIconMouseMotionListener(TrayIcon trayIcon) {
        this.trayIcon = trayIcon;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        UINotificationService uiNotificationService = (UINotificationService) Register.get(UINotificationService.class);
       
        if (null != uiNotificationService) {
            trayIcon.setToolTip(uiNotificationService.getToolTipMessage());
        } else {
            logger.debug("uiNotificationService is null.");
        }
    }

}
