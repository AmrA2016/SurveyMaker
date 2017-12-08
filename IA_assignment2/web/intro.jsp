<%-- 
    Document   : intro
    Created on : Oct 23, 2017, 1:41:36 PM
    Author     : osman
--%>




<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Intro jsp</title>
        <style>
       
        
        div{
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
        
        .input{
            border-radius: 3px;
            padding: 5px 0px;
            border: 1px solid black;
            text-align: center;
            font-size: 20px;
           
        }
        
        form{
            font-size:22px;
            display: inline-grid;
        }
        
        .submit-btn{
            padding: 10px;
            font-size: 20px;
        }
    </style>

    </head>
    <body>
        
        <%
            
            boolean flag = false ;
            Cookie[] AllCookies = request.getCookies();
            if(AllCookies !=null){
                for (Cookie cookie : AllCookies) {
                    if (cookie.getName().equals("MyCurrentSession")) {
                      flag = true;
                      if(application.getAttribute("SessionManager") != null){//searching for session manager object  
                          String cookie_session_id = cookie.getValue();
                          HashMap<String,HttpSession> SessionManager = new HashMap<String,HttpSession>();
                          SessionManager = (HashMap)application.getAttribute("SessionManager");
                          
                          if(SessionManager.get(cookie_session_id) != null){// if session object exist in session manager
                              %>
                              <p> <% out.println("Name : " + session.getAttribute("name")); %> </p>
                              <%
                              
                              Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IA_Assignment", "root","");
                              
                              Statement Stmt = Con.createStatement();
                              ResultSet RS = Stmt.executeQuery("SELECT email, mobile FROM user WHERE session_id = '"+session.getId()+"' ");
                              
                              RS.next();
                              %>
                              <p> <% out.println("Email : " + RS.getString(1)); %> </p>
                              
                              <p> <% out.println("Phone Number : " + RS.getString(2)); %> </p>
                              
                              <p> <% out.println("Number of active sessions : " + SessionManager.size()); %> </p>
                              
                              <a href="logout">logout</a>
                              <%

                              
                          }
                          
                          else{ // if session object does not exist 
                            cookie.setValue(null);
                            cookie.setMaxAge(0);
                            cookie.setPath("/");
                            response.addCookie(cookie);
                          }
                          
                      }
                      
                      else{ //if session manager object does not exist
                          
                        cookie.setValue(null);
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                      }
                      
                     }
                   }
               
                if(!flag){
                    if(application.getAttribute("SessionManager") == null){
                        HashMap<String,HttpSession> SessionManager = new HashMap<String,HttpSession>();
                        application.setAttribute("SessionManager", SessionManager);
                        
                        
                    }
                    else{
                        %>
                            <div>
                                <form action="StoreTheSession" method="post">
                                    Name <input type="text" name="name" class='input' />
                                    Email <input type="text" name="email" class='input' />
                                    Phone Number <input type="text" name="mobile" class='input' />
                                    <br>
                                    <input type='submit' class='submit-btn'/>
                                </form>
                            </div>
                        <%
                    }
//application.removeAttribute("SessionManager");
                }
            }
            
        %>
        
    </body>
</html>
