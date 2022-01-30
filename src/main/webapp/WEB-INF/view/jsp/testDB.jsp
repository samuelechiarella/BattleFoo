<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" 
        integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT" 
        crossorigin="anonymous"></script>
<script type="text/javascript" src="js/testDB.js"></script>
<script src="js/jquery.gracket.min.js"></script>
<style type="text/css">
.g_gracket {
  width: 9999px;
  background-color: #fff;
  padding: 55px 15px 5px;
  line-height: 100%;
  position: relative;
  overflow: hidden;
}

.g_round {
  float: left;
  margin-right: 70px;
}

.g_game {
  position: relative;
  margin-bottom: 15px;
}

.g_gracket h3 {
  margin: 0;
  padding: 10px 8px 8px;
  font-size: 18px;
  font-weight: normal;
  color: #fff
}

.g_team {
  background: #3597AE;
}

.g_team:last-child {
  background: #FCB821;
}

.g_round:last-child {
  margin-right: 20px;
}

.g_winner {
  background: #444;
}

.g_winner .g_team {
  background: none;
}

.g_current {
  cursor: pointer;
  background: #A0B43C!important;
}

.g_round_label {
  top: -5px;
  font-weight: normal;
  color: #CCC;
  text-align: center;
  font-size: 18px;
}
</style>
</head>
<body>

<div class="my_gracket"></div>

</body>
</html>