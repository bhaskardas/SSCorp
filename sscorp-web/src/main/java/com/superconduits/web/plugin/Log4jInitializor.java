/*
 * This is a custom plugin class used to initialize the logging framework
 * (log4j) to do all the logging in the sscorp application.
 */

package com.superconduits.web.plugin;

import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

/**
 * @author bhaskar
 * @Created on 03rd Jan 2011
 * @Version : 1.0
 * @ChangeLog :
 */
public class Log4jInitializor implements PlugIn{

    @Override
    public void init(ActionServlet as, ModuleConfig mc) throws ServletException {
        System.out.println("Log4jInitializor is initializing log4j");

        ServletContext sc = as.getServletContext();

        String log4jLocation = sc.getInitParameter("log4j-xml-location");

        if (log4jLocation == null) {
            System.err.println("*** No log4j-xml-location init param found; " +
                                "initializing log4j with BasicConfigurator");
            BasicConfigurator.configure();
        } else {
            String webAppPath = sc.getRealPath("/");
            String log4jXmlFilePath = webAppPath + log4jLocation;
            File log4jFile = new File(log4jXmlFilePath);
            if (log4jFile.exists()) {
                System.out.println("Initializing log4j with: " +
                                                            log4jLocation);
                DOMConfigurator.configure(log4jXmlFilePath);
            } else {
                System.err.println("*** " + log4jXmlFilePath +
                        " file not found, so initializing log4j with " +
                        "BasicConfigurator");
                BasicConfigurator.configure();
            }
        }
    }

    @Override
    public void destroy() {}
}
