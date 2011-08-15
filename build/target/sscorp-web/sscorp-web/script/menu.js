//var $j = jQuery.noConflict();

$(function() {
    /**
    * for each menu element, on mouseenter,
    * we enlarge the image, and show both sdt_active span and
    * sdt_wrap span. If the element has a sub menu (sdt_box),
    * then we slide it - if the element is the last one in the menu
    * we slide it to the left, otherwise to the right
    */
    $('#sscorp_menu > li').bind('mouseenter',function(){
        var $elem = $(this);
        $elem.find('.sscorp_currentMenu')
        .css("display", "block")
        .stop(true)
        .animate({
            'left' : '0px',
            'top':'40px',
            'width' : '100%',
            'height' : '100px'
        },500,'easeOutBack', function(){
            var $computedHeight = $elem.find('.sscorp_currentMenu').height();
            var $computedLeft = $elem.find('.sscorp_currentMenu').width();
            var $computedWidth = $elem.find('.sscorp_currentMenu').width();
            var $sub_menu = $elem.find('.sscorp_submenu');
            if($sub_menu.height() > $computedHeight){
                $computedHeight = $sub_menu.height() + 'px';
            }
            if($sub_menu.width() > $computedWidth){
                $computedWidth = $sub_menu.width() + 25;
                $computedWidth = $computedWidth + 'px';
            }
            if($sub_menu.length){
                var left = $computedLeft + 'px';
                if($elem.parent().children().length == $elem.index()+1)
                    left = '-' + ($computedLeft + 10) + 'px';
                $sub_menu.css("display", "block")
                .animate({
                    'left':left,
                    'top' : '40px',
                    'width' : $computedWidth,
                    'height' : $computedHeight
                },200);
            }
        })
        .andSelf()
        .find('#cMenuLink')
        .css("display", "block")
        .andSelf()
        .find('.menuImage')
        .css("display", "block")
        .find('img')
        .stop(true)
        .animate({
            'width':'100%',
            'height':'96px',
            'left':'0px'
        },400,'easeOutBack')
        .andSelf();
    }).bind('mouseleave',function(){
        var $elem = $(this);

        var $sub_menu = $elem.find('.sscorp_submenu');
        if($sub_menu.length)
            $sub_menu.hide().css({
                left: '0px',
                height: '',
                width : ''
            });

        $elem.find('.sscorp_currentMenu')
        .stop(true)
        .animate({
            'width':'0px',
            'height':'0px',
            'left':'0px',
            'top' : '0px'
        }, 500)
        .andSelf()
        .find('#cMenuLink')
        .css("display", "none")
        .andSelf()
        .find('img')
        .stop(true)
        .animate({
            'width':'0px',
            'height':'0px',
            'left':'0px'
        },400)
        .find('.menuImage')
        .css("display", "none")
        .andSelf();
    });
    /**
     * function to display the page corresponding to a menu link when clicked.
     */
    $("#sscorp_menu > li > a, #sscorp_menu > li > .sscorp_currentMenu > a, #sscorp_menu > li > .sscorp_submenu > a, #footerDiv > a").click(function(){
        var $linkClicked = $(this);
        var $url = $linkClicked.attr("href");
        $url = $url.substring($url.lastIndexOf("/") + 1);

        /**
         * Call back for the all menu links clicked. Populates the content
         * box and also displays the navigational menu content.
         * Also sets the breadcrumb text.
         */
        new sscorp.Ajax($url, function(){
            dynamicScriptLoader(this.req.responseText, "contentDiv");
            handleBreadcrumbChange(this.req.getResponseHeader("breadcrumb"));
            if($url == "profile.html"){
                new sscorp.Ajax("aboutUs.html", function(){
                    document.getElementById("profileContentDiv").innerHTML =
                                                        this.req.responseText;
                }, null, "profileContentDiv");
            }
        }, null, "contentDiv");
        return false;
    });
});