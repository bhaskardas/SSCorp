| <c:forEach var="menu" items="${menus}">
	<a href="<%=request.getContextPath()%>${menu.menuUrl}">
        ${menu.menuName}
    </a> |
</c:forEach>
<br/>
Site by Bhaskar Das &copy;
<br/>
All rights reserved By Bhaskar Das and Super Sales Corporation.

