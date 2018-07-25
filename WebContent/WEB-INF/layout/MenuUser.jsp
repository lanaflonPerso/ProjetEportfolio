<c:if test="${!empty sessionScope.user }">

	<c:if test="${ sessionScope.user.admin }">
		<ul>
			<li><a href="<c:url value="/admin/stagiaire/add"/>">Ajouter Stagiaire</a></li>
		</ul>
	</c:if>
	<ul>
		<li><a href="<c:url value="/stagiaire/id/${ sessionScope.user.id }"/>">voir compte</a></li>
		<li><a href="<c:url value="/stagiaire/modifier"/>">modifier</a></li>
		<li><a href="<c:url value="/compte/metier/ajouter"/>">Ajouter Métier</a></li>
		<li><a href="<c:url value="/metier/chercher"/>">Chercher Métier</a></li>
	</ul>
</c:if>