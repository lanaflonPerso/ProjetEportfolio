<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	${ stagiaire.getNom() } ${ stagiaire.getPrenom() }  ${ stagiaire.getAge() } 
	
	<p>Listes D'entreprise</p>
	
	<ul class="list-group">
	
		<c:forEach items="${ stagiaire.getEntreprises() }" var="entreprise" varStatus="boucle">
	    	<li class="list-group-item"> ${ entreprise.nom } à ${ entreprise.ville }</li>
		</c:forEach>
	</ul>
</body>
</html>