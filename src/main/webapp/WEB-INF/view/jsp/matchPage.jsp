<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/matchPage.css">
<script type="text/javascript" src="js/matchPage.js"></script>
</head>
<body>
<jsp:include page="background.jsp"></jsp:include>
  <div class="tournament-match">
    <div class="top-bar">
      <div class="match-team">
        <div class="team1">
        
          <jstl:forEach items="${tournamentAttendees}" var="team">
          	<jstl:if test="${team.teamName eq match.firstTeam}">
          		<img src="${team.logo}" alt="team1Logo">
          	</jstl:if>
          </jstl:forEach>
          
          <span>${match.firstTeam}</span>
        </div>
      </div>
      <img src="images/sidebar-icons/vsLogo.png" alt="vs">
      <div class="match-team">
        <div class="team2">
          
          <jstl:forEach items="${tournamentAttendees}" var="team">
          	<jstl:if test="${team.teamName eq match.secondTeam}">
          		<img src="${team.logo}" alt="team2Logo">
          	</jstl:if>
          </jstl:forEach>
          
          <span>${match.secondTeam}</span>
        </div>
      </div>
    </div>

    <div class="match-chat">
      <div class="match-text">
        <textarea id="textAreaChat" readonly></textarea>
        <input id="messageToSend" type="text" placeholder="Type something...">
      </div>
      <jstl:if test="${not empty loggedUser}">
      	<jstl:choose>
      	  <jstl:when test="${not empty loggedManager}">
      	  		<jstl:set var="intoTheStaff" value="false"></jstl:set>
      	  		<jstl:forEach items="${matchStaff}" var="staffMember">
      	  			<jstl:if test="${staffMember.managerId eq loggedManager.managerId}">
      	  			<jstl:set var="intoTheStaff" value="true"></jstl:set>
      	  				<div class="sendbox">
				          <button id="sendMessageBtn" onclick="sendMessage()">Send</button>
				      	</div>
      	  			</jstl:if>
      	  		</jstl:forEach>
      	  		
      	  		<jstl:if test="${not intoTheStaff}">
      	  			<jstl:forEach items="${matchAttendees}" var="attendee">
	      	  			<jstl:if test="${attendee.playerId eq loggedPlayer.playerId}">
	      	  				<div class="sendbox">
					          <button id="sendMessageBtn" onclick="sendMessage()">Send</button>
					      	</div>
	      	  			</jstl:if>
      	  			</jstl:forEach>
      	  		</jstl:if>
      	  		
	      </jstl:when>
	      
	      <jstl:otherwise>
	      
	      		<jstl:forEach items="${matchAttendees}" var="attendee">
      	  			<jstl:if test="${attendee.playerId eq loggedPlayer.playerId}">
      	  				<div class="sendbox">
				          <button id="sendMessageBtn" onclick="sendMessage()">Send</button>
				      	</div>
      	  			</jstl:if>
      	  		</jstl:forEach>
      	  		
      	  	</jstl:otherwise>
      	  </jstl:choose>
      </jstl:if>
    </div>
  </div>

</body>
</html>