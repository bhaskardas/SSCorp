/*
 * This is the main interface in the service/layer that interacts with the
 * front end layer. This class handles all requests related to the display and
 * manipulation of products in Super Sales Corporation
 */

package com.superconduits.core.business.interfaces;

import com.superconduits.core.to.product.ProductCategoryTO;
import java.util.List;

/**
 * @author bhaskar
 * @Created on 17th Sep 2010
 * @Version : 1.0
 * @ChangeLog : 
 */
public interface IProducts {

    /**
     * Business function which feeds the front end application with the
     * product category information to be displayed on a JSP or some desktop
     * application.
     */
    public List<ProductCategoryTO> fetchProductCategories(Integer companyId);

    /**
     * Fetch all the products and their features in the super sales corporation.
     * @param companyId
     * @return
     */
    public List<Object[]> fetchAllProducts();
}
