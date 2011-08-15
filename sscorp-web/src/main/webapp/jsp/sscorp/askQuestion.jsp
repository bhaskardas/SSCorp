<%-- 
    Document   : askQuestion
    Created on : 20 Jul, 2011, 12:55:36 PM
    Author     : bhaskar
--%>

<%@ include file="/jsp/include.jsp"%>

<div id="enquiry-header">
    <h3 class="page-heading roundedCorner">Ask a Question</h3>
</div>
<div id="sbjectandQuestion" class="subjectandQuestion roundedCorner">
    <html:form action="/saveUserQuery.html" method="post">
        <table width="100%" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td width="20%" class="data">
                    <font color="red">*</font>Subject
                </td>
                <td width="30%" colspan="3" class="data">
                    <html:text styleId="subjectId" property="subject"
                               value="" name="userQueryForm"
                               maxlength="180"></html:text>
            </td>
            </tr>
            <tr>
                <td width="20%" class="data">
                    <font color="red">*</font>Query
                </td>
                <td width="80%" colspan="3" class="data">
                    <html:textarea property="query" cols="60"
                                   rows="5" name="userQueryForm" value="" 
                                   styleId="queryId">
                    </html:textarea>
                </td>
            </tr>
            <tr>
                <td width="20%" class="data">
                    <font color="red">*</font>Where did you hear about us?
                </td>
                <td width="30%" colspan="3" class="data">
                    <html:text property="whereAboutsOfCompany"
                               name="userQueryForm" value="" 
                               styleId="aboutId" maxlength="480">
                    </html:text>
                </td>
            </tr>
            <tr>
                <td width="20%" class="data">
                    Company Name
                </td>
                <td width="30%" class="data">
                    <html:text property="companyName"
                               name="userQueryForm" value="" 
                               styleId="cnId" maxlength="40">
                    </html:text>
                </td>
                <td width="20%" class="data">
                    Company Website
                </td>
                <td width="30%" class="data">
                    <html:text property="companyWebsite"
                               name="userQueryForm" value="" 
                               styleId="cwId" maxlength="50">
                    </html:text>
                </td>
            </tr>
            <tr>
                <td width="20%" class="data">
                    <font color="red">*</font>Email Id
                </td>
                <td width="30%" colspan="3" class="data">
                    <html:text property="companyEmailId"
                               name="userQueryForm" value="" 
                               styleId="ceId" maxlength="180">
                    </html:text>
                </td>
            </tr>
            <tr>
                <td width="20%" class="data">
                    <font color="red">*</font>Name
                </td>
                <td width="30%" class="data">
                    <html:text property="fullName"
                               name="userQueryForm" value="" 
                               styleId="fullNameId" maxlength="185"></html:text>
                </td>
                <td width="20%" class="data">
                    Phone Number
                </td>
                <td width="30%" class="data">
                    <html:text property="phoneNo"
                               name="userQueryForm" value="" 
                               styleId="phoneNoId" maxlength="18"></html:text>
                </td>
            </tr>
            <tr>
                <td colspan="4" class="data">
                    &nbsp;
                </td>
            </tr>
            <tr>
                <td width="20%" class="data">
                    &nbsp;
                </td>
                <td width="70%" colspan="3" class="data">
                    <input id="querySubmit" type="button"
                           name="querySubmit" value="Submit"
                           class="button"/>
                    <div id="saveInProcess" class="display-none">
                        Please wait. Saving your query...
                        <img id="querySubmitImg"
                             alt="Saving User Query"
                             src="<%=request.getContextPath()%>/images/ajax-loader.gif"
                             title="Saving User Query"/>
                    </div>
                </td>
            </tr>
        </table>
    </html:form>
</div>
