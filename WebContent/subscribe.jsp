<%@page import="java.util.LinkedList"%>
<%@page import="Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> 
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
}
%>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>PANDAFLIX &mdash; News</title>
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
					<h1 id="-logo"><a href="index.html">PANDAFLIX <sup>TM</sup></a></h1>
					<h2 id="-logo"><a href="index.html">Become Premium</a></h2>
					<figure>
					<a><img src="images/offerte.jpg" alt="Image"></a>
				</figure>
				</div>

			</div>
		
		</div>

	</header>
	<!-- END #-header -->
          
           <form action="/" method="post">
          
          <div class="top-row">
          
            <div class="field-wrap">  
            <h4>Street</h4>
              <input type="text" required autocomplete="off" />
              
        
             <div class="field-wrap">
            <h4>Country</h4>
              <input type="text" required autocomplete="off" />
            </div>
            
             <div class="field-wrap">
            <h4>District</h4>
              <input type="text" required autocomplete="off" />
            </div>
            
             <div class="field-wrap">
            <h4>Zipcode</h4>
              <input type="text" required autocomplete="off" />
            </div>
            
             <div class="field-wrap">
            <h4>Card Number</h4>
              <input type="text" required autocomplete="off" />
            </div>
            
             <div class="field-wrap">
            <h4>Expiration date</h4>
              <input type="text" required autocomplete="off" />
            </div>
            
            <div class="field-wrap">
            <h4>Card CVC</h4>
              <input type="text" required autocomplete="off" />
            </div>

			 <div class="field-wrap">
            
              <button class="button button-block"/>Stay premium stay foolish</button>
            </div>
            </div>

            

          </div>
		
