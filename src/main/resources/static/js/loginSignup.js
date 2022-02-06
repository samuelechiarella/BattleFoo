function login(){
	let div = document.getElementById("login-error-message");
	if(div.firstChild != null){
		div.removeChild(div.firstChild);
	}
	let span = document.createElement("span");
	span.style.color = "red";
	span.classList.add("do-not-hide");
	div.appendChild(span);
	
	if($("#loginUsername").val() == "" || $("#loginPassword").val()  == ""){
		span.innerHTML = "Fill both the fields!";
		document.getElementById("signupPassword").style.borderColor = "red";
		document.getElementById("confirmPassword").style.borderColor = "red";
		return;
	}
	let user = new UserLogIn($("#loginUsername").val(),$("#loginPassword").val());
	$.ajax({
		type: "POST",
		url: "/login",
		contentType: "application/json",
		data: JSON.stringify(user),
		success: function(answer){
			if(answer.responseCode==200) {
				location.reload(true);
			}
			else {
				document.getElementById("loginUsername").style.borderColor = "red";
				document.getElementById("loginPassword").style.borderColor = "red";
				span.innerHTML = "Invalid Username or Password";
			}
			console.log(answer.responseMessage);
		},
		error: function(err){
				console.log(err);
			}
	});
}

function signup() {
	let div = document.getElementById("signup-error-message");
	if(div.firstChild != null) {
		div.removeChild(div.firstChild);
	}
	let span = document.createElement("span");
	span.style.color = "red";
	span.classList.add("do-not-hide");
	div.appendChild(span);
	if($("#signupPassword").val() !== $("#confirmPassword").val()){
		span.innerHTML = "Passwords must be the same!";
		document.getElementById("signupPassword").style.borderColor = "red";
		document.getElementById("confirmPassword").style.borderColor = "red";
		return;
	}
	let user = new User($("#signupUsername").val(),$("#firstname").val(),$("#lastname").val(),$("#email").val(),$("#signupPassword").val());
	$.ajax({
		type: "POST",
		url: "/signup",
		contentType: "application/json",
		data: JSON.stringify(user),
		success: function(answer){
			span.innerHTML = answer.responseMessage;
			switch(answer.responseCode){
				case 501:
					document.getElementById("email").style.borderColor = "red";
					div.appendChild(span);
					break;
				case 502:
					document.getElementById("email").style.borderColor = "red";
					div.appendChild(span);
					break;
				case 503:
					document.getElementById("signupUsername").style.borderColor = "red";
					div.appendChild(span);
					break;
				default :
					location.reload(true);
					break;
			}
			console.log(answer.responseMessage);
		},
		error: function(err){
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

var clicked = false;
function clickLogin(){ clicked=true; }

function logout(){
	$.ajax({
		type: "GET",
		url: "/logout",
		contentType: "application/json",
		success: function(answer){
			console.log(answer.responseMessage);
			clicked = false;
			location.href = "/index";
		},
		error: function(err){
			console.log(err);
		}
	});
}

var username = "";
var firstname = "";
var lastname = "";
var email = "";
var password = "";

function onSignIn(googleUser) {
  if(clicked){  
	var profile = googleUser.getBasicProfile();
	email = profile.getEmail();
	username = email.replace("@gmail.com","");
  	firstname = profile.getGivenName()
  	lastname = profile.getName().replace(profile.getGivenName(),"");
    console.log(username);
  	loginWithGoogle();
  }
}

function loginWithGoogle(){
	$("#loginUsername").val(username);
	$.ajax({
		type: "POST",
		url: "/loginWithGoogle",
		contentType: "application/json",
		data: JSON.stringify(username),
		success: function(answer){
			if(answer.responseCode==200) {
				location.reload(true);
			}
			else {
				openLoginSignup("log-in-google");
			}
		},
		error: function(err){
				console.log(err);
			}
	});
}

function signupWithGoogle() {
	password = $("#loginGooglePassword").val();
	let user = new User(username,firstname,lastname,email,password);
	$.ajax({
		type: "POST",
		url: "/signup",
		contentType: "application/json",
		data: JSON.stringify(user),
		success: function(answer){
			if(answer.responseCode == 200){
				$("#loginUsername").val(username);
				$("#loginPassword").val(password);
				login();
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