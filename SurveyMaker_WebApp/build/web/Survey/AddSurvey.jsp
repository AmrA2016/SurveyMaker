<%-- 
    Document   : tempSurvey
    Created on : Dec 2, 2017, 1:05:35 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Add Survey | Survey Maker</title>


        <!-- Stylesheets -->
        <link href="${pageContext.request.contextPath}/Global/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Global/css/font-awesome.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Survey/css/AddSurvey_Style.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    </head>
    <body>

        <jsp:include page="../Global/navbar.jsp" />
        
        <!--   Survey Form-->
        <div class="survey-container row">
            <div class="col-md-6 col-md-offset-3 ">
                <div class="survey-form ">
                    <form action="${pageContext.request.contextPath}/Survey_AddSurvey" method="post">
                        <div class="form-header text-center">
                            <p>Add Survey</p>
                        </div>
                        <div class="form-body">
                            <div class="form-group">
                                <input type="text" class="form-control survey-title" name="survey_title" value="Untitled Survey" required>
                            </div>

                            <div class="questions">
                                <input type="number" min="1" class="questions-count" name="questions_count" value="0" hidden="hidden">
                            </div>

                            <div class="add-question">
                                <a href="#" title="Add Question" data-toggle="modal" data-target="#question-popup"><i class="fa fa-plus-circle" aria-hidden="true"></i></a>
                            </div>

                        </div>
                        <div class="form-footer text-center">
                            <input type="submit" class="btn " value="Save Survey">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!--    Add Question Popup-->
        <div id="question-popup" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <button class="close" data-dismiss="modal">
                            &times;
                        </button>
                        <div class="modal-title">
                            <h4>Add Question</h4>
                        </div>
                    </div>
                    <div class="modal-body">
                        <form id="add-question-form" action="" class="form-inline">
                            <div class="row">
                                <div class="form-group col-md-4 col-md-offset-2 ">
                                    <select name="new_question_type"  class="form-control">
                                        <option value="-1" disabled selected>Select Question type</option>
                                        <option value="mcq">MCQ</option>
                                        <option value="checkbox">Checkbox</option>
                                        <option value="open">Open Question</option>
                                    </select>
                                </div>
                                <div class="col-md-2 ">
                                    <input type="submit" class="btn btn-primary" value="Add Question">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!--======================-->

        <!--############################################ -->
        
    </body>

    <script src="${pageContext.request.contextPath}/Global/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/Global/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/Survey/js/AddSurvey_Script.js"></script>
</html>
