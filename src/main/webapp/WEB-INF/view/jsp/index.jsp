<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
<jsp:include page="background.jsp"></jsp:include>
<!-- Slider -->
  <div class="slider">
    <div class="slides">
      <input type="radio" name="radio-btn" id="radio1">
      <input type="radio" name="radio-btn" id="radio2">
      <input type="radio" name="radio-btn" id="radio3">
      <input type="radio" name="radio-btn" id="radio4">
      <input type="radio" name="radio-btn" id="radio5">

      <div class="slide first">
        <img src="images/games_banner_index/fortniteWallpaper.jpeg">
      </div>
      <div class="slide">
        <img src="images/games_banner_index/lolWallpaper.jpg">
      </div>
      <div class="slide">
        <img src="images/games_banner_index/paladinsWallpaper.jpg">
      </div>
      <div class="slide">
        <img src="images/games_banner_index/r6Wallpaper.jpg">
      </div>
      <div class="slide">
        <img src="images/games_banner_index/tf2Wallpaper.jpg">
      </div>

      <div class="navigation-auto">
        <div class="auto-btn1"></div>
        <div class="auto-btn2"></div>
        <div class="auto-btn3"></div>
        <div class="auto-btn4"></div>
        <div class="auto-btn5"></div>
      </div>
    </div>

    <div class="navigation-manual">
      <label for="radio1" class="manual-btn"></label>
      <label for="radio2" class="manual-btn"></label>
      <label for="radio3" class="manual-btn"></label>
      <label for="radio4" class="manual-btn"></label>
      <label for="radio5" class="manual-btn"></label>
    </div>
  </div>

  <div class="tournaments">
    <h2>Random:</h2>
    <div class="list-tournaments">
    	<jstl:forEach items="${randomTournaments}" var="tournament">
      		<div class="tournament">
      			<img src="${tournament.logo}" title="${tournament.name}" onclick="openTournamentPage('${tournament.tournamentId}')">
      		</div>
      	</jstl:forEach>
      </div>
  </div>

</body>
</html>