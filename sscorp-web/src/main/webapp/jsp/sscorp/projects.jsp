<%--
    Document   : projects
    Created on : 5 Dec, 2010, 9:53:10 PM
    Author     : bhaskar
--%>

<%@include file="/jsp/include.jsp"%>

<c:set var="projCatId" value='<%=request.getParameter("projectCategoryId")%>'>
</c:set>
<c:set var="image" value='<%=request.getParameter("projImage")%>'>
</c:set>

<span id="go_back" class="go_back">
	<img id="close"
 		src="<%=request.getContextPath()%>/images/close.png"
    	alt="Close"/>
</span>

<div id="project_content" class="project_content"
     style="background-image: url('${image}')">
    <logic:present name="projectCategories" scope="application">
        <logic:iterate id="pcto" name="projectCategories">
            <c:if test="${pcto.projectCatId == projCatId}">
                <c:set var="ptos"
                       value="${pcto.projectTOs}">
                </c:set>
                <div id="project_content_header" class="project_content_header">
                    <h3>${pcto.projectCatName}</h3>
                </div>
                <hr/>
                <div id="project_desc" class="project_desc">
                    ${pcto.projectDesc}
                </div>
                <div id="project_list" class="project_list">
                    <table width="100%"
                           border="0"
                           cellspacing="0"
                           cellpadding="0">
                        <thead>
                            <tr>
                                <td>Project Name</td>
                                <td>City/State</td>
                            </tr>
                        </thead>
                        <tbody>
                            <logic:iterate id="project"
                                           name="ptos"
                                           indexId="counter">
                                <c:choose>
                                    <c:when test="${counter % 2 == 0}">
                                        <tr class="tableRowEven">
                                        </c:when>
                                        <c:otherwise>
                                        <tr class="tableRowOdd">
                                        </c:otherwise>
                                    </c:choose>
                                    <td width="60%">${project.projectName}</td>
                                    <td width="40%">${project.city}</td>
                                </tr>
                            </logic:iterate>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </logic:iterate>
    </logic:present>
    <logic:notPresent name="projectCategories" scope="application">
        Data could not be displayed due to some error. Please try again after
        some time.
    </logic:notPresent>
</div>