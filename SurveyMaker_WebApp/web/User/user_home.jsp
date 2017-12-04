<%-- 
    Document   : home
    Created on : Dec 4, 2017, 9:56:21 AM
    Author     : Amr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | Survey Maker</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Global/css/bootstrap.css">
    </head>
    <body>
        <h1>User Home</h1>
        <%
            Boolean justVerified = (Boolean) request.getAttribute("JustVerified");

            if (justVerified != null && justVerified) {
        %>
        <div class="alert alert-success">
            <h3>Your account is verified successfully</h3>
        </div>
        <%
            }
        %>
        
        <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Logout</a>
    </body>
    <script src="${pageContext.request.contextPath}/Global/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/Global/js/bootstrap.js"></script>
</html>
