<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,model.*, java.text.DecimalFormat"%>
<%
	DecimalFormat formatPrice = new DecimalFormat();
	formatPrice.setMaximumFractionDigits(2);
	formatPrice.setMinimumFractionDigits(2);

	CartBean cart = (CartBean) session.getAttribute("cart");
	UtenteBean user = (UtenteBean) session.getAttribute("user");
	System.out.println(cart+ " bho " + user);

	if (cart == null || cart.isEmpty() || user == null) {
		response.sendRedirect("./index.jsp");
		return;
	}

	double totale = 0.f;
	for (Map.Entry<Integer, ProductBean> set : cart.getProducts().entrySet()) {

		totale += (double) set.getValue().getPrezzo() * cart.getQuantity(set.getValue().getID());

	}

	totale += totale * 0.22f;
	totale += totale * 0.05f;
	
	String totaleStr = formatPrice.format(totale);
%>

<!DOCTYPE html>
<html lang="en">
<script src="scripts/scriptPayment.js"></script>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="styles/payment-style.css">
<title>Pagamento</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div class="container mt-5 px-5">

		<div class="mb-4">

			<h2>Conferma l'ordine e Paga</h2>
			<span id="span">Fai il pagamento per godere di tutti i
				benefits</span>

		</div>

		<div class="row">
<form method="post" action="HandleOrders?action=createorder" name="HandleOrder" onsubmit="event.preventDefault(); validate(this)">
			<div class="col-md-8">

				<div class="card p-3">

					<h6 class="text-uppercase white">Dettagli del Pagamento</h6>
					<div class="inputbox mt-3">
						<input type="text" name="name" maxlength="40" class="form-control"
							required="required"> <span>Nome intestatario</span>
					</div>


					<div class="row">

						<div class="col-md-6">

							<div class="inputbox mt-3 mr-2">
								<input type="number" name="cardNumber"
									class="form-control" required="required"> <i
									class="fa fa-credit-card"></i> <span>Numero della carta</span>

							</div>

						</div>

						<div class="col-md-6">

							<div class="d-flex flex-row">


								<div class="inputbox mt-3 mr-2">
									<input type="text" maxlength="5" minlength="5" name="expiry"
										class="form-control" required="required"> <span>Data
										di scadenza</span>
								</div>

								<div class="inputbox mt-3 mr-2">
									<input type="number" maxlength="3" minlength="3" name="cvv"
										class="form-control" required="required"> <span>CVV</span>
								</div>

							</div>

						</div>
					</div>

				</div>
			</div>

			<div class="col-md-4">

				<div class="card card-blue p-3 text-white mb-3">
		<p id="errorMessage">Il nome deve contenere solo lettere</p>
					<span>Devi pagare</span>
					<div class="d-flex flex-row align-items-end mb-3">
						<h1 class="mb-0 yellow">
							€
							<%=totaleStr%></h1>
						
					</div>


					<span>Divertiti e goditi il tuo acquisto, è incluso il 5%
						extra per beneficenza!</span>

					<div class="mt-4 mb-4">

						<button class="btn btn-outline-warning  px-3"
							onclick="validate(this);">
							Pay €
							<%=totaleStr%></button>
					</div>

				</div>


			</div>

                 
</form>

		</div>


	</div>

	<jsp:include page="footer.jsp"/>
</body>
</html>
