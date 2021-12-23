/* Set the width of the sidebar to 250px (show it) */
function openNav() {
  document.getElementById("mySidepanel").style.width = "250px";
}

/* Set the width of the sidebar to 0 (hide it) */
function closeNav() {
  document.getElementById("mySidepanel").style.width = "0";
}

function openMTournaments(){
	document.getElementById("myBackMTournaments").style.width = "250px";
}

function openMTeams(){
	document.getElementById("myBackMTeams").style.width = "250px";
}

function closeBack() {
	document.getElementById("myBackMTournaments").style.width = "0px";
	document.getElementById("myBackMTeams").style.width = "0px";
  	document.getElementById("mySidepanel").style.width = "250px";
}