/*
 * POJO which holds the mapping for the products and its various features in the
 * super sales corporation.
 */

package com.superconduits.core.to.product;

import java.io.Serializable;
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
 * @author bhaskar
 * @created on 12 Jan 2010
 * @version 1.0
 * @changeLog
 */
@Entity
@Table(name="sscorp_prod_feature_map_m")
@NamedQueries(
    @NamedQuery(name="ProdMapping.fetchAllProducts",
                query=" select pf.prodFeatureName, " +
                    " pf.entityTypeTO.entityTypeId, " +
                    " pfm.prodFeatrueParent.prodFeatureId, " +
                    " pfm.pcto.productCatId " +
                    " from " +
                    " ProductFeatureMappingTO pfm, " +
                    " ProductFeaturesTO pf " +
                    " where " +
                    " pfm.pcto.productCatId in " +
                    " ( " +
                    " select pc.productCatId " +
                    " from " +
                    " ProductCategoryTO pc " +
                    " ) " +
                    " and pfm.productFeatures.prodFeatureId = pf.prodFeatureId " +
                    " order by pfm.pcto.productCatId, " +
                    " pfm.prodFeatrueParent.prodFeatureId"
    )
)
public class ProductFeatureMappingTO implements Serializable{

    @Id()
    @Column(name = "prod_mapping_id", nullable = false, length = 11)
    @GeneratedValue
    private Integer prodMappingId;

    @ManyToOne
    @JoinColumn(name = "member_entity_id", referencedColumnName = "prod_feature_id")
    private ProductFeaturesTO productFeatures;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "prod_feature_id")
    private ProductFeaturesTO prodFeatrueParent;

    @ManyToOne
    @JoinColumn(name = "product_cat_id", referencedColumnName = "product_cat_id")
    private ProductCategoryTO pcto;

    /**
     * @return the prodMappingId
     */
    public Integer getProdMappingId() {
        return prodMappingId;
    }

    /**
     * @param prodMappingId the prodMappingId to set
     */
    public void setProdMappingId(Integer prodMappingId) {
        this.prodMappingId = prodMappingId;
    }

    /**
     * @return the productFeatures
     */
    public ProductFeaturesTO getProductFeatures() {
        return productFeatures;
    }

    /**
     * @param productFeatures the productFeatures to set
     */
    public void setProductFeatures(ProductFeaturesTO productFeatures) {
        this.productFeatures = productFeatures;
    }

    /**
     * @return the prodFeatrueParent
     */
    public ProductFeaturesTO getProdFeatrueParent() {
        return prodFeatrueParent;
    }

    /**
     * @param prodFeatrueParent the prodFeatrueParent to set
     */
    public void setProdFeatrueParent(ProductFeaturesTO prodFeatrueParent) {
        this.prodFeatrueParent = prodFeatrueParent;
    }

    /**
     * @return the pcto
     */
    public ProductCategoryTO getPcto() {
        return pcto;
    }

    /**
     * @param pcto the pcto to set
     */
    public void setPcto(ProductCategoryTO pcto) {
        this.pcto = pcto;
    }
}
