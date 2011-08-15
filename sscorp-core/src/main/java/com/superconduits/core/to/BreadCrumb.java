/*
 * This DTO is used to store the information regarding the menus and submenus
 * to be displayed on the breadcrumb area of the web page.
 */

package com.superconduits.core.to;

/**
 *
 * @author bhaskar
 * @since  17th Oct 2010
 * @version 1.0
 * @changeLog
 */
public class BreadCrumb {

    private String menuName = "";
    private String menuUrl = "";
    private String submenuName = "";
    private String submenuUrl = "";
    private String additionalMenuName = "";
    private String additionalMenuUrl = "";
    private boolean isRefresh = false;

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
     * @return the additionalMenuName
     */
    public String getAdditionalMenuName() {
        return additionalMenuName;
    }

    /**
     * @param additionalMenuName the additionalMenuName to set
     */
    public void setAdditionalMenuName(String additionalMenuName) {
        this.additionalMenuName = additionalMenuName;
    }

    /**
     * @return the additionalMenuUrl
     */
    public String getAdditionalMenuUrl() {
        return additionalMenuUrl;
    }

    /**
     * @param additionalMenuUrl the additionalMenuUrl to set
     */
    public void setAdditionalMenuUrl(String additionalMenuUrl) {
        this.additionalMenuUrl = additionalMenuUrl;
    }

    /**
     * @return the isRefresh
     */
    public boolean isIsRefresh() {
        return isRefresh;
    }

    /**
     * @param isRefresh the isRefresh to set
     */
    public void setIsRefresh(boolean isRefresh) {
        this.isRefresh = isRefresh;
    }
}
