$(document).ready(function(){
	$.ajax({
		type: "GET",
		url: "/testDB2",
		contentType: "application/json",
		success:function(data){
			console.log(data);
			$(".my_gracket").gracket({ 
		    	src: data
			});
		},
		error: function(err){
			console.log(err);
		}
	});
	
});


