<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/teamPage.css">
<script type="text/javascript" src="js/teamPage.js"></script>
</head>
<body>
<jsp:include page="background.jsp"></jsp:include>
<jstl:set value="${team}" var="team"/>
	<div class="out-tab">
      <div class="out-tab-logo-name">
        <img id="team-logo" src="${team.logo}" alt="">
        <label for="team-logo">${team.teamName}</label>
      </div>
    </div>

    <div class="tabs">
      <!-- Tab links -->
      <div class="tab">
        <label class="tablinks first-tab" for="description" onclick="openTab(event, 'description-content')">Description</label>
        <label class="tablinks second-tab" for="attendees"  onclick="openTab(event, 'list-attendees')">Attendees</label>
        <button class="keep-away"></button>
        <button class="keep-away"></button>
      </div>

      <!-- Tab content -->
      <div id="description-content" class="tabcontent">
      	<textarea readonly>${team.description}</textarea>
      	<div id="editTeamDescription">
        	<label for="edit"><img src="images/sidebar-icons/editIcon.png" title="Edit Description"></label>
        	<button class="keep-away" id="edit" onclick="setDescriptionEditable()"></button>
      	</div>
      	<div class="apply-cancel-changes hide-apply-changes">
	      <label for="apply-changes" class="label-button" onclick="confirmDescriptionChanges()">Apply</label>
		  <button id="apply-changes" class="keep-away"></button>
		  <label for="cancel-changes" class="label-button" onclick="setNotEditable()">Cancel</label>
		  <button id="cancel-changes" class="keep-away"></button>
	  	</div>
      </div>
      
      <div id="list-attendees" class="tabcontent">
      	<jstl:forEach items="${teamMembersList}" var="player">
		<div class="attendee tooltip">
		  <img id="${player.username}" src="${player.profilePicture}">
		  <span class="tooltiptext">${player.username}</span>
		</div>
		</jstl:forEach>
		<img id="editTeamMembers" class="" onclick="openLoginSignup('edit-team-member')" src="images/sidebar-icons/editIcon.png" title="Edit Members">
      </div>
	  
    </div>
    
    <!-- Edit Team Members -->
	<div class="edit-team-member do-not-hide">
	    <a class="close-log-in-sign-up do-not-hide" onclick="closeLoginSignup('edit-team-member')">&times;</a>
	    <h1 class="do-not-hide">Edit Member</h1>
	    <input type="text" id="newTeamMember" class="do-not-hide" placeholder="New Member Username">
	    <div class="do-not-hide">
	    	<button id="addMember" class="do-not-hide" onclick="insertNewTeamMember()">Add</button>
	    	<button id="deleteMember" class="do-not-hide" onclick="removeTeamMember()">Remove</button>
	    </div>
 	</div>
</body>
</html>