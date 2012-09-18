package max.utility.tomato.gui;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import max.utility.tomato.dao.HibernateBasicDaoImpl;

public class StartTimerTest {
	private final HibernateBasicDaoImpl tomatoDao;

	public StartTimerTest() {
		super();
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("tomatoPU");
		EntityManager entityManager = emFactory.createEntityManager();
		tomatoDao = new HibernateBasicDaoImpl(entityManager);
	}

	// @Test
	public void testOpenWindow() {
		assertEquals(2, tomatoDao.namedQuery("TomatoDaoImpl.list").size());
	}

}
