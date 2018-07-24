<div class="row">

	<div class="col-md-6">
		<c:if test = "${ ok }">
			<h2>metier</h2>
			<ul class="list-group">
				<c:forEach items="${ entreprises }" var="entreprise">
					<li>
						<a href="/ProjetEportfolio/metier/id/1/${ entreprise.id }">${ entreprise.nom }</a> à ${ entreprise.ville } de ${ metier.dateEntree } a ${ metier.dateSortie }
					</li>
				</c:forEach>
			</ul>
		</c:if>
	</div>
	
	<div class="col-md-6">
	
	<form method="get" action="">
		<fieldset class="form-group">
	    	<legend>Rechercher une entreprise</legend>
			
			<input type="hidden" name="idMetier" value="${ idMetier }" />
			<div class="form-group">
				<label for="nom">Nom: </label>
				<input name="nom" type="text" class="form-control" id="nom" placeholder="" require />
			</div>
			
			<div class="form-group">
				<label for="cp">Code postal: </label>
				<input name="cp" type="text" class="form-control" id="cp" placeholder="" require />
			</div>
			
			<input type="submit" class="btn btn-primary" value="Rechercher" />
		</fieldset>
	</form>
	
	<a href="/ProjetEportfolio/entreprise/ajouter"><button class="btn btn-primary" name="ajouter">Ajouter une entreprise</button></a>
	
	</div>
</div>
