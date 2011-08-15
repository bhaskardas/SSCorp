<%--
    Document   : missionVission
    Created on : 1 Feb, 2011, 11:03:40 AM
    Author     : bhaskar
--%>

<%@ include file="/jsp/include.jsp"%>

<div id="missionVission" class="profileContent roundedCorner">
    <logic:present name="companyProfile" scope="application">
        <div id="missionHeader" class="profileHeader roundedCorner">
            Mission
        </div>
        <div id="missionContent" class="mvContent">
            <i>${companyProfile.mission}</i>
        </div>
        <br/>
        <br/>
        <div id="vissionHeader" class="profileHeader page-heading roundedCorner">
            Vision
        </div>
        <div id="visionContent" class="mvContent">
            <i>${companyProfile.vision}</i>
        </div>
    </logic:present>
</div>