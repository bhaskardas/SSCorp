/**
 * Copyright Super Sales Corporation 2009,  
 * All rights reserved.
 */

package com.superconduits.web.plugin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.utilities.util.tuplelibrary.FourTuple;

import com.superconduits.core.business.interfaces.ICompanyProfile;
import com.superconduits.core.exceptions.SSCorpBusinessException;
import com.superconduits.core.exceptions.SSCorpException;
import com.superconduits.core.to.AchievementsTO;
import com.superconduits.core.to.AddressTO;
import com.superconduits.core.to.CompanyProfileTO;
import com.superconduits.core.to.EmployeeTO;
import com.superconduits.core.util.SSCorpUtil;

/**
 * This custom class loads the company profile information into the
 * application scope at server startup.
 * 
 * @author bhaskar.das
 * @Created on 03rd Jan 2011
 * @Version : 1.0, 1.1, 1.2
 * @ChangeLog : 27 Feb 2011, 
 * 				as on 02 Oct 2011 - 1. Changes regarding exception
 * 				handling added in PropertiesUtil.java.
 * 				2. Added code for handling exception conditions.
 */
public class CompanyProfileLoader implements PlugIn{
    private static Logger logger = Logger.getLogger(CompanyProfileLoader.class);
    private ServletContext sc;
    
    @Override
    public void init(ActionServlet as, ModuleConfig mc) 
                            throws ServletException {

        sc = as.getServletContext();

        logger.info("Going to set the entire companyprofile information in " +
                    "the application scope.");
        try{
        	setCompanyProfile();
        }catch (SSCorpException e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage(), e);
		}
        
        logger.info("Successfully completed setting the companyprofile " +
                    "information in the application scope.");
    }

    /**
     * set the company profile data in the application scope.
     * 
     * @param request 
     * 			HttpServletRequest object
     * @throws IOException
     * @throws SSCorpException
     */
    @SuppressWarnings("unchecked")
	private void setCompanyProfile()
            throws SSCorpException {

        logger.info("in setCompanyProfile function.. Going to set"
                + "the companyProfile object in application scope.");
        
        FourTuple<CompanyProfileTO, 
				List<EmployeeTO>, 
				List<AchievementsTO>, 
				AddressTO> fourTuple = null;
        try {
            ICompanyProfile iCompanyProfile = (ICompanyProfile)SSCorpUtil
                                                .getObject("companyProfile");
            
            
            fourTuple = iCompanyProfile.fetchCompanyProfileInfo();
        }catch (SSCorpBusinessException e) {
        	logger.info("Exception occurred "
                    + "in CompanyProfileLoader Plugin while setting "
                    + "company profile object");
			e.printStackTrace();
			throw new SSCorpException(e.getMessage(), e);
		}

        if(fourTuple == null){
            throw new SSCorpException("Four Tuple Object is null. Company Profile" +
                                " not loaded at all.");
        }

        CompanyProfileTO companyProfileTO = (CompanyProfileTO)fourTuple.one;
        if (companyProfileTO == null) {
            throw new SSCorpException("companyProfileTO object inside fourTuple " +
                                    "object is found to be null. Aborting.");
        }
            
        List<EmployeeTO> employeeTOs = (List<EmployeeTO>)fourTuple.two;
        if(employeeTOs == null)
            throw new SSCorpException("employee list inside fourTuple object" +
                                " is found to be null. Aborting.");

        List<AchievementsTO> atos = (List<AchievementsTO>)fourTuple.three;
        if(atos == null)
            throw new SSCorpException("achievements list inside fourTuple object" +
                                " is found to be null. Aborting.");

        AddressTO addressTO = (AddressTO)fourTuple.four;
        if(addressTO == null){
            throw new SSCorpException("addressTO object inside fourTuple object" +
                                " is found to be null. Aborting.");
        }

        sc.setAttribute("companyProfile", companyProfileTO);
        sc.setAttribute("employeeList", employeeTOs);
        sc.setAttribute("achievements", atos);
        sc.setAttribute("address", addressTO);
    }

    @Override
    public void destroy() {

    }
}