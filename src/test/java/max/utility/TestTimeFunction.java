package max.utility;

import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatterBuilder;
import org.junit.Test;

public class TestTimeFunction {

	@Test
	public void diff() {
		LocalDateTime start = new LocalDateTime(2012, 2, 6, 10, 0, 51);
		LocalDateTime atnow = new LocalDateTime(2012, 2, 6, 11, 10, 51);
		PeriodFormatterBuilder formatter = new PeriodFormatterBuilder();
		formatter.appendMinutes().appendSeparator(":").appendSeconds();
		Period period = new Period(start.plusMinutes(25), atnow);
		String timeLeft = period.toString(formatter.toFormatter());
		int tl = 25 - (atnow.getMinuteOfHour() - start.getMinuteOfHour());
		System.out.println(tl);
		// 25-(now-start) = 25 - now + start
	}
}
// 11.10
// 10.25