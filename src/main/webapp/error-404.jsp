<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>404 Not Found</title>
</head>
<body>

<%
    request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
%>

</body>
</html>
