<%-- 
    Document   : login
    Created on : Jun 20, 2016, 6:29:02 PM
    Author     : lovel_mimica
--%>
<%@ page import="com.foi.nwtis.lovmimica.logika.*" %>
<%
    String user = request.getParameter("user");
    String pswd = request.getParameter("passwd");
    
    boolean odobreno = Autentification.userAutentification(user, pswd);
    if(odobreno){
        session.setAttribute("user", user);
        session.setAttribute("passwd", pswd);
        boolean admin = Autentification.adminAutentification(user, pswd);
        if(admin){
            response.sendRedirect("admin/home_admin.jsp");
        }else{
            response.sendRedirect("user/home_user.jsp");
        }
    }else response.sendRedirect("index.jsp");
%>
