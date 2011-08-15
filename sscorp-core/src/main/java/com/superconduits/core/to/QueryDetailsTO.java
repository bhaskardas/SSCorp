/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superconduits.core.to;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author bhaskar
 */
@Entity
@Table(name = "sscorp_queryDetails_t")
public class QueryDetailsTO implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "query_id", nullable = false, length = 11)
    private Integer queryId;

    @Column(name = "query", nullable = false, length = 3000)
    private String query;

    @Column(name = "query_subject", nullable = false, length = 200)
    private String querySubject;

    @Column(name = "query_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date queryDate;

    @Column(name = "hear_about_us", length = 500)
    private String hearAboutUs;

    @Column(name = "company_name", length = 50)
    private String companyName;

    @Column(name = "company_website", length = 50)
    private String companyWebsite;

    @Column(name = "email_id", nullable = false, length = 200)
    private String emailId;

    @Column(name = "full_name", nullable = false, length = 200)
    private String fullName;

    @Column(name = "phone_no", length = 100)
    private String phoneNo;

    @ManyToOne(cascade={CascadeType.REMOVE, CascadeType.REFRESH})
    @JoinColumn(name="company_id", referencedColumnName="company_id")
    private CompanyProfileTO companyProfileTO;

    /**
     * @return the queryId
     */
    public Integer getQueryId() {
        return queryId;
    }

    /**
     * @param queryId the queryId to set
     */
    public void setQueryId(Integer queryId) {
        this.queryId = queryId;
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * @return the querySubject
     */
    public String getQuerySubject() {
        return querySubject;
    }

    /**
     * @param querySubject the querySubject to set
     */
    public void setQuerySubject(String querySubject) {
        this.querySubject = querySubject;
    }

    /**
     * @return the queryDate
     */
    public Date getQueryDate() {
        return queryDate;
    }

    /**
     * @param queryDate the queryDate to set
     */
    public void setQueryDate(Date queryDate) {
        this.queryDate = queryDate;
    }

    /**
     * @return the hearAboutUs
     */
    public String getHearAboutUs() {
        return hearAboutUs;
    }

    /**
     * @param hearAboutUs the hearAboutUs to set
     */
    public void setHearAboutUs(String hearAboutUs) {
        this.hearAboutUs = hearAboutUs;
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
     * @return the companyWebsite
     */
    public String getCompanyWebsite() {
        return companyWebsite;
    }

    /**
     * @param companyWebsite the companyWebsite to set
     */
    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    /**
     * @return the emailId
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId the emailId to set
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the phoneNo
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * @param phoneNo the phoneNo to set
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
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
}
