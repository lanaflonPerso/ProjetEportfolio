<div class="col-md-5 offset-md-3">
	<h1>Connection</h1>
	  			
	<form method="post" action="/ProjetEportfolio/connection">
		
		<div>
			<p>${ var. msgErreur }
		</div>
		
		<div class="form-group">
			<label for="email">Email: </label>
	
			<input name="email" type="text" class="form-control ${ form.classeEmail }" id="email" placeholder="Email stagiaire"
				<c:if test="${ !ok }" >value="${ Email }"</c:if> require />
		</div>
		 
		<div class="form-group">
			<label for="mdp">Password: </label>
	
			<input name="mdp" type="password" class="form-control" id="mdp" placeholder="Mot de passe"  require />
		</div>
		
		<button type="submit" class="btn btn-primary">Connection</button>					
	</form>
</div>
