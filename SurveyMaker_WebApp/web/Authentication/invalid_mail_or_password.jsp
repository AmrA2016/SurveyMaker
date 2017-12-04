<%-- 
    Document   : invalid_email
    Created on : Dec 4, 2017, 4:21:41 PM
    Author     : Manar Ashraf
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login form</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Global/css/bootstrap.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Authentication/css/LoginStyle.css">
    </head>
    <body>
        <div class = "container">
            <div class = "col-lg-4">
            </div>
            <div class="col-lg-4">
                <div class="jumbotron">
                    <div class = "app-name">
                        <h2 id= "surveymaker"> Survey Maker </h2>
                    </div>
                    <div class = "survey-maker">
                        <h4>Login to Survey Maker</h4>
                        <h6>Not registered yet?<a href="${pageContext.request.contextPath}/User_GetSignupForm">sign up now</a></h6>
                    </div>
                    <form name="form" action="${pageContext.request.contextPath}/Login" method="post" onsubmit="return validateemail();">
                        <div class="form-group">
                            <label class= "form-text" for="exampleInputEmail1">Email</label>
                            <input type="email" class="form-control" id="exampleInputEmail1" name="email"  required>
                            <% Boolean invalidMail = (Boolean) request.getAttribute("InvalidMail");
                               Boolean invalidpassword = (Boolean) request.getAttribute("Invalidpassword");
                            if (invalidMail != null && invalidMail){ %>
                            <div id="emailmsg" style = "color:Red; display:block"><p style="font-size: 12px;">Wrong e-mail address!</p></div>
                        </div>
                            <div class="form-group">
                            <label class="form-text" for="exampleInputPassword1">Password</label>
                            <input type="password" class="form-control form-text" id="exampleInputPassword1" name="password" required="">
                            <a id="forgot" href="${pageContext.request.contextPath}/getResetForm">forgot password?</a>
                        </div>
                            <% }if(invalidpassword != null && invalidpassword ){
                             %>
                         </div>    
                        <div class="form-group">
                            <label class="form-text" for="exampleInputPassword1">Password</label>
                            <input type="password" class="form-control form-text" id="exampleInputPassword1" name="password" required="">
                            <div style = "color:Red; display:block"><p style="font-size: 12px;">Wrong password!</p></div>
                            <a id="forgot" href="${pageContext.request.contextPath}/getResetForm">forgot password?</a>
                        </div>
                            <% }
                            %>
                        <button type="submit" class="btn btn-primary form-text">Login</button>
                    </form>
                </div>
            </div>
            <div class = "col-lg-4">
            </div>
        </div>
    </body>
    
    <script src="${pageContext.request.contextPath}/Global/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/Global/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/Authentication/js/form_validation.js"></script>
    
    
</html>
