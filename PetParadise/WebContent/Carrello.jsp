<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, model.*"%>

<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="css/cart.css">
<body>
<div class="main">
	<jsp:include page="header.jsp"/>
<% double totale=0;%>
	<div class="small-container cart-page">


		<% CartBean carrello=(CartBean) session.getAttribute("cart"); if (carrello != null && carrello.getCartSize() != 0) {%>
		<div class="tabella">
		<table>
			<tr>
				<th>PRODOTTO</th>
				<th>QUANTITA</th>
				<th>TOTALE</th>
				<th>AZIONE</th>
				
			</tr>
			<% for (Map.Entry<Integer, ProductBean> set : carrello.getProducts().entrySet()) { 
			double tot= set.getValue().getPrezzo() * carrello.getQuantities().get(set.getKey());
			totale+= tot;%>
			<div>
				<td>

					<div class="cart-info">
						<img class="image" src="images/<%=set.getValue().getNomeImmagine()%>">
						<div>
							<p>
								<%=set.getValue().getNome()%>
							</p>
							<small>
									<%=set.getValue().getPrezzo()%>&euro;
							</small>
						</div>
					</div>
				</td>

				<td>
					<form action="cart?action=modify&returnto=cart.jsp">
						<input type="hidden" name="id" value="<%=set.getValue().getID()%>">

						<select name="quantity" id="quantity" onchange="this.form.submit()">

							<% for (int i=1; i <=10; i++) { if (carrello.getQuantity(set.getKey())==i) { %>

							<option value="<%=i%>" selected>
								<%=i%>
							</option>

							<% } else { %>
							<option value="<%=i%>">
								<%=i%>
							</option>

							<% } } %>

						</select>
					</form>
				</td>

				<td>	<%=set.getValue().getPrezzo() * carrello.getQuantities().get(set.getKey())%>
					&euro;
				</td>
			</div>
			<td> <a href="cart?action=remove&id=<%=set.getValue().getID()%>">Rimuovi</a></td>
		
		</div>

	</table>

		<% } %>
			<input type="button" onclick="location.href='${pageContext.request.contextPath}/show-catalog'" value="Catalogo">

		<form action="save-cart-to-orders?action=createorder&totale=<%=totale%>" method="post">
    <button class="btn btn-outline-warning px-3" type="submit">
        Compra
    </button>
    </form>
		
		 <p>Totale: <%= totale %>&euro;</p>
		<%  }else {%>
		<div>
			<p>Carrello Vuoto</p>
			<input type="button" onclick="location.href='${pageContext.request.contextPath}/show-catalog'" value="Catalogo">
		</div>
		<%}%>

		</table>
	</div>
	<jsp:include page="footer.jsp"/>

</body>

</html>