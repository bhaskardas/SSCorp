/*
 * This is main class for displaying the products and its categories.
 */

package com.superconduits.core.business.impl;

import com.superconduits.core.business.interfaces.IProducts;
import com.superconduits.core.dbaccess.ProductDAO;
import com.superconduits.core.to.product.ProductCategoryTO;
import com.superconduits.core.util.SSCorpUtil;
import com.superconduits.core.util.persistence.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;

/**
 *
 * @author bhaskar
 * @created on 17 Sep 2010
 * @version 1.0, 1.1
 * @changeLog: removed function fetchProductsByCategory().
 */
public class ProductsImpl implements IProducts{

    private static Logger logger = Logger.getLogger(ProductsImpl.class);

    @Override
    public List<ProductCategoryTO> fetchProductCategories(Integer companyId) {
        EntityManager em = null;
        em = PersistenceUtil.getEntityManagerFactory().createEntityManager();

        //TODO: Object creation exception
        ProductDAO productDAO = (ProductDAO)SSCorpUtil.getObject("productDAO");

        try{
            logger.info("fetching categories of product on the basis of" +
                        " companyId = " + companyId);
            return productDAO.fetch(em, companyId);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }

        return null;
    }

    @Override
    public List<Object[]> fetchAllProducts() {
        EntityManager em = null;
        em = PersistenceUtil.getEntityManagerFactory().createEntityManager();

        //TODO: Object creation exception
        ProductDAO productDAO = (ProductDAO)SSCorpUtil.getObject("productDAO");

        try{
            logger.info("fetching all products.");
            return productDAO.fetch(em);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }

        return null;
    }
}
