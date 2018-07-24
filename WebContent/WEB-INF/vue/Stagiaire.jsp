<h1>${ stagiaire.nom } ${ stagiaire.prenom } ${ stagiaire.age } ans</h1> 

<p>Listes D'entreprise <a href="<c:url value="/compte/entreprise/ajouter"/>"><button type="button" class="btn btn-info btn-sm">Ajouter</button></a></p>

<ul class="list-group">
	<c:forEach items="${ stagiaire.getMetiers() }" var="metier">
		<ul>
			<c:forEach items="${ metier.getEntreprises() }" var="entreprise">
				<li>
					${ entreprise.getNom() } à ${ entreprise.ville } de ${ metier.dateEntree } a ${ metier.dateSortie }<br />
					comme ${ metier.getFonction() }
					<a href="<c:url value="/compte/competence/ajouter/${ metier.id }"/>"><button type="button" class="btn btn-info btn-sm">Ajouter une competence</button></a><br />
					description du poster: ${ metier.description }
				</li>
			</c:forEach>
			<ul>
				<c:forEach items="${ metier.getCompetences() }" var="competence">
					<li>${ competence.getNom() }</li>
				</c:forEach>
			</ul>
		</ul>
	</c:forEach>
</ul>