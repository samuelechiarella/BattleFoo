var imgDataURL = "";

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
	console.log(id);
  var popup = document.getElementById(id);
  popup.classList.toggle("show");
}

function closeCreateTeam(){
  $("#nickname").val("");
  $("#img-logo").attr("src","");
  document.getElementsByClassName("side-panel")[0].style.right="-30em";
  openManageTeams();
  openMenu();
}

function storeTeam() {
	$(document).ready(function(){
		let newTeam = new Team($("#nickname").val(), imgDataURL);
		$.ajax({
			type: "POST",
			url: "/createTeam",
			contentType: "application/json",
			data: JSON.stringify(newTeam),
			success: function(answer){
				switch(answer.responseCode){
						case 501:
							openPopup("nameTakenPopup");
							console.log(answer.responseMessage);
							break;
						case 502:
							openPopup("teamNameNotFilledPopup");
							console.log(answer.responseMessage);
							break;
						case 503:
							openPopup("constraintsNotSatisfiedPopup");
							console.log(answer.responseMessage);
							break;
						default:
							console.log("TEAM CREATED");
							window.location = "/";
							break;
					};
			},
			error: function(err){
				console.log("CREATE TEAM ERROR");
				console.log(err);
			}
		});
	});
}

function getImg(input) {
	let file = input.files[0];
	let reader = new FileReader();
	if(file){
		reader.readAsDataURL(file);
		reader.onload = function() {
			imgDataURL = reader.result;
			$("#img-logo").attr("src",imgDataURL);
		};
	}
}

function loadEvents(){
}

window.addEventListener("load", function(){
  loadEvents();
});