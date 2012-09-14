package max.utility.tomato.gui;

import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import max.utility.tomato.dao.TomatoDaoImpl;

import org.junit.Test;

public class StartTimerTest {
	private TomatoDaoImpl tomatoDao;

	public StartTimerTest() {
		super();
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("tomatoPU");
		EntityManager entityManager = emFactory.createEntityManager();
		tomatoDao = new TomatoDaoImpl(entityManager);
		StartTimer timer = new StartTimer(tomatoDao);
		timer.saveTomato("sdfasdf");
	}

	@Test
	public void testOpenWindow() {
		fail("Not yet implemented");
	}

}
