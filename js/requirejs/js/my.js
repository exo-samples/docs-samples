require.config({
	baseUrl: "js",
	paths: {
		jquery: "jquery-1.7.1",
		util: "util"
	}
});

function myClick(){
	require(["util", "jquery"], function(util, $){
		$("#result").text(util.count());
	});
}
