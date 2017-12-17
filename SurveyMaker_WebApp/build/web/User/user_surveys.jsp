<%-- 
    Document   : user_surveys
    Created on : Dec 11, 2017, 10:45:40 PM
    Author     : Amr
--%>

<%@page import="java.util.HashMap"%>
<%@page import="Entities.Survey"%>
<%@page import="java.util.ArrayList"%>
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
        <jsp:include page="../Global/navbar.jsp" />
        <div class="wrapper">
            <div class="container surveys-container">
                <div class="row">
                    <%
                        ArrayList<Survey> surveys = (ArrayList<Survey>) request.getAttribute("Surveys");
                        HashMap<Integer, Integer> responses_count = (HashMap<Integer, Integer>) request.getAttribute("ResponsesCount");

                        for (int i = 0; i < surveys.size(); i++) {
                    %>
                    <div id="survey-<%out.print(surveys.get(i).getId());%>" class="col-md-3">
                        <div class="survey-card">
                            <input type="number" class="survey-id" value=<%= surveys.get(i).getId()%> hidden>
                            <div class="survey-header">
                                <h3 class="survey-name"> <%= surveys.get(i).getTitle()%> </h3>
                            </div>
                            <div class="responses">
                                <span style="font-size: 60px; font-family: Roboto; color: #0abab5;" class="responses-word"> 
                                    <%= responses_count.get(surveys.get(i).getId())%>
                                    <h5 style="font-family: Roboto; position:relative; left: 5px; color: #595959; bottom: 15px;"> Responses </h5>
                                </span>
                            </div>
                            <div class=" suspend">
                                <label class="switch">
                                    <%
                                        if (surveys.get(i).isSuspended()) {
                                    %>
                                    <input id="suspend-survey-<%out.print(surveys.get(i).getId());%>" class="suspend-survey" onclick="suspendSurvey(<%out.print(surveys.get(i).getId());%>)" type="checkbox">   
                                    <%
                                    } else {
                                    %>
                                    <input id="suspend-survey-<%out.print(surveys.get(i).getId());%>" class="suspend-survey" onclick="suspendSurvey(<%out.print(surveys.get(i).getId());%>)" type="checkbox" checked="checked">
                                    <%
                                        }
                                    %>
                                    <span class="slider round"></span>
                                </label>
                            </div>
                            <div class="survey-footer">
                                <div class="icons">
                                    <a class="view-survey" href=<% out.print(request.getContextPath() + "/Survey_ViewSurvey?survey_id=" + surveys.get(i).getId()); %> title="View survey">
                                       <i class="fa fa-eye fa-lg icon" aria-hidden="true"></i>
                                    </a>
                                    <a class="view-stat" href=<% out.print(request.getContextPath() + "/Survey_ViewStatistics?survey_id=" + surveys.get(i).getId()); %> title="View survey statistics">
                                       <i class="fa fa-line-chart fa-lg icon" aria-hidden="true"></i>
                                    </a>
                                    <a class="del-survey" href="#" onclick="deleteSurvey(<%out.print(surveys.get(i).getId());%>)" title="Delete survey">
                                        <i class="fa fa-times fa-lg icon" aria-hidden="true"></i>
                                    </a>
                                </div>
                                <div id="response" class="text-center"></div>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>



                </div>
            </div>
        </div>
        <jsp:include page="../Global/footer.jsp" />
    </body>
    <script src="${pageContext.request.contextPath}/Global/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/Global/js/bootstrap.js"></script>
    <script>
                                        function deleteSurvey(id) {
                                            var xhttp = new XMLHttpRequest();

                                            xhttp.onreadystatechange = function () {
                                                if (this.readyState == 4 && this.status == 200) {
                                                    document.getElementById("response").innerHTML = this.responseText;
                                                    document.getElementById("survey-" + id).style.display = 'none';
                                                }
                                            };
                                            xhttp.open("GET", '/SurveyMaker_WebApp/Survey_RemoveSurvey?survey_id=' + id, true);
                                            xhttp.send();
                                        }

                                        function suspendSurvey(id) {
                                            if (document.getElementById("suspend-survey-" + id).checked == false) {
                                                var xhttp = new XMLHttpRequest();

                                                xhttp.onreadystatechange = function () {
                                                    if (this.readyState == 4 && this.status == 200) {
                                                        document.getElementById("response").innerHTML = this.responseText;
                                                    }
                                                };
                                                xhttp.open("GET", '/SurveyMaker_WebApp/Survey_SuspendSurvey?survey_id=' + id, true);
                                                xhttp.send();
                                            } else {
                                                var xhttp = new XMLHttpRequest();

                                                xhttp.onreadystatechange = function () {
                                                    if (this.readyState == 4 && this.status == 200) {
                                                        document.getElementById("response").innerHTML = this.responseText;
                                                    }
                                                };
                                                xhttp.open("GET", '/SurveyMaker_WebApp/Survey_UnSuspendSurvey?survey_id=' + id, true);
                                                xhttp.send();
                                            }

                                        }


    </script>
</html>
