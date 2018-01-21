 $(document).on("click", "#cart", function() {
            	$.get("goToCart", function(responseText) {  
            		showpopup ();
            	});
            });
 
 $(document).on("click", "#favourite", function() {
 	$.get("myFavourite", function(responseText) { 
		showpopup ();
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
        		showpopup (response);
		    }
		});
	 
 	
 });
 
 $(document).on("click", "#cart", function() {
 	alert ("CIAO");
 	$.get("goToCart", function(responseText) {
  	alert (responseText);
 	});
 });
 
 function showpopup(message){
	 $('#message').text(message);
	 $('.popup').css({
	      'transform':'translateY(0)',
	      'z-index':'999',
	      'height':'250px'
	    });
	    
	    $('body').addClass('overlay');
	    
	    $('.popup h1').animate({
	      left:'0'
	    },1000);
	    
	    $(this).css({
	      'z-index':'-1'
	    });
	    
	    $('.popup > .close').on('click',function(){
	      $(this).parent().css({
	      'transform':'translateY(-300%)',
	      'height':'0px'
	     });
	     
	      $('body').removeClass('overlay');
	      $(this).parent().siblings('.btn').css({
	        'z-index':'1'
	      });
	    });
 }
 