<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.sql.*"%>
	
<!DOCTYPE html>

<jsp:include page="header.jsp"/>

<html lang="en">

<head>
 	 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <link rel="stylesheet" href="css/homepagestyle.css">
    <link href="categorie.css" rel="stylesheet" type="text/css">
    
	<meta name="viewport" content="width=device-width, initial-scale=1">
<meta content="text/html; charset=iso-8859-2" http-equiv="Content-Type">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">



	<title>Homepage</title>

</head>

<body>
                    
<div class="w3-content w3-section" style="max-width:100%">
  <img class="mySlides" src="images/spot.png" style="width:100%">
  <img class="mySlides" src="images/spot2.png" style="width:100%">
  <img class="mySlides" src="images/spot1.jpg" style="width:100%">
</div>

<script>
var myIndex = 0;
carousel();

function carousel() {
  var i;
  var x = document.getElementsByClassName("mySlides");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";  
  }
  myIndex++;
  if (myIndex > x.length) {myIndex = 1}    
  x[myIndex-1].style.display = "block";  
  setTimeout(carousel, 3000); // Change image every 2 seconds
}


</script>
</head>

 
                     <div class="footer"><jsp:include page="footer.jsp"/></div>
                  
                   </body>
                   </html>