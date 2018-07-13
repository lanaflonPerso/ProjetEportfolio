<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 

 		<%@ include file="/WEB-INF/layout/Doctype.jsp" %>

		<div class="container">
			<c:choose>
				<c:when test="${ page == 1 }">
					<%@ include file="/WEB-INF/vue/Home.jsp" %>	
				</c:when>
				<c:when test="${ page == 3 }">
					<%@ include file="/WEB-INF/form/FormCreateStagiaire.jsp" %>	
				</c:when>
				<c:when test="${ page == 2 }">
					<%@ include file="/WEB-INF/vue/ShowStagiaire.jsp" %>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</div>
	</body>
</html>