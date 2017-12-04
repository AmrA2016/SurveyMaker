/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.User;
import Models.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.UUID;
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

/**
 *
 * @author Amr
 */
@WebServlet(name = "AuthenticationServlet", urlPatterns = {"/Login", "/getResetForm", "/ResetPassword", "/Logout"})
public class AuthenticationServlet extends HttpServlet {

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

        if (path.equals("/Login")) {
            login(request, response);
        } else if (path.equals("/getResetForm")) {
            getResetPasswordForm(request, response);
        } else if (path.equals("/ResetPassword")) {
            resetPassword(request, response);
        } else if (path.equals("/Logout")) {
            logout(request, response);
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
            Logger.getLogger(AuthenticationServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthenticationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AuthenticationServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthenticationServlet.class.getName()).log(Level.SEVERE, null, ex);
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

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserModel user_model = new UserModel();
        User user = null;
        user = user_model.getByMail(email);

        if (user == null) {
            request.setAttribute("InvalidMail", true);
            request.getRequestDispatcher("Authentication/login_form.jsp").forward(request, response);

        } else {

            if (user.getPassword().equals(password)) {
                if (user.isVerified() == true) {
                    if (user.isAdmin() == true) {
                        response.sendRedirect("Admin/admin_home.jsp");
                    } else {
                        response.sendRedirect("User/user_home.jsp");
                    }
                }else{
                    
                    response.sendRedirect("Authentication/confirm_account.jsp");
                }

            }else{
                
                request.setAttribute("Invalidpassword", true);
                request.getRequestDispatcher("Authentication/login_form.jsp").forward(request, response);
                
            }
        }

    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
    }

    private void getResetPasswordForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("Authentication/forget_password.jsp");
    }

    private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        String mail = request.getParameter("email");
        User user = UserModel.getByMail(mail);
        if (user == null) {
            request.setAttribute("InvalidMail", true);
            request.getRequestDispatcher("Authentication/forget_password.jsp").forward(request, response);
        } else {
            String newPassword = UUID.randomUUID() + "";

            UserModel.setPasswrod(user.getId(), newPassword);

            String subject = "Survey Maker | Reset Password";
            String content = "This is your new account authentication info\n"
                    + "Email: " + mail
                    + "\nPassword: " + newPassword;

            sendMail(mail, subject, content);
            response.sendRedirect(request.getContextPath());
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
