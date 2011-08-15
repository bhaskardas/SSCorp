/*
 * Main javascript file to execute super sales corporation specific
 * functionalities.
 */

/**
 * Function to be called on load of the page.
 */
function init(){
    new sscorp.Ajax("home.html", function(){

    }, null, "contentDiv");

    new sscorp.Ajax("profile.html", function(){
        dynamicScriptLoader(this.req.responseText, "contentDiv");
        handleBreadcrumbChange(this.req.getResponseHeader("breadcrumb"));
        new sscorp.Ajax("aboutUs.html", function(){
            document.getElementById("profileContentDiv").innerHTML =
            this.req.responseText;
        }, null, "profileContentDiv");
    }, null, "contentDiv");
}

/**
 * Call back for the init() function. Populates the content box and also
 * displays the navigational menu content.
 */
function callBack4Init(){
    dynamicScriptLoader(this.req.responseText, "contentDiv");
    handleBreadcrumbChange(this.req.getResponseHeader("breadcrumb"));
    new sscorp.Ajax("profile.html", callBack4Init,
        null, "contentDiv");
}

/**
 * Function to dynamically load the javascript files.
 * TODO: remove the script tag entries from the returned html.
 * TODO: code to load css files dynamically.
 */
function dynamicScriptLoader(response, divId){
    //create a transient div to extract the javascript file
    //urls that are to dynamically loaded.
    var sampleDiv = document.createElement("div");
    sampleDiv.id = "sampleDiv";
    sampleDiv.innerHTML = response;
    sampleDiv.style.display = "none";
    document.body.appendChild(sampleDiv);

    var d = document.getElementById("sampleDiv").getElementsByTagName("script");
    var $javascriptUrl;

    //simple display of content
    document.getElementById(divId).innerHTML = response;

    //Jquery based display of content
    //    $("#" + divId).html(response)
    //                    .fadeIn(5000, "swing");

    for(var count = 0; count < d.length; count++){
        $javascriptUrl = d[count].src;
        //load the javascript files dynamically after the main
        //index page has finished loading.
        $.getScript($javascriptUrl, function(){
            //custom code execution
            //currently this function is being used by many other functions.
            //cant add any custom code here. The code added has to be generic.
            });
    }

    //remove the dummy div from the body of the document.
    document.body.removeChild(sampleDiv);
}

/**
 * This function gets the list of products based on a product category id
 */
function getProducts(productCatId, prodCatName){
    new sscorp.Ajax("dispProducts.html?productCatId=" + productCatId +
        "&prodCatName=" + prodCatName, function(){
            dynamicScriptLoader(this.req.responseText, "contentDiv");
        }, null, "contentDiv");
}

/**
 * this functions displays the details of each product in the list.
 */
function showProductDetail(imageObj, productId, productCatId){
    var $prodDescDiv = $("#" + productCatId + "_" + productId + "_prodDescDiv");
    if(imageObj.src.search("plus.gif") > 0){
        imageObj.src = imageObj.src.replace("plus", "minus");
        imageObj.title = "Collapse";
        $($prodDescDiv).slideDown(3000, "swing");
    }else{
        $($prodDescDiv).slideUp(2000, "swing");
        imageObj.src = imageObj.src.replace("minus", "plus");
    }
}

/**
 * function to load the breadcrumb links.
 */
function handleBreadcrumbChange(jsonResponse){
    //get the div object from DOM to append the links.
    var breadCrumbLinks = document.getElementById("breadCrumbLinks");

    //return false if response does not contain the "breadcrumb" variable.
    if(jsonResponse == null || jsonResponse == ""){
        breadCrumbLinks.innerHTML = "";
    }else{
        //get the equivalent javascript object for the json string.
        var breadcrumbObj = eval('(' + jsonResponse + ')');

        //reset the contents of the div to empty for a fresh request loading.
        breadCrumbLinks.innerHTML = "";

        var linkObj = null;

        if(breadcrumbObj.isRefresh == true){
            if(checkObject(breadcrumbObj.menuName)){
                linkObj = createNewELement("a");
                setElementAttr(linkObj, "href", breadcrumbObj.menuUrl)
                linkObj.onclick = function(){
                    var url = linkObj.getAttribute("href");
                    setElementAttr(linkObj, "href", "javascript:void(0);")
                    url = url.substring(url.lastIndexOf("/") + 1);

                    new sscorp.Ajax(url, function(){
                        dynamicScriptLoader(this.req.responseText, "contentDiv");
                        handleBreadcrumbChange(this.req.getResponseHeader("breadcrumb"));
                        if(url == "profile.html"){
                            new sscorp.Ajax("aboutUs.html", function(){
                                document.getElementById("profileContentDiv")
                                .innerHTML = this.req.responseText;
                            }, null, "profileContentDiv");
                        }
                    }, null, "contentDiv");
                //return false;
                }
                appendObject(linkObj, addTextNode(breadcrumbObj.menuName));
                appendObject(breadCrumbLinks, linkObj);
            }
            if(checkObject(breadcrumbObj.submenuName)){
                appendObject(breadCrumbLinks, addTextNode(" :: "));
                appendObject(breadCrumbLinks,
                    addTextNode(breadcrumbObj.submenuName));
            }
        }
    }
}

/**
 * Check for nullability and empty for a given object.
 */
function checkObject(obj){
    return obj != "" && obj != null ? true : false;
}

/**
 * Adds a text node to the document with the specified text as parameter.
 */
function addTextNode(text){
    return document.createTextNode(text);
}

/**
 * Appends a dom element to the passed in object (another DOM element)
 */
function appendObject(obj, value){
    obj.appendChild(value);
}

/**
 * Creates and returns an element with the passed in name as the name of the
 * element.
 */
function createNewELement(elementName){
    return document.createElement(elementName);
}

/**
 * Sets the attribute for a given DOM element passes as parameter to the
 * function.
 */
function setElementAttr(object, attribute, value){
    object.setAttribute(attribute, value);
}

/**
 * sets the interval after which the original marquee message will again be
 * displayed.
 */
function setInterval4MarqueeMsgChange(){
    var retVal = window.setTimeout("restoreMarqueeMessage()", 10000,"javascript");
    window.clearTimeOut(retVal);
}

/**
 * Restore the original marquee message in the breadcrumb.
 */
function restoreMarqueeMessage(){
    $("#globalMessages").css("color", "#000").css("background-color", "#668BB2")
    	.html("<marquee behavior='scroll'><span>Member of Delhi Chamber of Commerce & Delhi Electrical Traders Association</span></marquee>");
}

/**
 * test the pattern(regex) with the value and return true of false.
 */
function testRegExPattern(regex, value){
    return regex.test(value);
}

/**
 * Calculates the height of elements with id - idName1 and idname2; and renders
 * the heights of both the elements as equal.
 */
function calculateElementHeight(idName1, idName2){
	var element1 = document.getElementById(idName1);
	var element2 = document.getElementById(idName2);
    if(element1.offsetHeight > element2.offsetHeight){
        element2.style.height = element1.offsetHeight + "px";
        element1.style.height = element2.style.height;
    }else if(element1.offsetHeight < element2.offsetHeight){
        element1.style.height = element2.offsetHeight + 10 + "px";
        element2.style.height = element1.style.height;
    }
}

var arr;
var count = 0;

/*
 * routine for recursively/infinitely looping through the available set of
 * banner images in the main page.
 */
function bannerImageRecursion(){
	var banner = document.getElementById("banner");
	var bannerImage = banner.getElementsByTagName("img")[0];

	if(count < arr.length - 1){
		bannerImage.src = arr[count++];
	}else{
		bannerImage.src = arr[count];
		count = 0;
	}
}

window.onload = function(){
	init();
    arr = document.getElementById("bannerImages").value.split("##");
	setInterval("bannerImageRecursion()", 10000);

}