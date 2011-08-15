<%--
    Document   : achievements
    Created on : 2 Feb, 2011, 12:45:19 AM
    Author     : bhaskar
--%>

<%@include file="/jsp/include.jsp"%>

<div id="achievements" class="profileContent roundedCorner">
    <div id="achievementHeader" class="profileHeader roundedCorner">
        Our Major Achievements
    </div>
    <logic:present name="companyProfile" scope="application">
        <div id="achievementsDesc" class="achievementsDesc">
            ${companyProfile.achievmentsDesc}
        </div>
    </logic:present>
    <hr/>
    <logic:present name="achievements" scope="application">
        <div id="mainAchievement_${ato.achievmentId}" class="mainAchievement">
            <dl>
                <logic:iterate id="ato" name="achievements">
                    <dd class="content-heading">-&nbsp;&nbsp;${ato.achievementName}</dd>
                    <c:if test="${ato.achievementsTOs != null}">
                        <c:set var="childAchievements" value="${ato.achievementsTOs}"></c:set>
                        <logic:iterate id="cato"
                                       name="childAchievements"
                                       offset="1">
                            <dt>- ${cato.achievementName}</dt>
                        </logic:iterate>
                    </c:if>
                </logic:iterate>
            </dl>
        </div>
    </logic:present>
    <logic:notPresent name="achievements" scope="application">
        Data not found. Please try again later.
    </logic:notPresent>
</div>