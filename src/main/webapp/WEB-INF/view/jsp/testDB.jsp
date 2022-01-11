<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jstl:forEach items="${allGames}" var="game">
<h1>${game.name} - ${game.genre}</h1>
</jstl:forEach>
</body>
</html>