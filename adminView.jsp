<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="java.util.*, mvc.model.bean.User, mvc.model.bean.Prodotto, java.math.BigDecimal"%>

<jsp:useBean id="beanUtente" class="mvc.model.bean.User" scope="session" />
<% ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) request.getAttribute("prodotti"); %>
<% ArrayList<User> utenti = (ArrayList<User>) request.getAttribute("users"); %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PLACEHOLDER - Admin</title>
<%@ include file="_import.jsp" %>
</head>
<body class="site">
<jsp:include page="_header.jsp" />
<main class="site-content">

	<div id="blocco-pagina" class="fullwidth">
	
		<div class="tab">
			<h3>Mostra opzioni</h3>
				<button class="link-tab" onclick="apriTab(event, 'lista-utenti')" id="default">Lista Utenti</button>
				<button class="link-tab" onclick="apriTab(event, 'aggiungi-prodotto')">Aggiungi Prodotto</button>
				<button class="link-tab" onclick="apriTab(event, 'lista-prodotti')">Lista Prodotti</button>
				<button class="link-tab logout" onclick="logout()">Logout</button>
		</div>
		
		
		<div class="contenuto">
			<div id="lista-utenti" class="contenuto-tab">
			
				<h3>Lista degli utenti</h3>
				<ul>
					<% for(User x: utenti){ %>
					<li id="<%=x.getUser() %>"><span class="dati"><%=x.getUser() %> - <%=x.getNome() %> <%=x.getCognome() %></span>
						<span class="azioni">
							<% if(x.getRuolo().equals("admin")){%>	
								<img src="img/var/admin.png" height="20px" width="20px" onclick="rendiUtente(event, '<%= x.getUser()%>')">
							<%} else {%>
								<img src="img/var/utente.png" height="20px" width="20px" onclick="rendiAdmin(event, '<%= x.getUser()%>')">
							<%} %>
							<img src="img/var/ics.png" height="20px" width="20px" onclick="rimuoviUtente(event, '<%= x.getUser()%>')">
						</span>
					</li>
					<%} %>
				</ul>
			</div>
		
			<div id="aggiungi-prodotto" class="contenuto-tab">
		<p><%if (session.getAttribute("status")!= null) out.println((String) session.getAttribute("status")); %></p>
			<form method="post" action="admin/additem" enctype="multipart/form-data">
			Nome <input type="text" name="nome"><br/>
			Descrizione <input type="text" name="descrizione"><br/>
			Codice Univico <input type="text" name="id"><br/>
			Tipo di oggetto
				<select name="tipo">
					<option value="maglia">Maglia</option>
					<option value="pantalone">Pantalone</option>
					<option value="completo">Completo</option>
					<option value="scarpe">Scarpe</option>
					<option value="accessorio">Accessorio</option>
				</select> <br/>
			Genere <input type="radio" name="genere" value="m"/>M <input type="radio" name="genere" value="f"/> F <input type="radio" name="genere" value="u"/>Unisex <br/>
			Colore
			<input type="checkbox" name="colore" value="bianco"/> Bianco <br/>
			<input type="checkbox" name="colore" value="nero"/> Nero <br/>
			<input type="checkbox" name="colore" value="arancione"/> Arancione <br/>
			<input type="checkbox" name="colore" value="blu"/> Blu <br/>
			<input type="checkbox" name="colore" value="giallo"/> Giallo <br/>
			<input type="checkbox" name="colore" value="rosso"/> Rosso <br/>
			<input type="checkbox" name="colore" value="verde"/> Verde <br/>
			<input type="checkbox" name="colore" value="viola"/> Viola <br/>
			Data Inserimento <input type="date" name="data" placeholder="aaaa-mm-gg"/><br/>	
			Prezzo <input type="text" name="prezzo"><br/>
			Inserisci foto: <input type="file" name="img"><br>
			<input type="submit">
			</form> 
			</div>
		
			<div id="lista-prodotti" class="contenuto-tab">
				<h3>Lista dei prodotti</h3>
				<ul>
					<% for(Prodotto x: prodotti){ %>
					<li id="<%=x.getId()%>">
						<span class="img"><img src="img/prodotti/<%=x.getImmagine() %>" height="90px" width="90px" style="border-radius: 50px;"></span>
						<span class="dati"><%= x.getId() %> - <%=x.getNome() %></span>
						<span classe="azioni">
							<%= x.getPrezzo() %>€&nbsp;&nbsp;&nbsp;
							<img src="img/var/ics.png" height="20px" width="20px" onclick="rimuoviProdotto(event, '<%= x.getId() %>')">
						</span>
					</li>
					<%} %>
				</ul>
			</div>
		</div>
	</div>
<script src="js/admin.js"></script>
</body>
</html>