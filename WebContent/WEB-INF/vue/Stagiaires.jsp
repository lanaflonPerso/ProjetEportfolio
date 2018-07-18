<table class="table table-dark">
	<thead class="thead-dark">
		<tr>
			<td>Nom</td>
			<td>Prenom</td>
			<td>email</td>
			<td>Adresse</td>
		</tr>
	</thead>

	<c:forEach items="${ stagiaires }" var="stagiaire">
	<tr>
		<td><c:out value="${ stagiaire.nom }" /></td>
		<td><c:out value="${ stagiaire.prenom }" /></td>
		<td><c:out value="${ stagiaire.email }" /></td>
		<td><c:out value="${ stagiaire.adresse }" /></td>
	</tr>
	</c:forEach>
	<c:if test="${not empty error}">Error: ${error}</c:if>

</table>