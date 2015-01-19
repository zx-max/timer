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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import zxmax.tools.timerreview.domain.Tomato;

public class HibernateBasicDaoImplTest {

    private HibernateBasicDaoImpl basicDao = null;

    public HibernateBasicDaoImplTest() {
        EntityManagerFactory emFactory = Persistence
                .createEntityManagerFactory("H2MemTomatoPU");
        EntityManager entityManager = emFactory.createEntityManager();

        basicDao = new HibernateBasicDaoImpl(entityManager);
    }

    @Test
    public void testCount() {
        Long count = basicDao.count("Tomato.count");
        assertTrue(2 == count);
    }

    @Test
    public void testNamedQueryTomatoReviewList() {
        List list = basicDao.namedQuery("TomatoReview.list");
        assertTrue(0 == list.size());
    }

    @Test
    public void testLoad() {
        Tomato tomato = basicDao.load(Tomato.class, 2L);
        assertNotNull(tomato);
        assertTrue(tomato.getId() == 2L);
    }

    @Test
    public void testSave() {
        Tomato entity = new Tomato("prova");
        assertEquals(null, entity.getId());
        basicDao.save(entity);
        assertEquals(Long.valueOf(3L), entity.getId());
    }

}
