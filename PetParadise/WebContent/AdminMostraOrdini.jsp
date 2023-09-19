<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<html>
<head>
    <title>Ordini</title>
</head>
<body>
 <link rel="stylesheet" href="css/ordini.css">
<jsp:include page="header.jsp"/>
<div class="order">
<table>
    <tr>
        <th>ID UTENTE</th>
        <th>ID ORDINE</th>
        <th>TOTALE</th>
        <th>AZIONE</th>
    
    </tr>
    <% @SuppressWarnings("unchecked")
    ArrayList<OrdineBean> orderList = (ArrayList<OrdineBean>) request.getAttribute("orders");
        if (orderList == null || orderList.isEmpty()) {%>
   
        <p>Non ci sono ordini da visualizzare</p>
   
    <% } else {
        for (OrdineBean ordine : orderList) {%>
    
     <tr>
        <td><%= ordine.getID_utente() %></td>
        <td><%= ordine.getID_ordine() %></td>
        <td><%= String.format("%.2f", ordine.getTotale()) %> &euro;</td>
        <td><form action="save-cart-to-orders?action=deleteorder&IDordine=<%=ordine.getID_ordine()%>" method="post">
    <button class="btn btn-outline-warning px-3" type="submit">
        Rimuovi
    </button>
    </form></td>
       </tr>
    <% } %>
    </table>
    <% } %>
    
   </div> 
    <div class="order">
    Utenti
<table>
    <tr>
        <th>ID </th>
        <th>EMAIL</th>
        <th>TELEFONO</th>
        <th>DATA DI NASCITA</th>
        <th>AZIONE</th>
    
    </tr>
    <% @SuppressWarnings("unchecked")
    ArrayList<UtenteBean> utenteList = (ArrayList<UtenteBean>) request.getAttribute("users");
        if (orderList == null || orderList.isEmpty()) {%>
   
        <p>Non ci sono utenti da visualizzare</p>
   
    <% } else {
        for (UtenteBean utente : utenteList) {%>
    
     <tr>
        <td><%= utente.getId() %></td>
        <td><%= utente.getEmail() %></td>
        <td><%= utente.getTelefono() %></td>
        <td><%= utente.getDdNascita() %></td>
        <td><form action="save-cart-to-orders?action=deleteutente&IDutente=<%=utente.getId()%>" method="post">
    <button class="btn btn-outline-warning px-3" type="submit">
        Rimuovi
    </button>
   </form> </td>
       </tr>
    <% } %>
    </table>
    <% } %>
    
    </div>
<%@ include file="/footer.jsp"%>

</body>
</html>