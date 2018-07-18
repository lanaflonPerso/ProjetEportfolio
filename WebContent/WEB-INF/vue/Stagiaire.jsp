<h1>${ stagiaire.getNom() } ${ stagiaire.getPrenom() }</h1> 

<p>Listes D'entreprise<a href="/ProjetEportfolio/entreprise/ajouter">Ajouter</a></p>

<ul class="list-group">
	<c:forEach items="${ stagiaire.getEntreprises() }" var="entreprise">
    	<li class="list-group-item"> ${ entreprise.nom }</li>
    	<ul>
    	<c:forEach items="${ entreprise.getMetier() }" var="metier">
    		<li> ${ metier.fonction }</li>
    	</c:forEach>
    	</ul>
	</c:forEach>
</ul>