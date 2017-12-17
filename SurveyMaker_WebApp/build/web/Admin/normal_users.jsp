<%-- 
    Document   : normal_users
    Created on : Dec 17, 2017, 9:25:35 AM
    Author     : Amr
--%>

<%@page import="Entities.User"%>
<%@page import="java.util.ArrayList"%>
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
                        <th>Email</th>
                        <th class="col-md-6">Options</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<User> normal_users = (ArrayList<User>)request.getAttribute("Users");
                        for(int i =0;i < normal_users.size();i++)
                        {
                    %>
                    <tr>
                        <th scope="row">1</th>
                        <td><%= normal_users.get(i).getMail() %></td>        
                        <td class="text-center">
                            <% 
                                if(normal_users.get(i).isSuspended())
                                {
                            %>  
                            <a href="${pageContext.request.contextPath}/Suspend_User?user_id="<%= normal_users.get(i).getId() %> style="margin-right:10px" class="btn">Suspend</a>
                            <%
                                }else
                                {
                            %>   
                            <a href="${pageContext.request.contextPath}/Unsuspend_User?user_id="<%= normal_users.get(i).getId() %> style="margin-right:10px" class="btn">Unsuspend</a>
                            <%
                                }
                            %>
                            <a href="${pageContext.request.contextPath}/Make_Admin?user_id="<%= normal_users.get(i).getId() %> style="margin-right:10px" class="btn">Make Admin</a>
                            <a href="" class="btn">Change Password</a>
                        </td>
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
