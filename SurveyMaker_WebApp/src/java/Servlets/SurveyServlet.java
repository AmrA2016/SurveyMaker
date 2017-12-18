/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.Answer;
import Entities.Choice;
import Entities.Notification;
import Entities.Question;
import Entities.Report;
import Entities.Survey;
import Entities.SurveyAnswer;
import Entities.User;
import Models.AnswerModel;
import Models.ChoiceModel;
import Models.NotificationModel;
import Models.QuestionModel;
import Models.ReportModel;
import Models.SurveyAnswerModel;
import Models.SurveyModel;
import Models.UserModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
                                                    "/Survey_UnSuspendSurvey" , "/Survey_RemoveSurvey" , "/Survey_ReportSurvey"
                                                   ,"/Survey_ViewSurvey","/Survey_SubmitAnswers","/Survey_ViewStatistics"})
                                                    
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
           else if(path.equals("/Survey_ViewSurvey"))
               viewSurvey(request,response);
           else if(path.equals("/Survey_SubmitAnswers"))
               submitAnswers(request,response);
           else if(path.equals("/Survey_ViewStatistics"))
               viewStatistics(request,response);
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
            String user_type = (String)session.getAttribute("user_type");
            Integer surveyID = Integer.parseInt(request.getParameter("survey_id"));
            Survey survey = SurveyModel.getByID(surveyID);
            
            if(survey.getCreator_id() != user_id && user_type.equals("user"))
                response.sendRedirect(request.getContextPath() + "/Home");
                
            
            result = SurveyModel.remove(surveyID);
            
            if(user_type.equals("admin") && user_id != survey.getCreator_id()){
                Notification notification = new Notification("Admin removed your survey with name: " + survey.getTitle());
                int notificationID = NotificationModel.save(notification);
                UserModel.notify(survey.getCreator_id(), notificationID);
            }
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
            Notification notification = new Notification("user with id = " + user_id + " report the survey with id = " + surveyID);
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
            String user_type = (String)session.getAttribute("user_type");
            Integer surveyID = Integer.parseInt(request.getParameter("survey_id"));
            Survey survey = SurveyModel.getByID(surveyID);
            
            if(survey.getCreator_id() != user_id && user_type.equals("user"))
                response.sendRedirect(request.getContextPath() + "/Home");
                
            
            result = SurveyModel.suspend(surveyID);
            
            if(user_type.equals("admin") && user_id != survey.getCreator_id()){
                Notification notification = new Notification("Admin suspended your survey with name: " + survey.getTitle());
                int notificationID = NotificationModel.save(notification);
                UserModel.notify(survey.getCreator_id(), notificationID);
            }
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
            String user_type = (String)session.getAttribute("user_type");
            Integer surveyID = Integer.parseInt(request.getParameter("survey_id"));
            Survey survey = SurveyModel.getByID(surveyID);
            
            if(survey.getCreator_id() != user_id && user_type.equals("user"))
                response.sendRedirect(request.getContextPath() + "/Home");
                
            
            result = SurveyModel.unSuspend(surveyID);
            
            if(user_type.equals("admin") && user_id != survey.getCreator_id()){
                Notification notification = new Notification("Admin unsuspended your survey with name: " + survey.getTitle());
                int notificationID = NotificationModel.save(notification);
                UserModel.notify(survey.getCreator_id(), notificationID);
            }
        }
        
        return result;
        
    }

    public void addSurvey(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        if(user_id == null)
            response.sendRedirect(request.getContextPath() + "/Home");
        else{
            User user = UserModel.getByID(user_id);
            if(user.isSuspended()){
                response.sendRedirect(request.getContextPath() + "/Home");
            }
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

    private void viewSurvey(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        if(user_id == null)
            response.sendRedirect(request.getContextPath() + "/Home");
        else{
            int survey_id = Integer.parseInt(request.getParameter("survey_id"));
            
            Survey survey = SurveyModel.getByID(survey_id);
            ArrayList<Question> questions = QuestionModel.getBySurveyID(survey_id);
            HashMap<Integer,ArrayList<Choice> > questions_choices = new HashMap<>();
            
            for(int i = 0;i < questions.size();i++){
                if(questions.get(i).getType().equals("open"))
                    continue;
                ArrayList<Choice> choices = ChoiceModel.getByQuestionID(questions.get(i).getId());
                questions_choices.put(questions.get(i).getId(), choices);
            }
            
            request.setAttribute("Survey", survey);
            request.setAttribute("Questions", questions);
            request.setAttribute("Questions_Choices", questions_choices);
            
            request.getRequestDispatcher("Survey/ViewSurvey.jsp").forward(request, response);
        }
    }

    private void submitAnswers(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        if(user_id == null)
            response.sendRedirect(request.getContextPath() + "/Home");
        else{
            int survey_id = Integer.parseInt(request.getParameter("survey_id"));
            int questions_count = Integer.parseInt(request.getParameter("questions_count"));
            Boolean show_info = Boolean.parseBoolean(request.getParameter("show_info"));
            
            SurveyAnswer surveyAnswer = new SurveyAnswer(show_info, survey_id, user_id);
            int survey_answer_id = SurveyAnswerModel.save(surveyAnswer);
            
            for(int i =0;i < questions_count;i++){
                int question_id = Integer.parseInt(request.getParameter("question" + (i+1) + "_id"));
                String question_type = request.getParameter("question" + (i+1) + "_type");
                
                String answerContent = "";
                if(question_type.equals("checkbox")){
                    int options_count = Integer.parseInt(request.getParameter("question"+(i+1)+"_options_count"));
                    for (int j = 0; j < options_count; j++) {
                        String temp = request.getParameter("question"+(i+1)+"_answer"+(j+1));
                        if(temp != null){
                            if(answerContent.isEmpty())
                                answerContent += temp;
                            else
                                answerContent += "|" + temp;
                                        
                        }
                        
                    }
                }
                else{
                    answerContent = request.getParameter("question" + (i+1) + "_answer");
                }
                
                Answer answer = new Answer(answerContent, question_id, survey_answer_id);
                AnswerModel.save(answer);
            }
            
            response.sendRedirect(request.getContextPath() + "/Home");
        }
    }

    private void viewStatistics(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Integer user_id = (Integer)session.getAttribute("user_id");
        if(user_id == null)
            response.sendRedirect(request.getContextPath() + "/Home");
        else{
            int survey_id = Integer.parseInt(request.getParameter("survey_id"));
            
            Survey survey = SurveyModel.getByID(survey_id);
            ArrayList<Question> questions = QuestionModel.getBySurveyID(survey_id);
            
            ArrayList<SurveyAnswer> survey_answers = SurveyAnswerModel.getBySurveyID(survey_id);
            int totalNumberOfAnswers = survey_answers.size();
            
            HashMap<Integer,HashMap<String,Integer> > questions_answers = new HashMap<>();
            
            for (int i = 0; i < questions.size(); i++) {
                HashMap<String, Integer> answers_count = new HashMap<>();
                ArrayList<Answer> answers = AnswerModel.getByQuestionID(questions.get(i).getId());
                
                if(questions.get(i).getType().equals("checkbox") || 
                        questions.get(i).getType().equals("mcq"))
                {
                    ArrayList<Choice> choices = ChoiceModel.getByQuestionID(questions.get(i).getId());
                    
                    for (int j = 0; j < choices.size(); j++) {
                        answers_count.put(choices.get(j).getContent(), 0);
                    }
                }
                
                for (int j = 0; j < answers.size(); j++) 
                {
                    if(answers.get(j).getAnswerContent().isEmpty())
                        continue;
                    
                    if(answers_count.containsKey(answers.get(j).getAnswerContent())){
                        int old_count = answers_count.get(answers.get(j).getAnswerContent());
                        answers_count.replace(answers.get(j).getAnswerContent(), old_count, old_count+1);
                    }
                    else{
                        if(questions.get(i).getType().equals("checkbox")){
                            String[] answer_parts = answers.get(j).getAnswerContent().split("\\|");
                            for (int k = 0; k < answer_parts.length; k++) {
                                int old_count = answers_count.get(answer_parts[k]);
                                answers_count.replace(answer_parts[k], old_count, old_count+1);
                            }
                        }
                        else
                            answers_count.put(answers.get(j).getAnswerContent(), 1);
                    }
                }
                
                questions_answers.put(questions.get(i).getId(), answers_count);
            }
            request.setAttribute("Survey", survey);
            request.setAttribute("SurveyAnswers", survey_answers);
            request.setAttribute("Questions", questions);
            request.setAttribute("QuestionAnswers", questions_answers);
            
            request.getRequestDispatcher("Survey/SurveyStatistics.jsp").forward(request, response);
        }
    }


}
