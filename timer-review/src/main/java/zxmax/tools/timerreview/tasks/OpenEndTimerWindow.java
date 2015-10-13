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
package zxmax.tools.timerreview.tasks;

import java.util.TimerTask;
import java.util.concurrent.Callable;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zxmax.tools.timerreview.Register;
import zxmax.tools.timerreview.domain.Tomato;
import zxmax.tools.timerreview.gui.EndTimerWindow;
import zxmax.tools.timerreview.serviceses.UINotificationService;

public class OpenEndTimerWindow extends TimerTask implements Callable<JFrame> {

    private static final Logger logger = LoggerFactory
            .getLogger(OpenEndTimerWindow.class);

    private Tomato tomato;

    public OpenEndTimerWindow(Tomato tomato) {
        this.tomato = tomato;
    }

    // Timer timer = new Timer();
    // timer.schedule(task, delay);
    @Override
    public void run() {
        openEndTimerWindow();
    }

    @Override
    public JFrame call() throws Exception {
        return openEndTimerWindow();
    }

    private JFrame openEndTimerWindow() {
        UINotificationService uiNotificationService = (UINotificationService) Register
                .get(UINotificationService.class);
        uiNotificationService.setTomato(null);
        EndTimerWindow endTimer = new EndTimerWindow(tomato);
        endTimer.createBufferStrategy(1);
        logger.debug("open endTomato");
        return endTimer;
    }

}
