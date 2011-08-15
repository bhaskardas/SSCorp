/*
 * This is the main interface in the service layer to interact with the front
 * end layer of the application. This interface provides business functionality
 * to return all the projects related information of Super Sales Corporation.
 */

package com.superconduits.core.business.interfaces;

import com.superconduits.core.to.project.ProjectCategoryTO;
import com.superconduits.core.to.project.ProjectTO;
import java.util.List;

/**
 * @author bhaskar
 * @Created on 20th Nov 2010
 * @Version : 1.0
 * @ChangeLog :
 */
public interface IProjects {

    /**
     * Fetches the list of all the project categories that super sales
     * corporation is currently doing.
     * @param companyId
     * @return
     */
    public List<ProjectCategoryTO> fetchProjectCategories(Integer companyId);
}
