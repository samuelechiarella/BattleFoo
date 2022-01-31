<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/createTournament.css">
<script src="js/createTournament.js"></script>
</head>
<body>
    <!-- Create Tournament Panel -->
    <div class="create-tournament-tabs do-not-hide-create-tournament">
      <input type='radio' id='r1' name='t' class="create-tournament do-not-hide-create-tournament" checked><!--
      --><label class="create-tournament-label first-create-tournament-label do-not-hide-create-tournament" for='r1'>Category</label><!--
      --><div class="create-tournament-content do-not-hide-create-tournament">
          <div class="category do-not-hide-create-tournament">
            <input id="gameTitle" type="text" class="do-not-hide-create-tournament" placeholder="Type a title..">
            <button id="searchGame" class="do-not-hide-create-tournament">Search</button>
            <div class="games-list do-not-hide-create-tournament">
              <table class="do-not-hide-create-tournament">
                <thead class="do-not-hide-create-tournament">
      	          <tr class="do-not-hide-create-tournament">
      	            <th class="do-not-hide-create-tournament"></th>
      	            <th class="do-not-hide-create-tournament">Title</th>
                    <th class="do-not-hide-create-tournament">Genre</th>
      	          </tr>
                </thead>
                <tbody class="do-not-hide-create-tournament">
                	<jstl:forEach items="${gamesList}" var="game">
                  		<tr class="do-not-hide-create-tournament" onclick="setGameName('${game.name}')">
    		        		<td class="do-not-hide-create-tournament"><img alt="${game.name}" src="${game.logo}" class="do-not-hide-create-tournament"></td>
    		        		<td class="do-not-hide-create-tournament">${game.name}</td>
                    		<td class="do-not-hide-create-tournament">${game.genre}</td>
  		            	</tr>
  		        	</jstl:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div><!--
      --><input type='radio' id='r2' name='t' class="create-tournament do-not-hide-create-tournament"><!--
      --><label class="create-tournament-label do-not-hide-create-tournament" for='r2'>Information</label><!--
      --><div class="create-tournament-content do-not-hide-create-tournament">
          <div class="information do-not-hide-create-tournament">
            <input type="text" id="createTournamentName" class="do-not-hide-create-tournament" placeholder="Tournament name">
            <input type="text" id="createTournamentDate" class="do-not-hide-create-tournament" placeholder="mm/dd/yyyy">
            <input type="number" id="numOfAttendees" class="do-not-hide-create-tournament" min="8" max="32">
            <div class="tournament-banner do-not-hide-create-tournament">
              <img id="sponsor-banner-img" class="do-not-hide-create-tournament">
            </div>
            
            <label class="do-not-hide-create-tournament" for="bannerSponsor">Sponsor Banner</label>
		    <input id="bannerSponsor" class="do-not-hide-create-tournament" type="file" accept="image/*" onchange="getSponsorBanner(this)">
            
            <div class="tournament-banner do-not-hide-create-tournament">
              <img id="tournament-banner-img" class="do-not-hide-create-tournament">
            </div>
            
            <label class="do-not-hide-create-tournament" for="tournamentBanner">Tournament Banner</label>
		    <input id="tournamentBanner" class="do-not-hide-create-tournament" type="file" accept="image/*" onchange="getTournamentBanner(this)">
            
          </div>
        </div><!--
      --><input type='radio' id='r3' name='t' class="create-tournament do-not-hide-create-tournament"><!--
      --><label class="create-tournament-label last-create-tournament-label do-not-hide-create-tournament" for='r3'>Additional Information</label><!--
      --><div class="create-tournament-content do-not-hide-create-tournament">
          <div class="additional-information do-not-hide-create-tournament">
            <textarea id="createTournamentDescription" class="do-not-hide-create-tournament" placeholder="Description"></textarea>
            <textarea id="createTournamentRules" class="do-not-hide-create-tournament" placeholder="Rules"></textarea>
            <textarea id="createTournamentSchedule" class="do-not-hide-create-tournament" placeholder="Schedule"></textarea>
            <div class="create-tournament-create-cancel-btns do-not-hide-create-tournament">
	            <button id="create-tournament-create-button" class="do-not-hide-create-tournament" onclick="createTournament()">Create</button>
	            <button id="create-tournament-cancel-button" class="do-not-hide-create-tournament" onclick="closeCreateTournament()">Cancel</button>
            </div>
          </div>
        </div><!--
      --><div id='createTournamentSlider' class="do-not-hide-create-tournament"></div>
    </div>
</body>
</html>