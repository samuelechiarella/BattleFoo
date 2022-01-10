<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/background.css">
<link rel="stylesheet" type="text/css" href="css/create-team.css">
<link rel="stylesheet" href="css/organization.css">
<script type="text/javascript" src="js/background.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="js/organization.js" defer></script>
<script>
$(document).ready(function() {
	$('#content').load('html/background.html');
});
</script>
</head>
<body>
	<div id="content">
	<!-- including background.html -->
	</div>

	<ul class="tabs">
		<li data-tab-target="#home" class="active tab">HOME</li>
		<li data-tab-target="#tournaments" class="tab">TOURNAMENTS</li>
		<li data-tab-target="#staff" class="tab">STAFF</li>
		<li data-tab-target="#live" class="tab">LIVE</li>
	</ul>

	<div class="tab-content">
    <div id="home" data-tab-content class="active">
      <h1>HOME</h1>
      <p>This is the home</p>
    </div>
    <div id="tournaments" data-tab-content>
      <h1>TOURNAMENTS</h1>
      <p>Some information about tournaments</p>
    </div>
    <div id="staff" data-tab-content>
      <h1>STAFF</h1>
      <p>Let me tell you about our staff</p>
    </div>
		<div id="live" data-tab-content>
      <h1>LIVE</h1>
      <p>there will be implemented live stream</p>
    </div>
  </div>
</body>
</html>
