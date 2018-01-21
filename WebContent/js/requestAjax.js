 $(document).on("click", "#cart", function() {
            	$.get("goToCart", function(responseText) {  
            		alert (responseText);
            	});
            });
 
 $(document).on("click", "#favourite", function() {
 	$.get("myFavourite", function(responseText) { 
  	alert (responseText);
 	});
 });
 
 $(document).on("click", "#submit", function() { 
	 var rankvalue = $("#rank :selected").text();
	 var rank = {
			 rank: rankvalue
	 };
	 
	 $.ajax({
		    type: "POST",
		    url: "vote",
		    contentType: "application/json", // NOT dataType!
		    data: JSON.stringify(rank),
		    success: function(response) {
		        alert (response);
		    }
		});
	 
 	
 });
 
 $(document).on("click", "#cart", function() {
 	alert ("CIAO");
 	$.get("goToCart", function(responseText) {
  	alert (responseText);
 	});
 });
 
 