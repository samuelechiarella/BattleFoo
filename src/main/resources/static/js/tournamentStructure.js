const tabs = document.querySelectorAll('[data-tab-target]')
const tabContents = document.querySelectorAll('[data-tab-content]')

tabs.forEach(tab => {
  tab.addEventListener('click', () => {
    const target = document.querySelector(tab.dataset.tabTarget)
    tabContents.forEach(tabContent => {
      tabContent.classList.remove('active')
    })
    tabs.forEach(tab => {
      tab.classList.remove('active')
    })
    tab.classList.add('active')
    target.classList.add('active')
    if(!target.className.includes("overview")) {
        document.getElementsByClassName("overview_box")[0].style.display="none";
    }
    else {
        document.getElementsByClassName("overview_box")[0].style.display="block";

    }
  })
});

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
  // Show the current tab, and add an "active" class to the button that opened the tab
  document.getElementById(tabName).style.display = "block";
  evt.currentTarget.className += " active";
  console.log(evt.currentTarget.className);
}
function openLeaveTournament() {
	document.getElementsByClassName("signup-tournament")[0].classList.add("show-signup-tournament");
}

function openSignupTournament() {
	document.getElementsByClassName("signup-tournament")[0].classList.add("show-signup-tournament");
}

function closeSignupTournament() {
	document.getElementById("teamChoosedForSignup").removeAttribute("src");
	document.getElementById("teamChoosedForSignup").removeAttribute("title");
	document.getElementsByClassName("signup-tournament")[0].classList.remove("show-signup-tournament");
}

function setImageTeamChoosed(teamLogo,teamName) {
	$("#teamChoosedForSignup").attr("src",teamLogo);
	$("#teamChoosedForSignup").attr("title",teamName);
}

function signupTeam() {
	teamName = $("#teamChoosedForSignup").attr("title");
	if(teamName == null){
		alert("Choose a Team!");
		return;
	}
	$.ajax({
		type: "POST",
		url: "/signupTeam",
		contentType: "application/json",
		data: JSON.stringify(teamName),
		success:function(answer){
			if(answer.responseMessage==200){
				location.reaload();
			}
			else{
				alert(answer.responseMessage);
			}
		}
	});
}

function sendMessage() {
	if($("#messageToSend").val() == null){ return; }
	$.ajax({
		type: "POST",
		url: "/sendMessage",
		contentType: "application/json",
		data: JSON.stringify($("#messageToSend").val()),
		success: function(answer){
			if(answer.responseCode==200){
				$("#messageToSend").val("");
			}
			else{
				console.log(answer.responseMessage);
			}
		},
		error: function(err){ console.log(err); }
	});
}

$(document).ready(function(){
	setInterval(function() {
	console.log("refreshed");
	$.ajax({
		type: "GET",
		url: "/refreshChatHistory",
		contentType: "application/json",
		success: function(answer){
			if(answer.responseCode==200){
				$("#textAreaChat").val(answer.data);
			}
			else{
				console.log(answer.responseMessage);
			}
		},
		error: function(err){ console.log(err); }
	});
}, 1000);
});