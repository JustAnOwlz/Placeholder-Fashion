<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="mvc.model.bean.Prodotto, java.util.*, java.math.BigDecimal"%>
<% ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) request.getAttribute("prodotti"); %>
<jsp:useBean id="prec" class="java.lang.String" scope="request" />
<jsp:useBean id="succ" class="java.lang.String" scope="request" />

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>PLACEHOLDER - Shop</title>
	<%@ include file="_import.jsp" %>
</head>
<body class="site">
	<jsp:include page="_header.jsp" />
	<main class="site-content">
<% if(prodotti.size() == 0){ %>
	<div id="blocco-errore" class="fullwidth">
		<div class="error-msg">
			<h2>Non abbiamo prodotti qui</h2>
			<a href="<%=request.getContextPath() %>/shop">Sei stato troppo specifico nella tua richiesta. Magari alla prossima collezione avremo quello che cerhi</a>
		</div>		
	</div>
<% }else{ %>
	<div class="sbilenco"></div>
	<div id="selettore">
		<form method="get" action="shop" id="nl-form" class="nl-form">
			Sono 
			<select name="g">
				<option value="x" selected>un Umano</option>
				<option value="m">un Uomo</option>
				<option value="f">una Donna</option>
			</select>
			alla ricerca di
			<select name="t">
				<option value="x" selected>un Oggetto</option>
				<option value="maglia">una Maglia</option>
				<option value="pantalone">un Pantalone</option>
				<option value="completo">un Completo</option>
				<option value="scarpe">un paio di Scarpe</option>
				<option value="accessorio">un Accessorio</option>
			</select>
			di colore
			<select name="c">
				<option value="x" selected>qualsiasi.</option>
				<option value="bianco">bianco.</option>
				<option value="nero">nero.</option>
				<option value="arancione">arancione.</option>
				<option value="blu">blu.</option>
				<option value="giallo">giallo.</option>
				<option value="rosso">rosso.</option>
				<option value="verde">verde.</option>
				<option value="viola">viola.</option>
			</select>
			<br/>In ordine di
			<select name="o">
				<option value="a" selected>prezzo ascendente</option>
				<option value="d">prezzo discendente</option>
				<option value="u">ultimi arrivi</option>
			</select>
			<br/><input type="submit" value="cerca">
		</form>
	</div>
	<div class="wrapper">
		<div class="griglia">
		<% for(Prodotto x: prodotti){ %>
			<div class="cella">
				<figure>
					<a href="shop/item?i=<%=x.getId()%>">
						<img src="img/prodotti/<%=x.getImmagine() %>" alt="<%= x.getNome() %> - <%= x.getId() %>">
					</a>
					<figcaption onclick="aggiungiCarrello('<%= x.getId() %>')">
						Aggiungi al carrello
					</figcaption>
				</figure>
				<a href="shop/item?i=<%=x.getId()%>">
				<h3><%= x.getNome() %></h3>
				<p><%= x.getPrezzo() %>&euro;</p>
				</a>
			</div>
		<% } %>
		</div>
		<p class="page-changer"><% if(!prec.equals("")){ %>
		<a href="<%=request.getContextPath() %>/shop<%=prec %>">&lt;&lt;&nbsp;Pagina Precedente</a>
		<% } %>
		<% if(!(prec.equals("") || prec.equals(""))) {%>
		&nbsp;-&nbsp;
		<%} %>
		<% if(!succ.equals("")){ %>
		<a href="<%=request.getContextPath() %>/shop<%=succ %>">Pagina Successiva&nbsp;&gt;&gt;</a>
		<% } %>
	</div>
	<% } %>
	</main>
	<%@ include file="_footer.jsp" %>
<script src="js/shop.js"></script>
</body>
</html>