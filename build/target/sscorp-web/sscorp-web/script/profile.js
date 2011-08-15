/*
 * Javascript file for loading the company profile data.
 */

$(function(){
    $("#profileMainDiv > #profileMenuDiv > #profileMenuWrapper > #profiles > a").click(function(){
        var $linkClicked = $(this);
        var $url = $linkClicked.attr("href");
        $url = $url.substring($url.lastIndexOf("/") + 1);

        new sscorp.Ajax($url, function(){
            document.getElementById("profileContentDiv").innerHTML = 
                                                        this.req.responseText;
            handleBreadcrumbChange(this.req.getResponseHeader("breadcrumb"));
        }, null, "profileContentDiv");
        return false;
    });
});