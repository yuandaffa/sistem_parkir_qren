<%-- 
    Document   : index
    Created on : May 18, 2026, 8:12:28 PM
    Author     : asus
--%>

<%@page import="classes.JDBC"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            JDBC db = new JDBC();
            db.connect();
        %>

    <p>Status Database:</p>

    <h3>
        <%= db.getMessage()%>
    </h3>
    </body>
</html>
