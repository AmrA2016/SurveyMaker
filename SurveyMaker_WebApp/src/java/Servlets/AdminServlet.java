/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.Notification;
import Entities.Report;
import Entities.User;
import Models.NotificationModel;
import Models.ReportModel;
import Models.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Manar Ashraf
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/Make_Admin", "/Suspend_User", "/Unsuspend_User" , "/GetReports", 
                                                  "/GetUsers", "/Admin_sendMessage","/Admin_ChangePassword"})
public class AdminServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
         String path = request.getServletPath();
       if(path.equals("/Make_Admin")) {
            makeAdmin(request, response);
        }
       else if(path.equals("/Suspend_User")){
            suspendUser(request,response);
        }else if (path.equals("/Unsuspend_User")) {
            unSuspendUser(request, response);
        }
        else if(path.equals("/GetUsers")){
            getUsers(request, response);
        }else if (path.equals("/Admin_sendMessage")) {
            sendMessage(request, response);
        }else if (path.equals("/Admin_ChangePassword")) {
            adminChangePassword(request, response);
        }else if (path.equals("/GetReports")) {
            getReports(request, response);
        }
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    public void makeAdmin(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException
    {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        UserModel user = new UserModel();
        
        if(user_id != null)
        {
            String userType = (String) session.getAttribute("user_type");
            if(userType.equals("admin"))
            {
                int id = Integer.parseInt(request.getParameter("user_id"));
                user.setAdmin(id);
                
                Notification notification = new Notification("You became an admin");
                NotificationModel NotificationModel = new NotificationModel();
                int notificationID = NotificationModel.save(notification);
                UserModel.notify(id, notificationID);
                
                response.sendRedirect(request.getContextPath() + "/GetUsers");
            }else{
                response.sendRedirect(request.getContextPath() + "/Home");
            }
        }else{
            response.sendRedirect(request.getContextPath() + "/Home");
            
        }
    }
    public void suspendUser(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException
    {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        
        if(user_id != null)
        {
            String userType = (String) session.getAttribute("user_type");
            if(userType.equals("admin"))
            {
                int id = Integer.parseInt(request.getParameter("user_id"));
                UserModel.setSuspended(id);
                
                User user = UserModel.getByID(id);
                String receiver = user.getMail();
                String subject = "Survey Maker| Suspension inform";
                String message = "Dear " + user.getFirstName() + ",\n"
                                +"You have been suspended from entering Survey Maker website.\n"
                                + "If you see that there's mistake, please don't hesitate to mail us\n\n"
                                + "Regareds,\nAdmin";
                sendMail(receiver, subject, message);
                
                response.sendRedirect(request.getContextPath() + "/GetUsers");
            }else{
                response.sendRedirect(request.getContextPath() + "/Home");
            }
        }else{
            response.sendRedirect(request.getContextPath() + "/Home");
            
        }
    }
    public void unSuspendUser(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException
    {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        
        if(user_id != null)
        {
            String userType = (String) session.getAttribute("user_type");
            if(userType.equals("admin"))
            {
                int id = Integer.parseInt(request.getParameter("user_id"));
                UserModel.setUnSuspended(id);
                
                User user = UserModel.getByID(id);
                
                String receiver = user.getMail();
                String subject = "Survey Maker| Unsuspension inform";
                String message = "Dear " + user.getFirstName() + ",\n"
                                +"Your account now is unsuspended!\n"
                                + "We are sorry for misunderstading, you're welcomed in our website.\n\n"
                                + "Regareds,\nAdmin";
                sendMail(receiver, subject, message);
                response.sendRedirect(request.getContextPath() + "/GetUsers");
            }else{
                response.sendRedirect(request.getContextPath() + "/Home");
            }
        }else{
            response.sendRedirect(request.getContextPath() + "/Home");
            
        }
    }

    private void getUsers(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        UserModel user = new UserModel();
        
        if(user_id != null)
        {
            String userType = (String) session.getAttribute("user_type");
            if(userType.equals("admin"))
            {
                ArrayList<Integer> user_ids = UserModel.getAllNormalUsers();
                ArrayList<User> normal_users = new ArrayList<>();
                
                for(int i = 0;i < user_ids.size();i++)
                    normal_users.add(UserModel.getByID(user_ids.get(i)));
                
                request.setAttribute("Users", normal_users);
                request.getRequestDispatcher("Admin/normal_users.jsp").forward(request, response);
            }else{
                response.sendRedirect(request.getContextPath() + "/Home");
            }
        }else{
            response.sendRedirect(request.getContextPath() + "/Home");
            
        }
    }
    
    private void sendMessage(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException {
         HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        
        
        if(user_id == null)
            response.sendRedirect(request.getContextPath() + "/Home");
         else {
            String user_type = (String)session.getAttribute("user_type");
            if(user_type.equals("admin")){
                String target = request.getParameter("target");
                String messageContent = request.getParameter("message_content");
                Notification notification = new Notification("Admin sent you: " + messageContent);
                NotificationModel NotificationModel = new NotificationModel();

                int notificationID = NotificationModel.save(notification);

                ArrayList<Integer> targetIDs = new ArrayList<Integer>();
                if(target.equals("allUsers")){
                    targetIDs = UserModel.getAllNormalUsers();
                }
                else{
                    targetIDs.add(Integer.parseInt(target));
                }

                for(int i=0 ; i<targetIDs.size();i++){
                    UserModel.notify(targetIDs.get(i), notificationID);
                }
            }
            response.sendRedirect(request.getContextPath() + "/Home");
            //response.getWriter().print("hi");
            
        }
    }
    
    private void adminChangePassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        if(user_id == null)
            response.sendRedirect(request.getContextPath() + "/Home");
        else{
            String user_type = (String)session.getAttribute("user_type");
            if(user_type.equals("admin")){
                int id = Integer.parseInt(request.getParameter("user_id"));
                
                String newPassword = (String) request.getParameter("new_password");
                UserModel.changePasswrod(id, newPassword);
                
                response.sendRedirect(request.getContextPath() + "/GetUsers");
            }
            else{
                response.sendRedirect(request.getContextPath() + "/Home");
            }
        }
    }
    
    
    private void getReports(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        if(user_id == null)
            response.sendRedirect(request.getContextPath() + "/Home");
        else{
            String user_type = (String)session.getAttribute("user_type");
            if(user_type.equals("admin")){
                ArrayList<Report> reports = ReportModel.getAll();
                
                request.setAttribute("Reports", reports);
                request.getRequestDispatcher("Admin/survey_reports.jsp").forward(request, response);
            }
            else{
                response.sendRedirect(request.getContextPath() + "/Home");
            }
        }
    }
    
    private boolean sendMail(String Reciever, String subject, String text) {

        String username = "surveymaker.owner@gmail.com";
        String password = "iaP@ssw0rd!";

        String from = "surveymaker.owner@gmail.com";

        String to = Reciever;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(text);

            // Send message
            Transport.send(message);
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
    }

}
