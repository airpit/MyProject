$(document).ready(function(){
	
	$('#neighborTab a[href="#showNeighbor"]').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
	});
	
	$('#neighborTab a[href="#searchNeighbor"]').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
	});	
	

	$("ul.nav-tabs > li > a").on("shown.bs.tab", function (e) {
	        var id = $(e.target).attr("href").substr(1);
	        window.location.hash = id;
	 });
	    // on load of the page: switch to the currently selected tab
	    var hash = window.location.hash;
	$('#neighborTab a[href="' + hash + '"]').tab('show');
	
	
	$('#searchBtn').click(function(){
		alert($('#category').val());	
		return false;	
	});

	
	$(".neighbor-list-li .neighbor-home-a").click(function(){
		
		var index = $("li .neighbor-home-a").index(this);
//		alert($("#homeCode").val());
		$("#neighbor-form").submit();
	});

	
});
