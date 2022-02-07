function storeOrganization() {
	$(document).ready(function(){
		let newOrganization = new Organization($("#nicknameOrganization").val(), $("#img-banner").attr("src"));
		$.ajax({
			type: "POST",
			url: "/createOrganization",
			contentType: "application/json",
			data: JSON.stringify(newOrganization),
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
						console.log(answer.message);
						location.href = "/organizationPage";
						break;
				};
			},
			error: function(err){
				console.log("CREATE TEAM ERROR");
				console.log(err);
			}
		});
	});
}

function getOrgImg(input) {
	let file = input.files[0];
	let reader = new FileReader();
	if(file){
		reader.readAsDataURL(file);
		reader.onload = function() {
			$("#img-banner").attr("src",reader.result);
		};
	}
}

function openOrganizationPage(organizationId, creatorId){
	let orgIdAndCreatorId = [organizationId, creatorId];
	$.ajax({
		type: "POST",
		url: "/organizationPage",
		contentType: "application/json",
		data: JSON.stringify(orgIdAndCreatorId),
		success: function(answer){
			if(answer.responseCode==200){
				location.href = "/organizationPage";
			}
			console.log(answer.responseMessage);
		},
		error: function(err){
			console.log(err);
		}
	});
}