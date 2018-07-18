<c:if test = "${empty stagiaire.nom}">
	<jsp:forward page="/notExist.jsp"/>
</c:if>

<h1>${ stagiaire.nom } ${ stagiaire.prenom }</h1>