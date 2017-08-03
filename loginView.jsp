<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="beanUtente" class="mvc.model.bean.User" scope="session" />
<jsp:useBean id="errore_reg" class="java.lang.String" scope="request" />
<jsp:useBean id="errore_log" class="java.lang.String" scope="request" />

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<title>PLACEHOLDER - Login</title>

	<%@ include file="_import.jsp" %>
</head>
<body class="site">
<jsp:include page="_header.jsp" />
<main class="site-content">
	<div id="blocco-login" class="fullwidth">
		<div class="side">
			<div class="contorno" id="reg">
				<h3 class="titolo">Registrazione</h3>
				<% if(errore_reg!=null){ %>
				<p class="sottotitolo"><%=errore_reg %></p>
				<%} %>
				<form action="registrazione" method="post" onsubmit="return validateRegistrazione(this)">
					<label>Username: <input type="text" name="user" placeholder="username"></label><br/>
					<label>Password: <input type="password" name="pswd" placeholder="password"></label><br/>
					<label>Email: <input type="email" name="mail" placeholder="mail@dominio.it"></label><br/>
					<label>Nome: <input type="text" name="nome" placeholder="Giuseppe"></label><br/>
					<label>Cognome: <input type="text" name="cogn" placeholder="Verdi"></label><br/>
					<label>Indirizzo: <input type="text" name="addr" placeholder="Via Roma, 10"></label><br/>
					<input type="submit" value="Registrati!"><br/>
				</form>
				<p onclick="showLog()">Vuoi forse entrare?</p>
			</div>
		</div>
		<div class="side">
			<div class="contorno" id="log">
				<h3 class="titolo">Login</h3>
				<% if(errore_log!=null){ %>
				<p class="sottotitolo"><%=errore_log %></p>
				<%} %>
				<form action="login" method="post" onsubmit="return validateLogin(this)">
					<label>Username: <input type="text" name="user"></label><br/>
					<label>Password: <input type="password" name="pswd"></label><br/>
					<label>Ricordami: <input type="checkbox" name="remember" value="true" /></label><br/>
					<input type="submit" value="Entra!">
				</form>
				<p onclick="showReg()">Vuoi forse registrarti?</p>
			</div>
		</div>
	</div>
</main>
<%@include file="_footer.jsp" %>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/form-check-switch.js"></script>
</body>
</html>