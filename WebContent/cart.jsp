<%@page import="Model.User"%>
<%@page import="Model.Cart"%>
<%@page import="Model.MultimediaInCart"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Model.Multimedia"%>
<%@page import="Model.Film"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> 
<head>
	
<jsp:useBean id="film" class="Model.Film" scope="page"/>
<jsp:useBean id="preview" class="Model.PreviewMultimedia" scope="page"/>
<jsp:useBean id="curSession" class="Model.UserSession" scope="session"/>
<jsp:useBean id="poster" class="Model.FilmPoster" scope="page"/>
<jsp:useBean id="tmp" class="Model.PreviewMultimedia" scope="page"/>
<%
User user = (User) session.getAttribute("user");
if (user == null)
	response.sendRedirect("loginpage.html");
else{
	System.out.println(user);
	curSession.setUser(user.getEmail());
	curSession.setFirstName(user.getFirstName());
	curSession.setLastName(user.getLastName());
	curSession.setImage(user.getPathToImage());
	
	boolean control = (user.isAdmin());
	curSession.setAdmin(control);
	
	control = (user.isPremium());
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
			<h3 class="heading"><a href="myprofile.jsp">MyProfile</a></h3>
			<p>Hi, <jsp:getProperty name="curSession" property="firstName"/> <jsp:getProperty name="curSession" property="lastName"/>.</p>
			<p> I'm in. </p>
			
			<a href="/Project/signOut">Log Out</a>
			
		</div>
	<!-- Profilo utente -->
		<div class="-menu">
			<div class="-box">
				<h3 class="heading">Categories</h3>
				<ul>
					<li><a href="/Project/search">News</a></li>
					<li><a href="/Project/film">Film</a></li>
					<li><a href="/Project/tvserie">TVSeries</a></li>
					<li><a href="aboutUs.jsp">About Us</a></li>
					<% if (curSession.isAdmin()) { %>
					<li><a href="posterFilm.jsp">Add New Film</a></li>
					<li><a href="posterTVSerie.jsp">Add New TVSerie</a></li>
					<%} %>
				</ul>
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
					<h2>Cart</h2>
					<div class="-box" id="searchBox" style=" float: right"> 
						<h3 class="heading">Search Film</h3>
							<form action="/Project/search" method="get">
								<div class="form-group">
									<input name="keyword" type="text" class="form-control" placeholder="Type a keyword">
								</div>
							</form>
					</div>
					<div class="-box" id="searchBox" style=" float: left"> 
						<h3 class="heading">Search TVSerie</h3>
							<form action="/Project/searchTV" method="get">
								<div class="form-group">
									<input name="keyword" type="text" class="form-control" placeholder="Type a keyword">
								</div>
							</form>
					</div>

				</div>

			</div>
		
		</div>
</header>
<div class="container-fluid">
<% LinkedList<MultimediaInCart> cart = (LinkedList<MultimediaInCart>) request.getAttribute ("cart");
for (MultimediaInCart multimedia: cart) {
			
			Multimedia filmtmp = multimedia.getMultimedia();
			preview.setTitle(filmtmp.getPoster().getTitle());
			preview.setImage(filmtmp.getPoster().getImage());
			preview.setId(filmtmp.getId());
			preview.setPrice(filmtmp.getPrice());
			tmp.setPrice(tmp.getPrice()+filmtmp.getPrice());
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
	<form action="/Project/goToCart">
		<div class="row -post-entry">
			<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
				<figure>	<!-- qui è il tag di cambio pagina -->
					<a><img src=<jsp:getProperty name="preview" property="image"/> alt="Image" class="img-responsive"></a>
				</figure>
				<span class="-meta"><a> <jsp:getProperty name="preview" property="title"/> </a></span>
				<span class="-meta -date"><jsp:getProperty name="preview" property="price"/></span>
				<%if (isFilm) {%>
				<button class="button" name="fremove" value=<jsp:getProperty name="preview" property="id"/>> Remove </button>
				<%}else{ %>
				<button class="button" name="tremove" value=<jsp:getProperty name="preview" property="id"/>> Remove </button>
				<%} %>
			</article>
			</form>
			<%} %>
			
		
			<article class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2 col-xs-12 col-xs-offset-0">
								
				<div class="col-lg-12 col-lg-offset-0 col-md-12 col-md-offset-0 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-left content-article">
					<div class="row">
		
						<div class="col-lg-4 animate-box">
							<div class="-highlight right">
								<h4>Total Price</h4>
								<p><jsp:getProperty name="tmp" property="price"/></p>
							</div>
						</div>
						</div>
						</div>
						<form action="/Project/Check-Out">
							<button class="button" name="iscart" value="true">Buy It!</button>
						</form>
						</article>	
			</div>		
				
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
