<%@ page pageEncoding="UTF-8" %>

<div class="col-md-5 offset-md-3">
	<h1>Connection</h1>
    
	<c:if test="${ !ok }" >			
		<div class="alert alert-danger" role="alert">
			<strong>Erreur</strong><br>
				<c:if test="${not empty form.msgErrNom}">${ form.msgErrNom }<br></c:if>
				<c:if test="${not empty form.msgErrPrenom}">${ form.msgErrPrenom }<br></c:if>
				<c:if test="${not empty form.msgErrEmail}">${ form.msgErrEmail }<br></c:if>
				<c:if test="${not empty form.msgErrDdn}">${ form.msgErrDdn }</c:if>
		</div>
	</c:if>
			
	<form class="formCreateStagiaire" method="post" action="/ProjetEportfolio/creationstagiaire/">

		<div class="form-group formCreateStagiaire">
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
