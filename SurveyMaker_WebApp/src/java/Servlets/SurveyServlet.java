/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;
import Entities.question;
import Entities.survey;
import Models.QuestionModel;
import Models.SurveyModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet(name = "SurvetServlet", urlPatterns = {"/SurvetServlet"})
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SurvetServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SurvetServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
        processRequest(request, response);
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

public void getSurveyForm(HttpServletRequest request, HttpServletResponse response) throws IOException{
    
    response.sendRedirect("getSurveyForm.html");
}


public void addSurvey(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException{
           survey object = null;
           question quesObject = null ;
            
           String survey_title= request.getParameter("survey_title");
           object.setTitle(survey_title);
           
           int questions_count=Integer.parseInt(request.getParameter("questions_count"));
       
           
           for(int i = 0 ; i < questions_count ; i++){
              String question_type= request.getParameter("question"+(i+1)+"_type"); 
              String question_content= request.getParameter("question"+(i+1)+"_content"); 
              quesObject.setContent(question_content);
              
              
              if (question_type.equalsIgnoreCase("mcq")||question_type.equalsIgnoreCase("checkbox")){
                  
                  int question_options_count=Integer.parseInt(request.getParameter("question"+(i+1)+"_options_count"));
                  
                  for(int j = 0 ; j < question_options_count ; j++){
                      
                      String question_option= request.getParameter("question"+(i+1)+"_option"+(i+1)+""); 
                  }
              }

           }
           SurveyModel.saveSurvey(object);
           QuestionModel.savequestion(quesObject);
            
      
    }

}