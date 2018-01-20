<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import = "Model.User" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> 
<head>

<jsp:useBean id="curSession" class="Model.UserSession" scope="session"/>
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
	request.getSession().setAttribute("isFilm", false);
}
%>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>PANDAFLIX &mdash; CategoryTV</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="author" content="Pastore-Perri">
	
	<link href='http://fonts.googleapis.com/css?family=Playfair+Display:400,700,400italic|Roboto:400,300,700' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="css/animate.css">
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
					<li><a href="posterFilm.html">Add New Film</a></li>
					<li><a href="posterTVSerie.html">Add New TVSerie</a></li>
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
					<h2>Categories</h2>
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
	<!-- END #-header -->
	<div class="container-fluid">
	<form action="/Project/searchTV" method = "get">
		<div class="row -post-entry">
			<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
				<figure>	<!-- qui è il tag di cambio pagina -->
					<input name="giveCategory" type="image" value="Animation" class="img-responsive" alt="Image" src="images/animationSerie.jpg">
				</figure>
					<span class="-meta">Animation</span>
					<span class="-meta -date">Last update, now</span>
			</article>
			<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
				<figure>
					<input name="giveCategory" type="image" value="Adventure" class="img-responsive" alt="Image" src="images/adventureSerie.jpg">
				</figure>
					<span class="-meta">Adventure</span>
					<span class="-meta -date">Last update, now</span>
			</article>
			<div class="clearfix visible-xs-block"></div>
			<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
				<figure>
					<input name="giveCategory" type="image" value="Commedy" class="img-responsive" alt="Image" src="images/comedySerie.jpg">
				</figure>
					<span class="-meta">Comedy</span>
					<span class="-meta -date">Last update, now</span>
			</article>
			<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
				<figure>
					<input name="giveCategory" type="image" value="Drama" class="img-responsive" alt="Image" src="images/dramaticSerie.jpg">
				</figure>
				<span class="-meta">Dramatic</span>
				<span class="-meta -date">Last update, now</span>
			</article>
				<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
				<figure>
					<input name="giveCategory" type="image" value="Sci-Fi" class="img-responsive" alt="Image" src="images/scienzeSerie.jpg">
				</figure>
					<span class="-meta">Science fiction</span>
					<span class="-meta -date">Last update, now</span>
			</article>
			<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
				<figure>
					<input name="giveCategory" type="image" value="Fantasy" class="img-responsive" alt="Image" src="images/fantasySerie.jpg">
				</figure>
					<span class="-meta">Fantasy / Fantastic</span>
					<span class="-meta -date">Last update, now</span>
			</article>
			<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
				<figure>
					<input name="giveCategory" type="image" value="War" class="img-responsive" alt="Image" src="images/warSerie.jpg">
				</figure>
					<span class="-meta">War</span>
					<span class="-meta -date">Last update, now</span>
			</article>
			<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
				<figure>
					<input name="giveCategory" type="image" value="Horror" class="img-responsive" alt="Image" src="images/horrorSerie.jpg">
				</figure>
					<span class="-meta">Horror</span>
					<span class="-meta -date">Last update, now</span>
			</article>
		<div class="clearfix visible-lg-block visible-md-block visible-sm-block visible-xs-block"></div>
		</form>
	</div>

	<footer id="-footer">
		<p><small>&copy;2017 ingegneria del software e siw project <br> Designed by Andrea Pastore & Mario Perri</a> </small></p>
	</footer>


	
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.waypoints.min.js"></script>
	<script src="js/main.js"></script>

	</body>
</html>

