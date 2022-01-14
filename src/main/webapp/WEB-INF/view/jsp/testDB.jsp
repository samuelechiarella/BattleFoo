<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style type="text/css">
body{
	background-color: #373737;
}

table {
  margin: 5em;
  border-collapse: collapse;
  width: 100%;
  font-family: sans-serif;
}

th {
	font-size: 3em;
	color: #DA4D0B;
}

td {
	font-size: 1em;
	color: #E8E8E8;
}

th, td {
  padding: 8px;
  text-align: left;
  border-bottom: 1px solid #DDD;
}

tr:hover {background-color: #585858;}

.logo {
	widht: 10em;
	height: 10em;
}

</style>
</head>
<body>
<table>
	<thead>
		<tr>
			<th>Game Name</th>
			<th>Game Genre</th>
		</tr>
	</thead>
	<tbody>
		<jstl:forEach items="${allGames}" var="game">
		<tr>
			<td><h3>${game.name}</h3></td>
			<td><h3>${game.genre}</h3></td>
		</tr>
		</jstl:forEach>
	</tbody>
</table>

<table>
	<thead>
		<tr>
			<th>Player Nickname</th>
			<th>Player ID</th>
		</tr>
	</thead>
	<tbody>
		<jstl:forEach items="${allPlayers}" var="player">
		<tr>
			<td><h3>${player.nickname}</h3></td>
			<td><h3>${player.playerId}</h3></td>
		</tr>
		</jstl:forEach>
	</tbody>
</table>

<table>
	<thead>
		<tr>
			<th>Player Nickname</th>
			<th>Player ID</th>
		</tr>
	</thead>
	<tbody>
		<jstl:forEach items="${allManagers}" var="manager">
		<tr>
			<td><h3>${manager.nickname}</h3></td>
			<td><h3>${manager.managerId}</h3></td>
		</tr>
		</jstl:forEach>
	</tbody>
</table>

<table>
	<thead>
		<tr>
			<th>Team Name</th>
			<th>Leader ID</th>
			<th>Logo</th>
		</tr>
	</thead>
	<tbody>
		<jstl:forEach items="${teamsList}" var="team">
		<tr>
			<td><h3>${team.teamName}</h3></td>
			<td><h3>${team.leaderId}</h3></td>
			<td><img class="logo" src="${team.logo}"></td>
		</tr>
		</jstl:forEach>
	</tbody>
</table>

</body>
</html>