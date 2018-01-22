 $(document).on("click", "#cart", function() {
            	$.get("goToCart", function(responseText) {  
            		showpopup (responseText);
            		scroll(200,200);
            	});
            });
 
 $(document).on("click", "#favourite", function() {
 	$.get("myFavourite", function(responseText) { 
		showpopup (responseText);
		scroll(200,200);
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
        		scroll(200,200);
		    }
		});
	 
 	
 });
 
 $("#add").click(function() {
	 var idvalue = $(this).val();
	 var id = {
			 user: idvalue,
			 action: "add"
	 }
	 $.ajax({
		    type: "POST",
		    url: "friend",
		    contentType: "application/json", // NOT dataType!
		    data: JSON.stringify(id),
		    success: function(response) {
        		showpopup (response);
        		scroll(200,200);
		    }
		});
 });
 
 $("#remove").click(function() {
	 var idvalue = $(this).val();
	 var id = {
			 user: idvalue,
			 action: "remove"
	 }
	 $.ajax({
		    type: "POST",
		    url: "friend",
		    contentType: "application/json", // NOT dataType!
		    data: JSON.stringify(id),
		    success: function(response) {
        		showpopup (response);
        		scroll(200,200);
		    }
		});
 });
 
 $("#decline").click(function() {
	 var idvalue = $(this).val();
	 var id = {
			 user: idvalue,
			 action: "decline"
	 }
	 $.ajax({
		    type: "POST",
		    url: "friend",
		    contentType: "application/json", // NOT dataType!
		    data: JSON.stringify(id),
		    success: function(response) {
        		showpopup (response);
        		scroll(200,200);
		    }
		});
 });
 
 $("#accept").click(function() { 
	 var idvalue = $(this).val();
	 var id = {
			 user: idvalue,
			 action: "accept"
	 }
	 $.ajax({
		    type: "POST",
		    url: "friend",
		    contentType: "application/json", // NOT dataType!
		    data: JSON.stringify(id),
		    success: function(response) {
        		showpopup (response);
        		scroll(200,200);
		    }
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
 