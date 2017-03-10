<%-- 
    Document   : pocetna_korisnik
    Created on : Jun 20, 2016, 6:17:24 PM
    Author     : lovel_mimica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User home page</title>
    </head>
    <body>
        <h1>User:</h1>
        <%
            out.print("Username: " + session.getAttribute("user"));
        %>
        <br/>
        <a href="user_requests.jsp">User requests</a>
    </body>
</html>
