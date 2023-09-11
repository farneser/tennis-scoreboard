<%@ page import="com.farneser.tennisscoreboard.data.entities.viewmodel.MatchListViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Finished matches</title>
    <link rel="stylesheet" type="text/css" href="css/matches.css">

    <% var matches = (MatchListViewModel) request.getServletContext().getAttribute("matches");
        var numPagesToShow = 5;
        var startPage = Math.max(1, matches.currentPageNumber() - (numPagesToShow / 2));
        var endPage = Math.min(matches.lastPageNumber(), startPage + numPagesToShow - 1);
        if (endPage - startPage + 1 < numPagesToShow) {
            startPage = Math.max(1, endPage - numPagesToShow + 1);
        }
        var filter = request.getParameter("filter_by_player_name");
    %>
</head>

<body>

<form action="#" method="GET">
    <label>
        Enter player name
        <input type="text" name="filter_by_player_name" placeholder="Player name"
               value="<%=filter == null ? "" : filter%>">
    </label>
    <input type="submit" value="Search">
</form>


<% if (!matches.matches().isEmpty()) { %>
<table>
    <caption>
        Score
    </caption>
    <tr>
        <th>Match Id</th>
        <th>First player</th>
        <th>Second player</th>
    </tr>
    <% for (var match : matches.matches()) {
        out.println("<tr>");
        out.println("<td>" + match.getId() + "</td>");
        out.println("<td>" + match.getPlayer1().getName() + (match.getPlayer1() == match.getWinner() ? " &#x1F3C6;" : "") + "</td>");
        out.println("<td>" + match.getPlayer2().getName() + (match.getPlayer2() == match.getWinner() ? " &#x1F3C6;" : "") + "</td>");
        out.println("</tr>");
    } %>

</table>

<ul>
    <%!
        public String formatHref(int pageNumber, String filter) {
            var link = "?page=" + pageNumber;

            if (filter != null && !filter.isEmpty()) {
                link += "&filter_by_player_name=" + filter;
            }

            return link;
        }
    %>
    <li>
        <a href="<%=formatHref(1, filter)%>">
            <<
        </a>
    </li>

    <li>
        <a href="<%=formatHref(Math.max(matches.currentPageNumber() - 1, 1), filter)%>">
            <
        </a>
    </li>

    <% for (int i = startPage; i <= endPage; i++) { %>
    <li>
        <a href="<%=formatHref(i, filter)%>"<%= (i == matches.currentPageNumber()) ? " class='active'" : "" %>><%=i%>
        </a>
    </li>
    <% } %>

    <li>
        <a href="<%=formatHref(Math.min(matches.currentPageNumber() + 1, matches.lastPageNumber()), filter)%>">
            >
        </a>
    </li>

    <li>
        <a href="<%=formatHref(matches.lastPageNumber(), filter)%>">
            >>
        </a>
    </li>
</ul>

<% } else {%>
<div>
    <div>
        Матчи на данной странице не найдены
    </div>

    <a href="<%=formatHref(1, filter)%>">Go to first page</a>

</div>

<% } %>
</body>
</html>
