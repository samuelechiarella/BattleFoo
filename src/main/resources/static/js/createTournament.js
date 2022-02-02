function createTournament() {
	let numOfAttendees = $("#numOfAttendees").val();
	if(parseInt(numOfAttendees) < 8 || parseInt(numOfAttendees) > 32){
		alert("Number of attendees must be between 8 and 32!");
	}
	else{
		let name = $("#createTournamentName").val();
		let date = $("#createTournamentDate").val();
		let description = $("#createTournamentDescription").val();
		let rules = $("#createTournamentRules").val();
		let schedule = $("#createTournamentSchedule").val();
		let gameName = $("#gameTitle").val();
		let sponsorBanner = $("#sponsor-banner-img").attr("src");
		let prizes = "Prizes";
		let tournamentBanner = $("#tournament-banner-img").attr("src");
		let tournament = new Tournament(name,date,description,rules,schedule,gameName,sponsorBanner,prizes,tournamentBanner,numOfAttendees);
		$.ajax({
			type: "POST",
			url: "/createTournament",
			contentType: "application/json",
			data: JSON.stringify(tournament),
			success: function(answer){
				if(answer.responseCode==200){
					location.href = "/tournamentPage";
				}
				else{
					alert(answer.responseMessage);
				}
			},
			error: function(err){
				console.log(err);
			}
		});
	}
}

function closeCreateTournament() {
	location.reload();
}

function setGameName(name){
	gameName = name;
	$("#gameTitle").val(gameName);
}

function getSponsorBanner(input){
	let file = input.files[0];
	let reader = new FileReader();
	if(file){
		reader.readAsDataURL(file);
		reader.onload = function() {
			$("#sponsor-banner-img").attr("src",reader.result);
		};
	}	
}

function getTournamentBanner(input){
	let file = input.files[0];
	let reader = new FileReader();
	if(file){
		reader.readAsDataURL(file);
		reader.onload = function() {
			$("#tournament-banner-img").attr("src",reader.result);
		};
	}	
}

function openCreateTournament(){
  	closeCreateTeam();
  	closeMenu();
  	closeBack();
	let firstDiv = document.querySelector(".create-tournament-tabs");
	firstDiv.classList.add("show-create-tournament");
  	array = document.body.querySelectorAll("*:not(.do-not-hide-create-tournament)");
  	let len = array.length;
  	for (var i = 0; i < len; i++){
		array[i].classList.add("no-event-in-create-tournament");
		if(!array[i].classList.contains("do-not-hide")){
        	array[i].classList.add("semi-transparent-in-create-tournament");
		}
 	}
}

function applyFilterSearchGame() {
	if($("#gameTitle").val() == null){ return; }
	let tbody = document.getElementById("gamesTableBodyCreateTournament");
	while(tbody.hasChildNodes()){
		tbody.removeChild(tbody.firstChild);
	}
	$.ajax({
		type: "POST",
		url: "/filterGames",
		contentType: "application/json",
		data: JSON.stringify($("#gameTitle").val()),
		success: function(games){
			if(games == null){
				console.log("GAMES LIST FILTERED IS NULL");
				return;
			}
			
			for(let i = 0; i < games.length; ++i) {
				insertNewGame(games[i][0],games[i][1],games[i][2]);
			}
		},
		error: function(err){
			console.log(err);
		}
	});
}

function insertNewGame(logo, name, genre){
	/*let table = document.getElementById("gamesTableCreateTournament");
	let row = table.insertRow(-1);
	let cell = [row.insertCell(0), row.insertCell(1)];
	let img = document.createElement('img');
	img.src = logo;
	cell[0].appendChild(img);
	cell[1].textContent = name;*/
	var row= "<tr class='do-not-hide-create-tournament' onclick=\"setGameName('" + name + "')\"><td class='do-not-hide-create-tournament'><img src='"
			+ logo + "'></td><td>" + name + "</td><td>" + genre + "</td></tr>";
  	$('#gamesTableCreateTournament').append(row);
} 