/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.superconduits.core.to.project;

import com.superconduits.core.to.AddressTO;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author: bhaskar
 * @Since: on 20 Nov 2010
 * @version: 1.0
 * @changeLog:
 */
@Entity
@Table(name="sscorp_projects_m")
public class ProjectTO implements Serializable{

    @Id
    @Column(name="project_id", nullable=false, length=11)
    @GeneratedValue
    private Integer projectId;

    @Column(name="project_name", nullable=false, length=200)
    private String projectName;

    @ManyToOne(cascade={CascadeType.REMOVE, CascadeType.REFRESH})
    @JoinColumn(name="project_cat_id", referencedColumnName="project_cat_id")
    private ProjectCategoryTO projectCategoryTO;

    @ManyToOne(cascade={CascadeType.REMOVE, CascadeType.REFRESH})
    @JoinColumn(name="address_id", referencedColumnName="address_id")
    private AddressTO addressTO;

    @Transient
    private String city;

    /**
     * @return the projectId
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return the projectCategoryTO
     */
    public ProjectCategoryTO getProjectCategoryTO() {
        return projectCategoryTO;
    }

    /**
     * @param projectCategoryTO the projectCategoryTO to set
     */
    public void setProjectCategoryTO(ProjectCategoryTO projectCategoryTO) {
        this.projectCategoryTO = projectCategoryTO;
    }

    /**
     * @return the addressTO
     */
    public AddressTO getAddressTO() {
        return addressTO;
    }

    /**
     * @param addressTO the addressTO to set
     */
    public void setAddressTO(AddressTO addressTO) {
        this.addressTO = addressTO;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
}