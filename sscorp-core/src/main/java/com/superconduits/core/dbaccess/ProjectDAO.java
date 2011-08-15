/*
 * This class is the bridge between the application and the database layer.
 * It interacts with the database and quries all project related information
 * in the application and sends it back to the service layer for formatting.
 */

package com.superconduits.core.dbaccess;

import com.superconduits.core.to.project.ProjectCategoryTO;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * @author bhaskar
 * @Created on 20th Nov 2010
 * @Version : 1.0
 * @ChangeLog :
 */
public class ProjectDAO {

    /**
     * fetch all the project categories based on the company.
     * @param em
     * @param companyId
     * @return
     * @throws Exception
     */
    public List<ProjectCategoryTO> fetch(EntityManager em, Integer companyId)
                                                            throws Exception {
        return em.createNamedQuery("ProjectCategory.fetchAll")
            .setParameter("companyId", companyId)
            .getResultList();
    }
}
