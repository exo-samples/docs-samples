(function(util, $){
	$(document).ready(function(){
		$("body").on("click", ".counter-portlet button", function(){
			$("#result").text(util.count());
		});	
	});	
})(util, jq);
