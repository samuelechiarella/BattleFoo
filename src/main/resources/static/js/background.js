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

function openPopup(id) {
  var popup = document.getElementById(id);
  popup.classList.toggle("show");
}

function openCreateOrganizationSidebar() {
  closeMenu();
  closeBack();
  document.getElementsByClassName("side-panel-create-organization")[0].style.right="0";
}

function closeCreateOrganization() {
  document.getElementsByClassName("side-panel-create-organization")[0].style.right="-30em";
  $("#nicknameOrganization").val("");
  $("#img-banner").removeAttr("src");
  openOrganizeTournaments();
  openMenu();
}
function openCreateTeamSidebar(){
  closeMenu();
  closeBack();
  document.getElementsByClassName("side-panel-create-team")[0].style.right="0";
}

function closeCreateTeam(){
  $("#nickname").val("");
  $("#img-logo").removeAttr("src");
  document.getElementsByClassName("side-panel-create-team")[0].style.right="-30em";
  openManageTeams();
  openMenu();
}

function openLoginSignup(choice){
  	closeCreateTeam();
  	closeMenu();
  	closeBack();
  	array = document.body.getElementsByTagName("*");
  	let len = array.length;
  	for (var i = 0; i < len; i++){
    	if(!array[i].classList.contains("do-not-hide")){
        	array[i].classList.add("semi-transparent");
    	}
  	}
  	document.getElementsByClassName(choice)[0].classList.add("show-login-signup");
};

function closeLoginSignup(choice){
  	let array = document.body.getElementsByTagName("*");
  	for (let i = 0, len = array.length; i < len; i++){
    	if(!array[i].classList.contains("do-not-hide")){
        	array[i].classList.remove("semi-transparent");
    	}
  	}
	array = document.body.getElementsByClassName("clear-" + choice);
	for (let i = 0, len = array.length; i < len; i++){
    	array[i].value="";
  	}
  	document.getElementsByClassName(choice)[0].classList.remove("show-login-signup");
}

function openTournamentPage(tournamentId) {
	$.ajax({
		type: "POST",
		url: "/tournamentPage",
		contentType: "application/json",
		data: JSON.stringify(tournamentId),
		success: function(answer){
			if(answer.responseCode == 200){
				location.href = "/tournamentPage";
			}
			else{
				console.log(answer.responseMessage);
			}
		},
		error: function(err){ console.log(err); }
	});
}