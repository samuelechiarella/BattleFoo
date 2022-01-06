function openMenu() {
  document.getElementById("sidePanel").style.left = "0";
}

function closeMenu() {
  document.getElementById("sidePanel").style.left = "-12.5em";
}

function openOrganizeTournaments(){
	document.getElementById("organizeTournamentsBackside").style.left = "0";
}

function openManageTeams(){
	document.getElementById("manageTeamsBackside").style.left = "0";
}

function closeBack() {
	document.getElementById("organizeTournamentsBackside").style.left = "-12.5em";
	document.getElementById("manageTeamsBackside").style.left = "-12.5em";
}

function openCreateTeamSidebar(){
  closeMenu();
  closeBack();
  document.getElementsByClassName("side-panel")[0].style.right="0";
}

function sendInvite(){
  //check if it exists
  //put it into the list
}

function closeCreateTeam(){
  document.getElementsByClassName("side-panel")[0].style.right="-30em";
  openMenu();
}

function getImg(input) {
	let file = input.files[0];
	let reader = new FileReader();
	if(file){
		reader.readAsDataURL(file);
		reader.onload = function() {
			let img = document.getElementById("img-logo");
			//img.src=URL.createObjectURL(file);
			$.ajax({
				type: "POST",
				url: "/storeTeamLogo",
				contentType: "application/json",
				data: JSON.stringify(reader.result),
				success: function(risposta){
					//****************************************************************** create a class RESPONSE
					console.log(risposta);
					if (risposta === "Done"){
						img.src=reader.result;
						console.log("finally - " + file.name + " stored");
					}
				},
				error: function(xhr){
					console.log(xhr);
					var res = JSON.parse(xhr.responseText);
					alert(res.messaggio);
				}
			});
		};
	}
}

function loadEvents(){
}

window.addEventListener("load", function(){
  loadEvents();
});