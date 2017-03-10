<%-- 
    Document   : vlastiti_zahtijevi
    Created on : Jun 20, 2016, 6:23:59 PM
    Author     : lovel_mimica
--%>

<%@page import="com.foi.nwtis.lovmimica.bp.PresentUsers"%>
<%@page import="com.foi.nwtis.lovmimica.bp.PresentSocketRequests"%>
<%@ page import="com.foi.nwtis.lovmimica.logika.*" %>
<%@ page import="com.foi.nwtis.lovmimica.datatypes.*" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User socket requests</title>
    </head>
    <body>
        <h1>User socket requests</h1>
        <%
           String username = (String)session.getAttribute("user");
           int userId = PresentUsers.getId(username);
           
           List<SocketRequest> userRequests = PresentSocketRequests.getUserSocketRequests(userId);
           for(SocketRequest r : userRequests){
               out.print("ID: " + r.getId() + ", UserId: " + r.getUserId()+
                       ", AddressId: " + r.getAddressId()+ ", Processing start: " + r.getStart()+ 
                       ", Processing end: " + r.getEnd()+ ", Command: " + r.getCommand()
               );
               out.print("</br>");
           }
        %>
    </body>
</html>
