<!DOCTYPE html>
<!--
    Document            : sscorp_main.jsp
    Created on          : 14 Aug, 2010, 3:02:53 PM
    Author              : bhaskar
    Version             : 1.0
    Project 		: SSCorp
    Module  		:
    Program 		: sscorp_main.jsp
    Description 	:JSP for defining the common  layout of the page.

   REVISION HISTORY

    S.No.  				:
    Author  			:
    Version             : 1.0
    Date of Revision    :
    Defect Id           :
    Revision Details    : Initial Release
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <link rel="stylesheet"
              href="<%=request.getContextPath()%>/css/base.css"
              type="text/css" />
        <link rel="stylesheet"
              href="<%=request.getContextPath()%>/css/menu.css"
              type="text/css" />
        <link rel="stylesheet"
              href="<%=request.getContextPath()%>/css/product.css"
              type="text/css" />
        <link rel="stylesheet"
              href="<%=request.getContextPath()%>/css/project.css"
              type="text/css" />
        <link rel="stylesheet"
              href="<%=request.getContextPath()%>/css/profile.css"
              type="text/css" />

        <script type="text/javascript"
                src="<%=request.getContextPath()%>/script/jquery/jquery-1_4_4_min.js">
        </script>
        <script type="text/javascript"
                src="<%=request.getContextPath()%>/script/jquery/jquery.easing.1.3.js">
        </script>
        <script type="text/javascript"
                src="<%=request.getContextPath()%>/script/menu.js">
        </script>
        <script type="text/javascript"
                src="<%=request.getContextPath()%>/script/ajax.js">
        </script>
        <script type="text/javascript"
                src="<%=request.getContextPath()%>/script/base.js">
        </script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Super Sales Corporation</title>
    </head>
    <body>
        <div id="mainLayoutDiv" class="mainBody">
            <div id="headerDiv" class="headerDiv">
                <%@include file="../layout/header.jsp"%>
            </div>
            <div id="banner" class="banner">
                <input type="hidden" name="bannerImages" id="bannerImages"
                        value="${applicationScope.bannerImages}"/>
                <img src="<%=request.getContextPath()%>/images/vision-image.jpg"
                     alt="Super Sales Corporation" width="960" height="172"/>
            </div>
            <div id="breadCrumbDiv"  class="breadCrumbDiv">
                <%@include file="../layout/breadCrumb.jsp"%>
            </div>
            <hr/>
            <div id="contentDiv"  class="contentDiv roundedCorner">
            </div>
            <div id="footerDiv"  class=" footerDiv">
                <%@include file="../layout/footer.jsp"%>
            </div>
        </div>
    </body>
</html>