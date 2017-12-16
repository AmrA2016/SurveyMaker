<%-- 
    Document   : navbar
    Created on : Dec 16, 2017, 7:40:54 PM
    Author     : Amr
--%>

<%@page import="Models.NotificationModel"%>
<%@page import="Entities.Notification"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.UserModel"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Global/css/font-awesome.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Global/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Global/css/navBarStyleSheet.css">
    </head>
    <body>
        <nav class="navbar navbar-fixed-top">    
            <div class="container-fluid">
                <div class="navbar-header ">        
                    
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                      <i class="fa fa-bars" style="color:#fff" aria-hidden="true"></i>
                    </button>
                    <a href="${pageContext.request.contextPath}/Home" class="navbar-brand">Survey Maker</a>    
                </div>
                <% 
                    Integer user_id = (Integer)session.getAttribute("user_id");
                    if(user_id != null)
                    {
                %>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">             
                        <%
                            String user_type = (String)session.getAttribute("user_type");
                            if(user_type.equals("user"))
                            {
                        %>
                        <li>
                            <a href="${pageContext.request.contextPath}/Survey_AddSurveyForm" class="linkOne"><b>Add Survey</b></a>
                        </li>
                        <%
                            }
                        %>

                        <%
                            HashMap<Integer,Boolean> user_notifications = UserModel.getMyNotifications(user_id);

                            ArrayList<Notification> notifications = new ArrayList<Notification>();
                            int unread_count = 0;
                            for(int key : user_notifications.keySet())
                            {
                                Notification notification = NotificationModel.getById(key);
                                notifications.add(notification);

                                if(user_notifications.get(key) == false)
                                    unread_count++;
                            }
                        %>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle notifications" data-toggle="dropdown">
                                <i class="fa fa-bell"></i><span class="badge badge-notify"><%= unread_count %></span>                               
                            </a>                 
                            <ul class="dropdown-menu">  
                                <%
                                    for(int i = 0;i < notifications.size();i++)
                                    {
                                %>
                                <li><a href=""><%= notifications.get(i).getContent() %></a></li>
                                <%
                                    }
                                %>
                            </ul>
                        </li>


                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle info" data-toggle="dropdown">
                                <i class="fa fa-odnoklassniki"></i>
                                <span class="caret"></span>                                
                            </a>                 
                            <ul class="dropdown-menu">          
                                <li><a href="#" data-toggle="modal" data-target="#changePassword">Change Password</a></li>
                                <%
                                    if(user_type.equals("user"))
                                    {
                                %>
                                <li><a href="#">My Surveys</a></li>
                                <%
                                    }else{
                                %>
                                <li><a href="#">View Users</a></li>
                                <li><a href="#">Send Message</a></li>
                                <%
                                    }
                                %>
                                <li><a href="#">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                    <%
                        }
                    %>
                </div>
            </div>
        </nav>
                
        <%
            if(user_id != null)
            {
        %>  
            <div class="modal " id="changePassword">              
                 <div class="modal-dialog ">
                     <div class="modal-content">
                         
                         <div class="modal-header title">
                                 Change Password                  
                             <button class="close" data-dismiss="modal">&times;</button>   
                         </div>
                         <form action="${pageContext.request.contextPath}/ChangePassword" method="post"  onsubmit="return matchpass();">
                            <div class="modal-body">
                                
                                <div class="form-group">
                                    <label>Enter new password</label>
                                    <input type="password" class="form-control" name="new_password" required>
                                </div>
                                
                                <div class="form-group">
                                    <label>Repeat new password</label>
                                    <input type="password" class="form-control" name="confirm_password" required>

                                     <div id="passwordmsg" style="color:Red; display:none">
                                        <p style="font-size: 12px;">Passwords must be the same!</p>
                                    </div>
                                </div>
                                
                            </div>
                            <div class="modal-footer">
                                <input class="btn" type="submit" >
                            </div>
                        </form>
                     </div>
                 </div>
             </div>
        <%
            }
        %>
    </body>
    
    <script src="${pageContext.request.contextPath}/Global/js/change_password_validation.js"></script>
</html>
