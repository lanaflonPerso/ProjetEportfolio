<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 
<!DOCTYPE html>
<html lang="fr">

 		<%@ include file="/WEB-INF/layout/Head.jsp" %>

	<body>
		<%@ include file="/WEB-INF/layout/Menu.jsp" %>
		
		<div class="container">
			<h1>Erreur 404</h1>
			<h1>${requestScope['javax.servlet.error.message']}</h1>
		</div>
		
		<%@ include file="/WEB-INF/layout/Footer.jsp" %>

	</body>
</html>