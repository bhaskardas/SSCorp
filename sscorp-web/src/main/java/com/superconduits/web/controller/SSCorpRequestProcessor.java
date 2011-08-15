/*
 * This is the main class of the applications where all requests are filtered
 * before they are forwarded.
 * Sets the breadcrumb objects for the breadcrumb links to be displayed in the
 * html pages.
 */
package com.superconduits.web.controller;

import com.superconduits.web.interceptor.BreadCrumbInterceptor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.RequestProcessor;

/**
 * @author bhaskar
 * @since July 25th 2010
 * @version 1.0, 1.1, 2.0
 * @changeLog 17 Oct 2010 (Added code for intercepting current menu request and
 *                          the current menu or submenu url. Also set the
 *                          display content for the breadcrumb area.)
 * @changeLog: 05th Jan 2011(Moved the companyProfile and menu loading code
 *              into the plugin classes. Also, the breadcrumb functions in the
 *              BreadcrumbIntercpetor class.)
 */
public class SSCorpRequestProcessor extends RequestProcessor {

    private static Logger logger = Logger.getLogger(SSCorpRequestProcessor.class);

    public SSCorpRequestProcessor() {
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        logger.warn("In Request Processor");
        //set the breadcrumb object in the response object.
        new BreadCrumbInterceptor().interceptCurMenuRequest(request, response);
        super.process(request, response);
    }
}