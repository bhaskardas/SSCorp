/*
 * Class to set the breadcrumb links to be displayed on the web page.
 */
package com.superconduits.web.interceptor;

import com.superconduits.core.to.BreadCrumb;
import com.superconduits.core.to.menu.MenuTO;
import com.superconduits.core.to.menu.SubmenuTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author bhaskar
 * @since  16th Dec 2010
 * @version 1.0
 * @changeLog
 */
public class BreadCrumbInterceptor {

    private static Logger logger = Logger.getLogger(BreadCrumbInterceptor.class);
    private MenuTO menuTO;
    private SubmenuTO submenuTO;
    private BreadCrumb breadCrumb;
    String path;

    /**
     * Checks every request coming from the jsp page - menu or submenu link
     * clicked or some additional link on the page is clicked. Sets the value
     * of the Breadcrumb parameter according to that.
     * @param request
     */
    public void interceptCurMenuRequest(HttpServletRequest request,
                HttpServletResponse response) {

        logger.info("Intercepting current request for bread crumb links");

        if (request.getSession().getServletContext()
                                .getAttribute("menus") != null) {
            List<MenuTO> menus = (List<MenuTO>) request.getSession()
                                                        .getServletContext()
                                                        .getAttribute("menus");

            String url = request.getRequestURL().toString();
            path = url.substring(0, url.lastIndexOf("/"));
            url = url.substring(url.lastIndexOf("/"));

            breadCrumb = new BreadCrumb();
            breadCrumb.setIsRefresh(true);

            for (int count = 0; count < menus.size(); count++) {
                menuTO = menus.get(count);
                if (url.trim().equals(menuTO.getMenuUrl().trim())) {
                    setBreadCrumbMenuName();

                    if (menuTO.getSubmenus() != null) {
                        submenuTO = ((List<SubmenuTO>) menuTO.getSubmenus())
                                                                .get(0);
                        setBreadCrumbSubmenuName();
                    }
                    break;
                } else {
                    List<SubmenuTO> submenuTOs =
                            (List<SubmenuTO>) menuTO.getSubmenus();
                    if (submenuTOs != null && submenuTOs.size() > 0) {
                        for (int count1 = 0; count1 < submenuTOs.size(); count1++) {
                            submenuTO = submenuTOs.get(count1);
                            if (url.trim().equals(submenuTO.getSubmenuUrl().trim())) {
                                setBreadCrumbMenuName();
                                setBreadCrumbSubmenuName();
                                break;
                            }
                        }
                    }
                }
            }

            JSONObject jSONObject = new JSONObject(breadCrumb);
            logger.info(jSONObject.toString());
            response.setHeader("breadcrumb", jSONObject.toString());
        } else {
            logger.warn("Menu object not saved in session.");
            logger.warn("Generating an error and forwarding to error jsp");
            //forward to some error jsp...
        }
    }

    /**
     * Set the breadcrumb main menu links.
     */
    private void setBreadCrumbMenuName(){

        breadCrumb.setMenuName(menuTO.getMenuName().trim());
        breadCrumb.setMenuUrl(path + menuTO.getMenuUrl().trim());
    }

    /**
     * Sets the breadcrumb submenu links.
     */
    private void setBreadCrumbSubmenuName(){
        breadCrumb.setSubmenuName(submenuTO.getSubmenuName().trim());
        breadCrumb.setSubmenuUrl(path + submenuTO.getSubmenuUrl().trim());
    }
}