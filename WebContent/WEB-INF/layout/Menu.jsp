<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<a class="navbar-brand" href="/ProjetEportfolio/">Portfolio</a>
	
	<div class="collapse navbar-collapse" id="navbarColor01">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
				<a class="nav-link" href="/ProjetEportfolio/creationstagiaire">Création Stagiaire<span class="sr-only">(current)</span></a>
			</li>
			
			<li class="nav-item active">
				<a class="nav-link" href="http://10.115.57.234:8080/PortfolioAdmin/">autre <span class="sr-only">(current)</span></a>
			</li>
		</ul>
		
		<span>${ sessionScope.user.nom }</span>
		<hr />
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
				<c:choose>
					<c:when test="${ empty sessionScope.user }">
						<a class="nav-link" href="/ProjetEportfolio/connection">Connection</a>
					</c:when>
					<c:otherwise>
					<a class="nav-link" href="/ProjetEportfolio/deconnection">Déconnection</a>
					</c:otherwise>
				</c:choose>
			</li>
		</ul>
	</div>
</nav>