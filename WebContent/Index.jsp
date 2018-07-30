<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 
<!DOCTYPE html>
<html lang="fr">

 		<%@ include file="/WEB-INF/layout/Head.jsp" %>

	<body>
		<%@ include file="/WEB-INF/layout/Menu.jsp" %>
		
		<div class="container">
			<div class="row">
				<div class="col-md-4 rouge">
					<c:choose>
						<c:when test = "${ page == '/WEB-INF/form/ModifierStagiaire.jsp' }">
							<p>
								sur cette page il peut modifier sont nom, son prénom, sont email et sa date de naissance les vérification sont les mêmes que lors de l'enregistrement du stagiaire par l'admin<br />
								il peut aussi ajouter une adresse (de 10 caractéres min.) qui n'avait pas etait rentrer dans la base a la création du stagiaire<br />
								il peut aussi modfier son mot de passe qui est par défaut "azerty" du javascript lui indique si pass1 == pass2 et si le pass2 fait moin de 8 caractéres (le minimum pour être persisté en BD).
							</p>
						</c:when>
						<c:when test = "${ page == '/WEB-INF/form/AjouterCompetence.jsp' }">
							<p>
								sur cette page le stagiaire peut recherche des compétences. En tapant 4 caractéres une requête <strong>AJAX</strong> et éxècuté est une liste de compétence en base apparait<br />
								il peut cliquer sur la compétence ou ajouter grâce au formulaire une nouvelle compétence.
							</p>
						</c:when>
						<c:when test = "${ page == '/WEB-INF/form/Connection.jsp' }">
							<p>
								la page de connection:<br />
								non rentrons notre mail et notre mot de passe<br />
							</p>
						</c:when>
						<c:when test = "${ page == '/WEB-INF/form/AjouterStagiaire.jsp' }">
							<p>
								page de création de stagiaire:<br />
								sur cette page l'on pourra crée un stagiaire qui seras persité dans la DB<br />
								le nom doit comporté 6 caractéres min.<br />
								le prenom 6 caractéres min.<br />
								l'email doit etre un mail valide ^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$ et ne pas être présent en BD<br />
								la date de naissance au format jj/mm/YYYY ou jj-mm-YYYY<br />
								le stagiaire ne peut pas avoir plus de 100 ans ( c'est comme ça) et moin de 16 ans<br />
								la civilité ne seras pas utiliser par la suite.
							</p>
						</c:when>
						<c:when test = "${ page == '/WEB-INF/form/AjouterEntreprise.jsp' }">
							<p>
								page de création d'entreprise:<br />
								le nom doit l'entrepise doit être different de celui de la base avoir plus de 6 caractères<br />
								l'adresse 10 caractères.<br />
								la ville 2 caractères<br />
								le code postal être composé de 5 chiffres (^[0-9]{5}$)<br />
							</p>
						</c:when>
						<c:when test = "${ page == '/WEB-INF/vue/Stagiaire.jsp' }">
							<p>
								même JSP que lors de la création du stagiaire mais comme la "session.id=user.id" le stagiaire peut:<br />
								si une entreprise et un métier sont renseigné il peut ajouter des compétences.<br />
								il peut aussi ajouté une formation.
							</p>
						</c:when>
						<c:when test = "${ page == '/WEB-INF/form/AjouterMetier.jsp' }">
							<p>
								page ajout métier:<br />
								nous devons ajouter:<br />
								la fonction dans l'entreprise 5 caractéres min.<br />
								une description du poste  5 caractéres min.<br />
								la date de début et la date de fin elle peut prendre le même motif que la date de naissance soit 10/05/2012 ou 10-05-2012
								une verification est faite pour savoir si le salarié avait plus de 16 ans aux début du contrat et si les deux date sont bien rentré dans l'ordre.
							</p>
						</c:when>
						<c:when test = "${ page == '/WEB-INF/form/LierEntrepriseMetier.jsp' }">
							<p>
								page de recherche d'entreprise:<br />
								ici nous pouvons rechercher un entreprise a la lier a notre métier précédement enregistrer<br />
								la recherche se fait par nom ou par code postal<br />
								en cliquant sur le lien nous lions l'entreprise a notre métiers et retournons sur la portfolio.
								
							</p>
						</c:when>
						<c:when test = "${ page == '/WEB-INF/vue/VoirEntreprise.jsp' }">
							<p>
								la page de l'entreprise:<br />
								nous pouvons voir les information de l'entreprise<br />
								et aussi les personnes qui ont travailler dans cette boite.<br />
								si l'acces est fait par une personne identifier il peur ajouter un métier a l'entreprise.
							</p>
						</c:when>
						<c:when test = "${ page == '/WEB-INF/vue/Stagiaire.jsp' && stagiaire.id == 1 }">
							<p>
								après la validation nous somme redirigé sur la vue public du stagiaire nous pouvons imaginé qu'un token seras généré et envoyer par mail avec un lien pour crée le paswword!<br />
								actuellement le mot de passe est <stong>"azerty"</stong> par defaut crypter en SHA1!<br />
								nous pouvons nous déconnecté et nous relogger avec notre nouveau stagiaire!
							</p>
						</c:when>
						<c:otherwise>
							<p>
								page d'accueil connecté:<br />
								une fois connectée un objet user est crée et mis dans la session<br />
								<c:choose>
									<c:when test="${ sessionScope.user.admin }">
								ici connection en <strong>admin</strong> le menu couleur jaune lui est dedié avec la possibilité de crée un stagaire.<br />
								<br />
								une confirmation de connection seras demandé par le navigateur.		
									</c:when>
									<c:otherwise>
								ici nous somme connecté avec un user standard donc pas de menu jaune
									</c:otherwise>
								</c:choose>					
							</p>
						</c:otherwise>
					</c:choose>
				</div>
				
				<div class="col-md-3">
					<%@ include file="/WEB-INF/layout/MenuUser.jsp" %>	
				</div>

				<div class="col-md-5">
					<c:choose>
						<c:when test="${ not empty page }">
							<% pageContext.include((String) request.getAttribute("page")); %>
						</c:when>
						<c:otherwise>
							<%@ include file="/WEB-INF/vue/Home.jsp" %>	
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		
		<%-- <% pageContext.include((String) request.getAttribute("page")); %> --%> 
		
		<%@ include file="/WEB-INF/layout/Footer.jsp" %>

	</body>
</html>