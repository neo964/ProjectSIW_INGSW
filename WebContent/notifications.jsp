<%@page import="Model.User"%>
<%@page import="Model.Friendship"%>
<%@page import="Model.Advice"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> 
<head>
	<jsp:useBean id="curSession" class="Model.UserSession" scope="session"/>
	<jsp:useBean id="friend" class="Model.UserSession" scope="page"/>
	<jsp:useBean id="previewadvice" class="Model.PreviewAdvice" scope="page"/>
<%
User user = (User) session.getAttribute("user");
LinkedList <User> requests = (LinkedList<User>) request.getAttribute("requests");
LinkedList <Advice> advices = (LinkedList<Advice>) request.getAttribute("advices");
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
	<title>PANDAFLIX &mdash; Notifications</title>
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
``                 			<h2>Notifications</h2>
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
		
<div class="popup">
  <h1 id="message">
  </h1>
  <span class="close">
 <button class="action-button shadow animate blue" >Quit!</button>
  </span>
</div>
	</header>

<div class="container-fluid">
		<div class="row -post-entry">
			<article class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2 col-xs-12 col-xs-offset-0">
								
				<div class="col-lg-12 col-lg-offset-0 col-md-12 col-md-offset-0 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-left content-article">
					<div class="row">
		
						<div class="col-lg-4 animate-box">
							<div class="-highlight right">
								<h3>Friend Request</h3>
								<%for(User userfriendtmp: requests){ 
									friend.setFirstName(userfriendtmp.getFirstName());
									friend.setLastName(userfriendtmp.getLastName());
									friend.setImage(userfriendtmp.getPathToImage());
									friend.setUser(userfriendtmp.getEmail());
								%>
								<p> <jsp:getProperty name="friend" property="firstName"/> <jsp:getProperty name="friend" property="lastName"/> </p>
								<button class="action-button shadow animate blue" id="accept" name="accept" value=<jsp:getProperty name="friend" property="user"/>>Accept</button>
								<button class="action-button shadow animate blue" id="decline" name="decline" value=<jsp:getProperty name="friend" property="user"/>>Refuse</button>
								<%} %>
							</div>
						</div>
					</div>
				</div>
			</article>
			<article class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2 col-xs-12 col-xs-offset-0">
								
				<div class="col-lg-12 col-lg-offset-0 col-md-12 col-md-offset-0 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-left content-article">
					<div class="row">
		
						<div class="col-lg-4 animate-box">
							<div class="-highlight right">
								<h3>Advice Received</h3>
								<%for (Advice advice: advices){
									if (curSession.getUser().equals(advice.getAdvisor().getEmail())){
										previewadvice.setAdviser(advice.getAdvicer().getEmail());
										previewadvice.setAdvisor(advice.getAdvisor().getEmail());
										previewadvice.setFname(advice.getAdvicer().getFirstName());
										previewadvice.setLname(advice.getAdvicer().getLastName());
										previewadvice.setIdmultiemdia(advice.getMultimedia().getId());
										previewadvice.setTitle(advice.getMultimedia().getPoster().getTitle());
									%>
								<p><jsp:getProperty name="previewadvice" property="fname"/> <jsp:getProperty name="previewadvice" property="lname"/> advice you to watch <jsp:getProperty name="previewadvice" property="title"/> </p>
								
								<%} }%>
							</div>
						</div>
					</div>
				</div>
			</article>	
				
		</div>	
	</div>

	<footer id="-footer">
		<p><small>&copy;2017 ingegneria del software e siw project <br> Designed by Andrea Pastore & Mario Perri</a> </small></p>
	</footer>


	
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.waypoints.min.js"></script>
	<script src="js/main.js"></script>
	<script src="js/requestAjax.js"></script>

	</body>
</html>
