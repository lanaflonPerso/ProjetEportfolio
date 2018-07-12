DROP DATABASE IF EXISTS ProjetEportfolio;
CREATE DATABASE ProjetEportfolio;
USE ProjetEportfolio;

CREATE TABLE Utilisateurs (
	Id					int(6)			PRIMARY KEY AUTO_INCREMENT,
	Nom					VARCHAR(20)		NOT NULL,
	Prenom				VARCHAR(20)		NOT NULL,
	Email				VARCHAR(50)		NOT NULL,
	Adresse				VARCHAR(150)	NULL,
	DateNaissance		DATE 			NULL,
	motDePasse			VARCHAR(56)		NULL,
	IsStagiaire 		TINYINT(1)		NULL,
	IsAdministrateur	TINYINT(1)		NULL
) ENGINE = InnoDB;

INSERT INTO Utilisateurs(Nom, Prenom, Adresse, DateNaissance, IsStagiaire, IsAdministrateur, Email)
				VALUES ("Vianney", "Bailleux", "31 Allée du gros chêne 59320 haubourdin", "1979-06-07", 0, 1, "via@free.fr");
INSERT INTO Utilisateurs(Nom, Prenom, Adresse, DateNaissance, IsStagiaire, IsAdministrateur, Email)
				VALUES ("Bruce", "Dickinson", "26 rue jean jaurés 59320 haubourdin", "1984-01-20", 1, 0, "bruce@free.fr");
INSERT INTO Utilisateurs(Nom, Prenom, Adresse, DateNaissance, IsStagiaire, IsAdministrateur, Email)
				VALUES ("Rob", "Halford", "72, rue du Château 97480 SAINT-JOSEPH", "1944-07-23", 1, 0, "rob@free.fr");
INSERT INTO Utilisateurs(Nom, Prenom, Adresse, DateNaissance, IsStagiaire, IsAdministrateur, Email)
				VALUES ("Josette", "Allain", "26, rue des Chaligny 58000 NEVERS", "1967-06-16", 1, 0, "josette@free.fr");
INSERT INTO Utilisateurs(Nom, Prenom, Adresse, DateNaissance, IsStagiaire, IsAdministrateur, Email)
				VALUES ("Emmanuel", "Lagrange", "88, Rue Joseph Vernet 92220 BAGNEUX", "1984-02-12", 1, 0, "manu@free.fr");

CREATE TABLE Entreprises (
	Id					int(6)			PRIMARY KEY AUTO_INCREMENT,
	Adresse				VARCHAR(150)	NOT NULL,
	Ville				VARCHAR(25)		NOT NULL,
	Nom					VARCHAR(50)		NOT NULL,
	DateEntree			Date			NOT NULL,
	DateSortie			Date			NOT NULL,
	IdUtilisateur		int(6)			REFERENCES Utilisateur(Id)
) ENGINE = InnoDB;

INSERT INTO Entreprises(Nom, Adresse, Ville, DateEntree, DateSortie, IdUtilisateur)
				VALUES ("Quinton Hazell", "94, rue de Geneve", "80000 AMIENS", "2001-01-01", "2005-06-01", 1);
INSERT INTO Entreprises(Nom, Adresse, Ville, DateEntree, DateSortie, IdUtilisateur)
				VALUES ("Lab Photo pro", "15, place Stanislas", "92000 NANTERRE", "2005-08-01", "2010-03-08", 1);
INSERT INTO Entreprises(Nom, Adresse, Ville, DateEntree, DateSortie, IdUtilisateur)
				VALUES ("Food Giant", "42, rue de Geneve", "80000 AMIENS", "2001-01-01", "2009-06-01", 2);
INSERT INTO Entreprises(Nom, Adresse, Ville, DateEntree, DateSortie, IdUtilisateur)
				VALUES ("Pantry Pride", "15, place Stanislas", "92000 NANTERRE", "2010-05-02", "2014-07-09", 2);
INSERT INTO Entreprises(Nom, Adresse, Ville, DateEntree, DateSortie, IdUtilisateur)
				VALUES ("Record Town", "61, rue des six frères Ruellan", "57200 SARREGUEMINES", "2001-01-01", "2005-06-01", 3);
INSERT INTO Entreprises(Nom, Adresse, Ville, DateEntree, DateSortie, IdUtilisateur)
				VALUES ("Magna Gases", "95, Rue Hubert de Lisle", "39000 LONS-LE-SAUNIER", "2005-09-01", "2008-08-25", 3);
INSERT INTO Entreprises(Nom, Adresse, Ville, DateEntree, DateSortie, IdUtilisateur)
				VALUES ("Mr Fables", "49, avenue du Marechal Juin", "50000 SAINT-LÔ", "2009-06-15", "2012-03-06", 3);
INSERT INTO Entreprises(Nom, Adresse, Ville, DateEntree, DateSortie, IdUtilisateur)
				VALUES ("Dynatronics Accessories", "19, Avenue Millies Lacroix", "78990 ÉLANCOURT", "2013-01-08", "2016-04-20", 3);
INSERT INTO Entreprises(Nom, Adresse, Ville, DateEntree, DateSortie, IdUtilisateur)
				VALUES ("Titania", "93, rue du Faubourg National", "94320 THIAIS", "2001-01-01", "2005-06-01", 4);
INSERT INTO Entreprises(Nom, Adresse, Ville, DateEntree, DateSortie, IdUtilisateur)
				VALUES ("Liberal", "99, Chemin Des Bateliers", "49000 ANGERS", "2012-12-10", "2016-08-08", 4);

CREATE TABLE Certifications (
	Id				int(6)	PRIMARY KEY AUTO_INCREMENT,
	IdCompetence	int(6)	REFERENCES Compretences(Id),
	IdFormation		int(6)	REFERENCES Formations(Id),
	IdMetier		int(6)	REFERENCES Metiers(Id)
) ENGINE = InnoDB;



CREATE TABLE Formations (
	Id					int(6)	PRIMARY KEY AUTO_INCREMENT,
	IntituleFormation	VARCHAR(50)	NOT NULL,
	IdCompetence	int(6)	REFERENCES Compretences(Id)
) ENGINE = InnoDB;