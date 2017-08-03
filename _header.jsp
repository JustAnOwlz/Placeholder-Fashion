<jsp:useBean id="beanUtente" class="mvc.model.bean.User" scope="session" />
<jsp:useBean id="carrello" class="mvc.model.classi.Cart" scope="session" />
<div id="sidebar">
	<a href="javascript:void(0)" class="closebtn" onclick="closeSidebar()">&times;</a>
	<ul>
		<% if(beanUtente.getRuolo().equalsIgnoreCase("visitatore")) {%>
		<li><a href="<%=request.getContextPath() %>/login" class="cta-login"><strong>Entra!</strong></a></li>
		<% } else {
			if(beanUtente.getRuolo().equals("admin")){%>
				<li><a href="<%=request.getContextPath() %>/utente" id="user"><jsp:getProperty property="user" name="beanUtente"/></a></li>
				<li><a href="<%=request.getContextPath() %>/admin" id="amministratore">Pannello Admin</a></li>
			<%}else{ %>
				<li><a href="<%=request.getContextPath() %>/utente" id="user"><jsp:getProperty property="user" name="beanUtente"/></a></li>
			<%} %>	
		<%} %>
		<li><a href="<%=request.getContextPath() %>/shop?g=m">Uomo</a></li>
		<li><a href="<%=request.getContextPath() %>/shop?g=f">Donna</a></li>
		<li><a href="<%=request.getContextPath() %>/shop?t=accessorio">Accessori</a></li>
		<li><a href="<%=request.getContextPath() %>/shop?o=data">Ultimi Arrivi</a></li>
	</ul>
</div>
<header>
	<div class="wrapper-90 flex-between">
		<p id="hamburger" onclick="openSidebar()">&#9776;</p>
		<a href="<%=request.getContextPath() %>" class="logo"><img src="<%= request.getContextPath() %>/img/var/logoNero.png" alt="logo aziendale"></a>
		<ul id="scelta">
		<li><a href="<%=request.getContextPath() %>/shop?g=m">Uomo</a></li>
		<li><a href="<%=request.getContextPath() %>/shop?g=f">Donna</a></li>
		<li><a href="<%=request.getContextPath() %>/shop?t=accessorio">Accessori</a></li>
		<li><a href="<%=request.getContextPath() %>/shop?o=data">Ultimi Arrivi</a></li>
		</ul>
		<div id="dati-personali">
			<ul>
				<% if(!beanUtente.getRuolo().equalsIgnoreCase("visitatore")) {
						if(beanUtente.getRuolo().equals("admin")){%>
				<li><a href="<%=request.getContextPath() %>/admin" id="amministratore">Panel</a></li>
				<li><a href="<%=request.getContextPath() %>/utente" id="user"><jsp:getProperty property="user" name="beanUtente"/></a></li>
						<%}else{ %>
				<li><a href="<%=request.getContextPath() %>/utente" id="user"><jsp:getProperty property="user" name="beanUtente"/></a></li>
						<%} %>		
				<% } else { %>
				<li><a href="<%=request.getContextPath() %>/login" class="cta-login"><strong>Entra!</strong></a></li>
				<%} %>
				<li><a href="<%=request.getContextPath()%>/carrello" id="carrello">(<%= carrello.getLista().size() %>)</a></li>
			</ul>
		</div>
	</div>
</header>
<script src="<%= request.getContextPath() %>/js/sidebar.js"></script>
