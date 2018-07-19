<h1>Connection</h1>
  			
<form method="post" action="/ProjetEportfolio/connection">
	<div class="row">
		<div class="col-md-5 col-sm-12">	
			<c:if test="${ not empty var.ok && !var.ok }" >
				<ul>
					<c:if test="${ not empty var.msgErreur }" ><li>${ var.msgErreur }</li></c:if>
					<c:if test="${ not empty var.msgEmail }" ><li>${ var.msgEmail }</li></c:if>
					<c:if test="${ not empty var.msgMdp }" ><li>${ var.msgMdp }</li></c:if>
				</ul>
			</c:if>
		</div>
		<div class="col-md-7 col-sm-12">
		
			<div class="form-group">
				<label for="email">Email: </label>
				<input name="email" type="text" class="form-control" id="email" placeholder="Email stagiaire"
				<c:if test="${ not empty var.ok && !var.ok }" >value="${ var.email }"</c:if> require />
			</div>
			 
			<div class="form-group">
				<label for="mdp">Password: </label>
				<input name="mdp" type="password" class="form-control" id="mdp" placeholder="Mot de passe"  require />
			</div>
			
			<button type="submit" class="btn btn-primary">Connection</button>	
		</div>
	</div>				
</form>

