package max.utility.tomato.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * for examples of select and more advanced configuration checkout:
 * http://code.google.com/p/generic-spatial-dao/
 */
public class HibernateBasicDaoImpl {

	public static final Logger logger = LoggerFactory.getLogger(HibernateBasicDaoImpl.class);

	private final EntityManager entityManager;

	public HibernateBasicDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private Long convertToLong(Object number) {
		if (null == number) {
			return 0L;
		}
		if (number instanceof Number) {
			return ((Number) number).longValue();
		}
		if (number instanceof BigDecimal) {
			return ((BigDecimal) number).longValue();
		}
		if (number instanceof BigInteger) {
			return ((BigInteger) number).longValue();
		}
		if (number instanceof Integer) {
			return ((Integer) number).longValue();
		}
		if (number instanceof Double) {
			return ((Double) number).longValue();
		}
		if (number instanceof Float) {
			return ((Float) number).longValue();
		}

		return Long.valueOf(number.toString());
	}

	public Long count(String namedQuery) {
		Query query = entityManager.createNamedQuery(namedQuery);

		return convertToLong(query.getSingleResult());
	}

	public org.hibernate.Session getSession() {
		return ((org.hibernate.Session) entityManager.getDelegate());
	}

	public <T> T load(Class<T> entityClass, Long id) {
		T reference = entityManager.getReference(entityClass, id);
		logger.debug("loaded [{}], with id:[{}]", new Object[] { entityClass, id });
		return reference;

	}

	public List namedQuery(String namedQuery) {
		Query query = entityManager.createNamedQuery(namedQuery);
		List list = query.getResultList();
		return list;
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
