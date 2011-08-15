<%--
    Document   : companyProfile
    Created on : 31 Jan, 2011, 12:28:56 AM
    Author     : bhaskar
--%>

<%@include file="/jsp/include.jsp"%>

<script type="text/javascript"
        src="<%=request.getContextPath()%>/script/profile.js">
</script>

<div id="profileMainDiv" class="profileMainDiv roundedCorner">
    <div id="profileContentDiv" class="profileContentDiv">
    </div>
    <div id="profileMenuDiv" class="profileMenuDiv roundedCorner">
        <div id="profileMenuWrapper">
            <div id="profileMenuHeader" class="profileMenuHeader">
                Company Profile
            </div>
            <logic:present name="menus" scope="application">
                <c:forEach var="menu" items="${menus}">
                    <c:if test="${menu.menuId == 2}">
                        <c:set var="submenu" value="${menu.submenus}"></c:set>
                        <c:if test="${submenu != null}">
                            <c:forEach var="sMenu" items="${submenu}">
                                <div id="profiles" class="profiles">
                                    <a href="<%=request.getContextPath()%>${sMenu.submenuUrl}">
                                        ${sMenu.submenuName}
                                    </a>
                                </div>
                            </c:forEach>
                        </c:if>
                    </c:if>
                </c:forEach>
            </logic:present>
            <logic:notPresent name="menus" scope="application">
                Object Not found. Please try after some time.
            </logic:notPresent>
        </div>
    </div>
</div>