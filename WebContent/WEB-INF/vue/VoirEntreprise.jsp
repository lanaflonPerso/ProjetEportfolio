<h1>
	${ entreprise.nom }
	<c:if test="${ not empty sessionScope.user.id && source }">
		<a href="<c:url value="/compte/metier/ajouter?entreprise=${ entreprise.id }"/>">
			<button type="button" class="btn btn-info btn-sm">Ajouter votre métier</button>
		</a>
	</c:if>
</h1>

<p>${ entreprise.adresse }</p>
<p>${ entreprise.codePostal } ${ entreprise.ville }</p>
