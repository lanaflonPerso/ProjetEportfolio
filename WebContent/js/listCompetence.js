xhr= getXMLHttpRequest();
var contextPath= "/ProjetEportfolio";

var input= document.getElementById("competence");
var result= document.getElementById("result");

input.addEventListener('input', function() {
    var url= contextPath+"/rest/competence?nom="+input.value;
    xhr.open("GET", url, true);
    xhr.send(null);
});

xhr.onreadystatechange = function() {
    if (xhr.readyState == 4 && (xhr.status == 200 || xhr.status == 0)) {
        listJson= xhr.responseText;
        competences= JSON.parse(listJson);

        result.innerHTML="";

    	var ul= document.createElement("ul");
        result.appendChild(ul);

        if (input.value.length > 3 ) {
        	var idMetier = document.location.href.substring(document.location.href.lastIndexOf( "/" )+1 );
        	
        	for (var i= 0; i<competences.length; i++) {
        		var ulr= contextPath+"/compte/competence/ajouter/"+idMetier+"?competence="+competences[i].Id
        		var lien= "<a href=\" "+ulr+" \">"+competences[i].Nom+"</a>"; 
        		var li = document.createElement('li');
                li.innerHTML= lien;
                ul.appendChild(li);                
            }
        }
        formulaire();
    }
}

function formulaire() {
	var links = document.querySelectorAll(".competence");
	for(var i= 0; i < links.length; i++) {
		console.log("ok");
		var link = links[i];
		link.addEventListener('click', function(e) {
			e.preventDefault();
			var id= link.getAttribute("id")
			console.log(id);
		})
	}
}

