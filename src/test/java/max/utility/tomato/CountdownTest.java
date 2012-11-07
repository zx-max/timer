package max.utility.tomato;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.Callable;

import max.test.logging.LogStub;
import max.utility.tomato.aspects.task.LogExceptionAspect;
import max.utility.tomato.tasks.ThorwUncheckedExceptionTask;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

@ContextConfiguration
public class CountdownTest extends AbstractJUnit4SpringContextTests {

	public CountdownTest() {
		PropertyLoader.loadFromClassPathAsInputStream("timer-manager.properties");
	}

	@Rule
	public LogStub logStub = new LogStub() {
		{
			record(LogLevel.DEBUG);
			recordLoggingForType(Countdown.class);
			// togliendo i commenti a una delle righe successive, l'aspetto non
			// viene eseguito.
			// recordLoggingForType(ThorwUncheckedExceptionTask.class);
			recordLoggingForType(LogExceptionAspect.class);
		}
	};

	// qoogle: slf4j get appender
	// http://stackoverflow.com/questions/3803184/setting-logback-appender-path-programmatically
	// http://itsthecodestupid.blogspot.it/2011/02/programmatically-adding-appender-and.html
	// http://www.slf4j.org/extensions.html#javaagent
	// http://tux2323.blogspot.it/2011/06/test-logging-via-junit-rule.html
	@Test
	public void should_log_exception() throws InterruptedException {
		Countdown<Object> countdown = new Countdown<Object>();
		Callable<Object> task = new ThorwUncheckedExceptionTask();
		countdown.start(task);
		Thread.sleep(6000);
		ListAppender<ILoggingEvent> listAppender = logStub.getListAppender();
		System.out.println(listAppender.list);
		assertThat(logStub.size(), is(1));
		assertTrue(logStub.containes("#catch_block#"));
		assertTrue(logStub.catchException(ThorwUncheckedExceptionTask.BLA_BLA_BLA));
	}
}
