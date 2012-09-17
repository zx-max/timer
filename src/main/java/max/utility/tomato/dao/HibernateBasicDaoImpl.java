package max.utility.tomato.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import max.utility.tomato.domain.Tomato;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * per esmpi di select e configurazioni più avanzate, vedi ad esempio:
 * http://code.google.com/p/generic-spatial-dao/
 */
public class HibernateBasicDaoImpl {

	public static final Logger logger = LoggerFactory.getLogger(HibernateBasicDaoImpl.class);

	private final EntityManager entityManager;

	public HibernateBasicDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public org.hibernate.Session getSession() {
		return ((org.hibernate.Session) entityManager.getDelegate());
	}

	public List<Tomato> list() {
		Query query = entityManager.createNamedQuery("TomatoDaoImpl.list");
		List<Tomato> list = query.getResultList();
		return list;
	}

	public <T> T load(Class<T> entityClass, Long id) {
		T reference = entityManager.getReference(entityClass, id);
		logger.debug("loaded [{}], with id:[{}]", new Object[] { entityClass, id });
		return reference;

	}

	public void save(Object entity) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.flush();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			logger.error("#catch_block#", e);
		}
		logger.debug(entity.toString());
	}

}
