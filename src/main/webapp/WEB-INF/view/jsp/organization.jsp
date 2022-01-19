<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
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
      <div id="org-info">
          <div id="org-description" contenteditable="false">
              Add a description for your Organization
          </div>
          <button id="submitDescription">Done</button>
          <button id="editDescription">Edit</button>
      </div>
    </div>
    <div id="tournaments" data-tab-content>
      <h1>TOURNAMENTS</h1>
      <p>Some information about tournaments</p>
    </div>
    <div id="staff" data-tab-content>
      <h1>STAFF</h1>
      <p>Let me tell you about our staff</p>
      <table class="staffTable">
          <tr>
              <th></th>
            <th>Name</th>
          </tr>
          <jstl:forEach items="staffMembers" var="member">
          <tr>
              <td>${member.profilePicture}</td>
            <td>${member.username}</td>
          </tr>
            </jstl:forEach>
      </table>
      <input type="text" id="newMemebr">
      <button onclick="addMember()">Add Member</button>
    </div>
    <div id="live" data-tab-content>
      <h1>LIVE</h1>
      <p>there will be implemented live stream</p>
      <!-- Add a placeholder for the Twitch embed -->
      <div id="twitch-embed"></div>
    
      <!-- Load the Twitch embed JavaScript file -->
      <script src="https://embed.twitch.tv/embed/v1.js"></script>
      <!-- Create a Twitch.Embed object that will render within the "twitch-embed" element -->
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
</body>
</html>