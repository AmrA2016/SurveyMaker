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
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="../Global/navbar.jsp" />

        <%
            Boolean justVerified = (Boolean) request.getAttribute("JustVerified");

            if (justVerified != null && justVerified) {
        %>
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <p>Your account is verified successfully</p>
        </div>
        <%
            }
        %>

        <div class="wrapper">
            <div class="container surveys-container">
                <div class="row">
                    <%
                        ArrayList<Survey> surveys = (ArrayList<Survey>) request.getAttribute("Surveys");
                        HashMap<Integer, Integer> responses_count = (HashMap<Integer, Integer>) request.getAttribute("ResponsesCount");

                        for (int i = 0; i < surveys.size(); i++) {
                    %>
                    <div class="col-lg-3">
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

                            <div class="survey-footer">
                                <div class="icons">
                                    <a class="view-survey" title="View survey" href=<% out.print(request.getContextPath() + "/Survey_ViewSurvey?survey_id=" + surveys.get(i).getId()); %>  >
                                       <i class="fa fa-eye fa-lg icon" aria-hidden="true"></i>
                                    </a>
                                    <a class="report-survey" href="#" data-survey-id=<%= surveys.get(i).getId() %> data-toggle="modal" data-target="#reportSurvey" title="Report survey">
                                        <i class="fa fa-pencil-square-o fa-lg icon" aria-hidden="true"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>



                </div>
            </div>
        </div>
        <div class="modal " id="reportSurvey">              
            <div class="modal-dialog ">
                <div class="modal-content">

                    <div class="modal-header title">
                        Report Survey                  
                        <button class="close" data-dismiss="modal">&times;</button>   
                    </div>
                    <form action="${pageContext.request.contextPath}/Survey_ReportSurvey" method="post">
                        <input type="number" name="survey_id" hidden="hidden">
                        <div class="modal-body">
                            <textarea class="form-control" name="report_content" rows="3" placeholder="Reasons For Reporting "></textarea>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn">Report Survey</button>
                        </div>
                    </form>
                </div>
            </div>
        </div> 
        <jsp:include page="../Global/footer.jsp" />

    </body>
    <script src="${pageContext.request.contextPath}/Global/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/Global/js/bootstrap.js"></script>
    <script>
        $(document).on("click",".report-survey",function(){
            var survey_id = $(this).data("survey-id");
            $("#reportSurvey input[name='survey_id']").val(survey_id);
        });
    </script>
</html>
