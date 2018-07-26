<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<h1>${ stagiaire.nom } ${ stagiaire.prenom } ${ stagiaire.age } ans</h1> 

<div class="listeStagiaire">
	<p class="titre">Listes D'entreprise <a href="<c:url value="/compte/entreprise/ajouter"/>"><button type="button" class="btn btn-info btn-sm">Ajouter</button></a></p>
	<c:if test="${not empty  metier.id || metier.id == null }">
		<p> le stagiaire n'a pas enregistré d'expérience professionelle</p> 
	</c:if>
	<c:forEach items="${ stagiaire.metiers }" var="metier">
		<ul>
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
	<p class="titre">Listes de formations <a href="<c:url value="/compte/formation/ajouter"/>"><button type="button" class="btn btn-info btn-sm">Ajouter</button></a></p>
	<c:if test="${ not empty  formation.id || formation.id == null }">
		<p> le stagiaire n'a enregistré aucune formation</p> 
	</c:if>
	<ul>
	<c:forEach items="${ stagiaire.formations }" var="formation">
		<li>
			<h2>Centre de formation inconnu</h2>
			<ul>
				<li>intitulé de la formation: ${ formation.intituleFormation }</li>
				<c:if test="${ not empty formation.certification.nom }"><li>Certication obtenu: ${ formation.certification.nom } niveau ${ formation.certification.niveau }</li></c:if>
			</ul>
		</li>
	</c:forEach>
	</ul>
</div>