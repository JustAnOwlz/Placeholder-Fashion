<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="mvc.model.bean.Prodotto"%>

<jsp:useBean id="beanUtente" class="mvc.model.bean.User" scope="session" />
<jsp:useBean id="carrello" class="mvc.model.classi.Cart" scope="session" />
<jsp:useBean id="errore" class="java.lang.String" scope="request" />

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>PLACEHOLDER - Pagamento</title>
	<%@ include file="_import.jsp" %>
</head>
<body class="site">
<jsp:include page="_header.jsp" />
<main class="site-content">
	<div id="blocco-pagamento" class="fullwidth">
		<div class="riepilogo-dati">
			<h3> Riepilogo dati di spedizione </h3>
			<table>
				<tr><td><strong>Nome: </strong></td>
					<td><jsp:getProperty property="nome" name="beanUtente"/></td>
				</tr>
				<tr><td><strong>Cognome: </strong></td>
					<td><jsp:getProperty property="cognome" name="beanUtente"/></td>
				</tr>
				<tr><td><strong>Indirizzo: </strong></td>
					<td><jsp:getProperty property="indirizzo" name="beanUtente"/></td>
				</tr>
				<tr><td><strong>Codice carta prepagata: </strong></td>
					<td><input type="text" name="carta" placeholder="1234-5678-0102-0304" title="Formato: xxxx-xxxx-xxxx-xxxx"></td>
				</tr>
				<tr><td><strong>Data di scadenza: </strong></td>
					<td><input type="text" size="3" name="data" placeholder="10/18" title="Formato: mm-aa oppure mm-aaaa"></td>
				</tr>
			</table>
		</div>
		<div class="riepilogo-prodotti">
			<h3> Riepilogo carrello</h3>
			<table>
				<tr><td><strong>Totale prodotti: </strong></td>
					<td><jsp:getProperty property="totale" name="carrello"/>&euro;</td>
				</tr>
				<tr><td><strong>Numero prodotti: </strong></td>
					<td><%= carrello.getLista().size() %></td>
				</tr>
				<tr><td><strong>Costi di spedizione: </strong></td>
					<td>29.99€ <strong>worldwide</strong></td>
				</tr>
				<tr><td><strong>Codici coupon: </strong></td>
					<td><input type="text" name="coupon" placeholder="AB123456" title="Formato: AB123456" onkeyup="caps(this)"></td>
				</tr>
				<tr class="saldo"><td><strong>Totale: </strong></td>
					<td><h4 class="totale"><jsp:getProperty property="totale" name="carrello"/>€</h4></td>
				</tr>
			</table>
			<div onclick="pagamento(event, '<jsp:getProperty property="user" name="beanUtente"/>')" class="cta-checkout">Paga</div>
		</div>
	</div>
</main>
<%@ include file="_footer.jsp" %>
<script src="js/pagamento.js"></script>
</body>
</html>