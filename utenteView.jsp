<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="java.util.*, mvc.model.bean.Prodotto, java.math.BigDecimal"%>

<jsp:useBean id="beanUtente" class="mvc.model.bean.User" scope="session" />
<% ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) request.getAttribute("prodotti"); %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PLACEHOLDER - Utente</title>
<%@ include file="_import.jsp" %>
</head>
<body class="site">
<jsp:include page="_header.jsp" />
<main class="site-content">

	<div id="blocco-pagina" class="fullwidth utente">
		<div class="tab">
			<h3>Mostra opzioni</h3>
			<button class="link-tab" onclick="apriTab(event, 'dati')" id="default">Dati Personali</button>
			<button class="link-tab" onclick="apriTab(event, 'cambio-dati')">Cambia Dati Personali</button>
			<button class="link-tab" onclick="apriTab(event, 'storico-acquisti')">Storico Acquisti</button>
			<button class="link-tab logout" onclick="logout()">Logout</button>
		</div>
		
		
		<div class="contenuto">
			<div id="dati" class="contenuto-tab">
				<h3>Dati utente</h3>
				<ul>
					<li>
						<span class="nome-val">Nome:</span>
						<span class="val"><jsp:getProperty property="nome" name="beanUtente"/></span>
					</li>
					<li>
						<span class="nome-val">Cognome:</span>
						<span class="val"><jsp:getProperty property="cognome" name="beanUtente"/></span>
					</li>
					<li>
						<span class="nome-val">Username:</span>
						<span class="val"><jsp:getProperty property="user" name="beanUtente"/></span>
					</li>
					<li>
						<span class="nome-val">Domicilio:</span>
						<span class="val"><jsp:getProperty property="indirizzo" name="beanUtente"/></span>
					</li>
					<li>
						<span class="nome-val">Email:</span>
						<span class="val"><jsp:getProperty property="mail" name="beanUtente"/></span>
					</li>
				</ul>
			</div>
		
			<div id="cambio-dati" class="contenuto-tab">
				<h3>Cambio dati</h3>
				<p>
					<%if(request.getSession().getAttribute("errore") != null)
						out.print(request.getSession().getAttribute("errore")); %>
				</p>
				<form method="post" action="utente/cambiamail">
					Nuovo indirizo mail <input type="text" name="mail"><br/>
					<input type="submit" value="Cambia Mail">
				</form>
				<hr>
				<form method="post" action="utente/cambiapassword">
					Vecchia passowrd<input type="text" name="oldpass"><br/>
					Nuova passowrd <input type="text" name="newpass1"><br/>
					Ripeti nuova password <input type="text" name="newpass2"><br/>
					<input type="submit" value="Cambia password">
				</form>
			</div>
		
			<div id="storico-acquisti" class="contenuto-tab">
				<h3>Storico degli acquisti</h3>
				<% if(prodotti.size() == 0){ %>
					<p> Non hai mai comprato prodotti! </p>
				<% }else{ %>
				<ul>
					<% for(Prodotto x: prodotti){ %>
					<li id="<%=x.getId()%>">
						<span class="img"><img src="img/prodotti/<%=x.getImmagine() %>" height="90px" width="90px" style="border-radius: 50px;"></span>
						<span class="dati"><%= x.getId() %> - <%=x.getNome() %></span>
						<span class="azioni">
							<%= x.getPrezzo() %>&nbsp;&nbsp; <%= x.getDataInserimento() %>
						</span>
					</li>
					<%} %>
				</ul>
				<%} %>
			</div>
		</div>
	</div>
<script src="js/user.js"></script>
</body>
</html>