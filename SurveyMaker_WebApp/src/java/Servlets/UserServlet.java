/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.User;
import Entities.VerificationToken;
import Models.UserModel;
import Models.VerificationTokenModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Amr
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/User_GetSignupForm", "/User_Register", "/User_GetLoginForm",
    "/User_Login", "/User_ForgetPassword", "/User_ChangePassword", "/User_VerifyAccount"})
public class UserServlet extends HttpServlet {

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

        if (path.equals("/User_GetSignupForm")) {
            getSignupForm(request, response);
        } else if (path.equals("/User_Register")) {
            register(request, response);
        } else if (path.equals("/User_GetLoginForm")) {
            getLoginForm(request, response);
        } else if (path.equals("/User_Login")) {
            login(request, response);
        } else if (path.equals("/User_ForgetPassword")) {
            forgetPassword(request, response);
        } else if (path.equals("/User_ChangePassword")) {
            changePassword(request, response);
        } else if (path.equals("/User_VerifyAccount")) {
            verifyAccount(request, response);
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
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
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

    private void getSignupForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("Authentication/signup_form.jsp");
        } catch (IOException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password1");
        String mobile = request.getParameter("mobile");
        
        if(UserModel.getByMail(email) != null){
            request.setAttribute("ExistMail", true);
            request.getRequestDispatcher("Authentication/signup_form.jsp").forward(request, response);
        }
        else{
            User user = new User(firstName,lastName,email,password,mobile);
            int user_id = UserModel.save(user);
            String token = email + "_" + UUID.randomUUID();
            VerificationToken verification_token = new VerificationToken(token, user_id);
            VerificationTokenModel.save(verification_token);
            String basePath = "http://localhost:8080" + request.getContextPath();
            String subject = "Survey Maker | Account Confirmation";
            String content = "Please open this link to verify your account:\n"
                            + basePath + "/User_VerifyAccount?token=" + token;
            sendMail(email, subject, content);
            
            response.sendRedirect("Authentication/confirm_account.jsp");
        }

    }

    private void getLoginForm(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void forgetPassword(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
         HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        if(user_id == null)
            response.sendRedirect(request.getContextPath());
        else{
            String newPassword = (String) request.getParameter("new_password");
            UserModel.changePasswrod(user_id, newPassword);
            
            String userType = (String) session.getAttribute("user_type");
            if(userType.equals("admin")){
                response.sendRedirect("Admin/admin_home.jsp");
            }
            else{
                response.sendRedirect("User/user_home.jsp");
            }
        }
    }

    private void verifyAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException, ServletException {
        String token_text = request.getParameter("token");
        
        VerificationToken token = VerificationTokenModel.getByToken(token_text);
        
        if(token == null)
            response.sendRedirect("invalid_link.jsp");
        else{
            int user_id = token.getUser_id();
            UserModel.setVerified(user_id, true);
            VerificationTokenModel.delete(token.getId());
            request.setAttribute("JustVerified", true);
            request.getRequestDispatcher("User/user_home.jsp").forward(request, response);
        }
    }
    
    private boolean sendMail(String Reciever, String subject, String text){
        
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
