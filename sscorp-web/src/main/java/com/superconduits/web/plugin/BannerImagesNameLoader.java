/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superconduits.web.plugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

/**
 *
 * @author bhaskar
 */
public class BannerImagesNameLoader implements PlugIn {

    private static Logger logger = Logger.getLogger(BannerImagesNameLoader.class);

    @Override
    public void init(ActionServlet as, ModuleConfig mc) throws ServletException {
        logger.info("Going to load the image names for the banner portion of the web page.");
        
        InputStream is = BannerImagesNameLoader.class.getClassLoader()
            .getResourceAsStream("com/superconduits/web/config/sscorp-config.properties");

        Properties properties = null;

        if (is != null) {
            try {
                properties = new Properties();
                properties.load(is);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        String bannerImageNames = properties.getProperty("bannerImageNames");
        as.getServletContext().setAttribute("bannerImages", bannerImageNames);
        logger.info("Completed loading the images names for the banner portion of the web page.");
    }

    @Override
    public void destroy() {
        
    }
}
