<%--
    Document   : contactUs
    Created on : 27 Feb, 2011, 8:22:01 PM
    Author     : bhaskar
--%>

<script type="text/javascript"
src="<%=request.getContextPath()%>/script/askQuestion.js"></script>

<script type="text/javascript"
src="<%=request.getContextPath()%>/script/contactUs.js"></script>

<%@ include file="/jsp/include.jsp"%>

<div id="information" class="information roundedCorner">
    <div id="contactUs" class="contactUs">
        <logic:present name="address" scope="application">
            <logic:present name="companyProfile" scope="application">
                <div id="contactUs-header">
                    <h3 class="page-heading roundedCorner">Contact Us</h3>
                </div>
                <div id="contactUs-content" class="contactUs-content roundedCorner">
                    <span>${companyProfile.companyName}</span><br/>
                    ${address.address}<br/>
                    ${address.city} - ${address.zipcode}, <br/>
                    ${address.province}, ${address.country}<br/><br/>
                    <img src="<%=request.getContextPath()%>/images/phone_icon.png"
                         alt="Phone" title="Phone"/>: ${companyProfile.companyPhone}<br/>
                    <img src="<%=request.getContextPath()%>/images/fax_icon.png"
                         alt="Fax" title="Fax"/>: ${companyProfile.faxNumber}<br/>
                    <img src="<%=request.getContextPath()%>/images/email.png" 
                         height="20" width="20"
                         alt="Email" title="Email"/>: ${companyProfile.emailId}<br/><br/>

                    <a href="http://www.facebook.com/pages/SuperSales-Corporation/140565559323463">
                        <img src="<%=request.getContextPath()%>/images/facebook-icon.png"
                             alt="Follow us on Facebook"
                             title="Follow us on Facebook" height="20" width="20"/>
                    </a>
                </div>
            </logic:present>
            <logic:notPresent name="companyProfile" scope="application">
		        Data Could not be displayed currently. Please try again later.
            </logic:notPresent>
        </logic:present>
        <logic:notPresent name="address" scope="application">
		    Data Could not be displayed currently. Please try again later.
        </logic:notPresent>
    </div>
    <div id="enquiry" class="enquiry">
        <%@ include file="/jsp/sscorp/askQuestion.jsp"%>
    </div>
</div>