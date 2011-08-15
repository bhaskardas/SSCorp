<%--
    Document   : projectCategory
    Created on : 28 Nov, 2010, 11:10:49 AM
    Author     : bhaskar
--%>

<%@include file="/jsp/include.jsp"%>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/script/project.js">
</script>

<logic:present name="projectCategories" scope="application">
    <div id="projCat-header">
        <h2 class="page-heading roundedCorner">Project Categories</h2>
    </div>
    <div id="projCat" class="projCatContainer">
        <logic:iterate id="projCategory" name="projectCategories"
                       indexId="zIndex">
            <div class="projCat_item"
                 style="z-index: ${fn:length(projectCategories) - zIndex};">
                <img src="${projCategory.projectImage}"
                     alt="${projCategory.projectCatName}"
                     title="Project Category - ${projCategory.projectCatName}"/>
                <span class="projCat_title">${projCategory.projectCatName}</span>
                <div class="projCat_desc">
                    <ul>
                        <li id="${projCategory.projectCatId}">
                            <input type="hidden"
                                   id="projImage_${projCategory.projectCatId}"
                                   value="${projCategory.projectImage}"/>
                            ${projCategory.projectCatName}
                        </li>
                    </ul>
                </div>
            </div>
        </logic:iterate>
        <div id="project_container" class="project_container roundedCorner"></div>
        <span id="go_back" class="go_back">
            <img id="close"
                 src="<%=request.getContextPath()%>/images/close.png"
                 alt="Close"/>
        </span>
    </div>
</logic:present>
<logic:notPresent name="projectCategories" scope="application">
    Data could not be displayed due to some error. Please try again after
    some time.
</logic:notPresent>