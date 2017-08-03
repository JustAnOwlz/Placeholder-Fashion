let rendiAdmin = function(evt, utente) {
	let img = evt.currentTarget;
	let xml = new XMLHttpRequest();
	let url = "admin/changeadmin?nome=" + utente;

	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			if (xml.responseText == "ok") {
				img.setAttribute("src", "img/var/admin.png");
				img.setAttribute("onclick", "rendiUtente(event, '"+utente+"')");
			}else{
				img.setAttribute("src", "img/var/errore.png");
			}
		}
	};
	xml.open("get", url, true);
	xml.send();

	img.setAttribute("src", "img/var/loading.gif"); /* loading animation */
}

let rendiUtente = function(evt, utente) {
	let img = evt.currentTarget;
	let xml = new XMLHttpRequest();
	let url = "admin/changeadmin?nome=" + utente + "&e=x";

	xml.open("get", url, true);
	xml.send();

	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			if (xml.responseText == "ok") {
				img.setAttribute("src", "img/var/utente.png");
				img.setAttribute("onclick", "rendiAdmin(event, '"+utente+"')");
			}else{
				img.setAttribute("src", "img/var/errore.png");
			}
		}
	};

	img.setAttribute("src", "img/var/loading.gif"); /* loading animation */
}

let rimuoviUtente = function(evt, utente){
	let img = evt.currentTarget;
	let xml = new XMLHttpRequest();
	let url = "admin/removeutente?nome=" + utente;
	
	xml.open("get", url, true);
	xml.send();

	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			if (xml.responseText == "ok") {
				let x = document.getElementById(utente);
				x.parentNode.removeChild(x);
			}else{
				img.setAttribute("src", "img/var/errore.png");
			}
		}
	};

	img.setAttribute("src", "img/var/loading.gif"); /* loading animation */
}
let rimuoviProdotto = function(evt, id) {
	let img = evt.currentTarget;
	let xml = new XMLHttpRequest();
	let url = "admin/removeitem?id=" + id;
	
	xml.open("get", url, true);
	xml.send();

	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			if (xml.responseText == "ok") {
				let x = document.getElementById(id);
				x.parentNode.removeChild(x);
			}else{
				img.setAttribute("src", "img/var/errore.png");
			}
		}
	};

	img.setAttribute("src", "img/var/loading.gif"); /* loading animation */
}
let apriTab = function(evt, nomeTab) {
	let i, contenutoTabs, linkTabs;
	
	/*li fa scomparire tutti*/
	contenutoTabs = document.getElementsByClassName("contenuto-tab");
	for (i = 0; i < contenutoTabs.length; i++) contenutoTabs[i].style.display = "none";
	
	linkTabs = document.getElementsByClassName("link-tab");
	for (i = 0; i < linkTabs.length; i++) linkTabs[i].classList.remove("active");
	
	document.getElementById(nomeTab).style.display = "block";
	evt.currentTarget.classList.add("active");
}
let logout = function() {
	document.location.href = "logout";
}

window.onload = function(){
	document.getElementById("default").click();
	
	let div = document.getElementsByClassName("tab")[0];
	div.onclick = function() {
		if(document.documentElement.clientWidth < 850){
			this.classList.toggle("attivo");			
		}
	}
}