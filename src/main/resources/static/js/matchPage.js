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
	}, 5000);
});