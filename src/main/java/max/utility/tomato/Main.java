package max.utility.tomato;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import max.utility.tomato.dao.TomatoDaoImpl;
import max.utility.tomato.domain.Tomato;
import max.utility.tomato.gui.StartTimer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private TomatoDaoImpl tomatoDao;

	public static final Logger logger = LoggerFactory.getLogger(Main.class);

	public Main() {
		super();
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("tomatoPU");
		EntityManager entityManager = emFactory.createEntityManager();
		tomatoDao = new TomatoDaoImpl(entityManager);
		StartTimer timer = new StartTimer(tomatoDao);
		timer.setVisible(true);
		timer.openWindow();
	}

	public static void main(String[] args) {
		new Main();
	}

	private void list() {
		List<Tomato> list = tomatoDao.list();
		if (logger.isDebugEnabled()) {
			logger.debug(Arrays.toString(list.toArray()).replace("Tomato [", "\nTomato ["));
		}

	}

}
