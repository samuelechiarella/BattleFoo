<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/organization.css">
<script src="js/organization.js" defer></script>
<jsp:include page="resources.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="background.jsp"></jsp:include>

	<ul class="tabs">
		<li data-tab-target="#home" class="active tab">HOME</li>
		<li data-tab-target="#tournaments" class="tab">TOURNAMENTS</li>
		<li data-tab-target="#staff" class="tab">STAFF</li>
		<li data-tab-target="#live" class="tab">LIVE</li>
	</ul>

	<div class="tab-content">
    <div id="home" data-tab-content class="active">
			<img src="src\main\resources\static\images\games_banner_index\lolWallpaper.jpg" alt="Organization Logo">
      <h1>Org Name HERE</h1>
      <p>Add a description for your Organization</p>
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
