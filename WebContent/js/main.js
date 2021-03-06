;(function () {
	
	'use strict';
	
	var provaclick = function (){
		 $(document).on("click", "#somebutton", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
             window.alert("qui ci passo");
         	$.get("someservlet", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                 $("#somediv").text(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
             });
         });
	};
	
	

	// Click outside of offcanvass
	var mobileMenuOutsideClick = function() {

		$(document).click(function (e) {
	    var container = $("#-offcanvas, .js--close-offcanvas");
	    if (!container.is(e.target) && container.has(e.target).length === 0) {

	    	if ( $('#-offcanvas').hasClass('animated fadeInLeft') ) {

    			$('#-offcanvas').addClass('animated fadeOutLeft');
				setTimeout(function(){
					$('#-offcanvas').css('display', 'none');	
					$('#-offcanvas').removeClass('animated fadeOutLeft fadeInLeft');
				}, 1000);
				$('.js--nav-toggle').removeClass('active');
				
	    	}
	    
	    	
	    }
		});

		$('body').on('click', '.js--close-offcanvas', function(event){
		

	  		$('#-offcanvas').addClass('animated fadeOutLeft');
			setTimeout(function(){
				$('#-offcanvas').css('display', 'none');	
				$('#-offcanvas').removeClass('animated fadeOutLeft fadeInLeft');
			}, 1000);
			$('.js--nav-toggle').removeClass('active');

	    	event.preventDefault();

		});

	};

	

	

	// Burger Menu
	var burgerMenu = function() {

		$('body').on('click', '.js--nav-toggle', function(event){

			var $this = $(this);

			$('#-offcanvas').css('display', 'block');
			setTimeout(function(){
				$('#-offcanvas').addClass('animated fadeInLeft');
			}, 100);
			
			// $('body').toggleClass('-overflow offcanvas-visible');
			$this.toggleClass('active');
			event.preventDefault();

		});

	};

	var scrolledWindow = function() {

		$(window).scroll(function(){

			var header = $('#-header'),
				scrlTop = $(this).scrollTop();


		   $('#-home .flexslider .-overlay').css({
				'opacity' : (.5)+(scrlTop/2000)
		   });

		   if ( $('body').hasClass('offcanvas-visible') ) {
		   	$('body').removeClass('offcanvas-visible');
		   	$('.js--nav-toggle').removeClass('active');
		   }
		 
		});

		$(window).resize(function() {
			if ( $('body').hasClass('offcanvas-visible') ) {
		   	$('body').removeClass('offcanvas-visible');
		   	$('.js--nav-toggle').removeClass('active');
		   }
		});
		
	};


	

	// Page Nav
	var clickMenu = function() {
		var topVal = ( $(window).width() < 769 ) ? 0 : 58;

		$(window).resize(function(){
			topVal = ( $(window).width() < 769 ) ? 0 : 58;		
		});

		if ( $(this).attr('href') != "#") {
			$('#-main-nav a:not([class="external"]), #-offcanvas a:not([class="external"])').click(function(event){
				var section = $(this).data('nav-section');


				if ( $('div[data-section="' + section + '"]').length ) {

					$('html, body').animate({
			        	scrollTop: $('div[data-section="' + section + '"]').offset().top - topVal
			    	}, 500);	
			    	
			   }
			   event.preventDefault();

			});
		}

		


	};


	var contentWayPoint = function() {
		var i = 0;
		$('.animate-box').waypoint( function( direction ) {

			if( direction === 'down' && !$(this.element).hasClass('animated') ) {
				
				i++;

				$(this.element).addClass('item-animate');
				setTimeout(function(){
					
					$('body .animate-box.item-animate').each(function(k){
						var el = $(this);
						setTimeout( function () {
							el.addClass('fadeInUp animated');
							el.removeClass('item-animate');
						},  k * 200, 'easeInOutExpo' );
					});
					
				}, 100);
				
			}

		} , { offset: '85%' } );


	};


	// Document on load.
	$(function(){

		mobileMenuOutsideClick();
		burgerMenu();
		scrolledWindow();
		
		// Animations
		contentWayPoint();
		
		

	});


}());