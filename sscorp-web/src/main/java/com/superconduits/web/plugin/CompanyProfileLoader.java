/*
 * This custom loads the company profile information into the
 * application memory at server startup.
 */

package com.superconduits.web.plugin;

import com.superconduits.core.business.interfaces.ICompanyProfile;
import com.superconduits.core.to.AchievementsTO;
import com.superconduits.core.to.AddressTO;
import com.superconduits.core.to.CompanyProfileTO;
import com.superconduits.core.to.EmployeeTO;
import com.superconduits.core.util.SSCorpUtil;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.utilities.util.PropertiesUtil;
import org.utilities.util.tuplelibrary.FourTuple;

/**
 * @author bhaskar
 * @Created on 03rd Jan 2011
 * @Version : 1.0, 1.1
 * @ChangeLog : 27 Feb 2011
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
        setCompanyProfile();
        logger.info("Successfully completed setting the companyprofile " +
                    "information in the application scope.");
    }

    /**
     * set the company profile data in the application scope.
     * @param request HttpServletRequest object
     * @throws IOException
     * @throws ServletException
     */
    private void setCompanyProfile()
            throws ServletException {

        logger.info("in setCompanyProfile function.. Going to set"
                + "the companyProfile object in application scope.");
        try {
            ICompanyProfile iCompanyProfile = (ICompanyProfile)SSCorpUtil
                                                .getObject("companyProfile");
            
            FourTuple fourTuple =
                    iCompanyProfile.fetchCompanyProfileInfo(Integer.parseInt(
                                        PropertiesUtil.readProperty(
                                        "sscorp-config", "sscorp.companyId")));

            if(fourTuple == null){
                throw new Exception("Four Tuple Object is null. Company Profile" +
                                    " not loaded at all.");
            }

            CompanyProfileTO companyProfileTO = (CompanyProfileTO)fourTuple.one;
            if (companyProfileTO == null) {
                throw new Exception("companyProfileTO object inside fourTuple " +
                                    "object is found to be null. Aborting.");
            }
            
            List<EmployeeTO> employeeTOs = (List<EmployeeTO>)fourTuple.two;
            if(employeeTOs == null)
                throw new Exception("employee list inside fourTuple object" +
                                    " is found to be null. Aborting.");

            List<AchievementsTO> atos = (List<AchievementsTO>)fourTuple.three;
            if(atos == null)
                throw new Exception("achievements list inside fourTuple object" +
                                    " is found to be null. Aborting.");

            AddressTO addressTO = (AddressTO)fourTuple.four;
            if(addressTO == null){
                throw new Exception("addressTO object inside fourTuple object" +
                                    " is found to be null. Aborting.");
            }

            sc.setAttribute("companyProfile", companyProfileTO);
            sc.setAttribute("employeeList", employeeTOs);
            sc.setAttribute("achievements", atos);
            sc.setAttribute("address", addressTO);
        } catch (Exception e) {
            logger.info("Exception occurred "
                    + "in CompanyProfileLoader Plugin while setting "
                    + "company profile object");

            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}