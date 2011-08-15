/*
 * This custom class loads the product information of super sales coeporation
 * into the application memory at server startup.
 */

package com.superconduits.web.plugin;

import com.superconduits.core.business.interfaces.IProducts;
import com.superconduits.core.to.CompanyProfileTO;
import com.superconduits.core.to.product.ProductCategoryTO;
import com.superconduits.core.util.SSCorpUtil;
import com.superconduits.web.formbean.ProductForm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.utilities.util.PropertiesUtil;

/**
 * @author bhaskar
 * @Created on 03rd Jan 2011
 * @Version : 1.0
 * @ChangeLog :
 */
public class ProductLoader implements PlugIn{

    private final String key = "product";
    private ServletContext sc;
    private static Logger logger = Logger.getLogger(ProductLoader.class);
    private String[] prodCatImagePath;
    private String[] prodImagePath;

    /**
     * Map which holds the list of products grouped by the product categories.
     * The key is the product categories.
     */
    private Map<Integer, List<ProductForm>> allProducts =
                        new HashMap<Integer, List<ProductForm>>();

    /**
     * List for holding all the product objects for a single product category.
     */
    private List<ProductForm> listOfProducts;

    /**
     * List for holding the relative path names of the images for a product.
     */
    private List<String> productImages;

    /**
     * Main product object for holding all the information regarding a product.
     */
    private ProductForm productForm;
    
    @Override
    public void init(ActionServlet as, ModuleConfig mc) throws ServletException {
        sc = as.getServletContext();

        logger.info("in Product Loader init() function");
        logger.info("starting with the process of fetching all the " +
                    "product details");

        Integer companyId = ((CompanyProfileTO)sc.getAttribute("companyProfile"))
                                                .getCompanyId();

        logger.info("create a new isntance of the product impl object");
        //Create a new instance of the product impl business layer class.
        IProducts iProducts = (IProducts)SSCorpUtil.getObject(key);

        logger.info("fetch the list of all the product categories in" +
                    " the super sales corporation.");
        //get the list of product categories.
        List<ProductCategoryTO> pCatList = iProducts
                                            .fetchProductCategories(companyId);

        //set the list of product categories in the application scope.
        if(pCatList != null && pCatList.size() > 0){
            //TODO: custom exception
            try{
                setImagePath4ProdCat(pCatList);
            }catch(Exception e){
                e.printStackTrace();
            }
            sc.setAttribute("prodCategories", pCatList);
        }

        logger.info("fetch the list of all the product based on their " +
                    "categories in the super sales corporation.");
        //get the list of all the products on the basis of their product
        //categories.
        List<Object[]> allProductList = iProducts.fetchAllProducts();

        //set the product list by categories in the application scope.
        if(allProductList != null && allProductList.size() > 0){
            //TODO: custom exception
            try{
                prepareProductList(allProductList);
            }catch(Exception e){
                e.printStackTrace();
            }
            sc.setAttribute("productListByCat", allProducts);
        }
    }

    /**
     * prepare the list of products according to the product categories.
     * @param list
     */
    private void prepareProductList(List<Object[]> list)
                                                    throws Exception{
        Integer productCatId = -1;
        Integer productId = -1;

        prodImagePath = PropertiesUtil.readProperty("sscorp-config",
                                               "productImagePath").split(",,");

        for(Object[] objArr: list){
            if(productId == -1 ||
                    productId != Integer.parseInt(objArr[2].toString())){

                productId = Integer.parseInt(objArr[2].toString());
                if(productCatId == -1 ||
                        productCatId != Integer.parseInt(objArr[3].toString())){
                    productCatId = Integer.parseInt(objArr[3].toString());
                    listOfProducts = new ArrayList<ProductForm>();
                    allProducts.put(productCatId, listOfProducts);
                }

                productForm = new ProductForm();
                productForm.setProductCatId(objArr[3].toString());
                productForm.setProductId(objArr[2].toString());
                productImages = new ArrayList<String>();
                listOfProducts.add(productForm);
            }
            prepareProductObj(objArr[0].toString(),
                        Integer.parseInt(objArr[1].toString()));
        }
    }

    /**
     * Prepare and populate the individual product objects.
     * @param feature
     * @param featureType
     */
    private void prepareProductObj(String feature, Integer featureType)
                                                    throws Exception{
        switch(featureType){
            case 1:
                if(productForm.getSize() == null){
                    productForm.setSize(feature);
                }else{
                    productForm.setSize(productForm.getSize() + ", " + feature);
                }
                break;
            case 2:
                if(productForm.getMaterial() == null){
                    productForm.setMaterial(feature);
                }else{
                    productForm.setMaterial(productForm.getMaterial() + ", " +
                                            feature);
                }
                break;
            case 3:
                if(productForm.getFinish() == null){
                    productForm.setFinish(feature);
                }else{
                    productForm.setFinish(productForm.getFinish() + ", " +
                                            feature);
                }
                break;
            case 6:
                if(productForm.getWay() == null){
                    productForm.setWay(feature);
                }else{
                    productForm.setWay(productForm.getWay() + ", " + feature);
                }
            case 7:
                if(productForm.getSurfaceDiameter() == null){
                    productForm.setSurfaceDiameter(feature);
                }else{
                    productForm.setSurfaceDiameter(
                            productForm.getSurfaceDiameter() + ", " + feature);
                }
                break;
            case 8:
                if(productForm.getLength() == null){
                    productForm.setLength(feature);
                }else{
                    productForm.setLength(productForm.getLength() + ", " +
                                            feature);
                }
                break;
            case 9:
                if(productForm.getThickness() == null){
                    productForm.setThickness(feature);
                }else{
                    productForm.setThickness(productForm.getThickness() + ", " +
                                                feature);
                }
                break;
            case 10:
                productForm.setProductName(feature);
                break;
            case 11:
                productImages.add(SSCorpUtil.setImagePath(prodImagePath,
                                                            feature));
                productForm.setPhotoName(productImages);
                break;
            case 12:
                if(productForm.getGauge() == null){
                    productForm.setGauge(feature);
                }else{
                    productForm.setGauge(productForm.getGauge() + ", " + feature);
                }
                break;
        }
    }

    /**
     * Set the relative paths for the product category images to be displayed.
     * @param pCatList list of product category objects.
     */
    private void setImagePath4ProdCat(List<ProductCategoryTO> pCatList)
                                                        throws Exception{
        prodCatImagePath = PropertiesUtil.readProperty("sscorp-config", 
                                            "productCatImagePath").split(",,");
        for(ProductCategoryTO pcto: pCatList){
            if(pcto != null){
                pcto.setProdCategoryImage(SSCorpUtil.setImagePath(
                                prodCatImagePath, pcto.getProdCategoryImage()));
            }
        }
    }

    @Override
    public void destroy() {}
}
