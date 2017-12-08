/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.Choice;
import Entities.Notification;
import Entities.Question;
import Entities.Report;
import Entities.Survey;
import Models.ChoiceModel;
import Models.NotificationModel;
import Models.QuestionModel;
import Models.ReportModel;
import Models.SurveyModel;
import Models.UserModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
@WebServlet(name = "SurveyServlet", urlPatterns = {"/Survey_AddSurveyForm","/Survey_AddSurvey" , "/Survey_SuspendSurvey" , 
                                                    "/Survey_UnSuspendSurvey" , "/Survey_RemoveSurvey" , "/Survey_ReportSurvey"})
                                                    
public class SurveyServlet extends HttpServlet {

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
           
           if(path.equals("/Survey_AddSurveyForm"))
               getSurveyForm(request, response);
           else if(path.equals("/Survey_AddSurvey"))
               addSurvey(request, response);
           else if(path.equals("/Survey_SuspendSurvey"))
               suspendSurvey(request, response);
           else if(path.equals("/Survey_UnSuspendSurvey"))
               unSuspendSurvey(request, response);
           else if(path.equals("/Survey_RemoveSurvey"))
               removeSurvey(request, response);
           else if(path.equals("/Survey_ReportSurvey"))
               reportSurvey(request, response);
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
            Logger.getLogger(SurveyServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SurveyServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SurveyServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SurveyServlet.class.getName()).log(Level.SEVERE, null, ex);
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

    public void getSurveyForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        if(user_id == null)
            response.sendRedirect(request.getContextPath() + "/Home");
        else{
            response.sendRedirect("Survey/AddSurvey.jsp");
        }
        
    }
    
    public String removeSurvey(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        String result = "non";
        if(user_id == null)
            response.sendRedirect(request.getContextPath() + "/Home");
        else{
            Integer surveyID = Integer.parseInt(request.getParameter("survey_id"));
            result = SurveyModel.remove(surveyID);
        }
        
        return result;
        
    }
    
    public void reportSurvey(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        if(user_id == null)
            response.sendRedirect(request.getContextPath() + "/Home");
        else{
            Integer surveyID = Integer.parseInt(request.getParameter("survey_id"));
            String reportContent = request.getParameter("report_content");
            
            //create report record
            Report report = new Report(reportContent,surveyID,user_id);
            int result = ReportModel.save(report);
            
            //create notification record
            Notification notification = new Notification("user with id = " + user_id + " report the survey with id = " + surveyID + " because : "+ reportContent);
            NotificationModel NotificationModel = new NotificationModel();
            int notificationID = NotificationModel.save(notification);
            
            //Send notification to each admin
            ArrayList<Integer> adminIDs = new ArrayList<Integer>();
            adminIDs = UserModel.getAllAdmins();
            for(int i=0 ; i<adminIDs.size(); i++){
                UserModel.notify(adminIDs.get(i), notificationID);
            }
            
            response.sendRedirect("User/user_home.jsp");
            
        }
        
    }
    
    public String suspendSurvey(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        String result = "non";
        if(user_id == null)
            response.sendRedirect(request.getContextPath() + "/Home");
        else{
            Integer surveyID = Integer.parseInt(request.getParameter("survey_id"));
            result = SurveyModel.suspend(surveyID);
        }
        
        return result;
        
    }
    
    public String unSuspendSurvey(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        String result = "non";
        if(user_id == null)
            response.sendRedirect(request.getContextPath() + "/Home");
        else{
            Integer surveyID = Integer.parseInt(request.getParameter("serveyID"));
            result = SurveyModel.unSuspend(surveyID);
        }
        
        return result;
        
    }

    public void addSurvey(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        if(user_id == null)
            response.sendRedirect(request.getContextPath() + "/Home");
        else{
            Survey survey = null;
            Question quesObject = null;
            Choice choice = null;

            String survey_title = request.getParameter("survey_title");
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String creation_date = dateFormat.format(date);
            survey = new Survey(survey_title,creation_date,user_id);
            
            int survey_id = SurveyModel.save(survey);
            
            int questions_count = Integer.parseInt(request.getParameter("questions_count"));

            for (int i = 0; i < questions_count; i++) {
                String question_type = request.getParameter("question" + (i + 1) + "_type");
                String question_content = request.getParameter("question" + (i + 1) + "_content");
                
                
                if (question_type.equalsIgnoreCase("mcq")) {
                    
                    quesObject = new Question(question_content,"mcq", survey_id);

                    int question_id = QuestionModel.save(quesObject);
                    
                    int question_options_count = Integer.parseInt(request.getParameter("question" + (i + 1) + "_options_count"));

                    for (int j = 0; j < question_options_count; j++) {

                        String question_option = request.getParameter("question" + (i + 1) + "_option" + (j + 1) + "");
                        choice = new Choice(question_option, question_id);
                        ChoiceModel.save(choice);
                    }
                }
                else if (question_type.equalsIgnoreCase("checkbox")) {
                    
                    quesObject = new Question(question_content,"checkbox", survey_id);
                    int question_id = QuestionModel.save(quesObject);
                    
                    int question_options_count = Integer.parseInt(request.getParameter("question" + (i + 1) + "_options_count"));

                    for (int j = 0; j < question_options_count; j++) {

                        String question_option = request.getParameter("question" + (i + 1) + "_option" + (j + 1) + "");
                        choice = new Choice(question_option, question_id);
                        ChoiceModel.save(choice);
                    }
                }
                else if(question_type.equalsIgnoreCase("open")){
                    quesObject = new Question(question_content,"open", survey_id);
                    QuestionModel.save(quesObject);
                }
            }
            response.sendRedirect(request.getContextPath() + "/Home");

        }
    }


}
