/*
 * POJO for storing the descriptions of leaders and founder members of
 * Super Sales Corporation.
 */

package com.superconduits.core.to;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author bhaskar
 * @since 26th Jan 2010
 * @version 1.0
 * @changeLog
 */
@Entity
@Table(name="sscorp_leadership_desc_m")
public class LeadershipDescTO implements Serializable{

    @Id
    @GeneratedValue
    @Column(name="leadership_id", nullable=false, length=11)
    private Integer leadershipId;

    @Column(name="leadership_desc", nullable=false)
    private String leadershipDesc;

    @OneToOne
    @JoinColumn(name="employee_id", referencedColumnName="employee_id")
    private EmployeeTO employeeTO;

    /**
     * @return the leadershipId
     */
    public Integer getLeadershipId() {
        return leadershipId;
    }

    /**
     * @param leadershipId the leadershipId to set
     */
    public void setLeadershipId(Integer leadershipId) {
        this.leadershipId = leadershipId;
    }

    /**
     * @return the leadershipDesc
     */
    public String getLeadershipDesc() {
        return leadershipDesc;
    }

    /**
     * @param leadershipDesc the leadershipDesc to set
     */
    public void setLeadershipDesc(String leadershipDesc) {
        this.leadershipDesc = leadershipDesc;
    }

    /**
     * @return the employeeTO
     */
    public EmployeeTO getEmployeeTO() {
        return employeeTO;
    }

    /**
     * @param employeeTO the employeeTO to set
     */
    public void setEmployeeTO(EmployeeTO employeeTO) {
        this.employeeTO = employeeTO;
    }
}
