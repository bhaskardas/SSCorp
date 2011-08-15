/*
 * This is the DAO class for loading the company profile data and passing it
 * onto the business layer.
 */

package com.superconduits.core.dbaccess;

import com.superconduits.core.to.AchievementsTO;
import com.superconduits.core.to.AddressTO;
import com.superconduits.core.to.CompanyProfileTO;
import com.superconduits.core.to.EmployeeTO;
import java.util.List;
import javax.persistence.EntityManager;
import org.utilities.util.PropertiesUtil;

/**
 * @author bhaskar
 * @since 25th Aug 2010
 * @changeLog 17th Oct 2010
 * @version 1.0, 1.1
 */
public class CompanyProfileDAO {

    /**
     *
     * @param em
     * @return
     * @throws Exception
     */
    public CompanyProfileTO fetch(EntityManager em) throws Exception{
        return (CompanyProfileTO)em
                .createNamedQuery("CompanyProfileTO.find")
                .setParameter("companyId",
                            Integer.parseInt(
                            PropertiesUtil.readProperty(
                            "sscorp-config", "sscorp.companyId")))
                .setParameter("companyName",
                            PropertiesUtil.readProperty(
                            "sscorp-config", "sscorp.companyName"))
                .getSingleResult();
    }

    /**
     *
     * @param em
     * @param companyId
     * @return
     * @throws Exception
     */
    public List<AchievementsTO> fetchAchievements(EntityManager em,
                                        Integer companyId) throws Exception {
        return em.createNamedQuery("Achievements.fetchAll")
                    .setParameter("companyId", companyId)
                    .getResultList();
    }

    /**
     * 
     * @param em
     * @return
     * @throws Exception
     */
    public List<EmployeeTO> fetchLeadershipInfo(EntityManager em)
                                                        throws Exception {
        return em.createNamedQuery("Employee.fetchAll").getResultList();
    }

    public AddressTO fetchCompanyAddress(EntityManager em){
        return (AddressTO)em.createQuery("from AddressTO where addressId = 1")
                            .getSingleResult();
        
    }
}