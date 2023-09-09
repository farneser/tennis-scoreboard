<%@ page import="com.farneser.tennisscoreboard.data.entities.viewmodel.MatchListViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Finished matches</title>
</head>
<body>

<% var matches = (MatchListViewModel) request.getServletContext().getAttribute("matches"); %>

<pre>
    <% out.println(matches.lastPageNumber()); %>
    <% out.println(matches.currentPageNumber()); %>
</pre>

<% for (var match : matches.matches()) {
    out.println(match + "<br/>");
} %>

</body>
</html>
