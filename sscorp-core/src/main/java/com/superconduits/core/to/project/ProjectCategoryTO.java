/*
 * POJO representing a project category from the database table.
 */

package com.superconduits.core.to.project;

import com.superconduits.core.to.CompanyProfileTO;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author: bhaskar
 * @Since: on 20 Nov 2010
 * @version: 1.0
 * @changeLog:
 */
@Entity
@Table(name="sscorp_project_category_m")
@NamedQueries({
    @NamedQuery(name="ProjectCategory.fetchAll",
                query="select pct from ProjectCategoryTO pct " +
                        " where pct.cpto.companyId = :companyId"
    )
})
public class ProjectCategoryTO implements Serializable {

    @Id
    @Column(name="project_cat_id", nullable=false, length=11)
    @GeneratedValue
    private Integer projectCatId;

    @Column(name="project_category", length=200, nullable=false)
    private String projectCatName;

    @Column(name="project_desc", length=1000)
    private String projectDesc;

    @Column(name="project_image", length=50)
    private String projectImage;

    @ManyToOne(cascade={CascadeType.REMOVE, CascadeType.REFRESH})
    @JoinColumn(name="company_id", referencedColumnName="company_id")
    private CompanyProfileTO cpto;

    @OneToMany(mappedBy="projectCategoryTO", fetch=FetchType.EAGER)
    private Collection<ProjectTO> projectTOs;

    /**
     * @return the projectCatID
     */
    public Integer getProjectCatId() {
        return projectCatId;
    }

    /**
     * @param projectCatID the projectCatID to set
     */
    public void setProjectCatId(Integer projectCatId) {
        this.projectCatId = projectCatId;
    }

    /**
     * @return the projectCatName
     */
    public String getProjectCatName() {
        return projectCatName;
    }

    /**
     * @param projectCatName the projectCatName to set
     */
    public void setProjectCatName(String projectCatName) {
        this.projectCatName = projectCatName;
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

    /**
     * @return the projectTOs
     */
    public Collection<ProjectTO> getProjectTOs() {
        return projectTOs;
    }

    /**
     * @param projectTOs the projectTOs to set
     */
    public void setProjectTOs(Collection<ProjectTO> projectTOs) {
        this.projectTOs = projectTOs;
    }

    /**
     * @return the projectDesc
     */
    public String getProjectDesc() {
        return projectDesc;
    }

    /**
     * @param projectDesc the projectDesc to set
     */
    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    /**
     * @return the projectImage
     */
    public String getProjectImage() {
        return projectImage;
    }

    /**
     * @param projectImage the projectImage to set
     */
    public void setProjectImage(String projectImage) {
        this.projectImage = projectImage;
    }
}
