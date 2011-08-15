/*
 * POJO for holding the master data related to products in the super sales
 * corporation.
 */

package com.superconduits.core.to.product;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author bhaskar
 * @created on 12 Jan 2010
 * @version 1.0
 * @changeLog
 */
@Entity
@Table(name="sscorp_product_features_m")
public class ProductFeaturesTO implements Serializable{

    @Id()
    @Column(name = "prod_feature_id", nullable = false, length = 11)
    private Integer prodFeatureId;

    @Column(name = "name", nullable = false, length = 200)
    private String prodFeatureName;

    @ManyToOne
    @JoinColumn(name = "feature_type", referencedColumnName = "entity_type_id")
    private ProductEntityTypeTO entityTypeTO;

    /**
     * @return the prodFeatureId
     */
    public Integer getProdFeatureId() {
        return prodFeatureId;
    }

    /**
     * @param prodFeatureId the prodFeatureId to set
     */
    public void setProdFeatureId(Integer prodFeatureId) {
        this.prodFeatureId = prodFeatureId;
    }

    /**
     * @return the prodFeatureName
     */
    public String getProdFeatureName() {
        return prodFeatureName;
    }

    /**
     * @param prodFeatureName the prodFeatureName to set
     */
    public void setProdFeatureName(String prodFeatureName) {
        this.prodFeatureName = prodFeatureName;
    }

    /**
     * @return the entityTypeTO
     */
    public ProductEntityTypeTO getEntityTypeTO() {
        return entityTypeTO;
    }

    /**
     * @param entityTypeTO the entityTypeTO to set
     */
    public void setEntityTypeTO(ProductEntityTypeTO entityTypeTO) {
        this.entityTypeTO = entityTypeTO;
    }
}
