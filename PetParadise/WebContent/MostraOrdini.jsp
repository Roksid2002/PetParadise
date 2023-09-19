<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*,java.text.DecimalFormat"%>
<%


Collection<?> orders = (Collection<?>) request.getAttribute("orders");


DecimalFormat formatPrice = new DecimalFormat();
formatPrice.setMaximumFractionDigits(2);
formatPrice.setMinimumFractionDigits(2);


%>

<!DOCTYPE html>
<html lang="en">
<head>

 <link rel="stylesheet" href="css/ordini2.css">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
         
    <title>Gestione Ordini</title>
</head>
<body id="ordini-page">

    <jsp:include page="header.jsp"/>


	<div class="object-container">

	
		<table>
			<tr>
				<th>ID Ordine</th>
				<th>Prezzo Totale</th>
				
			</tr>
			
		<% 
	if (orders != null && orders.size() != 0) {
		Iterator<?> it = orders.iterator();
		while (it.hasNext()) {
			OrdineBean ordine = (OrdineBean) it.next();
	%>	
			<tr>
				
				<a href="HandleInvoices?id=<%=ordine.getID_utente()%>"><ion-icon name="document-attach-outline"></ion-icon></a>
				
				
			<td><%=ordine.getID_ordine()%></td>
		
			
			<td><%=formatPrice.format(ordine.getTotale()) %> &euro;</td>
			
			</tr>
			</table>
</div>
	<%
		}
	} else { %>
			
	<div>
	<p>Non ci sono ordini da visualizzare</p></br>
	<a href="/Homepage.jsp">Torna alla HomePage</a>
	</div>
	<% } %>
     <jsp:include page="footer.jsp"/>
</body>
</html>
