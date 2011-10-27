/**
 * Copyright Super Sales Corporation 2009,  
 * All rights reserved.
 */

package com.superconduits.core.business.interfaces;

import java.util.List;

import org.utilities.util.tuplelibrary.FourTuple;

import com.superconduits.core.exceptions.SSCorpBusinessException;
import com.superconduits.core.to.AchievementsTO;
import com.superconduits.core.to.AddressTO;
import com.superconduits.core.to.CompanyProfileTO;
import com.superconduits.core.to.EmployeeTO;

/**
 * This is the main service layer interface which 
 * provides the controller with the complete 
 * information of the company profile and achievements.
 * 
 * @author bhaskar.das
 * @since 25th Aug 2010
 * @version 1.0, 1.1, 1.2
 * @changeLog:
 * 	as on 17 Oct, 2010 - By bhaskar.das,
 * 	as on 02 Oct, 2011 - By bhaskar.das
 * 						1. Modified the return type to contain
 * 						specific parameters using generics.
 * 						2. Added SSCOrpBusinessException that the
 * 						method will throw.
 * 	as on 10 Oct, 2011 - By bhaskar.das
 * 						1. Removed the parameters from the function.
 * 						Now calculating the parameter values in the
 * 						function itself.
 */
public interface ICompanyProfile {

    /**
     * Fetches the company profile information,
     * the list of employees in the organisation
     * (those who are in the top management only),
     * company's achievements and address.
     * @return
     * 		a custom object containing all the
     * 		necessary information mentioned above.
     * @throws SSCorpBusinessException
     * 			This function reads certain properties
     * 			from properties files for communicating
     * 			with the database. If cannot successfully
     * 			fetch the values from those files, Properties
     * 			related exception is thrown, wrapping it into
     * 			SSCorpBusinessException type exception.
     * 			It also this exception if there is any error
     * 			in the database call.	
     */
    public FourTuple<CompanyProfileTO, List<EmployeeTO>, List<AchievementsTO>, AddressTO> 
    		fetchCompanyProfileInfo() throws SSCorpBusinessException;
}