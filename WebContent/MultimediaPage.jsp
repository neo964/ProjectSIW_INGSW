<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> 
<%@page import="Model.Episode"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Model.seasonindex"%>
<%@page import="Model.User"%>
<%@page import="Model.Film"%>
<%@page import="Model.Multimedia"%>
<%@page import="Model.TVSerie"%>
<%@page import="Model.Actor"%>
<%@page import="java.util.Iterator"%>


<head>
<jsp:useBean id="film" class="Model.Film" scope="page"/>
<jsp:useBean id="tvserie" class="Model.TVSerie" scope="page"/>
<jsp:useBean id="curSession" class="Model.UserSession" scope="session"/>
<jsp:useBean id="poster" class="Model.FilmPoster" scope="page"/>
<jsp:useBean id="actor" class="Model.Actor" scope="page"/>
<jsp:useBean id="TVposter" class="Model.TVSeriePoster" scope="page"/>
<jsp:useBean id="index" class="Model.seasonindex" scope="page"/>
<jsp:useBean id="episode" class="Model.Episode" scope="page"/>

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

Multimedia multimedia = (Multimedia) request.getAttribute("YourMultimedia");
Film filmtmp = null;
TVSerie tvserietmp = null;

if (multimedia instanceof Film){
	filmtmp = (Film) multimedia;
	film.setId(filmtmp.getId());
	film.setPrice(filmtmp.getPrice());
	film.setTrailer(filmtmp.getTrailer());
	film.setVideoOnDemand(filmtmp.getVideoOnDemand());
	poster.setActors(filmtmp.getPoster().getActors());
	poster.setCategory(filmtmp.getPoster().getCategory());
	poster.setDirector(filmtmp.getPoster().getDirector());
	poster.setImage(filmtmp.getPoster().getImage());
	poster.setPlot(filmtmp.getPoster().getPlot());
	poster.setTitle(filmtmp.getPoster().getTitle());
	poster.setYear(filmtmp.getPoster().getYear());
} else {
	tvserietmp = (TVSerie) multimedia;
	
	tvserie.setId(tvserietmp.getId());
	tvserie.setPrice(tvserietmp.getPrice());
	tvserie.setTrailer(tvserietmp.getTrailer());
	tvserie.setAllSeasons(tvserietmp.getAllSeasons());
	TVposter.setActors(tvserietmp.getPoster().getActors());
	TVposter.setCategory(tvserietmp.getPoster().getCategory());
	TVposter.setDirector(tvserietmp.getPoster().getDirector());
	TVposter.setImage(tvserietmp.getPoster().getImage());
	TVposter.setPlot(tvserietmp.getPoster().getPlot());
	TVposter.setTitle(tvserietmp.getPoster().getTitle());
	TVposter.setYear(tvserietmp.getPoster().getYear());
	TVposter.setCompleted(tvserietmp.getTvPoster().isCompleted());
	TVposter.setSeasons(tvserietmp.getTvPoster().getSeasons());
}

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

<% if (multimedia instanceof Film) {%>

	<div class="container-fluid">
		<div class="row -post-entry single-entry">
			<article class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2 col-xs-12 col-xs-offset-0">
				<figure class="animate-box">
					<img src=<jsp:getProperty name="poster" property="image"/> alt="Image" class="img-responsive">
				</figure>
				<span class="-meta animate-box"><a href="#"><jsp:getProperty name="poster" property="title"/></a></span>
				<h2 class="-article-title animate-box"><a href="single.html"></a></h2>
				
				<div class="col-lg-12 col-lg-offset-0 col-md-12 col-md-offset-0 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-left content-article">
					<div class="row">
						<div class="col-lg-8 cp-r animate-box">
							<p>Title: <jsp:getProperty name="poster" property="title"/></p>
							<p>Category: <jsp:getProperty name="poster" property="category"/></p>
							<p>Director: <jsp:getProperty name="poster" property="director"/></p>
							<p>Year: <jsp:getProperty name="poster" property="year"/></p>
							<p>Plot: <jsp:getProperty name="poster" property="plot"/></p>
							<p>Price: <jsp:getProperty name="film" property="price"/></p>
						</div>
						<div class="col-lg-4 animate-box">
							<div class="-highlight right">
								<h4>Cast</h4>
								<% for (String actortmp: poster.getActors()){ 
									actor.setActor(actortmp);
								%>
								<p><jsp:getProperty name="actor" property="actor"/></p>
								
								<%} %>
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
	<%} %>
	
<% if (multimedia instanceof TVSerie) {%>

	<div class="container-fluid">
		<div class="row -post-entry single-entry">
			<article class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2 col-xs-12 col-xs-offset-0">
				<figure class="animate-box">
					<img src=<jsp:getProperty name="TVposter" property="image"/> alt="Image" class="img-responsive">
				</figure>
				<span class="-meta animate-box"><a href="#"><jsp:getProperty name="TVposter" property="title"/></a></span>
				<h2 class="-article-title animate-box"><a href="single.html"></a></h2>
				
				<div class="col-lg-12 col-lg-offset-0 col-md-12 col-md-offset-0 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-left content-article">
					<div class="row">
						<div class="col-lg-8 cp-r animate-box">
							<p>Title: <jsp:getProperty name="TVposter" property="title"/></p>
							<p>Category: <jsp:getProperty name="TVposter" property="category"/></p>
							<p>Director: <jsp:getProperty name="TVposter" property="director"/></p>
							<p>Year: <jsp:getProperty name="TVposter" property="year"/></p>
							<p>Plot: <jsp:getProperty name="TVposter" property="plot"/></p>
							<p>Seasons: <jsp:getProperty name="TVposter" property="seasons"/></p>
							<p>Completed: <jsp:getProperty name="TVposter" property="completed"/></p>
							<p>Price <jsp:getProperty name="tvserie" property="price"/></p>
						</div>
						<div class="col-lg-4 animate-box">
							<div class="-highlight right">
								<h4>Cast</h4>
								<% for (String actortmp: TVposter.getActors()){ 
									actor.setActor(actortmp);
								%>
								<p><jsp:getProperty name="actor" property="actor"/></p>
								
								<%} %>
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
				
		<ul class="tab-group">
	       <% for (index.setIndex(0); index.getIndex() < TVposter.getSeasons(); index.setIndex(index.getIndex()+1)) {
	       		if (index.getIndex() == 0){
	       %>	
	        <li class="tab active"><a href="#<jsp:getProperty property="index" name="index"/>"><jsp:getProperty property="index" name="index"/></a></li>
	        <% }else{ %>
	        <li class="tab"><a href="#<jsp:getProperty property="index" name="index"/>"><jsp:getProperty property="index" name="index"/></a></li>
	     	<%}} %>
     	</ul>
     	
     	 
     	 <div class="tab-content">
     	 <% for (index.setIndex(0); index.getIndex() < TVposter.getSeasons(); index.setIndex(index.getIndex()+1)) {

 			System.out.println (index.getIndex());
	       %>
        <div id=<jsp:getProperty property="index" name="index"/>>   
          <h1><jsp:getProperty property="index" name="index"/></h1>
          <div class="top-row">
            <div class="field-wrap">
     			<div class="container-fluid">
		<div class="row -post-entry">
			<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
				
		<% 
		LinkedList<Episode> episodes = tvserietmp.getSeason(index.getIndex()+1);

		for (Iterator<Episode> iterator = episodes.iterator(); iterator.hasNext();) {
			Episode episodetmp = (Episode) iterator.next();
			episode.setEpisode(episodetmp.getEpisode());
			episode.setSeason(episodetmp.getSeason());
			%>
			
			<h1><jsp:getProperty name="episode" property="season"/></h1>
			<form class="field-wrap" action="/Project/videoOnDemand" method="get" id="episode">
            <div class="field-wrap">
          <div> <h4>
          <button type = "submit" name="episode" value = "ep" form="episode"><jsp:getProperty name="episode" property="season"/>x<jsp:getProperty name="episode" property="episode"/> </button>
           </h4></div>
          </div>
          </form>
          <%}%>
			</article>
			
			
			<div class="clearfix visible-lg-block visible-md-block visible-sm-block visible-xs-block"></div>
			
			</div>
            </div>
            
			</div>
            </div>
            
        <%} %>
        </div>
	
	</div>
	<%} %>

	<footer id="-footer">
		<p><small>&copy;2017 ingegneria del software e siw project <br> Designed by Andrea Pastore & Mario Perri</a> </small></p>

	</footer>
	
	
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script  src="js/loginpage.js"></script>
	
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
