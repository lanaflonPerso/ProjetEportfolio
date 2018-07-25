<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<h1>${ stagiaire.nom } ${ stagiaire.prenom } ${ stagiaire.age } ans</h1> 

<p>Listes D'entreprise <a href="<c:url value="/compte/entreprise/ajouter"/>"><button type="button" class="btn btn-info btn-sm">Ajouter</button></a></p>

<div class="listeStagiaire">
	<c:forEach items="${ stagiaire.getMetiers() }" var="metier">
		<ul >
			<c:forEach items="${ metier.entreprises }" var="entreprise">
				<li>
					<span class="entreprise">${ entreprise.getNom() } à ${ entreprise.ville } de <tags:localDate date="${ metier.dateEntree }"/> a <tags:localDate date="${ metier.dateSortie }"/></span><br />
					<span class="fonction">comme ${ metier.getFonction() }</span>
					<a href="<c:url value="/compte/competence/ajouter/${ metier.id }"/>"><button type="button" class="btn btn-info btn-sm">Ajouter une competence</button></a><br />
					<span class="description">description du poster: ${ metier.description }</span>
				</li>
			</c:forEach>
			<ul class="competence">
				<c:forEach items="${ metier.getCompetences() }" var="competence">
					<li>
						${ competence.nom } <a href="<c:url value="/compte/competence/effacer/${ competence.id }/${ metier.id }"/>">Suprrimer</a>
					</li>
				</c:forEach>
			</ul>
		</ul>
		<hr />
	</c:forEach>
</div>