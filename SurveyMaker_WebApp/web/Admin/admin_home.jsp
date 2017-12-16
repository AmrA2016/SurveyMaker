<%-- 
    Document   : home
    Created on : Dec 4, 2017, 9:54:56 AM
    Author     : Amr
--%>

<%@page import="Models.UserModel"%>
<%@page import="java.util.HashMap"%>
<%@page import="Entities.Survey"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | Survey Maker</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Global/css/bootstrap.css">
    </head>
    <body>
        <jsp:include page="../Global/navbar.jsp" />
        <h1>Admin Home</h1>
        
        
        <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Logout</a>
        <jsp:include page="../Global/footer.jsp" />
    </body>
    <script src="${pageContext.request.contextPath}/Global/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/Global/js/bootstrap.js"></script>
</html>
