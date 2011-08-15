/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superconduits.web.action;

import com.superconduits.core.business.interfaces.IEnquiry;
import com.superconduits.core.exceptions.SSCorpBusinessException;
import com.superconduits.core.to.CompanyProfileTO;
import com.superconduits.core.to.QueryDetailsTO;
import com.superconduits.core.util.SSCorpUtil;
import com.superconduits.web.exceptions.SSCorpControllerException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

/**
 *
 * @author bhaskar
 */
public class SaveUserQueryAction extends Action {

    private static Logger logger = Logger.getLogger(SaveUserQueryAction.class);
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        CompanyProfileTO cpto = (CompanyProfileTO)request.getSession()
                                        .getServletContext()
                                        .getAttribute("companyProfile");

        DynaActionForm dynamicForm = (DynaActionForm) form;
        QueryDetailsTO queryDetailsTO = prepareQueryDTO(dynamicForm, cpto);

        IEnquiry iEnquiry = (IEnquiry)SSCorpUtil.getObject("enquiry");

        try{
            if(queryDetailsTO != null){
                iEnquiry.saveEnquiry(queryDetailsTO);
            response.setHeader("message", "Your query has been logged successfully. We'll get back to you with an answer shortly.");
            }else{
                throw new SSCorpControllerException("Form values received are null.");
            }
        }catch(SSCorpBusinessException be){
            if(logger.isDebugEnabled())
                be.printStackTrace();
            response.setHeader("message", "Message could not be looged due to some error in the network. Please try again after some time.");
            throw new Exception(be.getMessage());
        }catch(SSCorpControllerException ex){
            if(logger.isDebugEnabled())
                ex.printStackTrace();
            response.setHeader("message", "Message could not be looged due to some error in the network. Please try again after some time.");
            throw new Exception(ex.getMessage());
        }

        return mapping.findForward("userQueryLogged");
    }

    /**
     * 
     * @param dynamicForm
     * @param cpto
     * @return
     */
    private QueryDetailsTO prepareQueryDTO(DynaActionForm dynamicForm,
                                    CompanyProfileTO cpto) {
        QueryDetailsTO queryDetailsTO;

        if (dynamicForm != null) {
            queryDetailsTO = new QueryDetailsTO();
            queryDetailsTO.setQuerySubject(dynamicForm.get("subject").toString());
            queryDetailsTO.setQuery(dynamicForm.get("query").toString());
            queryDetailsTO.setHearAboutUs(dynamicForm.get("whereAboutsOfCompany").toString());
            queryDetailsTO.setCompanyName(dynamicForm.get("companyName") != null
                    ? dynamicForm.get("companyName").toString() : "--");
            queryDetailsTO.setCompanyWebsite(
                    dynamicForm.get("companyWebsite") != null ?
                        dynamicForm.get("companyWebsite").toString() : "--");
            queryDetailsTO.setEmailId(dynamicForm.get("companyEmailId").toString());
            queryDetailsTO.setFullName(dynamicForm.get("fullName").toString());
            queryDetailsTO.setPhoneNo(dynamicForm.get("phoneNo") != null ? 
                dynamicForm.get("phoneNo").toString() : "--");
            queryDetailsTO.setQueryDate(new Date());
            queryDetailsTO.setCompanyProfileTO(cpto);

            return queryDetailsTO;
        } else {
            return null;
        }
    }
}
