<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="prefabs/main_sidebar_menu/background.css">
<link rel="stylesheet" type="text/css" href="prefabs/main_sidebar_menu/create-team.css">
<link rel="stylesheet" href="tournamentStructure.css">
<script type="text/javascript" src="prefabs/main_sidebar_menu/background.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="tournamentStructure.js" defer></script>
<script>
$(document).ready(function() {
	$('#content').load('prefabs/main_sidebar_menu/background.html');
});
</script>
</head>
<body>
	<div id="content">
	<!-- including background.html -->
	</div>

	<ul class="tabs">
		<li data-tab-target="#overview" class="active tab">OVERVIEW</li>
		<li data-tab-target="#partecipants" class="tab">PARTECIPANTS</li>
		<li data-tab-target="#brakets" class="tab">BRACKETS</li>
    <li data-tab-target="#stats" class="tab">STATS</li>
		<li data-tab-target="#live" class="tab">LIVE</li>
	</ul>

	<div class="tab-content">
    <div class="overview_box">
      <div id="overview" data-tab-content class="active overview">
        <h1>NOME TORNEO</h1>
        <img src="prefabs/games_banner_index/r6Wallpaper.jpg" alt="PLACEHOLDER_IMG">
        <p>This is the tournament's home</p>
        <br></br>
      </div>
      <!-- Tab links -->
      <div class="sub-tab">
        <label for="Details" class="tablinks active" onclick="openTab(event, 'Details-content')">Details</label>
        <label for="Rules" class="tablinks" onclick="openTab(event, 'Rules-content')">Rules</label>
        <label for="Prizes" class="tablinks" onclick="openTab(event, 'Prizes-content')">Prizes</label>
        <label for="Schedule" class="tablinks" onclick="openTab(event, 'Schedule-content')">Schedule</label>
        <label for="Contact" class="tablinks" onclick="openTab(event, 'Contact-content')">Contact</label>
        <button class="keepaway" id="Details">Details</button>
        <button class="keepaway" id="Rules">Rules</button>
        <button class="keepaway" id="Prizes">Prizes</button>
        <button class="keepaway" id="Schedule">Schedule</button>
        <button class="keepaway" id="Contact">Contact</button>
      </div>
      <!-- Tab content -->
      <div id="Details-content" class="tabcontent">
        <h3>Details</h3>
        <p>some details about the tournament.</p>
      </div>

      <div id="Rules-content" class="tabcontent">
        <h3>Rules</h3>
        <p>tournament's rules.</p>
      </div>

      <div id="Prizes-content" class="tabcontent">
        <h3>Prizes</h3>
        <img src="prefabs/games_banner_index/paladinsWallpaper.jpg" alt="PRIZE_PLACEHOLDER">
        <p>tournament's prizes.</p>
      </div>

      <div id="Schedule-content" class="tabcontent">
        <h3>Schedule</h3>
        <p>tournament's Schedule.</p>
      </div>

      <div id="Contact-content" class="tabcontent">
        <h3>Contact</h3>
        <p>tournament's Contacts.</p>
      </div>
    </div>

    <div id="partecipants" data-tab-content>
      <h1>PARTECIPANTS</h1>
      <p>Some information about tournament's partecipants</p>
    </div>
    <div id="brakets" data-tab-content>
      <h1>BRACKETS</h1>
      <p>Let me tell you about our staff</p>
    </div>
    <div id="stats" data-tab-content>
      <h1>STATS</h1>
      <p>Give a look to tournament stats</p>
    </div>
		<div id="live" data-tab-content>
      <h1>LIVE</h1>
      <p>there will be implemented live stream</p>
    </div>
  </div>
</body>
</html>
