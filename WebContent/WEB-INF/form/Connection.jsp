<div class="col-md-5 offset-md-3">
	<h1>Connection</h1>
   			
	<form method="post" action="/ProjetEportfolio/connection">

		<div class="form-group">
			<select name="civilite" class="form-control" id="civilite">
				<option>Monsieur</option>
				<option>Madame</option>
				<option>Mademoiselle</option>
			</select>
		</div>
		
		<div class="form-group formConnectio">
			<label for="email">Email: </label>

			<input name="email" type="text" class="form-control ${ form.classeEmail }" id="email" placeholder="Email stagiaire"
				<c:if test="${ !ok }" >value="${ Email }"</c:if> require />
		</div>
		 
		<div class="form-group">
			<label for="prenom">Password: </label>

			<input name="mdp" type="password" class="form-control" id="mdp" placeholder="Mot de passe"  require />
		</div>
		
		<button type="submit" class="btn btn-primary">Connection</button>					
	</form>
</div>
