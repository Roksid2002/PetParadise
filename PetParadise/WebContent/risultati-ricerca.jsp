<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, model.*"%>

<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="css/categorie.css">
<body>
        <jsp:include page="header.jsp"/>
            <div class="risult"><h2>Risultati della Ricerca</h2></div>
            <div class="cat-body">
            <% 
            ArrayList<ProductBean> searchResults = (ArrayList<ProductBean>) request.getAttribute("searchResults");

            if (searchResults != null && !searchResults.isEmpty()) {
                for (ProductBean product : searchResults) {
            %>
  
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
            <%
                }
            } else {
            %>
            <p>Nessun risultato trovato.</p>
            <%
            }
            %>
        </div>
        <jsp:include page="footer.jsp"/>
    </div>
</body>
</html>
