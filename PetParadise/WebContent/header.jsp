<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ page import="model.*" %>
	
<%UtenteBean myProfile = (UtenteBean) request.getSession().getAttribute("user");
    String name, url;

    if(myProfile == null){
        name = "Login";
        url = "location.href='Login.jsp'";
    }else{
        name= myProfile.getNome();
        url = "location.href='user-profile-servlet'";
    }
%>
<!DOCTYPE html>
<html lang = "en">

<script type="text/javascript" src="caption.js"></script>
<head>
<link rel="stylesheet" href="css/header.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<script src="https://kit.fontawesome.com/0a07c70885.js"
	crossorigin="anonymous"></script>
  <script>
	var request = new XMLHttpRequest();
	function searchInfo() {
		var name = document.searchForm.name.value;
		var url = "NavBar.jsp?val=" + name;

		try {
			request.onreadystatechange = function() {
				if (request.readyState == 4) {
					var val = request.responseText;
					document.getElementById('mylocation').innerHTML = val;
				}
			}//end of function  
			request.open("GET", url, true);
			request.send();
		} catch (e) {
			alert("Impossibile connettersi al server!");
		}
	}
	
	
	function visualizzaCatalogo() {
			 window.location.href = "ProductView.jsp";
			}
	
	$(document).ready(function() {
	    // Gestisce il click sul pulsante "Cerca"
	    $("#search-button").on("click", function() {
	        // Ottiene il testo di ricerca dalla casella di input
	        var searchQuery = $("#searchQuery").val();

	        // Esegue la chiamata AJAX alla servlet
	        $.ajax({
	            type: "POST",
	            url: "search-product-servlet", // Assicurati che l'URL sia corretto
	            data: { "searchQuery": searchQuery },
	            dataType: "json",
	            success: function(data) {
	                // Manipola i risultati ricevuti dalla servlet
	                displaySearchResults(data);
	            },
	            error: function() {
	                // Gestisce eventuali errori di comunicazione
	                alert("Si è verificato un errore nella ricerca.");
	            }
	        });
	    });

	    // Funzione per visualizzare i risultati della ricerca
	  $(document).ready(function() {
    console.log("Funzione di ricerca attivata!"); // Aggiungi questo console.log
   
    // Gestisce il click sul pulsante "Cerca"
    $("#search-button").on("click", function() {
        // Ottiene il testo di ricerca dalla casella di input
        var searchQuery = $("#searchQuery").val();

        // Effettua il reindirizzamento alla pagina dei risultati di ricerca
        window.location.href = "risultati-ricerca.jsp?searchQuery=" + encodeURIComponent(searchQuery);
    });
});

</script>


<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>


<body class"header-body">

<nav class="header-navbar">
<div class="header-nav">
    <a href="Homepage.jsp">
  <img src="images/imgprofilo.jpg" class="header-brand-logo" alt="Logo">
</a>
    <div class="header-nav-items">
        <div class="header-search">
    
    
   
 <form action="search-product-servlet" method="post">
    <label for="searchQuery"></label>
    <input type="text" id="searchQuery" name="searchQuery">
    <button type="submit">Cerca</button>
</form>


</div>
<button class="hButton" onclick="<%=url%> "><%=name%>
        <i class="fa solid fa-user"></i>
    </button>
       
            <button class="hButtonC" onclick="location.href='Carrello.jsp'">
        <i class="fa fa-shopping-cart"></i>
    </button>
    </div>
</div>
</nav>
<ul class="header-links-container">
    
    <li class="header-link-item">
    <i class="fa-sharp fa-solid fa-dog"></i><input type="button" onclick="location.href='${pageContext.request.contextPath}/show-specie?specie=Cani'" value="Cani"></li>
		
		
    <li class="header-link-item">
  <i class="fa-sharp fa-solid fa-cat"></i><input type="button" onclick="location.href='${pageContext.request.contextPath}/show-specie?specie=Gatti'" value="Gatti"></li>
		
		
    <li class="header-link-item">
    <i class="fa-solid fa-fish"></i><input type="button" onclick="location.href='${pageContext.request.contextPath}/show-specie?specie=Pesci'" value="Pesci"></li>
		
		
    <li class="header-link-item">
   <i class="fa-solid fa-crow"></i><input type="button" onclick="location.href='${pageContext.request.contextPath}/show-specie?specie=Uccelli'" value="Uccelli"></li>

<li class="header-link-item"><i class='fa-solid fa-bars'></i><input type="button" onclick="location.href='${pageContext.request.contextPath}/show-catalog'" value="Catalogo"></li>


</ul>

</body>


</html>
