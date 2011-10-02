/*
 * Ajax framework for asynchronously calling the server from javascript.
 */

var sscorp = new Object();
sscorp.READY_STATE_UNINITIALIZED=0;
sscorp.READY_STATE_LOADING=1;
sscorp.READY_STATE_LOADED=2;
sscorp.READY_STATE_INTERACTIVE=3;
sscorp.READY_STATE_COMPLETE=4;

/**
 * constructor function for admin.ajaxObj object
 */
sscorp.Ajax = function(url, callback, formName, divId, callback4Error){
    //the destinaltion to send the request to
    this.url = url;

    //function where the response from the current request will be handled.
    this.callback = callback;

    //assigns a default error callback function is error handler is null
    this.callback4Error= callback4Error || defaultCallback4Error;

    this.formName = formName;
    this.req = null;

    //Type of request: GET or POST. Default is GET
    this.reqMethod = null;

    //the target container where the response will be shown
    this.divId = divId;

    if(formName != null){
        this.reqMethod = "POST";
    }else{
        this.reqMethod = "GET";
    }

    this.assignDefCallBackHandler();
    this.createRequest();
    this.prepareAjaxLoader();

    if(this.req != null){
        this.fireRequest();
    }else{
        //TODO : alert or function call
        alert("XMLHTTPRequest Not Supported");
    /* XMLHTTPRequest not supported */
    }
};

/**
 * if no callback is provided, then execute the default call back function
 */
sscorp.Ajax.prototype.assignDefCallBackHandler = function(){
    if(this.callback == null){
        if(this.divId == null){
            alert("No region found for refresh.");
            return false;
        }else{
            this.callback = defaultCallBack;
        }
    }
};

/**
 * create the javascript XMLHttpRequest object
 */
sscorp.Ajax.prototype.createRequest = function(){
    if(window.XMLHttpRequest){
        this.req = new XMLHttpRequest();
    }else if(window.ActiveXObject){
        try{
            this.req = new ActiveXObject("Msxml2.XMLHTTP");
        }catch(e){
            try{
                this.req = new ActiveXObejct("Microsoft.XMLHTTP");
            }catch(e1){
                alert("XMLHttpRequest Object creation not successful...! Please try again...");
                this.req = null;
            }
        }
    }
};

/**
 * display the loader image till the request is processed
 * and the response is shown
 */
sscorp.Ajax.prototype.prepareAjaxLoader = function(){
    if(this.divId != null){
        var html = "<div id='ajaxLoader' style='margin: auto;padding: 8px;'>";
        html += "<img src='images/ajax-loader.gif' " +
                "alt='Loading Data' " +
                "title='Loading' /></div>";

        document.getElementById(this.divId).innerHTML = html;
    }
};

/**
 * Go to the server to execute the request and fetch the response.
 */
sscorp.Ajax.prototype.fireRequest = function(){
    if(this.req){
        try{
            var loader=this;
            this.req.onreadystatechange=function(){
                sscorp.Ajax.prototype.onReadyState.call(loader);
            }

            this.req.open(this.reqMethod, this.url, true);
            this.req.setRequestHeader("Accept", "text/xml");
            
            //IE Hack
            this.req.setRequestHeader("If-Modified-Since", "Fri, 1 Apr 2011 00:00:00 GMT");
            if(this.formName != null){
                this.req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                var postData = sscorp.Ajax.prototype.getFormData.call(loader);
                this.req.send(postData);
            }else{
                this.req.send(null);
            }
        }catch(e){
            //alert(e);
        //this.callback4Error.call(this);
        }
    } else {
        alert("XMLHttpRequest object not created. Please Try again...");
    }
};

/**
 * To check the status of the request that has been sent.
 */
sscorp.Ajax.prototype.onReadyState = function(){
    try{
        if (this.req.readyState == sscorp.READY_STATE_COMPLETE){
            if (this.req.status == 200 || this.req.status == 0){
                //could have also been "this.callback();" - this would also work
                this.callback.call(this);
            }else{
                //could have also been "this.callback4Error();" - this would also work
                this.callback4Error.call(this);
            }
        }
    }catch(e){
        alert(e);
    }
};

/**
 * Find the form instance on the html page.
 */
sscorp.Ajax.prototype.findForm = function(formName){
    var form;
    if(formName != null){
        form = document.forms[formName];
    }else{
        alert("Error: Form not found.");
        return false;
    }

    if(typeof form != "object"){
        alert("Error: Form object with the name [" + formName + "] not found.");
        return false;
    }

    return form;
};

/**
 * Get the data from all the form elements for post submitting the reuqest.
 */
sscorp.Ajax.getFormData = function(){
    var form = sscorp.Ajax.prototype.findForm(this.formName);
    var postData = "";
    for ( var count = 0; count < form.elements.length; count++) {
        var element = form.elements[count];
        if (element.tagName.toUpperCase() == "INPUT") {
            if (element.type.toUpperCase() == "CHECKBOX"
                || element.type.toUpperCase() == "RADIO") {
                if (element.checked) {
                    postData += "&" + encodeURIComponent(element.name) + "="
                    + encodeURIComponent(element.value);
                }
            }else if (element.type.toUpperCase() != "BUTTON") {
                postData += "&" + encodeURIComponent(element.name) + "="
                + encodeURIComponent(element.value);
            }
        }else if (element.tagName.toUpperCase() == "SELECT") {
            for (var j = 0; j < element.options.length; j++) {
                var opt = element.options[j];
                if (opt.selected)
                    postData += "&" + encodeURIComponent(element.name) + "="
                    + encodeURIComponent(opt.value);
            }
        }else if (element.tagName.toUpperCase() == "TEXTAREA") {
            postData += "&" + encodeURIComponent(element.name) + "="
            + encodeURIComponent(element.value);
        }
    }

    return postData;
};

/**
 * Default call back handler
 */
function defaultCallBack(){
    document.getElementById(this.divId).innerHTML = this.req.responseText;
};

/**
 * Default error handler.
 * @return
 */
function defaultCallback4Error(){
    var error = confirm("Error!!!Could not complete the request. \n\
                            Error Code is : " + this.req.status +
        "\n Would you like to view the error returned \n\
                                from the server.");

    if(error){
        var errorWindow = window.open("", "Debug Window");
        if(errorWindow != null){
            errorWindow.document.write("<html><head><title>");
            errorWindow.document.write("Error Code:" + this.req.status);
            errorWindow.document.write("</title></head><body>");
            errorWindow.document.write("ERROR!!! <br/>");
            errorWindow.document.write("Could not complete the request due \n\
                                        to some error in the bacckend. <br/>");
            errorWindow.document.write("Request Information: <br/>");
            errorWindow.document.write("<b>Ready State:</b> " +
                                        this.req.readyState);
            errorWindow.document.write("<br/><b>Request Status:</b> " +
                                        this.req.status);
            errorWindow.document.write("<br/><b>Header Information: </b><br/>");
            errorWindow.document.write("<b>Response: </b><br/>");
            errorWindow.document.write(this.req.responseText);
        }else{
            alert("Please disable your pop-up blocker for this site to \n\
                    be able to view the error message.");
        }
    }
};