sponsorBanner = "";
tournamentBanner = "";
gameName="";
function createTournament() {
	let name = $("#createTournamentName").val();
	let date = $("#create-tournament-date").val();
	let description = $("#createTournamentDescription").val();
	let rules = $("#createTournamentRules").val();
	let schedule = $("#createTournament").val();
	let gameName = "GameName";
	let sponsor = "BannerSponsor";
	let prizes = "Prizes";
	let logo = "Logo";
	let tournament = new Tournament(name,date,description,rules,schedule,gameName,sponsor,prizes,logo);
	$.ajax({
		type: "POST",
		url: "/createTournament",
		contentType: "application/json",
		data: JSON.stringify(tournament),
		success: function(){
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