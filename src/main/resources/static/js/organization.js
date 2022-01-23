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
  })
})

function addMember() {
	console.log($("#newMember").val());
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
					breaK;
				default:
					insertNewMember(answer.data.profilePicture, answer.data.username);
					$("#newMember").val("");
					break;
			}
		}, 
        error: function(err){console.log("err")}
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