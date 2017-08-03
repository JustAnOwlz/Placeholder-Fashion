function apriTab(evt, nomeTab) {
	let i, contenutoTabs, linkTabs;
	
	/*li fa scomparire tutti*/
	contenutoTabs = document.getElementsByClassName("contenuto-tab");
	for (i = 0; i < contenutoTabs.length; i++) contenutoTabs[i].style.display = "none";
	
	linkTabs = document.getElementsByClassName("link-tab");
	for (i = 0; i < linkTabs.length; i++) linkTabs[i].classList.remove("active");
	
	document.getElementById(nomeTab).style.display = "block";
	evt.currentTarget.classList.add("active");
}

function logout(){
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