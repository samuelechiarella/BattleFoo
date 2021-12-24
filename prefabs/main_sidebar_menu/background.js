/* Set the width of the sidebar to 250px (show it) */
function openMenu() {
  document.getElementById("sidePanel").style.width = "250px";
}

/* Set the width of the sidebar to 0 (hide it) */
function closeMenu() {
  document.getElementById("sidePanel").style.width = "0";
}

function openMTournaments(){
	document.getElementById("organizeTournamentsBackside").style.width = "250px";
}

function openMTeams(){
	document.getElementById("manageTeamsBackside").style.width = "250px";
}

function closeBack() {
	document.getElementById("organizeTournamentsBackside").style.width = "0px";
	document.getElementById("manageTeamsBackside").style.width = "0px";
}