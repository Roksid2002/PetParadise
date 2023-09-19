<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.ProductBean" %>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/details.css">
    <title>Dettagli</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class= "details-body"> 

     
    <% 
        // Ottiene l'attributo "product" dalla richiesta
        ProductBean prodotto = (ProductBean) request.getAttribute("prodotto");
        
    %>

 <div class="product-wrapper">
 
  <table class="details-table">
    <tr>
      <td>
        <div class="product-images">
          <img src="images/<%=prodotto.getNomeImmagine()%>" alt="Not available!">
        </div>
      </td>
      <td>
      <div class="details">
          <div class="titolo-pagina"> <h1><%=prodotto.getNome()%></h1> </div>
          <br><p class="product-short-des"><%=prodotto.getDescrizione()%></p>
          <br><span class="product-price"><%=prodotto.getPrezzo()%>&euro;</span>
          </div>
      </td>
    </tr>
    <tr>
      <td>
        <input type="button" onclick="location.href='${pageContext.request.contextPath}/show-catalog'" value="Catalogo">
      </td>
      <td>
     <form action="cart" method="get">
    <input type="hidden" name="action" value="add">
    <input type="hidden" name="id" value=<%= prodotto.getID() %>>
    <input type="hidden" name="quantity" value="1">
    <button type="submit">Aggiungi al Carrello</button>
</form>
          </td>
    </tr>
  </table>

 
</div>
</div>
    
    <div class="footer">
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>