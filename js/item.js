function aggiungiCarrello(id){
	let xml = new XMLHttpRequest();
	let url = "../shop/addcarrello?id="+id;
	
	xml.open("get", url, true);
	xml.send();
	
	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			try{
				let obj = JSON.parse(xml.responseText);
				let notifica = creaNotifica(obj);
				animaNotifica(notifica);
				let x = document.getElementById("carrello");
				x.innerHTML = "(" + (parseInt(x.innerHTML.substring(1,x.innerHTML.length-1))+1) + ")";
			}catch(errore){
				console.log(errore);
			}
		}
	};
}
function animaNotifica(x){
	let a = document.getElementsByTagName("body")[0];
	a.appendChild(x);
	setTimeout(() => { a.lastChild.style.opacity = "1";}, 1);
	
	setTimeout(function() {
		setTimeout(() => { a.lastChild.style.opacity = "0";}, 1);
		setTimeout(() => { a.removeChild(a.lastChild);}, 600);
	}, 2500);
	
}
function creaNotifica(x){
	let figure = document.createElement("figure");
	figure.classList.add("notifica");
	figure.addEventListener("click", function(e) {
		figure.style.display = "none";
	}, false);
	
	let img = document.createElement("img");
	img.setAttribute("src", "../img/prodotti/"+x.immagine);
	img.setAttribute("alt", x.id + " " + x.nome);
	
	let caption = document.createElement("figcaption");
	
	let h3 = document.createElement("h3");
	h3.appendChild(document.createTextNode(x.nome));
	
	let p = document.createElement("p");
	p.appendChild(document.createTextNode(x.prezzo + "€"));
	
	let a = document.createElement("a");
	a.appendChild(document.createTextNode("Vai al carrello"));
	a.setAttribute("href", "../carrello");
	
	caption.appendChild(h3);
	caption.appendChild(p);
	caption.appendChild(a);

	figure.appendChild(img);
	figure.appendChild(caption);
	
	return figure;
}