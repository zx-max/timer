package max.utility;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Run a simple task once every second, starting 3 seconds from now. Cancel the
 * task after 20 seconds.
 */
public final class AlarmClock {

	private static final class SoundAlarmTask implements Runnable {
		private int fCount;

		public void run() {
			++fCount;
			log("beep " + fCount);
		}
	}

	private final class StopAlarmTask implements Runnable {
		private final ScheduledFuture<?> fSchedFuture;

		StopAlarmTask(ScheduledFuture<?> aSchedFuture) {
			fSchedFuture = aSchedFuture;
		}

		public void run() {
			log("Stopping alarm.");
			fSchedFuture.cancel(DONT_INTERRUPT_IF_RUNNING);
			/*
			 * Note that this Task also performs cleanup, by asking the
			 * scheduler to shutdown gracefully.
			 */
			fScheduler.shutdown();
		}
	}

	private static final boolean DONT_INTERRUPT_IF_RUNNING = false;

	/** If invocations might overlap, you can specify more than a single thread. */
	private static final int NUM_THREADS = 1;

	private static void log(String aMsg) {
		System.out.println(aMsg);
	}

	/** Run the example. */
	public static void main(String... aArgs) throws InterruptedException {
		log("Main started.");
		AlarmClock alarmClock = new AlarmClock(0, 1, 20);
		alarmClock.activateAlarmThenStop();
		/*
		 * To start the alarm at a specific date in the future, the initial
		 * delay needs to be calculated relative to the current time, as in :
		 * Date futureDate = ... long startTime = futureDate.getTime() -
		 * System.currentTimeMillis(); AlarmClock alarm = new
		 * AlarmClock(startTime, 1, 20); This works only if the system clock
		 * isn't reset.
		 */
		log("Main ended.");
	}

	private final long fDelayBetweenRuns;

	private final long fInitialDelay;
	// PRIVATE
	private final ScheduledExecutorService fScheduler;

	private final long fShutdownAfter;

	AlarmClock(long aInitialDelay, long aDelayBetweenBeeps, long aStopAfter) {
		fInitialDelay = aInitialDelay;
		fDelayBetweenRuns = aDelayBetweenBeeps;
		fShutdownAfter = aStopAfter;
		fScheduler = Executors.newScheduledThreadPool(NUM_THREADS);
	}

	/** Sound the alarm for a few seconds, then stop. */
	void activateAlarmThenStop() {
		Runnable soundAlarmTask = new SoundAlarmTask();
		ScheduledFuture<?> soundAlarmFuture = fScheduler.scheduleWithFixedDelay(soundAlarmTask, fInitialDelay,
				fDelayBetweenRuns, TimeUnit.SECONDS);
		Runnable stopAlarm = new StopAlarmTask(soundAlarmFuture);
		fScheduler.schedule(stopAlarm, fShutdownAfter, TimeUnit.SECONDS);
	}
}