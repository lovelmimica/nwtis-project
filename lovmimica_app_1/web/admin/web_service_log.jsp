<%-- 
    Document   : pregled_dnevnika_ws
    Created on : Jun 20, 2016, 7:06:50 PM
    Author     : lovel_mimica
--%>
<%@page import="com.foi.nwtis.lovmimica.bp.PresentWsRequests"%>
<%@ page import="com.foi.nwtis.lovmimica.logika.*" %>
<%@ page import="com.foi.nwtis.lovmimica.datatypes.*" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WebService log</title>
    </head>
    <body>
        <h1>WebService log</h1>
        <%
            Integer next;
            Integer ordinal;
            Integer previous;
        %>
        <%
            String ordinalStr = request.getParameter("ordinal");
            ordinal = Integer.parseInt(ordinalStr);
            next = ordinal +1;
            if(ordinal>0) previous = ordinal -1;
            else previous = ordinal;

            Integer startNo = ordinal * 10;
            Integer endNo = startNo + 10;

            List<WsRequest> currentRequests = PresentWsRequests.getAll();
           
           out.print("<table border='1'>");
           out.print("<tr> <th>id</th> <th>user_id</th> <th>ws_name</th> <th>start</th> <th>end</th> <th>request</th>");
           
           for(int i = startNo; i<endNo && i<currentRequests.size(); i++){
               WsRequest req = currentRequests.get(i);
               out.print("<tr> <td>" + req.getId() + "</td> <td>" + req.getUserId()  +"</td> <td>" + req.getWsName() + "</td> <td>" + req.getStart() + "</td> <td>" + req.getEnd() + "</td> <td>" + req.getCommand() + "</td> </tr>");
           }
           
           out.print("</table>");
        %>
        <a href="pregled_dnevnika_ws.jsp?ordinal=<%=next%>">Next</a>
        <a href="pregled_dnevnika_ws.jsp?ordinal=<%=previous%>">Previous</a>
    </body>
</html>
