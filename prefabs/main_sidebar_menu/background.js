var sizeSidebar = "12.5em";
function openMenu() {
  document.getElementById("sidePanel").style.width = sizeSidebar;
}

function closeMenu() {
  document.getElementById("sidePanel").style.width = "0";
}

function openOrganizeTournaments(){
	document.getElementById("organizeTournamentsBackside").style.width = sizeSidebar;
}

function openManageTeams(){
	document.getElementById("manageTeamsBackside").style.width = sizeSidebar;
}

function closeBack() {
	document.getElementById("organizeTournamentsBackside").style.width = "0px";
	document.getElementById("manageTeamsBackside").style.width = "0px";
}