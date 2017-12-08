<%-- 
    Document   : Display
    Created on : Oct 2, 2017, 10:27:45 PM
    Author     : osman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <style>
        span{
            width:5px;
            display: inline-block;
            
        }
        
        div{
            font-size: 25px;
            position:fixed;
            top: 25%;
            left: 50%;
            width:30em;
            text-align: center;
            
            margin-left: -15em; /*set to a negative number 1/2 of your width*/
            border: 1px solid #ccc;
            background-color: #f3f3f3;
            padding: 100px 0px;
        }
        
        
    </style>
    </head>
    
    
    <body>
        
        <div>
        
        <%
             String number =  (String)request.getAttribute("number");
             double final_result = (Double)request.getAttribute("result");
             
             out.print("Calculation String: ");
             for(int i=0 ; i<number.length() ; i++){
                 if(number.charAt(i)=='+' || number.charAt(i)=='-'){
                     %>
                     <span style="color:red"><% out.print(number.charAt(i)); %></span>
                     <%
                 }

                else if(number.charAt(i)=='/' || number.charAt(i)=='*'){
                     %>
                     <span style="color:blue"><% out.print(number.charAt(i)); %></span>
                     <%
                 }

                else{
                    %>
                    <span style="color:black"><% out.print(number.charAt(i)); %></span>
                    <%
                }

                
             }
           out.print("<br>Results: "+final_result);
        %>
        
        </div>
    </body>
</html>
