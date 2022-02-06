<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<li data-tab-target="#live" class="tab">LIVE</li>
	</ul>

	<div class="tab-content">
    <div class="overview_box">
      <div id="overview" data-tab-content class="active overview">
        <h1>${tournament.name}</h1>
        <img id="imgLogo" src="${tournament.logo}">
      </div>
      <jstl:if test="${not empty loggedPlayer}">
	      <jstl:set var="teamFound" value="false"></jstl:set>
	      <jstl:forEach items="${tournamentAttendees}" var="team">
		      <jstl:if test="${team.leaderId eq loggedPlayer.playerId}">
		      	<jstl:set var="teamFound" value="true"></jstl:set>
		      	<jstl:set var="teamSigned" value="${team.teamName}"></jstl:set>
		      </jstl:if>
	      </jstl:forEach>
	      
	      <jstl:choose>
	      <jstl:when test="${not teamFound}">
		      <button id="signupTournamentBtn" onclick="openSignupTournament()">Sign Up Tournament</button>
		      <div class="signup-tournament">
		     	<img id="teamChoosedForSignup">
		      	<div class="dropdown">
					<button class="dropbtn">My Teams</button>
						<div class="dropdown-content">
							<jstl:forEach items="${teamsList}" var="team">
								<jstl:if test="${team.leaderId eq loggedPlayer.playerId}">
							    	<a href="#" onclick="setImageTeamChoosed('${team.logo}','${team.teamName}')">${team.teamName}</a>
						    	</jstl:if>
					    	</jstl:forEach>
					   	</div>
			  	</div>
			  	<div class="confirm-cancel-signup-tournament-btns">
				  	<button id="confirmSignupTournament" onclick="signupTeam()">Confirm</button>
				  	<button id="closeSignupTOurnament" onclick="closeSignupTournament()">Cancel</button>
			  	</div>
			  </div>
		  </jstl:when>
			<jstl:otherwise>
				<button id="leaveTournamentBtn" onclick="leaveTournament('${teamSigned}')">Leave Tournament</button>	
			</jstl:otherwise>
		  
		  </jstl:choose>
	  </jstl:if>
	  
	  
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
        <textarea readonly>${tournament.description}</textarea>
      </div>

      <div id="Rules-content" class="tabcontent">
        <h3>Rules</h3>
        <textarea readonly>${tournament.rules}</textarea>
      </div>
      
      <div id="Schedule-content" class="tabcontent">
        <h3>Schedule</h3>
        <textarea readonly>${tournament.schedule}</textarea>
      </div>

      <div id="Prizes-content" class="tabcontent">
      	
      	<div class="tournament-match">
      		
      		<jstl:if test="${not empty match}">
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
			</jstl:if>
		    
		    <div class="match-chat">
		      <div class="match-text">
		        <textarea id="textAreaChat" readonly></textarea>
		        <input id="messageToSend" type="text" placeholder="Type something...">
		      </div>
		      <div class="sendbox">
		          <button id="sendMessageBtn" onclick="sendMessage()">Send</button>
		      </div>
		    </div>
		</div>
      </div>
      
    </div>

    <div id="partecipants" data-tab-content>
      <h1>PARTECIPANTS</h1>
     
      <div class="partecipants-table">
		<table>
			<tr>
		  		<th>Team Logo</th>
		    	<th>Team Name</th>
			</tr>
			<jstl:forEach items="${tournamentAttendees}" var="team">
				<tr onclick="openTeamPage('${team.teamName}')">
					<td><img src="${team.logo}"></td>
			  		<td>${team.teamName}</td>
				</tr>
			</jstl:forEach>
		</table>
	  </div>
	  
    </div>
    
    <div id="brakets" data-tab-content>
      <h1>BRACKETS</h1>
      <p>Let me tell you about our staff</p>
    </div>
    	
	<div id="live" data-tab-content>
		<div class="h1-text"><h1>LIVE</h1></div>
	    <div class="live-content">
	      	<!-- Load the Twitch embed JavaScript file -->
		    <script src="https://embed.twitch.tv/embed/v1.js"></script>
		    <!-- Create a Twitch.Embed object that will render within the "twitch-embed" element -->
		    <!-- Add a placeholder for the Twitch embed -->
		    <div id="twitch-embed">
		    	<script type="text/javascript" id="liveMediaPlayer">
	        new Twitch.Embed("twitch-embed", {
	        width: 854,
	        height: 480,
	        channel: "nambon", <!-- ADD HERE THE ORGANIZATION CHANNEL BY JUST TYPING THE CHANNEL'S NAME -->
	        // Only needed if this page is going to be embedded on other websites
	        parent: ["embed.example.com", "othersite.example.com"]
	          });
	        </script>
	        
	        <div class="liveBox">
		        <textarea id="liveDescription" readonly>Inserisci nel campo sottostante il nome del tuo account Twitch.tv, successivamente clicca il bottone ed infine ricarica la pagina!</textarea>
		       	<input type="text" id="liveInput">
		        <button id="addTwitchAccount">Confirm</button>
		    </div>
		    
		    <div class="invitationLink">
		        <label for="generateInvitationLinkBtn">Genera Link Invito! </label>
				<button id="generateInvitationLinkBtn">Generate</button>
			</div>
		    </div>
		 </div>
    </div>
  </div>
</body>
</html>
