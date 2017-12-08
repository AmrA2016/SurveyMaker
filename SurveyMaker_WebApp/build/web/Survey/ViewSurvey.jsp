<%-- 
    Document   : ViewSurvey
    Created on : Dec 9, 2017, 12:04:06 AM
    Author     : Amr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/Survey_SubmitAnswers" method="post">
            <input type="checkbox" name="answer" value="true" > Show My Info
            <br>
            <input type="submit">
        </form>
    </body>
</html>
