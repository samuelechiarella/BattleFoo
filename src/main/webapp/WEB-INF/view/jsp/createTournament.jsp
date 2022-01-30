<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <!-- Create Tournament Panel -->
    <div class='create-tournament-tabs do-not-hide'>
      <input type='radio' id='r1' name='t' class="create-tournament do-not-hide" checked><!--
      --><label class="create-tournament-label first-create-tournament-label do-not-hide" for='r1'>Category</label><!--
      --><div class='create-tournament-content do-not-hide'>
          <div class="category do-not-hide">
            <input id="gameTitle" type="text" class="do-not-hide" placeholder="Type a title..">
            <button id="searchGame" class="do-not-hide">Search</button>
            <div class="games-list do-not-hide">
              <table class="do-not-hide">
                <thead class="do-not-hide">
      	          <tr class="do-not-hide">
      	            <th class="do-not-hide"></th>
      	            <th class="do-not-hide">Title</th>
                    <th class="do-not-hide">Genre</th>
      	          </tr>
                </thead>
                <tbody class="do-not-hide">
                	<jstl:forEach items="${gamesList}" var="game">
                  		<tr class="do-not-hide" onclick="setGameName('${game.name}')">
    		        		<td class="do-not-hide"><img alt="${game.name}" src="${game.logo}" class="do-not-hide"></td>
    		        		<td class="do-not-hide">${game.name}</td>
                    		<td class="do-not-hide">${game.genre}</td>
  		            	</tr>
  		        	</jstl:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div><!--
      --><input type='radio' id='r2' name='t' class="create-tournament do-not-hide"><!--
      --><label class="create-tournament-label do-not-hide" for='r2'>Information</label><!--
      --><div class='create-tournament-content do-not-hide'>
          <div class="information do-not-hide">
            <input type="text" id="createTournamentName" class="do-not-hide" placeholder="Tournament name">
            <input type="text" id="createTournamentDate" class="do-not-hide" placeholder="mm/dd/yyyy">
            <div class="tournament-banner do-not-hide">
              <img class="do-not-hide">
            </div>
            <button id="bannerSponsor" class="do-not-hide">Sponsor</button>
            <div class="tournament-banner do-not-hide">
              <img class="do-not-hide">
            </div>
            <button id="bannerTournament" class="do-not-hide">Banner</button>
          </div>
        </div><!--
      --><input type='radio' id='r3' name='t' class="create-tournament do-not-hide"><!--
      --><label class="create-tournament-label last-create-tournament-label do-not-hide" for='r3'>Additional Information</label><!--
      --><div class='create-tournament-content do-not-hide'>
          <div class="additional-information do-not-hide">
            <textarea id="createTournamentDescription" class="do-not-hide" placeholder="Description"></textarea>
            <textarea id="createTournamentRules" class="do-not-hide" placeholder="Rules"></textarea>
            <textarea id="createTournamentSchedule" class="do-not-hide" placeholder="Schedule"></textarea>
            <div class="create-tournament-create-cancel-btns do-not-hide">
	            <button id="create-tournament-create-button" class="do-not-hide" onclick="createTournament()">Create</button>
	            <button id="create-tournament-cancel-button" class="do-not-hide" onclick="closeCreateTournament()">Cancel</button>
            </div>
          </div>
        </div><!--
      --><div id='createTournamentSlider' class="do-not-hide"></div>
    </div>
</body>
</html>