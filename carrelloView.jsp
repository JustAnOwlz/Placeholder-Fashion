<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="mvc.model.bean.Prodotto"%>
<jsp:useBean id="beanUtente" class="mvc.model.bean.User" scope="session" />
<jsp:useBean id="carrello" class="mvc.model.classi.Cart" scope="session" />
<jsp:useBean id="errore" class="java.lang.String" scope="request" />

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Placeholder - Carrello</title>
	<%@include file="_import.jsp" %>
</head>
<body class="site">
<jsp:include page="_header.jsp" />
<main class="site-content">
	<% if(errore.equals("")){ %>
	
	<div id="blocco-carrello" class="fullwidth">

		<div id="main">
			<ul>
			<% for(Prodotto x: carrello.getLista()){ %>
				<li id="<%=x.getId()%>">
					<span class="img"><img src="img/prodotti/<%=x.getImmagine() %>" height="100px" width="100px" style="border-radius: 100px;"></span>
					<span class="dati"><%= x.getId() %> - <%=x.getNome() %></span>
					<span class="azioni">
						<%= x.getPrezzo() %>€&nbsp;&nbsp;&nbsp;
						<img src="img/var/ics.png" height="20px" width="20px" onclick="rimuoviCarrello(event, '<%= x.getId() %>')">
					</span>
				</li>
			<%} %>		
			</ul>
		</div>
		<div id="side">
			<div id="titolo">
				<h3>Carrello</h3>			
			</div>
			<div id="costo">
				<h3>Costo totale:&nbsp;&nbsp;<span id="costo-numero"><%= carrello.getTotale() %></span>&nbsp;&euro;</h3>
			</div>
		<% if(beanUtente.getRuolo().equals("visitatore")) { %>
			<div id="indirizzo">
				<h3><a href="<%=request.getContextPath() %>/login">Per proseguire c'è bisogno di effettuare il login!</a></h3>
			</div>
		<% } else { %>
			<div id="indirizzo">
					<h3>Indirizzo spedizione: <jsp:getProperty property="indirizzo" name="beanUtente"/> </h3>
			</div>
			<div id="scelte">
				<div id="reset" onclick="svuotaCarrello('<jsp:getProperty property="user" name="beanUtente"/>')">Elimina tutto il carrello</div>
				<div id="checkout" onclick="checkout()">Prosegui al checkout</div>
			</div>
		<% } %>
		</div>
	</div>	
	<% }else{ %>
		<div id="blocco-errore" class="fullwidth">
			<div class="error-msg">
				<h2><%=errore%></h2>
				<a href="<%=request.getContextPath() %>/shop">Clicca qui per ricominciare a fare acquisti!</a>
			</div>		
		</div>
	<% } %>
	
	
</main>
<%@include file="_footer.jsp" %>
<script src="js/carrello.js"></script>
</body>
</html>