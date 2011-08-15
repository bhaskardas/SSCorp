/*
 * This class is responsible for fetching and updating all the product
 * related information of super sales corporation. It is used to directly
 * interact with the database and fetch or save the required information.
 */

package com.superconduits.core.dbaccess;

import com.superconduits.core.to.product.ProductCategoryTO;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author bhaskar
 * @created on 17 Sep 2010
 * @version 1.0
 * @changeLog
 */
public class ProductDAO {

    /**
     *
     * @param em
     * @param id
     * @return
     * @throws Exception
     */
    public List<ProductCategoryTO> fetch(EntityManager em, Integer id)
                                                            throws Exception {

        return (List<ProductCategoryTO>)
                            em.createNamedQuery("ProdCategoryTO.fetchAll")
                            .setParameter("companyId", id)
                            .getResultList();
    }

    /**
     * 
     * @param em
     * @return
     * @throws Exception
     */
    public List<Object[]> fetch(EntityManager em) throws Exception {
        return em.createNamedQuery("ProdMapping.fetchAllProducts")
                .getResultList();
    }


}
