<form method="post" action="">
	<div class="row">
		<div class="col-md-5 col-sm-12">
			<h2>${ metier.fonction }</h2>
			<div id="result">
			</div>
		</div>
		<div class="col-md-7 col-sm-12">
			<fieldset class="form-group">
	    		<legend>Compétence acquise dans l'entreprise</legend>
	    		
	    		<div class="form-group">
	    			<input name="idMetier" type="hidden" value="${ metier.id }" require />
					<input name="competence" type="text" class="form-control " id="competence" placeholder="Compétence acquise" require />
				</div>	
				<button type="submit" class="btn btn-primary">Ajouter</button>
	    	</fieldset>
	    </div>
	</div>
</form>

<form method="post" action="">
	<input name="idMetier" type="hidden" value="${ metier.id }" require />
	<input type="hidden" name="competence" value="" />
</form>

<script type="text/javascript" src="<c:url value ="/js/oXHR.js" />"></script>
<script type="text/javascript" src="<c:url value ="/js/listCompetence.js" />"></script>