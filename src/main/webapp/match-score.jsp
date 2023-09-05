<%@ page import="com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch" %>
<%--@elvariable id="currentMatch" type="com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Match score</title>
    <link rel="stylesheet" href="css/match-score.css">
    <% var currentMatch = (CurrentMatch) request.getServletContext().getAttribute("currentMatch"); %>
</head>

<% if (currentMatch != null) { %>

<body>
<div>
    <table>
        <% if (currentMatch.getWinnerPlayer() != null) { %>
        <p> Player
            <% out.println(currentMatch.getWinnerPlayer().getName()); %>
            won the game</p>
        <% } else { %>
        <p> Game in progress...</p>
        <% } %>
        <caption>Match score</caption>
        <tr>
            <th>
                <% out.println(currentMatch.getFirstPlayer().getName()); %>
            </th>

            <th>
                <% out.println(currentMatch.getSecondPlayer().getName()); %>
            </th>
        </tr>

        <% for (var set : currentMatch.getSetScores()) {

            out.println("<tr>");
            out.println("<td>" + set.getFirstPlayerScore() + "</td>");
            out.println("<td>" + set.getSecondPlayerScore() + "</td>");
            out.println("</tr>");
        } %>

    </table>


</div>
<% if (currentMatch.getWinnerPlayer() == null) { %>

<div>
    <table>
        <caption>Current Set</caption>

        <tr>
            <th>
                <% out.println(currentMatch.getFirstPlayer().getName()); %>
            </th>

            <th>
                <% out.println(currentMatch.getSecondPlayer().getName()); %>
            </th>
        </tr>
        <tr>
            <% out.println("<td>" + currentMatch.getCurrentSet().getFirstPlayerScore() + "</td> ");%>
            <% out.println("<td>" + currentMatch.getCurrentSet().getSecondPlayerScore() + "</td> ");%>
        </tr>
    </table>

</div>

<div>
    <div>

        <p>First player:
            <% out.println(currentMatch.getFirstPlayer().getName()); %>
        </p>
        <p>score:
            <% out.println(currentMatch.getGameScore().getFirstPlayerScore().getPointCode()); %>
            <% out.println(currentMatch.isCurrentFirst ? "ball" : ""); %>
        </p>
        <form method="post">

            <button class="btn" name="winner" value="first">Take score for first player</button>

        </form>
    </div>
    <div>
        <p>second:
            <% out.println(currentMatch.getFirstPlayer().getName()); %>
        </p>
        <p>score:
            <% out.println(currentMatch.getGameScore().getSecondPlayerScore().getPointCode()); %>
            <% out.println(currentMatch.isCurrentFirst ? "" : "ball"); %>
        </p>
        <form method="post">

            <button class="btn" name="winner" value="second">Take score for second player</button>

        </form>
    </div>


</div>


<% } %>


</body>

<% } else { %>

<div>

    match not found

</div>

<% } %>

</html>
