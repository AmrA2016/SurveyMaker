<%-- 
    Document   : ConfirmAccount
    Created on : Dec 4, 2017, 12:43:42 AM
    Author     : Amr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invalid Link | Survey Maker</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Global/css/bootstrap.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Authentication/css/verification.css">
    </head>
    <body>
        <jsp:include page="Global/navbar.jsp" />

        <div class = "container wrapper">
            <div class = "col-lg-4">
            </div>
            <div class="col-lg-4">
                <div class = "main">
                    <div class="paragraph">
                        <div class = "app-name">
                            <h2 id= "surveymaker"> Survey Maker </h2>
                        </div>
                        <h2 class="text-danger">Invalid Link!</h2>
                    </div>
                    <div>

                    </div>
                </div>
                <div class = "col-lg-4">
                </div>
            </div>
        </div>
        
        <jsp:include page="Global/footer.jsp" />

    </body>
    <script src="${pageContext.request.contextPath}/Global/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/Global/js/bootstrap.js"></script>

</html>
