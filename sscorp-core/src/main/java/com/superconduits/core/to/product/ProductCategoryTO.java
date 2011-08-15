/*
 * This class represents the product categories for super sales.
 * It serves as the main Data Transfer Object for information related to
 * product categories.
 */

package com.superconduits.core.to.product;

import com.superconduits.core.to.CompanyProfileTO;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author: bhaskar
 * @Since: on 17 Sep 2010
 * @version: 1.0
 * @changeLog:
 */
@Entity
@Table(name="sscorp_products_category_m")
@NamedQueries({
    @NamedQuery(name="ProdCategoryTO.fetchAll", query="SELECT pc from " +
                "ProductCategoryTO pc where pc.companyProfileTO.companyId = " +
                ":companyId")
})
public class ProductCategoryTO implements Serializable{

    @Id
    @Column(name="product_cat_id", length=11)
    @GeneratedValue
    private Integer productCatId;

    @Column(name="product_cat_name", nullable=false, length=200)
    private String productCatName;

    @Column(name="prod_cat_photo_attachName", nullable=true, length=100)
    private String prodCategoryImage;

    @Column(name="prod_cat_desc")
    private String prodCategoryDesc;

    @ManyToOne(cascade={CascadeType.REMOVE, CascadeType.REFRESH})
    @JoinColumn(name="company_id", referencedColumnName="company_id")
    private CompanyProfileTO companyProfileTO;

    /**
     * @return the productCatId
     */
    public Integer getProductCatId() {
        return productCatId;
    }

    /**
     * @param productCatId the productCatId to set
     */
    public void setProductCatId(Integer productCatId) {
        this.productCatId = productCatId;
    }

    /**
     * @return the productCatName
     */
    public String getProductCatName() {
        return productCatName;
    }

    /**
     * @param productCatName the productCatName to set
     */
    public void setProductCatName(String productCatName) {
        this.productCatName = productCatName;
    }

    /**
     * @return the prodCategoryImage
     */
    public String getProdCategoryImage() {
        return prodCategoryImage;
    }

    /**
     * @param prodCategoryImage the prodCategoryImage to set
     */
    public void setProdCategoryImage(String prodCategoryImage) {
        this.prodCategoryImage = prodCategoryImage;
    }

    /**
     * @return the companyProfileTO
     */
    public CompanyProfileTO getCompanyProfileTO() {
        return companyProfileTO;
    }

    /**
     * @param companyProfileTO the companyProfileTO to set
     */
    public void setCompanyProfileTO(CompanyProfileTO companyProfileTO) {
        this.companyProfileTO = companyProfileTO;
    }

    /**
     * @return the prodCategoryDesc
     */
    public String getProdCategoryDesc() {
        return prodCategoryDesc;
    }

    /**
     * @param prodCategoryDesc the prodCategoryDesc to set
     */
    public void setProdCategoryDesc(String prodCategoryDesc) {
        this.prodCategoryDesc = prodCategoryDesc;
    }
}
