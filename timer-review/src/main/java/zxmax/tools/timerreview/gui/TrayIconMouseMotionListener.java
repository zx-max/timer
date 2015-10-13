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
        UINotificationService uiNotificationService = (UINotificationService) Register
                .get(UINotificationService.class);

        if (null != uiNotificationService) {
            trayIcon.setToolTip(uiNotificationService.getToolTipMessage());
        } else {
            logger.debug("uiNotificationService is null.");
        }
    }

}
