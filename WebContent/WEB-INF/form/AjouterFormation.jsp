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
	    		<legend>Formation</legend>
	    		
	    		<div class="form-group">
	    			<label for="intitule">Intitulé: </label>
					<input name="intitule" type="text" class="form-control " id="intitule" placeholder="Intitulé de la formation" require />
				</div>
				
				<div class="form-group">
	    			<label for="certification">Certification obtenu: </label>
					<input name="certification" type="text" class="form-control " id="certification" placeholder="Certification obtenu" require />
				</div>
				
				<div class="form-group">
	    			<label for="niveau">niveau de la certification: </label>
					<input name="niveau" type="text" class="form-control " id="niveau" placeholder="niveau de la certification" require />
				</div>
				
				<button type="submit" class="btn btn-primary">Ajouter</button>
	    	</fieldset>
	    </div>
	</div>
</form>