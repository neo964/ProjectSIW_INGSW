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
<jsp:useBean id="trailer" class="Model.Trailer" scope="page"/>

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

Multimedia multimedia = (Multimedia) request.getAttribute("YourMultimedia");
Film filmtmp = null;
TVSerie tvserietmp = null;
String isfilm = null;

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
	//session.setAttribute("isFilm", true);
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
	//session.setAttribute("isFilm", false);
}
request.setAttribute("multimedia", multimedia.getId());
trailer.setPath(multimedia.getTrailer().getPath());
request.getSession().setAttribute("rankid", multimedia.getId());

%>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>PANDAFLIX &mdash; Content</title>
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
					<li><a href="aboutUs.html">About Us</a></li>
					<% if (curSession.isAdmin()) { %>
					<li><a href="posterFilm.jsp">Add New Film</a></li>
					<li><a href="posterTVSerie.jsp">Add New TVSerie</a></li>
					<%} %>
				</ul>
			</div>
		</div>
	</div>
	<!-- END offcanvas -->
	<header id="-header">
		
		<div class="container-fluid">

			<div class="row">
				<a href="#" class="js--nav-toggle -nav-toggle"><i></i></a>
				<!-- logo -->
				<div class="col-lg-12 col-md-12 text-center">
					<h1 id="-logo"><a href="index.jsp">PANDAFLIX <sup>TM</sup></a></h1>
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
				
          			<!-- pulsante per admin -->
          			<%if (curSession.isAdmin()) {%>
          				<div class="field-wrap">
          				<form action="edit">
          				<%if (tvserietmp != null) {%>
          					<h4><button class="button" name="edit" value=<jsp:getProperty name="tvserie" property="id"/>>Edit!</button> </h4>
          				<% }else{ %>
          					<h4><button class="button" name="edit" value=<jsp:getProperty name="film" property="id"/>>Edit!</button> </h4>
          					<%} %>
          				</form>
          				</div> 
          				<%} %>

			</div>
		
		</div>

	</header>

<% if (multimedia instanceof Film) {%>

	<div class="container-fluid">
		<div class="row -post-entry single-entry">
			<article class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2 col-xs-12 col-xs-offset-0">
				<figure class="animate-box">
					<img src=<jsp:getProperty name="poster" property="image"/> alt="Image" class="img-responsive">
				</figure>
				<span class="-meta animate-box"><a href="#"><jsp:getProperty name="poster" property="title"/></a></span>
				
				<div class="col-lg-12 col-lg-offset-0 col-md-12 col-md-offset-0 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-left content-article">
					<div class="row">
						<div class="col-lg-8 cp-r animate-box">
							<p>Title: <jsp:getProperty name="poster" property="title"/></p>
							<p>Category: <jsp:getProperty name="poster" property="category"/></p>
							<p>Director: <jsp:getProperty name="poster" property="director"/></p>
							<p>Year: <jsp:getProperty name="poster" property="year"/></p>
							<p>Plot: <jsp:getProperty name="poster" property="plot"/></p>
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
								
								<div class="col-lg-4 animate-box">
							<div class="-highlight right">
								<h4>Trailer</h4>
								<iframe width="560" height="315" 
									src= <jsp:getProperty name="trailer" property="path"/>
									frameborder="0" 
									gesture="media" 
									allow="encrypted-media" 
									allowfullscreen>
								</iframe>
							</div>
						</div>
					<div class="col-lg-4 animate-box">
						<div class="-highlight right">
							<h4>Price</h4>
							<p><jsp:getProperty name="film" property="price"/></p>
						</div>
					</div>
					
							
					
					<form action="/Project/vote" method ="get">
					<div class="container-fluid">
						<div class="row -post-entry">
							<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
								<figure>	<!-- qui è il tag di cambio pagina -->
									<input name="rank" type="image" value="1" class="img-responsive" alt="Image" src="images/stella.png">
								</figure>
							</article>
							
							<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
								<figure>	<!-- qui è il tag di cambio pagina -->
									<input name="rank" type="image" value="2" class="img-responsive" alt="Image" src="images/stella.png">
								</figure>
							</article>
							
							<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
								<figure>	<!-- qui è il tag di cambio pagina -->
									<input name="rank" type="image" value="3" class="img-responsive" alt="Image" src="images/stella.png">
								</figure>
							</article>
							
							<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
								<figure>	<!-- qui è il tag di cambio pagina -->
									<input name="rank" type="image" value="4" class="img-responsive" alt="Image" src="images/stella.png">
								</figure>
							</article>
							
							<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
								<figure>	<!-- qui è il tag di cambio pagina -->
									<input name="rank" type="image" value="5" class="img-responsive" alt="Image" src="images/stella.png">
								</figure>
							</article>
							
							</div>
							</div>
							</form>
								
					<div class="top-row">
					<form action="/Project/goToCart" method = "get">
						 <div class="field-wrap">
          					<button class="button" name = "multimedia" value = <jsp:getProperty name="film" property="id"/>>Add To Cart!</button> 
          				</div> 
          			</form>
          			</div>
          			
          			<div class="top-row">
					<form action="/Project/myFavourite" method = "get">
						 <div class="field-wrap">
          					<button class="button" name = "favourite" value = <jsp:getProperty name="film" property="id"/>>Add To Favourites!</button> 
          				</div> 
          			</form>
          			</div>
          			
          			<div class="top-row">
					<form action="/Project/suggestTo" method = "get">
						 <div class="field-wrap">
          					<button class="button" name = "multimedia" value = <jsp:getProperty name="film" property="id"/>>Suggest It!</button> 
          				</div> 
          			</form>
          			</div>
          			
          			<%if (curSession.isPremium()){ %>
					<div class="top-row">
					<form action="watchIt" method = "get">
						 <div class="field-wrap">
          					<button class="button" name = "path" value = <jsp:getProperty name="film" property="videoOnDemand"/>>Watch It!</button> 
          				</div> 
          			</form>
          			</div>
          			<%} %>
					</div>
	
	</div>
	</article>
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
						<!-- Spazio per il link -->
						<div class="col-lg-4 animate-box">
							<div class="-highlight right">
								<h4>Trailer</h4>
								<iframe width="560" height="315" 
									src= <jsp:getProperty name="trailer" property="path"/>
									frameborder="0" 
									gesture="media" 
									allow="encrypted-media" 
									allowfullscreen>
								</iframe>
							</div>
						</div>
					<div class="col-lg-4 animate-box">
						<div class="-highlight right">
							<h4>Price</h4>
							<p><jsp:getProperty name="tvserie" property="price"/></p>
						</div>
					</div>
					
					
							</div>
						</div>
					
					<form action="/Project/vote" method ="get">
					<div class="container-fluid">
						<div class="row -post-entry">
							<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
								<figure>	<!-- qui è il tag di cambio pagina -->
									<input name="rank" type="image" value="1" class="img-responsive" alt="Image" src="images/stella.png">
								</figure>
							</article>
							
							<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
								<figure>	<!-- qui è il tag di cambio pagina -->
									<input name="rank" type="image" value="2" class="img-responsive" alt="Image" src="images/stella.png">
								</figure>
							</article>
							
							<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
								<figure>	<!-- qui è il tag di cambio pagina -->
									<input name="rank" type="image" value="3" class="img-responsive" alt="Image" src="images/stella.png">
								</figure>
							</article>
							
							<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
								<figure>	<!-- qui è il tag di cambio pagina -->
									<input name="rank" type="image" value="4" class="img-responsive" alt="Image" src="images/stella.png">
								</figure>
							</article>
							
							<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
								<figure>	<!-- qui è il tag di cambio pagina -->
									<input name="rank" type="image" value="5" class="img-responsive" alt="Image" src="images/stella.png">
								</figure>
							</article>
							
							</div>
							</div>
							</form>
								
					<div class="top-row">
					<form action="/Project/goToCart" method = "get">
						 <div class="field-wrap">
          					<button class="button" name = "multimedia" value = <jsp:getProperty name="tvserie" property="id"/>>Add To Cart!</button> 
          				</div> 
          			</form>
          			</div>
          			
          			<div class="top-row">
					<form action="/Project/myFavourite" method = "get">
						 <div class="field-wrap">
          					<button class="button" name = "favourite" value = <jsp:getProperty name="tvserie" property="id"/>>Add To Favourites!</button> 
          				</div> 
          			</form>
          			</div>
					<div class="row rp-b">
						<div class="col-md-12 animate-box">
							<blockquote>
								<p></p>
							</blockquote>
						</div>
					</div>
				
		<!-- <ul class="tab-group">
	       <% for (index.setIndex(0); index.getIndex() < TVposter.getSeasons(); index.setIndex(index.getIndex()+1)) {
	       		if (index.getIndex() == 0){
	       %>	
	        <li class="tab active"><a href="#<jsp:getProperty property="index" name="index"/>"><jsp:getProperty property="index" name="index"/></a></li>
	        <% }else{ %>
	        <li class="tab"><a href="#<jsp:getProperty property="index" name="index"/>"><jsp:getProperty property="index" name="index"/></a></li>
	     	<%}} %>
     	</ul>
     	
     	 
     	 <div class="tab-content"> -->
     	 <% 
     	 if (curSession.isPremium()){
     	 for (index.setIndex(1); index.getIndex() < TVposter.getSeasons()+1; index.setIndex(index.getIndex()+1)) {

 			System.out.println (index.getIndex());
	       %>
        <div id=<jsp:getProperty property="index" name="index"/>>   
			<h2><jsp:getProperty name="index" property="index"/></h2>
          <div class="top-row">
            <div class="field-wrap">
     			<div class="container-fluid">
		<div class="row -post-entry">
			<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
          
				
		<% 
		LinkedList<Episode> episodes = tvserietmp.getSeason(index.getIndex());

		for (Iterator<Episode> iterator = episodes.iterator(); iterator.hasNext();) {
			Episode episodetmp = (Episode) iterator.next();
			episode.setEpisode(episodetmp.getEpisode());
			episode.setSeason(episodetmp.getSeason());
			episode.setPath(episodetmp.getPath());
			episode.setTVSerieID(episodetmp.getTVSerieID());
			%>
			
			<div class="top-row">
				<form action="watchIt" method = "get">
					 <div class="field-wrap">
          				<button name = "path" value = <jsp:getProperty name="episode" property="path"/>> <jsp:getProperty name="episode" property="season"/>x<jsp:getProperty name="episode" property="episode"/> </button> 
          			</div> 
          		</form>
          	</div>
			
         
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
	<%} }%>
	</div>
	</div>
	</article>
	</div>
	</div>

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
