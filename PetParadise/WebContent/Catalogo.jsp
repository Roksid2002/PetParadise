<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.*" %>
<%@ page import="java.util.ArrayList" %>

<head>
     <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <link href="categorie.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="css/homepagestyle.css">
    <title>Catalogo</title>
</head>

<body >
<%


ArrayList<ProductBean> prodotti = (ArrayList<ProductBean>) request.getAttribute("prodotti");%>


<jsp:include page="header.jsp"/>

<%if( prodotti == null || prodotti.size() == 0){%>

<div>Catalogo vuoto</div>

<%} else{%>


 
   
<div class="cat-body">
    <%for(ProductBean product : prodotti){%>
   <div class="cat-prodotto">
   <a href="${pageContext.request.contextPath}/show-product?productId=<%= product.getID() %>" target="_blank">
        <img src="images/<%= product.getNomeImmagine() %>" class="cat-imgProdotto">
    </a>
        <p><%= product.getNome() %></p><br>
        <p><%= product.getPrezzo() %> &euro;</p>

        
<form action="cart" method="get">
    <input type="hidden" name="action" value="add">
    <input type="hidden" name="id" value=<%= product.getID() %>>
    <input type="hidden" name="quantity" value="1">
    <button type="submit">Aggiungi al Carrello</button>
</form>



    </div>
    <%}%>

 
<%}%>
 </div>
<jsp:include page="footer.jsp"/>

</body>
</html>