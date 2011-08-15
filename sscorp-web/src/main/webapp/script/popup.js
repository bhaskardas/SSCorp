var admin = new Object();

admin.popup = function( url, callBack, height, width){
	this.backgrndDivId = "blanket";
	this.div_id = "popUpDiv";
	this.popContentDivId = "popupContent";
	this.popWidth = width != null ? width : 300;
	this.popHeight = height != null ? height : 250;
	this.url = url;
	this.formName = null;
	this.callBack = callBack;
	this.coords = {left:0, top:0};
	
	//this.getCurrentMousePos();
	this.open();
}

admin.popup.prototype.open = function(){
	this.setBckgrndSize(this.div_id);
	this.setWindowPos(this.div_id);
	admin.popup.toggleDisplay.call(this, this.backgrndDivId, 1.0);
	admin.popup.toggleDisplay.call(this, this.div_id, 1.5);
	//if(this.url != null && this.callBack != null){
	//	new admin.ajaxObj(this.url, this.callBack);
	//}
}

admin.popup.toggleDisplay = function(div_id, timeLag) {
	var el = document.getElementById(div_id);
	if(el.style.display == 'none' ) 
	{	
		Effect.Appear(div_id, { duration: timeLag });
	}else{
		Effect.Fade(div_id, { duration: timeLag});
	}
}

admin.popup.prototype.getCurrentMousePos = function(){
	var e = window.event;
		
	if(e.clientX || e.clientY){
		this.coords.left = e.clientX ;
		this.coords.top = e.clientY ;
	}else if(e.pageX || e.pageY){
		this.coords.left = e.pageX;
		this.coords.top = e.pageY;
	}
	
	// include html element space, if applicable
	//if (document.body.parentElement && document.body.parentElement.clientLeft) {
		//var parentBody = document.body.parentElement;
		//this.coords.left += parentBody.scrollLeft - parentBody.clientLeft;
		//this.coords.top += parentBody.scrollTop - parentBody.clientTop;
	//}
}

admin.popup.prototype.setBckgrndSize = function(popUpDivVar) {
	if (typeof window.innerWidth != 'undefined') {
		viewportheight = window.innerHeight;
	} else {
		viewportheight = document.documentElement.clientHeight;
	}
	
	if ((viewportheight > document.body.parentNode.scrollHeight) && (viewportheight > document.body.parentNode.clientHeight)) {
		blanket_height = viewportheight;
	}else {
		if (document.body.parentNode.clientHeight > document.body.parentNode.scrollHeight) {
			blanket_height = document.body.parentNode.clientHeight;
		} else {
			blanket_height = document.body.parentNode.scrollHeight;
		}
	}
	
	var blanket = document.getElementById(this.backgrndDivId);
	blanket.style.height = blanket_height + 'px';
}

admin.popup.prototype.setWindowPos = function(popUpDivVar) {
	var popUpDiv = document.getElementById(popUpDivVar);
	
	//set the width and height of the popup content div
	var popContentDiv = document.getElementById(this.popContentDivId);
	popContentDiv.style.width = this.popWidth + "px";
	popContentDiv.style.height = this.popHeight + "px";
	
	//set the height and width of the main popup div
	popUpDiv.style.width = (this.popWidth+20) + "px";
	popUpDiv.style.height = (this.popHeight+45) + "px";
	
	popUpDiv.style.left = document.body.scrollLeft + document.body.clientWidth/2 - (this.popWidth+20)/2 + "px";
	popUpDiv.style.top = document.body.scrollTop + document.body.clientHeight/2 - (this.popHeight+45)/3 + "px";
}

function closePopup(){
	admin.popup.toggleDisplay.call(this, "popUpDiv", 1.0);
	admin.popup.toggleDisplay.call(this, "blanket", 1.5);
}
