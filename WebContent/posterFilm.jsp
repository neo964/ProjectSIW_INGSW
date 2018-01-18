<%@page import="Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> 
<head>
	<jsp:useBean id="curSession" class="Model.UserSession" scope="session"/>
	<jsp:useBean id="preview" class="Model.PreviewMultimedia" scope="session"/>
<%
User user = (User) session.getAttribute("user");
boolean itsok = false;
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
	if (request.getAttribute("id") != null){
		itsok = true;
		int id = (int) request.getAttribute("id");
		preview.setId(id);
	}
}
%>

 	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>PANDAFLIX &mdash; FormFilm</title>
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
				</div>

			</div>
		
		</div>

	</header>


<body>
<form action = "/Project/addNewFilm" method = "post">
  <div class="form" >

          <h1>Insert a new Film</h1>

           <div class="field-wrap">
           	<h4>Title</h4>
			<input name="Title" type="text" value="" size="40" maxlength="200" />
          </div>            
          
          <div class="field-wrap">
          <h4>Category</h4>
			<input name="Category" type="text" value="" size="40" maxlength="200" />
          </div>
          
           <div class="field-wrap">
           <h4>Director</h4>
			<input name="Director" type="text" value="" size="40" maxlength="200" />
          </div>
          
		  <div class="field-wrap">
		  <h4>Plot</h4>
            <input name="Plot" type="text" value="" size="40" maxlength="2000" />
          </div>
          
		 <div class="field-wrap">
		 <h4>URL Image</h4>
          <input name="Image" type="text" value="" size="40" maxlength="200" />
          </div>
          
		 <div class="field-wrap">
		 <h4>URL Trailer</h4>
          <input name="Trailer" type="text" value="" size="40" maxlength="200" />
          </div>
          
		 <div class="field-wrap">
		 <h4>URL Film</h4>
          <input name="VideoOnDemand" type="text" value="" size="40" maxlength="200" />
          </div>
          
        <div class="field-wrap">
        <h4>Year</h4>
          <input name="Year" type="text" value="" size="40" maxlength="200" />
          	</div>
          	
          	
        <div class="field-wrap">
        <h4>Price</h4>
          <input name="Price" type="text" value="" size="40" maxlength="200" />
          	</div>
          	
          	<div class="field-wrap">
        <h4>Actors</h4>
         <textarea name="Actors" rows="5" cols="40">
 			 
		</textarea>
          	</div>
          	
          	<%if (itsok) {%>
         <div class="field-wrap">
        <h4>ID</h4>
          <input name="id" type="text" value=<jsp:getProperty name="preview" property="id" />/>
         </div>
          	<%} %>
          	
          <div class="field-wrap">
          	<button type="submit" class="button ">Post It!</button>
          </div> 
           
           </div>
          </form>

        </div>
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->
</form>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
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
