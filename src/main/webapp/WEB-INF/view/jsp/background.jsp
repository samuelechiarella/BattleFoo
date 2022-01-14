<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<button class="openbtn" onclick="openMenu()">&#9776; Menu</button>
<!-- Menu -->
<div id="sidePanel" class="sidepanel">
  	<a class="closebtn not-move" onclick="closeMenu()">&times;</a>
    <div id="logo" class="side-elem">
      <img src="images/sidebar-icons/logo_noBG.png">
    </div>
    <div class="side-elem">
      <img id="home" src="images/sidebar-icons/home_icon.png">
      <a href="/"><label for="home">Home</label></a>
    </div>
    <div class="side-elem">
      <img id="browse" src="images/sidebar-icons/browse_tournaments_icon.png">
      <a href="#"><label for="browse">Browse<br>Tournaments</label></a>
    </div>
    
    <!-- ONLY IF THE USER IS LOGGED -->
    <jstl:set value="${loggedUser}" var="user" />
    <jstl:if test="${not empty user}">
    <div class="side-elem">
      <img id="organize" src="images/sidebar-icons/organize_tournaments_icon.png">
      <a href="#"><label for="organize" onclick="openOrganizeTournaments()">Organize<br>Tournaments</label></a>
    </div>
    <div class="side-elem">
      <img id="manage" src="images/sidebar-icons/teamIcon.png">
      <a href="#"><label for="manage" onclick="openManageTeams()">Manage<br>Teams</label></a>
    </div>
    <div class="side-elem">
      <a href="/testDB">Test Database</a>
    </div>
    </jstl:if>
    <!-- END IF -->
    
    <div class="side-elem">
      <a id="loginButton" href="#">Login</a>
    </div>
  	<!-- <a href="#">My Profile</a> compare dopo essersi loggato -->
  	<!-- <a href="#">Logout</a> compare dopo essersi loggato -->
</div>

<!-- Organize Tournaments side panel -->
<div id="organizeTournamentsBackside" class="sidepanel">
	<a href="javascript:void(0)" class="closebtn" onclick="closeBack()">&lt;</a>
	<div class="backside-list">
	<!-- lista organizzazioni -->
	<ul>
		<li><a href="organization">Organizzazione1</a></li>
	</ul>
	</div>
	<a href="#" class="not-move" id="newOrganizationButton">+ New Organization</a>
</div>

<!-- Manage Teams side panel -->
<div id="manageTeamsBackside" class="sidepanel">
	<a href="javascript:void(0)" class="closebtn" onclick="closeBack()">&lt;</a>
	<div id="teams-list" class="backside-list">
	<jstl:forEach items="${teamsList}" var="team">
		<div class="team-logo">
			<div class="tooltip">
				<img id="${team.leaderId}"  src="${team.logo}">
				<span class="tooltiptext">${team.teamName}</span>
			</div>
		</div>
	</jstl:forEach>
	</div>
	<a href="#" class="not-move" id="newTeamButton" onclick="openCreateTeamSidebar()">+ New Team</a>
</div>

<!-- Create Team Panel-->
<div class="side-panel">
  <div class="writings">
    <h1>Create Team</h1>
  </div>
  <form action="">
    <div class="side-panel-form">
      <div class="writings form-elem popup">
        <label for="nickname">Nickname</label>
        <input id="nickname" type="text" name="" value="">
        <span class="popuptext" id="nameTakenPopup">Team name already taken!</span>
        <span class="popuptext" id="insertNamePopup">This field must be filled!</span>
        <span class="popuptext" id="teamNameNotFilledPopup">Team name field is not filled!</span>
        <span class="popuptext" id="constraintsNotSatisfiedPopup">Only letters/numbers/underscores are allowed!</span>
      </div>
      <div class="form-elem">
        <img id="img-logo" src="images/sidebar-icons/insert-picture-icon.png">
        <label class="label-button" for="choose-img">Logo</label>
        <input id="choose-img" type="file" accept="image/*" onchange="getImg(this)">
      </div>
      <div class="form-elem btns">
        <label class="label-button" for="create-btn" onclick="storeTeam()">Create</label>
        <label class="label-button" for="cancel-btn" onclick="closeCreateTeam()">Cancel</label>
        <input id="create-btn" type="button">
        <input id="cancel-btn" type="button">
      </div>
    </div>
  </form>
</div>
</body>
</html>