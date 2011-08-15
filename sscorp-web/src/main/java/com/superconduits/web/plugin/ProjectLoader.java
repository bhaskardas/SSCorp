/*
 * This custom class loads the project information of super sales corporation
 * into the application memory at server startup.
 */

package com.superconduits.web.plugin;

import com.superconduits.core.business.interfaces.IProjects;
import com.superconduits.core.to.AddressTO;
import com.superconduits.core.to.CompanyProfileTO;
import com.superconduits.core.to.project.ProjectCategoryTO;
import com.superconduits.core.to.project.ProjectTO;
import com.superconduits.core.util.SSCorpUtil;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.utilities.util.PropertiesUtil;

/**
 * @author bhaskar
 * @Created on 03rd Jan 2011
 * @Version : 1.0
 * @ChangeLog :
 */
public class ProjectLoader implements PlugIn{

    private ServletContext sc;
    private final String key = "project";
    private String[] imagePath;

    @Override
    public void init(ActionServlet as, ModuleConfig mc)
                                throws ServletException {

        try{
            sc = as.getServletContext();
            Integer companyId = ((CompanyProfileTO)sc.getAttribute("companyProfile"))
                                                    .getCompanyId();

            //TODO: Custom Exception for failure in reading property file.
            imagePath = PropertiesUtil.readProperty(
                                "sscorp-config", "projectCatImagePath")
                                .split(",,");

            IProjects iProjects = (IProjects)SSCorpUtil.getObject(key);
            List<ProjectCategoryTO> projCatList =
                                    iProjects.fetchProjectCategories(companyId);

            if(projCatList != null && projCatList.size() > 0){
                prepareFinalProjectList(projCatList);
                sc.setAttribute("projectCategories", projCatList);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This function is used to prepare the final list of projects based on the
     * project categories passed as the input parameters.
     * @param projCatList
     */
    private void prepareFinalProjectList(List<ProjectCategoryTO> projCatList)
                                            throws Exception{
        for(ProjectCategoryTO pcto : projCatList){
            pcto.setProjectImage(SSCorpUtil.setImagePath(imagePath,
                                            pcto.getProjectImage()));
            List<ProjectTO> ptos = (List<ProjectTO>)pcto.getProjectTOs();
            if(ptos != null && ptos.size() > 0){
                modifyProjectObjects(ptos);
                pcto.setProjectTOs(ptos);
            }
        }
    }

    /**
     * Modifies the individual project object inside the list of project
     * objects by adding  the location information of the object.
     * @param ptos
     */
    private void modifyProjectObjects(List<ProjectTO> ptos){
        for(ProjectTO pto : ptos){
            if(pto.getAddressTO() != null)
                pto.setCity(setCity(pto.getAddressTO()));
        }
    }

    /**
     * sets the city proerty of ProjectoForm object from two parameters received
     * from the backend, namely, city and province in the address object. 
     * Combines these two parameters to make a single property for display
     * purpose.
     * @param addressTO
     * @return
     */
    private String setCity(AddressTO addressTO){
        String city = "";

        city = addressTO.getCity() != null && addressTO.getCity().length() > 1 ?
                                                addressTO.getCity() + ", " : "";

        if(addressTO.getProvince() != null && addressTO.getProvince()
                                                        .length() > 1){
            city += addressTO.getProvince();
        }else{
            city = addressTO.getCity();
        }

        return city;
    }

    @Override
    public void destroy() {}
}