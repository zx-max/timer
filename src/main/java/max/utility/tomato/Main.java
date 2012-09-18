package max.utility.tomato;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import max.utility.tomato.dao.HibernateBasicDaoImpl;
import max.utility.tomato.gui.StartTimer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	public static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		try {
			new Main();
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public Main() {
		super();
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("tomatoPU");
		EntityManager entityManager = emFactory.createEntityManager();
		HibernateBasicDaoImpl basicDao = new HibernateBasicDaoImpl(entityManager);
		DaoRegister.put(HibernateBasicDaoImpl.class, basicDao);
		StartTimer timer = new StartTimer();
		timer.setVisible(true);
	}

}
