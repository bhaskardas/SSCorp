/*
 * This DTO represents all the relevant data for a single submenu object
 * in front end.
 */

package com.superconduits.core.to.menu;

import java.io.Serializable;
import javax.persistence.CascadeType;
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
 * @since 25th Aug 2010
 * @version 1.0
 */

@Entity
@Table(name="sscorp_submenu_m")
public class SubmenuTO implements Serializable{

    @Id
    @GeneratedValue
    @Column(name="submenu_id", nullable=false, length=11)
    private Integer submenuId;

    @Column(name="submenu_name", nullable=false, length=50)
    private String submenuName;

    @Column(name="submenu_position", nullable=false, length=5)
    private String submenuPosition;

    @Column(name="submenu_url", nullable=true, length=100)
    private String submenuUrl;

    @ManyToOne(cascade={CascadeType.REMOVE, CascadeType.REFRESH})
    @JoinColumn(name="menu_id", referencedColumnName="menu_id")
    private MenuTO menuTO;

    /**
     * @return the submenuId
     */
    public Integer getSubmenuId() {
        return submenuId;
    }

    /**
     * @param submenuId the submenuId to set
     */
    public void setSubmenuId(Integer submenuId) {
        this.submenuId = submenuId;
    }

    /**
     * @return the submenuName
     */
    public String getSubmenuName() {
        return submenuName;
    }

    /**
     * @param submenuName the submenuName to set
     */
    public void setSubmenuName(String submenuName) {
        this.submenuName = submenuName;
    }

    /**
     * @return the submenuPosition
     */
    public String getSubmenuPosition() {
        return submenuPosition;
    }

    /**
     * @param submenuPosition the submenuPosition to set
     */
    public void setSubmenuPosition(String submenuPosition) {
        this.submenuPosition = submenuPosition;
    }

    /**
     * @return the submenuUrl
     */
    public String getSubmenuUrl() {
        return submenuUrl;
    }

    /**
     * @param submenuUrl the submenuUrl to set
     */
    public void setSubmenuUrl(String submenuUrl) {
        this.submenuUrl = submenuUrl;
    }

    /**
     * @return the menuTO
     */
    public MenuTO getMenuTO() {
        return menuTO;
    }

    /**
     * @param menuTO the menuTO to set
     */
    public void setMenuTO(MenuTO menuTO) {
        this.menuTO = menuTO;
    }
}
