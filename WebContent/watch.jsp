<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> 
<jsp:useBean id="trailer" class="Model.Trailer" scope="page"/>
<jsp:useBean id="curSession" class="Model.UserSession" scope="session"/>

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
<h1><jsp:getProperty name="trailer" property="path"/> </h1>

<head>
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
</head>
  <link href="http://vjs.zencdn.net/6.4.0/video-js.css" rel="stylesheet">

  <!-- If you'd like to support IE8 -->
  <script src="http://vjs.zencdn.net/ie8/1.1.2/videojs-ie8.min.js"></script>
</head>

<body>
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
</body>