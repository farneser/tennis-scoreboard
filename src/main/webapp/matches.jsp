<%@ page import="com.farneser.tennisscoreboard.data.entities.viewmodel.MatchListViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Finished matches</title>
    <% var matches = (MatchListViewModel) request.getServletContext().getAttribute("matches"); %>
</head>
<body>

<% if (!matches.matches().isEmpty()) { %>

<% for (var match : matches.matches()) {
    out.println(match + "<br/>");
} %>

<% } else {%>
<div>
    <div>
        Матчи на данной странице не найдены
    </div>

    <form action="matches" method="get" id="notFoundPageForm">
        <script>
            const urlParams = new URLSearchParams(window.location.search);

            const filterByPlayerName = urlParams.get('filter_by_player_name')

            const form = document.getElementById("notFoundPageForm");

            if (filterByPlayerName !== null && filterByPlayerName !== "") {

                const filterField = document.createElement("input")

                filterField.type = "hidden";
                filterField.name = "filter_by_player_name"
                filterField.value = filterByPlayerName

                form.append(filterField)
            }

        </script>

        <input type="hidden" name="page" value="1"/>
        <input type="submit" value="Go to first page"/>
    </form>
</div>

<% } %>
</body>
</html>
