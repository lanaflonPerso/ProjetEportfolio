<h1>Connection</h1>
  			
<form method="post" action="/ProjetEportfolio/connection">
	<div class="row">
		<div class="col-md-5 col-sm-12">	
			<c:if test="${ not empty info.ok && !info.ok }" >
				<ul>
					<c:if test="${ not empty info.msgErreur }" ><li>${ info.msgErreur }</li></c:if>
					<c:if test="${ not empty info.msgErrEmail }" ><li>${ info.msgErrEmail }</li></c:if>
				</ul>
			</c:if>
		</div>
		<div class="col-md-7 col-sm-12">
			<fieldset class="form-group">
				<legend>Connection</legend>
				<div class="form-group">
					<label for="email">Email: </label>
					<input name="email" type="text" class="form-control" id="email" placeholder="Email stagiaire"
					<c:if test="${ not empty info.ok && !info.ok }" >value="${ email }"</c:if> require />
				</div>
				 
				<div class="form-group">
					<label for="mdp">Password: </label>
					<input name="mdp" type="password" class="form-control" id="mdp" placeholder="Mot de passe"  require />
				</div>
				
				<button type="submit" class="btn btn-primary">Connection</button>
			</fieldset>	
		</div>
	</div>				
</form>

