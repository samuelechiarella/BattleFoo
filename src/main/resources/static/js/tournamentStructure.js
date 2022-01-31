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

function openTournamentPage(tournamentId) {
	$.ajax({
		type: "POST",
		url: "tournamentPage",
		contentType: "application/json",
		data: JSON.stringify(tournamentId),
		success: function(answer){
			if(answer.responseCode == 200){
				location.href = "/tounamentPage";
			}
			else{
				console.log(answer.responseMessage);
			}
		},
		error: function(err){ console.log(err); }
	});
}