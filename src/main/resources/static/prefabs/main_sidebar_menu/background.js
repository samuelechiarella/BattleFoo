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


function openCreateOrganizationSidebar(){
  closeMenu();
  closeBack();
  document.getElementsByClassName("side-panel-organization")[0].style.right="0";
}

function closeCreateOrganization(){
  document.getElementsByClassName("side-panel-organization")[0].style.right="-30em";
  openMenu();
}
function sendInviteOrg(){
  //check if it exists
  //put it into the list
}

function getImg() {
  // send the img (json) to the server and store the img into that (TO-DO)
  // then store the url into the db (TO-DO)
  // show it in its place (TO-DO)
}

function loadEvents(){
}

window.addEventListener("load", function(){
  loadEvents();
});