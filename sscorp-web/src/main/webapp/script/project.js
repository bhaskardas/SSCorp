$(function(){
    //all the project categories
    var $projectCategories = $("#projCat .projCat_item");

    //count of project categories
    var count_projCat = $projectCategories.length;

    //if one of the project categories is expanded then folded is true
    var folded = false;

    //timeout to trigger the mouseenter event on the menu items
    var timeout;
    var $projectCatId;

    /**
     * bind the mouseenter, mouseleave to each project categories:
     * - shows / hides image and submenu
     * bind the click event to the list elements (submenu):
     * - hides all items except the clicked one,
     * and shows the content for that item
     */
    $projectCategories
    .unbind("mouseenter")
    .bind("mouseenter",m_enter)
    .unbind("mouseleave")
    .bind("mouseleave",m_leave)
    .find(".projCat_desc > ul > li")
    .bind("click",function(){
        var $li_e = $(this);
        $projectCatId = $li_e.attr("id");
        //if project category is already folded,
        //just replace the content
        if(folded){
            hideContent();
            showContent();
        }else{ //fold and show the project category details
            fold($li_e);
        }
    });

    /**
     * mouseenter function for the project categories
     * the timeout is used to prevent this event
     * to trigger if the user moves the mouse with
     * a considerable speed through the project categories
     */
    function m_enter(){
        var $this = $(this);
        clearTimeout(timeout);
        timeout = setTimeout(function(){
            //img
            $this.find("img")
            .stop()
            .css("display", "block")
            .animate({
                "height":"298px"
            },400);

            //projCat_desc ul
            $this.find(".projCat_desc > ul")
            .stop()
            .css("display", "block")
            .animate({
                "height":"120px"
            },400);
        },200);
    }

    /**
     * mouseleave function for the project categories
     */
    function m_leave(){
        var $this = $(this);
        clearTimeout(timeout);
        //img
        $this.find("img")
        .stop()
        .animate({
            "height":"0px"
        }, 400);

        //projCat_desc ul
        $this.find(".projCat_desc > ul")
        .stop()
        .css("display", "none")
        .animate({
            "height":"0px"
        }, 400);
    }

    /**
     * Back to project categories page - unfolds all the project categories.
     * Shows all the project categories;
     * Also hides any project category image / submenu
     * that might be displayed.
	 */
    $("#go_back").live("click", function(e){
    	$("#project_container")
        .stop()
        .animate({
            "left":"-100%"
        },700,function(){
            var d = 100;
            var step = 0;
            $projectCategories.each(function(){
                var $item = $(this);
                $item.find("img")
                .stop()
                .animate({
                    "height":"0px"
                },200)
                .andSelf()
                .find(".projCat_desc > ul")
                .stop()
                .css("display", "none")
                .animate({
                    "height":"0px"
                },200);

                $item.stop().animate({
                    "marginLeft":"0px"
                },d += 200,function(){
                    ++step;
                    if(step == count_projCat-1){
                        folded = false;
                        $projectCategories.unbind("mouseenter")
                        .bind("mouseenter",m_enter)
                        .unbind("mouseleave")
                        .bind("mouseleave",m_leave);
                        hideContent();
                    }
                });
            });
        });
        $("#go_back").css("display", "none");
        
        e.stopPropagation();
        //IE Hack for stopping event propagation.
        return false;
    });

    /**
     * hides all the project categories except the clicked one
     * after that, the content is shown
     */
    function fold($li_e){
        var $item = $li_e.closest(".projCat_item");
        var d = 100;
        var step = 0;
        $projectCategories.unbind("mouseenter mouseleave");
        $projectCategories.not($item).each(function(){
            var $item = $(this);
            $item.stop()
            .animate({
                "marginLeft":"-12.4%"
            }, d += 200,function(){
                ++step;
                if(step == count_projCat-1){
                    folded = true;
                    showContent();
                }
            });
        });
    }

    /**
     * function to show the content
     */
    function showContent(){
        $("#project_container").stop().animate({
            "left":"160px"
        },200,function(){
        new sscorp.Ajax("dispProjects.html?projectCategoryId=" +
            $projectCatId + "&projImage=" +
            document.getElementById("projImage_" + $projectCatId).value,
            callBack4ProjectList, null, "project_container");
    	});
    }

    /**
     * 
     */
    function callBack4ProjectList(){
        $("#project_container").html(this.req.responseText);
        $("#go_back").fadeIn();
        $(".project_content").fadeIn();
    }

    /**
     * function to hide the content
     */
    function hideContent(){
        $("#project_content").find("div").hide();
        $("#project_container").find("div").html("").hide();
    }
});