<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>PLACEHOLDER - Contattaci</title>
	<%@ include file="_import.jsp" %>
</head>
<body class="site">
	<jsp:include page="_header.jsp" />
	<main class="site-content">
		<div id="blocco-contatti">
			<form name="form-contatti">
				<ul class="form-contattaci">
				    <li><label>Nome</label><input type="text" name="nome" class="doppio-campo" placeholder="Nome" />&nbsp;<input type="text" name="cogn" class="doppio-campo" placeholder="Cognome" /></li>
				    <li>
				        <label>Email</label>
				        <input type="text" name="mail" class="campo-lungo" />
				    </li>
				    <li>
				        <label>Oggetto</label>
				        <select name="oggetto" class="campo-select">
				        <option value="vestiti">Vestiti</option>
				        <option value="info-lavorative">Info lavorative</option>
				        <option value="generale">Generale</option>
				        </select>
				    </li>
				    <li>
				        <label>Il tuo messaggio</label>
				        <textarea name="textarea" id="textarea" class="campo-lungo campo-textarea"></textarea>
				    </li>
				    <li>
				        <input type="button" value="Invia" title="Invia il messaggio!" onclick="validaMail()"/>
				    </li>
				</ul>
			</form>
		</div>
	</main>
	<%@ include file="_footer.jsp" %>
	<script src="js/contatti.js"></script>
</body>
</html>