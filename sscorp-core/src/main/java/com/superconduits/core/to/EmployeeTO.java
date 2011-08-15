/*
 * POJO representing the records of Employees in the Super Sales Corporation.
 */

package com.superconduits.core.to;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author bhaskar
 * @since 26th Jan 2010
 * @version 1.0
 * @changeLog
 */
@Entity
@Table(name="sscorp_employee_m")
@NamedQueries(
    @NamedQuery(name="Employee.fetchAll",
                query="from " +
                    " EmployeeTO e " +
                    " where e.designationTO.designationId in (1, 2) "
    )
)
public class EmployeeTO implements Serializable{

    @Id
    @GeneratedValue
    @Column(name="employee_id", nullable=false, length=11)
    private Integer employeeId;

    @Column(name="first_name", nullable=false, length=100)
    private String firstName;

    @Column(name="middle_name", length=100)
    private String middleName;

    @Column(name="last_name", length=100)
    private String lastName;

    @Column(name="status", nullable=false)
    private Character status;

    @ManyToOne
    @JoinColumn(name="company_id", referencedColumnName="company_id")
    private CompanyProfileTO companyProfileTO;

    @ManyToOne
    @JoinColumn(name="designation_id", referencedColumnName="designation_id")
    private DesignationTO designationTO;

    @OneToOne
    @JoinColumn(name="employee_id", referencedColumnName="employee_id")
    private LeadershipDescTO ldto;

    /**
     * @return the employeeId
     */
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the status
     */
    public Character getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Character status) {
        this.status = status;
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
     * @return the designationTO
     */
    public DesignationTO getDesignationTO() {
        return designationTO;
    }

    /**
     * @param designationTO the designationTO to set
     */
    public void setDesignationTO(DesignationTO designationTO) {
        this.designationTO = designationTO;
    }

    /**
     * @return the ldto
     */
    public LeadershipDescTO getLdto() {
        return ldto;
    }

    /**
     * @param ldto the ldto to set
     */
    public void setLdto(LeadershipDescTO ldto) {
        this.ldto = ldto;
    }
}
