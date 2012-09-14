package max.utility.tomato;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import max.utility.tomato.dao.TomatoDaoImpl;
import max.utility.tomato.domain.Tomato;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private TomatoDaoImpl tomatoDao;

	public static final Logger logger = LoggerFactory.getLogger(Main.class);

	public Main() {
		super();
		EntityManagerFactory emFactory = Persistence
				.createEntityManagerFactory("tomatoPU");
		EntityManager entityManager = emFactory.createEntityManager();
		tomatoDao = new TomatoDaoImpl(entityManager);
	}

	public static void main(String[] args) {

//		String focusOn = args[0];
		
		countDown(args);
		
//		new Main().saveTomato(focusOn);
//		new Main().list();

	}

	private static void countDown(String[] args) {
		BeeperControl beeperControl = new BeeperControl();
		beeperControl.beepForAnHour();
//		int duration = Integer.parseInt(args[1]);
//		logger.info("end timer");
	}

	private void list() {
		List<Tomato> list = tomatoDao.list();
		if (logger.isDebugEnabled()) {
			logger.debug(Arrays.toString(list.toArray()).replace("Tomato [",
					"\nTomato ["));
		}

	}

	private void saveTomato(String focusOn) {
		tomatoDao.save(new Tomato(focusOn));
	}

}
