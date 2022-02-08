var last = ""
function openTab(evt, tabName) {
  // Declare all variables
  var i, tabcontent, tablinks;

  // Get all elements with class="tabcontent" and hide them
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  // Get all elements with class="tablinks" and remove the class "active"
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }

  //clicking twice a button will close it
  if(last.replace(" active", "")==event.currentTarget.className && last.includes("active")){
    document.getElementById(tabName).style.display = "none";
    evt.currentTarget.className.replace(" active", "");
    last = evt.currentTarget.className;
  }
  else{
    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
    last = evt.currentTarget.className;
  }
}

function setDescriptionEditable(){
	document.getElementById("editTeamDescription").classList.add("hide-apply-changes");
	document.querySelector(".apply-cancel-changes").classList.remove("hide-apply-changes");
	document.querySelector("#description-content textarea").removeAttribute("readonly");
	document.querySelector("#description-content textarea").style.pointerEvents = "auto";
}

function setNotEditable() {
	location.reload();
}

function confirmDescriptionChanges() {
	console.log($("#description-content textarea").val());
	$.ajax({
		type: "POST",
		url: "/editDescription",
		contentType: "application/json",
		data: JSON.stringify($("#description-content textarea").val()),
		success:function(answer){
			console.log(answer.responseMessage);
			textarea = document.querySelector("#description-content textarea");
			textarea.readonly = true;
			textarea.style.pointerEvents = "none";
			document.getElementById("editTeamDescription").classList.remove("hide-apply-changes")
			document.querySelector(".apply-cancel-changes").classList.add("hide-apply-changes");;
		},
		error:function(err){
			console.log(err);
		}
	});
}

function insertNewTeamMember() {
	$.ajax({
        type: "POST", 
        url: "/addTeamMember",
        contentType: "application/json",
        data: JSON.stringify($("#newTeamMember").val()),
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
					insertTeamMember(answer.data)
					break;
			}
		}, 
        error: function(err){ console.log(err); }
    });
}

				  
				
function insertTeamMember(member){
	var div= "<div class='attendee tooltip'><img id='" + member.username + "' src='" + member.profilePicture + "'>"+
				"<span class='tooltiptext'>" + member.username + "</span></div>";
	$('#theTeamList').append(div);
}

function removeTeamMember() {
	$.ajax({
        type: "POST", 
        url: "/removeTeamMember",
        contentType: "application/json",
        data: JSON.stringify($("#newTeamMember").val()),
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
					console.log(answer.data);
					removeMember(answer.data);
					break;
			}
		}, 
        error: function(err){console.log(err)}
    });
}

function removeMember(member){
	let div = document.getElementById(member.username).parentElement;
	document.getElementById("theTeamList").removeChild(div);
}