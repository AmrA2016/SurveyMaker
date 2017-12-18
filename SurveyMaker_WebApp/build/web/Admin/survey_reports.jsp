<%-- 
    Document   : SurveyReports
    Created on : Dec 18, 2017, 6:53:35 AM
    Author     : Amr
--%>

<%@page import="Models.SurveyModel"%>
<%@page import="Entities.Survey"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entities.Report"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Users | Survey Maker</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Global/css/bootstrap.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Admin/css/all_users.css">
    </head>
    <body>
        <jsp:include page="../Global/navbar.jsp" />
        <div class="container wrapper">
            <table class="table table-hover table-bordered `">
                <thead class="head">
                    <tr >
                        <th>#</th>
                        <th>Survey Name</th>
                        <th class="col-md-6">Report</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<Report> reports = (ArrayList<Report>) request.getAttribute("Reports");
                        HashMap<Integer,Survey> reports_surveys = new HashMap<Integer, Survey>();
                        for (int i = 0; i < reports.size(); i++) 
                        {
                            Survey survey;
                            int survey_id = reports.get(i).getSurvey_id();
                            if(reports_surveys.containsKey(survey_id))
                                survey = reports_surveys.get(survey_id);
                            else{
                                survey = SurveyModel.getByID(survey_id);
                                reports_surveys.put(survey_id, survey);
                            }
                    %>
                    <tr>
                        <td><%= (i+1) %></td>
                        <td><%= survey.getTitle() %></td>        
                        <td><%= reports.get(i).getContent() %></td>
                    </tr>
                    <%
                        }
                    %>

                </tbody> 
            </table>

        </div>
                    
        <jsp:include page="../Global/footer.jsp" />
    </body>

    <script src="${pageContext.request.contextPath}/Global/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/Global/js/bootstrap.js"></script>
</html>
