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
package zxmax.tools.timerreview.serviceses;






import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;

import zxmax.tools.timerreview.I18N;
import zxmax.tools.timerreview.domain.Tomato;

public class UINotificationService {

    private static final String TIMER_ENDED_LABEL = "timer.ended";
    private Tomato tomato = null;

    public UINotificationService(Tomato tomato) {
        super();
        setTomato(tomato);
    }

    public String getToolTipMessage() {
        if (null != getTomato()) {

            LocalDateTime startTime = getTomato().getStartTime();
            LocalDateTime endTimer = startTime.plusMinutes(getTomato()
                    .getDuration());
            LocalDateTime now = new LocalDateTime();
            
            final int minRemaining = Minutes.minutesBetween(now,endTimer).getMinutes() + 1;

            String timeLeft = "min: "+ minRemaining;

            String toolTipMessage = timeLeft + " / "
                    + System.getProperty("line.separator")
                    + getTomato().getTitle() + " / "
                    + System.getProperty("line.separator")
                    + getTomato().getFocusOn();

            return toolTipMessage;

        } else {
            return I18N.getKey(this.getClass(), TIMER_ENDED_LABEL);
        }
    }

    public Tomato getTomato() {
        return tomato;
    }

    public void setTomato(Tomato tomato) {
        this.tomato = tomato;
    }
}
