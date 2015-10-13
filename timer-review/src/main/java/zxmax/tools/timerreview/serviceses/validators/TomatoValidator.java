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
package zxmax.tools.timerreview.serviceses.validators;

import java.util.ArrayList;
import java.util.List;

import zxmax.tools.timerreview.I18N;
import zxmax.tools.timerreview.domain.Tomato;
import zxmax.tools.timerreview.gui.StartTimerWindow;

public class TomatoValidator extends ObjectValidator {

    public TomatoValidator() {
    }

    List<String> validate(Object objectToValidate) {
        List<String> errorMessages = new ArrayList<String>();

        if (objectToValidate instanceof Tomato) {

            Tomato tomato = (Tomato) objectToValidate;

            if (tomato.getTitle() != null && tomato.getTitle().length() > 80) {
                errorMessages.add(I18N.getLabel(StartTimerWindow.class,
                        "tomato.title.longer.than.max"));
            }

            if (tomato.getFocusOn() != null
                    && tomato.getFocusOn().length() > 500) {
                errorMessages.add(I18N.getLabel(StartTimerWindow.class,
                        "tomato.focusOn.longer.than.max"));
            }

            if (tomato.getDuration() < 1 || tomato.getDuration() > 60) {
                errorMessages.add(I18N.getLabel(StartTimerWindow.class,
                        "tomato.duration.out.of.range"));
            }

        }
        return errorMessages;
    }
}
