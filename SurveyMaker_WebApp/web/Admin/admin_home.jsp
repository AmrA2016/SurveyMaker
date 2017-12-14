<%-- 
    Document   : home
    Created on : Dec 4, 2017, 9:54:56 AM
    Author     : Amr
--%>

<%@page import="Models.UserModel"%>
<%@page import="java.util.HashMap"%>
<%@page import="Entities.Survey"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | Survey Maker</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Global/css/bootstrap.css">
    </head>
    <body>
        <h1>Admin Home</h1>
        <!-- OSMAN part start here ya 3atofaaaa -->
        <a href="#" class="btn btn-danger" data-toggle="modal" data-target="#myModal">Send Message</a>
        
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                  <form id="messageForm" method="get" action="${pageContext.request.contextPath}/User_sendMessage">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                      <h4 class="modal-title" id="myModalLabel">Send Message Form</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container" style="width: 100%">
                            <div class="row text-center">
                                <h5>Choose the Email of the User !</h5>
                                <select name="target" form="messageForm">
                                    <option value="allUsers">All Users</option>
                                    <%
                                        ArrayList<String> usersMails = new ArrayList<String>();
                                        ArrayList<Integer> usersIDs= new ArrayList<Integer>();
                                        usersMails = UserModel.getAllNormalUsersMails();
                                        usersIDs = UserModel.getAllNormalUsers();
                                        for(int i=0 ; i<usersMails.size(); i++ ){
                                            %>
                                            <option value=<% out.print(usersIDs.get(i)); %>><% out.print(usersMails.get(i)); %></option>
                                            <%
                                        }
                                    %>
                                </select>
                                <h5>Write here the Message !</h5>
                                <textarea name="message_content" rows="4"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                      <button id="send-message" type="submit" class="btn btn-primary">Send Message</button>
                    </div>
                </form>
              </div>
            </div>
          </div>
        
        
        <!-- OSMAN part 5les here ya 3atofaaaa -->
        
        <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Logout</a>
    </body>
    <script src="${pageContext.request.contextPath}/Global/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/Global/js/bootstrap.js"></script>
</html>
