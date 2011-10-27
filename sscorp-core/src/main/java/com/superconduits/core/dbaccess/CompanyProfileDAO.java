/**
 * Copyright Super Sales Corporation 2009,  
 * All rights reserved.
 */

package com.superconduits.core.dbaccess;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.superconduits.core.exceptions.SSCorpDatabaseException;
import com.superconduits.core.to.AchievementsTO;
import com.superconduits.core.to.AddressTO;
import com.superconduits.core.to.CompanyProfileTO;
import com.superconduits.core.to.EmployeeTO;

/**
 * This is the DAO class for loading the company profile data and passing it
 * onto the business layer.
 * 
 * @author bhaskar.das
 * @since 25th Aug 2010
 * @version 1.0, 1.1
 * @changeLog 17th Oct 2010
 */
public class CompanyProfileDAO {
	private static Logger logger = Logger.getLogger(CompanyProfileDAO.class);
	
    /**
     * Gives the information regarding the company based on the
     * company name and the company id parameter.
     * 
     * @param em
     * 			the entity manager object to connect
     * 			to the database and execute queries.
     * @param companyId
     * 			the unique identifier for the company
     * @param compnayName
     * 			the unique name for the company.
     * @return
     * 		the copmanyProfileTO object.
     * @throws SSCorpDatabaseException
     * 		when exception occur while communicating with the
     * 		database.
     */
    public CompanyProfileTO fetch(EntityManager em, Integer companyId, String compnayName) 
    						throws SSCorpDatabaseException{
    	try{
    		return (CompanyProfileTO)em
            .createNamedQuery("CompanyProfileTO.find")
            .setParameter("companyId", companyId)
            .setParameter("companyName", compnayName)
            .getSingleResult();
    	} catch (Exception e) {
			if(logger.isDebugEnabled())
    			e.printStackTrace();
    		throw new SSCorpDatabaseException(e.getMessage(), e);
		}
    }

    /**
     * 
     * @param em
     * @param companyId
     * @return
     * @throws SSCorpDatabaseException
     */
    @SuppressWarnings("unchecked")
	public List<AchievementsTO> fetchAchievements(EntityManager em,
                	Integer companyId) throws SSCorpDatabaseException {
    	try{
    		return (List<AchievementsTO>)em.createNamedQuery("Achievements.fetchAll")
							            .setParameter("companyId", companyId)
							            .getResultList();
    	} catch (Exception e) {
    		if(logger.isDebugEnabled())
    			e.printStackTrace();
    		throw new SSCorpDatabaseException(e.getMessage(), e);
		}
    }

    /**
     * 
     * @param em
     * @return
     * @throws SSCorpDatabaseException
     */
    @SuppressWarnings("unchecked")
	public List<EmployeeTO> fetchLeadershipInfo(EntityManager em)
                    throws SSCorpDatabaseException {
    	try{
    		return (List<EmployeeTO>) em.createNamedQuery("Employee.fetchAll")
    									.getResultList();
    	} catch (Exception e) {
    		if(logger.isDebugEnabled())
    			e.printStackTrace();
    		throw new SSCorpDatabaseException(e.getMessage(), e);
		}
    }

    /**
     * 
     * @param em
     * @return
     * @throws SSCorpDatabaseException
     */
    public AddressTO fetchCompanyAddress(EntityManager em) 
    						throws SSCorpDatabaseException {
    	try{
    		return (AddressTO)em.createQuery("from AddressTO where addressId = 1")
            					.getSingleResult();
    	} catch (Exception e) {
    		if(logger.isDebugEnabled())
    			e.printStackTrace();
    		throw new SSCorpDatabaseException(e.getMessage(), e);
		}
    }
}