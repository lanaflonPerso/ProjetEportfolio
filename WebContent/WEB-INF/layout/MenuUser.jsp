<c:if test="${!empty sessionScope.user }">
	<ul>
		<li><a href="<c:url value="/stagiaire/id/${ sessionScope.user.id }"/>">voir compte</a></li>
		<li><a href="<c:url value="/stagiaire/modifier"/>">modifier</a></li>
		<li><a href="<c:url value="/compte/metier/ajouter"/>">Ajouter M�tier</a></li>
		<li><a href="<c:url value="/metier/chercher"/>">Chercher M�tier</a></li>
	</ul>
</c:if>