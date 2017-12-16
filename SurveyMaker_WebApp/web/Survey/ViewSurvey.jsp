<%-- 
    Document   : ViewSurvey
    Created on : Dec 9, 2017, 12:04:06 AM
    Author     : Amr
--%>

<%@page import="Entities.Choice"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entities.Question"%>
<%@page import="Entities.Survey"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View Survey</title>

        <link href="${pageContext.request.contextPath}/Global/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Survey/css/ViewSurvey_Style.css">
    </head>
    <body>
        <%
            Survey survey = (Survey)request.getAttribute("Survey");
            ArrayList<Question> questions = (ArrayList<Question>)request.getAttribute("Questions");
            HashMap<Integer,ArrayList<Choice> > questions_choices = (HashMap<Integer,ArrayList<Choice> >)request.getAttribute("Questions_Choices");
        %>
        <div class="survey-container row">
            <div class="col-md-6 col-md-offset-3 ">
                <div class="survey-form">
                    <form action="${pageContext.request.contextPath}/Survey_SubmitAnswers" method="post">
                        <div class="form-header text-center">
                            <p><%= survey.getTitle() %></p>
                        </div>
                        <div class="form-body">
                            <input type="number" name="survey_id" value=<%= survey.getId() %> hidden="hidden">
                            <input type="number" name="questions_count" value=<%= questions.size() %> hidden="hidden">
                            <%
                                for (int i = 0; i < questions.size(); i++) 
                                {
                                    if(questions.get(i).getType().equals("checkbox")){
                            %>
                            <div class="question checkbox-question">
                                <input type="number" name=<% out.print("question" +(i+1) + "_id"); %> 
                                       value=<%= questions.get(i).getId() %> hidden="hidden">
                                
                                <input type="text" name=<% out.print("question" +(i+1) + "_type"); %> 
                                       value=<%= questions.get(i).getType() %> hidden="hidden">
                                
                                <input type="text" name=<% out.print("question" +(i+1) + "_options_count"); %> 
                                       value=<%= questions_choices.get(questions.get(i).getId()).size() %> hidden="hidden">

                                <h3 class="question-title">
                                    <p><%= questions.get(i).getContent() %></p>
                                </h3>
                                <div class="answers">
                                    <%
                                        ArrayList<Choice> choices = questions_choices.get(questions.get(i).getId());
                                        for (int j = 0; j < choices.size(); j++) {
                                    %>
                                    <div class="form-check">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="checkbox" name=<% out.print("question" + (i+1) + "_answer" +(j+1)); %> 
                                                   value=<%= choices.get(j).getContent() %> <% if(survey.isSuspended()) out.print("disabled"); %> >
                                            <%= choices.get(j).getContent() %>
                                        </label>
                                    </div>
                                    <%
                                        }
                                    %>
                                </div>
                            </div>
                            <%
                                    }
                                    else if(questions.get(i).getType().equals("mcq")){
                            %>      
                            <div class="question mcq-question">
                                <input type="number" name=<% out.print("question" +(i+1) + "_id"); %> 
                                       value=<%= questions.get(i).getId() %> hidden="hidden">
                                
                                <input type="text" name=<% out.print("question" +(i+1) + "_type"); %> 
                                       value=<%= questions.get(i).getType() %> hidden="hidden">
                                
                                <h3 class="question-title">
                                    <p><%= questions.get(i).getContent() %></p>
                                </h3>
                                <div class="answers">
                                    <%
                                        ArrayList<Choice> choices = questions_choices.get(questions.get(i).getId());
                                        for (int j = 0; j < choices.size(); j++) {
                                    %>
                                    <div class="form-check">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="radio" name=<% out.print("question" + (i+1) + "_answer"); %> 
                                                   value=<%= choices.get(j).getContent() %> required <% if(survey.isSuspended()) out.print("disabled"); %>>
                                            <%= choices.get(j).getContent() %>
                                        </label>
                                    </div>
                                    <%
                                        }
                                    %>
                                </div>
                            </div>
                            <%
                                    }
                                    else if(questions.get(i).getType().equals("open")){
                            %>  
                            <div class="question open-question">
                                <input type="number" name=<% out.print("question" +(i+1) + "_id"); %> 
                                       value=<%= questions.get(i).getId() %> hidden="hidden">
                                
                                <input type="text" name=<% out.print("question" +(i+1) + "_type"); %> 
                                       value=<%= questions.get(i).getType() %> hidden="hidden">
                                
                                <h3 class="question-title">
                                    <p><%= questions.get(i).getContent() %></p>
                                </h3>
                                <div class="row">
                                    <div class="form-group col-md-10 col-md-offset-1">
                                        <textarea class="form-control" style="min-width: 70px;" 
                                                  rows="3" placeholder="Enter your answer" 
                                                  name=<% out.print("question" + (i+1) + "_answer"); %> required 
                                                  <% if(survey.isSuspended()) out.print("disabled"); %> ></textarea>
                                    </div>
                                </div>
                            </div>
                            <%
                                    }
                                }
                            %>
                            <hr>
                            <div class="form-check" style="margin-left: 50px">
                                <label class="form-check-label" >
                                    <input class="form-check-input" type="checkbox" name="show_info" value="true">
                                    Show my account info to survey owner
                                </label>
                            </div>
                            <br>
                            <div class="form-footer text-center">
                                <% if(!survey.isSuspended()) {%>
                                <input type="submit" class="btn submit-btn" value="Submit Answers">
                                <% } %>
                            </div>
                            <br>
                        </div>


                    </form>
                </div>
            </div>
        </div>
    </body>
    
    <script src="${pageContext.request.contextPath}/Global/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/Global/js/bootstrap.min.js"></script>
</html>
