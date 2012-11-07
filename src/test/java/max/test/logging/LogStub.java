package max.test.logging;

import java.util.ArrayList;
import java.util.List;

import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.read.ListAppender;

@SuppressWarnings("deprecation")
public class LogStub extends TestWatchman {

	public enum LogLevel {
		TRACE(Level.TRACE), DEBUG(Level.DEBUG), INFO(Level.INFO), WARN(Level.WARN), ERROR(Level.ERROR);

		Level internalLevel;

		private LogLevel(Level level) {
			this.internalLevel = level;
		}
	}

	private final ListAppender<ILoggingEvent> listAppender = new ListAppender<ILoggingEvent>();

	private final LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

	private final List<Class> loggingSources = new ArrayList<Class>();

	private LogLevel level = LogLevel.TRACE;

	public ListAppender<ILoggingEvent> getListAppender() {
		return listAppender;
	}

	@Override
	public void starting(FrameworkMethod method) {
		before();
	}

	@Override
	public void finished(FrameworkMethod method) {
		after();
	}

	public void before() {
		// resetLoggingContext();
		for (Class logSource : loggingSources) {
			addAppenderToType(logSource);
		}
		listAppender.start();
	}

	public void after() {
		listAppender.stop();
		// resetLoggingContext();
	}

	public void record(LogLevel level) {
		this.level = level;
	}

	public void recordLoggingForObject(Object sut) {
		Class type = sut.getClass();
		recordLoggingForType(type);
	}

	public <T> void recordLoggingForType(Class<T> type) {
		loggingSources.add(type);
		addAppenderToType(type);
	}

	public boolean containes(String loggingStatement) {
		List<ILoggingEvent> list = listAppender.list;
		for (ILoggingEvent event : list) {
			if (event.getFormattedMessage().contains(loggingStatement)) {
				return true;
			}
		}
		return false;
	}

	public int size() {
		return listAppender.list.size();
	}

	private <T> void addAppenderToType(Class<T> type) {
		Logger logger = (Logger) LoggerFactory.getLogger(type);
		logger.addAppender(listAppender);
		logger.setLevel(level.internalLevel);
	}

	// private void resetLoggingContext() {
	// lc.reset();
	// }

	public boolean catchException(String loggingStatement) {
		List<ILoggingEvent> list = listAppender.list;

		for (ILoggingEvent event : list) {
			IThrowableProxy throwableProxy = event.getThrowableProxy();
			if (null != throwableProxy && throwableProxy.getMessage().contains(loggingStatement)) {
				return true;
			}
		}

		return false;
	}
}
