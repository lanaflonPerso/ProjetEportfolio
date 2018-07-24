<h1>Rechercher un metier</h1>

<form method="get" action="">
	<div class="row">
		<div class="col-md-5 col-sm-12">
			<c:if test = "${ ok }">
				<h2>Metiers</h2>
				<ul class="list-group">
					<c:forEach items="${ metiers }" var="metier">
						<li>
							<a href="<c:url value="/compte/metier/id/${ metier.id }"/>">${ metier.fonction }</a>
						</li>
					</c:forEach>
				</ul>
			</c:if>
		</div>
		<div class="col-md-7 col-sm-12">
			<div class="form-group">
				<label for="recherche">Métier: </label>
				<input name="recherche" type="text" class="form-control" id="recherche" placeholder="Mots cles" require />
			</div>
			
			<button type="submit" class="btn btn-primary">Rechercher</button>	
		</div>
	</div>				
</form>