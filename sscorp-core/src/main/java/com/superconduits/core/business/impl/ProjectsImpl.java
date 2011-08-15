/*
 * This is the main implementation class in the service layer catering to all
 * project realted requests in the application. It prvides the actual
 * implementation to the buisness methods exposed by the service layer interface
 * IProjects.
 */

package com.superconduits.core.business.impl;

import com.superconduits.core.business.interfaces.IProjects;
import com.superconduits.core.dbaccess.ProjectDAO;
import com.superconduits.core.to.project.ProjectCategoryTO;
import com.superconduits.core.util.SSCorpUtil;
import com.superconduits.core.util.persistence.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;

/**
 * @author bhaskar
 * @Created on 20th Nov 2010
 * @Version : 1.0
 * @ChangeLog :
 */
public class ProjectsImpl implements IProjects{

    private static Logger logger = Logger.getLogger(ProjectsImpl.class);
    
    @Override
    public List<ProjectCategoryTO> fetchProjectCategories(Integer companyId) {
        logger.info("Entered into fetchProjectCategories function to fetch" +
                    " the project categories");
        EntityManager em = PersistenceUtil.getEntityManagerFactory()
                                            .createEntityManager();

        //TODO: Object Creation Exception
        ProjectDAO productDAO = (ProjectDAO)SSCorpUtil.getObject("projectDAO");

        try{
            logger.info("Going to the call the database layer to fetch the " +
                        " required records.");
            return productDAO.fetch(em, companyId);
        }catch(Exception e){
            logger.error("Exception occurred while fetching project " +
                        "categories. Going to forward the exception " +
                        " and returning null from business layer.");
            e.printStackTrace();
            return null;
        }finally{
            em.close();
        }
    }
}
