<%@ page import="com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch" %>
<%--@elvariable id="currentMatch" type="com.farneser.tennisscoreboard.data.services.currentmatches.CurrentMatch"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Match score</title>
    <% var currentMatch = (CurrentMatch) request.getServletContext().getAttribute("currentMatch"); %>
</head>

<% if (currentMatch != null) { %>

<body>
<table>

    <tr>
        <%
            out.println("<td>" + (currentMatch.isCurrentFirst ? "ball" : "") + "</td> ");
            out.println("<td>" + (currentMatch.isCurrentFirst ? "" : "ball") + "</td> ");
        %>
    </tr>

    <tr>
        <th>first</th>
        <th>second</th>
    </tr>

    <% for (var set : currentMatch.getSetScores()) {

        out.println("<tr>");
        out.println("<td>" + set.getFirstPlayerScore() + "</td>");
        out.println("<td>" + set.getSecondPlayerScore() + "</td>");
        out.println("</tr>");
    }%>
</table>

<% if (currentMatch.getWinnerPlayer() != null) { %>
<p> player won the game</p>
<% } else { %>
<p> game in progress</p>
<% } %>

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

<div>
    ${currentMatch}
</div>

</body>

<% } else { %>

<div>

    match not found

</div>

<% } %>

</html>
