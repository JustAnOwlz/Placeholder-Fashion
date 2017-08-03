function resetInput(form){
	for(let i = 0; i<form.length; i++){
		form[i].classList.remove("error");
		form[i].setAttribute("title", "");
	}
}
function checkEmpty(form){
	for(let i = 0; i<form.length; i++){
		if(form[i].value.trim() === "" && (form[i].type === "text" || form[i].type === "password")){
			form[i].classList.add("error");
			return false;
		}
	}
	return true;
}

function validateLogin(form){
	resetInput(form);
	return checkEmpty(form);
}

function validateRegistrazione(form){
	console.log("Dentro la validazione");
	resetInput(form);
	console.log("resettati i campi")
	if(checkEmpty(form)){
		console.log("nessun campo vuoto");
		if(!(form["user"].value.trim().match(/^[a-zA-Z-\.\d]{3,15}$/))){
			form["user"].setAttribute("title", "L'username deve avere fra i 3 ed i 15 caratteri alfanumerici");
			form["user"].classList.add("error");
			console.log("niente nome utente");
			return false;
		}
		if(!(form["pswd"].value.match(/^[\S]{5,}$/))){
			form["pswd"].classList.add("error");
			form["pswd"].setAttribute("title", "La password deve avere fra i 3 ed i 10 caratteri e non può avere white space");
			return false;
		}
		if(!(form["mail"].value.trim().match(/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/))){
			form["mail"].classList.add("error");
			form["mail"].setAttribute("title", "Inserisci una mail valida. Esempio: nome@dominio.it");
			return false;
		}
		if(!(form["nome"].value.trim().match(/^[a-zA-Z]{3,15}$/i))){
			form["nome"].classList.add("error");
			form["nome"].setAttribute("title", "Il nome utente deve avere fra i 3 ed i 15 caratteri alfabetici");
			return false;
		}
		if(!(form["cogn"].value.trim().match(/^[a-zA-Z\s]{3,15}$/i))){
			form["cogn"].classList.add("error");
			form["cogn"].setAttribute("title", "Il cognome deve avere fra i 3 ed i 15 caratteri alfabetici e spazi");
			return false;
		}
		if(!(form["addr"].value.trim().match(/^[a-zA-Z]{3,10}\s([a-zA-Z]{2,10}\s?)*\,\s?\d{1,5}\s?(\((\w*\s*)*\))?$/i))){
			form["addr"].classList.add("error");
			form["addr"].setAttribute("title", "Esempio di indirizzo: Via Verdi, 59 (Interno 1)");
			return false;
		}
		
		return true;
	}else return false;
}


var flag = false;
function checkSize(){
	if(window.innerWidth <= 850){
		if(!flag){
			$('.side:first-child').hide();
			$('.side:last-child').show();
		}else{
			$('.side:last-child').hide();
			$('.side:first-child').show();
		}
	}else
		$('.side').show();
}

window.onload = function (){
	checkSize();
}
window.addEventListener('resize', function(){
	checkSize();
});

function showReg(){
	$('#reg').slideDown(500)
	.animate({opacity: '1'}, {duration: '500', queue: true});
	
	$('#log').slideUp(500)
	.animate({opacity: '0'}, {duration: '500', queue: true});
	
	$('#blocco-login')
	.css("background-image", "url(img/ragazza-login.png)");
	
	if(window.innerWidth <= 850){
		$('.side:last-child').hide();
		$('.side:first-child').show();
	}else
		$('.side').show();	
	flag = true
}
function showLog(){
	$('#log').slideDown(500)
	.animate({opacity: '1'}, {duration: '500', queue: true});
	
	$('#reg').slideUp(500)
	.animate({opacity: '0'}, {duration: '500', queue: true});

	$('#blocco-login')
		.css("background-image", "url(img/ragazzo-login-2.png)");
	
	if(window.innerWidth <= 850){
		$('.side:first-child').hide();
		$('.side:last-child').show();
	}else
		$('.side').show();
	flag = false;
}