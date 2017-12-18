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
                        ArrayList<User> normal_users = (ArrayList<User>) request.getAttribute("Users");
                        for (int i = 0; i < normal_users.size(); i++) {
                    %>
                    <tr>
                        <td><%= (i+1) %></td>
                        <td><%= normal_users.get(i).getMail()%></td>        
                        <td class="text-center">
                            <%
                                if (!normal_users.get(i).isSuspended()) {
                            %>  
                            <a href=<%= request.getContextPath() + "/Suspend_User?user_id=" + normal_users.get(i).getId()%> style="margin-right:10px" class="btn">Suspend</a>
                            <%
                            } else {
                            %>   
                            <a href=<%= request.getContextPath() + "/Unsuspend_User?user_id=" + normal_users.get(i).getId()%> style="margin-right:10px" class="btn">Unsuspend</a>
                            <%
                                }
                            %>
                            <a href=<%= request.getContextPath() + "/Make_Admin?user_id=" + normal_users.get(i).getId()%> style="margin-right:10px" class="btn">Make Admin</a>
                            <a id="adminChangePasswordBtn" href="#" data-user-id=<%=normal_users.get(i).getId()%> data-toggle="modal" data-target="#adminChangePassword" class="btn">Change Password</a>
                        </td>
                    </tr>
                    <%
                        }
                    %>

                </tbody> 
            </table>

        </div>

        <div class="modal " id="adminChangePassword">              
            <div class="modal-dialog ">
                <div class="modal-content">

                    <div class="modal-header title">
                        Change User Password                  
                        <button class="close" data-dismiss="modal">&times;</button>   
                    </div>
                    <form id="adminChangePasswordForm" action="${pageContext.request.contextPath}/Admin_ChangePassword" method="post"  onsubmit="return matchpass2();">
                        <div class="modal-body">
                            <input name="user_id" type="number" hidden="hidden">
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
        <jsp:include page="../Global/footer.jsp" />
    </body>

    <script src="${pageContext.request.contextPath}/Global/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/Global/js/bootstrap.js"></script>
    <script>
        $(document).on("click","#adminChangePasswordBtn",function(){
            var user_id = $(this).data("user-id");
            $("#adminChangePassword input[name='user_id']").val(user_id);
        });
    </script>
</html>
