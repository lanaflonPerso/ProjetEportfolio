<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 
 <!DOCTYPE html>

 		<%@ include file="/WEB-INF/layout/Head.jsp" %>

	<body>
		
		<%@ include file="/WEB-INF/layout/Menu.jsp" %>
		
		<div class="container">
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
					<%@ include file="/WEB-INF/form/Connection.jsp" %>
				</c:when>
				<c:when test="${ page  == 'test' }">
					<%@ include file="/TestVu.jsp" %>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</div>
		
		<%@ include file="/WEB-INF/layout/Footer.jsp" %>
	</body>
</html>