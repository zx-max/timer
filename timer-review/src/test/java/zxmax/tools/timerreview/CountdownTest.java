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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.Callable;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import zxmax.tools.timerreview.aspects.LogExceptionAspect;
import zxmax.tools.timerreview.tasks.ThorwUncheckedExceptionTask;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

//@ContextConfiguration
public class CountdownTest /* extends AbstractJUnit4SpringContextTests */{

    private ListAppender<ILoggingEvent> listAppender;

    public CountdownTest() {
        PropertyLoader
                .loadFromClassPathAsInputStream(PropertyLoaderTest.TEST_TIMER_REVIEW_PROPERTIES_AS_PROPERTIES_FILE);
    }

    // http://stackoverflow.com/questions/3803184/setting-logback-appender-path-programmatically
    @Test
    public void should_log_exception() throws InterruptedException {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory
                .getILoggerFactory();
        listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();

        // attach the rolling file appender to the logger of your choice
        Logger logbackLogger = loggerContext
                .getLogger(LogExceptionAspect.class);
        logbackLogger.addAppender(listAppender);

        // OPTIONAL: print logback internal status messages
        // StatusPrinter.print(loggerContext);

        Countdown countdown = new Countdown();
        Callable<Object> task = new ThorwUncheckedExceptionTask();
        countdown.start(task);
        Thread.sleep(6000);
        assertThat(listAppender.list.size(), is(2));
        ILoggingEvent event = listAppender.list.get(1);
        assertTrue(LogExceptionAspect.CATCH_BLOCK.equals(event
                .getFormattedMessage()));
        assertTrue(ThorwUncheckedExceptionTask.BLA_BLA_BLA.equals(event
                .getThrowableProxy().getMessage()));
        assertNotNull(event.getThrowableProxy()
                .getStackTraceElementProxyArray());
    }
}
