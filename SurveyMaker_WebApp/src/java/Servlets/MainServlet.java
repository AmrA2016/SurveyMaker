/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.User;
import Entities.Survey;
import Entities.SurveyAnswer;
import Models.SurveyAnswerModel;
import Models.SurveyModel;
import Models.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
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
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "MainServlet", urlPatterns = {"/Home","/Login", "/getResetForm", "/ResetPassword",
                                                            "/ChangePassword", "/Logout"})
public class MainServlet extends HttpServlet {

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
        }else if(path.equals("/Home")){
            getHome(request,response);
        }else if (path.equals("/getResetForm")) {
            getResetPasswordForm(request, response);
        } else if (path.equals("/ResetPassword")) {
            resetPassword(request, response);
        }  else if (path.equals("/ChangePassword")) {
            changePassword(request, response);
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
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    private void getHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        
        if(user_id == null)
            request.getRequestDispatcher("index.jsp").forward(request, response);
        else{
            String userType = (String) session.getAttribute("user_type");
            ArrayList<Survey> all_surveys = SurveyModel.getAll();
            ArrayList<Survey> other_surveys = new ArrayList<>();
            HashMap<Integer,Integer> responses_count = new HashMap<>();
            for(int i =0;i < all_surveys.size();i++)
            {
                if(all_surveys.get(i).getCreator_id() != user_id){
                    other_surveys.add(all_surveys.get(i));
                    ArrayList<SurveyAnswer> survey_answers = SurveyAnswerModel.getBySurveyID(all_surveys.get(i).getId());
                    responses_count.put(all_surveys.get(i).getId(), survey_answers.size());
                }
            }
            
            request.setAttribute("Surveys", other_surveys);
            request.setAttribute("ResponsesCount", responses_count);
            if(userType.equals("admin")){
                request.getRequestDispatcher("Admin/admin_home.jsp").forward(request, response);
            }
            else{
                request.getRequestDispatcher("User/user_home.jsp").forward(request, response);
            }
        }
    }
    
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
                    HttpSession session = request.getSession();
                    session.setAttribute("user_id", user.getId());
                    if (user.isAdmin() == true) {
                        session.setAttribute("user_type", "admin");
                    } else {
                        session.setAttribute("user_type", "user");
                    }
                    
                    response.sendRedirect(request.getContextPath() + "/Home");
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
        response.sendRedirect(request.getContextPath() + "/Home");
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
            response.sendRedirect(request.getContextPath() + "/Home");
        }
    }
 
    private void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        if(user_id == null)
            response.sendRedirect(request.getContextPath() + "/Home");
        else{
            String newPassword = (String) request.getParameter("new_password");
            UserModel.changePasswrod(user_id, newPassword);
            
            String userType = (String) session.getAttribute("user_type");
            response.sendRedirect(request.getContextPath() + "/Home");
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

