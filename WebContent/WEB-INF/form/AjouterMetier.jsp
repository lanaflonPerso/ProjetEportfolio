<h1>Ajouter Metier</h1>
 
<form method="post" action="">
	<div class="row">
		<div class="col-md-5 col-sm-12">
			<c:if test="${ not empty info.ok && !info.ok }" >
				<div class="alert alert-danger" role="alert">
					<ul>
						<c:if test="${ not empty info.msgErrFonction }" ><li>${ info.msgErrFonction }</li></c:if>
						<c:if test="${ not empty info.msgErrDescription }" ><li>${ info.msgErrDescription }</li></c:if>
						<c:if test="${ not empty info.msgErrDateEntree }" ><li>${ info.msgErrDateEntree }</li></c:if> 
						<c:if test="${ not empty info.msgErrDateSortie }" ><li>${ info.msgErrDateSortie }</li></c:if>
					</ul>
				</div>
			</c:if>	
		</div>
		<div class="col-md-7 col-sm-12">
			<fieldset class="form-group">
	    		<legend>Votre r�le dans l'entreprise</legend>
	    		
				<div class="form-group">
					<label for="fonction">Fonction: </label>
					<input name="fonction" type="text" class="form-control ${ info.classeFonction }" id="fonction" placeholder="Fonction occup� dans l'entreprise"
					<c:if test="${ not empty info.ok && !info.ok }" >value="${ metier.fonction }"</c:if> require />
				</div>
				 
				<div class="form-group">
					<label for="description">Description: </label>
					<input name="description" type="text" class="form-control ${ info.classeDescription }" id="description" placeholder="Description de votre r�les dans l'entreprise"
						<c:if test="${ not empty info.ok && !info.ok }" >value="${ metier.description }"</c:if> require />
				</div>
				
				<div class="form-group">
					<label for="dateE">Date d'entr�e: </label>
					<input name="dateE" type="text" class="form-control" id="dateE" placeholder="Date d'entr� dans l'entreprise" require />
				</div>
				
				<div class="form-group">
					<label for="dateS">Date de fin de contrat: </label>
					<input name="dateS" type="text" class="form-control" id="dateS" placeholder="Date de d�part de l'entreprise" require />
				</div>		
			</fieldset>
			
			<fieldset class="form-group">
	    		<legend>Comp�tence acquise dans l'entreprise</legend>
	    		
	    		<div class="form-group">
					<label for="nom">Comp�tence: </label>
					<input name="dateS" type="text" class="form-control " id="dateS" placeholder="Comp�tence acquise" require />
				</div>	
	    	</fieldset>

			<button type="submit" class="btn btn-primary">Enregistrer</button>
		</div>
	</div>			
</form>