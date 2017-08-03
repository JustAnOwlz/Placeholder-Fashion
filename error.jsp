<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="beanUtente" class="mvc.model.bean.User" scope="session" />

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<title>PLACEHOLDER - Error</title>

<%@include file="_import.jsp" %>
</head>
<body class="site">
<jsp:include page="_header.jsp" />
<main class="site-content">
	<div id="blocco-errore" class="fullwidth">
		<div class="error-msg">
			<h2>Ops qualcosa è andato storto!</h2>
			<p><a href="<%=request.getContextPath() %>/shop">Clicca qui per ricominciare a fare acquisti!</a></p>
		</div>		
	</div>
</main>
<%@include file="_footer.jsp" %>
</body>
</html>