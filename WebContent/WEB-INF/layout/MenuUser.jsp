<div class="menuUser">
	<c:if test="${!empty sessionScope.user }">
	
		<c:if test="${ sessionScope.user.admin }">
			<ul class="jaune">
				<li><a href="<c:url value="/admin/stagiaire/add"/>">Ajouter Stagiaire</a></li>
			</ul>
		</c:if>
		<ul>
			<li><a href="<c:url value="/stagiaire/id/${ sessionScope.user.id }"/>">voir compte</a></li>
			<li><a href="<c:url value="/compte/stagiaire/modifier"/>">modifier</a></li>
			<li><a href="<c:url value="/compte/metier/ajouter"/>">Ajouter Métier</a></li>
			<li><a href="<c:url value="/compte/metier/chercher"/>">Chercher Métier</a></li>
		</ul>
	</c:if>
</div>