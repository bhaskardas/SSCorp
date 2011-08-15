/*
 * This custom class initializes/establishes the JPA connection with the
 * database at server startup.
 */

package com.superconduits.web.plugin;

import com.superconduits.core.util.persistence.PersistenceUtil;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

/**
 * @author bhaskar
 * @Created on 03rd Jan 2011
 * @Version : 1.0
 * @ChangeLog :
 */
public class JPAInitializor implements PlugIn{

    private static Logger logger = Logger.getLogger(JPAInitializor.class);

    @Override
    public void init(ActionServlet as, ModuleConfig mc) throws ServletException {
        logger.info("Initializing the JPA Persistence Manager Factory.");
        EntityManager em = PersistenceUtil.getEntityManagerFactory()
                                            .createEntityManager();

        if(em == null){
            throw new ServletException("Could not establish a connection with " +
                                "the database. Entity manager returned null.");
        }

        logger.info("Successfully initialized the persistence manager factory." +
                    " The connection with the database is now established.");
    }

    @Override
    public void destroy() {}
}
