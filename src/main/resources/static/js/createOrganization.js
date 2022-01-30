function storeTeam() {
	$(document).ready(function(){
		let newTeam = new Team($("#nicknameOrganization").val(), $("#img-banner").attr("src"));
		$.ajax({
			type: "POST",
			url: "/createOrganization",
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

function getOrgImg(input) {
	let file = input.files[0];
	let reader = new FileReader();
	if(file){
		reader.readAsDataURL(file);
		reader.onload = function() {
			$("#img-banner").attr("src",reader.result);
		};
	}
}