function rimuoviCarrello(evt, id){
	let li = evt.currentTarget.parentNode.parentNode;
	
	let xml = new XMLHttpRequest();
	let url = "shop/removecarrello?id="+id;
	
	xml.open("get", url, true);
	xml.send();
	
	
	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			
			if(xml.responseText !== "errore"){
				let ul = li.parentNode;
				ul.removeChild(li);
				
				if(ul.childElementCount!== 0){
					let x = document.getElementById("carrello");
					x.innerHTML = "(" + (parseInt(x.innerHTML.substring(1,x.innerHTML.length-1))-1) + ")";
					
					let y = parseFloat(document.getElementById("costo-numero").innerHTML);
					let costo = parseFloat(xml.responseText); 
					y = y - costo;
					document.getElementById("costo-numero").innerHTML = y.toFixed(2);					
				}else location.reload();
			}
		}
	};
	
	evt.currentTarget.setAttribute("src", "img/var/loading.gif");
}

function svuotaCarrello(idUtente){
	let xml = new XMLHttpRequest();
	let url = "shop/svuotacarrello?id="+idUtente;
	
	xml.open("get", url, true);
	xml.send();
	
	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			location.reload();
		}
	};
	
	let reset = document.getElementById("reset");
	let img = document.createElement("img");
	img.setAttribute("src", "img/var/loading.gif");

	reset.innerHTML = "";
	reset.appendChild(img);
}

function checkout(){
	document.location.href = "pagamento";
}