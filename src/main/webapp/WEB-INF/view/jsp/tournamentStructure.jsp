<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/tournamentStructure.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="js/tournamentStructure.js" defer></script>
</head>
<body>
<jsp:include page="background.jsp"></jsp:include>
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
        <h1>${tournament.name}</h1>
        <img src="images/games_banner_index/r6Wallpaper.jpg" alt="PLACEHOLDER_IMG">
        <p>This is the tournament's home</p>
        <br></br>
      </div>
      <!-- Tab links -->
      <div class="sub-tab">
        <label for="Details" class="tablinks active" onclick="openTab(event, 'Details-content')">Details</label>
        <label for="Rules" class="tablinks" onclick="openTab(event, 'Rules-content')">Rules</label>
        <label for="Prizes" class="tablinks" onclick="openTab(event, 'Prizes-content')">My Match</label>
        <label for="Schedule" class="tablinks" onclick="openTab(event, 'Schedule-content')">Schedule</label>
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
      
      <div id="Schedule-content" class="tabcontent">
        <h3>Schedule</h3>
        <p>tournament's Schedule.</p>
      </div>

      <div id="Prizes-content" class="tabcontent">
      	<div class="tournament-match">
		    <div class="top-bar">
		      <div class="match-team">
		        <div class="team1">
		          <img src="https://img.search.brave.com/8yjDaafA2sZyO-GyI8eTGhA1Jovk5ZmNcfCL0Uj4p7E/rs:fit:474:225:1/g:ce/aHR0cHM6Ly90c2Uy/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC52/V2VEam5YSUZUdkJG/UGxIVW0zLUdBSGFI/YSZwaWQ9QXBp" title="team1" alt="team1Logo">
		        </div>
		      </div>
		      <div class="vs-logo">
		      	<img src="https://thumbs.dreamstime.com/b/v-s-versus-letter-logo-vs-letters-transparent-background-vector-illustration-competition-confrontation-v-s-versus-letter-159953254.jpg" alt="">
		      </div>
		      <div class="match-team">
		        <div class="team2">
		          <img src="https://img.search.brave.com/PmYFjP68SimuSEmY9LCGZ2ENmH-6gFRFEo1PUoqvh9Q/rs:fit:474:225:1/g:ce/aHR0cHM6Ly90c2Ux/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC5q/VUNoMEstWVhjTGl6/MGhzR2tySmtnSGFI/YSZwaWQ9QXBp" title="team2" alt="team2Logo">
		        </div>
		      </div>
		    </div>
		
		    <div class="match-chat">
		      <div class="match-text">
		        <textarea readonly>asdasdasasd asd</textarea>
		        <input type="text" placeholder="Type something...">
		      </div>
		      <div class="sendbox">
		          <button id="sendMessageBtn">Send</button>
		          <button id="chatRefresh"><img src="https://img.icons8.com/external-kmg-design-basic-outline-kmg-design/2x/external-refresh-arrows-kmg-design-basic-outline-kmg-design.png" alt="refresh"></button>
		      </div>
		    </div>
		</div>
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
