<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/BrowseTournament.css">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="background.jsp"></jsp:include>

<div class="PageTitle">
	<h1>BROWSE	TOURNAMENTS</h1>
	
	<div class="FilterArea">
		<input type="text" class="FilterInput">
		<button class="FilterBtn">Search</button>
	</div>
	
	<div class="browse-tournament-table">
		<table>
			<tr>
		  		<th>Category</th>
		    	<th>Title</th>
				<th>Date</th>
			</tr>
			<tr>
				<td>*INSERT GAME LOGO HERE*</td>
			  	<td>*INSERT TOURNAMENT TITLE HERE*</td>
			  	<td>*INSERT TOURNAMENT DATE HERE*</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>