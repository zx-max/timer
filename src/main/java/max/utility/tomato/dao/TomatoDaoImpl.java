package max.utility.tomato.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import max.utility.tomato.domain.Tomato;

public class TomatoDaoImpl {

	public static final Logger logger = LoggerFactory.getLogger(TomatoDaoImpl.class);

	private EntityManager entityManager;

	public TomatoDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Tomato tomato) {
		try {
			logger.trace("before open transaction");
			entityManager.getTransaction().begin();
			logger.trace("before save");
			entityManager.persist(tomato);
			logger.trace("before flush");
			entityManager.flush();
			logger.trace("before commit");
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			logger.error("#catch_block#", e);
		}
		logger.debug(tomato.toString());
	}

	public List<Tomato> list() {
		Query query = entityManager.createNamedQuery("TomatoDaoImpl.list");
		List list = query.getResultList();
		return list;
	}

}
