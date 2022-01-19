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
    $.ajax({
        type: "POST", 
        url: "/addMember",
        contentType: "apllication/json",
        data: JSON.stringify($("#newMember").val()),
        success: function(answer){}, 
        error: function(err){console.log("err")}
    });
}