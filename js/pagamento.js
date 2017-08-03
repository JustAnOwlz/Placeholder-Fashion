let pagamento = function (evt, user){
	if(formCheck()){
		let xml = new XMLHttpRequest();
		let url = "pagamento/completapagamento?u=" + user;
		
		xml.open("get", url, true);
		xml.send();
		
		document.getElementsByTagName("body")[0].appendChild(creaModal());
		setTimeout(() => {
			document.getElementById("modal-wait").style.opacity = "1";
			document.getElementById("modal-wait").childNodes[0].style.opacity = "1";
		}, 1);

		xml.onreadystatechange = function(){
			if(this.readyState == 4 && this.status == 200){
				if(this.responseText == "ok"){
					buonFine();
				}else{
					
				}
			}
		}
	}else{
		return false;
	}
}
let buonFine = function(){
	let modalInner = document.getElementById("modal-wait").childNodes[0];
	modalInner.childNodes[0].setAttribute("src", "img/var/check.png");
	modalInner.childNodes[1].innerHTML = "Grazie per aver acquistato da noi!"
	
	
	setTimeout(() => {
		modalInner.parentNode.style.opacity = "0";
		modalInner.style.opacity = "0";
		setTimeout(() => {
			document.location.href = "";			
		}, 1000);
	}, 1500);
	
	
}
let resetAndEmpty = function(form) {
	for(let i = 0; i<form.length; i++) form[i].classList.remove("error");
	
	for(let i = 0; i<form.length; i++){
		if(form[i].value.trim() === "" && form[i].type === "text"){
			form[i].classList.add("error");
			return false;
		}
	}
	return true;
}

let formCheck = function(){
	let coupon = document.getElementsByName("coupon")[0];
	let carta = document.getElementsByName("carta")[0];
	let data = document.getElementsByName("data")[0];
	let form = [carta, data];
	
	if(!resetAndEmpty(form)) return false;
	
	if(!(carta.value.trim().match(/^\d{4}-\d{4}-\d{4}-\d{4}$/))){
		carta.classList.add("error");
		return false;
	}
	if(!(data.value.trim().match(/^(0[1-9]|1[0-2])\/([0-9]{4}|[0-9]{2})$/))){
		data.classList.add("error");
		return false;
	}
	
	return true;
}

let creaModal = function(){
	let img = document.createElement("img");
	img.setAttribute("src", "img/var/loading.gif");
	
	let h3 = document.createElement("h3");
	h3.appendChild(document.createTextNode("Pagamento in corso . . ."));
	
	let div = document.createElement("div");
	div.classList.add("modal-area");
	
	let divGen = document.createElement("div");
	divGen.setAttribute("id", "modal-wait");

	div.appendChild(img);
	div.appendChild(h3);
	
	divGen.appendChild(div);
	
	return divGen;
}

let caps = function(obj){
	obj.value = obj.value.toUpperCase();
	
	/*
	 * Se non è vuoto, togli il messaggio di errore, poi
	 * - Se il match è valido, sconta l' acquisto
	 * - Se il match non è valido aggiungi il segno dell'errore
	 */
	if(!(obj.value.trim() == "")){
		if(obj.classList.contains("error")) obj.classList.remove("error");
		if(obj.value.trim().match(/^[A-Z]{2}\d{6}$/)){
			match("20", obj);
		}else obj.classList.add("error")
	}
}
let match = function(int, obj){
	let val = parseInt(document.querySelector("h4.totale").innerHTML);
	val = val - ((val / 100) * int);
	document.querySelector("h4.totale").innerHTML = val + "€";
	obj.disabled = true;
}