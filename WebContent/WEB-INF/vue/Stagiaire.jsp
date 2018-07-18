<h1>${ stagiaire.getNom() } ${ stagiaire.getPrenom() }</h1> 

<p>Listes D'entreprise<a href="/ProjetEportfolio/entreprise/ajouter">Ajouter</a></p>

<ul class="list-group">
	<c:forEach items="${ stagiaire.getEntreprises() }" var="entreprise">
    	<li class="list-group-item">${ entreprise.nom } (${ entreprise.id })</li>
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
    		</ul>
    	</c:forEach>
    	</ul>
	</c:forEach>
</ul>