/*
 * This custom class loads the menu objects for the super sales corporation
 * into the application memory at server startup.
 */

package com.superconduits.web.plugin;

import com.superconduits.core.business.interfaces.IMenu;
import com.superconduits.core.to.CompanyProfileTO;
import com.superconduits.core.to.menu.MenuTO;
import com.superconduits.core.util.SSCorpUtil;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.utilities.util.PropertiesUtil;

/**
 * @author bhaskar
 * @Created on 03rd Jan 2011
 * @Version : 1.0
 * @ChangeLog :
 */
public class MenuLoader implements PlugIn{

    private static Logger logger = Logger.getLogger(MenuLoader.class);

    @Override
    public void init(ActionServlet as, ModuleConfig mc) throws ServletException {
        logger.info("Going to set the menu object in the application scope.");
        loadMenu(as.getServletContext());
        logger.info("Completed setting up the menu object in the application " +
                    "scope.");
    }

    @Override
    public void destroy() {}

    /**
     * set the menu and submenu information in the session.
     * @param request HttpServletRequest object.
     */
    private void loadMenu(ServletContext sc)
            throws ServletException {

        logger.warn("in loadMenu function.. Going to set"
                + "the menu object in application scope.");

        Integer companyId = ((CompanyProfileTO) sc.getAttribute("companyProfile"))
                                                    .getCompanyId();

        String[] imagePath = null;
        try {
            imagePath = PropertiesUtil.readProperty("sscorp-config", "menuImagePath").split(",,");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(MenuLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MenuLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            IMenu iMenu = (IMenu) SSCorpUtil.getObject("menu");
            List<MenuTO> menus = iMenu.fetchMenus(companyId);

            if (menus != null && menus.size() > 0) {
                for(MenuTO menuTO: menus){
                    menuTO.setMenuImage(SSCorpUtil.setImagePath(imagePath,
                                                    menuTO.getMenuImage()));
                }
                sc.setAttribute("menus", menus);
            }

            logger.warn("Setting menu object in application scope completed.");
        } catch (Exception e) {
            logger.info("Exception occurred "
                    + "in request processor process method while setting "
                    + "company profile object");

            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
    }
}
