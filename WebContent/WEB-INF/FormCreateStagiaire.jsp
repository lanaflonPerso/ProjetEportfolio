<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<<<<<<< HEAD
=======
<jsp:useBean id="stagiaire" class="com.vianney.bean.Stagiaire" scope="request" />

>>>>>>> 1adf6a495cc5ba058073815ef548bcc5ab6c4910
 		<%@ include file="/WEB-INF/layout/Doctype.jsp" %>

		<div class="container">
			
			<div class="col-md-5 offset-md-3">
				<h1>Création d'un stagiaire</h1>
				
<<<<<<< HEAD
				<%-- <c:choose>
    				<c:when test="${erreur == ""}">
						<div class="alert alert-success" role="alert">
							<strong>Ok</strong> utilisateur crée.
						</div>
					</c:when>
					<c:otherwise>
						<div class="alert alert-danger" role="alert">
							<strong>Erreur</strong><br>
							${ erreur }
						</div>
					</c:otherwise>
				</c:choose> --%>
=======
				<c:if test="${ post }" >
				
					<c:choose>
	    				<c:when test="${ ok }">
							<div class="alert alert-success" role="alert">
								<strong>Ok</strong> utilisateur crée.<br />
								nom: ${ map.get("civilite") } ${ map.get("nom") }<br />
								prenom: ${ map.get("prenom") }<br />
								email: ${ map.get("email") }<br />
								Date de naissance: ${ map.get("ddn") }<br />
							</div>
						</c:when>
						<c:otherwise>
							<div class="alert alert-danger" role="alert">
								<strong>Erreur</strong><br>
									${ map.get("msgNom") }
									${ map.get("msgPrenom") }
									${ map.get("msgEmail") }
									${ map.get("msgDdn") }
								<%-- </c:if> --%>
							</div>
						</c:otherwise>
					</c:choose>
				</c:if>
>>>>>>> 1adf6a495cc5ba058073815ef548bcc5ab6c4910
				
				<form class="formCreateStagiaire" method="post" action="/ProjetEportfolio/creationstagiaire/">
				
					<div class="form-group formCreateStagiaire">
<<<<<<< HEAD
						<select name="civilite">
=======
						<select name="civilite" class="form-control" id="civilite">
>>>>>>> 1adf6a495cc5ba058073815ef548bcc5ab6c4910
							<option>Monsieur</option>
							<option>Madame</option>
							<option>Mademoiselle</option>
						</select>
					</div>
					
					<div class="form-group formCreateStagiaire">
						<label for="nom">Nom: </label>
<<<<<<< HEAD
						<input name="nom" type="text" class="form-control" id="nom" placeholder="Nom du stagiaire">
=======
						<input name="nom" type="text" class="form-control ${ map.get("classNom") }" id="nom" placeholder="Nom du stagiaire" value="${ map.get("nom") }" require />
>>>>>>> 1adf6a495cc5ba058073815ef548bcc5ab6c4910
					</div>
					 
					<div class="form-group">
						<label for="prenom">Prénom: </label>
<<<<<<< HEAD
						<input name="prenom" type="text" class="form-control" id="prenom" placeholder="Prénom du stagiaire">
=======
						<input name="prenom" type="text" class="form-control ${ map.get("classPrenom") }" id="prenom" placeholder="Prénom du stagiaire" value="${ map.get("prenom") }" require />
>>>>>>> 1adf6a495cc5ba058073815ef548bcc5ab6c4910
					</div>
					
					<div class="form-group">
						<label for="email">Email: </label>
<<<<<<< HEAD
						<input name="email" type="email" class="form-control" id="email" placeholder="Email du stagiaire">
=======
						<input name="email" type="text" class="form-control ${ map.get("classEmail") }" id="email" placeholder="Email du stagiaire"  value="${ map.get("email") }" require>
>>>>>>> 1adf6a495cc5ba058073815ef548bcc5ab6c4910
					</div>
					
					<div class="form-group">
						<label for="ddn">Date De Naissance: </label>
<<<<<<< HEAD
						<input name="ddn" type="text" class="form-control" id="ddn" placeholder="Date de naissance du stagiaire format 05/06/1979">
=======
						<input name="ddn" type="text" class="form-control ${ map.get("classDdn") }" id="ddn" placeholder="Date de naissance du stagiaire format 05/06/1979" value="${ map.get("ddn") }">
>>>>>>> 1adf6a495cc5ba058073815ef548bcc5ab6c4910
					</div>
					
					<button type="submit" class="btn btn-primary">Envoyer</button>					
				</form>
			</div>
		</div>
	</body>
</html>