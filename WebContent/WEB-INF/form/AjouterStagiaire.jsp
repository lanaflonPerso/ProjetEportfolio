<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<h1>Création d'un stagiaire</h1>

<form class="formCreateStagiaire" method="post" action="">
<div class="row">
		<div class="col-md-5 col-sm-12">
			<ul>
				<c:if test="${ not empty info.ok && !info.ok }" >
					<div class="alert alert-danger" role="alert">
						<c:if test="${not empty info.msgErrNom}"><li>${ info.msgErrNom }</li></c:if>
						<c:if test="${not empty info.msgErrPrenom}"><li>${ info.msgErrPrenom }</li></c:if>
						<c:if test="${not empty info.msgErrEmail}"><li>${ info.msgErrEmail }</li></c:if>
						<c:if test="${not empty info.msgErrDdn}"><li>${ info.msgErrDdn }</li></c:if>
					</div>
				</c:if>
			</ul>
		</div>

		<div class="col-md-7 col-sm-12">
			<fieldset class="form-group">
	    		<legend>Nouveau Stagiaire</legend>
	    		
				<div class="form-group formCreateStagiaire">
					<select name="civilite" class="form-control" id="civilite">
						<option>Monsieur</option>
						<option>Madame</option>
						<option>Mademoiselle</option>
					</select>
				</div>
				
				<div class="form-group formCreateStagiaire">
					<label for="nom">Nom: </label>
			
					<input name="nom" type="text" class="form-control ${ info.classeNom }" id="nom" placeholder="Nom du stagiaire"
						<c:if test="${ !info.ok }" >value="${ stagiaire.nom }"</c:if>  required/>
				</div>
				 
				<div class="form-group">
					<label for="prenom">Prénom: </label>
			
					<input name="prenom" type="text" class="form-control ${ info.classePrenom }" id="prenom" placeholder="Prénom du stagiaire" 
						<c:if test="${ !info.ok }" >value="${ stagiaire.prenom }"</c:if> required/>
				</div>
				
				<div class="form-group">
					<label for="email">Email: </label>
			
					<input name="email" type="email" class="form-control ${ info.classeEmail }" id="email" placeholder="Email du stagiaire"  
						<c:if test="${ !info.ok }" >value="${ stagiaire.email }"</c:if> required/>
				</div>
				
				<div class="form-group">
					<label for="ddn">Date De Naissance: </label>
			
					<input name="ddn" type="text" class="form-control ${ info.classeDdn }" id="ddn" placeholder="Date de naissance du stagiaire format 05/06/1979" 
						<c:if test="${ !info.ok }" >
							value="<tags:localDate date="${ stagiaire.dateNaissance }"/>"
						</c:if> pattern="[0-9]{2}/[0-9]{2}/[1-2][0-9]{3}"  required/>
				</div>
			
				<button type="submit" class="btn btn-primary">Envoyer</button>
			</fieldset>
		</div>
	</div>					
</form>