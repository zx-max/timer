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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Register {
    private static Map<Class, Object> registry = new ConcurrentHashMap<Class, Object>();

    private Register() {
    }

    public static Object get(final Class key) {
        return registry.get(key);
    }

    public static void put(final Class key, final Object value) {
        registry.put(key, value);
    }

}
