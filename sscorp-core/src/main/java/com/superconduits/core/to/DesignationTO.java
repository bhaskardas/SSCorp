/*
 * POJO representing the Designations of the employees in the Super Sales
 * Corporation.
 */

package com.superconduits.core.to;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author bhaskar
 * @since 26th Jan 2010
 * @version 1.0
 * @changeLog
 */
@Entity
@Table(name="sscorp_designation_m")
public class DesignationTO implements Serializable{


    @Id
    @GeneratedValue
    @Column(name="designation_id", nullable=false, length=11)
    private Integer designationId;

    @Column(name="designation_name", nullable=false, length=50)
    private String designationName;

    @Column(name="design_level", nullable=false, length=11)
    private Integer designLevel;

    /**
     * @return the designationId
     */
    public Integer getDesignationId() {
        return designationId;
    }

    /**
     * @param designationId the designationId to set
     */
    public void setDesignationId(Integer designationId) {
        this.designationId = designationId;
    }

    /**
     * @return the designationName
     */
    public String getDesignationName() {
        return designationName;
    }

    /**
     * @param designationName the designationName to set
     */
    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    /**
     * @return the designLevel
     */
    public Integer getDesignLevel() {
        return designLevel;
    }

    /**
     * @param designLevel the designLevel to set
     */
    public void setDesignLevel(Integer designLevel) {
        this.designLevel = designLevel;
    }
}
