<%--
    Document   : productCategory
    Created on : 17 Sep, 2010, 12:24:39 AM
    Author     : bhaskar
--%>

<%@include file="/jsp/include.jsp"%>

<logic:present name="prodCategories" scope="application">
    <div>
        <h3 class="page-heading roundedCorner">Product Categories</h3>
    </div>
    <div id="prodCat-container" class="prodCat-container roundedCorner">
        <div id="image-container" onmousemove="">
            <logic:iterate id="prodCat" name="prodCategories">
                <div class="image" id="prodCat-image"
                     onclick="getProducts('${prodCat.productCatId}',
                         '${prodCat.productCatName}')">
                    <div class="mask"></div>
                    <img src="${prodCat.prodCategoryImage}"
                         alt="${prodCat.productCatName}"
                         title="${prodCat.productCatName} - ${prodCat.prodCategoryDesc}"/>
                    <div id="prodCat-header" class="prodCat-header">
                        ${prodCat.productCatName}
                    </div>
                </div>
            </logic:iterate>
        </div>
    </div>
</logic:present>
<logic:notPresent name="prodCategories" scope="application">
    Data could not be displayed. Please try again after some time.
</logic:notPresent>