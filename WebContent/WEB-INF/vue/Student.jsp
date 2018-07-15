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
	<c:forEach items="${ stagiaire.getEntreprises() }" var="titre" varStatus="entreprise">
    	<p>N°<c:out value="${ entreprise.count }" /> : <c:out value="${ titre.getNom() }" />  <c:out value="${ titre.getAdresse() }" />!</p>
	</c:forEach>
</body>
</html>