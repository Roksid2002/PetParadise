<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<jsp:include page="header.jsp"/>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js" defer></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js" defer></script>
	
    <link rel="stylesheet" href="css/login.css">
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
      <form class = "log" action="login-servlet" method="post" id="loginForm">
            <label for="email" >Email</label>
            <input type="email" name="email" placeholder="Email" id="email" required>
            <label for="password" >Password</label>
            <input type="password" name="password" placeholder="Password" id="password" required>
            <input class="login-button" type="button" name="Accedi" value="Accedi" onclick="validateLogin()"></br>
            <span>Se non hai un account: <a href="Registrazione.jsp">Registrati</a></span>
        </form>
</body>

<jsp:include page="footer.jsp"/>
</html>
