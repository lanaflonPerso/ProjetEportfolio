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
								sur cette page il peut modifier sont nom, son pr�nom, sont email et sa date de naissance les v�rification sont les m�mes que lors de l'enregistrement du stagiaire par l'admin<br />
								il peut aussi ajouter une adresse (de 10 caract�res min.) qui n'avait pas etait rentrer dans la base a la cr�ation du stagiaire<br />
								il peut aussi modfier son mot de passe qui est par d�faut "azerty" du javascript lui indique si pass1 == pass2 et si le pass2 fait moin de 8 caract�res (le minimum pour �tre persist� en BD).
							</p>
						</c:when>
						<c:when test = "${ page == '/WEB-INF/form/AjouterCompetence.jsp' }">
							<p>
								sur cette page le stagiaire peut recherche des comp�tences. En tapant 4 caract�res une requ�te <strong>AJAX</strong> et �x�cut� est une liste de comp�tence en base apparait<br />
								il peut cliquer sur la comp�tence ou ajouter gr�ce au formulaire une nouvelle comp�tence.
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
								page de cr�ation de stagiaire:<br />
								sur cette page l'on pourra cr�e un stagiaire qui seras persit� dans la DB<br />
								le nom doit comport� 6 caract�res min.<br />
								le prenom 6 caract�res min.<br />
								l'email doit etre un mail valide ^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$ et ne pas �tre pr�sent en BD<br />
								la date de naissance au format jj/mm/YYYY ou jj-mm-YYYY<br />
								le stagiaire ne peut pas avoir plus de 100 ans ( c'est comme �a) et moin de 16 ans<br />
								la civilit� ne seras pas utiliser par la suite.
							</p>
						</c:when>
						<c:when test = "${ page == '/WEB-INF/form/AjouterEntreprise.jsp' }">
							<p>
								page de cr�ation d'entreprise:<br />
								le nom doit l'entrepise doit �tre different de celui de la base avoir plus de 6 caract�res<br />
								l'adresse 10 caract�res.<br />
								la ville 2 caract�res<br />
								le code postal �tre compos� de 5 chiffres (^[0-9]{5}$)<br />
							</p>
						</c:when>
						<c:when test = "${ page == '/WEB-INF/vue/Stagiaire.jsp' }">
							<p>
								m�me JSP que lors de la cr�ation du stagiaire mais comme la "session.id=user.id" le stagiaire peut:<br />
								si une entreprise et un m�tier sont renseign� il peut ajouter des comp�tences.<br />
								il peut aussi ajout� une formation.
							</p>
						</c:when>
						<c:when test = "${ page == '/WEB-INF/form/AjouterMetier.jsp' }">
							<p>
								page ajout m�tier:<br />
								nous devons ajouter:<br />
								la fonction dans l'entreprise 5 caract�res min.<br />
								une description du poste  5 caract�res min.<br />
								la date de d�but et la date de fin elle peut prendre le m�me motif que la date de naissance soit 10/05/2012 ou 10-05-2012
								une verification est faite pour savoir si le salari� avait plus de 16 ans aux d�but du contrat et si les deux date sont bien rentr� dans l'ordre.
							</p>
						</c:when>
						<c:when test = "${ page == '/WEB-INF/form/LierEntrepriseMetier.jsp' }">
							<p>
								page de recherche d'entreprise:<br />
								ici nous pouvons rechercher un entreprise a la lier a notre m�tier pr�c�dement enregistrer<br />
								la recherche se fait par nom ou par code postal<br />
								en cliquant sur le lien nous lions l'entreprise a notre m�tiers et retournons sur la portfolio.
								
							</p>
						</c:when>
						<c:when test = "${ page == '/WEB-INF/vue/VoirEntreprise.jsp' }">
							<p>
								la page de l'entreprise:<br />
								nous pouvons voir les information de l'entreprise<br />
								et aussi les personnes qui ont travailler dans cette boite.<br />
								si l'acces est fait par une personne identifier il peur ajouter un m�tier a l'entreprise.
							</p>
						</c:when>
						<c:when test = "${ page == '/WEB-INF/vue/Stagiaire.jsp' && stagiaire.id == 1 }">
							<p>
								apr�s la validation nous somme redirig� sur la vue public du stagiaire nous pouvons imagin� qu'un token seras g�n�r� et envoyer par mail avec un lien pour cr�e le paswword!<br />
								actuellement le mot de passe est <stong>"azerty"</stong> par defaut crypter en SHA1!<br />
								nous pouvons nous d�connect� et nous relogger avec notre nouveau stagiaire!
							</p>
						</c:when>
						<c:otherwise>
							<p>
								page d'accueil connect�:<br />
								une fois connect�e un objet user est cr�e et mis dans la session<br />
								<c:choose>
									<c:when test="${ sessionScope.user.admin }">
								ici connection en <strong>admin</strong> le menu couleur jaune lui est dedi� avec la possibilit� de cr�e un stagaire.<br />
								<br />
								une confirmation de connection seras demand� par le navigateur.		
									</c:when>
									<c:otherwise>
								ici nous somme connect� avec un user standard donc pas de menu jaune
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