<%-- 
    Document   : user_surveys
    Created on : Dec 11, 2017, 10:45:40 PM
    Author     : Amr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User surveys</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Global/css/bootstrap.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Global/css/font-awesome.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/User/css/general_surveys_style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/User/css/user_surveys_style.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row">
                
                <div class="col-lg-3">
                    <div class="survey-card">
                        <div class="survey-header">
                            <h3 class="survey-name"> Travelling </h3>
                        </div>
                        <div class="responses">
                            <span style="font-size: 60px; font-family: Roboto; color: #0abab5;" class="responses-word"> 12 <h5 style="font-family: Roboto; position:relative; left: 5px; color: #595959; bottom: 15px;"> Responses </h5></span>
                        </div>
                        <div class=" suspend">
                            <label class="switch">
                  <input type="checkbox">
                  <span class="slider round"></span>
               </label>
                        </div>
                        <div class="survey-footer">
                            <div class="icons">
                                <a class="view-survey" href="" title="View survey"><i class="fa fa-eye fa-lg icon" aria-hidden="true"></i></a>
                                <a class="report-survey" href="" title="Report survey"><i class="fa fa-pencil-square-o fa-lg icon" aria-hidden="true"></i></a>
                                <a class="view-stat" href="" title="View survey statistics"><i class="fa fa-line-chart fa-lg icon" aria-hidden="true"></i></a>
                                <a class="del-survey" href="" title="Delete survey"><i class="fa fa-times fa-lg icon" aria-hidden="true"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                
                
            </div>
        </div>
    </body>
    <script src="${pageContext.request.contextPath}/Global/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/Global/js/bootstrap.js"></script>
</html>