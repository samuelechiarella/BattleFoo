function openCreateTeamSidebar(){
  closeMenu();
  closeBack();
  document.getElementsByClassName("side-panel")[0].style.right="0";
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
		let newTeam = new Team($("#nickname").val(), $("#img-logo").attr("src"));
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
							location.reload(true);
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