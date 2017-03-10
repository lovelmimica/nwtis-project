<%-- 
    Document   : pregled_socket_zahtijeva
    Created on : Jun 20, 2016, 7:07:00 PM
    Author     : lovel_mimica
--%>
<%@page import="com.foi.nwtis.lovmimica.bp.PresentSocketRequests"%>
<%@ page import="com.foi.nwtis.lovmimica.logika.*" %>
<%@ page import="com.foi.nwtis.lovmimica.datatypes.*" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pregled socket zahtijeva</title>
    </head>
    <body>
        <h1>SocketRequest list</h1>
        <%
            Integer next;
            Integer ordinal;
            Integer previous;
        %>
        <%
            String ordinalString = request.getParameter("ordinal");
            ordinal = Integer.parseInt(ordinalString);
            next = ordinal + 1;
            if(ordinal > 0) previous = ordinal -1;
            else previous = ordinal;
                    
                    
            
            Integer startNo = ordinal * 10;
            Integer endNo = startNo + 10;
            
           List<SocketRequest> allRequests = PresentSocketRequests.getAll();
           
           out.print("<table border='1'>");
           out.print("<tr> <th>id</th> <th>user_id</th> <th>start</th> <th>end</th> <th>request</th>");
           
           for(int i = startNo; i<endNo && i<allRequests.size(); i++){
               SocketRequest r = allRequests.get(i);
               out.print("<tr> <td>" + r.getId() + "</td> <td>" + r.getUserId()  +"</td> <td>" + r.getStart()+ "</td> <td>" + r.getEnd() + "</td> <td>" + r.getCommand()+ "</td> </tr>");
           }
           
           out.print("</table>");
        %>
        <a href="pregled_socket_zahtijeva.jsp?ordinal=<%=next%>">Next</a>
        <a href="pregled_socket_zahtijeva.jsp?ordinal=<%=previous%>">Previous</a>
    </body>
</html>
