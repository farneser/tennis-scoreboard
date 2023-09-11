<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create a new match</title>
    <link rel="stylesheet" type="text/css" href="css/new-match.css">
    <link rel="stylesheet" type="text/css" href="css/footer-styles.css">
    <link rel="stylesheet" type="text/css" href="css/button-styles.css">
</head>
<body>

<form action="new-match" method="post">

    <label>
        First player name
        <input name="player1" required>
    </label>
    <br>
    <label>
        Second player name
        <input name="player2" required>
    </label>
    <br>
    <label>
        Count of sets
        <select required name="setsCount">
            <option>3</option>
            <option>5</option>
        </select>
    </label>
    <input type="submit" value="Start">

</form>
<footer>
    <button onclick="goUpOneLevel()" class="btn btn-back">Go Back</button>
</footer>
<script src="scripts/goUpOneLevel.js"></script>
</body>
</html>
