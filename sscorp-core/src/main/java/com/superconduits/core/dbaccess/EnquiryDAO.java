/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.superconduits.core.dbaccess;

import com.superconduits.core.exceptions.SSCorpDatabaseException;
import com.superconduits.core.to.QueryDetailsTO;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;

/**
 *
 * @author bhaskar
 */
public class EnquiryDAO {

    private Logger logger = Logger.getLogger(EnquiryDAO.class);
    /**
     * 
     * @param queryDetailsTO
     */
    public void insertNewQuery(EntityManager em, QueryDetailsTO queryDetailsTO)
                throws SSCorpDatabaseException{
        try{
            em.persist(queryDetailsTO);
            logger.info("query logged successfully.");
        }catch(Exception ex){
            if(logger.isDebugEnabled())
                ex.printStackTrace();
            throw new SSCorpDatabaseException(ex.getMessage());
        }
    }
}
