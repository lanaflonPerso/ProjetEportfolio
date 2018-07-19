<h1>${ stagiaire.nom } ${ stagiaire.prenom } ${ stagiaire.age } ans</h1> 

<p>Listes D'entreprise <a href="/ProjetEportfolio/entreprise/ajouter"><button type="button" class="btn btn-info btn-sm">Ajouter</button></a></p>

<ul class="list-group">
	<c:forEach items="${ stagiaire.getMetiers() }" var="metier">
		<ul>
			<c:forEach items="${ metier.getEntreprises() }" var="entreprise">
				<li>
					${ entreprise.getNom() } à ${ entreprise.ville } de ${ metier.dateEntree } a ${ metier.dateSortie }<br />
					comme ${ metier.getFonction() }<br />
					description du poster: ${ metier.description }
				</li>
			</c:forEach>
			<ul>
				<c:forEach items="${ metier.getCompetences() }" var="competence">
					<li>${ competence.getNom() }</li>
				</c:forEach>
			</ul>
		</ul>
    	<%-- <li class="list-group-item">${ entreprise.nom } (${ entreprise.id })</li>
    	<ul>
    	<c:forEach items="${ entreprise.getMetier() }" var="metier">
    		<li>
    			<p>${ metier.fonction } (${ metier.id })</p>
    			<p>${ metier.description }</p>
    		</li>
    		<ul>
    			<c:forEach items="${ metier.getCompetence() }" var="competence">
    				<li>${ competence.nom } (${ competence.id })</li>
    			</c:forEach>
    		</ul> --%>
	</c:forEach>
</ul>