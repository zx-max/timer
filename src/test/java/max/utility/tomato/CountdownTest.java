package max.utility.tomato;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.Callable;

import max.utility.tomato.aspects.task.LogExceptionAspect;
import max.utility.tomato.tasks.ThorwUncheckedExceptionTask;

import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

@ContextConfiguration
public class CountdownTest extends AbstractJUnit4SpringContextTests {
	private ListAppender<ILoggingEvent> listAppender;

	public CountdownTest() {
		PropertyLoader.loadFromClassPathAsInputStream("timer-manager.properties");
	}

	// http://stackoverflow.com/questions/3803184/setting-logback-appender-path-programmatically
	@Test
	public void should_log_exception() throws InterruptedException {
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		listAppender = new ListAppender<ILoggingEvent>();
		listAppender.start();

		// attach the rolling file appender to the logger of your choice
		Logger logbackLogger = loggerContext.getLogger(LogExceptionAspect.class);
		logbackLogger.addAppender(listAppender);

		// OPTIONAL: print logback internal status messages
		// StatusPrinter.print(loggerContext);

		Countdown<Object> countdown = new Countdown<Object>();
		Callable<Object> task = new ThorwUncheckedExceptionTask();
		countdown.start(task);
		Thread.sleep(6000);
		assertThat(listAppender.list.size(), is(2));
		ILoggingEvent event = listAppender.list.get(1);
		assertTrue(Main.CATCH_BLOCK.equals(event.getFormattedMessage()));
		assertTrue(ThorwUncheckedExceptionTask.BLA_BLA_BLA.equals(event.getThrowableProxy().getMessage()));
		assertNotNull(event.getThrowableProxy().getStackTraceElementProxyArray());
	}
}
