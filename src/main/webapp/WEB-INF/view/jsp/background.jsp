<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="resources.jsp"></jsp:include>
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
		      <a href="/BrowseTournament"><label for="browse">Browse<br>Tournaments</label></a>
		    </div>
		    
		    <!-- ONLY IF THE USER IS LOGGED -->
		    <jstl:if test="${not empty loggedUser}">
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
			    <div class="side-elem profile-logout-buttons sidebar-log-buttons">
			      <a id="profileButton" href="#">${loggedUser}</a>
			      <a id="logoutButton" href="#" onclick="logout()">Logout</a>
			    </div>
		    </jstl:if>
		    <!-- END IF -->
		    
		    <!-- ONLY IF THE USER IS NOT LOGGED -->
		    <jstl:if test="${empty loggedUser}">
			    <div class="side-elem login-signup-buttons sidebar-log-buttons">
			      <a id="loginButton" href="#" onclick="openLoginSignup('log-in')">Log In</a>
			      <a id="signupButton" href="#" onclick="openLoginSignup('sign-up')">Sign Up</a>
			    </div>
		    </jstl:if>
		</div>
		
		<!-- Organize Tournaments side panel -->
		<div id="organizeTournamentsBackside" class="sidepanel">
			<a href="javascript:void(0)" class="closebtn" onclick="closeBack()">&lt;</a>
			<jstl:choose>
				<jstl:when test="${not empty loggedManager}">
					<div class="backside-list">
						<!-- lista organizzazioni -->
						<jstl:forEach items="${organizationsList}" var="org">
						 	<div class="side-elem">
								<span onclick="openOrganizationPage('${org.organizationId}','${org.creatorId}')">${org.organizationName}</span>
							</div>
						</jstl:forEach>
					</div>
					<a href="#" class="not-move" id="newOrganizationButton" onclick="openCreateOrganizationSidebar()">+ New Organization</a>
				</jstl:when>
				<jstl:otherwise>
					<div class="side-elem">
						<span id="sign-up-manager" onclick="openLoginSignup('sign-up-manager')">Sign up as<br>Manager</span>
					</div>
				</jstl:otherwise>
			</jstl:choose>
		</div>
		
		<!-- Manage Teams side panel -->
		<div id="manageTeamsBackside" class="sidepanel">
			<a href="javascript:void(0)" class="closebtn" onclick="closeBack()">&lt;</a>
			<div id="teams-list" class="backside-list">
			<jstl:forEach items="${teamsList}" var="team">
				<div class="team-logo">
					<div class="tooltip">
						<img id="${team.leaderId}"  src="${team.logo}" onclick="openTeamPage('${team.teamName}')">
						<span class="tooltiptext">${team.teamName}</span>
					</div>
				</div>
			</jstl:forEach>
			</div>
			<a href="#" class="not-move" id="newTeamButton" onclick="openCreateTeamSidebar()">+ New Team</a>
		</div>

		<!-- Create Organization Panel-->
		<div class="side-panel side-panel-create-organization">
		  <div class="writings">
		    <h1>Create Organization</h1>
		  </div>
		  <form action="">
		    <div class="side-panel-form">
		      <div class="writings form-elem popup">
		        <label for="nicknameOrganization">Nickname</label>
		        <input id="nicknameOrganization" type="text">
		      </div>
		      <div class="form-elem organization-banner">
		        <img id="img-banner">
		        <label class="label-button" for="choose-banner">Banner</label>
		        <input id="choose-banner" type="file" accept="image/*" onchange="getOrgImg(this)">
		      </div>
		      <div class="form-elem btns">
		        <label class="label-button" for="organization-create-btn" onclick="storeOrganization()">Create</label>
		        <label class="label-button" for="organization-cancel-btn" onclick="closeCreateOrganization()">Cancel</label>
		        <input id="organization-create-btn" type="button">
		        <input id="organization-cancel-btn" type="button">
		      </div>
		    </div>
		  </form>
		</div>

		<!-- Create Team Panel-->
		<div class="side-panel side-panel-create-team">
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
		        <img id="img-logo">
		        <label class="label-button" for="choose-img">Logo</label>
		        <input id="choose-img" type="file" accept="image/*" onchange="getImg(this)">
		      </div>
		      <div class="form-elem btns">
		        <label class="label-button" for="team-create-btn" onclick="storeTeam()">Create</label>
		        <label class="label-button" for="team-cancel-btn" onclick="closeCreateTeam()">Cancel</label>
		        <input id="team-create-btn" type="button">
		        <input id="team-cancel-btn" type="button">
		      </div>
		    </div>
		  </form>
		</div>
		
		<!-- Log in -->  
		<div class="log-in do-not-hide popup">
			<a class="close-log-in-sign-up do-not-hide" onclick="closeLoginSignup('log-in')">&times;</a>
			<span class="popuptext" id="invalidUsernamePassword">Invalid Username or Password!</span>
			<h1 class="do-not-hide">Log In</h1>
			<input type="text" id="loginUsername" class="do-not-hide clear-log-in" name="loginUsername" placeholder="Username or Email">
			<input type="password" id="loginPassword" class="do-not-hide clear-log-in" name="loginPassword" placeholder="Password">
			<button class="do-not-hide" onclick="login()">Submit</button>
		</div>
		
		<!-- Sign up -->
		<div class="sign-up do-not-hide">
		    <a class="close-log-in-sign-up do-not-hide" onclick="closeLoginSignup('sign-up')">&times;</a>
		    <h1 class="do-not-hide">Sign Up</h1>
		    <input type="text" id="signupUsername" class="do-not-hide clear-sign-up" name="signupUsername" placeholder="Username">
	    	<input type="text" id="firstname" class="do-not-hide clear-sign-up" name="firstname" placeholder="Firstname">
	    	<input type="text" id="lastname" class="do-not-hide clear-sign-up" name="lastname" placeholder="Lastname">
	    	<input type="text" id="email" class="do-not-hide clear-sign-up" name="email" placeholder="Email">
	    	<input type="password" id="signupPassword" class="do-not-hide clear-sign-up" name="signupPassword" placeholder="Password">
	    	<input type="password" id="confirmPassword" class="do-not-hide clear-sign-up" name="confirmPassword" placeholder="Confirm Password">
	    	<button class="do-not-hide" onclick="signup()">Submit</button>
	 	</div>
	 	
	 	<!-- Sign up as Manager -->
		<div class="sign-up-manager do-not-hide">
		    <a class="close-log-in-sign-up clear-sign-up-manager do-not-hide" onclick="closeLoginSignup('sign-up-manager')">&times;</a>
		    <h1 class="do-not-hide">Confirm your password</h1>
		    <input type="password" id="signupManagerPassword" class="do-not-hide clear-sign-up-manager" name="signupPassword" placeholder="Password">
	    	<button class="do-not-hide" onclick="signupAsManager()">Submit</button>
	 	</div>
  		
	</body>
</html>