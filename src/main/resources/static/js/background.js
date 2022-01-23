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

function openPopup(id) {
  var popup = document.getElementById(id);
  popup.classList.toggle("show");
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

function openTeamPage(teamName){
	$.ajax({
		type: "POST",
		url: "/teamPage",
		contentType: "application/json",
		data: JSON.stringify(teamName),
		success: function(answer){
			if(answer.responseCode==200){
				location.href = "/teamPage";
			}
			console.log(answer.responseMessage);
		},
		error: function(err){
			console.log(err);
		}
	});
}

function openOrganizationPage(organizationId, organizationName, description, creatorId){
	let org = new Organization(organizationId, organizationName, description, creatorId);
	console.log(org);
	$.ajax({
		type: "POST",
		url: "/organizationPage",
		contentType: "application/json",
		data: JSON.stringify(org),
		success: function(answer){
			if(answer.responseCode==200){
				location.href = "/organizationPage";
			}
			console.log(answer.responseMessage);
		},
		error: function(err){
			console.log(err);
		}
	});
}