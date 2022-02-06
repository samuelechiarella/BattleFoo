function applyFilterSearchTournament(){
	if($("#tournamentName").val() == null){return;}
	let tbody = document.getElementById("filterTournamentTableBody");
	while(tbody.hasChildNodes())
		tbody.removeChild(tbody.firstChild);
		
	$.ajax({
		type:"POST",
		url:"/filterTournament",
		contentType:"application/json",
		data: JSON.stringify($("#tournamentName").val()),
		success: function(tournament){
			if (tournament == null){
				console.log(" TOURNAMENT LIST IS NULL");
				return;
				}
			for(let i = 0; i < tournament.length; ++i) {
			insertNewTournament(tournament[i][0],tournament[i][1],tournament[i][2],tournament[i][3]);
				}
			},
			error: function(err){
			console.log(err);
			}
		});
	}
	function insertNewTournament(logo, name, data,id){
	var row= "<tr onclick=\"openTournamentPage('" + Number(id) + "')\"><td><img src='"
			+ logo + "'></td><td>" + name + "</td><td>" + data + "</td></tr>";
  	$('#filterTournamentTableBody').append(row);
  	
} 
	
	