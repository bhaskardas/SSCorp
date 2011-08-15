<%--
    Document   : aboutUs
    Created on : 31 Jan, 2011, 2:38:12 AM
    Author     : bhaskar
--%>

<%@ include file="/jsp/include.jsp"%>

<div id="aboutUs" class="profileContent roundedCorner">
    <logic:present name="companyProfile" scope="application">
        <div id="aboutUsHeader" class="profileHeader roundedCorner">
            About Us
        </div>
        <br/>
        <div id="aboutUsContent" class="aboutUsContent">
            ${companyProfile.aboutUs}
        </div>
    </logic:present>
</div>