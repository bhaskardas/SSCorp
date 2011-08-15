/*
 * This class loads the JPA Entity Manager Factory object. Initializes the
 * database connection.
 */

package com.superconduits.core.util.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author bhaskar
 * @Created on 17th Sep 2010
 * @Version : 1.0
 * @ChangeLog :
 */
public class PersistenceUtil{

    private static EntityManagerFactory emf;

    //static block which reads the persistence unit mentioned
    //persistence.xml file and initializes the database connection and
    //populates the EntityManagerFactory reference.
    static{
        emf = Persistence.createEntityManagerFactory("sscorpPersistenceUnit");
    }

    /**
     * @return the emf
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
