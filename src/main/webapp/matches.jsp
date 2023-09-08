<%@ page import="com.farneser.tennisscoreboard.data.entities.Match" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Finished matches</title>
</head>
<body>
<% var matches = (List<Match>) request.getServletContext().getAttribute("matches"); %>

<pre>
    <% out.println(matches); %>
</pre>

<% matches.forEach(System.out::println); %>

</body>
</html>
