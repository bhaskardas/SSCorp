/*
 * Javascript file for loading product related data.
 */

$(function(){
    $(".page-heading-links").click(function(){
        new sscorp.Ajax("dispProductCat.html", function(){
            dynamicScriptLoader(this.req.responseText, "contentDiv");
        }, null, "contentDiv");
    });
});

