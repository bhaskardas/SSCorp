/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.superconduits.core.business.interfaces;

import com.superconduits.core.exceptions.SSCorpBusinessException;
import com.superconduits.core.to.QueryDetailsTO;

/**
 *
 * @author bhaskar
 */
public interface IEnquiry {

    void saveEnquiry(QueryDetailsTO queryDetailsTO) throws SSCorpBusinessException;
}
