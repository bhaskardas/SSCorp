/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.superconduits.core.to;

import java.io.Serializable;
import java.util.Collection;
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
 * @author bhaskar
 * @since 26th Jan 2010
 * @version 1.0
 * @changeLog
 */
@Entity
@Table(name="sscorp_achievments_m")
@NamedQueries(
    @NamedQuery(name="Achievements.fetchAll",
                query="from AchievementsTO a" +
                        " where a.profileTO.companyId = :companyId " +
                        " and a.achievmentId = a.achievementsTO " +
                        " order by a.achievementsTO, a.achievmentId"
    )
)
public class AchievementsTO implements Serializable{

    @Id
    @GeneratedValue
    @Column(name="achievment_id", nullable=false, length=11)
    private Integer achievmentId;

    @Column(name="achievement", nullable=false, length=100)
    private String achievementName;

    @ManyToOne
    @JoinColumn(name="parent_id", referencedColumnName="achievment_id")
    private AchievementsTO achievementsTO;

    @ManyToOne
    @JoinColumn(name="company_id", referencedColumnName="company_id")
    private CompanyProfileTO profileTO;

    @OneToMany(mappedBy="achievementsTO", fetch=FetchType.EAGER)
    private Collection<AchievementsTO> achievementsTOs;

    /**
     * @return the achievmentId
     */
    public Integer getAchievmentId() {
        return achievmentId;
    }

    /**
     * @param achievmentId the achievmentId to set
     */
    public void setAchievmentId(Integer achievmentId) {
        this.achievmentId = achievmentId;
    }

    /**
     * @return the achievementName
     */
    public String getAchievementName() {
        return achievementName;
    }

    /**
     * @param achievementName the achievementName to set
     */
    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    /**
     * @return the achievementsTO
     */
    public AchievementsTO getAchievementsTO() {
        return achievementsTO;
    }

    /**
     * @param achievementsTO the achievementsTO to set
     */
    public void setAchievementsTO(AchievementsTO achievementsTO) {
        this.achievementsTO = achievementsTO;
    }

    /**
     * @return the profileTO
     */
    public CompanyProfileTO getProfileTO() {
        return profileTO;
    }

    /**
     * @param profileTO the profileTO to set
     */
    public void setProfileTO(CompanyProfileTO profileTO) {
        this.profileTO = profileTO;
    }

    /**
     * @return the achievementsTOs
     */
    public Collection<AchievementsTO> getAchievementsTOs() {
        return achievementsTOs;
    }

    /**
     * @param achievementsTOs the achievementsTOs to set
     */
    public void setAchievementsTOs(Collection<AchievementsTO> achievementsTOs) {
        this.achievementsTOs = achievementsTOs;
    }
}
