/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superconduits.core.business.impl;

import com.superconduits.core.business.interfaces.IEnquiry;
import com.superconduits.core.dbaccess.EnquiryDAO;
import com.superconduits.core.exceptions.SSCorpBusinessException;
import com.superconduits.core.exceptions.SSCorpDatabaseException;
import com.superconduits.core.to.QueryDetailsTO;
import com.superconduits.core.util.SSCorpUtil;
import com.superconduits.core.util.persistence.PersistenceUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;
import org.utilities.util.exceptions.mail.MailerException;
import org.utilities.util.mail.EMail.EMailSender;
import org.utilities.util.mail.MailTO;
import org.utilities.util.mail.SendEMail;

/**
 *
 * @author bhaskar
 */
public class EnquiryImpl implements IEnquiry {

    private Logger logger = Logger.getLogger(EnquiryImpl.class);
    private QueryDetailsTO detailsTO;

    @Override
    public void saveEnquiry(QueryDetailsTO queryDetailsTO) throws SSCorpBusinessException {

        detailsTO = queryDetailsTO;
        EntityManager em = null;
        try {
            em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            logger.info("entity manager created. Going to begin the transaction.");

            EnquiryDAO enquiryDAO = (EnquiryDAO) SSCorpUtil.getObject("enquiryDAO");
            enquiryDAO.insertNewQuery(em, queryDetailsTO);

            new EMailRequestForwarder().forward();

            em.getTransaction().commit();
            logger.info("Transaction committed.");
        } catch (MailerException e) {
            if(logger.isDebugEnabled())
                e.printStackTrace();
            em.getTransaction().rollback();
            throw new SSCorpBusinessException(e.getMessage());
        } catch (SSCorpDatabaseException de) {
            if(logger.isDebugEnabled())
                de.printStackTrace();
            em.getTransaction().rollback();
            throw new SSCorpBusinessException(de.getMessage());
        }catch (Exception ex) {
            if(logger.isDebugEnabled())
                ex.printStackTrace();
            em.getTransaction().rollback();
            throw new SSCorpBusinessException(ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * 
     */
    class EMailRequestForwarder {

        /**
         * 
         * @throws MailerException
         */
        void forward() throws MailerException {
            EmailPayloadCreator payloadCreator = new EmailPayloadCreator();
            MailTO mailTO = payloadCreator.createPayload();
            EMailSender eMailSender = new SendEMail(mailTO);
            eMailSender.sendMail();
        }
    }

    /**
     *
     */
    class EmailPayloadCreator {

        private MailTO mailTO;

        /**
         *
         */
        EmailPayloadCreator() {
            mailTO = new MailTO();
        }

        /**
         * 
         * @return
         */
        MailTO createPayload() {
            prepareHTMLMessage4SendingMail();
            loadMailConfigurations();
            return mailTO;
        }

        /**
         * 
         */
        private void prepareHTMLMessage4SendingMail() {
            logger.info("preparing html message payload before sending mail...");
            StringBuilder htmlBodyCache = new StringBuilder();

            htmlBodyCache
                    .append("<html><head><meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' />")
                    .append("<title>New Query Posted</title>")
                    .append("</head><body>")
                    .append("<div>New Query Posted on www.superconduits.com</div>")
                    .append("<br/>")
                    .append("<div style='border-bottom: 1px solid #5FABF7; font-size: 18px; color:#fff; font-weight: bold; background-color: #153E7E;'><h3 style='margin: 0px 0px 3px 0px; padding:2px 10px; font-style:oblique; font-weight:bold; font-variant:small-caps; text-align: left;'>Query Details</h3></div>")
                    .append("<div style='margin: 5px 0 0 0; padding: 4px; color: #000; background-color: #CCC; opacity: 0.8; filter: alpha(opacity=80); font-weight:bold;'><table width='100%' cellpadding='2' cellspacing='2' border='0'>")
                    .append("<tr><td width='20%' nowrap='true'>Name</td><td width='30%' colspan='3' nowrap='true'>" + detailsTO.getFullName() + "</td></tr>")
                    .append("<tr><td width='20%' nowrap='true'>Company Name</td><td width='30%' nowrap='true'>" + detailsTO.getCompanyName() + "</td><td width='20%'>Company Website</td><td width='30%'>" + detailsTO.getCompanyWebsite() + "</td></tr>")
                    .append("<tr><td width='20%' nowrap='true'>Phone Number</td><td width='30%' colspan='3' nowrap='true'>" + detailsTO.getPhoneNo() + "</td>,</tr>")
                    .append("<tr><td width='20%' nowrap='true'>Email ID</td><td width='30%' colspan='3' nowrap='true'>" + detailsTO.getEmailId() + "</td></tr>")
                    .append("<tr><td width='20%' nowrap='true'>Where did you hear about us?</td><td width='30%' colspan='3' nowrap='true'>" + detailsTO.getHearAboutUs() + "</td></tr>")
                    .append("<tr><td width='20%' nowrap='true'>Subject</td><td width='30%' colspan='3' nowrap='true'>" + detailsTO.getQuerySubject() + "</td></tr>")
                    .append("<tr><td width='20%' nowrap='true'>Date Posted</td><td width='30%' colspan='3' nowrap='true'>" + detailsTO.getQueryDate() + "</td></tr>")
                    .append("<tr><td width='20%' nowrap='true'>Query</td><td width='30%' colspan='3' nowrap='true'>" + detailsTO.getQuery() + "</td></tr>")
                    .append("</table></div>")
                    .append("<br/>")
                    .append("<div>(Note: This is an autogenerated mail from www.superconduits.com. For replying use the Email-ID provided in the mail.)</div>")
                    .append("</body></html>");

            mailTO.setSubject(detailsTO.getQuerySubject());
            mailTO.setMessage(htmlBodyCache.toString());
            mailTO.setFrom(detailsTO.getEmailId());
            mailTO.setFullName(detailsTO.getFullName());
        }

        /**
         * 
         */
        private void loadMailConfigurations() {
            Properties properties = null;
            InputStream inputStream = EmailPayloadCreator.class.getClassLoader().getResourceAsStream("com/superconduits/web/config/mail-config.properties");

            if (inputStream != null) {
                properties = new Properties();
                try {
                    properties.load(inputStream);
                    mailTO.setMailConfigurations(properties);
                    String to[] = properties.get("recipient-email-id").toString().split(",");
                    mailTO.setTo(to);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
