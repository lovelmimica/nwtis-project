<%-- 
    Document   : pregled_korisnika
    Created on : Jun 20, 2016, 7:06:16 PM
    Author     : lovel_mimica
--%>
<%@page import="com.foi.nwtis.lovmimica.bp.PresentUsers"%>
<%@ page import="com.foi.nwtis.lovmimica.logika.*" %>
<%@ page import="com.foi.nwtis.lovmimica.datatypes.*" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User list</title>
    </head>
    <body>
        <h1>User list</h1>
        <%
           List<User> users = PresentUsers.getAll();
           
            out.print("<table border='1'>");
           out.print("<tr> <th>id</th> <th>username</th> <th>password</th> <th>category</th> <th>admin</th> </tr>");
           
           for(User u : users){
               out.print("<tr> <td>" + u.getId() + "</td> <td>" + u.getName()+"</td> <td>" + u.getPassword()+ "</td> <td>" + u.getCategory()+ "</td> <td>" + u.isAdmin()+ "</td> </tr>");
           }
           out.print("</table>");
           
        %>
    </body>
</html>
