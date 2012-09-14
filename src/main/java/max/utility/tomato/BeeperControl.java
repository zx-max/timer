package max.utility.tomato;

import static java.util.concurrent.TimeUnit.*;

import java.util.Date;
import java.util.concurrent.*;

import max.utility.tomato.gui.EndTomato;

public class BeeperControl {

	ScheduledExecutorService scheduledExecutorService = Executors
			.newScheduledThreadPool(5);
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void beepForAnHour() {
		try {

			Callable actionToPerform = new Callable() {
				public Object call() throws Exception {
					System.out.println("open a window...");
					EndTomato endTomato = new EndTomato();
					endTomato.openWindow();
					return "timer ended...";
				}
			};
			
			ScheduledFuture scheduledFuture = scheduledExecutorService
					.schedule(actionToPerform, 5, TimeUnit.SECONDS);

			System.out.println("result = " + scheduledFuture.get());
		} catch (Exception e) {
			// TODO: handle exception
		}

		scheduledExecutorService.shutdown();
	}
}
