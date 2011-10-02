/*
 * JS file for handling the enquiry form page.
 */

$(function(){
    //event handler when the submit button is clicked.
    $("#querySubmit").click(function(){
        //check empty for any required fields.
        if($("#subjectId").val() == "" || $("#queryId").val() == ""
           || $("#aboutId").val() == "" || $("#ceId").val() == ""
           || $("#fullNameId").val() == ""){

            $("#globalMessages").css("color", "#fff")
            					.css("background-color", "#006616")
                                .html("Fields marked as * are mandatory to be filled.");
            setInterval4MarqueeMsgChange();
           return false;
        }

        //check for valid email type.
        if(!checkEmail($("#ceId").val())){
           $("#globalMessages").css("color", "#fff")
            					.css("background-color", "#006616")
                                .html("Email format is not valid. Kindly, enter a valid email.");
            setInterval4MarqueeMsgChange();
           return false;
        }

        //check for valid phone no type - should have only digits.
        if(!testRegExPattern(/^[0-9]*$/, $("#phoneNoId").val())){
            $("#globalMessages").css("color", "#fff")
                                .css("background-color", "#006616")
                                .html("Phone number can only contain digits.");
            setInterval4MarqueeMsgChange();
            return false;
        }

        //proceed with the save process and display the InProgress icon
        $("#saveInProcess").css("display", "block");

        alert("sdfsdf");
        //make an ajax call to the server.
        new sscorp.Ajax("saveUserQuery.html", function(){
        	alert("sdfsdf");
            $("#saveInProcess").css("display", "none");
            var response = this.req.getResponseHeader("message");
            if(response != null && response != ""){
               $("#globalMessages").css("color", "#fff")
                                .css("background-color", "#006616")
                                .html(response);
            }
            setInterval4MarqueeMsgChange();
       }, "userQueryForm");
   });

   //check for the maxLength of the query field (textarea)
   $("#queryId").bind("keyup blur", function(){
       var maxLength = 2980;
       var value = $(this).val();

       if(value.length > maxLength){
       		$(this).val(value.slice(0, maxLength));
           $("#globalMessages").css("color", "#fff")
                                .css("background-color", "#006616")
                                .html("Maximum length reached for your query. Cannot accept more characters.");
            setInterval4MarqueeMsgChange();

           return false;
       }
   });
});

/**
 * Reference: Sandeep V. Tamhankar (stamhankar@hotmail.com),
 * http://javascript.internet.com
 */
function checkEmail(emailStr) {
    if (emailStr.length == 0) {
        return true;
    }
    var emailPat=/^(.+)@(.+)$/;
    var specialChars="\\(\\)<>@,;:\\\\\\\"\\.\\[\\]";
    var validChars="\[^\\s" + specialChars + "\]";
    var quotedUser="(\"[^\"]*\")";
    var ipDomainPat=/^(\d{1,3})[.](\d{1,3})[.](\d{1,3})[.](\d{1,3})$/;
    var atom=validChars + '+';
    var word="(" + atom + "|" + quotedUser + ")";
    var userPat=new RegExp("^" + word + "(\\." + word + ")*$");
    var domainPat=new RegExp("^" + atom + "(\\." + atom + ")*$");
    var matchArray=emailStr.match(emailPat);
    if (matchArray == null) {
        return false;
    }
    var user=matchArray[1];
    var domain=matchArray[2];
    if (user.match(userPat) == null) {
        return false;
    }
    var IPArray = domain.match(ipDomainPat);
    if (IPArray != null) {
        for (var i = 1; i <= 4; i++) {
            if (IPArray[i] > 255) {
                return false;
            }
        }
        return true;
    }
    var domainArray=domain.match(domainPat);
    if (domainArray == null) {
        return false;
    }
    var atomPat=new RegExp(atom,"g");
    var domArr=domain.match(atomPat);
    var len=domArr.length;
    if ((domArr[domArr.length-1].length < 2) ||
        (domArr[domArr.length-1].length > 3)) {
        return false;
    }
    if (len < 2) {
        return false;
    }
    return true;
}