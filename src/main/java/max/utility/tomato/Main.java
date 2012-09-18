package max.utility.tomato;

import java.io.File;
import java.security.CodeSource;

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

	public Main() throws Exception {
		super();
		CodeSource codeSource = this.getClass().getProtectionDomain().getCodeSource();
		File jarFile = new File(codeSource.getLocation().toURI().getPath());
		String path = jarFile.getParentFile().getPath();
		File propsFile = new File(path + File.separator + PropertyLoader.TIMER_MANAGER_PROP_FILE);
		PropertyLoader.loadFromFileSystem(propsFile);

		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("tomatoPU");
		EntityManager entityManager = emFactory.createEntityManager();
		HibernateBasicDaoImpl basicDao = new HibernateBasicDaoImpl(entityManager);
		DaoRegister.put(HibernateBasicDaoImpl.class, basicDao);
		StartTimer timer = new StartTimer();
		timer.setVisible(true);
	}

}
