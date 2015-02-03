/**
 * Timer Review  -  a personal time management tool
 *
 *
 * Copyright (C)  2012 - 2014 Parentini Massimiliano
 * Project home page: http://www.timer-review.net
 *
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */
package zxmax.tools.timerreview.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * for examples of select and more advanced configuration checkout:
 * http://code.google.com/p/generic-spatial-dao/
 */
public class HibernateBasicDaoImpl {

    public static final Logger logger = LoggerFactory
            .getLogger(HibernateBasicDaoImpl.class);

    private final EntityManager entityManager;

    public HibernateBasicDaoImpl() {
        
        final EntityManagerFactory emFactory = Persistence
                .createEntityManagerFactory("H2FileTomatoPU");
        entityManager = emFactory.createEntityManager();
                
    }

    public HibernateBasicDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
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
        logger.debug("loaded [{}], with id:[{}]", new Object[] { entityClass,
                id });
        return reference;

    }

    public List namedQuery(String namedQuery) {
        Query query = entityManager.createNamedQuery(namedQuery);
        List list = query.getResultList();
        return list;
    }

    public <T> T save(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            logger.error("#catch_block#", e);
        }

        logger.debug("saved: " + entity.toString());

        return entity;
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

        logger.warn(String.format("conversione in long cnon gestita per: [%s]",
                number.toString()));

        return Long.valueOf(number.toString());
    }

}
