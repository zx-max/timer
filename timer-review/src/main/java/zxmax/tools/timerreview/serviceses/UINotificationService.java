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

			final int minRemaining = Minutes.minutesBetween(now, endTimer)
					.getMinutes() + 1;

			String timeLeft = "min: " + minRemaining;

			String toolTipMessage = timeLeft + " / "
					+ System.getProperty("line.separator")
					+ getTomato().getTitle() + " / "
					+ System.getProperty("line.separator")
					+ getTomato().getFocusOn();

			return toolTipMessage;

		} else {
			return I18N.getLabel(this.getClass(), TIMER_ENDED_LABEL);
		}
	}

	public Tomato getTomato() {
		return tomato;
	}

	public void setTomato(Tomato tomato) {
		this.tomato = tomato;
	}
}
