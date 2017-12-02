<%-- 
    Document   : tempSurvey
    Created on : Dec 2, 2017, 1:05:35 PM
    Author     : DELL
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.beans.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
     
  
        <form method="post" action="SurveyServlet">
            <table border="0" cellpadding="2" cellspacing="2">
                <tr>
                    <td>title</td>
                    <td><input type="text" name="survey_title"></td>   
                </tr>
                <tr>
                    <td>Number Of Questions</td>
                    <td><input type="text" name="questions_count"></td>   
                </tr>
                <% 
                    int questions_count=(int)request.getAttribute("questions_count"); 
                    for(int i=0; i<questions_count; i++){ 
                %>
                <tr>
                   <td>Question Type</td>
                   <td><input type="text" name="question_type"></td>   
                </tr>
                <tr>
                   <td>Question Content</td>
                   <td><input type="text" name="question_content"></td>   
                </tr>
                    
                 <% String question_type=(String)request.getAttribute("question_type"); 
                     if (question_type.equalsIgnoreCase("mcq")|| question_type.equalsIgnoreCase("checkbbox")){
                      
                         %>
                <tr>
                   <td>Number of options for the question</td>
                   <td><input type="text" name="question_options_count"></td>   
                </tr>
                    
                <%  
                    int question_options_count=(int)request.getAttribute("question_options_count");
                    for (int j =0; j<question_options_count; j++){
                        %>
                <tr>
                   <td>Question Options</td>
                   <td><input type="text" name="question_option"></td>   
                </tr>  
                
                <%      
                    }
                     }
                   }
                
                %>
              

            </table>
        </form>
    </body>
</html>
