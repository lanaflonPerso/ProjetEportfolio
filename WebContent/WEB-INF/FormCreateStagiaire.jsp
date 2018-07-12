<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 		<%@ include file="/WEB-INF/layout/Doctype.jsp" %>

		<div class="container">
			
			<div class="col-md-5 offset-md-3">
				<h1>Création d'un stagiaire</h1>
				
				<c:if test="${ post }" >
				
					<c:choose>
	    				<c:when test="${ ok }">
							<div class="alert alert-success" role="alert">
								<strong>Ok</strong> utilisateur crée.<br />
								nom: ${ stagiaire.civilite } ${ stagiaire.nom }<br />
								prenom: ${ stagiaire.prenom }<br />
								email: ${ stagiaire.email }<br />
								Age: ${ stagiaire.age }<br />
							</div>
						</c:when>
						<c:otherwise>
							<div class="alert alert-danger" role="alert">
								<strong>Erreur</strong><br>
									${ form.msgErrNom }<br>
									${ form.msgErrPrenom }<br>
									${ form.msgErrEmail }<br>
									${ form.msgErrDdn }
							</div>
						</c:otherwise>
					</c:choose>
				</c:if>
				
				<form class="formCreateStagiaire" method="post" action="/ProjetEportfolio/creationstagiaire/">
				
					<div class="form-group formCreateStagiaire">
						<select name="civilite" class="form-control" id="civilite">
							<option>Monsieur</option>
							<option>Madame</option>
							<option>Mademoiselle</option>
						</select>
					</div>
					
					<div class="form-group formCreateStagiaire">
						<label for="nom">Nom: </label>

						<input name="nom" type="text" class="form-control ${ form.classeNom }" id="nom" placeholder="Nom du stagiaire"
							<c:if test="${ !ok }" >value="${ nom }"</c:if> require />
					</div>
					 
					<div class="form-group">
						<label for="prenom">Prénom: </label>

						<input name="prenom" type="text" class="form-control ${ form.classePrenom }" id="prenom" placeholder="Prénom du stagiaire" 
							<c:if test="${ !ok }" >value="${ prenom }"</c:if> require />
					</div>
					
					<div class="form-group">
						<label for="email">Email: </label>

						<input name="email" type="text" class="form-control ${ form.classeEmail }" id="email" placeholder="Email du stagiaire"  
							<c:if test="${ !ok }" >value="${ email }"</c:if> require>
					</div>
					
					<div class="form-group">
						<label for="ddn">Date De Naissance: </label>

						<input name="ddn" type="text" class="form-control ${ form.classeDdn }" id="ddn" placeholder="Date de naissance du stagiaire format 05/06/1979" 
							<c:if test="${ !ok }" >value="${ ddn }"</c:if> pattern="[0-9]{2}/[0-9]{2}/[1-2][0-9]{3}">
					</div>
					
					<button type="submit" class="btn btn-primary">Envoyer</button>					
				</form>
			</div>
		</div>
	</body>
</html>