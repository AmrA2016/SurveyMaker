<%-- 
    Document   : SurveyStatistics
    Created on : Dec 12, 2017, 1:13:40 AM
    Author     : Amr
--%>

<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.HashMap"%>
<%@page import="Entities.Question"%>
<%@page import="Entities.SurveyAnswer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entities.Survey"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Survey Statistics | Survey Maker</title>

        <!-- Stylesheets -->
        <link href="${pageContext.request.contextPath}/Global/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Global/css/font-awesome.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Survey/css/Statistics_Style.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    </head>
    <body>
        <%
            Survey survey = (Survey)request.getAttribute("Survey");
            ArrayList<SurveyAnswer> survey_answers = (ArrayList<SurveyAnswer>)request.getAttribute("SurveyAnswers");
            ArrayList<Question> questions = (ArrayList<Question>)request.getAttribute("Questions");
            HashMap<Integer,HashMap<String,Integer> > questions_answers = (HashMap<Integer, HashMap<String, Integer>>) request.getAttribute("QuestionAnswers");
            
        %>
        <div class="survey-container row">
            <div class="col-md-6 col-md-offset-3 ">
                <div class="survey-stat ">
                    <div class="stat-header text-center">
                        <p> <%= survey.getTitle() %> </p>
                    </div>
                    <div class="stat-body">
                        <h4 style="padding-left:30px">
                            <strong>Total number of survey responses: </strong> 
                            <span style="font-size:20px"> <%= survey_answers.size() %> </span>
                        </h4>
                        <br>
                        <div class="row">
                            <%
                                for(int i = 0;i < questions.size();i++){
                                    
                                    if(! questions.get(i).getType().equals("open")){
                            %>
                            <div class="col-md-10 col-md-offset-1" style="height:250px;margin-bottom: 50px; " id=<% out.print("chartContainer" + (i+1)); %> ></div>
                            <%
                                    }else{
                            %>
                            <div class="col-md-10 col-md-offset-1">
                                <h4 class="text-center" style="font-weight:bold">
                                    <%= "Q" + (i+1) + "." + questions.get(i).getContent() %>
                                </h4>
                                <table class="table table-bordered table-hover table-responsive">
                                    <thead >
                                        <tr>
                                            <th class="col-md-10">Answer</th>
                                            <th class="col-md-2">Count</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            HashMap<String,Integer> answers_map = questions_answers.get(questions.get(i).getId());

                                            for(String key: answers_map.keySet()){
                                        %>
                                        <tr>
                                            <td> <%= key %> </td>
                                            <td> <%= answers_map.get(key) %> </td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                            <%
                                    }
                            %>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    
    <script src="${pageContext.request.contextPath}/Global/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/Global/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/Survey/js/jquery.canvasjs.min.js"></script>
    <script>
        $(function () {
            <% for(int i = 0; i < questions.size();i++){ 
                HashMap<String,Integer> answers_map = questions_answers.get(questions.get(i).getId());
                ArrayList<HashMap<Object,Object> > data_list = new ArrayList<HashMap<Object, Object>>();
                
                Gson gson = new Gson();
                
                
                for(String key : answers_map.keySet()){
                    HashMap<Object,Object> point = new HashMap<Object, Object>();
                    point.put("y", answers_map.get(key));
                    point.put("label", key);
                    data_list.add(point);
                }
                String data_points = gson.toJson(data_list);
            %>
                var chart = new CanvasJS.Chart("chartContainer"+<%= (i+1) %>, {
                    animationEnabled: true,
                    theme: "light2", // "light1", "light2", "dark1", "dark2"
                    title:{
                            text: "<%= "Q" + (i+1) + "." + questions.get(i).getContent() %>"
                    },
                    axisY: {
                            title: "Count"
                    },
                    data: [{        
                            type: "column",
                            dataPoints: <%= data_points %>
                        }]
                });
                chart.render();
            <%}%>
        });
    </script>
</html>
