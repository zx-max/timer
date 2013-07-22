package zxmax.tools.timerreview;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Countdown<T> {

    public static final Logger logger = LoggerFactory
            .getLogger(Countdown.class);

    private long duration;

    private final ScheduledExecutorService scheduledExecutorService = Executors
            .newSingleThreadScheduledExecutor();

    private TimeUnit timeUnit;

    public Countdown() {
        duration = Integer.valueOf(PropertyLoader.getProperty("duration"));
        timeUnit = Enum.valueOf(TimeUnit.class,
                PropertyLoader.getProperty("time.measurement.unit"));
        logger.info("timer: " + duration + " " + timeUnit);
    }

    public Countdown(long duration) {
        this.duration = duration;
        timeUnit = TimeUnit.MINUTES;
        logger.info("timer: " + duration + " " + timeUnit);
    }

    public void start(Callable<T> task) {
        logger.debug("before timer");
        scheduledExecutorService.schedule(task, duration, timeUnit);
        logger.debug("after timer");
        scheduledExecutorService.shutdown();
    }

}
