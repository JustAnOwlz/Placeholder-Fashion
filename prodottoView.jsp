<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" 
	import="mvc.model.bean.Prodotto, java.util.*"%>

<jsp:useBean id="prod" class="mvc.model.bean.Prodotto" scope="request" />
<% ArrayList<Prodotto> listaProd = (ArrayList<Prodotto>) request.getAttribute("prodotti"); %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="utf-8"/>	
	<title>Prodotto <jsp:getProperty property="nome" name="prod"/></title>
	<%@ include file="_import.jsp" %>
</head>
<body class="site">
<jsp:include page="_header.jsp" />
	<main class="site-content">
		<div class="sbilenco alt"></div>
		<div id="breadcrumbs">
			<p><a href="<%= request.getContextPath() %>">Home</a>&nbsp;<i class="fa fa-chevron-right" aria-hidden="true"></i>&nbsp;<a href="<%= request.getContextPath() %>/shop">Shop</a>&nbsp;<i class="fa fa-chevron-right" aria-hidden="true"></i>&nbsp;<a href="<%= request.getContextPath() %>/shop/item?i=<jsp:getProperty property="id" name="prod"/>"><jsp:getProperty property="nome" name="prod"/></a></p>
		</div>
		<div class="wrapper-80">
			<div id="area-prodotto">
				<div id="left-side">
					<figure>
						<img src='../img/prodotti/<jsp:getProperty property="immagine" name="prod"/>' alt='<jsp:getProperty property="nome" name="prod"/>'>
					</figure>
				</div>
				<div id="right-side">
					<div>
						<h3><jsp:getProperty property="nome" name="prod"/></h3>
						<h4><jsp:getProperty property="id" name="prod"/></h4>
						<hr/>
						<h5><jsp:getProperty property="prezzo" name="prod"/>&nbsp;&euro;</h5>
					</div>
					<p><jsp:getProperty property="descrizione" name="prod"/></p>
					<div id="cta-compra" onclick="aggiungiCarrello('<jsp:getProperty property="id" name="prod"/>')">
						Compra!
					</div>
				</div>
			</div>
		</div>
		<hr class="divisore"/>
		<h2 class="sottotitolo-prodotti">Completa il tuo stile</h2>
		<div class="griglia wrapper margine-extra">
		<% for(Prodotto x: listaProd){ %>
			<div class="cella-4">
				<figure>
					<a href="item?i=<%=x.getId()%>">
						<img src="../img/prodotti/<%=x.getImmagine() %>" alt="<%= x.getNome() %> - <%= x.getId() %>">
					</a>
				</figure>
			</div>
		<% } %>
		</div>
	</main>	
<%@ include file="_footer.jsp" %>
<script src="../js/item.js"></script>
</body>
</html>