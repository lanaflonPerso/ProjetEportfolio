<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 		<%@ include file="/WEB-INF/layout/Doctype.jsp" %>

		<div class="container">
			
			<div class="col-md-5 offset-md-3">
				<h1>Création d'un stagiaire</h1>
				
				<%-- <c:choose>
    				<c:when test="${erreur == "non"}">
						<div class="alert alert-success" role="alert">
							<strong>Ok</strong> utilisateur crée.
						</div>
					</c:when>
					<c:otherwise>
						<div class="alert alert-danger" role="alert">
							<strong>Erreur</strong> l'utilisateur n'a pas put être crée.
						</div>
					</c:otherwise>
				</c:choose> --%>
				
				<form class="formCreateStagiaire" method="post" action="/ProjetEportfolio/creationstagiaire/">
				
					<div class="form-group formCreateStagiaire">
						<label for="nom">Nom: </label>
						<input name="nom" type="text" class="form-control" id="nom" placeholder="Nom du stagiaire">
					</div>
					 
					<div class="form-group">
						<label for="prenom">Prénom: </label>
						<input name="prenom" type="text" class="form-control" id="prenom" placeholder="Prénom du stagiaire">
					</div>
					
					<div class="form-group">
						<label for="email">Email: </label>
						<input name="email" type="email" class="form-control" id="email" placeholder="Email du stagiaire">
					</div>
					
					<button type="submit" class="btn btn-primary">Envoyer</button>					
				</form>
			</div>
		</div>
	</body>
</html>