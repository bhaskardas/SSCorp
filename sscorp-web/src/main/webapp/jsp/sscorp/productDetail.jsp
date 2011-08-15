<%--
    Document   : productDetail
    Created on : 17 Jan, 2011, 12:16:06 AM
    Author     : bhaskar
--%>

<%@include file="/jsp/include.jsp"%>

<logic:iterate id="plf" name="plfs">
    <div id="${plf.productCatId}_${plf.productId}_prodDiv" align="left"
         class="product-content">
        <div class="content-heading roundedCorner">
            <img alt="Expand"
                 src="<%=request.getContextPath()%>/images/plus.gif"
                 title="Expand to view product details"
                 onclick="showProductDetail(this, '${plf.productId}',
                     '${plf.productCatId}')"/>
            ${plf.productName}
        </div>
        <div id="${plf.productCatId}_${plf.productId}_prodDescDiv"
             style="display: none;"
             class="product-description roundedCorner">
            <div id="product-data" class="product-data roundedCorner">
                <c:if test="${plf.material != null}">
                    <span>Material</span> - ${plf.material}<br/>
                </c:if>
                <c:if test="${plf.finish != null}">
                    <span>Finish</span> - ${plf.finish}<br/>
                </c:if>
                <c:if test="${plf.size != null}">
                    <span>Size</span> - ${plf.size}<br/>
                </c:if>
                <c:if test="${plf.way != null}">
                    <span>Way</span> - ${plf.way}<br/>
                </c:if>
                <c:if test="${plf.surfaceDiameter != null}">
                    <span>Surface Diameter</span> - ${plf.surfaceDiameter}<br/>
                </c:if>
                <c:if test="${plf.length != null}">
                    <span>Length</span> - ${plf.length}<br/>
                </c:if>
                <c:if test="${plf.thickness != null}">
                    <span>Thickness</span> - ${plf.thickness}<br/>
                </c:if>
                <c:if test="${plf.gauge != null}">
                    <span>Gauge</span> - ${plf.gauge}<br/>
                </c:if>
                <%--<a href="#">Ask a Question</a>--%>
            </div>
            <div id="${plf.productCatId}_${plf.productId}_product-image"
                 class="product-image roundedCorner">
                <c:if test="${plf.photoName != null}">
                    <c:set var="prod_image_list" value="${plf.photoName}"></c:set>
                    <logic:iterate id="prod_images" name="prod_image_list" length="2">
                        <div class="imageGallery">
                            <img alt="${plf.productName}"
                                 src="${prod_images}"
                                 title="${plf.productName}" />
                            <div class="imageDesc">
                                ${plf.productName}
                            </div>
                        </div>
                    </logic:iterate>
                </c:if>
            </div>
        </div>
    </div>
</logic:iterate>