
<%@ include file="/jsp/include.jsp"%>

<ul id="sscorp_menu" class="menu">
    <c:forEach var="menu" items="${menus}">
        <c:set var="sMenu" value="${menu.submenus}"></c:set>
        <li>
            <a href="<%=request.getContextPath()%>${menu.menuUrl}">
                ${menu.menuName}
                <span class="menuImage">
                    <img src="${menu.menuImage}"
                         alt=""/>
                </span>
            </a>
            <div class="sscorp_currentMenu">
                <a id="cMenuLink"
                   href="<%=request.getContextPath()%>${menu.menuUrl}">
                    ${menu.menuName}
                </a>
            </div>
            <c:if test="${sMenu != null}">
                <div class="sscorp_submenu">
                    <c:forEach items="${menu.submenus}" var="submenu">
                        <a href="<%=request.getContextPath()%>${submenu.submenuUrl}">
                            ${submenu.submenuName}
                        </a><br/>
                    </c:forEach>
                </div>
            </c:if>
        </li>
    </c:forEach>
</ul>