<%@page import="Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> 

<head>
<jsp:useBean id="curSession" class="Model.UserSession" scope="session"/>
<%
String user = (String) session.getAttribute("user");
if (user == null)
	response.sendRedirect("loginpage.html");

curSession.setUser(user);
curSession.setFirstName((String)session.getAttribute("firstName"));
curSession.setLastName((String)session.getAttribute("lastName"));
curSession.setImage((String)session.getAttribute("image"));

boolean control = (boolean)session.getAttribute("admin");
curSession.setAdmin(control);

control = (boolean)session.getAttribute("premium");
curSession.setPremium(control);
%>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Magazine &mdash; Free Fully Responsive HTML5 Bootstrap Template by FREEHTML5.co</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
	<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
	<meta name="author" content="FREEHTML5.CO" />

  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<link rel="shortcut icon" href="favicon.ico">
	<!-- Google Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Playfair+Display:400,700,400italic|Roboto:400,300,700' rel='stylesheet' type='text/css'>
	<!-- Animate -->
	<link rel="stylesheet" href="css/animate.css">
	<!-- Icomoon -->
	<link rel="stylesheet" href="css/icomoon.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="css/bootstrap.css">

	<link rel="stylesheet" href="css/style.css">


	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

	</head>
	<body>
	
<div id="-offcanvas">
		<a href="#" class="-close-offcanvas js--close-offcanvas"><span><i class="icon-cross3"></i> <span>Close</span></span></a>
		<div class="-bio">
			<figure>
				<img src="images/io.jpeg" alt="Free HTML5 Bootstrap Template" class="img-responsive">
			</figure>
			<h3 class="heading">My Profile</h3>
			<h2>Utente</h2>
			<p>Commento dell'utente</p>
			
		</div>

		<div class="-menu">
			<div class="-box">
				<h3 class="heading">Categories</h3>
				<ul>
					<li><a href="#">Subscribe</a></li>
					<li><a href="#">About Us</a></li>
					<li><a href="#">News</a></li>
					<li><a href="#">Film</a></li>
					<li><a href="#">TVSeries</a></li>
					<li><a href="#">MyFavourite</a></li>
				</ul>
			</div>
			<div class="-box">
				<h3 class="heading">Search</h3>
				<form action="#">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Type a keyword">
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<header id="-header">
		
		<div class="container-fluid">

			<div class="row">
				<a href="#" class="js--nav-toggle -nav-toggle"><i></i></a>
				<ul class="-social">
					<li><a href="#"><i class="icon-twitter"></i></a></li>
					<li><a href="#"><i class="icon-facebook"></i></a></li>
					<li><a href="#"><i class="icon-instagram"></i></a></li>
				</ul>
				<div class="col-lg-12 col-md-12 text-center">
					<h1 id="-logo"><a href="index.html">Titolo Del Contenuto</a></h1>
				</div>

			</div>
		
		</div>

	</header>
	<a href="#" class="-post-prev"><span><i class="icon-chevron-left"></i> Prev</span></a>
	<a href="#" class="-post-next"><span>Next <i class="icon-chevron-right"></i></span></a>
	<!-- END #-header -->
	<div class="container-fluid">
		<div class="row -post-entry single-entry">
			<article class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2 col-xs-12 col-xs-offset-0">
				<figure class="animate-box">
					<img src="images/single_1.jpg" alt="Image" class="img-responsive">
				</figure>
				<span class="-meta animate-box"><a href="single.html">Titolo Film</a></span>
				<h2 class="-article-title animate-box"><a href="single.html"></a></h2>
				<span class="-meta -date animate-box">Quando è stato caricato</span>
				
				<div class="col-lg-12 col-lg-offset-0 col-md-12 col-md-offset-0 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-left content-article">
					<div class="row">
						<div class="col-lg-8 cp-r animate-box">
							<p>Descrizione.</p>
						</div>
						<div class="col-lg-4 animate-box">
							<div class="-highlight right">
								<h4>Cast</h4>
								<p>Descrzione cast</p>
							</div>
						</div>
					</div>

					<div class="row rp-b">
						<div class="col-md-12 animate-box">
							<blockquote>
								<p></p>
							</blockquote>
						</div>
					</div>
	
	</div>

	<footer id="-footer">
		<p><small>&copy;2017 ingegneria del software e siw project <br> Designed by Andrea Pastore & Mario Perri</a> </small></p>

	</footer>
	
	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Main JS -->
	<script src="js/main.js"></script>

	</body>
</html>