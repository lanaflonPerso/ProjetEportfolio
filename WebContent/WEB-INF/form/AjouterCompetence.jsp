<form method="post" action="">
	<div class="row">
		<div class="col-md-5 col-sm-12">
			<c:if test="" >
				<div class="alert alert-danger" role="alert">
					<ul>

					</ul>
				</div>
			</c:if>	
		</div>
		<div class="col-md-7 col-sm-12">
			<fieldset class="form-group">
	    		<legend>Compétence acquise dans l'entreprise</legend>
	    		
	    		<div class="form-group">
	    			<input name="idMetier" type="hidden" value="${ metier.id }" require />
					<input name="competence" type="text" class="form-control " id="competence" placeholder="Compétence acquise" require />
				</div>	
	    	</fieldset>
	    	
	    	<button type="submit" class="btn btn-primary">Ajouter</button>
	    </div>
	</div>
</form>