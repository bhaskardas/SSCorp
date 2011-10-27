/**
 * Copyright Super Sales Corporation 2009,  
 * All rights reserved.
 */
 
package com.superconduits.core.business.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.utilities.util.PropertiesUtil;
import org.utilities.util.exceptions.PropertiesException;
import org.utilities.util.tuplelibrary.FourTuple;
import org.utilities.util.tuplelibrary.Tuple;

import com.superconduits.core.business.interfaces.ICompanyProfile;
import com.superconduits.core.dbaccess.CompanyProfileDAO;
import com.superconduits.core.exceptions.SSCorpBusinessException;
import com.superconduits.core.exceptions.SSCorpDatabaseException;
import com.superconduits.core.to.AchievementsTO;
import com.superconduits.core.to.AddressTO;
import com.superconduits.core.to.CompanyProfileTO;
import com.superconduits.core.to.EmployeeTO;
import com.superconduits.core.util.SSCorpUtil;
import com.superconduits.core.util.persistence.PersistenceUtil;

/**
 * This class is responsible for loading the 
 * company profile object into the super sales 
 * web application. It acts as the 
 * service/business layer class and executes 
 * all the logic related to communicating with 
 * database layer classes. It also prepares the
 * final object to be returned to the controller.
 * 
 * @author bhaskar.das
 * @since 25th Aug 2010
 * @version 1.0, 1.1, 1.2, 1.3
 * @changeLog 17th Oct 2010, 27 Feb 2011, 10 October, 2011
 */
public class CompanyProfileImpl implements ICompanyProfile{
	private static Logger logger = Logger.getLogger(CompanyProfileImpl.class);
 
	@Override
    public FourTuple<CompanyProfileTO, List<EmployeeTO>, List<AchievementsTO>, AddressTO> 
			fetchCompanyProfileInfo() throws SSCorpBusinessException{
		
		Integer companyId = null;
		String companyName = null;
		try {
			companyId = Integer.parseInt(
			        PropertiesUtil.readProperty(
			                "sscorp-config", "sscorp.companyId"));
			
			companyName = PropertiesUtil.readProperty(
                    	"sscorp-config", "sscorp.companyName");
		} catch (PropertiesException e1) {
			e1.printStackTrace();
			throw new SSCorpBusinessException(e1.getMessage(), e1);
		}
		
        EntityManager em = null;

        //TODO - Catch a custom Exception - ObjectCreationException
        CompanyProfileDAO companyProfileDAO = (CompanyProfileDAO)SSCorpUtil
                                            .getObject("companyProfileDAO");
        
        try{
            em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
            
            //fetch company profile
            CompanyProfileTO companyProfileTO = companyProfileDAO.fetch(em, companyId, companyName);

            //fetch top management information for leadership jsp page.
            List<EmployeeTO> employeeTOs = companyProfileDAO.fetchLeadershipInfo(em);

            //fetch company achievements
            List<AchievementsTO> achievementsTOs = companyProfileDAO
                                            .fetchAchievements(em, companyId);

            //fetch company address information for the contact us jsp page.
            AddressTO addressTO = companyProfileDAO.fetchCompanyAddress(em);

            return Tuple.tuple(companyProfileTO, employeeTOs, achievementsTOs, addressTO);
        }catch(SSCorpDatabaseException e){
        	if(logger.isDebugEnabled())
        		e.printStackTrace();
        	throw new SSCorpBusinessException(e.getMessage(), e);
        }finally{
            em.close();
        }
    }
}