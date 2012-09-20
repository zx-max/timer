package max.utility.tomato;

import java.io.File;
import java.security.CodeSource;
import java.util.MissingResourceException;

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
		// java.lang.IllegalArgumentException:
		// java.io.FileNotFoundException:
		// C:\Users\MAX\Documents\groovy\plugins\tomato\target\timer-manager.properties
		// (Impossibile trovare il file specificato)
		File propsFile = new File(path + File.separator + PropertyLoader.TIMER_MANAGER_PROP_FILE);
		try {
			PropertyLoader.loadFromFileSystem(propsFile);
		} catch (Exception e) {
			if (e instanceof MissingResourceException) {
				PropertyLoader.loadFromClassPathAsInputStream(PropertyLoader.TIMER_MANAGER_PROP_FILE);
				logger.info("load default timer configuration: [{}]", PropertyLoader.dump());
			}
		}

		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("tomatoPU");
		EntityManager entityManager = emFactory.createEntityManager();
		HibernateBasicDaoImpl basicDao = new HibernateBasicDaoImpl(entityManager);
		DaoRegister.put(HibernateBasicDaoImpl.class, basicDao);
		StartTimer timer = new StartTimer();
		timer.setVisible(true);
	}
}
