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
    <h2>Happening Soon:</h2>
    <div class="list-tournaments">
      <div class="tournament"><img src="https://imgr.search.brave.com/Nerqv-diQqQcQdRA4A9oEDUfOB8-OBzFqssgJ1rFTQ8/fit/1080/675/ce/1/aHR0cDovL2dhbWVk/dXN0cmlhLmNvbS93/cC1jb250ZW50L3Vw/bG9hZHMvMjAxOC8w/NS9HQU1FUE9MSVMt/Rk9STklURS0yLTEw/ODB4Njc1LmpwZw"></div>
      <div class="tournament"><img src="https://imgr.search.brave.com/4fF5bgc_A2ZX-fIPLb3FCCr7zGb_N-REuTgRr6ZUJGs/fit/1200/567/ce/1/aHR0cHM6Ly9vcmln/MDAuZGV2aWFudGFy/dC5uZXQvZjMwNy9m/LzIwMTQvMDA1L2Mv/MC90b3JuZW9fbG9s/X3N0YV9jcnV6X2Jh/bm5lcl9ieV9qdWxp/b3R0LWQ3MTBwcWQu/anBn"></div>
      <div class="tournament"><img src="https://imgr.search.brave.com/47XXpa3jd1-O-6ML_dztc0BAnsjsgCugObiUyk2GgHI/fit/1200/720/ce/1/aHR0cHM6Ly9pLnl0/aW1nLmNvbS92aS9X/dTdyaUVsUjM1MC9t/YXhyZXNkZWZhdWx0/LmpwZw"></div>
      <div class="tournament"><img src="https://imgr.search.brave.com/faICdBdYsPKee3dKXAD6HkabJGN1oOV_l_PfFCGsOSM/fit/1200/720/ce/1/aHR0cHM6Ly9pLnl0/aW1nLmNvbS92aS8t/dWFXNEVVN0Z2ay9t/YXhyZXNkZWZhdWx0/LmpwZw"></div>
      <div class="tournament"><img src="https://imgr.search.brave.com/f8sF16aoO-eXcFjOuNnDrojrxxt4-F-l_9iyngpOWHk/fit/460/269/ce/1/aHR0cDovL2kuaW1n/dXIuY29tL0tmcDRM/eU0ucG5n"></div>
    </div>
  </div>

</body>
</html>