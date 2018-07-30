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


<div>
	<c:if test="${ not empty stagiaires }">
		<h3>Liste de stagiaire qui ont travailler dans cettte entreprise</h3>
		<ul>
			<c:forEach items="${ stagiaires }" var="stagiaire" >
			    <li><a href="<c:url value="/stagiaire/id/${ stagiaire.id }" />">${ stagiaire.nom } ${ stagiaire.prenom }</a></li>
			</c:forEach>
		</ul>
	</c:if>
</div>