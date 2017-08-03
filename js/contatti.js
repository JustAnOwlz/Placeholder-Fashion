let resetInput = function(form) {
	for(let i = 0; i<form.length; i++){
		form[i].classList.remove("error-contattaci");
		form[i].setAttribute("title", "");
	}
}
let checkEmpty = function(form) {
	for(let i = 0; i<form.length; i++){
		if(form[i].value.trim() === "" && (form[i].type === "text" || form[i].type === "password")){
			form[i].classList.add("error-contattaci");
			return false;
		}
	}
	return true;
}

let creaModal = function(){
	let img = document.createElement("img");
	img.setAttribute("src", "img/var/loading.gif");
	
	let h3 = document.createElement("h3");
	h3.appendChild(document.createTextNode("Invio mail in corso . . ."));
	
	let div = document.createElement("div");
	div.classList.add("modal-area");
	
	let divGen = document.createElement("div");
	divGen.setAttribute("id", "modal-wait");

	div.appendChild(img);
	div.appendChild(h3);
	
	divGen.appendChild(div);
	
	return divGen;
}
let validaMail = function(){
	let form = document.getElementsByName("form-contatti")[0];
	resetInput(form);
	if(checkEmpty(form)){
		if(!(form["mail"].value.trim().match(/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/))){
			form["mail"].classList.add("error-contattaci");
			form["mail"].setAttribute("title", "Inserisci una mail valida. Esempio: nome@dominio.it");
			return false;
		}
		if(!(form["nome"].value.trim().match(/^[a-zA-Z]{3,15}$/i))){
			form["nome"].classList.add("error-contattaci");
			form["nome"].setAttribute("title", "Il nome utente deve avere fra i 3 ed i 15 caratteri alfabetici");
			return false;
		}
		if(!(form["cogn"].value.trim().match(/^[a-zA-Z\s]{3,15}$/i))){
			form["cogn"].classList.add("error-contattaci");
			form["cogn"].setAttribute("title", "Il cognome deve avere fra i 3 ed i 15 caratteri alfabetici e spazi");
			return false;
		}
		if(form["textarea"].value.trim() == ""){
			form["textarea"].classList.add("error-contattaci");
			form["textarea"].setAttribute("title", "Impossibile inviare mail vuota");
			return false;
		}
		
		let x = creaModal();
		document.getElementsByTagName("main")[0].appendChild(x);
		let modalInner = document.getElementById("modal-wait").childNodes[0];
		setTimeout(() => {
			modalInner.parentNode.style.opacity = "1";
			modalInner.style.opacity = "1";
		}, 1);
		
		setTimeout(() => {
			modalInner.childNodes[0].setAttribute("src", "img/var/check.png");
			modalInner.childNodes[1].innerHTML = "Mail inviata con successo!"
				
				
				setTimeout(() => {
					modalInner.parentNode.style.opacity = "0";
					modalInner.style.opacity = "0";
					setTimeout(() => {
						document.location.href = "";			
					}, 1000);
				}, 1500);			
		}, 2000);
	}else return false;
}