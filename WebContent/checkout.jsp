<%@page import="Model.PaymentMethod"%>
<%@page import="Model.Address"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> 
<head>
	<jsp:useBean id="curSession" class="Model.UserSession" scope="session"/>
	<jsp:useBean id="address" class="Model.Address" scope="request"/>
	<jsp:useBean id="payment" class="Model.PaymentMethod" scope="request"/>
	
<%
User user = (User) session.getAttribute("user");
LinkedList<PaymentMethod> payments = null;
LinkedList<Address> addresses = null;
String iscart = (String)request.getAttribute("iscart");
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
	payments = (LinkedList<PaymentMethod>) request.getAttribute("payments");
	addresses = (LinkedList<Address>) request.getAttribute("addresses");
}
%>
 	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>PANDAFLIX &mdash; CheckOut</title>
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
					<h2>Check Out</h2>
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
		<div class="row -post-entry single-entry">
			<article class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2 col-xs-12 col-xs-offset-0">
								
				<div class="col-lg-12 col-lg-offset-0 col-md-12 col-md-offset-0 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-left content-article">
					<div class="row">
				
				<form action="/Project/buy" method="post" id="buyit">
					<div class="col-lg-4 animate-box">
					<div class="-highlight right">
					 <fieldset>
				  <legend>Payment Method</legend>
		   				<h4>Payment Method</h4>
				 
				  <select name="paymentmethod" >
				  <% if (payments != null)
					  for (PaymentMethod paymentmp: payments){
						  payment.setCardNumber(paymentmp.getCardNumber());
					  %>
				   <option value=<jsp:getProperty name="payment" property="cardNumber"/> selected="selected"> <jsp:getProperty name="payment" property="cardNumber"/></p></option>
				   <%} %>
				   </select>
				 </fieldset>
		    		</div>
		    		</div>
				
				
				<div class="col-lg-4 animate-box">
					<div class="-highlight right">
				 <fieldset>
				  <legend>Shipping Address</legend>
		   				<h4>Shipping Address</h4>
				 
				  <select name="address" >
				  <% if (addresses != null)
					  for (Address addresstmp: addresses){
					  address.setCountry(addresstmp.getCountry());
					  address.setDistrict(addresstmp.getDistrict());
					  address.setStreet(addresstmp.getStreet());
					  address.setZipcode(addresstmp.getZipcode());
					  %>
				  
				   <option value=<jsp:getProperty name="address" property="street"/> selected="selected"> <p> <jsp:getProperty name="address" property="street"/>, <jsp:getProperty name="address" property="district"/>, <jsp:getProperty name="address" property="country"/>, <jsp:getProperty name="address" property="zipcode"/></p></option>
				   <%} %>
				   </select>
				 </fieldset>
				 </div>
				 </div>
				</form>
															
						<div class="top-row">
						 <div class="field-wrap">
						 <%if (iscart != null && iscart.equals("true")) {%>
          					<button class="button" name="iscart" value="true" form="buyit">Confirm your information</button> 
          				 <%}else{ %>
          					 <button class="button" name="iscart" value="false" form="buyit">Confirm your information</button> 
           				 <% }%>
          				</div> 
          				
          				
          				
					</div>
						</div>
					<div class="row rp-b">
						<div class="col-md-12 animate-box">
							<blockquote>
								<p>If you want to change shipping address</p>
							</blockquote>
						</div>
					</div>
					
					<br>
	<form action="addressform.html">
		<div class="container-fluid" style=" float: center">
			<div class="row -post-entry">
				<button class="button">Change!</button>
			</div>
		</div>
		</form>
		
					<div class="row rp-b">
						<div class="col-md-12 animate-box">
							<blockquote>
								<p>If you want to change payment method</p>
							</blockquote>
						</div>
					</div>
					
					<br>
	<form action="paymentform.html">
		<div class="container-fluid" style=" float: center">
			<div class="row -post-entry">
				<button class="button">Change!</button>
			</div>
		</div>
		</form>
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

