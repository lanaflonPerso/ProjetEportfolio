<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 
 <!DOCTYPE html>

 		<%@ include file="/WEB-INF/layout/Head.jsp" %>

	<body>
		<%@ include file="/WEB-INF/layout/Menu.jsp" %>
		
		<div class="container">
			<div class="row">
				<div class="col-md-2 rouge">
					<c:if test="${!empty sessionScope.user }">
						<ul>
							<li><a href="/ProjetEportfolio/stagiaire/modifier">modifier</a></li>
							<li><a href="/ProjetEportfolio/metier/ajouter">Ajouter Métier</a></li>
						</ul>
					</c:if>
				</div> 

				<div class="col-md-9 jaune">
					<c:choose>
						<c:when test="${ page == 'index' }">
							<%@ include file="/WEB-INF/vue/Home.jsp" %>	
						</c:when>
						<c:when test="${ page == 'createStagiaire' }">
							<%@ include file="/WEB-INF/form/FormCreateStagiaire.jsp" %>	
						</c:when>
						<c:when test="${ page == 'stagiaire' }">
							<%@ include file="/WEB-INF/vue/Stagiaire.jsp" %>
						</c:when>
						<c:when test="${ page == 'stagiaires' }">
							<%@ include file="/WEB-INF/vue/Stagiaires.jsp" %>
						</c:when>
						<c:when test="${ page  == 'connection' }">
							<%@ include file="/WEB-INF/form/Connection.jsp" %>
						</c:when>
						<c:when test="${ page  == 'ajouterEntreprise' }">
							<%@ include file="/WEB-INF/form/AjouterEntreprise.jsp" %>
						</c:when>
						<c:when test="${ page  == 'modifierStagiaire' }">
							<%@ include file="/WEB-INF/form/ModifierStagiaire.jsp" %>
						</c:when>
						<c:when test="${ page  == 'voirEntreprise' }">
							<%@ include file="/WEB-INF/vue/VoirEntreprise.jsp" %>
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