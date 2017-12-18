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
        <title>Forget password | Survey Maker</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Global/css/bootstrap.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Authentication/css/verification.css">
    </head>
    <body>
        <jsp:include page="../Global/navbar.jsp" />
        <div class="wrapper">
            <div class = "container ">
                <div class="col-md-6 col-md-offset-3">
                    <div class = "main">
                        <div class="paragraph">
                            <div class = "app-name">
                                <h2 id= "surveymaker"> Survey Maker </h2>
                            </div>
                            <p> Please enter your mail to reset your password<p>
                            <form class="form-inline" action="${pageContext.request.contextPath}/ResetPassword" >
                                <div class="form-group">
                                    <input type="email" class="form-control" id="exampleInputEmail1" name="email" placeholder="example@domain.com"  required>

                                </div>
                                <input type="submit" class="btn btn-primary">
                            </form>
                            <%
                                Boolean invalidMail = (Boolean) request.getAttribute("InvalidMail");

                                if (invalidMail != null && invalidMail) {
                            %>
                            <small class="text-danger">Email does not exist</small>
                            <%
                                }
                            %>
                        </div>
                        <div>

                        </div>
                    </div>
                    <div class = "col-lg-4">
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../Global/footer.jsp" />

    </body>
    <script src="${pageContext.request.contextPath}/Global/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/Global/js/bootstrap.js"></script>

</html>
