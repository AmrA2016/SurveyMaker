<%-- 
    Document   : signup_form
    Created on : Dec 3, 2017, 7:38:52 PM
    Author     : Amr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign-up form</title>
        <link rel="stylesheet" href="../Global/css/bootstrap.css">
        <link rel="stylesheet" href="css/LoginStyle.css">
        <link rel="stylesheet" href="css/SignupStyle.css">
    </head>
    <body>
        <div class="container">
            <div class="col-lg-4">
            </div>
            <div class="col-lg-4">
                <div class="jumbotron">
                    <div class="app-name">
                        <h2 id="surveymaker"> Survey Maker </h2>
                    </div>
                    <div class="survey-maker">
                        <h4>Sign-up to Survey Maker</h4>
                    </div>
                    <form name="form" onsubmit="event.preventDefault(); validation();">
                        <div class="form-group">
                            <label class="form-text" for="exampleInputFirstName1">First Name</label>
                            <input type="text" class="form-control" id="exampleInputFirstName1" name="firstName" required>
                        </div>
                        <div class="form-group">
                            <label class="form-text" for="exampleInputLastName1">Last Name</label>
                            <input type="text" class="form-control" id="exampleInputLastName1" name="lastName" required>
                        </div>
                        <div class="form-group">
                            <label class="form-text" for="exampleInputEmail1">Email</label>
                            <input type="email" class="form-control" id="exampleInputEmail1" name="email" required>
                            <div id="emailmsg" style="color:Red; display:none">
                                <p style="font-size: 12px;">Not a valid e-mail address!</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="form-text" for="exampleInputPassword1">Password</label>
                            <input type="password" class="form-control form-text" id="exampleInputPassword1" name="password1" required>
                        </div>
                        <div class="form-group">
                            <label class="form-text" for="exampleInputPassword1">Re-enter password</label>
                            <input type="password" class="form-control form-text" id="exampleInputPassword1" name="password2" required>
                            <div id="passwordmsg" style="color:Red; display:none">
                                <p style="font-size: 12px;">password must be the same!</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="form-text" for="exampleInputMobile1">Mobile number</label>
                            <input type="tel" class="form-control" id="exampleInputMobile1" name="mobile" required>
                            <div id="mobilemsg" style="color:Red; display:none">
                                <p style="font-size: 12px;">value must be numeric only!</p>
                            </div>
                        </div>
                        <br>
                        <button type="submit" class="btn btn-primary form-text pull-right">Sign Up</button>
                    </form>
                </div>
            </div>
            <div class="col-lg-4">
            </div>
        </div>
    </body>
    
    
    <script src="../Global/js/jquery.js"></script>
    <script src="../Global/js/bootstrap.js"></script>
    <script src="js/form_validation.js"></script>
</html>
