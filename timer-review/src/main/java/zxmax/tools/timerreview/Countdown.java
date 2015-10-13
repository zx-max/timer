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

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Countdown {

    public static final Logger LOGGER = LoggerFactory
            .getLogger(Countdown.class);

    private final long duration;
    private final TimeUnit timeUnit;

    private final ScheduledExecutorService executorService = Executors
            .newSingleThreadScheduledExecutor();

    public Countdown() {
        duration = Integer.valueOf(PropertyLoader.getProperty("duration"));
        timeUnit = Enum.valueOf(TimeUnit.class,
                PropertyLoader.getProperty("time.measurement.unit"));

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("timer: " + duration + " " + timeUnit);
        }
    }

    public Countdown(final long duration) {
        this.duration = duration;
        timeUnit = TimeUnit.MINUTES;

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("timer: " + duration + " " + timeUnit);
        }
    }

    public void start(final Callable task) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("before timer");
        }
        executorService.schedule(task, duration, timeUnit);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("after timer");
        }
        executorService.shutdown();
    }

}
