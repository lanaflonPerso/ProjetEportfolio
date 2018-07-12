<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<a class="navbar-brand" href="/ProjetEportfolio/">Portfolio</a>
	
	<div class="collapse navbar-collapse" id="navbarColor01">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
				<a class="nav-link" href="/ProjetEportfolio/creationstagiaire/">Création Stagiaire<span class="sr-only">(current)</span></a>
			</li>
			
			<li class="nav-item active">
				<a class="nav-link" href="http://10.115.57.234:8080/PortfolioAdmin/">autre <span class="sr-only">(current)</span></a>
			</li>
		</ul>
		
		<form class="form-inline" method="get" action="/ProjetEportfolio/Stagiaire">
			<input class="form-control mr-sm-2" type="search" placeholder="Par Email" 
			name="email" aria-label="Search" />
			<button class="btn btn-outline-info my-2 my-sm-0" type="submit">Chercher</button>
		</form>
	</div>
</nav>