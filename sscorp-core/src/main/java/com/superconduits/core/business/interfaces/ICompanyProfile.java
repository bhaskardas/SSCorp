/*
 * This is the main service layer interface which provides the front end layer
 * with the complete information of the company profile and achievements.
 */

package com.superconduits.core.business.interfaces;

import org.utilities.util.tuplelibrary.FourTuple;

/**
 * @author bhaskar
 * @since 25th Aug 2010
 * @changeLog 17th Oct 2010
 * @version 1.0, 1.1
 */
public interface ICompanyProfile {

    /**
     * Fetches the company profile.
     * @return
     * @throws Exception
     */
    public FourTuple fetchCompanyProfileInfo(Integer companyId)
                                                            throws Exception;
}