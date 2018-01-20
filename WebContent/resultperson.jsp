<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Iterator"%>
<%@page import="Model.User"%>
<%@page import="Model.Friendship"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> 
<head>

<jsp:useBean id="friend" class="Model.UserSession" scope="page"/>
<jsp:useBean id="preview" class="Model.PreviewMultimedia" scope="page"/>
<jsp:useBean id="curSession" class="Model.UserSession" scope="session"/>
<jsp:useBean id="friendhip" class="Model.Friendship" scope="page"/>

<%
User user = (User) session.getAttribute("user");
LinkedList<Friendship> friends = (LinkedList<Friendship>) request.getAttribute("friends");
LinkedList<User> userfriends = (LinkedList<User>) request.getAttribute("userfriends");
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
	<title>PANDAFLIX &mdash; Results</title>
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
					<h2>Results</h2>
					<h3 id="-logo"><a>Invite your friends to join your network</a> </h3>
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

		
		</div>

	</header>
	<% if (friends.isEmpty()){%> 
				<div class="col-lg-12 col-md-12 text-center">
			<h2 id="-logo">We are sorry but the searched people is not present, try with another search!</h2>
			</div>
		<% }%>

	<div class="container-fluid">
		<div class="row -post-entry">
			<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 animate-box">
			<% if (friends != null)
				for (Iterator iterator = userfriends.iterator(), iteratorfriend = friends.iterator(); iterator.hasNext();) {
					User userfriendtmp = (User) iterator.next();
					Friendship friedshiptmp = null;
					if (iteratorfriend.hasNext())
						friedshiptmp = (Friendship) iteratorfriend.next();
					
					friend.setFirstName(userfriendtmp.getFirstName());
					friend.setLastName(userfriendtmp.getLastName());
					friend.setImage(userfriendtmp.getPathToImage());
					friend.setUser(userfriendtmp.getEmail());
					if (friedshiptmp != null)
						friendhip.setAccepted(friedshiptmp.isAccepted());
					else
						friendhip.setAccepted(false);
						
				%>
			<form action="/Project/friend" method="get">
				<figure>	<!-- qui è il tag di cambio pagina -->
					<input name="profile" type="image" value=<jsp:getProperty name="friend" property="user"/> class="img-responsive" alt="Image" src=<jsp:getProperty name="friend" property="image"/> >
				</figure> 			<!-- da vedere se vogliamo mettere il link del profilo dell utente trovato o meno -->
				<span class="-meta"><jsp:getProperty name="friend" property="firstName"/> <jsp:getProperty name="friend" property="lastName"/></span>
				<%if (friendhip.isAccepted()) { %>
					<button class="button" name="remove" value=<jsp:getProperty name="friend" property="user"/>>Remove</button>
					<%} else { %>
					<button class="button" name="add" value=<jsp:getProperty name="friend" property="user"/>>Invite</button>
					<%} %>
				<span class="-meta -date"></span>
			</form>
			<%} %>
			</article>
			</div>
			</div>
			
			<footer id="-footer">
		<p><small>&copy;2017 ingegneria del software e siw project <br> Designed by Andrea Pastore & Mario Perri </small></p>
	</footer>


	
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.waypoints.min.js"></script>
	<script src="js/main.js"></script>

	</body>
</html>
			
