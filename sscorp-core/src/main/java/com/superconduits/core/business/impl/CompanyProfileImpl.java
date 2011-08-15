/*
 * This class is responsisble for loading the company profile object into the
 * super sales web application.
 */

package com.superconduits.core.business.impl;

import com.superconduits.core.business.interfaces.ICompanyProfile;
import com.superconduits.core.dbaccess.CompanyProfileDAO;
import com.superconduits.core.to.AchievementsTO;
import com.superconduits.core.to.AddressTO;
import com.superconduits.core.to.CompanyProfileTO;
import com.superconduits.core.to.EmployeeTO;
import com.superconduits.core.util.SSCorpUtil;
import com.superconduits.core.util.persistence.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import org.utilities.util.tuplelibrary.FourTuple;

/**
 * @author bhaskar
 * @since 25th Aug 2010
 * @changeLog 17th Oct 2010, 27 Feb 2011
 * @version 1.0, 1.1, 1.2
 */
public class CompanyProfileImpl implements ICompanyProfile{
    @Override
    public FourTuple fetchCompanyProfileInfo(Integer companyId)
                                                        throws Exception{
        EntityManager em = null;
            

        //TODO - Catch a custom Exception - ObjectCreationException
        CompanyProfileDAO companyProfileDAO = (CompanyProfileDAO)SSCorpUtil
                                            .getObject("companyProfileDAO");
        
        try{
            em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
            
            //fetch company profile
            CompanyProfileTO companyProfileTO = companyProfileDAO.fetch(em);

            //fetch top management information for leadership jsp page.
            List<EmployeeTO> employeeTOs = companyProfileDAO.fetchLeadershipInfo(em);

            //fetch company achievements
            List<AchievementsTO> achievementsTOs = companyProfileDAO
                                            .fetchAchievements(em, companyId);

            //fetch company address information for the contact us jsp page.
            AddressTO addressTO = companyProfileDAO.fetchCompanyAddress(em);

            FourTuple fourTuple = new FourTuple(companyProfileTO, employeeTOs,
                                                    achievementsTOs, addressTO);
            return fourTuple;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }

        return null;
    }
}