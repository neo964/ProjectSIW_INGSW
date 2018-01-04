<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> 
<jsp:useBean id="trailer" class="Model.Trailer" scope="page"/>
<jsp:useBean id="curSession" class="Model.UserSession" scope="session"/>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>PANDAFLIX &mdash; Watch</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="author" content="Pastore-Perri">
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
System.out.println (request.getParameter("path"));
trailer.setPath(request.getParameter("path"));
System.out.println (trailer.getPath());
%>

  <link href="http://vjs.zencdn.net/6.4.0/video-js.css" rel="stylesheet">
  <script src="http://vjs.zencdn.net/ie8/1.1.2/videojs-ie8.min.js"></script>
<script src="js/video.min.js" type="text/javascript"></script>
<script src="js/video.es.js" type="text/javascript"></script>
<script src="js/video.cjs.js" type="text/javascript"></script>
<script src="js/video.js" type="text/javascript"></script>
<script type="text/javascript">
  // Add VideoJS to all video tags on the page when the DOM is ready
  VideoJS.setupAllWhenReady();
</script>
<link rel="stylesheet" href="css/video-js.css" type="text/css">
<link rel="stylesheet" href="css/video-js.min.css" type="text/css">
<link rel="shortcut icon" href="favicon.ico">
<link href='http://fonts.googleapis.com/css?family=Playfair+Display:400,700,400italic|Roboto:400,300,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/icomoon.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/style.css">

<script src="js/modernizr-2.6.2.min.js"></script>




<header id="-header">
		
		<div class="container-fluid">

			<div class="row">
				<!-- logo -->
				<div class="col-lg-12 col-md-12 text-center">
					<h1 id="-logo"><a href="index.jsp">PANDAFLIX <sup>TM</sup></a></h1>
				</div>

			</div>
		
		</div>

	</header>
<div class="container-fluid">
  <video id="my-video" class="video-js" controls preload="auto" width="1280" height="720"
  poster="" data-setup="{}">
    <source src=<jsp:getProperty name="trailer" property="path"/> type='video/mp4'>
    <source src="MY_VIDEO.webm" type='video/webm'>
    <p class="vjs-no-js">
      To view this video please enable JavaScript, and consider upgrading to a web browser that
      <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
    </p>
  </video>

  <script src="http://vjs.zencdn.net/6.4.0/video.js"></script>
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.easing.1.3.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.waypoints.min.js"></script>
	<script src="js/main.js"></script>
</div>


<footer id="-footer">
		<p><small>&copy;2017 ingegneria del software e siw project <br> Designed by Andrea Pastore & Mario Perri</a> </small></p>
</footer>