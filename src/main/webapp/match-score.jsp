<%--@elvariable id="currentMatch" type="com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Match score</title>
</head>
<body>

<div>

    <p>first: ${currentMatch.getFirstPlayer().getName()}</p>
    <p>score: ${currentMatch.getGameScore().getFirstPlayerScore().getPointCode()}</p>

    <form method="post">

        <button class="btn" name="winner" value="first">Take score #1</button>

    </form>

</div>
<div>
    <p>second: ${currentMatch.getSecondPlayer().getName()}</p>
    <p>score: ${currentMatch.getGameScore().getSecondPlayerScore().getPointCode()}</p>

    <form method="post">

        <button class="btn" name="winner" value="second">Take score #2</button>

    </form>

</div>

</body>
</html>
