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

import java.util.List;
import java.util.Map;

public class ValidatorService {
    // private List<ObjectValidator> validators = null;
    private Map<Class, ObjectValidator> commands = null;

    public ValidatorService(Map<Class, ObjectValidator> commands) {
        this.commands = commands;
    }

    public List<String> validate(Object objectToValidate) {

        ObjectValidator objectValidator = commands.get(objectToValidate
                .getClass());
        List<String> errorMessages = objectValidator.validate(objectToValidate);

        // for (ObjectValidator validator : validators) {
        // List<String> messages = validator
        // .validate((Tomato) objectToValidate);
        //
        // errorMessages.addAll(messages);
        //
        // }

        return errorMessages;
    }
}
