/*
 * Persistent class representing the company_profile table in
 * the database.
 */

package com.superconduits.core.to;

import com.superconduits.core.to.menu.MenuTO;
import com.superconduits.core.to.product.ProductCategoryTO;
import com.superconduits.core.to.product.ProductEntityTypeTO;
import com.superconduits.core.to.project.ProjectCategoryTO;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author bhaskar
 * @since 4th July 2010
 * @version 1.0
 * @changeLog
 */
@Entity
@Table(name="sscorp_companyProfile_m")
@NamedQueries({
    @NamedQuery(name="CompanyProfileTO.find", query="select cp from " +
                    "CompanyProfileTO cp where cp.companyId = " +
                    ":companyId and cp.companyName = :companyName ")
})
public class CompanyProfileTO implements Serializable{

    @Id
    @GeneratedValue
    @Column(name="company_id", nullable=false, length=11)
    private Integer companyId;

    @Column(name="company_name", nullable=false, length=50)
    private String companyName;

    @Column(name="company_phone", length=20)
    private String companyPhone;

    @Column(name="email_id", length=50)
    private String emailId;

    @Column(name="company_logo_name", length=50, nullable=false)
    private String logoName;

    @Column(name="company_punchLine", length=100)
    private String companyPunchLine;

    @Column(name="about_us", nullable=false, length=10000)
    private String aboutUs;

    @Column(name="fax_number", length=20)
    private String faxNumber;

    @Column(name="achievments_desc", nullable=false, length=10000)
    private String achievmentsDesc;

    @Column(name="mission", nullable=false, length=5000)
    private String mission;

    @Column(name="vision", nullable=false, length=5000)
    private String vision;

    @OneToMany(mappedBy="companyProfileTO", fetch=FetchType.LAZY)
    private Collection<MenuTO> menus;

    @OneToMany(mappedBy="companyProfileTO", fetch=FetchType.LAZY)
    private Collection<ProductCategoryTO> prodCategories;

    @OneToMany(mappedBy="cpto", fetch=FetchType.LAZY)
    private Collection<ProjectCategoryTO> projectCategoryTOs;

    @OneToMany(mappedBy="cpto", fetch=FetchType.LAZY)
    private Collection<ProductEntityTypeTO> prodEntityTypeTOs;

    @OneToMany(mappedBy="companyProfileTO", fetch=FetchType.LAZY)
    private Collection<QueryDetailsTO> queryDetailsTOs;

    /**
     * @return the companyId
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId the companyId to set
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the companyPhone
     */
    public String getCompanyPhone() {
        return companyPhone;
    }

    /**
     * @param companyPhone the companyPhone to set
     */
    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    /**
     * @return the email_id
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param email_id the email_id to set
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return the logoAttachName
     */
    public String getLogoName() {
        return logoName;
    }

    /**
     * @param logoAttachName the logoAttachName to set
     */
    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }

    /**
     * @return the companyPunchLine
     */
    public String getCompanyPunchLine() {
        return companyPunchLine;
    }

    /**
     * @param companyPunchLine the companyPunchLine to set
     */
    public void setCompanyPunchLine(String companyPunchLine) {
        this.companyPunchLine = companyPunchLine;
    }

    /**
     * @return the aboutUs
     */
    public String getAboutUs() {
        return aboutUs;
    }

    /**
     * @param aboutUs the aboutUs to set
     */
    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    /**
     * @return the faxNumber
     */
    public String getFaxNumber() {
        return faxNumber;
    }

    /**
     * @param faxNumber the faxNumber to set
     */
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    /**
     * @return the achievmentsDesc
     */
    public String getAchievmentsDesc() {
        return achievmentsDesc;
    }

    /**
     * @param achievmentsDesc the achievmentsDesc to set
     */
    public void setAchievmentsDesc(String achievmentsDesc) {
        this.achievmentsDesc = achievmentsDesc;
    }

    /**
     * @return the menus
     */
    public Collection<MenuTO> getMenus() {
        return menus;
    }

    /**
     * @param menus the menus to set
     */
    public void setMenus(Collection<MenuTO> menus) {
        this.menus = menus;
    }

    /**
     * @return the prodCatTOs
     */
    public Collection<ProductCategoryTO> getProdCategories() {
        return prodCategories;
    }

    /**
     * @param prodCatTOs the prodCatTOs to set
     */
    public void setProdCategories(Collection<ProductCategoryTO> prodCategories) {
        this.prodCategories = prodCategories;
    }

    /**
     * @return the projectCategoryTOs
     */
    public Collection<ProjectCategoryTO> getProjectCategoryTOs() {
        return projectCategoryTOs;
    }

    /**
     * @param projectCategoryTOs the projectCategoryTOs to set
     */
    public void setProjectCategoryTOs(Collection<ProjectCategoryTO> projectCategoryTOs) {
        this.projectCategoryTOs = projectCategoryTOs;
    }

    /**
     * @return the prodEntityTypeTOs
     */
    public Collection<ProductEntityTypeTO> getProdEntityTypeTOs() {
        return prodEntityTypeTOs;
    }

    /**
     * @param prodEntityTypeTOs the prodEntityTypeTOs to set
     */
    public void setProdEntityTypeTOs(Collection<ProductEntityTypeTO> prodEntityTypeTOs) {
        this.prodEntityTypeTOs = prodEntityTypeTOs;
    }

    /**
     * @return the mission
     */
    public String getMission() {
        return mission;
    }

    /**
     * @param mission the mission to set
     */
    public void setMission(String mission) {
        this.mission = mission;
    }

    /**
     * @return the vision
     */
    public String getVision() {
        return vision;
    }

    /**
     * @param vision the vision to set
     */
    public void setVision(String vision) {
        this.vision = vision;
    }

    /**
     * @return the queryDetailsTOs
     */
    public Collection<QueryDetailsTO> getQueryDetailsTOs() {
        return queryDetailsTOs;
    }

    /**
     * @param queryDetailsTOs the queryDetailsTOs to set
     */
    public void setQueryDetailsTOs(Collection<QueryDetailsTO> queryDetailsTOs) {
        this.queryDetailsTOs = queryDetailsTOs;
    }
}
