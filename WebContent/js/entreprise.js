xhr= getXMLHttpRequest();

var input= document.getElementById("input");
var result= document.getElementById("listeEntreprise");

input.addEventListener('input', function() {
    var url= "http://localhost/r.php?task=getComment&nom="+input.value;
    // var url= "http://localhost:8080/ProjetEportfolio/RestServlet/entreprise?nom="+input.value;
    xhr.open("GET", url, true);
    xhr.send(null);
});

xhr.onreadystatechange = function() {
    if (xhr.readyState == 4 && (xhr.status == 200 || xhr.status == 0)) {
        entreprises= xhr.responseText;
        entreprise= JSON.parse(entreprises);

        result.innerHTML="";

    	var ul= document.createElement("ul");
        result.appendChild(ul);

        if (input.value.length > 3 ) {
        	for (var i= 0; i<entreprise.length; i++) {
        		var li = document.createElement('li');
                li.innerHTML= entreprise[i].Nom;
                ul.appendChild(li);
            }
        }
    }
}