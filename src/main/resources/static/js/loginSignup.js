function login(){
	let user = new UserLogIn($("#loginUsername").val(),$("#loginPassword").val());
	console.log(user)
	$.ajax({
		type: "POST",
		url: "/login",
		contentType: "application/json",
		data: JSON.stringify(user),
		success: function(answer){
			if(answer.responseCode==200){
				location.reload(true);
			}
			else{
				openPopup("invalidUsernamePassword");
			}
			console.log(answer.responseMessage);
		},
		error: function(err){
				console.log("CREATE TEAM ERROR");
				console.log(err);
			}
	});
}

function signup(){
	if($("#signupPassword").val() !== $("#confirmPassword").val()){
		console.log("Passwords must be the same!");
		return;
	}
	let user = new User($("#signupUsername").val(),$("#firstname").val(),$("#lastname").val(),$("#email").val(),$("#signupPassword").val());
	console.log(user)
	$.ajax({
		type: "POST",
		url: "/signup",
		contentType: "application/json",
		data: JSON.stringify(user),
		success: function(answer){
			switch(answer.responseCode){
				case 501:
					console.log("Invali email!");
					break;
				case 502:
					console.log("Email already exists!");
					break;
				case 503:
					console.log("Username already exists!");
					break;
				default :
					location.reload(true);
					break;
			}
			console.log(answer.responseMessage);
		},
		error: function(err){
				console.log("CREATE TEAM ERROR");
				console.log(err);
			}
	});
}

function signupAsManager(){
	$.ajax({
		type: "POST",
		url: "/signupManager",
		contentType: "application/json",
		data: JSON.stringify($("#signupManagerPassword").val()),
		success: function(answer){
			if(answer.responseCode == 200){
				location.reload();
			}
			else {
				alert(answer.responseMessage);
			}
		},
		error: function(err){
			console.log(err);
		}
	});	
}