<%--
    Document   : products
    Created on : 22 Sep, 2010, 6:48:17 PM
    Author     : bhaskar
--%>

<%@include file="/jsp/include.jsp"%>


<%--<script type="text/javascript"
        src="<%=request.getContextPath()%>/script/jquery/easySlider.js">
</script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/script/jquery/loopedslider.js">
</script>--%>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/script/product.js">
</script>

<c:set var="prodCatId" value='<%=request.getParameter("productCatId")%>'></c:set>

<logic:present name="productListByCat" scope="application">
    <div id="prodListHeader" class="prodListHeader">
        <h3 class="page-heading roundedCorner">
            Product List for <%=request.getParameter("prodCatName")%>
            <span class="page-heading-links">Go Back</span>
        </h3>
    </div>
    <div id="productList" class="productList">
            <logic:iterate id="prodList" name="productListByCat">
                <logic:equal name="prodCatId" value="${prodList.key}">
                        <c:set var="plfs" value="${prodList.value}"></c:set>
                        <%@include file="/jsp/sscorp/productDetail.jsp"%>
                </logic:equal>
            </logic:iterate>
    </div>
</logic:present>
<logic:notPresent name="productListByCat">

</logic:notPresent>