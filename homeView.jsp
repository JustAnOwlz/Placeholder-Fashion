<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="beanUtente" class="mvc.model.bean.User" scope="session" />

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<title>PLACEHOLDER</title>
	
	<%@include file="_import.jsp" %>
</head>
<body class="site">
<jsp:include page="_header.jsp" />
<main class="site-content">
	<div id="blocco-main" class="fullwidth">
		<div class="side">
			<h2><span>Nuovi</span><br/><span>arrivi</span></h2>
			<a class="cta-new" href="shop?o=data">Scoprili tutti!</a>
		</div><div id="dummy" class="side"></div>
	</div>
	<div id="blocco-supp" class="fullwidth">
		<div id="dummy" class="side"></div>
		<div class="side">
			<h2><span>Sembra</span><br/><span>costoso</span></h2>
			<a class="cta-new" href="shop?o=prezzo">Compralo!</a>
		</div>
	</div>
	<div id="blocco-scelta" class="fullwidth">
		<div class="side" id="maglie">
			<a class="cta-gen" href="shop?t=maglia">Maglie</a>
		</div>
		<div class="side" id="pantaloni">
			<a class="cta-gen" href="shop?t=pantalone">Pantaloni</a>
		</div>
	</div>
</main>
<%@include file="_footer.jsp" %>
</body>
</html>