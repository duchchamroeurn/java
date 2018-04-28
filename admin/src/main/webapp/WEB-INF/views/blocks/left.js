$(function () {
	
	$(".btn-menu").each(function() {
		var obj = $(this);
		var $menus = obj.attr("menu-family");
		if($menus){
			$.each($menus.split(","), function(key, value) {
				if(window.location.pathname.indexOf(value) != -1) {
					obj.closest("li").addClass("active");
					obj.closest('ul').addClass("in");
					obj.closest('ul').closest('li').addClass("active");
				}
			});
		}
	});

});