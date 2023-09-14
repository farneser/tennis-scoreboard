<%@ page import="com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Match Score</title>
    <link rel="stylesheet" type="text/css" href="css/match-score.css">
    <link rel="stylesheet" type="text/css" href="css/footer-styles.css">
    <link rel="stylesheet" type="text/css" href="css/button-styles.css">
    <% var currentMatch = (CurrentMatch) request.getServletContext().getAttribute("currentMatch"); %>
</head>
<body>
<header>
    <h1>Match Score</h1>
</header>
<div class="content">
    <% if (currentMatch != null) { %>
    <div class="card">
        <table>
            <caption>Match Score</caption>
            <tr>
                <th>Player 1</th>
                <th>Player 2</th>
            </tr>
            <% for (var set : currentMatch.getSetScores()) { %>
            <tr>
                <td><%= set.getFirstPlayerScore() %>
                </td>
                <td><%= set.getSecondPlayerScore() %>
                </td>
            </tr>
            <% } %>
        </table>
    </div>

    <% if (currentMatch.getWinnerPlayer() == null) { %>
    <div class="card">
        <table>
            <caption>Current Set</caption>
            <tr>
                <th>Player 1</th>
                <th>Player 2</th>
            </tr>
            <tr>
                <td><%= currentMatch.getCurrentSet().getFirstPlayerScore() %>
                </td>
                <td><%= currentMatch.getCurrentSet().getSecondPlayerScore() %>
                </td>
            </tr>
        </table>
    </div>

    <div class="card">
        <div>
            <p>First player: <%= currentMatch.getFirstPlayer().getName() %>
            </p>
            <p>
                Score: <%= currentMatch.getCurrentGame().getFirstPlayerScore().toString() %><%= currentMatch.isCurrentFirst ? "&#127934;" : "" %>
            </p>
            <form method="post">
                <button class="btn" name="winner" value="first">Take score for first player</button>
            </form>
        </div>
        <div>
            <p>Second player: <%= currentMatch.getSecondPlayer().getName() %>
            </p>
            <p>
                Score: <%= currentMatch.getCurrentGame().getSecondPlayerScore().toString() %><%= currentMatch.isCurrentFirst ? "" : "&#127934;" %>
            </p>
            <form method="post">
                <button class="btn" name="winner" value="second">Take score for second player</button>
            </form>
        </div>
    </div>
    <% } %>
    <% } else { %>
    <div class="match-not-found">
        <p>Match not found</p>
    </div>
    <% } %>
</div>
<footer>
    <button onclick="goUpOneLevel()" class="btn btn-back">Go Back</button>
</footer>
<script src="scripts/goUpOneLevel.js"></script>
</body>
</html>
