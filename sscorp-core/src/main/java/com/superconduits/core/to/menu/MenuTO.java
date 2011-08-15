/*
 * This DTO represents all the relevant data for a single menu object
 * in front end.
 */

package com.superconduits.core.to.menu;

import com.superconduits.core.to.CompanyProfileTO;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author bhaskar
 * @since 25th Aug 2010
 * @changeLog 16th Oct 2010
 * @version 1.0, 1.1
 */
@Entity
@Table(name="sscorp_menu_m")
public class MenuTO implements Serializable{

    @Id
    @Column(name="menu_id", nullable=false, length=11)
    @GeneratedValue
    private Integer menuId;

    @Column(name="menu_name", nullable=false, length=50)
    private String menuName;

    @Column(name="menu_position", nullable=false, length=5)
    private Integer menuPosition;

    @Column(name="menu_url", nullable=true, length=100)
    private String menuUrl;

    @Column(name="menu_image", nullable=true, length=50)
    private String menuImage;

    @ManyToOne
    @JoinColumn(name="company_id", referencedColumnName="company_id")
    private CompanyProfileTO companyProfileTO;

    @OneToMany(mappedBy="menuTO")
    private Collection<SubmenuTO> submenus;

    @OneToMany(mappedBy="aMenuTO")
    private Collection<AdditionalMenuUrlTO> additionalMenuUrlTOs;

    /**
     * @return the menuId
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * @param menuId the menuId to set
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /**
     * @return the menuName
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * @param menuName the menuName to set
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * @return the menuPosition
     */
    public Integer getMenuPosition() {
        return menuPosition;
    }

    /**
     * @param menuPosition the menuPosition to set
     */
    public void setMenuPosition(Integer menuPosition) {
        this.menuPosition = menuPosition;
    }

    /**
     * @return the menuUrl
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * @param menuUrl the menuUrl to set
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
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
     * @return the submenus
     */
    public Collection<SubmenuTO> getSubmenus() {
        return submenus;
    }

    /**
     * @param submenus the submenus to set
     */
    public void setSubmenus(Collection<SubmenuTO> submenus) {
        this.submenus = submenus;
    }

    /**
     * @return the additionalMenuUrlTOs
     */
    public Collection<AdditionalMenuUrlTO> getAdditionalMenuUrlTOs() {
        return additionalMenuUrlTOs;
    }

    /**
     * @param additionalMenuUrlTOs the additionalMenuUrlTOs to set
     */
    public void setAdditionalMenuUrlTOs(Collection<AdditionalMenuUrlTO> additionalMenuUrlTOs) {
        this.additionalMenuUrlTOs = additionalMenuUrlTOs;
    }

    /**
     * @return the menuImage
     */
    public String getMenuImage() {
        return menuImage;
    }

    /**
     * @param menuImage the menuImage to set
     */
    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }
}
