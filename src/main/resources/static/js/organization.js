function setActive(choice) {
	let tabs = document.getElementsByClassName("tab");
	for(let i=0; i < tabs.length; ++i){
		tabs[i].classList.remove("active");
	}
	let contents = document.getElementsByClassName("tab-content");
	for(let i=0; i < contents.length; ++i){
		contents[i].classList.remove("active");
	}
	document.getElementById(choice).classList.add("active");
	document.getElementById(choice.substring(0,choice.length-3)+"Content").classList.add("active");
}

function addMember() {
    $.ajax({
        type: "POST", 
        url: "/addMember",
        contentType: "application/json",
        data: JSON.stringify($("#newMember").val()),
        success: function(answer){
			switch(answer.responseCode){
				case 501:
					alert(answer.responseMessage);
					break;
				case 502:
					alert(answer.responseMessage);
					break;
				case 503:
					alert(answer.responseMessage);
					break;
				default:
					insertNewMember(answer.data.profilePicture, answer.data.username);
					$("#newMember").val("");
					break;
			}
		}, 
        error: function(err){console.log(err);}
    });
}

function removeMember (){
	$.ajax({
		type: "POST",
		url: "/removeMember",
		contentType: "application/json",
		data: JSON.stringify($("#newMember").val()),
		success: function(answer){
			if (answer.responseCode == 200){
				location.reload();
			} else {
				console.log(answer.responseMessage);
			}
		},
		error: function(err){console.log(err);}
	});
}

function insertNewMember(profilePicture, username){
	console.log("<img src=\""+profilePicture+"\">");
	let table = $(".staffTable")[0];
	let row = table.insertRow(-1);
	let cell = [row.insertCell(0), row.insertCell(1)];
	let img = document.createElement('img');
	img.src = profilePicture;
	cell[0].appendChild(img);
	cell[1].textContent = username;
}

function selectTournament(element){
	let tr = document.querySelectorAll("#tournamentsContent tr");
	for(let i = 0; i < tr.length; ++i){
		tr[i].classList.remove("tournament-active");
	}
	element.classList.add("tournament-active");
}

function deleteTournament() {
	let tr = document.getElementsByClassName("tournament-active")[0];
	if(tr == null){
		alert("Select a Tournament to delete it");
		return;
	}
	$.ajax({
		type: "POST",
		url: "/deleteTournament",
		contentType: "application/json",
		data: JSON.stringify(tr.id),
		success: function(answer){
			if(answer.responseCode==200) {
				alert(answer.responseMessage);
				location.reload();
			}
			else {
				console.log(answer.responseMessage);
			}
		},
		error: function(err){ console.log(err); }
	});
}

function deleteOrganization(orgId) {
	$.ajax({
		type: "POST",
		url: "/deleteOrganization",
		contentType: "application/json",
		data: JSON.stringify(orgId),
		success: function(answer){
			if(answer.responseCode==200) {
				alert(answer.responseMessage);
				location.href = "/index";
			}
			else {
				console.log(answer.responseMessage);
			}
		},
		error: function(err){ console.log(err); }
	});
}

function editOrganizationDescription() {
	textarea = document.getElementById("organizationDescription");
	textarea.removeAttribute("readonly");
}

function closeOrganizationDescriptionEditing() {
	textarea = document.getElementById("organizationDescription");
	$.ajax({
		type: "POST",
		url: "/editOrganizationDescription",
		contentType: "application/json",
		data: JSON.stringify(textarea.value),
		success: function(answer){
			if(answer.responseCode){
				textarea.readonly = true;
			}
			else {
				console.log(answer.responseMessage);
			}
		},
		error: function(err){ console.log(err); }
	});
}