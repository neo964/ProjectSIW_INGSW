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
else{
	System.out.println(user);
	curSession.setUser(user);
	curSession.setFirstName((String) session.getAttribute("firstName"));
	curSession.setLastName((String) session.getAttribute("lastName"));
	curSession.setImage((String) session.getAttribute("image"));
	
	boolean control = ((boolean) session.getAttribute("admin"));
	curSession.setAdmin(control);
	
	control = ((boolean) session.getAttribute("premium"));
	curSession.setPremium(control);
}
%>

 	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>PANDAFLIX &mdash; Pastore-Perri</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="author" content="Pastore-Perri">

	<link rel="shortcut icon" href="favicon.ico">
	<link href='http://fonts.googleapis.com/css?family=Playfair+Display:400,700,400italic|Roboto:400,300,700' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="css/animate.css">
	<link rel="stylesheet" href="css/icomoon.css">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/style.css">
	
	<script src="js/modernizr-2.6.2.min.js"></script>

	</head>
	<body>
	<div id="-offcanvas">
		<a href="#" class="-close-offcanvas js--close-offcanvas"><span><i class="icon-cross3"></i> <span>Close</span></span></a>
		<div class="-bio">
			<figure>
				<img src=<jsp:getProperty name="curSession" property="image"/> alt="Pandaflix" class="img-responsive">
			</figure>
			<h3 class="heading"><a href="/Project/myProfile">MyProfile</a></h3>
			<p>Hi, <jsp:getProperty name="curSession" property="firstName"/> <jsp:getProperty name="curSession" property="lastName"/>.</p>
			<p> I'm in. </p>
			
			<a href="/Project/signOut">Log Out</a>
			
		</div>
	<!-- Profilo utente -->
		<div class="-menu">
			<div class="-box">
				<h3 class="heading">Categories</h3>
				<ul>
					<li><a href="/Project/Subscribe">Subscribe</a></li>
					<li><a href="aboutUs.html">About Us</a></li>
					<li><a href="/Project/search">News</a></li></form>
					<li><a href="/Project/film">Film</a></li>
					<li><a href="/Project/tvserie">TVSeries</a></li>
					<li><a href="/Project/myFavourite">My Favourite</a></li>
					<li><a href="/Project/goToCart">Cart</a></li>
					<% if (curSession.isAdmin()) { %>
					<li><a href="posterFilm.html">Add New Film</a></li>
					<li><a href="posterTVSerie.html">Add New TVSerie</a></li>
					<%} %>
				</ul>
			</div>
			<div class="-box">
				<h3 class="heading">Search Film</h3>
				<form action="/Project/search" method="get">
					<div class="form-group">
						<input name="keyword" type="text" class="form-control" placeholder="Type a keyword">
					</div>
				</form>
				
				<h3 class="heading">Search TVSerie</h3>
				<form action="/Project/searchTV" method="get">
					<div class="form-group">
						<input name="keyword" type="text" class="form-control" placeholder="Type a keyword">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- END #-offcanvas -->
	<header id="-header">
		
		<div class="container-fluid">

			<div class="row">
				<a href="#" class="js--nav-toggle -nav-toggle"><i></i></a>
				<!-- logo -->
				<div class="col-lg-12 col-md-12 text-center">
					<h1 id="-logo"><a href="index.jsp">PANDAFLIX <sup>TM</sup></a></h1>
				</div>

			</div>
		
		</div>

	</header>
	<!-- END #-header -->
	<div class="container-fluid">
	<form action="/Project/search" method="get">
		<div class="row -post-entry">
			<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
				<figure>	<!-- qui è il tag di cambio pagina -->
					<input name="giveNews" type="image" value="news" class="img-responsive" alt="Image" src="images/news.png">
				</figure>
					<span class="-meta"><a href="/Project/search" value = "news" name = "giveNews">News</a></span>
					<h2 class="-article-title"><a href="single.html">Look at what we have chosen for you</a></h2>
					<span class="-meta -date">Last update, now</span>
			</article>
			<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
				<figure>
					<input name="giveNews" type="image" value="film" class="img-responsive" alt="Image" src="images/film.jpg">
				</figure>
					<span class="-meta"><a href="single.html">Film</a></span>
					<h2 class="-article-title"><a href="single.html">Do you want a movie?</a></h2>
					<span class="-meta -date">Last update, now</span>
			</article>
			<div class="clearfix visible-xs-block"></div>
			<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
				<figure>
					<input name="giveNews" type="image" value="tvserie" class="img-responsive" alt="Image" src="images/serie.jpg">
				</figure>
				<span class="-meta"><a href="single.html">TVSeries</a></span>
				<h2 class="-article-title"><a href="single.html">Or do you want a tv serie?</a></h2>
				<span class="-meta -date">Last update, now</span>
			</article>
			<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
				<figure>
					<input name="giveNews" type="image" value="friend" class="img-responsive" alt="Image" src="images/amici.jpg">
				</figure>
				<span class="-meta"><a href="single.html">Friends</a></span>
				<h2 class="-article-title"><a href="single.html">Expand and explore your network</a></h2>
				<span class="-meta -date">Last update, now</span>
			</article>
			<div class="clearfix visible-lg-block visible-md-block visible-sm-block visible-xs-block"></div>
		</form>
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



