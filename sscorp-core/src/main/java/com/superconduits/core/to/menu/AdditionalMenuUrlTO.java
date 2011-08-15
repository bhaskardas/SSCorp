/*
 * This DTO is used to store any additional menu urls for a given menu or
 * submenu or both at the same time.
 */

package com.superconduits.core.to.menu;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author bhaskar
 * @since 16 Oct 2010
 * @version 1.0, 1.1
 * @changeLog 17 Oct 2010 (Added url_name field in the database table and changed the DTO
 *                         accordingly.)
 */
@Entity
@Table(name="sscorp_additional_menu_url_m")
public class AdditionalMenuUrlTO implements Serializable {

    @Id
    @Column(name="url_id", nullable=false, length=11)
    private Integer urlId;

    @Column(name="url_name", nullable=false, length=200)
    private String urlName;

    @Column(name="url", nullable=false, length=200)
    private String url;

    @ManyToOne(cascade={CascadeType.REMOVE, CascadeType.REFRESH})
    @JoinColumn(name="menu_id", referencedColumnName="menu_id")
    private MenuTO aMenuTO;

    private Integer submenuId;

    /**
     * @return the urlId
     */
    public Integer getUrlId() {
        return urlId;
    }

    /**
     * @param urlId the urlId to set
     */
    public void setUrlId(Integer urlId) {
        this.urlId = urlId;
    }

    /**
     * @return the urlName
     */
    public String getUrlName() {
        return urlName;
    }

    /**
     * @param urlName the urlName to set
     */
    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    /**
     * @return the aMenuTO
     */
    public MenuTO getaMenuTO() {
        return aMenuTO;
    }

    /**
     * @param aMenuTO the aMenuTO to set
     */
    public void setaMenuTO(MenuTO aMenuTO) {
        this.aMenuTO = aMenuTO;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

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
}
