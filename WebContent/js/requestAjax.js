 $(document).on("click", "#cart", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
            	$.get("goToCart", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
            		alert (responseText);
            	});
            });
 
 $(document).on("click", "#favourite", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
 	$.get("myFavourite", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
  	alert (responseText);
 	});
 });
 
 $(document).on("click", "#cart", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
 	alert ("CIAO");
 	$.get("goToCart", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
  	alert (responseText);
 	});
 });
 
 $(document).on("click", "#cart", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
 	alert ("CIAO");
 	$.get("goToCart", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
  	alert (responseText);
 	});
 });
 
 