<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/organization.css">
<script src="js/organization.js"></script>
<jsp:include page="resources.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="background.jsp"></jsp:include>
    
    <ul class="tabs">
        <li id="homeTab" class="active tab" onclick="setActive('homeTab')">HOME</li>
        <li id="tournamentsTab" class="tab" onclick="setActive('tournamentsTab')">TOURNAMENTS</li>
        <li id="staffTab" class="tab" onclick="setActive('staffTab')">STAFF</li>
        <li id="liveTab" class="tab" onclick="setActive('liveTab')">LIVE</li>
    </ul>
    
    <div class="tabs-contents">
	    
	    <div id="homeContent" class="tab-content active">
	      <img id="organizationBanner" src="https://img.search.brave.com/bg8y4g6v_9rMlzYQh1sy-ggkVPPL6uc7P0cm3AWJbMc/rs:fit:600:240:1/g:ce/aHR0cDovL25lcmRy/ZWFjdG9yLmNvbS93/cC1jb250ZW50L3Vw/bG9hZHMvMjAxNC8w/Ny9MZWFndWUtb2Yt/TGVnZW5kcy1CYW5u/ZXIucG5n" alt="Organization Logo">
	      <h1>${organization.organizationName}</h1>
	      <div id="org-info">
	          <div id="org-description">
	              <textarea readonly>Add a description for your Organization</textarea>
	          </div>
	          <button id="submitOrganizationDescription">Done</button>
	          <button id="editOrganizationDescription">Edit</button>
	      </div>
	    </div>
	    
	    <div id="tournamentsContent" class="tab-content">
	      <h1>TOURNAMENTS</h1>
	      <p>Some information about tournaments</p>
	    </div>
	    
	    <div id="staffContent" class="tab-content">
	      <h1>STAFF</h1>
	      <p>Let me tell you about our staff</p>
	      <table class="staffTable">
	          <tr>
	            <th></th>
	            <th>Name</th>
	          </tr>
	          
	          <jstl:forEach items="${staff}" var="member">
		          <tr>
		            <td><img src="${member.profilePicture}"></td>
		            <td>${member.username}</td>
		          </tr>
	           </jstl:forEach>
	           
	      </table>
	      
	      <jstl:if test="${organization.creatorId eq loggedManager.managerId}">
		      <input type="text" id="newMember">
		      <button onclick="addMember()">Add Member</button>
	      </jstl:if>
	      
	    </div>
	    <div id="liveContent" class="tab-content">
	      <h1>LIVE</h1>
	      <p>Here will be implemented live stream</p>
	     
	      <!-- Load the Twitch embed JavaScript file -->
	      <script src="https://embed.twitch.tv/embed/v1.js"></script>
	      <!-- Create a Twitch.Embed object that will render within the "twitch-embed" element -->
	       <!-- Add a placeholder for the Twitch embed -->
	      <div id="twitch-embed">
	      	<script type="text/javascript">
	        new Twitch.Embed("twitch-embed", {
	        width: 854,
	        height: 480,
	        channel: "nambon", <!-- ADD HERE THE ORGANIZATION CHANNEL BY JUST TYPING THE CHANNEL'S NAME -->
	        // Only needed if this page is going to be embedded on other websites
	        parent: ["embed.example.com", "othersite.example.com"]
	          });
	        </script>
	      </div>
	     </div>
  	</div>
</body>
</html>