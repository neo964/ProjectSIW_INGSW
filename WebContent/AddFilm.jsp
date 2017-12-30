<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> 
<jsp:useBean id="film" class="Model.Film" scope="request" />
<jsp:setProperty name="stud" property="nome" value="un Nome"/>


<html>
<head lang="it">
<title>Pandaflix</title>
<meta charset="utf-8">

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
<script src="js/jquery-3.2.1.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>


</head>
<body>
<header>
<!-- 
<jsp:getProperty name="stud" property="nome"/>
 -->
 <c:if test="${film != null}">
 	<h1>Ho caricato il seguente film</h1>
 	<p>${film.title}</p>
 	<p>${film.id}</p>
 </c:if>
 <c:if test="${studente == null}">
	<h1>Iscrivi un nuovo Studente</h1>
	<h2>Compila i seguente form per registrare un nuovo studente</h2>
 </c:if>
<script language="javascript">
	function messaggioPulisci(){
		alert("I dati verranno ora puliti");
	}
</script>

</header>

<section class="moduloRegistrazione" class="row">
<div class="col-lg-3">
	<form method="post" action="iscriviStudente">
		<div class="form-group"><label for="matricola">Matricola:</label> <input name="matricola" type="text" class="form-control" /> </div> 
		<div class="form-group"><label for="nome">Nome:</label><input name="nome" type="text" class="form-control" /></div> 
		<div class="form-group"><label for="cognome">Cognome:</label> <input name="cognome" type="text" class="form-control" /> </div> 
		<div class="form-group"><label for="dataNascita">Data di Nascita:</label> <input name="dataNascita" type="date" class="form-control" /> </div> 
		<div class="form-group"><label for="password">Password per lo studente:</label> <input name="password" type="password" class="form-control" /> </div> 
		<div class="form-group"><label for="confermaPassword">Conferm password:</label>  <input name="confermaPassword" type="password" class="form-control" /> </div> 
		<div class="form-group"><label for="indirizzo">Indirizzo:</label>			
			<select name="indirizzo" class="form-control">
				<optgroup label="Intelligenza Artificiale">
				<option value="2">Robotica</option>
				<option value="1">Logica Computazionale</option>
				</optgroup>
				<optgroup label="Storia">
				<option value="3">Archeologia</option>
				</optgroup>				
			</select>		
		</div>		
		<div class="form-group">
			<input name="validaDati" type="button" value="Valida Dati" class="btn btn-warning"/>
			<input name="resetDati" type="reset" value="Reset Dati"  class="btn btn-danger" onclick="messaggioPulisci();"/>
			<input name="inviaDati" type="submit" value="Invia Dati"  class="btn btn-success"/>
		</div>		
	</form>
</div>
</section>


</body>
</html>