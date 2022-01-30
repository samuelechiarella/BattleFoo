<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chat</title>

    <link type="text/css" href="css/testChat.css" rel="stylesheet" />
</head>

<body>

<div id="chat">
    <div id="chat-messages"></div>

    <form id="chat-form" method="post">
        <input type="text" name="message" placeHolder="Enter a message..." maxlength="500" autoComplete="off" />
        <button type="submit">Send</button>
    </form>
</div>

<script type="text/javascript" src="js/testChat.js"></script>

</body>
</html>