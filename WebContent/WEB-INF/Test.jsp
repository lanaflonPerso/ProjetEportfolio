<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<p>Nom Stagiaire= ${ stagiaire.getNom() }</p>
	<p>Prenom Stagiaire= ${ stagiaire.getPrenom() }</p>
	

	<ul>                            
	<c:forEach items="${ stagiaire.getEntreprises() }" var="entreprise" >
		<li>${ entreprise.getNom() }</li>
		<li>${ entreprise.getAdresse() }</li>
		<li>${ entreprise.getCodePostal() }</li>
		<li>${ entreprise.getVille() }</li>
	</c:forEach>
	</ul>
