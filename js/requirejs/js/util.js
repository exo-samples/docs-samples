define(function(){
	var counter = 0;
	var count = function(){
		if (counter > 10) {
			alert("Stop! You're too excited!");
		}
		return (++counter);
	}
	return {
		count: count
	};
});
