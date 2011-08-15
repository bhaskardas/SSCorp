/*
 * POJO for holding the various entity types or (features) possible for a
 * product in the super sales corporation.
 */

package com.superconduits.core.to.product;

import com.superconduits.core.to.CompanyProfileTO;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author bhaskar
 * @created on 12 Jan 2010
 * @version 1.0
 * @changeLog
 */
@Entity
@Table(name="sscorp_prod_entity_types_m")
public class ProductEntityTypeTO implements Serializable{

    @Id
    @Column(name="entity_type_id", nullable=false, length=11)
    @GeneratedValue
    private Integer entityTypeId;
    
    @Column(name="entity_type_name", nullable=false, length=100)
    private String entityTypeName;

    @Column(name="entity_abbr", nullable=false, length=4)
    private String entityAbbr;

    @ManyToOne
    @JoinColumn(name="company_id", referencedColumnName="company_id")
    private CompanyProfileTO cpto;

    /**
     * @return the entityTypeId
     */
    public Integer getEntityTypeId() {
        return entityTypeId;
    }

    /**
     * @param entityTypeId the entityTypeId to set
     */
    public void setEntityTypeId(Integer entityTypeId) {
        this.entityTypeId = entityTypeId;
    }

    /**
     * @return the entityTypeName
     */
    public String getEntityTypeName() {
        return entityTypeName;
    }

    /**
     * @param entityTypeName the entityTypeName to set
     */
    public void setEntityTypeName(String entityTypeName) {
        this.entityTypeName = entityTypeName;
    }

    /**
     * @return the entityAbbr
     */
    public String getEntityAbbr() {
        return entityAbbr;
    }

    /**
     * @param entityAbbr the entityAbbr to set
     */
    public void setEntityAbbr(String entityAbbr) {
        this.entityAbbr = entityAbbr;
    }

    /**
     * @return the cpto
     */
    public CompanyProfileTO getCpto() {
        return cpto;
    }

    /**
     * @param cpto the cpto to set
     */
    public void setCpto(CompanyProfileTO cpto) {
        this.cpto = cpto;
    }
}
