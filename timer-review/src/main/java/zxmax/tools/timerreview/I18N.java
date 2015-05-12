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

import java.util.Locale;
import java.util.ResourceBundle;

public class I18N {

	public static String getLabel(Class clazz, String key) {

		String baseName = clazz.getCanonicalName();

		ResourceBundle labels = ResourceBundle.getBundle(baseName,
				Locale.getDefault());

		return labels.getString(key);
	}

}
