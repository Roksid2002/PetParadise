<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.*" %>
<%@ page import="java.util.ArrayList" %>

<head>
     <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <link href="categorie.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="css/homepagestyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js" defer></script>
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
 <div class="cat-prodotto">
 <h3>Aggiungi Prodotto</h3>
         <form action="admin-servlet" method="post">
        <input type="hidden" name="action" value="addproduct">
            <div class="containerProduct">
                <div class="product-name">
                    <label for="name">Nome</label>
                    <input type="text" id="nome" name="nome" class="product" placeholder="Nome" maxlength="20" required>
                </div>

                <div class="product-description">
                    <label for="description">Descrizione</label>
                    <textarea id=""descrizione" name="descrizione" class="product" placeholder="Descrizione" rows="6" maxlength="255" required></textarea>
                </div>

                <div class="product-price">
                    <label for="price">Prezzo</label>
                    <input type="text" id="prezzo" class="product" name="prezzo" placeholder="Prezzo" pattern="^[1-9]\d*(\.\d+)?$" required>
                </div>


                <div class="product-image">
                    <label for="image" id="imageUpload">Immagine
                        <i class="fa-solid fa-file-circle-xmark"></i>
                        <input type="file" id="image" name="image" class="product" onchange="imageUpload(event)" accept="image/*" required></label>
                    <img src="" alt="" class="preview">
                </div>

                <div class="product-category">
                    <label for="category">Specie</label>
                    <input list="category_name" id="specie" name="specie" class="product" placeholder="Categoria" maxlength="20" required>
                </div>

                <div class="add-product">
                    <input type="button" onclick="validateAddProduct()" value="Inserisci">
                </div>
            </div>
        </form>
         </div>
         
         
    <%for(ProductBean product : prodotti){%>
   <div class="cat-prodotto">
   <a href="${pageContext.request.contextPath}/show-product?productId=<%= product.getID() %>" target="_blank">
        <img src="images/<%= product.getNomeImmagine() %>" class="cat-imgProdotto">
    </a>
        <p><%= product.getNome() %></p><br>
        <p><%= product.getPrezzo() %> &euro;</p>

  <form action="admin-servlet" method="post">
    <input type="hidden" name="action" value="deleteproduct">
    <input type="hidden" name="productId" value="<%= product.getID() %>">
    <button type="submit">Elimina</button>
</form>      



    </div>
    <%}%>

 
<%}%>

  
         </div>
<jsp:include page="footer.jsp"/>

</body>
</html>