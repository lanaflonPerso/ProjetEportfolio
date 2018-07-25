<form method="post" action="">
	<div class="row">
		<div class="col-md-5 col-sm-12">
			<%-- <c:if test="${ not empty metier.id }" > --%>
				<div>
					<ul>
						<li>${ metier.fonction }</li>
					</ul>
				</div>
			<%-- </c:if>	 --%>
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