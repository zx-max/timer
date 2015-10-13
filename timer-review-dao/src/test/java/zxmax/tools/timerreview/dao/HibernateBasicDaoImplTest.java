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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.h2.tools.RunScript;
import org.hibernate.jdbc.Work;
import org.junit.Test;

import zxmax.tools.timerreview.domain.Tomato;

public class HibernateBasicDaoImplTest {

    private HibernateBasicDaoImpl basicDao = null;

    public HibernateBasicDaoImplTest() {
        EntityManagerFactory emFactory = Persistence
                .createEntityManagerFactory("H2MemTomatoPU");
        EntityManager entityManager = emFactory.createEntityManager();

        basicDao = new HibernateBasicDaoImpl(entityManager);

        Work work = new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {

                try {
                    RunScript.execute(connection, new FileReader(new File(
                            "src/test/resources/test_data.sql")));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        basicDao.getSession().doWork(work);

    }

    @Test
    public void testCount() {
        Long count = basicDao.count("Tomato.count");
        assertTrue(3 == count);
    }

    @Test
    public void testNamedQueryTomatoList() {
        List list = basicDao.namedQuery("TomatoDaoImpl.list");
        assertTrue("list.size(): " + list.size(), 3 == list.size());
    }

    @Test
    public void testNamedQueryTomatoReviewList() {
        List list = basicDao.namedQuery("TomatoReview.list");
        assertTrue("list.size(): " + list.size(), 3 == list.size());
    }

    @Test
    public void testLoad() {
        Tomato tomato = basicDao.load(Tomato.class, 2L);
        assertNotNull(tomato);
        assertTrue(tomato.getId() == 2L);
    }

    @Test
    public void testFindByExample() {

        List list = basicDao.namedQuery("ExistTomatoReviewForTomato",
                "tomatoId", 3L);
        assertEquals(1, list.size());
    }

    @Test
    public void testSave() {
        Tomato entity = new Tomato("prova");
        assertEquals(null, entity.getId());
        basicDao.save(entity);
        assertTrue(entity.getId() > 0);
    }

}
