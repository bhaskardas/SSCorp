/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.superconduits.core.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author bhaskar
 */
public class PersistenceConnectionTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void initEmfAndEm(){
        emf = Persistence.createEntityManagerFactory("sscorpPersistenceUnit");
        em = emf.createEntityManager();
    }

    @After
    public void cleanup(){
        em.close();
    }

    @Test
    public void emptyTest(){

    }
}
