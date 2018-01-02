<%@page import="java.util.LinkedList"%>
<%@page import="Model.User"%>
<%@page import="Model.Film"%>
<%@page import="Model.Poster"%>
<%@page import="Model.Multimedia"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> 
<head>

<jsp:useBean id="film" class="Model.Film" scope="page"/>
<jsp:useBean id="preview" class="Model.PreviewMultimedia" scope="page"/>
<jsp:useBean id="curSession" class="Model.UserSession" scope="session"/>
<jsp:useBean id="poster" class="Model.FilmPoster" scope="page"/>

<%
String user = (String) session.getAttribute("user");
request.setAttribute("isFilm", true);
int length = 0;
boolean control = false;
if (user == null)
	response.sendRedirect("loginpage.html");
else{
	length = (int)session.getAttribute("size");
	
	curSession.setUser(user);
	curSession.setFirstName((String)session.getAttribute("firstName"));
	curSession.setLastName((String)session.getAttribute("lastName"));
	curSession.setImage((String)session.getAttribute("image"));
	
	control = (boolean)session.getAttribute("admin");
	curSession.setAdmin(control);
	
	control = (boolean)session.getAttribute("premium");
	curSession.setPremium(control);
}
%>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>PANDAFLIX &mdash; Pastore-Perri</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
	<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
	<meta name="author" content="Pastore-Perri" />

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
				<img src=<jsp:getProperty name="curSession" property="image"/> alt="Pandaflix" class="img-responsive">
			</figure>
			<h3 class="heading"><a href="">MyProfile</a></h3>
			<h3>Hi, <jsp:getProperty name="curSession" property="firstName"/> <jsp:getProperty name="curSession" property="lastName"/>.</h3>
			<p> Hi, I'm in. </p>
			
		</div>
	<!-- Profilo utente -->
		<div class="-menu">
			<div class="-box">
				<h3 class="heading">Categories</h3>
				<ul>
					<li><a href="/Project/Subscribe">Subscribe</a></li>
					<li><a href="aboutUs.html">About Us</a></li>
					<li><a href="/Project/search" name = "giveNews" value = "news">News</a></li></form>
					<li><a href="/Project/film">Film</a></li>
					<li><a href="/Project/tvserie">TVSeries</a></li>
					<li><a href="/Project/myFavourite">MyFavourite</a></li>
					<% if (curSession.isAdmin()) { %>
					<li><a href="posterFilm.html">AddNewFilm</a></li>
					<li><a href="posterTVSerie.html">AddNewTVSerie</a></li>
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
					<h1 id="-logo"><a href="/Project/home">Results</a></h1>
				</div>

			</div>
		
		</div>

	</header>
	<!-- END #-header -->
	<div class="container-fluid">
		<div class="row -post-entry">
		<% for (int i = 0; i < length; i++) {
			
			Multimedia filmtmp = (Multimedia) request.getAttribute("film"+i);
			preview.setTitle(filmtmp.getPoster().getTitle());
			preview.setImage(filmtmp.getPoster().getImage());
			preview.setId(filmtmp.getId());
			boolean isFilm;
			if (filmtmp instanceof Film)
				preview.setFilm(true);
			else
				preview.setFilm(false);
			
			if (preview.isFilm())
				isFilm = true;
			else
				isFilm = false;
			System.out.println (isFilm);
				session.setAttribute("isFilm", isFilm);
				%>
				
				<form action="/Project/GiveMePage" method = "get">
					<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
						<figure>	<!-- qui è il tag di cambio pagina -->
							<input name="giveMultimedia" type="image" value=<jsp:getProperty name="preview" property="id"/> class="img-responsive" alt="Image" src=<jsp:getProperty name="preview" property="image"/> >
						</figure>
							<span class="-meta"><a href="/Project/GiveMePage" method = "get" name = "giveMultimedia" id = <jsp:getProperty name="preview" property="id"/>><jsp:getProperty name="preview" property="title"/></a></span>
							<h2 class="-article-title"><a href="/Project/GiveMePage" method = "get" name = "giveMultimedia" id = <jsp:getProperty name="preview" property="id"/>></a></h2>
					</article>
				</form>
			
			<%} %>
			
			<div class="clearfix visible-lg-block visible-md-block visible-sm-block visible-xs-block"></div>
			
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