<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.ProductBean" %>
<%@ page import="java.util.ArrayList" %>

  <% 
        // Ottiene l'attributo "product" dalla richiesta
        ProductBean prodotto = (ProductBean) request.getAttribute("prodotto");
        
    %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifica Prodotto</title>
</head>
<body>
    <h1>Modifica Prodotto</h1>

    <form action="admin-servlet" method="post">
        <input type="hidden" name="action" value="modifica">
        <input type="hidden" name="productId" value="<%= prodotto.getID() %>">
        
        <label for="specie">Specie:</label>
        <input type="text" id="specie" name="specie" value="<%= prodotto.getSpecie() %>"><br>

        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" value="<%=prodotto.getNome()%>"><br>

        <label for="descrizione">Descrizione:</label>
        <textarea id="descrizione" name="descrizione"><%= prodotto.getDescrizione() %></textarea><br>

        <label for="prezzo">Prezzo:</label>
        <input type="text" id="prezzo" name="prezzo" value="<%= prodotto.getPrezzo() %>"><br>

        <label for="quantita">Quantità:</label>
        <input type="text" id="quantita" name="quantita" value="<%= prodotto.getQuantita() %>"><br>

        <input type="submit" value="Salva Modifiche">
    </form>

    <a href="AdminCatalog">Torna all'Elenco Prodotti</a>
</body>
</html>
