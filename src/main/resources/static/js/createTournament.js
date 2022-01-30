sponsorBanner = "";
tournamentBanner = "";
gameName="";
function createTournament() {
	let name = $("#createTournamentName").val();
	let date = $("#createTournamentDate").val();
	let description = $("#createTournamentDescription").val();
	let rules = $("#createTournamentRules").val();
	let schedule = $("#createTournamentSchedule").val();
	let prizes = "Prizes";
	let tournament = new Tournament(name,date,description,rules,schedule,gameName,sponsorBanner,prizes,tournamentBanner);
	$.ajax({
		type: "POST",
		url: "/createTournament",
		contentType: "application/json",
		data: JSON.stringify(tournament),
		success: function(answer){
			console.log("done");
		},
		error: function(err){
			console.log(err);
		}
	});
}

function closeCreateTournament() {
	location.reload();
}

function setGameName(name){
	gameName = name;
	$("#gameTitle").val(gameName);
}