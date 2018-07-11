<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="stagiaire" class="com.vianney.bean.Stagiaire" scope="request" />

 		<%@ include file="/WEB-INF/layout/Doctype.jsp" %>

		<div class="container">
			
			<div class="col-md-5 offset-md-3">
				<h1>Création d'un stagiaire</h1>
				
				<c:if test="${ post }" >
				
					<c:choose>
	    				<c:when test="${ ok }">
							<div class="alert alert-success" role="alert">
								<strong>Ok</strong> utilisateur crée.<br>
								<p>nom: ${ map.get("msgNom") }</p>
								<p>prenom: ${ map.get("msgPrenom") }</p>
								<p>email: ${ map.get("msgEmail") }</p>
							</div>
						</c:when>
						<c:otherwise>
							<div class="alert alert-danger" role="alert">
								<strong>Erreur</strong><br>
								${ map.get("msgNom") } <br>
								${ map.get("msgPrenom") }<br>
								${ map.get("msgEmail") }<br>
		
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
						<input name="nom" type="text" class="form-control ${ map.get("classNom") }" id="nom" placeholder="Nom du stagiaire" value="${ map.get("nom") }" require />
					</div>
					 
					<div class="form-group">
						<label for="prenom">Prénom: </label>
						<input name="prenom" type="text" class="form-control ${ map.get("classPrenom") }" id="prenom" placeholder="Prénom du stagiaire" value="${ map.get("prenom") }" require />
					</div>
					
					<div class="form-group">
						<label for="email">Email: </label>
						<input name="email" type="text" class="form-control ${ map.get("classEmail") }" id="email" placeholder="Email du stagiaire"  value="${ map.get("email") }" require>
					</div>
					
					<div class="form-group">
						<label for="ddn">Date De Naissance: </label>
						<input name="ddn" type="text" class="form-control" id="ddn" placeholder="Date de naissance du stagiaire format 05/06/1979">
					</div>
					
					<button type="submit" class="btn btn-primary">Envoyer</button>					
				</form>
			</div>
		</div>
	</body>
</html>