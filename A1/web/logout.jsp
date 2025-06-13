<%-- 
    Document   : logout
    Created on : Jun 4, 2025, 8:15:41 PM
    Author     : User
--%>

<%@ page session="true" %>
<%
    session.invalidate();  
    response.sendRedirect("login.jsp");  
%>

