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

import java.lang.Thread.UncaughtExceptionHandler;

import org.slf4j.Logger;

public class MyUncaughtExceptionHandler implements UncaughtExceptionHandler {

	private Logger logger = null;

	public MyUncaughtExceptionHandler(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {

		// if (e instanceof IllegalStateException
		// || e instanceof PersistenceException) {
		// String message = I18N.getLabel(this.getClass(),
		// "another.instance.already.open");
		// JOptionPane.showOptionDialog(null, message,
		// I18N.getLabel(this.getClass(), "error.box.title"),
		// JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
		// null, new Object[] {}, null);
		// } else {
		// logger.error("", e);
		// }

		logger.error("", e);
	}

}
