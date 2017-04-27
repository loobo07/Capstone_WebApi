$(document).ready(function() {
	setInterval(checkForStatus, 2000);
	
	
	function checkForStatus(){
	    $.ajax({
	        url: "/cloudtest/update", 
	        type: "GET",
	        success: function( inst ) {
	            updateTable(inst)
	        },
	        error: function(data){
	        	console.log(data);
	        }
	    });
	}
	
	function updateTable(objStr){
		var objJson = JSON.parse(objStr);
		for (var key in objJson) {
			var updateRow = document.getElementById(key);
			var oldStatus = $(updateRow).children()[1];
			if(oldStatus.innerHTML != objJson[key]){
				console.log(oldStatus.innerHTML + "->" + objJson[key]);
				oldStatus.innerHTML = objJson[key];
			}
		}
		
	}

		 
});